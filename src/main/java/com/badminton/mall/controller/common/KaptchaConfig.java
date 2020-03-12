package com.badminton.mall.controller.common;

import com.badminton.mall.common.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.image.width", "150");
        properties.put("kaptcha.image.height", "40");
        properties.put("kaptcha.textproducer.font.size", "30");
        properties.put("kaptcha.session.key", Constants.MALL_VERIFY_CODE_KEY);
        properties.put("kaptcha.textproducer.char.space", "4");
        properties.put("kaptcha.textproducer.char.length", "4");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}