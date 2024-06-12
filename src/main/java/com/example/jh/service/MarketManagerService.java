package com.example.jh.service;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class MarketManagerService {


  private Map<String, Market> marketMap;

  public MarketManagerService(ApplicationContext applicationContext) {
    marketMap = applicationContext.getBeansOfType(Market.class);
  }

  public List<String> getMarketList()//Todo : 모든 마켓 조회
  {
    return marketMap.keySet().stream().toList();
  }

  public Market getMarket(String marketName) {
    return marketMap.get(marketName);
  }

}
