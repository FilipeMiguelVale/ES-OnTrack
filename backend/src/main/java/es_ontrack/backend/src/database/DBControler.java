package es_ontrack.backend.src.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es_ontrack.backend.src.database.models.User;

@Controller
public class DBControler {

    @Autowired
    private UserRepository userRepository;

    public boolean canLogin(String email, String passwd) {

        List<User> user = userRepository.findByEmail(email);

        if (user.isEmpty())
            return false;
        if (!user.get(0).getPassword().equals(passwd))
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

        List<User> userToDelete = userRepository.findByEmail(email);

        if (userToDelete.isEmpty())
            return false;

        userRepository.delete(userToDelete.get(0));
        return true;
    }

    public boolean get_user(String email) {
        return !userRepository.findByEmail(email).isEmpty();
    }

    public User[] get_all_users() {
        ArrayList<User> result = new ArrayList<>();
        userRepository.findAll().forEach(user -> result.add(user));
        return result.toArray(new User[result.size()]);
    }

}
