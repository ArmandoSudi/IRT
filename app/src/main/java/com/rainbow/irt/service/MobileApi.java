package com.rainbow.irt.service;

import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sugar on 12/3/2018
 */
public class MobileApi {

    public static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static final MobileApiInterface SERVICE = RETROFIT.create(MobileApiInterface.class);

    public static MobileApiInterface getService() {
        return SERVICE;
    }
}
