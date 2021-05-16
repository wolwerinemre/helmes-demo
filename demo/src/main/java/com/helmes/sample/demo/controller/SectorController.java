package com.helmes.sample.demo.controller;

import com.helmes.sample.demo.dto.SectorDto;
import com.helmes.sample.demo.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/sector")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class SectorController {

    private final SectorService sectorService;

    @Autowired
    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<SectorDto> getById(@PathVariable @Valid @Min(1) @NotNull Long id){
        return new ResponseEntity<>(sectorService.findById(id),
                HttpStatus.OK);
    }

    @GetMapping("/")
    public @ResponseBody
    ResponseEntity<List<SectorDto>> getAll(){
        return new ResponseEntity<>(sectorService.findAll(),
                HttpStatus.OK);
    }

}
