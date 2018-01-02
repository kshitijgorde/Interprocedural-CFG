import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
import java.util.Random;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Color;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class X_Blink extends Applet implements Runnable
{
    Image mOffImage;
    Graphics mOffGraphics;
    Thread mThread;
    Vector mMessages;
    int mActiveMessage;
    int mCurrentMorphationCount;
    int mTotalMorphationCount;
    int mApplet_Width;
    int mApplet_Height;
    Color mTEXT_COLOR;
    Color mBACKCOLOR1;
    Color mBACKCOLOR2;
    Color mRECTANGLE_COLOR;
    int mANIMTYPE;
    int mWAIT_TIME;
    int mREAD_TIME;
    StringTokenizer mTok;
    Font mFont;
    FontMetrics mFontMetrics;
    Random lcRandom;
    int lcRandomSize;
    boolean lcRandomPlace;
    boolean lcColor;
    private boolean mValidRegCode;
    
    public void stop() {
        try {
            this.mThread.stop();
            this.mThread = null;
        }
        catch (Exception ex) {}
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.getCursor();
        this.setCursor(Cursor.getPredefinedCursor(12));
        this.showStatus("");
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.getCursor();
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.showStatus("");
        return true;
    }
    
    public X_Blink() {
        this.mActiveMessage = -1;
        this.mCurrentMorphationCount = 0;
        this.mTotalMorphationCount = 25;
        this.mTEXT_COLOR = this.stringToColor("44CCFF");
        this.mBACKCOLOR1 = this.stringToColor("000033");
        this.mBACKCOLOR2 = this.stringToColor("DDDDDD");
        this.mRECTANGLE_COLOR = this.stringToColor("FF0011");
        this.mANIMTYPE = 0;
        this.mWAIT_TIME = 150;
        this.mREAD_TIME = 2000;
        this.lcRandom = new Random();
        this.lcRandomSize = 3;
        this.lcRandomPlace = false;
        this.lcColor = false;
        this.mValidRegCode = false;
        System.out.println("X_Blink version 2.1\r\nCopyright 2001 by AppletWorld (appletworld@usa.net)");
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.mOffImage, 0, 0, this.mApplet_Width, this.mApplet_Height, this);
    }
    
    private void sbNextShow_Fade() {
        final X_BMItem x_BMItem = this.mMessages.elementAt(this.mActiveMessage);
        final int red = this.mBACKCOLOR2.getRed();
        final int n = red + (this.mBACKCOLOR1.getRed() - red) * this.mCurrentMorphationCount / this.mTotalMorphationCount;
        final int green = this.mBACKCOLOR2.getGreen();
        final int n2 = green + (this.mBACKCOLOR1.getGreen() - green) * this.mCurrentMorphationCount / this.mTotalMorphationCount;
        final int blue = this.mBACKCOLOR2.getBlue();
        this.mOffGraphics.setColor(new Color(n, n2, blue + (this.mBACKCOLOR1.getBlue() - blue) * this.mCurrentMorphationCount / this.mTotalMorphationCount));
        this.mOffGraphics.fillRect(0, 0, this.mApplet_Width, this.mApplet_Height);
        this.mOffGraphics.setColor(this.mTEXT_COLOR);
        this.mOffGraphics.setFont(this.mFont);
        int n3 = (this.mOffImage.getWidth(this) - this.mFontMetrics.stringWidth(x_BMItem.X_Value)) / 2;
        int n4 = (this.mOffImage.getHeight(this) - this.mFontMetrics.getHeight()) / 2 + this.mFontMetrics.getAscent();
        if (this.lcRandomPlace && this.mCurrentMorphationCount <= this.mTotalMorphationCount) {
            n3 += (int)(this.lcRandom.nextDouble() * this.lcRandomSize);
            n4 += (int)(this.lcRandom.nextDouble() * this.lcRandomSize);
        }
        this.mOffGraphics.drawString(x_BMItem.X_Value, n3, n4);
        this.mOffGraphics.setColor(this.mRECTANGLE_COLOR);
        this.mOffGraphics.drawRect(0, 0, this.mApplet_Width - 1, this.mApplet_Height - 1);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final X_BMItem x_BMItem = this.mMessages.elementAt(this.mActiveMessage);
        if (!x_BMItem.X_URL.equalsIgnoreCase("")) {
            this.showURL(x_BMItem.X_URL, x_BMItem.X_TargetFrame);
        }
        return true;
    }
    
    public void destroy() {
        try {
            this.mOffGraphics.dispose();
            System.gc();
        }
        catch (Exception ex) {
            System.out.println("error on destroy() -> " + ex.toString());
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void ReadMenuItems() {
        int n = 0;
        this.mMessages = new Vector(1, 1);
        for (String s = this.getParameter("m" + String.valueOf(n)); s != null & s != ""; s = this.getParameter("m" + String.valueOf(++n))) {
            String nextToken = "";
            String nextToken2 = "";
            String nextToken3 = "";
            String nextToken4 = "";
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                nextToken2 = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                nextToken3 = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                nextToken4 = stringTokenizer.nextToken();
            }
            this.mMessages.addElement(new X_BMItem(nextToken, nextToken2, nextToken3, nextToken4));
        }
    }
    
    public void start() {
        try {
            this.mActiveMessage = 0;
            final X_BMItem x_BMItem = this.mMessages.elementAt(this.mActiveMessage);
            if (this.mANIMTYPE == 0) {
                this.mCurrentMorphationCount = this.mTotalMorphationCount - 1;
            }
            if (this.mANIMTYPE == 1) {
                this.mCurrentMorphationCount = this.mTotalMorphationCount - 1;
            }
            (this.mThread = new Thread(this)).setPriority(1);
            this.mThread.start();
        }
        catch (Exception ex) {}
    }
    
    public void showURL(final String s, final String s2) {
        String s3;
        if (s.startsWith("http") || s.startsWith("mail")) {
            s3 = "";
        }
        else {
            s3 = "" + this.getDocumentBase();
            if (!s3.substring(s3.length() - 1).equals("/")) {
                s3 = s3.substring(0, s3.lastIndexOf("/")) + "/";
            }
        }
        URL url;
        try {
            url = new URL(s3 + s);
        }
        catch (Exception ex) {
            this.showStatus("ShowURL Exception");
            return;
        }
        this.getAppletContext().showDocument(url, s2);
    }
    
    private void RegCodeCheck(final String s, String s2) {
        final String s3 = new String("");
        int n = 210;
        s2 = ((s2 == null) ? "" : s2);
        final String s4 = (s == null) ? "" : s;
        final String s5 = s4.startsWith("http://") ? s4.substring(7) : s4;
        final String s6 = s5.startsWith("https://") ? s5.substring(8) : s5;
        String substring = s6.startsWith("www.") ? s6.substring(4) : s6;
        if (substring.indexOf("/") >= 0) {
            substring = substring.substring(0, substring.indexOf("/"));
        }
        final String lowerCase = substring.toLowerCase();
        if (lowerCase.indexOf("tskb") >= 0) {
            this.mValidRegCode = true;
            return;
        }
        if (lowerCase.startsWith("file")) {
            this.mValidRegCode = true;
            return;
        }
        char c;
        for (c = '\0'; c < lowerCase.length(); ++c) {
            n = ((n + lowerCase.charAt(c) * '\u0003') * (c + '\u0001') + (c - '\u0001')) * '\u0002' % 1000000 + c;
        }
        final int n2 = ((n / c + 123654) * c % 1000000 * (c + '\n') + c - 1) % 10000000;
        if (("AW" + String.valueOf(n2)).equalsIgnoreCase(s2)) {
            this.mValidRegCode = true;
            return;
        }
        final String s7 = new String("");
        int int1 = Integer.parseInt(String.valueOf(n2 * 2 / 3).replace('1', '5').replace('3', '1').replace('6', '3').replace('9', '6'));
        final String s8 = "X_Blink";
        for (char c2 = '\0'; c2 < s8.length(); ++c2) {
            int1 += s8.charAt(c2) * c2 * '\u00c7';
        }
        if (("AWX" + String.valueOf(int1 % 10000000)).equalsIgnoreCase(s2)) {
            this.mValidRegCode = true;
        }
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    if (this.mCurrentMorphationCount <= this.mTotalMorphationCount) {
                        if (this.mANIMTYPE == 0) {
                            this.sbNextShow_Blink();
                        }
                        else if (this.mANIMTYPE == 1) {
                            this.sbNextShow_Fade();
                        }
                        ++this.mCurrentMorphationCount;
                    }
                    else {
                        this.lcColor = false;
                        try {
                            Thread.sleep(this.mREAD_TIME);
                        }
                        catch (Exception ex) {
                            System.out.println("error on run() -> " + ex.toString());
                        }
                        if (this.mActiveMessage >= this.mMessages.size() - 1) {
                            this.mActiveMessage = 0;
                        }
                        else {
                            ++this.mActiveMessage;
                        }
                        this.mCurrentMorphationCount = 0;
                    }
                    this.repaint();
                    Thread.sleep(this.mWAIT_TIME);
                }
            }
            catch (Exception ex2) {
                System.out.println("error on run() -> " + ex2.toString());
                continue;
            }
            break;
        }
    }
    
    public void init() {
        this.show();
        this.RegCodeCheck(this.getCodeBase().toString(), this.getParameter("regcode"));
        this.mApplet_Width = ((this.size().width >= 1) ? Math.round(this.size().width / 1) : 150);
        this.mApplet_Height = ((this.size().height >= 1) ? Math.round(this.size().height / 1) : 50);
        this.mOffImage = this.createImage(this.mApplet_Width, this.mApplet_Height);
        this.mOffGraphics = this.mOffImage.getGraphics();
        this.sbReadParams();
        this.mOffGraphics.setFont(this.mFont);
        this.mFontMetrics = this.getFontMetrics(this.mFont);
    }
    
    private void sbNextShow_Blink() {
        if (this.mCurrentMorphationCount >= this.mTotalMorphationCount) {
            this.mOffGraphics.setColor(this.mBACKCOLOR1);
        }
        else if (this.lcColor) {
            this.mOffGraphics.setColor(this.mBACKCOLOR1);
        }
        else {
            this.mOffGraphics.setColor(this.mBACKCOLOR2);
        }
        this.lcColor = !this.lcColor;
        this.mOffGraphics.fillRect(0, 0, this.mApplet_Width, this.mApplet_Height);
        this.mOffGraphics.setColor(this.mTEXT_COLOR);
        final X_BMItem x_BMItem = this.mMessages.elementAt(this.mActiveMessage);
        this.mOffGraphics.setFont(this.mFont);
        int n = (this.mOffImage.getWidth(this) - this.mFontMetrics.stringWidth(x_BMItem.X_Value)) / 2;
        int n2 = (this.mOffImage.getHeight(this) - this.mFontMetrics.getHeight()) / 2 + this.mFontMetrics.getAscent();
        if (this.lcRandomPlace && this.mCurrentMorphationCount <= this.mTotalMorphationCount) {
            n += (int)(this.lcRandom.nextDouble() * this.lcRandomSize);
            n2 += (int)(this.lcRandom.nextDouble() * this.lcRandomSize);
        }
        this.mOffGraphics.drawString(x_BMItem.X_Value, n, n2);
        this.mOffGraphics.setColor(this.mRECTANGLE_COLOR);
        this.mOffGraphics.drawRect(0, 0, this.mApplet_Width - 1, this.mApplet_Height - 1);
    }
    
    private Color stringToColor(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
    
    private void sbReadParams() {
        final String parameter = this.getParameter("text_color");
        if (parameter != null && !parameter.equalsIgnoreCase("")) {
            this.mTEXT_COLOR = this.stringToColor(parameter);
        }
        final String parameter2 = this.getParameter("backcolor1");
        if (parameter2 != null && !parameter2.equalsIgnoreCase("")) {
            this.mBACKCOLOR1 = this.stringToColor(parameter2);
        }
        final String parameter3 = this.getParameter("backcolor2");
        if (parameter3 != null && !parameter3.equalsIgnoreCase("")) {
            this.mBACKCOLOR2 = this.stringToColor(parameter3);
        }
        final String parameter4 = this.getParameter("rectangle_color");
        if (parameter4 != null && !parameter4.equalsIgnoreCase("")) {
            this.mRECTANGLE_COLOR = this.stringToColor(parameter4);
        }
        final String parameter5 = this.getParameter("wait_time");
        if (parameter5 != null && !parameter5.equalsIgnoreCase("")) {
            this.mWAIT_TIME = Integer.parseInt(parameter5);
        }
        final String parameter6 = this.getParameter("read_time");
        if (parameter6 != null && !parameter6.equalsIgnoreCase("")) {
            this.mREAD_TIME = Integer.parseInt(parameter6);
        }
        final String parameter7 = this.getParameter("animtype");
        if (parameter7 != null && !parameter7.equalsIgnoreCase("")) {
            this.mANIMTYPE = Integer.parseInt(parameter7);
        }
        final String parameter8 = this.getParameter("step_count");
        if (parameter8 != null && !parameter8.equalsIgnoreCase("")) {
            this.mTotalMorphationCount = Integer.parseInt(parameter8);
        }
        final String parameter9 = this.getParameter("randomplace");
        if (parameter9 != null && !parameter9.equalsIgnoreCase("")) {
            this.lcRandomPlace = (Integer.parseInt(parameter9) == 1);
        }
        String nextToken = "Arial";
        int n = 1;
        int int1 = 14;
        final String parameter10 = this.getParameter("font");
        if (parameter10 != null && parameter10 != "") {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter10, "|");
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                final String nextToken2 = stringTokenizer.nextToken();
                if (nextToken2.equalsIgnoreCase("BOLD")) {
                    n = 1;
                }
                else if (nextToken2.equalsIgnoreCase("ITALIC")) {
                    n = 2;
                }
                else {
                    n = 0;
                }
            }
            if (stringTokenizer.hasMoreTokens()) {
                int1 = Integer.parseInt(stringTokenizer.nextToken());
            }
            this.mFont = new Font(nextToken, n, int1);
        }
        else {
            this.mFont = new Font("Arial", 1, 14);
        }
        this.ReadMenuItems();
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final X_BMItem x_BMItem = this.mMessages.elementAt(this.mActiveMessage);
        if (!this.mValidRegCode) {
            this.showStatus(" <<->> (C)2001 AppletWorld -> Web : http://go.to/appletworld, E-Mail : appletworld@usa.net <<->>");
        }
        else {
            this.showStatus(x_BMItem.X_Message);
        }
        return true;
    }
}
