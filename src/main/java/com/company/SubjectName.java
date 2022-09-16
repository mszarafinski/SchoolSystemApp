package com.company;

public enum SubjectName {

    MATHEMATICS("Math"),
    BIOLOGY("Bio"),
    CHEMISTRY("Chem"),
    PHYSICS ("Phys"),
    ART("Art"),
    GEOGRAPHY ("Geo"),
    LITERACY("Literature");

    private final String name;

    private SubjectName(String name){
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
