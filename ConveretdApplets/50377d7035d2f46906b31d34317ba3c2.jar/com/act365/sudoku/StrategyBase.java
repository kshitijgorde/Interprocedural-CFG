// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

import java.util.Random;

public abstract class StrategyBase
{
    int size;
    protected Grid grid;
    protected boolean[] stateWrite;
    protected int[] xMoves;
    protected int[] yMoves;
    protected int[] values;
    protected StringBuffer[] reasons;
    protected int nMoves;
    protected int[] xCandidates;
    protected int[] yCandidates;
    protected int[] valueCandidates;
    protected StringBuffer[] reasonCandidates;
    protected int nCandidates;
    protected boolean randomize;
    Random generator;
    protected boolean explain;
    protected int score;
    protected int bestX;
    protected int bestY;
    protected int bestValue;
    protected String bestReason;
    protected IState state;
    protected transient boolean resize;
    
    protected StrategyBase(final boolean randomize, final boolean explain) {
        this.randomize = randomize;
        this.explain = explain;
        if (randomize) {
            this.generator = new Random();
        }
    }
    
    protected void setup(final Grid grid) throws Exception {
        this.resize = (this.grid == null || grid.cellsInRow != this.size);
        this.grid = grid;
        this.size = grid.cellsInRow;
        if (this.resize) {
            this.xMoves = new int[grid.cellsInRow * grid.cellsInRow];
            this.yMoves = new int[grid.cellsInRow * grid.cellsInRow];
            this.values = new int[grid.cellsInRow * grid.cellsInRow];
            this.reasons = new StringBuffer[grid.cellsInRow * grid.cellsInRow];
            this.stateWrite = new boolean[grid.cellsInRow * grid.cellsInRow];
            this.xCandidates = new int[grid.cellsInRow * grid.cellsInRow * grid.cellsInRow];
            this.yCandidates = new int[grid.cellsInRow * grid.cellsInRow * grid.cellsInRow];
            this.valueCandidates = new int[grid.cellsInRow * grid.cellsInRow * grid.cellsInRow];
            this.reasonCandidates = new StringBuffer[grid.cellsInRow * grid.cellsInRow * grid.cellsInRow];
        }
        this.nMoves = 0;
        this.nCandidates = 0;
        this.score = 0;
        this.bestX = grid.cellsInRow;
        this.bestY = grid.cellsInRow;
        this.bestValue = grid.cellsInRow;
        if (this.explain) {
            for (int i = 0; i < grid.cellsInRow * grid.cellsInRow; this.reasons[i++] = new StringBuffer()) {}
        }
        if (this.state instanceof IState) {
            this.state.setup(grid.boxesAcross, grid.boxesDown);
            for (int i = 0; i < grid.cellsInRow; ++i) {
                for (int j = 0; j < grid.cellsInRow; ++j) {
                    if (grid.data[i][j] > 0) {
                        this.state.addMove(i, j, grid.data[i][j] - 1);
                    }
                }
            }
        }
    }
    
    public void selectCandidate() {
        final int pick = (this.randomize && this.nCandidates > 1) ? Math.abs(this.generator.nextInt() % this.nCandidates) : 0;
        this.bestX = this.xCandidates[pick];
        this.bestY = this.yCandidates[pick];
        this.bestValue = this.valueCandidates[pick];
        if (this.explain) {
            this.bestReason = this.reasonCandidates[pick].toString();
        }
    }
    
    public void setCandidate() {
        this.grid.data[this.bestX][this.bestY] = this.bestValue;
    }
    
    public boolean updateState(final int x, final int y, final int value, final String reason, final boolean writeState) throws Exception {
        if (this.nMoves == -1) {
            return false;
        }
        if (writeState) {
            this.state.pushState(this.nMoves);
            this.stateWrite[this.nMoves] = true;
        }
        else {
            this.stateWrite[this.nMoves] = false;
        }
        this.xMoves[this.nMoves] = x;
        this.yMoves[this.nMoves] = y;
        this.values[this.nMoves] = value - 1;
        if (this.explain) {
            this.reasons[this.nMoves].append(reason);
        }
        ++this.nMoves;
        this.state.addMove(x, y, value - 1);
        return true;
    }
    
    public boolean unwind(final int newNMoves, final boolean reset) {
        if (newNMoves < 0) {
            return false;
        }
        if (this.explain && reset) {
            this.reasons[newNMoves].append("The move (");
            this.reasons[newNMoves].append(1 + this.xMoves[newNMoves]);
            this.reasons[newNMoves].append(",");
            this.reasons[newNMoves].append(1 + this.yMoves[newNMoves]);
            this.reasons[newNMoves].append("):=");
            this.reasons[newNMoves].append(1 + this.values[newNMoves]);
            this.reasons[newNMoves].append(" would lead to a contradiction.\n");
            for (int i = newNMoves + 1; i < this.nMoves; this.reasons[i++] = new StringBuffer()) {}
        }
        this.state.popState(newNMoves);
        this.state.eliminateMove(this.xMoves[newNMoves], this.yMoves[newNMoves], this.values[newNMoves]);
        if (reset) {
            for (int i = newNMoves; i < this.nMoves; ++i) {
                this.grid.data[this.xMoves[i]][this.yMoves[i]] = 0;
            }
        }
        this.nMoves = newNMoves;
        return true;
    }
    
    public void reset() {
        this.reset(0);
    }
    
    public void reset(final int move) {
        while (this.nMoves > move && this.nMoves >= 0) {
            if (this.nMoves > this.grid.cellsInRow * this.grid.cellsInRow) {
                break;
            }
            --this.nMoves;
            this.grid.data[this.xMoves[this.nMoves]][this.yMoves[this.nMoves]] = 0;
        }
        while (this.nMoves >= 0 && this.nMoves < move && this.nMoves < this.grid.cellsInRow * this.grid.cellsInRow) {
            this.grid.data[this.xMoves[this.nMoves]][this.yMoves[this.nMoves]] = 1 + this.values[this.nMoves];
            ++this.nMoves;
        }
    }
    
    public int getBestX() {
        return this.bestX;
    }
    
    public int getBestY() {
        return this.bestY;
    }
    
    public int getBestValue() {
        return this.bestValue;
    }
    
    public String getBestReason() {
        return this.bestReason;
    }
    
    public int getXCandidate(final int index) {
        return this.xCandidates[index];
    }
    
    public int getYCandidate(final int index) {
        return this.yCandidates[index];
    }
    
    public int getValueCandidate(final int index) {
        return this.valueCandidates[index];
    }
    
    public String getReasonCandidate(final int index) {
        return this.reasonCandidates[index].toString();
    }
    
    public int getNumberOfCandidates() {
        return this.nCandidates;
    }
    
    public int getThreadLength() {
        return this.nMoves;
    }
    
    public int getThreadX(final int move) {
        return this.xMoves[move];
    }
    
    public int getThreadY(final int move) {
        return this.yMoves[move];
    }
    
    public String getReason(final int move) {
        return this.reasons[move].toString();
    }
    
    public boolean explainsReasoning() {
        return this.explain;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public int getLastWrittenMove() {
        int i = this.nMoves;
        while (--i >= 0 && !this.stateWrite[i]) {}
        return i;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.nMoves; ++i) {
            sb.append(String.valueOf(1 + i) + ". (" + (1 + this.xMoves[i]) + "," + (1 + this.yMoves[i]) + "):=" + this.grid.data[this.xMoves[i]][this.yMoves[i]] + "\n");
        }
        sb.append('\n');
        sb.append(this.state.toString());
        return sb.toString();
    }
}
