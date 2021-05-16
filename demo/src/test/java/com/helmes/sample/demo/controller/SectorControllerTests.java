package com.helmes.sample.demo.controller;

import com.helmes.sample.demo.dto.SectorDto;
import com.helmes.sample.demo.service.SectorService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SectorControllerTests {

    @Mock
    private SectorService sectorService;
    private SectorController sectorController;

    @BeforeEach
    void init() {
        sectorController = new SectorController(sectorService);
    }

    @Test
    void test_getAll() {
        List<SectorDto> sectors = List.of(new SectorDto()
                .setId(1L)
                .setName("Test-1")
                .setChildren(Set.of(new SectorDto()
                        .setId(11L)
                        .setParent(new SectorDto()
                                .setId(1L)
                                .setName("Test-1"))
                        .setName("Test-child"))));
        Mockito.when(sectorService.findAll()).thenReturn(sectors);
        List<SectorDto> result = sectorController.getAll().getBody();
        Mockito.verify(sectorService, Mockito.times(1)).findAll();
        Assertions.assertThat(sectors.get(0).getName()).isEqualTo(result.get(0).getName());
    }

}
