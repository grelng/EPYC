package com.game.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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


	private String[] tokenizeString(String sentence) throws IllegalArgumentException {
		sentence = sentence.toLowerCase();
		String[] words = sentence.split("\\s+");
		return words;
	}

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

	
	Hashtable<String,String> Sentences = new Hashtable<String,String>();
	int NumUsers = 0;
	//ArrayList<String> Sentences = new ArrayList<String>();
	boolean Step1Done = false;
	
	/**
	 * Initialize the game.
	 * 
	 * @param input ArrayList of strings - always in this order:
	 * User ID
	 * Number of users
	 * Sentence
	 * @return none
	 */
	@Override
	public ArrayList<String> PushPhraseInit(ArrayList<String> input)
			throws IllegalArgumentException {

		if(!Sentences.contains(input.get(2))) {
			Sentences.put(input.get(0),input.get(2));
		}
		if(NumUsers == 0) {
			NumUsers = Integer.parseInt(input.get(1));
		}

		// Check if UserIDs contains as many users as NumUsers
		if(NumUsers > 0 && Sentences.size() == NumUsers) {
			Step1Done = true;
		}
		return null;
	}

	/**
	 * check if step 1 is done
	 * We know it's done because every client is added to UserIDs.
	 * 
	 * @param none
	 * @return boolean value Step1Done
	 */
	@Override
	public ArrayList<String> IstStep1Done(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO return Step1Done, change all instances of this method to boolean
		return null;
	}

	@Override
	public ArrayList<String> GetPhraseGame(ArrayList<String> input)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Ming Wang");
		return ret;
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
		ArrayList<String> results = new ArrayList();
	    String query_url;
		try {
			query_url = "http://query.yahooapis.com/v1/public/yql?q=" + 
				URLEncoder.encode("SELECT * FROM flickr.photos.search WHERE text=\""+input.get(0)+"\"", "UTF-8")+
				"&format=xml";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return null;
		}
	    
	    URL u;
	    HttpURLConnection conn;
		try {
			u = new URL(query_url);
			conn = (HttpURLConnection) u.openConnection();
		    conn.setRequestMethod("GET");
	        conn.setDoOutput(true);
	        conn.setReadTimeout(10000);
	        conn.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}


        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(conn.getInputStream());
			doc.getDocumentElement().normalize();
	        NodeList nl=doc.getElementsByTagName("results");
	        Node current_result = nl.item(0).getFirstChild();
	 
	        while (current_result != null){
        		String farm=current_result.getAttributes().getNamedItem("farm").getNodeValue();
        		String server= current_result.getAttributes().getNamedItem("server").getNodeValue();
        		String id = current_result.getAttributes().getNamedItem("id").getNodeValue();
        		String secret = current_result.getAttributes().getNamedItem("secret").getNodeValue();
        	
        	
        		results.add(String.format("http://farm%s.static.flickr.com/%s/%s_%s_t.jpg", farm, server,id, secret));
        	
        		current_result=current_result.getNextSibling();
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
        
        return results;
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
