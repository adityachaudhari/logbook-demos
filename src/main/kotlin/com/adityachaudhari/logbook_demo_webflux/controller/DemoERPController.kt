package com.adityachaudhari.logbook_demo_webflux.controller

import com.adityachaudhari.logbook_demo_webflux.service.ThirdPartyERPService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/erp")
class DemoERPController(private val thirdPartyService: ThirdPartyERPService) {

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
