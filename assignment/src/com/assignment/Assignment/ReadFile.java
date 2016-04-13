/*
 * (i)Interface that contains the method to read a file.
 * (ii)Class used for filtering what files the user can select while browsing for 
 * 	   files to read.
 * 
 * Code referenced from: https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
 * 
 * Author: 	April Tan Pao Yin
 * Date: 	7/4/16
 */
package com.assignment.Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ReadFile 
{
	public static void read(File file, File unhelpful) throws FileNotFoundException
	{
	}
	
	public static void unhelpfulWords(File file, File unhelpful, String holder) throws FileNotFoundException
	{
	}
	
	public static void addedWords(ArrayList<String> user)
	{
	}
}
