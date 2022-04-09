package com.fish.encyclopedia.security.providers;

import com.fish.encyclopedia.database.entity.Account;
import com.fish.encyclopedia.database.repository.AccountRepository;
import com.fish.encyclopedia.security.AccountContext;
import com.fish.encyclopedia.security.AccountContextService;
import com.fish.encyclopedia.security.tokens.PostAuthorizationToken;
import com.fish.encyclopedia.security.tokens.PreAuthorizationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;

@AllArgsConstructor
public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        PreAuthorizationToken token = (PreAuthorizationToken) authentication;

        String userEmail = token.getUsername();
        String password = token.getUserPassword();

        Account account = accountRepository.findByEmail(userEmail).orElseThrow(() -> new NoSuchElementException("일치하는 계정이 없습니다."));
        if(isCorrectPassword(password, account)){
            //TODO return Post authentication token
            return PostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(account));
        }
        throw new NoSuchElementException("인증 정보가 정확하지 않습니다.");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthorizationToken.class.isAssignableFrom(aClass);
    }

    private boolean isCorrectPassword(String password, Account account){
        return passwordEncoder.matches(account.getPassword(), password); //순서 조 (account password , 비교할 password)
    }
}
