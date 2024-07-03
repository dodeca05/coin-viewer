package com.example.jh.service.indicator;

import com.example.jh.model.CoinPriceModel;
import com.example.jh.service.Indicator;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EMA implements Indicator {
    @Override
    public void caculate(CoinPriceModel[] candles, String[] params) {
        if (params.length != 1) {
            throw new IllegalArgumentException();
        }
        int len = Integer.parseInt(params[0]);
        double temp = 0;
        for(int i=0;i<len;i++)
            temp+=candles[candles.length-i-1].getClose();
        temp/=len;
        double alpha=2/((double)len+1);
        for(int i=candles.length-len;i>=0;i--){
            if (candles[i].isIndiNull()) {
                candles[i].setIndi(new HashMap<>());
            }
            candles[i].getIndi().put("EMA(" + len + ")",temp);

            if(i==0)break;

            temp=(candles[i-1].getClose()-temp)*alpha+temp;
        }

    }
}
