package com.adityachaudhari.logbook_demo_webflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
class LogbookDemoWebfluxApplication

fun main(args: Array<String>) {
	runApplication<LogbookDemoWebfluxApplication>(*args)

}
