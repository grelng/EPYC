package com.game.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ViewAllGamesWidget extends Composite{
	private final GreetingServiceAsync greetingService;
	
	public ViewAllGamesWidget(final GreetingServiceAsync greetingService){
		this.greetingService = greetingService;
		
		final HorizontalPanel hpanel = new HorizontalPanel();
		initWidget(hpanel);
		
		ArrayList<String> input = new ArrayList<String>();
		greetingService.ViewAllGames(input, new AsyncCallback<ArrayList<ArrayList<ArrayList<String>>>>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(ArrayList<ArrayList<ArrayList<String>>> result) {
				for(int game_idx = 0; game_idx < result.size(); game_idx++){
					VerticalPanel game_panel = new VerticalPanel();
					hpanel.add(game_panel);
					for(int step_idx = 0; step_idx < result.get(game_idx).size(); step_idx++){
						if(step_idx == 0){
							String username = result.get(game_idx).get(step_idx).get(0);
							HTML username_HTML = new HTML(username);
							game_panel.add(username_HTML);
						}
						else{
							if(step_idx % 2 == 1){
								//Odd will be phrases
								String phrase = result.get(game_idx).get(step_idx).get(0);
								HTML phrase_HTML = new HTML(phrase);
								game_panel.add(phrase_HTML);
							}
							else{
								//Even Will be List of Pictures
								ArrayList<String> picture_urls = new ArrayList<String>();
								HorizontalPanel pic_panel = new HorizontalPanel();
								for(int picture_idx = 0; picture_idx < result.get(game_idx).get(step_idx).size(); picture_idx++){
									picture_urls.add(result.get(game_idx).get(step_idx).get(picture_idx));
									if(result.get(game_idx).get(step_idx).get(picture_idx).length() == 0)
										continue;
									Image image = new Image(result.get(game_idx).get(step_idx).get(picture_idx));
									pic_panel.add(image);
								}
								game_panel.add(pic_panel);
							}
						}
					}
				}
			}
		});
	}
}
