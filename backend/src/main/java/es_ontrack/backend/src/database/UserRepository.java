package es_ontrack.backend.src.database;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import es_ontrack.backend.src.database.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
