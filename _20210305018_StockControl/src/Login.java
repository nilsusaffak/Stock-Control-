import java.util.Scanner;

public class Login {
    private String correctUsername = "nilsu";
    private String correctPassword = "123";

    // Giriş doğrulama metodu
    public boolean authenticate(String username, String password) {
        return username.equals(correctUsername) && password.equals(correctPassword);
    }
}

