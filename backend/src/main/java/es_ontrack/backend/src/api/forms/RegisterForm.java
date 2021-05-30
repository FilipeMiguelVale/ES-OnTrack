package es_ontrack.backend.src.api.forms;

public class RegisterForm extends LoginForm {
    private String role;
    private int roleType;

    public void setRole(String role) {
        this.role = role;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public String getRole() {
        return role;
    }

    public int getRoleType() {
        return roleType;
    }
}