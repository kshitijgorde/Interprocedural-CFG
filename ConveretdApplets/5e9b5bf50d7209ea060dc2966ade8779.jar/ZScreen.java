import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.util.NoSuchElementException;
import java.awt.Toolkit;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class ZScreen extends Canvas
{
    int lines;
    int chars;
    Font fixedfont;
    FontMetrics fixedmetrics;
    Font variablefont;
    Font graphicsfont;
    SyncVector inputcodes;
    Vector bufferedcodes;
    boolean bufferdone;
    ZWindow inputwindow;
    ZCursor inputcursor;
    int zforeground;
    int zbackground;
    Image backing_store;
    Graphics g_store;
    Color zbcolor;
    boolean hasscrolled;
    static final char[] accent_table;
    
    public ZScreen() {
        this.zforeground = 2;
        this.zbackground = 9;
        this.hasscrolled = false;
        final Dimension mysize = this.size();
        this.fixedfont = new Font("Courier", 0, 12);
        this.fixedmetrics = this.getFontMetrics(this.fixedfont);
        this.chars = mysize.width / this.fixedmetrics.charWidth(' ');
        this.lines = mysize.height / this.fixedmetrics.getHeight();
        this.inputcodes = new SyncVector();
        this.bufferedcodes = new Vector();
        this.inputcursor = new ZCursor(this);
        this.setForeground(ZColor.getcolor(this.zforeground));
        this.setBackground(ZColor.getcolor(this.zbackground));
    }
    
    protected boolean isterminator(final int key) {
        return key == 10 || key == 13;
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        this.requestFocus();
        return true;
    }
    
    static char zascii_to_unicode(final short zascii) {
        if (zascii >= 32 && zascii <= 126) {
            return (char)zascii;
        }
        if (zascii >= 155 && zascii <= 251) {
            if (zascii - 155 < ZScreen.accent_table.length) {
                return ZScreen.accent_table[zascii - 155];
            }
            return '?';
        }
        else {
            if (zascii == 0 || zascii >= 256) {
                return '?';
            }
            System.err.println("Illegal character code: " + zascii);
            return '?';
        }
    }
    
    static short unicode_to_zascii(final char unicode) throws NoSuchKeyException {
        if (unicode == '\n') {
            return 13;
        }
        if (unicode == '\b') {
            return 127;
        }
        if (unicode < ' ' && unicode != '\r' && unicode != '\u001b') {
            throw new NoSuchKeyException("Illegal character input: " + (short)unicode);
        }
        if (unicode < '\u0080') {
            return (short)unicode;
        }
        for (short i = 0; i < ZScreen.accent_table.length; ++i) {
            if (ZScreen.accent_table[i] == unicode) {
                return (short)(155 + i);
            }
        }
        throw new NoSuchKeyException("Illegal character input: " + (short)unicode);
    }
    
    static short fkey_to_zascii(final int fkey) throws NoSuchKeyException {
        switch (fkey) {
            case 1004: {
                return 129;
            }
            case 1005: {
                return 130;
            }
            case 1006: {
                return 131;
            }
            case 1007: {
                return 132;
            }
            case 1008: {
                return 133;
            }
            case 1009: {
                return 134;
            }
            case 1010: {
                return 135;
            }
            case 1011: {
                return 136;
            }
            case 1012: {
                return 137;
            }
            case 1013: {
                return 138;
            }
            case 1014: {
                return 139;
            }
            case 1015: {
                return 140;
            }
            case 1016: {
                return 141;
            }
            case 1017: {
                return 142;
            }
            case 1018: {
                return 143;
            }
            case 1019: {
                return 144;
            }
            default: {
                throw new NoSuchKeyException("Illegal function key " + fkey);
            }
        }
    }
    
    public boolean keyDown(final Event e, final int key) {
        try {
            short code;
            if (e.id == 401) {
                code = unicode_to_zascii((char)key);
            }
            else {
                code = fkey_to_zascii(key);
            }
            this.inputcodes.syncAddElement(new Integer(code));
        }
        catch (NoSuchKeyException excpt) {
            System.err.println(excpt);
        }
        return true;
    }
    
    public void set_input_window(final ZWindow thewindow) {
        this.inputwindow = thewindow;
    }
    
    public short read_code() {
        Integer thecode;
        for (thecode = null; thecode == null; thecode = (Integer)this.inputcodes.syncPopFirstElement()) {}
        return (short)(int)thecode;
    }
    
    public short read_buffered_code() {
        this.inputwindow.flush();
        final int cw = this.fixedmetrics.charWidth(' ');
        final int ch = this.fixedmetrics.getHeight();
        this.inputcursor.setcolors(this.getForeground(), this.zbcolor);
        this.inputcursor.size(cw, ch);
        while (!this.bufferdone) {
            this.inputwindow.flush();
            this.inputcursor.move((this.inputwindow.getLeft() + this.inputwindow.cursorx) * cw, (this.inputwindow.getTop() + this.inputwindow.cursory) * ch);
            this.inputcursor.show();
            Toolkit.getDefaultToolkit().sync();
            final int incode = this.read_code();
            this.inputcursor.hide();
            if (incode != 8) {
                if (incode != 127) {
                    if (this.isterminator(incode)) {
                        this.bufferdone = true;
                        if (incode == 10 || incode == 13) {
                            this.inputwindow.newline();
                        }
                    }
                    else {
                        this.inputwindow.printzascii((short)incode);
                        this.inputwindow.flush();
                    }
                    this.bufferedcodes.addElement(new Integer(incode));
                    continue;
                }
            }
            try {
                final Integer thecode = this.bufferedcodes.lastElement();
                this.bufferedcodes.removeElementAt(this.bufferedcodes.size() - 1);
                this.inputwindow.flush();
                this.inputwindow.movecursor(this.inputwindow.cursorx - 1, this.inputwindow.cursory);
                this.inputwindow.printzascii((short)32);
                this.inputwindow.flush();
                this.inputwindow.movecursor(this.inputwindow.cursorx - 1, this.inputwindow.cursory);
            }
            catch (NoSuchElementException ex) {}
        }
        final Integer thecode = this.bufferedcodes.firstElement();
        this.bufferedcodes.removeElementAt(0);
        if (this.bufferedcodes.isEmpty()) {
            this.bufferdone = false;
        }
        return (short)(int)thecode;
    }
    
    public int getlines() {
        return this.lines;
    }
    
    public int getchars() {
        return this.chars;
    }
    
    public synchronized void reshape(final int x, final int y, final int width, final int height) {
        if (width >= 0 && height >= 0) {
            final int lines = height / this.fixedmetrics.getHeight();
            this.chars = width / this.fixedmetrics.charWidth(' ');
            this.backing_store = this.createImage(width, height);
            (this.g_store = this.backing_store.getGraphics()).setColor(this.zbcolor);
            this.g_store.fillRect(0, 0, width, height);
            this.lines = lines;
        }
        super.reshape(x, y, width, height);
    }
    
    public int charwidth() {
        return this.fixedmetrics.charWidth(' ');
    }
    
    public synchronized void settext(final int y, final int x, final char[] newtext, final int offset, final int length) {
        this.settext(y, x, newtext, offset, length, false, this.fixedfont);
    }
    
    public synchronized void settext(final int y, final int x, final char[] newtext, final int offset, final int length, final boolean reverse, final Font textfont) {
        try {
            this.g_store.setFont(textfont);
            this.drawtext(this.g_store, y, x, newtext, offset, length, reverse);
            if (!this.hasscrolled) {
                final Graphics g = this.getGraphics();
                g.setFont(textfont);
                this.drawtext(g, y, x, newtext, offset, length, reverse);
            }
        }
        catch (NullPointerException ex) {
            System.err.println("No graphics in settext");
        }
    }
    
    protected synchronized void drawtext(final Graphics g, final int y, final int x, final char[] newtext, final int offset, final int length, final boolean reverse) {
        final int tw = length * this.fixedmetrics.charWidth(' ');
        final int th = this.fixedmetrics.getHeight();
        final int tx = x * this.fixedmetrics.charWidth(' ');
        final int ty = th * (y + 1) - this.fixedmetrics.getDescent();
        if (reverse) {
            g.setColor(this.getForeground());
            g.fillRect(tx, th * y, tw, th);
            g.setColor(this.zbcolor);
        }
        else {
            g.setColor(this.zbcolor);
            g.fillRect(tx, th * y, tw, th);
            g.setColor(this.getForeground());
        }
        g.drawChars(newtext, offset, length, tx, ty);
        g.setColor(this.getForeground());
    }
    
    public synchronized void scrollLines(final int top, final int height, final int lines) {
        try {
            final int texttop = top * this.fixedmetrics.getHeight();
            this.g_store.copyArea(0, texttop + lines * this.fixedmetrics.getHeight(), this.size().width, (height - lines) * this.fixedmetrics.getHeight(), 0, -lines * this.fixedmetrics.getHeight());
            this.g_store.setColor(this.zbcolor);
            this.g_store.fillRect(0, texttop + (height - 1) * this.fixedmetrics.getHeight(), this.size().width, this.fixedmetrics.getHeight());
        }
        catch (NullPointerException ex) {
            System.err.println("No graphics in scrollLines");
        }
        this.repaint();
        this.hasscrolled = true;
    }
    
    public synchronized void paint(final Graphics g) {
        g.drawImage(this.backing_store, 0, 0, null);
        this.inputcursor.redraw(g);
        this.hasscrolled = false;
    }
    
    public void update(final Graphics g) {
        g.setColor(this.getForeground());
        this.paint(g);
    }
    
    public void clear() {
        final Dimension mysize = this.size();
        try {
            this.g_store.setColor(this.zbcolor);
            this.g_store.fillRect(0, 0, mysize.width, mysize.height);
        }
        catch (NullPointerException ex) {
            System.err.println("No graphics in clear");
        }
        this.repaint();
    }
    
    public int getZForeground() {
        return this.zforeground;
    }
    
    public int getZBackground() {
        return this.zbackground;
    }
    
    public void setZForeground(final int zcolor) {
        this.zforeground = zcolor;
        this.setForeground(ZColor.getcolor(zcolor));
    }
    
    public void setZBackground(final int zcolor) {
        this.zbackground = zcolor;
        this.zbcolor = ZColor.getcolor(zcolor);
    }
    
    public Frame getFrame() {
        Component cursor;
        for (cursor = this; !(cursor instanceof Frame); cursor = cursor.getParent()) {}
        return (Frame)cursor;
    }
    
    public Dimension minimumSize() {
        return new Dimension(100, 100);
    }
    
    public Dimension preferredSize() {
        return new Dimension(500, 500);
    }
    
    static {
        accent_table = new char[] { '\u00e4', '\u00f6', '\u00fc', '\u00c4', '\u00d6', '\u00dc', '\u00df', '»', '«', '\u00eb', '\u00ef', '\u00ff', '\u00cb', '\u00cf', '\u00e1', '\u00e9', '\u00ed', '\u00f3', '\u00fa', '\u00fd', '\u00c1', '\u00c9', '\u00cd', '\u00d3', '\u00da', '\u00dd', '\u00e0', '\u00e8', '\u00ec', '\u00f2', '\u00f9', '\u00c0', '\u00c8', '\u00cc', '\u00d2', '\u00d9', '\u00e2', '\u00ea', '\u00ee', '\u00f4', '\u00fb', '\u00c2', '\u00ca', '\u00ce', '\u00d4', '\u00da', '\u00e5', '\u00c5', '\u00f8', '\u00d8', '\u00e3', '\u00f1', '\u00f5', '\u00c3', '\u00d1', '\u00d5', '\u00e6', '\u00c6', '\u00e7', '\u00c7', '\u00fe', '\u00f0', '\u00de', '\u00d0', '£', '\u0153', '\u0152', '¡', '¿' };
    }
}
