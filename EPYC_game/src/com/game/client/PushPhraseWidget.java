package com.game.client;

import java.util.ArrayList;

import com.game.shared.FieldVerifier;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PushPhraseWidget extends Composite {
	
	private final GreetingServiceAsync greetingService;
	
	public PushPhraseWidget(final GreetingServiceAsync greetingService){
		this.greetingService = greetingService;
		
		VerticalPanel vpanel = new VerticalPanel();
		HorizontalPanel hpanel1 = new HorizontalPanel();
		HorizontalPanel hpanel2 = new HorizontalPanel();
		vpanel.add(hpanel1);
		vpanel.add(hpanel2);
		initWidget(vpanel);
		final HTML UserID_Label = new HTML("UserName: ");
		final TextBox User_ID_Box = new TextBox();
		hpanel1.add(UserID_Label);
		hpanel1.add(User_ID_Box);
		
		final HTML Phrase_labe = new HTML("Phrase: ");
		hpanel2.add(Phrase_labe);
		final TextBox Phrase_Box = new TextBox();
		hpanel2.add(Phrase_Box);
		final Button PushPhraseButton = new Button();
		PushPhraseButton.setText("Send Phrase Init");
		hpanel2.add(PushPhraseButton);
		
		
		
		//Callback for clicking Submit
		class SubmitPhraseHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}


			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				ArrayList<String> input = new ArrayList<String>();
				input.add(User_ID_Box.getText());
				input.add("4");
				input.add(Phrase_Box.getText());
				greetingService.PushPhraseInit(input, new AsyncCallback<ArrayList<String>>() {
					public void onFailure(Throwable caught) {
						
					}
					@Override
					public void onSuccess(ArrayList<String> result) {
					}
				});
			}
		}
		
		SubmitPhraseHandler submit_handler = new SubmitPhraseHandler();
		PushPhraseButton.addClickHandler(submit_handler);
	}
}
