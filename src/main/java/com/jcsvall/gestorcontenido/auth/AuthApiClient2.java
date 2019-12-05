/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcsvall.gestorcontenido.auth;

import com.jcsvall.gestorcontenido.interfaces.AuthApiService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author scjuan
 */
public class AuthApiClient2 {
     private static AuthApiClient2 instance = null;
    private Retrofit retrofit;
    private AuthApiService authApiservice;

    public AuthApiClient2() {
        OkHttpClient cliente = UnsafeOkHttpClient.getUnsafeOkHttpClient(); //to pass bad certificates
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        //OkHttpClient cliente = okHttpClientBuilder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://osjpii.ciexelsalvador.gob.sv:8083/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(cliente)
                .build();

        authApiservice = retrofit.create(AuthApiService.class);
    }

    public static AuthApiClient2 getInstance() {
        if (instance == null) {
            instance = new AuthApiClient2();
        }
        return instance;
    }

    public AuthApiService getAuthApiService() {
        return authApiservice;
    }
}
