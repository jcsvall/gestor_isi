/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcsvall.gestorcontenido.main;

import com.jcsvall.gestorcontenido.auth.AuthApiClient;
import com.jcsvall.gestorcontenido.interfaces.AuthApiService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 *
 * @author scjuan
 */
public class UploadObjectBytes {
    
    public static void main(String[] args) {
        try {
            AuthApiService authApiService;
            AuthApiClient authApiClient;
            authApiClient = AuthApiClient.getInstance();
            authApiService = authApiClient.getAuthApiService();

            String path = "C:/Users/scjuan/";
            File file = new File(path + "CertificadosCiexSV.pdf");
            byte[] fileContent = Files.readAllBytes(file.toPath());
            
            // create RequestBody instance from file
            RequestBody fileReqBody = RequestBody.create(MediaType.parse("application/*"), fileContent);
            
            // Create MultipartBody.Part using file request-body,file name and part name 
            MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);
            
            //Create request body with text description and text media type
            RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "pdf-type");

            Map<String, String> headers = new HashMap<>();
            headers.put("X-Object-Meta-name", "CertificadosCiexSV");
            headers.put("X-Object-Meta-test", "Prueba de metadata");
            
            Call<ResponseBody> call2 = authApiService.uploadFileWithHeaders(headers,"CertificadosCiexSV", description, part);
            Response<ResponseBody> response = call2.execute();

            if (response.isSuccessful()) {                
                System.out.println("OK");                
            } else {
                System.out.println("Fallo");
            }

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
