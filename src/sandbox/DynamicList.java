package sandbox;

//Dynamic List class.
class DynamicList {

  // DynamicNode as a field to point to first node within the linkedList.
  DynamicNode head;

  //Dynamic list constructor
  DynamicList() {
    head = null;
  }

  /**
   * checks if list is empty.
   */
  private boolean isEmpty() {
    return head == null;
  }

  /**
   * Method used to insert a new node at the front of the DynamicList.
   *
   * @param data represents data that will be stored within the new node being placed in the front
   * of the list.
   */
  void insertFirst(Object data) {
    //create new node
    DynamicNode q = new DynamicNode(data, null);
    if (!isEmpty()) {
      //set node's pointer to point to the beginning of the list
      q.setNext(head);
    }
    // set list to point to new node as the first node in the list
    head = q;
  }

  public void insertAfter(DynamicNode p, Object x) {
    //check if current node pointer is empty
    if (p == null) {
      //cannot insert a node after a null pointer
      System.out.println("void insertion");
      System.exit(1);
    }
    //create new node and insert data into object and insert address to the next node
    DynamicNode q = new DynamicNode(x, p.getNext());
    //point current node to new node
    p.setNext(q);
  }

  public Object deleteFirst() {
    if (isEmpty()) {
      //Cannot delete from empty list
      System.out.println("void deletion!");
      System.exit(1);
    }
    //store list info into temp object
    Object temp = head.getInfo();
    if (head.getNext() == null) {
      //set list to null if list will be empty after deletion
      head = null;
    } else {
      //set list to next node
      head = head.getNext();
    }
    //show deleted node data
    System.out.println(temp);
    return temp;
  }

  public Object deleteAfter(DynamicNode p) {
    if (p == null || p.getNext() == null) {
      //cannot delete after if the list is empty or if there isn't a node after the current node
      System.out.println("void deletion");
      System.exit(1);
    }
    //create new node and point to the node the current node is pointing to
    DynamicNode q = p.getNext();
    //store node data within temp object
    Object temp = p.getInfo();
    //set current node to the node after the next node
    p.setNext(q.getNext());
    //return deleted node data
    return temp;
  }

  /**
   * Method used to find the corresponding node.
   *
   * @param x is the node that is being searched for.
   */
  public DynamicNode mystery(Object x) {
    DynamicNode nd = head;
    while (nd != null) {
      if (nd.getInfo().equals(x)) {
        return nd;
      }
      nd = nd.getNext();
    }
    return nd;
  }

  /**
   * Iterative method used to print linkedList.
   */
  void printList() {
    //check list to see if it is empty.
    if (isEmpty()) {
      System.out.println("List is empty.");
      return;
    }
    //create a new String that will be used to print the data within each DynamicNode.
    String result = "";
    DynamicNode currentNode = head;
    //Loop through the DynamicList
    while (currentNode != null) {
      //If there is another node beyond the current node
      if (currentNode.getNext() != null) {
        //add data to the result String and add an arrow at the end
        result = result + currentNode.getInfo() + "->";
        currentNode = currentNode.getNext();
      } else { //if there isn't another node then add the final node's data and set the head to null
        result = result + currentNode.getInfo();
        currentNode = currentNode.getNext();
      }
    }//end while loop
    //print the list after the loop.
    System.out.println(result);
  }

  /**
   * Recursive method used to print DynamicList.
   */
  void print(DynamicNode l) {
    if (l != null) {
      System.out.println(l.getInfo());
    }
    print(l.getNext());
  }

  int sum(DynamicNode nd) {
    if (nd == null) {
      return 0;
    } else {
      return 1 + sum(nd.getNext());
    }
  }

  void reversePrint(DynamicNode l) {
    if (l == null) {
      return;
    } else {
      reversePrint(l.getNext());
      System.out.println(l.getInfo());
    }
  }

  public static void recursivePrint(DynamicNode list) {
    if (list == null) {
      return;
    } else {
      System.out.println(list.getInfo());
      recursivePrint(list.getNext());
    }
  }

  Object recursivelyReverse(DynamicNode list) {
    if (list == null) {
      return null;
    } else if (list.getNext() == null) {
      return list;
    } else {
      DynamicNode nextNode = list.getNext();
      list.setNext(null);
      DynamicNode restNode = (DynamicNode) recursivelyReverse(nextNode);
      nextNode.setNext(list);
      return restNode;
    }
  }

  void displayNth(int index) {
    //create new node and assign its pointer to the first node of the linkedList
    //initialize counter that will be used in conjunction with the int from the parameter
    int counter = 0;
    //cycle through the linkedList using new node
    for (DynamicNode p = head; p != null; p = p.getNext()) {
      if (counter == index) {
        System.out.println(p.getInfo());
        break;
      } else if (p.getNext() == null) {
        break;
      } else {
        counter++;
      }
    }
    if (counter < index) {
      System.out.println("Not found in index");
    }
  }

  boolean checkNth(int index) {
    //create new node and assign its pointer to the first node of the linkedList
    //initialize counter that will be used in conjunction with the int from the parameter
    int counter = 0;
    //cycle through the linkedList using new node
    for (DynamicNode p = head; p != null; p = p.getNext()) {
      if (counter == index) {
        return true;
      } else if (p.getNext() == null) {
        break;
      } else {
        counter++;
      }
    }
    if (counter < index) {
      System.out.println("Not found in index");
    }
    return false;
  }

  /**
   * Method used to append the beginning of one list to the end of another list.
   *
   * @param otherList is the list used to append to the end of the the current list's list.
   */
  boolean appendList(DynamicList otherList) {
    //check if the current list is empty and check if the list being attached is empty.
    //If the list is empty then append the other list to it.
    if (isEmpty() && !otherList.isEmpty()) {
      //append the other list to the current list
      head = otherList.head;
      return true;
    }
    //loop through the list to seek where the last node is.
    for (DynamicNode p = head; p != null; p = p.getNext()) {
      if (p.getNext() == null && otherList != null) {
        //once found, set the pointer of the last node from null to the beginning of the otherList
        p.setNext(otherList.head);
        return true;
      }
    } // if the list is empty and the other list is empty then return false.
    return false;
  }

  /**
   * Method is used to reverse the linkedList.
   */
  void reverse() {
    //Create three nodes that will represent positions within the linkedList
    //current will start at the first node within the list
    DynamicNode currentNode = head;
    //the next two nodes previous and next will start off at null
    DynamicNode nextNode = new DynamicNode();
    DynamicNode previousNode = new DynamicNode();

    //check if list is empty
    if (currentNode == null) {
      System.out.println("Error: List is empty.");
      return;
    }

    //loop through the linkedList while the currentNode doesn't equal null
    while (currentNode != null) {
      //set nextNode to the next node in the linkedList
      nextNode = currentNode.getNext();
      //then set the current node to the previous node which starts at null
      currentNode.setNext(previousNode);
      //the previous node will then move to where the current node is
      previousNode = currentNode;
      // finally, move the current node to the next node
      currentNode = nextNode;
    }
    //once current node is moved out of the list, set the head of the list to the last node
    //which is assigned to previousNode once the loop is finished
    head = previousNode;

    //remove null node from reversed linkedList
    DynamicNode checkNull = head;
    while (checkNull != null) {
      //if the next node's information is null
      if (checkNull.getNext().getInfo() == null) {
        //create a pointer that points to the null node
        DynamicNode nullNode = checkNull.getNext();
        //set node before null node next to no node
        checkNull.setNext(nullNode.getNext());
      }
      //increment checkNull by one
      checkNull = checkNull.getNext();
    }
  }

  /**
   * Method used to check if there is a middle node within a linkedList and delete it if there is
   * and return the deleted node.
   */
  Object deleteMid() {
    //first check if the list is empty or not
    if (isEmpty()) {
      System.out.println("Error: This list is empty");
      return 0;
    }
    //create three pointers current, slow and fast.
    DynamicNode current = head; //set current to first node on list
    DynamicNode slow;
    DynamicNode fast = null;

    //check if the next node is null
    if (current.getNext() != null) {
      //set the fast pointer to the node next in the linkedList
      fast = current.getNext();
    } else {
      //if there isn't a next node on the list then it is the only node on the list.
      System.out.println("Only one node in list!");
      System.exit(1);
    }
    //run through the linkedList iteratively
    while (current != null) {
      //move slow pointer to current node
      slow = current;
      //check if node next to fast pointer is not empty
      if (fast.getNext() != null) {
        //check if node next to it is not empty as well
        if (fast.getNext().getNext() != null) {
          // if not, then move the fast node two spots.
          fast = fast.getNext().getNext();
        } else {
          //if the node two positions ahead of the fast pointer is empty
          //move the slow pointer by one position which will be the middle node
          slow = slow.getNext();
          //set the current node pointer to the node next
          // to the slow pointer to delete the middle node
          current.setNext(slow.getNext());
          //return middle node
          return slow;
        }
      } else {
        //if there is only one non-null node after the fast pointer then the list is even therefore
        //there is no middle node
        System.out.print("Error: The list is even, no middle node to remove.");
        break;
      }
      //move the current node by one to continue to course through the linkedList
      current = current.getNext();
    }
    //return null if the list is even or empty.
    return null;
  }

  void swap(int firstIndex, int secondIndex) {

    int counter1 = 0;
    int counter2 = 0;

    DynamicNode firstNode = new DynamicNode();
    DynamicNode secondNode = new DynamicNode();

    for (DynamicNode p = head; p != null; p = p.getNext()) {
      if (counter1 == firstIndex) {
        firstNode = p;
        for (DynamicNode q = p; q != null; q = q.getNext()) {
          if (counter2 == secondIndex) {
            secondNode = q;
            break;
          } else if (q.getNext() == null) {
            break;
          } else {
            counter2++;
          }
        }
        if (counter2 < firstIndex) {
          System.out.println("Error: Not found found with second index.");
        }
        return;
      } else if (p.getNext() == null) {
        break;
      } else {
        counter1++;
      }
    }
    if (counter1 < firstIndex) {
      System.out.println("Error: First index Not found.");
    }

    for (DynamicNode r = head; r != null; r.getNext()) {
      if (firstIndex == 0) {
        for (DynamicNode t = r; t != null; t = t.getNext()) {
          if (t.getNext().equals(secondNode)) {
            head.setNext(secondNode);
            secondNode.setNext(firstNode);
            break;
          }
        }
        if (r.getNext() == null) {
          System.out.println("Error: Second index not found.");
          break;
        }
      }
      if (r.getNext().equals(head.getNext())) {

        for (DynamicNode s = r; s != null; r.getNext()) {
          if (s.getNext().equals(secondNode)) {
            r.setNext(secondNode);
            s.setNext(firstNode);
            break;
          }
        }
      }
    }
  }
/**
	* Mutator method that moves the last node in the list to the front of the list.
	*
	*/
  void moveToFront() {
  //Three pointers
    DynamicNode current = head;
    DynamicNode nextNode = current;
    DynamicNode secondNode = null;

//check if list is empty first
    if (current == null) {
      System.out.println("Error: Empty List.");
      return;
    }
    while (current != null) {
    //check if the node being placed second is set or not.
      if (current == nextNode && secondNode == null) {
      //if not set, set it to current node which is the first node on the list and move nextNode over one.
        nextNode = nextNode.getNext();
        secondNode = current;
      } else {
      //if already set, move nextNode by one.
        nextNode = nextNode.getNext();
      }
      //check if the follow node is Null.
      if (nextNode.getNext() == null){
      //if so, set the head of the list to the nextNode as it is the last node on the list.
        head = nextNode;
        //set current node to null so it is the last node in the list.
        current.setNext(null);
        //set new head pointer to what was the first node on the list.
        head.setNext(secondNode);
        return;
      }
      //traverse through the list.
      current = nextNode;
    }
  }
}