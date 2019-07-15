/** 
* This file contains methods which allow one to manipulate a collection of tennis records 
* Stores two objects called TennisPlayerContainer & TennisMatchContainer
* @project CS-102 Assignment 1
* @author William Wakefield
* @date 08 June 2019
*/

package TennisDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TennisDatabase implements TennisDatabaseInterface {
	
	private TennisPlayerContainer playerContainer = new TennisPlayerContainer(); //Creates object to access TennisplayerContainer class 
	
	private TennisMatchContainer matchContainer = new TennisMatchContainer(); //creates object to access TennisMatchContainer class


	//Desc.: loads data from chosen file (args[])
	//Input: The files name (String)
	///Output: None
	public void loadFromFile(String fileName) throws TennisDatabaseException, TennisDatabaseRuntimeException {

		Scanner fileScan;
		
		try { 
			
			File inFile = new File(fileName);
			
			fileScan = new Scanner(inFile).useDelimiter("[\\r\\n]+");
			
		} catch (FileNotFoundException e) {
			
			throw new TennisDatabaseException("ERROR: Input file not found.");
			
		}

		while (fileScan.hasNextLine()) {              

			String inString = fileScan.nextLine();
			
			Scanner inScan = new Scanner(inString).useDelimiter("[\\r\\n/]");
			
			String token = inScan.next().toUpperCase();

			if (token.equals("PLAYER")) {
				
				String id = inScan.next().toUpperCase();
			
				String firstName = inScan.next().toUpperCase();
				
				String lastName = inScan.next().toUpperCase();
				
				int year = inScan.nextInt();
				
				String country = inScan.next().toUpperCase();
				
				insertPlayer(id, firstName, lastName, year, country);
			
			}

			else if (token.equals("MATCH")) {
			
				String idPlayer1 = inScan.next().toUpperCase();
				
				String idPlayer2 = inScan.next().toUpperCase();
				
				int[] date = splitDate(inScan.next());
				
				String tournament = inScan.next().toUpperCase();
				
				String score = inScan.next().toUpperCase();
				
				insertMatch(idPlayer1, idPlayer2, date[0], date[1], date[2], tournament, score);

			}
			
			else {
			
				fileScan.close();
				
				inScan.close();
				
				throw new TennisDatabaseException("ERROR: Wrong file, try loading a text based file with the Tennis Players' Data");
			
			}
			
			inScan.close();
		
		}
		
		fileScan.close();

	}
	
	// Desc.: Returns copies of all playerss data in the database arranged in the output array 
	// Input: none, it invokes the getAllPlayers method from TennisPlayerContainer Class
    // Output: Throws an unchecked (non-critical) exception if there are no players in the database.
	public TennisPlayer[] getAllPlayers() throws TennisDatabaseRuntimeException {
		
		TennisPlayer[] players = playerContainer.getAllPlayers();
		
		return players;
		
	}
	
	//used if GUI fails to print to console
	public String printAllPlayers() {
		
		TennisPlayer[] playersInfo = playerContainer.getAllPlayers();
		
		String playerString = "";
			
		for ( int i = 0; i < playersInfo.length; i++ ) {
			
			playerString += (playerInfoToString(playersInfo[i])); 
		}
		
		return playerString;
		
	}
	
	//supporting method for printAllPlayers method
	private String playerInfoToString(TennisPlayer t) {
		
        return  ("PLAYER: " + t.getId() + "\nFIRST NAME: "+ t.getFirstName() + "\nLAST NAME" 
        + t.getLastName()+ "\nBIRTH YEAR: " + t.getBirthYear()+ "\nCOUNTRY: " + t.getCountry() + "\n\n");
	}

	public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseException, TennisDatabaseRuntimeException {
		
		return playerContainer.getMatchesOfPlayer(playerId);
		
	}
	
	//Used to print to console if GUI fails
	public String printAllMatchesOfPlayer(String id) throws TennisDatabaseRuntimeException, TennisDatabaseException {
		
		TennisMatch[] matchOfPlayer = playerContainer.getMatchesOfPlayer(id);
		
		String playerString = "";
		
		if( playerContainer.getMatchesOfPlayer(id) == null) {
			
			return playerString = "Database of matches is empty, please load a file or insert matches";
		} else {
		
			for ( int i = 0; i < matchOfPlayer.length; i++ ) {
			
				playerString += (matchInfoToString(matchOfPlayer[i])); 
			}
		}
		
		return playerString;
		
	}
	
    public TennisPlayer getPlayer(String id) throws TennisDatabaseRuntimeException {
        
    	return playerContainer.getPlayer(id);
    }
    
	public TennisMatch[] getAllMatches() throws TennisDatabaseRuntimeException {
		
		return matchContainer.getAllMatches();
	}
	
	// Desc.: Adds player data to matchContainer and playerContainer
	// Input: Data from loadFromFile method
	// Output: None, changes data within Tennis Player Container
	public void insertPlayer(String id, String firstName, String lastName, int year, String country) throws TennisDatabaseException {
		
		try {
			
			TennisPlayer newPlayer = new TennisPlayer(id, firstName, lastName, year, country);
			
			playerContainer.insertPlayer(newPlayer); 
            
		} catch (TennisDatabaseException e) {
			
			throw new TennisDatabaseRuntimeException("Tennis Database Exception in insertPlayer, File's Data is not formatted correctly. Please refer to Example Data File.");
		}

	}

    // Desc.: Adds match data to matchContainer and playerContainer
	// Input: Data from loadFromFile method
	// Output: None, changes data within Tennis Match Container and Tennis Player Container
	public void insertMatch(String idPlayer1, String idPlayer2, int year, int month, int day, String tournament,
			String score) throws TennisDatabaseException {
		
		TennisMatch m = new TennisMatch(idPlayer1, idPlayer2, year, month, day, tournament, score);
		
        try {
        	
            matchContainer.insertMatch(m);
            playerContainer.insertMatch(m);
            
        } catch (TennisDatabaseRuntimeException e) {
        	
            throw new TennisDatabaseRuntimeException("Error: Invalid Match. Match not added");
            
        }
        
        if (m.getWinner() == 1) {
        	
            getPlayer(m.getIdPlayer1()).addWin();
            getPlayer(m.getIdPlayer2()).addLoss();
            
        } else {
        	
            getPlayer(m.getIdPlayer1()).addLoss();
            getPlayer(m.getIdPlayer2()).addWin();
            
        }

	}
	
	//splits the date into a String array which is parsed into Integer to be used with TennisMatch constructor
    public int[] splitDate(String date) throws TennisDatabaseException {
    	
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        
        int[] parsedDate= new int[3];
        
        parsedDate[0] = Integer.parseInt(year);
        parsedDate[1] = Integer.parseInt(month);
        parsedDate[2] = Integer.parseInt(day);
         
        return parsedDate;
    }

    //used to print to console if GUI fails
    public String printAllMatches() {
    	
    	TennisMatch[] matchesInfo = matchContainer.getAllMatches();
		
		String matchString = "";
		
		for ( int i = 0; i < matchesInfo.length; i++ ) {
			
			matchString += (matchInfoToString(matchesInfo[i])); 
		}
		
		return matchString;
		
	}
    
    //supports prinatAllMatches method
    private String matchInfoToString(TennisMatch t) {
		
        return  ("Match: \nID OF PLAYER 1:" + t.getIdPlayer1()+ "\nID OF PLAYER 2: "+ t.getIdPlayer2() + "\nDATE: " 
        + t.getDateYear() + "\t" + t.getDateMonth()	+ "\t" + t.getDateDay() + "\nTOURNAMENT: " 
        		+ t.getTournament() + "\nMATCH SCORE: " + t.getMatchScore()+ "\n\n");
	}
	
    
    public int getMatchCount() {
    	
        return matchContainer.getMatchCount();
        
    }

	public void saveToFile(String fileName) throws TennisDatabaseException {
		
		PrintStream out;
		
        try {
            out = new PrintStream(fileName);
            
        } catch (FileNotFoundException e) {
        	
            throw new TennisDatabaseException("ERROR: Input file not found.");
            
        }

        //print players within the designated file
        TennisPlayerContainerIterator playerIterator = playerContainer.iterator();
        
        playerIterator.setInorder();

        while (playerIterator.hasNext()) {
        	
            out.println(playerIterator.next().getOutput());
        }
        
        Iterator<TennisMatch> matchIterator = matchContainer.iterator();

        while (matchIterator.hasNext()) {
        	
            out.println((matchIterator.next().getOutput()));
            
        }

	}
	
	//Desc.: Purges all data from the database. Database functionality is kept intact
	//Input: None
	//Output: A database with nothing in it
	public void reset() {
		
		 if (this.playerContainer.getNumPlayers() == 0) {
			 
	            System.out.println("Player Container is empty. Nothing to delete");
	            
	        }
	        if (this.matchContainer.getNumMatches() == 0) {
	        	
	            System.out.println("Match Container is empty. Nothing to delete");
	            
	        }
	        
	        this.playerContainer = new TennisPlayerContainer();
	        
	        this.matchContainer = new TennisMatchContainer();
	        
	}
	
	// Desc.: Deletes Player from Database
	// Input: Player ID (String)
	// Output: 
	public void deletePlayer(String playerId) throws TennisDatabaseRuntimeException {
		
		matchContainer.deleteMatchesOfPlayer(playerId);
		
		playerContainer.deletePlayer(playerId);		
		 
	}

	

	

}
