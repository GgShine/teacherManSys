package com.edu.archives;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.edu.archives.mapper")
public class ArchivesManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArchivesManagementApplication.class, args);
    }
}