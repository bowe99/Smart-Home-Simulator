package com.simulator.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class MainWindow {
    public JFrame frame;

    /**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setForeground(new Color(0, 0, 0));
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(new EmptyBorder(10, 10, 10, 10), "Simulation", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JToggleButton tglbtnOn = new JToggleButton("off");
		tglbtnOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tglbtnOn.isSelected()) {
					tglbtnOn.setText("on");
				}
				else {
					tglbtnOn.setText("off");
				}
			}
		});
		panel.add(tglbtnOn);
	}
}

