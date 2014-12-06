package me.jtgi;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by John on 12/6/2014.
 */
public class StringPermutations {

    public static Set<String> perms(String s) {
        return permsHelper(s, "", new HashSet<String>());
    }

    private static Set<String> permsHelper(String s, String out, Set<String> resultSet) {
        if(s.isEmpty()) {
            resultSet.add(out);
            return resultSet;
        }

        for(int i = 0; i < s.length(); i++) {
            String reducedStr = new StringBuilder(s).deleteCharAt(i).toString();
            permsHelper(reducedStr, out + s.charAt(i), resultSet);
        }

        return resultSet;
    }

    public static void permsTest() {
        Set<String> set = perms("abcd");
        for(String s : set) {
            System.out.print("{");
            System.out.print(s + " ");
            System.out.print("}");
            System.out.println();
        }
    }

}
