package default2.client;

import java.util.ArrayList;

import default2.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EatPoop implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	
	private final EatPooServiceAsync pooService = GWT
	.create(EatPooService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();
		
		
		//Starting my portion
		final TextBox enter_phrase = new TextBox();
		enter_phrase.setText("Random String");
		RootPanel.get("MingField").add(enter_phrase);
		System.out.println("Ming");
		enter_phrase.setFocus(true);
		enter_phrase.selectAll();
		
		
		// Create a handler for the sendButton and nameField
		class SubmitPhraseHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = enter_phrase.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}
				
				ArrayList<String> input_to_server = new ArrayList<String>();
				input_to_server.add("UserName:Ming");
				input_to_server.add("Mode:1");
				
				
				String[] splitted_string = textToServer.split(" ");
				for(int i = 0; i < splitted_string.length; i++){
					input_to_server.add(splitted_string[i]);
				}
				
				
				greetingService.greetServer(input_to_server, new AsyncCallback<ArrayList<String> >() {
					public void onFailure(Throwable caught) {
						String p = ";";
					}

					@Override
					public void onSuccess(ArrayList<String> result) {
						// TODO Auto-generated method stub
						String p = ";";						
					}
				});
			}
		}
		
		final Button sendPhrase = new Button("SendPhrase");
		SubmitPhraseHandler temp_handler = new SubmitPhraseHandler(); 
		sendPhrase.addClickHandler(temp_handler);
		RootPanel.get("SendPhrase").add(sendPhrase);
		
		
		
		//Panel to display pictures
		final HorizontalPanel hpanel = new HorizontalPanel();
		final Button TestButton = new Button("Ming");
		final Button TestButton2 = new Button("Ming2");
		hpanel.add(TestButton);
		hpanel.add(TestButton2);
		RootPanel.get("PhraseButtons").add(hpanel);
		
		final HorizontalPanel phrasepictures = new HorizontalPanel();
		RootPanel.get("PhrasePictures").add(phrasepictures);
		
		final HorizontalPanel selectedPicturesPanel = new HorizontalPanel();
		RootPanel.get("SelectedPictures").add(selectedPicturesPanel);
		
		final VerticalPanel allmovePanel = new VerticalPanel();
		RootPanel.get("AllMoveArea").add(allmovePanel);
		
		final ArrayList<String> Phrase_Urls_Chosen = new ArrayList<String>();
		
		class PhrasePictureClear implements ClickHandler, KeyUpHandler {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onClick(ClickEvent event) {
				phrasepictures.clear();	
			}
			
		}
		
		class GetAllMoveHandler implements ClickHandler {

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
				// First, we validate the input.
				phrasepictures.clear();
				greetingService.GetAllSteps(null, new AsyncCallback<ArrayList<ArrayList<String>>>() {
					public void onFailure(Throwable caught) {
						String p = ";";
					}

					@Override
					public void onSuccess(ArrayList<ArrayList<String>> result) {
						allmovePanel.clear();
						for(int i = 0; i < result.size(); i++){
							if(i % 2 == 0){
								//It will be text
								HTML random_text = new HTML();
								String text = new String();
								//random_text.setText(text)
								for(int j = 0; j < result.get(i).size(); j++){
									text += result.get(i).get(j) + " ";
								}
								random_text.setText(text);
								allmovePanel.add(random_text);
							}
							else{
								//It will be pictures
								HorizontalPanel pictures = new HorizontalPanel();
								for(int j = 0; j < result.get(i).size(); j++){
									Image result_image = new Image(result.get(i).get(j));
									result_image.setWidth("200px");
									pictures.add(result_image);
								}
								allmovePanel.add(pictures);
							}
						}
					}
				});
				
			}
		}
		
		final Button GetAllMovesButton = new Button();
		GetAllMovesButton.setText("GetAllMoves");
		GetAllMoveHandler getallmove_handler = new GetAllMoveHandler();
		GetAllMovesButton.addClickHandler(getallmove_handler);
		RootPanel.get("GetAllMovesButton").add(GetAllMovesButton);
		
		
		// Create a handler for the sendButton and nameField
		class SubmitPictureHandler implements ClickHandler {

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
				// First, we validate the input.
				phrasepictures.clear();
				greetingService.setPictures(Phrase_Urls_Chosen, new AsyncCallback<ArrayList<String> >() {
					public void onFailure(Throwable caught) {
						String p = ";";
					}

					@Override
					public void onSuccess(ArrayList<String> result) {
						String p = ";";
					}
				});
			}
		}
		
		final Button SubmitPicturesButton = new Button();
		SubmitPicturesButton.setText("Submit Pictures");
		SubmitPictureHandler sub_pic_handler = new SubmitPictureHandler();
		SubmitPicturesButton.addClickHandler(sub_pic_handler);
		RootPanel.get("SelectedPictures").add(SubmitPicturesButton);
		
		// Create a handler for the sendButton and nameField
		class PictureClick implements ClickHandler {
			String url;
			int index;
			
			public void setPhrase(String url, int index){
				this.url = url;
				this.index = index;
			}
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
				// First, we validate the input.
				phrasepictures.clear();
				Phrase_Urls_Chosen.set(index, url);
				
				//Setting it up so it displays what we've chose
				selectedPicturesPanel.clear();
				for(int i = 0; i < Phrase_Urls_Chosen.size(); i++){
					Image gwt_image = new Image(Phrase_Urls_Chosen.get(i));
					gwt_image.setWidth("200px");
					selectedPicturesPanel.add(gwt_image);
				}
			}
		}
		
		
		// Create a handler for the sendButton and nameField
		class PhraseClick implements ClickHandler, KeyUpHandler {
			String phrase;
			int index;
			
			public void setPhrase(String in_phrase, int index){
				this.phrase = in_phrase;
				this.index = index;
			}
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				
				ArrayList<String> input_to_server = new ArrayList<String>();				
				
				greetingService.getPictures(input_to_server, new AsyncCallback<ArrayList<String> >() {
					public void onFailure(Throwable caught) {
						String p = ";";
					}

					@Override
					public void onSuccess(ArrayList<String> result) {
						// TODO Auto-generated method stub
						phrasepictures.clear();
						for(int i = 0; i < result.size(); i++){
							Image gwt_image = new Image(result.get(i));
							gwt_image.setWidth("200px");
							PictureClick pic_click_handler = new PictureClick();
							pic_click_handler.setPhrase(result.get(i), index);
							gwt_image.addClickHandler(pic_click_handler);
							
							phrasepictures.add(gwt_image);
						}
						
						String p = ";";
					}
				});
			}
		}
		
		// Create a handler for the sendButton and nameField
		class RequestPhrase implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = enter_phrase.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}
				
				ArrayList<String> input_to_server = new ArrayList<String>();
				input_to_server.add("UserName:Ming");
				input_to_server.add("Mode:1");
				input_to_server.add("Phrase:PoopFace");
				
				
				greetingService.getPhrase(input_to_server, new AsyncCallback<ArrayList<String> >() {
					public void onFailure(Throwable caught) {
						String p = ";";
					}

					@Override
					public void onSuccess(ArrayList<String> result) {
						// TODO Auto-generated method stub
						hpanel.clear();
						for(int i = 0; i < result.size(); i++){
							final Button word_button = new Button();
							word_button.setText(result.get(i));
							
							PhraseClick submit_handler = new PhraseClick();
							submit_handler.setPhrase(result.get(i), i);
							word_button.addClickHandler(submit_handler);
							
							hpanel.add(word_button);
						}
						final Button clear_button = new Button();
						PhrasePictureClear clear_handler = new PhrasePictureClear();
						clear_button.addClickHandler(clear_handler);
						clear_button.setText("Clear");
						hpanel.add(clear_button);
						
						Phrase_Urls_Chosen.clear();
						for(int i = 0; i < result.size(); i++){Phrase_Urls_Chosen.add("");}
						
						String p = ";";						
					}
				});
			}
		}
		
		//Requesting Phrase 
		
		final Button GetPhrase = new Button("getPhrase");
		RequestPhrase temp_handler2 = new RequestPhrase();
		GetPhrase.addClickHandler(temp_handler2);
		RootPanel.get("GetPhrase").add(GetPhrase);
		
		
		
		
		
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});
		
		
	}
}
