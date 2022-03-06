package com.fish.encyclopedia.account;

import com.fish.encyclopedia.database.entity.Account;
import com.fish.encyclopedia.database.repository.AccountRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoApi {

    @Value("${Kakao.client_id}")
    private String CLIEND_ID;

    @Value("${Kakao.grant_type}")
    private String GRANT_TYPE;

    @Value("${Kakao.access_token}")
    private String ACCESS_TOKEN;

    @Value("${Kakao.user_info}")
    private String USER_INFO;

    @Value("${Kakao.logout}")
    private String LOGOUT_URL;

    @Value("${Kakao.disconnect}")
    private String DISCONNECT_URL;

    @Value("${Kakao.redirect_uri}")
    private String REDIRECT_URI;

    private final AccountRepository accountRepository;

    public String getAccessToken(String code) {

        String accessToken = "";
        String refreshToken = "";
        String reqURL = ACCESS_TOKEN;

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type="+GRANT_TYPE);
            sb.append("&client_id="+CLIEND_ID);// 내 api key
            sb.append("&redirect_uri="+REDIRECT_URI);
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
        String reqUrl = USER_INFO;
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

            System.out.println("email : " + email);
            userInfo.put("nickname", nickName);
            userInfo.put("email", email);

        }catch (Exception e){
            e.printStackTrace();
        }
        return userInfo;
    }

    public void kakaoLogout(String accessToken) {
        String reqURL = LOGOUT_URL;

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
        String reqURL = DISCONNECT_URL;

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

    /**
     * 신규 혹은 가입된 회원인지 확인
     * @param email
     */
    public void checkUserEmail(String email){

        Account kakaoUser = accountRepository.findByEmail(email).orElse(null);

        if(kakaoUser == null){
            Account account = Account.builder().email(email).build();
            accountRepository.save(account);
        }
    }

}
