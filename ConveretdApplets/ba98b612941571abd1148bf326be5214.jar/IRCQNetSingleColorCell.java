import java.awt.Graphics;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetSingleColorCell extends Panel
{
    private Color displayColor;
    private int mHeight;
    private int mWidth;
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        graphics.setColor(this.displayColor);
        graphics.fillRect(0, 0, this.mWidth, this.mHeight);
    }
    
    public void reshape(final int n, final int n2, final int mWidth, final int mHeight) {
        super.reshape(n, n2, mWidth, mHeight);
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }
    
    public Color getColor() {
        return this.displayColor;
    }
    
    public void setColor(final Color displayColor) {
        this.displayColor = displayColor;
        this.repaint();
    }
    
    IRCQNetSingleColorCell() {
        this.displayColor = Color.black;
    }
}
