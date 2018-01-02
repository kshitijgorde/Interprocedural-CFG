// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.terminal;

import java.awt.event.FocusEvent;
import java.awt.AWTEventMulticaster;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusListener;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JScrollBar;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.Component;

public class SwingTerminal extends Component implements VDUDisplay, KeyListener, MouseListener, MouseMotionListener
{
    private static final int debug = 0;
    private VDUBuffer buffer;
    private static final long VDU_EVENTS = 188L;
    private Insets insets;
    private boolean raised;
    private Font normalFont;
    private FontMetrics fm;
    private int charWidth;
    private int charHeight;
    private int charDescent;
    private int resizeStrategy;
    private Point selectBegin;
    private Point selectEnd;
    private String selection;
    private JScrollBar scrollBar;
    private SoftFont sf;
    private boolean colorPrinting;
    private Image backingStore;
    private Color[] color;
    public static final int RESIZE_NONE = 0;
    public static final int RESIZE_FONT = 1;
    public static final int RESIZE_SCREEN = 2;
    public static final int COLOR_BOLD = 8;
    public static final int COLOR_INVERT = 9;
    private static final int COLOR_FG_STD = 7;
    private static final int COLOR_BG_STD = 0;
    private Color cursorColorFG;
    private Color cursorColorBG;
    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;
    private KeyListener keyListener;
    FocusListener focusListener;
    static /* synthetic */ Class class$de$mud$terminal$SwingTerminal;
    
    private Color brighten(final Color clr) {
        final int r = (int)this.min(clr.getRed() * 1.2, 255.0);
        final int g = (int)this.min(clr.getGreen() * 1.2, 255.0);
        final int b = (int)this.min(clr.getBlue() * 1.2, 255.0);
        return new Color(r, g, b);
    }
    
    private Color darken(final Color clr) {
        final int r = (int)this.max(clr.getRed() * 0.8, 0.0);
        final int g = (int)this.max(clr.getGreen() * 0.8, 0.0);
        final int b = (int)this.max(clr.getBlue() * 0.8, 0.0);
        return new Color(r, g, b);
    }
    
    protected double max(final double f1, final double f2) {
        return (f1 < f2) ? f2 : f1;
    }
    
    protected double min(final double f1, final double f2) {
        return (f1 < f2) ? f1 : f2;
    }
    
    public SwingTerminal(final VDUBuffer buffer, final Font font) {
        this.sf = new SoftFont();
        this.colorPrinting = false;
        this.backingStore = null;
        this.color = new Color[] { Color.black, Color.red, Color.green, Color.yellow, Color.blue, Color.magenta, Color.cyan, Color.white, null, null };
        this.cursorColorFG = null;
        this.cursorColorBG = null;
        this.setVDUBuffer(buffer);
        this.addKeyListener(this);
        final String version = System.getProperty("java.version");
        final String versionStart = version.substring(0, 3);
        final double ver = Double.parseDouble(versionStart);
        if (ver >= 1.4) {
            try {
                final Class[] params = { Boolean.TYPE };
                ((SwingTerminal.class$de$mud$terminal$SwingTerminal == null) ? (SwingTerminal.class$de$mud$terminal$SwingTerminal = class$("de.mud.terminal.SwingTerminal")) : SwingTerminal.class$de$mud$terminal$SwingTerminal).getMethod("setFocusable", (Class[])params).invoke(this, new Boolean(true));
                ((SwingTerminal.class$de$mud$terminal$SwingTerminal == null) ? (SwingTerminal.class$de$mud$terminal$SwingTerminal = class$("de.mud.terminal.SwingTerminal")) : SwingTerminal.class$de$mud$terminal$SwingTerminal).getMethod("setFocusTraversalKeysEnabled", (Class[])params).invoke(this, new Boolean(false));
            }
            catch (Exception e) {
                System.err.println("vt320: unable to reset focus handling for java version " + version);
                e.printStackTrace();
            }
        }
        this.enableEvents(188L);
        this.setFont(font);
        this.setResizeStrategy(1);
        this.setForeground(Color.white);
        this.setBackground(Color.black);
        this.cursorColorFG = this.color[7];
        this.cursorColorBG = this.color[0];
        this.clearSelection();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.selection = null;
    }
    
    public SwingTerminal(final VDUBuffer buffer) {
        this(buffer, new Font("Monospaced", 0, 11));
    }
    
    public void setVDUBuffer(final VDUBuffer buffer) {
        (this.buffer = buffer).setDisplay(this);
    }
    
    public VDUBuffer getVDUBuffer() {
        return this.buffer;
    }
    
    public void setColorSet(final Color[] colorset) {
        System.arraycopy(colorset, 0, this.color, 0, 10);
        this.buffer.update[0] = true;
        this.redraw();
    }
    
    public Color[] getColorSet() {
        return this.color;
    }
    
    public void setFont(final Font font) {
        super.setFont(this.normalFont = font);
        this.fm = this.getFontMetrics(font);
        if (this.fm != null) {
            this.charWidth = this.fm.charWidth('@');
            this.charHeight = this.fm.getHeight();
            this.charDescent = this.fm.getDescent();
        }
        if (this.buffer.update != null) {
            this.buffer.update[0] = true;
        }
        this.redraw();
    }
    
    public void setResizeStrategy(final int strategy) {
        this.resizeStrategy = strategy;
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
    
    public void setScrollbar(final JScrollBar scrollBar) {
        if (scrollBar == null) {
            return;
        }
        (this.scrollBar = scrollBar).setValues(this.buffer.windowBase, this.buffer.height, 0, this.buffer.bufSize - this.buffer.height);
        this.scrollBar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent evt) {
                SwingTerminal.this.buffer.setWindowBase(evt.getValue());
            }
        });
    }
    
    public void redraw() {
        if (this.backingStore != null) {
            this.redraw(this.backingStore.getGraphics());
            this.repaint();
        }
    }
    
    public void updateScrollBar() {
        if (this.scrollBar == null) {
            return;
        }
        this.scrollBar.setValues(this.buffer.windowBase, this.buffer.height, 0, this.buffer.bufSize);
    }
    
    protected void redraw(final Graphics g) {
        int xoffset = (super.getSize().width - this.buffer.width * this.charWidth) / 2;
        int yoffset = (super.getSize().height - this.buffer.height * this.charHeight) / 2;
        final int selectStartLine = this.selectBegin.y - this.buffer.windowBase;
        final int selectEndLine = this.selectEnd.y - this.buffer.windowBase;
        Color fg = this.darken(this.color[7]);
        Color bg = this.darken(this.color[0]);
        g.setFont(this.normalFont);
        for (int l = 0; l < this.buffer.height; ++l) {
            if (this.buffer.update[0] || this.buffer.update[l + 1]) {
                this.buffer.update[l + 1] = false;
                for (int c = 0; c < this.buffer.width; ++c) {
                    int addr = 0;
                    final int currAttr = this.buffer.charAttributes[this.buffer.windowBase + l][c];
                    fg = this.darken(this.getForeground());
                    bg = this.darken(this.getBackground());
                    final int n = currAttr;
                    final VDUBuffer buffer = this.buffer;
                    if ((n & 0x1E0) != 0x0) {
                        final Color[] color = this.color;
                        final int n2 = currAttr;
                        final VDUBuffer buffer2 = this.buffer;
                        final int n3 = n2 & 0x1E0;
                        final VDUBuffer buffer3 = this.buffer;
                        fg = this.darken(color[(n3 >> 5) - 1]);
                    }
                    final int n4 = currAttr;
                    final VDUBuffer buffer4 = this.buffer;
                    if ((n4 & 0x1E00) != 0x0) {
                        final Color[] color2 = this.color;
                        final int n5 = currAttr;
                        final VDUBuffer buffer5 = this.buffer;
                        final int n6 = n5 & 0x1E00;
                        final VDUBuffer buffer6 = this.buffer;
                        bg = this.darken(this.darken(color2[(n6 >> 9) - 1]));
                    }
                    if ((currAttr & 0x1) != 0x0) {
                        g.setFont(new Font(this.normalFont.getName(), 1, this.normalFont.getSize()));
                        if (null != this.color[8]) {
                            fg = this.color[8];
                        }
                    }
                    else {
                        g.setFont(this.normalFont);
                    }
                    if ((currAttr & 0x8) != 0x0) {
                        fg = this.darken(fg);
                    }
                    if ((currAttr & 0x4) != 0x0) {
                        if (null == this.color[9]) {
                            final Color swapc = bg;
                            bg = fg;
                            fg = swapc;
                        }
                        else {
                            if (null == this.color[8]) {
                                fg = bg;
                            }
                            else {
                                fg = this.color[8];
                            }
                            bg = this.color[9];
                        }
                    }
                    if (this.sf.inSoftFont(this.buffer.charArray[this.buffer.windowBase + l][c])) {
                        g.setColor(bg);
                        g.fillRect(c * this.charWidth + xoffset, l * this.charHeight + yoffset, this.charWidth, this.charHeight);
                        g.setColor(fg);
                        if ((currAttr & 0x10) == 0x0) {
                            this.sf.drawChar(g, this.buffer.charArray[this.buffer.windowBase + l][c], xoffset + c * this.charWidth, l * this.charHeight + yoffset, this.charWidth, this.charHeight);
                        }
                        if ((currAttr & 0x2) != 0x0) {
                            g.drawLine(c * this.charWidth + xoffset, (l + 1) * this.charHeight - this.charDescent / 2 + yoffset, c * this.charWidth + this.charWidth + xoffset, (l + 1) * this.charHeight - this.charDescent / 2 + yoffset);
                        }
                    }
                    else {
                        while (c + addr < this.buffer.width && (this.buffer.charArray[this.buffer.windowBase + l][c + addr] < ' ' || this.buffer.charAttributes[this.buffer.windowBase + l][c + addr] == currAttr) && !this.sf.inSoftFont(this.buffer.charArray[this.buffer.windowBase + l][c + addr])) {
                            if (this.buffer.charArray[this.buffer.windowBase + l][c + addr] < ' ') {
                                this.buffer.charArray[this.buffer.windowBase + l][c + addr] = ' ';
                                this.buffer.charAttributes[this.buffer.windowBase + l][c + addr] = 0;
                            }
                            else {
                                ++addr;
                            }
                        }
                        g.setColor(bg);
                        g.fillRect(c * this.charWidth + xoffset, l * this.charHeight + yoffset, addr * this.charWidth, this.charHeight);
                        g.setColor(fg);
                        if ((currAttr & 0x10) == 0x0) {
                            g.drawChars(this.buffer.charArray[this.buffer.windowBase + l], c, addr, c * this.charWidth + xoffset, (l + 1) * this.charHeight - this.charDescent + yoffset);
                        }
                        if ((currAttr & 0x2) != 0x0) {
                            g.drawLine(c * this.charWidth + xoffset, (l + 1) * this.charHeight - this.charDescent / 2 + yoffset, c * this.charWidth + addr * this.charWidth + xoffset, (l + 1) * this.charHeight - this.charDescent / 2 + yoffset);
                        }
                        c += addr - 1;
                    }
                }
                if (l >= selectStartLine && l <= selectEndLine) {
                    final int selectStartColumn = (l == selectStartLine) ? this.selectBegin.x : 0;
                    final int selectEndColumn = (l == selectEndLine) ? ((l == selectStartLine) ? (this.selectEnd.x - selectStartColumn) : this.selectEnd.x) : this.buffer.width;
                    if (selectStartColumn != selectEndColumn) {
                        g.setXORMode(bg);
                        g.fillRect(selectStartColumn * this.charWidth + xoffset, l * this.charHeight + yoffset, selectEndColumn * this.charWidth, this.charHeight);
                        g.setPaintMode();
                    }
                }
            }
        }
        if (this.buffer.showcursor && this.buffer.screenBase + this.buffer.cursorY >= this.buffer.windowBase && this.buffer.screenBase + this.buffer.cursorY < this.buffer.windowBase + this.buffer.height) {
            g.setColor(this.cursorColorFG);
            g.setXORMode(this.cursorColorBG);
            g.fillRect(this.buffer.cursorX * this.charWidth + xoffset, (this.buffer.cursorY + this.buffer.screenBase - this.buffer.windowBase) * this.charHeight + yoffset, this.charWidth, this.charHeight);
            g.setPaintMode();
            g.setColor(this.color[7]);
        }
        if (this.insets != null) {
            g.setColor(this.getBackground());
            --xoffset;
            --yoffset;
            for (int i = this.insets.top - 1; i >= 0; --i) {
                g.draw3DRect(xoffset - i, yoffset - i, this.charWidth * this.buffer.width + 1 + i * 2, this.charHeight * this.buffer.height + 1 + i * 2, this.raised);
            }
        }
        this.buffer.update[0] = false;
    }
    
    public void paint(final Graphics g) {
        if (this.backingStore == null) {
            final Dimension size = super.getSize();
            this.backingStore = this.createImage(size.width, size.height);
            this.buffer.update[0] = true;
            this.redraw();
        }
        g.drawImage(this.backingStore, 0, 0, this);
    }
    
    public void setColorPrinting(final boolean colorPrint) {
        this.colorPrinting = colorPrint;
    }
    
    public void print(final Graphics g) {
        for (int i = 0; i <= this.buffer.height; ++i) {
            this.buffer.update[i] = true;
        }
        Color fg = null;
        Color bg = null;
        Color[] colorSave = null;
        if (!this.colorPrinting) {
            fg = this.getForeground();
            bg = this.getBackground();
            this.setForeground(Color.black);
            this.setBackground(Color.white);
            colorSave = this.color;
            this.color = new Color[] { Color.black, Color.black, Color.black, Color.black, Color.black, Color.black, Color.black, Color.white, null, null };
        }
        this.redraw(g);
        if (!this.colorPrinting) {
            this.color = colorSave;
            this.setForeground(fg);
            this.setBackground(bg);
        }
    }
    
    public Point mouseGetPos(final Point evtpt) {
        final Point mousepos = new Point(0, 0);
        final int xoffset = (super.getSize().width - this.buffer.width * this.charWidth) / 2;
        final int yoffset = (super.getSize().height - this.buffer.height * this.charHeight) / 2;
        mousepos.x = (evtpt.x - xoffset) / this.charWidth;
        if (mousepos.x < 0) {
            mousepos.x = 0;
        }
        if (mousepos.x >= this.buffer.width) {
            mousepos.x = this.buffer.width - 1;
        }
        mousepos.y = (evtpt.y - yoffset) / this.charHeight;
        if (mousepos.y < 0) {
            mousepos.y = 0;
        }
        if (mousepos.y >= this.buffer.height) {
            mousepos.y = this.buffer.height - 1;
        }
        return mousepos;
    }
    
    public void setCursorColors(final Color fg, final Color bg) {
        if (fg == null) {
            this.cursorColorFG = this.color[7];
        }
        else {
            this.cursorColorFG = fg;
        }
        if (bg == null) {
            this.cursorColorBG = this.color[0];
        }
        else {
            this.cursorColorBG = bg;
        }
        this.repaint();
    }
    
    public void setBounds(final int x, final int y, int w, int h) {
        super.setBounds(x, y, w, h);
        if (x == 0 && y == 0 && w == 0 && h == 0) {
            return;
        }
        if (this.insets != null) {
            w -= this.insets.left + this.insets.right;
            h -= this.insets.top + this.insets.bottom;
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
            case 2: {
                this.buffer.setScreenSize(w / this.charWidth, this.buffer.height = h / this.charHeight, true);
                break;
            }
            case 1: {
                final int height = h / this.buffer.height;
                final int width = w / this.buffer.width;
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
        this.buffer.markLine(0, this.buffer.height);
    }
    
    public Dimension getSize() {
        int xborder = 0;
        int yborder = 0;
        if (this.insets != null) {
            xborder = this.insets.left + this.insets.right;
            yborder = this.insets.top + this.insets.bottom;
        }
        return new Dimension(this.buffer.width * this.charWidth + xborder, this.buffer.height * this.charHeight + yborder);
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
            final int xoffset = (super.getSize().width - this.buffer.width * this.charWidth) / 2;
            final int yoffset = (super.getSize().height - this.buffer.height * this.charHeight) / 2;
            final int x = (evt.getX() - xoffset) / this.charWidth;
            final int y = (evt.getY() - yoffset) / this.charHeight + this.buffer.windowBase;
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
                this.buffer.update[0] = true;
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
        final int xoffset = (super.getSize().width - this.buffer.width * this.charWidth) / 2;
        final int yoffset = (super.getSize().height - this.buffer.height * this.charHeight) / 2;
        if (this.buffer instanceof VDUInput) {
            ((VDUInput)this.buffer).mousePressed(xoffset, yoffset, evt.getModifiers());
        }
        if (this.buttonCheck(evt.getModifiers(), 16)) {
            this.selectBegin.x = (evt.getX() - xoffset) / this.charWidth;
            this.selectBegin.y = (evt.getY() - yoffset) / this.charHeight + this.buffer.windowBase;
            this.selectEnd.x = this.selectBegin.x;
            this.selectEnd.y = this.selectBegin.y;
        }
    }
    
    public void mouseReleased(final MouseEvent evt) {
        final int xoffset = (super.getSize().width - this.buffer.width * this.charWidth) / 2;
        final int yoffset = (super.getSize().height - this.buffer.height * this.charHeight) / 2;
        if (this.buffer instanceof VDUInput) {
            ((VDUInput)this.buffer).mousePressed(xoffset, yoffset, evt.getModifiers());
        }
        if (this.buttonCheck(evt.getModifiers(), 16)) {
            this.mouseDragged(evt);
            if (this.selectBegin.x == this.selectEnd.x && this.selectBegin.y == this.selectEnd.y) {
                this.buffer.update[0] = true;
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
            if (this.selectEnd.y >= this.buffer.charArray.length) {
                this.selectEnd.y = this.buffer.charArray.length - 1;
            }
            if (this.selectEnd.x > this.buffer.charArray[0].length) {
                this.selectEnd.x = this.buffer.charArray[0].length;
            }
            for (int l = this.selectBegin.y; l <= this.selectEnd.y; ++l) {
                final StringBuffer selectionBuf = new StringBuffer(this.buffer.charArray[0].length);
                int start = (l == this.selectBegin.y) ? (start = this.selectBegin.x) : 0;
                int end = (l == this.selectEnd.y) ? (end = this.selectEnd.x) : this.buffer.charArray[l].length;
                boolean newlineFound = false;
                char ch = ' ';
                for (int i = start; i < end; ++i) {
                    if ((this.buffer.charAttributes[l][i] & 0x10) != 0x0) {
                        ch = ' ';
                    }
                    else {
                        ch = this.buffer.charArray[l][i];
                    }
                    if (ch == '\n') {
                        newlineFound = true;
                    }
                    selectionBuf.append(ch);
                }
                if (!newlineFound) {
                    selectionBuf.append('\n');
                }
                this.selection += ("-" + selectionBuf.toString()).trim().substring(1);
                if (end == this.buffer.charArray[l].length) {
                    this.selection += "\n";
                }
            }
        }
    }
    
    public void keyTyped(final KeyEvent e) {
        if (this.buffer != null && this.buffer instanceof VDUInput) {
            ((VDUInput)this.buffer).keyTyped(e.getKeyCode(), e.getKeyChar(), this.getModifiers(e));
        }
    }
    
    public void keyPressed(final KeyEvent e) {
        if (this.buffer != null && this.buffer instanceof VDUInput) {
            ((VDUInput)this.buffer).keyPressed(e.getKeyCode(), e.getKeyChar(), this.getModifiers(e));
        }
    }
    
    public void keyReleased(final KeyEvent e) {
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
    
    private int getModifiers(final KeyEvent e) {
        return (e.isControlDown() ? 1 : 0) | (e.isShiftDown() ? 2 : 0) | (e.isAltDown() ? 4 : 0) | (e.isActionKey() ? 8 : 0);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
