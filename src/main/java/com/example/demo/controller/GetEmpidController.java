package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URISyntaxException;

@Slf4j
@RequestMapping("/api/v1")
@RestController
public class GetEmpidController {
    @GetMapping("/user")
    public ResponseEntity<String> getUserInfo(RequestEntity<String> requestEntity) {
        // RequestEntity에서 URI 가져오기
        URI requestUri = requestEntity.getUrl();
        //System.out.println("Request URI: " + requestUri);
        String uriStr = requestUri.toString();
        String empId = "";

        try {  // 정규식을 이용해 숫자 토큰 추출
            Pattern pattern = Pattern.compile("app-(\\d+)\\.ce[a-z]+-aa\\.kubepia\\.net");
            Matcher matcher = pattern.matcher(uriStr);
            if (matcher.find()) {
                empId = matcher.group(1);
            } else {
                empId ="No empId in the URL.";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(empId);
    }
}
