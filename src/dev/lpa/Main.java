package dev.lpa;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {



    }

    public static void printData (String header, Collection<Contact> contacts){
        System.out.println("-".repeat(30));
        System.out.println(header);
        System.out.println("-".repeat(30));
        contacts.forEach(System.out::println);
    }
}