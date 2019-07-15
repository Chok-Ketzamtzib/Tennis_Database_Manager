


// Giuseppe Turini
// CS-102, Spring 2019
// Assignment 1
// Modified by @author William Wakefield
// @date 07/01/2019

package TennisDatabase;

import java.util.Iterator;
import java.util.LinkedList;

// Interface (package-private) providing the specifications for the TennisMatchesContainer class.
interface TennisMatchContainerInterface {

   // Desc.: Returns the number of matches in this container.
   // Output: The number of matches in this container as an integer.
   public int getNumMatches();

   // Desc.: Returns an iterator object ready to be used to iterate this container.
   // Output: The iterator object configured for this container.
   public Iterator<TennisMatch> iterator();

   // Desc.: Insert a tennis match into this container.
   // Input: A tennis match.
   // Output: Throws a checked (critical) exception if the container is full.
   public void insertMatch( TennisMatch m ) throws TennisDatabaseException;
   
   // Desc.: Returns all matches in the database arranged in the output array (sorted by date, most recent first).
   // Output: Changed output to LinkedList<TennisMatch>
   // Throws an exception if there are no matches in this container.
   public TennisMatch[] getAllMatches() throws TennisDatabaseRuntimeException;
   
   // Desc.: Returns all matches of input player (id) arranged in the output array (sorted by date, most recent first).
   // Input: The id of the tennis player.
   // Output: Throws an unchecked (non-critical) exception if there are no tennis matches in the list.
   public TennisMatch[] getMatchesOfPlayer( String playerId ) throws TennisDatabaseRuntimeException, TennisDatabaseException;
   
   // Desc.: Delete all matches of a player by id (if found).
   // Output: Throws an unchecked (non-critical) exception if there is no match with that input id.
   public void deleteMatchesOfPlayer( String playerId ) throws TennisDatabaseRuntimeException;
   
}


