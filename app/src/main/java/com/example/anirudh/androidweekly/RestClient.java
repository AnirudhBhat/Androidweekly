package com.example.anirudh.androidweekly;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RestClient {
    static public Retrofit retrofit;

    private RestClient() {

    }

    static Retrofit getInstance() {
        return retrofit;
    }

    public static ApiService fetchAndroidWeekly() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://androidweekly.net/")
                .addConverterFactory(new AndroidWeeklyConverter.Factory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(ApiService.class);
    }
}
