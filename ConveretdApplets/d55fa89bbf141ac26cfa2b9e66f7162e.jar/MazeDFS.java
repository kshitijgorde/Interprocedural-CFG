import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class MazeDFS extends Maze
{
    MazeDFS(final Dimension dimension, final MazeCanvas mazeCanvas, final MazeGame mazeGame) {
        super(dimension, mazeCanvas, mazeGame);
        this.setWalls();
        this.setStartEnd();
        mazeCanvas.drawMaze(this);
    }
    
    void setWalls() {
        final int[] array = new int[4];
        int randomInt = this.randomInt(super.cols);
        int randomInt2 = this.randomInt(super.rows);
        super.stackPtr = 0;
        int i = 1;
        while (i < super.totalCells) {
            int n = 0;
            if ((super.m[randomInt][randomInt2] & 0x10) == 0x0 && (super.m[randomInt][randomInt2 - 1] & 0xF) == 0xF) {
                array[n++] = 0;
            }
            if ((super.m[randomInt][randomInt2] & 0x20) == 0x0 && (super.m[randomInt + 1][randomInt2] & 0xF) == 0xF) {
                array[n++] = 1;
            }
            if ((super.m[randomInt][randomInt2] & 0x40) == 0x0 && (super.m[randomInt][randomInt2 + 1] & 0xF) == 0xF) {
                array[n++] = 2;
            }
            if ((super.m[randomInt][randomInt2] & 0x80) == 0x0 && (super.m[randomInt - 1][randomInt2] & 0xF) == 0xF) {
                array[n++] = 3;
            }
            if (n != 0) {
                switch (super.stack[super.stackPtr++] = array[this.randomInt(n)]) {
                    case 0: {
                        final int[] array2 = super.m[randomInt];
                        final int n2 = randomInt2;
                        array2[n2] &= 0xFFFFFFFE;
                        final int[] array3 = super.m[randomInt];
                        final int n3 = --randomInt2;
                        array3[n3] &= 0xFFFFFFFB;
                        break;
                    }
                    case 1: {
                        final int[] array4 = super.m[randomInt];
                        final int n4 = randomInt2;
                        array4[n4] &= 0xFFFFFFFD;
                        final int[] array5 = super.m[++randomInt];
                        final int n5 = randomInt2;
                        array5[n5] &= 0xFFFFFFF7;
                        break;
                    }
                    case 2: {
                        final int[] array6 = super.m[randomInt];
                        final int n6 = randomInt2;
                        array6[n6] &= 0xFFFFFFFB;
                        final int[] array7 = super.m[randomInt];
                        final int n7 = ++randomInt2;
                        array7[n7] &= 0xFFFFFFFE;
                        break;
                    }
                    case 3: {
                        final int[] array8 = super.m[randomInt];
                        final int n8 = randomInt2;
                        array8[n8] &= 0xFFFFFFF7;
                        final int[] array9 = super.m[--randomInt];
                        final int n9 = randomInt2;
                        array9[n9] &= 0xFFFFFFFD;
                        break;
                    }
                }
                ++i;
            }
            else {
                final int[] stack = super.stack;
                final int stackPtr = super.stackPtr - 1;
                super.stackPtr = stackPtr;
                switch (stack[stackPtr]) {
                    default: {
                        continue;
                    }
                    case 0: {
                        ++randomInt2;
                        continue;
                    }
                    case 1: {
                        --randomInt;
                        continue;
                    }
                    case 2: {
                        --randomInt2;
                        continue;
                    }
                    case 3: {
                        ++randomInt;
                        continue;
                    }
                }
            }
        }
    }
}
