// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public class MostCandidates extends StrategyBase implements IStrategy
{
    boolean[][] mask;
    
    public MostCandidates(final boolean[][] mask, final boolean randomize) {
        super(randomize, false);
        super.randomize = randomize;
        this.mask = mask;
        super.state = new InvulnerableState();
    }
    
    public void setup(final Grid grid) throws Exception {
        super.setup(grid);
    }
    
    public int findCandidates() {
        final InvulnerableState invulnerableState = (InvulnerableState)super.state;
        int minEliminated = Integer.MAX_VALUE;
        super.nCandidates = 0;
        for (int i = 0; i < super.grid.cellsInRow; ++i) {
            for (int j = 0; j < super.grid.cellsInRow; ++j) {
                if (super.grid.data[i][j] <= 0 && (this.mask == null || this.mask[i][j])) {
                    for (int v = 0; v < super.grid.cellsInRow; ++v) {
                        if (!invulnerableState.eliminated[v][i][j] && invulnerableState.nInvulnerable[v][i][j] < minEliminated) {
                            super.nCandidates = 1;
                            minEliminated = invulnerableState.nInvulnerable[v][i][j];
                        }
                    }
                }
            }
        }
        if (super.nCandidates == 0) {
            return 0;
        }
        super.score = 3 * super.grid.cellsInRow - super.grid.boxesAcross - super.grid.boxesDown - minEliminated;
        super.nCandidates = 0;
        for (int i = 0; i < super.grid.cellsInRow; ++i) {
            for (int j = 0; j < super.grid.cellsInRow; ++j) {
                if (super.grid.data[i][j] <= 0 && (this.mask == null || this.mask[i][j])) {
                    for (int v = 0; v < super.grid.cellsInRow; ++v) {
                        if (!invulnerableState.eliminated[v][i][j] && invulnerableState.nInvulnerable[v][i][j] == minEliminated) {
                            super.xCandidates[super.nCandidates] = i;
                            super.yCandidates[super.nCandidates] = j;
                            super.valueCandidates[super.nCandidates] = v + 1;
                            ++super.nCandidates;
                            if (!super.randomize) {
                                return super.nCandidates;
                            }
                        }
                    }
                }
            }
        }
        return super.nCandidates;
    }
    
    public boolean unwind(final int newNMoves, final boolean reset) {
        if (newNMoves < ((this.mask != null) ? 1 : 0)) {
            return false;
        }
        super.state.popState(newNMoves);
        super.state.eliminateMove(super.xMoves[newNMoves], super.yMoves[newNMoves], super.values[newNMoves]);
        if (reset) {
            for (int i = newNMoves; i < super.nMoves; ++i) {
                super.grid.data[super.xMoves[i]][super.yMoves[i]] = 0;
            }
        }
        super.nMoves = newNMoves;
        return true;
    }
}
