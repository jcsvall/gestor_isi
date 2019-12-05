/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcsvall.gestorcontenido.main;

import com.jcsvall.gestorcontenido.auth.AuthApiClient2;
import com.jcsvall.gestorcontenido.interfaces.AuthApiService;
import com.jcsvall.gestorcontenido.pojos.Login;
import java.io.IOException;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Response;

/**
 *
 * @author scjuan
 */
public class App {

    public static void main(String[] args) {
        try {
            
//            KeyStore.Entry newEntry = new KeyStore.TrustedCertificateEntry(someCert);
//            ks.setEntry("someAlias", newEntry, null);            
            AuthApiService authApiService;
            AuthApiClient2 authApiClient;
            authApiClient = AuthApiClient2.getInstance();
            authApiService = authApiClient.getAuthApiService();

//            Login login = new Login();
//
//            Call<String> call = authApiService.getPermiso(login);
//            Response<String> response = call.execute();
            Map<String, String> headers = new HashMap<>();
            headers.put("X-Auth-User", "SWIFTACCOUNT_L_SYS:devuser");
            headers.put("X-Auth-Key", "isilonBCR2019");

            Call<Login> call2 = authApiService.getUserToken(headers);
           // SSLTool.disableCertificateValidation();
            Response<Login> response = call2.execute();

            if (response.isSuccessful()) {
                Headers head = response.headers();
                String token = head.get("X-Auth-Token");
                Login body = response.body();
                System.out.println("TOKEN: "+token);
                if (body.getStorage() != null) {
                    System.out.println("cluster_name: " + body.getStorage().getClusterName());
                }
            } else {
                System.out.println("Fallo");
            }

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
