import java.awt.Dimension;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Maze
{
    static final int NORTH = 0;
    static final int N_WALL = 1;
    static final int N_BORDER = 16;
    static final int N_PATH = 256;
    static final int CN_PATH = 4352;
    static final int EAST = 1;
    static final int E_WALL = 2;
    static final int E_BORDER = 32;
    static final int E_PATH = 512;
    static final int CE_PATH = 4608;
    static final int SOUTH = 2;
    static final int S_WALL = 4;
    static final int S_BORDER = 64;
    static final int S_PATH = 1024;
    static final int CS_PATH = 5120;
    static final int WEST = 3;
    static final int W_WALL = 8;
    static final int W_BORDER = 128;
    static final int W_PATH = 2048;
    static final int CW_PATH = 6144;
    static final int ALL_WALLS = 15;
    static final int C_PATH = 4096;
    static final int N_BACK = 65536;
    static final int CN_BACK = 1114112;
    static final int E_BACK = 131072;
    static final int CE_BACK = 1179648;
    static final int S_BACK = 262144;
    static final int CS_BACK = 1310720;
    static final int W_BACK = 524288;
    static final int CW_BACK = 1572864;
    static final int C_BACK = 1048576;
    protected Point startPt;
    protected Point endPt;
    protected int[][] m;
    protected int[] stack;
    protected int stackPtr;
    protected int cols;
    protected int rows;
    protected int totalCells;
    MazeCanvas mc;
    MazeGame mg;
    
    Maze(final Dimension dimension, final MazeCanvas mc, final MazeGame mg) {
        this.mc = mc;
        this.mg = mg;
        this.cols = dimension.width;
        this.rows = dimension.height;
        this.totalCells = this.cols * this.rows;
        this.stack = new int[this.totalCells];
        this.m = new int[this.cols][this.rows];
        for (int i = 0; i < this.cols; ++i) {
            for (int j = 0; j < this.rows; ++j) {
                final int[] array = this.m[i];
                final int n = j;
                array[n] |= 0xF;
                if (j == 0) {
                    final int[] array2 = this.m[i];
                    final int n2 = 0;
                    array2[n2] |= 0x10;
                }
                if (i == this.cols - 1) {
                    final int[] array3 = this.m[i];
                    final int n3 = j;
                    array3[n3] |= 0x20;
                }
                if (j == this.rows - 1) {
                    final int[] array4 = this.m[i];
                    final int n4 = j;
                    array4[n4] |= 0x40;
                }
                if (i == 0) {
                    final int[] array5 = this.m[0];
                    final int n5 = j;
                    array5[n5] |= 0x80;
                }
            }
        }
    }
    
    void setStartEnd() {
        this.startPt = new Point(0, 0);
        this.endPt = new Point(this.cols - 1, this.rows - 1);
    }
    
    public boolean isReachable(final Point point, final Point point2, final int n) {
        final int x = point2.x;
        final int y = point2.y;
        return x >= 0 && y >= 0 && x <= this.cols - 1 && y <= this.rows - 1 && this.getPath(point, point2) <= n;
    }
    
    public synchronized int getPath(final Point point, final Point point2) {
        final int[] array = new int[4];
        int x = point.x;
        int y = point.y;
        int x2 = point.x;
        int y2 = point.y;
        final int[][] array2 = new int[this.cols][this.rows];
        for (int i = 0; i < this.cols; ++i) {
            for (int j = 0; j < this.rows; ++j) {
                array2[i][j] = this.m[i][j];
            }
        }
        this.stackPtr = 0;
        final int[] array3 = array2[x];
        final int n = y;
        array3[n] |= 0x1000;
        while (x != point2.x || y != point2.y) {
            int n2 = 0;
            if ((array2[x][y] & 0x1) == 0x0 && (array2[x][y - 1] & 0x1000) == 0x0 && (array2[x][y - 1] & 0x100000) == 0x0) {
                array[n2++] = 0;
            }
            if ((array2[x][y] & 0x2) == 0x0 && (array2[x + 1][y] & 0x1000) == 0x0 && (array2[x + 1][y] & 0x100000) == 0x0) {
                array[n2++] = 1;
            }
            if ((array2[x][y] & 0x4) == 0x0 && (array2[x][y + 1] & 0x1000) == 0x0 && (array2[x][y + 1] & 0x100000) == 0x0) {
                array[n2++] = 2;
            }
            if ((array2[x][y] & 0x8) == 0x0 && (array2[x - 1][y] & 0x1000) == 0x0 && (array2[x - 1][y] & 0x100000) == 0x0) {
                array[n2++] = 3;
            }
            if (n2 != 0) {
                switch (this.stack[this.stackPtr++] = array[this.randomInt(n2)]) {
                    default: {
                        continue;
                    }
                    case 0: {
                        final int[] array4 = array2[x];
                        final int n3 = y--;
                        array4[n3] |= 0x100;
                        final int[] array5 = array2[x];
                        final int n4 = y;
                        array5[n4] |= 0x1400;
                        --y2;
                        continue;
                    }
                    case 1: {
                        final int[] array6 = array2[x++];
                        final int n5 = y;
                        array6[n5] |= 0x200;
                        final int[] array7 = array2[x];
                        final int n6 = y;
                        array7[n6] |= 0x1800;
                        ++x2;
                        continue;
                    }
                    case 2: {
                        final int[] array8 = array2[x];
                        final int n7 = y++;
                        array8[n7] |= 0x400;
                        final int[] array9 = array2[x];
                        final int n8 = y;
                        array9[n8] |= 0x1100;
                        ++y2;
                        continue;
                    }
                    case 3: {
                        final int[] array10 = array2[x--];
                        final int n9 = y;
                        array10[n9] |= 0x800;
                        final int[] array11 = array2[x];
                        final int n10 = y;
                        array11[n10] |= 0x1200;
                        --x2;
                        continue;
                    }
                }
            }
            else {
                final int[] stack = this.stack;
                final int stackPtr = this.stackPtr - 1;
                this.stackPtr = stackPtr;
                switch (stack[stackPtr]) {
                    default: {
                        continue;
                    }
                    case 0: {
                        final int[] array12 = array2[x];
                        final int n11 = y;
                        array12[n11] &= 0xFFFFEBFF;
                        final int[] array13 = array2[x];
                        final int n12 = y;
                        array13[n12] |= 0x140000;
                        final int[] array14 = array2[x];
                        final int n13 = ++y;
                        array14[n13] &= 0xFFFFFEFF;
                        final int[] array15 = array2[x];
                        final int n14 = y;
                        array15[n14] |= 0x10000;
                        continue;
                    }
                    case 1: {
                        final int[] array16 = array2[x];
                        final int n15 = y;
                        array16[n15] &= 0xFFFFE7FF;
                        final int[] array17 = array2[x];
                        final int n16 = y;
                        array17[n16] |= 0x180000;
                        final int[] array18 = array2[--x];
                        final int n17 = y;
                        array18[n17] &= 0xFFFFFDFF;
                        final int[] array19 = array2[x];
                        final int n18 = y;
                        array19[n18] |= 0x20000;
                        continue;
                    }
                    case 2: {
                        final int[] array20 = array2[x];
                        final int n19 = y;
                        array20[n19] &= 0xFFFFEEFF;
                        final int[] array21 = array2[x];
                        final int n20 = y;
                        array21[n20] |= 0x110000;
                        final int[] array22 = array2[x];
                        final int n21 = --y;
                        array22[n21] &= 0xFFFFFBFF;
                        final int[] array23 = array2[x];
                        final int n22 = y;
                        array23[n22] |= 0x40000;
                        continue;
                    }
                    case 3: {
                        final int[] array24 = array2[x];
                        final int n23 = y;
                        array24[n23] &= 0xFFFFEDFF;
                        final int[] array25 = array2[x];
                        final int n24 = y;
                        array25[n24] |= 0x120000;
                        final int[] array26 = array2[++x];
                        final int n25 = y;
                        array26[n25] &= 0xFFFFF7FF;
                        final int[] array27 = array2[x];
                        final int n26 = y;
                        array27[n26] |= 0x80000;
                        continue;
                    }
                }
            }
        }
        return this.stackPtr;
    }
    
    int getCols() {
        return this.cols;
    }
    
    int getRows() {
        return this.rows;
    }
    
    int getCell(final int n, final int n2) {
        return this.m[n][n2];
    }
    
    Point getStart() {
        return this.startPt;
    }
    
    Point getEnd() {
        return this.endPt;
    }
    
    int randomInt(final int n) {
        return (int)(n * Math.random());
    }
    
    abstract void setWalls();
}
