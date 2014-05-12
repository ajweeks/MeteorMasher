package ca.ajweeks.meteormasher.state;

import java.awt.Graphics;
import java.util.ArrayList;

import ca.ajweeks.meteormasher.input.Input;

public class StateManager {
	
	public final static int MAIN_MENU_STATE = 0;
	public final static int GAME_STATE = 1;
	public final static int LEVEL_SELECT_STATE = 2;
	
	public ArrayList<BasicState> states;
	public BasicState currentState;
	
	public StateManager() {
		states = new ArrayList<>();
		states.add(new MainMenuState());
		states.add(new GameState());
		states.add(new LevelSelectState());
		
		currentState = states.get(MAIN_MENU_STATE);
	}
	
	public void changeState(int index) {
		if (index > states.size()) throw new IllegalArgumentException();
		currentState = states.get(index);
	}
	
	public void update(Input input) {
		currentState.update(input);
	}
	
	public void render(Graphics g) {
		currentState.render(g);
	}
}
