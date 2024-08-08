package ru.practicum.utility;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;

@UtilityClass
public class Paginator {

    public static PageRequest simplePage(int from, int size) {
        return PageRequest.of(from / size, size);
    }
}
