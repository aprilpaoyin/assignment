/*
 * CODE: 		SCREEN (GUI)
 * DESCRIPTION:	Allows user to enter address of txt file, then displays a pop-up that shows words occurred in 
 * 				txt file and the frequency of it. 
 * AUTHOR: 		APRIL TAN
 * DATE: 		28 MAR 2016
 * 
 */
package com.assignment.Assignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Screen extends JFrame implements ActionListener, MouseListener
{
	//private JTextField field2;
		private JTextField field;
		private JButton button1;
		//private JButton button2;
		private String str1;
		private String str2;
		private JLabel label1;

	//constructor
		public Screen(String title)
		{
			super(title);
			
			setLayout(new FlowLayout());	
			
			label1 = new JLabel("Enter address of text file:");
			field = new JTextField("E.g. D:\\Documents\\my_file.txt");
		    field.setPreferredSize( new Dimension( 200, 30 ) );
		    field.addMouseListener(this);
			
		    add(label1);
		    add(field);
			
			/*
			field2 = new JTextField();
		    field2.setPreferredSize( new Dimension( 100, 24 ) );
			add(field2);
			*/
			
			button1 = new JButton("Read this file");
			add(button1);
			
			//button2= new JButton("Not Error Checking");
			//add(button2);
			
			
			button1.addActionListener(this);
			//mouseClicked(this)
			addMouseListener(new MyMouseListener(this));

			//button2.addActionListener(this);

			
			setVisible(true);
			

		}




		public void actionPerformed(ActionEvent arg0) {

			if(arg0.getSource()==button1)
			{
				str1=field.getText();
				//str2=field2.getText();
				
				if(str2.length()==1)
				{
					
					try
					{
						if(str1.charAt(0)==str2.charAt(0))
						{
							JOptionPane.showMessageDialog(this,"They Match");	
						}
						else
						{
							JOptionPane.showMessageDialog(this, "They Dont Match");
			
						}
					}
					catch (IndexOutOfBoundsException e)
					{
						    System.err.println("IndexOutOfBoundsException: " + e.getMessage());
					}
				}
				
				else{
					JOptionPane.showMessageDialog(this, "Please only enter 1 character");
				}
			}
			
			/*if(arg0.getSource()==button2)
			{
				JOptionPane.showMessageDialog(this, "THIS AINT ERROR CHECKING DWAG");
			}*/

			
		}
		
		public void mousePressed(MouseEvent e){
		
		}


		public void mouseClicked(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			field.setText("");
		}



		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void mouseReleased(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}




		public JLabel getLabel1() {
			return label1;
		}




		public void setLabel1(JLabel label1) {
			this.label1 = label1;
		}
}
