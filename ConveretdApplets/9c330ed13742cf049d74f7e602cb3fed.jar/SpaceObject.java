import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class SpaceObject implements Runnable
{
    public boolean alive;
    public boolean move;
    
    public abstract void paint(final Graphics p0);
    
    public abstract void isAHit(final int p0, final int p1);
    
    SpaceObject() {
        this.alive = true;
        this.move = true;
    }
    
    public abstract void run();
}
