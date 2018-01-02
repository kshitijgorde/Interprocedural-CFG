// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.terminal;

import java.util.NoSuchElementException;
import java.util.Properties;

public interface Terminal
{
    public static final int ATTR_BOLD = 1;
    public static final int ATTR_LOWINTENSITY = 2;
    public static final int ATTR_UNDERLINE = 4;
    public static final int ATTR_BLINKING = 8;
    public static final int ATTR_INVERSE = 16;
    public static final int ATTR_INVISIBLE = 32;
    public static final int ATTR_FGCOLOR = 64;
    public static final int ATTR_BGCOLOR = 128;
    public static final int OPT_REV_VIDEO = 0;
    public static final int OPT_AUTO_WRAP = 1;
    public static final int OPT_REV_WRAP = 2;
    public static final int OPT_INSERTMODE = 3;
    public static final int OPT_AUTO_LF = 4;
    public static final int OPT_SCROLL_SK = 5;
    public static final int OPT_SCROLL_SI = 6;
    public static final int OPT_LOCAL_PGKEYS = 7;
    public static final int OPT_COPY_CRNL = 8;
    public static final int OPT_VIS_CURSOR = 9;
    public static final int OPT_ASCII_LDC = 10;
    public static final int OPT_LOCAL_ECHO = 11;
    public static final int OPT_SCALE_FONT = 12;
    public static final int OPT_VIS_BELL = 13;
    public static final int OPT_MAP_CTRLSP = 14;
    public static final int OPT_DECCOLM = 15;
    public static final int OPT_DEC132COLS = 16;
    public static final int OPT_COPY_SEL = 17;
    public static final int OPT_LAST_OPT = 18;
    
    String terminalType();
    
    int rows();
    
    int cols();
    
    int vpixels();
    
    int hpixels();
    
    void write(final char p0);
    
    void write(final char[] p0, final int p1, final int p2);
    
    void write(final String p0);
    
    void writeLineDrawChar(final char p0);
    
    void addTerminalListener(final TerminalListener p0);
    
    void sendBytes(final byte[] p0);
    
    void doBell();
    
    void doBS();
    
    void doTab();
    
    void doTabs(final int p0);
    
    void doBackTabs(final int p0);
    
    void setTab(final int p0);
    
    void clearTab(final int p0);
    
    void resetTabs();
    
    void clearAllTabs();
    
    void doCR();
    
    void doLF();
    
    void resetInterpreter();
    
    void resetWindow();
    
    void setWindow(final int p0, final int p1);
    
    void setWindow(final int p0, final int p1, final int p2, final int p3);
    
    int getWindowTop();
    
    int getWindowBottom();
    
    int getWindowLeft();
    
    int getWindowRight();
    
    int getCursorV();
    
    int getCursorH();
    
    void cursorSetPos(final int p0, final int p1, final boolean p2);
    
    void cursorUp(final int p0);
    
    void cursorDown(final int p0);
    
    void cursorForward(final int p0);
    
    void cursorBackward(final int p0);
    
    void cursorIndex(final int p0);
    
    void cursorIndexRev(final int p0);
    
    void cursorSave();
    
    void cursorRestore();
    
    void scrollUp(final int p0);
    
    void scrollDown(final int p0);
    
    void clearBelow();
    
    void clearAbove();
    
    void clearScreen();
    
    void clearRight();
    
    void clearLeft();
    
    void clearLine();
    
    void eraseChars(final int p0);
    
    void insertChars(final int p0);
    
    void insertLines(final int p0);
    
    void deleteChars(final int p0);
    
    void deleteLines(final int p0);
    
    void setOption(final int p0, final boolean p1);
    
    boolean getOption(final int p0);
    
    void setAttribute(final int p0, final boolean p1);
    
    boolean getAttribute(final int p0);
    
    void setForegroundColor(final int p0);
    
    void setBackgroundColor(final int p0);
    
    void clearAllAttributes();
    
    void setProperties(final Properties p0, final boolean p1) throws IllegalArgumentException, NoSuchElementException;
    
    Properties getProperties();
    
    boolean getPropsChanged();
    
    void setPropsChanged(final boolean p0);
}
