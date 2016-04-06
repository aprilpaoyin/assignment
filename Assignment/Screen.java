/*
 * Allows user to enter address of txt file, then displays a pop-up that shows words occurred in 
 * txt file and the frequency of it. 
 * 
 * Author: 	April Tan Pao Yin
 * Date: 	28/3/16
 * 
 */
package com.assignment.Assignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.*;

public class Screen extends JFrame implements ActionListener//, MouseListener
{
		private JTextField field; //shows address of file
		private JButton button1; //to confirm, read a file
		private JButton button2; //to browse for a file
		private String str1; //address of file in a str
		private JLabel label1; //label for the txt field
		final JFileChooser fChooser; //let user choose a file
		
		//constructor
		public Screen(String title)
		{
			//put a title for screen
			super(title);
			
			setLayout(new FlowLayout());
			
			label1 = new JLabel("Choose a text file");
			field = new JTextField("E.g. D:\\Documents\\my_file.txt");
		    field.setPreferredSize( new Dimension( 200, 30 ));
			fChooser = new JFileChooser();
			
			button1 = new JButton("Read this file");
			button2 = new JButton("Browse");
			
			add(label1);
		    add(field);
		    add(button2);
			add(button1);
			
			button1.addActionListener(this);
			button2.addActionListener(this);

			setVisible(true);
		}//end 1st constructor

		public void actionPerformed(ActionEvent arg0) 
		{

			if(arg0.getSource()==button1)
			{
				setStr1(field.getText());
			}
			else if (arg0.getSource() == button2)
			{
				int returnVal = fChooser.showOpenDialog(this);
				
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fChooser.getSelectedFile();
					JOptionPane.showMessageDialog(this, "Opening: " + file.getName());
				}
				else
				{
					JOptionPane.showMessageDialog(this, "CANCELLED");
				}
			}
			
		}//end actionlistener
		
		public JLabel getLabel1() 
		{
			return label1;
		}

		public void setLabel1(JLabel label1) 
		{
			this.label1 = label1;
		}

		public String getStr1() 
		{
			return str1;
		}

		public void setStr1(String str1) 
		{
			this.str1 = str1;
		}
}
