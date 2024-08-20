package com.yilmazgokhan.architecture.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary(){
        Map config = new HashMap();
        config.put("cloud_name","dgtc9qzcp");
        config.put("api_key","622221992862633");
        config.put("api_secret","p3CJfbtgzz70xp-Rzs5KwtGybiI");
        config.put("secure", true);
        return new Cloudinary(config);
    }

}
