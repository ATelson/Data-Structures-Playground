package sandbox;

//Create node class.
class DynamicNode {

  //info is the information that will be stored within each node of a linkedList.
  private Object info;
  //next is the pointer of a node that points to the next node within a linkedList.
  private DynamicNode next;

  //Constructor used to initialize a null node.
  DynamicNode() {
    info = null;
    next = null;
  }

  //Overloaded constructor that sets info and next using parameters.
  DynamicNode(Object info, DynamicNode next) {
    this.info = info;
    this.next = next;
  }

  /**
   * Accessor method that extracts info from node.
   */
  Object getInfo() {
    return info;
  }

  /**
   * Mutator method that sets the info within the node.
   *
   * @param info is used to set the data within the node.
   */
  void setInfo(Object info) {
    this.info = info;
  }

  /**
   * Accessor method used to extract the address to the next node within the linkedList.
   */
  DynamicNode getNext() {
    return next;
  }

  /**
   * Mutator method used to set address for the next node within the current node.
   *
   * @param next is the address to the next node.
   */
  void setNext(DynamicNode next) {
    this.next = next;
  }
}
