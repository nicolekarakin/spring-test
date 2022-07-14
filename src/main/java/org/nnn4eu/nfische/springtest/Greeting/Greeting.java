package org.nnn4eu.nfische.springtest.Greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Greeting {
    @GetMapping("/a")
    public String greetingA(){
        return "Hy Hei Holla";
    }

    @GetMapping("/b")
    public String greetingB(){
        return "Hy Hei Holla";
    }
}

