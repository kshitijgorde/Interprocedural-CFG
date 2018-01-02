import java.awt.Dimension;
import java.awt.Container;
import java.awt.Event;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class tovButton extends Canvas
{
    int width;
    int height;
    Image[] pic;
    tovImage si;
    int textx;
    int texty;
    boolean text;
    String caption;
    int brightStep;
    int sideWidth;
    int pid;
    int mousex;
    int mousey;
    
    tovButton(final tovImage si, final int brightStep, final int sideWidth, final String caption, final int textx, final int texty) {
        this.width = 0;
        this.height = 0;
        this.pic = new Image[3];
        this.textx = 3;
        this.texty = 10;
        this.text = false;
        this.brightStep = 50;
        this.sideWidth = 10;
        this.pid = 0;
        this.mousex = 0;
        this.mousey = 0;
        this.si = si;
        this.width = si.width;
        this.height = si.height;
        this.brightStep = brightStep;
        this.sideWidth = sideWidth;
        this.caption = caption;
        this.textx = textx;
        this.texty = texty;
        this.resize(this.width, this.height);
        this.IdleAndNormalButtonImage();
        this.SelectedButtonImage();
        this.repaint();
    }
    
    void IdleAndNormalButtonImage() {
        float n = 0.0f;
        final int n2 = this.width / 2;
        final int n3 = this.height / 2;
        final int[] array = new int[this.width * this.height];
        final int[] array2 = new int[this.width * this.height];
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                final int n4 = (this.si.red(i, j) + this.si.green(i, j) + this.si.blue(i, j)) / 3;
                final int n5 = j * n3 + i * (this.width - 1 - n2) + (n3 - this.width * n3);
                final int n6 = j * n3 - i * n2;
                if (j >= this.sideWidth && j <= this.width - this.sideWidth && i >= this.sideWidth && i <= this.height - this.sideWidth) {
                    n = 1.0f;
                }
                else if (n5 < 0 && n6 < 0) {
                    n = (100.0f + this.brightStep) / 100.0f;
                }
                else if (n5 < 0 && n6 >= 0) {
                    n = (100.0f + this.brightStep + 20.0f) / 100.0f;
                }
                else if (n5 >= 0 && n6 < 0) {
                    n = (100.0f - this.brightStep - 20.0f) / 100.0f;
                }
                else if (n5 >= 0 && n6 >= 0) {
                    n = (100.0f - this.brightStep) / 100.0f;
                }
                int n7 = (int)(this.si.red(i, j) * n);
                int n8 = (int)(this.si.green(i, j) * n);
                int n9 = (int)(this.si.blue(i, j) * n);
                if (n7 > 255) {
                    n7 = 255;
                }
                if (n7 < 0) {
                    n7 = 0;
                }
                if (n8 > 255) {
                    n8 = 255;
                }
                if (n8 < 0) {
                    n8 = 0;
                }
                if (n9 > 255) {
                    n9 = 255;
                }
                if (n9 < 0) {
                    n9 = 0;
                }
                array2[i * this.width + j] = new Color(n7, n8, n9).getRGB();
                int n10 = (int)((n7 + n8 + n9) * n / 3.0f);
                if (n10 > 255) {
                    n10 = 255;
                }
                if (n10 < 0) {
                    n10 = 0;
                }
                array[i * this.width + j] = new Color(n10, n10, n10).getRGB();
            }
        }
        this.pic[0] = this.createImage(new MemoryImageSource(this.width, this.height, array, 0, this.width));
        this.pic[1] = this.createImage(new MemoryImageSource(this.width, this.height, array2, 0, this.width));
    }
    
    void SelectedButtonImage() {
        final int[] array = new int[this.width * this.height];
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                array[i * this.width + j] = new Color(~this.si.red(i, j) & 0xFF, ~this.si.green(i, j) & 0xFF, ~this.si.blue(i, j) & 0xFF).getRGB();
            }
        }
        this.pic[2] = this.createImage(new MemoryImageSource(this.width, this.height, array, 0, this.width));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.pic[this.pid], 0, 0, this);
        this.text = false;
        if (this.text) {
            graphics.setFont(new Font("Helvetica", 0, 10));
            final int stringWidth = graphics.getFontMetrics().stringWidth(this.caption);
            final int height = graphics.getFontMetrics().getHeight();
            final int descent = graphics.getFontMetrics().getDescent();
            graphics.setColor(new Color(255, 255, 0));
            graphics.fillRoundRect(this.textx - 3, this.texty - 2, stringWidth + 5, height + 4, 6, 6);
            graphics.setColor(new Color(0, 80, 0));
            graphics.drawRoundRect(this.textx - 3, this.texty - 2, stringWidth + 5, height + 4, 6, 6);
            graphics.drawString(this.caption, this.textx, this.texty + height - descent);
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.text = false;
        this.pid = 0;
        this.repaint();
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.text = true;
        this.pid = 1;
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.text = false;
        this.pid = 2;
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
    
    public Dimension getMinimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
}
