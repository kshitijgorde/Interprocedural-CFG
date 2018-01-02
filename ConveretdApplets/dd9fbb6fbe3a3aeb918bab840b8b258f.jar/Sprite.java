import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Sprite
{
    protected boolean visible;
    protected boolean active;
    
    public void suspend() {
        this.setVisible(false);
        this.setActive(false);
    }
    
    public abstract void paint(final Graphics p0);
    
    public void restore() {
        this.setVisible(true);
        this.setActive(true);
    }
    
    public void setVisible(final boolean b) {
        this.visible = b;
    }
    
    public abstract void update();
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void setActive(final boolean b) {
        this.active = b;
    }
    
    public boolean isActive() {
        return this.active;
    }
}
