package edu.escuelaing.arem.ASE.app;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.google.gson.Gson;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class UserController
{
    public static void main( String[] args ) throws NoSuchAlgorithmException {
        secure("certificados/ecikeystore.p12", "123456", null, null);
        port(getPort());
        UserDB userDB = UserDB.getInstance();
        UserDTO userToAdd = new UserDTO("Santiago", "Arevalo", "santiar18@hotmail.com", "Colombia18*");
        userDB.add(userToAdd);

        get("/login", (req, res) -> {
            String email = req.queryParams("email");
            String password = req.queryParams("password");

            User userToValid = userDB.getByEmail(email);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();

            for(byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            String passwordHash = sb.toString();
            return passwordHash.equals(userToValid.getPasswordHash());
        });

        get("/users", (req, res) -> {
            res.type("application/json");
            return userDB.getAll();
        });

        get("/users/:email", (req, res) -> {
            res.type("application/json");
            String email = req.params(":email");
            User user = userDB.getByEmail(email);
            Gson gson = new Gson();
            return gson.toJson(user);
        });

        post("/users", (req, res) -> {
           String jsonBody = req.body();
           Gson gson = new Gson();
           UserDTO userDTO = gson.fromJson(jsonBody, UserDTO.class);
           return userDB.add(userDTO);
        });

        get("/hello", (req, res) -> "Hello world");
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

}
