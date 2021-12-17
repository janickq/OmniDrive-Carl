package frc.robot.Astar;

//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class AStarAlgorithm {

    private Network network;
    private ArrayList<Node> m_path;          //List of connecting cells that form the path
    private ArrayList<Node> m_pathWayPoints; //Waypoints are position where path change direction

    private Node start;
    private Node end;

    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    //private PropertyChangeSupport support;

    public AStarAlgorithm(Network network) {
        this.network = network;
        //support = new PropertyChangeSupport(this);
    }

    // public void addPropertyChangeListener(PropertyChangeListener pcl) {
    //     //support.addPropertyChangeListener(pcl);
    // }

    // public void removePropertyChangeListener(PropertyChangeListener pcl) {
    //     //support.removePropertyChangeListener(pcl);
    // }

    public void solve() {

        if (start == null && end == null) {
            return;
        }

        if (start.equals(end)) {
            m_path = new ArrayList<>();
            return;
        }

        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();

        start.setParent(null);
        end.setParent(null);
        this.openList.add(start);

        while (!openList.isEmpty()) {
            Node current = getLowestF();

            if (current.equals(end)) {
                retracePath(current);
                break;
            }

            openList.remove(current);
            closedList.add(current);
            // System.out.println("333");
            // System.out.println(current);
            // System.out.println(current.getNeighbours());

            ArrayList<Node> nodes = current.getNeighbours();
            if (nodes != null) {
                for (Node n : current.getNeighbours()) {

                    if (closedList.contains(n) || n.getObsValue() == Node.maxObsValue) {
                        continue;
                    }

                    double extraCost = 1;
                    double dir = -1;
                    dir = current.dirTo(n);
                    if (dir != current.getDir())
                        extraCost = 2;
                    double tempScore = current.getCost() + current.distanceTo(n) * extraCost + n.getObsValue();

                    if (openList.contains(n)) {
                        if (tempScore == n.getCost()) {
                            // Same cost
                            // Choose the one with lower direction difference
                            double a = n.dirTo(end);
                            double diff1 = Math.abs(Math.sin(a - dir));
                            double diff2 = Math.abs(Math.sin(a - n.getDir()));
                            if (diff1 < diff2) {
                                n.setCost(tempScore);
                                n.setParent(current);
                                n.setDir(dir);
                            }
                        } else if (tempScore < n.getCost()) {
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
        }
        //updateUI();
    }

    public void reset() {
        this.start = null;
        this.end = null;
        m_path = null;
        m_pathWayPoints = null;
        // this.openList = null;
        // this.closedList = null;
        // Don't reset obstacles
        // for (Node n : network.getNodes()) {
        // n.setObsValue(0.0);
        // }
    }

    private void retracePath(Node current) {
        Node temp = current;

        m_path = new ArrayList<>();
        m_pathWayPoints = new ArrayList<>();

        //retrace the point from end to start.
        //current should be end point.
        m_path.add(current);

        while (temp.getParent() != null) {
            m_path.add(temp.getParent());
            temp = temp.getParent();
        }

        //Generate the waypoints from the path points.
        //Waypoints are points where path direction change
        //This make the path more compact.
        boolean first = true;
        double curDir = 0;
        for (Node n : m_path) {
            Tile t = (Tile) n;
            //System.out.printf("(%d, %d, %f)\n", t.getX(), t.getY(), t.getDir());
            if (first) {
                System.out.printf("(%d, %d, %f)\n", t.getX(), t.getY(), t.getDir());
                m_pathWayPoints.add(n);
                curDir = n.getDir();
                first = false;
            } else {
                if (t.getDir() != curDir) {
                    System.out.printf("w(%d, %d, %f)\n", t.getX(), t.getY(), t.getDir());
                    m_pathWayPoints.add(n);
                    curDir = t.getDir();
                }

            }
        }
        //If end point is not in waypoints, add it in.
        if (m_pathWayPoints.get(m_pathWayPoints.size()-1) != m_path.get(m_path.size()-1))
            m_pathWayPoints.add(m_path.get(m_path.size()-1));
    }

    private Node getLowestF() {
        Node lowest = openList.get(0);
        for (Node n : openList) {
            if (n.getFunction() < lowest.getFunction()) {
                lowest = n;
            }
        }
        return lowest;
    }

    // public void updateUI() {
    //     //support.firePropertyChange("news", 0, this);

    // }

    public Network getNetwork() {
        return network;
    }

    public ArrayList<Node> getPath() {
        return m_path;
    }

    public ArrayList<Node> getPathWayPoints() {
        return m_pathWayPoints;
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
