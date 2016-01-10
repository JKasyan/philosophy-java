package anotations.example1;

import java.util.List;

/**
 * Created by evgen on 10.01.16.
 */
public class PasswordUtils {

    @UseCase(id = 47, description = "Password must contain ay least one numeric")
    public boolean validatePassword(String password){
        return password.matches("\\w*");
    }

    @UseCase(id = 48)
    public String encrypt(String password){
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id = 49, description = "Repeat passwords!")
    public boolean checkNewPassword(List<String> paswords, String password){
        return !paswords.contains(password);
    }
}
