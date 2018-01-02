import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Dragon extends Applet implements Runnable
{
    Dimension d;
    String[] s;
    Thread kicker;
    int xScroll;
    int y_Point;
    Font font;
    int strHeight;
    int strWidth;
    int maxText;
    int curTextCount;
    String fontcolor;
    Color color;
    int red;
    int green;
    int blue;
    int rgb;
    int random;
    String background;
    Color bgcolor;
    int bgred;
    int bggreen;
    int bgblue;
    int bgrgb;
    int speed;
    Image offs;
    Graphics grf;
    char[] separated;
    int charWidth;
    float[] f;
    int maxFloatArray;
    
    public void stop() {
        if (this.kicker != null) {
            this.kicker.stop();
            this.kicker = null;
        }
    }
    
    public Font initFont() {
        int fontstyle = -1;
        final Font defaultfont = this.getFont();
        String fontname = this.getParameter("fontname");
        if (fontname == null) {
            fontname = defaultfont.getName();
        }
        final String stylestring = this.getParameter("fontstyle");
        if (stylestring == null) {
            fontstyle = defaultfont.getStyle();
        }
        if (!fontname.equalsIgnoreCase("Courier") && !fontname.equalsIgnoreCase("Dialog") && !fontname.equalsIgnoreCase("Helvetica") && !fontname.equalsIgnoreCase("TimesRoman")) {
            fontname = defaultfont.getName();
        }
        if (fontstyle == -1) {
            if (stylestring.equalsIgnoreCase("PLAIN")) {
                fontstyle = 0;
            }
            else if (stylestring.equalsIgnoreCase("BOLD")) {
                fontstyle = 1;
            }
            else if (stylestring.equalsIgnoreCase("ITALIC")) {
                fontstyle = 2;
            }
            else if (stylestring.equalsIgnoreCase("PLAIN_ITALIC")) {
                fontstyle = 2;
            }
            else if (stylestring.equalsIgnoreCase("BOLD_ITALIC")) {
                fontstyle = 3;
            }
            else {
                fontstyle = 1;
            }
        }
        final String sizestring = this.getParameter("fontsize");
        int fontsize;
        if (sizestring == null) {
            fontsize = defaultfont.getSize();
        }
        else {
            try {
                fontsize = Integer.parseInt(sizestring);
            }
            catch (NumberFormatException e) {
                fontsize = defaultfont.getSize();
            }
        }
        final Font font = new Font(fontname, fontstyle, fontsize);
        return font;
    }
    
    public void paint(final Graphics g) {
        this.grf.setFont(this.font);
        this.grf.setColor(this.bgcolor);
        this.grf.fillRect(0, 0, this.d.width, this.d.height);
        if (this.xScroll == this.d.width) {
            if (this.random == 1) {
                this.red = (int)(Math.random() * 256.0);
                this.blue = (int)(Math.random() * 256.0);
                this.green = (int)(Math.random() * 256.0);
                this.color = new Color(this.red, this.green, this.blue);
            }
            ++this.curTextCount;
            if (this.curTextCount > this.maxText) {
                this.curTextCount = 1;
            }
            this.strWidth = this.getFontMetrics(this.font).stringWidth(this.s[this.curTextCount - 1]);
            this.separated = new char[this.s[this.curTextCount - 1].length()];
            this.s[this.curTextCount - 1].getChars(0, this.s[this.curTextCount - 1].length(), this.separated, 0);
        }
        this.grf.setColor(this.color);
        this.xScroll -= this.speed;
        int _xOffset = 0;
        final int _width = this.d.height - this.y_Point;
        for (int i = 0; i < this.s[this.curTextCount - 1].length(); ++i) {
            int _floatIdx = 0;
            _xOffset += ((i != 0) ? this.getFontMetrics(this.font).charWidth(this.separated[i - 1]) : 0);
            if (this.xScroll + _xOffset < this.d.width) {
                if (this.d.width - this.xScroll - i >= 0) {
                    _floatIdx = (this.d.width - this.xScroll - i) % this.maxFloatArray;
                }
                else {
                    _floatIdx = 0;
                }
            }
            final int _y = (int)(this.f[_floatIdx] * _width * 0.9);
            this.grf.drawChars(this.separated, i, 1, this.xScroll + _xOffset, this.y_Point + _y);
        }
        if (this.xScroll < -this.strWidth) {
            this.xScroll = this.d.width;
        }
        g.drawImage(this.offs, 0, 0, this);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void start() {
        if (this.kicker == null) {
            (this.kicker = new Thread(this)).start();
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(2);
        while (this.kicker != null) {
            this.repaint();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
        }
        this.kicker = null;
    }
    
    public void init() {
        this.d = this.size();
        this.xScroll = this.d.width;
        this.offs = this.createImage(this.d.width, this.d.height);
        this.grf = this.offs.getGraphics();
        this.f = new float[this.maxFloatArray];
        double x = 3.1;
        for (int i = 0; i < this.maxFloatArray; ++i) {
            this.f[i] = (float)Math.sin(x) * (float)Math.cos(x) * (float)Math.exp(x);
            x -= 0.1;
        }
        String param = this.getParameter("speed");
        this.speed = ((param != null) ? Integer.parseInt(param) : 2);
        if (this.speed < 1 || this.speed > 5) {
            this.speed = 2;
        }
        param = this.getParameter("maxText");
        this.maxText = ((param != null) ? Integer.parseInt(param) : 1);
        this.s = new String[this.maxText];
        int j = 0;
        do {
            param = this.getParameter("text" + (j + 1));
            if (param != null) {
                this.s[j] = new String(param);
            }
            else {
                if (j == 0) {
                    this.s[j] = "Java";
                }
                this.maxText = j + 1;
            }
        } while (param != null && ++j != this.maxText);
        this.setFont(this.font = this.initFont());
        this.strHeight = this.getFontMetrics(this.font).getLeading();
        this.strHeight -= this.getFontMetrics(this.font).getDescent();
        this.strHeight += this.getFontMetrics(this.font).getAscent();
        this.y_Point = (this.d.height + this.strHeight - this.getFontMetrics(this.font).getDescent()) / 2;
        this.fontcolor = this.getParameter("fontcolor");
        if (this.fontcolor == null) {
            this.fontcolor = "black";
        }
        if (this.fontcolor.equals("setcolor")) {
            param = this.getParameter("rgb");
            final int nLen_rgb = param.length();
            try {
                if (nLen_rgb == 6) {
                    this.rgb = Integer.parseInt(param, 16);
                }
                else {
                    this.rgb = Integer.parseInt("000000", 16);
                }
            }
            catch (NumberFormatException e) {
                this.rgb = Integer.parseInt("000000", 16);
            }
            this.color = new Color(this.rgb);
        }
        else {
            if (this.fontcolor.equals("black")) {
                this.red = 0;
                this.green = 0;
                this.blue = 0;
            }
            else if (this.fontcolor.equals("blue")) {
                this.red = 0;
                this.green = 0;
                this.blue = 255;
            }
            else if (this.fontcolor.equals("cyan")) {
                this.red = 0;
                this.green = 255;
                this.blue = 255;
            }
            else if (this.fontcolor.equals("darkgray")) {
                this.red = 169;
                this.green = 169;
                this.blue = 169;
            }
            else if (this.fontcolor.equals("gray")) {
                this.red = 128;
                this.green = 128;
                this.blue = 128;
            }
            else if (this.fontcolor.equals("green")) {
                this.red = 0;
                this.green = 255;
                this.blue = 0;
            }
            else if (this.fontcolor.equals("lightgray")) {
                this.red = 211;
                this.green = 211;
                this.blue = 211;
            }
            else if (this.fontcolor.equals("magenta")) {
                this.red = 255;
                this.green = 0;
                this.blue = 255;
            }
            else if (this.fontcolor.equals("orange")) {
                this.red = 255;
                this.green = 165;
                this.blue = 0;
            }
            else if (this.fontcolor.equals("pink")) {
                this.red = 255;
                this.green = 192;
                this.blue = 203;
            }
            else if (this.fontcolor.equals("red")) {
                this.red = 255;
                this.green = 0;
                this.blue = 0;
            }
            else if (this.fontcolor.equals("white")) {
                this.red = 255;
                this.green = 255;
                this.blue = 255;
            }
            else if (this.fontcolor.equals("yellow")) {
                this.red = 255;
                this.green = 255;
                this.blue = 0;
            }
            else if (this.fontcolor.equals("random")) {
                this.random = 1;
                this.red = 0;
                this.green = 0;
                this.blue = 0;
            }
            else {
                this.red = 0;
                this.green = 0;
                this.blue = 0;
            }
            this.color = new Color(this.red, this.green, this.blue);
        }
        this.background = this.getParameter("background");
        if (this.background == null) {
            this.background = "white";
        }
        if (this.background.equals("setcolor")) {
            param = this.getParameter("bgrgb");
            final int nLen_bgrgb = param.length();
            try {
                if (nLen_bgrgb == 6) {
                    this.bgrgb = Integer.parseInt(param, 16);
                }
                else {
                    this.bgrgb = Integer.parseInt("ffffff", 16);
                }
            }
            catch (NumberFormatException e) {
                this.bgrgb = Integer.parseInt("ffffff", 16);
            }
            this.bgcolor = new Color(this.bgrgb);
        }
        else {
            if (this.background.equals("black")) {
                this.bgred = 0;
                this.bggreen = 0;
                this.bgblue = 0;
            }
            else if (this.background.equals("blue")) {
                this.bgred = 0;
                this.bggreen = 0;
                this.bgblue = 255;
            }
            else if (this.background.equals("cyan")) {
                this.bgred = 0;
                this.bggreen = 255;
                this.bgblue = 255;
            }
            else if (this.background.equals("darkgray")) {
                this.bgred = 169;
                this.bggreen = 169;
                this.bgblue = 169;
            }
            else if (this.background.equals("gray")) {
                this.bgred = 128;
                this.bggreen = 128;
                this.bgblue = 128;
            }
            else if (this.background.equals("green")) {
                this.bgred = 0;
                this.bggreen = 255;
                this.bgblue = 0;
            }
            else if (this.background.equals("lightgray")) {
                this.bgred = 211;
                this.bggreen = 211;
                this.bgblue = 211;
            }
            else if (this.background.equals("magenta")) {
                this.bgred = 255;
                this.bggreen = 0;
                this.bgblue = 255;
            }
            else if (this.background.equals("orange")) {
                this.bgred = 255;
                this.bggreen = 165;
                this.bgblue = 0;
            }
            else if (this.background.equals("pink")) {
                this.bgred = 255;
                this.bggreen = 192;
                this.bgblue = 203;
            }
            else if (this.background.equals("red")) {
                this.bgred = 255;
                this.bggreen = 0;
                this.bgblue = 0;
            }
            else if (this.background.equals("white")) {
                this.bgred = 255;
                this.bggreen = 255;
                this.bgblue = 255;
            }
            else if (this.background.equals("yellow")) {
                this.bgred = 255;
                this.bggreen = 255;
                this.bgblue = 0;
            }
            else {
                this.bgred = 255;
                this.bggreen = 255;
                this.bgblue = 255;
            }
            this.bgcolor = new Color(this.bgred, this.bggreen, this.bgblue);
        }
    }
    
    public Dragon() {
        this.maxFloatArray = 62;
    }
}
