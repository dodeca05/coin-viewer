package com.example.jh.model;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CoinPriceModel {
  private Date date;
  private double high;
  private double low;
  private double open;
  private double close;
}
