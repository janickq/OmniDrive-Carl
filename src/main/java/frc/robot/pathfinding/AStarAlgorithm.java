package frc.robot.pathfinding;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import frc.robot.pathfinding.element.Network;
import frc.robot.pathfinding.element.Node;

public class AStarAlgorithm {

    private Network network;
    private ArrayList<Node> path;

    private Node start;
    private Node end;

    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private PropertyChangeSupport support;
    
    public AStarAlgorithm(Network network) {
        this.network = network;
        support = new PropertyChangeSupport(this);
    }
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
    public void solve() {

        if (start == null && end == null) {
            return;
        }

        if (start.equals(end)) {
            this.path = new ArrayList<>();
            return;
        }

        this.path = new ArrayList<>();

        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();

        this.openList.add(start);

        while (!openList.isEmpty()) {
            Node current = getLowestF();

            if (current.equals(end)) {
                retracePath(current);
                break;
            }

            openList.remove(current);
            closedList.add(current);

            for (Node n : current.getNeighbours()) {

                if (closedList.contains(n) || n.getObsValue()==Node.maxObsValue) {
                    continue;
                }

                double extraCost = 1;
                double dir = -1;
                dir = current.dirTo(n);
                if (dir != current.getDir()) extraCost = 2;
                double tempScore = current.getCost() + current.distanceTo(n) * extraCost + n.getObsValue();

                if (openList.contains(n)) {
                    if (tempScore == n.getCost()) {
                        //Same cost
                        //Choose the one with lower direction difference
                        double a = n.dirTo(end);
                        double diff1 = Math.abs(Math.sin(a-dir));
                        double diff2 = Math.abs(Math.sin(a-n.getDir()));
                        if (diff1 < diff2) {
                            n.setCost(tempScore);
                            n.setParent(current);
                            n.setDir(dir);
                        }
                    }
                    else if (tempScore < n.getCost()) {
                        n.setCost(tempScore);
                        n.setParent(current);
                        n.setDir(dir);
                    }
                } else {
                    n.setCost(tempScore);
                    openList.add(n);
                    n.setDir(dir);
                    n.setParent(current);
                }

                n.setHeuristic(n.heuristic(end));
                n.setFunction(n.getCost() + n.getHeuristic());

            }

        }

        updateUI();
    }

    public void reset() {
        this.start = null;
        this.end = null;
        this.path = null;
        this.openList = null;
        this.closedList = null;
        //Don't reset obstacles
        //for (Node n : network.getNodes()) {
        //    n.setObsValue(0.0);
        //}
    }

    private void retracePath(Node current) {
        Node temp = current;
        this.path.add(current);
        
        while (temp.getParent() != null) {
            this.path.add(temp.getParent());
            temp = temp.getParent();
        }
        
        this.path.add(start);
    }

    private Node getLowestF() {
        Node lowest = openList.get(0);
        for (Node n : openList) {
            if (n.getFunction()< lowest.getFunction()) {
                lowest = n;
            }
        }
        return lowest;
    }

    public void updateUI() {
        support.firePropertyChange("news", 0, this);
        
    }

    public Network getNetwork() {
        return network;
    }

    public ArrayList<Node> getPath() {
        return path;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

}
