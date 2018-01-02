import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class C_MSG extends Frame implements WindowListener, ActionListener, AdjustmentListener
{
    public static C_MSG MSG;
    public static TextArea info;
    public static Scrollbar sbar;
    public static Button buttonOk;
    public static Button buttonClear;
    public static int windowH;
    public static int windowW;
    public static String msgString;
    public static int msgLineCount;
    public static int msgLength;
    public static int[] msgLineIndex;
    public static int displayLines;
    public static int charPerLine;
    public static boolean UndefinedDrawConstants;
    
    public static void MSG_MAIN(final String title, final C_MSG msg) {
        (C_MSG.MSG = msg).setResizable(true);
        C_MSG.MSG.setTitle(title);
        C_MSG.MSG.setBounds(0, 0, 200, 100);
        C_MSG.MSG.show();
        C_MSG.MSG.addWindowListener(C_MSG.MSG);
        C_MSG.MSG.setLayout(null);
        C_MSG.MSG.add(C_MSG.info);
        C_MSG.MSG.add(C_MSG.buttonOk);
        C_MSG.buttonOk.addActionListener(C_MSG.MSG);
        C_MSG.MSG.add(C_MSG.buttonClear);
        C_MSG.buttonClear.addActionListener(C_MSG.MSG);
        C_MSG.MSG.add(C_MSG.sbar);
        C_MSG.sbar.addAdjustmentListener(C_MSG.MSG);
        C_MSG.info.setEditable(false);
        msgClear();
        SetFontConstants();
    }
    
    public static void SetFontConstants() {
        C_MSG.MSG.setFont(C_TOOL.fontNormal);
        C_MSG.info.setFont(C_TOOL.fontNormal);
        C_MSG.buttonOk.setFont(C_TOOL.fontNormal);
        C_MSG.buttonClear.setFont(C_TOOL.fontNormal);
        C_MSG.windowW = C_TOOL.fontWidth * 65 + C_TOOL.windowLeft + C_TOOL.windowRight;
        C_MSG.windowH = C_TOOL.fontHeight * 12 + C_TOOL.windowTopNoMenu + C_TOOL.windowBottom;
        C_MSG.MSG.setBounds(0, C_TOOL.windowTopNoMenu, C_MSG.windowW, C_MSG.windowH);
        SetDrawConstants();
    }
    
    public static void SetDrawConstants() {
        final int fontHeight = C_TOOL.fontHeight;
        final int fontWidth = C_TOOL.fontWidth;
        final int boxHeight = C_TOOL.boxHeight;
        final Dimension size = C_MSG.MSG.getSize();
        final int n = size.width - C_TOOL.windowLeft - C_TOOL.windowRight;
        final int n2 = size.height - C_TOOL.windowTopNoMenu - C_TOOL.windowBottom;
        final int top = C_MSG.MSG.getInsets().top;
        final int left = C_MSG.MSG.getInsets().left;
        final int n3 = fontWidth * 9;
        final int n4 = left + (n - n3 * 3) / 2;
        final int n5 = n4 + n3 * 2;
        final int n6 = top + n2 - boxHeight - 2;
        final int n7 = top + 10;
        final int n8 = n6 - n7 - 5;
        final int n9 = left + fontWidth;
        final int n10 = n - fontWidth * 4;
        C_MSG.info.setBounds(n9, n7, n10, n8);
        C_MSG.sbar.setBounds(n9 + n10, n7, fontWidth * 2, n8);
        C_MSG.buttonOk.setBounds(n4, n6, n3, boxHeight);
        C_MSG.buttonClear.setBounds(n5, n6, n3, boxHeight);
        C_MSG.displayLines = (n8 - 20) / fontHeight;
        C_MSG.sbar.setBlockIncrement(C_MSG.displayLines - 1);
        final Dimension size2 = C_MSG.MSG.getSize();
        C_MSG.windowW = size2.width;
        C_MSG.windowH = size2.height;
        C_MSG.UndefinedDrawConstants = false;
    }
    
    public static void MSG_append(final String s, final boolean b) {
        if (b) {
            C_MSG.MSG.setVisible(true);
            C_MSG.MSG.toFront();
        }
        final int length = s.length();
        if (length <= 0) {
            return;
        }
        if (C_MSG.msgLineCount > 300) {
            final int n = 50;
            C_MSG.msgString = C_MSG.msgString.substring(C_MSG.msgLineIndex[n]);
            C_MSG.msgLength = C_MSG.msgString.length();
            final int n2 = C_MSG.msgLineIndex[n];
            for (int i = n; i <= C_MSG.msgLineCount; ++i) {
                C_MSG.msgLineIndex[i - n] = C_MSG.msgLineIndex[i] - n2;
            }
            C_MSG.msgLineCount -= n;
        }
        for (int j = 0; j < length; ++j) {
            if (s.charAt(j) == '\n') {
                ++C_MSG.msgLineCount;
                C_MSG.msgLineIndex[C_MSG.msgLineCount] = C_MSG.msgLength + j;
            }
        }
        C_MSG.msgString = String.valueOf(String.valueOf(C_MSG.msgString)).concat(String.valueOf(String.valueOf(s)));
        C_MSG.msgLength += length;
        C_MSG.sbar.setMaximum(C_MSG.msgLineCount);
        C_MSG.sbar.setValue(Math.max(0, C_MSG.msgLineCount - C_MSG.displayLines));
        SetDisplayText();
    }
    
    public static void SetDisplayText() {
        final int value = C_MSG.sbar.getValue();
        final int n = C_MSG.msgLineIndex[value] + 1;
        int msgLength;
        if (C_MSG.msgLineCount > value + C_MSG.displayLines) {
            msgLength = C_MSG.msgLineIndex[value + C_MSG.displayLines];
        }
        else {
            msgLength = C_MSG.msgLength;
        }
        String substring = "";
        if (msgLength > n) {
            substring = C_MSG.msgString.substring(n, msgLength);
        }
        C_MSG.info.setText(substring);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        C_MSG.MSG.setVisible(false);
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("hide")) {
            C_MSG.MSG.setVisible(false);
        }
        else {
            msgClear();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        SetDisplayText();
    }
    
    public void paint(final Graphics graphics) {
        if (C_MSG.UndefinedDrawConstants) {
            return;
        }
        final Dimension size = C_MSG.MSG.getSize();
        if (C_MSG.windowW != size.width || C_MSG.windowH != size.height) {
            SetDrawConstants();
        }
    }
    
    public static void msgClear() {
        C_MSG.msgString = "\n";
        C_MSG.msgLineCount = 1;
        C_MSG.msgLength = 1;
        C_MSG.sbar.setMaximum(0);
        C_MSG.sbar.setValue(0);
        SetDisplayText();
    }
    
    public static void QuitC_MSG() {
        if (C_MSG.MSG == null) {
            return;
        }
        C_MSG.MSG.dispose();
        C_MSG.MSG = null;
    }
    
    static {
        C_MSG.MSG = null;
        C_MSG.info = new TextArea("", 15, 100, 2);
        C_MSG.sbar = new Scrollbar(1);
        C_MSG.buttonOk = new Button("hide");
        C_MSG.buttonClear = new Button("clear");
        C_MSG.msgLineIndex = new int[2000];
        C_MSG.UndefinedDrawConstants = true;
    }
}
