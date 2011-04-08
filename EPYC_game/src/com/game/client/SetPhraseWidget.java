package com.game.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SetPhraseWidget extends Composite{
	private final GreetingServiceAsync greetingService;
	private final String  username;
	
	public SetPhraseWidget(final GreetingServiceAsync greetingService, final String username){
		this.greetingService = greetingService;
		this.username = username;
		
		ArrayList<String> input = new ArrayList<String>();
		input.add(username);
		
		final VerticalPanel vpanel = new VerticalPanel();
		initWidget(vpanel);
		final HorizontalPanel hpanel1 = new HorizontalPanel();
		final HorizontalPanel hpanel2 = new HorizontalPanel();
		final TextBox enter_phrase_box = new TextBox();
		final Button enter_phrase_button = new Button("Submit Phrase");
		hpanel2.add(enter_phrase_box);
		hpanel2.add(enter_phrase_button);
		vpanel.add(hpanel1);
		vpanel.add(hpanel2);
		
		enter_phrase_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ArrayList<String> input = new ArrayList<String>();
				input.add(username);
				input.add(enter_phrase_box.getText());
				greetingService.SubmitPhraseGame(input, new AsyncCallback<ArrayList<String>>() {
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void onSuccess(ArrayList<String> result) {
						// TODO Auto-generated method stub
					}
				});
			}
		});
		
		
		greetingService.GetImagesGame(input,new AsyncCallback<ArrayList<String>>() {
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(ArrayList<String> result) {
				//Lets Display all these pictures
				if(result == null)
					return;
				for(int i = 0; i < result.size(); i++){
					Image image = new Image(result.get(i));
					hpanel1.add(image);
				}
			}
		});
	}
}
