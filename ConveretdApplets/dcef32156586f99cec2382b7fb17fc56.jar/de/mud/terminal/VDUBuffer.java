// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.terminal;

public class VDUBuffer
{
    public static final String ID = "$Id: VDUBuffer.java 503 2005-10-24 07:34:13Z marcus $";
    public static final int debug = 0;
    public int height;
    public int width;
    public boolean[] update;
    public char[][] charArray;
    public int[][] charAttributes;
    public int bufSize;
    public int maxBufSize;
    public int screenBase;
    public int windowBase;
    public int scrollMarker;
    private int topMargin;
    private int bottomMargin;
    protected boolean showcursor;
    protected int cursorX;
    protected int cursorY;
    public static final boolean SCROLL_UP = false;
    public static final boolean SCROLL_DOWN = true;
    public static final int NORMAL = 0;
    public static final int BOLD = 1;
    public static final int UNDERLINE = 2;
    public static final int INVERT = 4;
    public static final int LOW = 8;
    public static final int INVISIBLE = 16;
    public static final int COLOR_FG_SHIFT = 5;
    public static final int COLOR_BG_SHIFT = 9;
    public static final int COLOR = 8160;
    public static final int COLOR_FG = 480;
    public static final int COLOR_BG = 7680;
    protected VDUDisplay display;
    
    public VDUBuffer(final int width, final int height) {
        this.showcursor = true;
        this.setScreenSize(width, height, false);
    }
    
    public VDUBuffer() {
        this(80, 24);
    }
    
    public void putChar(final int c, final int l, final char ch) {
        this.putChar(c, l, ch, 0);
    }
    
    public void putChar(int c, int l, final char ch, final int attributes) {
        c = this.checkBounds(c, 0, this.width - 1);
        l = this.checkBounds(l, 0, this.height - 1);
        this.charArray[this.screenBase + l][c] = ch;
        this.charAttributes[this.screenBase + l][c] = attributes;
        this.markLine(l, 1);
    }
    
    public char getChar(int c, int l) {
        c = this.checkBounds(c, 0, this.width - 1);
        l = this.checkBounds(l, 0, this.height - 1);
        return this.charArray[this.screenBase + l][c];
    }
    
    public int getAttributes(int c, int l) {
        c = this.checkBounds(c, 0, this.width - 1);
        l = this.checkBounds(l, 0, this.height - 1);
        return this.charAttributes[this.screenBase + l][c];
    }
    
    public void insertChar(int c, int l, final char ch, final int attributes) {
        c = this.checkBounds(c, 0, this.width - 1);
        l = this.checkBounds(l, 0, this.height - 1);
        System.arraycopy(this.charArray[this.screenBase + l], c, this.charArray[this.screenBase + l], c + 1, this.width - c - 1);
        System.arraycopy(this.charAttributes[this.screenBase + l], c, this.charAttributes[this.screenBase + l], c + 1, this.width - c - 1);
        this.putChar(c, l, ch, attributes);
    }
    
    public void deleteChar(int c, int l) {
        c = this.checkBounds(c, 0, this.width - 1);
        l = this.checkBounds(l, 0, this.height - 1);
        if (c < this.width - 1) {
            System.arraycopy(this.charArray[this.screenBase + l], c + 1, this.charArray[this.screenBase + l], c, this.width - c - 1);
            System.arraycopy(this.charAttributes[this.screenBase + l], c + 1, this.charAttributes[this.screenBase + l], c, this.width - c - 1);
        }
        this.putChar(this.width - 1, l, '\0');
    }
    
    public void putString(final int c, final int l, final String s) {
        this.putString(c, l, s, 0);
    }
    
    public void putString(final int c, final int l, final String s, final int attributes) {
        for (int i = 0; i < s.length() && c + i < this.width; ++i) {
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
        l = this.checkBounds(l, 0, this.height - 1);
        char[][] cbuf = null;
        int[][] abuf = null;
        int offset = 0;
        final int oldBase = this.screenBase;
        if (l > this.bottomMargin) {
            return;
        }
        final int top = (l < this.topMargin) ? 0 : ((l > this.bottomMargin) ? ((this.bottomMargin + 1 < this.height) ? (this.bottomMargin + 1) : (this.height - 1)) : this.topMargin);
        final int bottom = (l > this.bottomMargin) ? (this.height - 1) : ((l < this.topMargin) ? ((this.topMargin > 0) ? (this.topMargin - 1) : 0) : this.bottomMargin);
        if (scrollDown) {
            if (n > bottom - top) {
                n = bottom - top;
            }
            cbuf = new char[bottom - l - (n - 1)][this.width];
            abuf = new int[bottom - l - (n - 1)][this.width];
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
                        this.scrollMarker += offset;
                        this.bufSize = this.maxBufSize;
                        this.screenBase = this.maxBufSize - this.height - 1;
                        this.windowBase = this.screenBase;
                    }
                    else {
                        this.scrollMarker += n;
                        this.screenBase += n;
                        this.windowBase += n;
                        this.bufSize += n;
                    }
                    cbuf = new char[this.bufSize][this.width];
                    abuf = new int[this.bufSize][this.width];
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
                if (l < this.height - 1) {
                    System.arraycopy(this.charArray, oldBase + l + 1, cbuf, this.screenBase + l + 1, this.height - 1 - l);
                    System.arraycopy(this.charAttributes, oldBase + l + 1, abuf, this.screenBase + l + 1, this.height - 1 - l);
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
                System.err.println("size.width=" + this.width + ", size.height=" + this.height);
                System.err.println("abuf.length=" + abuf.length + ", cbuf.length=" + cbuf.length);
                System.err.println("*** done dumping debug information");
            }
        }
        this.scrollMarker -= n;
        for (int i = 0; i < n; ++i) {
            cbuf[this.screenBase + l + (scrollDown ? i : (-i))] = new char[this.width];
            abuf[this.screenBase + l + (scrollDown ? i : (-i))] = new int[this.width];
        }
        this.charArray = cbuf;
        this.charAttributes = abuf;
        if (scrollDown) {
            this.markLine(l, bottom - l + 1);
        }
        else {
            this.markLine(top, l - top + 1);
        }
        this.display.updateScrollBar();
    }
    
    public void deleteLine(int l) {
        l = this.checkBounds(l, 0, this.height - 1);
        final int bottom = (l > this.bottomMargin) ? (this.height - 1) : ((l < this.topMargin) ? this.topMargin : (this.bottomMargin + 1));
        System.arraycopy(this.charArray, this.screenBase + l + 1, this.charArray, this.screenBase + l, bottom - l - 1);
        System.arraycopy(this.charAttributes, this.screenBase + l + 1, this.charAttributes, this.screenBase + l, bottom - l - 1);
        this.charArray[this.screenBase + bottom - 1] = new char[this.width];
        this.charAttributes[this.screenBase + bottom - 1] = new int[this.width];
        this.markLine(l, bottom - l);
    }
    
    public void deleteArea(int c, int l, final int w, final int h, final int curAttr) {
        c = this.checkBounds(c, 0, this.width - 1);
        l = this.checkBounds(l, 0, this.height - 1);
        final char[] cbuf = new char[w];
        final int[] abuf = new int[w];
        for (int i = 0; i < w; ++i) {
            abuf[i] = curAttr;
        }
        for (int i = 0; i < h && l + i < this.height; ++i) {
            System.arraycopy(cbuf, 0, this.charArray[this.screenBase + l + i], c, w);
            System.arraycopy(abuf, 0, this.charAttributes[this.screenBase + l + i], c, w);
        }
        this.markLine(l, h);
    }
    
    public void deleteArea(int c, int l, final int w, final int h) {
        c = this.checkBounds(c, 0, this.width - 1);
        l = this.checkBounds(l, 0, this.height - 1);
        final char[] cbuf = new char[w];
        final int[] abuf = new int[w];
        for (int i = 0; i < h && l + i < this.height; ++i) {
            System.arraycopy(cbuf, 0, this.charArray[this.screenBase + l + i], c, w);
            System.arraycopy(abuf, 0, this.charAttributes[this.screenBase + l + i], c, w);
        }
        this.markLine(l, h);
    }
    
    public void showCursor(final boolean doshow) {
        if (doshow != this.showcursor) {
            this.markLine(this.cursorY, 1);
        }
        this.showcursor = doshow;
    }
    
    public void setCursorPosition(final int c, final int l) {
        this.cursorX = this.checkBounds(c, 0, this.width - 1);
        this.markLine(this.cursorY = this.checkBounds(l, 0, this.height - 1), 1);
    }
    
    public int getCursorColumn() {
        return this.cursorX;
    }
    
    public int getCursorRow() {
        return this.cursorY;
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
        if (this.bottomMargin > this.height - 1) {
            this.bottomMargin = this.height - 1;
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
        if (this.bottomMargin > this.height - 1) {
            this.bottomMargin = this.height - 1;
        }
    }
    
    public int getBottomMargin() {
        return this.bottomMargin;
    }
    
    public void setBufferSize(int amount) {
        if (amount < this.height) {
            amount = this.height;
        }
        if (amount < this.maxBufSize) {
            final char[][] cbuf = new char[amount][this.width];
            final int[][] abuf = new int[amount][this.width];
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
            this.screenBase = this.bufSize - this.height;
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
    
    public void setScreenSize(final int w, final int h, final boolean broadcast) {
        final int bsize = this.bufSize;
        if (w < 1 || h < 1) {
            return;
        }
        if (h > this.maxBufSize) {
            this.maxBufSize = h;
        }
        if (h > this.bufSize) {
            this.bufSize = h;
            this.screenBase = 0;
            this.windowBase = 0;
        }
        if (this.windowBase + h >= this.bufSize) {
            this.windowBase = this.bufSize - h;
        }
        if (this.screenBase + h >= this.bufSize) {
            this.screenBase = this.bufSize - h;
        }
        final char[][] cbuf = new char[this.bufSize][w];
        final int[][] abuf = new int[this.bufSize][w];
        if (this.charArray != null && this.charAttributes != null) {
            for (int i = 0; i < bsize && i < this.bufSize; ++i) {
                System.arraycopy(this.charArray[i], 0, cbuf[i], 0, (w < this.width) ? w : this.width);
                System.arraycopy(this.charAttributes[i], 0, abuf[i], 0, (w < this.width) ? w : this.width);
            }
        }
        this.charArray = cbuf;
        this.charAttributes = abuf;
        this.width = w;
        this.height = h;
        this.topMargin = 0;
        this.bottomMargin = h - 1;
        (this.update = new boolean[h + 1])[0] = true;
    }
    
    public int getRows() {
        return this.height;
    }
    
    public int getColumns() {
        return this.width;
    }
    
    public void markLine(int l, final int n) {
        l = this.checkBounds(l, 0, this.height - 1);
        for (int i = 0; i < n && l + i < this.height; ++i) {
            this.update[l + i + 1] = true;
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
    
    public void setDisplay(final VDUDisplay display) {
        this.display = display;
    }
    
    protected void redraw() {
        if (this.display != null) {
            this.display.redraw();
        }
    }
}
