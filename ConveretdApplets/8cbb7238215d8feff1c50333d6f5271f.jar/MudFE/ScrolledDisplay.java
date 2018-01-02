// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.Panel;

public class ScrolledDisplay extends Panel implements DisplayInterface
{
    Scrollbar scroll;
    private DispChar[] buffer;
    private int maxrow;
    private int maxcol;
    private int prow;
    private int pcol;
    private int dispCol;
    private int row;
    private int col;
    private int buffsize;
    private int current;
    private int displayfrom;
    private Font CharFont;
    private Dimension mySize;
    private int maxscroll;
    private int lastscroll;
    private int barsize;
    boolean hscroll;
    private int hlastscroll;
    private int hmaxscroll;
    private int lastcolscroll;
    private int maxcolscroll;
    private final int _startBorder = 0;
    private static final boolean debug = false;
    private static final boolean redebug = false;
    private static final boolean sdebug = true;
    boolean dorepaint;
    boolean wrap;
    boolean cursor;
    int fontsize;
    boolean fixedfont;
    String fontType;
    
    public char getChar(final int r, final int c) {
        return this.buffer[this.normalise(r, c)].getChar();
    }
    
    public int getCol() {
        return this.col;
    }
    
    public void checkrepaint() {
        final Graphics g = this.getGraphics();
        if (g != null) {
            this.paint(g, this.row);
        }
    }
    
    public void advanceLine() {
        if (this.row < this.maxrow - 1) {
            ++this.row;
        }
        else {
            if (this.current == this.displayfrom) {
                if (++this.displayfrom > this.buffsize - 1) {
                    this.displayfrom = 0;
                }
            }
            else if (((this.displayfrom == 0 && this.current == this.buffsize - 1) || this.current == this.displayfrom - 1) && ++this.displayfrom > this.buffsize - 1) {
                this.displayfrom = 0;
            }
            if (++this.current > this.buffsize - 1) {
                this.current = 0;
            }
        }
        for (int i = 0; i < this.maxcol; ++i) {
            this.buffer[this.normalise(this.row, i)].set(' ', Attribute._defaultat);
        }
        this.col = 0;
        this.repaint();
    }
    
    public void down() {
        if (this.row < this.maxrow) {
            ++this.row;
        }
    }
    
    public void setWrap(final boolean w) {
        this.wrap = w;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void addChar(final char b, final Attribute a) {
        this.dorepaint = true;
        switch (b) {
            case '\n': {
                this.advanceLine();
                break;
            }
            case '\r': {
                this.col = 0;
                break;
            }
            case '\b': {
                if (this.col > 0) {
                    --this.col;
                }
                this.buffer[this.normalise(this.row, this.col)].set(' ', Attribute._defaultat);
                break;
            }
            default: {
                this.buffer[this.normalise(this.row, this.col)].set(b, a);
                if (this.col < this.maxcol - 1) {
                    ++this.col;
                    break;
                }
                if (this.wrap) {
                    this.advanceLine();
                    break;
                }
                break;
            }
        }
    }
    
    public void addChar(final char b) {
        this.addChar(b, Attribute._defaultat);
    }
    
    public void right() {
        if (this.col < this.maxcol) {
            ++this.col;
        }
    }
    
    public Dimension preferredSize() {
        return this.mySize;
    }
    
    private int normalise(final int r, final int c) {
        final int total;
        if ((total = this.current + r) >= this.buffsize) {
            return (total - this.buffsize) * this.maxcol + c;
        }
        return total * this.maxcol + c;
    }
    
    public void cleol() {
        this.cleol(this.row);
    }
    
    public void setPreferredCol(final int pc) {
        if (pc == this.pcol) {
            return;
        }
        this.pcol = pc;
    }
    
    public void cleol(final int i) {
        for (int c = this.col; c < this.maxcol; ++c) {
            this.buffer[this.normalise(i, c)].set(' ', Attribute._defaultat);
        }
    }
    
    public void fixFont(final boolean fix) {
        this.fixedfont = fix;
    }
    
    public void addString(final String b, final Attribute a) {
        final char[] bu = b.toCharArray();
        for (int i = 0; i < b.length(); ++i) {
            this.addChar(bu[i], a);
        }
        this.checkrepaint();
    }
    
    public void addString(final String b) {
        this.addString(b, Attribute._defaultat);
    }
    
    public String bufftoString() {
        final StringBuffer ret = new StringBuffer(this.buffsize);
        for (int i = this.current * this.maxcol; i < this.buffsize; ++i) {
            ret.append(this.buffer[i].getChar());
            if (i % this.maxcol == 0) {
                ret.append("\n");
            }
        }
        for (int i = 0; i < this.current * this.maxcol; ++i) {
            ret.append(this.buffer[i].getChar());
        }
        return ret.toString();
    }
    
    public void reshape(final int x, final int y, final int w, final int h) {
        super.reshape(x, y, w, h);
        final String fontName = this.CharFont.getName();
        System.out.println("Font name is " + fontName);
        final FontMetrics fm = this.getFontMetrics(this.CharFont);
        final int charWidth = fm.charWidth('@');
        final int charHeight = fm.getMaxAscent() + fm.getMaxDescent() + fm.getLeading();
        System.out.println("In resize with fixed font");
        if (charWidth * this.maxcol > w) {
            this.dispCol = w / charWidth;
            if (this.dispCol > this.maxcol) {
                this.dispCol = this.maxcol;
            }
            System.out.println("About to add hscroll, dispCol set to " + this.dispCol);
        }
        int mr = h / charHeight;
        if (mr > this.buffsize) {
            mr = this.buffsize;
        }
        this.maxrow = mr;
        if (this.row >= this.maxrow) {
            final int diff = this.row - this.maxrow + 1;
            this.current += diff;
            if (this.current >= this.buffsize) {
                this.current -= this.buffsize;
            }
            this.displayfrom += diff;
            if (this.displayfrom >= this.buffsize) {
                this.displayfrom -= this.buffsize;
            }
            this.row = this.maxrow - 1;
            this.maxscroll = this.buffsize - this.maxrow;
            int sv = this.scroll.getValue();
            if (sv > this.maxscroll) {
                sv = this.maxscroll;
            }
            this.scroll.setValues(sv, (100 + this.maxrow) / this.maxscroll, 1, this.maxscroll);
            this.lastscroll = sv;
        }
    }
    
    void set(final int row, final int col, final int current, final int displayfrom) {
        this.row = row;
        this.col = col;
        this.current = current;
        this.displayfrom = displayfrom;
    }
    
    public ScrolledDisplay(final int mcol, final int mrow, final int buff) {
        this.scroll = new Scrollbar();
        this.row = 0;
        this.col = 0;
        this.hscroll = false;
        this.lastcolscroll = 1;
        this.dorepaint = false;
        this.wrap = true;
        this.cursor = true;
        this.fontsize = 12;
        this.fixedfont = false;
        this.fontType = new String("Courier");
        try {
            this.create(mcol, mrow, buff);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void create(final int mcol, final int mrow, int buff) {
        this.CharFont = new Font(this.fontType, 0, this.fontsize);
        if (buff <= mrow) {
            buff = mrow + 1;
        }
        final int tsize = buff * mcol;
        this.buffer = new DispChar[tsize];
        this.current = 0;
        this.displayfrom = 0;
        this.row = 0;
        this.col = 0;
        this.maxcol = mcol;
        this.maxrow = mrow;
        this.pcol = mcol;
        this.prow = mrow;
        this.dispCol = mcol;
        this.hscroll = false;
        this.buffsize = buff;
        for (int i = 0; i < tsize; ++i) {
            this.buffer[i] = new DispChar();
        }
        this.maxscroll = this.buffsize - this.maxrow;
        this.scroll.setValues(this.maxscroll, this.barsize = 100 * this.maxrow / this.maxscroll, 1, this.maxscroll);
        this.lastscroll = this.maxscroll;
    }
    
    public void addNotify() {
        super.addNotify();
    }
    
    public void paint(final Graphics g) {
        this.paint(g, 0);
    }
    
    public void paint(final Graphics g, final int srow) {
        final char[] draw = new char[this.maxcol];
        final Attribute c_att = new Attribute(Attribute._defaultat);
        final Dimension d = this.size();
        c_att.set(this.getDisp(0, 0).getAttribute());
        g.setFont(this.CharFont);
        final FontMetrics fm = g.getFontMetrics();
        final int bl = fm.getAscent();
        final int fh = fm.getMaxAscent() + fm.getMaxDescent() + fm.getLeading();
        final int fw = fm.charWidth('@');
        final int activew = this.dispCol * fw;
        final int activeh = this.maxrow * fh;
        final int overlap = d.height - activeh;
        final int starth = overlap / 2;
        g.setColor(Attribute._defaultbg);
        if (srow == 0) {
            g.fillRect(0, 0, 0, d.height);
            g.fillRect(0, d.height - (overlap + 1) / 2, d.width, (overlap + 1) / 2);
            g.fillRect(0, 0, d.width, starth);
            g.fillRect(activew, starth, d.width - activew, activeh);
        }
        final int startcol = 0;
        for (int ro = srow; ro < this.maxrow; ++ro) {
            int no_in = 0;
            int co;
            for (co = 0; co < this.dispCol; ++co) {
                final DispChar curr = this.getDisp(ro, co + startcol);
                if (curr.getAttribute().eq(c_att)) {
                    draw[no_in++] = curr.getChar();
                }
                else {
                    if (no_in > 0) {
                        g.setColor(c_att.getBg());
                        g.fillRect((co - no_in) * fw, starth + ro * fh, no_in * fw, fh);
                        g.setColor(c_att.getFg());
                        g.drawChars(draw, 0, no_in, (co - no_in) * fw, starth + bl + ro * fh);
                        no_in = 0;
                    }
                    draw[no_in++] = curr.getChar();
                    c_att.set(curr.getAttribute());
                }
            }
            if (no_in > 0) {
                g.setColor(c_att.getBg());
                g.fillRect((co - no_in) * fw, starth + ro * fh, no_in * fw, fh);
                g.setColor(c_att.getFg());
                g.drawChars(draw, 0, no_in, (co - no_in) * fw, starth + bl + ro * fh);
            }
        }
        if (this.cursor && this.col >= startcol && this.col - startcol <= this.dispCol) {
            final int ccol = this.col - startcol;
            int adjc;
            if (this.displayfrom > this.current) {
                adjc = this.current + this.buffsize;
            }
            else {
                adjc = this.current;
            }
            if (this.displayfrom + this.maxrow >= adjc + this.row) {
                final int cx = ccol * fw;
                final int cy = starth + (adjc + this.row - this.displayfrom) * fh;
                g.fillRect(cx, cy + fh - 3, fw, 3);
            }
        }
        g.finalize();
    }
    
    public void setFontType(final String nf) {
        this.fontType = nf;
        this.CharFont = new Font(this.fontType, 0, this.fontsize);
    }
    
    public void setFontSize(final int sf) {
        if (this.fontsize == sf) {
            return;
        }
        if (sf == 0) {
            this.fontsize = 12;
        }
        else {
            this.fontsize = sf;
        }
    }
    
    public Dimension minimumSize() {
        return this.mySize;
    }
    
    public void left() {
        if (this.col > 0) {
            --this.col;
        }
    }
    
    public void setMySize() {
        final Graphics g = this.getGraphics();
        if (g == null) {
            this.mySize = new Dimension(300, 200);
            System.out.println("Set my size, G returned null");
            return;
        }
        g.setFont(this.CharFont = new Font(this.fontType, 0, this.fontsize));
        g.setColor(this.getBackground());
        final FontMetrics font = g.getFontMetrics();
        final int fh = font.getMaxAscent() + font.getMaxDescent() + font.getLeading();
        final int fw = font.charWidth('@');
        this.mySize = new Dimension(this.pcol * fw, this.prow * fh);
        System.out.println("SD setMySize: maxcol " + this.maxcol + ", pcol: " + this.pcol + " fontsize " + this.fontsize + "maxrow " + this.maxrow + " mysize " + this.mySize);
        System.out.println("setMySize reporst font width of " + fw + "and height of " + fh);
    }
    
    public void setMySize(final int c, final int r, final int f) {
        this.setPreferredCol(c);
        this.newrow(r);
        this.setFontSize(f);
        this.setMySize();
    }
    
    public void setMySize(final int c, final int r, final int f, final boolean fix) {
        this.fixFont(fix);
        this.setMySize(c, r, f);
    }
    
    public DispChar getDisp(final int r, final int c) {
        return this.buffer[this.normaliseDisplay(r, c)];
    }
    
    public void up() {
        if (this.row > 0) {
            --this.row;
        }
    }
    
    private int normaliseDisplay(final int r, final int c) {
        final int total;
        if ((total = this.displayfrom + r) >= this.buffsize) {
            return (total - this.buffsize) * this.maxcol + c;
        }
        return total * this.maxcol + c;
    }
    
    public void init() {
        System.out.println("Init with pcol " + this.pcol + " and maxcol: " + this.maxcol);
        this.setMySize();
    }
    
    public int getRow() {
        return this.row;
    }
    
    public void newrow(final int mrow) {
        if (mrow == this.maxrow) {
            return;
        }
        this.maxrow = mrow;
        this.prow = mrow;
        final int oldrow = this.row;
        this.setMySize();
        if (oldrow >= mrow) {
            this.current += oldrow - mrow + 1;
            if (this.current > this.buffsize - 1) {
                this.current -= this.buffsize;
            }
            this.row = mrow - 1;
        }
        this.displayfrom = this.current;
        this.maxscroll = this.buffsize - this.maxrow;
        this.lastscroll = this.maxscroll;
        this.scroll.setValues(this.lastscroll, 100 * this.maxrow / this.maxscroll, 1, this.maxscroll);
        this.resize(this.mySize);
    }
    
    public boolean handleEvent(final Event e) {
        if (e.target == this.scroll) {
            int x = this.scroll.getValue();
            if (x != this.lastscroll) {
                if ((this.lastscroll = x) >= this.maxscroll - this.barsize) {
                    this.displayfrom = this.current;
                }
                else {
                    x = this.current - (this.maxscroll - x);
                    if (x < 0) {
                        x += this.buffsize;
                    }
                    this.displayfrom = x;
                }
                this.repaint();
            }
            return true;
        }
        return super.handleEvent(e);
    }
    
    public void cls() {
        this.current = this.normalise(this.row + 1, 0) / this.maxcol;
        this.displayfrom = this.current;
        this.row = 0;
        this.col = 0;
        for (int i = 0; i < this.maxrow; ++i) {
            this.cleol(i);
        }
    }
    
    public void position(final int x, final int y) {
        if (x < 0) {
            this.col = 0;
        }
        else if (x <= this.maxcol) {
            this.col = x;
        }
        else {
            this.col = this.maxcol;
        }
        if (y < 0) {
            this.row = 0;
        }
        else if (y <= this.maxrow) {
            this.row = y;
        }
        else {
            this.row = this.maxrow;
        }
        this.repaint();
    }
}
