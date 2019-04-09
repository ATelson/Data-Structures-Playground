package sandbox;

public class TreeNode {

  public int info;
  public TreeNode left, right;

  public TreeNode(int info) {
    this.info = info;
    left = right = null;
  }
/*
  public void insert(int value) {
    if (value <= info) {
      if (left == null) {
        left = new TreeNode(value);
      } else {
        left.insert(value);
      }
    } else {
      if (right == null) {
        right = new TreeNode(value);
      } else {
        right.insert(value);
      }
    }
  }*/
}
