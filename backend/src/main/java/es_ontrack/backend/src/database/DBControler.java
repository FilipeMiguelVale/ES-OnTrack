package es_ontrack.backend.src.database;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import es_ontrack.backend.src.database.models.User;

public class DBControler {
    @Autowired
    private static UserRepository userRepository;

    public static boolean canLogin(String email, String passwd) {

        User user = userRepository.findByEmail(email).get(0);

        if (user == null)
            return false;
        if (!user.getPassword().equals(passwd))
            return false;

        return true;
    }

    public static boolean add_user_to_database(String email, String passwd, String role, int roleType) {
        User nUser = new User();
        nUser.setEmail(email);
        nUser.setPassword(passwd);
        nUser.setRole(role);
        nUser.setRoleType(roleType);

        nUser = userRepository.save(nUser);

        return userRepository.existsById(nUser.getId());
    }

    public static void delete_user(String email) {

        User userToDelete = userRepository.findByEmail(email).get(0);

        userRepository.delete(userToDelete);
    }

    public static boolean get_user(String email) {
        return !userRepository.findByEmail(email).isEmpty();
    }

    public static User[] get_all_users() {
        ArrayList<User> result = new ArrayList<>();
        userRepository.findAll().forEach(user -> result.add(user));
        return result.toArray(new User[result.size()]);
    }

}
