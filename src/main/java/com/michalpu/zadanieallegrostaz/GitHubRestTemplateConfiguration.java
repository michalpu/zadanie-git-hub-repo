package com.michalpu.zadanieallegrostaz;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GitHubRestTemplateConfiguration {

    @Bean
    public RestTemplate gitHubRestTemplate(
            @Value("${gitHub.client.connectionTimeout}") int connectionTimeout,
            @Value("${gitHub.client.connectionRequestTimeout}") int connectionRequestTimeout,
            @Value("${gitHub.client.ReadTimeout}") int readTimeout,
            @Value("${gitHub.client.maxConnectionTotal}") int maxConnectionTotal,
            @Value("${gitHub.client.maxConnectionPerRoute}") int maxConnectionPerRoute) {
        RestTemplate restTemplate = new RestTemplate(httpFactory(
                connectionTimeout,
                connectionRequestTimeout,
                readTimeout,
                maxConnectionTotal,
                maxConnectionPerRoute));
        return restTemplate;
    }

    private HttpComponentsClientHttpRequestFactory httpFactory(int connectionTimeout,
                                                               int connectionRequestTimeout,
                                                               int readTimeout,
                                                               int maxConnectionTotal,
                                                               int maxConnectionPerRoute) {
        HttpComponentsClientHttpRequestFactory requestConfig = new HttpComponentsClientHttpRequestFactory();
        requestConfig.setConnectTimeout(connectionTimeout);
        requestConfig.setConnectionRequestTimeout(connectionRequestTimeout);
        requestConfig.setReadTimeout(readTimeout);
        requestConfig.setHttpClient(httpClient(maxConnectionTotal, maxConnectionPerRoute));
        return requestConfig;
    }

    private org.apache.http.client.HttpClient httpClient(int maxConnectionTotal,
                                                         int maxConnectionPerRoute) {
        return HttpClientBuilder.create()
                .setMaxConnTotal(maxConnectionTotal)
                .setMaxConnPerRoute(maxConnectionPerRoute)
                .build();
    }
}
