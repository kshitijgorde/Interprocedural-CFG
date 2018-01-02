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
    
    wordset(final Applet app, final Graphics g, final int xsize, final int ysize) {
        String str = "Arial";
        String ts1 = app.getParameter("font");
        if (ts1 != null) {
            if (ts1.toLowerCase().equals("courier")) {
                str = "Courier";
            }
            else if (ts1.toLowerCase().equals("dialog")) {
                str = "Dialog";
            }
            else if (ts1.toLowerCase().equals("dialoginput")) {
                str = "DialogInput";
            }
            else if (ts1.toLowerCase().equals("helvetica")) {
                str = "Helvetica";
            }
            else if (ts1.toLowerCase().equals("symbol")) {
                str = "Symbol";
            }
            else if (ts1.toLowerCase().equals("timesroman")) {
                str = "TimesRoman";
            }
            else {
                str = "Arial";
            }
        }
        this.fontsize = 11;
        ts1 = app.getParameter("fontsize");
        if (ts1 != null) {
            this.fontsize = this.getInt(ts1, 10);
        }
        if (this.fontsize < 11) {
            this.fontsize = 11;
        }
        this.mf = new Font(str, 1, this.fontsize);
        this.fontspace = this.fontsize + 1 + this.fontsize / 4;
        this.shadow = false;
        this.shadowshift = 1;
        this.maxwords = 0;
        ts1 = app.getParameter("maxlines");
        if (ts1 != null) {
            this.maxwords = this.getInt(ts1, 10);
        }
        System.err.println("maxlines: " + this.maxwords);
        this.pos = 0;
        ts1 = app.getParameter("textpos");
        if (ts1 != null) {
            this.pos = this.getInt(ts1, 10);
        }
        System.err.println("textpos: " + this.pos);
        this.align = 1;
        ts1 = app.getParameter("align");
        if (ts1 != null) {
            if (ts1.toLowerCase().equals("left")) {
                this.align = 0;
            }
            else if (ts1.toLowerCase().equals("right")) {
                this.align = 2;
            }
        }
        this.height = ysize;
        this.width = xsize;
        this.set(app.getParameter("textcolor"), app.getParameter("linkcolor"), app.getParameter("alinkcolor"));
        if (this.maxwords > 0) {
            g.setFont(this.mf);
            final FontMetrics fm = g.getFontMetrics();
            for (int count = 0; count < this.maxwords; ++count) {
                this.setword(count, fm, app.getParameter("line" + (count + 1)), app.getParameter("color" + (count + 1)), app.getParameter("url" + (count + 1)));
            }
            this.setalign();
        }
    }
    
    public void draw(final Graphics g) {
        int i = 0;
        int y = this.wordsTop;
        g.setFont(this.mf);
        while (y < 0) {
            ++i;
            y += this.fontspace;
        }
        while (i < this.maxwords && y < this.height + this.fontspace) {
            if (this.shadow) {
                g.setColor(Color.black);
                g.drawString(this.words[i], this.wordsStart[i] + this.shadowshift, y + this.shadowshift);
            }
            if (i == this.over) {
                g.setColor(this.alinkcolor);
            }
            else {
                g.setColor(this.textcolor[i]);
            }
            g.drawString(this.words[i], this.wordsStart[i], y);
            y += this.fontspace;
            ++i;
        }
        if (this.over < 0 && !this.mousemove && --this.wordsTop < -this.maxwords * this.fontspace) {
            this.wordsTop = this.height + 20;
        }
    }
    
    int getInt(final String s) {
        return this.getInt(s, 10);
    }
    
    int getInt(final String s, final int len) {
        int i = 0;
        try {
            i = Integer.parseInt(s, len);
        }
        catch (NumberFormatException ex) {
            i = 0;
        }
        return i;
    }
    
    public String getLink() {
        if (this.over >= 0) {
            return this.wordsLink[this.over];
        }
        return "";
    }
    
    public boolean isOver(final int x, final int y) {
        int top = this.wordsTop;
        int i = 0;
        while (top < 0) {
            ++i;
            top += this.fontspace;
        }
        while (i < this.maxwords && top < this.height + this.fontsize) {
            final int yt = y - top;
            final int xt = x - this.wordsStart[i];
            if (xt > 0 && yt <= 0 && yt > -this.fontsize && xt < this.wordsWidth[i] && this.wordsLink[i] != null) {
                this.over = i;
                return true;
            }
            top += this.fontspace;
            ++i;
        }
        this.over = -1;
        return false;
    }
    
    public void move(final boolean dn, final int ymove) {
        this.mousemove = dn;
        if (this.mousemove) {
            this.wordsTop += ymove;
        }
    }
    
    public void set(final String c1, final String c2, final String c3) {
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
            this.tcolor = new Color(80, 250, 200);
            this.linkcolor = new Color(80, 250, 250);
            this.alinkcolor = new Color(250, 230, 80);
            if (c1 != null) {
                this.tcolor = new Color(this.getInt(c1, 16));
            }
            if (c2 != null) {
                this.linkcolor = new Color(this.getInt(c2, 16));
            }
            if (c3 != null) {
                this.alinkcolor = new Color(this.getInt(c3, 16));
            }
        }
    }
    
    public void setalign() {
        if (this.align == 0) {
            final int ln = this.pos + this.width / 2 - this.maxlen / 2;
            for (int i = 0; i < this.maxwords; ++i) {
                this.wordsStart[i] = ln;
            }
        }
        else if (this.align == 2) {
            final int ln = this.pos + this.width / 2 + this.maxlen / 2;
            for (int i = 0; i < this.maxwords; ++i) {
                this.wordsStart[i] = ln - this.wordsWidth[i];
            }
        }
    }
    
    public void setword(final int count, final FontMetrics fm, final String text, final String tcol, String turl) {
        this.words[count] = text;
        this.textcolor[count] = this.tcolor;
        if (turl != null) {
            try {
                final URL u = new URL(turl);
            }
            catch (MalformedURLException ex) {
                turl = null;
            }
            if (turl != null) {
                this.wordsLink[count] = turl;
                this.textcolor[count] = this.linkcolor;
            }
        }
        if (tcol != null) {
            this.textcolor[count] = new Color(this.getInt(tcol, 16));
        }
        this.wordsWidth[count] = 0;
        if (this.words[count] != null) {
            this.wordsWidth[count] = fm.stringWidth(this.words[count]);
            if (this.wordsWidth[count] > this.maxlen) {
                this.maxlen = this.wordsWidth[count];
            }
            this.wordsStart[count] = this.pos + this.width / 2 - this.wordsWidth[count] / 2;
        }
        System.err.println("Line" + (count + 1) + ": " + this.words[count] + " : (" + this.wordsWidth[count] + ") " + this.wordsLink[count] + " ");
    }
    
    public void shadowOn() {
        this.shadow = true;
    }
    
    public void shadowOn(final int s) {
        this.shadow = true;
        this.shadowshift = s;
    }
}
