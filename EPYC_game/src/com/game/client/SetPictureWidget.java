package com.game.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SetPictureWidget extends Composite {
	
	private final GreetingServiceAsync greetingService;
	private final ArrayList<String> selected_urls;
	
	public SetPictureWidget(final GreetingServiceAsync greetingService){
		this.greetingService = greetingService;
		this.selected_urls = new ArrayList<String>();
		
		
		final VerticalPanel vpanel = new VerticalPanel();
		final HorizontalPanel hpanel1 = new HorizontalPanel();
		final HorizontalPanel hpanel2 = new HorizontalPanel();
		final HorizontalPanel hpanel3 = new HorizontalPanel();
		
		vpanel.add(hpanel1);
		vpanel.add(hpanel2);
		vpanel.add(hpanel3);
		
		initWidget(vpanel);
		
		//Callback When I click on the pictures
		class ClickPictureHandler implements ClickHandler {
			int index;
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			
			public void setQuery(int index){
				this.index = index;
			}
			
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}


			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
			}
		}
		
		
		//Callback for when I click on a word to pull up the pictures

		//Callback for clicking Submit
		class ClickPhraseHandler implements ClickHandler {
			String query_string;
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			
			public void setQuery(String query){
				this.query_string = query;
			}
			
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}


			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				ArrayList<String> input = new ArrayList<String>();
				input.add(query_string);
				greetingService.GetPictures(input, new AsyncCallback<ArrayList<String>>() {
					public void onFailure(Throwable caught) {
						
					}
					@Override
					public void onSuccess(ArrayList<String> result) {
						hpanel2.clear();
						for(int i = 0; i < result.size(); i++){
							Image image = new Image(result.get(i));
							hpanel2.add(image);
						}
					}
				});
			}
		}
		
		
		ArrayList<String> input = new ArrayList<String>();
		input.add("Ming");
		input.add("1");
		greetingService.GetPhraseGame(input, new AsyncCallback<ArrayList<String>>() {
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(ArrayList<String> result) {
				//Lets Display all these pictures
				//html.setText(result.get(0));
				String string = result.get(0);
				String [] split_string = string.split(" ");
				selected_urls.clear();
				for(int i = 0; i < split_string.length; i++){
					final HTML phrase_word = new HTML(split_string[i]);
					ClickPhraseHandler handler = new ClickPhraseHandler();
					handler.setQuery(split_string[i]);
					phrase_word.addClickHandler(handler);
					
					hpanel1.add(phrase_word);
					
					selected_urls.add("");
				}
			}
		});
	}

}
