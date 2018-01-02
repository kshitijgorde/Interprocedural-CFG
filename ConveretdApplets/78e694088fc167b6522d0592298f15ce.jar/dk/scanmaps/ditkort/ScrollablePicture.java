// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Scrollable;
import javax.swing.JLabel;

public class ScrollablePicture extends JLabel implements Scrollable, MouseMotionListener, MouseListener
{
    static final long serialVersionUID = 0L;
    private int maxUnitIncrement;
    private boolean missingPicture;
    Point point;
    Point anotherPoint;
    private int padding_x;
    private int padding_y;
    private int vertical;
    private int horizontal;
    private Integer region_x;
    private Integer region_y;
    
    public ScrollablePicture(final ImageIcon i, final int m) {
        super(i);
        this.maxUnitIncrement = 1;
        this.missingPicture = false;
        this.point = new Point();
        this.anotherPoint = new Point();
        if (i == null) {
            this.missingPicture = true;
            this.setText("No picture found.");
            this.setHorizontalAlignment(0);
            this.setOpaque(true);
            this.setBackground(Color.white);
        }
        this.maxUnitIncrement = m;
        this.padding_x = 30;
        this.padding_y = 40;
        this.vertical = 1873 / this.padding_x;
        this.horizontal = 2241 / this.padding_y;
        this.setVerticalAlignment(0);
        this.setAutoscrolls(true);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setEnabled(true);
    }
    
    public void mouseMoved(final MouseEvent e) {
        this.point.x = e.getX();
        this.point.y = e.getY();
        for (int x = 0; x < this.vertical; ++x) {
            for (int y = 0; y < this.horizontal; ++y) {
                if (this.point.x > x * this.padding_x && this.point.x < x * this.padding_x + this.padding_x && this.point.y > y * this.padding_y && this.point.y < y * this.padding_y + this.padding_y) {
                    this.region_x = x;
                    this.region_y = y;
                    break;
                }
            }
        }
    }
    
    public void mouseClicked(final MouseEvent e) {
        System.out.println("You are in " + this.point.x + ", " + this.point.y);
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
        final Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
        this.scrollRectToVisible(r);
    }
    
    public Dimension getPreferredSize() {
        if (this.missingPicture) {
            return new Dimension(564, 450);
        }
        return super.getPreferredSize();
    }
    
    public Dimension getPreferredScrollableViewportSize() {
        return this.getPreferredSize();
    }
    
    public int getScrollableUnitIncrement(final Rectangle visibleRect, final int orientation, final int direction) {
        int currentPosition = 0;
        if (orientation == 0) {
            currentPosition = visibleRect.x;
        }
        else {
            currentPosition = visibleRect.y;
        }
        if (direction < 0) {
            final int newPosition = currentPosition - currentPosition / this.maxUnitIncrement * this.maxUnitIncrement;
            return (newPosition == 0) ? this.maxUnitIncrement : newPosition;
        }
        return (currentPosition / this.maxUnitIncrement + 1) * this.maxUnitIncrement - currentPosition;
    }
    
    public int getScrollableBlockIncrement(final Rectangle visibleRect, final int orientation, final int direction) {
        if (orientation == 0) {
            return visibleRect.width - this.maxUnitIncrement;
        }
        return visibleRect.height - this.maxUnitIncrement;
    }
    
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }
    
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
    
    public void setMaxUnitIncrement(final int pixels) {
        this.maxUnitIncrement = pixels;
    }
    
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
    }
    
    private void drawBox(final int x, final int y) {
        this.anotherPoint.x = x;
        this.anotherPoint.y = y;
        this.repaint();
    }
}
