package ru.practicum.stats.dto.annotation;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckIpAddress {
    String message() default "ip указан неверно";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
