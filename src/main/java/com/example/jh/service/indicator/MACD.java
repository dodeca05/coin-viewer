package com.example.jh.service.indicator;

import com.example.jh.model.CoinPriceModel;
import com.example.jh.service.Indicator;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MACD implements Indicator {
    private final EMA ema;
    @Override
    public void caculate(CoinPriceModel[] candles, String[] params) {
        if (params.length != 3) {
            throw new IllegalArgumentException();
        }
        int shotLen=Integer.parseInt(params[0]);
        int longLen=Integer.parseInt(params[1]);
        int signalLen= Integer.parseInt(params[2]);

        String shotEmaKey="EMA("+shotLen+")";
        String longEmaKey="EMA("+longLen+")";

        String macdKey="MACD("+shotLen+","+longLen+")";
        String macdSignalKey="MACD-SIGNAL("+signalLen+")";

        if(candles[0].isIndiNull() || !candles[0].getIndi().keySet().contains(shotEmaKey))
        {
            String[]tempParam={""+shotLen};
            ema.caculate(candles,tempParam);
        }
        if(!candles[0].getIndi().keySet().contains(longEmaKey))
        {
            String[]tempParam={""+longLen};
            ema.caculate(candles,tempParam);
        }
        for(int i=0;i<candles.length-longLen;i++)
        {
            double macd=(double)candles[i].getIndi().get(shotEmaKey)
                    - (double)candles[i].getIndi().get(longEmaKey);
            candles[i].getIndi().put(macdKey,macd);
        }
        double tempSum=0;
        for(int i=0;i<signalLen;i++){
            tempSum+=(double)candles[i].getIndi().get(macdKey);
        }
        for(int i=0;i<candles.length-longLen-signalLen;i++)
        {
            double signal=tempSum/signalLen;
            candles[i].getIndi().put(macdSignalKey,signal);
            candles[i].getIndi().put("MACD-HIST",(double)candles[i].getIndi().get(macdKey)-signal);
            tempSum-=(double)candles[i].getIndi().get(macdKey);
            tempSum+=(double)candles[i+signalLen].getIndi().get(macdKey);
        }


    }
}
