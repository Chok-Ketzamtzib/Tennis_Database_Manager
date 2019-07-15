


// Giuseppe Turini
// CS-102, Spring 2019
// Assignment 1

package TennisDatabase;

// Generic class implemeting a queue using a circular array (resizable).
public class QueueArrayBased<T> {

   private T[] array; // Internal circular array (resizable).
   private int sizePhysical; // Maximum number of items the array can store.
   private int sizeLogical; // Current number of items the array is storing.
   private int front; // Index of the item at the front of the queue.
   private int back; // Index of the item at the back of the queue.

   // Constructor.
   public QueueArrayBased() {
      this.sizePhysical = 8;
      this.sizeLogical = 0;
      this.front = 0;
      this.back = -1;
      //@SuppressWarnings("unchecked")
      this.array = (T[]) new Object[ this.sizePhysical ];
   }
   
   // Boolean method to check if this queue is empty, or not.
   public boolean isEmpty() { return this.sizeLogical == 0; }
   
   // Method to insert an item at the back of this queue.
   public void enqueue( T item ) {
      // Check if queue is full.
      if( this.sizeLogical == this.sizePhysical ) {
         // Resize the circular array into a bigger circular array.
         int newSizePhysical = this.sizePhysical * 2;
         T[] newArray = (T[]) new Object[ newSizePhysical ];
         int newFront = 0;
         int currIndexOldArray = this.front;
         int currIndexNewArray = newFront;
         for( int i = 1; i <= this.sizeLogical; i++ ) {
            newArray[ currIndexNewArray ] = this.array[ currIndexOldArray ];
            currIndexNewArray = ( currIndexNewArray + 1 ) % newSizePhysical;
            currIndexOldArray = ( currIndexOldArray + 1 ) % this.sizePhysical;
         }
         this.array = newArray;
         this.sizePhysical = newSizePhysical;
         this.front = newFront;
         this.back = currIndexNewArray;
      }
      // Insert input item in array at back.
      this.back = ( this.back + 1 ) % this.sizePhysical; // Update index of item at back.
      this.array[ this.back ] = item;
      this.sizeLogical++;
   }
   
   // Method to extract an item from the front of this queue.
   public T dequeue() {
      if( isEmpty() ) { return null; } // Return null if queue is empty.
      else {
         T itemAtFront = this.array[ this.front ]; // Store apart item at front of queue.
         this.array[ this.front ] = null; // Totally unnecessary.
         this.front = ( this.front + 1 ) % this.sizePhysical; // Update index of item at front.
         this.sizeLogical--;
         return itemAtFront; // Return item stored apart.
      }
   }

}


