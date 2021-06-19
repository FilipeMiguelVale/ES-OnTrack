package es_ontrack.backend.src.database;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es_ontrack.backend.src.database.models.User;

@Controller
public class DBControler {

    @Autowired
    private UserRepository userRepository;

    public boolean canLogin(String email, String passwd) {

        User user = get_user(email);

        if (!user.getPassword().equals(passwd))
            return false;

        return true;
    }

    public boolean add_user_to_database(String email, String passwd, String username) {
        User nUser = new User();
        nUser.setEmail(email);
        nUser.setPassword(passwd);
        nUser.setUsername(username);

        if (userRepository == null)
            System.out.println("repository not available");

        nUser = userRepository.save(nUser);

        return userRepository.existsById(nUser.getId());
    }

    public Boolean delete_user(String email) {

        User user = get_user(email);

        userRepository.delete(user);
        return true;
    }

    public User find_user(String email) {
        return get_user(email);
    }

    private User get_user(String email) {
        Optional<User> users = userRepository.findByEmail(email);
        if (users.isEmpty())
            return null;
        return users.get();
    }

    public User[] get_all_users() {
        ArrayList<User> result = new ArrayList<>();
        userRepository.findAll().forEach(user -> result.add(user));
        return result.toArray(new User[result.size()]);
    }

}
