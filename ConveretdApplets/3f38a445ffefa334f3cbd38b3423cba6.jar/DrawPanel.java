import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class DrawPanel extends Panel implements MouseListener, MouseMotionListener
{
    public static final int LINES = 0;
    public static final int POINTS = 1;
    int mode;
    Vector lines;
    Vector colors;
    int x1;
    int y1;
    int x2;
    int y2;
    int xl;
    int yl;
    int last;
    int FG_BG;
    int drawm;
    
    public DrawPanel() {
        this.mode = 0;
        this.lines = new Vector();
        this.colors = new Vector();
        this.FG_BG = 0;
        this.drawm = 0;
        this.setBackground(Color.black);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        mouseEvent.consume();
        switch (this.mode) {
            case 0: {
                this.xl = this.x2;
                this.yl = this.y2;
                this.x2 = mouseEvent.getX();
                this.y2 = mouseEvent.getY();
                if (this.drawm == 1) {
                    this.x1 = this.x2;
                }
                else if (this.drawm == 2) {
                    this.y1 = this.y2;
                }
                this.colors.addElement(this.getForeground());
                this.lines.addElement(new Rectangle(this.x1, this.y1, this.x2, this.y2));
                break;
            }
            default: {
                this.colors.addElement(this.getForeground());
                this.lines.addElement(new Rectangle(this.x1, this.y1, mouseEvent.getX(), mouseEvent.getY()));
                this.x1 = mouseEvent.getX();
                this.y1 = mouseEvent.getY();
                break;
            }
        }
        this.last = this.lines.size() - 1;
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.last = 0;
        this.repaint();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        mouseEvent.consume();
        switch (this.mode) {
            case 0: {
                this.x1 = mouseEvent.getX();
                this.y1 = mouseEvent.getY();
                this.x2 = -1;
                break;
            }
            default: {
                this.colors.addElement(this.getForeground());
                this.lines.addElement(new Rectangle(mouseEvent.getX(), mouseEvent.getY(), -1, -1));
                this.x1 = mouseEvent.getX();
                this.y1 = mouseEvent.getY();
                this.repaint();
                break;
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        mouseEvent.consume();
        switch (this.mode) {
            case 0: {
                this.colors.addElement(this.getForeground());
                this.lines.addElement(new Rectangle(this.x1, this.y1, mouseEvent.getX(), mouseEvent.getY()));
                final int n = -1;
                this.xl = n;
                this.x2 = n;
                break;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        final int size = this.lines.size();
        graphics.setColor(this.getForeground());
        graphics.setPaintMode();
        for (int i = this.last; i < size; ++i) {
            final Rectangle rectangle = this.lines.elementAt(i);
            graphics.setColor((Color)this.colors.elementAt(i));
            if (rectangle.width != -1) {
                graphics.drawLine(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
            else {
                graphics.drawLine(rectangle.x, rectangle.y, rectangle.x, rectangle.y);
            }
        }
    }
    
    public void setDrawMode(final int mode) {
        switch (mode) {
            case 0:
            case 1: {
                this.mode = mode;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    public void setFG_BG(final int n) {
        switch (n) {
            case 0: {
                this.FG_BG = 0;
                break;
            }
            case 1: {
                this.FG_BG = 1;
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    public void setStarMode(final int n) {
        switch (n) {
            case 0: {
                this.drawm = 0;
                break;
            }
            case 1: {
                this.drawm = 1;
                break;
            }
            case 2: {
                this.drawm = 2;
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
