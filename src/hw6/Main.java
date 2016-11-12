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
 
    }
}
