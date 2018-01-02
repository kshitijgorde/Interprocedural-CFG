import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Drawable implements Clickable
{
    protected int x;
    protected int y;
    protected Color color;
    private boolean visible;
    private boolean moving;
    private int numberOfMoves;
    private int move;
    private Move[] moves;
    
    Drawable() {
        this.visible = false;
        this.moving = false;
    }
    
    public abstract void draw(final Graphics p0);
    
    public synchronized boolean drawObject(final Graphics g) {
        if (this.moving && this.visible) {
            if (this.moves[this.move].moreLegs()) {
                this.moves[this.move].nextMove(this);
            }
            else if (++this.move < this.numberOfMoves) {
                this.moves[this.move].nextMove(this);
            }
            else {
                this.moving = false;
            }
            this.draw(g);
        }
        else if (this.moving) {
            this.moving = false;
        }
        else if (this.visible) {
            this.draw(g);
        }
        return this.moving;
    }
    
    public void hide() {
        this.visible = false;
    }
    
    public boolean inside(final Position position) {
        return false;
    }
    
    public synchronized void moveTo(final Position[] moveList) {
        this.numberOfMoves = moveList.length;
        this.moves = new Move[this.numberOfMoves];
        Position from = new Position(this.x, this.y);
        this.move = 0;
        while (this.move < this.numberOfMoves) {
            final Position to = moveList[this.move];
            this.moves[this.move] = new Move(from, to);
            from = to;
            ++this.move;
        }
        this.move = 0;
        this.moving = true;
    }
    
    public synchronized void reposition(final Position position) {
        this.x = position.x();
        this.y = position.y();
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public void show() {
        this.visible = true;
    }
}
