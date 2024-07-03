package com.example.jh.service.indicator;

import com.example.jh.model.CoinPriceModel;
import com.example.jh.service.Indicator;
import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class BollingerBand implements Indicator {

  @Override
  public void caculate(CoinPriceModel[] candles, String[] params) {
    if (params.length != 2) {
      throw new IllegalArgumentException();
    }
    int len = Integer.parseInt(params[0]);
    int k = Integer.parseInt(params[1]);
    double tempSum = 0;
    for (int i = 0; i < len; i++) {
      tempSum += candles[i].getClose();
    }

    for (int i = 0; i < candles.length - len; i++) {

      double mdd = tempSum / len;
      double variance = 0;
      for (int j = i; j < i + len; j++) {
        variance += Math.pow(candles[j].getClose() - mdd, 2);
      }

      variance = Math.sqrt(variance / len);

      if (candles[i].isIndiNull()) {
        candles[i].setIndi(new HashMap<>());
      }

      candles[i].getIndi().put("BollingerBand-hdd(" + len + "," + k + ")", mdd + (k * variance));
      candles[i].getIndi().put("BollingerBand-mdd(" + len + "," + k + ")", mdd);
      candles[i].getIndi().put("BollingerBand-ldd(" + len + "," + k + ")", mdd - (k * variance));

      tempSum += candles[i + len].getClose();
      tempSum -= candles[i].getClose();
    }

  }
}
