import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Canvas;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Line
{
    public Vector Strings;
    public Vector BG;
    public Vector FG;
    public Vector Bold;
    public Vector UnderLine;
    private Canvas Parent;
    private Vector LinesImages;
    private Font TFont;
    private FontMetrics FontInfo;
    private boolean fullMode;
    private Color mBG;
    
    public Vector getImages() {
        return this.LinesImages;
    }
    
    public int RenderLine(final int n, final Canvas parent) {
        this.Parent = parent;
        if (!parent.isVisible()) {
            return -1;
        }
        int n2;
        if (n < 200) {
            n2 = 200;
        }
        else {
            n2 = n;
        }
        try {
            this.paintLine(n2);
        }
        catch (NullPointerException ex) {
            return 0;
        }
        return this.LinesImages.size();
    }
    
    public void setFullMode(final boolean fullMode, final Color mbg) {
        this.fullMode = fullMode;
        this.mBG = mbg;
    }
    
    public Image NewImage(final int n) {
        if (n <= 0) {
            return null;
        }
        this.TFont = new Font("Helvetica", 0, 14);
        final int height = this.Parent.getFontMetrics(this.TFont).getHeight();
        Image image;
        try {
            image = this.Parent.createImage(n, height);
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
        catch (NullPointerException ex2) {
            return null;
        }
        catch (Exception ex3) {
            return null;
        }
        try {
            final Graphics graphics = image.getGraphics();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, n, height);
            graphics.setFont(this.TFont);
            graphics.setColor(Color.white);
            this.FontInfo = graphics.getFontMetrics();
            graphics.dispose();
        }
        catch (NullPointerException ex4) {
            return null;
        }
        return image;
    }
    
    public int paintLine(final int n) {
        int n2 = 0;
        this.LinesImages.addElement(this.NewImage(n));
        Graphics graphics = this.LinesImages.elementAt(n2).getGraphics();
        this.FontInfo = graphics.getFontMetrics();
        final int n3 = this.Strings.size() - 1;
        final int n4 = n - 5;
        int n5 = 5;
        if (this.fullMode) {
            graphics.setColor(this.mBG);
            graphics.fillRect(0, 0, n, this.LinesImages.elementAt(n2).getHeight(null));
        }
        for (int i = 0; i <= n3; ++i) {
            final String s = this.Strings.elementAt(i);
            final Boolean b = this.UnderLine.elementAt(i);
            if (this.Bold.elementAt(i)) {
                graphics.setFont(this.TFont = new Font("Helvetica", 1, 14));
                this.FontInfo = graphics.getFontMetrics();
            }
            else {
                graphics.setFont(this.TFont = new Font("Helvetica", 0, 14));
                this.FontInfo = graphics.getFontMetrics();
            }
            this.FontInfo = graphics.getFontMetrics();
            final int n6 = (int)(this.FontInfo.getHeight() / 1.5) + 2;
            if (this.FontInfo.stringWidth(s) + n5 > n4) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
                for (int countTokens = stringTokenizer.countTokens(), j = 0; j < countTokens; ++j) {
                    final String string = stringTokenizer.nextToken() + " ";
                    if (this.FontInfo.stringWidth(string) + n5 > n4) {
                        if (this.FontInfo.stringWidth(string) > n4) {
                            ++n2;
                            this.LinesImages.addElement(this.NewImage(n));
                            graphics = this.LinesImages.elementAt(n2).getGraphics();
                            graphics.setFont(this.TFont);
                            n5 = 5 + this.FontInfo.stringWidth("  ");
                            graphics.setColor((Color)this.BG.elementAt(i));
                            graphics.fillRect(5, n6 - (int)(this.FontInfo.getHeight() / 1.5) - 3, this.FontInfo.stringWidth("  "), this.FontInfo.getHeight() + 2);
                            graphics.setColor((Color)this.FG.elementAt(i));
                            graphics.drawString("  ", 5, n6);
                            for (int k = 0; k < string.length() - 1; ++k) {
                                graphics.setColor((Color)this.BG.elementAt(i));
                                graphics.fillRect(n5, n6 - (int)(this.FontInfo.getHeight() / 1.5) - 3, this.FontInfo.stringWidth(string.substring(k, k + 1)), this.FontInfo.getHeight() + 2);
                                graphics.setColor((Color)this.FG.elementAt(i));
                                graphics.drawString(string.substring(k, k + 1), n5, n6);
                                graphics.setColor((Color)this.FG.elementAt(i));
                                if (b) {
                                    graphics.drawLine(n5, n6 + 1, n5 + this.FontInfo.stringWidth(string.substring(k, k + 1)), n6 + 1);
                                }
                                n5 += this.FontInfo.stringWidth(string.substring(k, k + 1));
                                if (n5 + this.FontInfo.stringWidth(string.substring(k, k + 1)) > n4) {
                                    ++n2;
                                    this.LinesImages.addElement(this.NewImage(n));
                                    graphics = this.LinesImages.elementAt(n2).getGraphics();
                                    graphics.setFont(this.TFont);
                                    n5 = 5 + this.FontInfo.stringWidth("  ");
                                    graphics.setColor((Color)this.BG.elementAt(i));
                                    graphics.fillRect(5, n6 - (int)(this.FontInfo.getHeight() / 1.5) - 3, this.FontInfo.stringWidth("  "), this.FontInfo.getHeight() + 2);
                                    graphics.setColor((Color)this.FG.elementAt(i));
                                    graphics.drawString("  ", 5, n6);
                                }
                            }
                        }
                        else {
                            ++n2;
                            this.LinesImages.addElement(this.NewImage(n));
                            graphics = this.LinesImages.elementAt(n2).getGraphics();
                            graphics.setFont(this.TFont);
                            final int n7 = 5 + this.FontInfo.stringWidth("  ");
                            graphics.setColor((Color)this.BG.elementAt(i));
                            graphics.fillRect(5, n6 - (int)(this.FontInfo.getHeight() / 1.5) - 3, this.FontInfo.stringWidth("  "), this.FontInfo.getHeight() + 2);
                            graphics.setColor((Color)this.FG.elementAt(i));
                            graphics.drawString("  ", 5, n6);
                            graphics.setColor((Color)this.BG.elementAt(i));
                            graphics.fillRect(n7, n6 - (int)(this.FontInfo.getHeight() / 1.5) - 3, this.FontInfo.stringWidth(string), this.FontInfo.getHeight() + 2);
                            graphics.setColor((Color)this.FG.elementAt(i));
                            graphics.drawString(string, n7, n6);
                            graphics.setColor((Color)this.FG.elementAt(i));
                            if (b) {
                                graphics.drawLine(n7, n6 + 1, n7 + this.FontInfo.stringWidth(string), n6 + 1);
                            }
                            n5 = n7 + this.FontInfo.stringWidth(string);
                        }
                    }
                    else {
                        graphics.setColor((Color)this.BG.elementAt(i));
                        graphics.fillRect(n5, n6 - (int)(this.FontInfo.getHeight() / 1.5) - 3, this.FontInfo.stringWidth(string), this.FontInfo.getHeight() + 2);
                        graphics.setColor((Color)this.FG.elementAt(i));
                        graphics.drawString(string, n5, n6);
                        graphics.setColor((Color)this.FG.elementAt(i));
                        if (b) {
                            graphics.drawLine(n5, n6 + 1, n5 + this.FontInfo.stringWidth(string), n6 + 1);
                        }
                        n5 += this.FontInfo.stringWidth(string);
                    }
                }
            }
            else {
                graphics.setColor((Color)this.BG.elementAt(i));
                graphics.fillRect(n5, n6 - (int)(this.FontInfo.getHeight() / 1.5) - 3, this.FontInfo.stringWidth(s), this.FontInfo.getHeight() + 2);
                graphics.setColor((Color)this.FG.elementAt(i));
                graphics.drawString(s, n5, n6);
                if (b) {
                    graphics.drawLine(n5, n6 + 1, n5 + this.FontInfo.stringWidth(s), n6 + 1);
                }
                n5 += this.FontInfo.stringWidth(s);
            }
        }
        graphics.dispose();
        System.gc();
        return this.LinesImages.size();
    }
    
    Line() {
        this.Strings = new Vector(10, 10);
        this.BG = new Vector(10, 10);
        this.FG = new Vector(10, 10);
        this.Bold = new Vector(10, 10);
        this.UnderLine = new Vector(10, 10);
        this.LinesImages = new Vector(2, 2);
        this.TFont = new Font("Helvetica", 0, 14);
        this.fullMode = false;
        this.mBG = Color.white;
    }
}
