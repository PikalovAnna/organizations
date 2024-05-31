package org.pikalovanna.organizations.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.pikalovanna.organizations.entity.BranchOffice;
import org.pikalovanna.organizations.entity.Organization;
import org.pikalovanna.organizations.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Объект для передачи данных об организации
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationDto {
    /**
     * Идентификатор организации
     */
    Long id;

    /**
     * Наименование
     */
    String name;

    /**
     * Краткое наименование
     */
    String shortName;

    /**
     * ИНН
     */
    String inn;

    /**
     * ОГРН
     */
    String ogrn;

    /**
     * Адрес
     */
    String address;

    /**
     * Генеральный директор
     */
    User generalDirector;

    /**
     * Филиалы
     */
    List<BranchOffice> branchOffices = new ArrayList<>();

    /**
     * Конструктор для получения dto из сущности
     * (p.s. Mapstruct использовать не стала)
     */
    public OrganizationDto(Organization organization) {
        this.id = organization.getId();
        this.name = organization.getName();
        this.shortName = organization.getShortName();
        this.inn = organization.getInn();
        this.ogrn = organization.getOgrn();
        this.address = organization.getAddress();
        this.generalDirector = organization.getGeneralDirector();
        this.branchOffices = organization.getBranchOffices();
    }
}
