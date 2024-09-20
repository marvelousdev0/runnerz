package dev.vamshimaddur.runnerz.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserRestClient {
    public final RestClient restClient;

    public UserRestClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    public List<User> getUsers() {
        return this.restClient.get().uri("/users").retrieve().body(new ParameterizedTypeReference<List<User>>() {
        });
    }

    public User getUserById(Integer id) {
        return this.restClient.get().uri("/users/{id}", id).retrieve().body(User.class);
    }
}
