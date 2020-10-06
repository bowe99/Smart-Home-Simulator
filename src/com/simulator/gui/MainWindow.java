package com.simulator.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setForeground(new Color(0, 0, 0));
		panel.setToolTipText("");
		panel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.RAISED, null, null), new TitledBorder(new EmptyBorder(60, 10, 60, 10), "Simulation", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0))));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		
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
		panel.setLayout(new GridLayout(9, 0, 5, 5));
		panel.add(tglbtnOn);
		
		JButton btnNewButton = new JButton("Edit Profile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton);
		
		JLabel lblProfilePicture = new JLabel("Profile Picture");
		lblProfilePicture.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblProfilePicture);
		
		JLabel lblParent = new JLabel("Parent");
		lblParent.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblParent);
		
		JLabel lblLocation = new JLabel("Location:  Kitchen");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblLocation);
		
		JLabel lblOutsideTemperaturenc = new JLabel("Outside Temp: 19\u00B0C");
		panel.add(lblOutsideTemperaturenc);
		
		JLabel lblfirstLinesecondLine = new JLabel("<html>Fri Sep 18 2020<br>10:38:20</html>");
		lblfirstLinesecondLine.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblfirstLinesecondLine);
	}
}

