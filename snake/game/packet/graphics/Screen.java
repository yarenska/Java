package game.packet.graphics;

import game.packet.Game;
import game.packet.entities.Apple;
import game.packet.entities.BodyPart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Screen extends JPanel implements Runnable{

	public static final int WIDTH = 690, HEIGHT = 690;
	private Thread thread;
	private boolean running = false;
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	private int xCoor = 7, yCoor = 7;
	private int size = 5;
	private boolean right = true, left = false, up = false, down = false;
	private int ticks = 0;
	private Key key;
	private Random r;
	private Apple apple;
	private ArrayList<Apple> apples;
	private int speed;
	private JPanel GameOver = new JPanel();
	private JLabel score = new JLabel("0");
	
	public Screen(){
		setFocusable(true);
		key = new Key();
		addKeyListener(key);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		r = new Random();
		speed = 20000;
		snake = new ArrayList<BodyPart>();
		apples = new ArrayList<Apple>();
		GameOver.add(score);
		this.add(GameOver);
		start();
	}
	
	public void tick(){
		if(snake.size() == 0){
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
		}
		
		if(apples.size() == 0){
			int xCoor = r.nextInt(69);
			int yCoor = r.nextInt(69);
			
			apple = new Apple(xCoor, yCoor, 10);
			apples.add(apple);
		}
		
		for(int i = 0; i < apples.size(); i++)
		{
		  if (xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) // elmanýn sol üst köþe koordinatlarý ile bizim sürekli
			  																		// güncellediðimiz koordinat örtüþüyor ise
		  {
			  size++;
			  apples.remove(i);
			  i--;
			  speed++;
		  }
		}
		
		for(int i = 0; i < snake.size()-1; i++) // also stop when he tries to eat himself
			if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor())
				{
					this.add(GameOver);
				    stop();
				    
				}
				
		
		
		
		if(xCoor < 0 || xCoor > 67 || yCoor < 0 || yCoor > 67)
		{
			stop();
		}
		
		ticks++;
		
		if(ticks > (300000-speed)) // az yaparsan hýzlý çok yaparsan yavaþ
		{
			// we are updating xCoor and yCoor according
				// to movement of the key we pressed. 
			if(right) 
				xCoor++;
			if(left)
				xCoor--;
		    if(up) 
		    	yCoor--;
		    if(down) 
		    	yCoor++;
		    
		    ticks = 0;
		    b = new BodyPart(xCoor, yCoor, 10);
		    snake.add(b);
		   }
		
		if(snake.size() > size)
		{
			snake.remove(0);
		}
		
	}
	
	public void paint(Graphics g){
		
		g.clearRect(xCoor, yCoor, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		for(int i = 0; i < WIDTH/10; i++){
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}
		
		for(int i = 0; i < HEIGHT/10; i++){
			g.drawLine(0, i * 10, WIDTH, i * 10);
		}
		
		for(int i = 0; i < snake.size(); i++)
		{
			snake.get(i).draw(g);
		}
		
		for(int i = 0; i < apples.size(); i++)
		{
		  apples.get(i).draw(g);
		}
		
	}
	
	public void start(){
		running = true;
		thread = new Thread(this, "Game Loop");
		thread.start(); // calls the class' run() method
	}
	
	public void stop(){
		running = false;
		try{
			thread.join();
		} 
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void run(){ //When an object implementing interface Runnable is used to create a thread, 
						//starting the thread causes the object's run method to be called in that separately executing thread.
		while(running){
			tick();
			repaint();
		}
	}
	
	private class Key implements KeyListener{

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_D && !left)
			{
				up = false;
				down = false;
				right = true;
			}
			
			if(key == KeyEvent.VK_A && !right)
			{
				up = false;
				down = false;
				left = true;
			}
            
			if(key == KeyEvent.VK_W && !down)
			{
				left = false;
				right = false;
				up = true;
			}
			
			if(key == KeyEvent.VK_S && !up)
			{
				left = false;
				right = false;
				down = true;
			}
			
			if(key == KeyEvent.VK_R && running == false){
				new Game();
			}
		}

		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
}
