package com.helmes.sample.demo.controller;

import com.helmes.sample.demo.dto.FormDto;
import com.helmes.sample.demo.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping(value = "/form")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class FormController {

    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<FormDto> getById(@PathVariable @Valid @Min(1) @NotNull Long id){
        return new ResponseEntity<>(formService.findById(id),
                HttpStatus.OK);
    }

    @GetMapping("/")
    public @ResponseBody
    ResponseEntity<FormDto> getLatest(){
        return new ResponseEntity<>(formService.getLatest(),
                HttpStatus.OK);
    }

    @PostMapping("/")
    public @ResponseBody
    ResponseEntity<FormDto> save(@RequestBody @Valid @NotNull FormDto dto){
        return new ResponseEntity<>(formService.save(dto),
                HttpStatus.OK);
    }

    @PutMapping("/")
    public @ResponseBody
    ResponseEntity<FormDto> update(@RequestBody @Valid @NotNull FormDto dto){
        return new ResponseEntity<>(formService.update(dto),
                HttpStatus.OK);
    }
}
