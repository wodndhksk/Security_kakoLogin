package com.fish.encyclopedia.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fish.encyclopedia.database.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
public class JwtFactory {
    private static final Logger log = LoggerFactory.getLogger(JwtFactory.class);
    private static String signingKey = "jwttest";

    /**
     * 토큰 만들기
     * @param context
     * @return
     */
    public String generateToken(AccountContext context){
        String token = null;
//        List<GrantedAuthority> authorities = (List)context.getAuthorities();

        try {
            token = JWT.create()
                    .withIssuer("Jaewoo_jae")
                    .withClaim("USER_ROLE", context.getAccount().getUserRole().getRoleName())
                    .sign(generateAlgorithm());
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return token;
    }

    private Algorithm generateAlgorithm() throws UnsupportedEncodingException{
        return Algorithm.HMAC256(signingKey);
    }
}
