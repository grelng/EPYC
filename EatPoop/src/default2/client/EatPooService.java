package default2.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("eatpoo")
public interface EatPooService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
}
