// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public class NumberState implements IState
{
    int boxesAcross;
    int boxesDown;
    int cellsInRow;
    boolean[][][] eliminated;
    int[][] nEliminated;
    boolean[][] isFilled;
    boolean[][][][] threadEliminated;
    int[][][] threadNEliminated;
    boolean[][][] threadIsFilled;
    
    public void setup(final int boxesAcross, final int boxesDown) {
        this.boxesAcross = boxesAcross;
        this.boxesDown = boxesDown;
        final boolean resize = this.cellsInRow != boxesAcross * boxesDown;
        this.cellsInRow = boxesAcross * boxesDown;
        if (resize) {
            this.eliminated = new boolean[this.cellsInRow][3 * this.cellsInRow][this.cellsInRow];
            this.nEliminated = new int[this.cellsInRow][3 * this.cellsInRow];
            this.isFilled = new boolean[this.cellsInRow][3 * this.cellsInRow];
            this.threadEliminated = new boolean[this.cellsInRow * this.cellsInRow][this.cellsInRow][3 * this.cellsInRow][this.cellsInRow];
            this.threadNEliminated = new int[this.cellsInRow * this.cellsInRow][this.cellsInRow][3 * this.cellsInRow];
            this.threadIsFilled = new boolean[this.cellsInRow * this.cellsInRow][this.cellsInRow][3 * this.cellsInRow];
        }
        else {
            for (int i = 0; i < this.cellsInRow; ++i) {
                for (int j = 0; j < 3 * this.cellsInRow; ++j) {
                    this.nEliminated[i][j] = 0;
                    this.isFilled[i][j] = false;
                    for (int k = 0; k < this.cellsInRow; ++k) {
                        this.eliminated[i][j][k] = false;
                    }
                }
            }
        }
    }
    
    public void pushState(final int nMoves) {
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < 3 * this.cellsInRow; ++j) {
                for (int k = 0; k < this.cellsInRow; ++k) {
                    this.threadEliminated[nMoves][i][j][k] = this.eliminated[i][j][k];
                }
                this.threadNEliminated[nMoves][i][j] = this.nEliminated[i][j];
                this.threadIsFilled[nMoves][i][j] = this.isFilled[i][j];
            }
        }
    }
    
    public void popState(final int nMoves) {
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < 3 * this.cellsInRow; ++j) {
                for (int k = 0; k < this.cellsInRow; ++k) {
                    this.eliminated[i][j][k] = this.threadEliminated[nMoves][i][j][k];
                }
                this.nEliminated[i][j] = this.threadNEliminated[nMoves][i][j];
                this.isFilled[i][j] = this.threadIsFilled[nMoves][i][j];
            }
        }
    }
    
    public void addMove(final int x, final int y, final int value) throws Exception {
        int boxSector = 2 * this.cellsInRow + x / this.boxesAcross * this.boxesAcross + y / this.boxesDown;
        int boxPosition = x % this.boxesAcross * this.boxesDown + y % this.boxesDown;
        if (this.eliminated[value][x][y] || this.eliminated[value][this.cellsInRow + y][x] || this.eliminated[value][boxSector][boxPosition]) {
            throw new Exception("The move (" + (1 + x) + "," + (1 + y) + "):=" + (1 + value) + " has already been eliminated");
        }
        this.isFilled[value][x] = true;
        this.isFilled[value][this.cellsInRow + y] = true;
        this.isFilled[value][boxSector] = true;
        boxSector = 2 * this.cellsInRow + x / this.boxesAcross * this.boxesAcross;
        boxPosition = x % this.boxesAcross * this.boxesDown;
        int i = -1;
        while (++i < this.cellsInRow) {
            if (i == y) {
                continue;
            }
            if (!this.eliminated[value][x][i]) {
                this.eliminated[value][x][i] = true;
                final int[] array = this.nEliminated[value];
                ++array[x];
            }
            if (!this.eliminated[value][this.cellsInRow + i][x]) {
                this.eliminated[value][this.cellsInRow + i][x] = true;
                final int[] array2 = this.nEliminated[value];
                final int n = this.cellsInRow + i;
                ++array2[n];
            }
            if (this.eliminated[value][boxSector + i / this.boxesDown][boxPosition + i % this.boxesDown]) {
                continue;
            }
            this.eliminated[value][boxSector + i / this.boxesDown][boxPosition + i % this.boxesDown] = true;
            final int[] array3 = this.nEliminated[value];
            final int n2 = boxSector + i / this.boxesDown;
            ++array3[n2];
        }
        if (this.nEliminated[value][x] != this.cellsInRow - 1) {
            throw new Exception("Couldn't eliminate in Row " + (1 + x));
        }
        i = -1;
        boxSector = 2 * this.cellsInRow + y / this.boxesDown;
        boxPosition = y % this.boxesDown;
        while (++i < this.cellsInRow) {
            if (i == x) {
                continue;
            }
            if (!this.eliminated[value][i][y]) {
                this.eliminated[value][i][y] = true;
                final int[] array4 = this.nEliminated[value];
                final int n3 = i;
                ++array4[n3];
            }
            if (!this.eliminated[value][this.cellsInRow + y][i]) {
                this.eliminated[value][this.cellsInRow + y][i] = true;
                final int[] array5 = this.nEliminated[value];
                final int n4 = this.cellsInRow + y;
                ++array5[n4];
            }
            if (this.eliminated[value][boxSector + i / this.boxesAcross * this.boxesAcross][boxPosition + i % this.boxesAcross * this.boxesDown]) {
                continue;
            }
            this.eliminated[value][boxSector + i / this.boxesAcross * this.boxesAcross][boxPosition + i % this.boxesAcross * this.boxesDown] = true;
            final int[] array6 = this.nEliminated[value];
            final int n5 = boxSector + i / this.boxesAcross * this.boxesAcross;
            ++array6[n5];
        }
        if (this.nEliminated[value][this.cellsInRow + y] != this.cellsInRow - 1) {
            throw new Exception("Couldn't eliminate in Column " + (1 + y));
        }
        i = x / this.boxesAcross * this.boxesAcross - 1;
        while (++i < (x / this.boxesAcross + 1) * this.boxesAcross) {
            int j = y / this.boxesDown * this.boxesDown - 1;
            while (++j < (y / this.boxesDown + 1) * this.boxesDown) {
                if (i == x && j == y) {
                    continue;
                }
                if (!this.eliminated[value][i][j]) {
                    this.eliminated[value][i][j] = true;
                    final int[] array7 = this.nEliminated[value];
                    final int n6 = i;
                    ++array7[n6];
                }
                if (this.eliminated[value][this.cellsInRow + j][i]) {
                    continue;
                }
                this.eliminated[value][this.cellsInRow + j][i] = true;
                final int[] array8 = this.nEliminated[value];
                final int n7 = this.cellsInRow + j;
                ++array8[n7];
            }
        }
        i = -1;
        boxSector = 2 * this.cellsInRow + x / this.boxesAcross * this.boxesAcross + y / this.boxesDown;
        boxPosition = x % this.boxesAcross * this.boxesDown + y % this.boxesDown;
        while (++i < this.cellsInRow) {
            if (i == x % this.boxesAcross * this.boxesDown + y % this.boxesDown) {
                continue;
            }
            if (this.eliminated[value][boxSector][i]) {
                continue;
            }
            this.eliminated[value][boxSector][i] = true;
            final int[] array9 = this.nEliminated[value];
            final int n8 = boxSector;
            ++array9[n8];
        }
        if (this.nEliminated[value][boxSector] != this.cellsInRow - 1) {
            throw new Exception("Couldn't eliminate in Box [" + (1 + boxSector / this.boxesAcross) + "," + (1 + boxSector % this.boxesAcross) + "]");
        }
        i = -1;
        while (++i < this.cellsInRow) {
            if (i != value && !this.eliminated[i][x][y]) {
                this.eliminated[i][x][y] = true;
                final int[] array10 = this.nEliminated[i];
                ++array10[x];
            }
        }
        i = -1;
        while (++i < this.cellsInRow) {
            if (i != value && !this.eliminated[i][this.cellsInRow + y][x]) {
                this.eliminated[i][this.cellsInRow + y][x] = true;
                final int[] array11 = this.nEliminated[i];
                final int n9 = this.cellsInRow + y;
                ++array11[n9];
            }
        }
        i = -1;
        while (++i < this.cellsInRow) {
            if (i != value && !this.eliminated[i][boxSector][boxPosition]) {
                this.eliminated[i][boxSector][boxPosition] = true;
                final int[] array12 = this.nEliminated[i];
                final int n10 = boxSector;
                ++array12[n10];
            }
        }
    }
    
    public void eliminateMove(final int x, final int y, final int value) {
        final int boxSector = 2 * this.cellsInRow + x / this.boxesAcross * this.boxesAcross + y / this.boxesDown;
        this.eliminated[value][x][y] = true;
        final int[] array = this.nEliminated[value];
        ++array[x];
        this.eliminated[value][this.cellsInRow + y][x] = true;
        final int[] array2 = this.nEliminated[value];
        final int n = this.cellsInRow + y;
        ++array2[n];
        this.eliminated[value][boxSector][x % this.boxesAcross * this.boxesDown + y % this.boxesDown] = true;
        final int[] array3 = this.nEliminated[value];
        final int n2 = boxSector;
        ++array3[n2];
        this.isFilled[value][x] = false;
        this.isFilled[value][this.cellsInRow + y] = false;
        this.isFilled[value][boxSector] = false;
    }
    
    public String toString() {
        return SuDokuUtils.toString(this.nEliminated, this.boxesAcross);
    }
}
