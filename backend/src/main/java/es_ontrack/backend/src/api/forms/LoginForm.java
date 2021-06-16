package es_ontrack.backend.src.api.forms;

public class LoginForm {
    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswd(String passwd) {
        this.password = passwd;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return password;
    }

}