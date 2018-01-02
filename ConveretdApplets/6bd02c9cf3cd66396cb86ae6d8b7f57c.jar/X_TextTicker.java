import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class X_TextTicker extends Applet implements Runnable
{
    private Thread mThread;
    private Vector mMenuItems;
    private Image mOffImage;
    private Image mBImage;
    private Graphics mOffGraphics;
    private Font mFont;
    private FontMetrics mFontMetrics;
    private Color mBACKCOLOR;
    private Color mFORECOLOROFF;
    private Color mLINECOLOR;
    private Color mRECTANGLECOLOR;
    private Color mPOINTCOLOR;
    private Color mFORECOLORON;
    private int mDELAYTIME;
    private int mSpaceWidth;
    private int mAppletWidth;
    private int mAppletHeight;
    private int mXArtirma_Miktari;
    private int mXPosition;
    private int mYPosition;
    private int mLastXPosition;
    private int mTotalMenuWidth;
    private StringTokenizer mTok;
    private int mLineWidth;
    private int mLineHeight;
    private boolean mValidRegCode;
    
    public void stop() {
        if (this.mThread != null) {
            try {
                this.mThread.stop();
                this.mThread = null;
                System.gc();
            }
            catch (Exception ex) {
                System.out.println("error on stop() -> " + ex.toString());
            }
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("");
        this.setCursor(new Cursor(12));
        return true;
    }
    
    private void NextStep() {
        final String[] array = new String[15];
        this.ActivateItem(this.mLastXPosition);
        if (this.mBImage == null) {
            this.mOffGraphics.setColor(this.mBACKCOLOR);
            this.mOffGraphics.fillRect(0, 0, this.mOffImage.getWidth(this), this.mOffImage.getHeight(this));
        }
        else {
            int i = 0;
            int j = 0;
            while (i <= this.mAppletHeight) {
                while (j <= this.mAppletWidth) {
                    this.mOffGraphics.drawImage(this.mBImage, j, i, this);
                    j += this.mBImage.getWidth(this);
                }
                i += this.mBImage.getHeight(this);
                j = 0;
            }
        }
        int mxPosition = this.mXPosition;
        for (int k = 0; k < this.mMenuItems.size(); ++k) {
            final MenuItem menuItem = this.mMenuItems.elementAt(k);
            this.sbTokenize(menuItem.X_Message, array);
            if (menuItem.mActive) {
                this.mOffGraphics.setColor(this.mPOINTCOLOR);
                this.mOffGraphics.fillRect(2, mxPosition + 1 - this.mLineHeight + 2, 3, 6);
                for (int n = 0; !array[n].equalsIgnoreCase(""); ++n) {
                    this.mOffGraphics.setColor(this.mFORECOLORON);
                    this.mOffGraphics.drawString(array[n] + "  ", this.mYPosition + 1, mxPosition + 1);
                    mxPosition += this.mLineHeight;
                }
            }
            else {
                for (int n2 = 0; !array[n2].equalsIgnoreCase(""); ++n2) {
                    this.mOffGraphics.setColor(this.mFORECOLOROFF);
                    this.mOffGraphics.drawString(array[n2] + "  ", this.mYPosition, mxPosition);
                    mxPosition += this.mLineHeight;
                }
            }
            this.mOffGraphics.setColor(this.mLINECOLOR);
            this.mOffGraphics.drawLine(2, mxPosition, this.mAppletWidth - 4, mxPosition);
            mxPosition += this.mLineHeight;
        }
        this.mTotalMenuWidth = mxPosition - this.mXPosition;
        for (int l = 0; l < this.mMenuItems.size(); ++l) {
            final MenuItem menuItem2 = this.mMenuItems.elementAt(l);
            this.sbTokenize(menuItem2.X_Message, array);
            if (menuItem2.mActive) {
                this.mOffGraphics.setColor(this.mPOINTCOLOR);
                this.mOffGraphics.fillRect(2, mxPosition + 1 - this.mLineHeight + 2, 3, 6);
                for (int n3 = 0; !array[n3].equalsIgnoreCase(""); ++n3) {
                    this.mOffGraphics.setColor(this.mFORECOLORON);
                    this.mOffGraphics.drawString(array[n3] + "  ", this.mYPosition + 1, mxPosition + 1);
                    mxPosition += this.mLineHeight;
                }
            }
            else {
                for (int n4 = 0; !array[n4].equalsIgnoreCase(""); ++n4) {
                    this.mOffGraphics.setColor(this.mFORECOLOROFF);
                    this.mOffGraphics.drawString(array[n4] + "  ", this.mYPosition, mxPosition);
                    mxPosition += this.mLineHeight;
                }
            }
            this.mOffGraphics.setColor(this.mLINECOLOR);
            this.mOffGraphics.drawLine(2, mxPosition, this.mAppletWidth - 4, mxPosition);
            mxPosition += this.mLineHeight;
        }
        if (this.mBImage == null) {
            this.mOffGraphics.setColor(this.mRECTANGLECOLOR);
            this.mOffGraphics.drawRect(0, 0, this.mOffImage.getWidth(this) - 1, this.mOffImage.getHeight(this) - 1);
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.mMenuItems.size(); ++i) {
            ((MenuItem)this.mMenuItems.elementAt(i)).mActive = false;
        }
        this.showStatus("");
        this.mLastXPosition = -1;
        this.setCursor(new Cursor(0));
        return true;
    }
    
    public X_TextTicker() {
        this.mThread = null;
        this.mMenuItems = null;
        this.mOffImage = null;
        this.mBImage = null;
        this.mOffGraphics = null;
        this.mFont = new Font("Tahoma", 0, 10);
        this.mBACKCOLOR = this.stringToColor("FFFFFF");
        this.mFORECOLOROFF = this.stringToColor("800000");
        this.mLINECOLOR = this.stringToColor("FF0000");
        this.mRECTANGLECOLOR = this.stringToColor("0000FF");
        this.mPOINTCOLOR = this.stringToColor("FF0000");
        this.mFORECOLORON = this.stringToColor("FFFFFF");
        this.mDELAYTIME = 100;
        this.mXArtirma_Miktari = 2;
        this.mXPosition = this.mXArtirma_Miktari;
        this.mYPosition = 20;
        this.mLastXPosition = -1;
        this.mTotalMenuWidth = 0;
        this.mValidRegCode = false;
        System.out.println("X_TextTicker version 2.1\nCopyright 2000 by Applet World (appletworld@usa.net)");
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.mOffImage, 0, 0, this);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.mMenuItems.size(); ++i) {
            final MenuItem menuItem = this.mMenuItems.elementAt(i);
            if (menuItem.mActive) {
                this.showURL(menuItem.X_URL, menuItem.X_TargetFrame);
            }
        }
        return true;
    }
    
    public void destroy() {
        try {
            this.mOffGraphics.dispose();
            this.mOffImage = null;
            this.mThread = null;
            this.mMenuItems = null;
            this.mFont = null;
            this.mFontMetrics = null;
            System.gc();
        }
        catch (Exception ex) {
            System.out.println("error on destroy() -> " + ex.toString());
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void ActivateItem(final int n) {
        for (int i = 0; i < this.mMenuItems.size(); ++i) {
            ((MenuItem)this.mMenuItems.elementAt(i)).mActive = false;
        }
        if (n < 0) {
            return;
        }
        final String[] array = new String[15];
        this.mOffGraphics.setColor(this.mBACKCOLOR);
        this.mOffGraphics.fillRect(0, 0, this.mOffImage.getWidth(this), this.mOffImage.getHeight(this));
        int mxPosition = this.mXPosition;
        for (int j = 0; j < this.mMenuItems.size(); ++j) {
            final MenuItem menuItem = this.mMenuItems.elementAt(j);
            this.sbTokenize(menuItem.X_Message, array);
            final int n2 = mxPosition;
            for (int n3 = 0; !array[n3].equalsIgnoreCase(""); ++n3) {
                mxPosition += this.mLineHeight;
            }
            if (n > n2 - this.mLineHeight && n < mxPosition - this.mLineHeight) {
                menuItem.mActive = true;
            }
            mxPosition += this.mLineHeight;
        }
        this.mTotalMenuWidth = mxPosition - this.mXPosition;
        for (int k = 0; k < this.mMenuItems.size(); ++k) {
            final MenuItem menuItem2 = this.mMenuItems.elementAt(k);
            this.sbTokenize(menuItem2.X_Message, array);
            final int n4 = mxPosition;
            for (int n5 = 0; !array[n5].equalsIgnoreCase(""); ++n5) {
                mxPosition += this.mLineHeight;
            }
            if (n > n4 - this.mLineHeight && n < mxPosition - this.mLineHeight) {
                menuItem2.mActive = true;
            }
            mxPosition += this.mLineHeight;
        }
    }
    
    public void ReadMenuItems() {
        int n = 0;
        this.mMenuItems = new Vector(1, 1);
        for (String s = this.getParameter("m" + String.valueOf(n)); s != null && s != ""; s = this.getParameter("m" + String.valueOf(++n))) {
            String nextToken = "";
            String nextToken2 = "";
            String nextToken3 = "";
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
            final String nextToken4 = stringTokenizer.nextToken();
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                nextToken2 = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                nextToken3 = stringTokenizer.nextToken();
            }
            this.mMenuItems.addElement(new MenuItem(nextToken4, nextToken, nextToken2, nextToken3));
        }
    }
    
    public void start() {
        if (this.mThread == null) {
            try {
                (this.mThread = new Thread(this)).setPriority(1);
                this.mThread.start();
            }
            catch (Exception ex) {
                System.out.println("error on start() -> " + ex.toString());
            }
        }
    }
    
    public void showURL(final String s, final String s2) {
        URL url = null;
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
        try {
            url = new URL(s3 + s);
        }
        catch (Exception ex) {
            this.showStatus("ShowURL Exception");
        }
        this.getAppletContext().showDocument(url, s2);
    }
    
    private void sbTokenize(String trim, final String[] array) {
        trim = trim.trim();
        for (int i = 0; i < array.length; ++i) {
            array[i] = "";
        }
        if (trim == null || trim.equals("")) {
            return;
        }
        this.mTok = new StringTokenizer(trim);
        int n = 0;
        try {
            do {
                final String nextToken = this.mTok.nextToken();
                if (this.mFontMetrics.stringWidth(array[n] + " " + nextToken) < this.mLineWidth && !nextToken.equalsIgnoreCase("<BR>")) {
                    array[n] = new String(array[n] + " " + nextToken).trim();
                }
                else {
                    ++n;
                    array[n] = new String(nextToken.equalsIgnoreCase("<BR>") ? "" : nextToken).trim();
                }
            } while (this.mTok.hasMoreTokens());
        }
        catch (Exception ex) {
            System.out.println("error on sbTokenize() -> " + ex.toString());
        }
    }
    
    public void initX() {
        this.show();
        this.RegCodeCheck(this.getCodeBase().toString(), this.getParameter("regcode"));
        this.ReadParams();
        this.mOffImage = this.createImage(this.getSize().width, this.getSize().height);
        (this.mOffGraphics = this.mOffImage.getGraphics()).setFont(this.mFont);
        this.mFontMetrics = this.mOffGraphics.getFontMetrics(this.mFont);
        this.mSpaceWidth = this.mFontMetrics.stringWidth(" ");
        this.mAppletWidth = this.getSize().width;
        this.mAppletHeight = this.getSize().height;
        this.mYPosition = 5;
        this.mLineHeight = this.mFontMetrics.getHeight();
        this.mLineWidth = this.getSize().width - 10;
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
        final String s8 = "X_TextTicker";
        for (char c2 = '\0'; c2 < s8.length(); ++c2) {
            int1 += s8.charAt(c2) * c2 * '\u00c7';
        }
        if (("AWX" + String.valueOf(int1 % 10000000)).equalsIgnoreCase(s2)) {
            this.mValidRegCode = true;
        }
    }
    
    public void run() {
        this.initX();
    Label_0004_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        if (this.mLastXPosition == -1) {
                            this.mXPosition -= this.mXArtirma_Miktari;
                            if (this.mTotalMenuWidth + this.mXPosition <= this.mXArtirma_Miktari) {
                                this.mXPosition += this.mTotalMenuWidth;
                            }
                        }
                        this.NextStep();
                        this.repaint();
                        Thread.sleep(this.mDELAYTIME);
                    }
                }
                catch (Exception ex) {
                    System.out.println("error on run() -> " + ex.toString());
                    continue Label_0004_Outer;
                }
                continue;
            }
        }
    }
    
    private Color stringToColor(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
    
    public void ReadParams() {
        String nextToken = "Tahoma";
        int n = 0;
        int int1 = 10;
        new MediaTracker(this);
        final String parameter = this.getParameter("Font");
        if (parameter != null && parameter != "") {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, "|");
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
                else if (nextToken2.equalsIgnoreCase("PLAIN")) {
                    n = 0;
                }
            }
            if (stringTokenizer.hasMoreTokens()) {
                int1 = Integer.parseInt(stringTokenizer.nextToken());
            }
            this.mFont = new Font(nextToken, n, int1);
        }
        final String parameter2 = this.getParameter("rectangle_color");
        if (parameter2 != null && parameter2 != "") {
            this.mRECTANGLECOLOR = this.stringToColor(parameter2);
        }
        final String parameter3 = this.getParameter("linecolor");
        if (parameter3 != null && parameter3 != "") {
            this.mLINECOLOR = this.stringToColor(parameter3);
        }
        final String parameter4 = this.getParameter("backcolor");
        if (parameter4 != null && parameter4 != "") {
            this.mBACKCOLOR = this.stringToColor(parameter4);
        }
        final String parameter5 = this.getParameter("forecolorOff");
        if (parameter5 != null && parameter5 != "") {
            this.mFORECOLOROFF = this.stringToColor(parameter5);
        }
        final String parameter6 = this.getParameter("forecolorOn");
        if (parameter6 != null && parameter6 != "") {
            this.mFORECOLORON = this.stringToColor(parameter6);
        }
        final String parameter7 = this.getParameter("pointcolor");
        if (parameter7 != null && parameter7 != "") {
            this.mPOINTCOLOR = this.stringToColor(parameter7);
        }
        else {
            this.mPOINTCOLOR = this.mLINECOLOR;
        }
        final String parameter8 = this.getParameter("delaytime");
        if (parameter8 != null && parameter8 != "") {
            this.mDELAYTIME = Integer.parseInt(parameter8);
        }
        final String parameter9 = this.getParameter("image");
        if (parameter9 != null && parameter9 != "") {
            this.mBImage = this.getImage(this.getDocumentBase(), parameter9);
        }
        else {
            this.mBImage = null;
        }
        final String parameter10 = this.getParameter("delaytime");
        if (parameter10 != null && parameter10 != "") {
            this.mDELAYTIME = Integer.parseInt(parameter10);
        }
        this.ReadMenuItems();
        System.gc();
    }
    
    public boolean mouseMove(final Event event, final int n, final int mLastXPosition) {
        this.mLastXPosition = mLastXPosition;
        if (!this.mValidRegCode) {
            this.showStatus(" <<->> (C)2000 AppletWorld -> Web : http://go.to/appletworld, E-Mail : appletworld@usa.net <<->>");
        }
        else {
            for (int i = 0; i < this.mMenuItems.size(); ++i) {
                final MenuItem menuItem = this.mMenuItems.elementAt(i);
                if (menuItem.mActive) {
                    this.showStatus(menuItem.X_StatusMessage);
                }
            }
        }
        return true;
    }
}
