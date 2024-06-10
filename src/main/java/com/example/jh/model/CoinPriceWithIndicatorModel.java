package com.example.jh.model;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/*
@Getter
@Setter
@Builder
*/

public class CoinPriceWithIndicatorModel extends CoinPriceModel{

  CoinPriceWithIndicatorModel(Date date, double high, double low, double open, double close) {
    super(date, high, low, open, close);
  }
}
