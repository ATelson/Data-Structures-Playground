package sandbox;

import java.io.File;
import java.io.IOException;

public class PriorityQueues {

  //fields that are used in order to extract the data from the text file.
  private static String selectQueue = "";
  private static String queueElements = "";
  //field used to insert data pairs to later split into selectQueue and queueElements.
  private static StringBuilder numElements = new StringBuilder();

  //integer fields used to determine the size of the array and the amount
  // in which the queues can hold nodes and the index that will be used to keep items in order.
  final static int N = 4;
  private static int index = 0;
  private final static int QUEUESIZE = 4;


  //field used to establish the path to the text file.
  private static final File f = new File("data.txt");

  /**
   * This mutator method is used to push data from a txt file with the correct data format, treats
   * the data as key-value pairs and inserts the values into key queues which are within an array
   * structure.
   *
   * @param queues is the array of queues that will used with the data sets.
   */
  static void executeData(DynamicQueue[] queues) {
    //extract data from the txt file
    try {
      numElements = ReadTextFile.readLines(f);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //now sort the data into their respective locations.
    int i = 0;
    while (i != numElements.length()) {
      //if the first char in a set is a letter and the second
      // char in a set is a number, assign the chars to their respective String variables.
      try {

        if (Character.isLetter(numElements.charAt(i)) && Character
            .isDigit(numElements.charAt(i + 2))) {
          queueElements = queueElements + numElements.charAt(i);
          selectQueue = selectQueue + numElements.charAt(i + 2);
        } else {
          //if the data isn't set into pairs then the data is either in the incorrect
          // format or the file is corrupted.
          System.out.println("Error: Incorrect Data format");
          System.exit(1);
        }
        //increment the index by 4 as it would avoid
        // white spaces and move on to the next set of chars.
        i = i + 4;
      } catch (StringIndexOutOfBoundsException e) {
        System.out.println("Error: Incorrect data format.");
        System.exit(1);
      }
    }
    char selectedQueue = selectQueue.charAt(index);
    char selectedChar = queueElements.charAt(index);
    int x = Character.getNumericValue(selectedQueue);

    //Go through loop iteratively by setting the condition that the index should be lower
    // than the length of the amount of values within the queueElements String.

    while (index < queueElements.length()) {
      //Print which element is being read from the queueElements String.
      System.out.print(
          "\nRead key " + selectedChar + " for queue " + selectedQueue
              + ". ");

      // first, check if the corresponding queue is full
      if (DynamicQueue.checkSize(queues[x].front)
          < QUEUESIZE) {
        //if the queue isn't full, check to see if the element being inserted
        // already exists in the distinct queue.
        if (queues[x]
            .contains(selectedChar)) {

          //create new nodes that will be set up as pointers for the queue.
          DynamicNode currentNode = queues[Character
              .getNumericValue(selectedQueue)].front;
          DynamicNode previousNode = null;
          DynamicNode nextNode = currentNode.getNext();

          //check if the first node is the only node in the queue.
          if (currentNode.getNext() == null) {
            System.out.println(
                "Element: '" + currentNode.getInfo() + "' is the only element in Queue: "
                    + selectedQueue);
          }

          //otherwise go through loop within the queue itself to find the
          // element that already exists.
          while (currentNode != null) {

            //if the current node's information matches the element trying to be inserted
            if (currentNode.getInfo().equals(selectedChar)) {
              System.out.print(
                  "Moving " + selectedChar + " to rear.");

              //if it is the first node on the distinct queue
              if (currentNode == queues[Character
                  .getNumericValue(selectedQueue)].front) {
                //set the rear pointer to the first node on the list.
                queues[x].rear
                    .setNext(currentNode);
                //move rear pointer to the currentNode so it the last node in the queue.
                queues[x].rear = currentNode;
                //setNext for currentNode will be null.
                currentNode.setNext(null);
                //set front pointer to the next node on the list.
                queues[x].front = nextNode;
                break;
              }
              // or if the next node is positioned at the end of the queue
              else if (currentNode == queues[Character
                  .getNumericValue(selectedQueue)].rear) {
                System.out.print(" " + selectedChar + " already exist in rear of Q"
                    + selectedQueue + ".");
                break;
              } else {
                //if it isn't the first node on the queue nor is it the last on the queue,
                //set currentNode to null
                currentNode.setNext(null);
                queues[x].rear
                    .setNext(currentNode);
                queues[x].rear = currentNode;
                if (previousNode != null) {
                  previousNode.setNext(nextNode);
                }
                break;
              }
            }
            //increment the pointers by one position.
            previousNode = currentNode;
            currentNode = nextNode;
            nextNode = nextNode.getNext();
          }
        }
        //if the distinct queue doesn't already have the element trying to be inserted, insert
        // element into the distinct queue.
        else {
          System.out.print(
              "Inserting " + selectedChar + " in rear. Q" + selectedQueue
                  + ": ");
          queues[x]
              .insert(selectedChar);
          queues[x].printQueue();
        }
      }
      //if the queue is full, check to see if there is a duplicate element first before removing.
      else if (queues[x]
          .contains(selectedChar)) {
        //if it does contain a duplicate, move the element to the rear of the list.
        //create three pointers that will point to the current, next and previous positions
        // throughout the loop as you go through incrementally to find the element.
        DynamicNode current = queues[x].front;
        DynamicNode previous = null;
        DynamicNode next = current.getNext();

        //now loop through the queue.
        while (current != null) {
          //if the duplicate element happens to be the first in the queue
          if (current == queues[x].front
              && current
              .getInfo().equals(selectedChar)) {

            System.out.print("Moving " + selectedChar + " to rear.");
            //set the rear node to point to the first node on the queue.
            queues[x].rear.setNext(current);
            //set current node's pointer to null.
            current.setNext(null);
            //move front pointer to the next node on the list so it is in the front.
            queues[x].front = next;
            //move the rear pointer to the current node which will then be the last node
            // in the queue.
            queues[x].rear = current;
            //print the queue.
            System.out.print("Q" + selectedQueue + ": ");
            queues[x].printQueue();
            break;
          }
          //if the current node that is a duplicate happens to be the last node on a full queue
          else if (current.getInfo().equals(selectedChar)
              && current == queues[x].rear) {
            //there is no need to do anything but reprint the Q to show that it is
            // already at the end of the queue.
            System.out.print(
                selectedChar + " is already rear. " + "Q" + selectedQueue
                    + ": ");
            queues[x].printQueue();
            break;
          }
          //if the duplicate element is positioned neither in the front or the rear...
          else if (current.getInfo().equals(selectedChar)) {
            System.out.print("Moving " + selectedChar + " to rear. ");
            //set the specific node's pointer to null
            current.setNext(null);
            //set the rear node's next pointer to the current node.
            queues[x].rear.setNext(current);
            //move the rear pointer to the current node as it is now at the end of the queue.
            queues[x].rear = current;
            //now set the previous node's next pointer to point to the node after the
            // duplicate element's node.
            previous.setNext(next);
            //print the distinct queue.
            queues[x].printQueue();
            break;
          }
          //incrementally move through the queue by moving
          // each pointer to the next node beside them.
          previous = current;
          current = next;
          next = next.getNext();
        }
      }//if the distinct queue doesn't have a duplicate element that is trying to be inserted...
      else {
        //remove the front node of the queue and move it to the back of the queue.
        System.out.print(
            "Q is full, removing front. Inserting " + selectedChar
                + " in rear. Q" + selectedQueue + ": ");
        //remove the front node of the queue.
        queues[x].remove();
        //insert the new element into the queue.
        queues[Character.getNumericValue(selectQueue.charAt(index
        ))].insert(selectedChar);
        //print the queue.
        queues[x].printQueue();
      }
      //increment the index to continue through the loop.
      index++;
    }
    //Once the process is complete, print all of the queues in order to show the
    // results of the mutations.
    System.out.println("\n\n..Final Queues..");
    for (int q = 0; q < queues.length; q++) {
      System.out.print("Q" + q + ":");
      queues[q].printQueue();
      System.out.print("\n");
    }
  }
}
