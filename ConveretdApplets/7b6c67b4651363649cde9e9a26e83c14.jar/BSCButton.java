import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class BSCButton extends Canvas
{
    private Image img;
    private Image D_img;
    private Image U_img;
    private String Ret;
    private int Width;
    private int Height;
    private boolean Down;
    private boolean Initialized;
    private boolean MouseButDown;
    private Color C;
    
    public boolean mouseExit(final Event evt, final int x, final int y) {
        if (this.MouseButDown) {
            this.Down = false;
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        this.Down = true;
        this.MouseButDown = true;
        this.repaint();
        return true;
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        if (this.MouseButDown) {
            this.Down = true;
        }
        this.repaint();
        return true;
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.Width, this.Height);
    }
    
    public void setColor(final Color c) {
        this.C = c;
        this.Initialized = false;
        this.repaint();
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.Width, this.Height);
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        this.Down = false;
        this.MouseButDown = false;
        this.repaint();
        if (this.inside(x, y)) {
            this.postEvent(new Event(this, 1001, this.Ret));
        }
        return true;
    }
    
    public BSCButton(final Image P_img, final String P_Ret) {
        this.C = Color.lightGray;
        this.img = P_img;
        this.Ret = P_Ret;
        final MediaTracker img_tracker = new MediaTracker(this);
        img_tracker.addImage(this.img, 0);
        try {
            img_tracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.Width = this.img.getWidth(this) + 6;
        this.Height = this.img.getHeight(this) + 6;
    }
    
    public boolean action(final Event e, final Object o) {
        return false;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        if (!this.Initialized) {
            this.D_img = this.createImage(this.Width, this.Height);
            this.U_img = this.createImage(this.Width, this.Height);
            final Graphics D_buf = this.D_img.getGraphics();
            final Graphics U_buf = this.U_img.getGraphics();
            D_buf.setColor(Color.black);
            D_buf.fillRect(0, 0, this.Width, this.Height);
            D_buf.setColor(this.C);
            D_buf.fill3DRect(1, 1, this.Width - 1, this.Height - 1, false);
            D_buf.fillRect(2, 2, this.Width - 3, this.Height - 3);
            D_buf.drawImage(this.img, 3, 3, this);
            D_buf.dispose();
            U_buf.setColor(Color.black);
            U_buf.fillRect(0, 0, this.Width, this.Height);
            U_buf.setColor(this.C);
            U_buf.fill3DRect(0, 0, this.Width - 1, this.Height - 1, true);
            U_buf.drawImage(this.img, 2, 2, this);
            U_buf.dispose();
            this.Initialized = true;
        }
        if (this.Down) {
            g.drawImage(this.D_img, 0, 0, this);
        }
        else {
            g.drawImage(this.U_img, 0, 0, this);
        }
    }
}
