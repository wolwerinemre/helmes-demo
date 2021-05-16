package com.helmes.sample.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Data
@Accessors(chain = true)
@RequiredArgsConstructor
@Table(name = "form")
public class Form implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator = "form_id_seq")
    @SequenceGenerator(name="form_id_seq", sequenceName = "form_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "form_sector",  joinColumns = {
            @JoinColumn(name = "form_id", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "sector_id", referencedColumnName = "id", nullable = false) })
    private Set<Sector> sectors;

    @Column(name = "agree_terms", nullable = false)
    private boolean agreeTerms;
    @CreationTimestamp
    @Column(name = "create_date")
    private Instant createDate;
    @UpdateTimestamp
    @Column(name = "update_date")
    private Instant updateDate;

}


