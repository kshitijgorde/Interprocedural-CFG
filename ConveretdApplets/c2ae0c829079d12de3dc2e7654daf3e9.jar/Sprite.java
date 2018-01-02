import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Sprite
{
    protected boolean visible;
    protected boolean active;
    
    abstract void paint(final Graphics p0);
    
    abstract void update();
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(final boolean active) {
        this.active = active;
    }
    
    public void suspend() {
        this.setVisible(false);
        this.setActive(false);
    }
    
    public void restore() {
        this.setVisible(true);
        this.setActive(true);
    }
}
