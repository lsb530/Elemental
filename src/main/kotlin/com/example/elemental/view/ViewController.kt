package com.example.elemental.view

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {

    @GetMapping("/welcome")
    fun hello(model: Model): String {
        model.addAttribute("greeting", "Hello Thymeleaf!")
        return "welcome"
    }

    @GetMapping("/ws-test")
    fun wsTest(): String {
        return "ws-test"
    }
}