package com.game.server;

import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable

public class GameEntity {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private int gameID;
    
    @Persistent
    private int NumUsers;
    
    @Persistent
    private boolean Step1Done;
    
    @Persistent 
    private boolean Step3Done;
    
    @Persistent
    private boolean Step4Done;

    @Persistent
    private Date date;
    
    @Persistent
    private ArrayList<ArrayList<ArrayList<String>>> games;

    public GameEntity(int gameID, int NumUsers, Date date, ArrayList<ArrayList<ArrayList<String>>> game) {
        this.NumUsers = NumUsers;
        this.gameID = gameID;
        this.date = date;
        this.games = game;
    }

    public Key getKey() {
        return key;
    }


    public Date getDate() {
        return date;
    }
    
    public ArrayList<ArrayList<ArrayList<String>>> getGame(){
    	return games;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setGame(ArrayList<ArrayList<ArrayList<String>>> game){
    	this.games = game;
    }

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getNumUsers() {
		return NumUsers;
	}

	public void setNumUsers(int numUsers) {
		NumUsers = numUsers;
	}

	public boolean isStep1Done() {
		return Step1Done;
	}

	public void setStep1Done(boolean step1Done) {
		Step1Done = step1Done;
	}

	public boolean isStep3Done() {
		return Step3Done;
	}

	public void setStep3Done(boolean step3Done) {
		Step3Done = step3Done;
	}

	public boolean isStep4Done() {
		return Step4Done;
	}

	public void setStep4Done(boolean step4Done) {
		Step4Done = step4Done;
	}

	public ArrayList<ArrayList<ArrayList<String>>> getGames() {
		return games;
	}

	public void setGames(ArrayList<ArrayList<ArrayList<String>>> games) {
		this.games = games;
	}
}
