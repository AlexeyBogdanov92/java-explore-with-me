package ru.practicum.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCategoryDto {
    @Size(max = 50, message = "Превышен максимальный размер в 50 символов")
    @NotBlank(message = "Имя категории не может быть пустым")
    private String name;
}
