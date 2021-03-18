package com.wilhelm.prisacaru_konsza;

import java.util.HashMap;
import java.util.Map;

public class Location {

    private final String description;
    private final Map<String, Integer> exits;


    public Location(String description, Map<String, Integer> exits) {
        this.description = description;
        if (exits != null) {
            this.exits = new HashMap<>(exits);
        } else {
            this.exits = new HashMap<>();
        }
        this.exits.put("Q", 0);

    }


    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new HashMap<String, Integer>(exits); //shallow copy
    }

    @Override
    public String toString() {
        return "Location description: " + description + ", available exits: " + exits.keySet();
    }
}
