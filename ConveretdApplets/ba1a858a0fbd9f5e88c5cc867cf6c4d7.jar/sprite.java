import java.awt.Rectangle;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.MediaTracker;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class sprite
{
    protected int x_pos;
    protected int y_pos;
    protected MediaTracker tracker;
    protected Applet parentApplet;
    protected Graphics offScrGC;
    protected Rectangle collision;
    
    public abstract void draw();
    
    public int getPosX() {
        return this.x_pos;
    }
    
    public int getPosY() {
        return this.y_pos;
    }
    
    public void move(final int x_pos, final int y_pos) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }
    
    public boolean collisionDetected(final sprite sprite) {
        return new Rectangle(this.collision.x + this.x_pos, this.collision.y + this.y_pos, this.collision.width, this.collision.height).intersects(new Rectangle(sprite.collision.x + sprite.x_pos, sprite.collision.y + sprite.y_pos, sprite.collision.width, sprite.collision.height));
    }
    
    public Rectangle getCollision() {
        return this.collision;
    }
}
