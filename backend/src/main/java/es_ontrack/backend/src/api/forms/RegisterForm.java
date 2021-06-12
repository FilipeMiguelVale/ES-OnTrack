package es_ontrack.backend.src.api.forms;

public class RegisterForm extends LoginForm {
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}