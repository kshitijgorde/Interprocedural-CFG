import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageButton extends Canvas
{
    Image image;
    boolean mouseInView;
    boolean mouseDown;
    boolean clickable;
    boolean state;
    boolean pushbutton;
    String label;
    
    ImageButton(final int i, final int j) {
        this(null, false);
        this.resize(i, j);
    }
    
    ImageButton(final Image image1) {
        this(image1, true);
    }
    
    ImageButton(final Image image1, final boolean flag) {
        this.mouseInView = false;
        this.mouseDown = false;
        this.state = false;
        this.pushbutton = false;
        this.image = image1;
        this.clickable = flag;
        if (this.image != null) {
            this.resize(this.image.getWidth(null) + 2, this.image.getHeight(null) + 2);
        }
    }
    
    ImageButton(final Image image1, final boolean flag, final boolean flag1) {
        this(image1, flag);
        this.pushbutton = flag1;
    }
    
    ImageButton(final String s, final int i, final int j) {
        this(i, j);
        this.label = s;
    }
    
    public boolean getState() {
        return this.state;
    }
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        if (this.clickable) {
            this.mouseDown = true;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int i, final int j) {
        this.mouseInView = true;
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int i, final int j) {
        this.mouseInView = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int i, final int j) {
        if (this.clickable) {
            if (this.pushbutton) {
                this.state = !this.state;
            }
            this.mouseDown = false;
            this.repaint();
        }
        if (this.mouseInView && this.clickable) {
            this.postEvent(new Event(this, 1001, null));
        }
        return true;
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("sanserif", 0, 10));
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.size().width, this.size().height);
        if (this.clickable && this.image != null) {
            g.setColor(this.getForeground());
        }
        if (this.image != null) {
            g.drawImage(this.image, ((this.mouseDown || this.getState()) ? 1 : 0) + (this.size().width - this.image.getWidth(this) >> 1), ((this.mouseDown || this.getState()) ? 1 : 0) + (this.size().height - this.image.getHeight(this) >> 1), this);
        }
        if (this.label != null) {
            g.setColor(this.getForeground());
            g.drawString(this.label, this.size().width - g.getFontMetrics().stringWidth(this.label) + ((this.mouseDown || this.getState()) ? 1 : 0), ((this.mouseDown || this.getState()) ? 1 : 0) + this.size().height - 3);
            g.drawLine(this.size().width - g.getFontMetrics().stringWidth(this.label) + ((this.mouseDown || this.getState()) ? 1 : 0), ((this.mouseDown || this.getState()) ? 1 : 0) + this.size().height - 3, this.size().width, ((this.mouseDown || this.getState()) ? 1 : 0) + this.size().height - 3);
        }
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        this.repaint();
    }
    
    public void setClickable(final boolean flag) {
        this.clickable = flag;
    }
    
    public void setImage(final Image image1) {
        this.image = image1;
        this.repaint();
    }
    
    public void setState(final boolean flag) {
        this.state = flag;
        this.repaint();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
