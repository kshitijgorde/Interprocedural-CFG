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
    
    public void kickOff() {
        this.raised = true;
        this.videoConnected = false;
        this.cameraConnected = false;
        this.disable();
    }
    
    public void cameraConnected(final boolean cameraConnected) {
        this.cameraConnected = cameraConnected;
        if (this.cameraConnected && this.videoConnected) {
            this.enable();
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.isEnabled() ? Color.black : Color.gray);
        if (!this.raised) {
            graphics.translate(1, 1);
        }
        graphics.drawRect(WvPanoramaButton.scope1.x, WvPanoramaButton.scope1.y, WvPanoramaButton.scope1.width, WvPanoramaButton.scope1.height);
        graphics.drawLine(3, 11, 39, 11);
        graphics.drawLine(22, 3, 22, 18);
        graphics.setColor(Color.lightGray);
        graphics.draw3DRect(3, 3, 39, 17, false);
        graphics.setColor(this.isEnabled() ? Color.blue : Color.gray);
        graphics.drawRect(WvPanoramaButton.scope2.x, WvPanoramaButton.scope2.y, WvPanoramaButton.scope2.width, WvPanoramaButton.scope2.height);
        if (!this.raised) {
            graphics.translate(-1, -1);
        }
        graphics.setColor(Color.lightGray);
        graphics.draw3DRect(0, 0, 45, 23, this.raised);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.raised = true;
        this.repaint();
        return false;
    }
    
    public void disconnect(final int n) {
        this.disable();
    }
    
    public Dimension minimumSize() {
        return WvPanoramaButton.SIZE;
    }
    
    public void videoConnected(final boolean videoConnected) {
        this.videoConnected = videoConnected;
        if (this.videoConnected && this.cameraConnected) {
            this.enable();
        }
    }
    
    static {
        SIZE = new Dimension(46, 24);
        scope1 = new Rectangle(6, 6, 32, 10);
        scope2 = new Rectangle(14, 5, 11, 9);
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
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.raised = false;
        this.repaint();
        return false;
    }
    
    public void connect(final String s) {
        this.disable();
    }
}
