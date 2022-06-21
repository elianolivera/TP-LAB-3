package Logica;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public Validator() {
    }

    public boolean validAlphabetical(String text) {
        if(isEmpty(text)) {
            return false;
        }

        char[] chars = text.toCharArray();

        for(Character c : chars) {
            if(Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public boolean validDNI(String text) {
        if(isEmpty(text) || text.length() != 8) {
            return false;
        }

        char[] chars = text.toCharArray();

        for(Character c : chars) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public boolean validEmail(String text) {
        if(isEmpty(text)) {
            return false;
        }

        final String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }

    public boolean validDate(String text) {
        if(isEmpty(text)) {
            return false;
        }

        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        df.setLenient(false);

        try {
            df.parse(text);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public boolean isEmpty(String text) {
        if(text == null || text.equals("")) {
            return true;
        }

        return false;
    }
}
