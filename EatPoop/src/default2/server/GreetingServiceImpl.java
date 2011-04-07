package default2.server;

import java.util.ArrayList;
import java.util.Random;

import default2.client.GreetingService;
import default2.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheManager;
import java.util.Collections;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	Random rand_gen = new Random(5);

	public ArrayList<String> greetServer(ArrayList<String> name) throws IllegalArgumentException {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Value:Ming");
		
		ArrayList<ArrayList<String>> MingGame;
		if(isPresentCache("MingGame")){
			MingGame = (ArrayList<ArrayList<String>>) getCached("MingGame");
		}
		else{
			MingGame = new ArrayList<ArrayList<String>>();
		}
		
		
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 2; i < name.size(); i++){
			temp.add(name.get(i));
		}
		
		if(MingGame.size() % 2 == 0){
			MingGame.add(temp);
		
			putCached("MingGame", MingGame);
		}
		
		return ret;
	}
	
	
	@Override
	public ArrayList<String> getPhrase(ArrayList<String> name)
			throws IllegalArgumentException {
		ArrayList<ArrayList<String>> MingGame = (ArrayList<ArrayList<String>>) getCached("MingGame");
		
		return MingGame.get(MingGame.size()-1);
	}
	
	
	@Override
	public ArrayList<String> getPictures(ArrayList<String> name)
			throws IllegalArgumentException {
		
		ArrayList<String> image_urls = new ArrayList<String>();
		
		int random_num = Math.abs(rand_gen.nextInt()) % 3;
		System.out.println(random_num);
		if(random_num == 0){
			image_urls.add("http://www.nasa.gov/images/content/54350main_MM_image_feature_101_jw4.jpg");
			image_urls.add("http://www.flash-slideshow-maker.com/images/help_clip_image020.jpg");
			image_urls.add("http://www.nasa.gov/images/content/54350main_MM_image_feature_101_jw4.jpg");
			image_urls.add("http://www.flash-slideshow-maker.com/images/help_clip_image020.jpg");
		}
		else if(random_num == 1){
			image_urls.add("http://www.nasa.gov/images/content/54350main_MM_image_feature_101_jw4.jpg");
			image_urls.add("http://www.naxos-images.com/image1.jpg");
			image_urls.add("http://www.nasa.gov/images/content/54350main_MM_image_feature_101_jw4.jpg");
			image_urls.add("http://www.flash-slideshow-maker.com/images/help_clip_image020.jpg");
		}
		else if(random_num == 2){
			image_urls.add("http://www.astonmartinmedia.com/content/allsites/images/AM_RAPIDE_8_HR_27e41122-07b2-4cb8-8c22-e2b78470c084.jpg");
			image_urls.add("http://www.flash-slideshow-maker.com/images/help_clip_image020.jpg");
			image_urls.add("http://www.nasa.gov/images/content/54350main_MM_image_feature_101_jw4.jpg");
			image_urls.add("http://www.flash-slideshow-maker.com/images/help_clip_image020.jpg");
		}
		
		
		
		
		return image_urls;
	}
	
	@Override
	public ArrayList<String> setPictures(ArrayList<String> name)
			throws IllegalArgumentException {
		
		ArrayList<String> image_urls = new ArrayList<String>();
		ArrayList<ArrayList<String>> MingGame = (ArrayList<ArrayList<String>>)getCached("MingGame");
		
		
		if(MingGame.size() % 2 == 1){
			MingGame.add(name);
			putCached("MingGame", MingGame);
		}
		
		
		return image_urls;
	}

	
	@Override
	public ArrayList<ArrayList<String>> GetAllSteps(ArrayList<String> name){
		return (ArrayList<ArrayList<String>>)getCached("MingGame");
	}

	
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


	


	
}
