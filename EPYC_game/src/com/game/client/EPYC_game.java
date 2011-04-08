package com.game.client;

import java.util.ArrayList;
import java.util.Random;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class EPYC_game implements EntryPoint {
	public static final String SERVER_ERROR = "An error occurred uh ohz";
	public int step;
	final public int pushPhrase = 1;
	final public int pushPhraseWait = 2;
	final public int setPicture = 3;
	final public int setPictureWait = 4;
	final public int setPhrase = 5;
	final public int setPhraseWait = 6;
	final public int gameEndWait = 7;
	final private Widget placeholder = new PlaceholderWidget();
	final private Label debug = new Label();
	public String username = "";
	
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	public void onModuleLoad() {
		step = 0;
		drawMe();
	}

	private void drawMe() {
		RootPanel content = RootPanel.get("content");
		Widget curr = nextWidget();
		content.add(curr);
		content.add(debug); // a debug pane
	}

	public void advance() {
		RootPanel content = RootPanel.get("content");
		// insert a call here to fade out the current widget
		content.clear();
		content.add(nextWidget());
	}
	
	public void finish(){
		RootPanel content = RootPanel.get("content");
		// insert a call here to fade out the current widget
		content.clear();
		content.add(new ViewAllGamesWidget(this.greetingService));
	}
	
	private Widget nextWidget() {
		step++;
		switch (step) {
		case pushPhrase:
			return new AlanPushPhraseWidget(this, greetingService);
		case pushPhraseWait:
			return new WaitingWidget(this, 0);
		case setPicture:
			return new AlanSetPictureWidget(this, greetingService);
		case setPictureWait:
			return new WaitingWidget(this, 1);
		case setPhrase:
			return new AlanSetPhraseWidget(this, greetingService);
		case setPhraseWait:
			return new WaitingWidget(this, 2);
		case gameEndWait:
			return new GameFinishedWidget(this);
		default:
			step = setPicture;
			return new AlanSetPictureWidget(this, greetingService);
		}
	}

	public class PlaceholderWidget extends Composite {
		public PlaceholderWidget() {
			Label placeholder = new Label("This is a placeholder");
			initWidget(placeholder);
		}
	}
	
	public class GameFinishedWidget extends Composite {
		final private EPYC_game game;
		final private static int REFRESH_INTERVAL = 100; // every second
		private int game_state = 0;	//0 means repoll, 1 not done, 2 means done
		final private Timer pollTimer;
		private Label message;
		
		private Timer newTimer() {
			Timer t = new Timer() {
				@Override
				public void run() {
					if(isGameFinished() == 2) {
						//game.advance();
						game.finish();
						this.cancel(); // stop the timer
					}
					if(isGameFinished() == 1) {
						game.advance();
						this.cancel(); // stop the timer
					}
				}
			};
			t.scheduleRepeating(REFRESH_INTERVAL);
			return t;
		}
		
		public void drawMe() {
			message = new Label("Waiting for other players");
			initWidget(message);
		}
		
		public GameFinishedWidget(final EPYC_game game){
			this.game = game;
			drawMe();
			pollTimer = newTimer();
		}
		
		int isGameFinished(){
			ArrayList<String> input = new ArrayList<String>();
			game.greetingService.IsGameDone(input, new AsyncCallback<ArrayList<String>>() {
				public void onFailure(Throwable caught) {}

				public void onSuccess(ArrayList<String> result) {
					if(result.get(0).equals("false")){
						game_state = 1;
					}
					if(result.get(0).equals("true")){
						game_state = 2;
					}
				}
			});
			
			return game_state;
		}
	}
	
	/**
	 * Displays waiting text while continuously polling server for the OK 
	 * to advance to next step.
	 * @author aleung
	 *
	 */
	public class WaitingWidget extends Composite {
		final private static int REFRESH_INTERVAL = 100; // every second
		final private Timer pollTimer;
		final private EPYC_game game;
		final private Random generator;
		private Label message;
		private boolean ready = false;
		private int iteration;
		
		
		public WaitingWidget(final EPYC_game game, int iteration) {
			this.game = game;
			this.generator = new Random();
			drawMe();
			pollTimer = newTimer();
			this.iteration = iteration;
		}
		
		public void drawMe() {
			message = new Label("Waiting for other players");
			initWidget(message);
		}
		
		private Timer newTimer() {
			Timer t = new Timer() {
				@Override
				public void run() {
					if(canAdvance()) {
						game.advance();
						this.cancel(); // stop the timer
					}
				}
			};
			t.scheduleRepeating(REFRESH_INTERVAL);
			return t;
		}
		
		public boolean canAdvance() {
			/* call greetingService to check for the OK to advance */
			message.setText(message.getText() + ".");
			
			if(iteration == 0){
				ArrayList<String> input = new ArrayList<String>();
				game.greetingService.IstStep1Done(input, new AsyncCallback<ArrayList<String>>() {
					public void onFailure(Throwable caught) {}
	
					public void onSuccess(ArrayList<String> result) {
						if(result.get(0).equals("true")){
							ready = true;
						}
					}
				});
			}
			if(iteration == 1){
				ArrayList<String> input = new ArrayList<String>();
				game.greetingService.IsStep3Done(input, new AsyncCallback<ArrayList<String>>() {
					public void onFailure(Throwable caught) {}
	
					public void onSuccess(ArrayList<String> result) {
						if(result.get(0).equals("true")){
							ready = true;
						}
					}
				});
			}
			if(iteration == 2){
				ArrayList<String> input = new ArrayList<String>();
				game.greetingService.IsStep4Done(input, new AsyncCallback<ArrayList<String>>() {
					public void onFailure(Throwable caught) {}
	
					public void onSuccess(ArrayList<String> result) {
						if(result.get(0).equals("true")){
							ready = true;
						}
					}
				});
			}
			
			if(ready){
				ready = false;
				return true;
			}
			return false;
		}
	}
}
