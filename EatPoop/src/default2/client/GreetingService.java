package default2.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	ArrayList<String> greetServer(ArrayList<String> name) throws IllegalArgumentException;
	ArrayList<String> getPhrase(ArrayList<String> name) throws IllegalArgumentException;
	ArrayList<String> getPictures(ArrayList<String> name) throws IllegalArgumentException;
	ArrayList<String> setPictures(ArrayList<String> name) throws IllegalArgumentException;
	ArrayList<ArrayList<String>> GetAllSteps(ArrayList<String> name) throws IllegalArgumentException;
}
