// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

import java.util.Random;
import java.io.PrintStream;
import java.util.StringTokenizer;
import java.io.Serializable;

public class Grid implements Cloneable, Serializable
{
    public static final int PLAIN_TEXT = 0;
    public static final int LIBRARY_BOOK = 1;
    public static final String[] puzzleTypes;
    public static int defaultPuzzleType;
    public static final String[] featuredGrades;
    int cellsInRow;
    int boxesAcross;
    int boxesDown;
    int[][] data;
    transient int nUnwinds;
    transient int complexity;
    transient Solver solver;
    
    static {
        puzzleTypes = new String[] { "Plain Text", "Library Book" };
        Grid.defaultPuzzleType = 0;
        featuredGrades = new String[] { "Ungraded" };
    }
    
    public Grid(final int boxesAcross, final int boxesDown) {
        this.resize(boxesAcross, boxesDown);
    }
    
    public Grid() {
    }
    
    public Object clone() {
        final Grid copy = new Grid(this.boxesAcross, this.boxesDown);
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                copy.data[i][j] = this.data[i][j];
            }
        }
        return copy;
    }
    
    public void resize(final int boxesAcross, final int boxesDown) {
        this.boxesAcross = boxesAcross;
        this.boxesDown = boxesDown;
        this.cellsInRow = boxesAcross * boxesDown;
        this.data = new int[this.cellsInRow][this.cellsInRow];
        this.nUnwinds = 0;
    }
    
    public Grid populate(final String s) {
        final StringTokenizer st = new StringTokenizer(s, " ");
        int boxesDown = 0;
        int boxesAcross = 1;
        String token;
        while (!(token = st.nextToken()).equals("\n")) {
            if (token.equals("*") || token.equals("|") || token.equals("¦")) {
                ++boxesAcross;
            }
            else {
                if (boxesAcross != 1) {
                    continue;
                }
                ++boxesDown;
            }
        }
        this.resize(boxesAcross, boxesDown);
        SuDokuUtils.populate(this.data, s);
        return this;
    }
    
    public int countFilledCells() {
        int count = 0;
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                if (this.data[i][j] > 0) {
                    ++count;
                }
            }
        }
        return count;
    }
    
    public int solve(final IStrategy strategy, final int maxSolns) {
        return this.solve(strategy, null, maxSolns);
    }
    
    synchronized int solve(final IStrategy strategy, final IStrategy composeSolver, final int maxSolns) {
        (this.solver = new Solver(this, strategy, composeSolver, 0, maxSolns, null)).start();
        int nSolns;
        try {
            this.solver.join();
            this.nUnwinds = this.solver.getNumberOfUnwinds();
            nSolns = this.solver.getNumberOfSolutions();
            this.complexity = this.solver.getComplexity();
        }
        catch (InterruptedException e) {
            final boolean b = false;
            this.complexity = (b ? 1 : 0);
            this.nUnwinds = (b ? 1 : 0);
            nSolns = (b ? 1 : 0);
        }
        return nSolns;
    }
    
    public void reset() {
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                this.data[i][j] = 0;
            }
        }
        this.nUnwinds = 0;
    }
    
    public String toString() {
        return (Grid.defaultPuzzleType == 0) ? SuDokuUtils.toString(this.data, this.boxesAcross) : this.toXML(1, Grid.featuredGrades[0]);
    }
    
    public boolean equals(final Object obj) {
        if (!(obj instanceof Grid)) {
            return false;
        }
        final Grid grid = (Grid)obj;
        if (this.boxesAcross != grid.boxesAcross || this.boxesDown != grid.boxesDown) {
            return false;
        }
        for (int r = 0; r < this.cellsInRow; ++r) {
            for (int c = 0; c < this.cellsInRow; ++c) {
                if (this.data[r][c] != grid.data[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    boolean precedes(final Grid grid) {
        if (this.boxesAcross != grid.boxesAcross || this.boxesDown != grid.boxesDown) {
            return false;
        }
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                if (grid.data[i][j] < this.data[i][j]) {
                    return false;
                }
                if (grid.data[i][j] > this.data[i][j]) {
                    return true;
                }
            }
        }
        return true;
    }
    
    Grid halfRotate() {
        int i;
        for (i = 0; i < this.cellsInRow / 2; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                final int tmp = this.data[this.cellsInRow - 1 - i][this.cellsInRow - 1 - j];
                this.data[this.cellsInRow - 1 - i][this.cellsInRow - 1 - j] = this.data[i][j];
                this.data[i][j] = tmp;
            }
        }
        if (this.cellsInRow % 2 == 1) {
            for (int j = 0; j < this.cellsInRow / 2; ++j) {
                final int tmp = this.data[this.cellsInRow - 1 - i][this.cellsInRow - 1 - j];
                this.data[this.cellsInRow - 1 - i][this.cellsInRow - 1 - j] = this.data[i][j];
                this.data[i][j] = tmp;
            }
        }
        return this;
    }
    
    Grid quarterRotate() {
        if (this.boxesAcross != this.boxesDown) {
            return this;
        }
        final int jMax = this.cellsInRow / 2 + ((this.cellsInRow % 2 == 1) ? 1 : 0);
        for (int i = 0; i < this.cellsInRow / 2; ++i) {
            for (int j = 0; j < jMax; ++j) {
                final int tmp = this.data[i][j];
                this.data[i][j] = this.data[j][this.cellsInRow - 1 - i];
                this.data[j][this.cellsInRow - 1 - i] = this.data[this.cellsInRow - 1 - i][this.cellsInRow - 1 - j];
                this.data[this.cellsInRow - 1 - i][this.cellsInRow - 1 - j] = this.data[this.cellsInRow - 1 - j][i];
                this.data[this.cellsInRow - 1 - j][i] = tmp;
            }
        }
        return this;
    }
    
    Grid reflectLeftRight() {
        int i;
        for (i = 0; i < this.cellsInRow / 2; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                final int tmp = this.data[this.cellsInRow - 1 - i][j];
                this.data[this.cellsInRow - 1 - i][j] = this.data[i][j];
                this.data[i][j] = tmp;
            }
        }
        if (this.cellsInRow % 2 == 1) {
            for (int j = 0; j < this.cellsInRow / 2; ++j) {
                final int tmp = this.data[this.cellsInRow - 1 - i][j];
                this.data[this.cellsInRow - 1 - i][j] = this.data[i][j];
                this.data[i][j] = tmp;
            }
        }
        return this;
    }
    
    Grid reflectTopBottom() {
        int j;
        for (j = 0; j < this.cellsInRow / 2; ++j) {
            for (int i = 0; i < this.cellsInRow; ++i) {
                final int tmp = this.data[i][this.cellsInRow - 1 - j];
                this.data[i][this.cellsInRow - 1 - j] = this.data[i][j];
                this.data[i][j] = tmp;
            }
        }
        if (this.cellsInRow % 2 == 1) {
            for (int i = 0; i < this.cellsInRow / 2; ++i) {
                final int tmp = this.data[i][this.cellsInRow - 1 - j];
                this.data[i][this.cellsInRow - 1 - j] = this.data[i][j];
                this.data[i][j] = tmp;
            }
        }
        return this;
    }
    
    Grid reflectTopLeftBottomRight() {
        if (this.boxesAcross != this.boxesDown) {
            return this;
        }
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = i + 1; j < this.cellsInRow; ++j) {
                final int tmp = this.data[j][i];
                this.data[j][i] = this.data[i][j];
                this.data[i][j] = tmp;
            }
        }
        return this;
    }
    
    Grid reflectTopRightBottomLeft() {
        if (this.boxesAcross != this.boxesDown) {
            return this;
        }
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow - 1 - i; ++j) {
                final int tmp = this.data[this.cellsInRow - 1 - j][this.cellsInRow - 1 - i];
                this.data[this.cellsInRow - 1 - j][this.cellsInRow - 1 - i] = this.data[i][j];
                this.data[i][j] = tmp;
            }
        }
        return this;
    }
    
    public Grid rectify(final boolean[][] mask) {
        this.rearrangeData();
        final Grid grid = (Grid)this.clone();
        grid.halfRotate().rearrangeData();
        boolean precedesGrid = this.precedes(grid);
        for (int i = 0; i < grid.cellsInRow; ++i) {
            for (int j = 0; j < grid.cellsInRow; ++j) {
                if (precedesGrid) {
                    grid.data[i][j] = this.data[i][j];
                }
                else {
                    this.data[i][j] = grid.data[i][j];
                }
            }
        }
        if (MaskFactory.isSymmetricLeftRight(mask)) {
            grid.reflectLeftRight().rearrangeData();
            precedesGrid = this.precedes(grid);
            for (int i = 0; i < grid.cellsInRow; ++i) {
                for (int j = 0; j < grid.cellsInRow; ++j) {
                    if (precedesGrid) {
                        grid.data[i][j] = this.data[i][j];
                    }
                    else {
                        this.data[i][j] = grid.data[i][j];
                    }
                }
            }
        }
        if (MaskFactory.isSymmetricTopBottom(mask)) {
            grid.reflectTopBottom().rearrangeData();
            precedesGrid = this.precedes(grid);
            for (int i = 0; i < grid.cellsInRow; ++i) {
                for (int j = 0; j < grid.cellsInRow; ++j) {
                    if (precedesGrid) {
                        grid.data[i][j] = this.data[i][j];
                    }
                    else {
                        this.data[i][j] = grid.data[i][j];
                    }
                }
            }
        }
        if (MaskFactory.isSymmetricTopLeftBottomRight(mask)) {
            grid.reflectTopLeftBottomRight().rearrangeData();
            precedesGrid = this.precedes(grid);
            for (int i = 0; i < grid.cellsInRow; ++i) {
                for (int j = 0; j < grid.cellsInRow; ++j) {
                    if (precedesGrid) {
                        grid.data[i][j] = this.data[i][j];
                    }
                    else {
                        this.data[i][j] = grid.data[i][j];
                    }
                }
            }
        }
        if (MaskFactory.isSymmetricTopRightBottomLeft(mask)) {
            grid.reflectTopRightBottomLeft().rearrangeData();
            precedesGrid = this.precedes(grid);
            for (int i = 0; i < grid.cellsInRow; ++i) {
                for (int j = 0; j < grid.cellsInRow; ++j) {
                    if (precedesGrid) {
                        grid.data[i][j] = this.data[i][j];
                    }
                    else {
                        this.data[i][j] = grid.data[i][j];
                    }
                }
            }
        }
        if (MaskFactory.isSymmetricOrder4(mask)) {
            grid.quarterRotate().rearrangeData();
            precedesGrid = this.precedes(grid);
            for (int i = 0; i < grid.cellsInRow; ++i) {
                for (int j = 0; j < grid.cellsInRow; ++j) {
                    if (precedesGrid) {
                        grid.data[i][j] = this.data[i][j];
                    }
                    else {
                        this.data[i][j] = grid.data[i][j];
                    }
                }
            }
            grid.halfRotate().rearrangeData();
            precedesGrid = this.precedes(grid);
            for (int i = 0; i < grid.cellsInRow; ++i) {
                for (int j = 0; j < grid.cellsInRow; ++j) {
                    if (precedesGrid) {
                        grid.data[i][j] = this.data[i][j];
                    }
                    else {
                        this.data[i][j] = grid.data[i][j];
                    }
                }
            }
        }
        return this;
    }
    
    Grid rearrangeData() {
        int subSize = 0;
        final int[] substitute = new int[this.cellsInRow];
        for (int r = 0; r < this.cellsInRow; ++r) {
            for (int c = 0; c < this.cellsInRow; ++c) {
                if (this.data[r][c] > 0) {
                    int i;
                    for (i = 0; i < subSize && this.data[r][c] != substitute[i]; ++i) {}
                    if (i == subSize) {
                        substitute[subSize++] = this.data[r][c];
                    }
                }
            }
        }
        for (int r = 0; r < this.cellsInRow; ++r) {
            for (int c = 0; c < this.cellsInRow; ++c) {
                if (this.data[r][c] > 0) {
                    for (int i = 0; i < subSize; ++i) {
                        if (this.data[r][c] == substitute[i]) {
                            this.data[r][c] = i + 1;
                            break;
                        }
                    }
                }
            }
        }
        return this;
    }
    
    public Grid shuffle() {
        final Random generator = new Random();
        int size;
        int[] substitute;
        int i;
        int pick;
        for (size = this.cellsInRow, substitute = new int[this.cellsInRow]; size > 0; substitute[i] = size--) {
            i = -1;
            pick = Math.abs(generator.nextInt() % size);
            while (pick-- >= 0) {
                while (substitute[++i] > 0) {}
            }
        }
        for (i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                if (this.data[i][j] > 0) {
                    this.data[i][j] = substitute[this.data[i][j] - 1];
                }
            }
        }
        pick = Math.abs(generator.nextInt() % 4);
        switch (pick) {
            case 1: {
                this.quarterRotate();
                break;
            }
            case 2: {
                this.halfRotate();
                break;
            }
            case 3: {
                this.halfRotate().quarterRotate();
                break;
            }
        }
        pick = Math.abs(generator.nextInt() % 16);
        if ((pick & 0x1) == 0x1) {
            this.reflectLeftRight();
        }
        if ((pick & 0x2) == 0x2) {
            this.reflectTopBottom();
        }
        if ((pick & 0x4) == 0x4) {
            this.reflectTopLeftBottomRight();
        }
        if ((pick & 0x8) == 0x8) {
            this.reflectTopRightBottomLeft();
        }
        return this;
    }
    
    public String toXML(final int serial, final String grade) {
        final StringBuffer sb = new StringBuffer();
        sb.append("<puzzle>\n");
        sb.append("<serial>" + serial + "</serial>\n");
        sb.append("<grade>" + grade + "</grade>\n");
        sb.append("<solvers>0000</solvers>\n");
        sb.append("<question>\n");
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                if (this.data[i][j] > 0) {
                    if (this.cellsInRow >= 10 && this.data[i][j] < 10) {
                        sb.append('0');
                    }
                    sb.append(this.data[i][j]);
                }
                else {
                    sb.append('.');
                }
            }
            sb.append('\n');
        }
        sb.append("</question>\n");
        sb.append("</puzzle>\n");
        return sb.toString();
    }
}
