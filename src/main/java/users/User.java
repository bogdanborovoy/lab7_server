package users;

import exceptions.UnsuitablePasswordException;
import helpers.CollectionManager;
import helpers.Invoker;
import models.SpaceMarine;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeSet;

public class User {
    private final String username;
    private final String password;
    private Invoker invoker;
    private CollectionManager cm;
    public User(String username, String password) {
        this.username = username;
        this.password = hashPassword(password);
        this.cm = new CollectionManager();
        this.invoker = new Invoker(this.cm);


    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String hashPassword(String password) {
        String hashedPassword = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            hashedPassword = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if (hashedPassword.isEmpty()) {
            throw new UnsuitablePasswordException();
        }
        return hashedPassword;
    }
    public Invoker getInvoker() {
        return invoker;
    }
    public CollectionManager getCm() {
        return cm;
    }
    public void setCm(CollectionManager cm) {
        this.cm = cm;
    }

}
