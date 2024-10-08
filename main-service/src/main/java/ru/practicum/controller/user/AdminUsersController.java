package ru.practicum.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.user.NewUserRequest;
import ru.practicum.dto.user.UserDto;
import ru.practicum.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/admin/users")
public class AdminUsersController {
    private final UserService service;

    @GetMapping
    public List<UserDto> getUsersByParam(@RequestParam(required = false) List<Long> ids,
                                         @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                         @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Получение списка пользователей: id - {}, количество пропущенных - {}, элементов в наборе - {}",
                ids, from, size);
        return service.getUsersByParam(ids, from, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid NewUserRequest creatingDto) {
        log.info("Создание нового пользователя: {}", creatingDto);
        return service.createUser(creatingDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long userId) {
        log.info("Удаление пользователя с id: {}", userId);
        service.deleteUser(userId);
    }
}
