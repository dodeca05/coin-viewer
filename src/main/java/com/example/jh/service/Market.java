package com.example.jh.service;

import com.example.jh.model.CoinPriceModel;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface Market {

  List<String> getAllCoinList();

  CoinPriceModel[] getCoinPrices(String cointicker,int interval, int length);

}
