package com.es_ontrack.test.cucumber.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.es_ontrack.test.cucumber.models.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
public interface UserRepositoryTest extends CrudRepository<User, Integer> {

    List<User> findByEmail(String email);

}
