/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcsvall.gestorcontenido.auth;

import com.jcsvall.gestorcontenido.interceptors.AuthInterceptor;
import com.jcsvall.gestorcontenido.interfaces.AuthApiService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author scjuan
 */
public class AuthApiClient {
    private static AuthApiClient instance = null;
    private Retrofit retrofit;
    private AuthApiService authApiservice;

    public AuthApiClient() { 
        //descomentar para prod
        //OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        //okHttpClientBuilder.addInterceptor(new AuthInterceptor());
        //OkHttpClient cliente = okHttpClientBuilder.build();
        //////////////
        OkHttpClient cliente = UnsafeOkHttpClient.getUnsafeOkHttpClient(); //to pass bad certificates, comentar para prod

        retrofit = new Retrofit.Builder()
                .baseUrl("https://osjpii.ciexelsalvador.gob.sv:8083/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(cliente)
                .build();

        authApiservice = retrofit.create(AuthApiService.class);
    }

    public static AuthApiClient getInstance() {
        if (instance == null) {
            instance = new AuthApiClient();
        }
        return instance;
    }

    public AuthApiService getAuthApiService() {
        return authApiservice;
    }
}
