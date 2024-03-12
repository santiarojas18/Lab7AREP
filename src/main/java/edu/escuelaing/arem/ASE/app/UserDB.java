package edu.escuelaing.arem.ASE.app;

import java.util.HashMap;

public class UserDB {
    private HashMap<String, User> users;

    private static UserDB instance = null;
    private UserDB() {
        users = new HashMap<>();
    }
    public static UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

}
