package sandbox;

import java.util.Stack;

public class SortedTree {

  TreeNode root; // the root of the treepublic

  SortedTree() {
    root = null;
  }

  boolean insert(int newInt) {
    TreeNode p = root, q = null;
    while (p != null) {
      // search succeeds, no need to insert
      if (p.info == newInt) {
        System.out.println(newInt + " already exists in the tree.");
        return false;
      }
      // q, the parent, is now p
      q = p;
      // left < parent < right
      if (newInt < p.info) {
        p = p.left;
      } else {
        p = p.right;
      }
    }
    // once out of the loop, the search has failed//create and insert node
    TreeNode v = new TreeNode(newInt);
    if (q == null) {
      System.out.println("Insert at root " + v.info);
      root = v;
      // new tree with v as root
    } else if (newInt < q.info) {
      System.out.println("Insert " + v.info + " left of " + q.info);
      q.left = v;
    } else {
      System.out.println("Insert " + v.info + " right of " + q.info);
      q.right = v;
    }
    return true;
  }


  void traverse() {
    Stack s = new Stack();
    TreeNode p = root;
    do {
      while (p != null) {
        s.push(p);
        p = p.left;
      }
      if (!s.empty()) {
        p = (TreeNode) s.pop();
        System.out.println(p.info);
        p = p.right;
      }
    } while (!s.empty() || p != null);
  }

  boolean isStrictlyBinary(TreeNode root) {
    if (root == null) {
      return true;
    } else if (root.left == null && root.right == null) {
      return true;
    } else if (root.left != null && root.right != null) {
      boolean leftStrict = isStrictlyBinary(root.left);
      boolean rightStrict = isStrictlyBinary(root.right);
      return leftStrict && rightStrict;
    } else {
      return false;
    }
  }

  static void displayLeafDepths(TreeNode root, int depth) {
    if (root == null) {
      return;
    } else if (root.left == null && root.right == null) {
      System.out.println(depth);
    } else {
      displayLeafDepths(root.left, depth + 1);
      displayLeafDepths(root.right, depth + 1);
    }
  }

  static TreeNode buildTree(int[] inputs, int start, int end) {
    if (start > end) {
      return null;
    }

    int mid = start + (end - start) / 2;
    SortedTree t = new SortedTree();

    TreeNode nd = new TreeNode(inputs[mid]);
    t.root = nd;

    t.root.left = buildTree(inputs, start, mid - 1);
    t.root.right = buildTree(inputs, mid + 1, end);

    return t.root;
  }

  int length() {
    int length = 0;
    Stack s = new Stack();
    TreeNode p = root;
    do {
      while (p != null) {
        length++;
        s.push(p);
        p = p.left;
      }
      if (!s.empty()) {
        p = (TreeNode) s.pop();
        p = p.right;
      }
    } while (!s.empty() || p != null);
    return length;
  }

  void preTrav(TreeNode tree) {
    if (tree != null) {
      System.out.println(
          tree.info);  // visit the root5.
      preTrav(tree.left);
      // traverse the left subtree6.
      preTrav(tree.right);
      //traverse the right subtree7.
    }
  }

  static void lessThanK(TreeNode root, int k) {
    if (root == null || (root.right == null && root.left == null && root.info > k)) {
      return;
    }
    if (root.info < k) {
      System.out.println(root.info);
    }

    lessThanK(root.left, k);
    lessThanK(root.right, k);
  }

  static int depth(TreeNode root) {
    if (root == null) {
      return -1;
    }

    int ldepth = depth(root.left);
    int rdepth = depth(root.right);

    if (ldepth > rdepth) {
      return (ldepth + 1);
    } else {
      return (rdepth + 1);
    }
  }

  void delete(int number){
      if (root == null){
        return;
      }

  }
}
