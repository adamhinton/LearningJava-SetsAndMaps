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

        for(Contact c : List.of(daffy, daisy, first, archie)){
            System.out.printf("floor(%s)=%s%n", c.getName(), fullSet.floor(c));
            System.out.printf("lower(%s)=%s%n", c.getName(), fullSet.lower(c));
        }
        System.out.println("-".repeat(30));


        // Frex:
        // Knowing the next card in a player's sorted Hand would make it easier to look for straights and flushes
        // Risk battleodds, something about sorted dice rolls


        // Now for methods that return different sets


        // Descending sorted order
        // This is backed by the original set. So any changes in the orig will be reflected here, and vice versa.
        // That could be dangerous
        NavigableSet<Contact> descendingSet = fullSet.descendingSet();
        descendingSet.forEach(System.out::println);
        System.out.println("-".repeat(30));

        // Remove charlie brown from descending set; this is reflected in original set as well
        Contact lastContact = descendingSet.pollLast();
        System.out.println("Removed: " + lastContact);
        descendingSet.forEach(System.out::println);
        System.out.println("-".repeat(30));
        fullSet.forEach(System.out::println);
        System.out.println("-".repeat(30));


        // headSet is all the elements who are strictly less than marion
        // treesets are pretty cool
        // Exclusive by default, it excludes the element passed. But we can override this. As opposed to tailSet
        // which is inclusive by default.
        Contact marion = new Contact("Maid Marion");
        var headSet = fullSet.headSet(marion, true);
        headSet.forEach(System.out::println);
        System.out.println("-".repeat(30));


        // Tail set is inclusive by default, as opposed to headSet which is exclusive
        var tailSet = fullSet.tailSet(marion, false);
        tailSet.forEach(System.out::println);
        System.out.println("-".repeat(30));

        // Will return error on attempt to insert item outside its range. Very interesting
        Contact linus = new Contact("Linus Van Pelt");
        var subset = fullSet.subSet(linus, false, marion, true);
        subset.forEach(System.out::println);

        // TreeSet specs:
        // Higher cost than simple arrays etc
        // But it's self-sorting which is very cool
        // Continuously re-sorts itself as you add and remove els
        // Also won't contain dupes



        // STARTING MAPS
        // HashMap is unordered
        // LinkedHashMap is ordered by insertion order
        // TreeMap is a sorted map




    }
}
