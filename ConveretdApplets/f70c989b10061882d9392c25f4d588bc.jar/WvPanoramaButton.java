import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvPanoramaButton extends Canvas implements WvEventListener
{
    private static final int WIDTH = 46;
    private static final int HEIGHT = 24;
    private static final Dimension SIZE;
    private static final Rectangle scope1;
    private static final Rectangle scope2;
    private boolean raised;
    private boolean videoConnected;
    private boolean cameraConnected;
    
    static {
        SIZE = new Dimension(46, 24);
        scope1 = new Rectangle(6, 6, 32, 10);
        scope2 = new Rectangle(14, 5, 11, 9);
    }
    
    public void kickOff() {
        this.raised = true;
        this.videoConnected = false;
        this.cameraConnected = false;
        this.disable();
    }
    
    public void cameraConnected(final boolean flag) {
        this.cameraConnected = flag;
        if (this.cameraConnected && this.videoConnected) {
            this.enable();
        }
    }
    
    public void paint(final Graphics g) {
        Color color = this.isEnabled() ? Color.black : Color.gray;
        g.setColor(color);
        if (!this.raised) {
            g.translate(1, 1);
        }
        g.drawRect(WvPanoramaButton.scope1.x, WvPanoramaButton.scope1.y, WvPanoramaButton.scope1.width, WvPanoramaButton.scope1.height);
        g.drawLine(3, 11, 39, 11);
        g.drawLine(22, 3, 22, 18);
        g.setColor(Color.lightGray);
        g.draw3DRect(3, 3, 39, 17, false);
        color = (this.isEnabled() ? Color.blue : Color.gray);
        g.setColor(color);
        g.drawRect(WvPanoramaButton.scope2.x, WvPanoramaButton.scope2.y, WvPanoramaButton.scope2.width, WvPanoramaButton.scope2.height);
        if (!this.raised) {
            g.translate(-1, -1);
        }
        g.setColor(Color.lightGray);
        g.draw3DRect(0, 0, 45, 23, this.raised);
    }
    
    public boolean mouseUp(final Event event, final int i, final int j) {
        this.raised = true;
        this.repaint();
        return false;
    }
    
    public void disconnect(final int i) {
        this.disable();
    }
    
    public Dimension minimumSize() {
        return WvPanoramaButton.SIZE;
    }
    
    public void videoConnected(final boolean flag) {
        this.videoConnected = flag;
        if (this.videoConnected && this.cameraConnected) {
            this.enable();
        }
    }
    
    public void enable() {
        super.enable();
        this.repaint();
    }
    
    public void disable() {
        super.disable();
        this.repaint();
    }
    
    public WvPanoramaButton() {
        this.raised = true;
        this.disable();
    }
    
    public Dimension preferredSize() {
        return WvPanoramaButton.SIZE;
    }
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        this.raised = false;
        this.repaint();
        return false;
    }
    
    public void connect(final String s) {
        this.disable();
    }
}
