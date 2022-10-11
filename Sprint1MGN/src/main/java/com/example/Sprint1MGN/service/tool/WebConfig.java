package com.example.Sprint1MGN.service.tool;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //@EnableWebMVC
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true) //"format" by default
                .parameterName("mediaType")
                .ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);

//              .ignoreAcceptHeader(false)
//        Whether to disable checking the 'Accept' request header.
//        By default this value is set to false.

//              .useRegisteredExtensionsOnly(false)
//        When favorPathExtension is set,
//        this property determines whether to use only registered MediaType mappings to resolve a path extension to a specific MediaType.
//        By default this is not set in which case PathExtensionContentNegotiationStrategy will use defaults if available.


    }
}

