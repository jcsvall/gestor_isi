/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcsvall.gestorcontenido.interfaces;

import com.jcsvall.gestorcontenido.pojos.Login;
import java.util.List;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 *
 * @author scjuan
 */
public interface AuthApiService {

    @POST("AUTH_SWIFTACCOUNT_L_SYS/repsit02/")
    Call<String> getPermiso(@Body Login login);

    @GET("auth/v1.0")
    Call<Login> getUserToken(@HeaderMap Map<String, String> headers);
    
    @GET("AUTH_SWIFTACCOUNT_L_SYS/repsit02/{objeto}")
    Call<ResponseBody> getFileValue(@Path("objeto") String objeto);
    
    @Multipart
    @PUT("AUTH_SWIFTACCOUNT_L_SYS/repsit02/{objeto}")
    Call<ResponseBody> uploadFile(@Path("objeto") String objeto,@Part("description") RequestBody description,
        @Part MultipartBody.Part file);
    
    @Multipart
    @PUT("AUTH_SWIFTACCOUNT_L_SYS/repsit02/{objeto}")
    Call<ResponseBody> uploadFileWithHeaders(@HeaderMap Map<String, String> headers,@Path("objeto") String objeto,@Part("description") RequestBody description,
        @Part MultipartBody.Part file);
    
}
