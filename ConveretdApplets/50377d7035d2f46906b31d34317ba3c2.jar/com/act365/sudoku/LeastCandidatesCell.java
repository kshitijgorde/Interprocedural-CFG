// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public class LeastCandidatesCell extends StrategyBase implements IStrategy
{
    boolean findMany;
    
    public LeastCandidatesCell(final boolean randomize) {
        this(randomize, randomize, true);
    }
    
    public LeastCandidatesCell(final boolean findMany, final boolean randomize, final boolean explain) {
        super(randomize, explain);
        this.findMany = findMany;
        super.state = new CellState();
    }
    
    public void setup(final Grid grid) throws Exception {
        super.setup(grid);
    }
    
    public int findCandidates() {
        final CellState cellState = (CellState)super.state;
        int maxEliminated = -1;
        super.nCandidates = 0;
    Label_0172:
        for (int i = 0; i < super.grid.cellsInRow; ++i) {
            for (int j = 0; j < super.grid.cellsInRow; ++j) {
                if (cellState.nEliminated[i][j] == super.grid.cellsInRow) {
                    super.score = 0;
                    return super.nCandidates = 0;
                }
                if (super.grid.data[i][j] <= 0) {
                    if (!this.findMany && cellState.nEliminated[i][j] == super.grid.cellsInRow - 1) {
                        super.nCandidates = 1;
                        maxEliminated = super.grid.cellsInRow - 1;
                        break Label_0172;
                    }
                    if (cellState.nEliminated[i][j] > maxEliminated) {
                        super.nCandidates = 1;
                        maxEliminated = cellState.nEliminated[i][j];
                    }
                }
            }
        }
        super.score = super.grid.cellsInRow - maxEliminated;
        if (super.nCandidates == 0) {
            return 0;
        }
        super.nCandidates = 0;
        for (int i = 0; i < super.grid.cellsInRow; ++i) {
            for (int j = 0; j < super.grid.cellsInRow; ++j) {
                if (super.grid.data[i][j] == 0 && cellState.nEliminated[i][j] == maxEliminated) {
                    for (int k = 0; k < super.grid.cellsInRow; ++k) {
                        if (!cellState.eliminated[i][j][k]) {
                            super.xCandidates[super.nCandidates] = i;
                            super.yCandidates[super.nCandidates] = j;
                            super.valueCandidates[super.nCandidates] = k + 1;
                            if (super.explain) {
                                final StringBuffer sb = new StringBuffer();
                                sb.append("The value ");
                                sb.append(SuDokuUtils.toString(k + 1));
                                sb.append(" is ");
                                if (super.score > 1) {
                                    sb.append("one of ");
                                    sb.append(super.score);
                                    sb.append(" candidates ");
                                }
                                else {
                                    sb.append("the only candidate ");
                                }
                                sb.append("for the cell (");
                                sb.append(i + 1);
                                sb.append(",");
                                sb.append(j + 1);
                                sb.append(").\n");
                                super.reasonCandidates[super.nCandidates] = sb;
                            }
                            ++super.nCandidates;
                            if (!this.findMany) {
                                return super.nCandidates;
                            }
                        }
                    }
                }
            }
        }
        return super.nCandidates;
    }
}
