// 
// Decompiled by Procyon v0.5.30
// 

package display;

import java.awt.Graphics;
import java.awt.Label;
import java.awt.Component;
import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Panel;

public class CharDisplay extends Panel
{
    public String version;
    public static final int debug = 0;
    private Dimension size;
    private Insets insets;
    private boolean raised;
    private char[][] charArray;
    private int[][] charAttributes;
    private int bufSize;
    private int maxBufSize;
    private int windowBase;
    private int screenBase;
    private int topMargin;
    private int bottomMargin;
    private Scrollbar scrollBar;
    private String scrBarPos;
    private Font normalFont;
    private FontMetrics fm;
    private int charWidth;
    private int charHeight;
    private int charDescent;
    private int resizeStrategy;
    private int cursorX;
    private int cursorY;
    private Point selectBegin;
    private Point selectEnd;
    private TextArea selection;
    private Frame selectFrame;
    private SoftFont sf;
    private boolean screenLocked;
    private boolean[] update;
    private Color[] color;
    private static final int COLOR_FG_STD = 7;
    private static final int COLOR_FG_BOLD = 3;
    private static final int COLOR_BG_STD = 0;
    private static final int COLOR = 2040;
    private static final int COLOR_FG = 120;
    private static final int COLOR_BG = 1920;
    public static final boolean SCROLL_UP = false;
    public static final boolean SCROLL_DOWN = true;
    public static final int RESIZE_NONE = 0;
    public static final int RESIZE_SCREEN = 1;
    public static final int RESIZE_FONT = 2;
    public static final int NORMAL = 0;
    public static final int BOLD = 1;
    public static final int UNDERLINE = 2;
    public static final int INVERT = 4;
    
    public CharDisplay() {
        this.version = "$Revision: 1.29 $ $Date: 1999/03/20 17:02:34 $";
        this.sf = new SoftFont();
        this.screenLocked = false;
        this.color = new Color[] { this.notbold(Color.black), this.notbold(Color.red), this.notbold(Color.green), this.notbold(Color.yellow), this.notbold(Color.blue), this.notbold(Color.magenta), this.notbold(Color.cyan), this.notbold(Color.white) };
        this.InitializeCharDisplay(80, 24, "Courier", 12);
    }
    
    public CharDisplay(final int width, final int height) {
        this.version = "$Revision: 1.29 $ $Date: 1999/03/20 17:02:34 $";
        this.sf = new SoftFont();
        this.screenLocked = false;
        this.color = new Color[] { this.notbold(Color.black), this.notbold(Color.red), this.notbold(Color.green), this.notbold(Color.yellow), this.notbold(Color.blue), this.notbold(Color.magenta), this.notbold(Color.cyan), this.notbold(Color.white) };
        this.InitializeCharDisplay(width, height, "Courier", 12);
    }
    
    public CharDisplay(final int width, final int height, final String fname, final int fsize) {
        this.version = "$Revision: 1.29 $ $Date: 1999/03/20 17:02:34 $";
        this.sf = new SoftFont();
        this.screenLocked = false;
        this.color = new Color[] { this.notbold(Color.black), this.notbold(Color.red), this.notbold(Color.green), this.notbold(Color.yellow), this.notbold(Color.blue), this.notbold(Color.magenta), this.notbold(Color.cyan), this.notbold(Color.white) };
        this.InitializeCharDisplay(width, height, fname, fsize);
    }
    
    public CharDisplay(final String fname, final int fsize) {
        this.version = "$Revision: 1.29 $ $Date: 1999/03/20 17:02:34 $";
        this.sf = new SoftFont();
        this.screenLocked = false;
        this.color = new Color[] { this.notbold(Color.black), this.notbold(Color.red), this.notbold(Color.green), this.notbold(Color.yellow), this.notbold(Color.blue), this.notbold(Color.magenta), this.notbold(Color.cyan), this.notbold(Color.white) };
        this.InitializeCharDisplay(80, 24, fname, fsize);
    }
    
    private void InitializeCharDisplay(final int width, final int height, final String fontname, final int fsize) {
        System.err.println("CharDisplay: screen size: [" + width + "," + height + "]");
        this.setFont(this.normalFont = new Font(fontname, 1, fsize));
        this.fm = this.getFontMetrics(this.normalFont);
        if (this.fm != null) {
            this.charWidth = this.fm.charWidth('@');
            this.charHeight = this.fm.getHeight();
            this.charDescent = this.fm.getDescent();
        }
        this.resizeStrategy = 2;
        this.size = new Dimension(width, height);
        this.charArray = new char[this.size.height][this.size.width];
        this.charAttributes = new int[this.size.height][this.size.width];
        this.bufSize = this.size.height;
        this.maxBufSize = 2 * this.size.height;
        this.windowBase = 0;
        this.screenBase = 0;
        this.topMargin = 0;
        this.bottomMargin = this.size.height - 1;
        this.update = new boolean[this.size.height + 1];
        for (int i = 1; i <= this.size.height; ++i) {
            this.update[i] = true;
        }
        this.selectBegin = new Point(0, 0);
        this.selectEnd = new Point(0, 0);
        this.setLayout(null);
    }
    
    private int checkBounds(final int value, final int lower, final int upper) {
        if (value < lower) {
            return lower;
        }
        if (value > upper) {
            return upper;
        }
        return value;
    }
    
    public void deleteArea(int c, int l, final int w, final int h) {
        c = this.checkBounds(c, 0, this.size.width - 1);
        l = this.checkBounds(l, 0, this.size.height - 1);
        final char[] cbuf = new char[w];
        final int[] abuf = new int[w];
        for (int i = 0; i < h && l + i < this.size.height; ++i) {
            System.arraycopy(cbuf, 0, this.charArray[this.screenBase + l + i], c, w);
            System.arraycopy(abuf, 0, this.charAttributes[this.screenBase + l + i], c, w);
        }
        this.markLine(l, h);
    }
    
    public void deleteChar(int c, int l) {
        c = this.checkBounds(c, 0, this.size.width - 1);
        l = this.checkBounds(l, 0, this.size.height - 1);
        if (c < this.size.width - 1) {
            System.arraycopy(this.charArray[this.screenBase + l], c + 1, this.charArray[this.screenBase + l], c, this.size.width - c - 1);
            System.arraycopy(this.charAttributes[this.screenBase + l], c + 1, this.charAttributes[this.screenBase + l], c, this.size.width - c - 1);
        }
        this.putChar(this.size.width - 1, l, '\0');
    }
    
    public void deleteLine(int l) {
        l = this.checkBounds(l, 0, this.size.height - 1);
        final int bottom = (l > this.bottomMargin) ? (this.size.height - 1) : ((l < this.topMargin) ? this.topMargin : (this.bottomMargin + 1));
        System.arraycopy(this.charArray, this.screenBase + l + 1, this.charArray, this.screenBase + l, bottom - l - 1);
        System.arraycopy(this.charAttributes, this.screenBase + l + 1, this.charAttributes, this.screenBase + l, bottom - l - 1);
        this.charArray[this.screenBase + bottom - 1] = new char[this.size.width];
        this.charAttributes[this.screenBase + bottom - 1] = new int[this.size.width];
        this.markLine(l, bottom - l);
    }
    
    public int getAttributes(int c, int l) {
        c = this.checkBounds(c, 0, this.size.width - 1);
        l = this.checkBounds(l, 0, this.size.height - 1);
        return this.charAttributes[l][c];
    }
    
    public int getBottomMargin() {
        return this.bottomMargin;
    }
    
    public int getBufferSize() {
        return this.bufSize;
    }
    
    public char getChar(int c, int l) {
        c = this.checkBounds(c, 0, this.size.width - 1);
        l = this.checkBounds(l, 0, this.size.height - 1);
        return this.charArray[l][c];
    }
    
    public int getColumns() {
        return this.size.width;
    }
    
    public Dimension getCursorPos() {
        return new Dimension(this.cursorX, this.cursorY);
    }
    
    public int getMaxBufferSize() {
        return this.maxBufSize;
    }
    
    public int getRows() {
        return this.size.height;
    }
    
    public int getTopMargin() {
        return this.topMargin;
    }
    
    public int getWindowBase() {
        return this.windowBase;
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt != null && evt.target == this.scrollBar && evt.arg != null) {
            final int val = (int)evt.arg;
            this.setWindowBase(val);
            return true;
        }
        if (evt.id == 501 || evt.id == 502 || evt.id == 506) {
            final int xoffset = (super.size().width - this.size.width * this.charWidth) / 2;
            final int yoffset = (super.size().height - this.size.height * this.charHeight) / 2;
            switch (evt.id) {
                case 501: {
                    this.selectBegin.x = (evt.x - xoffset) / this.charWidth;
                    this.selectBegin.y = (evt.y - yoffset) / this.charHeight + this.windowBase;
                    this.selectEnd.x = this.selectBegin.x;
                    this.selectEnd.y = this.selectBegin.y;
                    if (this.selectFrame != null) {
                        this.selectFrame.hide();
                        break;
                    }
                    break;
                }
                case 502:
                case 506: {
                    final int x = (evt.x - xoffset) / this.charWidth;
                    final int y = (evt.y - yoffset) / this.charHeight + this.windowBase;
                    final int oldx = this.selectEnd.x;
                    final int oldy = this.selectEnd.y;
                    if (x < this.selectBegin.x && y < this.selectBegin.y && x < this.selectEnd.x && y < this.selectEnd.y) {
                        this.selectBegin.x = x;
                        this.selectBegin.y = y;
                    }
                    else {
                        this.selectEnd.x = x;
                        this.selectEnd.y = y;
                    }
                    if (evt.id == 502) {
                        if (this.selectBegin.x == this.selectEnd.x && this.selectBegin.y == this.selectEnd.y) {
                            this.repaint();
                            return true;
                        }
                        String tmp = "";
                        if (this.selectEnd.x < 0) {
                            this.selectEnd.x = 0;
                        }
                        if (this.selectEnd.y < 0) {
                            this.selectEnd.y = 0;
                        }
                        if (this.selectEnd.y >= this.charArray.length) {
                            this.selectEnd.y = this.charArray.length - 1;
                        }
                        if (this.selectEnd.x >= this.charArray[0].length) {
                            this.selectEnd.x = this.charArray[0].length - 1;
                        }
                        for (int l = this.selectBegin.y; l <= this.selectEnd.y; ++l) {
                            if (l == this.selectBegin.y) {
                                tmp = String.valueOf(new String(this.charArray[l]).substring(this.selectBegin.x)) + "\n";
                            }
                            else if (l == this.selectEnd.y) {
                                tmp = String.valueOf(tmp) + new String(this.charArray[l]).substring(0, this.selectEnd.x);
                            }
                            else {
                                tmp = String.valueOf(tmp) + new String(this.charArray[l]) + "\n";
                            }
                        }
                        if (this.selectFrame == null) {
                            this.selectFrame = new Frame("Pasteboard");
                            (this.selection = new TextArea()).setFont(this.normalFont);
                            this.selectFrame.add("Center", this.selection);
                            this.selectFrame.add("South", new Label("Click on the terminal window to hide!"));
                            this.selectFrame.pack();
                        }
                        this.selection.setText(tmp);
                        this.selectFrame.show();
                        this.selection.selectAll();
                        this.repaint();
                        break;
                    }
                    else {
                        if (oldx != x || oldy != y) {
                            this.repaint();
                            break;
                        }
                        break;
                    }
                    break;
                }
            }
            return true;
        }
        return false;
    }
    
    public void insertChar(int c, int l, final char ch, final int attributes) {
        c = this.checkBounds(c, 0, this.size.width - 1);
        l = this.checkBounds(l, 0, this.size.height - 1);
        System.arraycopy(this.charArray[this.screenBase + l], c, this.charArray[this.screenBase + l], c + 1, this.size.width - c - 1);
        System.arraycopy(this.charAttributes[this.screenBase + l], c, this.charAttributes[this.screenBase + l], c + 1, this.size.width - c - 1);
        this.putChar(c, l, ch, attributes);
    }
    
    public void insertLine(final int l) {
        this.insertLine(l, 1, false);
    }
    
    public void insertLine(final int l, final int n) {
        this.insertLine(l, n, false);
    }
    
    public synchronized void insertLine(int l, int n, final boolean scrollDown) {
        this.screenLocked = true;
        l = this.checkBounds(l, 0, this.size.height - 1);
        char[][] cbuf = null;
        int[][] abuf = null;
        int offset = 0;
        final int oldBase = this.screenBase;
        final int top = (l < this.topMargin) ? 0 : ((l > this.bottomMargin) ? ((this.bottomMargin + 1 < this.size.height) ? (this.bottomMargin + 1) : (this.size.height - 1)) : this.topMargin);
        final int bottom = (l > this.bottomMargin) ? (this.size.height - 1) : ((l < this.topMargin) ? ((this.topMargin > 0) ? (this.topMargin - 1) : 0) : this.bottomMargin);
        if (scrollDown) {
            if (n > bottom - top) {
                n = bottom - top;
            }
            cbuf = new char[bottom - l - (n - 1)][this.size.width];
            abuf = new int[bottom - l - (n - 1)][this.size.width];
            System.arraycopy(this.charArray, oldBase + l, cbuf, 0, bottom - l - (n - 1));
            System.arraycopy(this.charAttributes, oldBase + l, abuf, 0, bottom - l - (n - 1));
            System.arraycopy(cbuf, 0, this.charArray, oldBase + l + n, bottom - l - (n - 1));
            System.arraycopy(abuf, 0, this.charAttributes, oldBase + l + n, bottom - l - (n - 1));
            cbuf = this.charArray;
            abuf = this.charAttributes;
        }
        else {
            try {
                if (n > bottom - top + 1) {
                    n = bottom - top + 1;
                }
                if (this.bufSize < this.maxBufSize) {
                    if (this.bufSize + n > this.maxBufSize) {
                        offset = n - (this.maxBufSize - this.bufSize);
                        this.bufSize = this.maxBufSize;
                        this.screenBase = this.maxBufSize - this.size.height - 1;
                        this.windowBase = this.screenBase;
                    }
                    else {
                        this.screenBase += n;
                        this.windowBase += n;
                        this.bufSize += n;
                    }
                    cbuf = new char[this.bufSize][this.size.width];
                    abuf = new int[this.bufSize][this.size.width];
                }
                else {
                    offset = n;
                    cbuf = this.charArray;
                    abuf = this.charAttributes;
                }
                if (oldBase > 0) {
                    System.arraycopy(this.charArray, offset, cbuf, 0, oldBase - offset);
                    System.arraycopy(this.charAttributes, offset, abuf, 0, oldBase - offset);
                }
                if (top > 0) {
                    System.arraycopy(this.charArray, oldBase, cbuf, this.screenBase, top);
                    System.arraycopy(this.charAttributes, oldBase, abuf, this.screenBase, top);
                }
                if (oldBase > 0) {
                    System.arraycopy(this.charArray, oldBase + top, cbuf, oldBase - offset, n);
                    System.arraycopy(this.charAttributes, oldBase + top, abuf, oldBase - offset, n);
                }
                System.arraycopy(this.charArray, oldBase + top + n, cbuf, this.screenBase + top, l - top - (n - 1));
                System.arraycopy(this.charAttributes, oldBase + top + n, abuf, this.screenBase + top, l - top - (n - 1));
                if (l < this.size.height - 1) {
                    System.arraycopy(this.charArray, oldBase + l + 1, cbuf, this.screenBase + l + 1, this.size.height - 1 - l);
                    System.arraycopy(this.charAttributes, oldBase + l + 1, abuf, this.screenBase + l + 1, this.size.height - 1 - l);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("*** Error while scrolling up:");
                System.err.println("--- BEGIN STACKTRACE ---");
                e.printStackTrace();
                System.err.println("--- END STACKTRACE ---");
                System.err.println("bufSize=" + this.bufSize + ", maxBufSize=" + this.maxBufSize);
                System.err.println("top=" + top + ", bottom=" + bottom);
                System.err.println("n=" + n + ", l=" + l);
                System.err.println("screenBase=" + this.screenBase + ", windowBase=" + this.windowBase);
                System.err.println("oldBase=" + oldBase);
                System.err.println("size.width=" + this.size.width + ", size.height=" + this.size.height);
                System.err.println("abuf.length=" + abuf.length + ", cbuf.length=" + cbuf.length);
                System.err.println("*** done dumping debug information");
            }
        }
        for (int i = 0; i < n; ++i) {
            cbuf[this.screenBase + l + (scrollDown ? i : (-i))] = new char[this.size.width];
            abuf[this.screenBase + l + (scrollDown ? i : (-i))] = new int[this.size.width];
        }
        this.charArray = cbuf;
        this.charAttributes = abuf;
        if (scrollDown) {
            this.markLine(l, bottom - l + 1);
        }
        else {
            this.markLine(top, l - top + 1);
        }
        if (this.scrollBar != null) {
            this.scrollBar.setValues(this.windowBase, this.size.height, 0, this.bufSize);
        }
        this.screenLocked = false;
    }
    
    public void insertLine(final int l, final boolean scrollDown) {
        this.insertLine(l, 1, scrollDown);
    }
    
    public Insets insets() {
        return (this.insets == null) ? super.insets() : this.insets;
    }
    
    public void markLine(int l, final int n) {
        l = this.checkBounds(l, 0, this.size.height - 1);
        for (int i = 0; i < n && l + i < this.size.height; ++i) {
            this.update[l + i + 1] = true;
        }
    }
    
    public Color notbold(final Color colr) {
        return new Color(Math.max((int)(colr.getRed() * 0.85), 0), Math.max((int)(colr.getGreen() * 0.85), 0), Math.max((int)(colr.getBlue() * 0.85), 0));
    }
    
    public synchronized void paint(final Graphics g) {
        if (this.screenLocked) {
            return;
        }
        int xoffset = (super.size().width - this.size.width * this.charWidth - ((this.scrollBar != null) ? 15 : 0)) / 2;
        int yoffset = (super.size().height - this.size.height * this.charHeight) / 2;
        Color fg = this.color[7];
        Color bg = this.color[0];
        if (this.scrollBar != null && this.scrBarPos.equals("West")) {
            xoffset += 15;
        }
        g.setFont(this.normalFont);
        for (int l = 0; l < this.size.height; ++l) {
            if (!this.update[0] || this.update[l + 1]) {
                this.update[l + 1] = false;
                for (int c = 0; c < this.size.width; ++c) {
                    int addr = 0;
                    final int currAttr = this.charAttributes[this.windowBase + l][c];
                    fg = this.color[7];
                    bg = this.color[0];
                    if ((currAttr & 0x1) != 0x0 && (currAttr & 0x78) == 0x0 && (currAttr & 0x780) == 0x0) {
                        fg = this.color[3];
                    }
                    if ((currAttr & 0x78) != 0x0) {
                        fg = this.color[((currAttr & 0x78) >> 3) - 1];
                    }
                    if ((currAttr & 0x780) != 0x0) {
                        bg = this.color[((currAttr & 0x780) >> 7) - 1];
                    }
                    if ((currAttr & 0x1) != 0x0) {
                        if (fg.equals(Color.black)) {
                            fg = Color.gray;
                        }
                        else {
                            fg = fg.brighter();
                            bg = bg.brighter();
                        }
                    }
                    if ((currAttr & 0x4) != 0x0) {
                        final Color swapc = bg;
                        bg = fg;
                        fg = swapc;
                    }
                    if (this.sf.inSoftFont(this.charArray[this.windowBase + l][c])) {
                        g.setColor(bg);
                        g.fillRect(c * this.charWidth + xoffset, l * this.charHeight + yoffset, this.charWidth, this.charHeight);
                        g.setColor(fg);
                        this.sf.drawChar(g, this.charArray[this.windowBase + l][c], xoffset + c * this.charWidth, l * this.charHeight + yoffset, this.charWidth, this.charHeight);
                        if ((currAttr & 0x2) != 0x0) {
                            g.drawLine(c * this.charWidth + xoffset, (l + 1) * this.charHeight - this.charDescent / 2 + yoffset, c * this.charWidth + this.charWidth + xoffset, (l + 1) * this.charHeight - this.charDescent / 2 + yoffset);
                        }
                    }
                    else {
                        while (c + addr < this.size.width && this.charAttributes[this.windowBase + l][c + addr] == currAttr && !this.sf.inSoftFont(this.charArray[this.windowBase + l][c + addr])) {
                            if (this.charArray[this.windowBase + l][c + addr] < ' ') {
                                this.charArray[this.windowBase + l][c + addr] = ' ';
                            }
                            ++addr;
                        }
                        g.setColor(bg);
                        g.fillRect(c * this.charWidth + xoffset, l * this.charHeight + yoffset, addr * this.charWidth, this.charHeight);
                        g.setColor(fg);
                        g.drawChars(this.charArray[this.windowBase + l], c, addr, c * this.charWidth + xoffset, (l + 1) * this.charHeight - this.charDescent + yoffset);
                        if ((currAttr & 0x2) != 0x0) {
                            g.drawLine(c * this.charWidth + xoffset, (l + 1) * this.charHeight - this.charDescent / 2 + yoffset, c * this.charWidth + addr * this.charWidth + xoffset, (l + 1) * this.charHeight - this.charDescent / 2 + yoffset);
                        }
                        c += addr - 1;
                    }
                }
            }
        }
        if (this.screenBase + this.cursorY >= this.windowBase && this.screenBase + this.cursorY < this.windowBase + this.size.height) {
            g.setColor(this.color[7]);
            g.setXORMode(this.color[0]);
            g.fillRect(this.cursorX * this.charWidth + xoffset, (this.cursorY + this.screenBase - this.windowBase) * this.charHeight + yoffset, this.charWidth, this.charHeight);
            g.setPaintMode();
        }
        if (this.windowBase <= this.selectBegin.y || this.windowBase <= this.selectEnd.y) {
            int beginLine = this.selectBegin.y - this.windowBase;
            int endLine = this.selectEnd.y - this.selectBegin.y;
            if (beginLine < 0) {
                endLine += beginLine;
                beginLine = 0;
            }
            if (endLine > this.size.height) {
                endLine = this.size.height - beginLine;
            }
            g.setXORMode(this.color[0]);
            g.fillRect(this.selectBegin.x * this.charWidth + xoffset, beginLine * this.charHeight + yoffset, ((endLine == 0) ? (this.selectEnd.x - this.selectBegin.x) : (this.size.width - this.selectBegin.x)) * this.charWidth, this.charHeight);
            if (endLine > 1) {
                g.fillRect(xoffset, (beginLine + 1) * this.charHeight + yoffset, this.size.width * this.charWidth, (endLine - 1) * this.charHeight);
            }
            if (endLine > 0) {
                g.fillRect(xoffset, (beginLine + endLine) * this.charHeight + yoffset, this.selectEnd.x * this.charWidth, this.charHeight);
            }
            g.setPaintMode();
        }
        if (this.insets != null) {
            g.setColor(this.getBackground());
            --xoffset;
            --yoffset;
            for (int i = this.insets.top - 1; i >= 0; --i) {
                g.draw3DRect(xoffset - i, yoffset - i, this.charWidth * this.size.width + 1 + i * 2, this.charHeight * this.size.height + 1 + i * 2, this.raised);
            }
        }
        this.update[0] = false;
    }
    
    public Dimension preferredSize() {
        return this.size();
    }
    
    public void putChar(final int c, final int l, final char ch) {
        this.putChar(c, l, ch, 0);
    }
    
    public void putChar(int c, int l, final char ch, final int attributes) {
        c = this.checkBounds(c, 0, this.size.width - 1);
        l = this.checkBounds(l, 0, this.size.height - 1);
        this.charArray[this.screenBase + l][c] = ch;
        this.charAttributes[this.screenBase + l][c] = attributes;
        this.markLine(l, 1);
    }
    
    public void putString(final int c, final int l, final String s) {
        this.putString(c, l, s, 0);
    }
    
    public void putString(final int c, final int l, final String s, final int attributes) {
        for (int i = 0; i < s.length() && c + i < this.size.width; ++i) {
            this.putChar(c + i, l, s.charAt(i), attributes);
        }
    }
    
    public void redraw() {
        this.update[0] = true;
        this.repaint();
    }
    
    public void reshape(final int x, final int y, int w, int h) {
        int xborder = 0;
        int yborder = 0;
        if (this.insets != null) {
            w -= (xborder = this.insets.left + this.insets.right);
            h -= (yborder = this.insets.top + this.insets.bottom);
        }
        if (this.scrollBar != null) {
            w -= 15;
        }
        final Font tmpFont = this.normalFont;
        final String fontName = this.normalFont.getName();
        this.fm = this.getFontMetrics(this.normalFont);
        if (this.fm != null) {
            this.charWidth = this.fm.charWidth('@');
            this.charHeight = this.fm.getHeight();
        }
        switch (this.resizeStrategy) {
            case 1: {
                this.setWindowSize(w / this.charWidth, this.size.height = h / this.charHeight);
                break;
            }
            case 2: {
                final int height = h / this.size.height;
                final int width = w / this.size.width;
                final Font normalFont = new Font(fontName, 0, this.charHeight);
                this.normalFont = normalFont;
                this.fm = this.getFontMetrics(normalFont);
                if (this.fm.getHeight() < height || this.fm.charWidth('@') < width) {
                    do {
                        final Font normalFont2 = new Font(fontName, 0, ++this.charHeight);
                        this.normalFont = normalFont2;
                        this.fm = this.getFontMetrics(normalFont2);
                    } while (this.fm.getHeight() < height || this.fm.charWidth('@') < width);
                }
                if (this.fm.getHeight() > height || this.fm.charWidth('@') > width) {
                    do {
                        final Font normalFont3 = new Font(fontName, 0, --this.charHeight);
                        this.normalFont = normalFont3;
                        this.fm = this.getFontMetrics(normalFont3);
                    } while (this.charHeight > 1 && (this.fm.getHeight() > height || this.fm.charWidth('@') > width));
                }
                if (this.charHeight <= 1) {
                    System.err.println("CharDisplay: error during resize, resetting");
                    this.normalFont = tmpFont;
                    System.err.println("CharDisplay: disabling font/screen resize");
                    this.resizeStrategy = 0;
                }
                this.setFont(this.normalFont);
                this.fm = this.getFontMetrics(this.normalFont);
                this.charWidth = this.fm.charWidth('@');
                this.charHeight = this.fm.getHeight();
                this.charDescent = this.fm.getDescent();
                break;
            }
        }
        super.reshape(x, y, w + xborder + ((this.scrollBar != null) ? 15 : 0), h + yborder);
        if (this.scrollBar != null) {
            final int xoffset = (super.size().width - this.size.width * this.charWidth - 15) / 2;
            final int yoffset = (super.size().height - this.size.height * this.charHeight) / 2;
            if (this.scrBarPos.equals("West")) {
                this.scrollBar.reshape(xoffset - xborder / 2, yoffset - yborder / 2, 15, this.size.height * this.charHeight + yborder);
            }
            else {
                this.scrollBar.reshape(xoffset + xborder / 2 + this.size.width * this.charWidth, yoffset - yborder / 2, 15, this.size.height * this.charHeight + yborder);
            }
        }
    }
    
    public void setBorder(final int thickness, final boolean raised) {
        if (thickness == 0) {
            this.insets = null;
        }
        else {
            this.insets = new Insets(thickness + 1, thickness + 1, thickness + 1, thickness + 1);
        }
        this.raised = raised;
    }
    
    public void setBottomMargin(final int l) {
        if (l < this.topMargin) {
            this.bottomMargin = this.topMargin;
            this.topMargin = l;
        }
        else {
            this.bottomMargin = l;
        }
        if (this.topMargin < 0) {
            this.topMargin = 0;
        }
        if (this.bottomMargin > this.size.height - 1) {
            this.bottomMargin = this.size.height - 1;
        }
    }
    
    public void setBufferSize(int amount) {
        this.screenLocked = true;
        if (amount < this.size.height) {
            amount = this.size.height;
        }
        if (amount < this.maxBufSize) {
            final char[][] cbuf = new char[amount][this.size.width];
            final int[][] abuf = new int[amount][this.size.width];
            System.arraycopy(this.charArray, this.bufSize - amount, cbuf, 0, amount);
            System.arraycopy(this.charAttributes, this.bufSize - amount, abuf, 0, amount);
            this.charArray = cbuf;
            this.charAttributes = abuf;
        }
        this.maxBufSize = amount;
        this.screenLocked = false;
        this.repaint();
    }
    
    public void setCursorPos(int c, int l) {
        c = this.checkBounds(c, 0, this.size.width - 1);
        l = this.checkBounds(l, 0, this.size.height - 1);
        this.markLine(this.cursorY, 1);
        this.cursorX = ((c < this.size.width) ? c : this.size.width);
        this.cursorY = ((l < this.size.height) ? l : this.size.height);
        this.markLine(l, 1);
    }
    
    public void setResizeStrategy(final int strategy) {
        this.resizeStrategy = strategy;
    }
    
    public void setScrollbar(final String position) {
        this.add(this.scrollBar = new Scrollbar());
        this.scrollBar.setValues(this.windowBase, this.size.height, 0, this.bufSize - this.size.height);
        this.scrBarPos = position;
    }
    
    public void setTopMargin(final int l) {
        if (l > this.bottomMargin) {
            this.topMargin = this.bottomMargin;
            this.bottomMargin = l;
        }
        else {
            this.topMargin = l;
        }
        if (this.topMargin < 0) {
            this.topMargin = 0;
        }
        if (this.bottomMargin > this.size.height - 1) {
            this.bottomMargin = this.size.height - 1;
        }
    }
    
    public void setWindowBase(int line) {
        if (line > this.screenBase) {
            line = this.screenBase;
        }
        else if (line < 0) {
            line = 0;
        }
        this.windowBase = line;
        this.repaint();
    }
    
    public void setWindowSize(final int width, final int height) {
        final int bsize = this.bufSize;
        if (width < 1 || height < 1) {
            return;
        }
        this.screenLocked = true;
        super.update(this.getGraphics());
        if (height > this.maxBufSize) {
            this.maxBufSize = height;
        }
        if (height > this.bufSize) {
            this.bufSize = height;
            this.screenBase = 0;
            this.windowBase = 0;
        }
        final char[][] cbuf = new char[this.bufSize][width];
        final int[][] abuf = new int[this.bufSize][width];
        for (int i = 0; i < bsize && i < this.bufSize; ++i) {
            System.arraycopy(this.charArray[i], 0, cbuf[i], 0, (width < this.size.width) ? width : this.size.width);
            System.arraycopy(this.charAttributes[i], 0, abuf[i], 0, (width < this.size.width) ? width : this.size.width);
        }
        this.charArray = cbuf;
        this.charAttributes = abuf;
        this.size = new Dimension(width, height);
        this.topMargin = 0;
        this.bottomMargin = height - 1;
        this.update = new boolean[height + 1];
        for (int j = 0; j <= height; ++j) {
            this.update[j] = true;
        }
        this.screenLocked = false;
    }
    
    public Dimension size() {
        int xborder = 0;
        int yborder = 0;
        if (this.insets != null) {
            xborder = this.insets.left + this.insets.right;
            yborder = this.insets.top + this.insets.bottom;
        }
        if (this.scrollBar != null) {
            xborder += 15;
        }
        return new Dimension(this.size.width * this.charWidth + xborder, this.size.height * this.charHeight + yborder);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
