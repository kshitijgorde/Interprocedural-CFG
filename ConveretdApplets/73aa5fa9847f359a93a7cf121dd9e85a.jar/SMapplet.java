import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.guyhaas.Intro_to_Programming.TurtleGraphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SMapplet extends Applet implements Runnable
{
    private static final int BLACK = 0;
    private static final int RED = 4;
    private static final int SALMON = 12;
    private static final int PATH_PEN_SIZE = 5;
    private static final int SQUARE_SIZE = 12;
    public static final int TOP_LEFT_X = -90;
    public static final int TOP_LEFT_Y = 90;
    Maze m;
    mazeSquare[][] maze;
    Thread solver;
    TurtleGraphics tgObj;
    
    public SMapplet() {
        this.solver = null;
    }
    
    private void drawMaze() {
        this.tgObj.clean();
        this.tgObj.setpensize(3);
        this.tgObj.setpencolor(0);
        for (int i = 0; i < 15; ++i) {
            if (this.m.sideIsWall(0, i, 0)) {
                this.drawWall(0, i, 0);
            }
        }
        for (int j = 0; j < 15; ++j) {
            if (this.m.sideIsWall(j, 14, 1)) {
                this.drawWall(j, 14, 1);
            }
        }
        for (int k = 0; k < 15; ++k) {
            if (this.m.sideIsWall(14, k, 2)) {
                this.drawWall(14, k, 2);
            }
        }
        for (int l = 0; l < 15; ++l) {
            if (this.m.sideIsWall(l, 0, 3)) {
                this.drawWall(l, 0, 3);
            }
        }
        for (int n = 1; n < 15; ++n) {
            for (int n2 = 0; n2 < 15; ++n2) {
                if (this.m.sideIsWall(n, n2, 0)) {
                    this.drawWall(n, n2, 0);
                }
            }
        }
        for (int n3 = 0; n3 < 15; ++n3) {
            for (int n4 = 1; n4 < 15; ++n4) {
                if (this.m.sideIsWall(n3, n4, 3)) {
                    this.drawWall(n3, n4, 3);
                }
            }
        }
    }
    
    void drawPath(final int n, final int n2, final int n3, final int n4) {
        this.tgObj.penup();
        this.tgObj.setxy(-90 + n2 * 12 + 6, 90 - n * 12 - 6);
        switch (n3) {
            case 0: {
                this.tgObj.setheading(0);
                break;
            }
            case 1: {
                this.tgObj.setheading(90);
                break;
            }
            case 2: {
                this.tgObj.setheading(180);
                break;
            }
            case 3: {
                this.tgObj.setheading(270);
                break;
            }
            default: {
                System.err.println("drawPath: bad side=" + n3);
                break;
            }
        }
        this.tgObj.pendown();
        this.tgObj.setpensize(5);
        this.tgObj.setpencolor(n4);
        this.tgObj.forward(12);
        try {
            Thread.sleep(150L);
        }
        catch (InterruptedException ex) {}
    }
    
    private void drawWall(final int n, final int n2, final int n3) {
        this.tgObj.penup();
        switch (n3) {
            case 0: {
                this.tgObj.setxy(-90 + n2 * 12, 90 - n * 12);
                this.tgObj.setheading(90);
                break;
            }
            case 1: {
                this.tgObj.setxy(-90 + n2 * 12 + 12, 90 - n * 12);
                this.tgObj.setheading(180);
                break;
            }
            case 2: {
                this.tgObj.setxy(-90 + n2 * 12, 90 - (n * 12 + 12));
                this.tgObj.setheading(90);
                break;
            }
            case 3: {
                this.tgObj.setxy(-90 + n2 * 12, 90 - n * 12);
                this.tgObj.setheading(180);
                break;
            }
            default: {
                System.err.println("drawWall: bad side=" + n3);
                break;
            }
        }
        this.tgObj.pendown();
        this.tgObj.forward(12);
    }
    
    void initialize() {
        this.m = new Maze();
        this.maze = new mazeSquare[15][];
        for (int i = 0; i < 15; ++i) {
            this.maze[i] = new mazeSquare[15];
        }
        for (int j = 0; j < 15; ++j) {
            for (int k = 0; k < 15; ++k) {
                this.maze[j][k] = new mazeSquare(j, k);
            }
        }
        this.tgObj.hideturtle();
    }
    
    boolean inMaze(final int n, final int n2) {
        return n >= 0 && n < 15 && n2 >= 0 && n2 < 15;
    }
    
    void solveMaze() {
        int startColumn = this.m.getStartColumn();
        int startRow = this.m.getStartRow();
        int startSide = this.m.getStartSide();
        this.maze[startRow][startColumn].setSideToIN(startSide);
        int n = 4;
        while (this.inMaze(startRow, startColumn)) {
            if (this.solver != Thread.currentThread()) {
                return;
            }
            this.drawPath(startRow, startColumn, startSide, n);
            this.maze[startRow][startColumn].setSideToIN(startSide);
            int sideToOUT = this.maze[startRow][startColumn].getOpenSide();
            if (sideToOUT < 0) {
                sideToOUT = this.maze[startRow][startColumn].getINSide();
                n = 12;
            }
            else {
                this.maze[startRow][startColumn].setSideToOUT(sideToOUT);
                n = 4;
            }
            switch (sideToOUT) {
                default: {
                    continue;
                }
                case 0: {
                    --startRow;
                    startSide = 2;
                    continue;
                }
                case 1: {
                    ++startColumn;
                    startSide = 3;
                    continue;
                }
                case 2: {
                    ++startRow;
                    startSide = 0;
                    continue;
                }
                case 3: {
                    --startColumn;
                    startSide = 1;
                    continue;
                }
            }
        }
        this.drawPath(startRow, startColumn, startSide, n);
    }
    
    void resetMaze() {
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                this.maze[i][j].resetMazeSquare();
            }
        }
    }
    
    public void start() {
        this.solver = new Thread(this);
        this.resetMaze();
        this.tgObj.clean();
        this.solver.start();
    }
    
    public void stop() {
        this.solver = null;
    }
    
    public void run() {
        Thread thread = Thread.currentThread();
        while (this.solver == thread) {
            this.resetMaze();
            this.m.makeMaze();
            this.drawMaze();
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex) {}
            thread = Thread.currentThread();
            if (this.solver != thread) {
                break;
            }
            this.solveMaze();
            try {
                Thread.sleep(5000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        (this.tgObj = new TurtleGraphics(200, 200)).setBackground(Color.white);
        this.add("Center", this.tgObj);
        this.initialize();
    }
    
    public class mazeSquare
    {
        private static final char IN = 'i';
        private static final char OUT = 'o';
        private static final char UNKNOWN = 'u';
        private static final char WALL = 'w';
        private char bottomSide;
        private char leftSide;
        private char rightSide;
        private char topSide;
        private int myColumn;
        private int myRow;
        
        public mazeSquare(final int myRow, final int myColumn) {
            this.myRow = myRow;
            this.myColumn = myColumn;
            this.topSide = 'u';
            this.rightSide = 'u';
            this.bottomSide = 'u';
            this.leftSide = 'u';
        }
        
        public int getINSide() {
            if (this.topSide == 'i') {
                return 0;
            }
            if (this.rightSide == 'i') {
                return 1;
            }
            if (this.bottomSide == 'i') {
                return 2;
            }
            if (this.leftSide == 'i') {
                return 3;
            }
            return -1;
        }
        
        public int getOpenSide() {
            if (this.topSide == 'u') {
                if (!SMapplet.this.m.sideIsWall(this.myRow, this.myColumn, 0)) {
                    return 0;
                }
                this.setSideToWALL(0);
            }
            if (this.rightSide == 'u') {
                if (!SMapplet.this.m.sideIsWall(this.myRow, this.myColumn, 1)) {
                    return 1;
                }
                this.setSideToWALL(1);
            }
            if (this.bottomSide == 'u') {
                if (!SMapplet.this.m.sideIsWall(this.myRow, this.myColumn, 2)) {
                    return 2;
                }
                this.setSideToWALL(2);
            }
            if (this.leftSide == 'u') {
                if (!SMapplet.this.m.sideIsWall(this.myRow, this.myColumn, 3)) {
                    return 3;
                }
                this.setSideToWALL(3);
            }
            return -1;
        }
        
        private void changeSideState(final int n, final char c) {
            switch (n) {
                case 0: {
                    if (this.topSide == 'u') {
                        this.topSide = c;
                        break;
                    }
                    break;
                }
                case 1: {
                    if (this.rightSide == 'u') {
                        this.rightSide = c;
                        break;
                    }
                    break;
                }
                case 2: {
                    if (this.bottomSide == 'u') {
                        this.bottomSide = c;
                        break;
                    }
                    break;
                }
                case 3: {
                    if (this.leftSide == 'u') {
                        this.leftSide = c;
                        break;
                    }
                    break;
                }
                default: {
                    System.err.println("changeSideState: bad side=" + n);
                    break;
                }
            }
        }
        
        public void dumpSquare() {
            System.err.print(this.topSide);
            System.err.print(this.rightSide);
            System.err.print(this.bottomSide);
            System.err.print(this.leftSide);
        }
        
        void resetMazeSquare() {
            this.topSide = 'u';
            this.rightSide = 'u';
            this.bottomSide = 'u';
            this.leftSide = 'u';
        }
        
        public void setSideToIN(final int n) {
            this.changeSideState(n, 'i');
        }
        
        public void setSideToOUT(final int n) {
            this.changeSideState(n, 'o');
        }
        
        public void setSideToWALL(final int n) {
            this.changeSideState(n, 'w');
        }
    }
}
