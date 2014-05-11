package ca.ajweeks.meteormasher;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import ca.ajweeks.meteormasher.input.Input;
import ca.ajweeks.meteormasher.state.StateManager;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final Dimension SIZE = new Dimension(540, 720);
	public static Font font42 = new Font("Sans", Font.BOLD, 42);
	public static Font font24 = font42.deriveFont(24f);
	public static StateManager stateManager;
	
	public static Image icon;
	public static Image meteorL;
	public static Image meteorM;
	public static Image meteorS;
	public static Image meteorNuke;
	public static Image meteorExplode;
	public static Image starryBackground;
	
	private volatile boolean running = false;
	private volatile boolean paused = false;
	private final boolean DEBUG = true;
	
	private Input input;
	
	public Game() {
		//Image initialization
		icon = new ImageIcon("res/icon.png").getImage();
		meteorL = icon.getScaledInstance((icon.getWidth(null) / 3) * 3, (icon.getHeight(null) / 3) * 3, Image.SCALE_DEFAULT); //three sixths (one half)
		meteorM = icon.getScaledInstance((icon.getWidth(null) / 3) * 2, (icon.getHeight(null) / 3) * 2, Image.SCALE_DEFAULT); //two sixths
		meteorS = icon.getScaledInstance((icon.getWidth(null) / 3) * 1, (icon.getHeight(null) / 3) * 1, Image.SCALE_DEFAULT); //one sixth
		meteorNuke = new ImageIcon("res/pwrup_nuke.png").getImage();
		meteorExplode = new ImageIcon("res/pwrup_explode.png").getImage();
		starryBackground = new ImageIcon("res/background.png").getImage();
		
		stateManager = new StateManager();
		input = new Input(this);
		
		setSize(SIZE);
		
		JFrame frame = new JFrame("Meteor Masher");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setIconImage(icon);
		frame.setVisible(true);
	}
	
	private void update() {
		if (!paused) {
			stateManager.update(input);
			input.update(); //Clear inputs
		}
	}
	
	private void render() {
		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(2);
			return;
		}
		Graphics g = buffer.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		stateManager.render(g);
		
		if (DEBUG) {
			//awful awful awful.. :$
			int x = 450;
			int y = 30;
			g.setFont(font24);
			g.setColor(Color.WHITE);
			g.drawString("DEBUG", x - 2, y - 2);
			g.setColor(Color.WHITE);
			g.drawString("DEBUG", x - 2, y);
			g.setColor(Color.WHITE);
			g.drawString("DEBUG", x - 2, y + 2);
			g.setColor(Color.WHITE);
			g.drawString("DEBUG", x, y - 2);
			g.setColor(Color.WHITE);
			g.drawString("DEBUG", x, y + 2);
			g.setColor(Color.WHITE);
			g.drawString("DEBUG", x + 2, y - 2);
			g.setColor(Color.WHITE);
			g.drawString("DEBUG", x + 2, y);
			g.setColor(Color.WHITE);
			g.drawString("DEBUG", x + 2, y + 2);
			g.setColor(Color.BLACK);
			g.drawString("DEBUG", x, y);
		}
		
		g.dispose();
		buffer.show();
	}
	
	private void loop() {
		while (running) {
			update();
			render();
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		running = true;
		loop();
	}
	
	public static void main(String[] args) {
		new Thread(new Game()).start();
	}
}
