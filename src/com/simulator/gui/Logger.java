package com.simulator.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Logger {

    @FXML
    private static TextArea outputConsole;

    private static Logger instance = null;

    private Logger() {
    }

    public static Logger newInstance(TextArea outputConsole2) {
        try {
            if (instance == null) {
                instance = new Logger();
                outputConsole = outputConsole2;
                outputConsole.setText("Welcome to Smart Home Simulator");
            }
            return instance;
        } catch (Exception e) {
            System.out.print("something went wrong");
            return null;
        }
    }

    public static Logger getInstance() {
        return instance;
    }

    public void ouputToConsole(String output) {
        outputConsole.appendText("\n" + output);
    }
    public void resetLogFile(){
        try{
        Files.write(Paths.get("./log.txt"), "".getBytes());
        }
        catch(Exception e){
            System.out.print("could not erase log file");
        }
    }

    public void outputToLogFile(String outputToLog) throws IOException {
        outputToLog = outputToLog+"\n";
        try{
        Files.write(Paths.get("./log.txt"), outputToLog.getBytes(), StandardOpenOption.APPEND);
        }
        catch(IOException e){
            System.out.println("Could not write to log.txt");
        }
    }
}

    

