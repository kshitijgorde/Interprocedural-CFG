import java.util.Date;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class PaintScreen extends Thread
{
    private static final int NUM_COLORS = 16;
    private static final int TOP_COLOR = 12;
    private static final int RECTANGLE_SE_NW_COLOR = 10;
    private static final int TRIANGLE_SSE_NNW_COLOR = 9;
    private static final int TRIANGLE_SE_NW_COLOR = 8;
    private static final int RECTANGLE_W_E_COLOR = 7;
    private static final int FLOOR_COLOR = 6;
    private static final int TRIANGLE_SW_NE_COLOR = 5;
    private static final int RECTANGLE_SW_NE_COLOR = 4;
    private static final int TRIANGLE_SSW_NNE_COLOR = 3;
    private static final int BACKOUT_COLOR = 13;
    private static final int ADVANCE_COLOR = 14;
    private static final int SOLUTION_COLOR = 15;
    private static final double RELATIVE_WIDTH_OF_WALL = 0.25;
    private static final double RELATIVE_HEIGHT_OF_WALL = 2.0;
    private static final double MIN_WALL_LENGTH_IN_INCHES = 0.2;
    private static final double SECONDS_FOR_MAZE_SELECTION = 0.25;
    private static final int[] SUBSTITUTION_HIGH;
    private static final int[] SUBSTITUTION_LOW;
    private int adjacency;
    public boolean alreadyPainting;
    private VertexRec[][] baseRectangle;
    private VertexRec[][] baseTriangle;
    private boolean clearUserAttempts;
    private char[][] computerPage;
    private double cosTilt;
    private int counter0;
    private int counter1;
    private int counter2;
    private int counter3;
    private int counter4;
    private int counter5;
    private int counter6;
    private int counter7;
    private Graphics graph;
    private boolean hexagonalRooms;
    private int[][] hexDeltaX;
    private int[][] hexDeltaY;
    private int maxX;
    private int maxY;
    private MazeCanvas mazeCanvas;
    private boolean minimized;
    private int numColumns;
    private int numRoomsInMaze;
    private int numRoomsInSolution;
    private int numRows;
    private boolean paint;
    private double pixelsPerX;
    private double pixelsPerZ;
    public VertexRec[][] rectangle;
    private Color[] redGreenBlue;
    private double relDistOfUserFromScreen;
    private boolean resize;
    public boolean restart;
    private Rectangle screen;
    private int[] seed;
    private double sinTilt;
    private boolean solutionDisplayed;
    private int[][] sqrDeltaX;
    private int[][] sqrDeltaY;
    private double sqrt3;
    private StackRec[] stack;
    public int state;
    private double tilt;
    public boolean userHasSolved;
    private char[][] userPage;
    private int userX;
    private double userXRelative;
    private int userY;
    private double userYRelative;
    private int x;
    private double xMax;
    private double xOffset;
    private int y;
    private double yMax;
    private int yMod4;
    private double yOffset;
    
    PaintScreen(final MazeCanvas mazeCanvas) {
        this.setDaemon(true);
        this.mazeCanvas = mazeCanvas;
        this.restart = false;
        this.hexagonalRooms = this.mazeCanvas.maze3D.hexagonalRooms;
        this.clearUserAttempts = true;
        this.state = 5;
        this.baseRectangle = new VertexRec[6][4];
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.baseRectangle[i][j] = new VertexRec();
            }
        }
        this.rectangle = new VertexRec[6][4];
        for (int k = 0; k < 6; ++k) {
            for (int l = 0; l < 4; ++l) {
                this.rectangle[k][l] = new VertexRec();
            }
        }
        this.baseTriangle = new VertexRec[4][3];
        for (int n = 0; n < 4; ++n) {
            for (int n2 = 0; n2 < 3; ++n2) {
                this.baseTriangle[n][n2] = new VertexRec();
            }
        }
        this.hexDeltaX = new int[6][720];
        this.hexDeltaY = new int[6][720];
        this.sqrDeltaX = new int[4][24];
        this.sqrDeltaY = new int[4][24];
        this.redGreenBlue = new Color[16];
        this.computerPage = null;
        this.userPage = null;
        this.stack = null;
        this.alreadyPainting = false;
        this.solutionDisplayed = false;
        this.userHasSolved = false;
        this.minimized = false;
        this.hexDeltaY[0][0] = -1;
        this.hexDeltaX[0][0] = -2;
        this.hexDeltaY[1][0] = 1;
        this.hexDeltaX[1][0] = -2;
        this.hexDeltaY[2][0] = -2;
        this.hexDeltaX[2][0] = 0;
        this.hexDeltaY[3][0] = 2;
        this.hexDeltaX[3][0] = 0;
        this.hexDeltaY[4][0] = -1;
        this.hexDeltaX[4][0] = 2;
        this.hexDeltaY[5][0] = 1;
        this.hexDeltaX[5][0] = 2;
        int n3 = 0;
        for (int n4 = 0; n4 < 6; ++n4) {
            for (int n5 = 0; n5 < 6; ++n5) {
                if (n4 != n5) {
                    for (int n6 = 0; n6 < 6; ++n6) {
                        if (n4 != n6 && n5 != n6) {
                            for (int n7 = 0; n7 < 6; ++n7) {
                                if (n4 != n7 && n5 != n7 && n6 != n7) {
                                    for (int n8 = 0; n8 < 6; ++n8) {
                                        if (n4 != n8 && n5 != n8 && n6 != n8 && n7 != n8) {
                                            for (int n9 = 0; n9 < 6; ++n9) {
                                                if (n4 != n9 && n5 != n9 && n6 != n9 && n7 != n9 && n8 != n9) {
                                                    this.hexDeltaX[n4][n3] = this.hexDeltaX[0][0];
                                                    this.hexDeltaY[n4][n3] = this.hexDeltaY[0][0];
                                                    this.hexDeltaX[n5][n3] = this.hexDeltaX[1][0];
                                                    this.hexDeltaY[n5][n3] = this.hexDeltaY[1][0];
                                                    this.hexDeltaX[n6][n3] = this.hexDeltaX[2][0];
                                                    this.hexDeltaY[n6][n3] = this.hexDeltaY[2][0];
                                                    this.hexDeltaX[n7][n3] = this.hexDeltaX[3][0];
                                                    this.hexDeltaY[n7][n3] = this.hexDeltaY[3][0];
                                                    this.hexDeltaX[n8][n3] = this.hexDeltaX[4][0];
                                                    this.hexDeltaY[n8][n3] = this.hexDeltaY[4][0];
                                                    this.hexDeltaX[n9][n3] = this.hexDeltaX[5][0];
                                                    this.hexDeltaY[n9][n3++] = this.hexDeltaY[5][0];
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.sqrDeltaY[0][0] = 0;
        this.sqrDeltaX[0][0] = -1;
        this.sqrDeltaY[1][0] = 1;
        this.sqrDeltaX[1][0] = 0;
        this.sqrDeltaY[2][0] = 0;
        this.sqrDeltaX[2][0] = 1;
        this.sqrDeltaY[3][0] = -1;
        this.sqrDeltaX[3][0] = 0;
        int n10 = 0;
        for (int n11 = 0; n11 < 4; ++n11) {
            for (int n12 = 0; n12 < 4; ++n12) {
                if (n11 != n12) {
                    for (int n13 = 0; n13 < 4; ++n13) {
                        if (n11 != n13 && n12 != n13) {
                            for (int n14 = 0; n14 < 4; ++n14) {
                                if (n11 != n14 && n12 != n14 && n13 != n14) {
                                    this.sqrDeltaX[n11][n10] = this.sqrDeltaX[0][0];
                                    this.sqrDeltaY[n11][n10] = this.sqrDeltaY[0][0];
                                    this.sqrDeltaX[n12][n10] = this.sqrDeltaX[1][0];
                                    this.sqrDeltaY[n12][n10] = this.sqrDeltaY[1][0];
                                    this.sqrDeltaX[n13][n10] = this.sqrDeltaX[2][0];
                                    this.sqrDeltaY[n13][n10] = this.sqrDeltaY[2][0];
                                    this.sqrDeltaX[n14][n10] = this.sqrDeltaX[3][0];
                                    this.sqrDeltaY[n14][n10++] = this.sqrDeltaY[3][0];
                                }
                            }
                        }
                    }
                }
            }
        }
        this.sqrt3 = Math.sqrt(3.0);
        for (int n15 = 0; n15 <= 12; ++n15) {
            final int n16 = 256 * n15 / 13;
            this.redGreenBlue[n15] = new Color(n16, n16, n16);
        }
        this.redGreenBlue[13] = new Color(255, 255, 0);
        this.redGreenBlue[14] = new Color(0, 255, 0);
        this.redGreenBlue[15] = new Color(255, 0, 0);
    }
    
    private void drawQuadrilateral(final POINT[] array, final int n) {
        final int[] array2 = new int[4];
        final int[] array3 = new int[4];
        for (int i = 0; i < 4; ++i) {
            array2[i] = array[i].x;
            array3[i] = array[i].y;
        }
        this.graph.setColor(this.redGreenBlue[n]);
        this.graph.fillPolygon(array2, array3, 4);
    }
    
    private POINT getCorner(final double n, final double n2, final double n3) {
        final double n4 = (this.yMax - n2) * this.cosTilt - n3 * this.sinTilt;
        final double n5 = this.yMax / 2.0 + this.relDistOfUserFromScreen * ((this.yMax - n2) * this.sinTilt + n3 * this.cosTilt - this.yMax / 2.0) / (n4 + this.relDistOfUserFromScreen);
        final double n6 = this.xMax / 2.0 + this.relDistOfUserFromScreen * (n - this.xMax / 2.0) / (n4 + this.relDistOfUserFromScreen) + this.xOffset;
        final POINT point = new POINT();
        point.x = (int)(this.pixelsPerX * n6);
        point.y = this.screen.height - (int)(this.pixelsPerZ * n5);
        return point;
    }
    
    private void displayQuadrilateral(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10, final double n11, final double n12, final int n13) {
        this.drawQuadrilateral(new POINT[] { this.getCorner(n, n2, n3), this.getCorner(n4, n5, n6), this.getCorner(n7, n8, n9), this.getCorner(n10, n11, n12) }, n13);
    }
    
    private void drawTriangle(final POINT[] array, final int n) {
        final int[] array2 = new int[3];
        final int[] array3 = new int[3];
        for (int i = 0; i < 3; ++i) {
            array2[i] = array[i].x;
            array3[i] = array[i].y;
        }
        this.graph.setColor(this.redGreenBlue[n]);
        this.graph.fillPolygon(array2, array3, 3);
    }
    
    private void displayTriangle(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final int n10) {
        this.drawTriangle(new POINT[] { this.getCorner(n, n2, n3), this.getCorner(n4, n5, n6), this.getCorner(n7, n8, n9) }, n10);
    }
    
    private void outputTriangle(final VertexRec[] array, final boolean b, final int n) {
        if (b) {
            if (array[1].x < this.xMax / 2.0 && array[1].x > array[0].x) {
                this.displayQuadrilateral(array[2].x, array[2].y, 2.0, array[1].x, array[1].y, 2.0, array[1].x, array[1].y, 0.0, array[2].x, array[2].y, 0.0, 3);
            }
            if (array[1].x > this.xMax / 2.0 && array[1].x < array[2].x) {
                this.displayQuadrilateral(array[1].x, array[1].y, 2.0, array[0].x, array[0].y, 2.0, array[0].x, array[0].y, 0.0, array[1].x, array[1].y, 0.0, 9);
            }
        }
        else {
            this.displayQuadrilateral(array[0].x, array[0].y, 2.0, array[2].x, array[2].y, 2.0, array[2].x, array[2].y, 0.0, array[0].x, array[0].y, 0.0, n);
            this.displayTriangle(array[0].x, array[0].y, 2.0, array[1].x, array[1].y, 2.0, array[2].x, array[2].y, 2.0, 12);
        }
    }
    
    private void outputRectangle(final VertexRec[] array, final int n) {
        this.displayQuadrilateral(array[3].x, array[3].y, 2.0, array[2].x, array[2].y, 2.0, array[2].x, array[2].y, 0.0, array[3].x, array[3].y, 0.0, n);
        this.displayQuadrilateral(array[0].x, array[0].y, 2.0, array[1].x, array[1].y, 2.0, array[2].x, array[2].y, 2.0, array[3].x, array[3].y, 2.0, 12);
    }
    
    private void outputLeftRight(final VertexRec[] array) {
        if (2.0 * array[0].x > this.xMax) {
            this.displayQuadrilateral(array[0].x, array[0].y, 2.0, array[3].x, array[3].y, 2.0, array[3].x, array[3].y, 0.0, array[0].x, array[0].y, 0.0, 10);
        }
        if (2.0 * array[1].x < this.xMax) {
            this.displayQuadrilateral(array[2].x, array[2].y, 2.0, array[1].x, array[1].y, 2.0, array[1].x, array[1].y, 0.0, array[2].x, array[2].y, 0.0, 4);
        }
    }
    
    private void drawLine(final double n, final double n2, final double n3, final double n4) {
        final POINT corner = this.getCorner(n, n2, 2.0);
        final int x = corner.x;
        final int y = corner.y;
        final POINT corner2 = this.getCorner(n3, n4, 2.0);
        this.graph.drawLine(x, y, corner2.x, corner2.y);
    }
    
    private void hash() {
        int counter0 = this.counter0;
        int counter2 = this.counter1;
        int counter3 = this.counter2;
        int counter4 = this.counter3;
        int counter5 = this.counter4;
        int counter6 = this.counter5;
        int counter7 = this.counter6;
        int counter8 = this.counter7;
        for (int i = 8; i > 0; --i) {
            final int n = 10 * counter2 + counter0;
            final int n2 = PaintScreen.SUBSTITUTION_LOW[n];
            final int n3 = PaintScreen.SUBSTITUTION_HIGH[n];
            final int n4 = 10 * counter4 + counter3;
            counter0 = PaintScreen.SUBSTITUTION_LOW[n4];
            final int n5 = PaintScreen.SUBSTITUTION_HIGH[n4];
            final int n6 = 10 * counter6 + counter5;
            counter3 = PaintScreen.SUBSTITUTION_LOW[n6];
            counter2 = PaintScreen.SUBSTITUTION_HIGH[n6];
            final int n7 = 10 * counter8 + counter7;
            counter6 = PaintScreen.SUBSTITUTION_LOW[n7];
            counter8 = PaintScreen.SUBSTITUTION_HIGH[n7];
            counter4 = n2;
            counter7 = n3;
            counter5 = n5;
        }
        this.counter0 = counter0;
        this.counter1 = counter2;
        this.counter2 = counter3;
        this.counter3 = counter4;
        this.counter4 = counter5;
        this.counter5 = counter6;
        this.counter6 = counter7;
        this.counter7 = counter8;
    }
    
    private void increment() {
        final int counter0 = this.counter0 + 1;
        if (counter0 <= 9) {
            this.counter0 = counter0;
            return;
        }
        this.counter0 = 0;
        final int counter2 = this.counter1 + 1;
        if (counter2 <= 9) {
            this.counter1 = counter2;
            return;
        }
        this.counter1 = 0;
        final int counter3 = this.counter2 + 1;
        if (counter3 <= 9) {
            this.counter2 = counter3;
            return;
        }
        this.counter2 = 0;
        final int counter4 = this.counter3 + 1;
        if (counter4 <= 9) {
            this.counter3 = counter4;
            return;
        }
        this.counter3 = 0;
        final int counter5 = this.counter4 + 1;
        if (counter5 <= 9) {
            this.counter4 = counter5;
            return;
        }
        this.counter4 = 0;
        final int counter6 = this.counter5 + 1;
        if (counter6 <= 9) {
            this.counter5 = counter6;
            return;
        }
        this.counter5 = 0;
        final int counter7 = this.counter6 + 1;
        if (counter7 <= 9) {
            this.counter6 = counter7;
            return;
        }
        this.counter6 = 0;
        final int counter8 = this.counter7 + 1;
        if (counter8 <= 9) {
            this.counter7 = counter8;
            return;
        }
        this.counter7 = 0;
    }
    
    public void hexKey(final int n) {
        int n2 = 0;
        double userXRelative = 0.0;
        double userYRelative = 0.0;
        boolean b = true;
        final int n3 = this.userX + this.hexDeltaX[n][0];
        if (n3 <= 0) {
            b = false;
        }
        else if (n3 >= this.maxX) {
            b = false;
        }
        else {
            n2 = this.userY + this.hexDeltaY[n][0];
        }
        if (n2 <= 0) {
            b = false;
        }
        else if (n2 > this.maxY) {
            b = false;
        }
        else if (this.userPage[n2][n3] == '\0') {
            b = false;
        }
        if (b) {
            final int userX = n3 + this.hexDeltaX[n][0];
            final int userY = n2 + this.hexDeltaY[n][0];
            if (userY < this.maxY) {
                if (this.userPage[userY][userX] == '\u0001') {
                    this.graph.setColor(this.redGreenBlue[13]);
                    this.userPage[this.userY][this.userX] = '\u0003';
                }
                else {
                    this.graph.setColor(this.redGreenBlue[14]);
                    this.userPage[userY][userX] = '\u0001';
                }
                switch (userY - this.userY) {
                    case -4: {
                        userXRelative = this.userXRelative;
                        userYRelative = this.userYRelative - this.sqrt3;
                        break;
                    }
                    case -2: {
                        if (userX > this.userX) {
                            userXRelative = this.userXRelative + 1.5;
                            userYRelative = this.userYRelative - this.sqrt3 / 2.0;
                            break;
                        }
                        userXRelative = this.userXRelative - 1.5;
                        userYRelative = this.userYRelative - this.sqrt3 / 2.0;
                        break;
                    }
                    case 2: {
                        if (userX > this.userX) {
                            userXRelative = this.userXRelative + 1.5;
                            userYRelative = this.userYRelative + this.sqrt3 / 2.0;
                            break;
                        }
                        userXRelative = this.userXRelative - 1.5;
                        userYRelative = this.userYRelative + this.sqrt3 / 2.0;
                        break;
                    }
                    default: {
                        userXRelative = this.userXRelative;
                        userYRelative = this.userYRelative + this.sqrt3;
                        break;
                    }
                }
                this.drawLine(this.userXRelative, this.userYRelative, userXRelative, userYRelative);
            }
            else {
                this.graph.setColor(this.redGreenBlue[14]);
                this.drawLine(this.userXRelative, this.userYRelative, this.userXRelative, this.yMax);
                this.userHasSolved = true;
            }
            this.userX = userX;
            this.userY = userY;
            this.userXRelative = userXRelative;
            this.userYRelative = userYRelative;
        }
    }
    
    public void hexDisplayUserMoves() {
        double n = 0.0;
        int i = 2;
        double n2 = this.sqrt3 / 2.0;
        boolean b = false;
        while (i < this.maxY) {
            int j;
            if (b) {
                j = 7;
                n = 2.5;
            }
            else {
                j = 3;
                n = 1.0;
            }
            while (j < this.maxX) {
                if (this.userPage[i][j] == '\u0001' || this.userPage[i][j] == '\u0003') {
                    for (int k = 0; k < 6; ++k) {
                        final int n3 = j + this.hexDeltaX[k][0];
                        final int n4 = i + this.hexDeltaY[k][0];
                        if (this.userPage[n4][n3] != '\0') {
                            if (n4 == 0) {
                                this.graph.setColor(this.redGreenBlue[14]);
                                this.drawLine(1.0, 0.0, n, n2);
                            }
                            else if (n4 == this.maxY) {
                                if (this.userHasSolved) {
                                    this.graph.setColor(this.redGreenBlue[14]);
                                    this.drawLine(n, n2, n, n2 + this.sqrt3 / 2.0);
                                }
                            }
                            else {
                                final int n5 = n3 + this.hexDeltaX[k][0];
                                if (n5 > 0 && n5 < this.maxX) {
                                    final int n6 = n4 + this.hexDeltaY[k][0];
                                    if (n6 > 0 && n6 < this.maxY && (this.userPage[n6][n5] == '\u0001' || this.userPage[n6][n5] == '\u0003')) {
                                        if (this.userPage[i][j] == this.userPage[n6][n5]) {
                                            if (this.userPage[i][j] == '\u0001') {
                                                this.graph.setColor(this.redGreenBlue[14]);
                                            }
                                            else {
                                                this.graph.setColor(this.redGreenBlue[13]);
                                            }
                                        }
                                        else {
                                            this.graph.setColor(this.redGreenBlue[13]);
                                        }
                                        double n7 = 0.0;
                                        double n8 = 0.0;
                                        switch (n4 - i) {
                                            case -2: {
                                                n7 = n;
                                                n8 = n2 - this.sqrt3 / 2.0;
                                                break;
                                            }
                                            case -1: {
                                                if (n3 > j) {
                                                    n7 = n + 0.75;
                                                    n8 = n2 - this.sqrt3 / 4.0;
                                                    break;
                                                }
                                                n7 = n - 0.75;
                                                n8 = n2 - this.sqrt3 / 4.0;
                                                break;
                                            }
                                            case 1: {
                                                if (n3 > j) {
                                                    n7 = n + 0.75;
                                                    n8 = n2 + this.sqrt3 / 4.0;
                                                    break;
                                                }
                                                n7 = n - 0.75;
                                                n8 = n2 + this.sqrt3 / 4.0;
                                                break;
                                            }
                                            default: {
                                                n7 = n;
                                                n8 = n2 + this.sqrt3 / 2.0;
                                                break;
                                            }
                                        }
                                        this.drawLine(n, n2, n7, n8);
                                    }
                                }
                            }
                        }
                    }
                }
                n += 3.0;
                j += 8;
            }
            b = !b;
            n2 += this.sqrt3 / 2.0;
            i += 2;
        }
        if (this.userHasSolved) {
            this.graph.setColor(this.redGreenBlue[14]);
            this.drawLine(n, n2, n, this.yMax);
        }
    }
    
    private void hexSolveMaze() {
        this.numRoomsInSolution = 1;
        this.adjacency = 0;
        int n = 3;
        int n2 = 2;
        int i = -1;
        this.computerPage[n2][n] = '\u0001';
        int j = 0;
        int n3 = 0;
        do {
            int index1 = 0;
            boolean b = false;
            while (true) {
                if (index1 >= 6 || b) {
                    if (!b) {
                        index1 = this.stack[i].index1;
                        this.computerPage[n2][n] = '\u0002';
                        final int n4 = n - this.hexDeltaX[index1][0];
                        final int n5 = n2 - this.hexDeltaY[index1][0];
                        this.computerPage[n5][n4] = '\u0002';
                        n = n4 - this.hexDeltaX[index1][0];
                        n2 = n5 - this.hexDeltaY[index1][0];
                        --i;
                        ++index1;
                    }
                    if (b) {
                        break;
                    }
                    continue;
                }
                else {
                    n3 = n + this.hexDeltaX[index1][0];
                    j = n2 + this.hexDeltaY[index1][0];
                    if (this.computerPage[j][n3] == '\u0002') {
                        b = true;
                    }
                    else {
                        ++index1;
                    }
                }
            }
            this.computerPage[j][n3] = '\u0001';
            n3 += this.hexDeltaX[index1][0];
            j += this.hexDeltaY[index1][0];
            if (j <= this.maxY) {
                this.stack[++i].index1 = (short)index1;
                this.computerPage[j][n3] = '\u0001';
                n = n3;
                n2 = j;
            }
        } while (j < this.maxY);
        int n6 = this.maxX - 3;
        int n7 = this.maxY - 2;
        this.adjacency = 0;
        while (i >= 0) {
            for (int k = 0; k < 6; ++k) {
                final int n8 = n6 + this.hexDeltaX[k][0];
                final int n9 = n7 + this.hexDeltaY[k][0];
                if (this.computerPage[n9][n8] != '\u0001' && this.computerPage[n9][n8] == '\0') {
                    final int n10 = n8 + this.hexDeltaX[k][0];
                    final int n11 = n9 + this.hexDeltaY[k][0];
                    if (n10 < 0) {
                        ++this.adjacency;
                    }
                    else if (n10 > this.maxX) {
                        ++this.adjacency;
                    }
                    else if (n11 < 0) {
                        ++this.adjacency;
                    }
                    else if (n11 > this.maxY) {
                        ++this.adjacency;
                    }
                    else if (this.computerPage[n11][n10] == '\u0001') {
                        ++this.adjacency;
                    }
                }
            }
            n6 -= 2 * this.hexDeltaX[this.stack[i].index1][0];
            n7 -= 2 * this.hexDeltaY[this.stack[i--].index1][0];
            ++this.numRoomsInSolution;
        }
        for (int l = 0; l < 6; ++l) {
            final int n12 = n6 + this.hexDeltaX[l][0];
            final int n13 = n6 + this.hexDeltaY[l][0];
            if (this.computerPage[n13][n12] != '\u0002' && this.computerPage[n13][n12] == '\0') {
                final int n14 = n12 + this.hexDeltaX[l][0];
                final int n15 = n13 + this.hexDeltaY[l][0];
                if (n14 < 0) {
                    ++this.adjacency;
                }
                else if (n14 > this.maxX) {
                    ++this.adjacency;
                }
                else if (n15 < 0) {
                    ++this.adjacency;
                }
                else if (n15 > this.maxY) {
                    ++this.adjacency;
                }
                else if (this.computerPage[n15][n14] == '\u0001') {
                    ++this.adjacency;
                }
            }
        }
    }
    
    private void hexGenerateMaze(final int[] array) {
        int n = 0;
        int n2 = 0;
        final int[] array2 = { array[0] + 1, array[1] + 1, array[2] + 1, array[3] + 1, array[4] + 1, array[5] + 1, array[6] + 1, array[7] + 1 };
        int n3 = 1;
        for (int i = 0; i <= this.maxY; ++i) {
            if (n3 == 1) {
                int n4 = 1;
                for (int j = 0; j <= this.maxX; ++j) {
                    if ((n4 == 0 && i != 0 && i != this.maxY) || n4 == 3 || n4 == 4 || n4 == 5) {
                        this.computerPage[i][j] = '\0';
                    }
                    else {
                        this.computerPage[i][j] = '\u0002';
                    }
                    if (++n4 >= 8) {
                        n4 = 0;
                    }
                }
            }
            else if (n3 == 0 || n3 == 2) {
                int n5 = 1;
                for (int k = 0; k <= this.maxX; ++k) {
                    if (n5 == 2 || n5 == 6) {
                        this.computerPage[i][k] = '\0';
                    }
                    else {
                        this.computerPage[i][k] = '\u0002';
                    }
                    if (++n5 >= 8) {
                        n5 = 0;
                    }
                }
            }
            else {
                int n6 = 1;
                for (int l = 0; l <= this.maxX; ++l) {
                    if (n6 == 0 || n6 == 1 || n6 == 4 || n6 == 7) {
                        this.computerPage[i][l] = '\0';
                    }
                    else {
                        this.computerPage[i][l] = '\u0002';
                    }
                    if (++n6 >= 8) {
                        n6 = 0;
                    }
                }
            }
            if (++n3 >= 4) {
                n3 = 0;
            }
        }
        int n7 = array2[0];
        int n8 = 0;
        for (int n9 = 1; n9 < 8; n8 = n9++) {
            final int n10 = array2[n9];
            array2[n8] = n10;
            n7 += n10;
            if (n7 >= 727) {
                n7 -= 727;
            }
        }
        array2[7] = n7;
        final int n11 = n7 % this.numColumns;
        int n12 = 4 * n11 + 3;
        int n13 = array2[0];
        int n14 = 0;
        for (int n15 = 1; n15 < 8; n14 = n15++) {
            final int n16 = array2[n15];
            array2[n14] = n16;
            n13 += n16;
            if (n13 >= 727) {
                n13 -= 727;
            }
        }
        array2[7] = n13;
        int n17;
        if (n11 % 2 == 0) {
            n17 = 4 * (n13 % this.numRows) + 2;
        }
        else {
            n17 = 4 * (n13 % (this.numRows - 1)) + 4;
        }
        this.computerPage[n17][n12] = '\u0002';
        int n18 = -1;
        do {
            int index1 = 0;
            int index2;
            do {
                index2 = array2[0];
                int n19 = 0;
                for (int n20 = 1; n20 < 8; n19 = n20++) {
                    final int n21 = array2[n20];
                    array2[n19] = n21;
                    index2 += n21;
                    if (index2 >= 727) {
                        index2 -= 727;
                    }
                }
            } while ((array2[7] = index2) >= 720);
            boolean b = false;
            for (int n22 = 0; n22 == 0; n22 = ((b || (n18 == -1 && index1 >= 6)) ? 1 : 0)) {
                while (index1 < 6 && !b) {
                    n2 = n12 + 2 * this.hexDeltaX[index1][index2];
                    if (n2 <= 0) {
                        ++index1;
                    }
                    else if (n2 > this.maxX) {
                        ++index1;
                    }
                    else {
                        n = n17 + 2 * this.hexDeltaY[index1][index2];
                        if (n <= 0) {
                            ++index1;
                        }
                        else if (n > this.maxY) {
                            ++index1;
                        }
                        else if (this.computerPage[n][n2] == '\0') {
                            b = true;
                        }
                        else {
                            ++index1;
                        }
                    }
                }
                if (!b && n18 >= 0) {
                    index1 = this.stack[n18].index1;
                    index2 = this.stack[n18--].index2;
                    n12 -= 2 * this.hexDeltaX[index1][index2];
                    n17 -= 2 * this.hexDeltaY[index1++][index2];
                }
            }
            if (b) {
                this.stack[++n18].index1 = (short)index1;
                this.stack[n18].index2 = (short)index2;
                this.computerPage[n][n2] = '\u0002';
                this.computerPage[(n17 + n) / 2][(n12 + n2) / 2] = '\u0002';
                n12 = n2;
                n17 = n;
            }
        } while (n18 != -1);
        this.computerPage[0][3] = '\u0001';
        this.computerPage[this.maxY][this.maxX - 3] = '\u0002';
    }
    
    private void hexSelectMaze() {
        this.adjacency = 0;
        this.numRoomsInSolution = 0;
        final int[] array = new int[8];
        final int[] array2 = new int[8];
        this.counter0 = this.seed[0];
        this.counter1 = this.seed[1];
        this.counter2 = this.seed[2];
        this.counter3 = this.seed[3];
        this.counter4 = this.seed[4];
        this.counter5 = this.seed[5];
        this.counter6 = this.seed[6];
        this.counter7 = this.seed[7];
        this.hash();
        int adjacency = 4 * this.numRoomsInMaze + 1;
        int n = 0;
        array2[0] = this.counter0;
        array2[1] = this.counter1;
        array2[2] = this.counter2;
        array2[3] = this.counter3;
        array2[4] = this.counter4;
        array2[5] = this.counter5;
        array2[6] = this.counter6;
        array2[7] = this.counter7;
        final double n2 = new Date().getTime() / 1000.0;
        double n3;
        do {
            array[0] = this.counter0;
            array[1] = this.counter1;
            array[2] = this.counter2;
            array[3] = this.counter3;
            array[4] = this.counter4;
            array[5] = this.counter5;
            array[6] = this.counter6;
            array[7] = this.counter7;
            this.hexGenerateMaze(array);
            this.hexSolveMaze();
            if (3 * this.numRoomsInSolution >= this.numRoomsInMaze) {
                if (this.adjacency < adjacency) {
                    adjacency = this.adjacency;
                    n = this.numRoomsInSolution;
                    array2[0] = array[0];
                    array2[1] = array[1];
                    array2[2] = array[2];
                    array2[3] = array[3];
                    array2[4] = array[4];
                    array2[5] = array[5];
                    array2[6] = array[6];
                    array2[7] = array[7];
                }
                else if (this.adjacency == adjacency && this.numRoomsInSolution > n) {
                    n = this.numRoomsInSolution;
                    array2[0] = array[0];
                    array2[1] = array[1];
                    array2[2] = array[2];
                    array2[3] = array[3];
                    array2[4] = array[4];
                    array2[5] = array[5];
                    array2[6] = array[6];
                    array2[7] = array[7];
                }
            }
            this.increment();
            n3 = new Date().getTime() / 1000.0 - n2;
        } while (n3 >= 0.0 && n3 < 0.25);
        this.hexGenerateMaze(array2);
        this.hexSolveMaze();
    }
    
    public void hexDisplaySolution() {
        double n = 1.0;
        double n2 = this.sqrt3 / 2.0;
        double n3 = 0.0;
        double n4 = 0.0;
        this.graph.setColor(this.redGreenBlue[15]);
        this.drawLine(1.0, 0.0, n, n2);
        int n5 = 3;
        int n6 = -2;
        int n7 = 3;
        int n8 = 2;
        int n9 = 0;
        int i = 0;
        do {
            boolean b = false;
            int n10 = 0;
            while (!b) {
                n9 = n7 + this.hexDeltaX[n10][0];
                i = n8 + this.hexDeltaY[n10][0];
                if (this.computerPage[i][n9] == '\u0001') {
                    n9 += this.hexDeltaX[n10][0];
                    i += this.hexDeltaY[n10][0];
                    if (n9 != n5 || i != n6) {
                        b = true;
                    }
                    else {
                        ++n10;
                    }
                }
                else {
                    ++n10;
                }
            }
            if (i < this.maxY) {
                switch (i - n8) {
                    case -4: {
                        n3 = n;
                        n4 = n2 - this.sqrt3;
                        break;
                    }
                    case -2: {
                        if (n9 > n7) {
                            n3 = n + 1.5;
                            n4 = n2 - this.sqrt3 / 2.0;
                            break;
                        }
                        n3 = n - 1.5;
                        n4 = n2 - this.sqrt3 / 2.0;
                        break;
                    }
                    case 2: {
                        if (n9 > n7) {
                            n3 = n + 1.5;
                            n4 = n2 + this.sqrt3 / 2.0;
                            break;
                        }
                        n3 = n - 1.5;
                        n4 = n2 + this.sqrt3 / 2.0;
                        break;
                    }
                    default: {
                        n3 = n;
                        n4 = n2 + this.sqrt3;
                        break;
                    }
                }
                this.drawLine(n, n2, n3, n4);
            }
            else {
                this.drawLine(n, n2, n, this.yMax);
            }
            n5 = n7;
            n6 = n8;
            n7 = n9;
            n8 = i;
            n = n3;
            n2 = n4;
        } while (i < this.maxY);
    }
    
    private void hexOutputMaze() {
        final VertexRec[] array = new VertexRec[4];
        for (int i = 0; i < 4; ++i) {
            array[i] = new VertexRec();
        }
        final VertexRec[] array2 = new VertexRec[3];
        for (int j = 0; j < 3; ++j) {
            array2[j] = new VertexRec();
        }
        final VertexRec[][] array3 = new VertexRec[4][3];
        for (int k = 0; k < 4; ++k) {
            for (int l = 0; l < 3; ++l) {
                array3[k][l] = new VertexRec();
            }
        }
        switch (this.state) {
            case 0: {
                if (this.sizeChanged(false)) {
                    this.seed = new int[8];
                    long time = new Date().getTime();
                    for (int n = 0; n < 8; ++n) {
                        final long n2 = time / 10L;
                        this.seed[n] = (int)(time - 10L * n2);
                        time = n2;
                    }
                    this.numColumns = (int)(2.0 * (this.screen.width * 0.28 / (0.2 * 25.4) - 2.0 - 0.25 / this.sqrt3) / 3.0 + 1.0);
                    if (this.numColumns % 2 == 0) {
                        --this.numColumns;
                    }
                    if (this.numColumns < 3) {
                        this.numColumns = 3;
                    }
                    final double n3 = this.screen.height * 0.28;
                    final double n4 = this.screen.width * 0.28;
                    final double n5 = this.numColumns;
                    final double n6 = 0.25;
                    this.numRows = (int)((n3 / n4 * (3.0 * (n5 - 1.0) / 2.0 + 2.0 + n6 / this.sqrt3) - n6) / this.sqrt3);
                    if (this.numRows < 2) {
                        this.numRows = 2;
                    }
                    this.maxX = 8 * (this.numColumns / 2) + 6;
                    this.maxY = 4 * this.numRows;
                    this.numRoomsInMaze = this.numRows * this.numColumns - this.numColumns / 2;
                    this.computerPage = new char[this.maxY + 1][this.maxX + 1];
                    this.userPage = new char[this.maxY + 1][this.maxX + 1];
                    this.clearUserAttempts = true;
                    this.stack = new StackRec[this.numRoomsInMaze];
                    for (int n7 = 0; n7 < this.numRoomsInMaze; ++n7) {
                        this.stack[n7] = new StackRec();
                    }
                    this.hexSelectMaze();
                }
                if (this.clearUserAttempts) {
                    this.userX = 0;
                    while (this.userX <= this.maxX) {
                        this.userY = 0;
                        while (this.userY <= this.maxY) {
                            if (this.computerPage[this.userY][this.userX] == '\0') {
                                this.userPage[this.userY][this.userX] = '\0';
                            }
                            else {
                                this.userPage[this.userY][this.userX] = '\u0002';
                            }
                            ++this.userY;
                        }
                        ++this.userX;
                    }
                    this.userX = 3;
                    this.userXRelative = 1.0;
                    this.userY = 2;
                    this.userYRelative = this.sqrt3 / 2.0;
                    this.userPage[this.userY][this.userX] = '\u0001';
                    this.clearUserAttempts = false;
                }
                if (this.paint) {
                    final int[] array4 = new int[4];
                    final int[] array5 = new int[4];
                    array4[0] = 0;
                    array4[1] = (array5[0] = 0);
                    array5[1] = this.screen.height;
                    array4[2] = this.screen.width;
                    array5[2] = this.screen.height;
                    array4[3] = this.screen.width;
                    array5[3] = 0;
                    this.graph.setColor(new Color(255, 255, 255));
                    this.graph.fillPolygon(array4, array5, 4);
                    final double n8 = this.tilt * (Math.atan(1.0) / 45.0);
                    this.sinTilt = Math.sin(n8);
                    this.cosTilt = Math.cos(n8);
                    this.xMax = 3.0 * (this.numColumns - 1.0) / 2.0 + 2.0 + 0.25 / this.sqrt3;
                    this.pixelsPerX = (this.screen.width - 1.0) / (this.xMax * (this.xMax / (this.xMax - 2.0)));
                    this.xOffset = this.xMax / 2.0 * (2.0 / (this.xMax - 2.0));
                    this.yMax = this.numRows * this.sqrt3 + 0.25;
                    this.pixelsPerZ = (this.screen.height - 1.0) / Math.sqrt(this.yMax * this.yMax + 4.0);
                    if (this.yMax > this.xMax) {
                        this.relDistOfUserFromScreen = this.yMax;
                    }
                    else {
                        this.relDistOfUserFromScreen = this.xMax;
                    }
                    this.paint = false;
                }
                if (this.state == 0) {
                    this.state = 1;
                    return;
                }
                break;
            }
            case 1: {
                this.baseTriangle[0][0].x = 0.0;
                this.baseTriangle[0][0].y = 0.25 + this.sqrt3 / 2.0;
                this.baseTriangle[0][1].x = 0.0;
                this.baseTriangle[0][1].y = this.sqrt3 / 2.0;
                this.baseTriangle[0][2].x = 0.25 * this.sqrt3 / 2.0;
                this.baseTriangle[0][2].y = (0.25 + this.sqrt3) / 2.0;
                this.baseTriangle[1][0].x = (1.0 - 0.25 / this.sqrt3) / 2.0;
                this.baseTriangle[1][0].y = 0.125;
                this.baseTriangle[1][1].x = 0.5 + 0.25 / this.sqrt3;
                this.baseTriangle[1][1].y = 0.0;
                this.baseTriangle[1][2].x = this.baseTriangle[1][1].x;
                this.baseTriangle[1][2].y = 0.25;
                this.baseTriangle[2][0].x = 1.5;
                this.baseTriangle[2][0].y = 0.25;
                this.baseTriangle[2][1].x = 1.5;
                this.baseTriangle[2][1].y = 0.0;
                this.baseTriangle[2][2].x = 1.5 * (1.0 + 0.25 / this.sqrt3);
                this.baseTriangle[2][2].y = 0.125;
                this.baseTriangle[3][0].x = 2.0 - 0.25 / (2.0 * this.sqrt3);
                this.baseTriangle[3][0].y = this.baseTriangle[0][2].y;
                this.baseTriangle[3][1].x = 2.0 + 0.25 / this.sqrt3;
                this.baseTriangle[3][1].y = this.baseTriangle[0][1].y;
                this.baseTriangle[3][2].x = this.baseTriangle[3][1].x;
                this.baseTriangle[3][2].y = this.baseTriangle[0][0].y;
                this.baseRectangle[0][0].x = this.baseTriangle[0][2].x;
                this.baseRectangle[0][0].y = this.baseTriangle[0][2].y;
                this.baseRectangle[0][1].x = this.baseTriangle[1][1].x;
                this.baseRectangle[0][1].y = this.sqrt3;
                this.baseRectangle[0][2].x = this.baseTriangle[1][0].x;
                this.baseRectangle[0][2].y = this.sqrt3 + 0.125;
                this.baseRectangle[0][3].x = this.baseTriangle[0][0].x;
                this.baseRectangle[0][3].y = this.baseTriangle[0][0].y;
                this.baseRectangle[1][0].x = this.baseTriangle[0][1].x;
                this.baseRectangle[1][0].y = this.baseTriangle[0][1].y;
                this.baseRectangle[1][1].x = this.baseTriangle[1][0].x;
                this.baseRectangle[1][1].y = this.baseTriangle[1][0].y;
                this.baseRectangle[1][2].x = this.baseTriangle[1][2].x;
                this.baseRectangle[1][2].y = this.baseTriangle[1][2].y;
                this.baseRectangle[1][3].x = this.baseTriangle[0][2].x;
                this.baseRectangle[1][3].y = this.baseTriangle[0][2].y;
                this.baseRectangle[2][0].x = this.baseTriangle[1][1].x;
                this.baseRectangle[2][0].y = this.baseTriangle[1][1].y;
                this.baseRectangle[2][1].x = this.baseTriangle[2][1].x;
                this.baseRectangle[2][1].y = this.baseTriangle[2][1].y;
                this.baseRectangle[2][2].x = this.baseTriangle[2][0].x;
                this.baseRectangle[2][2].y = this.baseTriangle[2][0].y;
                this.baseRectangle[2][3].x = this.baseTriangle[1][2].x;
                this.baseRectangle[2][3].y = this.baseTriangle[1][2].y;
                this.baseRectangle[3][0].x = this.baseTriangle[2][2].x;
                this.baseRectangle[3][0].y = this.baseTriangle[2][2].y;
                this.baseRectangle[3][1].x = this.baseTriangle[3][1].x;
                this.baseRectangle[3][1].y = this.baseTriangle[3][1].y;
                this.baseRectangle[3][2].x = this.baseTriangle[3][0].x;
                this.baseRectangle[3][2].y = this.baseTriangle[3][0].y;
                this.baseRectangle[3][3].x = this.baseTriangle[2][0].x;
                this.baseRectangle[3][3].y = this.baseTriangle[2][0].y;
                this.baseRectangle[4][0].x = this.baseTriangle[3][1].x;
                this.baseRectangle[4][0].y = this.baseTriangle[3][1].y;
                this.baseRectangle[4][1].x = this.baseTriangle[3][1].x + (this.baseTriangle[2][1].x - this.baseTriangle[1][1].x);
                this.baseRectangle[4][1].y = this.baseTriangle[3][1].y;
                this.baseRectangle[4][2].x = this.baseRectangle[4][1].x;
                this.baseRectangle[4][2].y = this.baseTriangle[3][2].y;
                this.baseRectangle[4][3].x = this.baseTriangle[3][2].x;
                this.baseRectangle[4][3].y = this.baseTriangle[3][2].y;
                this.baseRectangle[5][0].x = this.baseRectangle[0][1].x + (this.baseTriangle[2][1].x - this.baseTriangle[1][1].x);
                this.baseRectangle[5][0].y = this.baseRectangle[0][1].y;
                this.baseRectangle[5][1].x = this.baseTriangle[3][0].x;
                this.baseRectangle[5][1].y = this.baseTriangle[3][0].y;
                this.baseRectangle[5][2].x = this.baseTriangle[3][2].x;
                this.baseRectangle[5][2].y = this.baseTriangle[3][2].y;
                this.baseRectangle[5][3].x = this.baseRectangle[0][2].x + (this.baseTriangle[2][2].x - this.baseTriangle[1][0].x);
                this.baseRectangle[5][3].y = this.baseRectangle[0][2].y;
                this.rectangle[0][0].x = this.baseTriangle[1][1].x;
                this.rectangle[0][0].y = this.baseTriangle[1][1].y;
                this.rectangle[0][1].x = this.xMax - this.baseTriangle[1][1].x;
                this.rectangle[0][1].y = this.baseTriangle[1][1].y;
                this.rectangle[0][2].x = this.xMax - this.baseTriangle[1][2].x;
                this.rectangle[0][2].y = this.baseTriangle[1][2].y;
                this.rectangle[0][3].x = this.baseTriangle[1][2].x;
                this.rectangle[0][3].y = this.baseTriangle[1][2].y;
                this.rectangle[1][0].x = this.baseTriangle[0][1].x;
                this.rectangle[1][0].y = this.baseTriangle[0][1].y;
                this.rectangle[1][1].x = this.xMax - this.baseTriangle[0][1].x;
                this.rectangle[1][1].y = this.baseTriangle[0][1].y;
                this.rectangle[1][2].x = this.xMax - this.baseTriangle[1][2].x;
                this.rectangle[1][2].y = this.baseTriangle[1][2].y;
                this.rectangle[1][3].x = this.baseTriangle[1][2].x;
                this.rectangle[1][3].y = this.baseTriangle[1][2].y;
                this.rectangle[2][0].x = this.baseTriangle[0][1].x;
                this.rectangle[2][0].y = this.baseTriangle[0][1].y;
                this.rectangle[2][1].x = this.xMax - this.baseTriangle[0][1].x;
                this.rectangle[2][1].y = this.baseTriangle[0][1].y;
                this.rectangle[2][2].x = this.xMax - this.baseTriangle[0][0].x;
                this.rectangle[2][2].y = this.baseTriangle[0][0].y;
                this.rectangle[2][3].x = this.baseTriangle[0][0].x;
                this.rectangle[2][3].y = this.baseTriangle[0][0].y;
                this.rectangle[3][0].x = this.baseTriangle[0][0].x;
                this.rectangle[3][0].y = this.baseTriangle[0][0].y;
                this.rectangle[3][1].x = this.xMax - this.baseTriangle[0][0].x;
                this.rectangle[3][1].y = this.baseTriangle[0][0].y;
                this.rectangle[3][2].x = this.xMax - this.baseRectangle[0][1].x;
                this.rectangle[3][2].y = this.baseRectangle[0][1].y;
                this.rectangle[3][3].x = this.baseRectangle[0][1].x;
                this.rectangle[3][3].y = this.baseRectangle[0][1].y;
                this.y = 0;
                this.state = 2;
            }
            case 2: {
                if (this.y <= this.maxY - 1) {
                    if (this.y > 0) {
                        this.displayQuadrilateral(this.rectangle[0][0].x, this.rectangle[0][0].y, 0.0, this.rectangle[0][1].x, this.rectangle[0][1].y, 0.0, this.rectangle[0][2].x, this.rectangle[0][2].y, 0.0, this.rectangle[0][3].x, this.rectangle[0][3].y, 0.0, 6);
                        this.displayQuadrilateral(this.rectangle[1][0].x, this.rectangle[1][0].y, 0.0, this.rectangle[1][1].x, this.rectangle[1][1].y, 0.0, this.rectangle[1][2].x, this.rectangle[1][2].y, 0.0, this.rectangle[1][3].x, this.rectangle[1][3].y, 0.0, 6);
                    }
                    this.displayQuadrilateral(this.rectangle[2][0].x, this.rectangle[2][0].y, 0.0, this.rectangle[2][1].x, this.rectangle[2][1].y, 0.0, this.rectangle[2][2].x, this.rectangle[2][2].y, 0.0, this.rectangle[2][3].x, this.rectangle[2][3].y, 0.0, 6);
                    if (this.y < this.maxY - 4) {
                        this.displayQuadrilateral(this.rectangle[3][0].x, this.rectangle[3][0].y, 0.0, this.rectangle[3][1].x, this.rectangle[3][1].y, 0.0, this.rectangle[3][2].x, this.rectangle[3][2].y, 0.0, this.rectangle[3][3].x, this.rectangle[3][3].y, 0.0, 6);
                    }
                    for (int n9 = 0; n9 < 4; ++n9) {
                        for (int n10 = 0; n10 < 4; ++n10) {
                            final VertexRec vertexRec = this.rectangle[n9][n10];
                            vertexRec.y += this.sqrt3;
                        }
                    }
                    this.y += 4;
                    return;
                }
                this.rectangle[0][0].x = this.baseTriangle[1][0].x;
                this.rectangle[0][0].y = this.baseTriangle[1][0].y;
                this.rectangle[0][1].x = this.baseTriangle[1][1].x;
                this.rectangle[0][1].y = this.baseTriangle[1][1].y;
                this.rectangle[0][2].x = this.baseTriangle[2][1].x;
                this.rectangle[0][2].y = this.baseTriangle[2][1].y;
                this.rectangle[0][3].x = this.baseTriangle[2][2].x;
                this.rectangle[0][3].y = this.baseTriangle[2][2].y;
                this.rectangle[1][0].x = this.baseTriangle[0][1].x;
                this.rectangle[1][0].y = this.baseTriangle[0][1].y;
                this.rectangle[1][1].x = this.baseTriangle[1][0].x;
                this.rectangle[1][1].y = this.baseTriangle[1][0].y;
                this.rectangle[1][2].x = this.baseTriangle[2][2].x;
                this.rectangle[1][2].y = this.baseTriangle[2][2].y;
                this.rectangle[1][3].x = this.baseTriangle[3][1].x;
                this.rectangle[1][3].y = this.baseTriangle[3][1].y;
                this.rectangle[2][0].x = this.baseTriangle[0][0].x;
                this.rectangle[2][0].y = this.baseTriangle[0][0].y;
                this.rectangle[2][1].x = this.baseTriangle[0][1].x;
                this.rectangle[2][1].y = this.baseTriangle[0][1].y;
                this.rectangle[2][2].x = this.baseTriangle[3][1].x;
                this.rectangle[2][2].y = this.baseTriangle[3][1].y;
                this.rectangle[2][3].x = this.baseTriangle[3][2].x;
                this.rectangle[2][3].y = this.baseTriangle[3][2].y;
                this.x = 0;
                this.state = 3;
            }
            case 3: {
                if (this.x <= this.maxX) {
                    for (int n11 = 0; n11 < 3; ++n11) {
                        this.displayQuadrilateral(this.rectangle[n11][0].x, this.rectangle[n11][0].y, 0.0, this.rectangle[n11][1].x, this.rectangle[n11][1].y, 0.0, this.rectangle[n11][2].x, this.rectangle[n11][2].y, 0.0, this.rectangle[n11][3].x, this.rectangle[n11][3].y, 0.0, 6);
                        this.displayQuadrilateral(this.rectangle[n11][0].x, this.yMax - this.rectangle[n11][0].y, 0.0, this.rectangle[n11][1].x, this.yMax - this.rectangle[n11][1].y, 0.0, this.rectangle[n11][2].x, this.yMax - this.rectangle[n11][2].y, 0.0, this.rectangle[n11][3].x, this.yMax - this.rectangle[n11][3].y, 0.0, 6);
                        for (int n12 = 0; n12 < 4; ++n12) {
                            final VertexRec vertexRec2 = this.rectangle[n11][n12];
                            vertexRec2.x += 3.0;
                        }
                    }
                    this.x += 8;
                    return;
                }
                this.yMod4 = 0;
                this.yOffset = 0.0;
                this.y = 0;
                this.state = 4;
            }
            case 4: {
                if (this.y <= this.maxY) {
                    switch (this.yMod4) {
                        case 0: {
                            int n13 = 0;
                            for (int n14 = 1; n14 <= 2; ++n14) {
                                for (int n15 = 0; n15 < 3; ++n15) {
                                    array3[n14][n15].x = this.baseTriangle[n14][n15].x;
                                    array3[n14][n15].y = this.baseTriangle[n14][n15].y + this.yOffset;
                                }
                            }
                            for (int n16 = 0; n16 < 4; ++n16) {
                                this.rectangle[2][n16].x = this.baseRectangle[2][n16].x;
                                this.rectangle[2][n16].y = this.baseRectangle[2][n16].y + this.yOffset;
                            }
                            this.x = 0;
                            while (this.x <= this.maxX) {
                                switch (n13) {
                                    case 2: {
                                        array2[0].x = array3[1][0].x;
                                        array2[0].y = array3[1][0].y;
                                        array2[1].x = array3[1][1].x;
                                        array2[1].y = array3[1][1].y;
                                        array2[2].x = array3[1][2].x;
                                        array2[2].y = array3[1][2].y;
                                        this.outputTriangle(array2, true, 3);
                                        break;
                                    }
                                    case 4: {
                                        array2[0].x = array3[2][0].x;
                                        array2[0].y = array3[2][0].y;
                                        array2[1].x = array3[2][1].x;
                                        array2[1].y = array3[2][1].y;
                                        array2[2].x = array3[2][2].x;
                                        array2[2].y = array3[2][2].y;
                                        this.outputTriangle(array2, true, 9);
                                        break;
                                    }
                                }
                                if (++n13 >= 8) {
                                    n13 = 0;
                                    for (int n17 = 1; n17 <= 2; ++n17) {
                                        for (int n18 = 0; n18 < 3; ++n18) {
                                            final VertexRec vertexRec3 = array3[n17][n18];
                                            vertexRec3.x += 3.0;
                                        }
                                    }
                                    for (int n19 = 0; n19 < 4; ++n19) {
                                        final VertexRec vertexRec4 = this.rectangle[2][n19];
                                        vertexRec4.x += 3.0;
                                    }
                                }
                                ++this.x;
                            }
                            int n20 = 0;
                            for (int n21 = 1; n21 <= 2; ++n21) {
                                for (int n22 = 0; n22 < 3; ++n22) {
                                    array3[n21][n22].x = this.baseTriangle[n21][n22].x;
                                    array3[n21][n22].y = this.baseTriangle[n21][n22].y + this.yOffset;
                                }
                            }
                            for (int n23 = 0; n23 < 4; ++n23) {
                                this.rectangle[2][n23].x = this.baseRectangle[2][n23].x;
                                this.rectangle[2][n23].y = this.baseRectangle[2][n23].y + this.yOffset;
                            }
                            this.x = 0;
                            while (this.x <= this.maxX) {
                                switch (n20) {
                                    case 2: {
                                        array2[0].x = array3[1][0].x;
                                        array2[0].y = array3[1][0].y;
                                        array2[1].x = array3[1][1].x;
                                        array2[1].y = array3[1][1].y;
                                        array2[2].x = array3[1][2].x;
                                        array2[2].y = array3[1][2].y;
                                        this.outputTriangle(array2, false, 8);
                                        break;
                                    }
                                    case 3: {
                                        if (this.computerPage[this.y][this.x] == '\0') {
                                            array[0].x = this.rectangle[2][0].x;
                                            array[0].y = this.rectangle[2][0].y;
                                            array[1].x = this.rectangle[2][1].x;
                                            array[1].y = this.rectangle[2][1].y;
                                            array[2].x = this.rectangle[2][2].x;
                                            array[2].y = this.rectangle[2][2].y;
                                            array[3].x = this.rectangle[2][3].x;
                                            array[3].y = this.rectangle[2][3].y;
                                            this.outputRectangle(array, 7);
                                            break;
                                        }
                                        break;
                                    }
                                    case 4: {
                                        array2[0].x = array3[2][0].x;
                                        array2[0].y = array3[2][0].y;
                                        array2[1].x = array3[2][1].x;
                                        array2[1].y = array3[2][1].y;
                                        array2[2].x = array3[2][2].x;
                                        array2[2].y = array3[2][2].y;
                                        this.outputTriangle(array2, false, 5);
                                        break;
                                    }
                                }
                                if (++n20 >= 8) {
                                    n20 = 0;
                                    for (int n24 = 1; n24 <= 2; ++n24) {
                                        for (int n25 = 0; n25 < 3; ++n25) {
                                            final VertexRec vertexRec5 = array3[n24][n25];
                                            vertexRec5.x += 3.0;
                                        }
                                    }
                                    for (int n26 = 0; n26 < 4; ++n26) {
                                        final VertexRec vertexRec6 = this.rectangle[2][n26];
                                        vertexRec6.x += 3.0;
                                    }
                                }
                                ++this.x;
                            }
                            break;
                        }
                        case 1: {
                            int n27 = 0;
                            for (int n28 = 1; n28 < 4; n28 += 2) {
                                for (int n29 = 0; n29 < 4; ++n29) {
                                    this.rectangle[n28][n29].x = this.baseRectangle[n28][n29].x;
                                    this.rectangle[n28][n29].y = this.baseRectangle[n28][n29].y + this.yOffset;
                                }
                            }
                            this.x = 0;
                            while (this.x <= this.maxX) {
                                switch (n27) {
                                    case 1: {
                                        if (this.computerPage[this.y][this.x] == '\0') {
                                            array[0].x = this.rectangle[1][0].x;
                                            array[0].y = this.rectangle[1][0].y;
                                            array[1].x = this.rectangle[1][1].x;
                                            array[1].y = this.rectangle[1][1].y;
                                            array[2].x = this.rectangle[1][2].x;
                                            array[2].y = this.rectangle[1][2].y;
                                            array[3].x = this.rectangle[1][3].x;
                                            array[3].y = this.rectangle[1][3].y;
                                            this.outputRectangle(array, 4);
                                            break;
                                        }
                                        break;
                                    }
                                    case 5: {
                                        if (this.computerPage[this.y][this.x] == '\0') {
                                            array[0].x = this.rectangle[3][0].x;
                                            array[0].y = this.rectangle[3][0].y;
                                            array[1].x = this.rectangle[3][1].x;
                                            array[1].y = this.rectangle[3][1].y;
                                            array[2].x = this.rectangle[3][2].x;
                                            array[2].y = this.rectangle[3][2].y;
                                            array[3].x = this.rectangle[3][3].x;
                                            array[3].y = this.rectangle[3][3].y;
                                            this.outputRectangle(array, 10);
                                            break;
                                        }
                                        break;
                                    }
                                }
                                if (++n27 >= 8) {
                                    n27 = 0;
                                    for (int n30 = 1; n30 < 4; n30 += 2) {
                                        for (int n31 = 0; n31 < 4; ++n31) {
                                            final VertexRec vertexRec7 = this.rectangle[n30][n31];
                                            vertexRec7.x += 3.0;
                                        }
                                    }
                                }
                                ++this.x;
                            }
                            break;
                        }
                        case 2: {
                            int n32 = 0;
                            for (int n33 = 0; n33 < 4; n33 += 3) {
                                for (int n34 = 0; n34 < 3; ++n34) {
                                    array3[n33][n34].x = this.baseTriangle[n33][n34].x;
                                    array3[n33][n34].y = this.baseTriangle[n33][n34].y + this.yOffset;
                                }
                            }
                            for (int n35 = 0; n35 < 4; ++n35) {
                                this.rectangle[4][n35].x = this.baseRectangle[4][n35].x;
                                this.rectangle[4][n35].y = this.baseRectangle[4][n35].y + this.yOffset;
                            }
                            this.x = 0;
                            while (this.x <= this.maxX) {
                                switch (n32) {
                                    case 0: {
                                        array2[0].x = array3[0][0].x;
                                        array2[0].y = array3[0][0].y;
                                        array2[1].x = array3[0][1].x;
                                        array2[1].y = array3[0][1].y;
                                        array2[2].x = array3[0][2].x;
                                        array2[2].y = array3[0][2].y;
                                        this.outputTriangle(array2, true, 3);
                                        break;
                                    }
                                    case 6: {
                                        array2[0].x = array3[3][0].x;
                                        array2[0].y = array3[3][0].y;
                                        array2[1].x = array3[3][1].x;
                                        array2[1].y = array3[3][1].y;
                                        array2[2].x = array3[3][2].x;
                                        array2[2].y = array3[3][2].y;
                                        this.outputTriangle(array2, true, 9);
                                        break;
                                    }
                                }
                                if (++n32 >= 8) {
                                    n32 = 0;
                                    for (int n36 = 0; n36 < 4; n36 += 3) {
                                        for (int n37 = 0; n37 < 3; ++n37) {
                                            final VertexRec vertexRec8 = array3[n36][n37];
                                            vertexRec8.x += 3.0;
                                        }
                                    }
                                    for (int n38 = 0; n38 < 4; ++n38) {
                                        final VertexRec vertexRec9 = this.rectangle[4][n38];
                                        vertexRec9.x += 3.0;
                                    }
                                }
                                ++this.x;
                            }
                            int n39 = 0;
                            for (int n40 = 0; n40 < 4; n40 += 3) {
                                for (int n41 = 0; n41 < 3; ++n41) {
                                    array3[n40][n41].x = this.baseTriangle[n40][n41].x;
                                    array3[n40][n41].y = this.baseTriangle[n40][n41].y + this.yOffset;
                                }
                            }
                            for (int n42 = 0; n42 < 4; ++n42) {
                                this.rectangle[4][n42].x = this.baseRectangle[4][n42].x;
                                this.rectangle[4][n42].y = this.baseRectangle[4][n42].y + this.yOffset;
                            }
                            this.x = 0;
                            while (this.x <= this.maxX) {
                                switch (n39) {
                                    case 0: {
                                        array2[0].x = array3[0][0].x;
                                        array2[0].y = array3[0][0].y;
                                        array2[1].x = array3[0][1].x;
                                        array2[1].y = array3[0][1].y;
                                        array2[2].x = array3[0][2].x;
                                        array2[2].y = array3[0][2].y;
                                        this.outputTriangle(array2, false, 5);
                                        break;
                                    }
                                    case 6: {
                                        array2[0].x = array3[3][0].x;
                                        array2[0].y = array3[3][0].y;
                                        array2[1].x = array3[3][1].x;
                                        array2[1].y = array3[3][1].y;
                                        array2[2].x = array3[3][2].x;
                                        array2[2].y = array3[3][2].y;
                                        this.outputTriangle(array2, false, 8);
                                        break;
                                    }
                                    case 7: {
                                        if (this.computerPage[this.y][this.x] == '\0') {
                                            array[0].x = this.rectangle[4][0].x;
                                            array[0].y = this.rectangle[4][0].y;
                                            array[1].x = this.rectangle[4][1].x;
                                            array[1].y = this.rectangle[4][1].y;
                                            array[2].x = this.rectangle[4][2].x;
                                            array[2].y = this.rectangle[4][2].y;
                                            array[3].x = this.rectangle[4][3].x;
                                            array[3].y = this.rectangle[4][3].y;
                                            this.outputRectangle(array, 7);
                                            break;
                                        }
                                        break;
                                    }
                                }
                                if (++n39 >= 8) {
                                    n39 = 0;
                                    for (int n43 = 0; n43 < 4; n43 += 3) {
                                        for (int n44 = 0; n44 < 3; ++n44) {
                                            final VertexRec vertexRec10 = array3[n43][n44];
                                            vertexRec10.x += 3.0;
                                        }
                                    }
                                    for (int n45 = 0; n45 < 4; ++n45) {
                                        final VertexRec vertexRec11 = this.rectangle[4][n45];
                                        vertexRec11.x += 3.0;
                                    }
                                }
                                ++this.x;
                            }
                            break;
                        }
                        default: {
                            int n46 = 0;
                            for (int n47 = 0; n47 < 6; n47 += 5) {
                                for (int n48 = 0; n48 < 4; ++n48) {
                                    this.rectangle[n47][n48].x = this.baseRectangle[n47][n48].x;
                                    this.rectangle[n47][n48].y = this.baseRectangle[n47][n48].y + this.yOffset;
                                }
                            }
                            this.x = 0;
                            while (this.x <= this.maxX) {
                                switch (n46) {
                                    case 1: {
                                        if (this.computerPage[this.y][this.x] == '\0') {
                                            array[0].x = this.rectangle[0][0].x;
                                            array[0].y = this.rectangle[0][0].y;
                                            array[1].x = this.rectangle[0][1].x;
                                            array[1].y = this.rectangle[0][1].y;
                                            array[2].x = this.rectangle[0][2].x;
                                            array[2].y = this.rectangle[0][2].y;
                                            array[3].x = this.rectangle[0][3].x;
                                            array[3].y = this.rectangle[0][3].y;
                                            this.outputRectangle(array, 10);
                                            break;
                                        }
                                        break;
                                    }
                                    case 5: {
                                        if (this.computerPage[this.y][this.x] == '\0') {
                                            array[0].x = this.rectangle[5][0].x;
                                            array[0].y = this.rectangle[5][0].y;
                                            array[1].x = this.rectangle[5][1].x;
                                            array[1].y = this.rectangle[5][1].y;
                                            array[2].x = this.rectangle[5][2].x;
                                            array[2].y = this.rectangle[5][2].y;
                                            array[3].x = this.rectangle[5][3].x;
                                            array[3].y = this.rectangle[5][3].y;
                                            this.outputRectangle(array, 4);
                                            break;
                                        }
                                        break;
                                    }
                                }
                                if (++n46 >= 8) {
                                    n46 = 0;
                                    for (int n49 = 0; n49 < 6; n49 += 5) {
                                        for (int n50 = 0; n50 < 4; ++n50) {
                                            final VertexRec vertexRec12 = this.rectangle[n49][n50];
                                            vertexRec12.x += 3.0;
                                        }
                                    }
                                }
                                ++this.x;
                            }
                            break;
                        }
                    }
                    if (++this.yMod4 >= 4) {
                        this.yMod4 = 0;
                        this.yOffset += this.sqrt3;
                    }
                    ++this.y;
                }
                else {
                    this.state = 5;
                }
                if (this.state != 5) {
                    break;
                }
                this.alreadyPainting = false;
                this.hexDisplayUserMoves();
                if (this.solutionDisplayed) {
                    this.hexDisplaySolution();
                    return;
                }
                break;
            }
        }
    }
    
    public void sqrKey(final int n) {
        int n2 = 0;
        double userXRelative = 0.0;
        double userYRelative = 0.0;
        boolean b = true;
        final int n3 = this.userX + this.sqrDeltaX[n][0];
        if (n3 <= 0) {
            b = false;
        }
        else if (n3 >= this.maxX) {
            b = false;
        }
        else {
            n2 = this.userY + this.sqrDeltaY[n][0];
        }
        if (n2 <= 0) {
            b = false;
        }
        else if (n2 > this.maxY) {
            b = false;
        }
        else if (this.userPage[n2][n3] == '\0') {
            b = false;
        }
        if (b) {
            final int userX = n3 + this.sqrDeltaX[n][0];
            final int userY = n2 + this.sqrDeltaY[n][0];
            if (userY < this.maxY) {
                if (this.userPage[userY][userX] == '\u0001') {
                    this.graph.setColor(this.redGreenBlue[13]);
                    this.userPage[this.userY][this.userX] = '\u0003';
                }
                else {
                    this.graph.setColor(this.redGreenBlue[14]);
                    this.userPage[userY][userX] = '\u0001';
                }
                userXRelative = this.userXRelative + this.sqrDeltaX[n][0];
                userYRelative = this.userYRelative + this.sqrDeltaY[n][0];
                this.drawLine(this.userXRelative, this.userYRelative, userXRelative, userYRelative);
            }
            else {
                this.graph.setColor(this.redGreenBlue[14]);
                this.drawLine(this.userXRelative, this.userYRelative, this.userXRelative, this.yMax);
                this.userHasSolved = true;
            }
            this.userX = userX;
            this.userY = userY;
            this.userXRelative = userXRelative;
            this.userYRelative = userYRelative;
        }
    }
    
    public void sqrDisplayUserMoves() {
        double n = 0.0;
        int i = 1;
        double n2 = 0.625;
        while (i < this.maxY) {
            int j = 1;
            n = 0.625;
            while (j < this.maxX) {
                if (this.userPage[i][j] == '\u0001' || this.userPage[i][j] == '\u0003') {
                    for (int k = 0; k < 4; ++k) {
                        final int n3 = j + this.sqrDeltaX[k][0];
                        final int n4 = i + this.sqrDeltaY[k][0];
                        if (this.userPage[n4][n3] != '\0') {
                            if (n4 == 0) {
                                this.graph.setColor(this.redGreenBlue[14]);
                                this.drawLine(n, 0.125, n, n2);
                            }
                            else if (n4 == this.maxY) {
                                if (this.userHasSolved) {
                                    this.graph.setColor(this.redGreenBlue[14]);
                                    this.drawLine(n, n2, n, this.yMax);
                                }
                            }
                            else {
                                final int n5 = n3 + this.sqrDeltaX[k][0];
                                if (n5 > 0 && n5 < this.maxX) {
                                    final int n6 = n4 + this.sqrDeltaY[k][0];
                                    if (n6 > 0 && n6 < this.maxY && (this.userPage[n6][n5] == '\u0001' || this.userPage[n6][n5] == '\u0003')) {
                                        if (this.userPage[i][j] == this.userPage[n6][n5]) {
                                            if (this.userPage[i][j] == '\u0001') {
                                                this.graph.setColor(this.redGreenBlue[14]);
                                            }
                                            else {
                                                this.graph.setColor(this.redGreenBlue[13]);
                                            }
                                        }
                                        else {
                                            this.graph.setColor(this.redGreenBlue[13]);
                                        }
                                        this.drawLine(n, n2, n + this.sqrDeltaX[k][0] / 2.0, n2 + this.sqrDeltaY[k][0] / 2.0);
                                    }
                                }
                            }
                        }
                    }
                }
                ++n;
                j += 2;
            }
            ++n2;
            i += 2;
        }
        if (this.userHasSolved) {
            this.graph.setColor(this.redGreenBlue[14]);
            this.drawLine(n, n2, n, this.yMax);
        }
    }
    
    private void sqrSolveMaze() {
        this.numRoomsInSolution = 1;
        this.adjacency = 0;
        int n = 1;
        int n2 = 1;
        int i = -1;
        this.computerPage[n2][n] = '\u0001';
        int j = 0;
        int n3 = 0;
        do {
            int index1 = 0;
            boolean b = false;
            while (true) {
                if (index1 >= 4 || b) {
                    if (!b) {
                        index1 = this.stack[i].index1;
                        this.computerPage[n2][n] = '\u0002';
                        final int n4 = n - this.sqrDeltaX[index1][0];
                        final int n5 = n2 - this.sqrDeltaY[index1][0];
                        this.computerPage[n5][n4] = '\u0002';
                        n = n4 - this.sqrDeltaX[index1][0];
                        n2 = n5 - this.sqrDeltaY[index1][0];
                        --i;
                        ++index1;
                    }
                    if (b) {
                        break;
                    }
                    continue;
                }
                else {
                    n3 = n + this.sqrDeltaX[index1][0];
                    j = n2 + this.sqrDeltaY[index1][0];
                    if (this.computerPage[j][n3] == '\u0002') {
                        b = true;
                    }
                    else {
                        ++index1;
                    }
                }
            }
            this.computerPage[j][n3] = '\u0001';
            n3 += this.sqrDeltaX[index1][0];
            j += this.sqrDeltaY[index1][0];
            if (j <= this.maxY) {
                this.stack[++i].index1 = (short)index1;
                this.computerPage[j][n3] = '\u0001';
                n = n3;
                n2 = j;
            }
        } while (j < this.maxY);
        int n6 = this.maxX - 1;
        int n7 = this.maxY - 1;
        this.adjacency = 0;
        while (i >= 0) {
            for (int k = 0; k < 4; ++k) {
                final int n8 = n6 + this.sqrDeltaX[k][0];
                final int n9 = n7 + this.sqrDeltaY[k][0];
                if (this.computerPage[n9][n8] != '\u0001' && this.computerPage[n9][n8] == '\0') {
                    final int n10 = n8 + this.sqrDeltaX[k][0];
                    final int n11 = n9 + this.sqrDeltaY[k][0];
                    if (n10 < 0) {
                        ++this.adjacency;
                    }
                    else if (n10 > this.maxX) {
                        ++this.adjacency;
                    }
                    else if (n11 < 0) {
                        ++this.adjacency;
                    }
                    else if (n11 > this.maxY) {
                        ++this.adjacency;
                    }
                    else if (this.computerPage[n11][n10] == '\u0001') {
                        ++this.adjacency;
                    }
                }
            }
            n6 -= 2 * this.sqrDeltaX[this.stack[i].index1][0];
            n7 -= 2 * this.sqrDeltaY[this.stack[i--].index1][0];
            ++this.numRoomsInSolution;
        }
        for (int l = 0; l < 4; ++l) {
            final int n12 = n6 + this.sqrDeltaX[l][0];
            final int n13 = n6 + this.sqrDeltaY[l][0];
            if (this.computerPage[n13][n12] != '\u0002' && this.computerPage[n13][n12] == '\0') {
                final int n14 = n12 + this.sqrDeltaX[l][0];
                final int n15 = n13 + this.sqrDeltaY[l][0];
                if (n14 < 0) {
                    ++this.adjacency;
                }
                else if (n14 > this.maxX) {
                    ++this.adjacency;
                }
                else if (n15 < 0) {
                    ++this.adjacency;
                }
                else if (n15 > this.maxY) {
                    ++this.adjacency;
                }
                else if (this.computerPage[n15][n14] == '\u0001') {
                    ++this.adjacency;
                }
            }
        }
    }
    
    private void sqrGenerateMaze(final int[] array) {
        int n = 0;
        int n2 = 0;
        final int[] array2 = { array[0] + 1, array[1] + 1, array[2] + 1, array[3] + 1, array[4] + 1, array[5] + 1, array[6] + 1, array[7] + 1 };
        for (int i = 0; i <= this.maxY; ++i) {
            for (int j = 0; j <= this.maxX; ++j) {
                this.computerPage[i][j] = '\0';
            }
        }
        int n3 = 0;
        for (int k = 1; k <= 3; ++k) {
            int n4 = array2[0];
            int n5 = 0;
            for (int l = 1; l < 8; n5 = l++) {
                final int n6 = array2[l];
                array2[n5] = n6;
                n4 += n6;
                if (n4 >= 29) {
                    n4 -= 29;
                }
            }
            array2[7] = n4;
            n3 = 29 * n3 + n4;
        }
        int n7 = 2 * (n3 % this.numColumns) + 1;
        int n8 = 0;
        for (int n9 = 1; n9 <= 3; ++n9) {
            int n10 = array2[0];
            int n11 = 0;
            for (int n12 = 1; n12 < 8; n11 = n12++) {
                final int n13 = array2[n12];
                array2[n11] = n13;
                n10 += n13;
                if (n10 >= 29) {
                    n10 -= 29;
                }
            }
            array2[7] = n10;
            n8 = 29 * n8 + n10;
        }
        int n14 = 2 * (n8 % this.numRows) + 1;
        this.computerPage[n14][n7] = '\u0002';
        int n15 = -1;
        do {
            int index1 = 0;
            int index2;
            do {
                index2 = array2[0];
                int n16 = 0;
                for (int n17 = 1; n17 < 8; n16 = n17++) {
                    final int n18 = array2[n17];
                    array2[n16] = n18;
                    index2 += n18;
                    if (index2 >= 29) {
                        index2 -= 29;
                    }
                }
            } while ((array2[7] = index2) >= 24);
            boolean b = false;
            for (int n19 = 0; n19 == 0; n19 = ((b || (n15 == -1 && index1 >= 4)) ? 1 : 0)) {
                while (index1 < 4 && !b) {
                    n2 = n7 + 2 * this.sqrDeltaX[index1][index2];
                    if (n2 <= 0) {
                        ++index1;
                    }
                    else if (n2 > this.maxX) {
                        ++index1;
                    }
                    else {
                        n = n14 + 2 * this.sqrDeltaY[index1][index2];
                        if (n <= 0) {
                            ++index1;
                        }
                        else if (n > this.maxY) {
                            ++index1;
                        }
                        else if (this.computerPage[n][n2] == '\0') {
                            b = true;
                        }
                        else {
                            ++index1;
                        }
                    }
                }
                if (!b && n15 >= 0) {
                    index1 = this.stack[n15].index1;
                    index2 = this.stack[n15--].index2;
                    n7 -= 2 * this.sqrDeltaX[index1][index2];
                    n14 -= 2 * this.sqrDeltaY[index1++][index2];
                }
            }
            if (b) {
                this.stack[++n15].index1 = (short)index1;
                this.stack[n15].index2 = (short)index2;
                this.computerPage[n][n2] = '\u0002';
                this.computerPage[(n14 + n) / 2][(n7 + n2) / 2] = '\u0002';
                n7 = n2;
                n14 = n;
            }
        } while (n15 != -1);
        this.computerPage[0][1] = '\u0001';
        this.computerPage[this.maxY][this.maxX - 1] = '\u0002';
    }
    
    private void sqrSelectMaze() {
        this.adjacency = 0;
        this.numRoomsInSolution = 0;
        final int[] array = new int[8];
        final int[] array2 = new int[8];
        this.counter0 = this.seed[0];
        this.counter1 = this.seed[1];
        this.counter2 = this.seed[2];
        this.counter3 = this.seed[3];
        this.counter4 = this.seed[4];
        this.counter5 = this.seed[5];
        this.counter6 = this.seed[6];
        this.counter7 = this.seed[7];
        this.hash();
        int adjacency = 2 * this.numRoomsInMaze + 1;
        int n = 0;
        array2[0] = this.counter0;
        array2[1] = this.counter1;
        array2[2] = this.counter2;
        array2[3] = this.counter3;
        array2[4] = this.counter4;
        array2[5] = this.counter5;
        array2[6] = this.counter6;
        array2[7] = this.counter7;
        final double n2 = new Date().getTime() / 1000.0;
        double n3;
        do {
            array[0] = this.counter0;
            array[1] = this.counter1;
            array[2] = this.counter2;
            array[3] = this.counter3;
            array[4] = this.counter4;
            array[5] = this.counter5;
            array[6] = this.counter6;
            array[7] = this.counter7;
            this.sqrGenerateMaze(array);
            this.sqrSolveMaze();
            if (3 * this.numRoomsInSolution >= this.numRoomsInMaze) {
                if (this.adjacency < adjacency) {
                    adjacency = this.adjacency;
                    n = this.numRoomsInSolution;
                    array2[0] = array[0];
                    array2[1] = array[1];
                    array2[2] = array[2];
                    array2[3] = array[3];
                    array2[4] = array[4];
                    array2[5] = array[5];
                    array2[6] = array[6];
                    array2[7] = array[7];
                }
                else if (this.adjacency == adjacency && this.numRoomsInSolution > n) {
                    n = this.numRoomsInSolution;
                    array2[0] = array[0];
                    array2[1] = array[1];
                    array2[2] = array[2];
                    array2[3] = array[3];
                    array2[4] = array[4];
                    array2[5] = array[5];
                    array2[6] = array[6];
                    array2[7] = array[7];
                }
            }
            this.increment();
            n3 = new Date().getTime() / 1000.0 - n2;
        } while (n3 >= 0.0 && n3 < 0.25);
        this.sqrGenerateMaze(array2);
        this.sqrSolveMaze();
    }
    
    public void sqrDisplaySolution() {
        double n = 0.625;
        double n2 = 0.625;
        double n3 = 0.0;
        double n4 = 0.0;
        this.graph.setColor(this.redGreenBlue[15]);
        this.drawLine(n, 0.125, n, n2);
        int n5 = 1;
        int n6 = -1;
        int n7 = 1;
        int n8 = 1;
        int n9 = 0;
        int i = 0;
        do {
            boolean b = false;
            int n10 = 0;
            while (!b) {
                n9 = n7 + this.sqrDeltaX[n10][0];
                i = n8 + this.sqrDeltaY[n10][0];
                if (this.computerPage[i][n9] == '\u0001') {
                    n9 += this.sqrDeltaX[n10][0];
                    i += this.sqrDeltaY[n10][0];
                    if (n9 != n5 || i != n6) {
                        b = true;
                    }
                    else {
                        ++n10;
                    }
                }
                else {
                    ++n10;
                }
            }
            if (i < this.maxY) {
                n3 = n + this.sqrDeltaX[n10][0];
                n4 = n2 + this.sqrDeltaY[n10][0];
                this.drawLine(n, n2, n3, n4);
            }
            else {
                this.drawLine(n, n2, n, this.yMax);
            }
            n5 = n7;
            n6 = n8;
            n7 = n9;
            n8 = i;
            n = n3;
            n2 = n4;
        } while (i < this.maxY);
    }
    
    private void sqrOutputMaze() {
        final VertexRec[] array = new VertexRec[4];
        for (int i = 0; i < 4; ++i) {
            array[i] = new VertexRec();
        }
        switch (this.state) {
            case 0: {
                if (this.sizeChanged(false)) {
                    this.seed = new int[8];
                    long time = new Date().getTime();
                    for (int j = 0; j < 8; ++j) {
                        final long n = time / 10L;
                        this.seed[j] = (int)(time - 10L * n);
                        time = n;
                    }
                    this.numColumns = (int)(this.screen.width * 0.28 / (0.2 * 25.4) - 0.25);
                    if (this.numColumns < 2) {
                        this.numColumns = 2;
                    }
                    this.numRows = (int)(this.screen.height * 0.28 * this.numColumns / (this.screen.width * 0.28));
                    if (this.numRows < 2) {
                        this.numRows = 2;
                    }
                    this.maxX = 2 * this.numColumns;
                    this.maxY = 2 * this.numRows;
                    this.numRoomsInMaze = this.numRows * this.numColumns;
                    this.computerPage = new char[this.maxY + 1][this.maxX + 1];
                    this.userPage = new char[this.maxY + 1][this.maxX + 1];
                    this.clearUserAttempts = true;
                    this.stack = new StackRec[this.numRoomsInMaze];
                    for (int k = 0; k < this.numRoomsInMaze; ++k) {
                        this.stack[k] = new StackRec();
                    }
                    this.sqrSelectMaze();
                }
                if (this.clearUserAttempts) {
                    this.userX = 0;
                    while (this.userX <= this.maxX) {
                        this.userY = 0;
                        while (this.userY <= this.maxY) {
                            if (this.computerPage[this.userY][this.userX] == '\0') {
                                this.userPage[this.userY][this.userX] = '\0';
                            }
                            else {
                                this.userPage[this.userY][this.userX] = '\u0002';
                            }
                            ++this.userY;
                        }
                        ++this.userX;
                    }
                    this.userX = 1;
                    this.userXRelative = 0.625;
                    this.userY = 1;
                    this.userYRelative = 0.625;
                    this.userPage[this.userY][this.userX] = '\u0001';
                    this.clearUserAttempts = false;
                }
                if (this.paint) {
                    final int[] array2 = new int[4];
                    final int[] array3 = new int[4];
                    array2[0] = 0;
                    array2[1] = (array3[0] = 0);
                    array3[1] = this.screen.height;
                    array2[2] = this.screen.width;
                    array3[2] = this.screen.height;
                    array2[3] = this.screen.width;
                    array3[3] = 0;
                    this.graph.setColor(new Color(255, 255, 255));
                    this.graph.fillPolygon(array2, array3, 4);
                    final double n2 = this.tilt * (Math.atan(1.0) / 45.0);
                    this.sinTilt = Math.sin(n2);
                    this.cosTilt = Math.cos(n2);
                    this.xMax = this.numColumns + 0.25;
                    this.pixelsPerX = (this.screen.width - 1.0) / (this.xMax * (this.xMax / (this.xMax - 2.0)));
                    this.xOffset = this.xMax / 2.0 * (2.0 / (this.xMax - 2.0));
                    this.yMax = this.numRows + 0.25;
                    this.pixelsPerZ = (this.screen.height - 1.0) / Math.sqrt(this.yMax * this.yMax + 4.0);
                    if (this.yMax > this.xMax) {
                        this.relDistOfUserFromScreen = this.yMax;
                    }
                    else {
                        this.relDistOfUserFromScreen = this.xMax;
                    }
                    this.paint = false;
                }
                if (this.state == 0) {
                    this.state = 1;
                    return;
                }
                break;
            }
            case 1: {
                this.baseRectangle[0][0].x = 0.0;
                this.baseRectangle[0][0].y = 0.0;
                this.baseRectangle[0][1].x = 0.25;
                this.baseRectangle[0][1].y = 0.0;
                this.baseRectangle[0][2].x = 0.25;
                this.baseRectangle[0][2].y = 0.25;
                this.baseRectangle[0][3].x = 0.0;
                this.baseRectangle[0][3].y = 0.25;
                this.baseRectangle[1][0].x = 0.25;
                this.baseRectangle[1][0].y = 0.0;
                this.baseRectangle[1][1].x = 1.0;
                this.baseRectangle[1][1].y = 0.0;
                this.baseRectangle[1][2].x = 1.0;
                this.baseRectangle[1][2].y = 0.25;
                this.baseRectangle[1][3].x = 0.25;
                this.baseRectangle[1][3].y = 0.25;
                this.baseRectangle[2][0].x = 0.25;
                this.baseRectangle[2][0].y = 0.25;
                this.baseRectangle[2][1].x = 1.0;
                this.baseRectangle[2][1].y = 0.25;
                this.baseRectangle[2][2].x = 1.0;
                this.baseRectangle[2][2].y = 1.0;
                this.baseRectangle[2][3].x = 0.25;
                this.baseRectangle[2][3].y = 1.0;
                this.baseRectangle[3][0].x = 0.0;
                this.baseRectangle[3][0].y = 0.25;
                this.baseRectangle[3][1].x = 0.25;
                this.baseRectangle[3][1].y = 0.25;
                this.baseRectangle[3][2].x = 0.25;
                this.baseRectangle[3][2].y = 1.0;
                this.baseRectangle[3][3].x = 0.0;
                this.baseRectangle[3][3].y = 1.0;
                this.rectangle[0][0].x = 0.0;
                this.rectangle[0][0].y = 0.0;
                this.rectangle[0][1].x = this.xMax;
                this.rectangle[0][1].y = 0.0;
                this.rectangle[0][2].x = this.xMax;
                this.rectangle[0][2].y = this.yMax;
                this.rectangle[0][3].x = 0.0;
                this.rectangle[0][3].y = this.yMax;
                this.displayQuadrilateral(this.rectangle[0][0].x, this.rectangle[0][0].y, 0.0, this.rectangle[0][1].x, this.rectangle[0][1].y, 0.0, this.rectangle[0][2].x, this.rectangle[0][2].y, 0.0, this.rectangle[0][3].x, this.rectangle[0][3].y, 0.0, 6);
                this.y = 0;
                this.yOffset = 0.0;
                this.state = 4;
            }
            case 4: {
                if (this.y <= this.maxY) {
                    for (int l = 0; l < 4; ++l) {
                        this.rectangle[0][l].x = this.baseRectangle[0][l].x;
                        this.rectangle[0][l].y = this.baseRectangle[0][l].y + this.yOffset;
                    }
                    this.x = 0;
                    while (this.x <= this.maxX) {
                        if (this.computerPage[this.y][this.x] == '\0') {
                            array[0].x = this.rectangle[0][0].x;
                            array[0].y = this.rectangle[0][0].y;
                            array[1].x = this.rectangle[0][1].x;
                            array[1].y = this.rectangle[0][1].y;
                            array[2].x = this.rectangle[0][2].x;
                            array[2].y = this.rectangle[0][2].y;
                            array[3].x = this.rectangle[0][3].x;
                            array[3].y = this.rectangle[0][3].y;
                            this.outputLeftRight(array);
                        }
                        for (int n3 = 0; n3 < 4; ++n3) {
                            final VertexRec vertexRec = this.rectangle[0][n3];
                            ++vertexRec.x;
                        }
                        this.x += 2;
                    }
                    for (int n4 = 0; n4 < 4; ++n4) {
                        this.rectangle[0][n4].x = this.baseRectangle[0][n4].x;
                        this.rectangle[0][n4].y = this.baseRectangle[0][n4].y + this.yOffset;
                    }
                    for (int n5 = 0; n5 < 4; ++n5) {
                        this.rectangle[1][n5].x = this.baseRectangle[1][n5].x;
                        this.rectangle[1][n5].y = this.baseRectangle[1][n5].y + this.yOffset;
                    }
                    this.x = 0;
                    while (this.x <= this.maxX) {
                        if (this.computerPage[this.y][this.x] == '\0') {
                            array[0].x = this.rectangle[0][0].x;
                            array[0].y = this.rectangle[0][0].y;
                            array[1].x = this.rectangle[0][1].x;
                            array[1].y = this.rectangle[0][1].y;
                            array[2].x = this.rectangle[0][2].x;
                            array[2].y = this.rectangle[0][2].y;
                            array[3].x = this.rectangle[0][3].x;
                            array[3].y = this.rectangle[0][3].y;
                            this.outputRectangle(array, 7);
                        }
                        for (int n6 = 0; n6 < 4; ++n6) {
                            final VertexRec vertexRec2 = this.rectangle[0][n6];
                            ++vertexRec2.x;
                        }
                        ++this.x;
                        if (this.x <= this.maxX) {
                            if (this.computerPage[this.y][this.x] == '\0') {
                                array[0].x = this.rectangle[1][0].x;
                                array[0].y = this.rectangle[1][0].y;
                                array[1].x = this.rectangle[1][1].x;
                                array[1].y = this.rectangle[1][1].y;
                                array[2].x = this.rectangle[1][2].x;
                                array[2].y = this.rectangle[1][2].y;
                                array[3].x = this.rectangle[1][3].x;
                                array[3].y = this.rectangle[1][3].y;
                                this.outputRectangle(array, 7);
                            }
                            for (int n7 = 0; n7 < 4; ++n7) {
                                final VertexRec vertexRec3 = this.rectangle[1][n7];
                                ++vertexRec3.x;
                            }
                            ++this.x;
                        }
                    }
                    ++this.y;
                    if (this.y <= this.maxY) {
                        for (int n8 = 0; n8 < 4; ++n8) {
                            this.rectangle[3][n8].x = this.baseRectangle[3][n8].x;
                            this.rectangle[3][n8].y = this.baseRectangle[3][n8].y + this.yOffset;
                        }
                        this.x = 0;
                        while (this.x <= this.maxX) {
                            if (this.computerPage[this.y][this.x] == '\0') {
                                array[0].x = this.rectangle[3][0].x;
                                array[0].y = this.rectangle[3][0].y;
                                array[1].x = this.rectangle[3][1].x;
                                array[1].y = this.rectangle[3][1].y;
                                array[2].x = this.rectangle[3][2].x;
                                array[2].y = this.rectangle[3][2].y;
                                array[3].x = this.rectangle[3][3].x;
                                array[3].y = this.rectangle[3][3].y;
                                this.outputLeftRight(array);
                            }
                            for (int n9 = 0; n9 < 4; ++n9) {
                                final VertexRec vertexRec4 = this.rectangle[3][n9];
                                ++vertexRec4.x;
                            }
                            this.x += 2;
                        }
                        for (int n10 = 0; n10 < 4; ++n10) {
                            this.rectangle[3][n10].x = this.baseRectangle[3][n10].x;
                            this.rectangle[3][n10].y = this.baseRectangle[3][n10].y + this.yOffset;
                        }
                        this.x = 0;
                        while (this.x <= this.maxX) {
                            if (this.computerPage[this.y][this.x] == '\0') {
                                array[0].x = this.rectangle[3][0].x;
                                array[0].y = this.rectangle[3][0].y;
                                array[1].x = this.rectangle[3][1].x;
                                array[1].y = this.rectangle[3][1].y;
                                array[2].x = this.rectangle[3][2].x;
                                array[2].y = this.rectangle[3][2].y;
                                array[3].x = this.rectangle[3][3].x;
                                array[3].y = this.rectangle[3][3].y;
                                this.outputRectangle(array, 7);
                            }
                            for (int n11 = 0; n11 < 4; ++n11) {
                                final VertexRec vertexRec5 = this.rectangle[3][n11];
                                ++vertexRec5.x;
                            }
                            this.x += 2;
                        }
                        ++this.y;
                    }
                    ++this.yOffset;
                }
                else {
                    this.state = 5;
                }
                if (this.state != 5) {
                    break;
                }
                this.alreadyPainting = false;
                this.sqrDisplayUserMoves();
                if (this.solutionDisplayed) {
                    this.sqrDisplaySolution();
                    return;
                }
                break;
            }
        }
    }
    
    private synchronized boolean sizeChanged(final boolean resize) {
        final boolean resize2 = this.resize;
        this.resize = resize;
        return resize2;
    }
    
    public synchronized boolean startOver(final boolean restart) {
        final boolean restart2 = this.restart;
        this.restart = restart;
        if (this.restart && this.mazeCanvas.resize) {
            this.sizeChanged(true);
        }
        return restart2;
    }
    
    public void run() {
        do {
            if (this.state < 5) {
                if (this.hexagonalRooms) {
                    this.hexOutputMaze();
                }
                else {
                    this.sqrOutputMaze();
                }
            }
            Thread.yield();
            if (this.startOver(false)) {
                this.graph = this.mazeCanvas.getGraphics().create();
                this.screen = new Rectangle(this.mazeCanvas.rectangle.x, this.mazeCanvas.rectangle.y, this.mazeCanvas.rectangle.width, this.mazeCanvas.rectangle.height);
                if (this.resize) {
                    this.userHasSolved = false;
                }
                this.hexagonalRooms = this.mazeCanvas.maze3D.hexagonalRooms;
                this.solutionDisplayed = this.mazeCanvas.maze3D.solutionDisplayed;
                if (this.mazeCanvas.maze3D.clearUserAttempts) {
                    this.clearUserAttempts = true;
                    this.mazeCanvas.maze3D.clearUserAttempts = false;
                    this.userHasSolved = false;
                }
                this.tilt = this.mazeCanvas.maze3D.tilt;
                this.paint = true;
                this.alreadyPainting = true;
                this.state = 0;
            }
        } while (this.state < 6);
    }
    
    static {
        SUBSTITUTION_HIGH = new int[] { 4, 1, 2, 8, 8, 9, 9, 6, 5, 7, 2, 1, 2, 9, 8, 8, 6, 3, 5, 1, 9, 5, 4, 4, 9, 8, 6, 0, 8, 0, 6, 0, 2, 4, 1, 9, 2, 0, 7, 4, 7, 3, 0, 0, 2, 6, 8, 9, 4, 0, 8, 3, 2, 3, 2, 5, 2, 4, 6, 9, 7, 9, 1, 3, 5, 7, 1, 1, 4, 5, 8, 1, 6, 0, 5, 7, 8, 2, 3, 3, 7, 3, 5, 1, 7, 5, 4, 0, 3, 6, 3, 7, 7, 1, 9, 4, 0, 5, 6, 6 };
        SUBSTITUTION_LOW = new int[] { 1, 2, 2, 1, 5, 5, 4, 6, 4, 6, 4, 4, 5, 6, 6, 3, 0, 9, 6, 5, 7, 2, 0, 9, 3, 4, 2, 3, 9, 1, 9, 9, 9, 3, 8, 9, 3, 4, 1, 5, 0, 5, 2, 7, 0, 8, 8, 0, 4, 5, 0, 3, 6, 8, 1, 7, 8, 8, 7, 1, 3, 2, 7, 7, 1, 8, 0, 3, 7, 5, 2, 6, 4, 0, 9, 9, 7, 7, 4, 6, 2, 0, 0, 1, 7, 3, 6, 6, 1, 1, 2, 4, 5, 9, 8, 2, 8, 8, 3, 5 };
    }
}
