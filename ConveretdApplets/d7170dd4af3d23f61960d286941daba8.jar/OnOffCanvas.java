import java.awt.Dimension;
import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class OnOffCanvas extends Canvas
{
    boolean state;
    Graphics g;
    boolean active;
    int myFrameNumber;
    AniS parent;
    Color color;
    
    public OnOffCanvas(final AniS parent, final int myFrameNumber) {
        this.state = true;
        this.g = null;
        this.parent = parent;
        this.active = true;
        this.myFrameNumber = myFrameNumber;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.modifiers == 4 || event.modifiers == 8) {
            this.parent.setCurrentFrame(true, this.myFrameNumber);
            return true;
        }
        this.setState(this.state);
        return true;
    }
    
    void setColor(final Color color) {
        this.setBackground(this.color = color);
        this.paint(this.getGraphics());
    }
    
    void setState(final boolean state) {
        this.state = state;
        if (this.state) {
            this.state = false;
            this.color = Color.red;
        }
        else {
            this.state = true;
            this.color = Color.green;
        }
        this.paint(this.getGraphics());
    }
    
    boolean getState() {
        return this.state;
    }
    
    void setActive(final boolean active) {
        this.active = active;
    }
    
    boolean getActive() {
        return this.active;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size = this.getSize();
        if (this.active) {
            this.setBackground(this.color);
            graphics.setColor(this.color);
            graphics.fillRect(0, 0, size.width, size.height);
        }
        else {
            this.setBackground(Color.white);
            graphics.setColor(Color.gray);
            graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        }
    }
}
