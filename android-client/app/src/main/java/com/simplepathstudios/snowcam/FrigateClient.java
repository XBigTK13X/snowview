package com.simplepathstudios.snowcam;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.http.GET;

interface FrigateService {
    @GET("api/config")
    Call<FrigateConfig> getConfig();
}

public class FrigateClient {
    private static final String TAG = "ApiClient";
    private static FrigateClient __instance;
    public static FrigateClient getInstance(){
        if(__instance == null){
            __instance = new FrigateClient();
        }
        return __instance;
    }

    private FrigateService httpClient;
    private FrigateClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SnowcamSettings.FrigateApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.httpClient = retrofit.create(FrigateService.class);
    }

    public Call loadConfig(){
        return this.httpClient.getConfig();
    }
}
