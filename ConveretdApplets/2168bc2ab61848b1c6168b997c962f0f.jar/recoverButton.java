import java.awt.Font;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class recoverButton extends Canvas
{
    int width;
    int height;
    int fontsize;
    Color bg;
    Color fg;
    Color bgmain;
    String caption;
    String fonttype;
    int status;
    int textx;
    int texty;
    
    recoverButton(final int width, final int height, final String caption, final String fonttype, final int fontsize, final Color color, final Color fg) {
        this.width = width;
        this.height = height;
        this.caption = caption;
        this.fonttype = fonttype;
        this.fontsize = fontsize;
        this.bg = color;
        this.bgmain = color;
        this.fg = fg;
        this.resize(width, height);
        this.status = 0;
        this.repaint();
    }
    
    public Dimension getMinimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Insets insets() {
        return new Insets(0, 0, 0, 0);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.status = 2;
        this.repaint();
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.status = 1;
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.status = 0;
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inside(n, n2)) {
            this.status = 1;
            this.repaint();
            if (!this.action(event, event.arg)) {
                for (Container container = this.getParent(); container != null && !container.action(event, event.arg); container = container.getParent()) {}
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.bgmain);
        graphics.fillRect(0, 0, this.width - 1, this.height - 1);
        if (this.status == 2) {
            graphics.setColor(this.fg);
        }
        else {
            graphics.setColor(this.bg);
        }
        graphics.fillOval(0, 0, this.width - 1, this.height - 1);
        if (this.status == 0) {
            graphics.setFont(new Font(this.fonttype, 0, this.fontsize));
        }
        else {
            graphics.setFont(new Font(this.fonttype, 1, this.fontsize));
        }
        final int stringWidth = graphics.getFontMetrics().stringWidth(this.caption);
        final int height = graphics.getFontMetrics().getHeight();
        final int descent = graphics.getFontMetrics().getDescent();
        this.textx = (this.width - stringWidth) / 2;
        this.texty = (this.height + height) / 2 - descent - 1;
        if (this.status == 2) {
            graphics.setColor(this.bg);
        }
        else if (this.status == 1) {
            graphics.setColor(Color.red);
        }
        else {
            graphics.setColor(this.fg);
        }
        graphics.drawString(this.caption, this.textx, this.texty);
        graphics.drawOval(0, 0, this.width - 1, this.height - 1);
    }
    
    public void setBGmain(final Color bgmain) {
        this.bgmain = bgmain;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
