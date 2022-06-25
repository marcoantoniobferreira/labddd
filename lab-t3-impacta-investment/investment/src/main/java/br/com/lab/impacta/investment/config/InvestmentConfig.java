package br.com.lab.impacta.investment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.okhttp.OkHttpClient;

@Configuration
public class InvestmentConfig {
    
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public OkHttpClient buildClient(){
        return new OkHttpClient();
    }
}
