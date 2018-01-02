// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

public class Position
{
    int S;
    int C;
    Field[][] F;
    
    public Position(final int s) {
        this.S = s;
        this.F = new Field[this.S][this.S];
        for (int i = 0; i < this.S; ++i) {
            for (int j = 0; j < this.S; ++j) {
                this.F[i][j] = new Field();
            }
        }
        this.C = 1;
    }
    
    public Position(final Position position) {
        this.S = position.S;
        this.F = new Field[this.S][this.S];
        for (int i = 0; i < this.S; ++i) {
            for (int j = 0; j < this.S; ++j) {
                this.F[i][j] = new Field();
            }
        }
        for (int k = 0; k < this.S; ++k) {
            for (int l = 0; l < this.S; ++l) {
                this.color(k, l, position.color(k, l));
                this.number(k, l, position.number(k, l));
                this.marker(k, l, position.marker(k, l));
                this.letter(k, l, position.letter(k, l));
                if (position.haslabel(k, l)) {
                    this.setlabel(k, l, position.label(k, l));
                }
            }
        }
        this.color(position.color());
    }
    
    int color(final int n, final int n2) {
        return this.F[n][n2].color();
    }
    
    void color(final int n, final int n2, final int n3) {
        this.F[n][n2].color(n3);
    }
    
    void number(final int n, final int n2, final int n3) {
        this.F[n][n2].number(n3);
    }
    
    int number(final int n, final int n2) {
        return this.F[n][n2].number();
    }
    
    int color() {
        return this.C;
    }
    
    void color(final int c) {
        this.C = c;
    }
    
    void markrek(final int n, final int n2, final int n3) {
        if (this.F[n][n2].mark() || this.F[n][n2].color() != n3) {
            return;
        }
        this.F[n][n2].mark(true);
        if (n > 0) {
            this.markrek(n - 1, n2, n3);
        }
        if (n2 > 0) {
            this.markrek(n, n2 - 1, n3);
        }
        if (n < this.S - 1) {
            this.markrek(n + 1, n2, n3);
        }
        if (n2 < this.S - 1) {
            this.markrek(n, n2 + 1, n3);
        }
    }
    
    public void markgroup(final int n, final int n2) {
        this.unmarkall();
        this.markrek(n, n2, this.F[n][n2].color());
    }
    
    boolean markrektest(final int n, final int n2, final int n3, final int n4) {
        if (this.F[n][n2].mark()) {
            return false;
        }
        if (this.F[n][n2].color() != n3) {
            return this.F[n][n2].color() == n4;
        }
        this.F[n][n2].mark(true);
        return (n > 0 && this.markrektest(n - 1, n2, n3, n4)) || (n2 > 0 && this.markrektest(n, n2 - 1, n3, n4)) || (n < this.S - 1 && this.markrektest(n + 1, n2, n3, n4)) || (n2 < this.S - 1 && this.markrektest(n, n2 + 1, n3, n4));
    }
    
    public boolean markgrouptest(final int n, final int n2, final int n3) {
        this.unmarkall();
        return this.markrektest(n, n2, this.F[n][n2].color(), n3);
    }
    
    public void unmarkall() {
        for (int i = 0; i < this.S; ++i) {
            for (int j = 0; j < this.S; ++j) {
                this.F[i][j].mark(false);
            }
        }
    }
    
    public void getterritory() {
        for (int i = 0; i < this.S; ++i) {
            for (int j = 0; j < this.S; ++j) {
                this.F[i][j].territory(-2);
            }
        }
        for (int k = 0; k < this.S; ++k) {
            for (int l = 0; l < this.S; ++l) {
                if (this.F[k][l].color() == 0 && this.F[k][l].territory() == -2) {
                    if (!this.markgrouptest(k, l, 1)) {
                        for (int n = 0; n < this.S; ++n) {
                            for (int n2 = 0; n2 < this.S; ++n2) {
                                if (this.F[n][n2].mark()) {
                                    this.F[n][n2].territory(-1);
                                }
                            }
                        }
                    }
                    else if (!this.markgrouptest(k, l, -1)) {
                        for (int n3 = 0; n3 < this.S; ++n3) {
                            for (int n4 = 0; n4 < this.S; ++n4) {
                                if (this.F[n3][n4].mark()) {
                                    this.F[n3][n4].territory(1);
                                }
                            }
                        }
                    }
                    else {
                        this.markgroup(k, l);
                        for (int n5 = 0; n5 < this.S; ++n5) {
                            for (int n6 = 0; n6 < this.S; ++n6) {
                                if (this.F[n5][n6].mark()) {
                                    this.F[n5][n6].territory(0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    boolean marked(final int n, final int n2) {
        return this.F[n][n2].mark();
    }
    
    int marker(final int n, final int n2) {
        return this.F[n][n2].marker();
    }
    
    void marker(final int n, final int n2, final int n3) {
        this.F[n][n2].marker(n3);
    }
    
    void letter(final int n, final int n2, final int n3) {
        this.F[n][n2].letter(n3);
    }
    
    int letter(final int n, final int n2) {
        return this.F[n][n2].letter();
    }
    
    int territory(final int n, final int n2) {
        return this.F[n][n2].territory();
    }
    
    boolean haslabel(final int n, final int n2) {
        return this.F[n][n2].havelabel();
    }
    
    String label(final int n, final int n2) {
        return this.F[n][n2].label();
    }
    
    void setlabel(final int n, final int n2, final String s) {
        this.F[n][n2].setlabel(s);
    }
    
    void clearlabel(final int n, final int n2) {
        this.F[n][n2].clearlabel();
    }
    
    TreeNode tree(final int n, final int n2) {
        return this.F[n][n2].tree();
    }
    
    void tree(final int n, final int n2, final TreeNode treeNode) {
        this.F[n][n2].tree(treeNode);
    }
}
