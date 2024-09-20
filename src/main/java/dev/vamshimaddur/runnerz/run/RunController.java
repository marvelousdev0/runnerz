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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void createRun(@Valid @RequestBody Run run) {
        runRepository.create(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void updateRun(@PathVariable String id, @Valid @RequestBody Run run) {
        runRepository.update(Integer.parseInt(id), run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteRun(@PathVariable String id) {
        runRepository.delete(Integer.parseInt(id));
    }
}
