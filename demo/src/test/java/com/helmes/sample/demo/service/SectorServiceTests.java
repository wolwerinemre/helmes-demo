package com.helmes.sample.demo.service;

import com.helmes.sample.demo.dto.SectorDto;
import com.helmes.sample.demo.model.Sector;
import com.helmes.sample.demo.repository.FormRepository;
import com.helmes.sample.demo.repository.SectorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SectorServiceTests {

    @Mock
    private SectorRepository repository;
    private ModelMapper modelMapper;
    private SectorService sectorService;

    @BeforeEach
    void init() {
        modelMapper = new ModelMapper();
        sectorService = new SectorService(repository, modelMapper);
    }

    @Test
    void test_getAll() {
        List<Sector> sectors = List.of(new Sector()
                .setId(1L)
                .setName("Test-1")
                .setChildren(Set.of(new Sector()
                        .setId(11L)
                        .setParent(new Sector()
                                .setId(1L)
                                .setName("Test-1"))
                        .setName("Test-child"))));
        Mockito.when(repository.findAll()).thenReturn(sectors);
        List<SectorDto> result = sectorService.findAll();
        Mockito.verify(repository, Mockito.times(1)).findAll();
        Assertions.assertThat(sectors.get(0).getName()).isEqualTo(result.get(0).getName());

    }
}
