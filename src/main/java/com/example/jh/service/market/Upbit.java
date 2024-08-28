package com.example.jh.service.market;

import com.example.jh.model.CoinPriceModel;
import com.example.jh.service.Market;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class Upbit implements Market {

    private final WebClient webClient;
    private final DateTimeFormatter formatter;

    public Upbit() {
        this.webClient = WebClient.create("https://api.upbit.com");
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    }

    @SneakyThrows
    @Override
    public List<String> getAllCoinList() {

        String response = webClient.get()
                .uri("/v1/market/all?isDetails=false")
                .retrieve()
                .bodyToMono(String.class).block();
        List<String> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(response);
        if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                JsonNode nameNode = node.get("market");
                if (nameNode != null) {
                    String coinName = nameNode.asText();
                    if (nameNode.asText().startsWith("KRW")) { //KRW거래 가능한것만 표시하자
                        result.add(coinName);
                    }
                }
            }
        }
        return result;

    }

    @SneakyThrows
    @Override
    public CoinPriceModel[] getCoinPrices(String cointicker, int interval, int length) {
        CoinPriceModel[] result = new CoinPriceModel[length];
        ObjectMapper objectMapper = new ObjectMapper();
        int index = 0;
        int remainCount=length;
        while (index < length) {

            String url="/v1/candles/minutes/" + interval + "?market=" + cointicker + "&count=" + Math.min(remainCount, 200);
            if(index!=0)
              url+="&to="+result[index-1].getDate().format(formatter);
            String response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class).block();
            JsonNode jsonNode = objectMapper.readTree(response);


            if (jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    JsonNode singleNode = node.get("market");
                    if (singleNode != null) {
                        result[index] = CoinPriceModel.builder()
                                .date(LocalDateTime.parse(node.get("candle_date_time_utc").asText(), formatter))
                                .high(node.get("high_price").asDouble())
                                .low(node.get("low_price").asDouble())
                                .open(node.get("opening_price").asDouble())
                                .close(node.get("trade_price").asDouble())
                                .volume(node.get("candle_acc_trade_volume").asDouble())
                                .build();
                        index++;
                    }
                }
            }
          remainCount-=200;
        }

        return result;
    }
}
