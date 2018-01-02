import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class DrawObj extends Component
{
    protected Color color;
    protected Graphics g;
    
    public DrawObj() {
        this.g = null;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public abstract void destroyEvent();
    
    public abstract void addEvent();
    
    public void paint(final Graphics mapG) {
        this.draw();
    }
    
    public abstract String getSubmitValue();
    
    public abstract void draw();
    
    public abstract void resize(final Point p0);
    
    public void disable_object() {
        this.reset_object();
        this.destroyEvent();
    }
    
    public void enable_object() {
        this.addEvent();
    }
    
    public void reset_object() {
    }
}
