package lk.ijse.shop.Util;

import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean textFieldValidate(lk.ijse.shop.Util.TextField textField, String text){
        String field = "";

        switch(textField){
            case NAME -> field = "([a-zA-Z\\s]+){3,}";
            case ADDRESS ->  field =  "^([A-z0-9]|[-/,. @+]|\\\\s){4,}$";
        }
        Pattern pattern = Pattern.compile(field);

        if (text != null){
            if(text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }
        Matcher matcher = pattern.matcher(text);

        if(matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(lk.ijse.shop.Util.TextField location,TextField textField ){
        if (Regex.textFieldValidate(location,textField.getText())){
                textField.setStyle("-fx-text-fill: green; -fx-border-color: green;");
                return true;
        }else {
            textField.setStyle("-fx-text-fill: red; -fx-border-color: red;");
            return false;
        }
    }
}
