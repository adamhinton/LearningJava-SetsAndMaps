import dev.lpa.Contact;
import dev.lpa.ContactData;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MapViewsMain {

    public static void main(String[] args) {

        Map<String, Contact> contacts = new HashMap<>();

        // Add phones and emails to contacts
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));

        Set<String> keysView = contacts.keySet();
        System.out.println(keysView);


        // Now they're in alph order
        Set<String> copyOfKeys = new TreeSet<>(contacts.keySet());
        System.out.println(copyOfKeys);

        if(contacts.containsKey("Linus Van Pelt")){
            System.out.println("I like linux and it's in contacts");
        }

    }

}
