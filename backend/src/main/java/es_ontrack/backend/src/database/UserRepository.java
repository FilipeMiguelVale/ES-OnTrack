package es_ontrack.backend.src.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es_ontrack.backend.src.database.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByEmail(String email);

}
