package com.wilhelm.prisacaru_konsza;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, Integer> exits = new HashMap<>();
    private static final Map<Integer, Location> locationMap = new HashMap<>();
    private static final Map<String, String> vocabulary = new HashMap<>();
    private static int counter = 0;

    public static void addLocation(Location location) {

        if (location != null) {
            locationMap.put(counter, location);
            counter++;
        }
    }

    public static void addLocationsAndExits() {
        addLocation(new Location("You are sitting in front of a computer learning Java", null));

        exits.put("W", 2);
        exits.put("E", 3);
        exits.put("S", 4);
        exits.put("N", 5);
        addLocation(new Location("You are standing at the end of the road before a small brick building", exits));

        exits = new HashMap<>();
        exits.put("N", 5);
        addLocation(new Location("You are at the top of the hill", exits));


        exits = new HashMap<>();
        exits.put("W", 1);
        addLocation(new Location("You are inside a building, a well house for a small spring", exits));

        exits = new HashMap<>();
        exits.put("N", 1);
        exits.put("W", 2);
        addLocation(new Location("You are in a valley beside a stream", exits));

        exits = new HashMap<>();
        exits.put("S", 1);
        exits.put("W", 2);
        addLocation(new Location("You are in the forest", exits));

    }

    public static void run() {


        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        int startingLocation = 1;
        int currentLocation = startingLocation;


        System.out.println(locationMap.get(startingLocation));
        Map<String, String> directionMap = new HashMap<>();
        directionMap.put("north", "N");
        directionMap.put("south", "S");
        directionMap.put("west", "W");
        directionMap.put("east", "E");
        directionMap.put("quit", "Q");


        while (!exit) {
            String direction = "";
            String input = scanner.nextLine();
            String[] splitInput;
            if (input.toLowerCase().trim().length() > 1) {
                splitInput = input.split(" ");
                for (String s : splitInput) {
                    if (directionMap.containsKey(s)) {
                        direction = directionMap.get(s);
                    }

                }


            } else
                direction = input.toUpperCase();

            if (direction.equals("Q")) {
                exit = true;
                scanner.close();
            } else if (locationMap.get(currentLocation).getExits().containsKey(direction)) {

                currentLocation = locationMap.get(currentLocation).getExits().get(direction);
                System.out.println(locationMap.get(currentLocation));

            } else {
                System.out.println("You can't go in that direction!");
                System.out.println(locationMap.get(currentLocation));
            }
        }
    }


    public static void main(String[] args) {
        // write your code here

        addLocationsAndExits();
        command();

    }

    public static void command() {

        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");
        vocabulary.put("QUIT", "Q");

        // write code here
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int currentLocation = 1;
        String direction = "";
        while (!quit) {

            System.out.println(locationMap.get(currentLocation).getDescription());
            System.out.print("Available exits are ");

            for (String exitsMapItem : locationMap.get(currentLocation).getExits().keySet()) {
                System.out.print(exitsMapItem + ", ");
            }
            System.out.println();

            //reading input
            String input = scanner.nextLine();

            //split logic

            if (input.length() > 1) {
                String[] splitInput = input.split(" ");

                for (String s : splitInput) {

                    //checking if splitInput contains item which is equal to key from vocabulary
                    if (vocabulary.containsKey(s.toUpperCase())) {
                        //direction now should be a letter (E,W,N,S,Q)
                        direction = vocabulary.get(s.toUpperCase());
                    }
                }
            } else
                direction = input.toUpperCase();

            //testing if direction is contained within the exits map or if it's Q in which case we exit while loop
            if (direction.equals("Q")) {
                quit = true;
                scanner.close();
            } else if (locationMap.get(currentLocation).getExits().containsKey(direction)) {
                currentLocation = locationMap.get(currentLocation).getExits().get(direction);
            } else
                System.out.println("Invalid direction" + "\n");


        }
    }

}


