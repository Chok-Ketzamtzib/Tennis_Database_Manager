/** 
* This file creates the Tennis Player Container Node to be used for its Binary Search Tree 
* Stores two objects called TennisPlayerContainer & TennisMatchContainer
* @project CS-102 Assignment 1
* @author William Wakefield
* @date 08 June 2019
*/

package TennisDatabase;

 class TennisPlayerContainerNode {

	private TennisPlayerContainerNode next;
	private TennisPlayerContainerNode prev;
	private TennisPlayerContainerNode left;
	private TennisPlayerContainerNode right;

	private TennisPlayer player;
	private SortedLinkedList<TennisMatch> list; // List of matches of this player.

	public TennisPlayerContainerNode(TennisPlayer inputPlayer) {
		this.next = null;
		this.player = null;
		this.player = inputPlayer;
		this.list = new SortedLinkedList<TennisMatch>();
		
		this.left = null;
		this.right = null;
	}
	
	//---Getters for left child, right child, players, previous node, and next node---
	
	public TennisPlayerContainerNode getLeft() {
		
		return this.left;
	}
	
	public TennisPlayerContainerNode getRight() {
	
		return this.right;
	}
	
    public TennisPlayer getPlayer() {
        return this.player;
    }

    public TennisPlayerContainerNode getPrev() {
        return this.prev;
    }

    public TennisPlayerContainerNode getNext() {
        return this.next;
    }
    
    //===Setters for left child, right child, players, previous node, and next node---
    
    public void setLeft(TennisPlayerContainerNode p) {
        this.left = p;
    }
    
    public void setRight(TennisPlayerContainerNode n) {
        this.right = n;
    }

    public void setPlayer(TennisPlayer player) {
    	
        this.player = player;
        
    }

    public void setPrev(TennisPlayerContainerNode p) {
        this.prev = p;
    }

    public void setNext(TennisPlayerContainerNode n) {
        this.next = n;
    }

    
    public void insertMatch(TennisMatch m) throws TennisDatabaseException {

    	try {
            list.insert(m);
        } catch (Exception e) {
            throw new TennisDatabaseException("insertion to doubly linked list failed");
        }
    	
    }

    public TennisMatch[] getMatches() throws TennisDatabaseRuntimeException { //utilizes shadow cloning method
    	
        TennisMatch[] a = new TennisMatch[list.size()];
        
        for(int i = 0; i < list.size();i++) {
        	
            a[i] = list.get(i);
        }

        TennisMatch[] b = new TennisMatch[a.length];

        for (int i = 0; i < a.length; i++) {
        	
            b[i] = new TennisMatch(a[i]); //copy constructor to TennisMatch Class
        
        }
        
        return b;
    }

    public SortedLinkedList<TennisMatch> getMatchList(){
    	
        return this.list;
        
    }

	public void setMatchList(SortedLinkedList<TennisMatch> matches) {
		this.list = matches;
	}
	
}
