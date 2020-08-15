package com.kmmoonlight.evilcomic;

import android.app.Application;

import rxhttp.RxHttp;
import rxhttp.wrapper.callback.Function;
import rxhttp.wrapper.param.Param;

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        RxHttp.setDebug(true);
        initRxHttpCommonHeader();
    }




    private void initRxHttpCommonHeader() {


        RxHttp.setOnParamAssembly(new Function<Param, Param>() {
            @Override
            public Param apply(Param param) throws Exception {
                param.addHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
                param.addHeader("Accept", "text/html");
                param.addHeader("Connection", "keep-alive");
                param.addHeader("Content-Type", "text/html;charset=UTF-8");
                return param;
            }
        });


    }
}
