package dev.lpa;

import java.util.HashSet;
import java.util.Set;

public class Contact {

    private String name;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();


    public Contact(String name) {
        this(name, null);
    }

    public Contact(String name, String email) {
        this(name, email, 0);
    }

    public Contact(String name, long phone) {
        this(name, null, phone);
    }

    public Contact (String name, String email, long phone){
        this.name = name;
        if (email != null){
            emails.add(email);
        }
        // Phone might just be 0; aka null
        if (phone > 0){
            String p = String.valueOf(phone);
            p = "(%s) %s-%s".formatted(p.substring(0, 3), p.substring(3, 6), p.substring(6));
            phones.add(p);
        }
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        // Java really needs template literals
        return "%s: %s %s".formatted(name, emails, phones);
    }

    public Contact mergeContactData (Contact contact){
        Contact newContact = new Contact(name);
        newContact.emails = new HashSet<>(this.emails);
        newContact.phones = new HashSet<>(this.phones);
        // Only adds non-dupes
        newContact.emails.addAll(contact.emails);
        newContact.phones.addAll(contact.phones);
        return newContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Contact contact = (Contact) o;

        return getName().equals(contact.getName());
    }

    @Override
    public int hashCode() {
        // A good idea to make sure objects that aren't the same class don't prod the same hashcode
        // comp sci isn't sure what the best numver is; probably any common one is fine.
        return getName().hashCode() * 33;
    }


    public void addEmail(String companyName){
        String[] names = name.split(" ");
        String email = "%c%s@%s.com".formatted(name.charAt(0), names[names.length -1], companyName.replaceAll(" ",
                "").toLowerCase());
        if(!emails.add(email)){
            System.out.println(name + " already has email " + email);
        }
        else {
            System.out.println("added email " + email + " to " + name);
        }
    }
}
