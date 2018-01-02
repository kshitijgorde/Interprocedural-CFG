// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.screenmodel;

import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.util.NoSuchElementException;
import java.awt.Toolkit;
import java.awt.Event;
import java.awt.Dimension;
import russotto.zplet.ZColor;
import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Canvas;

public class ZScreen extends Canvas
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
        final Dimension size = this.size();
        this.fixedfont = new Font("Courier", 0, 12);
        this.fixedmetrics = this.getFontMetrics(this.fixedfont);
        this.chars = size.width / this.fixedmetrics.charWidth(' ');
        this.lines = size.height / this.fixedmetrics.getHeight();
        this.inputcodes = new SyncVector();
        this.bufferedcodes = new Vector();
        this.inputcursor = new ZCursor(this);
        this.setForeground(ZColor.getcolor(this.zforeground));
        this.setBackground(ZColor.getcolor(this.zbackground));
    }
    
    protected boolean isterminator(final int n) {
        return n == 10 || n == 13;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.requestFocus();
        return true;
    }
    
    static char zascii_to_unicode(final short n) {
        if (n >= 32 && n <= 126) {
            return (char)n;
        }
        if (n >= 155 && n <= 251) {
            if (n - 155 < ZScreen.accent_table.length) {
                return ZScreen.accent_table[n - 155];
            }
            return '?';
        }
        else {
            if (n == 0 || n >= 256) {
                return '?';
            }
            System.err.println("Illegal character code: " + n);
            return '?';
        }
    }
    
    static short unicode_to_zascii(final char c) throws NoSuchKeyException {
        if (c == '\n') {
            return 13;
        }
        if (c == '\b') {
            return 127;
        }
        if (c < ' ' && c != '\r' && c != '\u001b') {
            throw new NoSuchKeyException("Illegal character input: " + (short)c);
        }
        if (c < '\u0080') {
            return (short)c;
        }
        for (int i = 0; i < ZScreen.accent_table.length; i = (short)(i + 1)) {
            if (ZScreen.accent_table[i] == c) {
                return (short)(155 + i);
            }
        }
        throw new NoSuchKeyException("Illegal character input: " + (short)c);
    }
    
    static short fkey_to_zascii(final int n) throws NoSuchKeyException {
        switch (n) {
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
                throw new NoSuchKeyException("Illegal function key " + n);
            }
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        try {
            short n2;
            if (event.id == 401) {
                n2 = unicode_to_zascii((char)n);
            }
            else {
                n2 = fkey_to_zascii(n);
            }
            this.inputcodes.syncAddElement(new Integer(n2));
        }
        catch (NoSuchKeyException ex) {
            System.err.println(ex);
        }
        return true;
    }
    
    public void set_input_window(final ZWindow inputwindow) {
        this.inputwindow = inputwindow;
    }
    
    public short read_code() {
        Integer n;
        for (n = null; n == null; n = (Integer)this.inputcodes.syncPopFirstElement()) {}
        return (short)(int)n;
    }
    
    public short read_buffered_code() {
        this.inputwindow.flush();
        final int charWidth = this.fixedmetrics.charWidth(' ');
        final int height = this.fixedmetrics.getHeight();
        this.inputcursor.setcolors(this.getForeground(), this.zbcolor);
        this.inputcursor.size(charWidth, height);
        while (!this.bufferdone) {
            this.inputwindow.flush();
            this.inputcursor.move((this.inputwindow.getLeft() + this.inputwindow.cursorx) * charWidth, (this.inputwindow.getTop() + this.inputwindow.cursory) * height);
            this.inputcursor.show();
            Toolkit.getDefaultToolkit().sync();
            final short read_code = this.read_code();
            this.inputcursor.hide();
            if (read_code != 8) {
                if (read_code != 127) {
                    if (this.isterminator(read_code)) {
                        this.bufferdone = true;
                        if (read_code == 10 || read_code == 13) {
                            this.inputwindow.newline();
                        }
                    }
                    else {
                        this.inputwindow.printzascii(read_code);
                        this.inputwindow.flush();
                    }
                    this.bufferedcodes.addElement(new Integer(read_code));
                    continue;
                }
            }
            try {
                final Integer n = this.bufferedcodes.lastElement();
                this.bufferedcodes.removeElementAt(this.bufferedcodes.size() - 1);
                this.inputwindow.flush();
                this.inputwindow.movecursor(this.inputwindow.cursorx - 1, this.inputwindow.cursory);
                this.inputwindow.printzascii((short)32);
                this.inputwindow.flush();
                this.inputwindow.movecursor(this.inputwindow.cursorx - 1, this.inputwindow.cursory);
            }
            catch (NoSuchElementException ex) {}
        }
        final Integer n2 = this.bufferedcodes.firstElement();
        this.bufferedcodes.removeElementAt(0);
        if (this.bufferedcodes.isEmpty()) {
            this.bufferdone = false;
        }
        return (short)(int)n2;
    }
    
    public int getlines() {
        return this.lines;
    }
    
    public int getchars() {
        return this.chars;
    }
    
    public synchronized void reshape(final int n, final int n2, final int n3, final int n4) {
        if (n3 >= 0 && n4 >= 0) {
            final int lines = n4 / this.fixedmetrics.getHeight();
            this.chars = n3 / this.fixedmetrics.charWidth(' ');
            this.backing_store = this.createImage(n3, n4);
            (this.g_store = this.backing_store.getGraphics()).setColor(this.zbcolor);
            this.g_store.fillRect(0, 0, n3, n4);
            this.lines = lines;
        }
        super.reshape(n, n2, n3, n4);
    }
    
    public int charwidth() {
        return this.fixedmetrics.charWidth(' ');
    }
    
    public synchronized void settext(final int n, final int n2, final char[] array, final int n3, final int n4) {
        this.settext(n, n2, array, n3, n4, false, this.fixedfont);
    }
    
    public synchronized void settext(final int n, final int n2, final char[] array, final int n3, final int n4, final boolean b, final Font font) {
        try {
            this.g_store.setFont(font);
            this.drawtext(this.g_store, n, n2, array, n3, n4, b);
            if (!this.hasscrolled) {
                final Graphics graphics = this.getGraphics();
                graphics.setFont(font);
                this.drawtext(graphics, n, n2, array, n3, n4, b);
            }
        }
        catch (NullPointerException ex) {
            System.err.println("No graphics in settext");
        }
    }
    
    protected synchronized void drawtext(final Graphics graphics, final int n, final int n2, final char[] array, final int n3, final int n4, final boolean b) {
        final int n5 = n4 * this.fixedmetrics.charWidth(' ');
        final int height = this.fixedmetrics.getHeight();
        final int n6 = n2 * this.fixedmetrics.charWidth(' ');
        final int n7 = height * (n + 1) - this.fixedmetrics.getDescent();
        if (b) {
            graphics.setColor(this.getForeground());
            graphics.fillRect(n6, height * n, n5, height);
            graphics.setColor(this.zbcolor);
        }
        else {
            graphics.setColor(this.zbcolor);
            graphics.fillRect(n6, height * n, n5, height);
            graphics.setColor(this.getForeground());
        }
        graphics.drawChars(array, n3, n4, n6, n7);
        graphics.setColor(this.getForeground());
    }
    
    public synchronized void scrollLines(final int n, final int n2, final int n3) {
        try {
            final int n4 = n * this.fixedmetrics.getHeight();
            this.g_store.copyArea(0, n4 + n3 * this.fixedmetrics.getHeight(), this.size().width, (n2 - n3) * this.fixedmetrics.getHeight(), 0, -n3 * this.fixedmetrics.getHeight());
            this.g_store.setColor(this.zbcolor);
            this.g_store.fillRect(0, n4 + (n2 - 1) * this.fixedmetrics.getHeight(), this.size().width, this.fixedmetrics.getHeight());
        }
        catch (NullPointerException ex) {
            System.err.println("No graphics in scrollLines");
        }
        this.repaint();
        this.hasscrolled = true;
    }
    
    public synchronized void paint(final Graphics graphics) {
        graphics.drawImage(this.backing_store, 0, 0, null);
        this.inputcursor.redraw(graphics);
        this.hasscrolled = false;
    }
    
    public void update(final Graphics graphics) {
        graphics.setColor(this.getForeground());
        this.paint(graphics);
    }
    
    public void clear() {
        final Dimension size = this.size();
        try {
            this.g_store.setColor(this.zbcolor);
            this.g_store.fillRect(0, 0, size.width, size.height);
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
    
    public void setZForeground(final int zforeground) {
        this.zforeground = zforeground;
        this.setForeground(ZColor.getcolor(zforeground));
    }
    
    public void setZBackground(final int zbackground) {
        this.zbackground = zbackground;
        this.zbcolor = ZColor.getcolor(zbackground);
    }
    
    public Frame getFrame() {
        Component parent;
        for (parent = this; !(parent instanceof Frame); parent = parent.getParent()) {}
        return (Frame)parent;
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
