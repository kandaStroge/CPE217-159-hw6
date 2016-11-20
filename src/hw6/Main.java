package hw6;

public class Main {

    public static BSTree generateTree1(){
        int[] keyList = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        BSTree tree = new BSTree();
        for (int i=0; i<keyList.length; i++)
            tree.insert(keyList[i]);
        return tree;
    }
    public static void main(String[] args) {

        //BSTree tests
        /*BSTree tree;
        tree = generateTree1();
        tree.printTree();*/
        //Test 1
        /*System.out.println("---- Test1 singleRotateFromLeft at Lv 3 ----");
        tree.singleRotateFromLeft(tree.find(6));
        tree.singleRotateFromLeft(tree.find(2));
        tree.printTree();
        System.out.println("---- Test2 singleRotateFromRight at Lv 3 ----");
        tree = generateTree1();
        tree.singleRotateFromRight(tree.find(10));
        tree.singleRotateFromRight(tree.find(14));
        tree.printTree();
        System.out.println("---- Test3 singleRotateFromLeft at Lv 2 ----");
        tree = generateTree1();
        tree.singleRotateFromLeft(tree.find(4));
        tree.printTree();
        System.out.println("---- Test4 singleRotateFromRight at Lv 2 ----");
        tree = generateTree1();
        tree.singleRotateFromRight(tree.find(12));
        tree.printTree();
        System.out.println("---- Test5 singleRotateFromLeft at Lv 1 ----");
        tree.singleRotateFromLeft(tree.find(8));
        tree.printTree();
        System.out.println("---- Test6 singleRotateFromRight at Lv 1 ----");
        tree.singleRotateFromRight(tree.find(8));
        tree.printTree();*/

        //Test 2
        /*System.out.println("---- Test7 doubleRotateFromRight at Lv 1 ----");
        tree.doubleRotateFromRight(tree.find(8));
        tree.printTree();
        System.out.println("---- Test8 doubleRotateFromLeft at Lv 1 ----");
        tree=generateTree1();
        tree.doubleRotateFromLeft(tree.find(8));
        tree.printTree();
        System.out.println("---- Test9.1 doubleRotateFromLeft at Lv 2 ----");
        tree=generateTree1();
        tree.doubleRotateFromLeft(tree.find(4));
        tree.printTree();
        System.out.println("---- Test9.2 doubleRotateFromLeft at Lv 2 ----");
        tree=generateTree1();
        tree.doubleRotateFromLeft(tree.find(12));
        tree.printTree();
        System.out.println("---- Test10.1 doubleRotateFromRight at Lv 2 ----");
        tree=generateTree1();
        tree.doubleRotateFromRight(tree.find(4));
        tree.printTree();
        System.out.println("---- Test10.2 doubleRotateFromRight at Lv 2 ----");
        tree=generateTree1();
        tree.doubleRotateFromRight(tree.find(12));
        tree.printTree();*/

        //Test 3
        /*BSTree tree1 = new BSTree();
        int[] keyList = {3, 2, 5, 1, 4, 8, 7, 6};
        for (int i=0; i<keyList.length; i++)
            tree1.insert(keyList[i]);
        BSTree tree2 = new BSTree();
        keyList = new int[]{9, 11, 10};
        for (int i=0; i<keyList.length; i++)
            tree2.insert(keyList[i]);
        tree1.printTree();
        tree2.printTree();
        tree1.merge(tree2);
        tree1.printTree();*/

        //Split tree test
        /*BSTree tree2 = new BSTree();
        tree2.insert(100);
        tree2.insert(50);
        tree2.insert(60);
        tree2.insert(70);
        tree2.insert(65);

        tree2.split(65).printTree();
        tree.split(5).printTree();*/

        //AVL tree tests
        /*AVLTree tree = new AVLTree();
        int[] keyList = {5, 2, 6, 1, 3, 4};
        for (int i=0; i<keyList.length; i++)
            tree.insert(keyList[i]);
        tree.printTree();
        keyList = new int[]{4, 3, 7, 6, 8, 5};
        tree = new AVLTree();
        for (int i=0; i<keyList.length; i++)
            tree.insert(keyList[i]);
        tree.printTree();
        tree.insert(9);
        tree.printTree();
        tree.insert(2);
        tree.insert(1);
        tree.printTree();*/

        /*AVLTree tree = new AVLTree();
        int[] keyList = {51, 30, 69, 18, 42, 63, 87, 12, 24, 36, 45, 57, 66, 81,
                93, 15, 21, 27, 33, 39, 48, 54, 60, 75, 84, 90, 96, 72, 78};
        for (int i=0; i<keyList.length; i++)
            tree.insert(keyList[i]);
        tree.insert(73); // must perform SingleRotationFromLeft(Node 81)
        tree.insert(77); // must perform DoubleRotationFromLeft(Node 87)
        tree.insert(76); // must perform SingleRotationFromLeft(Node 78)
        tree.insert(80); // must perform DoubleRotationFromRight(Node 69)
        tree.insert(74); // must perform SingleRotationFromRight(Node 72)
        tree.insert(64); // do nothing
        tree.insert(55); // must perform SingleRotationFromLeft(Node 69)
        tree.insert(70); // must perform DoubleRotationFromRight(Node 51)*/

        /*AVLTree tree = new AVLTree();
        int[] keyList = {21, 8, 34, 3, 16, 26, 42, 2, 5, 11, 18, 23, 31, 37, 47,
                1, 4, 6, 9, 13, 17, 19, 22, 24, 28, 33, 35, 40, 45, 52, 7, 10, 12,
                14, 20, 25, 27, 30, 32, 36, 38, 41, 43, 46, 49, 53, 15, 29, 39, 44,
                48, 51, 54, 50};
        for (int i=0; i<keyList.length; i++)
            tree.insert(keyList[i]);
        tree.delete(1);
        tree.delete(34);
        tree.delete(21);*/

        /*AVLTree tree1 = new AVLTree();
        int[] keyList = {4, 2, 6, 1, 3, 5, 8, 7, 9};
        for (int i=0; i<keyList.length; i++)
            tree1.insert(keyList[i]);
        System.out.println("Tree 1");
        tree1.printTree();
        AVLTree tree2 = new AVLTree();
        keyList = new int[]{12, 11};
        for (int i=0; i<keyList.length; i++)
            tree2.insert(keyList[i]);
        System.out.println("Tree 2");
        tree2.printTree();
        System.out.println("Try to merge tree1 to (the right of) tree2...");
        tree2.merge(tree1);
        tree2.printTree();System.out.println("Try to merge tree2 to (the right of) tree1...");
        tree1.merge(tree2);
        tree1.printTree();*/

        AVLTree tree1 = new AVLTree();
        int[] keyList = {1, 15, 3, 13, 5, 11, 9, 10, 8, 4, 12, 7, 2, 6, 14};
        for (int i=0; i<keyList.length; i++)
            tree1.insert(keyList[i]);
        tree1.printTree();
        NodeList list = tree1.split(7);
        (new AVLTree(list.r1)).printTree();
        (new AVLTree(list.r2)).printTree();
    }
}
