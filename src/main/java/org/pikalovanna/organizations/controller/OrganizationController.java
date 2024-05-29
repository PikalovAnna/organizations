package org.pikalovanna.organizations.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.pikalovanna.organizations.dto.OrganizationDto;
import org.pikalovanna.organizations.entity.Organization;
import org.pikalovanna.organizations.service.OrganizationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для эндпоинтов получения информации об организациях
 */
@RestController
@RequestMapping("/organizations")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrganizationController {

    OrganizationService organizationService;

    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable("id") Long id) {
        return organizationService.getById(id);
    }

    @GetMapping
    public Page<OrganizationDto> getList(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "20") int size,
            @RequestParam(value = "search", required = false, defaultValue = "") String search
    ) {
        return organizationService.getOrganizationsPageable(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")), search);
    }
}
