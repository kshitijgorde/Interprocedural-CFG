// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public class CellState implements IState
{
    int boxesAcross;
    int boxesDown;
    int cellsInRow;
    boolean[][][] eliminated;
    int[][] nEliminated;
    boolean[][][][] threadEliminated;
    int[][][] threadNEliminated;
    
    public void setup(final int boxesAcross, final int boxesDown) {
        this.boxesAcross = boxesAcross;
        this.boxesDown = boxesDown;
        final boolean resize = this.cellsInRow != boxesAcross * boxesDown;
        this.cellsInRow = boxesAcross * boxesDown;
        if (resize) {
            this.eliminated = new boolean[this.cellsInRow][this.cellsInRow][this.cellsInRow];
            this.nEliminated = new int[this.cellsInRow][this.cellsInRow];
            this.threadEliminated = new boolean[this.cellsInRow * this.cellsInRow][this.cellsInRow][this.cellsInRow][this.cellsInRow];
            this.threadNEliminated = new int[this.cellsInRow * this.cellsInRow][this.cellsInRow][this.cellsInRow];
        }
        else {
            for (int i = 0; i < this.cellsInRow; ++i) {
                for (int j = 0; j < this.cellsInRow; ++j) {
                    this.nEliminated[i][j] = 0;
                    for (int k = 0; k < this.cellsInRow; ++k) {
                        this.eliminated[i][j][k] = false;
                    }
                }
            }
        }
    }
    
    public void pushState(final int nMoves) {
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                for (int k = 0; k < this.cellsInRow; ++k) {
                    this.threadEliminated[nMoves][i][j][k] = this.eliminated[i][j][k];
                }
                this.threadNEliminated[nMoves][i][j] = this.nEliminated[i][j];
            }
        }
    }
    
    public void popState(final int nMoves) {
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                for (int k = 0; k < this.cellsInRow; ++k) {
                    this.eliminated[i][j][k] = this.threadEliminated[nMoves][i][j][k];
                }
                this.nEliminated[i][j] = this.threadNEliminated[nMoves][i][j];
            }
        }
    }
    
    public void addMove(final int x, final int y, final int value) throws Exception {
        if (this.eliminated[x][y][value]) {
            throw new Exception("The move (" + (1 + x) + "," + (1 + y) + "):=" + (1 + value) + " has already been eliminated");
        }
        for (int i = 0; i < this.cellsInRow; ++i) {
            if (i != value && !this.eliminated[x][y][i]) {
                this.eliminated[x][y][i] = true;
                final int[] array = this.nEliminated[x];
                ++array[y];
            }
        }
        if (this.nEliminated[x][y] != this.cellsInRow - 1) {
            throw new Exception("Couldn't eliminate at cell (" + (1 + x) + "," + (1 + y) + ")");
        }
        for (int j = 0; j < this.cellsInRow; ++j) {
            if (j != y && !this.eliminated[x][j][value]) {
                this.eliminated[x][j][value] = true;
                final int[] array2 = this.nEliminated[x];
                final int n = j;
                ++array2[n];
            }
        }
        for (int i = 0; i < this.cellsInRow; ++i) {
            if (i != x && !this.eliminated[i][y][value]) {
                this.eliminated[i][y][value] = true;
                final int[] array3 = this.nEliminated[i];
                ++array3[y];
            }
        }
        int i = x / this.boxesAcross * this.boxesAcross - 1;
        while (++i < (x / this.boxesAcross + 1) * this.boxesAcross) {
            if (i == x) {
                continue;
            }
            int j = y / this.boxesDown * this.boxesDown - 1;
            while (++j < (y / this.boxesDown + 1) * this.boxesDown) {
                if (j == y) {
                    continue;
                }
                if (this.eliminated[i][j][value]) {
                    continue;
                }
                this.eliminated[i][j][value] = true;
                final int[] array4 = this.nEliminated[i];
                final int n2 = j;
                ++array4[n2];
            }
        }
    }
    
    public void eliminateMove(final int x, final int y, final int value) {
        this.eliminated[x][y][value] = true;
        final int[] array = this.nEliminated[x];
        ++array[y];
    }
    
    public String toString() {
        return SuDokuUtils.toString(this.nEliminated, this.boxesAcross);
    }
}
