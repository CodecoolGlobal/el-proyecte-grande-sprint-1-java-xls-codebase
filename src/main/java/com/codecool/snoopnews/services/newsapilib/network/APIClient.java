package com.codecool.snoopnews.services.newsapilib.network;

import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class APIClient {

    private Retrofit retrofit;
    private final String BASE_URL = "https://newsapi.org";

    public APIClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public APIService getAPIService(){
        return retrofit.create(APIService.class);
    }

}

