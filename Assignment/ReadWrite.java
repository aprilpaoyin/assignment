/*
 * This class reads the file that was entered by the user. Then implements methods from
 * an interface to process the info that was read from the file.
 * 
 * Author: 	April Tan Pao Yin D14124009
 * Date: 	4/4/16
 */
package com.assignment.Assignment;

import java.util.Scanner;
import java.io.*;

public class ReadWrite extends Screen
{
	ReadWrite(String str1)
	{
		super(str1);
	}
	
	//constructors
	public static void ReadMacRead(String role) throws FileNotFoundException
	{
		File myfile = new File("D:\\Eclipse\\eclipse files\\assignment\\src\\com\\assignment\\Assignment\\Christmas.txt");
		Scanner Scan = new Scanner(myfile); 
		      
		while(Scan.hasNextLine() == true )
		{	   
			//boolean check = true; 
	    	String line = Scan.next(); 			    	 
	    	System.out.println(line); 
	    	
		    
		}//end while
		      
		Scan.close();
	}//end 1st constructor
}//end 
