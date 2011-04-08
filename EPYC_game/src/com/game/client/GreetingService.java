package com.game.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	
	ArrayList<String> PushPhraseInit(ArrayList<String> input) throws IllegalArgumentException;
	ArrayList<String> IstStep1Done(ArrayList<String> input) throws IllegalArgumentException;
	ArrayList<String> GetPhraseGame(ArrayList<String> input) throws IllegalArgumentException;
	ArrayList<String> QueryImage(ArrayList<String> input) throws IllegalArgumentException;
	ArrayList<String> SubmitLinks(ArrayList<String> input) throws IllegalArgumentException;
	ArrayList<String> IsStep3Done(ArrayList<String> input) throws IllegalArgumentException;
	ArrayList<String> GetPictures(ArrayList<String> input) throws IllegalArgumentException;
	ArrayList<String> SubmitPhraseGame(ArrayList<String> input) throws IllegalArgumentException;
	ArrayList<String> IsStep4Done(ArrayList<String> input) throws IllegalArgumentException;
	ArrayList<String> IsGameDone(ArrayList<String> input) throws IllegalArgumentException;
	ArrayList<String> ViewAllGames(ArrayList<String> input) throws IllegalArgumentException;

	
	
}
