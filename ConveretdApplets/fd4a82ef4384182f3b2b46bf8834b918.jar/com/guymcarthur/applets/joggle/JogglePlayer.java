// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.applets.joggle;

import com.guymcarthur.util.CachingWordList;
import java.util.Vector;
import com.guymcarthur.Debuggable;

public class JogglePlayer extends JoggleGame implements Debuggable
{
    protected boolean[][] state;
    protected StringBuffer word;
    protected Vector selected;
    private boolean debug;
    
    public boolean isDebug() {
        return this.debug;
    }
    
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
    
    public void init() {
        super.init();
        this.reset();
    }
    
    public void reset() {
        this.state = new boolean[super.board.rows][super.board.cols];
        this.word = new StringBuffer();
        this.selected = new Vector();
    }
    
    public boolean select(final int n, final int n2) {
        if (this.state[n][n2]) {
            if (this.debug) {
                System.out.println(this.word.toString() + " has been terminated.");
            }
            if (this.word.length() > 2) {
                if (this.debug) {
                    System.out.println("Scoring word.");
                }
                this.score(this.word.toString());
            }
            if (this.debug) {
                System.out.println("Resetting.");
            }
            this.reset();
        }
        else {
            if (this.selected.size() > 0) {
                if (this.debug) {
                    System.out.println("Continuing word " + this.word.toString());
                }
                final int[] array = this.selected.elementAt(this.selected.size() - 1);
                final int n3 = (array[0] - n) * (array[0] - n);
                final int n4 = (array[1] - n2) * (array[1] - n2);
                if (this.debug) {
                    System.out.println("dx = " + n3 + ", dy = " + n4 + ".");
                }
                if (n3 + n4 > 2) {
                    if (this.debug) {
                        System.out.println("Starting a new word.");
                    }
                    this.reset();
                }
            }
            this.state[n][n2] = true;
            this.selected.addElement(new int[] { n, n2 });
            this.word.append(super.board.getValue(n, n2));
            if (this.debug) {
                System.out.println("Word is now " + this.word.toString());
            }
        }
        return this.state[n][n2];
    }
    
    public JogglePlayer(final JoggleBoard joggleBoard, final CachingWordList list) {
        super(joggleBoard, list);
        this.debug = false;
    }
}
