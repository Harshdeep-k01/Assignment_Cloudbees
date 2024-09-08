package com.example.trainticket.model;

import java.util.HashMap;
import java.util.Map;

public class Train {
    private String id;
    private String name;
    private String route;
    private Map<String, Section> sections;

    public Train(String id, String name, String route) {
        this.id = id;
        this.name = name;
        this.route = route;
        this.sections = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void addSection(String sectionName, int capacity) {
        sections.put(sectionName, new Section(sectionName, capacity));
    }

    public Section getSection(String sectionName) {
        return sections.get(sectionName);
    }

    public Map<String, Section> getSections() {
        return new HashMap<>(sections);
    }


    public Train(String id) {
        this.id = id;
        this.sections = new HashMap<>();
    }


    public boolean isSeatAvailable(String sectionName, int seatNumber) {
        Section section = sections.get(sectionName);
        return section != null && section.isSeatAvailable(seatNumber);
    }

    public void occupySeat(String sectionName, int seatNumber) {
        Section section = sections.get(sectionName);
        if (section != null) {
            section.occupySeat(seatNumber);
        }
    }

    public void releaseSeat(String sectionName, int seatNumber) {
        Section section = sections.get(sectionName);
        if (section != null) {
            section.releaseSeat(seatNumber);
        }
    }

}