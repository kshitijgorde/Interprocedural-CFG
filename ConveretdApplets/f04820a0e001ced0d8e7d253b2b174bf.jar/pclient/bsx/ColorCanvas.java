// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Color;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class ColorCanvas extends Canvas implements MouseListener
{
    private Vector listenerList;
    private Color squareColor;
    private Dimension squareSize;
    
    public ColorCanvas(final int n, final int n2, final int n3) {
        this.listenerList = null;
        this.initData(n, n2, n3);
    }
    
    public ColorCanvas(final int n, final int n2, final int n3, final Dimension squareSize) {
        this.listenerList = null;
        this.initData(n, n2, n3);
        this.squareSize = squareSize;
    }
    
    private void initData(final int n, final int n2, final int n3) {
        this.squareColor = new Color(n, n2, n3);
        this.squareSize = new Dimension(16, 16);
        this.listenerList = new Vector(1);
        this.enableEvents(16L);
        this.addMouseListener(this);
    }
    
    public void addListener(final ColorListener colorListener) {
        if (colorListener != null) {
            this.listenerList.addElement(colorListener);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        for (int i = 0; i < this.listenerList.size(); ++i) {
            ((ColorListener)this.listenerList.elementAt(i)).processColor(this.squareColor.getRed(), this.squareColor.getGreen(), this.squareColor.getBlue());
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public Dimension getPreferredSize() {
        return this.squareSize;
    }
    
    public Dimension preferredSize() {
        return this.squareSize;
    }
    
    public Dimension minumumSize() {
        return this.squareSize;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.squareColor);
        graphics.fillRect(0, 0, this.squareSize.width, this.squareSize.height);
    }
}
