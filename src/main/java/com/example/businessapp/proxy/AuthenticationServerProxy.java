package com.example.businessapp.proxy;

import com.example.businessapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationServerProxy {
    @Autowired
    private RestTemplate restTemplate;


    public void sendAuth(String username,String password){
        String url="http://localhost:8080/user/auth";

        var body=new User();
        body.setUsername(username);
        body.setPassword(password);

        var request=new HttpEntity<>(body);
        restTemplate.postForEntity(url,request,Void.class);
    }

    public boolean sendOTP(String username,String code){
        String url="http://localhost:8080/otp/check";

        var body=new User();
        body.setUsername(username);
        body.setCode(code);

        var request=new HttpEntity<>(body);

        var response=restTemplate.postForEntity(url,request,Void.class);
        return response.getStatusCode().equals(HttpStatus.OK);
    }

}
