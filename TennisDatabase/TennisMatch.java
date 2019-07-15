/** 
* This file contains the Tennis Matches' data and important constructors to be used in the Tennis Database
* @project CS-102 Assignment 1
* @author William Wakefield
* @date 06 June 2019
*/

package TennisDatabase;

public class TennisMatch implements TennisMatchInterface {
	
	private String idPlayer1, idPlayer2, tournament, score;
	private int year, month, day, winner; 
	
	public TennisMatch(String idPlayer1, String idPlayer2, int year,
						int month, int day, String tournament, String score) { 
		
		this.idPlayer1 = idPlayer1;
		this.idPlayer2 = idPlayer2;
		this.year = year;
		this.month = month;
		this.day = day;
		this.tournament = tournament;
		this.score = score;
		
		try {
			
            this.winner = TennisMatchInterface.processMatchScore(this.score);

        } catch (TennisDatabaseRuntimeException e) {
            
        	throw new TennisDatabaseRuntimeException("Match creation failed: score invalid");
        
        }
	
	}
	
	//copy constructor from TennisPlayerContainerNode
    public TennisMatch(TennisMatch match) {
    	
        this.idPlayer1 = match.idPlayer1;
        this.idPlayer2 = match.idPlayer2;
        this.year = match.year;
        this.month = match.month;
        this.day = match.day;
        this.tournament = match.tournament;
        this.score = match.score;
        this.winner = match.winner;
    }
	
	
	public int compareTo(TennisMatch inMatch) {
		 if (this.year > inMatch.year){
			 
	            return 1;
	            
	        } else if (this.year < inMatch.year) {
	            
	        	return -1;
	        
	        } else {
	            if (this.month > inMatch.month) {
	                
	            	return 1;
	            
	            } else if (this.month < inMatch.month) {
	            
	            	return -1;
	            
	            } else {
	            
	            	if (this.day > inMatch.day) {
	                
	            		return 1;
	                
	            	}
	                
	            	if (this.day < inMatch.day) {
	                
	            		return -1;
	                
	            	} else {
	                
	            		return 1;
	                
	            	}
	            }
	        }
	}
		 

	public String getIdPlayer1() {

		return idPlayer1;
	}

	public String getIdPlayer2() {
		
		return idPlayer2;
	}

	public int getDateYear() {
		
		return year;
	}

	public int getDateMonth() {

		return month;
	}

	public int getDateDay() {

		return day;
	}
	

	public String getTournament() {

		return tournament;
	}
	public String getMatchScore() {

		return score;
	}

	public int getWinner() {

		return winner;
	}

	public String printMatch() {
		
		return (String.format("%02d", year) + "/" + String.format("%02d", month) + "/" + String.format("%02d", day) + "," + " "
                + idPlayer1 + "-" + idPlayer2 + "," + " " + tournament + "," + " " + score);
	
	}

	public String getOutput() {

		String output = "MATCH/" + idPlayer1 + "/" + idPlayer2 + "/" + year + "" + month + "" + day + "/" + tournament + "/" + score;
		
		return output;
	}
	
	

}
