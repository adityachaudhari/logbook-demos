package com.adityachaudhari.logbook_demo_webflux.controller

import com.adityachaudhari.logbook_demo_webflux.service.ThirdPartyCRMService
import com.adityachaudhari.logbook_demo_webflux.service.ThirdPartyERPService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/crm")
class DemoCRMController(private val thirdPartyService: ThirdPartyCRMService) {

    @GetMapping("/posts/{id}")
    fun getPost(@PathVariable id: Int): Mono<String> {
        return thirdPartyService.getPost(id)
    }

    @PostMapping("/posts")
    fun createPost(@RequestBody post: Map<String, Any>): Mono<String> {
        return thirdPartyService.createPost(post)
    }

    @GetMapping("/aa")
    fun get(): String {
        return "Hello Aditya"
    }
}
