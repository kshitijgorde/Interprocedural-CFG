// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public class MaskState
{
    int boxesAcross;
    int boxesDown;
    int cellsInRow;
    int maxScore;
    int[][] nInvulnerable;
    boolean[][] mask;
    
    public void setup(final int boxesAcross, final int boxesDown) {
        this.boxesAcross = boxesAcross;
        this.boxesDown = boxesDown;
        final boolean resize = this.cellsInRow != boxesAcross * boxesDown;
        this.cellsInRow = boxesAcross * boxesDown;
        this.maxScore = 3 * this.cellsInRow - boxesAcross - boxesDown;
        if (resize) {
            this.nInvulnerable = new int[this.cellsInRow][this.cellsInRow];
            this.mask = new boolean[this.cellsInRow][this.cellsInRow];
        }
        else {
            for (int i = 0; i < this.cellsInRow; ++i) {
                for (int j = 0; j < this.cellsInRow; ++j) {
                    this.nInvulnerable[i][j] = 0;
                    this.mask[i][j] = false;
                }
            }
        }
    }
    
    public void addCell(final int x, final int y) throws Exception {
        if (this.mask[x][y]) {
            throw new Exception("The cell (" + (1 + x) + "," + (1 + y) + ") has already been filled");
        }
        final int lowerX = x / this.boxesAcross * this.boxesAcross;
        final int upperX = (x / this.boxesAcross + 1) * this.boxesAcross;
        final int lowerY = y / this.boxesDown * this.boxesDown;
        final int upperY = (y / this.boxesDown + 1) * this.boxesDown;
        this.nInvulnerable[x][y] = this.maxScore;
        int i = -1;
        while (++i < this.cellsInRow) {
            if (i != x) {
                if (this.mask[i][y]) {
                    continue;
                }
                final int[] array = this.nInvulnerable[i];
                ++array[y];
            }
        }
        int j = -1;
        while (++j < this.cellsInRow) {
            if (j != y) {
                if (this.mask[x][j]) {
                    continue;
                }
                final int[] array2 = this.nInvulnerable[x];
                final int n = j;
                ++array2[n];
            }
        }
        i = lowerX - 1;
        while (++i < upperX) {
            if (i == x) {
                continue;
            }
            j = lowerY - 1;
            while (++j < upperY) {
                if (j != y) {
                    if (this.mask[i][j]) {
                        continue;
                    }
                    final int[] array3 = this.nInvulnerable[i];
                    final int n2 = j;
                    ++array3[n2];
                }
            }
        }
        this.mask[x][y] = true;
    }
    
    public String toString() {
        return SuDokuUtils.toString(this.nInvulnerable, this.boxesAcross, this.maxScore);
    }
}
