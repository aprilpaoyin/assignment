/*
 * Used for processing the data in the selected file. Will first read the contents then compare it to unhelpful.txt. 
 * If a match is found, the word is not calculated. It will then compare to user input and ignore any words that 
 * match user input. 
 * 
 * Author: 	April Tan Pao Yin D14124009
 * Date: 	11/4/16
 */
package com.assignment.Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Reader implements ReadFile
{
	static File unhelpful = new File("src/com/assignment/Assignment/unhelpful.txt"); //file for list of unhelpful words
	static ArrayList<String> words = new ArrayList<String>(); //array to store words
    static ArrayList<Integer> counts = new ArrayList<Integer>(); //array to store words count
	
	public Reader(File main_file, ArrayList<String> userUnhelpful)
	{
		try 
		{
			
			read(main_file, userUnhelpful);
		} 
		catch (FileNotFoundException e) 
		{
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}//end 1st constructor
	
	
	
	/*
	 * Reads file word by word
	 */
	public static void read(File main_file, ArrayList<String> userUnhelpful) throws FileNotFoundException
	{
		//clear arrayLists in case user reads the same file twice OR reads another file. Prevents stacking.
		words.clear();
		counts.clear();
		
		Scanner scan1 = new Scanner(main_file); 
		
	    String holder; //holder for word
	    int searchIx; //search index
	    boolean newWord; //whether or not current word is a new word
		
		//put first word into array then replace anything in string that aren't letters or numbers
		holder = scan1.next().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		unhelpfulWords(unhelpful, holder, userUnhelpful);
		
		while(scan1.hasNext())
		{	   
			holder = scan1.next().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
			
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
				unhelpfulWords(unhelpful, holder, userUnhelpful);
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
	}//end read	
	
	
	
	/*
	 * This scans the text file of words to exclude and compares it to the current word.
	 * A word is only added into the arrayList if it does not match the unhelpful word.
	 */
	public static void unhelpfulWords(File unhelpful, String holder, ArrayList<String> userUnhelpful)
	{
		boolean found = false;
		String temp;
		
		try 
		{
			Scanner scan2 = new Scanner(unhelpful);
		
			//scans & cycles through unhelpful.txt
			while(scan2.hasNext() && found == false)
			{
				temp = scan2.next().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
				
				if(temp.equals(holder))
				{
					found = true;
				}//end if
				else
				{
					//addedWords(userUnhelpful, holder);
					found = false;
				}
			}//end while
			
			if(found == false)
			{
				
				words.add(holder);
				counts.add(1);
			}//end if
			
			scan2.close();
		}//end try
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}//end catch
	}//end unhelpfulWords
	
	
	
	/*
	 * This compares the current word to the words that the user input. If a match is found, the word is not added into the arrays.
	 * 
	public static void addedWords(ArrayList<String> userUnhelpful, String holder)
	{
		Boolean found = false;
		String word;
			
		//goes through the array looking for matches. Adds current word into array only when no matches are found.
		while(userUnhelpful.iterator().hasNext() && found == false)
		{
			word = userUnhelpful.iterator().next();
			
			if(word.equals(holder))
			{
				found = true;
			}//end if
			else
			{
				found = false;
			}//end else
		}//end while
		
		if(found == false)
		{
			words.add(holder);
			counts.add(1);
		}//end if
		
	}//end addWords
	*/
}
