package com.kong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore //该注释可以使这个controller不出现在swagger2文档中
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Object hello() {
        return "Hello world !";
    }
}
