package hw6;

public class AVLTree extends BTreePrinter{

    Node root;

    public AVLTree() {

    }

    public AVLTree(Node root) {
        this.root = root;
        this.root.parent = null; // To make sure the root has no parent
    }

    public void printTree() {
        if (root == null) {
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(root);
        }
    }

    public Node find(int search_key) {
        return find(root, search_key);
    }

    public static Node find(Node node, int search_key) {
        if (search_key == node.key) {
            return node;
        } else if ((search_key < node.key) && (node.left != null)) {
            return find(node.left, search_key);
        } else if ((search_key > node.key) && (node.right != null)) {
            return find(node.right, search_key);
        } else {
            return null;
        }
    }

    public Node findMin() {
        return findMin(root);
    }

    public static Node findMin(Node node) {
        if (node==null || node.left == null) {
            return node;
        } else {
            return findMin(node.left);
        }
    }

    public Node findMax() {
        return findMax(root);
    }

    public static Node findMax(Node node) {
        if (node==null || node.right == null) {
            return node;
        } else {
            return findMax(node.right);
        }
    }
    
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            insert(this, root, key);
        }
    }

    public static void insert(AVLTree tree, Node node, int key) {
        if (key == node.key) {
            System.out.println("Duplicated key:" + key);
        } else if (key < node.key) {//Go left
            if (node.left == null) {
                node.left = new Node(key);
                node.left.parent = node;
            } else {
                insert(tree, node.left, key);
            }
        } else{ // Go right
            if (node.right == null) {
                node.right = new Node(key);
                node.right.parent = node;
            } else {
                insert(tree, node.right, key);
            }
        }
        //rebalance right after insertion, it has some cases that the height from left and right-subtree is balance
        //but in fact, it's not
        rebalance(tree, node);
    }

    //this method only works when it's called from bottom to top
    //some cases, for instance, left and right-subtree have the same height but unbalanced from their subtrees
    //this method won't execute
    public static void rebalance(AVLTree tree, Node node){
        if (node.isImbalance()){
            //simply go down to check if lower subtree do imbalance
            /*if (Node.height(node)>2){
                if (Node.height(node.left) > Node.height(node.right)){
                    rebalance(tree, node.left);
                }else{
                    rebalance(tree, node.right);
                }
            }*/
            if (Node.height(node.left) > Node.height(node.right)) { // Left heavy?
                if (Node.height(node.left.left) > Node.height(node.left.right)) { // Outer?
                    System.out.println("Perform SingleRotationFromLeft (Node " + node.key +")");
                    tree.singleRotateFromLeft(node);
                }else{ // Inner?
                    System.out.println("Perform DoubleRotationFromLeft (Node " + node.key +")");
                    tree.doubleRotateFromLeft(node);
                }
            }else{ // Right heavy?
                if (Node.height(node.right.right) > Node.height(node.right.left)) { //Outer?
                    System.out.println("Perform SingleRotationFromRight (Node " + node.key +")");
                    tree.singleRotateFromRight(node);
                } else { // Inner?
                    System.out.println("Perform DoubleRotationFromRight (Node " + node.key +")");
                    tree.doubleRotateFromRight(node);
                }
            }
        }
    }

    public void singleRotateFromLeft(Node y) {
        Node tmp = y.left;
        //shift y's left-subtree to be tmp's right-subtree and vise versa
        y.left = tmp.right;
        if(tmp.right != null)
            tmp.right.parent = y;

        //shift y's parent to be parent of tmp
        tmp.parent = y.parent;
        if(y != root)//if y is root, y's parent is null, so we unable to access to null's child
        {
            if(y.parent.left == y)
                y.parent.left = tmp;
            else
                y.parent.right = tmp;
            tmp.right = y;

        }
        else//so if y is root, we have to assign tmp as a root, otherwise, root will be y; no longer root of a tree
            root = tmp;
        //shift y to be tmp's right child
        y.parent = tmp;
        tmp.right = y;
    }

    public void singleRotateFromRight(Node y) {
        Node tmp = y.right;

        y.right = tmp.left;
        if(tmp.left != null)
            tmp.left.parent = y;

        tmp.parent = y.parent;
        if(y != root)
        {
            if(y.parent.left == y)
                y.parent.left = tmp;
            else
                y.parent.right = tmp;
        }
        else
            root = tmp;

        y.parent = tmp;
        tmp.left = y;
    }

    public void doubleRotateFromLeft(Node y) {
        //do single rotation 2 times
        singleRotateFromRight(y.left);
        singleRotateFromLeft(y);
    }

    public void doubleRotateFromRight(Node y) {
        singleRotateFromLeft(y.right);
        singleRotateFromRight(y);
    }

    public void delete(int key) {
        if (root == null) {
            System.out.println("Empty Tree!!!");
        } else if (root.key == key) { // Delete root node
            if ((root.left == null) && (root.right == null)) { // Case 1 (leaf node)
                root = null;
            } else if ((root.left != null) && (root.right != null)) { // Case 3 (full node)
                Node minRightSubTree = findMin(root.right);
                Node temp = new Node(minRightSubTree.key);
                replace(root, temp);
                root = temp;
                // recursively delete the node
                delete(this, root.right, minRightSubTree.key);
                //check if root is balance, delete above balance till its first right-subtree
                rebalance(this, root);
            } else if (root.left != null) { // Case 2 (single child, left child)
                root = root.left; // promote the left child to the root
            } else { // Case 2 (single child, right child)
                root = root.right; // promote the right child to the root
            }
        } else { // Delete non-root node
            delete(this, root, key);
        }
    }

    public static void delete(AVLTree tree, Node node, int key) {
        if (node==null)
        {
            //terminate recursive state
            System.out.println("Key not found!!!");
        }else if (node.key > key){ // Go left
            delete(tree, node.left, key);
        }else if (node.key < key){ // Go right
            delete(tree, node.right, key);
        }else if (node.key == key){ // Node to be deleted is found
            if ((node.left == null) && (node.right == null)) { // Case 1(leaf node)
                if (node.key < node.parent.key) {
                    node.parent.left = null; // disown the left child
                } else {
                    node.parent.right = null; // disown the right child
                }
                node.parent = null; //disconnect to its parent
            } else if ((node.left != null) && (node.right != null)) { // Case 3 (full nodes)
                Node minRightSubTree = findMin(node.right);
                Node temp = new Node(minRightSubTree.key);
                replace(node, temp);
                // recursively delete the node
                delete(tree, node.right, minRightSubTree.key);
            } else {// Case 2 (single child)
                Node childNode;
                if (node.left != null) {  // only the left child
                    childNode = node.left;
                } else { // only the right child
                    childNode = node.right;
                }
                childNode.parent = node.parent;
                if (node.parent.key > node.key) {
                    node.parent.left = childNode;
                } else {
                    node.parent.right = childNode;
                }
            }
        }
        rebalance(tree, node);
    }
    
    // Replace node1 with a new node2
    // node2 must be created using "new Node(key)" before calling this function
    // This function is optional, you do not have to use it
    public static void replace(Node node1, Node node2) {
        if ((node1.left != null) && (node1.left != node2)) {
            node2.left = node1.left;
            node1.left.parent = node2;
        }
        if ((node1.right != null) && (node1.right != node2)) {
            node2.right = node1.right;
            node1.right.parent = node2;
        }
        if ((node1.parent != null) && (node1.parent != node2)) {
            node2.parent = node1.parent;
            if (node1.parent.key > node1.key) {
                node1.parent.left = node2;
            } else {
                node1.parent.right = node2;
            }
        }
    }

    public static boolean isMergeable(Node r1, Node r2){
        //2 nodes can merge if r1's maximum value still less than r2's minimum value
        if(r1 == null || r2 == null)
            return true;
        return findMax(r1).key < findMin(r2).key;
    }
    
    public static Node mergeWithRoot(Node r1, Node r2, Node t){
        //the given node are assumed as a AVL balanced tree
        if (isMergeable(r1, r2)){
            if(Math.abs(Node.height(r1) - Node.height(r2)) <= 1)
            {
                //set t's left-subtree
                t.left = r1;
                if(r1 != null)
                    r1.parent = t;

                //set t's right-subtree
                t.right = r2;
                if(r2 != null)
                    r2.parent = t;
                return t;
            }
            else if(Node.height(r1) > Node.height(r2))
            {
                Node r = mergeWithRoot(r1.right, r2, t);
                r1.right = r;
                r.parent = r1;
                AVLTree tree = new AVLTree(r1);
                rebalance(tree, r1);
                return tree.root;
            }
            else
            {
                //r2.height > r1.height
                Node r = mergeWithRoot(r1, r2.left, t);
                r2.left = r;
                r.parent = r2;
                AVLTree tree = new AVLTree(r2);
                rebalance(tree, r2);
                return tree.root;
            }
            //make new tree and rebalance itself
            //it works if the given node is balance , if not, it won't work
        }else{
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
            return null;
        }
    }
          
    public void merge(AVLTree tree2){
        if (isMergeable(this.root, tree2.root)){
            //make temporary node to hold maximum value of this current tree
            Node newRoot = new Node(this.findMax().key);
            delete(newRoot.key); //delete this maximum key node
            root = mergeWithRoot(this.root, tree2.root, newRoot);
        }else{
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
        }
        
    }
    
    public NodeList split(int key){

        return split(root, key);
    }
    public static NodeList split(Node r, int key){
        NodeList list = new NodeList();
        if (r == null){
            //terminate recursion state
            return list;
        }else if (key < r.key){
            list = split(r.left, key);
            //merge nodelist's r2 with current node's right subtree using new node with current node key as a root
            list.r2 = mergeWithRoot(list.r2, r.right, new Node(r.key));
            return list;
        }else{ // key>=root.key
            list = split(r.right, key);
            //merge current node's left subtree with nodelist's r1 using new node with current node key as a root
            list.r1 = mergeWithRoot(r.left, list.r1, new Node(r.key));
            return list;
        }
    }
}
