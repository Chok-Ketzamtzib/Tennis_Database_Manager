Tennis Database Manager Instructions V2.0
Author: William Wakefield
Updated: 12 June, 2019


Maintenance:

	1. Data can only be imported in the form of a text file (.txt). The string within the file must follow this format:
	
		Player entry: 
			PLAYER/<PLAYERID>/<FIRST NAME>/<LAST NAME>/<birthyear yyyy>/<COUNTRY>
		Match entry:
			MATCH/<player1 id>/<player2 id>/<date yyyymmdd>/<TOURNAMENT>/<scores, separated by commas e.g. 6-3,6-7,6-21>
			
	2. Click the "Load from File" Button to choose the text file to load the data into the Tennis Database. NOTE only text files are supported in V2.0 
	3. Click the "Save to File" button to export data within the database. Go to the desired directory within your PC where you want to save your file and name it.
	4. Click the "Reset Database" to purge all data within the database. Please note that the database is still functional after data is deleted. 
	5. Enter a player's ID under "Delete Player" and click the button to delete the desired player.
	6. On the right side of the manager, fill the empty fields for either a match or player and click the respective button to insert a match or player.
	    6a. Inserting a Match:
	        1. Player 1 and 2 ID: An alphanumeric representation of the players
	        2. Year, Month, and Day: Numeric entries for these items
	        3. Tournament: Name for the tournament
	        4. Score: Comma separated score entries. Example: 4-6,4-6
        6b. Inserting a Player:
            1. Player ID: An alphanumeric representation of the player
            2. First and Last Name: Name of the player
            3. Birth Year: Numeric year the player was born
            4. Country: Player's country of origin
            
    The text fields conveniently will prompt you what you need to enter as well.
    
    The Tennis Database Manager also features a locked console at the bottom to inform you of any errors with your inputs or actions last done. 

Arrays VS Linked List: What Data Structure the Tennis Database Manager Implements

For the Tennis Database, Linked List has been chosen as the more appropriate Abstract Data Structure due to its efficiency in CRUD operations such as insertion, deletion, and creation of data. 
Linked Lists are more efficient compared to ArrayList when it comes to modifying the size of the database. Arrays are limited by the indices already produced at the start of the program. 
However, when more data is added than the number of indices within the array(s), memory needs to be reallocated every time the array is expanded. Modifying the array by means of insertion or deletion 
is also just as memory intensive, the fields have to be moved to different locations within the array. Linked Lists do not have memory allocation problems due to the use of references to change
the data within the list. When a CRUD operation happens, only the references with the desired data within the list are affected, not the entire list. While arrays are better than linked lists 
at querying a specific data point, searching throughout an entire list can upgraded with binary search trees (BST). The only major advantage the ArrayList has is easier maintainability and 
readability when it comes to an enterprise software environment. Among a team of software engineers, an ArrayList structure would be easier for the team to use and modify for different purposes
on the database. The use of arrays would also make it easier to learn for new software engineers. 
 
Overall, manipulation of the data within the Tennis Database is easier and faster since the LinkedList class it 
implements List and Dequeue Interfaces, allowing the LinkedList class to act as both a link and a queue. 
 
 
