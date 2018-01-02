import java.util.Random;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.applet.Applet;
import java.awt.Image;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class imageset
{
    final Font MFONT;
    int xsize;
    int ysize;
    int xpos;
    int ypos;
    int xinc;
    int yinc;
    int textlen;
    int sidemax;
    String text;
    String link;
    boolean over;
    boolean enter;
    boolean isLoaded;
    int gYSIZE;
    int screenx;
    int screeny;
    public Image img;
    
    public imageset(final Applet applet, final int n, final int screenx, final int screeny, final FontMetrics fontMetrics) {
        this.MFONT = new Font("Arial", 1, 11);
        this.screenx = screenx;
        this.screeny = screeny;
        this.gYSIZE = this.screeny - 17;
        this.img = applet.getImage(applet.getCodeBase(), applet.getParameter("img" + n));
        this.text = applet.getParameter("text" + n);
        this.link = applet.getParameter("url" + n);
        this.textlen = 0;
        if (this.text != null) {
            this.textlen = fontMetrics.stringWidth(this.text);
        }
        this.sidemax = 4;
        this.over = false;
        this.isLoaded = false;
    }
    
    public void draw(final Graphics graphics, final colors colors) {
        if (this.isLoaded) {
            graphics.drawImage(this.img, this.xpos, this.ypos, null);
            if (this.over) {
                graphics.setColor(Color.black);
                graphics.drawRect(this.xpos + 2, this.ypos + 2, this.xsize - 2, this.ysize - 2);
                graphics.setColor(colors.framecolor);
                graphics.drawRect(this.xpos + 1, this.ypos + 1, this.xsize - 2, this.ysize - 2);
                graphics.setColor(colors.framecolor2);
                graphics.drawRect(this.xpos, this.ypos, this.xsize, this.ysize);
                if (this.link != null && !colors.linkwhole) {
                    final int n = this.xpos + this.xsize / 2 - 20;
                    final int n2 = this.ypos + this.ysize - 25;
                    graphics.setColor(Color.black);
                    graphics.fillRect(n + 2, n2 + 2, 40, 15);
                    graphics.setColor(Color.white);
                    graphics.fillRect(n, n2 + 1, 40, 14);
                    graphics.setColor(new Color(150, 150, 150));
                    graphics.drawRect(n, n2, 40, 15);
                    if (!this.enter) {
                        graphics.setColor(new Color(150, 50, 50));
                    }
                    else {
                        graphics.setColor(new Color(50, 50, 150));
                    }
                    graphics.setFont(new Font("Arial", 1, 12));
                    graphics.drawString("Enter", n + 7, n2 + 12);
                }
                switch (colors.texttype) {
                    case 0: {
                        if (this.text != null) {
                            graphics.setColor(Color.white);
                            graphics.fillRect(this.xpos, this.ypos - 15, this.xsize, 14);
                            graphics.setColor(new Color(100, 100, 100));
                            graphics.drawRect(this.xpos, this.ypos - 16, this.xsize, 15);
                            graphics.setColor(new Color(50, 50, 150));
                            graphics.setFont(new Font("Arial", 1, 11));
                            graphics.drawString(this.text, this.xpos + 4, this.ypos - 4);
                            break;
                        }
                        break;
                    }
                    case 1:
                    case 2: {
                        if (this.text != null || colors.texttype == 1) {
                            graphics.setColor(colors.bordercolor);
                            graphics.fillRect(0, this.gYSIZE, this.screenx, 16);
                            graphics.setColor(colors.bordercolor2);
                            graphics.drawRect(-1, this.gYSIZE, this.screenx + 2, 16);
                        }
                        if (this.text != null) {
                            graphics.setColor(colors.textcolor);
                            graphics.setFont(this.MFONT);
                            graphics.drawString(this.text, 5, this.gYSIZE + 12);
                            break;
                        }
                        break;
                    }
                }
            }
            else if (colors.texttype == 1) {
                graphics.setColor(colors.bordercolor);
                graphics.fillRect(0, this.gYSIZE, this.screenx, 16);
                graphics.setColor(colors.bordercolor2);
                graphics.drawRect(-1, this.gYSIZE, this.screenx + 2, 16);
            }
        }
    }
    
    public void drawCenter(final Graphics graphics) {
        graphics.drawImage(this.img, (this.screenx - this.xsize) / 2, (this.screeny - this.ysize - 16) / 2, null);
    }
    
    public boolean isEnter(int n, int n2, final colors colors) {
        if (colors.linkwhole) {
            n -= this.xpos;
            n2 -= this.ypos;
            this.enter = (this.link != null && n > 0 && n2 > 0 && n < this.xsize && n2 < this.ysize);
        }
        else {
            n -= this.xpos + this.xsize / 2 - 18;
            n2 -= this.ypos + this.ysize - 25;
            this.enter = (this.link != null && n > 0 && n2 > 0 && n < 40 && n2 < 15);
        }
        return this.enter;
    }
    
    public boolean isOver(int n, int n2) {
        if (n < 0 || n2 < 0) {
            return this.over = false;
        }
        n -= this.xpos;
        n2 -= this.ypos;
        return this.over = (n > 0 && n2 > 0 && n < this.xsize && n2 < this.ysize);
    }
    
    public boolean isVisible() {
        return this.xpos >= -this.xsize && this.ypos >= -this.ysize && this.xpos < this.screenx && this.ypos < this.screeny;
    }
    
    public void move() {
        if (!this.over) {
            this.xpos += this.xinc;
            this.ypos += this.yinc;
        }
    }
    
    public void move(final int n, final int n2) {
        this.xpos += n;
        this.ypos += n2;
    }
    
    public void setDimensions(final int n, final Random random) {
        this.isLoaded = true;
        this.xsize = this.img.getWidth(null);
        this.ysize = this.img.getHeight(null);
        this.setpos(n, random);
    }
    
    public void setpos(final int n, final Random random) {
        final int abs = Math.abs(random.nextInt() % this.sidemax);
        final int n2 = n + Math.abs(random.nextInt() % 2);
        final int abs2 = Math.abs(random.nextInt() % (this.screenx - this.xsize - 40 + 20));
        final int abs3 = Math.abs(random.nextInt() % (this.screeny - this.ysize - 40 + 20));
        switch (abs) {
            case 0: {
                this.xinc = n + 1;
                this.yinc = 0;
                this.ypos = abs3;
                this.xpos = -this.xsize;
                break;
            }
            case 1: {
                this.xinc = -n - 1;
                this.yinc = 0;
                this.ypos = abs3;
                this.xpos = this.screenx;
                break;
            }
            case 2: {
                this.xinc = 0;
                this.yinc = n + 1;
                this.xpos = abs2;
                this.ypos = -this.ysize;
                break;
            }
            case 3: {
                this.xinc = 0;
                this.yinc = -n - 1;
                this.xpos = abs2;
                this.ypos = this.screeny;
                break;
            }
            case 4: {
                this.xinc = n + 1;
                this.yinc = n2 + 1;
                this.xpos = abs2 / 2;
                this.ypos = -this.ysize;
                break;
            }
            case 5: {
                this.xinc = n + 1;
                this.yinc = -n2 - 1;
                this.xpos = abs2 / 2;
                this.ypos = this.screeny;
                break;
            }
            case 6: {
                this.xinc = -n - 1;
                this.yinc = n2 + 1;
                this.xpos = abs2 / 2 + this.screenx / 2;
                this.ypos = -this.ysize;
                break;
            }
            case 7: {
                this.xinc = -n - 1;
                this.yinc = -n2 - 1;
                this.xpos = abs2 / 2 + this.screenx / 2;
                this.ypos = this.screeny;
                break;
            }
        }
    }
    
    public void setside(final int sidemax) {
        this.sidemax = sidemax;
    }
}
