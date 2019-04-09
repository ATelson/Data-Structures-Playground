package sandbox;

public class Main {

  public static void main(String[] args) {
    // write your code here
    int[] input = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    int[] p = {1};
    SortedTree tree = new SortedTree();
    tree.root = SortedTree.buildTree(input, 0, input.length - 1);
    tree.insert(10);
    System.out.println(SortedTree.depth(tree.root));
  }
}
