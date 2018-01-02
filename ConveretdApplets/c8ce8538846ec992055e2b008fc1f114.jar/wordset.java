import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class wordset
{
    private String[] words;
    private String[] wordsLink;
    private int[] wordsWidth;
    private int[] wordsStart;
    private int maxwords;
    private int maxlen;
    private int wordsTop;
    private int width;
    private int height;
    private int align;
    private int pos;
    private boolean mousemove;
    private Color[] textcolor;
    private Color alinkcolor;
    private Color linkcolor;
    private Color tcolor;
    private boolean shadow;
    private int shadowshift;
    private int over;
    private Font mf;
    private int fontsize;
    private int fontspace;
    
    wordset(final Applet applet, final Graphics graphics, final int width, final int height) {
        String s = "Arial";
        final String parameter = applet.getParameter("font");
        if (parameter != null) {
            if (parameter.toLowerCase().equals("courier")) {
                s = "Courier";
            }
            else if (parameter.toLowerCase().equals("dialog")) {
                s = "Dialog";
            }
            else if (parameter.toLowerCase().equals("dialoginput")) {
                s = "DialogInput";
            }
            else if (parameter.toLowerCase().equals("helvetica")) {
                s = "Helvetica";
            }
            else if (parameter.toLowerCase().equals("symbol")) {
                s = "Symbol";
            }
            else if (parameter.toLowerCase().equals("timesroman")) {
                s = "TimesRoman";
            }
            else {
                s = "Arial";
            }
        }
        this.fontsize = 11;
        final String parameter2 = applet.getParameter("fontsize");
        if (parameter2 != null) {
            this.fontsize = Integer.parseInt(parameter2, 10);
        }
        if (this.fontsize < 11) {
            this.fontsize = 11;
        }
        this.mf = new Font(s, 1, this.fontsize);
        this.fontspace = this.fontsize + 1 + this.fontsize / 4;
        this.shadow = false;
        this.shadowshift = 1;
        this.maxwords = 0;
        final String parameter3 = applet.getParameter("maxlines");
        if (parameter3 != null) {
            this.maxwords = Integer.parseInt(parameter3, 10);
        }
        System.err.println("maxlines: " + this.maxwords);
        this.pos = 0;
        final String parameter4 = applet.getParameter("textpos");
        if (parameter4 != null) {
            this.pos = Integer.parseInt(parameter4, 10);
        }
        System.err.println("textpos: " + this.pos);
        this.align = 1;
        final String parameter5 = applet.getParameter("align");
        if (parameter5 != null) {
            if (parameter5.toLowerCase().equals("left")) {
                this.align = 0;
            }
            else if (parameter5.toLowerCase().equals("right")) {
                this.align = 2;
            }
        }
        this.height = height;
        this.width = width;
        this.set(applet.getParameter("textcolor"), applet.getParameter("linkcolor"), applet.getParameter("alinkcolor"));
        if (this.maxwords > 0) {
            graphics.setFont(this.mf);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            for (int i = 0; i < this.maxwords; ++i) {
                this.setword(i, fontMetrics, applet.getParameter("line" + (i + 1)), applet.getParameter("color" + (i + 1)), applet.getParameter("url" + (i + 1)));
            }
            this.setalign();
        }
    }
    
    public void draw(final Graphics graphics) {
        int n = 0;
        int i = this.wordsTop;
        graphics.setFont(this.mf);
        while (i < 0) {
            ++n;
            i += this.fontspace;
        }
        while (n < this.maxwords && i < this.height + this.fontspace) {
            if (this.shadow) {
                graphics.setColor(Color.black);
                graphics.drawString(this.words[n], this.wordsStart[n] + this.shadowshift, i + this.shadowshift);
            }
            if (n == this.over) {
                graphics.setColor(this.alinkcolor);
            }
            else {
                graphics.setColor(this.textcolor[n]);
            }
            graphics.drawString(this.words[n], this.wordsStart[n], i);
            i += this.fontspace;
            ++n;
        }
        if (this.over < 0 && !this.mousemove && --this.wordsTop < -this.maxwords * this.fontspace) {
            this.wordsTop = this.height + 20;
        }
    }
    
    public String getLink() {
        if (this.over >= 0) {
            return this.wordsLink[this.over];
        }
        return "";
    }
    
    public boolean isOver(final int n, final int n2) {
        int i = this.wordsTop;
        int over = 0;
        while (i < 0) {
            ++over;
            i += this.fontspace;
        }
        while (over < this.maxwords && i < this.height + this.fontsize) {
            final int n3 = n2 - i;
            final int n4 = n - this.wordsStart[over];
            if (n4 > 0 && n3 <= 0 && n3 > -this.fontsize && n4 < this.wordsWidth[over] && this.wordsLink[over] != null) {
                this.over = over;
                return true;
            }
            i += this.fontspace;
            ++over;
        }
        this.over = -1;
        return false;
    }
    
    public void move(final boolean mousemove, final int n) {
        this.mousemove = mousemove;
        if (this.mousemove) {
            this.wordsTop += n;
        }
    }
    
    public void set(final String s, final String s2, final String s3) {
        this.mousemove = false;
        this.maxlen = 0;
        this.over = -1;
        this.wordsTop = this.height + 12;
        if (this.maxwords > 0) {
            this.words = new String[this.maxwords];
            this.wordsLink = new String[this.maxwords];
            this.wordsStart = new int[this.maxwords];
            this.wordsWidth = new int[this.maxwords];
            this.textcolor = new Color[this.maxwords];
            this.alinkcolor = new Color(250, 230, 80);
            this.tcolor = new Color(80, 250, 200);
            this.linkcolor = new Color(80, 250, 250);
            if (s3 != null) {
                this.alinkcolor = new Color(Integer.parseInt(s3, 16));
            }
            if (s != null) {
                this.tcolor = new Color(Integer.parseInt(s, 16));
            }
            if (s2 != null) {
                this.linkcolor = new Color(Integer.parseInt(s2, 16));
            }
        }
    }
    
    public void setalign() {
        if (this.align == 0) {
            final int n = this.pos + this.width / 2 - this.maxlen / 2;
            for (int i = 0; i < this.maxwords; ++i) {
                this.wordsStart[i] = n;
            }
        }
        else if (this.align == 2) {
            final int n2 = this.pos + this.width / 2 + this.maxlen / 2;
            for (int j = 0; j < this.maxwords; ++j) {
                this.wordsStart[j] = n2 - this.wordsWidth[j];
            }
        }
    }
    
    public void setword(final int n, final FontMetrics fontMetrics, final String s, final String s2, String s3) {
        this.words[n] = s;
        this.textcolor[n] = this.tcolor;
        if (s3 != null) {
            try {
                final URL url = new URL(s3);
            }
            catch (MalformedURLException ex) {
                s3 = null;
            }
            if (s3 != null) {
                this.wordsLink[n] = s3;
                this.textcolor[n] = this.linkcolor;
            }
        }
        if (s2 != null) {
            this.textcolor[n] = new Color(Integer.parseInt(s2, 16));
        }
        this.wordsWidth[n] = 0;
        if (this.words[n] != null) {
            this.wordsWidth[n] = fontMetrics.stringWidth(this.words[n]);
            if (this.wordsWidth[n] > this.maxlen) {
                this.maxlen = this.wordsWidth[n];
            }
            this.wordsStart[n] = this.pos + this.width / 2 - this.wordsWidth[n] / 2;
        }
        System.err.println("Line" + (n + 1) + ": " + this.words[n] + " : (" + this.wordsWidth[n] + ") " + this.wordsLink[n] + " ");
    }
    
    public void shadowOn() {
        this.shadow = true;
    }
    
    public void shadowOn(final int shadowshift) {
        this.shadow = true;
        this.shadowshift = shadowshift;
    }
}
