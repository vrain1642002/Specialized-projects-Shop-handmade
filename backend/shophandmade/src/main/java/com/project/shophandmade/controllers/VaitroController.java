package com.project.shophandmade.controllers;

import com.project.shophandmade.models.Vaitro;
import com.project.shophandmade.services.VaitroService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("${api.prefix}/vaitros")
@RequiredArgsConstructor

public class VaitroController {
    private final VaitroService vaitroService;
    @GetMapping("")
    public ResponseEntity<?> layVaitros() {
        List<Vaitro> vaitros=vaitroService.layVaitros();
        return ResponseEntity.ok(vaitros);
    }
}
