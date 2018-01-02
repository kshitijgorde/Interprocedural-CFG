// 
// Decompiled by Procyon v0.5.30
// 

package hades.models.special;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import javafig.gui.ImageHelper;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

public class TextLCDCanvas extends Canvas
{
    private Image characterMatrix;
    private Image[] characterImage;
    private int[] cgRAM;
    private Color lcdBackground;
    private int n_rows;
    private int n_cols;
    private final int MEM_SIZE;
    private final int NO_SUCH_DATA;
    private int[] data;
    private int char_width;
    private int char_height;
    private int left_border;
    private int top_border;
    private int char_spacer;
    private int base_address;
    private int cursor_address;
    private boolean display_enable;
    private boolean cursor_enable;
    private boolean cursor_blink;
    private boolean cursor_is_small;
    private boolean two_line_enable;
    
    public void initializeData() {
        this.data = new int[80];
        for (int r = 0; r < this.n_rows; ++r) {
            for (int c = 0; c < this.n_cols; ++c) {
                this.data[r * this.n_cols + c] = 32;
            }
        }
    }
    
    public void initializeHamburgData() {
        this.data = new int[80];
        final String[] hugo = { "Hello, world! This is HADES 0.67i                    ", "(c) 1999 University of Hamburg, FBI TECH             " };
        for (int r = 0; r < this.n_rows; ++r) {
            for (int c = 0; c < this.n_cols; ++c) {
                this.data[r * this.n_cols + c] = hugo[r].charAt(c);
            }
        }
    }
    
    public void clearDisplay() {
        for (int i = 0; i < this.data.length; ++i) {
            this.data[i] = 32;
        }
        this.repaint();
    }
    
    public void enableDisplay(final boolean b) {
        this.display_enable = b;
        this.repaint();
    }
    
    public void selectTwoLines(final boolean b) {
        this.two_line_enable = (b & this.n_rows > 1);
        this.repaint();
    }
    
    public void enableCursor(final boolean b) {
        this.cursor_enable = b;
    }
    
    public void selectUnderlineCursor(final boolean b) {
        this.cursor_is_small = b;
    }
    
    public void enableCursorBlinking(final boolean b) {
        this.cursor_blink = b;
    }
    
    public void setCursorAddress(final int address) {
        this.cursor_address = address;
    }
    
    public int getCursorAddress() {
        return this.cursor_address;
    }
    
    public void cursorHome() {
        this.setCursorAddress(0);
        this.setBaseAddress(0);
        this.repaint();
    }
    
    public void moveCursorLeft() {
        --this.cursor_address;
        if (this.cursor_address < 0) {
            this.cursor_address = this.data.length;
        }
    }
    
    public void moveCursorRight() {
        ++this.cursor_address;
        if (this.cursor_address >= this.data.length) {
            this.cursor_address = 0;
        }
    }
    
    public void setBaseAddress(final int address) {
        this.base_address = address;
    }
    
    public void rotateLeft() {
        ++this.base_address;
        this.repaint();
    }
    
    public void rotateRight() {
        --this.base_address;
        this.repaint();
    }
    
    public void createBlinkTimer() {
        if (this == null) {
            throw null;
        }
        final BlinkTimer blinkTimer = new BlinkTimer();
        blinkTimer.start();
    }
    
    public void loadCharacterMatrix() {
        this.characterMatrix = ImageHelper.loadResourceImage("/hades/gui/images/lcd-matrix.gif");
        this.characterImage = new Image[256];
        Graphics cg = null;
        for (int row = 0; row < 16; ++row) {
            for (int col = 0; col < 16; ++col) {
                this.characterImage[col * 16 + row] = ImageHelper.createImage(this.char_width, this.char_height);
                cg = this.characterImage[col * 16 + row].getGraphics();
                cg.setColor(Color.blue);
                cg.fillRect(0, 0, this.char_width, this.char_height);
                cg.drawImage(this.characterMatrix, 0, 0, this.char_width, this.char_height, 2 + col * 16, 2 + row * 22, 2 + col * 16 + this.char_width, 2 + row * 22 + this.char_height, null);
                cg.dispose();
            }
        }
    }
    
    public void setDataAt(final int address, final int value) {
        final int a = address & 0x7F;
        if (this.two_line_enable) {
            if (a >= 0 && a <= 39) {
                this.data[a] = value;
            }
            else {
                if (a < 64 || a > 103) {
                    msg("-W- Invalid address " + a + " in setDataAt(), ignored");
                    return;
                }
                this.data[a - 24] = value;
            }
        }
        else {
            if (a < 0 || a > 79) {
                msg("-W- Invalid address " + a + " in setDataAt(), ignored");
                return;
            }
            this.data[a] = value;
        }
    }
    
    public int getDataAt(final int addr) {
        if (this.two_line_enable) {
            if (addr >= 0 && addr <= 39) {
                return this.data[addr];
            }
            if (addr >= 64 && addr <= 103) {
                return this.data[addr - 24];
            }
            return 32;
        }
        else {
            if (addr >= 0 && addr <= 79) {
                return this.data[addr];
            }
            return 32;
        }
    }
    
    public int getDataAt(final int row, final int column) {
        int addr = row * this.n_cols + column;
        if (addr < 0) {
            addr = 0;
        }
        if (addr > this.data.length) {
            addr = 0;
        }
        return this.data[addr];
    }
    
    public int getRow(final int address) {
        int row = 0;
        if (this.two_line_enable && address >= 64) {
            row = 1;
        }
        return row;
    }
    
    public int getColumn(int address) {
        int col = 0;
        if (this.two_line_enable) {
            if (address >= 64) {
                address -= 64;
            }
            col = address - this.base_address;
        }
        else {
            col = address - this.base_address;
        }
        return col;
    }
    
    public int getPixelX(final int address) {
        final int c = this.getColumn(address);
        return this.left_border + c * this.char_width;
    }
    
    public int getPixelY(final int address) {
        final int r = this.getRow(address);
        return this.top_border + r * (this.char_height + this.char_spacer);
    }
    
    public boolean isVisibleAddress(final int address) {
        final int col = this.getColumn(address);
        return col >= 0 && col < this.n_cols;
    }
    
    public void paint(final Graphics g) {
        int address = 0;
        g.clearRect(0, 0, this.getSize().width, this.getSize().height);
        if (!this.display_enable) {
            return;
        }
        if (this.two_line_enable) {
            for (int c = 0; c < this.n_cols; ++c) {
                address = this.base_address + c;
                g.drawImage(this.characterImage[this.getDataAt(address)], this.getPixelX(address), this.getPixelY(address), null);
            }
            for (int c = 0; c < this.n_cols; ++c) {
                address = this.base_address + 64 + c;
                g.drawImage(this.characterImage[this.getDataAt(address)], this.getPixelX(address), this.getPixelY(address), null);
            }
        }
        else {
            for (int c = 0; c < this.n_cols; ++c) {
                address = this.base_address + c;
                g.drawImage(this.characterImage[this.getDataAt(address)], this.getPixelX(address), this.getPixelY(address), null);
            }
        }
        g.dispose();
    }
    
    public void repaintAt(final int address) {
        if (!this.display_enable) {
            return;
        }
        if (!this.isVisibleAddress(address)) {
            return;
        }
        final Graphics g = this.getGraphics();
        g.drawImage(this.characterImage[this.getDataAt(address)], this.getPixelX(address), this.getPixelY(address), null);
        g.dispose();
    }
    
    public void hideCursor(final int address) {
        if (!this.display_enable) {
            return;
        }
        if (!this.isVisibleAddress(address)) {
            return;
        }
        final Graphics g = this.getGraphics();
        g.drawImage(this.characterImage[this.getDataAt(address)], this.getPixelX(address), this.getPixelY(address), null);
        g.dispose();
    }
    
    public void showCursor(final int address) {
        if (!this.display_enable) {
            return;
        }
        if (!this.isVisibleAddress(address)) {
            return;
        }
        final Graphics g = this.getGraphics();
        final int x = this.getPixelX(address);
        final int y = this.getPixelY(address);
        g.drawImage(this.characterImage[this.getDataAt(address)], x, y, null);
        if (this.cursor_enable) {
            if (this.cursor_is_small) {
                g.setColor(Color.black);
                g.drawLine(x + 2, y + 16, x + 2 + 9, y + 16);
                g.drawLine(x + 2, y + 17, x + 2 + 9, y + 17);
            }
            else {
                g.drawImage(this.characterImage[255], x, y, null);
            }
        }
        g.dispose();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(2 * this.left_border + this.n_cols * this.char_width, 2 * this.top_border + this.n_rows * this.char_height + (this.n_rows - 1) * this.char_spacer);
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    private void createCGRam() {
        this.cgRAM = new int[64];
        for (int i = 0; i < this.cgRAM.length; ++i) {
            this.cgRAM[i] = 0;
        }
    }
    
    public void writeCGRam(final int address, final int value) {
        if (this.cgRAM == null) {
            this.createCGRam();
        }
        this.cgRAM[address & 0x3F] = (value & 0xFF);
        final int char_address = (address & 0x38) >> 3;
        final int row_address = address & 0x7;
        final int pixels = value & 0x1F;
        final Image target = this.characterImage[char_address];
        final Graphics g = target.getGraphics();
        g.setColor(this.lcdBackground);
        g.fillRect(0, 2 * row_address, this.char_width, 2);
        g.setColor(Color.black);
        int mask = 16;
        int x = 2;
        int y = 2 * row_address;
        if (row_address == 7) {
            y += 2;
        }
        for (int i = 0; i < 5; ++i) {
            if ((pixels & mask) > 0) {
                g.fillRect(x, y, 2, 2);
            }
            mask >>>= 1;
            x += 2;
        }
    }
    
    public int readCGRam(final int address) {
        if (this.cgRAM == null) {
            this.createCGRam();
        }
        return this.cgRAM[address & 0x3F];
    }
    
    public static void msg(final String msg) {
        System.out.println(msg);
    }
    
    public static void main(final String[] argv) {
        System.out.println("TextLCDCanvas selftest, please wait...");
        final Frame frame = new Frame("TextLCDCanvas selftest");
        frame.setSize(100, 50);
        frame.show();
        ImageHelper.setVisibleParent(frame);
        final TextLCDCanvas lcd = new TextLCDCanvas(2, 40);
        frame.add("Center", lcd);
        frame.pack();
        frame.show();
        try {
            Thread.currentThread();
            Thread.sleep(3000L);
        }
        catch (Exception ex) {}
        lcd.enableDisplay(true);
        for (int i = 0; i < 128; ++i) {
            final int value = (int)(32.0 * Math.random());
            lcd.writeCGRam(i, value);
        }
        for (int i = 0; i < 8; ++i) {
            lcd.setDataAt(3 * i, i);
        }
        lcd.repaint();
        while (true) {
            final int pos = (int)(128.0 * Math.random());
            final int chr = (int)(256.0 * Math.random());
            lcd.setDataAt(pos, chr);
            lcd.repaint();
            try {
                Thread.currentThread();
                Thread.sleep(100L);
            }
            catch (Exception ex2) {}
        }
    }
    
    public TextLCDCanvas(final int n_rows, final int n_cols) {
        this.MEM_SIZE = 80;
        this.NO_SUCH_DATA = 32;
        this.char_width = 12;
        this.char_height = 20;
        this.left_border = 5;
        this.top_border = 5;
        this.char_spacer = 2;
        this.base_address = 0;
        this.cursor_address = 0;
        this.display_enable = false;
        this.cursor_enable = false;
        this.cursor_blink = false;
        this.cursor_is_small = false;
        this.two_line_enable = true;
        this.n_rows = Math.min(n_rows, 2);
        this.n_cols = Math.min(n_cols, 40);
        this.initializeData();
        this.setBackground(this.lcdBackground = new Color(191, 223, 0));
        this.loadCharacterMatrix();
        this.createBlinkTimer();
    }
    
    protected class BlinkTimer extends Thread
    {
        int last_address;
        int new_address;
        
        public void run() {
            try {
                while (true) {
                    Thread.currentThread();
                    Thread.sleep(500L);
                    this.new_address = TextLCDCanvas.this.getCursorAddress();
                    TextLCDCanvas.this.hideCursor(this.last_address);
                    TextLCDCanvas.this.showCursor(this.new_address);
                    this.last_address = this.new_address;
                    Thread.currentThread();
                    Thread.sleep(500L);
                    TextLCDCanvas.this.hideCursor(this.new_address);
                }
            }
            catch (InterruptedException e) {
                System.out.println("-E- BlinkTimer: " + e);
            }
        }
        
        public BlinkTimer() {
            this.last_address = 0;
            this.new_address = 0;
            this.setDaemon(true);
        }
    }
}
