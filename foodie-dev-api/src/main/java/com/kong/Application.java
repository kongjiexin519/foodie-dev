package com.kong;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.kong.mapper")
public class Application {
    // git config --global http.sslBackend "openssl"
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
