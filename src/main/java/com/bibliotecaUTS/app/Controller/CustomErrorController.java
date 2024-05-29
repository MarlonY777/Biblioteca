package com.bibliotecaUTS.app.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "error"; // Asegúrate de que la vista 'error.html' está en src/main/resources/templates
    }

    public String getErrorPath() {
        return "/error";
    }
}
