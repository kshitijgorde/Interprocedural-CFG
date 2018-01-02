// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public class LeastCandidatesHybrid extends StrategyBase implements IStrategy
{
    LeastCandidatesNumber lcn;
    LeastCandidatesCell lcc;
    IStrategy better;
    boolean useDisjointSubsets;
    boolean useSingleSectorCandidates;
    boolean useXWings;
    boolean useSwordfish;
    boolean useNishio;
    boolean useGuesses;
    int singleCandidatureCalls;
    int disjointSubsetsCalls;
    int disjointSubsetsEliminations;
    int maxDisjointSubsetsSize;
    int singleSectorCandidatesCalls;
    int singleSectorCandidatesEliminations;
    int xWingsCalls;
    int xWingsEliminations;
    int swordfishCalls;
    int swordfishEliminations;
    int nishioCalls;
    int nishioEliminations;
    int nGuesses;
    int maxStrings;
    transient int[] x;
    transient int[] y;
    transient int[] linkedValues;
    transient int[] linkedCells;
    transient int[] stringR0;
    transient int[] stringC0;
    transient int[] stringR1;
    transient int[] stringC1;
    transient int[] stringLength;
    transient boolean[] union;
    transient int[][] mask;
    
    public LeastCandidatesHybrid(final boolean randomize, final boolean checkInvulnerable, final boolean useAllLogicalMethods, final boolean explain) {
        super(randomize, explain);
        this.lcn = new LeastCandidatesNumber(randomize || checkInvulnerable, randomize, explain);
        this.lcc = new LeastCandidatesCell(randomize || checkInvulnerable, randomize, explain);
        if (checkInvulnerable) {
            super.state = new InvulnerableState();
        }
        this.useDisjointSubsets = true;
        this.useSingleSectorCandidates = true;
        this.useXWings = useAllLogicalMethods;
        this.useSwordfish = useAllLogicalMethods;
        this.useNishio = useAllLogicalMethods;
        this.useGuesses = true;
    }
    
    public LeastCandidatesHybrid(final boolean randomize, final boolean explain) {
        this(randomize, false, false, explain);
    }
    
    public void setup(final Grid grid) throws Exception {
        super.setup(grid);
        this.lcn.setup(grid);
        this.lcc.setup(grid);
        if (super.state instanceof IState) {
            if (this.useDisjointSubsets) {
                this.x = new int[grid.cellsInRow];
                this.y = new int[grid.cellsInRow];
                this.linkedValues = new int[grid.cellsInRow];
                this.linkedCells = new int[grid.cellsInRow];
                this.union = new boolean[grid.cellsInRow];
            }
            if (this.useXWings || this.useSwordfish) {
                this.maxStrings = grid.cellsInRow * grid.cellsInRow * grid.cellsInRow;
                this.stringR0 = new int[this.maxStrings];
                this.stringC0 = new int[this.maxStrings];
                this.stringR1 = new int[this.maxStrings];
                this.stringC1 = new int[this.maxStrings];
                this.stringLength = new int[this.maxStrings];
            }
            if (this.useNishio) {
                this.mask = new int[grid.cellsInRow][grid.cellsInRow];
            }
        }
        this.maxDisjointSubsetsSize = (super.explain ? grid.cellsInRow : 6);
        this.singleCandidatureCalls = 0;
        final boolean b = false;
        this.disjointSubsetsEliminations = (b ? 1 : 0);
        this.disjointSubsetsCalls = (b ? 1 : 0);
        final boolean b2 = false;
        this.singleSectorCandidatesEliminations = (b2 ? 1 : 0);
        this.singleSectorCandidatesCalls = (b2 ? 1 : 0);
        final boolean b3 = false;
        this.xWingsEliminations = (b3 ? 1 : 0);
        this.xWingsCalls = (b3 ? 1 : 0);
        final boolean b4 = false;
        this.swordfishEliminations = (b4 ? 1 : 0);
        this.swordfishCalls = (b4 ? 1 : 0);
        final boolean b5 = false;
        this.nishioEliminations = (b5 ? 1 : 0);
        this.nishioCalls = (b5 ? 1 : 0);
        this.nGuesses = 0;
    }
    
    public int findCandidates() {
        final StringBuffer sb = super.explain ? new StringBuffer() : null;
        try {
            this.singleCandidature();
        }
        catch (Exception e) {
            super.score = 0;
            return super.nCandidates = 0;
        }
        if (super.state instanceof IState && super.score > 1) {
            try {
                while (true) {
                    if (this.useSingleSectorCandidates && this.singleSectorCandidates(sb)) {
                        if (this.singleCandidature()) {
                            break;
                        }
                        continue;
                    }
                    else if (this.useDisjointSubsets && this.disjointSubsets(sb)) {
                        if (this.singleCandidature()) {
                            break;
                        }
                        continue;
                    }
                    else if (this.useXWings && this.xWings(sb)) {
                        if (this.singleCandidature()) {
                            break;
                        }
                        continue;
                    }
                    else if (this.useSwordfish && this.swordfish(sb)) {
                        if (this.singleCandidature()) {
                            break;
                        }
                        continue;
                    }
                    else {
                        if (!this.useNishio || !this.nishio(sb)) {
                            break;
                        }
                        if (this.singleCandidature()) {
                            break;
                        }
                        continue;
                    }
                }
            }
            catch (Exception e) {
                super.score = 0;
                return super.nCandidates = 0;
            }
        }
        if (super.score > 1) {
            if (!this.useGuesses) {
                super.score = 0;
                return super.nCandidates = 0;
            }
            ++this.nGuesses;
        }
        super.nCandidates = 0;
        while (super.nCandidates < this.better.getNumberOfCandidates()) {
            super.xCandidates[super.nCandidates] = this.better.getXCandidate(super.nCandidates);
            super.yCandidates[super.nCandidates] = this.better.getYCandidate(super.nCandidates);
            super.valueCandidates[super.nCandidates] = this.better.getValueCandidate(super.nCandidates);
            if (super.explain) {
                (super.reasonCandidates[super.nCandidates] = new StringBuffer()).append(sb.toString());
                super.reasonCandidates[super.nCandidates].append(this.better.getReasonCandidate(super.nCandidates));
            }
            ++super.nCandidates;
        }
        if (super.state instanceof IState) {
            final InvulnerableState invulnerableState = (InvulnerableState)super.state;
            int minInvulnerable = Integer.MAX_VALUE;
            for (int i = 0; i < this.better.getNumberOfCandidates(); ++i) {
                if (invulnerableState.nInvulnerable[super.valueCandidates[i] - 1][super.xCandidates[i]][super.yCandidates[i]] < minInvulnerable) {
                    minInvulnerable = invulnerableState.nInvulnerable[super.valueCandidates[i] - 1][super.xCandidates[i]][super.yCandidates[i]];
                }
            }
            super.nCandidates = 0;
            for (int i = 0; i < this.better.getNumberOfCandidates(); ++i) {
                if (invulnerableState.nInvulnerable[super.valueCandidates[i] - 1][super.xCandidates[i]][super.yCandidates[i]] == minInvulnerable) {
                    super.xCandidates[super.nCandidates] = super.xCandidates[i];
                    super.yCandidates[super.nCandidates] = super.yCandidates[i];
                    super.valueCandidates[super.nCandidates] = super.valueCandidates[i];
                    if (super.explain) {
                        super.reasonCandidates[super.nCandidates] = super.reasonCandidates[i];
                    }
                    ++super.nCandidates;
                    if (!super.randomize) {
                        return super.nCandidates;
                    }
                }
            }
        }
        return super.nCandidates;
    }
    
    boolean singleCandidature() throws Exception {
        ++this.singleCandidatureCalls;
        if (this.lcc.findCandidates() == 0 || (this.lcc.getScore() > 1 && this.lcn.findCandidates() == 0)) {
            throw new Exception("Bad grid state");
        }
        if (this.lcc.getScore() == 1 || this.lcc.getScore() < this.lcn.getScore()) {
            this.better = this.lcc;
        }
        else {
            this.better = this.lcn;
        }
        final int score = this.better.getScore();
        super.score = score;
        return score == 1;
    }
    
    boolean disjointSubsets(final StringBuffer sb) throws Exception {
        ++this.disjointSubsetsCalls;
        final CellState cellState = (CellState)this.lcc.state;
        final NumberState numberState = (NumberState)this.lcn.state;
        for (int s = 0; s < 3 * super.grid.cellsInRow; ++s) {
            int nUnfilled = 0;
            for (int i = 0; i < super.grid.cellsInRow; ++i) {
                if (!numberState.isFilled[i][s]) {
                    ++nUnfilled;
                }
            }
            this.linkedValues[0] = 0;
            int subsetSize = 1;
            while (this.linkedValues[subsetSize - 1] < super.grid.cellsInRow && (numberState.nEliminated[this.linkedValues[subsetSize - 1]][s] == 0 || numberState.nEliminated[this.linkedValues[subsetSize - 1]][s] == super.grid.cellsInRow - 1)) {
                final int[] linkedValues = this.linkedValues;
                final int n = subsetSize - 1;
                ++linkedValues[n];
            }
            if (this.linkedValues[subsetSize - 1] != super.grid.cellsInRow) {
                int nUnconsideredValues = 0;
                for (int i = this.linkedValues[subsetSize - 1] + 1; i < super.grid.cellsInRow; ++i) {
                    if (!numberState.isFilled[i][s]) {
                        ++nUnconsideredValues;
                    }
                }
                int unionSize = super.grid.cellsInRow - numberState.nEliminated[this.linkedValues[0]][s];
                while (true) {
                    boolean anyMoveEliminated = false;
                    if (unionSize < subsetSize) {
                        throw new Exception("Bad grid state");
                    }
                    if (unionSize == subsetSize && unionSize > 1 && unionSize < nUnfilled) {
                        int i = 0;
                        for (int j = 0; j < super.grid.cellsInRow; ++j) {
                            if (this.union[j]) {
                                this.linkedCells[i++] = j;
                            }
                        }
                        for (i = 0; i < subsetSize; ++i) {
                            if (s < super.grid.cellsInRow) {
                                this.x[i] = s;
                                this.y[i] = this.linkedCells[i];
                            }
                            else if (s < 2 * super.grid.cellsInRow) {
                                this.x[i] = this.linkedCells[i];
                                this.y[i] = s - super.grid.cellsInRow;
                            }
                            else {
                                this.x[i] = (s - 2 * super.grid.cellsInRow) / super.grid.boxesAcross * super.grid.boxesAcross + this.linkedCells[i] / super.grid.boxesDown;
                                this.y[i] = (s - 2 * super.grid.cellsInRow) % super.grid.boxesAcross * super.grid.boxesDown + this.linkedCells[i] % super.grid.boxesDown;
                            }
                            int j = 0;
                        Label_0710:
                            while (j < super.grid.cellsInRow) {
                                if (cellState.eliminated[this.x[i]][this.y[i]][j]) {
                                    ++j;
                                }
                                else {
                                    for (int k = 0; k < subsetSize; ++k) {
                                        if (j == this.linkedValues[k]) {
                                            ++j;
                                            continue Label_0710;
                                        }
                                    }
                                    numberState.eliminateMove(this.x[i], this.y[i], j);
                                    cellState.eliminateMove(this.x[i], this.y[i], j);
                                    super.state.eliminateMove(this.x[i], this.y[i], j);
                                    anyMoveEliminated = true;
                                    ++this.disjointSubsetsEliminations;
                                    ++j;
                                }
                            }
                        }
                        if (anyMoveEliminated) {
                            if (super.explain) {
                                sb.append("The values ");
                                sb.append(SuDokuUtils.toString(1 + this.linkedValues[0]));
                                i = 1;
                                while (i < subsetSize - 1) {
                                    sb.append(", ");
                                    sb.append(SuDokuUtils.toString(1 + this.linkedValues[i++]));
                                }
                                sb.append(" and ");
                                sb.append(1 + this.linkedValues[i]);
                                sb.append(" occupy the cells (");
                                sb.append(1 + this.x[0]);
                                sb.append(",");
                                sb.append(1 + this.y[0]);
                                sb.append(")");
                                for (i = 1; i < subsetSize - 1; ++i) {
                                    sb.append(", (");
                                    sb.append(1 + this.x[i]);
                                    sb.append(",");
                                    sb.append(1 + this.y[i]);
                                    sb.append(")");
                                }
                                sb.append(" and (");
                                sb.append(1 + this.x[i]);
                                sb.append(",");
                                sb.append(1 + this.y[i]);
                                sb.append(") in some order.\n");
                            }
                            return true;
                        }
                        if (this.linkedValues[0] >= super.grid.cellsInRow - 1) {
                            break;
                        }
                        final int[] linkedValues2 = this.linkedValues;
                        final int n2 = 0;
                        ++linkedValues2[n2];
                        subsetSize = 1;
                    }
                    else if (unionSize >= this.maxDisjointSubsetsSize || unionSize > subsetSize + nUnconsideredValues || unionSize >= nUnfilled) {
                        final int[] linkedValues3 = this.linkedValues;
                        final int n3 = subsetSize - 1;
                        ++linkedValues3[n3];
                    }
                    else {
                        this.linkedValues[subsetSize] = this.linkedValues[subsetSize - 1] + 1;
                        ++subsetSize;
                    }
                    while (subsetSize > 0) {
                        while (this.linkedValues[subsetSize - 1] < super.grid.cellsInRow && (numberState.nEliminated[this.linkedValues[subsetSize - 1]][s] == 0 || numberState.nEliminated[this.linkedValues[subsetSize - 1]][s] == super.grid.cellsInRow - 1)) {
                            final int[] linkedValues4 = this.linkedValues;
                            final int n4 = subsetSize - 1;
                            ++linkedValues4[n4];
                        }
                        if (this.linkedValues[subsetSize - 1] != super.grid.cellsInRow) {
                            break;
                        }
                        if (--subsetSize <= 0) {
                            continue;
                        }
                        final int[] linkedValues5 = this.linkedValues;
                        final int n5 = subsetSize - 1;
                        ++linkedValues5[n5];
                    }
                    if (subsetSize == 0) {
                        break;
                    }
                    nUnconsideredValues = 0;
                    for (int i = this.linkedValues[subsetSize - 1] + 1; i < super.grid.cellsInRow; ++i) {
                        if (!numberState.isFilled[i][s]) {
                            ++nUnconsideredValues;
                        }
                    }
                    unionSize = 0;
                    for (int i = 0; i < super.grid.cellsInRow; this.union[i++] = false) {}
                    for (int j = 0; j < subsetSize; ++j) {
                        for (int i = 0; i < super.grid.cellsInRow; ++i) {
                            if (!this.union[i] && !numberState.eliminated[this.linkedValues[j]][s][i]) {
                                this.union[i] = true;
                                ++unionSize;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    boolean singleSectorCandidates(final StringBuffer sb) throws Exception {
        ++this.singleSectorCandidatesCalls;
        final CellState cellState = (CellState)this.lcc.state;
        final NumberState numberState = (NumberState)this.lcn.state;
        for (int value = 0; value < super.grid.cellsInRow; ++value) {
            int s;
            for (s = 0; s < 2 * super.grid.cellsInRow; ++s) {
                if (numberState.nEliminated[value][s] == super.grid.cellsInRow) {
                    throw new Exception("Bad grid state");
                }
                if (numberState.nEliminated[value][s] != super.grid.cellsInRow - 1) {
                    int box = -1;
                    int i;
                    for (i = 0; i < super.grid.cellsInRow; ++i) {
                        if (!numberState.eliminated[value][s][i]) {
                            int x0;
                            int y0;
                            if (s < super.grid.cellsInRow) {
                                x0 = s;
                                y0 = i;
                            }
                            else {
                                x0 = i;
                                y0 = s - super.grid.cellsInRow;
                            }
                            if (box == -1) {
                                box = x0 / super.grid.boxesAcross * super.grid.boxesAcross + y0 / super.grid.boxesDown;
                            }
                            else if (box != x0 / super.grid.boxesAcross * super.grid.boxesAcross + y0 / super.grid.boxesDown) {
                                break;
                            }
                        }
                    }
                    boolean anyMoveEliminated = false;
                    if (i == super.grid.cellsInRow) {
                        final int xLower = box / super.grid.boxesAcross * super.grid.boxesAcross;
                        final int xUpper = (box / super.grid.boxesAcross + 1) * super.grid.boxesAcross;
                        final int yLower = box % super.grid.boxesAcross * super.grid.boxesDown;
                        final int yUpper = (box % super.grid.boxesAcross + 1) * super.grid.boxesDown;
                        final int j = 0;
                        for (int x0 = xLower; x0 < xUpper; ++x0) {
                            if (s >= super.grid.cellsInRow || s != x0) {
                                for (int y0 = yLower; y0 < yUpper; ++y0) {
                                    if (s < super.grid.cellsInRow || s - super.grid.cellsInRow != y0) {
                                        if (!cellState.eliminated[x0][y0][value]) {
                                            numberState.eliminateMove(x0, y0, value);
                                            cellState.eliminateMove(x0, y0, value);
                                            super.state.eliminateMove(x0, y0, value);
                                            anyMoveEliminated = true;
                                            ++this.singleSectorCandidatesEliminations;
                                        }
                                    }
                                }
                            }
                        }
                        if (anyMoveEliminated) {
                            if (super.explain) {
                                sb.append("The value ");
                                sb.append(SuDokuUtils.toString(1 + value));
                                sb.append(" in Box [");
                                sb.append(1 + box / super.grid.boxesAcross);
                                sb.append(",");
                                sb.append(1 + box % super.grid.boxesAcross);
                                sb.append("] must lie in ");
                                if (s < super.grid.cellsInRow) {
                                    sb.append("Row ");
                                    sb.append(1 + s);
                                }
                                else {
                                    sb.append("Column ");
                                    sb.append(1 + s - super.grid.cellsInRow);
                                }
                                sb.append(".\n");
                            }
                            return true;
                        }
                    }
                }
            }
            while (s < 3 * super.grid.cellsInRow) {
                if (numberState.nEliminated[value][s] == super.grid.cellsInRow) {
                    throw new Exception("Bad grid state");
                }
                if (numberState.nEliminated[value][s] == super.grid.cellsInRow - 1) {
                    ++s;
                }
                else {
                    int row;
                    int column = row = -1;
                    int i;
                    for (i = 0; i < super.grid.cellsInRow; ++i) {
                        if (!numberState.eliminated[value][s][i]) {
                            final int x0 = (s - 2 * super.grid.cellsInRow) / super.grid.boxesAcross * super.grid.boxesAcross + i / super.grid.boxesDown;
                            final int y0 = (s - 2 * super.grid.cellsInRow) % super.grid.boxesAcross * super.grid.boxesDown + i % super.grid.boxesDown;
                            if (row == -1 && column == -1) {
                                row = x0;
                                column = y0;
                            }
                            else if (row == -1) {
                                if (y0 != column) {
                                    break;
                                }
                            }
                            else if (column == -1) {
                                if (x0 != row) {
                                    break;
                                }
                            }
                            else if (x0 == row) {
                                column = -1;
                            }
                            else {
                                if (y0 != column) {
                                    break;
                                }
                                row = -1;
                            }
                        }
                    }
                    boolean anyMoveEliminated = false;
                    if (i == super.grid.cellsInRow) {
                        final int xLower = (s - 2 * super.grid.cellsInRow) / super.grid.boxesAcross * super.grid.boxesAcross;
                        final int xUpper = ((s - 2 * super.grid.cellsInRow) / super.grid.boxesAcross + 1) * super.grid.boxesAcross;
                        final int yLower = (s - 2 * super.grid.cellsInRow) % super.grid.boxesAcross * super.grid.boxesDown;
                        final int yUpper = ((s - 2 * super.grid.cellsInRow) % super.grid.boxesAcross + 1) * super.grid.boxesDown;
                        for (int j = 0; j < super.grid.cellsInRow; ++j) {
                            int x0;
                            int y0;
                            if (column == -1) {
                                x0 = row;
                                y0 = j;
                            }
                            else {
                                x0 = j;
                                y0 = column;
                            }
                            if (xLower > x0 || x0 >= xUpper || yLower > y0 || y0 >= yUpper) {
                                if (!cellState.eliminated[x0][y0][value]) {
                                    numberState.eliminateMove(x0, y0, value);
                                    cellState.eliminateMove(x0, y0, value);
                                    super.state.eliminateMove(x0, y0, value);
                                    anyMoveEliminated = true;
                                    ++this.singleSectorCandidatesEliminations;
                                }
                            }
                        }
                        if (anyMoveEliminated) {
                            if (super.explain) {
                                sb.append("The value ");
                                sb.append(SuDokuUtils.toString(1 + value));
                                sb.append(" in ");
                                if (column == -1) {
                                    sb.append("Row ");
                                    sb.append(1 + row);
                                }
                                else {
                                    sb.append("Column ");
                                    sb.append(1 + column);
                                }
                                sb.append(" must lie in Box [");
                                sb.append(1 + (s - 2 * super.grid.cellsInRow) / super.grid.boxesAcross);
                                sb.append(",");
                                sb.append(1 + (s - 2 * super.grid.cellsInRow) % super.grid.boxesAcross);
                                sb.append("].\n");
                            }
                            return true;
                        }
                    }
                    ++s;
                }
            }
        }
        return false;
    }
    
    boolean xWings(final StringBuffer sb) {
        ++this.xWingsCalls;
        for (int v = 0; v < super.grid.cellsInRow; ++v) {
            for (int nUnitStrings = this.unitStrings(v), i = 0; i < nUnitStrings - 1; ++i) {
                for (int j = i + 1; j < nUnitStrings; ++j) {
                    if (this.xWings(v, i, j, sb)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    int unitStrings(final int v) {
        final NumberState numberState = (NumberState)this.lcn.state;
        int nStrings = 0;
        int s = 0;
        while (s < 3 * super.grid.cellsInRow && nStrings < this.maxStrings) {
            if (numberState.nEliminated[v][s] != super.grid.cellsInRow - 2) {
                ++s;
            }
            else {
                int t0;
                for (t0 = 0; numberState.eliminated[v][s][t0]; ++t0) {}
                int t2;
                for (t2 = t0 + 1; numberState.eliminated[v][s][t2]; ++t2) {}
                int x2;
                int x1;
                int y0;
                int y2;
                if (s < super.grid.cellsInRow) {
                    x1 = (x2 = s);
                    y0 = t0;
                    y2 = t2;
                }
                else if (s < 2 * super.grid.cellsInRow) {
                    x2 = t0;
                    x1 = t2;
                    y2 = (y0 = s - super.grid.cellsInRow);
                }
                else {
                    x2 = (s - 2 * super.grid.cellsInRow) / super.grid.boxesAcross * super.grid.boxesAcross + t0 / super.grid.boxesDown;
                    x1 = (s - 2 * super.grid.cellsInRow) / super.grid.boxesAcross * super.grid.boxesAcross + t2 / super.grid.boxesDown;
                    y0 = (s - 2 * super.grid.cellsInRow) % super.grid.boxesAcross * super.grid.boxesDown + t0 % super.grid.boxesDown;
                    y2 = (s - 2 * super.grid.cellsInRow) % super.grid.boxesAcross * super.grid.boxesDown + t2 % super.grid.boxesDown;
                    if (x2 == x1 || y0 == y2) {
                        ++s;
                        continue;
                    }
                }
                this.stringR0[nStrings] = x2;
                this.stringC0[nStrings] = y0;
                this.stringR1[nStrings] = x1;
                this.stringC1[nStrings] = y2;
                this.stringLength[nStrings] = 1;
                ++nStrings;
                ++s;
            }
        }
        return nStrings;
    }
    
    boolean swordfish(final StringBuffer sb) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: getfield        com/act365/sudoku/LeastCandidatesHybrid.swordfishCalls:I
        //     5: iconst_1       
        //     6: iadd           
        //     7: putfield        com/act365/sudoku/LeastCandidatesHybrid.swordfishCalls:I
        //    10: iconst_0       
        //    11: istore_2        /* v */
        //    12: goto            823
        //    15: aload_0         /* this */
        //    16: iload_2         /* v */
        //    17: invokevirtual   com/act365/sudoku/LeastCandidatesHybrid.unitStrings:(I)I
        //    20: dup            
        //    21: istore          nUnitStrings
        //    23: istore          nStrings
        //    25: iconst_0       
        //    26: istore          longStringsBegin
        //    28: iload           nStrings
        //    30: istore          longStringsEnd
        //    32: goto            680
        //    35: iconst_0       
        //    36: istore_3        /* i */
        //    37: goto            666
        //    40: iconst_1       
        //    41: iload_3         /* i */
        //    42: iadd           
        //    43: iload           longStringsBegin
        //    45: invokestatic    java/lang/Math.max:(II)I
        //    48: istore          j
        //    50: goto            647
        //    53: aload_0         /* this */
        //    54: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //    57: iload_3         /* i */
        //    58: iaload         
        //    59: aload_0         /* this */
        //    60: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //    63: iload           j
        //    65: iaload         
        //    66: if_icmpne       154
        //    69: aload_0         /* this */
        //    70: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //    73: iload_3         /* i */
        //    74: iaload         
        //    75: aload_0         /* this */
        //    76: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //    79: iload           j
        //    81: iaload         
        //    82: if_icmpne       154
        //    85: aload_0         /* this */
        //    86: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //    89: iload_3         /* i */
        //    90: iaload         
        //    91: aload_0         /* this */
        //    92: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //    95: iload           j
        //    97: iaload         
        //    98: if_icmpne       117
        //   101: aload_0         /* this */
        //   102: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   105: iload_3         /* i */
        //   106: iaload         
        //   107: aload_0         /* this */
        //   108: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   111: iload           j
        //   113: iaload         
        //   114: if_icmpeq       154
        //   117: aload_0         /* this */
        //   118: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //   121: iload_3         /* i */
        //   122: iaload         
        //   123: istore          x0
        //   125: aload_0         /* this */
        //   126: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   129: iload_3         /* i */
        //   130: iaload         
        //   131: istore          y0
        //   133: aload_0         /* this */
        //   134: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //   137: iload           j
        //   139: iaload         
        //   140: istore          x1
        //   142: aload_0         /* this */
        //   143: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   146: iload           j
        //   148: iaload         
        //   149: istore          y1
        //   151: goto            463
        //   154: aload_0         /* this */
        //   155: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //   158: iload_3         /* i */
        //   159: iaload         
        //   160: aload_0         /* this */
        //   161: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //   164: iload           j
        //   166: iaload         
        //   167: if_icmpne       255
        //   170: aload_0         /* this */
        //   171: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   174: iload_3         /* i */
        //   175: iaload         
        //   176: aload_0         /* this */
        //   177: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   180: iload           j
        //   182: iaload         
        //   183: if_icmpne       255
        //   186: aload_0         /* this */
        //   187: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //   190: iload_3         /* i */
        //   191: iaload         
        //   192: aload_0         /* this */
        //   193: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //   196: iload           j
        //   198: iaload         
        //   199: if_icmpne       218
        //   202: aload_0         /* this */
        //   203: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //   206: iload_3         /* i */
        //   207: iaload         
        //   208: aload_0         /* this */
        //   209: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //   212: iload           j
        //   214: iaload         
        //   215: if_icmpeq       255
        //   218: aload_0         /* this */
        //   219: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //   222: iload_3         /* i */
        //   223: iaload         
        //   224: istore          x0
        //   226: aload_0         /* this */
        //   227: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //   230: iload_3         /* i */
        //   231: iaload         
        //   232: istore          y0
        //   234: aload_0         /* this */
        //   235: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //   238: iload           j
        //   240: iaload         
        //   241: istore          x1
        //   243: aload_0         /* this */
        //   244: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //   247: iload           j
        //   249: iaload         
        //   250: istore          y1
        //   252: goto            463
        //   255: aload_0         /* this */
        //   256: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //   259: iload_3         /* i */
        //   260: iaload         
        //   261: aload_0         /* this */
        //   262: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //   265: iload           j
        //   267: iaload         
        //   268: if_icmpne       356
        //   271: aload_0         /* this */
        //   272: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //   275: iload_3         /* i */
        //   276: iaload         
        //   277: aload_0         /* this */
        //   278: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   281: iload           j
        //   283: iaload         
        //   284: if_icmpne       356
        //   287: aload_0         /* this */
        //   288: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //   291: iload_3         /* i */
        //   292: iaload         
        //   293: aload_0         /* this */
        //   294: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //   297: iload           j
        //   299: iaload         
        //   300: if_icmpne       319
        //   303: aload_0         /* this */
        //   304: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   307: iload_3         /* i */
        //   308: iaload         
        //   309: aload_0         /* this */
        //   310: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //   313: iload           j
        //   315: iaload         
        //   316: if_icmpeq       356
        //   319: aload_0         /* this */
        //   320: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //   323: iload_3         /* i */
        //   324: iaload         
        //   325: istore          x0
        //   327: aload_0         /* this */
        //   328: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   331: iload_3         /* i */
        //   332: iaload         
        //   333: istore          y0
        //   335: aload_0         /* this */
        //   336: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //   339: iload           j
        //   341: iaload         
        //   342: istore          x1
        //   344: aload_0         /* this */
        //   345: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //   348: iload           j
        //   350: iaload         
        //   351: istore          y1
        //   353: goto            463
        //   356: aload_0         /* this */
        //   357: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //   360: iload_3         /* i */
        //   361: iaload         
        //   362: aload_0         /* this */
        //   363: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //   366: iload           j
        //   368: iaload         
        //   369: if_icmpne       457
        //   372: aload_0         /* this */
        //   373: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   376: iload_3         /* i */
        //   377: iaload         
        //   378: aload_0         /* this */
        //   379: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //   382: iload           j
        //   384: iaload         
        //   385: if_icmpne       457
        //   388: aload_0         /* this */
        //   389: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //   392: iload_3         /* i */
        //   393: iaload         
        //   394: aload_0         /* this */
        //   395: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //   398: iload           j
        //   400: iaload         
        //   401: if_icmpne       420
        //   404: aload_0         /* this */
        //   405: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //   408: iload_3         /* i */
        //   409: iaload         
        //   410: aload_0         /* this */
        //   411: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   414: iload           j
        //   416: iaload         
        //   417: if_icmpeq       457
        //   420: aload_0         /* this */
        //   421: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //   424: iload_3         /* i */
        //   425: iaload         
        //   426: istore          x0
        //   428: aload_0         /* this */
        //   429: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //   432: iload_3         /* i */
        //   433: iaload         
        //   434: istore          y0
        //   436: aload_0         /* this */
        //   437: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //   440: iload           j
        //   442: iaload         
        //   443: istore          x1
        //   445: aload_0         /* this */
        //   446: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   449: iload           j
        //   451: iaload         
        //   452: istore          y1
        //   454: goto            463
        //   457: iinc            j, 1
        //   460: goto            647
        //   463: iload           6
        //   465: iload           8
        //   467: if_icmplt       484
        //   470: iload           x0
        //   472: iload           x1
        //   474: if_icmpne       503
        //   477: iload           y0
        //   479: iload           y1
        //   481: if_icmpge       503
        //   484: iload           x0
        //   486: istore          r0
        //   488: iload           y0
        //   490: istore          c0
        //   492: iload           x1
        //   494: istore          r1
        //   496: iload           y1
        //   498: istore          c1
        //   500: goto            519
        //   503: iload           x1
        //   505: istore          r0
        //   507: iload           y1
        //   509: istore          c0
        //   511: iload           x0
        //   513: istore          r1
        //   515: iload           y0
        //   517: istore          c1
        //   519: iconst_0       
        //   520: istore          k
        //   522: goto            582
        //   525: iload           r0
        //   527: aload_0         /* this */
        //   528: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //   531: iload           k
        //   533: iaload         
        //   534: if_icmpne       579
        //   537: iload           c0
        //   539: aload_0         /* this */
        //   540: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //   543: iload           k
        //   545: iaload         
        //   546: if_icmpne       579
        //   549: iload           r1
        //   551: aload_0         /* this */
        //   552: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //   555: iload           k
        //   557: iaload         
        //   558: if_icmpne       579
        //   561: iload           c1
        //   563: aload_0         /* this */
        //   564: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   567: iload           k
        //   569: iaload         
        //   570: if_icmpne       579
        //   573: iinc            j, 1
        //   576: goto            647
        //   579: iinc            k, 1
        //   582: iload           k
        //   584: iload           nStrings
        //   586: if_icmplt       525
        //   589: aload_0         /* this */
        //   590: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR0:[I
        //   593: iload           nStrings
        //   595: iload           r0
        //   597: iastore        
        //   598: aload_0         /* this */
        //   599: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC0:[I
        //   602: iload           nStrings
        //   604: iload           c0
        //   606: iastore        
        //   607: aload_0         /* this */
        //   608: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringR1:[I
        //   611: iload           nStrings
        //   613: iload           r1
        //   615: iastore        
        //   616: aload_0         /* this */
        //   617: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringC1:[I
        //   620: iload           nStrings
        //   622: iload           c1
        //   624: iastore        
        //   625: aload_0         /* this */
        //   626: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringLength:[I
        //   629: iload           nStrings
        //   631: iinc            nStrings, 1
        //   634: iconst_1       
        //   635: aload_0         /* this */
        //   636: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringLength:[I
        //   639: iload           j
        //   641: iaload         
        //   642: iadd           
        //   643: iastore        
        //   644: iinc            j, 1
        //   647: iload           j
        //   649: iload           longStringsEnd
        //   651: if_icmpge       663
        //   654: iload           nStrings
        //   656: aload_0         /* this */
        //   657: getfield        com/act365/sudoku/LeastCandidatesHybrid.maxStrings:I
        //   660: if_icmplt       53
        //   663: iinc            i, 1
        //   666: iload_3         /* i */
        //   667: iload           nUnitStrings
        //   669: if_icmplt       40
        //   672: iload           longStringsEnd
        //   674: istore          longStringsBegin
        //   676: iload           nStrings
        //   678: istore          longStringsEnd
        //   680: iload           longStringsBegin
        //   682: iload           longStringsEnd
        //   684: if_icmplt       35
        //   687: iload           nStrings
        //   689: aload_0         /* this */
        //   690: getfield        com/act365/sudoku/LeastCandidatesHybrid.maxStrings:I
        //   693: if_icmpne       726
        //   696: getstatic       java/lang/System.err:Ljava/io/PrintStream;
        //   699: new             Ljava/lang/StringBuffer;
        //   702: dup            
        //   703: ldc_w           "String buffer is full with "
        //   706: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   709: iload           nStrings
        //   711: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   714: ldc_w           " elements"
        //   717: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   720: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   723: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   726: iconst_0       
        //   727: istore_3        /* i */
        //   728: goto            812
        //   731: aload_0         /* this */
        //   732: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringLength:[I
        //   735: iload_3         /* i */
        //   736: iaload         
        //   737: iconst_2       
        //   738: irem           
        //   739: ifne            748
        //   742: iinc            i, 1
        //   745: goto            812
        //   748: iload_3         /* i */
        //   749: iconst_1       
        //   750: iadd           
        //   751: istore          j
        //   753: goto            802
        //   756: aload_0         /* this */
        //   757: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringLength:[I
        //   760: iload           j
        //   762: iaload         
        //   763: iconst_1       
        //   764: if_icmpeq       779
        //   767: aload_0         /* this */
        //   768: getfield        com/act365/sudoku/LeastCandidatesHybrid.stringLength:[I
        //   771: iload           j
        //   773: iaload         
        //   774: iconst_2       
        //   775: irem           
        //   776: ifne            785
        //   779: iinc            j, 1
        //   782: goto            802
        //   785: aload_0         /* this */
        //   786: iload_2         /* v */
        //   787: iload_3         /* i */
        //   788: iload           j
        //   790: aload_1         /* sb */
        //   791: invokevirtual   com/act365/sudoku/LeastCandidatesHybrid.xWings:(IIILjava/lang/StringBuffer;)Z
        //   794: ifeq            799
        //   797: iconst_1       
        //   798: ireturn        
        //   799: iinc            j, 1
        //   802: iload           j
        //   804: iload           nStrings
        //   806: if_icmplt       756
        //   809: iinc            i, 1
        //   812: iload_3         /* i */
        //   813: iload           nStrings
        //   815: iconst_1       
        //   816: isub           
        //   817: if_icmplt       731
        //   820: iinc            v, 1
        //   823: iload_2         /* v */
        //   824: aload_0         /* this */
        //   825: getfield        com/act365/sudoku/StrategyBase.grid:Lcom/act365/sudoku/Grid;
        //   828: getfield        com/act365/sudoku/Grid.cellsInRow:I
        //   831: if_icmplt       15
        //   834: iconst_0       
        //   835: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name              Signature
        //  -----  ------  ----  ----------------  -----------------------------------------
        //  0      836     0     this              Lcom/act365/sudoku/LeastCandidatesHybrid;
        //  0      836     1     sb                Ljava/lang/StringBuffer;
        //  12     824     2     v                 I
        //  37     643     3     i                 I
        //  728    95      3     i                 I
        //  50     616     4     j                 I
        //  753    59      4     j                 I
        //  522    125     5     k                 I
        //  125    29      6     x0                I
        //  226    29      6     x0                I
        //  327    29      6     x0                I
        //  428    29      6     x0                I
        //  470    177     6     x0                I
        //  133    21      7     y0                I
        //  234    21      7     y0                I
        //  335    21      7     y0                I
        //  436    21      7     y0                I
        //  470    177     7     y0                I
        //  142    12      8     x1                I
        //  243    12      8     x1                I
        //  344    12      8     x1                I
        //  445    12      8     x1                I
        //  470    177     8     x1                I
        //  151    3       9     y1                I
        //  252    3       9     y1                I
        //  353    3       9     y1                I
        //  454    3       9     y1                I
        //  470    177     9     y1                I
        //  488    15      10    r0                I
        //  507    140     10    r0                I
        //  492    11      11    c0                I
        //  511    136     11    c0                I
        //  496    7       12    r1                I
        //  515    132     12    r1                I
        //  500    3       13    c1                I
        //  519    128     13    c1                I
        //  25     798     14    nStrings          I
        //  23     800     15    nUnitStrings      I
        //  28     795     16    longStringsBegin  I
        //  32     791     17    longStringsEnd    I
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    boolean xWings(final int v, final int i, final int j, final StringBuffer sb) {
        boolean anyMoveEliminated = false;
        final CellState cellState = (CellState)this.lcc.state;
        final NumberState numberState = (NumberState)this.lcn.state;
        if ((this.stringR0[i] == this.stringR0[j] && this.stringC0[i] == this.stringC0[j]) || (this.stringR0[i] == this.stringR1[j] && this.stringC0[i] == this.stringC1[j]) || (this.stringR1[i] == this.stringR0[j] && this.stringC1[i] == this.stringC0[j]) || (this.stringR1[i] == this.stringR1[j] && this.stringC1[i] == this.stringC1[j])) {
            return anyMoveEliminated;
        }
        if (this.stringR0[i] == this.stringR0[j] && this.stringR1[i] == this.stringR1[j]) {
            for (int k = 0; k < super.grid.cellsInRow; ++k) {
                if (k != this.stringC0[i] && k != this.stringC0[j]) {
                    if (!cellState.eliminated[this.stringR0[i]][k][v]) {
                        numberState.eliminateMove(this.stringR0[i], k, v);
                        cellState.eliminateMove(this.stringR0[i], k, v);
                        super.state.eliminateMove(this.stringR0[i], k, v);
                        anyMoveEliminated = true;
                        if (this.stringLength[i] == 1 && this.stringLength[j] == 1) {
                            ++this.xWingsEliminations;
                        }
                        else {
                            ++this.swordfishEliminations;
                        }
                    }
                }
            }
            for (int k = 0; k < super.grid.cellsInRow; ++k) {
                if (k != this.stringC1[i] && k != this.stringC1[j]) {
                    if (!cellState.eliminated[this.stringR1[i]][k][v]) {
                        numberState.eliminateMove(this.stringR1[i], k, v);
                        cellState.eliminateMove(this.stringR1[i], k, v);
                        super.state.eliminateMove(this.stringR1[i], k, v);
                        anyMoveEliminated = true;
                        if (this.stringLength[i] == 1 && this.stringLength[j] == 1) {
                            ++this.xWingsEliminations;
                        }
                        else {
                            ++this.swordfishEliminations;
                        }
                    }
                }
            }
        }
        if (this.stringC0[i] == this.stringC0[j] && this.stringC1[i] == this.stringC1[j]) {
            for (int k = 0; k < super.grid.cellsInRow; ++k) {
                if (k != this.stringR0[i] && k != this.stringR0[j]) {
                    if (!cellState.eliminated[k][this.stringC0[i]][v]) {
                        numberState.eliminateMove(k, this.stringC0[i], v);
                        cellState.eliminateMove(k, this.stringC0[i], v);
                        super.state.eliminateMove(k, this.stringC0[i], v);
                        anyMoveEliminated = true;
                        if (this.stringLength[i] == 1 && this.stringLength[j] == 1) {
                            ++this.xWingsEliminations;
                        }
                        else {
                            ++this.swordfishEliminations;
                        }
                    }
                }
            }
            for (int k = 0; k < super.grid.cellsInRow; ++k) {
                if (k != this.stringR1[i] && k != this.stringR1[j]) {
                    if (!cellState.eliminated[k][this.stringC1[i]][v]) {
                        numberState.eliminateMove(k, this.stringC1[i], v);
                        cellState.eliminateMove(k, this.stringC1[i], v);
                        super.state.eliminateMove(k, this.stringC1[i], v);
                        anyMoveEliminated = true;
                        if (this.stringLength[i] == 1 && this.stringLength[j] == 1) {
                            ++this.xWingsEliminations;
                        }
                        else {
                            ++this.swordfishEliminations;
                        }
                    }
                }
            }
        }
        if (anyMoveEliminated) {
            if (super.explain) {
                sb.append(SuDokuUtils.toString(1 + v));
                sb.append("s must appear in the cells (");
                sb.append(1 + this.stringR0[i]);
                sb.append(",");
                sb.append(1 + this.stringC0[i]);
                sb.append(") and (");
                sb.append(1 + this.stringR1[j]);
                sb.append(",");
                sb.append(1 + this.stringC1[j]);
                sb.append(") or the cells (");
                sb.append(1 + this.stringR0[j]);
                sb.append(",");
                sb.append(1 + this.stringC0[j]);
                sb.append(") and (");
                sb.append(1 + this.stringR1[i]);
                sb.append(",");
                sb.append(1 + this.stringC1[i]);
                sb.append("). [");
                if (this.stringLength[j] == 1) {
                    sb.append("X-Wing");
                }
                else {
                    sb.append(this.stringLength[j]);
                    sb.append("-leg Swordfish");
                }
                sb.append("]\n");
            }
            return true;
        }
        return false;
    }
    
    boolean nishio(final StringBuffer sb) {
        ++this.nishioCalls;
        final CellState cellState = (CellState)this.lcc.state;
        final NumberState numberState = (NumberState)this.lcn.state;
        for (int v = 0; v < super.grid.cellsInRow; ++v) {
            for (int i = 0; i < super.grid.cellsInRow; ++i) {
                for (int j = 0; j < super.grid.cellsInRow; ++j) {
                    if (!cellState.eliminated[i][j][v] && cellState.nEliminated[i][j] != super.grid.cellsInRow - 1) {
                        int x0 = i;
                        int y0 = j;
                        boolean candidateNominated = true;
                        for (int r = 0; r < super.grid.cellsInRow; ++r) {
                            for (int c = 0; c < super.grid.cellsInRow; ++c) {
                                if (cellState.eliminated[r][c][v]) {
                                    this.mask[r][c] = 0;
                                }
                                else if (cellState.nEliminated[r][c] == super.grid.cellsInRow - 1) {
                                    this.mask[r][c] = 1;
                                }
                                else {
                                    this.mask[r][c] = 2;
                                }
                            }
                        }
                        boolean anyMoveEliminated = false;
                    Label_1064:
                        while (candidateNominated && !anyMoveEliminated) {
                            candidateNominated = false;
                            this.mask[x0][y0] = 1;
                            for (int c = 0; c < super.grid.cellsInRow; ++c) {
                                if (this.mask[x0][c] == 2) {
                                    this.mask[x0][c] = 0;
                                }
                            }
                            for (int r = 0; r < super.grid.cellsInRow; ++r) {
                                if (this.mask[r][y0] == 2) {
                                    this.mask[r][y0] = 0;
                                }
                            }
                            int xLower = x0 / super.grid.boxesAcross * super.grid.boxesAcross;
                            int xUpper = (x0 / super.grid.boxesAcross + 1) * super.grid.boxesAcross;
                            int yLower = y0 / super.grid.boxesDown * super.grid.boxesDown;
                            int yUpper = (y0 / super.grid.boxesDown + 1) * super.grid.boxesDown;
                            for (int r = xLower; r < xUpper; ++r) {
                                for (int c = yLower; c < yUpper; ++c) {
                                    if (this.mask[r][c] == 2) {
                                        this.mask[r][c] = 0;
                                    }
                                }
                            }
                            int r = 0;
                        Label_0621:
                            while (r < super.grid.cellsInRow) {
                                super.nCandidates = 0;
                                for (int c = 0; c < super.grid.cellsInRow; ++c) {
                                    if (this.mask[r][c] == 1) {
                                        ++r;
                                        continue Label_0621;
                                    }
                                    if (this.mask[r][c] == 2 && ++super.nCandidates > 1) {
                                        ++r;
                                        continue Label_0621;
                                    }
                                }
                                if (super.nCandidates == 0) {
                                    anyMoveEliminated = true;
                                    continue Label_1064;
                                }
                                if (!candidateNominated) {
                                    int c;
                                    for (c = 0; this.mask[r][c] != 2; ++c) {}
                                    x0 = r;
                                    y0 = c;
                                    candidateNominated = true;
                                }
                                ++r;
                            }
                            int c = 0;
                            Label_0772:
                            while (c < super.grid.cellsInRow) {
                                super.nCandidates = 0;
                                for (r = 0; r < super.grid.cellsInRow; ++r) {
                                    if (this.mask[r][c] == 1) {
                                        ++c;
                                        continue Label_0772;
                                    }
                                    if (this.mask[r][c] == 2 && ++super.nCandidates > 1) {
                                        ++c;
                                        continue Label_0772;
                                    }
                                }
                                if (super.nCandidates == 0) {
                                    anyMoveEliminated = true;
                                    continue Label_1064;
                                }
                                if (!candidateNominated) {
                                    for (r = 0; this.mask[r][c] != 2; ++r) {}
                                    x0 = r;
                                    y0 = c;
                                    candidateNominated = true;
                                }
                                ++c;
                            }
                            int box = 0;
                            Label_1052:
                            while (box < super.grid.cellsInRow) {
                                xLower = box / super.grid.boxesAcross * super.grid.boxesAcross;
                                xUpper = (box / super.grid.boxesAcross + 1) * super.grid.boxesAcross;
                                yLower = box % super.grid.boxesAcross * super.grid.boxesDown;
                                yUpper = (box % super.grid.boxesAcross + 1) * super.grid.boxesDown;
                                super.nCandidates = 0;
                                for (r = xLower; r < xUpper; ++r) {
                                    for (int c = yLower; c < yUpper; ++c) {
                                        if (this.mask[r][c] == 1) {
                                            ++box;
                                            continue Label_1052;
                                        }
                                        if (this.mask[r][c] == 2 && ++super.nCandidates > 1) {
                                            ++box;
                                            continue Label_1052;
                                        }
                                    }
                                }
                                if (super.nCandidates == 0) {
                                    anyMoveEliminated = true;
                                    break;
                                }
                                if (!candidateNominated) {
                                    r = xLower;
                                    Label_1040:
                                    while (r < xUpper) {
                                        for (int c = yLower; c < yUpper; ++c) {
                                            if (this.mask[r][c] == 2) {
                                                x0 = r;
                                                y0 = c;
                                                r = xUpper;
                                                continue Label_1040;
                                            }
                                        }
                                        ++r;
                                    }
                                    candidateNominated = true;
                                }
                                ++box;
                            }
                        }
                        if (anyMoveEliminated) {
                            cellState.eliminateMove(i, j, v);
                            numberState.eliminateMove(i, j, v);
                            super.state.eliminateMove(i, j, v);
                            ++this.nishioEliminations;
                            if (super.explain) {
                                sb.append("The move (");
                                sb.append(i + 1);
                                sb.append(",");
                                sb.append(j + 1);
                                sb.append("):= ");
                                sb.append(SuDokuUtils.toString(v + 1));
                                sb.append(" would make it impossible to place the remaining ");
                                sb.append(SuDokuUtils.toString(v + 1));
                                sb.append("s.\n");
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean updateState(final int x, final int y, final int value, final String reason, final boolean writeState) throws Exception {
        if (super.nMoves == -1) {
            return false;
        }
        if (super.state instanceof IState) {
            if (writeState) {
                super.state.pushState(super.nMoves);
                super.stateWrite[super.nMoves] = true;
            }
            else {
                super.stateWrite[super.nMoves] = false;
            }
        }
        super.xMoves[super.nMoves] = x;
        super.yMoves[super.nMoves] = y;
        super.values[super.nMoves] = value - 1;
        if (super.explain) {
            super.reasons[super.nMoves].append(reason);
        }
        ++super.nMoves;
        if (super.state instanceof IState) {
            super.state.addMove(x, y, value - 1);
        }
        this.lcn.updateState(x, y, value, reason, writeState);
        this.lcc.updateState(x, y, value, reason, writeState);
        return true;
    }
    
    public boolean unwind(final int newNMoves, final boolean reset) {
        if (newNMoves < 0) {
            return false;
        }
        if (super.explain && reset) {
            super.reasons[newNMoves].append("The move (");
            super.reasons[newNMoves].append(1 + super.xMoves[newNMoves]);
            super.reasons[newNMoves].append(",");
            super.reasons[newNMoves].append(1 + super.yMoves[newNMoves]);
            super.reasons[newNMoves].append("):=");
            super.reasons[newNMoves].append(super.grid.data[super.xMoves[newNMoves]][super.yMoves[newNMoves]]);
            super.reasons[newNMoves].append(" would lead to a contradiction.\n");
            for (int i = newNMoves + 1; i < super.nMoves; super.reasons[i++] = new StringBuffer()) {}
        }
        if (super.state instanceof IState) {
            super.state.popState(newNMoves);
            super.state.eliminateMove(super.xMoves[newNMoves], super.yMoves[newNMoves], super.grid.data[super.xMoves[newNMoves]][super.yMoves[newNMoves]] - 1);
        }
        this.lcn.unwind(newNMoves, false);
        this.lcc.unwind(newNMoves, false);
        if (reset) {
            for (int i = newNMoves; i < super.nMoves; ++i) {
                super.grid.data[super.xMoves[i]][super.yMoves[i]] = 0;
            }
        }
        super.nMoves = newNMoves;
        return true;
    }
    
    public int getLastWrittenMove() {
        final int lcnMove = this.lcn.getLastWrittenMove();
        final int lccMove = this.lcc.getLastWrittenMove();
        if (lcnMove < lccMove) {
            return lcnMove;
        }
        return lccMove;
    }
}
