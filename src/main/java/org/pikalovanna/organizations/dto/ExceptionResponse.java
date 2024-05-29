package org.pikalovanna.organizations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Объект для передачи исключений
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    /**
     * Код статуса
     */
    int status;
    /**
     * Текст исключения
     */
    String message;
    /**
     * Ссылка, по которой произошло исключение
     */
    String path;
}
