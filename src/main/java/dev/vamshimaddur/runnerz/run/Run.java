package dev.vamshimaddur.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Run(
        @Id
        Integer id,
        @NotEmpty String title,
        LocalDateTime startedAt,
        LocalDateTime completedAt,
        @Positive Integer miles,
        Location location,
        @Version Integer version
) {
    public Run {
        if (startedAt.isAfter(completedAt)) {
            throw new IllegalArgumentException("Run cannot end before it starts");
        }
    }
}
