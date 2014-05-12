package ca.ajweeks.meteormasher.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.meteormasher.Game;
import ca.ajweeks.meteormasher.input.Input;
import ca.ajweeks.meteormasher.utils.Button;
import ca.ajweeks.meteormasher.utils.Colour;

public class MainMenuState extends BasicState {
	
	private Button playGame = new Button((540 / 2) - (110 / 2), 300, 110, 45, "Play");
	private Button credits = new Button((540 / 2) - (160 / 2), 365, 160, 45, "Credits");
	private Button quit = new Button((540 / 2) - (110 / 2), 430, 110, 45, "Quit");
	private Button creditsOk = new Button((540 / 2) - (80 / 2), 580, 80, 45, "Ok");
	private Colour colour = new Colour();
	
	private boolean showCredits = false;
	
	@Override
	public void update(Input input) {
		if (showCredits) {
			creditsOk.hover = creditsOk.hovering(input.x, input.y);
			if (creditsOk.hover && (input.leftMouseDown || input.rightMouseDown)) {
				showCredits = false;
			}
			return;
		}
		
		playGame.hover = playGame.hovering(input.x, input.y);
		if (playGame.hover && (input.leftMouseDown || input.rightMouseDown)) {
			showCredits = false;
			Game.stateManager.changeState(StateManager.LEVEL_SELECT_STATE);
		}
		
		quit.hover = quit.hovering(input.x, input.y);
		if (quit.hover && (input.leftMouseDown || input.rightMouseDown)) {
			System.exit(0);
		}
		
		credits.hover = credits.hovering(input.x, input.y);
		if (credits.hover && (input.leftMouseDown || input.rightMouseDown)) {
			showCredits = true;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Game.starryBackground, 0, 0, null);
		
		if (showCredits) {
			String[] message = new String[] { "All code / assets developed by:", "AJ Weeks", "", "Original idea conceptualized by:",
					"Jared Fillo and Scott Hagel", "", "Tools used:", "-Eclipse Juno", "-Photoshop CS5.1" };
			
			g.setColor(new Color(25, 25, 25));
			g.fillRect(50, 50, 540 - 100, 720 - 100);
			
			g.setFont(Game.font24);
			g.setColor(Color.WHITE);
			for (int i = 0; i < message.length; i++) {
				g.drawString(message[i], 80, 115 + (i * 40));
			}
			
			creditsOk.render(g, new Color(90, 90, 90), new Color(70, 70, 70), Color.WHITE);
			return;
		}
		
		g.setColor(colour.title);
		playGame.render(g);
		credits.render(g);
		quit.render(g);
		
	}
	
}
