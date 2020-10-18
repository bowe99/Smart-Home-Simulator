package com.simulator.model;

import java.io.*;

public class HouseLayoutParser {

    public static House loadFile(String fileName) throws Exception{
        House loadedHouse;
        String[] elementStack = new String[4];
        int depth = -1;
        String attribute = "";
        File layoutFile = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(layoutFile))){
            int linenumber = 1;
            String line = reader.readLine();
            //remove whitespace
            line = line.trim();

            //first line should be a house and address
            if(line.toLowerCase().contains("$house")){
                attribute = getAttribute(line);
                loadedHouse = new House(attribute);
                elementStack[++depth] = attribute;
            }
            else{
                System.out.println("Unable to find address field for house at line: " + linenumber);
                return null;
            }
            line = reader.readLine();

            //To scan through the lines of information
            while(line !=null){
                ++linenumber;

                //remove whitespace
                line = line.trim();
                String lowerCaseLine = line.toLowerCase();

                if (lowerCaseLine.charAt(0) == '}') {
                    depth--;
                }
                else if (lowerCaseLine.contains("$room")) {
                    attribute = getAttribute(line);
                    loadedHouse.addRoom(attribute);
                    elementStack[++depth] = attribute;
                } else if (lowerCaseLine.contains("$window")) {
                    attribute = getAttribute(line);
                    loadedHouse.addWindow(attribute, elementStack[depth]);
                    elementStack[++depth] = attribute;
                }
                else if (lowerCaseLine.contains("$door")) {
                    attribute = getAttribute(line);
                    loadedHouse.addDoor(attribute, elementStack[depth]);
                    elementStack[++depth] = attribute;
                }
                else if (lowerCaseLine.contains("$light")) {
                    attribute = getAttribute(line);
                    loadedHouse.addLight(attribute, elementStack[depth]);
                    elementStack[++depth] = attribute;
                }
                else if (lowerCaseLine.contains("$motionsensor")) {
                    attribute = getAttribute(line);
                    loadedHouse.addMotionSensor(attribute, elementStack[depth]);
                    elementStack[++depth] = attribute;
                }
                else if (lowerCaseLine.contains("$entrywaysensor")) {
                    attribute = getAttribute(line);
                    loadedHouse.addEntrywaySensor(attribute, elementStack[depth - 1], elementStack[depth]);
                    elementStack[++depth] = attribute;
                }

                //moving onto the next line of information
                line = reader.readLine();
            }
            reader.close();
            return loadedHouse;
        }
        catch(IOException e){
            System.out.println("Something went wrong loading the txt file");
            e.printStackTrace();
            return null;
        }
    }

    private static String getAttribute(String line){
        String attribute = "";
        attribute = line.substring(line.indexOf("\"") + 1);
        return attribute.substring(0, attribute.indexOf("\""));
    }
}
