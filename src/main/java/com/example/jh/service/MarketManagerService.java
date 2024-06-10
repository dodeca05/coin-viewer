package com.example.jh.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MarketManagerService {
  private Map<String,Market> marketMap;
  public MarketManagerService(){

  }
  public List<String> getMarketList()//Todo : 모든 마켓 조회
  {
    return marketMap.keySet().stream().toList();
  }

  public Market getMarket(String marketName)
  {
    return marketMap.get(marketName);
  }

}
