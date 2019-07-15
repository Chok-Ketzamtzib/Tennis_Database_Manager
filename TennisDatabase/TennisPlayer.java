/** 
* This file contains the constructors and methods with data pertaining to the Tennis Player
* @project CS-102 Assignment 2
* @author William Wakefield
* @date 01 June 2019
*/
package TennisDatabase;

public class TennisPlayer implements TennisPlayerInterface {

	private String id, firstName, lastName, country;
	private int year;
	
	private int wins; //Read-only
    private int losses;//Read-only

	public TennisPlayer(String id, String firstName, String lastName, int year, String country) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.year = year;
		this.country = country;
	}

	public TennisPlayer(TennisPlayer player) {
		
		this.id = player.id;
        this.firstName = player.firstName;
        this.lastName = player.lastName;
        this.year = player.year;
        this.country = player.country;
		
	}

	public int compareTo(TennisPlayer p) {//arg0 is left from original interfaces
			
		return this.id.compareTo(p.id);
	}

	public String getId() {

		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getBirthYear() {
		return year;
	}

	public String getCountry() {
		return country;
	}

	public int getWins() {
	    return wins;
	}

	public int getLosses() {
		return losses;
	}
	    
	public void addWin() {//increments wins by 1
        wins += 1;
    } 

    public void addLoss() {//increments losses by 1 
        losses += 1;
    } 

    public String getFullName() {
    	
    	return firstName + " " + lastName;
    }
    
    public String getOutput() {
    	
    	String output = "PLAYER/" + id + "/"+ firstName + "/" + lastName + "/" + year + "/" + country;
    	
        return  output;
        
    }
}
