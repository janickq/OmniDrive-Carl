package frc.robot.Astar;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;

public class Layout {
    // Dimension of layout in real unit
    public static final int x_size_mm = 4038;
    public static final int y_size_mm = 2038;
    public static final int tile_size_mm = 25;
    public static final float tile_size_meter = tile_size_mm/1000.0f;
    public static final int X_SIZE = Math.round((float)x_size_mm/tile_size_mm)+1; //??
    public static final int Y_SIZE = Math.round((float)y_size_mm/tile_size_mm)+1;
    
    //List all fixed walls in layout here
    public static final int walls_mm[][] = {
        //(x0,y0) (x1,y1) end coordinates of line
        //x0,   y0,    x1,    y1
        //Boundary
        {0,     0,     4038,  0   }, 
        {4038,  0,     4038,  2038}, 
        {4038,  2038,  0,     2038}, 
        {0,     2038,  0,     0   }, 

        //Bottom
        {0,     680,   340,   680}, 
        {1013,  0,     1013,  680}, 
        {2023,  0,     2023,  680}, 
        {3033,  0,     3033,  680}, 
        //Top
        {1787,  2038,  1787,  2038-450}, 
        {3007,  2038,  3007,  2038-300}, 
    };


    //List all fixed rectangular obstacles in layout here
    public static final int obs_mm[][] = {
        //(x0,y0) - centre of box
        //x0   y0    xSize ySize Angle
        {1013, 755,  210,  150,  0 },   //corridoor gurney
        {2023, 755,  210,  150,  0 }, 
        {3033, 755,  210,  150,  0 }, 
        {1957, 2038-425,  150,  210,  0 }, 
        {3007, 2038-425,  150,  210,  0 }, 
        {1546, 115,       150,  210, 0}, //Room gurney
        {2546, 115,       150,  210, 0}, 
        {3546, 115,       150,  210, 0}, 
        {3546, 2038-115,  150,  210, 0}, 
        {2546, 2038-115,  150,  210, 0}, 

        //Room med cube obstacle are not really necessary but good for checking if positions are right
        {1546-185, 50,       60,  60, 0}, //Room med cube stand. 
        {2546-185, 50,       60,  60, 0}, 
        {3546-185, 50,       60,  60, 0}, 
        {3546+185, 2038-50,  60,  60, 0}, 
        {2546+185, 2038-50,  60,  60, 0}, 

        {1546+185, 50,       60,  60, 0}, //Room hazmat cube stand. 
        {2546+185, 50,       60,  60, 0}, 
        {3546+185, 50,       60,  60, 0}, 
        {3546-185, 2038-50,  60,  60, 0}, 
        {2546-185, 2038-50,  60,  60, 0}, 
        {525,  2038-50,   650,  100,  0 },  //Dispensary
    };

    //These are initial positions for robots to go to place medicine cube
    //Each row corresponds to a room (room-0, room-1 etc)
    //Robot needs to make final adjustment to align with stand
    public static final int medCubeStandPos[][] = {
        //x, y, angle
        //Robot stops 250mm from stand. To be adjusted
        {1546-185, 50+250,       -90}, //Room med cube stand. 
        {2546-185, 50+250,       -90}, 
        {3546-185, 50+250,       -90}, 
        {3546+185, 2038-50-250,  90}, 
        {2546+185, 2038-50-250,  90}, 
    };

    //These are initial positions for robots to go to retrieve hazmat cube
    public static final int hazMatStandPos[][] = {
        //x, y, angle
        {0, 0, 0},
    };

    //These are initial room gurney positions for robots to go to 
    public static final int roomGurneyPos[][] = {
        //x, y, angle
        {0, 0, 0},
    };

    // Initial Position for robot to go to for dispensary
    public static final int dispensaryPos[] = {525, 2038-500, 90};  

    // Position for robot to go to for reading work order
    public static final int workOrderPos[] = {950, 2038-500, 90};

    // Initial Position for robot to go to for disposing hazmat
    public static final int HazMatBinPos[] = {500, 980, 180};

    // Robot start position. Also the cleaning position
    public static final int startPos[] = {340, 340,  0};

    private int walls[][];
    private int obs[][];

    public Layout() {
        int i, j;
        
        //Convert walls in mm to walls in cell size
        walls = new int[walls_mm.length][4];
        for (i=0; i< walls_mm.length; i++) {
            for (j=0; j<4; j++) {
                walls[i][j] = Math.round((float)walls_mm[i][j]/tile_size_mm);
            }
        }

        //Convert obstacles in mm to obstacles in cell size
        obs = new int[obs_mm.length][5];
        for (i=0; i< obs_mm.length; i++) {
            for (j=0; j<4; j++) {
                obs[i][j] = Math.round((float)obs_mm[i][j]/tile_size_mm);
            }
            obs[i][4] = obs_mm[i][4];  //Angle in degree stays the same
        }

    }

    static public Translation2d Convert_cell_m(Translation2d pt) {
        Translation2d pt_m = new Translation2d(pt.getX()*tile_size_meter, pt.getY()*tile_size_meter);
        return pt_m;
    }

    static public int Convert_m_cell (double m) {
        return Math.round((float)m*1000/tile_size_mm);
    }

    static public Pose2d Convert_mm_Pose2d (int pos[]) {
        return new Pose2d(pos[0]/1000.0f, pos[1]/1000.0f, new Rotation2d(pos[2]*Math.PI/180));
   
    }

    public int [][] getWalls() {
        return walls;
    }

    public int [][] getObs() {
        return obs;
    }
}
