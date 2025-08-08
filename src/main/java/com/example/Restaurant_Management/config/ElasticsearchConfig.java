package com.example.Restaurant_Management.config;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Bean
    public RestClient restClient() {
        RestClientBuilder builder = RestClient
                .builder(new HttpHost("localhost", 9200))
                .setDefaultHeaders(new Header[]{
                        new BasicHeader("Authorization",
                                "Bearer AAEAAWVsYXN0aWMva2liYW5hL2tpYmFuYTpZZWptbkc2T1NEcUF5OXV0b0pqX1Z3")
                });
        return builder.build();
    }
}
