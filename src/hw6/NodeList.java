package hw6;

public class NodeList extends BTreePrinter{
    public Node r1;
    public Node r2;
    NodeList(){

    }

    public void printTree() {
        System.out.println("R1");
        if (r1 == null) {
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(r1);
        }

        System.out.println("R2");
        if (r2 == null) {
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(r2);
        }
    }
}
