package cl.sprintzambrano.sprintzambrano.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BienvenidaController {
    @GetMapping("/bienvenida")
    public String bienvenida(){
        return "bienvenida";
    }
}
