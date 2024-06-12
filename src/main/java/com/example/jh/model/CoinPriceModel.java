package com.example.jh.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CoinPriceModel {
  private LocalDateTime date;
  private double high;
  private double low;
  private double open;
  private double close;
  private double volume;
}
