package com.helmes.sample.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class FormDto {

    private Long id;
    @NotBlank
    @Size(min = 1, max = 200)
    private String name;
    @NotEmpty
    private Set<SectorDto> sectors;
    @NotNull
    private boolean agreeTerms;
}
