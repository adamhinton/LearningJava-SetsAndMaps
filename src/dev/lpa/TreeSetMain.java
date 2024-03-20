package dev.lpa;

import java.util.*;

public class TreeSetMain {
    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        // Runtime error if no comparator passed, e must impl Comparable. So we pass a comparator
//        NavigableSet<Contact> sorted = new TreeSet<>(phones);

        Comparator<Contact> mySort = Comparator.comparing(Contact::getName);
        // Passing comparator because Contact doesn't implement Comparable
        NavigableSet<Contact> sorted = new TreeSet<>(mySort);
        sorted.addAll(phones);

        sorted.forEach(System.out::println);

        NavigableSet<String> justNames = new TreeSet<>();
        phones.forEach( c-> justNames.add(c.getName()));
        System.out.println(justNames);

        NavigableSet<Contact> fullSet = new TreeSet<>(sorted);
        fullSet.addAll(emails);
        fullSet.forEach(System.out::println);



        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.sort(sorted.comparator());
        System.out.println("-".repeat(30));
        fullList.forEach(System.out::println);


        // Contact doesn't impl Comparable, so need to pass comparator
        Contact min = Collections.min(fullSet, fullSet.comparator());
        Contact max = Collections.max(fullSet, fullSet.comparator());


        // Pretty cool, returns lowest (minimum) element in tree
        Contact first = fullSet.first();
        // Returns maximum (aka last) element in tree
        Contact last = fullSet.last();

        System.out.println("-".repeat(30));
        System.out.printf("min = %s, first = %s%n", min.getName(), first.getName());
        System.out.printf("max = %s, last = %s %n", max.getName(), last.getName());
        System.out.println("-".repeat(30));

        NavigableSet<Contact> copiedSet = new TreeSet<>(fullSet);
        // Removes and returns first/min
        System.out.println("first = " + copiedSet.pollFirst());
        System.out.println("last = " + copiedSet.pollLast());
        copiedSet.forEach(System.out::println);
        System.out.println("-".repeat(30));


        // Now we look at ways to ID the closest match in the set to what we pass in to a method
        // Daffy is in the set
        Contact daffy = new Contact("Daffy Duck");
        // Daisy isn't in the set
        Contact daisy = new Contact ("Daisy Duck");
        //snoopy would be last
        Contact snoopy = new Contact("Snoopy");
        // archie would be first
        Contact archie = new Contact("Archie");

        // Higher and ceiling
        for(Contact c : List.of(daffy, daisy, last, snoopy)){
            System.out.printf("Ceiling(%s)=%s%n", c.getName(), fullSet.ceiling(c));
            System.out.printf("higher(%s)=%s%n", c.getName(), fullSet.higher(c));
        }
        System.out.println("-".repeat(30));

        for(Contact c : List.of(daffy, daisy, last, snoopy)){
            System.out.printf("Ceiling(%s)=%s%n", c.getName(), fullSet.ceiling(c));
            System.out.printf("higher(%s)=%s%n", c.getName(), fullSet.higher(c));
        }


    }
}
