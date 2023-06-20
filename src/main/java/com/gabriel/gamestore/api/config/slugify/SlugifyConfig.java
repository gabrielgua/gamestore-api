package com.gabriel.gamestore.api.config.slugify;

import com.github.slugify.Slugify;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SlugifyConfig {

    @Bean
    public Slugify slugify() {
        return Slugify.builder()
                .customReplacement("™", "")
                .build();
    }
}
