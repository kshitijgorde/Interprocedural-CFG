import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Image;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class ImageCanvas extends Canvas implements MouseListener
{
    private Vector listeners;
    Image canvasImage;
    int Xsize;
    int Ysize;
    String Name;
    Cursor cursor;
    
    public ImageCanvas(final Image canvasImage, final int xsize, final int ysize, final String name) {
        this.listeners = new Vector();
        this.addMouseListener(this);
        this.canvasImage = canvasImage;
        this.Xsize = xsize;
        this.Ysize = ysize;
        this.setSize(this.Xsize + 5, this.Ysize);
        this.Name = name;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.notifyListeners();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        Cursor.getDefaultCursor();
    }
    
    public void addActionListener(final ActionListener actionListener) {
        if (!this.listeners.contains(actionListener)) {
            this.listeners.addElement(actionListener);
        }
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.listeners.removeElement(actionListener);
    }
    
    private void notifyListeners() {
        final ActionEvent actionEvent = new ActionEvent(this, 2000, this.Name);
        final Enumeration<ActionListener> elements = (Enumeration<ActionListener>)((Vector)this.listeners.clone()).elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().actionPerformed(actionEvent);
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.canvasImage, 5, 0, this.Xsize, this.Ysize, this);
    }
}
