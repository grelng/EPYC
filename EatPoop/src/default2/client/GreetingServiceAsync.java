package default2.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(ArrayList<String> name, AsyncCallback<ArrayList<String>> callback)
			throws IllegalArgumentException;
	
	void getPhrase(ArrayList<String> name, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;
	
	void getPictures(ArrayList<String> name, AsyncCallback<ArrayList<String>> callback)
	throws IllegalArgumentException;
	
	void setPictures(ArrayList<String> name, AsyncCallback<ArrayList<String>> callback)
		throws IllegalArgumentException;
	
	void GetAllSteps(ArrayList<String> name, AsyncCallback<ArrayList<ArrayList<String>>> callback)
		throws IllegalArgumentException;
}
