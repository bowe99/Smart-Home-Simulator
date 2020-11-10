package com.simulator.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * This is a singleton class used to hold the outputConsole so it can be written to by all that need it
 */
public class Logger {

    @FXML
    private static TextArea outputConsole;

    private static Logger instance = null;

    private Logger() {
    }

    
    /** 
     * creates a new instance of the Logger object to be contained within the instance Logger object
     * @param outputConsole2
     * @return Logger
     */
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

    
    /** 
     * returns the instance that is held
     * @return Logger
     */
    public static Logger getInstance() {
        return instance;
    }

    
    /** 
     * outputs a given string to the Output Console on the Smart home dashboard
     * @param output
     */
    public void ouputToConsole(String output) {
        outputConsole.appendText("\n" + output);
    }

    /** 
     * resets the contents of the log.txt file
     */
    public void resetLogFile(){
        try{
        Files.write(Paths.get("./log.txt"), "".getBytes());
        }
        catch(Exception e){
            System.out.print("could not erase log file");
        }
    }

    
    /** 
     * Outputs to log.txt file
     * @param outputToLog
     * @throws IOException
     */
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

    

