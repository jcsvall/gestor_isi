/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcsvall.gestorcontenido.interceptors;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author scjuan
 */
public class AuthInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        try {          
            //AUTH_tkc94005ec411aa101f5e9d37580a764c2  token vencido
            String token = "AUTH_tk0da7e030a5fe52810f1b53f884a2ec76";
            Request request = chain.request().newBuilder().addHeader("X-Auth-Token", token).build();
            response = chain.proceed(request);
            System.out.println("OK");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("error: "+e.getMessage());
        }
        return response;
    }
}
