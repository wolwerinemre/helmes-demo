package com.helmes.sample.demo.service;

import com.helmes.sample.demo.dto.FormDto;
import com.helmes.sample.demo.model.Form;
import com.helmes.sample.demo.model.Sector;
import com.helmes.sample.demo.repository.FormRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FormServiceTests {

    @Mock
    private FormRepository repository;
    private ModelMapper modelMapper;
    private FormService formService;

    @BeforeEach
    void init() {
        modelMapper = new ModelMapper();
        formService = new FormService(repository, modelMapper);
    }

    @Test
    void test_findById(){
        Form form = new Form()
                .setName("Test")
                .setSectors(Set.of(new Sector().setName("Test-sector").setId(1L)))
                .setAgreeTerms(true);
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(form));
        FormDto result = formService.findById(1l);
        Mockito.verify(repository, Mockito.times(1)).findById(any());
        Assertions.assertThat(form.getName()).isEqualTo(result.getName());
        Assertions.assertThat(form.isAgreeTerms()).isEqualTo(result.isAgreeTerms());
    }

    @Test
    void test_findLatest(){
        Form form = new Form()
                .setName("Test")
                .setSectors(Set.of(new Sector().setName("Test-sector").setId(1L)))
                .setAgreeTerms(true);
        Mockito.when(repository.findTopByOrderByIdDesc()).thenReturn(form);
        FormDto result = formService.getLatest();
        Mockito.verify(repository, Mockito.times(1)).findTopByOrderByIdDesc();
        Assertions.assertThat(form.getName()).isEqualTo(result.getName());
        Assertions.assertThat(form.isAgreeTerms()).isEqualTo(result.isAgreeTerms());
    }

    @Test
    void test_save(){
        Form form = new Form()
                .setName("Test2")
                .setSectors(Set.of(new Sector().setName("Test2-sector").setId(1L)))
                .setAgreeTerms(true);
        Mockito.when(repository.save(any(Form.class))).thenReturn(form.setId(1L));
        FormDto result = formService.save(modelMapper.map(form, FormDto.class));
        Mockito.verify(repository, Mockito.times(1)).save(any());
        Assertions.assertThat(form.getName()).isEqualTo(result.getName());
        Assertions.assertThat(form.isAgreeTerms()).isEqualTo(result.isAgreeTerms());
    }

    @Test
    void test_update(){
        Form form = new Form()
                .setId(1L)
                .setName("Test")
                .setSectors(Set.of(new Sector().setName("Test-sector").setId(1L)))
                .setAgreeTerms(true);
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(form));
        Mockito.when(repository.save(any(Form.class))).thenReturn(form.setName("Test-update"));
        FormDto result = formService.update(modelMapper.map(form, FormDto.class));
        Mockito.verify(repository, Mockito.times(1)).save(any());
        Assertions.assertThat(form.getName()).isEqualTo(result.getName());
        Assertions.assertThat(form.isAgreeTerms()).isEqualTo(result.isAgreeTerms());
    }
}
