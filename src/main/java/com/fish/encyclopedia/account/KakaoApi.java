package com.fish.encyclopedia.account;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class KakaoApi {

    @Value("${restapi.kakao.loginkey}")
    private String KAKAO_API_KEY;

    @Value("${HOME.URL}")
    private String URL;


    public String getAccessToken(String code) {
        System.out.println("KAKAO_API_KEY : " + KAKAO_API_KEY);
        System.out.println("URL : " + URL);

        String accessToken = "";
        String refreshToken = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=cc55a9f1d718f1adfc4786e46a20f00c");// 내 api key
            sb.append("&redirect_uri=http://localhost:8080/login");
            sb.append("&code="+code);

            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("getAccessToken_responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while((line = br.readLine()) != null)
                result += line;

            System.out.println("response body : " + result);

            JsonObject parser = JsonParser.parseString(result).getAsJsonObject();
            accessToken= parser.getAsJsonObject().getAsJsonPrimitive("access_token").getAsString();
            refreshToken= parser.getAsJsonObject().getAsJsonPrimitive("refresh_token").getAsString();

            System.out.println("response body_accessToken : " + accessToken);
            System.out.println("response body_refreshToken : " + refreshToken);

            br.close();
            bw.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return accessToken;
    }

    public HashMap<String, Object> getUserInfo(String accessToken) {

        HashMap<String, Object> userInfo = new HashMap<>();
        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        try{
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int ResponseCode = conn.getResponseCode();
            System.out.println("getUserInfo_ResponseCode : " + ResponseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";
            while((line = br.readLine()) != null){
                result += line;
            }
            System.out.println("responseBody : " + result);

            JsonObject parser = JsonParser.parseString(result).getAsJsonObject();
            System.out.println("parser : " + parser);
            JsonObject properties = parser.getAsJsonObject("properties");
            JsonObject kakao_account = parser.getAsJsonObject("kakao_account");

            String nickName = properties.getAsJsonObject().getAsJsonPrimitive("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().getAsJsonPrimitive("email").getAsString();

            userInfo.put("nickname", nickName);
            userInfo.put("email", email);

        }catch (Exception e){
            e.printStackTrace();
        }
        return userInfo;
    }

    public void kakaoLogout(String accessToken) {
        String reqURL = "https://kapi.kakao.com/v1/user/logout";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer" + accessToken);

            int responseCode = conn.getResponseCode();
            System.out.println("kakaoLogout_responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while((line = br.readLine()) != null)
                result += line;

            System.out.println(result);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void disconnection(String accessToken) {
        String reqURL = "https://kapi.kakao.com/v1/user/unlink";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer" + accessToken);

            int responseCode = conn.getResponseCode();
            System.out.println("kakaoLogout_responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while((line = br.readLine()) != null)
                result += line;

            System.out.println(result);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
