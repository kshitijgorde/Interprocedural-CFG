// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public class InvulnerableState implements IState
{
    int boxesAcross;
    int boxesDown;
    int cellsInRow;
    int maxScore;
    boolean[][][] eliminated;
    int[][][] nInvulnerable;
    boolean[][][][] threadEliminated;
    int[][][][] threadNInvulnerable;
    transient int lowerX;
    transient int upperX;
    transient int lowerY;
    transient int upperY;
    
    public void setup(final int boxesAcross, final int boxesDown) {
        this.boxesAcross = boxesAcross;
        this.boxesDown = boxesDown;
        final boolean resize = this.cellsInRow != boxesAcross * boxesDown;
        this.cellsInRow = boxesAcross * boxesDown;
        this.maxScore = 3 * this.cellsInRow - boxesAcross - boxesDown;
        if (resize) {
            this.eliminated = new boolean[this.cellsInRow][this.cellsInRow][this.cellsInRow];
            this.nInvulnerable = new int[this.cellsInRow][this.cellsInRow][this.cellsInRow];
            this.threadEliminated = new boolean[this.cellsInRow * this.cellsInRow][this.cellsInRow][this.cellsInRow][this.cellsInRow];
            this.threadNInvulnerable = new int[this.cellsInRow * this.cellsInRow][this.cellsInRow][this.cellsInRow][this.cellsInRow];
        }
        else {
            for (int i = 0; i < this.cellsInRow; ++i) {
                for (int j = 0; j < this.cellsInRow; ++j) {
                    for (int k = 0; k < this.cellsInRow; ++k) {
                        this.eliminated[i][j][k] = false;
                        this.nInvulnerable[i][j][k] = 0;
                    }
                }
            }
        }
    }
    
    public void pushState(final int nMoves) {
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                for (int v = 0; v < this.cellsInRow; ++v) {
                    this.threadEliminated[nMoves][v][i][j] = this.eliminated[v][i][j];
                    this.threadNInvulnerable[nMoves][v][i][j] = this.nInvulnerable[v][i][j];
                }
            }
        }
    }
    
    public void popState(final int nMoves) {
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                for (int v = 0; v < this.cellsInRow; ++v) {
                    this.eliminated[v][i][j] = this.threadEliminated[nMoves][v][i][j];
                    this.nInvulnerable[v][i][j] = this.threadNInvulnerable[nMoves][v][i][j];
                }
            }
        }
    }
    
    public void addMove(final int x, final int y, final int value) throws Exception {
        if (this.eliminated[value][x][y]) {
            throw new Exception("The move (" + (1 + x) + "," + (1 + y) + "):=" + (1 + value) + " has already been eliminated");
        }
        this.lowerX = x / this.boxesAcross * this.boxesAcross;
        this.upperX = (x / this.boxesAcross + 1) * this.boxesAcross;
        this.lowerY = y / this.boxesDown * this.boxesDown;
        this.upperY = (y / this.boxesDown + 1) * this.boxesDown;
        for (int v = 0; v < this.cellsInRow; ++v) {
            this.nInvulnerable[v][x][y] = this.maxScore;
        }
        for (int v = 0; v < this.cellsInRow; ++v) {
            if (!this.eliminated[v][x][y]) {
                int i = -1;
                while (++i < this.cellsInRow) {
                    if (i != x) {
                        if (this.eliminated[v][i][y]) {
                            continue;
                        }
                        if (v == value) {
                            this.nInvulnerable[v][i][y] = this.maxScore;
                        }
                        else {
                            final int[] array = this.nInvulnerable[v][i];
                            ++array[y];
                        }
                    }
                }
                int j = -1;
                while (++j < this.cellsInRow) {
                    if (j != y) {
                        if (this.eliminated[v][x][j]) {
                            continue;
                        }
                        if (v == value) {
                            this.nInvulnerable[v][x][j] = this.maxScore;
                        }
                        else {
                            final int[] array2 = this.nInvulnerable[v][x];
                            final int n = j;
                            ++array2[n];
                        }
                    }
                }
                i = this.lowerX - 1;
                while (++i < this.upperX) {
                    if (i == x) {
                        continue;
                    }
                    j = this.lowerY - 1;
                    while (++j < this.upperY) {
                        if (j != y) {
                            if (this.eliminated[v][i][j]) {
                                continue;
                            }
                            if (v == value) {
                                this.nInvulnerable[v][i][j] = this.maxScore;
                            }
                            else {
                                final int[] array3 = this.nInvulnerable[v][i];
                                final int n2 = j;
                                ++array3[n2];
                            }
                        }
                    }
                }
            }
        }
        for (int cx = 0; cx < this.cellsInRow; ++cx) {
            if (cx != x) {
                for (int cy = 0; cy < this.cellsInRow; ++cy) {
                    if (!this.eliminated[value][cx][cy] && cy != y && (this.lowerX > cx || cx >= this.upperX || this.lowerY > cy || cy >= this.upperY)) {
                        final int lowerCX = cx / this.boxesAcross * this.boxesAcross;
                        final int upperCX = (cx / this.boxesAcross + 1) * this.boxesAcross;
                        final int lowerCY = cy / this.boxesDown * this.boxesDown;
                        final int upperCY = (cy / this.boxesDown + 1) * this.boxesDown;
                        for (int i = 0; i < this.cellsInRow; ++i) {
                            if (i == x) {
                                for (int j = 0; j < this.cellsInRow; ++j) {
                                    if (!this.eliminated[value][i][j] && (i == cx || j == cy || (lowerCX <= i && i < upperCX && lowerCY <= j && j < upperCY))) {
                                        final int[] array4 = this.nInvulnerable[value][cx];
                                        final int n3 = cy;
                                        ++array4[n3];
                                    }
                                }
                            }
                            else if (this.lowerX <= i && i < this.upperX) {
                                for (int j = this.lowerY; j < this.upperY; ++j) {
                                    if (!this.eliminated[value][i][j] && (i == cx || j == cy || (lowerCX <= i && i < upperCX && lowerCY <= j && j < upperCY))) {
                                        final int[] array5 = this.nInvulnerable[value][cx];
                                        final int n4 = cy;
                                        ++array5[n4];
                                    }
                                }
                            }
                            else if (!this.eliminated[value][i][y] && (i == cx || y == cy || (lowerCX <= i && i < upperCX && lowerCY <= y && y < upperCY))) {
                                final int[] array6 = this.nInvulnerable[value][cx];
                                final int n5 = cy;
                                ++array6[n5];
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < this.cellsInRow; ++i) {
            if (i != value && !this.eliminated[i][x][y]) {
                this.eliminated[i][x][y] = true;
            }
        }
        for (int j = 0; j < this.cellsInRow; ++j) {
            if (j != y && !this.eliminated[value][x][j]) {
                this.eliminated[value][x][j] = true;
            }
        }
        for (int i = 0; i < this.cellsInRow; ++i) {
            if (i != x && !this.eliminated[value][i][y]) {
                this.eliminated[value][i][y] = true;
            }
        }
        int i = this.lowerX - 1;
        while (++i < this.upperX) {
            if (i == x) {
                continue;
            }
            int j = this.lowerY - 1;
            while (++j < this.upperY) {
                if (j == y) {
                    continue;
                }
                if (this.eliminated[value][i][j]) {
                    continue;
                }
                this.eliminated[value][i][j] = true;
            }
        }
    }
    
    public void eliminateMove(final int x, final int y, final int value) {
        this.lowerX = x / this.boxesAcross * this.boxesAcross;
        this.upperX = (x / this.boxesAcross + 1) * this.boxesAcross;
        this.lowerY = y / this.boxesDown * this.boxesDown;
        this.upperY = (y / this.boxesDown + 1) * this.boxesDown;
        for (int i = 0; i < this.cellsInRow; ++i) {
            final int partial = this.inDomainPartial(x, i);
            for (int j = 0; j < this.cellsInRow; ++j) {
                if (i == x && j == y) {
                    this.eliminated[value][x][y] = true;
                    this.nInvulnerable[value][i][j] = this.maxScore;
                }
                else if (!this.eliminated[value][i][j] && this.inDomain(partial, y, j)) {
                    final int[] array = this.nInvulnerable[value][i];
                    final int n = j;
                    ++array[n];
                }
            }
        }
    }
    
    int inDomainPartial(final int x, final int p) {
        if (x == p) {
            return 2;
        }
        if (p >= this.lowerX && p < this.upperX) {
            return 1;
        }
        return 0;
    }
    
    boolean inDomain(final int partial, final int y, final int q) {
        switch (partial) {
            case 2: {
                return true;
            }
            case 1: {
                return q >= this.lowerY && q < this.upperY;
            }
            case 0: {
                return q == y;
            }
            default: {
                return false;
            }
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int v = 0; v < this.cellsInRow; ++v) {
            sb.append(v + 1);
            sb.append(".\n");
            sb.append(SuDokuUtils.toString(this.nInvulnerable[v], this.boxesAcross, this.maxScore));
        }
        return sb.toString();
    }
}
