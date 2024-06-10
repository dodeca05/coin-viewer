package com.example.jh.service;

import com.example.jh.model.CoinPriceModel;
import com.example.jh.model.CoinPriceWithIndicatorModel;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class IndicatorManagerService {
  private Map<String,Indicator> indicatorMap;
  public IndicatorManagerService(){

  }

  public CoinPriceWithIndicatorModel[] caculate(CoinPriceModel[] candles,String indicatorQuery)
  {
    return null;
  }

}
