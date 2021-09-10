package frc.robot;

public class Node {

    int x;
    int y;
    double hValue;
    int gValue;
    double fValue;
    Node parent;


    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

}