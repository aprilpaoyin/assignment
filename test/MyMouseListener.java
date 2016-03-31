package com.ass1.test;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MyMouseListener implements MouseListener {
	
	JFrame window1; 
	
	public MyMouseListener(JFrame window)
	{
		window1 = window;	
	}


	public void mouseClicked(MouseEvent e) 
	{
		//JOptionPane.showMessageDialog(window1, "Mouse Works");
	}

	public void mouseEntered(MouseEvent e) 
	{

	}

	public void mouseExited(MouseEvent e) 
	{
	
	}

	public void mousePressed(MouseEvent e) 
	{
		
	}
	
	public void mouseReleased(MouseEvent e) 
	{
		
	}

}
