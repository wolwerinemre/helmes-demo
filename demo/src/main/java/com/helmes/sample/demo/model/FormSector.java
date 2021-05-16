package com.helmes.sample.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "form_sector")
@NoArgsConstructor
public class FormSector implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator = "form_sector_id_seq")
    @SequenceGenerator(name="form_sector_id_seq", sequenceName = "form_sector_id_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Form formId;
    @ManyToOne
    @JoinColumn(name = "sector_id", nullable = false)
    private Sector sectorId;
}
