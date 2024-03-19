package edu.escuelaing.arem.ASE.app;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import com.google.gson.Gson;

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

    public boolean add (UserDTO userDTO) throws NoSuchAlgorithmException {
        if (userDTO != null) {
            users.put(userDTO.getEmail(), new User(userDTO));
            return true;
        }
        return false;
    }

    public User getByEmail (String email) {
        return users.get(email);
    }

    public ArrayList<String> getAll() {
        ArrayList<String> listOfUsers = new ArrayList<>();
        Collection<User> usersCollection = users.values();
        Gson gson = new Gson();
        for (User user : usersCollection) {
            listOfUsers.add(gson.toJson(user));
        }
        return listOfUsers;
    }

}
