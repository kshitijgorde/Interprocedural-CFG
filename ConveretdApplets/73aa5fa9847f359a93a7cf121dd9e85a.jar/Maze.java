// 
// Decompiled by Procyon v0.5.30
// 

class Maze
{
    public static final int NUM_SQUARE = 15;
    public static final int MAX_SQUARE_NUM = 14;
    public static final int TOP = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    private static final char AVAIL = 'a';
    private static final char FINISH = 'f';
    private static final char IN = 'i';
    private static final char OUT = 'o';
    private static final char START = 's';
    private static final char WALL = 'w';
    private int startSide;
    private SqrLoc endSquare;
    private SqrLoc startSquare;
    private Square[][] maze;
    
    public Maze() {
        this.maze = new Square[15][];
        for (int i = 0; i < 15; ++i) {
            this.maze[i] = new Square[15];
            for (int j = 0; j < 15; ++j) {
                this.maze[i][j] = new Square();
            }
        }
    }
    
    private void createPaths() {
        final int[] array = new int[3];
        final SqrLoc[] array2 = new SqrLoc[225];
        array2[0] = (SqrLoc)this.startSquare.clone();
        int n = 1;
        int n2 = this.startSquare.column;
        int n3 = this.startSquare.row;
        while (true) {
            int n4 = 0;
            if (this.maze[n3][n2].sideIsAVAIL(0)) {
                array[n4++] = 0;
            }
            if (this.maze[n3][n2].sideIsAVAIL(1)) {
                array[n4++] = 1;
            }
            if (this.maze[n3][n2].sideIsAVAIL(2)) {
                array[n4++] = 2;
            }
            if (this.maze[n3][n2].sideIsAVAIL(3)) {
                array[n4++] = 3;
            }
            if (n4 == 0) {
                if (--n < 0) {
                    break;
                }
                n3 = array2[n].row;
                n2 = array2[n].column;
            }
            else {
                int n5;
                if (n4 == 1) {
                    n5 = array[0];
                }
                else {
                    n5 = array[(int)(Math.random() * n4)];
                }
                switch (n5) {
                    case 0: {
                        if (this.maze[n3 - 1][n2].alreadyHasIN()) {
                            this.maze[n3 - 1][n2].setSideToWALL(2);
                            this.maze[n3][n2].setSideToWALL(0);
                            continue;
                        }
                        this.maze[n3--][n2].setSideToOUT(0);
                        this.maze[n3][n2].setSideToIN(2);
                        break;
                    }
                    case 1: {
                        if (this.maze[n3][n2 + 1].alreadyHasIN()) {
                            this.maze[n3][n2 + 1].setSideToWALL(3);
                            this.maze[n3][n2].setSideToWALL(1);
                            continue;
                        }
                        this.maze[n3][n2++].setSideToOUT(1);
                        this.maze[n3][n2].setSideToIN(3);
                        break;
                    }
                    case 2: {
                        if (this.maze[n3 + 1][n2].alreadyHasIN()) {
                            this.maze[n3 + 1][n2].setSideToWALL(0);
                            this.maze[n3][n2].setSideToWALL(2);
                            continue;
                        }
                        this.maze[n3++][n2].setSideToOUT(2);
                        this.maze[n3][n2].setSideToIN(0);
                        break;
                    }
                    case 3: {
                        if (this.maze[n3][n2 - 1].alreadyHasIN()) {
                            this.maze[n3][n2 - 1].setSideToWALL(1);
                            this.maze[n3][n2].setSideToWALL(3);
                            continue;
                        }
                        this.maze[n3][n2--].setSideToOUT(3);
                        this.maze[n3][n2].setSideToIN(1);
                        break;
                    }
                }
                if (n3 == this.endSquare.row && n2 == this.endSquare.column) {
                    this.maze[n3][n2].AVAILtoWALLs();
                }
                array2[n++] = new SqrLoc(n3, n2);
            }
        }
    }
    
    private void emptyMaze() {
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                if (i == 0) {
                    this.maze[i][j].setSideToWALL(0);
                }
                else {
                    this.maze[i][j].setSideToAVAIL(0);
                }
                if (j == 14) {
                    this.maze[i][j].setSideToWALL(1);
                }
                else {
                    this.maze[i][j].setSideToAVAIL(1);
                }
                if (i == 14) {
                    this.maze[i][j].setSideToWALL(2);
                }
                else {
                    this.maze[i][j].setSideToAVAIL(2);
                }
                if (j == 0) {
                    this.maze[i][j].setSideToWALL(3);
                }
                else {
                    this.maze[i][j].setSideToAVAIL(3);
                }
            }
        }
        this.startSquare = this.setMazeStartFinish('s');
        this.endSquare = this.setMazeStartFinish('f');
    }
    
    public int getStartColumn() {
        return this.startSquare.column;
    }
    
    public int getStartRow() {
        return this.startSquare.row;
    }
    
    public int getStartSide() {
        return this.startSide;
    }
    
    public void makeMaze() {
        this.emptyMaze();
        this.createPaths();
    }
    
    SqrLoc setMazeStartFinish(final char c) {
        final SqrLoc sqrLoc = new SqrLoc();
        if (c == 's') {
            this.startSide = -1;
        }
        int i;
        do {
            i = (int)(Math.random() * 4.0);
        } while (i == this.startSide);
        if (c == 's') {
            this.startSide = i;
        }
        final int n = (int)(Math.random() * 15.0);
        switch (i) {
            case 0: {
                this.maze[0][n].changeSideState(0, c);
                sqrLoc.row = 0;
                sqrLoc.column = n;
                break;
            }
            case 1: {
                this.maze[n][14].changeSideState(1, c);
                sqrLoc.row = n;
                sqrLoc.column = 14;
                break;
            }
            case 2: {
                this.maze[14][n].changeSideState(2, c);
                sqrLoc.row = 14;
                sqrLoc.column = n;
                break;
            }
            case 3: {
                this.maze[n][0].changeSideState(3, c);
                sqrLoc.row = n;
                sqrLoc.column = 0;
                break;
            }
            default: {
                System.err.println("setMazeStartFinish: bad side=" + i);
                break;
            }
        }
        return sqrLoc;
    }
    
    public boolean sideIsWall(final int n, final int n2, final int n3) {
        return this.maze[n][n2].sideIsWALL(n3);
    }
    
    class SqrLoc implements Cloneable
    {
        int row;
        int column;
        
        public SqrLoc(final Maze maze) {
            this(maze, 0, 0);
        }
        
        public SqrLoc(final int row, final int column) {
            this.row = row;
            this.column = column;
        }
        
        public Object clone() {
            try {
                return super.clone();
            }
            catch (CloneNotSupportedException ex) {
                return null;
            }
        }
    }
    
    public class Square
    {
        char topSide;
        char rightSide;
        char bottomSide;
        char leftSide;
        
        private boolean alreadyHasIN() {
            return this.topSide == 'i' || this.topSide == 's' || (this.rightSide == 'i' || this.rightSide == 's') || (this.bottomSide == 'i' || this.bottomSide == 's') || (this.leftSide == 'i' || this.leftSide == 's');
        }
        
        private void AVAILtoWALLs() {
            if (this.topSide == 'a') {
                this.topSide = 'w';
            }
            if (this.rightSide == 'a') {
                this.rightSide = 'w';
            }
            if (this.bottomSide == 'a') {
                this.bottomSide = 'w';
            }
            if (this.leftSide == 'a') {
                this.leftSide = 'w';
            }
        }
        
        private void changeSideState(final int n, final char c) {
            switch (n) {
                case 0: {
                    this.topSide = c;
                    break;
                }
                case 1: {
                    this.rightSide = c;
                    break;
                }
                case 2: {
                    this.bottomSide = c;
                    break;
                }
                case 3: {
                    this.leftSide = c;
                    break;
                }
                default: {
                    System.err.println("bad side=" + n);
                    break;
                }
            }
        }
        
        private void setSideToAVAIL(final int n) {
            this.changeSideState(n, 'a');
        }
        
        private void setSideToIN(final int n) {
            this.changeSideState(n, 'i');
        }
        
        private void setSideToOUT(final int n) {
            this.changeSideState(n, 'o');
        }
        
        private void setSideToWALL(final int n) {
            this.changeSideState(n, 'w');
        }
        
        private boolean sideIsAVAIL(final int n) {
            switch (n) {
                case 0: {
                    if (this.topSide == 'a') {
                        return true;
                    }
                    break;
                }
                case 1: {
                    if (this.rightSide == 'a') {
                        return true;
                    }
                    break;
                }
                case 2: {
                    if (this.bottomSide == 'a') {
                        return true;
                    }
                    break;
                }
                case 3: {
                    if (this.leftSide == 'a') {
                        return true;
                    }
                    break;
                }
            }
            return false;
        }
        
        private boolean sideIsWALL(final int n) {
            switch (n) {
                case 0: {
                    if (this.topSide == 'w') {
                        return true;
                    }
                    break;
                }
                case 1: {
                    if (this.rightSide == 'w') {
                        return true;
                    }
                    break;
                }
                case 2: {
                    if (this.bottomSide == 'w') {
                        return true;
                    }
                    break;
                }
                case 3: {
                    if (this.leftSide == 'w') {
                        return true;
                    }
                    break;
                }
            }
            return false;
        }
    }
}
