package uz.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class QuizzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizzerApplication.class, args);
    }

}
