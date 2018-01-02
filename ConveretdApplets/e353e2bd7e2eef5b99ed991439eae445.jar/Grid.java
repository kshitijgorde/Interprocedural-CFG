import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class Grid
{
    int \u00df;
    StringBuffer \u00e0;
    public Cell[][] cell;
    Grid \u00e1;
    Grid \u00e2;
    int maxValue;
    
    Grid(final int n, final int n2, final int maxValue) {
        this.maxValue = maxValue;
        this.\u00e0 = new StringBuffer();
        this.\u00df = 0;
        this.cell = new Cell[n][n2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n2; ++j) {
                this.cell[i][j] = new Cell(maxValue);
            }
        }
    }
    
    public void setMaskAndSolution(final Grid \u00e1, final Grid \u00e2) {
        this.\u00e1 = \u00e1;
        this.\u00e2 = \u00e2;
    }
    
    public int rows() {
        return this.cell.length;
    }
    
    public int columns() {
        return this.cell[0].length;
    }
    
    public void copy(final Grid grid) {
        for (int i = 0; i < this.rows(); ++i) {
            for (int j = 0; j < this.columns(); ++j) {
                this.cell[i][j].set(grid.cell[i][j].get());
            }
        }
    }
    
    public boolean equals(final Grid grid) {
        for (int i = 0; i < this.rows(); ++i) {
            for (int j = 0; j < this.columns(); ++j) {
                if (this.cell[i][j].get() != grid.cell[i][j].get()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean solved() {
        for (int i = 0; i < this.rows(); ++i) {
            for (int j = 0; j < this.columns(); ++j) {
                if (this.cell[i][j].get() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean scan(final boolean b) {
        boolean b2 = false;
        if (this.checkRows(true)) {
            b2 = true;
        }
        if (this.checkColumns(true)) {
            b2 = true;
        }
        if (this.checkBoxes(true)) {
            b2 = true;
        }
        return b2;
    }
    
    public void determinePossible() {
        for (int i = 0; i < this.cell.length; ++i) {
            for (int j = 0; j < this.cell.length; ++j) {
                this.cell[i][j].setAllPossible();
            }
        }
        this.scan(false);
    }
    
    private void \u00df(final String s) {
        ++this.\u00df;
        this.\u00e0.append(" " + s + " ");
    }
    
    public boolean update(final boolean b) {
        boolean b2 = false;
        for (int i = 0; i < this.rows(); ++i) {
            for (int j = 0; j < this.columns(); ++j) {
                if (this.cell[i][j].amountPossible() == 1) {
                    final int possibleValue = this.cell[i][j].getPossibleValue();
                    if (this.isAllowed(i, j, possibleValue, true)) {
                        this.\u00e0(i, j, possibleValue, " [UPDATE]" + possibleValue + " is enige mogelijkheid voor [" + i + "][" + j + "]", b);
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    private void \u00e0(final int n, final int n2, final int n3, final String s, final boolean b) {
        this.cell[n][n2].set(n3);
        this.\u00df("(" + n + "," + n2 + "):" + n3);
        if (this.cell[n][n2].get() != this.\u00e2.cell[n][n2].get()) {
            this.\u00e1("[NOT VALID!!] " + s);
        }
        else {
            this.\u00e1("[VALID] " + s);
        }
        this.\u00e2(n, n3, true);
        this.\u00e3(n2, n3, true);
        this.\u00e4(this.\u00e1.cell[n][n2].get(), n3, true);
    }
    
    private void \u00e1(final String s) {
    }
    
    public boolean checkRows(final boolean b) {
        boolean b2 = false;
        for (int i = 0; i < this.rows(); ++i) {
            for (int j = 0; j < this.columns(); ++j) {
                if (this.cell[i][j].get() != 0 && this.\u00e2(i, this.cell[i][j].get(), b)) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public boolean checkColumns(final boolean b) {
        boolean b2 = false;
        for (int i = 0; i < this.columns(); ++i) {
            for (int j = 0; j < this.rows(); ++j) {
                if (this.cell[j][i].get() != 0 && this.\u00e3(i, this.cell[j][i].get(), b)) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public boolean checkBoxes(final boolean b) {
        boolean b2 = false;
        for (int i = 0; i < this.columns(); ++i) {
            for (int j = 0; j < this.rows(); ++j) {
                final int value = this.cell[i][j].get();
                if (value != 0 && this.\u00e4(this.\u00e1.cell[i][j].get(), value, b)) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    private boolean \u00e2(final int n, final int notPossible, final boolean b) {
        boolean b2 = false;
        for (int i = 0; i < this.columns(); ++i) {
            if (this.cell[n][i].isPossible(notPossible)) {
                this.cell[n][i].setNotPossible(notPossible);
                if (b) {
                    this.\u00e1("[checkRows][CELL [" + n + "][" + i + "] HAS POSSIBLE VALUE [" + notPossible + "] \n Removing [" + notPossible + "] as possibility for [" + n + "][" + i + "], " + this.\u00e5(n, i));
                }
                b2 = true;
            }
        }
        return b2;
    }
    
    private boolean \u00e3(final int n, final int notPossible, final boolean b) {
        boolean b2 = false;
        for (int i = 0; i < this.rows(); ++i) {
            if (this.cell[i][n].isPossible(notPossible)) {
                this.cell[i][n].setNotPossible(notPossible);
                if (b) {
                    this.\u00e1("[checkColumns][CELL [" + i + "][" + n + "] HAS POSSIBLE VALUE [" + notPossible + "] \n Removing [" + notPossible + "] as possibility for [" + i + "][" + n + "], " + this.\u00e5(i, n));
                }
                b2 = true;
            }
        }
        return b2;
    }
    
    private boolean \u00e4(final int n, final int notPossible, final boolean b) {
        boolean b2 = false;
        for (int i = 0; i < this.rows(); ++i) {
            for (int j = 0; j < this.columns(); ++j) {
                if (this.\u00e1.cell[i][j].get() == n && this.cell[i][j].get() == 0 && this.cell[i][j].isPossible(notPossible)) {
                    this.cell[i][j].setNotPossible(notPossible);
                    if (b) {
                        this.\u00e1("[checkBox][CELL [" + i + "][" + j + "] HAS POSSIBLE VALUE [" + notPossible + "] \n Removing [" + notPossible + "] as possibility for [" + i + "][" + j + "], " + this.\u00e5(i, j));
                    }
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public int amountPossibleRow(final int n, final int n2) {
        int n3 = 0;
        for (int i = 0; i < this.columns(); ++i) {
            if (this.cell[i][n].isPossible(n2)) {
                ++n3;
            }
        }
        return n3;
    }
    
    public int amountPossibleColumn(final int n, final int n2) {
        int n3 = 0;
        for (int i = 0; i < this.rows(); ++i) {
            if (this.cell[n][i].isPossible(n2)) {
                ++n3;
            }
        }
        return n3;
    }
    
    public boolean existsIn(final int n, final int n2, final Point[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null && array[i].x == n2 && array[i].y == n) {
                return true;
            }
        }
        return false;
    }
    
    public boolean removePossibleFromRows(final Point[] array, final int notPossible) {
        boolean b = false;
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < this.columns(); ++j) {
                if (array[i] != null && !this.existsIn(array[i].y, j, array) && this.cell[j][array[i].y].isPossible(notPossible)) {
                    this.cell[j][array[i].y].setNotPossible(notPossible);
                    b = true;
                }
            }
        }
        return b;
    }
    
    public boolean removePossibleFromColumns(final Point[] array, final int notPossible) {
        boolean b = false;
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < this.rows(); ++j) {
                if (array[i] != null && !this.existsIn(j, array[i].x, array) && this.cell[array[i].x][j].isPossible(notPossible)) {
                    this.cell[array[i].x][j].setNotPossible(notPossible);
                    b = true;
                }
            }
        }
        return b;
    }
    
    public Point[] getPointsRow(final int y, final int n, final int n2) {
        final Point[] array = new Point[n2];
        int n3 = 0;
        for (int i = 0; i < this.columns(); ++i) {
            if (this.cell[i][y].isPossible(n)) {
                array[n3] = new Point();
                array[n3].x = i;
                array[n3].y = y;
                ++n3;
            }
        }
        return array;
    }
    
    public Point[] getPointsColumn(final int x, final int n, final int n2) {
        final Point[] array = new Point[n2];
        int n3 = 0;
        for (int i = 0; i < this.rows(); ++i) {
            if (this.cell[x][i].isPossible(n)) {
                array[n3] = new Point();
                array[n3].x = x;
                array[n3].y = i;
                ++n3;
            }
        }
        return array;
    }
    
    private String \u00e5(final int n, final int n2) {
        final StringBuffer sb = new StringBuffer();
        sb.append("posibilities left ");
        for (int i = 1; i <= this.maxValue; ++i) {
            if (this.cell[n2][n].isPossible(i)) {
                sb.append("[" + (char)(i + 48) + "]");
            }
        }
        return sb.toString();
    }
    
    public boolean isAllowed(final int n, final int n2, final int n3, final boolean b) {
        if (this.cell[n][n2].get() != 0) {
            return false;
        }
        for (int i = 0; i < this.rows(); ++i) {
            if (this.cell[i][n2].get() == n3) {
                return false;
            }
        }
        for (int j = 0; j < this.columns(); ++j) {
            if (this.cell[n][j].get() == n3) {
                return false;
            }
        }
        return this.checkBox(n, n2, n3);
    }
    
    public boolean checkBox(final int n, final int n2, final int n3) {
        for (int i = 0; i < this.rows(); ++i) {
            for (int j = 0; j < this.columns(); ++j) {
                if (this.\u00e1.cell[i][j].get() == this.\u00e1.cell[n][n2].get() && this.cell[i][j].get() == n3 && i != n && j != n2) {
                    return false;
                }
            }
        }
        return true;
    }
}
