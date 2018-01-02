// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

abstract class Player
{
    public static final int CUT = 1;
    public static final int SHORT = 2;
    public final int player;
    protected Graph graph;
    protected GraphCanvas canvas;
    protected boolean myTurn;
    public String name;
    public long time;
    public int moves;
    
    public Player(final String name, final int player, final GraphCanvas canvas) {
        this.time = 0L;
        this.moves = 0;
        this.name = name;
        this.player = player;
        this.canvas = canvas;
        this.graph = this.canvas.graph;
    }
    
    protected void move() {
    }
    
    public synchronized void play() {
        this.myTurn = true;
        this.move();
    }
    
    public synchronized void stop() {
    }
    
    protected synchronized void done() {
        this.myTurn = false;
        ++this.moves;
        this.canvas.nextTurn(this);
    }
}
