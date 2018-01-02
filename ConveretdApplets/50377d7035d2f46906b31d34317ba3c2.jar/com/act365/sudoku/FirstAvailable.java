// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public class FirstAvailable extends StrategyBase implements IStrategy
{
    boolean lastMoveSuccessful;
    int x;
    int y;
    int value;
    
    public FirstAvailable() {
        super(false, false);
        this.lastMoveSuccessful = true;
    }
    
    public void setup(final Grid grid) throws Exception {
        super.setup(grid);
        super.xCandidates = new int[1];
        super.yCandidates = new int[1];
        super.valueCandidates = new int[1];
        this.x = 0;
        this.y = 0;
        this.value = 0;
        this.lastMoveSuccessful = true;
        super.bestX = 0;
        super.bestY = 0;
        super.bestValue = 0;
    }
    
    public int findCandidates() {
        if (this.lastMoveSuccessful) {
            while (this.x < super.grid.cellsInRow && super.grid.data[this.x][this.y] > 0) {
                ++this.y;
                while (this.y < super.grid.cellsInRow && super.grid.data[this.x][this.y] > 0) {
                    ++this.y;
                }
                if (this.y == super.grid.cellsInRow) {
                    ++this.x;
                    this.y = 0;
                }
            }
        }
        final int originalValue = super.grid.data[this.x][this.y];
        int value;
        do {
            value = ++super.grid.data[this.x][this.y];
            this.value = value;
        } while (value <= super.grid.cellsInRow && !this.isSound());
        super.grid.data[this.x][this.y] = originalValue;
        if (this.value <= super.grid.cellsInRow) {
            super.xCandidates[0] = this.x;
            super.yCandidates[0] = this.y;
            super.valueCandidates[0] = this.value;
            super.score = 1;
            return super.nCandidates = 1;
        }
        super.score = 0;
        return super.nCandidates = 0;
    }
    
    public void selectCandidate() {
        super.bestX = super.xCandidates[0];
        super.bestY = super.yCandidates[0];
        super.bestValue = super.valueCandidates[0];
    }
    
    public boolean updateState(final int x, final int y, final int value, final String reason, final boolean writeState) {
        if (super.nMoves == -1) {
            return false;
        }
        this.lastMoveSuccessful = true;
        super.xMoves[super.nMoves] = x;
        super.yMoves[super.nMoves] = y;
        super.values[super.nMoves] = value - 1;
        super.stateWrite[super.nMoves] = true;
        ++super.nMoves;
        return true;
    }
    
    public boolean unwind(final int newNMoves, final boolean reset) {
        if (newNMoves < 0) {
            return false;
        }
        --super.nMoves;
        this.lastMoveSuccessful = false;
        if (super.grid.countFilledCells() == super.grid.cellsInRow * super.grid.cellsInRow) {
            return true;
        }
        if (reset) {
            super.grid.data[this.x][this.y] = 0;
        }
        this.x = super.xMoves[super.nMoves];
        this.y = super.yMoves[super.nMoves];
        return true;
    }
    
    boolean isColumnSound(final int i) {
        final boolean[] check = new boolean[super.grid.cellsInRow];
        for (int j = 0; j < super.grid.cellsInRow; ++j) {
            if (super.grid.data[i][j] > 0) {
                if (check[super.grid.data[i][j] - 1]) {
                    return false;
                }
                check[super.grid.data[i][j] - 1] = true;
            }
        }
        return true;
    }
    
    boolean isRowSound(final int j) {
        final boolean[] check = new boolean[super.grid.cellsInRow];
        for (int i = 0; i < super.grid.cellsInRow; ++i) {
            if (super.grid.data[i][j] > 0) {
                if (check[super.grid.data[i][j] - 1]) {
                    return false;
                }
                check[super.grid.data[i][j] - 1] = true;
            }
        }
        return true;
    }
    
    boolean isSubgridSound(final int i, final int j) {
        final boolean[] check = new boolean[super.grid.cellsInRow];
        for (int k = 0; k < super.grid.cellsInRow; ++k) {
            if (super.grid.data[i * super.grid.boxesAcross + k % super.grid.boxesAcross][j * super.grid.boxesDown + k / super.grid.boxesAcross] > 0) {
                if (check[super.grid.data[i * super.grid.boxesAcross + k % super.grid.boxesAcross][j * super.grid.boxesDown + k / super.grid.boxesAcross] - 1]) {
                    return false;
                }
                check[super.grid.data[i * super.grid.boxesAcross + k % super.grid.boxesAcross][j * super.grid.boxesDown + k / super.grid.boxesAcross] - 1] = true;
            }
        }
        return true;
    }
    
    boolean isSound() {
        for (int i = 0; i < super.grid.cellsInRow; ++i) {
            if (!this.isColumnSound(i)) {
                return false;
            }
            if (!this.isRowSound(i)) {
                return false;
            }
            if (!this.isSubgridSound(i % super.grid.boxesDown, i / super.grid.boxesDown)) {
                return false;
            }
        }
        return true;
    }
}
