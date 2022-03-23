package com.example.exampl_r.Message_class;

import java.util.ArrayList;

public class Biography {
    private String fullName;
    private ArrayList<String> aliases;
    private String placeOfBirth;
    private String firstAppearance;
    private String publisher;
    private String alignment;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public StringBuilder getAliases() {
        StringBuilder allAliases = new StringBuilder();
        for (String el : aliases){
            allAliases.append(el).append(", ");
        }
        allAliases.setCharAt(allAliases.length() - 2, '.');
        return allAliases;
    }

    public void setAliases(ArrayList<String> aliases) {
        this.aliases = aliases;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
}
