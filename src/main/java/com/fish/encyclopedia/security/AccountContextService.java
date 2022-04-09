package com.fish.encyclopedia.security;

import com.fish.encyclopedia.database.entity.Account;
import com.fish.encyclopedia.database.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class AccountContextService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(userEmail).orElseThrow(() -> new NoSuchElementException("정확한 계정을 입력하세요"));
        return getAccountContext(account);
    }

    private AccountContext getAccountContext(Account account){
        return AccountContext.fromAccountModel(account);
    }

}
