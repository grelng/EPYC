package com.game.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;

public class AlanSetPictureWidget extends Composite {
	private final GreetingServiceAsync greetingService;
	private final EPYC_game game;
	private final ArrayList<String> selectedUrls;
	private Panel pictures;
	private Panel sentence;
	private Panel choices;
	
	public AlanSetPictureWidget(EPYC_game game, GreetingServiceAsync greetingService) {
		this.game = game;
		this.greetingService = greetingService;
		this.selectedUrls = new ArrayList<String>();
		
		drawMe();
	}
	
	public void drawMe() {
		ArrayList<String> input = new ArrayList<String>();
		input.add(game.username);
		
		sentence = new FlowPanel();
		pictures = new FlowPanel();
		choices  = new FlowPanel();
		
		Button submitButton = new Button("submit");
		
		sentence.addStyleName("sentence");
		pictures.addStyleName("pictures");
		choices.addStyleName("choices");
		
		Panel box = new FlowPanel();
		
		box.add(pictures);
		box.add(sentence);
		box.add(choices);
		box.add(submitButton);
		
		submitButton.addClickHandler(new SubmitPictureHandler());
		
		initWidget(box);
		fetchSentence(input);
	}
	
	public void fetchSentence(ArrayList<String> input) {
		greetingService.GetPhraseGame(input, new AsyncCallback<ArrayList<String>>() {
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(ArrayList<String> result) {
				//Lets Display all these pictures
				//html.setText(result.get(0));
				String string = result.get(0);
				String [] splitString = string.split(" ");
				selectedUrls.clear();
				for(int i = 0; i < splitString.length; i++){
					final Anchor phrase = new Anchor(splitString[i]);
					ClickPhraseHandler handler = new ClickPhraseHandler();
					handler.setQuery(splitString[i], i);
					phrase.addClickHandler(handler);
					sentence.add(phrase);
					selectedUrls.add("");
				}
			}
		});
	}
	
	class ClickPhraseHandler implements ClickHandler {
		String queryString;
		int index;
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		
		public void setQuery(String query, int index){
			this.queryString = query;
			this.index = index;
		}
		
		public void onClick(ClickEvent event) {
			sendNameToServer();
		}


		/**
		 * Send the name from the nameField to the server and wait for a response.
		 */
		private void sendNameToServer() {
			ArrayList<String> input = new ArrayList<String>();
			input.add(queryString);
			greetingService.GetPictures(input, new AsyncCallback<ArrayList<String>>() {
				public void onFailure(Throwable caught) {
					
				}
				@Override
				public void onSuccess(ArrayList<String> result) {
					choices.clear();
					for(int i = 0; i < result.size(); i++){
						Image image = new Image(result.get(i));
						ClickPictureHandler handler = new ClickPictureHandler();
						handler.setQuery(index, result.get(i));
						image.addClickHandler(handler);
						
						choices.add(image);
					}
				}
			});
		}
	}
	
	class ClickPictureHandler implements ClickHandler {
		int index;
		String url;
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		
		public void setQuery(int index, String url){
			this.index = index;
			this.url = url;
		}
		
		public void onClick(ClickEvent event) {
			selectedUrls.set(index, url);
			pictures.clear();
			for(int i = 0; i < selectedUrls.size(); i++){
				String url = selectedUrls.get(i);
				System.out.println(url);
				if (url != null && url.length() > 0) {
					Image image = new Image(selectedUrls.get(i));
					pictures.add(image);
				}
			}
		}
	}
	
	class SubmitPictureHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			ArrayList<String> input = new ArrayList<String>();
			input.add(game.username);
			input.addAll(selectedUrls);
			greetingService.SubmitLinks(input, new AsyncCallback<ArrayList<String>>() {
				public void onFailure(Throwable caught) {}

				@Override
				public void onSuccess(ArrayList<String> result) {
					//We Are Done with this widget
					game.advance();
				}
			});		
		}
	}
}
