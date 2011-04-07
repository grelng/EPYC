package com.epyc.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	
	ArrayList<String> EnterPhraseInitial(ArrayList<String> name) throws IllegalArgumentException;
	
	ArrayList<String> IsStep1Done(ArrayList<String> name) throws IllegalArgumentException;

	ArrayList<String> GetPhraseGame(ArrayList<String> name) throws IllegalArgumentException;

	ArrayList<String> QueryImage(ArrayList<String> name) throws IllegalArgumentException;

	ArrayList<String> SubmitLink(ArrayList<String> name) throws IllegalArgumentException;

	ArrayList<String> IsStep3Done(ArrayList<String> name) throws IllegalArgumentException;

	ArrayList<String> GetPictureGame(ArrayList<String> name) throws IllegalArgumentException;

	ArrayList<String> EnterPhraseGame(ArrayList<String> name) throws IllegalArgumentException;

	ArrayList<String> IsStep4Done(ArrayList<String> name) throws IllegalArgumentException;

	ArrayList<String> IsGameDone(ArrayList<String> name) throws IllegalArgumentException;

	ArrayList<String> ViewAllGames(ArrayList<String> name) throws IllegalArgumentException;

}
