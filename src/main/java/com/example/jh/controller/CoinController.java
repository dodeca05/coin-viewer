package com.example.jh.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinController {

  @Operation(summary = "마켓 목록 조회", description = "마켓 목록을 조회 합니다.")
  @GetMapping("/market")
  List<String>getMarkets(){
    return null;
  }

  @GetMapping("/market/{market}/coin")
  String getCoins(@PathVariable String market){
    return market;
  }

  @GetMapping("/market/{market}/coin/{coin}")
  String getCoin(@PathVariable String market,@PathVariable String coin)
  {
    return market+" "+coin;
  }
}
