package game.packet;
import game.packet.graphics.Screen;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	public Frame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when you press the exit button this gon shut
		                                                // down the entire program.
		setTitle("Snake");
		setResizable(false);
		init();
	}
	
	public void init(){ 
		
		Screen s = new Screen();
		add(s);
		pack();  // make the window in that size
		
		setLocationRelativeTo(null);
		setVisible(true);  // now frame will be visible, by default not visible.
	}

}
