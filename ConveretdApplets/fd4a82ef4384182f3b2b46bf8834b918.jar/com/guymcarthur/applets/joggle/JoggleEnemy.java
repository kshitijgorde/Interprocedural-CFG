// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.applets.joggle;

import com.guymcarthur.util.CachingWordList;
import com.guymcarthur.Debuggable;

public class JoggleEnemy extends JoggleGame implements Runnable, Debuggable
{
    public static final int SKILL_NOVICE = 0;
    public static final int SKILL_INTERMEDIATE = 9;
    public static final int SKILL_ADVANCED = 19;
    public static final int SKILL_MASTER = 99;
    protected boolean debug;
    protected boolean active;
    protected int skill;
    
    public void setSkill(final int skill) {
        this.skill = skill;
    }
    
    public String getSkill() {
        switch (this.skill) {
            case 0: {
                return "Novice";
            }
            case 9: {
                return "Intermediate";
            }
            case 19: {
                return "Advanced";
            }
            case 99: {
                return "Master";
            }
            default: {
                return null;
            }
        }
    }
    
    public void setActive(final boolean active) {
        this.active = active;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public boolean isDebug() {
        return this.debug;
    }
    
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
    
    public void run() {
        if (this.debug) {
            System.out.println("Computer has begun play.");
        }
        for (int i = 0; i < super.board.rows; ++i) {
            for (int j = 0; j < super.board.cols; ++j) {
                if (this.active) {
                    this.recurse(new boolean[super.board.rows][super.board.cols], true, "", i, j);
                }
            }
        }
        this.active = false;
        if (this.debug) {
            System.out.println("Computer has finished play.");
        }
    }
    
    public boolean recurse(final boolean[][] array, final boolean b, String s, final int n, final int n2) {
        if (!this.active) {
            return false;
        }
        try {
            Thread.currentThread();
            Thread.sleep(10L);
        }
        catch (InterruptedException ex) {
            if (this.debug) {
                System.out.println("Computer was interrupted.");
            }
        }
        if (this.debug) {
            System.out.print("(" + n + "," + n2 + ") ");
        }
        if (array[n][n2]) {
            if (this.debug) {
                System.out.println("Cell has already been selected!");
            }
            return false;
        }
        final String string = s + super.board.getValue(n, n2);
        if (this.debug) {
            System.out.println("Word is now " + string + ".");
        }
        if (this.skill < 9 && string.length() > 4) {
            return false;
        }
        if (this.skill < 19 && string.length() > 5) {
            return false;
        }
        if (string.length() > 1) {
            switch (super.list.has(string)) {
                case 0: {
                    if (this.debug) {
                        System.out.println("Nothing starts with " + string + ".");
                    }
                    return false;
                }
                case 1: {
                    if (string.length() < 3) {
                        break;
                    }
                    if (this.debug) {
                        System.out.println("Scoring " + string + ".");
                    }
                    if (!super.added.containsKey(string)) {
                        this.score(string, false);
                    }
                    if (this.skill < 99) {
                        s = "";
                        return false;
                    }
                    break;
                }
            }
        }
        array[n][n2] = true;
        if (n == 0 && n2 == 0) {
            this.recurse(array, b, string, n + 1, n2);
            this.recurse(array, b, string, n, n2 + 1);
            this.recurse(array, b, string, n + 1, n2 + 1);
        }
        else if (n == 0 && n2 == super.board.cols - 1) {
            this.recurse(array, b, string, n, n2 - 1);
            this.recurse(array, b, string, n + 1, n2 - 1);
            this.recurse(array, b, string, n + 1, n2);
        }
        else if (n == super.board.rows - 1 && n2 == 0) {
            this.recurse(array, b, string, n - 1, n2);
            this.recurse(array, b, string, n - 1, n2 + 1);
            this.recurse(array, b, string, n, n2 + 1);
        }
        else if (n == super.board.rows - 1 && n2 == super.board.cols - 1) {
            this.recurse(array, b, string, n, n2 - 1);
            this.recurse(array, b, string, n - 1, n2 - 1);
            this.recurse(array, b, string, n - 1, n2);
        }
        else if (n == 0) {
            this.recurse(array, b, string, n, n2 - 1);
            this.recurse(array, b, string, n, n2 + 1);
            this.recurse(array, b, string, n + 1, n2 - 1);
            this.recurse(array, b, string, n + 1, n2);
            this.recurse(array, b, string, n + 1, n2 + 1);
        }
        else if (n == super.board.rows - 1) {
            this.recurse(array, b, string, n, n2 - 1);
            this.recurse(array, b, string, n, n2 + 1);
            this.recurse(array, b, string, n - 1, n2 - 1);
            this.recurse(array, b, string, n - 1, n2);
            this.recurse(array, b, string, n - 1, n2 + 1);
        }
        else if (n2 == 0) {
            this.recurse(array, b, string, n - 1, n2);
            this.recurse(array, b, string, n + 1, n2);
            this.recurse(array, b, string, n - 1, n2 + 1);
            this.recurse(array, b, string, n, n2 + 1);
            this.recurse(array, b, string, n + 1, n2 + 1);
        }
        else if (n2 == super.board.cols - 1) {
            this.recurse(array, b, string, n - 1, n2);
            this.recurse(array, b, string, n + 1, n2);
            this.recurse(array, b, string, n - 1, n2 - 1);
            this.recurse(array, b, string, n, n2 - 1);
            this.recurse(array, b, string, n + 1, n2 - 1);
        }
        else {
            this.recurse(array, b, string, n - 1, n2 - 1);
            this.recurse(array, b, string, n, n2 - 1);
            this.recurse(array, b, string, n + 1, n2 - 1);
            this.recurse(array, b, string, n + 1, n2);
            this.recurse(array, b, string, n + 1, n2 + 1);
            this.recurse(array, b, string, n, n2 + 1);
            this.recurse(array, b, string, n - 1, n2 + 1);
            this.recurse(array, b, string, n - 1, n2);
        }
        return array[n][n2] = false;
    }
    
    public JoggleEnemy(final JoggleBoard joggleBoard, final CachingWordList list) {
        super(joggleBoard, list);
        this.skill = 100;
    }
}
