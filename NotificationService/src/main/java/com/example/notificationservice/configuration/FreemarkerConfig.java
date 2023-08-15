package com.example.notificationservice.configuration;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@org.springframework.context.annotation.Configuration
public class FreemarkerConfig {
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        TemplateLoader loader = new ClassTemplateLoader(this.getClass(), "/templates");
        configuration.setTemplateLoader(loader);
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setConfiguration(configuration);
        return configurer;
    }
}
