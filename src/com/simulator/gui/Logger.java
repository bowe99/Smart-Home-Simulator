package com.simulator.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public void outputToLogFile(String outputToLog) throws IOException {
        Files.write(Paths.get("./log.txt"), outputToLog.getBytes());
    }
}

    

