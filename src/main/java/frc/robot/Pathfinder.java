package frc.robot;

import java.awt.*;
import java.util.*;

// implementation derived from https://github.com/Suwadith/A-Star-Shortest-Pathfinding-Algorithm-Square-Grid-Java



public class Pathfinder {
    public Node[][] cell;
    public ArrayList<Node> pathList = new ArrayList<>();
    public ArrayList<Node> closedList = new ArrayList<>();
    public boolean additionalPath = false;

    public void generateHValue(boolean matrix[][], int Ai, int Aj, int Bi, int Bj, int n, int v, int d, boolean additionalPath, int h) {

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix.length; x++) {
                //Creating a new Node object for each and every Cell of the Grid (Matrix)
                cell[y][x] = new Node(y, x);
                //Checks whether a cell is Blocked or Not by checking the boolean value
                if (matrix[y][x]) {
                    if (h == 1) {
                        //Assigning the Chebyshev Heuristic value
                        if (Math.abs(y - Bi) > Math.abs(x - Bj)) {
                            cell[y][x].hValue = Math.abs(y - Bi);
                        } else {
                            cell[y][x].hValue = Math.abs(x - Bj);
                        }
                    } else if (h == 2) {
                        //Assigning the Euclidean Heuristic value
                        cell[y][x].hValue = Math.sqrt(Math.pow(y - Bi, 2) + Math.pow(x - Bj, 2));
                    } else if (h == 3) {
                        //Assigning the Manhattan Heuristic value by calculating the absolute length (x+y) from the ending point to the starting point
                        cell[y][x].hValue = Math.abs(y - Bi) + Math.abs(x - Bj);
                    }
                } else {
                    //If the boolean value is false, then assigning -1 instead of the absolute length
                    cell[y][x].hValue = -1;
                }
            }
        }
        generatePath(cell, Ai, Aj, Bi, Bj, n, v, d, additionalPath);
    }
    
    public void generatePath(Node hValue[][], int Ai, int Aj, int Bi, int Bj, int n, int v, int d, boolean additionalPath) {

        //Creation of a PriorityQueue and the declaration of the Comparator
        PriorityQueue<Node> openList = new PriorityQueue<>(11, new Comparator() {
            @Override
            //Compares 2 Node objects stored in the PriorityQueue and Reorders the Queue according to the object which has the lowest fValue
            public int compare(Object cell1, Object cell2) {
                return ((Node) cell1).fValue < ((Node) cell2).fValue ? -1 :
                        ((Node) cell1).fValue > ((Node) cell2).fValue ? 1 : 0;
            }
        });

        //Adds the Starting cell inside the openList
        openList.add(cell[Ai][Aj]);

        //Executes the rest if there are objects left inside the PriorityQueue
        while (true) {

            //Gets and removes the objects that's stored on the top of the openList and saves it inside node
            Node node = openList.poll();

            //Checks if whether node is empty and f it is then breaks the while loop
            if (node == null) {
                break;
            }

            //Checks if whether the node returned is having the same node object values of the ending point
            //If it des then stores that inside the closedList and breaks the while loop
            if (node == cell[Bi][Bj]) {
                closedList.add(node);
                break;
            }

            closedList.add(node);

            //Left Cell
            try {
                if (cell[node.x][node.y - 1].hValue != -1
                        && !openList.contains(cell[node.x][node.y - 1])
                        && !closedList.contains(cell[node.x][node.y - 1])) {
                    double tCost = node.fValue + v;
                    cell[node.x][node.y - 1].gValue = v;
                    double cost = cell[node.x][node.y - 1].hValue + tCost;
                    if (cell[node.x][node.y - 1].fValue > cost || !openList.contains(cell[node.x][node.y - 1]))
                        cell[node.x][node.y - 1].fValue = cost;

                    openList.add(cell[node.x][node.y - 1]);
                    cell[node.x][node.y - 1].parent = node;
                }
            } catch (IndexOutOfBoundsException e) {
            }

            //Right Cell
            try {
                if (cell[node.x][node.y + 1].hValue != -1
                        && !openList.contains(cell[node.x][node.y + 1])
                        && !closedList.contains(cell[node.x][node.y + 1])) {
                    double tCost = node.fValue + v;
                    cell[node.x][node.y + 1].gValue = v;
                    double cost = cell[node.x][node.y + 1].hValue + tCost;
                    if (cell[node.x][node.y + 1].fValue > cost || !openList.contains(cell[node.x][node.y + 1]))
                        cell[node.x][node.y + 1].fValue = cost;

                    openList.add(cell[node.x][node.y + 1]);
                    cell[node.x][node.y + 1].parent = node;
                }
            } catch (IndexOutOfBoundsException e) {
            }

            //Bottom Cell
            try {
                if (cell[node.x + 1][node.y].hValue != -1
                        && !openList.contains(cell[node.x + 1][node.y])
                        && !closedList.contains(cell[node.x + 1][node.y])) {
                    double tCost = node.fValue + v;
                    cell[node.x + 1][node.y].gValue = v;
                    double cost = cell[node.x + 1][node.y].hValue + tCost;
                    if (cell[node.x + 1][node.y].fValue > cost || !openList.contains(cell[node.x + 1][node.y]))
                        cell[node.x + 1][node.y].fValue = cost;

                    openList.add(cell[node.x + 1][node.y]);
                    cell[node.x + 1][node.y].parent = node;
                }
            } catch (IndexOutOfBoundsException e) {
            }

            //Top Cell
            try {
                if (cell[node.x - 1][node.y].hValue != -1
                        && !openList.contains(cell[node.x - 1][node.y])
                        && !closedList.contains(cell[node.x - 1][node.y])) {
                    double tCost = node.fValue + v;
                    cell[node.x - 1][node.y].gValue = v;
                    double cost = cell[node.x - 1][node.y].hValue + tCost;
                    if (cell[node.x - 1][node.y].fValue > cost || !openList.contains(cell[node.x - 1][node.y]))
                        cell[node.x - 1][node.y].fValue = cost;

                    openList.add(cell[node.x - 1][node.y]);
                    cell[node.x - 1][node.y].parent = node;
                }
            } catch (IndexOutOfBoundsException e) {
            }

            if (additionalPath) {

                //TopLeft Cell
                try {
                    if (cell[node.x - 1][node.y - 1].hValue != -1
                            && !openList.contains(cell[node.x - 1][node.y - 1])
                            && !closedList.contains(cell[node.x - 1][node.y - 1])) {
                        double tCost = node.fValue + d;
                        cell[node.x - 1][node.y - 1].gValue = d;
                        double cost = cell[node.x - 1][node.y - 1].hValue + tCost;
                        if (cell[node.x - 1][node.y - 1].fValue > cost || !openList.contains(cell[node.x - 1][node.y - 1]))
                            cell[node.x - 1][node.y - 1].fValue = cost;

                        openList.add(cell[node.x - 1][node.y - 1]);
                        cell[node.x - 1][node.y - 1].parent = node;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                //TopRight Cell
                try {
                    if (cell[node.x - 1][node.y + 1].hValue != -1
                            && !openList.contains(cell[node.x - 1][node.y + 1])
                            && !closedList.contains(cell[node.x - 1][node.y + 1])) {
                        double tCost = node.fValue + d;
                        cell[node.x - 1][node.y + 1].gValue = d;
                        double cost = cell[node.x - 1][node.y + 1].hValue + tCost;
                        if (cell[node.x - 1][node.y + 1].fValue > cost || !openList.contains(cell[node.x - 1][node.y + 1]))
                            cell[node.x - 1][node.y + 1].fValue = cost;

                        openList.add(cell[node.x - 1][node.y + 1]);
                        cell[node.x - 1][node.y + 1].parent = node;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                //BottomLeft Cell
                try {
                    if (cell[node.x + 1][node.y - 1].hValue != -1
                            && !openList.contains(cell[node.x + 1][node.y - 1])
                            && !closedList.contains(cell[node.x + 1][node.y - 1])) {
                        double tCost = node.fValue + d;
                        cell[node.x + 1][node.y - 1].gValue = d;
                        double cost = cell[node.x + 1][node.y - 1].hValue + tCost;
                        if (cell[node.x + 1][node.y - 1].fValue > cost || !openList.contains(cell[node.x + 1][node.y - 1]))
                            cell[node.x + 1][node.y - 1].fValue = cost;

                        openList.add(cell[node.x + 1][node.y - 1]);
                        cell[node.x + 1][node.y - 1].parent = node;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                //BottomRight Cell
                try {
                    if (cell[node.x + 1][node.y + 1].hValue != -1
                            && !openList.contains(cell[node.x + 1][node.y + 1])
                            && !closedList.contains(cell[node.x + 1][node.y + 1])) {
                        double tCost = node.fValue + d;
                        cell[node.x + 1][node.y + 1].gValue = d;
                        double cost = cell[node.x + 1][node.y + 1].hValue + tCost;
                        if (cell[node.x + 1][node.y + 1].fValue > cost || !openList.contains(cell[node.x + 1][node.y + 1]))
                            cell[node.x + 1][node.y + 1].fValue = cost;

                        openList.add(cell[node.x + 1][node.y + 1]);
                        cell[node.x + 1][node.y + 1].parent = node;
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }

        /*for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(cell[i][j].fValue + "    ");
            }
            System.out.println();
        }*/

        //Assigns the last Object in the closedList to the endNode variable
        Node endNode = closedList.get(closedList.size() - 1);

        //Checks if whether the endNode variable currently has a parent Node. if it doesn't then stops moving forward.
        //Stores each parent Node to the PathList so it is easier to trace back the final path
        while (endNode.parent != null) {
            Node currentNode = endNode;
            pathList.add(currentNode);
            endNode = endNode.parent;
        }

        pathList.add(cell[Ai][Aj]);
        //Clears the openList
        openList.clear();

        System.out.println();

    }
}