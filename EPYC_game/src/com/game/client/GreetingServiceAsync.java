package com.game.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void PushPhraseInit(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
	throws IllegalArgumentException;
	void IstStep1Done(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
	throws IllegalArgumentException;
	void GetPhraseGame(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
	throws IllegalArgumentException;
	void QueryImage(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
	throws IllegalArgumentException;
	void SubmitLinks(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
	throws IllegalArgumentException;
	void IsStep3Done(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
	throws IllegalArgumentException;
	void GetPictures(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
	throws IllegalArgumentException;
	void IsStep4Done(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
	throws IllegalArgumentException;
	void SubmitPhraseGame(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
	throws IllegalArgumentException;
	void IsGameDone(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
	throws IllegalArgumentException;
	void ViewAllGames(ArrayList<String> input, AsyncCallback<ArrayList<String>> callback)
	throws IllegalArgumentException;
	
}
