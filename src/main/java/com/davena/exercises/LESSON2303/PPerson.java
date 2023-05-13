package com.davena.exercises.LESSON2303;

import java.util.Objects;

public class PPerson implements Comparable<PPerson>{
    String name;
    String lastname;

    public PPerson(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PPerson pPerson)) return false;
        return Objects.equals(getName(), pPerson.getName()) && Objects.equals(getLastname(), pPerson.getLastname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLastname());
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public int compareTo(PPerson o) {
        int r = name.compareTo(o.getName());
        if (r != 0) {
            return r;
        } else {
            return lastname.compareTo(o.lastname);
        }

    }
}
