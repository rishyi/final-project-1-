package lk.ijse.shop.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void textFieldValidate(lk.ijse.shop.Util.TextField textField, String text){
        String field = "";

        switch(textField){
            case NAME -> field = "([a-zA-Z\\s]+){3,}";
            case ADDRESS ->  field =  "^([A-z0-9]|[-/,. @+]|\\\\s){4,}$";
        }
    }
}
