package com.fish.encyclopedia.account;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "Account API")
@RestController
@RequiredArgsConstructor
public class AccountController {
    // TODO: reissue access token
    // TODO: give access token and refresh token from code in url parameter
    // TODO: give user info from access token
//    private final ModelMapper modelMapper;
//    private final AccountService accountService;
//
//    @ApiOperation(value="Kakao login")
//    @GetMapping(path = "/oauth/kakao/login")
//    public ResponseEntity<?> kakaoLogin() {
//        accountService
//        return new ResponseEntity<>(modelMapper.map(info, AccountDTO.ResponseMyInfo.class), HttpStatus.OK);
//    }
}
