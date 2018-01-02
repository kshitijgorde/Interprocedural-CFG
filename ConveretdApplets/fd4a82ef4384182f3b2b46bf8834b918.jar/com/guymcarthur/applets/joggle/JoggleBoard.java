// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.applets.joggle;

import java.util.Random;
import com.guymcarthur.Debuggable;

public class JoggleBoard implements Debuggable
{
    public static final String[] letters;
    protected int rows;
    protected int cols;
    protected boolean playable;
    private JoggleDie[][] dice;
    private Random random;
    private boolean debug;
    
    public boolean isDebug() {
        return this.debug;
    }
    
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
    
    public void shuffle() {
        try {
            for (int i = 0; i < this.rows; ++i) {
                for (int j = 0; j < this.cols; ++j) {
                    final int n = (int)(this.random.nextDouble() * this.rows);
                    final int n2 = (int)(this.random.nextDouble() * this.cols);
                    final JoggleDie joggleDie = (JoggleDie)this.dice[i][j].clone();
                    this.dice[i][j] = this.dice[n][n2];
                    this.dice[n][n2] = joggleDie;
                    this.dice[i][j].roll();
                }
            }
        }
        catch (CloneNotSupportedException ex) {
            if (this.debug) {
                System.out.println(ex.getMessage());
            }
        }
        if (this.debug) {
            System.out.println(this.toString());
        }
    }
    
    public void setPlayable(final boolean playable) {
        this.playable = playable;
    }
    
    public boolean isPlayable() {
        return this.playable;
    }
    
    public boolean recurse(final boolean[][] array, boolean b, final String s, int n, final int n2, final int n3) {
        if (this.debug) {
            System.out.print("" + n + ". (" + n2 + "," + n3 + ")");
        }
        if (!b) {
            b = (n == s.length());
        }
        if (b) {
            if (this.debug) {
                System.out.println("\nFound " + s + "!");
            }
            return b;
        }
        if (array[n2][n3]) {
            if (this.debug) {
                System.out.println(" has already been selected!");
            }
            return false;
        }
        final String value = this.getValue(n2, n3);
        String s2;
        if (s.charAt(n) == 'q') {
            s2 = s.substring(n, 2 + n);
            ++n;
        }
        else {
            s2 = s.substring(n, 1 + n);
        }
        if (this.debug) {
            System.out.println(" = " + value + " ?= " + s2 + ".");
        }
        if (!value.equals(s2)) {
            return false;
        }
        array[n2][n3] = true;
        if (n2 == 0 && n3 == 0) {
            if (this.debug) {
                System.out.println("Upper left.");
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2, n3 + 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3 + 1);
            }
        }
        else if (n2 == 0 && n3 == this.cols - 1) {
            if (this.debug) {
                System.out.println("Bottom left.");
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3);
            }
        }
        else if (n2 == this.rows - 1 && n3 == 0) {
            if (this.debug) {
                System.out.println("Upper right.");
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3 + 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2, n3 + 1);
            }
        }
        else if (n2 == this.rows - 1 && n3 == this.cols - 1) {
            if (this.debug) {
                System.out.println("Bottom right");
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3);
            }
        }
        else if (n2 == 0) {
            if (this.debug) {
                System.out.println("Left column");
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2, n3 + 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3 + 1);
            }
        }
        else if (n2 == this.rows - 1) {
            if (this.debug) {
                System.out.println("Right column");
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2, n3 + 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3 + 1);
            }
        }
        else if (n3 == 0) {
            if (this.debug) {
                System.out.println("Top row");
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3 + 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2, n3 + 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3 + 1);
            }
        }
        else if (n3 == this.cols - 1) {
            if (this.debug) {
                System.out.println("Bottom row");
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3 - 1);
            }
        }
        else {
            if (this.debug) {
                System.out.println("Center");
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3 - 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 + 1, n3 + 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2, n3 + 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3 + 1);
            }
            if (!b) {
                b = this.recurse(array, b, s, 1 + n, n2 - 1, n3);
            }
        }
        array[n2][n3] = false;
        return b;
    }
    
    public boolean has(final String s) {
        if (this.debug) {
            System.out.println("Checking board for " + s);
        }
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                if (this.recurse(new boolean[this.rows][this.cols], false, s, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                sb.append("(" + i + "," + j + ") = ");
                sb.append(this.getValue(i, j));
                sb.append(" :: ");
                sb.append(this.dice[i][j].toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    public String getValue(final int n, final int n2) {
        return (n < this.rows && n2 < this.cols) ? this.dice[n][n2].getValue() : null;
    }
    
    public JoggleBoard(final JoggleDie[][] dice) {
        this.dice = dice;
        this.rows = dice.length;
        this.cols = dice[0].length;
        this.random = new Random();
        this.shuffle();
    }
    
    static {
        letters = new String[26];
        for (char c = 'a'; c <= 'z'; ++c) {
            JoggleBoard.letters[c - 'a'] = ((c == 'q') ? "qu" : ("" + c));
        }
    }
}
