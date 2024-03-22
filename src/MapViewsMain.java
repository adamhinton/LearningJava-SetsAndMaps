import dev.lpa.Contact;
import dev.lpa.ContactData;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapViewsMain {

    public static void main(String[] args) {

        Map<String, Contact> contacts = new HashMap<>();

        // Add phones and emails to contacts
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));

        Set<String> keysView = contacts.keySet();
        System.out.println(keysView);

    }

}
