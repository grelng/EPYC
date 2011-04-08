package com.game.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;

public class AlanPushPhraseWidget extends Composite {
	private final EPYC_game game; // reference to the EPYC_game entry point
	private final GreetingServiceAsync greetingService;

	public AlanPushPhraseWidget(final EPYC_game game,
			final GreetingServiceAsync greetingService) {
		this.greetingService = greetingService;
		this.game = game;
		drawMe();
	}

	public void drawMe() {
		final HTML debugResponse = new HTML();
		final TextBox userId = new TextBox();
		final TextBox inputSentence = new TextBox();

		KeyUpHandler handler = new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendSentence();
				}
			}

			private void sendSentence() {
				ArrayList<String> input = new ArrayList<String>();
				input.add(userId.getText());
				input.add("3");
				input.add(inputSentence.getText());
				game.username = userId.getText();
				
				greetingService.PushPhraseInit(input,
						new AsyncCallback<ArrayList<String>>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user	
							}

							public void onSuccess(ArrayList<String> result) {
								game.advance();
							}
						});

			}
		};

		inputSentence.addKeyUpHandler(handler);
		inputSentence.setFocus(true);

		FlowPanel p = new FlowPanel();
		p.addStyleName("input");
		p.add(userId);
		p.add(inputSentence);
		p.add(debugResponse);
		initWidget(p);
	}

}
