import java.awt.Component;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class DrawingPanel extends Panel implements MouseListener, MouseMotionListener
{
    private Color color;
    private int oldx;
    private int oldy;
    private int newx;
    private int newy;
    private boolean clear;
    private Image image;
    
    public DrawingPanel() {
        this.color = Color.black;
        this.clear = false;
        this.setBackground(Color.white);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public void setClear(final boolean clear) {
        this.clear = clear;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.image == null) {
            this.image = this.createImage(this.getSize().width, this.getSize().height);
        }
        final Graphics graphics2 = this.image.getGraphics();
        graphics2.setColor(this.color);
        if (!this.clear) {
            graphics2.drawLine(this.oldx, this.oldy, this.newx, this.newy);
            this.oldx = this.newx;
            this.oldy = this.newy;
        }
        else {
            graphics2.setColor(Color.white);
            graphics2.fillRect(0, 0, this.image.getWidth(this), this.image.getHeight(this));
        }
        graphics.drawImage(this.image, 0, 0, this);
        this.clear = false;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.oldx = mouseEvent.getX();
        this.oldy = mouseEvent.getY();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.newx = mouseEvent.getX();
        this.newy = mouseEvent.getY();
        this.repaint();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("Sample");
        frame.add(new DrawingPanel());
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
