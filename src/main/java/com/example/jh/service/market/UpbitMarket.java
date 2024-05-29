package com.example.jh.service.market;

import com.example.jh.model.CoinPriceModel;
import com.example.jh.service.Market;
import java.util.List;

public class UpbitMarket implements Market {

  @Override
  public List<String> getAllCoinList() {
    return null;
  }

  @Override
  public CoinPriceModel[] getCoinPrices(String cointicker, int interval, int length) {
    return new CoinPriceModel[0];
  }
}
