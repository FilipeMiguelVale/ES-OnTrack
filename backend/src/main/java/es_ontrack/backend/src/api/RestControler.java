package es_ontrack.backend.src.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import es_ontrack.backend.src.api.forms.LoginForm;
import es_ontrack.backend.src.api.forms.RegisterForm;
import es_ontrack.backend.src.database.DBControler;
import es_ontrack.backend.src.database.models.User;
import lombok.extern.log4j.Log4j2;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

@Log4j2
@RestController
public class RestControler {

    @Autowired
    private DBControler dbControler;
    
    private static final Logger LOG = Logger.getLogger(RestControler.class.getName());


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

        Response result = new Response();
        result.setResponse("Invalid username or password");

        if (dbControler.canLogin(login.getEmail(), login.getPasswd())) {
            result.setResponse("Done");
        }

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/register_user")
    public ResponseEntity<Response> add_user(@RequestBody RegisterForm registration) {
        String email = registration.getEmail();
        String passwd = registration.getPasswd();
        String username = registration.getUsername();

        Response result = new Response();
        result.setResponse("FAILED TO ADD USER");

        if (dbControler.add_user_to_database(email, passwd, username))
            result.setResponse("Done");

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/delete_user/{email}")
    public ResponseEntity<String> delete_user(@PathVariable String email) {
        if (!dbControler.delete_user(email))
            return ResponseEntity.ok().body("User Not Found");
        if (!dbControler.get_user(email))
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

    @GetMapping("/all_users")
    public ResponseEntity<User[]> get_users() {
        // get all users
        User[] users = dbControler.get_all_users();

        return ResponseEntity.ok().body(users);
    }

    // #region BUS
    @GetMapping("/api/list_accidents")
    public ResponseEntity<String> list_accidents() {
        // handle logout
        return ResponseEntity.ok().body("ListAccidents");
    }

    // @app.route('/api/list_accidents', methods=['GET'])
    // def api_list_accidents():
    // global ind
    // global initTime
    //
    // autobus[lines[ind]["node_id"]]={"lat":lines[ind]["lat"],"lng":lines[ind]["lon"],"ts":lines[ind]["ts"]}
    // if ind > 999:
    // ind = 0
    //
    // to_del = [k for k,v in autobus.items() if initTime -
    // datetime.strptime(v["ts"], '%Y-%m-%d %H:%M:%S.%f') > timedelta(minutes=60) ]
    //
    // #to_del = [k for k,v in autobus.items() if k != "00000000 - 0000 - 0000 -
    // 0000 - 000000002755" ]
    //
    // for key in to_del: del autobus[key]
    //
    // initTime = datetime.strptime(lines[ind]["ts"], '%Y-%m-%d %H:%M:%S.%f')
    //
    // ind += 1
    // return autobus

    // #endregion

    // #endregion
}
