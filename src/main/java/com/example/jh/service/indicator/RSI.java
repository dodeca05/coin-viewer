package com.example.jh.service.indicator;

import com.example.jh.model.CoinPriceModel;
import com.example.jh.service.Indicator;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RSI implements Indicator {
    @Override
    public void caculate(CoinPriceModel[] candles, String[] params) {
        if (params.length != 1) {
            throw new IllegalArgumentException();
        }
        int len=Integer.parseInt(params[0]);
        double[]gain=new double[candles.length];
        double[]loss=new double[candles.length];
        double tempGainSum=0;
        double tempLossSum=0;
        for(int i=0;i<candles.length;i++)
        {
            double distance=candles[i].getClose()-candles[i].getOpen();

            if(distance>0){
                gain[i]=distance;
                loss[i]=0;
            }
            else{
                gain[i]=0;
                loss[i]=-distance;
            }
        }
        for(int i=0;i<len;i++)
        {
            tempGainSum+=gain[i];
            tempLossSum+=loss[i];
        }
        for(int i=0;i<candles.length-len;i++)
        {
            double rs=(tempGainSum/len)/(tempLossSum/len);
            if(candles[i].isIndiNull())
                candles[i].setIndi(new HashMap<>());
            candles[i].getIndi().put("RSI("+len+")",100.0d-(100.0d/(1+rs)));
            tempGainSum-=gain[i];
            tempGainSum+=gain[i+len];

            tempLossSum-=loss[i];
            tempLossSum+=loss[i+len];

        }
    }
}
