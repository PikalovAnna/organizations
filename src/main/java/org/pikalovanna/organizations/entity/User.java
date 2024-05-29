package org.pikalovanna.organizations.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * Сущность пользователя (для руководителей и тп)
 */
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users", schema = "public")
public class User {

    /**
     * Идентификатор пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Имя
     */
    @Column(name = "first_name")
    String firstName;

    /**
     * Фамилия
     */
    @Column(name = "last_name")
    String lastName;

    /**
     * Отчество
     */
    @Column(name = "middle_name")
    String middleName;

    /**
     * Дата рождения
     */
    @Column(name = "birthday")
    LocalDate birthday;
}
