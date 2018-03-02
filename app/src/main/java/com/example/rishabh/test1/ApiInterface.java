package com.example.rishabh.test1;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rishabh on 2/28/18.
 */

public interface ApiInterface {

    @GET("jsonparsetutorial.txt")
    Observable<CountryList> getCountryInfo();
}
