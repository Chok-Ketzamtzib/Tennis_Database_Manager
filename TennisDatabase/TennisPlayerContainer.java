/** 
* This file performs operations to store Tennis Player data
* @project CS-102 Assignment 1
* @author William Wakefield
* @date 01 June 2019
*/

package TennisDatabase;

 class TennisPlayerContainer implements TennisPlayerContainerInterface {

	private TennisPlayerContainerNode root;
	
	private int playerCount;

	public TennisPlayerContainer() {

		this.root = null;
		
		this.playerCount = 0;
		
	}
	
	// Desc.: Search for a player in this container by id, and delete it with all his matches (if found).
	// Input: TennisPlayer object with TennisPlayer Fields 
    // Output: None
	// Throws an unchecked (non-critical) exception if there is no player with that input id.
	
	public void insertPlayer(TennisPlayer p) throws TennisDatabaseException {
		
		this.root = insertPlayerRec(this.root, p);
		
		playerCount++;
		
	}
	
	// Desc.: Recursive function to support insertPlayer 
	// Input: Root from TennisPlayerContainerNode and TennisPlayer object from insertPlayer
	// Ouput: A new player node with player's data within the Tennis Player Container (TennisPlayerContainerNode)
	
	private TennisPlayerContainerNode insertPlayerRec(TennisPlayerContainerNode currRoot, TennisPlayer p) throws TennisDatabaseException {
		
		if (currRoot == null) {
			
            return new TennisPlayerContainerNode(p);
            
        } else { // 3-way comparison to understand how to proceed the search.
            
        	// Get player from current root and compare them
            int comparisonResult = currRoot.getPlayer().compareTo(p); 
            
            if (comparisonResult == 0) {// if they are the same
            	
                throw new TennisDatabaseException("Player already exists within database. Please insert a new player");
                
            } else if (comparisonResult < 0) {// if they are different
            	
                currRoot.setRight(insertPlayerRec(currRoot.getRight(), p));
                
                return currRoot;
                
            } else {// else 
            	
                currRoot.setLeft(insertPlayerRec(currRoot.getLeft(), p));
                
                return currRoot;
            }
        }
	}
	
	// Desc.: Inserts match for two players (Assuming they exist within the database)
	// Input: Tennis Match Object
	// Ouput: None
	// Throws a TennisDatabase Exception if players are not found

	public void insertMatch(TennisMatch m) throws TennisDatabaseException { 
		
		// Extract the ID's of player 1 and player 2 from TennisMatch class
		
        String idPlayer1 = m.getIdPlayer1();
        String idPlayer2 = m.getIdPlayer2();
        
        // Search the node associated with player 1 by ID 
      
        TennisPlayerContainerNode nodeP1 = getPlayerNodeRec(this.root, idPlayer1);
        TennisPlayerContainerNode nodeP2 = getPlayerNodeRec(this.root, idPlayer2);

        if ((nodeP1 != null) && (nodeP2 != null)) {// if players' nodes are not empty, we insert both matches 
        	
            nodeP1.insertMatch(m);
            nodeP2.insertMatch(m);
            
        } else {
        	
            throw new TennisDatabaseException("Player could not be found for insertion");
            
        }

	}
	
	
	// Desc.: Returns copies (deep copies) of all matches of input player (id) arranged in the output array (sorted by date, most recent first)
    // Input: The id of a player
    // Output: an array of the players (TennisPlayer[])
	// Throws a checked (critical) exception if the player (id) does not exists
    // Throws an unchecked (non-critical) exception if there are no matches (but the player id exists)
	
	public TennisPlayer[] getAllPlayers() throws TennisDatabaseRuntimeException {
		  	
		TennisPlayerContainerIterator iterator = this.iterator();
		
        iterator.setInorder();
        	
        TennisPlayer[] outputArray = new TennisPlayer[playerCount];
        
        int index = 0;
        
        while (iterator.hasNext()) {
        	
            outputArray[index] = iterator.next();
            
            index++;
            
        }
        
        return outputArray;
        
	}

	// Desc.: Searches through nodes to find player. 
	// Input: Player's ID (String)
	// Output: player's node from ID inputted (TennisPlayer)
	// Throws Tennis Database Runtime Exception if id does not exist
	public TennisPlayer getPlayer(String playerId) throws TennisDatabaseRuntimeException {
		
		TennisPlayerContainerNode playerNode = getPlayerNodeRec(this.root, playerId);
		
        if (playerNode == null) { 
        	
        	throw new TennisDatabaseRuntimeException("Player's ID could not be found"); 
        
        } else {
        	
            return playerNode.getPlayer();
        }
        
	}
	
	// Searches for player with inputted id and creates an array of their matches
    public TennisMatch[] getPlayerMatches(String playerId) throws TennisDatabaseRuntimeException {
    	
        TennisPlayerContainerNode node = this.root;
        
        boolean idFound = false;
        
        int nodeIndex = 0;

        while((node != null) && nodeIndex < this.playerCount && !(node.getPlayer().getId().equals(playerId))) {
        	
        	node = node.getNext();
        	
        	nodeIndex++;
        	
        }
        
        if(node != null) {
        	return node.getMatches();
        }
        
        throw new TennisDatabaseRuntimeException("Player could not be found.");

    }
    
    //Desc.: Uses the getPlayerNode method 
	public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseException, TennisDatabaseRuntimeException {
		
		TennisPlayerContainerNode nodePlayer = getPlayerNodeRec(this.root, playerId);
		
        return nodePlayer.getMatches();
	}
	
		// Desc.: Recursive function to support insertPlayer 
		// Input: Root from TennisPlayerContainerNode and TennisPlayer object from insertPlayer
		// Ouput: A new player node with player's data within the Tennis Player Container (TennisPlayerContainerNode)
		
		private TennisPlayerContainerNode getPlayerNodeRec(TennisPlayerContainerNode currRoot, String playerId) {
			
			 if (currRoot == null) { 
				 
				 return null; 
				 
				 } else {// 3-way comparison to understand how to proceed the search
		            
					// Get player from current root and compare their ID's
		            int comparisonResult = currRoot.getPlayer().getId().compareTo(playerId);
		            
		            if (comparisonResult == 0) {//if they are the same
		            	
		                return currRoot;
		            
		            } else if (comparisonResult < 0) {
		            	
		                return getPlayerNodeRec(currRoot.getRight(), playerId);
		            
		            } else {
		            	
		                return getPlayerNodeRec(currRoot.getLeft(), playerId);
		            }
		        }
			
		}
	
	// Desc.: Checks if new player ID already exists
	// Input: Player ID (TennisPlayer)
	// Output: Boolean of whether the statement is the same or not
    public boolean idNewPlayerCheck(TennisPlayer inPlayer) {
    	
        TennisPlayerContainerNode node = this.root;
        
        for (int i = 0; i < playerCount; i++) {
        	
            if (node.getPlayer().getId().equals(inPlayer.getId())) {
                return true;
            }
        }
        return false;
    }
    
    // Desc.: Gets the number of players
    // Input: None
    // Output: Number of Players (int)
    
    public int getPlayerCount() {
        
    	return playerCount;
    	
    }

    // Desc.: Returns the number of players within the Tennis Player Container.
    // Input: None
    // Output: The number of players in this container as an integer.
    
    public int getNumPlayers() {

		return this.playerCount;
	}

    // Desc.: Returns an iterator object ready to be used to iterate this container.
    // Input: None
    // Output: The iterator object configured for this container.
    
    public TennisPlayerContainerIterator iterator() {

		return new TennisPlayerContainerIterator(this.root);
	}

    // Desc: Searches for the tennis player in the container by id, and delete it with all his matches
    // Input: Tennis Player ID (String)
    // Output: Throws an unchecked (non-critical) exception if there is no player with that input id
    
	public void deletePlayer(String playerId) throws TennisDatabaseRuntimeException {
		
		TennisPlayerContainerNode playerNode = deletePlayerNodeRec(this.root, playerId);
		
	}

	private TennisPlayerContainerNode deletePlayerNodeRec(TennisPlayerContainerNode currRoot, String playerId) throws TennisDatabaseRuntimeException {
			
		if (currRoot == null) {
			
            throw new TennisDatabaseRuntimeException("Deletion Failed, Player could not be found");
            
        } else { // 3-way comparison to understand how to proceed the search.
        	
            int comparisonResult = currRoot.getPlayer().getId().compareTo(playerId);
            
            if (comparisonResult == 0) {
            	
                return deleteNode(currRoot);
                
            } else if (comparisonResult < 0) {
            	
                TennisPlayerContainerNode newRightChild = deletePlayerNodeRec(currRoot.getRight(), playerId);
                
                currRoot.setRight(newRightChild);
                
                return currRoot;
                
            } else {
            	
                TennisPlayerContainerNode newLeftChild = deletePlayerNodeRec(currRoot.getLeft(), playerId);
                
                currRoot.setLeft(newLeftChild);
                
                return currRoot;
                
            }
        }
	}

	private TennisPlayerContainerNode deleteNode(TennisPlayerContainerNode currRoot) {
		
        if (currRoot.getLeft() == null && currRoot.getRight() == null) {//currRoot has no children
        	
            return null;
            
        }
        
        else if (currRoot.getLeft() != null && currRoot.getRight() == null) {//only has left child
        	
            return currRoot.getLeft();
            
        }
        
        else if (currRoot.getRight() != null && currRoot.getLeft() == null) {//only has right child
            return currRoot.getRight();
        }
        
        //symmetric, search for inorder successor swap
        else { 
        	
            //find the inorder successor, leftmost node of the right subtree, get leftmost of right child
            TennisPlayerContainerNode leftMost = findLeftMost(currRoot.getRight());
            
            //perform the copy of content from the successor into currRoot (set and get players and matches)
            currRoot.setPlayer(leftMost.getPlayer());
            currRoot.setMatchList(leftMost.getMatchList());
            
            //delete the successor , if no children return null, if right child return that
            TennisPlayerContainerNode deletedNode = deleteLeftMostRec(currRoot.getRight());
            currRoot.setRight(deletedNode);
            return deletedNode;

            //return currRoot
        }
	}
	
	private TennisPlayerContainerNode deleteLeftMostRec(TennisPlayerContainerNode currRoot){
		
        if (currRoot == null) { return null;}
        
        else if (currRoot.getLeft() == null) {return currRoot.getRight();}
        
        else {
        	
            TennisPlayerContainerNode newLeftNode = deleteLeftMostRec(currRoot.getRight());
            
            currRoot.setLeft(newLeftNode);
            
            return currRoot;
            
        }
    }

    private TennisPlayerContainerNode findLeftMost(TennisPlayerContainerNode currRoot) {
    	
        if (currRoot.getLeft() == null) {
        	
            return currRoot;
        }
        else {
        	
            return findLeftMost(currRoot.getLeft());
            
        }
    }
}
