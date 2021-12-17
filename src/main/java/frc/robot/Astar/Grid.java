package frc.robot.Astar;

import java.util.ArrayList;

public class Grid extends Network{

    private int xSize, ySize;
    private ArrayList<Tile> tiles;

    public Grid(Layout layout) {
        xSize = Layout.X_SIZE;
        ySize = Layout.Y_SIZE;

        tiles = new ArrayList<>();
        //Add individual cell
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Tile t = new Tile(i, j);
                tiles.add(t);
            }
        }
        // System.out.println(xSize);
        // System.out.println(ySize);
        // System.out.println(tiles.size());
        
        //Pre assign neighbours
        for (Tile t : getTiles()) {
            t.calculateNeighbours(this, true);
        }

        //Add fixed walls
        int[][] walls = layout.getWalls();
        for(int i=0; i< walls.length; i++) {
            AddWall(walls[i][0], walls[i][1], walls[i][2], walls[i][3]);
        }

        //Add fixed obstacles
        int[][] obs = layout.getObs();
        for(int i=0; i< obs.length; i++) {
            AddObstacle(obs[i][0], obs[i][1], obs[i][2], obs[i][3], obs[i][4]*Math.PI/180);
        }

        //Expand obstacles. Do here????
    }

    
     /**
   * Expand obstacle to push robot away. Robot can still go through this expanded cell if necessary
   *
   * @param robotRadius_mm robot radius in mm
   */
    public void ExpandObstacles(float robotRadius_mm) {

        //Expand obstacle by robot radius. These are no entry zones
        int robotRadius = Math.round((float)robotRadius_mm/Layout.tile_size_mm);
        for (int k=0; k<robotRadius; k++) {
            for (int x = 0; x < xSize; x++) {
                for (int y = 0; y < ySize; y++) {
                    Node current = find(x,y);
                    if (current.getObsValue()==Node.maxObsValue) {
                        for (Node n : current.getNeighbours()) {
                            //temporary value
                            if (n.getObsValue()!=Node.maxObsValue)
                            n.setObsValue(Node.maxObsValue/2);
                        }
                    }
                }
            }
            //Set temporary value back to max!
            for (int x = 0; x < xSize; x++) {
                for (int y = 0; y < ySize; y++) {
                    Node current = find(x,y);
                    if (current.getObsValue()==Node.maxObsValue/2) {
                        current.setObsValue(Node.maxObsValue - 1);   //So that we can run mulitple times
                    }
                }
            }
        }

        //Expand obstacle by path cost. These are high cost cell to force robot away from obstacle
        //It is possible for robot to enter these cells (tiles).
        //Number of cells to expand and their values are defined here
        double keepOutDist_mm = 160;      
        int numOfCells = Math.round((float)keepOutDist_mm/Layout.tile_size_mm);  
        double expansion[] = new double[numOfCells];
        double factor = Math.exp(Math.log(0.1f)/numOfCells);
        double cost = Node.maxObsValue*factor;
        for (int i=0; i<numOfCells; i++) { 
            expansion[i] = cost;
            cost *= factor;
        }

        for (int k=0; k<expansion.length; k++) {
            for (int x = 0; x < xSize; x++) {
                for (int y = 0; y < ySize; y++) {
                    double obsValue = expansion[k];
                    Node current = find(x,y);
                    if (current.getObsValue()>obsValue) {
                        for (Node n : current.getNeighbours()) {
                            if (n.getObsValue()==0.0)
                                n.setObsValue(obsValue);
                        }
                    }
                }
            }
        }
    }

  /**
   * Add rectangular shape obstacle to field.
   *
   * @param x0 centre X pos
   * @param y0 centre Y pos
   * @param xSize X size of rect
   * @param ySize Y size of rect
   * @param angle orientation of rectangle
   */
    public void AddObstacle(int x0, int y0, int xSize, int ySize, double angle) {
        for (int x=-xSize/2; x<xSize/2; x++) {
            for (int y=-ySize/2; y<ySize/2; y++) {
                int xx = (int)Math.round(x*Math.cos(angle) - y*Math.sin(angle));
                int yy = (int)Math.round(x*Math.sin(angle) + y*Math.cos(angle));
                Tile t = find(xx+x0,yy+y0);
                if (t!=null)
                    t.setObsValue(Node.maxObsValue);
            }
        }
    }
     
    /**
     * function findLine() - to find that belong to line connecting the two points
     **/
    public void AddWall(int x0, int y0, int x1, int y1) {
  
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

        int err = dx - dy;
        int e2;

        while (true) {
            Tile t = find(x0,y0);
            if (t!=null)
                t.setObsValue(Node.maxObsValue);

            if (x0 == x1 && y0 == y1)
                break;

            e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x0 = x0 + sx;
            }

            if (e2 < dx) {
                err = err + dx;
                y0 = y0 + sy;
            }
        }
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
    
    public Tile find(int x, int y){
        // for(Tile t : tiles){
        //     if(t.getX() == x && t.getY() == y)
        //         return t;
        // }
        // return null;
        if (x>=0 && x<xSize && y>=0 && y<ySize)
            return tiles.get(x*ySize+y);
        else  {
            return null;
        }
    }

    @Override
    public Iterable<Node> getNodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.addAll(tiles);
        return nodes;
    }
}
