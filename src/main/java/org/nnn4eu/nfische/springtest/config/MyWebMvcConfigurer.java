package org.nnn4eu.nfische.springtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyWebMvcConfigurer  implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry)  {
        //registry.removeConvertible(String.class, Collection.class);
        //registry.addConverter(String.class,Collection.class,BypassCommaDelimiterConfiguration.commaDelimiterBypassedParsingConverter());
    }
}

//https://www.baeldung.com/spring-mvc-custom-data-binder