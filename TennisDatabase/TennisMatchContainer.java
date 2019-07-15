package TennisDatabase;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import TennisDatabase.TennisMatch;

class TennisMatchContainer implements TennisMatchContainerInterface {

	private TennisMatch[] matchArray;
	
	private LinkedList<TennisMatch> matches = new LinkedList<TennisMatch>();
	
	private int matchCount; // # matches
	
	private int maxMatches = 2; // max # matches

	// constructor for TennisMatchContainer
	public TennisMatchContainer() {
		
		this.matches = new LinkedList<TennisMatch>();

		this.matchArray = new TennisMatch[2];
		
		this.matchCount = 0;
		
	}
	
	public void insertMatch(TennisMatch m) throws TennisDatabaseException {
		
		int point = 0;
		
        while ((point < this.matches.size() ) && (this.matches.get(point).compareTo(m) > 0)) {
            
        	point++;
            
        }
        
        this.matches.add( point, m );
		
	}

	//Desc.: Returns all the matches to the user
	//Input: A tennis match
	//Output: All matches in the entire database formatted (String)
	
	public TennisMatch[] getAllMatches() throws TennisDatabaseRuntimeException {
		
		TennisMatch[] outputArray = new TennisMatch[getNumMatches()];
		

        for(int i = 0; i < outputArray.length; i++){
        	
            outputArray[i] = matches.get(i);
        }

        return outputArray;
	}
	
	

    // Desc.: Returns all matches of input player (id) arranged in the output array (sorted by date, most recent first).
    // Input: The id of the tennis player.
    // Output: Throws an unchecked (non-critical) exception if there are no tennis matches in the list.
	public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseException {
		
		System.out.println(matches.toArray());
		
	return (TennisMatch[]) matches.toArray();
		
	}
	
	public int getMatchCount() {
		
		return matchCount;
	
	}

	// Desc.: Returns number of matches 
	// Input: None
	// Output: # of matches (int)
	
	public int getNumMatches() {

		return this.matches.size();
	
	}

	public Iterator<TennisMatch> iterator() {

		return this.matches.iterator();
		
	}

	//Desc.: Deletes the desired match of a player based on the player's ID
	//Input: Player's ID (String)
	//Output: None
	public void deleteMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {
		
		int index = 0;
		
        while (index < matches.size()) {
        	
            if (matches.get(index).getIdPlayer1().equals(playerId) || matches.get(index).getIdPlayer2().equals(playerId)){
            	
                matches.remove(index);
                
            } else {
            	
                index += 1;
            }
        }
    }
	
}
