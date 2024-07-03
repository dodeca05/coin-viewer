package com.example.jh.service;

import com.example.jh.model.CoinPriceModel;

import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class IndicatorManagerService {

  private Map<String, Indicator> indicatorMap;

  public IndicatorManagerService(ApplicationContext applicationContext) {
    indicatorMap = applicationContext.getBeansOfType(Indicator.class);
  }

  public List<String> getIndicatorList(){
    return indicatorMap.keySet().stream().toList();
  }

  public void caculate(CoinPriceModel[] candles, String indicatorQuery) {

    String[] indis = indicatorQuery.split(";");
    for (String indi : indis) {
      String indiName = indi.replaceAll("\\(.*\\)", "");
      String[] params = indi.replaceAll(".*\\(|\\)", "").split(",");
      indicatorMap.get(indiName).caculate(candles, params);
    }

  }

}
