package ru.practicum.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUserRequest {
    @NotBlank
    @Size(min = 6, max = 254)
    @Email(message = "Почта должна быть оформлена по правилам")
    private String email;

    @NotBlank
    @Size(min = 2, max = 250)
    private String name;
}
