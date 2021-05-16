package com.helmes.sample.demo.service;

import com.helmes.sample.demo.dto.FormDto;
import com.helmes.sample.demo.model.Form;
import com.helmes.sample.demo.repository.FormRepository;
import com.helmes.sample.demo.util.exception.EntityNotFoundException;
import com.helmes.sample.demo.util.exception.PersistencyException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class FormService {

    private final FormRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public FormService(FormRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public FormDto findById(Long id) {
        return modelMapper.map(repository.findById(id).orElseThrow(EntityNotFoundException::new), FormDto.class);
    }

    public FormDto getLatest() {
        Optional<Form> latest = Optional.ofNullable(repository.findTopByOrderByIdDesc());
        return modelMapper.map(latest.orElse(new Form()), FormDto.class);
    }

    public FormDto save(FormDto dto) {
        Form bo = modelMapper.map(dto, Form.class);
        try {
            return modelMapper.map(repository.save(bo), FormDto.class);
        }catch (Exception e){
            throw new PersistencyException("Couldnt save entity", e.getCause());
        }
    }

    public FormDto update(FormDto dto) {
        FormDto dbForm = this.findById(dto.getId());
        BeanUtils.copyProperties(dto, dbForm);
        return this.save(dbForm);
    }
}
