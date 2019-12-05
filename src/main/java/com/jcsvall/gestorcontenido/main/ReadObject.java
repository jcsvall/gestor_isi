/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcsvall.gestorcontenido.main;

import com.jcsvall.gestorcontenido.auth.AuthApiClient;
import com.jcsvall.gestorcontenido.interfaces.AuthApiService;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import sun.misc.IOUtils;

/**
 *
 * @author scjuan
 */
public class ReadObject {

    public static void main(String[] args) {
        try {
            AuthApiService authApiService;
            AuthApiClient authApiClient;
            authApiClient = AuthApiClient.getInstance();
            authApiService = authApiClient.getAuthApiService();

            Call<ResponseBody> call2 = authApiService.getFileValue("CertificadosCiexSV");
            Response<ResponseBody> response = call2.execute();
            if (response.isSuccessful()) {
                Headers head = response.headers();
                String name = head.get("Content-Type");
                ResponseBody body = response.body();
                byte[] object = body.bytes();
                InputStream object2 = body.byteStream();
                System.out.println("Documento: " + body);

                String path = "C:/Users/scjuan/";
                File file = new File(path+ "CertificadosCiexSV.pdf");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(object);
                fileOutputStream.close();
                InputStream is = new BufferedInputStream(new ByteArrayInputStream(object));
                String mimeType = URLConnection.guessContentTypeFromStream(is);                
                Reader reader =body.charStream();
                String str = readerData(reader);
                System.out.println("mimeType: " + mimeType);
                String data="";
                int valor = is.read();
                int count=0;
                while (valor != -1) {                    
                    System.out.print((char)valor);
                    valor = is.read();
                    count++;
                    if(count==300){valor=-1;}
                }
                
                
                //String tipodeArchivo = Files.probeContentType(object);

//                FileOutputStream fileOuputStream = new FileOutputStream("C:\\Users\\scjuan\\objetoPrueba.JPG");
//			fileOuputStream.write(object);
//			fileOuputStream.close();
//                System.out.println("Documento: "+body);
            } else {
                System.out.println("Fallo");
                Reader reader = response.errorBody().charStream();
                String str = readerData(reader);
                System.out.println(str);
            }

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String readerData(Reader reader) throws IOException {
        int intValueOfChar;
        String targetString = "";
        while ((intValueOfChar = reader.read()) != -1) {
            targetString += (char) intValueOfChar;
        }
        reader.close();
        if (!targetString.isEmpty() && targetString.startsWith("[")) {
            targetString = targetString.substring(1, targetString.length() - 1);
        }

        return targetString;
    }

}
