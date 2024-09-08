package com.example.trainticket.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SectionService {
    private Map<String, Integer> sectionCapacity = new HashMap<>();

    public SectionService() {
        sectionCapacity.put("A", 50);
        sectionCapacity.put("B", 50);
    }

    public String allocateSection() {
        return sectionCapacity.get("A") > sectionCapacity.get("B") ? "A" : "B";
    }

    public void allocateSpecificSection(String section) {
        sectionCapacity.put(section, sectionCapacity.get(section) - 1);
    }

    public void releaseSection(String section) {
        sectionCapacity.put(section, sectionCapacity.get(section) + 1);
    }

    public boolean isSectionAvailable(String section) {
        return sectionCapacity.get(section) > 0;
    }
}
