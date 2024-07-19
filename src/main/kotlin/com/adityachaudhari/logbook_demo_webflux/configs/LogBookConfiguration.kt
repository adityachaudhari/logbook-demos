package com.adityachaudhari.logbook_demo_webflux.configs

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zalando.logbook.Correlation
import org.zalando.logbook.HeaderFilter
import org.zalando.logbook.HttpRequest
import org.zalando.logbook.HttpResponse
import org.zalando.logbook.Logbook
import org.zalando.logbook.Precorrelation
import org.zalando.logbook.Sink
import org.zalando.logbook.core.BodyOnlyIfStatusAtLeastStrategy
import org.zalando.logbook.core.SecurityStrategy

@Configuration
class LogBookConfiguration {

    /*@Bean
    fun logbook(): Logbook {
        *//*return Logbook.builder().build();*//*
        return Logbook.builder().strategy(BodyOnlyIfStatusAtLeastStrategy(201)).build()
    }*/

    @Bean
    @Qualifier("defaultLogbook")
    fun defaultLogbook(): Logbook {
        // Default configuration
        return Logbook.builder().build()
    }

    @Bean
    @Qualifier("erpLogbook")
    fun erpLogbook(): Logbook {
        return Logbook.builder()
            .sink(ErpLoggingCustomSink())
            /*.headerFilter(HeaderFilter.none()) // Ensure headers are skipped*/
            .build()
    }

    @Bean
    @Qualifier("crmLogbook")
    fun crmLogbook(): Logbook {
        return Logbook.builder()
            .sink(CrmLoggingCustomSink())
            /*.headerFilter(HeaderFilter.none()) // Ensure headers are skipped*/
            .build()
    }
}

class ErpLoggingCustomSink : Sink {
    private val logger = LoggerFactory.getLogger(ErpLoggingCustomSink::class.java)

    override fun write(precorrelation: Precorrelation, request: HttpRequest) {
        val requestBody = if (request.body.isNotEmpty()) "Request Body: ${request.bodyAsString}" else ""
        logger.info("ERP Request Correlation ID: ${precorrelation.id}, Request: ${request.method} ${request.requestUri} $requestBody")
    }

    override fun write(correlation: Correlation, request: HttpRequest, response: HttpResponse) {
        val isErrorResponse = response.status >= 400
        val logPrefix = if (isErrorResponse) "ERP Error Response" else "ERP Response"
        logger.info("$logPrefix Correlation ID: ${correlation.id}, Status: ${response.status}, Response Body: ${response.bodyAsString}")
    }
}

class CrmLoggingCustomSink : Sink {
    private val logger = LoggerFactory.getLogger(CrmLoggingCustomSink::class.java)

    override fun write(precorrelation: Precorrelation, request: HttpRequest) {
        val requestBody = if (request.body.isNotEmpty()) "Request Body: ${request.bodyAsString}" else ""
        logger.info("CRM Request Correlation ID: ${precorrelation.id}, Request: ${request.method} ${request.requestUri} $requestBody")
    }

    override fun write(correlation: Correlation, request: HttpRequest, response: HttpResponse) {
        val isErrorResponse = response.status >= 400
        val logPrefix = if (isErrorResponse) "CRM Error Response" else "ERP Response"
        logger.info("$logPrefix Correlation ID: ${correlation.id}, Status: ${response.status}, Response Body: ${response.bodyAsString}")
    }
}

