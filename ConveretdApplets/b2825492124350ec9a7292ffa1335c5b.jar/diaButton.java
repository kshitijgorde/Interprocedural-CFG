import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class diaButton extends Canvas
{
    int width;
    int height;
    Image pic;
    Color bg;
    int textx;
    int texty;
    boolean text;
    String caption;
    int sideWidth;
    int pid;
    int top;
    int left;
    int down;
    int right;
    
    diaButton(final diaImage diaImage, final int sideWidth, final String caption, final int textx, final int texty, final Color bg) {
        this.width = 0;
        this.height = 0;
        this.textx = 3;
        this.texty = 10;
        this.text = false;
        this.sideWidth = 5;
        this.pid = 0;
        this.top = 5;
        this.left = 5;
        this.down = 5;
        this.right = 5;
        this.pic = diaImage.image;
        this.width = diaImage.width;
        this.height = diaImage.height;
        this.sideWidth = sideWidth;
        this.caption = caption;
        this.textx = textx;
        this.texty = texty;
        this.bg = bg;
        this.resize(this.width, this.height);
        this.repaint();
    }
    
    public Insets getInsets() {
        return new Insets(this.top, this.left, this.down, this.right);
    }
    
    public Dimension getMinimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.text = false;
        this.pid = 2;
        this.repaint();
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.text = true;
        this.pid = 1;
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.text = false;
        this.pid = 0;
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inside(n, n2)) {
            this.text = false;
            this.pid = 1;
            this.repaint();
            if (!this.action(event, event.arg)) {
                for (Container container = this.getParent(); container != null && !container.action(event, event.arg); container = container.getParent()) {}
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.bg);
        graphics.fillRect(0, 0, this.width, this.height);
        if (this.pic != null) {
            graphics.drawImage(this.pic, 0, 0, this);
        }
        if (this.pid == 1) {
            for (int i = 0; i < this.sideWidth; ++i) {
                graphics.draw3DRect(i, i, this.width - 1 - i * 2, this.height - 1 - i * 2, true);
            }
        }
        else if (this.pid == 2) {
            for (int j = 0; j < this.sideWidth; ++j) {
                graphics.draw3DRect(j, j, this.width - 1 - j * 2, this.height - 1 - j * 2, false);
            }
        }
        if (this.text) {
            graphics.setFont(new Font("Helvetica", 1, 12));
            graphics.setColor(Color.black);
            graphics.drawString(this.caption, this.textx - 1, this.texty - 1);
            graphics.drawString(this.caption, this.textx - 1, this.texty + 1);
            graphics.drawString(this.caption, this.textx + 1, this.texty - 1);
            graphics.drawString(this.caption, this.textx + 1, this.texty + 1);
            graphics.setColor(Color.cyan);
            graphics.drawString(this.caption, this.textx, this.texty);
        }
    }
    
    public void setRoom(final int top, final int left, final int down, final int right) {
        this.top = top;
        this.left = left;
        this.down = down;
        this.right = right;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
