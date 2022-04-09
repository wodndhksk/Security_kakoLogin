package com.fish.encyclopedia.security;

import com.fish.encyclopedia.database.entity.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class JwtFactoryTest {
    private static final Logger log = LoggerFactory.getLogger(JwtFactoryTest.class);
    private AccountContext context;

    @Autowired
    private JwtFactory factory;

    @Before
    public void setUp(){
        Account account = new Account();
        this.context = AccountContext.fromAccountModel(account);
    }
    @Test
    public void TEST_JWT_GENERATE(){
        log.error(factory.generateToken(this.context));
    }


}