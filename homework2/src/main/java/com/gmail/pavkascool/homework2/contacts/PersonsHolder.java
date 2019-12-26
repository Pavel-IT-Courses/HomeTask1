package com.gmail.pavkascool.homework2.contacts;

import java.util.ArrayList;
import java.util.List;

class PersonsHolder {
    private static final PersonsHolder ourInstance = new PersonsHolder();

    private List<Person> persons;

    public static PersonsHolder getInstance() {
        return ourInstance;
    }

    private PersonsHolder() {
        persons = new ArrayList<Person>();
    }

    public boolean add(Person person) {
        return persons.add(person);
    }
    public int size() {
        System.out.println("ATTENTION!!! " + persons);
        System.out.println("Persons size is " + persons + " " + persons.size());
        return persons.size();
    }
    public Person get(int index) {
        return persons.get(index);
    }
    public boolean remove(Person p) {
        return persons.remove(p);
    }
}
