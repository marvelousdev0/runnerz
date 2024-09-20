package dev.vamshimaddur.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> getRuns() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run getRun(@PathVariable String id) {
        Optional<Run> run = runRepository.findById(Integer.parseInt(id));
        if (run.isEmpty()) {
            throw new RunNotFoundException("Run not found");
        }
        return run.get();

    }

    @GetMapping("/count")
    int countRuns() {
        return (int) runRepository.count();
    }

    @GetMapping("/location/{location}")
    List<Run> getRunsByLocation(@PathVariable String location) {
        return runRepository.findAllByLocation(Location.valueOf(location.toUpperCase()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void createRun(@Valid @RequestBody Run run) {
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void updateRun(@PathVariable String id, @Valid @RequestBody Run run) {
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteRun(@PathVariable String id) {
        runRepository.deleteById(Integer.parseInt(id));
    }
}
