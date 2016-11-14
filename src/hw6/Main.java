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

        BSTree tree;
        tree = generateTree1();
        tree.printTree();
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
    }
}
