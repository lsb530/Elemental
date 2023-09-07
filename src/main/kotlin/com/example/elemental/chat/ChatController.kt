package com.example.elemental.chat

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ChatController {
    @GetMapping("/chat")
    fun chatGET(): String? {
        println("chat GET()")
        return "chat"
    }

    @GetMapping("/welcome")
    fun hello(model: Model): String {
        model.addAttribute("greeting", "Hello Thymeleaf!")
        return "welcome"
    }
}