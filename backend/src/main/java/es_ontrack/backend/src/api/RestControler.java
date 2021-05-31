package es_ontrack.backend.src.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es_ontrack.backend.src.api.forms.LoginForm;
import es_ontrack.backend.src.api.forms.RegisterForm;
import es_ontrack.backend.src.database.DBControler;

@RestController
public class RestControler {

    // private static final Logger logger = LogManager.getLogger(); TODO: Set up
    // logger

    private static class Response {
        String response;

        public void setResponse(String response) {
            this.response = response;
        }

        public String getResponse() {
            return response;
        }
    }

    // #region POSTS

    @PostMapping("/login/request")
    public ResponseEntity<Response> login(@RequestBody LoginForm login) {
        // System.out.println("email: " + login.getEmail());
        // System.out.println("passwd: " + login.getPasswd());

        Response result = new Response();
        result.setResponse("Invalid username or password");

        // TODO: check database
        if (DBControler.canLogin(login.getEmail(), login.getPasswd())) {
            result.setResponse("DONE");
        }

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/add_user")
    public ResponseEntity<String> add_user(@RequestBody RegisterForm registration) {
        String email = registration.getEmail();
        String passwd = registration.getPasswd();
        String role = registration.getRole();
        int roleType = registration.getRoleType();
        if (DBControler.add_user_to_database(email, passwd, role, roleType))
            return ResponseEntity.ok().body("DONE");

        return ResponseEntity.ok().body("FAILED TO ADD USER");
    }

    @PostMapping("/delete_user")
    public ResponseEntity<String> delete_user(@PathVariable String email) {
        // handle delete_user
        DBControler.delete_user(email);
        if (!DBControler.get_user(email))
            return ResponseEntity.ok().body("Delete successful");

        return ResponseEntity.ok().body("Delete unsuccessful");
    }

    // #endregion

    // #region GETS
    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        // handle logout
        return ResponseEntity.ok().body("you are logged out");
    }

    // @GetMapping("/all_users")
    // public ResponseEntity<User[]> get_users() {
    // // get all users
    // User[] users = DBControler.get_all_users();

    // return ResponseEntity.ok().body(users);
    // }

    // #region BUS

    // #endregion

    // #endregion
}
