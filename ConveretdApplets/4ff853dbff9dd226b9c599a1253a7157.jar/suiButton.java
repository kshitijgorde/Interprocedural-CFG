import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class suiButton extends Canvas
{
    int width;
    int height;
    Image Input;
    Image[] pic;
    int brightStep;
    int sideWidth;
    int pid;
    int top;
    int left;
    int down;
    int right;
    int mousex;
    int mousey;
    int[] datain;
    boolean en;
    
    suiButton(final Image input, final int brightStep, final int sideWidth) {
        this.pic = new Image[4];
        this.brightStep = 50;
        this.sideWidth = 10;
        this.en = true;
        this.Input = input;
        this.width = input.getWidth(this);
        this.height = input.getHeight(this);
        this.datain = new int[this.width * this.height];
        final PixelGrabber pixelGrabber = new PixelGrabber(input, 0, 0, this.width, this.height, this.datain, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        this.brightStep = brightStep;
        this.sideWidth = sideWidth;
        this.ButtonImage();
    }
    
    void ButtonImage() {
        float n = 0.0f;
        float n2 = 0.0f;
        float n3 = 0.0f;
        float n4 = 1.0E7f;
        float n5 = 0.0f;
        float n6 = 0.0f;
        final int n7 = this.width / 2;
        final int n8 = this.height / 2;
        final int[] array = new int[this.width * this.height];
        final int[] array2 = new int[this.width * this.height];
        final int[] array3 = new int[this.width * this.height];
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                final int red = this.red(i, j);
                final int green = this.green(i, j);
                final int blue = this.blue(i, j);
                final int n9 = j * n8 + i * (this.width - 1 - n7) + (n8 - this.width * n8);
                final int n10 = j * n8 - i * n7;
                if (j >= this.sideWidth && j <= this.width - this.sideWidth && i >= this.sideWidth && i <= this.height - this.sideWidth) {
                    n5 = 1.0f;
                    n6 = 1.0f;
                }
                else if (n9 < 0 && n10 < 0) {
                    n5 = (100.0f + this.brightStep) / 100.0f;
                    n6 = (100.0f - this.brightStep) / 100.0f;
                }
                else if (n9 < 0 && n10 >= 0) {
                    n5 = (100.0f + this.brightStep + 20.0f) / 100.0f;
                    n6 = (100.0f - this.brightStep - 20.0f) / 100.0f;
                }
                else if (n9 >= 0 && n10 < 0) {
                    n5 = (100.0f - this.brightStep - 20.0f) / 100.0f;
                    n6 = (100.0f + this.brightStep + 20.0f) / 100.0f;
                }
                else if (n9 >= 0 && n10 >= 0) {
                    n5 = (100.0f - this.brightStep) / 100.0f;
                    n6 = (100.0f + this.brightStep) / 100.0f;
                }
                int n11 = (int)(red * n5);
                int n12 = (int)(green * n5);
                int n13 = (int)(blue * n5);
                if (n11 > 255) {
                    n11 = 255;
                }
                if (n11 < 0) {
                    n11 = 0;
                }
                if (n12 > 255) {
                    n12 = 255;
                }
                if (n12 < 0) {
                    n12 = 0;
                }
                if (n13 > 255) {
                    n13 = 255;
                }
                if (n13 < 0) {
                    n13 = 0;
                }
                array[i * this.width + j] = new Color(n11, n12, n13).getRGB();
                int n14 = (int)(red * n6);
                int n15 = (int)(green * n6);
                int n16 = (int)(blue * n6);
                if (n14 > 255) {
                    n14 = 255;
                }
                if (n14 < 0) {
                    n14 = 0;
                }
                if (n15 > 255) {
                    n15 = 255;
                }
                if (n15 < 0) {
                    n15 = 0;
                }
                if (n16 > 255) {
                    n16 = 255;
                }
                if (n16 < 0) {
                    n16 = 0;
                }
                array2[i * this.width + j] = new Color(n14, n15, n16).getRGB();
            }
        }
        for (int k = 0; k < this.height; ++k) {
            for (int l = 0; l < this.width; ++l) {
                if (l == this.width - 1 || k == this.height - 1) {
                    array3[k * this.width + l] = (int)(50.0f * (n + n2 + 1.0f) / (float)Math.sqrt(3.0));
                }
                else {
                    final float n17 = (this.red(k, l) + this.green(k, l) + this.blue(k, l)) / 3.0f;
                    final float n18 = (this.red(k, l + 1) + this.green(k, l + 1) + this.blue(k, l + 1)) / 3.0f;
                    final float n19 = (this.red(k + 1, l) + this.green(k + 1, l) + this.blue(k + 1, l)) / 3.0f;
                    n = n18 - n17;
                    n2 = n19 - n17;
                    final float n20 = 50.0f * (n + n2 + 1.0f) / (float)Math.sqrt(3.0);
                    array3[k * this.width + l] = (int)n20;
                    if (n20 > n3) {
                        n3 = n20;
                    }
                    if (n20 < n4) {
                        n4 = n20;
                    }
                }
            }
        }
        for (int n21 = 0; n21 < this.height; ++n21) {
            for (int n22 = 0; n22 < this.width; ++n22) {
                final float n23 = 50.0f / (float)Math.sqrt(3.0);
                if (array3[n21 * this.width + n22] >= n23) {
                    final float n24 = 192.0f + (array3[n21 * this.width + n22] - n23) * 63.0f / (n3 - n23);
                    array3[n21 * this.width + n22] = new Color((int)n24, (int)n24, (int)n24).getRGB();
                }
                else {
                    final float n25 = 192.0f + (array3[n21 * this.width + n22] - n23) * -182.0f / (n4 - n23);
                    array3[n21 * this.width + n22] = new Color((int)n25, (int)n25, (int)n25).getRGB();
                }
            }
        }
        this.pic[0] = this.Input;
        this.pic[1] = this.createImage(new MemoryImageSource(this.width, this.height, array, 0, this.width));
        this.pic[2] = this.createImage(new MemoryImageSource(this.width, this.height, array2, 0, this.width));
        this.pic[3] = this.createImage(new MemoryImageSource(this.width, this.height, array3, 0, this.width));
    }
    
    public int red(final int n, final int n2) {
        return (this.datain[n * this.width + n2] & 0xFF0000) >> 16;
    }
    
    public int green(final int n, final int n2) {
        return (this.datain[n * this.width + n2] & 0xFF00) >> 8;
    }
    
    public int blue(final int n, final int n2) {
        return this.datain[n * this.width + n2] & 0xFF;
    }
    
    public static int r(final int n) {
        return (n & 0xFF0000) >> 16;
    }
    
    public static int g(final int n) {
        return (n & 0xFF00) >> 8;
    }
    
    public static int b(final int n) {
        return n & 0xFF;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.pic[this.pid], 0, 0, this);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.en) {
            this.pid = 0;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.en) {
            this.pid = 1;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.en) {
            this.pid = 2;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.en && this.inside(n, n2)) {
            this.pid = 1;
            this.repaint();
            if (!this.action(event, event.arg)) {
                for (Container container = this.getParent(); container != null && !container.action(event, event.arg); container = container.getParent()) {}
            }
        }
        return true;
    }
    
    public void setDisable() {
        this.pid = 3;
        this.repaint();
        this.disable();
        this.en = false;
    }
    
    public void setEnable() {
        this.pid = 0;
        this.repaint();
        this.enable();
        this.en = true;
    }
    
    public Dimension getMinimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void setRoom(final int top, final int left, final int down, final int right) {
        this.top = top;
        this.left = left;
        this.down = down;
        this.right = right;
        this.repaint();
    }
    
    public Insets getInsets() {
        return new Insets(this.top, this.left, this.down, this.right);
    }
}
