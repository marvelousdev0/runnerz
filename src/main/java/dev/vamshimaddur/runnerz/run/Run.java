package dev.vamshimaddur.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty String title,
        LocalDateTime startedAt,
        LocalDateTime completedAt,
        @Positive Integer miles,
        Location location
) {
    public Run {
        if (startedAt.isAfter(completedAt)) {
            throw new IllegalArgumentException("Run cannot end before it starts");
        }
    }
}
