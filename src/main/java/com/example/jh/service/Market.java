package com.example.jh.service;

import com.example.jh.model.CoinPriceModel;
import java.util.List;
import org.springframework.stereotype.Service;

/*
마켓 클래스
 */
@Service
public interface Market {

  List<String> getAllCoinList();

  CoinPriceModel[] getCoinPrices(String cointicker,int interval, int length);

}
