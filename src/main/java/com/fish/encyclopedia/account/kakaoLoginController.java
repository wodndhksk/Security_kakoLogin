package com.fish.encyclopedia.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@Slf4j
@RequiredArgsConstructor
public class kakaoLoginController {

    private final KakaoApi kakaoApi;

    @RequestMapping(value = "/")
    public String index() {
        ModelAndView mv = new ModelAndView();

        return "index";
    }
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam("code") String code, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        // 인증코드 요청 전달
        String accessToken = kakaoApi.getAccessToken(code);
        // 인증코드 토큰 전달
        HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
        System.out.println("login Info : " + userInfo.toString());

        if(userInfo.get("email") != null){
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_token", accessToken);
        }
        mv.addObject("userId", userInfo.get("email"));
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session){
        ModelAndView mv = new ModelAndView();

        kakaoApi.kakaoLogout((String)session.getAttribute("access_token"));
        session.removeAttribute("access_token");
        session.removeAttribute("userId");
        mv.setViewName("index");

        return mv;
    }

    @RequestMapping(value = "/disconnect")
    public ModelAndView disconnect (@RequestParam("code") String code){
        ModelAndView mv = new ModelAndView();
        String accessToken = kakaoApi.getAccessToken(code);


        mv.setViewName("index");
        return mv;
    }


}
