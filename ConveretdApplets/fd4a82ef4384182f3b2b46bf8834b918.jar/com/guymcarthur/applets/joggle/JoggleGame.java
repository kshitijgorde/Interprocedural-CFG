// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.applets.joggle;

import com.guymcarthur.util.CachingWordList;
import java.util.Hashtable;
import java.text.ChoiceFormat;
import java.util.Observable;

public class JoggleGame extends Observable
{
    protected static final ChoiceFormat fmt;
    protected JoggleBoard board;
    protected int score;
    protected int total;
    protected int wordcount;
    protected Hashtable added;
    protected CachingWordList list;
    
    public void zero() {
        this.total = 0;
        this.init();
    }
    
    public void init() {
        this.score = 0;
        this.added = new Hashtable();
    }
    
    public synchronized void total() {
        this.total += this.score;
        this.wordcount += this.added.size();
        this.setChanged();
        this.notifyObservers(new GameMessage("Matching words eliminated."));
    }
    
    public int score(final String s) {
        return this.score(s, true);
    }
    
    public int score(final String s, final boolean b) {
        this.setChanged();
        if (s.length() < 3) {
            this.notifyObservers(new GameMessage("Word must be at least three letters long.", s, 0));
            return 0;
        }
        if (b) {
            if (this.added.containsKey(s)) {
                this.notifyObservers(new GameMessage("\"" + s + "\" already used.", s, 0));
                return 0;
            }
            if (!this.board.has(s)) {
                this.notifyObservers(new GameMessage("\"" + s + "\" not found on the board.", s, 0));
                return 0;
            }
            if (this.list.has(s) != 1) {
                this.notifyObservers(new GameMessage("\"" + s + "\" not found in dictionary.", s, 0));
                return 0;
            }
        }
        final int points = getPoints(s);
        synchronized (this) {
            this.score += points;
        }
        this.added.put(s, new Integer(points));
        this.notifyObservers(new GameMessage("\"" + s + "\" scored " + points + " " + JoggleGame.fmt.format(points), s, points));
        return points;
    }
    
    public static int getPoints(final String s) {
        if (s.length() < 3) {
            return 0;
        }
        switch (s.length()) {
            case 3: {
                return 1;
            }
            case 4: {
                return 1;
            }
            case 5: {
                return 2;
            }
            case 6: {
                return 3;
            }
            case 7: {
                return 5;
            }
            default: {
                return 11;
            }
        }
    }
    
    public JoggleGame(final JoggleBoard board, final CachingWordList list) {
        this.total = 0;
        this.wordcount = 0;
        this.board = board;
        this.list = list;
    }
    
    static {
        fmt = new ChoiceFormat(" 1# point.|1< points.");
    }
    
    public class GameMessage
    {
        public int pts;
        String msg;
        String word;
        
        public GameMessage(final String msg) {
            this.pts = 0;
            this.word = null;
            this.msg = msg;
        }
        
        public GameMessage(final String msg, final String word, final int pts) {
            this.pts = 0;
            this.word = null;
            this.msg = msg;
            this.word = word;
            this.pts = pts;
        }
    }
}
