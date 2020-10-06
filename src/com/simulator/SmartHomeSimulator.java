package com.simulator;

import java.awt.EventQueue;

import java.io.FileReader;

import com.simulator.gui.MainWindow; 

public class SmartHomeSimulator {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        
    }
}
