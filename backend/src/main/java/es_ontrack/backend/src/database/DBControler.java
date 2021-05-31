package es_ontrack.backend.src.database;

// import es_ontrack.backend.src.api.User;

public class DBControler {

    public static boolean canLogin(String email, String passwd) {
        return true;
    }

    public static boolean add_user_to_database(String email, String passwd, String role, int roleType) {
        return true;
    }

    public static void delete_user(String email) {
    }

    public static boolean get_user(String email) {
        return false;
    }

    // public static User[] get_all_users() {
    // return null;
    // }

}
