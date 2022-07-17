package com.company;

public enum Subject {

    MATHEMATICS("Math"),
    BIOLOGY("Bio"),
    CHEMISTRY("Chem"),
    PHYSICS ("Phys"),
    ART("Art"),
    GEOGRAPHY ("Geo"),
    LITERACY("Literature");

    private final String name;

    private Subject(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }



}
