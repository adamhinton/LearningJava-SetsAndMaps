package dev.lpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");

        printData("Phone List", phones);
        printData("Email List", emails);

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);
        printData("Phone contacts", phoneContacts);
        printData("Email contacts", emailContacts);


        int index = emails.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmail("Sherwood Forest");
        robinHood.addEmail("Sherwood Forest");
        robinHood.replaceEmailIfExists("RHood@sherwoodforest.com", "RHood@sherwoodforest.org");
        System.out.println(robinHood);

        // A is phone, B is emails
        // This is creating a union of phone and email
        Set<Contact> unionAB = new HashSet<>();
        unionAB.addAll(phoneContacts);
        unionAB.addAll(emailContacts);
        printData("(A ∪ B) union of emails (A) with phones (B)", unionAB);


        // INTERSECT
        // The intersection of two or more sets returns only the elements they have in common
        Set<Contact> intersectAB = new HashSet<>(emailContacts);
        // This way shows emails
        // Switching this to phonesC, retainall (EmailC) would retain phones
        intersectAB.retainAll(phoneContacts);
        printData("(A ∩ B) Intersect Emails (A) and phones (B)", intersectAB);

        Set<Contact> intersectBA = new HashSet<>(phoneContacts);
        intersectBA.retainAll(emailContacts);
        printData("(B ∩ A) Intersect Phones (B) and Emails (A)", intersectBA);



        // DIFFERENCE
        Set<Contact> AMinusB = new HashSet<>(emailContacts);
        AMinusB.removeAll(phoneContacts);
        printData("(A - B) Emails (A) minus phones (B)", AMinusB);

        Set<Contact> BMinusA = new HashSet<>(phoneContacts);
        BMinusA.removeAll(emailContacts);
        printData("(B - A) Phones (B) minus Emails (A)", AMinusB);

    }

    public static void printData (String header, Collection<Contact> contacts){
        System.out.println("-".repeat(30));
        System.out.println(header);
        System.out.println("-".repeat(30));
        contacts.forEach(System.out::println);
    }
}