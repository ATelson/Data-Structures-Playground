package sandbox;

public class DynamicQueue {

  public DynamicNode front, rear;

  public DynamicQueue() {
    front = rear = null;
  }

  public boolean empty() {
    return (front == null);
  }

  // insert to list rear (insert last element)
  public void insert(Object x) {
    DynamicNode p = new DynamicNode(x, null);

    if (empty()) {
      front = rear = p;
    } else {
      rear.setNext(p);
    }

    rear = p;
  }

  // remove from the front of the list (delete first element)
  public Object remove() {
    if (empty()) {
      System.out.println("Queue underflow");
      System.exit(1);
    }
    DynamicNode p = front;
    Object temp = p.getInfo();
    front = p.getNext();

    if (front == null) {
      rear = null;
    }
    return temp;
  }

  public void delete(Object x) {
    if (empty()) {
      System.out.println("Error: Void deletion");
      return;
    }
    DynamicNode q = null;
    //traverse through the loop.
    for (DynamicNode p = front; p != null; p = p.getNext()) {
      //check to see if current node's information matches the information being searched
      if (p.getInfo().equals(x)) {
        if (q == null) {
          front = front.getNext();
        } else if (p.getNext() != null) {
          p = p.getNext();
          q.setNext(null);
        }
        //print information within the node
        System.out.println(p.getInfo());
        //set current node's next pointer to the node after
        p.setNext(p.getNext());
        return;
      } else if (p.getNext() == rear.getNext()) {
        System.out.println("Error: Object not found.");
      }
    }
  }

  void printQueue() {
    DynamicNode currentNode = front;
    if (currentNode == null) {
      System.out.print("Empty");
      return;
    }
    while (currentNode != null) {
      if (currentNode.getNext() == null) {
        System.out.print(currentNode.getInfo());
        return;
      } else {
        System.out.print(currentNode.getInfo() + "->");
      }
      currentNode = currentNode.getNext();
    }
  }

  boolean contains(Object element) {
    //check if list is empty.
    if (empty()) {
      return false;
    }
    //Assign new node to front of the queue.
    DynamicNode p = front;
    while (p != null) {
      //check if current node's information exists.
      if (p.getInfo().equals(element)) {
        return true;
      }
      p = p.getNext();
    }
    //otherwise it does not exist in the queue.
    return false;
  }

  static int checkSize(DynamicNode node) {
    DynamicNode p = node;
    if (p == null) {
      return 0;
    }
    return 1 + checkSize(p.getNext());
  }


}
