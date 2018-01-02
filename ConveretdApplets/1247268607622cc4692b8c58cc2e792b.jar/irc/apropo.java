// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import irc.managers.Resources;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Container;
import java.awt.Frame;
import javax.swing.JFrame;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JDialog;

public class apropo extends JDialog implements MouseListener, MouseMotionListener
{
    private boolean movef;
    private int moveframx;
    private int moveframy;
    
    public apropo(final JFrame frame) {
        super(frame, "A propo", false);
        this.movef = false;
        this.moveframx = 0;
        this.moveframy = 0;
        this.setLocation(frame.getX() + 25, frame.getY() + 150);
        this.setContentPane(new monpanel());
        this.setResizable(false);
        this.setUndecorated(true);
        this.setSize(346, 266);
        this.getContentPane().addMouseListener(this);
        this.getContentPane().addMouseMotionListener(this);
        this.setVisible(true);
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.movef = false;
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.movef) {
            final Point location = this.getLocation();
            this.setLocation(location.x + (mouseEvent.getX() - this.moveframx), location.y + (mouseEvent.getY() - this.moveframy));
        }
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (!this.movef) {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.movef) {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        this.movef = false;
        if (mouseEvent.getY() < 27 && (mouseEvent.getX() <= 319 || mouseEvent.getX() >= 336 || mouseEvent.getY() <= 8 || mouseEvent.getY() >= 24)) {
            this.movef = true;
            this.setCursor(Cursor.getPredefinedCursor(13));
            this.moveframx = mouseEvent.getX();
            this.moveframy = mouseEvent.getY();
        }
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.getX() > 319 && mouseEvent.getX() < 336 && mouseEvent.getY() > 8 && mouseEvent.getY() < 24) {
            this.dispose();
        }
        this.movef = false;
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    class monpanel extends JPanel
    {
        private Image img;
        
        public monpanel() {
            this.img = Resources.getImages("apropot");
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            graphics.drawImage(this.img, 0, 0, this.getSize().width, this.getSize().height, this);
        }
    }
}
