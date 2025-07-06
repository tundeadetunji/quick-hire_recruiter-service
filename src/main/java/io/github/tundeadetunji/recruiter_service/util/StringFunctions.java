package io.github.tundeadetunji.recruiter_service.util;

public class StringFunctions {
    private static final String SPACE = " ";

    public static String toPlural(int count, String word){

        //for now, only appends 's'
        //would be more powerful in real prod
        return count == 1 ? "1 " + word.toLowerCase() : count + SPACE + word.toLowerCase() + "s";
    }
}
