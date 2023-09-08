package com.example.elemental.view

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {
    @GetMapping("/chat")
    fun chatGET(): String? {
        return "chat"
    }

    @GetMapping("/welcome")
    fun hello(model: Model): String {
        model.addAttribute("greeting", "Hello Thymeleaf!")
        return "welcome"
    }

    @GetMapping("/websocket-test")
    fun websocketTest(): String {
        return "websocket-test"
    }
}