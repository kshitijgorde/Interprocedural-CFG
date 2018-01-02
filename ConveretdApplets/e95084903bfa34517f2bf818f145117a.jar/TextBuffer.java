import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.FocusListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextBuffer extends Canvas implements FocusListener, MouseListener, MouseMotionListener, Runnable
{
    public static final int MAX_LINE_CHARS = 1024;
    public static final int MINIMUM_HEIGHT = 48;
    public static final int MINIMUM_WIDTH = 210;
    private static final char EM = '\u0019';
    private static final char LF = '\n';
    private static final Color BGC;
    private static final Color FGC;
    private static final int FONT_SIZE = 16;
    private static final int DEFAULT_HEIGHT = 136;
    private static final int NLIN = 8000;
    private static final int MAXLINIDX = 7999;
    private static final int SCROLL_COUNT = 400;
    private static final int WIDTH = 620;
    private static final String CLASS_NAME = "TextBuffer";
    private static final int ERRMSG_TOP_Y = 3;
    private static final int IDLE = 0;
    private static final int CLICK = 1;
    private static final int ERRMSG = 2;
    private static final int SCROLL = 3;
    private static final int SELECT = 4;
    private static int tbObjNum;
    private boolean cursorVisible;
    private boolean hasFocus;
    private boolean lineModified;
    private boolean mouseCursorMovement;
    private boolean scrolling;
    private Font stdFont;
    private Graphics myOffScreenGraphics;
    private Image myOffScreenImage;
    private int chDescent;
    private int chHeight;
    private int chWidth;
    private int curCol;
    private int curLin;
    private int errMsgRightX;
    private int height;
    private int leftGap;
    private int mouseAction;
    private int mousePressedX;
    private int mousePressedY;
    private int numDispLines;
    private int lastLin;
    private int topGap;
    private int topLinNum;
    private int ttNum;
    private int width;
    private long mousePressTime;
    private StringBuffer[] buffer;
    private Thread cursorThread;
    private TBScrollbar sbar;
    private TextSelection txtSel;
    private TextBufferListener tbListener;
    private Vector errMsgs;
    
    public TextBuffer(final int n, final int n2, final int n3) {
        this(n, n2, n3, true);
    }
    
    public TextBuffer(final int n, final int n2, int n3, final boolean mouseCursorMovement) {
        this.ttNum = ++TextBuffer.tbObjNum;
        this.setBackground(TextBuffer.BGC);
        this.setForeground(TextBuffer.FGC);
        if (n3 == 0) {
            n3 = 16;
        }
        this.setFont(this.stdFont = new Font("Courier", 0, n3));
        final FontMetrics fontMetrics = this.getFontMetrics(this.stdFont);
        this.chDescent = fontMetrics.getDescent();
        this.chHeight = fontMetrics.getHeight();
        this.chWidth = fontMetrics.charWidth('W');
        final int charWidth = fontMetrics.charWidth('i');
        if (this.chWidth != charWidth) {
            System.err.println("TextBuffer: Didn't get a fixed-width font!");
            System.err.print("            'W' width: " + this.chWidth);
            System.err.println(", 'i' width:" + charWidth);
        }
        this.height = ((n2 == 0) ? 136 : n2);
        if (this.height < 48) {
            this.height = 48;
        }
        this.width = ((n == 0) ? 620 : n);
        if (this.width < 210) {
            this.width = 210;
        }
        super.setSize(this.width, this.height);
        this.errMsgRightX = this.width - (TBScrollbar.WIDTH + 3);
        this.sbar = new TBScrollbar(this, this.width - TBScrollbar.WIDTH, this.height);
        this.topGap = this.height % this.chHeight / 2;
        if (this.topGap == 0) {
            this.topGap = this.chHeight / 2;
        }
        this.numDispLines = (this.height - this.topGap * 2) / this.chHeight;
        this.sbar.setNumVisibleLines(this.numDispLines);
        this.leftGap = this.width % this.chWidth / 2;
        if (this.leftGap == 0) {
            this.leftGap = this.chWidth / 2;
        }
        this.topLinNum = 0;
        this.sbar.setTopLineNum(this.topLinNum);
        (this.buffer = new StringBuffer[8000])[0] = new StringBuffer();
        this.curCol = 0;
        this.curLin = 0;
        this.lastLin = 0;
        this.sbar.setNumLines(1);
        this.errMsgs = new Vector(10, 5);
        this.cursorVisible = true;
        this.hasFocus = false;
        this.lineModified = false;
        this.scrolling = false;
        this.mouseCursorMovement = mouseCursorMovement;
        this.mouseAction = 0;
        this.mousePressTime = -1L;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addFocusListener(this);
        this.txtSel = new TextSelection();
        this.tbListener = null;
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.notifyFocusGained();
        this.hasFocus = true;
        this.startCursorBlinking();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.stopCursorBlinking();
        if (this.lineModified) {
            this.notifyLineModified(this.curLin);
        }
        this.notifyFocusLost();
        this.hasFocus = false;
    }
    
    private void fwdMouseEvent(final int n, final MouseEvent mouseEvent) {
        final boolean popupTrigger = mouseEvent.isPopupTrigger();
        final long when = mouseEvent.getWhen();
        final int clickCount = mouseEvent.getClickCount();
        final int modifiers = mouseEvent.getModifiers();
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final Rectangle bounds = this.getBounds();
        int n2 = x + bounds.x;
        int n3 = y + bounds.y;
        for (Container container = this.getParent(); container != null; container = container.getParent()) {
            final Class<?>[] interfaces = container.getClass().getInterfaces();
            int i = 0;
            while (i < interfaces.length) {
                if (interfaces[i].getName().equals("java.awt.event.MouseListener")) {
                    final MouseEvent mouseEvent2 = new MouseEvent(container, n, when, modifiers, n2, n3, clickCount, popupTrigger);
                    switch (n) {
                        case 501: {
                            ((MouseListener)container).mousePressed(mouseEvent2);
                            return;
                        }
                        case 502: {
                            ((MouseListener)container).mouseReleased(mouseEvent2);
                            return;
                        }
                        case 500: {
                            ((MouseListener)container).mouseClicked(mouseEvent2);
                            return;
                        }
                        case 504: {
                            ((MouseListener)container).mouseEntered(mouseEvent2);
                            return;
                        }
                        case 505: {
                            ((MouseListener)container).mouseExited(mouseEvent2);
                            return;
                        }
                        case 503: {
                            ((MouseMotionListener)container).mouseMoved(mouseEvent2);
                            return;
                        }
                        case 506: {
                            ((MouseMotionListener)container).mouseDragged(mouseEvent2);
                            return;
                        }
                        default: {
                            System.err.println("TextBuffer.fwdMouseEvent: bad id");
                            return;
                        }
                    }
                }
                else {
                    ++i;
                }
            }
            final Rectangle bounds2 = container.getBounds();
            n2 += bounds2.x;
            n3 += bounds2.y;
        }
    }
    
    private int toColumn(final int n) {
        return (n - this.leftGap) / this.chWidth;
    }
    
    private int toLine(final int n) {
        return (n - this.topGap) / this.chHeight;
    }
    
    private int moveCursor(int n, int n2) {
        if (n < 0) {
            n = 0;
        }
        if (n >= this.width) {
            n = this.width - 1;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 >= this.height) {
            n2 = this.height - 1;
        }
        return this.setCurLinNumCmd(this.topLinNum + this.toLine(n2)) * 1024 + this.setColNum(this.toColumn(n));
    }
    
    private void modifySelectionOp(final int n, final int n2) {
        final int moveCursor = this.moveCursor(n, n2);
        this.txtSel.setSelectionEnd(moveCursor / 1024, moveCursor % 1024);
    }
    
    private void wrapupSelectionOp(final int n, final int n2) {
        final int moveCursor = this.moveCursor(n, n2);
        this.txtSel.setSelectionEnd(moveCursor / 1024, moveCursor % 1024);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.fwdMouseEvent(500, mouseEvent);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.fwdMouseEvent(504, mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.fwdMouseEvent(505, mouseEvent);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getModifiers() == 16) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            if (!this.errMsgs.isEmpty() && this.errMsgs.firstElement().mouseInErrMsg(x, y)) {
                this.mouseAction = 2;
                return;
            }
            if (x >= this.width - TBScrollbar.WIDTH) {
                this.sbar.mousePressed(y);
                this.mousePressTime = -1L;
                this.mouseAction = 3;
                return;
            }
            if (this.mouseCursorMovement) {
                if (x < 0) {
                    x = 0;
                }
                if (y < 0) {
                    y = 0;
                }
                if (y >= this.height) {
                    y = this.height - 1;
                }
                this.mousePressedX = x;
                this.mousePressedY = y;
                this.mouseAction = 1;
                this.mousePressTime = System.currentTimeMillis();
            }
            else {
                this.chkAdjCurLinBottom();
            }
        }
        else {
            this.fwdMouseEvent(501, mouseEvent);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final int modifiers = mouseEvent.getModifiers();
        if (modifiers == 16) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            switch (this.mouseAction) {
                case 2: {
                    if (this.errMsgs.firstElement().mouseInCloseBox(x, y)) {
                        this.errMsgs.removeElementAt(0);
                        break;
                    }
                    break;
                }
                case 3: {
                    this.sbar.mouseReleased(y);
                    this.mousePressTime = -1L;
                    break;
                }
                case 1: {
                    if (this.mouseCursorMovement) {
                        this.moveCursor(x, y);
                        this.mousePressTime = -1L;
                        break;
                    }
                    break;
                }
                case 4: {
                    if (this.mouseCursorMovement) {
                        this.wrapupSelectionOp(x, y);
                        break;
                    }
                    break;
                }
            }
            this.mouseAction = 0;
        }
        else if (modifiers != 16) {
            this.fwdMouseEvent(502, mouseEvent);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (System.currentTimeMillis() - this.mousePressTime < 300L) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        switch (this.mouseAction) {
            case 3: {
                this.sbar.mouseDragged(y);
                break;
            }
            case 1: {
                if (this.mouseCursorMovement) {
                    this.txtSel.setSelectionStart(this.setCurLinNumCmd(this.topLinNum + this.toLine(this.mousePressedY)), this.setColNum(this.toColumn(this.mousePressedX)));
                }
                this.mouseAction = 4;
            }
            case 4: {
                if (this.mouseCursorMovement) {
                    this.modifySelectionOp(x, y);
                    break;
                }
                break;
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
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
        return new Dimension(210, 48);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    private int leftEdge(final int n) {
        return this.chWidth * n;
    }
    
    private int rightEdge(final int n) {
        return this.chWidth * n + this.chWidth - 1;
    }
    
    private int topEdge(final int n) {
        return this.chHeight * n;
    }
    
    private void paintColoredText(final Graphics graphics, final ColoredText[] array, final int n) {
        int leftGap = this.leftGap;
        for (int n2 = 0; n2 < array.length && array[n2] != null; ++n2) {
            if (array[n2].bg == TextBuffer.BGC && array[n2].fg == TextBuffer.FGC) {
                graphics.drawString(array[n2].text, leftGap, n);
            }
            else {
                if (array[n2].bg != TextBuffer.BGC) {
                    graphics.setColor(array[n2].bg);
                    graphics.fillRect(leftGap, n - (this.chHeight - this.chDescent), array[n2].text.length() * this.chWidth, this.chHeight);
                }
                graphics.setColor(array[n2].fg);
                graphics.drawString(array[n2].text, leftGap, n);
                graphics.setColor(TextBuffer.FGC);
                graphics.setFont(this.stdFont);
            }
            leftGap += array[n2].text.length() * this.chWidth;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.clearRect(0, 0, this.width, this.height);
        graphics.setColor(TextBuffer.FGC);
        for (int i = this.topLinNum; i < this.topLinNum + this.numDispLines; ++i) {
            final String line = this.getLine(i);
            if (line != null) {
                if (line.length() != 0) {
                    final int n = this.chHeight - this.chDescent + this.chHeight * (i - this.topLinNum) + this.topGap;
                    if (this.tbListener == null) {
                        graphics.drawString(line, this.leftGap, n);
                    }
                    else {
                        final ColoredText[] colorText = this.tbListener.colorText(line);
                        if (colorText == null) {
                            graphics.drawString(line, this.leftGap, n);
                        }
                        else {
                            this.paintColoredText(graphics, colorText, n);
                        }
                    }
                }
            }
        }
        if (this.txtSel.isActive()) {
            graphics.setColor(TextBuffer.BGC);
            graphics.setXORMode(Color.lightGray);
            for (int j = this.txtSel.getFirstLin(); j <= this.txtSel.getLastLin(); ++j) {
                if (j >= this.topLinNum) {
                    if (j < this.topLinNum + this.numDispLines) {
                        int n2;
                        if (j == this.txtSel.getFirstLin()) {
                            n2 = this.leftEdge(this.txtSel.getFirstCol());
                        }
                        else {
                            n2 = this.leftEdge(0);
                        }
                        int n3;
                        if (j == this.txtSel.getLastLin()) {
                            n3 = this.rightEdge(this.txtSel.getLastCol());
                        }
                        else {
                            n3 = this.rightEdge(this.lineLen(j));
                        }
                        graphics.fillRect(this.leftGap + n2, this.topGap + this.topEdge(j - this.topLinNum), this.leftGap + n3 - n2, this.chHeight);
                    }
                }
            }
            graphics.setColor(TextBuffer.FGC);
            graphics.setPaintMode();
        }
        if (!this.errMsgs.isEmpty()) {
            this.errMsgs.firstElement().paint(graphics, this.stdFont, this.errMsgRightX, 3);
        }
        if (this.cursorVisible || !this.hasFocus) {
            graphics.fillRect(this.leftGap + this.chWidth * this.curCol, this.topGap + this.chHeight * (this.curLin - this.topLinNum), 2, this.chHeight);
        }
        if (this.lastLin >= this.numDispLines || this.topLinNum > 0) {
            this.sbar.paint(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.myOffScreenImage == null) {
            this.myOffScreenImage = this.createImage(this.width, this.height);
            (this.myOffScreenGraphics = this.myOffScreenImage.getGraphics()).setFont(this.stdFont);
        }
        this.paint(this.myOffScreenGraphics);
        graphics.drawImage(this.myOffScreenImage, 0, 0, this);
    }
    
    private void chkAdjCurCol() {
        if (this.buffer[this.curLin] != null) {
            final int length = this.buffer[this.curLin].length();
            if (this.curCol > length) {
                this.curCol = length;
            }
        }
        else {
            this.buffer[this.curLin] = new StringBuffer();
            this.curCol = 0;
        }
    }
    
    private void chkAdjCurLinBottom() {
        if (this.curLin < this.topLinNum || this.curLin >= this.topLinNum + this.numDispLines) {
            final int n = this.curLin - (this.numDispLines - 1);
            this.setTopLineNum((n < 0) ? 0 : n);
        }
    }
    
    private void chkAdjCurLinTop() {
        if (this.curLin < this.topLinNum || this.curLin >= this.topLinNum + this.numDispLines) {
            this.setTopLineNum((this.curLin <= 0) ? 0 : (this.curLin - 1));
        }
    }
    
    private int decrCurLin() {
        if (this.lineModified) {
            this.notifyLineModified(this.curLin);
        }
        if (this.curLin == this.lastLin && this.isBlankLine(this.lastLin)) {
            --this.lastLin;
            this.sbar.setNumLines(this.lastLin + 1);
        }
        --this.curLin;
        this.chkAdjCurCol();
        this.notifyCurLineNumChanged(this.curLin);
        this.chkAdjCurLinTop();
        return this.curLin;
    }
    
    private int incrCurLin() {
        if (this.lineModified) {
            this.notifyLineModified(this.curLin);
        }
        ++this.curLin;
        if (this.curLin > this.lastLin) {
            this.lastLin = this.curLin;
            this.sbar.setNumLines(this.curLin + 1);
        }
        this.chkAdjCurCol();
        this.notifyCurLineNumChanged(this.curLin);
        this.chkAdjCurLinBottom();
        return this.curLin;
    }
    
    private boolean isBlankLine(final int n) {
        final StringBuffer sb = this.buffer[n];
        if (sb == null) {
            return true;
        }
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
    
    private void joinLines(final int n) {
        if (n >= 7999) {
            return;
        }
        final StringBuffer sb = this.buffer[n + 1];
        if (sb == null) {
            return;
        }
        final StringBuffer sb2 = this.buffer[n];
        for (int n2 = sb.length() - 1, i = 0; i <= n2; ++i) {
            sb2.append(sb.charAt(i));
        }
        this.delLine(n + 1);
        this.notifyLineDeleted(n + 1);
        this.lineModified = true;
    }
    
    private int lastNonBlankLine() {
        int lastNonEmptyLine;
        for (lastNonEmptyLine = this.lastNonEmptyLine(); lastNonEmptyLine > 0 && this.isBlankLine(lastNonEmptyLine); --lastNonEmptyLine) {}
        this.lastLin = lastNonEmptyLine;
        this.sbar.setNumLines(lastNonEmptyLine + 1);
        return lastNonEmptyLine;
    }
    
    private int lastNonEmptyLine() {
        int lastNonNullLine;
        for (lastNonNullLine = this.lastNonNullLine(); lastNonNullLine > 0 && this.buffer[lastNonNullLine].length() <= 0; --lastNonNullLine) {}
        return lastNonNullLine;
    }
    
    private int lastNonNullLine() {
        int n;
        for (n = 7999; n > 0 && this.buffer[n] == null; --n) {}
        return n;
    }
    
    private void lineDelSubstr(final int n, int n2, int i) {
        while (i < this.buffer[n].length()) {
            this.buffer[n].setCharAt(n2, this.buffer[n].charAt(i));
            ++n2;
            ++i;
        }
        this.buffer[n].setLength(n2);
        if (this.curLin == n) {
            this.setColNum(n2);
        }
    }
    
    private String lineSubstring(final int n, final int n2, final int n3) {
        if (n3 - n2 <= 0) {
            return null;
        }
        if (n < 0 || n > 7999) {
            return null;
        }
        if (this.buffer[n] == null) {
            return null;
        }
        final char[] array = new char[n3 - n2];
        this.buffer[n].getChars(n2, n3, array, 0);
        return new String(array);
    }
    
    private void notifyCurLineNumChanged(final int n) {
        if (this.tbListener != null) {
            this.tbListener.curLineNumChanged(n);
        }
    }
    
    private void notifyFocusGained() {
        if (this.tbListener != null) {
            this.tbListener.focusGained();
        }
    }
    
    private void notifyFocusLost() {
        if (this.tbListener != null) {
            this.tbListener.focusLost();
        }
    }
    
    private void notifyLineAdded(final int n) {
        if (this.tbListener != null) {
            this.tbListener.lineAdded(n);
        }
    }
    
    private void notifyLineDeleted(final int n) {
        if (this.tbListener != null) {
            this.tbListener.lineDeleted(n);
        }
    }
    
    private void notifyLineModified(final int n) {
        if (this.tbListener != null) {
            this.tbListener.lineModified(n);
        }
        this.lineModified = false;
    }
    
    private int setCurLin(final int curLin) {
        this.curLin = curLin;
        this.chkAdjCurCol();
        this.chkAdjCurLinTop();
        return this.curLin;
    }
    
    public void addChar(final int n, final int n2, final char c) {
        final int length = this.buffer[n].length();
        try {
            if (n2 == length) {
                this.buffer[n].append(c);
            }
            else {
                this.buffer[n].insert(n2, c);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("TextBuffer.addChar: col=" + n2 + ", linLen=" + length);
            this.buffer[n].append(c);
            this.curCol = this.buffer[n].length() - 1;
        }
    }
    
    public void addCharCmd(final char c) {
        if (this.txtSel.isActive()) {
            this.delSelection();
        }
        this.addChar(this.curLin, this.curCol, c);
        ++this.curCol;
        this.lineModified = true;
        this.chkAdjCurLinBottom();
        this.repaint();
    }
    
    public void addErrMsg(final ErrMsg errMsg) {
        final String text = errMsg.getText();
        int i = this.errMsgs.size() - 1;
        while (i >= 0) {
            if (this.errMsgs.elementAt(i).equals(text)) {
                if (i == 0) {
                    return;
                }
                this.errMsgs.removeElementAt(i);
                break;
            }
            else {
                --i;
            }
        }
        this.errMsgs.insertElementAt(errMsg, 0);
        this.repaint();
    }
    
    public void addEventHandler(final TextBufferListener tbListener) {
        final String s = ".addEventHandler: ";
        if (this.tbListener == null) {
            this.tbListener = tbListener;
        }
        else {
            System.err.println("TextBuffer" + s + "tbListener already set");
        }
    }
    
    public boolean atBeginningOfLine() {
        return this.curCol == 0;
    }
    
    public boolean atEndOfLine() {
        return this.curCol >= this.buffer[this.curLin].length();
    }
    
    public void clearText() {
        for (int i = 7999; i > 0; this.buffer[i--] = null) {}
        this.buffer[0].setLength(0);
        this.curCol = 0;
        this.curLin = 0;
        this.topLinNum = 0;
        this.repaint();
    }
    
    public void copySelection() {
        if (!this.txtSel.isActive()) {
            this.txtSel.setText(null);
            return;
        }
        final int firstCol = this.txtSel.getFirstCol();
        final int firstLin = this.txtSel.getFirstLin();
        final int lastCol = this.txtSel.getLastCol();
        final int lastLin = this.txtSel.getLastLin();
        final StringBuffer sb = new StringBuffer();
        for (int i = firstLin; i <= lastLin; ++i) {
            final int n = (i == firstLin) ? firstCol : false;
            final int n2 = (this.buffer[i] == null) ? 0 : this.buffer[i].length();
            int n3 = lastCol;
            boolean b = false;
            if (i != lastLin || lastCol >= n2) {
                b = true;
                n3 = n2 - 1;
            }
            if (n < n3) {
                sb.append(this.lineSubstring(i, n, n3 + 1));
            }
            if (b) {
                sb.append('\n');
            }
        }
        this.txtSel.setText(sb.toString());
        this.txtSel.clearSelection();
        this.repaint();
    }
    
    public void cursorDown() {
        if (this.txtSel.isActive()) {
            this.txtSel.clearSelection();
        }
        if (this.curLin < 7999) {
            this.incrCurLin();
        }
        this.repaint();
    }
    
    public void cursorLeft() {
        if (this.txtSel.isActive()) {
            this.txtSel.clearSelection();
        }
        if (--this.curCol < 0) {
            if (this.curLin == 0) {
                this.curCol = 0;
            }
            else {
                this.curCol = 1024;
                this.decrCurLin();
            }
        }
        this.repaint();
    }
    
    public void cursorRight() {
        if (this.txtSel.isActive()) {
            this.txtSel.clearSelection();
        }
        if (!this.atEndOfLine()) {
            ++this.curCol;
        }
        else if (this.curLin < 7999) {
            this.curCol = 0;
            this.incrCurLin();
        }
        this.repaint();
    }
    
    public void cursorUp() {
        if (this.txtSel.isActive()) {
            this.txtSel.clearSelection();
        }
        if (this.curLin > 0) {
            this.decrCurLin();
        }
        this.repaint();
    }
    
    public void cutSelection() {
        if (!this.txtSel.isActive()) {
            this.txtSel.setText(null);
            return;
        }
        final int firstCol = this.txtSel.getFirstCol();
        final int firstLin = this.txtSel.getFirstLin();
        final int lastCol = this.txtSel.getLastCol();
        final int lastLin = this.txtSel.getLastLin();
        final StringBuffer sb = new StringBuffer();
        for (int i = lastLin; i >= firstLin; --i) {
            final int n = (i == firstLin) ? firstCol : false;
            final int n2 = (this.buffer[i] == null) ? 0 : this.buffer[i].length();
            int n3 = lastCol;
            boolean b = false;
            if (i != lastLin || lastCol >= n2) {
                b = true;
                n3 = n2 - 1;
            }
            if (b) {
                sb.insert(0, '\n');
                this.joinLines(i);
            }
            if (n < n3) {
                sb.insert(0, this.lineSubstring(i, n, n3 + 1));
                this.lineDelSubstr(i, n, n3 + 1);
                this.lineModified = true;
            }
        }
        this.txtSel.setText(sb.toString());
        this.setCurLinNumCmd(firstLin);
        this.setColNum(firstCol);
        this.txtSel.clearSelection();
        this.repaint();
    }
    
    public void delChar(final int n, final int n2) {
        final StringBuffer sb = this.buffer[n];
        final int length = sb.length();
        if (n2 >= 0 && n2 < length) {
            final int length2 = length - 1;
            for (int i = n2 + 1; i <= length2; ++i) {
                sb.setCharAt(i - 1, sb.charAt(i));
            }
            sb.setLength(length2);
        }
        else {
            System.err.println("TextBuffer.delChar: out of bounds colNum=" + n2);
        }
    }
    
    public void delCharCmd() {
        if (this.txtSel.isActive()) {
            this.delSelection();
        }
        else if (this.curCol < this.buffer[this.curLin].length()) {
            this.delChar(this.curLin, this.curCol);
            this.lineModified = true;
        }
        else {
            this.joinLines(this.curLin);
        }
        this.repaint();
    }
    
    public void delLine(final int n) {
        this.buffer[n] = null;
        for (int n2 = n + 1; n2 <= 7999 && (this.buffer[n2 - 1] = this.buffer[n2]) != null; ++n2) {
            this.buffer[n2] = null;
        }
    }
    
    public int delPrevCharCmd() {
        this.txtSel.clearSelection();
        final StringBuffer sb = this.buffer[this.curLin];
        final int length = sb.length() - 1;
        if (this.curCol > 0) {
            for (int i = this.curCol; i <= length; ++i) {
                sb.setCharAt(i - 1, sb.charAt(i));
            }
            sb.setLength(length);
            --this.curCol;
            this.lineModified = true;
        }
        else if (this.curLin > 0) {
            this.curCol = this.buffer[this.curLin - 1].length();
            this.decrCurLin();
            this.joinLines(this.curLin);
        }
        this.repaint();
        return this.curLin;
    }
    
    public void delSelection() {
        if (!this.txtSel.isActive()) {
            return;
        }
        final int firstCol = this.txtSel.getFirstCol();
        final int firstLin = this.txtSel.getFirstLin();
        final int lastCol = this.txtSel.getLastCol();
        int i;
        for (int n = i = this.txtSel.getLastLin(); i >= firstLin; --i) {
            final int n2 = (i == firstLin) ? firstCol : false;
            final int n3 = (this.buffer[i] == null) ? 0 : this.buffer[i].length();
            int n4 = lastCol;
            boolean b = false;
            if (i != n || lastCol >= n3) {
                b = true;
                n4 = n3 - 1;
            }
            if (b) {
                this.joinLines(i);
            }
            if (n2 < n4) {
                this.lineDelSubstr(i, n2, n4 + 1);
                this.lineModified = true;
            }
        }
        this.setCurLinNumCmd(firstLin);
        this.setColNum(firstCol);
        this.txtSel.clearSelection();
        this.repaint();
    }
    
    public void delToEOLCmd() {
        if (this.txtSel.isActive()) {
            this.txtSel.clearSelection();
        }
        if (this.curCol < this.buffer[this.curLin].length()) {
            this.buffer[this.curLin].setLength(this.curCol);
            this.lineModified = true;
            this.repaint();
        }
        else {
            this.joinLines(this.curLin);
        }
    }
    
    public char getCurChar() {
        if (this.curCol < this.buffer[this.curLin].length()) {
            return this.buffer[this.curLin].charAt(this.curCol);
        }
        if (this.buffer[(this.curLin < 7999) ? (this.curLin + 1) : 7999] == null) {
            return '\u0019';
        }
        return '\n';
    }
    
    public int getCurColNum() {
        return this.curCol;
    }
    
    public int getCurLineNum() {
        return this.curLin;
    }
    
    public String getLine(final int n) {
        if (n < 0 || n >= 8000) {
            return null;
        }
        if (this.buffer[n] == null) {
            return null;
        }
        return this.buffer[n].toString();
    }
    
    public int getMaxLineNum() {
        return this.lastNonBlankLine();
    }
    
    public int getNumDispLines() {
        return this.numDispLines;
    }
    
    public int getTopLineNum() {
        return this.topLinNum;
    }
    
    public void gotoBeginOfLine() {
        if (this.txtSel.isActive()) {
            this.txtSel.clearSelection();
        }
        this.setColNum(0);
        this.repaint();
    }
    
    public void gotoEndOfLine() {
        if (this.txtSel.isActive()) {
            this.txtSel.clearSelection();
        }
        this.setColNum(Integer.MAX_VALUE);
        this.repaint();
    }
    
    public boolean hasFocus() {
        return this.hasFocus;
    }
    
    public void insertLineOfChars(final StringBuffer sb) {
        final String s = ".insertLineOfChars: ";
        if (this.txtSel.isActive()) {
            this.delSelection();
        }
        if (sb == null) {
            System.err.println("TextBuffer" + s + "null StringBuffer");
        }
        if (this.openEmptyLine(this.curLin)) {
            this.buffer[this.curLin - 1] = sb;
            this.curCol = 0;
        }
        else {
            System.err.println("TextBuffer" + s + "openEmptyLine failed");
        }
        this.repaint();
    }
    
    public int lineLen(final int n) {
        if (n < 0 || n > 7999) {
            return 0;
        }
        if (this.buffer[n] == null) {
            return 0;
        }
        return this.buffer[n].length();
    }
    
    public int lineStartingWith(final String s) {
        for (int i = 0; i <= 7999; ++i) {
            final StringBuffer sb;
            if ((sb = this.buffer[i]) == null) {
                return -1;
            }
            int n;
            for (n = 0; n < sb.length() && sb.charAt(n) == ' '; ++n) {}
            if (sb.length() - n >= s.length()) {
                int j = 0;
                while (j < s.length()) {
                    final char char1;
                    if ((char1 = s.charAt(j++)) == ' ') {
                        final char char2;
                        if (n < sb.length() && (char2 = s.charAt(n++)) != ' ') {
                            break;
                        }
                        while (n < sb.length() && sb.charAt(n) == ' ') {
                            ++n;
                        }
                    }
                    else {
                        if (n >= sb.length()) {
                            break;
                        }
                        final char char3 = sb.charAt(n++);
                        if (char1 == char3) {
                            continue;
                        }
                        if ((char1 ^ char3) != ' ') {
                            break;
                        }
                        continue;
                    }
                }
                if (j == s.length()) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public int enterCmd() {
        if (this.txtSel.isActive()) {
            this.delSelection();
        }
        int n = this.curLin + 1;
        if (!this.openEmptyLine(n)) {
            if (!this.scrolling) {
                Toolkit.getDefaultToolkit().beep();
                return this.curLin;
            }
            this.scrollBuffer();
            this.curCol = 1024;
            this.setCurLin(this.lastNonBlankLine());
            n = this.curLin + 1;
            this.chkAdjCurLinTop();
        }
        final int length = this.buffer[this.curLin].length();
        if (this.curCol < length) {
            for (int i = this.curCol; i < length; ++i) {
                this.buffer[n].append(this.buffer[this.curLin].charAt(i));
            }
            this.buffer[this.curLin].setLength(this.curCol);
            this.notifyLineModified(this.curLin);
            this.notifyLineAdded(n);
            this.incrCurLin();
        }
        else {
            if (this.lineModified) {
                this.notifyLineModified(this.curLin);
            }
            this.incrCurLin();
            this.notifyLineAdded(n);
        }
        this.curCol = 0;
        this.repaint();
        return this.curLin;
    }
    
    public boolean openEmptyLine() {
        return this.openEmptyLine(this.curLin);
    }
    
    public boolean openEmptyLine(final int n) {
        int lastLin = this.lastNonNullLine() + 1;
        if (n > lastLin || lastLin > 7999) {
            return false;
        }
        this.lastLin = lastLin;
        this.sbar.setNumLines(this.lastLin + 1);
        if (this.buffer[n] == null) {
            this.buffer[n] = new StringBuffer();
        }
        else {
            for (int i = lastLin - 1; i >= n; this.buffer[lastLin--] = this.buffer[i--]) {}
            this.buffer[n] = new StringBuffer();
        }
        if (n <= this.curLin) {
            ++this.curLin;
        }
        return true;
    }
    
    public void openLineCmd() {
        if (this.txtSel.isActive()) {
            this.txtSel.clearSelection();
        }
        final int curLin = this.curLin;
        if (this.openEmptyLine(this.curLin)) {
            this.notifyLineAdded(curLin);
            this.curCol = 0;
            this.decrCurLin();
        }
        this.repaint();
    }
    
    public void pageDownCmd(final boolean b) {
        if (this.txtSel.isActive()) {
            this.txtSel.clearSelection();
        }
        final int n = this.numDispLines - 1;
        final int lastNonBlankLine = this.lastNonBlankLine();
        int topLineNum = this.topLinNum + n;
        int n2 = lastNonBlankLine - this.numDispLines / 2;
        if (n2 < 0) {
            n2 = 0;
        }
        if (topLineNum > n2) {
            topLineNum = n2;
            this.lastLin = lastNonBlankLine;
            this.sbar.setNumLines(this.lastLin + 1);
        }
        if (b) {
            if (this.lineModified) {
                this.notifyLineModified(this.curLin);
            }
            int curLin = this.curLin + n;
            if (curLin > lastNonBlankLine) {
                curLin = lastNonBlankLine;
            }
            if (curLin != this.curLin) {
                this.setCurLin(curLin);
                this.notifyCurLineNumChanged(this.curLin);
            }
        }
        this.setTopLineNum(topLineNum);
    }
    
    public void pageUpCmd(final boolean b) {
        if (this.txtSel.isActive()) {
            this.txtSel.clearSelection();
        }
        if (this.topLinNum == 0) {
            return;
        }
        final int n = this.numDispLines - 1;
        int topLineNum = this.topLinNum - n;
        if (topLineNum < 0) {
            topLineNum = 0;
        }
        if (b) {
            if (this.lineModified) {
                this.notifyLineModified(this.curLin);
            }
            int curLin = this.curLin - n;
            if (curLin < 0) {
                curLin = 0;
            }
            if (curLin != this.curLin) {
                this.setCurLin(curLin);
                this.notifyCurLineNumChanged(this.curLin);
            }
        }
        this.setTopLineNum(topLineNum);
    }
    
    public void pasteSelection() {
        final String text = this.txtSel.getText();
        if (text != null) {
            if (this.txtSel.isActive()) {
                this.delSelection();
            }
            for (int i = 0; i < text.length(); ++i) {
                final char char1 = text.charAt(i);
                if (char1 == '\n') {
                    this.enterCmd();
                }
                else {
                    this.addCharCmd(char1);
                }
            }
            this.chkAdjCurLinTop();
            this.repaint();
        }
    }
    
    public void removeEventHandler(final TextBufferListener textBufferListener) {
        if (this.tbListener == textBufferListener) {
            this.tbListener = null;
        }
        else {
            System.err.println("TextBuffer.removeEventHandler: tbListener not set");
        }
    }
    
    public void setSize(final int width, final int height) {
        super.setSize(this.width = width, this.height = height);
        this.errMsgRightX = width - (TBScrollbar.WIDTH + 3);
        this.sbar.setBounds(width - TBScrollbar.WIDTH, height);
        this.leftGap = width % this.chWidth / 2;
        if (this.leftGap == 0) {
            this.leftGap = this.chWidth / 2;
        }
        this.topGap = height % this.chHeight / 2;
        final int numDispLines = (height - this.topGap * 2) / this.chHeight;
        if (numDispLines != this.numDispLines) {
            if (this.curLin >= this.topLinNum && this.curLin < this.topLinNum + this.numDispLines && this.lastNonBlankLine() + 1 > numDispLines) {
                this.setTopLineNum(this.curLin - Math.round(numDispLines * ((this.curLin - this.topLinNum) / this.numDispLines)));
            }
            this.numDispLines = numDispLines;
            this.sbar.setNumVisibleLines(this.numDispLines);
        }
        this.myOffScreenImage = null;
        this.repaint();
    }
    
    public void scrollBuffer() {
        int i = 0;
        for (int j = 400; j < 8000; this.buffer[i++] = this.buffer[j++]) {}
        this.buffer[i++].setLength(0);
        while (i < 8000) {
            this.buffer[i++] = null;
        }
    }
    
    public void scrollDown() {
        if (this.topLinNum < this.lastNonBlankLine()) {
            ++this.topLinNum;
            this.sbar.setTopLineNum(this.topLinNum);
            this.repaint();
        }
    }
    
    public void scrollUp() {
        if (this.topLinNum > 0) {
            --this.topLinNum;
            this.sbar.setTopLineNum(this.topLinNum);
            this.repaint();
        }
    }
    
    public int search(final RegExp regExp) {
        if (!regExp.compilePattern()) {
            return -1;
        }
        final int curCol = this.curCol;
        for (int i = this.curLin; i <= 7999; ++i) {
            final StringBuffer sb = this.buffer[i];
            if (sb == null) {
                return -1;
            }
            final int pattern = regExp.findPattern(sb.toString());
            if (pattern >= 0) {
                return i * 1024 + pattern;
            }
        }
        return -1;
    }
    
    public int searchBack(final String s) {
        final int length = s.length();
        if (length == 0) {
            return this.curLin * 1024 + this.curCol;
        }
        int curCol = this.curCol;
        int curLin = this.curLin;
        StringBuffer sb = this.buffer[curLin];
        int n = sb.length();
        while (true) {
            if (curCol >= 0) {
                int n2 = 0;
                for (int i = curCol; i < n; ++i) {
                    final char char1 = sb.charAt(i);
                    final char char2 = s.charAt(n2++);
                    if (char1 != char2 && (char2 ^ char1) != ' ') {
                        break;
                    }
                    if (n2 == length) {
                        return curLin * 1024 + curCol;
                    }
                }
                --curCol;
            }
            else {
                if (--curLin < 0) {
                    return -1;
                }
                sb = this.buffer[curLin];
                n = sb.length();
                curCol = n - length;
            }
        }
    }
    
    public int searchFwd(final String s) {
        final int length = s.length();
        if (length == 0) {
            return this.curLin * 1024 + this.curCol;
        }
        int curCol = this.curCol;
        for (int i = this.curLin; i <= 7999; ++i, curCol = 0) {
            final StringBuffer sb = this.buffer[i];
            if (sb == null) {
                return -1;
            }
            for (int length2 = sb.length(); length2 - curCol >= length; ++curCol) {
                int n = 0;
                for (int j = curCol; j < length2; ++j) {
                    final char char1 = sb.charAt(j);
                    final char char2 = s.charAt(n++);
                    if (char1 != char2 && (char2 ^ char1) != ' ') {
                        break;
                    }
                    if (n == length) {
                        return i * 1024 + curCol;
                    }
                }
            }
        }
        return -1;
    }
    
    public void setTopLineNum(int n) {
        if (n < 0) {
            System.err.println("TextBuffer.setTopLineNum: negative line=" + n);
            n = 0;
        }
        if (this.topLinNum != n) {
            this.topLinNum = n;
            this.sbar.setTopLineNum(n);
            this.repaint();
        }
    }
    
    public int setColNum(final int curCol) {
        this.curCol = curCol;
        this.chkAdjCurCol();
        this.repaint();
        return this.curCol;
    }
    
    public int setCurLinNumCmd(int curLin) {
        if (curLin < 0) {
            curLin = 0;
        }
        if (curLin > 7999) {
            curLin = 7999;
        }
        while (this.buffer[curLin] == null) {
            --curLin;
        }
        if (this.curLin != curLin) {
            if (this.lineModified) {
                this.notifyLineModified(this.curLin);
            }
            this.setCurLin(curLin);
            this.notifyCurLineNumChanged(this.curLin);
            this.repaint();
        }
        return curLin;
    }
    
    public void setScrolling() {
        this.scrolling = true;
    }
    
    public void startCursorBlinking() {
        (this.cursorThread = new Thread(this, "TBcursor-" + this.ttNum)).setDaemon(true);
        this.cursorThread.start();
    }
    
    public void stopCursorBlinking() {
        this.cursorThread = null;
        this.cursorVisible = true;
        this.repaint();
    }
    
    static {
        BGC = Color.white;
        FGC = Color.black;
        TextBuffer.tbObjNum = 0;
    }
}
