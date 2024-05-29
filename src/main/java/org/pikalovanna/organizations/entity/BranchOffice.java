package org.pikalovanna.organizations.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * Сущность филиала
 */
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "branch_office", schema = "public")
public class BranchOffice {

    /**
     * Идентификатор филиала
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
     * Почтовый адрес
     */
    @Column(name = "mail")
    String mail;

    /**
     * Руководитель
     */
    @ManyToOne
    @JoinColumn(name = "header")
    User header;

    /**
     * Организация, к которой относится филиал
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization")
    Organization organization;

}
