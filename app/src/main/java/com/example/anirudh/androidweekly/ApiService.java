package com.example.anirudh.androidweekly;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/archive")
    Observable<ArrayList<String>> fetchAndroidWeekly();
}
