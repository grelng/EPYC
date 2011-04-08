package com.game.client;

import java.util.ArrayList;

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
	
	public SetPictureWidget(final GreetingServiceAsync greetingService){
		this.greetingService = greetingService;
		
		final VerticalPanel vpanel = new VerticalPanel();
		final HorizontalPanel hpanel1 = new HorizontalPanel();
		final HorizontalPanel hpanel2 = new HorizontalPanel();
		
		vpanel.add(hpanel1);
		vpanel.add(hpanel2);
		
		initWidget(vpanel);
		
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
				for(int i = 0; i < split_string.length; i++){
					final HTML phrase_word = new HTML(split_string[i]);
					hpanel1.add(phrase_word);
				}
			}
		});
	}

}
