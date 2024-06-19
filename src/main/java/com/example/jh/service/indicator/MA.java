package com.example.jh.service.indicator;

import com.example.jh.model.CoinPriceModel;
import com.example.jh.service.Indicator;
import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class MA implements Indicator {

  @Override
  public void caculate(CoinPriceModel[] candles, String[] params) {
    if (params.length != 1) {
      throw new IllegalArgumentException();
    }
    int len = Integer.parseInt(params[0]);
    double tempSum = 0;
    for (int i = 0; i < len; i++) {
      tempSum += candles[i].getClose();
    }

    for (int i = 0; i < candles.length - len; i++) {
      if (candles[i].isIndiNull()) {
        candles[i].setIndi(new HashMap<>());
      }
      candles[i].getIndi().put("MA(" + len + ")", tempSum / len);

      tempSum += candles[i + len].getClose();
      tempSum -= candles[i].getClose();
    }

  }
}
