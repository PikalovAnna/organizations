package org.pikalovanna.organizations.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * Сущность организации
 */
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "organization", schema = "public")
public class Organization {

    /**
     * Идентификатор организации
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Наименование
     */
    @Column(name = "name")
    String name;

    /**
     * Краткое наименование
     */
    @Column(name = "short_name")
    String shortName;

    /**
     * ИНН
     */
    @Column(name = "inn")
    String inn;

    /**
     * ОГРН
     */
    @Column(name = "ogrn")
    String ogrn;

    /**
     * Адрес
     */
    @Column(name = "address")
    String address;

    /**
     * Генеральный директор
     */
    @ManyToOne
    @JoinColumn(name = "general_director")
    User generalDirector;

    /**
     * Генеральный директор
     */
    @ManyToMany
    @JoinTable(name = "organization_branch",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id"))
    List<BranchOffice> branchOffices = new ArrayList<>();
}
