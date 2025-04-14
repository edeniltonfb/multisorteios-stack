package com.multisorteios.cambista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multisorteios.cambista.service.EmpresaService;

@RestController
@RequestMapping(value = "/api")
public class HomeController {
	
	@Autowired
	private EmpresaService service;
	
    @GetMapping("/teste")
    public String home() {
        return service.teste();
    }
}
