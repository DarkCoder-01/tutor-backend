package com.darkcoder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@MapperScan("com.darkcoder")
@SpringBootApplication
public class TutorBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorBackendApplication.class, args);
    }

}
