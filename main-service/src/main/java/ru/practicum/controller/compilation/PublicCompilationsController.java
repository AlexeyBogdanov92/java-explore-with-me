package ru.practicum.controller.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.compilation.CompilationsDto;
import ru.practicum.service.CompilationService;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/compilations")
public class PublicCompilationsController {
    private final CompilationService service;

    @GetMapping
    public List<CompilationsDto> getCompilations(@RequestParam(required = false) Boolean pinned,
                                          @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                          @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Получение подборки событий: закрепленные - {}, количество пропущенных - {}, элементов в наборе - {}",
                pinned, from, size);
        return service.getCompilations(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public CompilationsDto getCompilationById(@PathVariable long compId) {
        log.info("Получение подборки событий по id = {}", compId);
        return service.getCompilationById(compId);
    }
}