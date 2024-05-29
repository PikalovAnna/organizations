package org.pikalovanna.organizations.repository;

import jakarta.persistence.criteria.Predicate;
import org.pikalovanna.organizations.entity.Organization;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Спецификация для поиска объектов по подстроке
 */
public class OrganizationSpecification {

    /**
     * Метод получения спецификации Organization (поиск по подстроке)
     */
    public static Specification<Organization> searchWithFilter(String search) {
        return (root, query, builder) -> {
            final List predicates = new ArrayList();
            if (StringUtils.hasText(search)) {
                predicates.add(builder.or(
                        builder.like(builder.upper(root.get("name")), '%' + search + '%'),
                        builder.like(builder.upper(root.get("shortName")), '%' + search + '%'),
                        builder.like(builder.upper(root.get("inn")), '%' + search + '%'),
                        builder.like(builder.upper(root.get("ogrn")), '%' + search + '%'),
                        builder.like(builder.upper(root.get("address")), '%' + search + '%')));
            }

            return builder.and((Predicate[]) predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
