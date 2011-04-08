package com.game.server;

import java.util.ArrayList;
import java.util.Collections;

import com.game.client.GreetingService;
import com.game.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheManager;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}
	
	//Utility Functions for using the memcache
	public boolean isPresentCache(String key){
		try{
			Cache cache;
			cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());
			return cache.containsKey(key);
		}
		catch(CacheException e){
			return false; 
		}
	}
	public Object getCached(String key){
		if(isPresentCache(key)){
			try{
				Cache cache;
				cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());
				return cache.get(key);
			}
			catch(CacheException e){
				return null;
			}
		}
		else{
			return null;
		}
	}
	public void putCached(String key, Object value){
		try{
			Cache cache;
			cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());
			cache.put(key, value);
		}
		catch(CacheException e){

		}
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public ArrayList<String> PushPhraseInit(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> IstStep1Done(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> GetPhraseGame(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> QueryImage(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> SubmitLinks(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> IsStep3Done(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> GetPictures(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> SubmitPhraseGame(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> IsStep4Done(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> IsGameDone(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> ViewAllGames(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
