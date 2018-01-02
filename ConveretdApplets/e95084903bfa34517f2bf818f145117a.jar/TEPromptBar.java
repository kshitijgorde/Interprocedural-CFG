import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class TEPromptBar extends Canvas implements FocusListener, Runnable
{
    private static int LEFT_GAP;
    private static int TOP_GAP;
    private boolean cursorVisible;
    private boolean textIsDefault;
    private boolean yesNoReply;
    private int chDescent;
    private int chHeight;
    private int chWidth;
    private int height;
    private int promptLength;
    private int width;
    private StringBuffer text;
    private Thread cursorThread;
    
    public TEPromptBar(final int n, final int n2, final String s) {
        this(n, n2, s, null);
    }
    
    public TEPromptBar(final int width, final int n, final String s, final String s2) {
        this.width = width;
        this.setBackground(Color.blue);
        this.setForeground(Color.yellow);
        final Font font = new Font("Courier", 0, n);
        this.setFont(font);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        this.chDescent = fontMetrics.getDescent();
        this.chHeight = fontMetrics.getHeight();
        this.chWidth = fontMetrics.charWidth('W');
        this.height = this.chHeight + 2 * TEPromptBar.TOP_GAP;
        this.setSize(this.width, this.height);
        this.addFocusListener(this);
        this.promptLength = s.length();
        this.text = new StringBuffer(s);
        if (s2 != null) {
            this.text.append(s2);
        }
        this.cursorVisible = true;
        this.textIsDefault = false;
        this.yesNoReply = false;
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.startCursorBlinking();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.stopCursorBlinking();
    }
    
    public void run() {
        while (this.cursorThread == Thread.currentThread()) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            this.cursorVisible = !this.cursorVisible;
            this.repaint();
        }
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void paint(final Graphics graphics) {
        this.setBackground(Color.blue);
        this.setForeground(Color.yellow);
        graphics.drawString(this.text.toString(), TEPromptBar.LEFT_GAP, TEPromptBar.TOP_GAP + this.chHeight - this.chDescent);
        if (this.cursorVisible) {
            graphics.fillRect(TEPromptBar.LEFT_GAP + this.chWidth * this.text.length(), TEPromptBar.TOP_GAP, 2, this.chHeight);
        }
    }
    
    public void setBounds(final int n, final int n2, final int width, final int height) {
        super.setBounds(n, n2, width, height);
        this.width = width;
        this.height = height;
    }
    
    public void setSize(final int width, final int height) {
        super.setSize(width, height);
        this.width = width;
        this.height = height;
    }
    
    private boolean isYorN(final char c) {
        return c == 'y' || c == 'Y' || c == 'n' || c == 'N';
    }
    
    public void addCharCmd(final char c) {
        if (this.textIsDefault) {
            this.text.setLength(this.promptLength);
            this.textIsDefault = false;
        }
        if (this.yesNoReply && !this.isYorN(c)) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        this.text.append(c);
        this.repaint();
    }
    
    public void delPrevCharCmd() {
        if (this.textIsDefault) {
            this.textIsDefault = false;
        }
        final int length = this.text.length();
        if (length > this.promptLength) {
            this.text.setLength(length - 1);
        }
        this.repaint();
    }
    
    public void done() {
        this.cursorThread = null;
    }
    
    public String getInputText() {
        final int length = this.text.length();
        final char[] array = new char[length - this.promptLength];
        this.text.getChars(this.promptLength, length, array, 0);
        return new String(array);
    }
    
    public void startCursorBlinking() {
        (this.cursorThread = new Thread(this)).setDaemon(true);
        this.cursorThread.start();
    }
    
    public void stopCursorBlinking() {
        this.cursorThread = null;
        this.cursorVisible = true;
    }
    
    public void setTextIsDefault() {
        this.textIsDefault = true;
    }
    
    public void setYesNoReply() {
        this.yesNoReply = true;
    }
    
    static {
        TEPromptBar.LEFT_GAP = 2;
        TEPromptBar.TOP_GAP = 2;
    }
}
