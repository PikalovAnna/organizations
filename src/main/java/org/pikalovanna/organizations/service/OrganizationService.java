package org.pikalovanna.organizations.service;

import lombok.RequiredArgsConstructor;
import org.pikalovanna.organizations.dto.OrganizationDto;
import org.pikalovanna.organizations.entity.Organization;
import org.pikalovanna.organizations.exceptions.ObjectNotFoundException;
import org.pikalovanna.organizations.repository.OrganizationRepository;
import org.pikalovanna.organizations.repository.OrganizationSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с организациями
 */
@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    /**
     * Метод получения организации по id
     *
     * @param id идентификатор организации
     * @return объект организации (Organization)
     */
    public Organization getById(Long id) {
        return organizationRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Организация с ID %s не найдена".formatted(id))
        );
    }

    /**
     * Метод получения организаций постранично
     *
     * @param pageable запрос страницы
     * @param search   строка поиска
     * @return страница с объектами организаций (Page<Organization>)
     */
    public Page<OrganizationDto> getOrganizationsPageable(Pageable pageable, String search) {
        return organizationRepository.findAll(OrganizationSpecification.searchWithFilter(search), pageable)
                .map(OrganizationDto::new);
    }

}
