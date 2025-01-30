package com.in28minutes.sprintboot.todo_list.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CSRFTokenController {
    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest req){
        return (CsrfToken) req.getAttribute("_csrf");
    }
}
