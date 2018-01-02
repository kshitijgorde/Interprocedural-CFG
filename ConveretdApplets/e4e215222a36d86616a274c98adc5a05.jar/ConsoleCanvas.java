import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ConsoleCanvas extends Canvas implements FocusListener, KeyListener, MouseListener
{
    protected StringBuffer typeAhead;
    protected final int maxLineLength = 256;
    protected int rows;
    protected int columns;
    protected int currentRow;
    protected int currentCol;
    protected Font font;
    protected int lineHeight;
    protected int baseOffset;
    protected int charWidth;
    protected int leading;
    protected int topOffset;
    protected int leftOffset;
    protected Image OSC;
    protected Graphics OSCGraphics;
    protected boolean hasFocus;
    protected boolean cursorIsVisible;
    private int pos;
    
    public ConsoleCanvas() {
        this.typeAhead = new StringBuffer();
        this.hasFocus = false;
        this.cursorIsVisible = false;
        this.pos = 0;
        this.addFocusListener(this);
        this.addKeyListener(this);
    }
    
    public final String readLine() {
        return this.doReadLine();
    }
    
    public final void addChar(final char ch) {
        this.putChar(ch);
    }
    
    public final void addCR() {
        this.putCR();
    }
    
    public synchronized void clear() {
        if (this.OSC == null) {
            return;
        }
        this.currentRow = 0;
        this.currentCol = 0;
        this.OSCGraphics.setColor(Color.white);
        this.OSCGraphics.fillRect(4, 4, this.getSize().width - 8, this.getSize().height - 8);
        this.OSCGraphics.setColor(Color.black);
        this.repaint();
        try {
            Thread.sleep(25L);
        }
        catch (InterruptedException ex) {}
    }
    
    public void keyPressed(final KeyEvent evt) {
        this.doKey(evt.getKeyChar());
    }
    
    public void keyReleased(final KeyEvent evt) {
    }
    
    public void keyTyped(final KeyEvent evt) {
    }
    
    public void focusGained(final FocusEvent evt) {
        this.doFocus(true);
    }
    
    public void focusLost(final FocusEvent evt) {
        this.doFocus(false);
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    public void mousePressed(final MouseEvent evt) {
        this.requestFocus();
    }
    
    public void mouseReleased(final MouseEvent evt) {
    }
    
    public void mouseClicked(final MouseEvent evt) {
    }
    
    public void mouseEntered(final MouseEvent evt) {
    }
    
    public void mouseExited(final MouseEvent evt) {
    }
    
    public synchronized void clearTypeAhead() {
        this.typeAhead.setLength(0);
        this.pos = -1;
        this.notify();
    }
    
    protected synchronized String doReadLine() {
        if (this.OSC == null) {
            try {
                this.wait(5000L);
            }
            catch (InterruptedException ex) {}
        }
        if (this.OSC == null) {
            this.doSetup();
        }
        if (!this.hasFocus) {
            this.requestFocus();
        }
        final StringBuffer lineBuffer = new StringBuffer();
        this.pos = 0;
        while (true) {
            if (this.pos >= this.typeAhead.length()) {
                this.cursorBlink();
                try {
                    this.wait(500L);
                }
                catch (InterruptedException e) {}
            }
            else {
                if (this.pos == -1) {
                    return "";
                }
                if (this.cursorIsVisible) {
                    this.cursorBlink();
                }
                if (this.typeAhead.charAt(this.pos) == '\r' || this.typeAhead.charAt(this.pos) == '\n') {
                    this.putCR();
                    ++this.pos;
                    break;
                }
                if (this.typeAhead.charAt(this.pos) == '\b' || this.typeAhead.charAt(this.pos) == '\u007f') {
                    if (lineBuffer.length() > 0) {
                        lineBuffer.setLength(lineBuffer.length() - 1);
                        this.eraseChar();
                    }
                    ++this.pos;
                }
                else if (this.typeAhead.charAt(this.pos) >= ' ' && this.typeAhead.charAt(this.pos) < '\u007f') {
                    this.putChar(this.typeAhead.charAt(this.pos));
                    lineBuffer.append(this.typeAhead.charAt(this.pos));
                    ++this.pos;
                }
                else {
                    ++this.pos;
                }
                if (lineBuffer.length() == 256) {
                    this.putCR();
                    this.pos = this.typeAhead.length();
                    break;
                }
                continue;
            }
        }
        if (this.pos >= this.typeAhead.length()) {
            this.typeAhead.setLength(0);
        }
        else {
            final int len = this.typeAhead.length();
            for (int i = this.pos; i < len; ++i) {
                this.typeAhead.setCharAt(i - this.pos, this.typeAhead.charAt(i));
            }
            this.typeAhead.setLength(len - this.pos);
        }
        return lineBuffer.toString();
    }
    
    protected synchronized void doKey(final char ch) {
        this.typeAhead.append(ch);
        this.notify();
    }
    
    private void putCursor(final Graphics g) {
        g.drawLine(this.leftOffset + this.currentCol * this.charWidth + 1, this.topOffset + this.currentRow * this.lineHeight, this.leftOffset + this.currentCol * this.charWidth + 1, this.topOffset + (this.currentRow * this.lineHeight + this.baseOffset));
    }
    
    protected synchronized void putChar(final char ch) {
        if (this.OSC == null) {
            try {
                this.wait(5000L);
            }
            catch (InterruptedException ex) {}
        }
        if (this.OSC == null) {
            this.doSetup();
        }
        if (this.currentCol >= this.columns) {
            this.putCR();
        }
        ++this.currentCol;
        final Graphics g = this.getGraphics();
        g.setColor(Color.black);
        g.setFont(this.font);
        final char[] fudge = { ch };
        g.drawChars(fudge, 0, 1, this.leftOffset + (this.currentCol - 1) * this.charWidth, this.topOffset + this.currentRow * this.lineHeight + this.baseOffset);
        this.OSCGraphics.drawChars(fudge, 0, 1, this.leftOffset + (this.currentCol - 1) * this.charWidth, this.topOffset + this.currentRow * this.lineHeight + this.baseOffset);
    }
    
    protected void eraseChar() {
        if (this.currentCol == 0 && this.currentRow == 0) {
            return;
        }
        --this.currentCol;
        if (this.currentCol < 0) {
            --this.currentRow;
            this.currentCol = this.columns - 1;
        }
        final Graphics g = this.getGraphics();
        g.setColor(Color.white);
        g.fillRect(this.leftOffset + this.currentCol * this.charWidth, this.topOffset + this.currentRow * this.lineHeight, this.charWidth, this.lineHeight - 1);
        this.OSCGraphics.setColor(Color.white);
        this.OSCGraphics.fillRect(this.leftOffset + this.currentCol * this.charWidth, this.topOffset + this.currentRow * this.lineHeight, this.charWidth, this.lineHeight - 1);
        this.OSCGraphics.setColor(Color.black);
    }
    
    protected synchronized void putCR() {
        if (this.OSC == null) {
            try {
                this.wait(5000L);
            }
            catch (InterruptedException ex) {}
        }
        if (this.OSC == null) {
            this.doSetup();
        }
        this.currentCol = 0;
        ++this.currentRow;
        if (this.currentRow < this.rows) {
            return;
        }
        this.OSCGraphics.copyArea(this.leftOffset, this.topOffset + this.lineHeight, this.columns * this.charWidth, (this.rows - 1) * this.lineHeight - this.leading, 0, -this.lineHeight);
        this.OSCGraphics.setColor(Color.white);
        this.OSCGraphics.fillRect(this.leftOffset, this.topOffset + (this.rows - 1) * this.lineHeight, this.columns * this.charWidth, this.lineHeight - this.leading);
        this.OSCGraphics.setColor(Color.black);
        this.currentRow = this.rows - 1;
        this.repaint();
        try {
            Thread.sleep(25L);
        }
        catch (InterruptedException ex2) {}
    }
    
    protected void cursorBlink() {
        if (this.cursorIsVisible) {
            final Graphics g = this.getGraphics();
            g.setColor(Color.white);
            this.putCursor(g);
            this.cursorIsVisible = false;
            g.dispose();
        }
        else if (this.hasFocus) {
            final Graphics g = this.getGraphics();
            g.setColor(Color.black);
            this.putCursor(g);
            this.cursorIsVisible = true;
            g.dispose();
        }
    }
    
    protected synchronized void doFocus(final boolean focus) {
        if (this.OSC == null) {
            this.doSetup();
        }
        this.hasFocus = focus;
        if (this.hasFocus) {
            this.OSCGraphics.setColor(Color.cyan);
        }
        else {
            this.OSCGraphics.setColor(Color.white);
        }
        final int w = this.getSize().width;
        final int h = this.getSize().height;
        for (int i = 0; i < 3; ++i) {
            this.OSCGraphics.drawRect(i, i, w - 2 * i, h - 2 * i);
        }
        this.OSCGraphics.drawLine(0, h - 3, w, h - 3);
        this.OSCGraphics.drawLine(w - 3, 0, w - 3, h);
        this.OSCGraphics.setColor(Color.black);
        this.repaint();
        try {
            Thread.sleep(50L);
        }
        catch (InterruptedException ex) {}
        this.notify();
    }
    
    protected void doSetup() {
        final int w = this.getSize().width;
        final int h = this.getSize().height;
        this.font = new Font("Courier", 0, this.getFont().getSize());
        final FontMetrics fm = this.getFontMetrics(this.font);
        this.lineHeight = fm.getHeight();
        this.leading = fm.getLeading();
        this.baseOffset = fm.getAscent();
        this.charWidth = fm.charWidth('W');
        this.columns = (w - 12) / this.charWidth;
        this.rows = (h - 12 + this.leading) / this.lineHeight;
        this.leftOffset = (w - this.columns * this.charWidth) / 2;
        this.topOffset = (h + this.leading - this.rows * this.lineHeight) / 2;
        this.OSC = this.createImage(w, h);
        (this.OSCGraphics = this.OSC.getGraphics()).setFont(this.font);
        this.OSCGraphics.setColor(Color.white);
        this.OSCGraphics.fillRect(0, 0, w, h);
        this.OSCGraphics.setColor(Color.black);
        this.notify();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public synchronized void paint(final Graphics g) {
        if (this.OSC == null) {
            this.doSetup();
        }
        g.drawImage(this.OSC, 0, 0, this);
    }
}
