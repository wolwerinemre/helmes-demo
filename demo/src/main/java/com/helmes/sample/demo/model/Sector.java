package com.helmes.sample.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "sector")
@NoArgsConstructor
public class Sector implements Serializable{
    @Id
    private Long id;
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Sector parent;
    @OneToMany
    @OrderColumn
    @JoinColumn(name = "parent_id")
    private Set<Sector> children;
    @ManyToMany(mappedBy = "sectors")
    Set<Form> forms;
}
