package CompanyAssessments;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * This is a 4-hour coding assessment I received from a company in early August.
 *
 * @author Ruby Chauhan
 * @since 2020-08-08
 *
 * "Prune Tree"
 *
 * Problem: Given a tree (n-ary) and a set of nodes, prune the tree such that:
 *   1) It includes all the nodes that are in the set.
 *   2) Any node that is in the final tree also has its immediate sibling in the result.
 *   3) The resulting tree contains the minimal number of nodes.
 *
 * Your solution should include a way to create different trees easily to test out your code.
 * Describe your thoughts or write code for the following variations:
 *   Variation 1: Tree includes duplicate nodes. Keep node closest to root if it is duplicated.
 *   Fine to include duplicate nodes if needed to satisfy the original constraints.
 *
 *   Variation 2: Tree includes duplicate nodes. Find a solution that minimizes the total
 *   number of nodes.
 *
 * Expected behavior:
 * Tree:         A                           A
 *              / \                         / \
 *             B   C     Set = B   --->    B   C
 *            / \   |
 *           D  E   F
 *
 * Tree:         A                           A
 *              / \                         / \
 *             B   C     Set = E   --->    B   C
 *            / \   |                     / \
 *           D  E   F                    D   E
 *
 *  Tree:         A                              A
 *               / \                            / \
 *              B   C     Set = B, F   --->    B   C
 *             / \   |                              |
 *            D  E   F                              F
 *
 * NOTE: I will be writing solutions for Variation 2.
 *
 * Post-Assessment Note: As described in the documentation at Node.prune(),
 * this solution does not produce the desired behavior and simply removes all
 * children of the Node in SET with the highest depth.
 * */

public class Node {
    // Value at this node
    String value;
    // Collection containing this node's children
    ArrayList<Node> children;
    // Max number of children per node
    int n;

    /**
     * Node Constructor
     * @param value value at this node
     * @param n Max number of children per node
     */
    public Node(String value, int n) {
        this.value = value;
        this.n = n;
        // Initialize capacity of children to n
        this.children = new ArrayList<>(n);
    }

    public Node(Node n) {
        this.value = n.value;
        this.n = n.n;
        this.children = n.children;
    }

    /**
     * Add child nodes.
     * Notes: Need to keep track of overflowing children collections.
     * Arraylist.add(Object o) returns true when successfully
     * appending o to end of list
     * @param value Value of the child to be added
     * @return boolean indicating successful node insert
     */
    public boolean addChild(String value) {
        Node child = new Node(value, this.n);
        return (children.size() < n) && children.add(child);
    }

    /**
     * Get children of this node.
     * Notes: This method returns only a copy of
     * the children of this node!
     * @return ArrayList containing children
     */
    public ArrayList<Node> getChildren() {
        return new ArrayList<>(children);
    }

    /**
     * Return the child Node at index i.
     * @param i index of desired child Node
     * @return Node
     */
    public Node getChild(int i) {
        return (i < children.size()) ? children.get(i) : null;
    }

    /**
     * A function which prunes all leaves that are below the height
     * of the highest node (i.e. lowest depth) in the SET
     * NOTE: This is not what is asked in the spec because there may be
     * other nodes in SET with a higher depth that will get cut off due
     * to the naive implementation of the pruning algorithm.
     * @param root root of an N-ary tree to prune
     * @param set set of nodes to include in tree
     */
    public void prune(Node root, ArrayList<Node> set) {
        // Prune all leaves below the nodes specified in SET
        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            // Remove Node from queue and check if it has a child
            // present in the set
            Node n = q.poll();
            for (int i = 0; i < n.children.size(); i++) {
                // If the node has a child present in the set, we need to add
                // all siblings of this child to the set to prune the tree as
                // per the spec
                if (set.contains(n.children.get(i))) {
                    set.addAll(n.children);
                    // Pruning
                    n.children.get(i).children.clear();
                }
                // Add the children of the polled item to the queue
                q.add(n.children.get(i));
            }

        }
    }

    public void printTree() {
        this.printHelper(this, 0);
    }

    /**
     * DFS Traversal of the N-ary tree is used to print
     * the tree graphically
     * @param n root node where printing should begin
     * @param depth depth of the current node
     */
    private void printHelper(Node n, int depth) {
        // NOTE: pre-incrementation is important here
        // Depths of current node are printed here
        for (int i = 0; i < depth; ++i) {
            // 5 spaces used
            System.out.print("     ");
        }

        System.out.println(n.value);

        // Recurse through children
        for (Node child : n.getChildren()) {
            child.printHelper(child, depth + 1);
        }
    }
}

class testTree {
    public static void main(String[] args) {
        // Create the example tree given in the problem prompt image
        // Root, depth = 0
        Node root = new Node("A", 2);

        // Root's left child, depth = 1
        root.addChild("B");
        // Depth = 2 children
        root.getChild(0).addChild("D");
        root.getChild(0).addChild("E");
        // Root's right child, depth = 2
        root.addChild("C");
        // Depth = 2 child
        root.getChild(1).addChild("F");

        System.out.println("Original Tree: ");
        root.printTree();

        System.out.println("Test Case 1: Set = B");
        ArrayList<Node> set = new ArrayList<>();
        set.add(root.getChild(0));
        root.prune(root, set);
        System.out.println("After pruning: ");
        root.printTree();

        // Reset the tree:
        root.children.clear();
        // Root's left child, depth = 1
        root.addChild("B");
        // Depth = 2 children
        root.getChild(0).addChild("D");
        root.getChild(0).addChild("E");
        // Root's right child, depth = 2
        root.addChild("C");
        // Depth = 2 child
        root.getChild(1).addChild("F");

        // The pruning algorithm fails this test case because we
        // also need to keep track of the siblings of the parent
        // of each node in SET, if those siblings of the parent
        // have children that are not in SET, then those children
        // need to be removed.
        // For this test case, F is not removed from the right subtree
        // due to the reasons described above.
        System.out.println("Test Case 2: Set = E");
        set.clear();
        set.add(root.getChild(0).getChild(1));
        root.prune(root, set);
        System.out.println("After pruning: ");
        root.printTree();

        // Reset the tree:
        root.children.clear();
        // Root's left child, depth = 1
        root.addChild("B");
        // Depth = 2 children
        root.getChild(0).addChild("D");
        root.getChild(0).addChild("E");
        // Root's right child, depth = 2
        root.addChild("C");
        // Depth = 2 child
        root.getChild(1).addChild("F");

        // This test case also fails because the algorithm
        // naively removes all children of a node in the SET
        // and the children of its siblings, so first we happen
        // upon node B, which is in the set, add its siblings to SET
        // and then clear the children of all Nodes in SET.
        System.out.println("Test Case 3: Set = B, F");
        set.clear();
        set.add(root.getChild(0));
        set.add(root.getChild(1).getChild(0));
        root.prune(root, set);
        System.out.println("After pruning: ");
        root.printTree();

    }
}
