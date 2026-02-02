package kz.aitu.transport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transports")
public class TransportController {
    @GetMapping
    public String mainPage() {
        return "transports";
    }
}
