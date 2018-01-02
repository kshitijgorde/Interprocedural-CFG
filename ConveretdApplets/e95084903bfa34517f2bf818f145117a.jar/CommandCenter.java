import java.util.NoSuchElementException;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.event.KeyListener;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class CommandCenter extends Container implements KeyListener
{
    private static final String CLASS_NAME = "CommandCenter";
    private static final char BACKSPACE = '\b';
    private static final char CTRL_A = '\u0001';
    private static final char CTRL_B = '\u0002';
    private static final char CTRL_D = '\u0004';
    private static final char CTRL_E = '\u0005';
    private static final char CTRL_F = '\u0006';
    private static final char CTRL_Q = '\u0011';
    private static final char CTRL_T = '\u0014';
    private static final char CTRL_U = '\u0015';
    private static final char HT = '\t';
    private static final char LF = '\n';
    private static final char UNIX_ENTER = '\n';
    private static final char WINDOWS_ENTER = '\r';
    private static final int EXTRA_LINES = 2;
    private static final int HEIGHT = 140;
    private static final int NUM_HISTORY_LINES = 10;
    private static final int WIDTH = 640;
    private int leftmostInpChar;
    private int historyLineNum;
    private TextBuffer tb;
    private Vector historyList;
    private Vector inputEvents;
    private Vector lineInpHandlers;
    private Vector quitHandlers;
    
    public CommandCenter(int n, final int n2, final int n3) {
        this.setLayout(null);
        if (n == 0) {
            n = 640;
        }
        this.add(this.tb = new TextBuffer(n, n2, n3, false));
        this.tb.addKeyListener(this);
        this.tb.setScrolling();
        super.setSize(n, n2);
        this.historyList = new Vector(10);
        this.historyLineNum = -1;
        this.leftmostInpChar = 0;
        this.inputEvents = new Vector(2);
        this.lineInpHandlers = new Vector(5, 5);
        this.quitHandlers = new Vector(5, 5);
    }
    
    private void doGoToBOL() {
        if (this.leftmostInpChar == 0) {
            this.tb.gotoBeginOfLine();
        }
        else {
            for (int i = this.tb.getCurColNum(); i > this.leftmostInpChar; --i) {
                this.tb.cursorLeft();
            }
        }
    }
    
    private void doMoveLeft() {
        final int curColNum = this.tb.getCurColNum();
        if (curColNum > 0 && curColNum >= this.leftmostInpChar) {
            this.tb.cursorLeft();
        }
    }
    
    private void doMoveRight() {
        if (!this.tb.atEndOfLine()) {
            this.tb.cursorRight();
        }
    }
    
    private synchronized void enterKey() {
        this.tb.gotoEndOfLine();
        final String substring = this.tb.getLine(this.tb.enterCmd() - 1).substring(this.leftmostInpChar);
        this.addLineToHistory(substring);
        this.historyLineNum = -1;
        this.inputEvents.addElement(substring);
        for (int size = this.lineInpHandlers.size(), i = 0; i < size; ++i) {
            ((TTYInputHandler)this.lineInpHandlers.elementAt(i)).lineAvail();
        }
        this.leftmostInpChar = 0;
    }
    
    private void fwdInput() {
        if (this.historyLineNum >= 0) {
            this.doGoToBOL();
            this.tb.delToEOLCmd();
            if (++this.historyLineNum > this.historyList.size() - 1) {
                this.historyLineNum = -1;
            }
            else {
                final String s = this.historyList.elementAt(this.historyLineNum);
                for (int i = 0; i < s.length(); ++i) {
                    this.tb.addCharCmd(s.charAt(i));
                }
            }
        }
    }
    
    private void prevInput() {
        int historyLineNum;
        if (this.historyLineNum == -1) {
            historyLineNum = this.historyList.size() - 1;
        }
        else {
            historyLineNum = this.historyLineNum - 1;
        }
        if (historyLineNum >= 0) {
            this.doGoToBOL();
            this.tb.delToEOLCmd();
            final String s = this.historyList.elementAt(historyLineNum);
            for (int i = 0; i < s.length(); ++i) {
                this.tb.addCharCmd(s.charAt(i));
            }
            this.historyLineNum = historyLineNum;
        }
    }
    
    private void printThreads() {
        if (this.getCurColNum() > 0) {
            this.println();
        }
        TGDriver.printTurtleStates();
    }
    
    private void quitKey() {
        for (int size = this.quitHandlers.size(), i = 0; i < size; ++i) {
            ((QuitHandler)this.quitHandlers.elementAt(i)).quit();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 127: {
                this.tb.delCharCmd();
                break;
            }
            case 40: {
                this.fwdInput();
                break;
            }
            case 35: {
                this.tb.gotoEndOfLine();
                break;
            }
            case 36: {
                this.doGoToBOL();
                break;
            }
            case 37: {
                this.doMoveLeft();
                break;
            }
            case 39: {
                this.doMoveRight();
                break;
            }
            case 38: {
                this.prevInput();
                break;
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        switch (keyChar) {
            case 8: {
                final int curColNum = this.tb.getCurColNum();
                if (curColNum > 0 && curColNum > this.leftmostInpChar) {
                    this.tb.delPrevCharCmd();
                    break;
                }
                break;
            }
            case 1: {
                this.doGoToBOL();
                break;
            }
            case 2: {
                this.doMoveLeft();
                break;
            }
            case 4: {
                this.tb.delCharCmd();
                break;
            }
            case 5: {
                this.tb.gotoEndOfLine();
                break;
            }
            case 6: {
                this.doMoveRight();
                break;
            }
            case 17: {
                this.quitKey();
                break;
            }
            case 20: {
                this.printThreads();
                break;
            }
            case 9: {
                this.tb.addCharCmd(' ');
                this.tb.addCharCmd(' ');
                break;
            }
            case 10:
            case 13: {
                this.enterKey();
                break;
            }
            default: {
                if (keyChar >= ' ' && keyChar <= '~') {
                    this.tb.addCharCmd(keyChar);
                    break;
                }
                break;
            }
        }
    }
    
    public Dimension getMinimumSize() {
        final Dimension minimumSize = this.tb.getMinimumSize();
        return new Dimension(minimumSize.width, minimumSize.height);
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = this.tb.getPreferredSize();
        return new Dimension(preferredSize.width, preferredSize.height);
    }
    
    public void requestFocus() {
        this.tb.requestFocus();
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.tb.setSize(n3, n4);
    }
    
    public void setSize(final int n, final int n2) {
        super.setSize(n, n2);
        this.tb.setSize(n, n2);
    }
    
    private void addLineToHistory(final String s) {
        if (s == null || s.length() == 0) {
            return;
        }
        boolean b = true;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) != ' ') {
                b = false;
                break;
            }
        }
        if (b) {
            return;
        }
        int size = this.historyList.size();
        for (int j = size - 1; j >= 0; --j) {
            if (s.equals(this.historyList.elementAt(j))) {
                this.historyList.removeElementAt(j);
                --size;
                break;
            }
        }
        if (size == 10) {
            this.historyList.removeElementAt(0);
        }
        this.historyList.addElement(s);
    }
    
    public void addInputHandler(final TTYInputHandler ttyInputHandler) {
        if (!this.lineInpHandlers.contains(ttyInputHandler)) {
            this.lineInpHandlers.addElement(ttyInputHandler);
        }
    }
    
    public void addQuitHandler(final QuitHandler quitHandler) {
        if (!this.quitHandlers.contains(quitHandler)) {
            this.quitHandlers.addElement(quitHandler);
        }
    }
    
    public synchronized void clearText() {
        this.tb.clearText();
        this.leftmostInpChar = 0;
    }
    
    public int getCurColNum() {
        return this.tb.getCurColNum();
    }
    
    public TextBuffer getTextBuffer() {
        return this.tb;
    }
    
    public void gotoEndOfLine() {
        this.tb.gotoEndOfLine();
    }
    
    public synchronized void print(final String s) {
        if (s == null) {
            return;
        }
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 9: {
                    this.tb.addCharCmd(' ');
                    this.tb.addCharCmd(' ');
                    break;
                }
                case 10: {
                    this.tb.enterCmd();
                    break;
                }
                case 13: {
                    break;
                }
                default: {
                    if (char1 >= ' ' && char1 <= '~') {
                        this.tb.addCharCmd(char1);
                        break;
                    }
                    break;
                }
            }
        }
        this.leftmostInpChar = this.tb.getCurColNum();
    }
    
    public synchronized void println() {
        this.tb.enterCmd();
        this.leftmostInpChar = 0;
    }
    
    public synchronized void println(final String s) {
        this.print(s);
        this.tb.enterCmd();
        this.leftmostInpChar = 0;
    }
    
    public void println(final StringBuffer sb) {
        this.println(sb.toString());
    }
    
    public synchronized String getInputLine() {
        String s = null;
        if (this.inputEvents.size() > 0) {
            try {
                s = this.inputEvents.firstElement();
                this.inputEvents.removeElementAt(0);
            }
            catch (NoSuchElementException ex) {
                System.err.println("CommandCenter.getInputLine: " + ex);
            }
        }
        return s;
    }
    
    public void removeQuitHandler(final QuitHandler quitHandler) {
        this.quitHandlers.removeElement(quitHandler);
    }
    
    public void setTopLineNum(final int topLineNum) {
        this.tb.setTopLineNum(topLineNum);
    }
}
