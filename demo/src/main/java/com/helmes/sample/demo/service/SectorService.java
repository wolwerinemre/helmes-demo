package com.helmes.sample.demo.service;

import com.helmes.sample.demo.dto.SectorDto;
import com.helmes.sample.demo.model.Sector;
import com.helmes.sample.demo.repository.SectorRepository;
import com.helmes.sample.demo.util.exception.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SectorService {

    private final SectorRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public SectorService(SectorRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public SectorDto findById(Long id) {
        return modelMapper.map(
                repository.findById(id).orElseThrow(EntityNotFoundException::new),
                SectorDto.class);
    }

    public List<SectorDto> findAll() {
        List<SectorDto> list = repository.findAll().stream()
                .map(bo -> modelMapper.map(bo, SectorDto.class))
                .collect(Collectors.toList());
        list.removeIf(dto -> Objects.nonNull(dto.getParent()) && dto.getParent().getChildren().contains(dto));
        return list;
    }
}
