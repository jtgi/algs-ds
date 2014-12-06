package me.jtgi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by John on 12/6/2014.
 */
public class Powerset {

    public static <T> Set<Set<T>> powerSet(Set<T> set) {
        Set<Set<T>> sets = new HashSet<Set<T>>();

        if(set.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }

        ArrayList<T> list = new ArrayList<T>(set);
        T head = list.get(0);
        Set<T> tail = new HashSet<T>(list.subList(1, list.size()));

        for(Set<T> subset : powerSet(tail)) {
            Set<T> newSet = new HashSet<T>(subset);
            newSet.add(head);
            sets.add(newSet);
            sets.add(subset);
        }

        return sets;
    }

    public static void powerSetTest() {
        HashSet<Integer> h = new HashSet<Integer>();

        h.add(1);
        h.add(2);
        h.add(3);
        h.add(4);

        Set<Set<Integer>> result = powerSet(h);

        for(Set<Integer> r : result) {
            System.out.print("{");
            for(Integer i : r) System.out.print(i + ", ");
            System.out.print("}");
        }

    }

}
