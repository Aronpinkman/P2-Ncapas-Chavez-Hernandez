
package com.example.p2ncapas.Domain.DTO;

import jakarta.validation.constraints.*;

public record CreateBookRequest(
        @NotBlank @Pattern(regexp = ".*\\D.*", message = "El título no puede ser solo números") String title,
        @NotBlank String author,
        @NotBlank String isbn,
        @Min(1900) @Max(2025) Integer publicationYear,
        String language,
        @Min(11) Integer pages,
        @NotBlank String genre
) {}
