package ru.practicum.stats.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.stats.dto.ViewStatsDto;
import ru.practicum.stats.entity.EndpointHit;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
class StatsStorageTest {
    private final StatsStorage storage;

    @BeforeEach
    public void init() {
        EndpointHit entity1 = EndpointHit.builder()
                .app("ewm-main-service")
                .uri("/events/2")
                .ip("192.163.0.1")
                .times(LocalDateTime.of(2022, 9, 6, 11, 0, 23))
                .build();

        EndpointHit entity2 = EndpointHit.builder()
                .app("ewm-main-service")
                .uri("/events/1")
                .ip("192.163.0.1")
                .times(LocalDateTime.of(2022, 10, 6, 11, 0, 23))
                .build();

        storage.save(entity1);
        storage.save(entity2);
    }

    @Test
    public void getUniqueByTimes_whenValidParam_thenReturnList() {
        List<ViewStatsDto> actualList = storage.getUniqueByTimes(
                LocalDateTime.of(2022, 1, 6, 11, 0, 23),
                LocalDateTime.of(2022, 12, 6, 11, 0, 23));

        assertEquals(2, actualList.size());

        assertEquals("ewm-main-service", actualList.get(0).getApp());
        assertEquals("/events/1", actualList.get(0).getUri());
        assertEquals(1, actualList.get(0).getHits());

        assertEquals("ewm-main-service", actualList.get(1).getApp());
        assertEquals("/events/2", actualList.get(1).getUri());
        assertEquals(1, actualList.get(1).getHits());
    }

    @Test
    public void getUniqueByTimesAndList_whenValidParam_thenReturnList() {
        List<ViewStatsDto> actualList = storage.getUniqueByTimesAndList(
                List.of("/events/1"),
                LocalDateTime.of(2022, 1, 6, 11, 0, 23),
                LocalDateTime.of(2022, 12, 6, 11, 0, 23));

        assertEquals(1, actualList.size());
        assertEquals("ewm-main-service", actualList.get(0).getApp());
        assertEquals("/events/1", actualList.get(0).getUri());
        assertEquals(1, actualList.get(0).getHits());
    }

    @Test
    public void getAllByTimes_whenValidParam_thenReturnList() {
        List<ViewStatsDto> actualList = storage.getAllByTime(
                LocalDateTime.of(2022, 1, 6, 11, 0, 23),
                LocalDateTime.of(2022, 12, 6, 11, 0, 23));

        assertEquals(2, actualList.size());

        assertEquals("ewm-main-service", actualList.get(0).getApp());
        assertEquals("/events/1", actualList.get(0).getUri());
        assertEquals(1, actualList.get(0).getHits());

        assertEquals("ewm-main-service", actualList.get(1).getApp());
        assertEquals("/events/2", actualList.get(1).getUri());
        assertEquals(1, actualList.get(1).getHits());
    }

    @Test
    public void getAllByTimeAndList_whenValidParam_thenReturnList() {
        List<ViewStatsDto> actualList = storage.getAllByTimeAndList(
                List.of("/events/1"),
                LocalDateTime.of(2022, 1, 6, 11, 0, 23),
                LocalDateTime.of(2022, 12, 6, 11, 0, 23));

        assertEquals(1, actualList.size());
        assertEquals("ewm-main-service", actualList.get(0).getApp());
        assertEquals("/events/1", actualList.get(0).getUri());
        assertEquals(1, actualList.get(0).getHits());
    }

    @Test
    public void getUniqueByTimes_whenNotFoundByTimes_thenReturnEmptyList() {
        List<ViewStatsDto> actualList = storage.getUniqueByTimes(
                LocalDateTime.of(2023, 1, 6, 11, 0, 23),
                LocalDateTime.of(2023, 12, 6, 11, 0, 23));

        assertEquals(0, actualList.size());
    }

    @Test
    public void getUniqueByTimesAndList_whenNotFoundByTimes_thenReturnEmptyList() {
        List<ViewStatsDto> actualList = storage.getUniqueByTimesAndList(
                List.of("/events/1"),
                LocalDateTime.of(2023, 1, 6, 11, 0, 23),
                LocalDateTime.of(2023, 12, 6, 11, 0, 23));

        assertEquals(0, actualList.size());
    }

    @Test
    public void getAllByTimes_whenNotFoundByTimes_thenReturnEmptyList() {
        List<ViewStatsDto> actualList = storage.getAllByTime(
                LocalDateTime.of(2023, 1, 6, 11, 0, 23),
                LocalDateTime.of(2023, 12, 6, 11, 0, 23));

        assertEquals(0, actualList.size());
    }

    @Test
    public void getAllByTimeAndList_whenNotFoundByTimes_thenReturnEmptyList() {
        List<ViewStatsDto> actualList = storage.getAllByTimeAndList(
                List.of("/events/1"),
                LocalDateTime.of(2023, 1, 6, 11, 0, 23),
                LocalDateTime.of(2023, 12, 6, 11, 0, 23));

        assertEquals(0, actualList.size());
    }

    @Test
    public void getUniqueByTimesAndList_whenNotFoundByUri_thenReturnEmptyList() {
        List<ViewStatsDto> actualList = storage.getUniqueByTimesAndList(
                List.of("/events/33"),
                LocalDateTime.of(2022, 1, 6, 11, 0, 23),
                LocalDateTime.of(2022, 12, 6, 11, 0, 23));

        assertEquals(0, actualList.size());
    }

    @Test
    public void getAllByTimeAndList_whenNotFoundByUri_thenReturnEmptyList() {
        List<ViewStatsDto> actualList = storage.getAllByTimeAndList(
                List.of("/events/33"),
                LocalDateTime.of(2022, 1, 6, 11, 0, 23),
                LocalDateTime.of(2022, 12, 6, 11, 0, 23));

        assertEquals(0, actualList.size());
    }
}