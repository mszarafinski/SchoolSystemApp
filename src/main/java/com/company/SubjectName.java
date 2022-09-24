package com.company;

public enum SubjectName {

    MATHEMATICS("MATH"),
    BIOLOGY("BIO"),
    CHEMISTRY("CHEM"),
    PHYSICS ("PHYS"),
    ART("ART"),
    GEOGRAPHY ("GEO"),
    LITERACY("LIT");

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
