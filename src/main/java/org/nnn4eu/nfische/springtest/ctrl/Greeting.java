package org.nnn4eu.nfische.springtest.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Greeting {
@GetMapping("/h")
public String greetingA(){
    return "Hy Hei Holla";
}

    @GetMapping("/h")
    public String greetingB(){
        return "Hy Hei Holla";
    }
}
