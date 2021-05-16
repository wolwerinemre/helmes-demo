package com.helmes.sample.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class SectorDto {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @JsonIgnore
    private SectorDto parent;
    private Set<SectorDto> children;
}
