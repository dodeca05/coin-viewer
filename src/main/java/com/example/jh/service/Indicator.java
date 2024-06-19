package com.example.jh.service;

import com.example.jh.model.CoinPriceModel;


/*
보조지표를 위한 인터페이스
 */
public interface Indicator {

  void caculate(CoinPriceModel[] candles, String[] params);


}
