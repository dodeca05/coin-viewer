package com.example.jh.controller;

import com.example.jh.model.CoinPriceModel;
import com.example.jh.service.IndicatorManagerService;
import com.example.jh.service.MarketManagerService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoinController {

  private final MarketManagerService marketManagerService;

  private final IndicatorManagerService indicatorManagerService;

  @Operation(summary = "마켓 목록 조회", description = "마켓 목록을 조회 합니다.")
  @GetMapping("/market")
  List<String> getMarkets() {
    return marketManagerService.getMarketList();
  }

  @Operation(summary = "코인 목록 조회", description = "해당 마켓에서 거래중인 코인 목록을 조회 합니다.")
  @GetMapping("/market/{market}/coin")
  List<String> getCoins(@PathVariable String market) {
    return marketManagerService.getMarket(market).getAllCoinList();
  }

  @Operation(summary = "코인가격 조회", description = "해당 코인의 현재 candles 정보를 가져옵니다.")
  @GetMapping("/market/{market}/coin/{coin}")
  List<CoinPriceModel> getCoin(@PathVariable String market,
      @PathVariable String coin,
      @RequestParam(value = "len", required = false, defaultValue = "20") Integer length,
      @RequestParam(value = "interval", required = false, defaultValue = "15") Integer interval,
      @RequestParam(value = "indi", required = false) String indi) {
    int len = 20;
    int itv = 5;
    if (length != null) {
      len = length;
    }
    if (interval != null) {
      itv = interval;
    }

    CoinPriceModel[] candles = marketManagerService.getMarket(market)
        .getCoinPrices(coin, itv, len);
    if (indi == null || indi.isEmpty()) {
      return Arrays.stream(candles).toList();
    }

    CoinPriceModel[] caculatedCandles=indicatorManagerService.caculate(candles,indi);
    return Arrays.stream(caculatedCandles).toList();
  }
}
