package com.example.springcrud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("/member")
    public Member member() {
        return new Member(1L, "동민", 34);
    }
}