package dev.moralestorres.util;

import jade.core.Agent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import dev.moralestorres.agents.RegressionAgent;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private JTextField[] x;
	private double[] xValues;
	
	public GUI(String title, int fields, RegressionAgent agent){
		this.x = new JTextField[fields];
		this.xValues = new double[fields];
		this.setTitle(title);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(fields+2, 2));
		for(int i=0;i<fields;++i){
			p.add(new JLabel("x[" + i + "]: ", SwingConstants.RIGHT));
			this.x[i] = new JTextField(15);
			p.add(this.x[i]);
		}
		getContentPane().add(p, BorderLayout.CENTER);
		JButton predictButton = new JButton("Predict");
		JLabel lbl = new JLabel();
		predictButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				try {
					for(int i=0;i<x.length;++i)
						xValues[i] = Double.parseDouble(x[i].getText().trim());
					lbl.setText(Double.toString(agent.predict(xValues)));
				}catch(Exception e){
					JOptionPane.showMessageDialog(GUI.this, "Invalid values. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p.add(predictButton);
		p.add(new JLabel(""));
		p.add(new JLabel("Value: "));
		p.add(lbl);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				((Agent) agent).doDelete();
			}
		} );
		setResizable(false);
	}
	public void showGui(){
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}
