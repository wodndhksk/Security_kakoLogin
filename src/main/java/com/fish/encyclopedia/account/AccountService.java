package com.fish.encyclopedia.account;

import com.fish.encyclopedia.database.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

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

    private AccountRepository accountRepository;
}
