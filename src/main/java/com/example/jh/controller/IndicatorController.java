package com.example.jh.controller;

import com.example.jh.service.IndicatorManagerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IndicatorController {

    private final IndicatorManagerService indicatorManagerService;
    @GetMapping("/indicator")
    @Operation(summary = "보조지표 목록 조회", description = "보조지표 목록을 조회 합니다.")
    public List<String> getAllIndicatorList(){
        return indicatorManagerService.getIndicatorList();
    }
}
