// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public class LeastCandidatesNumber extends StrategyBase implements IStrategy
{
    boolean findMany;
    boolean[][][] considered;
    
    public LeastCandidatesNumber(final boolean randomize) {
        this(randomize, randomize, true);
    }
    
    public LeastCandidatesNumber(final boolean findMany, final boolean randomize, final boolean explain) {
        super(randomize, explain);
        this.findMany = findMany;
        super.state = new NumberState();
    }
    
    public void setup(final Grid grid) throws Exception {
        super.setup(grid);
        if (this.findMany) {
            if (super.resize) {
                this.considered = new boolean[grid.cellsInRow][grid.cellsInRow][grid.cellsInRow];
            }
            else {
                for (int i = 0; i < grid.cellsInRow; ++i) {
                    for (int j = 0; j < grid.cellsInRow; ++j) {
                        for (int k = 0; k < grid.cellsInRow; ++k) {
                            this.considered[i][j][k] = false;
                        }
                    }
                }
            }
        }
    }
    
    public int findCandidates() {
        final NumberState numberState = (NumberState)super.state;
        int maxEliminated = -1;
        super.nCandidates = 0;
    Label_0171:
        for (int i = 0; i < super.grid.cellsInRow; ++i) {
            for (int j = 0; j < 3 * super.grid.cellsInRow; ++j) {
                if (numberState.nEliminated[i][j] == super.grid.cellsInRow) {
                    super.score = 0;
                    return super.nCandidates = 0;
                }
                if (!numberState.isFilled[i][j]) {
                    if (!this.findMany && numberState.nEliminated[i][j] == super.grid.cellsInRow - 1) {
                        super.nCandidates = 1;
                        maxEliminated = super.grid.cellsInRow - 1;
                        break Label_0171;
                    }
                    if (numberState.nEliminated[i][j] > maxEliminated) {
                        super.nCandidates = 1;
                        maxEliminated = numberState.nEliminated[i][j];
                    }
                }
            }
        }
        if (super.nCandidates == 0) {
            return 0;
        }
        super.score = super.grid.cellsInRow - maxEliminated;
        super.nCandidates = 0;
        if (this.findMany) {
            for (int i = 0; i < super.grid.cellsInRow; ++i) {
                for (int j = 0; j < super.grid.cellsInRow; ++j) {
                    for (int k = 0; k < super.grid.cellsInRow; ++k) {
                        this.considered[i][j][k] = false;
                    }
                }
            }
        }
        for (int i = 0; i < super.grid.cellsInRow; ++i) {
            for (int j = 0; j < 3 * super.grid.cellsInRow; ++j) {
                if (!numberState.isFilled[i][j] && numberState.nEliminated[i][j] == maxEliminated) {
                    int k = 0;
                    while (k < super.grid.cellsInRow) {
                        if (!numberState.eliminated[i][j][k]) {
                            int x;
                            int y;
                            if (j < super.grid.cellsInRow) {
                                x = j;
                                y = k;
                            }
                            else if (j < 2 * super.grid.cellsInRow) {
                                x = k;
                                y = j - super.grid.cellsInRow;
                            }
                            else {
                                x = (j - 2 * super.grid.cellsInRow) / super.grid.boxesAcross * super.grid.boxesAcross + k / super.grid.boxesDown;
                                y = (j - 2 * super.grid.cellsInRow) % super.grid.boxesAcross * super.grid.boxesDown + k % super.grid.boxesDown;
                            }
                            if (this.findMany) {
                                if (this.considered[x][y][i]) {
                                    ++k;
                                    continue;
                                }
                                this.considered[x][y][i] = true;
                            }
                            super.xCandidates[super.nCandidates] = x;
                            super.yCandidates[super.nCandidates] = y;
                            super.valueCandidates[super.nCandidates] = i + 1;
                            if (super.explain) {
                                final StringBuffer sb = new StringBuffer();
                                sb.append("The cell (");
                                sb.append(x + 1);
                                sb.append(",");
                                sb.append(y + 1);
                                sb.append(") is ");
                                if (super.score > 1) {
                                    sb.append("one of ");
                                    sb.append(super.score);
                                    sb.append(" candidates ");
                                }
                                else {
                                    sb.append("the only candidate ");
                                }
                                sb.append("for the value ");
                                sb.append(SuDokuUtils.toString(i + 1));
                                sb.append(" in ");
                                if (j < super.grid.cellsInRow) {
                                    sb.append("Row ");
                                    sb.append(x + 1);
                                }
                                else if (j < 2 * super.grid.cellsInRow) {
                                    sb.append("Column ");
                                    sb.append(y + 1);
                                }
                                else {
                                    sb.append("Box [");
                                    sb.append(1 + (j - 2 * super.grid.cellsInRow) / super.grid.boxesAcross);
                                    sb.append(",");
                                    sb.append(1 + (j - 2 * super.grid.cellsInRow) % super.grid.boxesAcross);
                                    sb.append("]");
                                }
                                sb.append(".\n");
                                super.reasonCandidates[super.nCandidates] = sb;
                            }
                            ++super.nCandidates;
                            if (!this.findMany) {
                                return super.nCandidates;
                            }
                        }
                        ++k;
                    }
                }
            }
        }
        return super.nCandidates;
    }
}
