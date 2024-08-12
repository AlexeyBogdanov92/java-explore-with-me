package ru.practicum.controller.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.service.CategoryService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class PublicCategoriesController {
    private final CategoryService service;


    @GetMapping
    public List<CategoryDto> getCategoriesByParam(@RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                                  @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Получение списка  категорий: количество пропущенных - {}, элементов в наборе - {}",
                from, size);
        return service.getCategoriesByParam(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getCompilationById(@PathVariable long catId) {
        log.info("Получение подборки событий по id = {}", catId);
        return service.getCategoriesById(catId);
    }
}
