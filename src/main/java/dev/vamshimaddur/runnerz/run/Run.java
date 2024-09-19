package dev.vamshimaddur.runnerz.run;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        String title,
        LocalDateTime startedAt,
        LocalDateTime completedAt,
        Integer miles,
        Location location
) {
}
