package com.example.jh.controller;

import com.example.jh.service.IndicatorManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IndicatorController {

    private final IndicatorManagerService indicatorManagerService;
    @GetMapping("/indicator")
    public List<String> getAllIndicatorList(){
        return indicatorManagerService.getIndicatorList();
    }
}
