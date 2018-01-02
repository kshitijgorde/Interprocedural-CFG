// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.terminal;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.AWTEventMulticaster;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Image;
import java.awt.Scrollbar;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;

public class VDU extends Component implements MouseListener, MouseMotionListener
{
    public static final String ID = "$Id: VDU.java,v 2.34 2000/09/28 09:34:00 leo Exp $";
    public static final int debug = 0;
    private static final long VDU_EVENTS = 188L;
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
    private Font normalFont;
    private FontMetrics fm;
    private int charWidth;
    private int charHeight;
    private int charDescent;
    private int resizeStrategy;
    private int cursorX;
    private int cursorY;
    private boolean showcursor;
    private Point selectBegin;
    private Point selectEnd;
    private String selection;
    private Scrollbar scrollBar;
    private SoftFont sf;
    private boolean[] update;
    private boolean colorPrinting;
    private Image backingStore;
    private Color[] color;
    public static final int COLOR_0 = 0;
    public static final int COLOR_1 = 1;
    public static final int COLOR_2 = 2;
    public static final int COLOR_3 = 3;
    public static final int COLOR_4 = 4;
    public static final int COLOR_5 = 5;
    public static final int COLOR_6 = 6;
    public static final int COLOR_7 = 7;
    private static int COLOR_FG_STD;
    private static int COLOR_FG_BOLD;
    private static int COLOR_BG_STD;
    private static final int COLOR = 2040;
    private static final int COLOR_FG = 120;
    private static final int COLOR_BG = 1920;
    private Color cursorColorFG;
    private Color cursorColorBG;
    public static final boolean SCROLL_UP = false;
    public static final boolean SCROLL_DOWN = true;
    public static final int RESIZE_NONE = 0;
    public static final int RESIZE_SCREEN = 1;
    public static final int RESIZE_FONT = 2;
    public static final int NORMAL = 0;
    public static final int BOLD = 1;
    public static final int UNDERLINE = 2;
    public static final int INVERT = 4;
    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;
    private KeyListener keyListener;
    FocusListener focusListener;
    
    private Color brighten(final Color clr) {
        return new Color(Math.max((int)(clr.getRed() * 0.85), 0), Math.max((int)(clr.getGreen() * 0.85), 0), Math.max((int)(clr.getBlue() * 0.85), 0));
    }
    
    public VDU(final int width, final int height, final Font font) {
        this.showcursor = true;
        this.sf = new SoftFont();
        this.colorPrinting = false;
        this.backingStore = null;
        this.color = new Color[] { this.brighten(Color.black), this.brighten(Color.red), this.brighten(Color.green), this.brighten(Color.yellow), this.brighten(Color.blue), this.brighten(Color.magenta), this.brighten(Color.cyan), this.brighten(Color.white) };
        this.cursorColorFG = null;
        this.cursorColorBG = null;
        this.enableEvents(188L);
        this.setFont(font);
        this.setResizeStrategy(2);
        this.setScreenSize(width, height);
        this.setForeground(Color.white);
        this.setBackground(Color.black);
        this.cursorColorFG = this.color[VDU.COLOR_FG_STD];
        this.cursorColorBG = this.color[VDU.COLOR_BG_STD];
        this.clearSelection();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.selection = null;
    }
    
    public VDU(final int width, final int height) {
        this(width, height, new Font("Monospaced", 0, 11));
    }
    
    public VDU(final Font font) {
        this(80, 24, font);
    }
    
    public VDU() {
        this(80, 24, new Font("Monospaced", 0, 11));
    }
    
    public void setColorSet(final Color[] colorset) {
        System.arraycopy(colorset, 0, this.color, 0, 8);
        this.update[0] = true;
        this.redraw();
    }
    
    public Color[] getColorSet() {
        return this.color;
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
    
    public char getChar(int c, int l) {
        c = this.checkBounds(c, 0, this.size.width - 1);
        l = this.checkBounds(l, 0, this.size.height - 1);
        return this.charArray[this.screenBase + l][c];
    }
    
    public int getAttributes(int c, int l) {
        c = this.checkBounds(c, 0, this.size.width - 1);
        l = this.checkBounds(l, 0, this.size.height - 1);
        return this.charAttributes[this.screenBase + l][c];
    }
    
    public void insertChar(int c, int l, final char ch, final int attributes) {
        c = this.checkBounds(c, 0, this.size.width - 1);
        l = this.checkBounds(l, 0, this.size.height - 1);
        System.arraycopy(this.charArray[this.screenBase + l], c, this.charArray[this.screenBase + l], c + 1, this.size.width - c - 1);
        System.arraycopy(this.charAttributes[this.screenBase + l], c, this.charAttributes[this.screenBase + l], c + 1, this.size.width - c - 1);
        this.putChar(c, l, ch, attributes);
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
    
    public void putString(final int c, final int l, final String s) {
        this.putString(c, l, s, 0);
    }
    
    public void putString(final int c, final int l, final String s, final int attributes) {
        for (int i = 0; i < s.length() && c + i < this.size.width; ++i) {
            this.putChar(c + i, l, s.charAt(i), attributes);
        }
    }
    
    public void insertLine(final int l) {
        this.insertLine(l, 1, false);
    }
    
    public void insertLine(final int l, final int n) {
        this.insertLine(l, n, false);
    }
    
    public void insertLine(final int l, final boolean scrollDown) {
        this.insertLine(l, 1, scrollDown);
    }
    
    public synchronized void insertLine(int l, int n, final boolean scrollDown) {
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
                System.err.println("--- BEGIN STACK TRACE ---");
                e.printStackTrace();
                System.err.println("--- END STACK TRACE ---");
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
    
    public void setCursorPosition(int c, int l) {
        c = this.checkBounds(c, 0, this.size.width - 1);
        l = this.checkBounds(l, 0, this.size.height - 1);
        this.markLine(this.cursorY, 1);
        this.cursorX = ((c < this.size.width) ? c : this.size.width);
        this.cursorY = ((l < this.size.height) ? l : this.size.height);
        this.markLine(l, 1);
    }
    
    public void showCursor(final boolean doshow) {
        if (doshow != this.showcursor) {
            this.markLine(this.cursorY, 1);
        }
        this.showcursor = doshow;
    }
    
    public Dimension getCursorPosition() {
        return new Dimension(this.cursorX, this.cursorY);
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
    
    public int getTopMargin() {
        return this.topMargin;
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
    
    public int getBottomMargin() {
        return this.bottomMargin;
    }
    
    public void setBufferSize(int amount) {
        if (amount < this.size.height) {
            amount = this.size.height;
        }
        if (amount < this.maxBufSize) {
            final char[][] cbuf = new char[amount][this.size.width];
            final int[][] abuf = new int[amount][this.size.width];
            final int copyStart = (this.bufSize - amount < 0) ? 0 : (this.bufSize - amount);
            final int copyCount = (this.bufSize - amount < 0) ? this.bufSize : amount;
            if (this.charArray != null) {
                System.arraycopy(this.charArray, copyStart, cbuf, 0, copyCount);
            }
            if (this.charAttributes != null) {
                System.arraycopy(this.charAttributes, copyStart, abuf, 0, copyCount);
            }
            this.charArray = cbuf;
            this.charAttributes = abuf;
            this.bufSize = copyCount;
            this.screenBase = this.bufSize - this.size.height;
            this.windowBase = this.screenBase;
        }
        this.maxBufSize = amount;
        this.update[0] = true;
        this.redraw();
    }
    
    public int getBufferSize() {
        return this.bufSize;
    }
    
    public int getMaxBufferSize() {
        return this.maxBufSize;
    }
    
    public void setWindowBase(int line) {
        if (line > this.screenBase) {
            line = this.screenBase;
        }
        else if (line < 0) {
            line = 0;
        }
        this.windowBase = line;
        this.update[0] = true;
        this.redraw();
    }
    
    public int getWindowBase() {
        return this.windowBase;
    }
    
    public void setFont(final Font font) {
        super.setFont(this.normalFont = font);
        this.fm = this.getFontMetrics(font);
        if (this.fm != null) {
            this.charWidth = this.fm.charWidth('@');
            this.charHeight = this.fm.getHeight();
            this.charDescent = this.fm.getDescent();
        }
        if (this.update != null) {
            this.update[0] = true;
        }
        this.redraw();
    }
    
    public void setScreenSize(final int width, final int height) {
        final int bsize = this.bufSize;
        if (width < 1 || height < 1) {
            return;
        }
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
        if (this.charArray != null && this.charAttributes != null) {
            for (int i = 0; i < bsize && i < this.bufSize; ++i) {
                System.arraycopy(this.charArray[i], 0, cbuf[i], 0, (width < this.size.width) ? width : this.size.width);
                System.arraycopy(this.charAttributes[i], 0, abuf[i], 0, (width < this.size.width) ? width : this.size.width);
            }
        }
        this.charArray = cbuf;
        this.charAttributes = abuf;
        this.size = new Dimension(width, height);
        this.topMargin = 0;
        this.bottomMargin = height - 1;
        (this.update = new boolean[height + 1])[0] = true;
    }
    
    public Dimension getScreenSize() {
        return this.size;
    }
    
    public void setResizeStrategy(final int strategy) {
        this.resizeStrategy = strategy;
    }
    
    public int getRows() {
        return this.size.height;
    }
    
    public int getColumns() {
        return this.size.width;
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
    
    public void setScrollbar(final Scrollbar scrollBar) {
        if (scrollBar == null) {
            return;
        }
        (this.scrollBar = scrollBar).setValues(this.windowBase, this.size.height, 0, this.bufSize - this.size.height);
        this.scrollBar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent evt) {
                VDU.this.setWindowBase(evt.getValue());
            }
        });
    }
    
    public void markLine(int l, final int n) {
        l = this.checkBounds(l, 0, this.size.height - 1);
        for (int i = 0; i < n && l + i < this.size.height; ++i) {
            this.update[l + i + 1] = true;
        }
    }
    
    public void redraw() {
        if (this.backingStore != null) {
            this.redraw(this.backingStore.getGraphics());
            this.repaint();
        }
    }
    
    protected synchronized void redraw(final Graphics g) {
        int xoffset = (super.getSize().width - this.size.width * this.charWidth) / 2;
        int yoffset = (super.getSize().height - this.size.height * this.charHeight) / 2;
        final int selectStartLine = this.selectBegin.y - this.windowBase;
        final int selectEndLine = this.selectEnd.y - this.windowBase;
        Color fg = this.color[VDU.COLOR_FG_STD];
        Color bg = this.color[VDU.COLOR_BG_STD];
        g.setFont(this.normalFont);
        for (int l = 0; l < this.size.height; ++l) {
            if (this.update[0] || this.update[l + 1]) {
                this.update[l + 1] = false;
                for (int c = 0; c < this.size.width; ++c) {
                    int addr = 0;
                    final int currAttr = this.charAttributes[this.windowBase + l][c];
                    fg = this.getForeground();
                    bg = this.getBackground();
                    if ((currAttr & 0x1) != 0x0 && (currAttr & 0x78) == 0x0 && (currAttr & 0x780) == 0x0) {
                        fg = this.color[VDU.COLOR_FG_BOLD];
                    }
                    if ((currAttr & 0x78) != 0x0) {
                        fg = this.color[((currAttr & 0x78) >> 3) - 1];
                    }
                    if ((currAttr & 0x780) != 0x0) {
                        bg = this.color[((currAttr & 0x780) >> 7) - 1];
                    }
                    if ((currAttr & 0x1) != 0x0) {
                        if (fg.equals(Color.black) && VDU.COLOR_FG_BOLD != 0) {
                            fg = Color.gray;
                        }
                        else {
                            fg = fg.brighter();
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
                if (l >= selectStartLine && l <= selectEndLine) {
                    final int selectStartColumn = (l == selectStartLine) ? this.selectBegin.x : 0;
                    final int selectEndColumn = (l == selectEndLine) ? ((l == selectStartLine) ? (this.selectEnd.x - selectStartColumn) : this.selectEnd.x) : this.size.width;
                    if (selectStartColumn != selectEndColumn) {
                        g.setXORMode(bg);
                        g.fillRect(selectStartColumn * this.charWidth + xoffset, l * this.charHeight + yoffset, selectEndColumn * this.charWidth, this.charHeight);
                        g.setPaintMode();
                    }
                }
            }
        }
        if (this.showcursor && this.screenBase + this.cursorY >= this.windowBase && this.screenBase + this.cursorY < this.windowBase + this.size.height) {
            g.setColor(this.cursorColorFG);
            g.setXORMode(this.cursorColorBG);
            g.fillRect(this.cursorX * this.charWidth + xoffset, (this.cursorY + this.screenBase - this.windowBase) * this.charHeight + yoffset, this.charWidth, this.charHeight);
            g.setPaintMode();
            g.setColor(this.color[VDU.COLOR_FG_STD]);
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
    
    public void paint(final Graphics g) {
        if (this.backingStore == null) {
            final Dimension size = super.getSize();
            this.backingStore = this.createImage(size.width, size.height);
            this.update[0] = true;
            this.redraw();
        }
        g.drawImage(this.backingStore, 0, 0, this);
    }
    
    public void setColorPrinting(final boolean colorPrint) {
        this.colorPrinting = colorPrint;
    }
    
    public void print(final Graphics g) {
        for (int i = 0; i <= this.size.height; ++i) {
            this.update[i] = true;
        }
        Color fg = null;
        Color bg = null;
        Color[] colorSave = null;
        int boldSave = 0;
        if (!this.colorPrinting) {
            fg = this.getForeground();
            bg = this.getBackground();
            this.setForeground(Color.black);
            this.setBackground(Color.white);
            boldSave = VDU.COLOR_FG_BOLD;
            VDU.COLOR_FG_BOLD = 0;
            colorSave = this.color;
            this.color = new Color[] { Color.black, Color.black, Color.black, Color.black, Color.black, Color.black, Color.black, Color.white };
        }
        this.redraw(g);
        if (!this.colorPrinting) {
            this.color = colorSave;
            VDU.COLOR_FG_BOLD = boldSave;
            this.setForeground(fg);
            this.setBackground(bg);
        }
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
    
    public Point mouseGetPos(final Point evtpt) {
        final Point mousepos = new Point(0, 0);
        final int xoffset = (super.getSize().width - this.size.width * this.charWidth) / 2;
        final int yoffset = (super.getSize().height - this.size.height * this.charHeight) / 2;
        mousepos.x = (evtpt.x - xoffset) / this.charWidth;
        if (mousepos.x < 0) {
            mousepos.x = 0;
        }
        if (mousepos.x >= this.size.width) {
            mousepos.x = this.size.width - 1;
        }
        mousepos.y = (evtpt.y - yoffset) / this.charHeight;
        if (mousepos.y < 0) {
            mousepos.y = 0;
        }
        if (mousepos.y >= this.size.height) {
            mousepos.y = this.size.height - 1;
        }
        return mousepos;
    }
    
    public void setCursorColors(final Color fg, final Color bg) {
        if (fg == null) {
            this.cursorColorFG = this.color[VDU.COLOR_FG_STD];
        }
        else {
            this.cursorColorFG = fg;
        }
        if (bg == null) {
            this.cursorColorBG = this.color[VDU.COLOR_BG_STD];
        }
        else {
            this.cursorColorBG = bg;
        }
        this.repaint();
    }
    
    public void setBounds(final int x, final int y, int w, int h) {
        super.setBounds(x, y, w, h);
        int xborder = 0;
        int yborder = 0;
        if (this.insets != null) {
            w -= (xborder = this.insets.left + this.insets.right);
            h -= (yborder = this.insets.top + this.insets.bottom);
        }
        final Font tmpFont = this.normalFont;
        final String fontName = tmpFont.getName();
        final int fontStyle = tmpFont.getStyle();
        this.fm = this.getFontMetrics(this.normalFont);
        if (this.fm != null) {
            this.charWidth = this.fm.charWidth('@');
            this.charHeight = this.fm.getHeight();
        }
        switch (this.resizeStrategy) {
            case 1: {
                this.setScreenSize(w / this.charWidth, this.size.height = h / this.charHeight);
                break;
            }
            case 2: {
                final int height = h / this.size.height;
                final int width = w / this.size.width;
                final Font normalFont = new Font(fontName, fontStyle, this.charHeight);
                this.normalFont = normalFont;
                this.fm = this.getFontMetrics(normalFont);
                if (this.fm.getHeight() < height || this.fm.charWidth('@') < width) {
                    do {
                        final Font normalFont2 = new Font(fontName, fontStyle, ++this.charHeight);
                        this.normalFont = normalFont2;
                        this.fm = this.getFontMetrics(normalFont2);
                    } while (this.fm.getHeight() < height || this.fm.charWidth('@') < width);
                }
                if (this.fm.getHeight() > height || this.fm.charWidth('@') > width) {
                    do {
                        final Font normalFont3 = new Font(fontName, fontStyle, --this.charHeight);
                        this.normalFont = normalFont3;
                        this.fm = this.getFontMetrics(normalFont3);
                    } while (this.charHeight > 1 && (this.fm.getHeight() > height || this.fm.charWidth('@') > width));
                }
                if (this.charHeight <= 1) {
                    System.err.println("VDU: error during resize, resetting");
                    this.normalFont = tmpFont;
                    System.err.println("VDU: disabling font/screen resize");
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
        this.backingStore = null;
        this.markLine(0, this.size.height);
    }
    
    public Dimension getSize() {
        int xborder = 0;
        int yborder = 0;
        if (this.insets != null) {
            xborder = this.insets.left + this.insets.right;
            yborder = this.insets.top + this.insets.bottom;
        }
        return new Dimension(this.size.width * this.charWidth + xborder, this.size.height * this.charHeight + yborder);
    }
    
    public Dimension getPreferredSize() {
        return this.getSize();
    }
    
    public Insets getInsets() {
        return this.insets;
    }
    
    public void clearSelection() {
        this.selectBegin = new Point(0, 0);
        this.selectEnd = new Point(0, 0);
        this.selection = null;
    }
    
    public String getSelection() {
        return this.selection;
    }
    
    private boolean buttonCheck(final int modifiers, final int mask) {
        return (modifiers & mask) == mask;
    }
    
    public void mouseMoved(final MouseEvent evt) {
    }
    
    public void mouseDragged(final MouseEvent evt) {
        if (this.buttonCheck(evt.getModifiers(), 16) || evt.getModifiers() == 0) {
            final int xoffset = (super.getSize().width - this.size.width * this.charWidth) / 2;
            final int yoffset = (super.getSize().height - this.size.height * this.charHeight) / 2;
            final int x = (evt.getX() - xoffset) / this.charWidth;
            final int y = (evt.getY() - yoffset) / this.charHeight + this.windowBase;
            final int oldx = this.selectEnd.x;
            final int oldy = this.selectEnd.y;
            if (x <= this.selectBegin.x && y <= this.selectBegin.y) {
                this.selectBegin.x = x;
                this.selectBegin.y = y;
            }
            else {
                this.selectEnd.x = x;
                this.selectEnd.y = y;
            }
            if (oldx != x || oldy != y) {
                this.update[0] = true;
                this.redraw();
            }
        }
    }
    
    public void mouseClicked(final MouseEvent evt) {
    }
    
    public void mouseEntered(final MouseEvent evt) {
    }
    
    public void mouseExited(final MouseEvent evt) {
    }
    
    public void mousePressed(final MouseEvent evt) {
        this.requestFocus();
        final int xoffset = (super.getSize().width - this.size.width * this.charWidth) / 2;
        final int yoffset = (super.getSize().height - this.size.height * this.charHeight) / 2;
        if (this.buttonCheck(evt.getModifiers(), 16)) {
            this.selectBegin.x = (evt.getX() - xoffset) / this.charWidth;
            this.selectBegin.y = (evt.getY() - yoffset) / this.charHeight + this.windowBase;
            this.selectEnd.x = this.selectBegin.x;
            this.selectEnd.y = this.selectBegin.y;
        }
    }
    
    public void mouseReleased(final MouseEvent evt) {
        if (this.buttonCheck(evt.getModifiers(), 16)) {
            final int xoffset = (super.getSize().width - this.size.width * this.charWidth) / 2;
            final int yoffset = (super.getSize().height - this.size.height * this.charHeight) / 2;
            this.mouseDragged(evt);
            if (this.selectBegin.x == this.selectEnd.x && this.selectBegin.y == this.selectEnd.y) {
                this.update[0] = true;
                this.redraw();
                return;
            }
            this.selection = "";
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
                int start = (l == this.selectBegin.y) ? (start = this.selectBegin.x) : 0;
                int end = (l == this.selectEnd.y) ? (end = this.selectEnd.x) : (this.charArray[l].length - 1);
                this.selection += new String(this.charArray[l]).substring(start, end);
                if (end == this.charArray[l].length - 1) {
                    this.selection += "\n";
                }
            }
        }
    }
    
    public void addMouseListener(final MouseListener listener) {
        this.mouseListener = AWTEventMulticaster.add(this.mouseListener, listener);
        this.enableEvents(16L);
    }
    
    public void removeMouseListener(final MouseListener listener) {
        this.mouseListener = AWTEventMulticaster.remove(this.mouseListener, listener);
    }
    
    public void addMouseMotionListener(final MouseMotionListener listener) {
        this.mouseMotionListener = AWTEventMulticaster.add(this.mouseMotionListener, listener);
        this.enableEvents(16L);
    }
    
    public void removeMouseMotionListener(final MouseMotionListener listener) {
        this.mouseMotionListener = AWTEventMulticaster.remove(this.mouseMotionListener, listener);
    }
    
    public void processMouseEvent(final MouseEvent evt) {
        if (this.mouseListener != null) {
            switch (evt.getID()) {
                case 500: {
                    this.mouseListener.mouseClicked(evt);
                    break;
                }
                case 504: {
                    this.mouseListener.mouseEntered(evt);
                    break;
                }
                case 505: {
                    this.mouseListener.mouseExited(evt);
                    break;
                }
                case 501: {
                    this.mouseListener.mousePressed(evt);
                    break;
                }
                case 502: {
                    this.mouseListener.mouseReleased(evt);
                    break;
                }
            }
        }
        super.processMouseEvent(evt);
    }
    
    public void processMouseMotionEvent(final MouseEvent evt) {
        if (this.mouseMotionListener != null) {
            switch (evt.getID()) {
                case 506: {
                    this.mouseMotionListener.mouseDragged(evt);
                    break;
                }
                case 503: {
                    this.mouseMotionListener.mouseMoved(evt);
                    break;
                }
            }
        }
        super.processMouseMotionEvent(evt);
    }
    
    public void addKeyListener(final KeyListener listener) {
        this.keyListener = AWTEventMulticaster.add(this.keyListener, listener);
        this.enableEvents(8L);
    }
    
    public void removeKeyListener(final KeyListener listener) {
        this.keyListener = AWTEventMulticaster.remove(this.keyListener, listener);
    }
    
    public void processKeyEvent(final KeyEvent evt) {
        if (this.keyListener != null) {
            switch (evt.getID()) {
                case 401: {
                    this.keyListener.keyPressed(evt);
                    break;
                }
                case 402: {
                    this.keyListener.keyReleased(evt);
                    break;
                }
                case 400: {
                    this.keyListener.keyTyped(evt);
                    break;
                }
            }
        }
        if (evt.getKeyCode() == 9 && evt.getSource() == this) {
            evt.consume();
        }
        super.processKeyEvent(evt);
    }
    
    public void addFocusListener(final FocusListener listener) {
        this.focusListener = AWTEventMulticaster.add(this.focusListener, listener);
    }
    
    public void removeFocusListener(final FocusListener listener) {
        this.focusListener = AWTEventMulticaster.remove(this.focusListener, listener);
    }
    
    public void processFocusEvent(final FocusEvent evt) {
        if (this.focusListener != null) {
            switch (evt.getID()) {
                case 1004: {
                    this.focusListener.focusGained(evt);
                    break;
                }
                case 1005: {
                    this.focusListener.focusLost(evt);
                    break;
                }
            }
        }
        super.processFocusEvent(evt);
    }
    
    static {
        VDU.COLOR_FG_STD = 7;
        VDU.COLOR_FG_BOLD = 3;
        VDU.COLOR_BG_STD = 0;
    }
}
