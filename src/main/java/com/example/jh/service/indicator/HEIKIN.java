package com.example.jh.service.indicator;

import com.example.jh.model.CoinPriceModel;
import com.example.jh.service.Indicator;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class HEIKIN implements Indicator {
    @Override
    public void caculate(CoinPriceModel[] candles, String[] params) {
        CoinPriceModel lastInfo=candles[candles.length-1];
        double haClose=(lastInfo.getClose()+lastInfo.getOpen()+lastInfo.getHigh()+lastInfo.getLow())/4;
        double haOpen=(lastInfo.getClose()+lastInfo.getOpen())/2;
        double haMax=lastInfo.getHigh();
        double haMin=lastInfo.getLow();
        for(int i=candles.length-2;i>=0;i--)
        {
            CoinPriceModel thisPriceInfo=candles[i];
            haOpen=(haOpen+haClose)/2;
            haClose=(thisPriceInfo.getClose()+ thisPriceInfo.getHigh()+ thisPriceInfo.getLow()+thisPriceInfo.getOpen())/4;
            haMax=Math.max(thisPriceInfo.getHigh(),Math.max(haOpen,haClose));
            haMin=Math.min(thisPriceInfo.getLow(),Math.min(haOpen,haClose));
            if(thisPriceInfo.isIndiNull())
                thisPriceInfo.setIndi(new HashMap<>());
            thisPriceInfo.getIndi().put("HEKIN-OPEN",haOpen);
            thisPriceInfo.getIndi().put("HEKIN-CLOSE",haClose);
            thisPriceInfo.getIndi().put("HEKIN-HIGH",haMax);
            thisPriceInfo.getIndi().put("HEKIN-LOW",haMin);

            thisPriceInfo.getIndi().put("HEKIN-STATUS",haOpen<haClose ? "UP" : "DONW");

        }
    }
}
