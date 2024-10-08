package ru.practicum.dto.compilation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCompilationRequest {
    @Builder.Default
    private Set<Long> events = null;
    @Builder.Default
    private Boolean pinned = null;

    @Size(min = 1, max = 50)
    private String title;
}
