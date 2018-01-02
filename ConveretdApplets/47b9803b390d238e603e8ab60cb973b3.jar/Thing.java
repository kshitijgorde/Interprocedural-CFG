import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Thing extends Thread
{
    MissileCommando parent;
    public int X;
    public int Y;
    Color color;
    
    abstract void paint(final Graphics p0);
    
    abstract void erase(final Graphics p0);
    
    abstract boolean hit(final Missile p0);
    
    abstract void explode();
}
