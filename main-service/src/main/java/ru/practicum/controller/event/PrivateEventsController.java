package ru.practicum.controller.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.event.*;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.service.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/events")
public class PrivateEventsController {
    private final EventService service;

    @GetMapping
    public List<EventShortDto> getEventsByUser(@PathVariable long userId,
                                               @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                               @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Получение списка событий от пользователя с id {}", userId);
        return service.getEventsByUser(userId, from, size);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventByIdAndUser(@PathVariable long userId, @PathVariable long eventId) {
        log.info("Получение события с id {} от пользователя с id {}", eventId, userId);
        return service.getEventByIdAndUser(userId, eventId);
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getRequestsByUser(@PathVariable long userId, @PathVariable long eventId) {
        log.info("Получение запросов на участии в событии с id {} от пользователя с id {}", eventId, userId);
        return service.getRequestsByUser(userId, eventId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto createEvent(@PathVariable long userId, @Valid @RequestBody NewEventDto creatingDto) {
        log.info("Добавление нового события {} от пользователя {}", creatingDto, userId);
        return service.createEvent(userId, creatingDto);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventById(@PathVariable long userId,
                                        @PathVariable long eventId,
                                        @RequestBody @Valid UpdateEventUserRequest updatingDto) {
        log.info("Изменение события с id {} от пользователя с id {} запроса: {}", eventId, userId, updatingDto);
        return service.updateEventById(userId, eventId, updatingDto);
    }

    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult updateEventStatus(@PathVariable long userId,
                                                            @PathVariable long eventId,
                                                            @RequestBody(required = false) EventRequestStatusUpdateRequest updatingDto) {
        log.info("Изменение статуса запроса с id {} от пользователя с id {}: {}", eventId, userId, updatingDto);
        return service.updateEventStatus(userId, eventId, updatingDto);
    }
}
