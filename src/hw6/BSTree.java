package hw6;

public class BSTree extends BTreePrinter {

    Node root;

    public BSTree() {

    }

    public BSTree(Node root) {
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
        if (node.left == null) {
            return node;
        } else {
            return findMin(node.left);
        }
    }

    public Node findMax() {
        return findMax(root);
    }

    public static Node findMax(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return findMax(node.right);
        }
    }
    
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            insert(root, key);
        }
    }

    public static void insert(Node node, int key) {
        if (key == node.key) {
            System.out.println("Duplicated key!!!");
        } else if (key < node.key) {
            if (node.left == null) {
                node.left = new Node(key);
                node.left.parent = node;
            } else {
                insert(node.left, key);
            }
        } else if (node.right == null) {
            node.right = new Node(key);
            node.right.parent = node;
        } else {
            insert(node.right, key);
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

        if(y != root)
        {
            tmp.parent = y.parent;
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

    // You should have "root node deletion" in this function
    public void delete(int key) {
        if (root == null) {
            System.out.println("Empty Tree!!!");
        } else if (root.key == key) { // Delete root node
            if ((root.left == null) && (root.right == null)) { // Case 1 (leaf node)
                root = null;
            } else if ((root.left != null) && (root.right != null)) { // Case 3 (full node)
                Node minRightSubTree = findMin(root.right); // find min from the right subtree
                Node temp = new Node(minRightSubTree.key);
                replace(root, temp);
                root = temp;
                // recursively delete the node from the right subtree
                delete(root.right, minRightSubTree.key);
            } else if (root.left != null) { // Case 2 (single child, left child)
                root = root.left; // promote the left child to the root
            } else { // Case 2 (single child, right child)
                root = root.right; // promote the right child to the root
            }
        } else { // Delete non-root node
            delete(root, key);
        }
    }

    // this function should delete only non-root node
    public static void delete(Node node, int key) {
        if (node==null)
        {
            System.out.println("Key not found!!!");
        }else if (node.key > key){
            delete(node.left, key);
        }else if (node.key < key){
            delete(node.right, key);
        }else if (node.key == key){ // Node to be deleted is found
            if ((node.left == null) && (node.right == null)) { // Case 1(leaf node)
                if (node.key < node.parent.key) {
                    node.parent.left = null; // disown the left child
                } else {
                    node.parent.right = null; // disown the right child
                }
            } else if ((node.left != null) && (node.right != null)) { // Case 3 (full nodes)
                Node minRightSubTree = findMin(node.right); // find min from the right subtree
                Node temp = new Node(minRightSubTree.key);
                replace(node, temp);
                // recursively delete the node start from the right subtree
                delete(node.right, minRightSubTree.key);
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
        return findMax(r1).key < findMin(r2).key;
    }
    
    public static Node mergeWithRoot(Node r1, Node r2, Node t){
        if (isMergeable(r1, r2)) {
            //set t'ss left-subtree
            t.left = r1;
            r1.parent = t;
            //set t's right-subtree
            t.right = r2;
            r2.parent = t;
            return t;
        } else {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
            return null;
        }
    }
          
    public void merge(BSTree tree2){
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
            return list;
        }else if (key < r.key){
            list = split(r.left, key);
            list.r2 = mergeWithRoot(list.r2, r.right, r);
            return list;
        }else{ // key>=root.key
            list = split(r.right, key);
            list.r1 = mergeWithRoot(r.left, list.r1, r);
            return list;
        }
    }
}
