package me.doublejin.api.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VideoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoApiApplication.class, args);
        System.out.println("webhook test");
    }

}
