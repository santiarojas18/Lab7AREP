package edu.escuelaing.arem.ASE.app;
import com.google.gson.Gson;

import static spark.Spark.*;

public class FacadeServices {
    public static void main( String[] args ) {
        staticFiles.location("/public");
        secure("certificados/ecikeystorefacade.p12", "123456", null, null);
        port(getPort());

        get("/login", (req, res) -> {
            String email = req.queryParams("email");
            String password = req.queryParams("password");
            String response = SecureURLReader.makeGet("https://localhost:5000/login?email=" + email + "&password=" + password);
            return response;
        });

        get("/users", (req, res) -> {
            res.type("application/json");
            String response = SecureURLReader.makeGet("https://localhost:5000/users");
            return response;
        });

        get("/users/:email", (req, res) -> {
            res.type("application/json");
            String email = req.params(":email");
            String response = SecureURLReader.makeGet("https://localhost:5000/users/" + email);
            return response;
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
