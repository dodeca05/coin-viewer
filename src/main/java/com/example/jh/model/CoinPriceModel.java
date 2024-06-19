package com.example.jh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoinPriceModel {

  private LocalDateTime date;
  private double high;
  private double low;
  private double open;
  private double close;
  private double volume;
  Map<String, Object> indi;

  @JsonIgnore
  public boolean isIndiNull() {
    return indi == null;
  }
}
