package hw6;

public class SplayTree extends BTreePrinter{
    Node root;
    
    public SplayTree(){
        
    }
    
    public SplayTree(Node root){
        
        if (root.parent!=null){ // To make sure the root has no parent
            if (root.key < root.parent.key){
                root.parent.left = null;
            }else{
                root.parent.right = null;
            }
            root.parent = null;
        }
        this.root = root;
    }
    
    public void printTree(){
        super.printTree(root);
    }
    
    public Node find(int search_key) {
        splay(findWithoutSplaying(search_key));
        return root; // fix this
    }

    // This function is already complete, no need to modify
    public Node findWithoutSplaying(int search_key) {
        return find(root, search_key);
    }
    
    // This function is already complete, no need to modify
    private static Node find(Node node, int search_key) {
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
    
    // This function is already complete, no need to modify
    private Node findMin() {
        return findMin(root);
    }

    // This function is already complete, no need to modify
    private static Node findMin(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return findMin(node.left);
        }
    }
    
    // This function is already complete, no need to modify
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            insert(this, root, key);
        }
    }

    // Fix this function to have splaying feature
    private static void insert(SplayTree tree, Node node, int key) {
        if (key == node.key) {
            System.out.println("Duplicated key:" + key);
        } else if (key < node.key) {//Go left
            if (node.left == null) {
                node.left = new Node(key);
                node.left.parent = node;
                tree.splay(node.left);
            } else {
                insert(tree, node.left, key);
            }
        } else{ // Go right
            if (node.right == null) {
                node.right = new Node(key);
                node.right.parent = node;
                tree.splay(node.right);
            } else {
                insert(tree, node.right, key);
            }
        }
    }
    
    
    public void delete(int key) {
        // To delete a key in a splay tree, do the following steps
        // 1. splay node with the key to the root of tree1
        find(key);
        // 2. create another tree using the node of the right-subtree (tree2)
        SplayTree tree = new SplayTree(root.right);
        // 3. Find min of the right-subtree and splay to the root
        tree.splay(tree.findMin());
        // 4. Take left-subtree of the tree1 and hang as the left subtree of the tree2
        tree.root.left = this.root.left;
        // 5. Reassign a new root (root of the tree2)
        this.root = tree.root;
    }
    
    // Use this function to call zigzig or zigzag
    public void splay(Node node){
        if (node!=null && node != root){
            if (node.parent == root){
                // Do something (just add one line of code)
                zig(node);
            }else{
                if (node.key < node.parent.key){
                    if (node.parent.key < node.parent.parent.key){
                        // Left outer case
                        // Do something (just add one line of code)
                        //ZigZig
                        zigzig(node);
                    }else{
                        // Left inner case
                        // Do something (just add one line of code)
                        zigzag(node);
                    }
                }else{
                    if (node.parent.key > node.parent.parent.key){
                        // Right outer case
                        // Do something (just add one line of code)
                        zigzig(node);
                    }else{
                        // Do something (just add one line of code)
                        zigzag(node); // do ziza
                    }
                }
                // Do something (just add one line of code)
                // recursive to root
                splay(node);
            }
        }
    }
    
    // Use this function to call zig
    public void zigzig(Node node){ // Move two nodes up along the Outer path
        // Do something
        zig(node.parent);
        zig(node);
    }
    
    // Use this funciton to call zig
    public void zigzag(Node node){ // Move two nodes up along the Inner path
        // Do something
        zig(node);
        zig(node);
    }
    
    // This function should be called by zigzig or zigzag
    public void zig(Node node){// Move up one step
        if (node.parent == null){
            System.out.println("Cannot perform Zig operation on the root node");
        }else if (node.parent == root){// If the node is a child of the root
            if (node.key<node.parent.key){// Zig from left
                node.parent.left = node.right;
                if(node.parent.left !=null) {
                    node.parent.left.parent = node.parent;
                }
                node.right = node.parent;
                node.right.parent = node;
                root = node;
                // Do something
            }else{ // Zig from right
                // Do something
                node.parent.right = node.left;
                if(node.parent.right != null) {
                    node.parent.right.parent = node.parent;
                }
                node.left = node.parent;
                node.left.parent = node;
                root = node;

            }
        }else{// if the node is not a child of the root
            if (node.key<node.parent.key){// Zig from left
                // Do something
                node.parent.left = node.right;
                if (node.parent.left != null){
                    node.parent.left.parent = node.parent;
                }
                node.right = node.parent;
                node.parent = node.right.parent;
                if (node.right.key < node.right.parent.key){
                    node.right.parent.left = node;
                }else {
                    node.right.parent.right = node;
                }
                node.right.parent = node;
            }else{
                // Do something
                node.parent.right = node.left;
                if (node.parent.right != null){
                    node.parent.right.parent = node.parent;
                }
                node.left = node.parent;
                node.parent = node.left.parent;
                if (node.left.key < node.left.parent.key) {
                    node.left.parent.left = node;
                }else {
                    node.left.parent.right = node;
                }
                node.left.parent = node;
            }
        }
    }
}
