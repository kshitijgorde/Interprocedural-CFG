import java.awt.Point;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Label;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Bridges extends Applet
{
    public static final int MAXSCREENX = 340;
    public static final int MAXSCREENY = 340;
    public static final int MAXROWS = 17;
    public static final int MAXCOLS = 17;
    public static final int NOTOWER = 0;
    public static final int BLUETOWER = 1;
    public static final int BLUE = 1;
    public static final int REDTOWER = 2;
    public static final int RED = 2;
    public static final int ROW = 0;
    public static final int COL = 1;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    public static final int DOWN = 4;
    public static int[][] board;
    public static int whoseTurn;
    static BridgeCanvas cnv1;
    static Label turnMsg;
    static Label statusMsg;
    Button newGameButton;
    Panel p;
    Panel p1;
    static boolean redWins;
    static boolean blueWins;
    static boolean debug;
    public static final int MAXSTEPS = 10000;
    public static final int MAXOPTIONS = 200;
    public static final int MAXPATHLIST = 32;
    public static boolean[][] visited;
    public static boolean[][] consider;
    public static Option[] optionList;
    public static Step[] steps;
    public static int[][] minSteps;
    public static PathList[] bluePathList;
    public static PathList[] redPathList;
    public static int optionTop;
    public static int firstFreeStep;
    public static int bluePathListTop;
    public static int redPathListTop;
    public static double stepCounter;
    public static double maxSteps;
    public static double maxOptions;
    public static double maxPathList;
    public static double maxMoves;
    public static final int COUNT = 2;
    public static final int CLOSEST = 3;
    public static final int MAXDIST = 289;
    
    public void init() {
        this.setLayout(null);
        this.resize(340, 400);
        this.addNotify();
        Bridges.debug = false;
        this.add(Bridges.cnv1 = new BridgeCanvas());
        Bridges.cnv1.reshape(0, 0, 340, 340);
        this.p = new Panel();
        (Bridges.turnMsg = new Label("")).setAlignment(1);
        this.p.add(Bridges.turnMsg);
        Bridges.whoseTurn = 1;
        toggleTurnMsg();
        this.p.reshape(0, 340, 340, 30);
        this.add(this.p);
        this.p1 = new Panel();
        this.newGameButton = new Button("New Game");
        this.p1.add(this.newGameButton);
        if (Bridges.debug) {
            (Bridges.statusMsg = new Label("")).setAlignment(1);
            this.p1.add(Bridges.statusMsg);
        }
        this.p1.reshape(0, 370, 340, 30);
        this.add(this.p1);
        this.initBoard();
        setupMem();
        Bridges.cnv1.repaint();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.newGameButton) {
            this.initBoard();
            Bridges.cnv1.repaint();
        }
        return true;
    }
    
    public void destroy() {
    }
    
    public void initBoard() {
        Bridges.redWins = false;
        Bridges.blueWins = false;
        Bridges.whoseTurn = 1;
        toggleTurnMsg();
        if (Bridges.debug) {
            Bridges.statusMsg.setText("");
        }
        Bridges.maxSteps = 0.0;
        Bridges.maxOptions = 0.0;
        Bridges.maxPathList = 0.0;
        Bridges.maxMoves = 0.0;
        Bridges.board = new int[17][17];
        for (int i = 0; i < 17; ++i) {
            for (int j = 0; j < 17; ++j) {
                Bridges.board[i][j] = 0;
                if (i % 2 == 0) {
                    if (j % 2 == 1) {
                        Bridges.board[i][j] = 1;
                    }
                }
                else if (j % 2 == 0) {
                    Bridges.board[i][j] = 2;
                }
            }
        }
    }
    
    public static void toggleTurnMsg() {
        if (Bridges.whoseTurn == 1) {
            Bridges.whoseTurn = 2;
            Bridges.turnMsg.setText("Red - It is your Move");
            return;
        }
        Bridges.whoseTurn = 1;
        Bridges.turnMsg.setText("Blue - It is your Move");
    }
    
    public static boolean toggleBox(final int n, final int n2) {
        boolean b;
        if (Bridges.board[n][n2] == 0) {
            b = true;
            if (Bridges.whoseTurn == 1) {
                Bridges.board[n][n2] = 1;
                toggleTurnMsg();
                determineBlueProgress();
            }
            else {
                Bridges.board[n][n2] = 2;
                toggleTurnMsg();
                determineRedProgress();
            }
        }
        else {
            b = false;
        }
        return b;
    }
    
    public static void determineBlueProgress() {
        Bridges.visited = new boolean[17][17];
        for (int i = 0; i < 17; ++i) {
            for (int j = 0; j < 17; ++j) {
                Bridges.visited[i][j] = false;
            }
        }
        final int[][] array = new int[289][2];
        Bridges.blueWins = false;
        for (int n = 1; n < 17 && !Bridges.blueWins; n += 2) {
            if (!Bridges.visited[0][n]) {
                int n2 = 0;
                int n3 = 0;
                array[n2][0] = 0;
                array[n2++][1] = n;
                while (n3 < n2 && !Bridges.blueWins) {
                    final int n4 = array[n3][0];
                    final int n5 = array[n3][1];
                    Bridges.visited[n4][n5] = true;
                    if (n4 == 16) {
                        Bridges.blueWins = true;
                    }
                    else {
                        if (n4 - 1 >= 0 && !Bridges.visited[n4 - 1][n5] && Bridges.board[n4 - 1][n5] == 1) {
                            array[n2][0] = n4 - 1;
                            array[n2++][1] = n5;
                        }
                        if (!Bridges.visited[n4 + 1][n5] && Bridges.board[n4 + 1][n5] == 1) {
                            array[n2][0] = n4 + 1;
                            array[n2++][1] = n5;
                        }
                        if (n5 - 1 >= 0 && !Bridges.visited[n4][n5 - 1] && Bridges.board[n4][n5 - 1] == 1) {
                            array[n2][0] = n4;
                            array[n2++][1] = n5 - 1;
                        }
                        if (n5 + 1 < 17 && !Bridges.visited[n4][n5 + 1] && Bridges.board[n4][n5 + 1] == 1) {
                            array[n2][0] = n4;
                            array[n2++][1] = n5 + 1;
                        }
                    }
                    ++n3;
                }
            }
        }
        if (Bridges.blueWins) {
            Bridges.turnMsg.setText("Blue Wins");
            if (Bridges.debug) {
                Bridges.statusMsg.setText("Blue Wins MS: " + Bridges.maxSteps + " MO: " + Bridges.maxOptions + " MP: " + Bridges.maxPathList + " MM: " + Bridges.maxMoves);
            }
        }
    }
    
    public static void determineRedProgress() {
        Bridges.visited = new boolean[17][17];
        for (int i = 0; i < 17; ++i) {
            for (int j = 0; j < 17; ++j) {
                Bridges.visited[i][j] = false;
            }
        }
        final int[][] array = new int[289][2];
        Bridges.redWins = false;
        for (int n = 1; n < 17 && !Bridges.redWins; n += 2) {
            if (!Bridges.visited[n][0]) {
                int n2 = 0;
                int n3 = 0;
                array[n2][0] = n;
                array[n2++][1] = 0;
                while (n3 < n2 && !Bridges.redWins) {
                    final int n4 = array[n3][0];
                    final int n5 = array[n3][1];
                    Bridges.visited[n4][n5] = true;
                    if (n5 == 16) {
                        Bridges.redWins = true;
                    }
                    else {
                        if (n4 - 1 >= 0 && !Bridges.visited[n4 - 1][n5] && Bridges.board[n4 - 1][n5] == 2) {
                            array[n2][0] = n4 - 1;
                            array[n2++][1] = n5;
                        }
                        if (n4 + 1 < 17 && !Bridges.visited[n4 + 1][n5] && Bridges.board[n4 + 1][n5] == 2) {
                            array[n2][0] = n4 + 1;
                            array[n2++][1] = n5;
                        }
                        if (n5 - 1 >= 0 && !Bridges.visited[n4][n5 - 1] && Bridges.board[n4][n5 - 1] == 2) {
                            array[n2][0] = n4;
                            array[n2++][1] = n5 - 1;
                        }
                        if (n5 + 1 < 17 && !Bridges.visited[n4][n5 + 1] && Bridges.board[n4][n5 + 1] == 2) {
                            array[n2][0] = n4;
                            array[n2++][1] = n5 + 1;
                        }
                    }
                    ++n3;
                }
            }
        }
        if (Bridges.redWins) {
            Bridges.turnMsg.setText("Red Wins");
            if (Bridges.debug) {
                Bridges.statusMsg.setText("Red Wins MS: " + Bridges.maxSteps + " MO: " + Bridges.maxOptions + " MP: " + Bridges.maxPathList + " MM: " + Bridges.maxMoves);
            }
        }
    }
    
    public static void setupMem() {
        Bridges.steps = new Step[10000];
        for (int i = 0; i < 10000; ++i) {
            Bridges.steps[i] = new Step();
        }
        Bridges.optionList = new Option[200];
        for (int j = 0; j < 200; ++j) {
            Bridges.optionList[j] = new Option();
        }
        Bridges.bluePathList = new PathList[32];
        for (int k = 0; k < 32; ++k) {
            Bridges.bluePathList[k] = new PathList();
        }
        Bridges.redPathList = new PathList[32];
        for (int l = 0; l < 32; ++l) {
            Bridges.redPathList[l] = new PathList();
        }
    }
    
    public static void findBestMove() {
        Bridges.stepCounter = 0.0;
        for (int i = 0; i < 10000; ++i) {
            Bridges.steps[i].nextStep = i + 1;
        }
        Bridges.steps[9999].nextStep = -1;
        Bridges.firstFreeStep = 0;
        Bridges.visited = new boolean[17][17];
        Bridges.minSteps = new int[17][17];
        Bridges.consider = new boolean[17][17];
        for (int j = 0; j < 17; ++j) {
            for (int k = 0; k < 17; ++k) {
                Bridges.visited[j][k] = false;
                Bridges.minSteps[j][k] = 289;
                if (j == 0 || j == 16 || k == 0 || k == 16) {
                    Bridges.consider[j][k] = false;
                }
                else {
                    Bridges.consider[j][k] = true;
                }
            }
        }
        Bridges.optionTop = 0;
        Bridges.bluePathListTop = 0;
        findShortestBluePaths();
        for (int l = 0; l < 17; ++l) {
            for (int n = 0; n < 17; ++n) {
                Bridges.visited[l][n] = false;
                Bridges.minSteps[l][n] = 289;
                if (l == 0 || l == 16 || n == 0 || n == 16) {
                    Bridges.consider[l][n] = false;
                }
                else {
                    Bridges.consider[l][n] = true;
                }
            }
        }
        Bridges.optionTop = 0;
        Bridges.redPathListTop = 0;
        findShortestRedPaths();
        if (Bridges.debug) {
            Bridges.statusMsg.setText("BPLT: " + Bridges.bluePathListTop + " RPLT: " + Bridges.redPathListTop + " SC: " + Bridges.stepCounter + " MS: " + Bridges.maxSteps);
        }
        final Point compareBlueAndRedLists = compareBlueAndRedLists();
        toggleBox(compareBlueAndRedLists.y, compareBlueAndRedLists.x);
        Bridges.cnv1.repaint();
    }
    
    public static Point compareBlueAndRedLists() {
        final Point point = new Point(0, 0);
        int n = 0;
        int n2 = 0;
        for (int i = 1; i < 17; i += 2) {
            if (Bridges.board[1][i] == 1) {
                n = 1;
            }
            if (Bridges.board[15][i] == 1) {
                n2 = 1;
            }
        }
        if (n == 0 || n2 == 0) {
            if (n == 0) {
                for (int j = 0; j <= 6; j += 2) {
                    if (n != 0) {
                        break;
                    }
                    for (int k = 7 - j; k <= 9 + j; k += 2) {
                        if (Bridges.board[1][k] == 0) {
                            point.x = k;
                            point.y = 1;
                            n = 1;
                        }
                    }
                }
            }
            else {
                for (int l = 0; l <= 6; l += 2) {
                    if (n2 != 0) {
                        break;
                    }
                    for (int x = 7 - l; x <= 9 + l; x += 2) {
                        if (Bridges.board[15][x] == 0) {
                            point.x = x;
                            point.y = 15;
                            n2 = 1;
                        }
                    }
                }
            }
        }
        else if (Bridges.redPathList[0].togo == 1 && Bridges.bluePathList[0].togo > 1) {
            int n3 = Bridges.redPathList[0].firstStep;
            point.x = -1;
            while (n3 != -1) {
                if (point.x != -1) {
                    break;
                }
                final int row = Bridges.steps[n3].row;
                final int col = Bridges.steps[n3].col;
                if (Bridges.board[row][col] == 0) {
                    point.x = col;
                    point.y = row;
                }
                n3 = Bridges.steps[n3].nextStep;
            }
        }
        else {
            final int[][] array = new int[289][3];
            final int[][] array2 = new int[289][4];
            final int[][][] array3 = new int[17][17][2];
            final int[][] array4 = new int[289][2];
            for (int n4 = 0; n4 < 17; ++n4) {
                for (int n5 = 0; n5 < 17; ++n5) {
                    array3[n4][n5][0] = 0;
                    array3[n4][n5][1] = 0;
                }
            }
            for (int n6 = 0; n6 < Bridges.bluePathListTop; ++n6) {
                for (int n7 = Bridges.bluePathList[n6].firstStep; n7 != -1; n7 = Bridges.steps[n7].nextStep) {
                    final int row2 = Bridges.steps[n7].row;
                    final int col2 = Bridges.steps[n7].col;
                    if (Bridges.board[row2][col2] == 0) {
                        final int[] array5 = array3[row2][col2];
                        final int n8 = 0;
                        ++array5[n8];
                    }
                }
            }
            int n9 = 0;
            for (int n10 = 0; n10 < 17; ++n10) {
                for (int n11 = 0; n11 < 17; ++n11) {
                    if (array3[n10][n11][0] != 0) {
                        array[n9][0] = n10;
                        array[n9][1] = n11;
                        array[n9++][2] = array3[n10][n11][0];
                    }
                }
            }
            for (int n12 = 0; n12 < 17; ++n12) {
                for (int n13 = 0; n13 < 17; ++n13) {
                    array3[n12][n13][0] = 0;
                    array3[n12][n13][1] = 289;
                }
            }
            int prevStep = -1;
            for (int n14 = 0; n14 < Bridges.redPathListTop; ++n14) {
                int n15 = Bridges.redPathList[n14].firstStep;
                Bridges.steps[n15].prevStep = -1;
                int n16 = 289;
                while (n15 != -1) {
                    final int row3 = Bridges.steps[n15].row;
                    final int col3 = Bridges.steps[n15].col;
                    if (Bridges.board[row3][col3] == 0) {
                        final int[] array6 = array3[row3][col3];
                        final int n17 = 0;
                        ++array6[n17];
                        array3[row3][col3][1] = Math.min(array3[row3][col3][1], n16++);
                    }
                    else {
                        n16 = 1;
                    }
                    prevStep = n15;
                    n15 = Bridges.steps[n15].nextStep;
                }
                int n18 = 289;
                while (prevStep != -1) {
                    final int row4 = Bridges.steps[prevStep].row;
                    final int col4 = Bridges.steps[prevStep].col;
                    if (Bridges.board[row4][col4] == 0) {
                        final int[] array7 = array3[row4][col4];
                        final int n19 = 0;
                        ++array7[n19];
                        array3[row4][col4][1] = Math.min(array3[row4][col4][1], n18++);
                    }
                    else {
                        n18 = 1;
                    }
                    prevStep = Bridges.steps[prevStep].prevStep;
                }
            }
            int n20 = 0;
            for (int n21 = 0; n21 < 17; ++n21) {
                for (int n22 = 0; n22 < 17; ++n22) {
                    if (array3[n21][n22][0] != 0) {
                        array2[n20][0] = n21;
                        array2[n20][1] = n22;
                        array2[n20][2] = array3[n21][n22][0] / 2;
                        array2[n20++][3] = array3[n21][n22][1];
                    }
                }
            }
            for (int n23 = 0; n23 < n9 - 1; ++n23) {
                for (int n24 = n23; n24 < n9; ++n24) {
                    if (compareRowCol(array[n23][0], array[n23][1], array[n24][0], array[n24][1]) == 1) {
                        final int n25 = array[n24][0];
                        final int n26 = array[n24][1];
                        final int n27 = array[n24][2];
                        array[n24][0] = array[n23][0];
                        array[n24][1] = array[n23][1];
                        array[n24][2] = array[n23][2];
                        array[n23][0] = n25;
                        array[n23][1] = n26;
                        array[n23][2] = n27;
                    }
                }
            }
            for (int n28 = 0; n28 < n20 - 1; ++n28) {
                for (int n29 = n28; n29 < n20; ++n29) {
                    if (compareCloseRowCol(array2[n28][3], array2[n28][0], array2[n28][1], array2[n29][3], array2[n29][0], array2[n29][1]) == 1) {
                        final int n30 = array2[n29][0];
                        final int n31 = array2[n29][1];
                        final int n32 = array2[n29][2];
                        final int n33 = array2[n29][3];
                        array2[n29][0] = array2[n28][0];
                        array2[n29][1] = array2[n28][1];
                        array2[n29][2] = array2[n28][2];
                        array2[n29][3] = array2[n28][3];
                        array2[n28][0] = n30;
                        array2[n28][1] = n31;
                        array2[n28][2] = n32;
                        array2[n28][3] = n33;
                    }
                }
            }
            int n34 = 0;
            int n35 = 0;
            point.x = -1;
            int n36 = 0;
            int n37 = 289;
            int n38 = 0;
            while (n34 < n20 && n35 == 0) {
                int n39 = 0;
                int n40 = n9 - 1;
                int n41 = 0;
                while (n39 <= n40 && n35 == 0) {
                    n41 = (n39 + n40) / 2;
                    final int compareRowCol = compareRowCol(array2[n34][0], array2[n34][1], array[n41][0], array[n41][1]);
                    if (compareRowCol > 0) {
                        n39 = n41 + 1;
                    }
                    else if (compareRowCol == 0) {
                        n35 = 1;
                    }
                    else {
                        n40 = n41 - 1;
                    }
                }
                if (n35 != 0) {
                    if (array[n41][2] + array2[n41][2] >= n36 && array2[n34][3] <= n37) {
                        if (array[n41][2] + array2[n41][2] > n36 || array2[n34][3] < n37) {
                            n38 = 0;
                        }
                        array4[n38][0] = array[n41][0];
                        array4[n38++][1] = array[n41][1];
                        n36 = array[n41][2];
                        n37 = array2[n34][3];
                    }
                    n35 = 0;
                }
                ++n34;
            }
            if (n38 == 0) {
                for (int n42 = 0; n42 < n9; ++n42) {
                    if (array[n42][2] >= n36) {
                        if (array[n42][2] > n36) {
                            n38 = 0;
                        }
                        array4[n38][0] = array[n42][0];
                        array4[n38++][1] = array[n42][1];
                        n36 = array[n42][2];
                    }
                }
            }
            Bridges.maxMoves = Math.max(n38, Bridges.maxMoves);
            final int n43 = (int)(Math.random() * n38);
            point.x = array4[n43][1];
            point.y = array4[n43][0];
        }
        return point;
    }
    
    public static int compareCloseRowCol(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = n * 2000 + n2 * 50 + n3;
        final int n8 = n4 * 2000 + n5 * 50 + n6;
        if (n7 == n8) {
            return 0;
        }
        if (n7 < n8) {
            return -1;
        }
        return 1;
    }
    
    public static int compareRowCol(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n * 100 + n2;
        final int n6 = n3 * 100 + n4;
        if (n5 == n6) {
            return 0;
        }
        if (n5 < n6) {
            return -1;
        }
        return 1;
    }
    
    public static int newStep() {
        final int firstFreeStep = Bridges.firstFreeStep;
        ++Bridges.stepCounter;
        Bridges.maxSteps = Math.max(Bridges.stepCounter, Bridges.maxSteps);
        Bridges.firstFreeStep = Bridges.steps[Bridges.firstFreeStep].nextStep;
        if (Bridges.firstFreeStep == -1) {
            if (Bridges.debug) {
                Bridges.statusMsg.setText("Out of Memory for Steps.");
                System.out.println("StepCounter: " + Bridges.stepCounter);
            }
            else {
                Bridges.turnMsg.setText("Out of Memory for Steps.");
            }
        }
        Bridges.steps[firstFreeStep].nextStep = -1;
        return firstFreeStep;
    }
    
    public static void deleteStep(final int firstFreeStep) {
        --Bridges.stepCounter;
        Bridges.steps[firstFreeStep].nextStep = Bridges.firstFreeStep;
        Bridges.firstFreeStep = firstFreeStep;
    }
    
    public static void findShortestBluePaths() {
        int n = 289;
        for (int i = 1; i < 16; i += 2) {
            for (int j = 1; j < 16; j += 2) {
                if (j != i) {
                    Bridges.consider[1][j] = false;
                }
                else {
                    Bridges.consider[1][j] = true;
                }
            }
            if (Bridges.board[1][i] != 2) {
                final int step;
                final int n2 = step = newStep();
                Bridges.steps[n2].row = 1;
                Bridges.steps[n2].col = i;
                Bridges.steps[n2].stepCnt = 1;
                Bridges.steps[n2].direction = 4;
                Bridges.steps[n2].nextStep = -1;
                Bridges.steps[n2].prevStep = -1;
                int length = 1;
                int togo;
                if (Bridges.board[1][i] == 0) {
                    togo = 1;
                }
                else {
                    togo = 0;
                }
                Bridges.visited[1][i] = true;
                Bridges.optionTop = 0;
                int n3 = 2;
                int n4 = i;
                final int n5 = 2;
                considerBlueMove(n5, n3, n4 + 1, 2);
                considerBlueMove(n5, n3, n4 - 1, 3);
                considerBlueMove(n5, n3 + 1, n4, 4);
                int k = n2;
                while (Bridges.optionTop > 0) {
                    --Bridges.optionTop;
                    while (Bridges.optionList[Bridges.optionTop].stepCnt <= Bridges.steps[k].stepCnt) {
                        final int row = Bridges.steps[k].row;
                        final int col = Bridges.steps[k].col;
                        Bridges.visited[row][col] = false;
                        --length;
                        if (Bridges.board[row][col] != 1) {
                            --togo;
                        }
                        k = Bridges.steps[k].prevStep;
                        deleteStep(Bridges.steps[k].nextStep);
                        Bridges.steps[k].nextStep = -1;
                    }
                    final int step2 = newStep();
                    Bridges.steps[k].nextStep = step2;
                    Bridges.steps[step2].prevStep = k;
                    Bridges.steps[step2].row = Bridges.optionList[Bridges.optionTop].row;
                    Bridges.steps[step2].col = Bridges.optionList[Bridges.optionTop].col;
                    Bridges.steps[step2].stepCnt = Bridges.optionList[Bridges.optionTop].stepCnt;
                    Bridges.steps[step2].direction = Bridges.optionList[Bridges.optionTop].direction;
                    Bridges.steps[step2].nextStep = -1;
                    ++length;
                    final int row2 = Bridges.steps[step2].row;
                    final int col2 = Bridges.steps[step2].col;
                    if (Bridges.board[row2][col2] == 0) {
                        ++togo;
                    }
                    Bridges.visited[row2][col2] = true;
                    if (Bridges.steps[step2].row == 15 || togo > n || togo > Bridges.minSteps[row2][col2]) {
                        if (Bridges.steps[step2].row == 15 && togo <= n) {
                            int n6;
                            if (togo < n) {
                                deleteBluePathList();
                                n6 = 0;
                                Bridges.bluePathListTop = 1;
                                n = togo;
                            }
                            else if (Bridges.bluePathListTop < 32) {
                                n6 = Bridges.bluePathListTop++;
                                Bridges.maxPathList = Math.max(Bridges.bluePathListTop, Bridges.maxPathList);
                            }
                            else {
                                n6 = 32;
                            }
                            if (n6 == 32) {
                                if (Bridges.debug) {
                                    Bridges.statusMsg.setText("Out of space for Blue PathList");
                                }
                                else {
                                    Bridges.turnMsg.setText("");
                                }
                            }
                            else {
                                final int step3 = newStep();
                                Bridges.bluePathList[n6].length = length;
                                Bridges.bluePathList[n6].togo = togo;
                                Bridges.bluePathList[n6].firstStep = step3;
                                Bridges.steps[step3].row = Bridges.steps[step].row;
                                Bridges.steps[step3].col = Bridges.steps[step].col;
                                Bridges.steps[step3].stepCnt = Bridges.steps[step].stepCnt;
                                Bridges.steps[step3].direction = Bridges.steps[step].direction;
                                int l = Bridges.steps[step].nextStep;
                                int prevStep = step3;
                                while (l != -1) {
                                    final int step4 = newStep();
                                    Bridges.steps[prevStep].nextStep = step4;
                                    Bridges.steps[step4].prevStep = prevStep;
                                    Bridges.steps[step4].row = Bridges.steps[l].row;
                                    Bridges.steps[step4].col = Bridges.steps[l].col;
                                    Bridges.steps[step4].stepCnt = Bridges.steps[l].stepCnt;
                                    Bridges.steps[step4].direction = Bridges.steps[l].direction;
                                    l = Bridges.steps[l].nextStep;
                                    prevStep = step4;
                                }
                                Bridges.steps[prevStep].nextStep = -1;
                            }
                        }
                    }
                    else {
                        Bridges.minSteps[row2][col2] = togo;
                        switch (Bridges.steps[step2].direction) {
                            case 1: {
                                n3 = Bridges.steps[step2].row - 1;
                                n4 = Bridges.steps[step2].col;
                                break;
                            }
                            case 4: {
                                n3 = Bridges.steps[step2].row + 1;
                                n4 = Bridges.steps[step2].col;
                                break;
                            }
                            case 3: {
                                n3 = Bridges.steps[step2].row;
                                n4 = Bridges.steps[step2].col - 1;
                                break;
                            }
                            case 2: {
                                n3 = Bridges.steps[step2].row;
                                n4 = Bridges.steps[step2].col + 1;
                                break;
                            }
                        }
                        final int n7 = Bridges.steps[step2].stepCnt + 1;
                        considerBlueMove(n7, n3 - 1, n4, 1);
                        considerBlueMove(n7, n3, n4 + 1, 2);
                        considerBlueMove(n7, n3, n4 - 1, 3);
                        considerBlueMove(n7, n3 + 1, n4, 4);
                    }
                    k = step2;
                }
                while (k != -1) {
                    Bridges.visited[Bridges.steps[k].row][Bridges.steps[k].col] = false;
                    final int prevStep2 = Bridges.steps[k].prevStep;
                    deleteStep(k);
                    k = prevStep2;
                }
            }
        }
    }
    
    public static void deleteBluePathList() {
        for (int i = 0; i < Bridges.bluePathListTop; ++i) {
            int j = Bridges.bluePathList[i].firstStep;
            while (j != -1) {
                final int n = j;
                j = Bridges.steps[j].nextStep;
                deleteStep(n);
            }
        }
        Bridges.bluePathListTop = 0;
    }
    
    public static void considerBlueMove(final int stepCnt, final int row, final int col, final int direction) {
        if (Bridges.consider[row][col] && !Bridges.visited[row][col] && Bridges.board[row][col] != 2) {
            Bridges.optionList[Bridges.optionTop].stepCnt = stepCnt;
            Bridges.optionList[Bridges.optionTop].row = row;
            Bridges.optionList[Bridges.optionTop].col = col;
            Bridges.optionList[Bridges.optionTop].direction = direction;
            ++Bridges.optionTop;
            Bridges.maxOptions = Math.max(Bridges.optionTop, Bridges.maxOptions);
        }
    }
    
    public static void findShortestRedPaths() {
        int n = 289;
        for (int i = 1; i < 16; i += 2) {
            for (int j = 1; j < 16; j += 2) {
                if (j != i) {
                    Bridges.consider[j][1] = false;
                }
                else {
                    Bridges.consider[j][1] = true;
                }
            }
            if (Bridges.board[i][1] != 1) {
                final int step;
                final int n2 = step = newStep();
                Bridges.steps[n2].row = i;
                Bridges.steps[n2].col = 1;
                Bridges.steps[n2].stepCnt = 1;
                Bridges.steps[n2].direction = 2;
                Bridges.steps[n2].nextStep = -1;
                Bridges.steps[n2].prevStep = -1;
                int length = 1;
                int togo;
                if (Bridges.board[i][1] == 0) {
                    togo = 1;
                }
                else {
                    togo = 0;
                }
                Bridges.visited[i][1] = true;
                Bridges.optionTop = 0;
                int n3 = i;
                int n4 = 2;
                final int n5 = 2;
                considerRedMove(n5, n3 - 1, n4, 1);
                considerRedMove(n5, n3 + 1, n4, 4);
                considerRedMove(n5, n3, n4 + 1, 2);
                int k = n2;
                while (Bridges.optionTop > 0) {
                    --Bridges.optionTop;
                    while (Bridges.optionList[Bridges.optionTop].stepCnt <= Bridges.steps[k].stepCnt) {
                        final int row = Bridges.steps[k].row;
                        final int col = Bridges.steps[k].col;
                        Bridges.visited[row][col] = false;
                        --length;
                        if (Bridges.board[row][col] != 2) {
                            --togo;
                        }
                        k = Bridges.steps[k].prevStep;
                        deleteStep(Bridges.steps[k].nextStep);
                        Bridges.steps[k].nextStep = -1;
                    }
                    final int step2 = newStep();
                    Bridges.steps[k].nextStep = step2;
                    Bridges.steps[step2].prevStep = k;
                    Bridges.steps[step2].row = Bridges.optionList[Bridges.optionTop].row;
                    Bridges.steps[step2].col = Bridges.optionList[Bridges.optionTop].col;
                    Bridges.steps[step2].stepCnt = Bridges.optionList[Bridges.optionTop].stepCnt;
                    Bridges.steps[step2].direction = Bridges.optionList[Bridges.optionTop].direction;
                    Bridges.steps[step2].nextStep = -1;
                    ++length;
                    final int row2 = Bridges.steps[step2].row;
                    final int col2 = Bridges.steps[step2].col;
                    if (Bridges.board[row2][col2] == 0) {
                        ++togo;
                    }
                    Bridges.visited[row2][col2] = true;
                    if (Bridges.steps[step2].col == 15 || togo > n || togo > Bridges.minSteps[row2][col2]) {
                        if (Bridges.steps[step2].col == 15 && togo <= n) {
                            int n6;
                            if (togo < n) {
                                deleteRedPathList();
                                n6 = 0;
                                Bridges.redPathListTop = 1;
                                n = togo;
                            }
                            else if (Bridges.redPathListTop < 32) {
                                n6 = Bridges.redPathListTop++;
                                Bridges.maxPathList = Math.max(Bridges.redPathListTop, Bridges.maxPathList);
                            }
                            else {
                                n6 = 32;
                            }
                            if (n6 == 32) {
                                if (Bridges.debug) {
                                    Bridges.statusMsg.setText("Out of space for Red PathList");
                                }
                                else {
                                    Bridges.turnMsg.setText("");
                                }
                            }
                            else {
                                final int step3 = newStep();
                                Bridges.redPathList[n6].length = length;
                                Bridges.redPathList[n6].togo = togo;
                                Bridges.redPathList[n6].firstStep = step3;
                                Bridges.steps[step3].row = Bridges.steps[step].row;
                                Bridges.steps[step3].col = Bridges.steps[step].col;
                                Bridges.steps[step3].stepCnt = Bridges.steps[step].stepCnt;
                                Bridges.steps[step3].direction = Bridges.steps[step].direction;
                                int l = Bridges.steps[step].nextStep;
                                int prevStep = step3;
                                while (l != -1) {
                                    final int step4 = newStep();
                                    Bridges.steps[prevStep].nextStep = step4;
                                    Bridges.steps[step4].prevStep = prevStep;
                                    Bridges.steps[step4].row = Bridges.steps[l].row;
                                    Bridges.steps[step4].col = Bridges.steps[l].col;
                                    Bridges.steps[step4].stepCnt = Bridges.steps[l].stepCnt;
                                    Bridges.steps[step4].direction = Bridges.steps[l].direction;
                                    l = Bridges.steps[l].nextStep;
                                    prevStep = step4;
                                }
                                Bridges.steps[prevStep].nextStep = -1;
                            }
                        }
                    }
                    else {
                        Bridges.minSteps[row2][col2] = togo;
                        switch (Bridges.steps[step2].direction) {
                            case 1: {
                                n3 = Bridges.steps[step2].row - 1;
                                n4 = Bridges.steps[step2].col;
                                break;
                            }
                            case 4: {
                                n3 = Bridges.steps[step2].row + 1;
                                n4 = Bridges.steps[step2].col;
                                break;
                            }
                            case 3: {
                                n3 = Bridges.steps[step2].row;
                                n4 = Bridges.steps[step2].col - 1;
                                break;
                            }
                            case 2: {
                                n3 = Bridges.steps[step2].row;
                                n4 = Bridges.steps[step2].col + 1;
                                break;
                            }
                        }
                        final int n7 = Bridges.steps[step2].stepCnt + 1;
                        considerRedMove(n7, n3, n4 - 1, 3);
                        considerRedMove(n7, n3 - 1, n4, 1);
                        considerRedMove(n7, n3 + 1, n4, 4);
                        considerRedMove(n7, n3, n4 + 1, 2);
                    }
                    k = step2;
                }
                while (k != -1) {
                    Bridges.visited[Bridges.steps[k].row][Bridges.steps[k].col] = false;
                    final int prevStep2 = Bridges.steps[k].prevStep;
                    deleteStep(k);
                    k = prevStep2;
                }
            }
        }
    }
    
    public static void deleteRedPathList() {
        for (int i = 0; i < Bridges.redPathListTop; ++i) {
            int j = Bridges.redPathList[i].firstStep;
            while (j != -1) {
                final int n = j;
                j = Bridges.steps[j].nextStep;
                deleteStep(n);
            }
        }
        Bridges.redPathListTop = 0;
    }
    
    public static void considerRedMove(final int stepCnt, final int row, final int col, final int direction) {
        if (Bridges.consider[row][col] && !Bridges.visited[row][col] && Bridges.board[row][col] != 1) {
            Bridges.optionList[Bridges.optionTop].stepCnt = stepCnt;
            Bridges.optionList[Bridges.optionTop].row = row;
            Bridges.optionList[Bridges.optionTop].col = col;
            Bridges.optionList[Bridges.optionTop].direction = direction;
            ++Bridges.optionTop;
            Bridges.maxOptions = Math.max(Bridges.optionTop, Bridges.maxOptions);
        }
    }
}
