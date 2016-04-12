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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Screen extends JFrame implements ActionListener, ReadFile
{
		protected JTextField field; //shows address of file
		protected JButton button1; //to browse for a file
		protected JButton button2; //to confirm, read a file
		private JLabel label; //label for the txt field
		static File main_file; //declaration for file
		static File unhelpful = new File("src/com/assignment/Assignment/unhelpful.txt"); //file for list of unhelpful words
		static ArrayList<String> words = new ArrayList<String>(); //array to store words
	    static ArrayList<Integer> counts = new ArrayList<Integer>(); //array to store words count
		static ArrayList<String> combined = new ArrayList<String>(); //array to store combined words and count to display
	    
		/*
		 * Code for FileChooser referenced from: https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
		 */
		final JFileChooser fChooser; //let user choose a file
		
		//constructor
		public Screen(String title)
		{
			//put a title for screen
			super(title);
			setLayout(new FlowLayout());
			
			label = new JLabel("Choose a text file");
			field = new JTextField("File name");
		    field.setPreferredSize( new Dimension( 200, 30 ));
			fChooser = new JFileChooser();
			
			//set font style of text field
			field.setFont(new Font("Arial", Font.ITALIC, 12));
			
			button1 = new JButton("Browse");
			button2 = new JButton("Read this file");
			
			add(label);
		    add(field);
		    add(button1);
			add(button2);
			
			button1.addActionListener(this);
			button2.addActionListener(this);

			setVisible(true);
		}//end 1st constructor

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
				
				//File Chooser code referenced from : https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
				int returnVal = fChooser.showOpenDialog(this);
			
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					//takes selected file and assigns it to file
					main_file = fChooser.getSelectedFile();
					JOptionPane.showMessageDialog(this, "Opening: " + main_file.getName(), "Opening File", JOptionPane.INFORMATION_MESSAGE);
					
					//put file name in test field
					field.setText(main_file.getName());
					field.setFont(new Font("Arial", Font.PLAIN, 12));
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
					//calls read method to read a file and store words in array
					read(main_file, unhelpful);
				}//end try
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(this, "Error: " + e);
				}//end catch
			}//end else...if
		}//end actionlistener
		
		
		
		/*
		 * Reads file word by word
		 */
		public static void read(File file, File unhelpful) throws FileNotFoundException
		{
			Scanner scan1 = new Scanner(file); 
			//Scanner scan2 = new Scanner(unhelpful);
			
		    String holder; //holder for word
		    //String singleWord; //used for comparison
		    int searchIx; //search index
		    boolean newWord; //whether or not current word is a new word
			
			//put first word into array then replace anything in string that aren't letters or numbers
			holder = scan1.next().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
			unhelpfulWords(file, unhelpful, holder);
			//words.add(holder);
			//counts.add(1);
			
			while(scan1.hasNext())
			{	   
				holder = scan1.next().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
				//singleWord = holder.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
				
				searchIx = 0;
				newWord = true;
				
				//cycle through words array
				for(String string : words)
				{
					//if current word is found in the words array
					if(string.equals(holder/*singleWord*/))
					{
						/*increment count, set element in that index to the incremented number, 
						 *then say that it is not a new word 
						 */
						int num = counts.get(searchIx);
						num++;
						counts.set(searchIx, num);
						newWord = false;
					}//end if
					
					searchIx++;
				}//end for
				
				//if it is a new word (not in words array)
				if(newWord)
				{
					unhelpfulWords(file, unhelpful, holder);
					
					/*boolean found = false;
					String temp = scan2.next().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
					
					while(scan2.hasNext() && found == false)
					{
						if(!(singleWord.equals(temp)))
						{
							words.add(singleWord);
							counts.add(1);
							found = true;
						}//end inner if
						temp = scan2.next().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
					}//end while*/
				}//end outer if
				
			}//end while
			  
			/*combine the contents of words and counts
			 * 
			 * code referenced from: http://stackoverflow.com/questions/16984221/display-arraylist-contents-in-a-joptionpane-showmessagedialog
			 */
	        StringBuilder combined = new StringBuilder("<html>");
	        int elsPerLine = 0;
	        
			for(int i = 0; i < words.size(); i++)
			{
				combined.append(words.get(i) + ": " + counts.get(i) + "\t\t");
				elsPerLine++;
				
				if(elsPerLine > 2) 
				{
					combined.append("<br>");
					elsPerLine = 0;
				}//end if
			}//end for
			combined.append("</html>");
			
			JOptionPane.showMessageDialog(null, combined.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);
			
			scan1.close();
			//scan2.close();
		}//end read
		
		
		
		public static void unhelpfulWords(File file, File unhelpful, String holder)
		{
			boolean found = false;
			String temp;
			int count=0;
			
			try 
			{

				Scanner scan2 = new Scanner(unhelpful);
			
				System.out.println(count);
				while(scan2.hasNext() && found == false)
				{
					temp = scan2.next().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
					
					if(temp.equals(holder))
					{
						found = true;
						System.out.println("please");
						count++;
					}//end if
					else
					{
						found = false;
						System.out.println("help");
					}
				}//end while
				
				if(found == false)
				{
					words.add(holder);
					counts.add(1);
				}//end if
				System.out.println(count);
				
				scan2.close();
			}//end try
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Error: " + e);
			}//end catch
		}//end unhelpfulWords
		
		
		/*
		 * SETTERS & GETTERS
		 */
		public JLabel getLabel1() 
		{
			return label;
		}

		public void setLabel1(JLabel label) 
		{
			this.label = label;
		}
}
