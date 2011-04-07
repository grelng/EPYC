package com.epyc.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void EnterPhraseInitial(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;
	
	void IsStep1Done(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;
	
	void GetPhraseGame(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;
	
	void QueryImage(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;
	
	void SubmitLink(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;
	
	void IsStep3Done(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;
	
	void GetPictureGame(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;
	
	void EnterPhraseGame(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;
	
	void IsStep4Done(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;
	
	void IsGameDone(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;
	
	void ViewAllGames(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;

}
