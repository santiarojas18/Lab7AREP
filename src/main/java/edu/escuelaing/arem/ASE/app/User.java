package edu.escuelaing.arem.ASE.app;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class User {
    String name;

    String lastName;

    String email;

    String passwordHash;

    Date createdAt;

    public User()
    {
    }


    public User (UserDTO userDto) throws NoSuchAlgorithmException {
        name = userDto.getName();
        lastName = userDto.getLastName();
        email = userDto.getEmail();
        createdAt = new Date();
        String password = userDto.getPassword();

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuffer sb = new StringBuffer();

        for(byte b : digest) {
            sb.append(String.format("%02x", b));
        }

        passwordHash = sb.toString();
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getLastName()
    {
        return lastName;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }

}
