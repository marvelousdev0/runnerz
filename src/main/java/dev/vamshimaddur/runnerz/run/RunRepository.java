package dev.vamshimaddur.runnerz.run;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private final List<Run> runs = new ArrayList<>();
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM run")
                .query(Run.class).list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM run WHERE id = :id")
                .param("id", id)
                .query(Run.class).stream().findFirst();
    }

    public void create(Run run) {
        int update = jdbcClient.sql("INSERT INTO run (id, title, started_at, completed_at, miles, location) VALUES (?, ?, ?, ?, ?, ?)")
                .params(List.of(run.id(), run.title(), run.startedAt(), run.completedAt(), run.miles(), run.location().toString()))
                .update();
        Assert.state(update == 1, run.title() + " run not created");

    }

    public void update(Integer id, Run run) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isEmpty()) {
            throw new IllegalArgumentException("Run not found");
        }
        int update = jdbcClient.sql("UPDATE run SET title = ?, started_at = ?, completed_at = ?, miles = ?, location = ? WHERE id = ?")
                .params(List.of(run.title(), run.startedAt(), run.completedAt(), run.miles(), run.location().toString(), id))
                .update();
        Assert.state(update == 1, run.title() + " run not updated");

    }

    public void delete(Integer id) {
        int delete = jdbcClient.sql("DELETE FROM run WHERE id = :id")
                .param("id", id)
                .update();
        Assert.state(delete == 1, "Run not deleted");
    }

    public int count() {
        return jdbcClient.sql("SELECT COUNT(*) FROM run")
                .query().listOfRows().size();
    }

    public void createAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(Location location) {
        return jdbcClient.sql("SELECT * FROM run WHERE location = :location")
                .param("location", location.toString())
                .query(Run.class).list();
    }
}
