/*
 * Allows user to enter address of txt file, then displays a pop-up that shows words occurred in 
 * txt file and the frequency of it. 
 * 
 * Author: 	April Tan Pao Yin D14124009
 * Date: 	28/3/16
 * 
 */
package com.assignment.Assignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Screen extends JFrame implements ActionListener, ReadFile
{
		protected JTextField field1; //shows name of file
		protected JTextField field2; //for words user wants to ignore
		protected JTextArea field3; //to show contents of userUnhelpful
		protected JButton button1; //to browse for a file
		protected JButton button2; //to confirm, read a file
		protected JButton button3; //add words to ignore
		protected JButton button4; //remove added words
		private JLabel label1; //label for field1
		private JLabel label2;
		private JLabel label3; 
		private JLabel label4; //label for * description
		static File main_file; //declaration for file
		static ArrayList<String> userUnhelpful = new ArrayList<String>(); //to store user input
	    final JFileChooser fChooser; //let user choose a file
	    private String userContent;
		
		//constructor
		public Screen(String title)
		{
			//put a title for screen
			super(title);
			setLayout(new FlowLayout());
			
			label1 = new JLabel("Choose a text file");
			field1 = new JTextField("File name");
		    field1.setPreferredSize(new Dimension(300, 30));
		    //set font style of text fields
			field1.setFont(new Font("Arial", Font.ITALIC, 12));
			field1.setEditable(false);
			
		    label2 = new JLabel("Add/remove words to ignore one by one below*");
		    field2 = new JTextField();
		    field2.setPreferredSize(new Dimension(400, 30));
		    
			label3 = new JLabel("Added unhelpful words shown below");
			field3 = new JTextArea();
			field3.setPreferredSize(new Dimension(400, 200));
			field3.setEditable(false);
			field3.setLineWrap(true);
			
			label4 = new JLabel("*Type in a word and click the \"add\" button to add the word");
			
		    fChooser = new JFileChooser();
			
			button1 = new JButton("Browse files");
			button2 = new JButton("Read this file");
			button3 = new JButton("Add unhelpful word");
			button4 = new JButton("Remove unhelpful word");
			
			add(label1);
			add(field1); 
			add(button1);
			add(button2);
			add(label2);
		    add(field2); 
			add(button3);
			add(button4);
			add(label3);
			add(field3);
			add(label4);
			
			button1.addActionListener(this);
			button2.addActionListener(this);
			button3.addActionListener(this);
			button4.addActionListener(this);

			setUserContent("");
			
			setVisible(true);
		}//end constructor

		

		/*
		 * -Checks source of the action event (button pressed).
		 * -If button1 (browse) pressed, a dialog opens that allows users to select 1 .TXT FILE,
		 * then displays a message that tells user file is being opened (no error).
		 * -If button2 (read this file) pressed, a dialog opens that reads the file
		 * and stores the words in an array.
		 * 
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			if(arg0.getSource() == button1)
			{
				/*
				 *File Filter code referenced from: http://stackoverflow.com/questions/15771949/how-do-i-make-jfilechooser-only-accept-txt
				 * 
				 * This code sets a filter so that users can only select .txt files to read.
				 */
				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				fChooser.setFileFilter(txtFilter);
				
				//clear unhelpful words used for previous file
				userUnhelpful.clear();
				setUserContent("");
				field3.setText(getUserContent());
				field2.setText("");
				
				//File Chooser code referenced from : https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
				int returnVal = fChooser.showOpenDialog(this);
			
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					//takes selected file and assigns it to file
					main_file = fChooser.getSelectedFile();
					JOptionPane.showMessageDialog(this, "Opening: " + main_file.getName(), "Opening File", JOptionPane.INFORMATION_MESSAGE);
					
					//put file name in test field
					field1.setText(main_file.getName());
					field1.setFont(new Font("Arial", Font.PLAIN, 12));
				}//end inner if
				else
				{
					JOptionPane.showMessageDialog(this, "CANCELLED", "Process Cancelled", JOptionPane.INFORMATION_MESSAGE);
				}//end else
			}//end outer if
			else if (arg0.getSource() == button2)
			{
				try
				{
					//calls Reader class to read a file and store words in array
					Reader.read(main_file, userUnhelpful);
				}//end try
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(this, "Error: " + e);
				}//end catch
			}//end else...if
			else if(arg0.getSource() == button3)
			{
				userUnhelpful.add(field2.getText());
				
				for(String item : userUnhelpful)
				{
					setUserContent(item + "\t" + getUserContent());
				}//end for
				
				field3.setText(getUserContent());
				field2.setText("");
			}//end else...if
			else if(arg0.getSource() == button4)
			{
				userUnhelpful.remove(field2.getText());
				
				for(String item : userUnhelpful)
				{
					setUserContent(item + "\t" + getUserContent());
				}//end for
				
				field3.setText(getUserContent());
				field2.setText("");
			}//end else...if
		}//end actionlistener
		
		
		
		/*
		 * SETTERS & GETTERS
		 */
		public JLabel getLabel1() 
		{
			return label1;
		}

		public void setLabel1(JLabel label) 
		{
			this.label1 = label;
		}

		public JLabel getLabel2() 
		{
			return label2;
		}

		public void setLabel2(JLabel label2) 
		{
			this.label2 = label2;
		}
		
		public String getUserContent()
		{
			return userContent;
		}

		public void setUserContent(String userContent) 
		{
			this.userContent = userContent;
		}
}
