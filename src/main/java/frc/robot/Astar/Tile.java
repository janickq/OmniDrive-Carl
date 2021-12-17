package frc.robot.Astar;

import java.awt.Point;
import java.util.ArrayList;

public class Tile extends Node {

    private int x, y;
    public static int TILE_SIZE = 5;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.setObsValue(0.0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void calculateNeighbours(Network network, boolean diagFlag) {
        
        Grid grid = (Grid) network;

        ArrayList<Node> nodes = new ArrayList<Node>();

        int minX = 0;
        int minY = 0;
        int maxX = grid.getxSize() - 1;
        int maxY = grid.getySize() - 1;

        if (x > minX) {
            nodes.add(grid.find(x - 1, y)); //west
        }

        if (x < maxX) {
            nodes.add(grid.find(x + 1, y)); //east
        }

        if (y > minY) {
            nodes.add(grid.find(x, y - 1)); //north
        }

        if (y < maxY) {
            nodes.add(grid.find(x, y + 1)); //south
        }
        if (diagFlag == true) {
            if (x > minX && y > minY) {
                nodes.add(grid.find(x - 1, y - 1)); // northwest
            }

            if (x < maxX && y < maxY) {
                nodes.add(grid.find(x + 1, y + 1)); // southeast
            }

            if (x < maxX && y > minY) {
                nodes.add(grid.find(x + 1, y - 1)); // northeast
            }

            if (x > minY && y < maxY) {
                nodes.add(grid.find(x - 1, y + 1)); // southwest
            }
        }

        setNeighbours(nodes);

    }

    @Override
    public double heuristic(Node dest) {
        return distanceTo(dest);
    }

    @Override
    public double distanceTo(Node dest) {
        Tile d = (Tile) dest;
        return new Point(x,y).distance(new Point(d.x, d.y));
    }
    @Override
    public double dirTo(Node dest) {
        Tile d = (Tile) dest;
        int dx = d.x - x;
        int dy = d.y - y;
        return Math.atan2(dy, dx);

    }
    public double angleTo(Node dest) {
        Tile d = (Tile) dest;
        double dx = d.x - x;
        double dy = d.y - y;
        return Math.atan2(dy, dx);
    }
}
