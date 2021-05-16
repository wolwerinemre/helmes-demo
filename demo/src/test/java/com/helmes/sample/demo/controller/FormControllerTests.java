package com.helmes.sample.demo.controller;

import com.helmes.sample.demo.dto.FormDto;
import com.helmes.sample.demo.dto.SectorDto;
import com.helmes.sample.demo.service.FormService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FormControllerTests {

    @Mock
    private FormService formService;
    private FormController formController;

    @BeforeEach
    void init() {
        formController = new FormController(formService);
    }

    @Test
    void test_findById(){
        FormDto form = new FormDto()
                .setId(1L)
                .setName("Test")
                .setSectors(Set.of(new SectorDto().setName("Test-sector").setId(1L)))
                .setAgreeTerms(true);
        Mockito.when(formService.findById(any())).thenReturn(form);
        FormDto result = formController.getById(1L).getBody();
        Mockito.verify(formService, Mockito.times(1)).findById(any());
        Assertions.assertThat(form.getName()).isEqualTo(result.getName());
        Assertions.assertThat(form.isAgreeTerms()).isEqualTo(result.isAgreeTerms());
    }

    @Test
    void test_findLatest(){
        FormDto form = new FormDto()
                .setName("Test")
                .setSectors(Set.of(new SectorDto().setName("Test-sector").setId(1L)))
                .setAgreeTerms(true);
        Mockito.when(formService.getLatest()).thenReturn(form);
        FormDto result = formController.getLatest().getBody();
        Mockito.verify(formService, Mockito.times(1)).getLatest();
        Assertions.assertThat(form.getName()).isEqualTo(result.getName());
        Assertions.assertThat(form.isAgreeTerms()).isEqualTo(result.isAgreeTerms());
    }

    @Test
    void test_save(){
        FormDto form = new FormDto()
                .setName("Test2")
                .setSectors(Set.of(new SectorDto().setName("Test2-sector").setId(1L)))
                .setAgreeTerms(true);
        Mockito.when(formService.save(any(FormDto.class))).thenReturn(form.setId(1L));
        FormDto result = formController.save(form).getBody();
        Mockito.verify(formService, Mockito.times(1)).save(any());
        Assertions.assertThat(form.getName()).isEqualTo(result.getName());
        Assertions.assertThat(form.isAgreeTerms()).isEqualTo(result.isAgreeTerms());
    }

    @Test
    void test_update(){
        FormDto form = new FormDto()
                .setId(1L)
                .setName("Test")
                .setSectors(Set.of(new SectorDto().setName("Test-sector").setId(1L)))
                .setAgreeTerms(true);
        Mockito.when(formService.update(any(FormDto.class))).thenReturn(form.setName("Test-update"));
        FormDto result = formController.update(form).getBody();
        Mockito.verify(formService, Mockito.times(1)).update(any());
        Assertions.assertThat(form.getName()).isEqualTo(result.getName());
        Assertions.assertThat(form.isAgreeTerms()).isEqualTo(result.isAgreeTerms());
    }
}
