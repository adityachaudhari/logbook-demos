package com.adityachaudhari.logbook_demo_webflux.configs

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.zalando.logbook.Logbook
import org.zalando.logbook.spring.webflux.LogbookExchangeFilterFunction

@Configuration
class WebClientConfig {

    /*@Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build()
    }*/

    @Bean
    fun erpWebClient(@Qualifier("erpLogbook") erpLogbook: Logbook): WebClient {
        return WebClient.builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .filter(LogbookExchangeFilterFunction(erpLogbook))
            .build()
    }

    @Bean
    fun crmWebClient(@Qualifier("crmLogbook") erpLogbook: Logbook): WebClient {
        return WebClient.builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .filter(LogbookExchangeFilterFunction(erpLogbook))
            .build()
    }
}