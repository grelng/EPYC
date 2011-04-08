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
		final Button submit_picture_button = new Button();
		submit_picture_button.setText("Submit Pictures");
		
		vpanel.add(hpanel1);
		vpanel.add(hpanel2);
		vpanel.add(hpanel3);
		vpanel.add(submit_picture_button);
		
		initWidget(vpanel);
		
		class SubmitPictureHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				greetingService.SubmitLinks(selected_urls, new AsyncCallback<ArrayList<String>>() {
					public void onFailure(Throwable caught) {}

					@Override
					public void onSuccess(ArrayList<String> result) {
						//We Are Done with this widget
					}
				});
					
			}
		}
		
		SubmitPictureHandler submit_picture_handler = new SubmitPictureHandler();
		submit_picture_button.addClickHandler(submit_picture_handler);
		
		//Callback When I click on the pictures
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
				String p = ";";
				selected_urls.set(index, url);
				hpanel3.clear();
				for(int i = 0; i < selected_urls.size(); i++){
					Image image = new Image(selected_urls.get(i));
					hpanel3.add(image);
				}
			}
		}
		
		
		//Callback for when I click on a word to pull up the pictures

		//Callback for clicking Submit
		class ClickPhraseHandler implements ClickHandler {
			String query_string;
			int index;
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			
			public void setQuery(String query, int index){
				this.query_string = query;
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
				input.add(query_string);
				greetingService.GetPictures(input, new AsyncCallback<ArrayList<String>>() {
					public void onFailure(Throwable caught) {
						
					}
					@Override
					public void onSuccess(ArrayList<String> result) {
						hpanel2.clear();
						for(int i = 0; i < result.size(); i++){
							Image image = new Image(result.get(i));
							ClickPictureHandler handler = new ClickPictureHandler();
							handler.setQuery(index, result.get(i));
							image.addClickHandler(handler);
							
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
					handler.setQuery(split_string[i], i);
					phrase_word.addClickHandler(handler);
					
					hpanel1.add(phrase_word);
					
					selected_urls.add("");
				}
			}
		});
	}

}
