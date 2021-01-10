package com.example.collegehelper;

public class Check {
    public static boolean checkLetter (String word) {
        if (word.matches("^[a-zA-Z0-9]*$")) {
            return false;
        } else return true;
    }

    public static boolean checkSpace (String word) {
        if (word.contains(" ")) return true;
        else return false;
    }
}
