import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
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

public class X_MenuSN extends Applet implements Runnable
{
    private Vector mMenuItems;
    private Image mBackgroundImage;
    private Image moffImage;
    private Graphics moffGraphics;
    private Font mFont;
    private FontMetrics mFontMetrics;
    private int mTRANSITIonTYPE;
    private String mDefaultMessage;
    private Color mBACKCOLOR;
    private Color mFORECOLOR;
    private Color mBACKCOLORoff;
    private Color mBACKCOLORon;
    private Color mFORECOLORoff;
    private Color mFORECOLORon;
    private int mALIGNMENT;
    private int mSTEPCOUNT;
    private int mDELAYTIME;
    private Thread mThread;
    int mMenuWidth;
    int mMenuHeight;
    int mActiveIndex;
    int mCurrentTransition;
    private boolean mValidRegCode;
    
    private void Draw_Transition(final int n, final boolean b, final int n2, final String s) {
        int n3 = 0;
        switch (this.mALIGNMENT) {
            case 1: {
                n3 = 5 + (this.mMenuWidth - 10 - this.mFontMetrics.stringWidth(s)) / 2;
                break;
            }
            case 2: {
                n3 = this.mMenuWidth - (this.mFontMetrics.stringWidth(s) + 5);
                break;
            }
            default: {
                n3 = 5;
                break;
            }
        }
        if (b) {
            this.moffGraphics.setColor(this.mBACKCOLORon);
            this.moffGraphics.fillRect(n * this.mMenuWidth, 0, this.mMenuWidth - 1, this.mMenuHeight - 1);
            this.moffGraphics.setColor(this.mFORECOLORon);
            this.moffGraphics.drawString(s, n * this.mMenuWidth + n3, this.mMenuHeight * 3 / 4);
            return;
        }
        final int red = this.mBACKCOLORoff.getRed();
        final int n4 = red + (this.mBACKCOLORon.getRed() - red) * n2 / this.mSTEPCOUNT;
        final int green = this.mBACKCOLORoff.getGreen();
        final int n5 = green + (this.mBACKCOLORon.getGreen() - green) * n2 / this.mSTEPCOUNT;
        final int blue = this.mBACKCOLORoff.getBlue();
        final int n6 = blue + (this.mBACKCOLORon.getBlue() - blue) * n2 / this.mSTEPCOUNT;
        if (this.mTRANSITIonTYPE == 1) {
            this.moffGraphics.setColor(this.mBACKCOLORoff);
            this.moffGraphics.fillRect(n * this.mMenuWidth, 0, this.mMenuWidth - 1, this.mMenuHeight - 1);
            this.moffGraphics.setColor(new Color(n4, n5, n6));
            if (this.mBackgroundImage != null) {
                if (n2 != 0) {
                    final int n7 = this.mMenuWidth * n2 / this.mSTEPCOUNT;
                    final int n8 = this.mMenuHeight * n2 / this.mSTEPCOUNT;
                    this.moffGraphics.fillRect(n * this.mMenuWidth + (this.mMenuWidth - n7) / 2, (this.mMenuHeight - n8) / 2, n7, n8);
                }
            }
            else {
                final int n9 = this.mMenuWidth * n2 / this.mSTEPCOUNT;
                final int n10 = this.mMenuHeight * n2 / this.mSTEPCOUNT;
                this.moffGraphics.fillRect(n * this.mMenuWidth + (this.mMenuWidth - n9) / 2, (this.mMenuHeight - n10) / 2, n9, n10);
            }
            this.moffGraphics.setColor(this.mFORECOLORoff);
            this.moffGraphics.drawString(s, n * this.mMenuWidth + n3, this.mMenuHeight * 3 / 4);
            return;
        }
        if (this.mTRANSITIonTYPE == 2) {
            this.moffGraphics.setColor(this.mBACKCOLORoff);
            this.moffGraphics.fillRect(n * this.mMenuWidth, 0, this.mMenuWidth - 1, this.mMenuHeight - 1);
            this.moffGraphics.setColor(new Color(n4, n5, n6));
            if (this.mBackgroundImage != null) {
                if (n2 != 0) {
                    final int n11 = this.mMenuWidth * n2 / this.mSTEPCOUNT;
                    final int mMenuHeight = this.mMenuHeight;
                    this.moffGraphics.fillRect(n * this.mMenuWidth + (this.mMenuWidth - n11) / 2, (this.mMenuHeight - mMenuHeight) / 2, n11, mMenuHeight);
                }
            }
            else {
                final int n12 = this.mMenuWidth * n2 / this.mSTEPCOUNT;
                final int mMenuHeight2 = this.mMenuHeight;
                this.moffGraphics.fillRect(n * this.mMenuWidth + (this.mMenuWidth - n12) / 2, (this.mMenuHeight - mMenuHeight2) / 2, n12, mMenuHeight2);
            }
            this.moffGraphics.setColor(this.mFORECOLORoff);
            this.moffGraphics.drawString(s, n * this.mMenuWidth + n3, this.mMenuHeight * 3 / 4);
            return;
        }
        if (this.mTRANSITIonTYPE == 0) {
            this.moffGraphics.setColor(this.mBACKCOLORoff);
            this.moffGraphics.fillRect(n * this.mMenuWidth, 0, this.mMenuWidth - 1, this.mMenuHeight - 1);
            this.moffGraphics.setColor(new Color(n4, n5, n6));
            if (this.mBackgroundImage != null) {
                if (n2 != 0) {
                    this.moffGraphics.fillRect(n * this.mMenuWidth, 0, this.mMenuWidth - 1, this.mMenuHeight - 1);
                }
            }
            else {
                this.moffGraphics.fillRect(n * this.mMenuWidth, 0, this.mMenuWidth - 1, this.mMenuHeight - 1);
            }
            this.moffGraphics.setColor(this.mFORECOLORoff);
            this.moffGraphics.drawString(s, n * this.mMenuWidth + n3, this.mMenuHeight * 3 / 4);
            return;
        }
        if (this.mTRANSITIonTYPE == 3) {
            this.moffGraphics.setColor(this.mBACKCOLORoff);
            if (this.mBackgroundImage != null) {
                if (n2 != 0) {
                    this.moffGraphics.fillRect(n * this.mMenuWidth, 0, this.mMenuWidth - 1, this.mMenuHeight - 1);
                }
            }
            else {
                this.moffGraphics.fillRect(n * this.mMenuWidth, 0, this.mMenuWidth - 1, this.mMenuHeight - 1);
            }
            this.moffGraphics.setColor(this.mFORECOLORoff);
            this.moffGraphics.drawString(s, n * this.mMenuWidth + n3, this.mMenuHeight * 3 / 4);
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(12));
        return true;
    }
    
    public void stop() {
        if (this.mThread != null) {
            try {
                this.mThread.stop();
                this.mThread = null;
            }
            catch (Exception ex) {
                System.out.println("error on start() -> " + ex.toString());
            }
            System.gc();
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.mMenuItems.size(); ++i) {
            ((X_MenuItem)this.mMenuItems.elementAt(i)).mActive = false;
        }
        this.mActiveIndex = -1;
        this.setCursor(new Cursor(0));
        return true;
    }
    
    public X_MenuSN() {
        this.mMenuItems = null;
        this.mBackgroundImage = null;
        this.moffImage = null;
        this.moffGraphics = null;
        this.mFont = new Font("Tahoma", 0, 12);
        this.mTRANSITIonTYPE = 0;
        this.mDefaultMessage = new String("");
        this.mBACKCOLOR = this.stringToColor("000000");
        this.mFORECOLOR = this.stringToColor("FFFFFF");
        this.mBACKCOLORoff = this.stringToColor("FFFFFF");
        this.mBACKCOLORon = this.stringToColor("AAAAFF");
        this.mFORECOLORoff = this.stringToColor("800000");
        this.mFORECOLORon = this.stringToColor("FFFFFF");
        this.mALIGNMENT = 0;
        this.mSTEPCOUNT = 50;
        this.mDELAYTIME = 50;
        this.mThread = null;
        this.mMenuWidth = 150;
        this.mMenuHeight = 50;
        this.mActiveIndex = -1;
        this.mCurrentTransition = 0;
        this.mValidRegCode = false;
        System.out.println("X_MenuSN version 2.1\r\nCopyright 1999-2001 by Applet World (appletworld@usa.net)");
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.moffImage, 0, 0, this);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final X_MenuItem x_MenuItem = this.mMenuItems.elementAt(this.mActiveIndex);
        this.showURL(x_MenuItem.X_URL, x_MenuItem.X_TargetFrame);
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void ReadMenuItems() {
        int n = 0;
        this.mMenuItems = new Vector(1, 1);
        for (String s = this.getParameter("m" + String.valueOf(n)); s != null && s != ""; s = this.getParameter("m" + String.valueOf(++n))) {
            String nextToken = "";
            String nextToken2 = "";
            String nextToken3 = "";
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
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
            this.mMenuItems.addElement(new X_MenuItem(nextToken4, nextToken, nextToken2, nextToken3));
        }
    }
    
    public void start() {
        this.moffImage = this.createImage(this.size().width, this.size().height);
        (this.moffGraphics = this.moffImage.getGraphics()).setFont(this.mFont);
        this.mFontMetrics = this.getFontMetrics(this.mFont);
        this.mMenuWidth = 0;
        int i;
        for (i = 0; i < this.mMenuItems.size(); ++i) {
            final X_MenuItem x_MenuItem = this.mMenuItems.elementAt(i);
            this.mMenuWidth = ((this.mFontMetrics.stringWidth(x_MenuItem.X_Name) > this.mMenuWidth) ? this.mFontMetrics.stringWidth(x_MenuItem.X_Name) : this.mMenuWidth);
        }
        this.mMenuWidth += 5;
        this.mMenuWidth = this.size().width / i;
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
    
    private void Draw_Menu() {
        if (this.mBackgroundImage != null) {
            try {
                this.moffGraphics.drawImage(this.mBackgroundImage, 0, 0, this);
            }
            catch (Exception ex) {
                this.moffGraphics.setColor(this.mBACKCOLOR);
                this.moffGraphics.fillRect(0, 0, this.size().width, this.size().height);
            }
        }
        else {
            this.moffGraphics.setColor(this.mBACKCOLOR);
            this.moffGraphics.fillRect(0, 0, this.size().width, this.size().height);
        }
        for (int i = 0; i < this.mMenuItems.size(); ++i) {
            final X_MenuItem x_MenuItem = this.mMenuItems.elementAt(i);
            this.Draw_Transition(i, x_MenuItem.mActive, x_MenuItem.mTransitionLevel, x_MenuItem.X_Name);
        }
        final int red = this.mFORECOLOR.getRed();
        final int n = red + (this.mBACKCOLOR.getRed() - red) * this.mCurrentTransition / this.mSTEPCOUNT;
        final int green = this.mFORECOLOR.getGreen();
        final int n2 = green + (this.mBACKCOLOR.getGreen() - green) * this.mCurrentTransition / this.mSTEPCOUNT;
        final int blue = this.mFORECOLOR.getBlue();
        final int n3 = blue + (this.mBACKCOLOR.getBlue() - blue) * this.mCurrentTransition / this.mSTEPCOUNT;
        if (this.mActiveIndex >= 0) {
            final X_MenuItem x_MenuItem2 = this.mMenuItems.elementAt(this.mActiveIndex);
            this.moffGraphics.setColor(new Color(n, n2, n3));
            this.moffGraphics.drawString(x_MenuItem2.X_Message, 5, this.getSize().height - 2);
            this.moffGraphics.drawString(x_MenuItem2.X_Message, 6, this.getSize().height - 2);
            return;
        }
        this.moffGraphics.setColor(new Color(n, n2, n3));
        if (this.mValidRegCode) {
            this.moffGraphics.drawString(this.mDefaultMessage, 5, this.getSize().height - 2);
            this.moffGraphics.drawString(this.mDefaultMessage, 6, this.getSize().height - 2);
            return;
        }
        this.moffGraphics.drawString("Copyright 1999-2001 by AppletWorld (appletworld.xoasis.com)", 5, this.getSize().height - 2);
        this.moffGraphics.drawString("Copyright 1999-2001 by AppletWorld (appletworld.xoasis.com)", 6, this.getSize().height - 2);
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
    
    private void Manage_Transitions() {
        for (int i = 0; i < this.mMenuItems.size(); ++i) {
            final X_MenuItem x_MenuItem = this.mMenuItems.elementAt(i);
            if (!x_MenuItem.mActive) {
                final X_MenuItem x_MenuItem2 = x_MenuItem;
                int mTransitionLevel;
                if (x_MenuItem.mTransitionLevel > 0) {
                    final X_MenuItem x_MenuItem3 = x_MenuItem;
                    mTransitionLevel = --x_MenuItem3.mTransitionLevel;
                }
                else {
                    mTransitionLevel = 0;
                }
                x_MenuItem2.mTransitionLevel = mTransitionLevel;
            }
        }
        this.mCurrentTransition = ((this.mCurrentTransition - 1 > 0) ? (--this.mCurrentTransition) : 0);
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
        final String s8 = "X_MenuSN";
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
                    this.Manage_Transitions();
                    this.Draw_Menu();
                    this.repaint();
                    Thread.sleep(this.mDELAYTIME);
                }
            }
            catch (Exception ex) {
                System.out.println("error on start() -> " + ex.toString());
                continue;
            }
            break;
        }
    }
    
    public void init() {
        this.show();
        this.mMenuHeight = this.size().height / 2;
        this.RegCodeCheck(this.getCodeBase().toString(), this.getParameter("regcode"));
        this.ReadParams();
    }
    
    private Color stringToColor(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
    
    public void ReadParams() {
        String nextToken = "Tahoma";
        int n = 0;
        int int1 = 10;
        final MediaTracker mediaTracker = new MediaTracker(this);
        final String parameter = this.getParameter("image");
        if (parameter != null && parameter != "") {
            mediaTracker.addImage(this.mBackgroundImage = this.getImage(this.getDocumentBase(), parameter), 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {
                System.out.println("error on ReadParams() -> " + ex.toString());
            }
        }
        final String parameter2 = this.getParameter("font");
        if (parameter2 != null && parameter2 != "") {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, "|");
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
        final String parameter3 = this.getParameter("tranType");
        if (parameter3 != null && parameter3 != "") {
            this.mTRANSITIonTYPE = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("backcolor");
        if (parameter4 != null && parameter4 != "") {
            this.mBACKCOLOR = this.stringToColor(parameter4);
        }
        final String parameter5 = this.getParameter("forecolor");
        if (parameter5 != null && parameter5 != "") {
            this.mFORECOLOR = this.stringToColor(parameter5);
        }
        final String parameter6 = this.getParameter("backcoloroff");
        if (parameter6 != null && parameter6 != "") {
            this.mBACKCOLORoff = this.stringToColor(parameter6);
        }
        final String parameter7 = this.getParameter("backcoloron");
        if (parameter7 != null && parameter7 != "") {
            this.mBACKCOLORon = this.stringToColor(parameter7);
        }
        final String parameter8 = this.getParameter("forecoloroff");
        if (parameter8 != null && parameter8 != "") {
            this.mFORECOLORoff = this.stringToColor(parameter8);
        }
        final String parameter9 = this.getParameter("forecoloron");
        if (parameter9 != null && parameter9 != "") {
            this.mFORECOLORon = this.stringToColor(parameter9);
        }
        final String parameter10 = this.getParameter("stepcount");
        if (parameter10 != null && parameter10 != "") {
            this.mSTEPCOUNT = Integer.parseInt(parameter10);
        }
        final String parameter11 = this.getParameter("delaytime");
        if (parameter11 != null && parameter11 != "") {
            this.mDELAYTIME = Integer.parseInt(parameter11);
        }
        final String parameter12 = this.getParameter("dmessage");
        if (parameter12 != null && parameter12 != "") {
            this.mDefaultMessage = parameter12;
        }
        final String parameter13 = this.getParameter("alignment");
        if (parameter13 != null && parameter13 != "") {
            if (parameter13.equalsIgnoreCase("left")) {
                this.mALIGNMENT = 0;
            }
            else if (parameter13.equalsIgnoreCase("center")) {
                this.mALIGNMENT = 1;
            }
            else if (parameter13.equalsIgnoreCase("right")) {
                this.mALIGNMENT = 2;
            }
        }
        this.ReadMenuItems();
        System.gc();
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.mMenuItems.size(); ++i) {
            ((X_MenuItem)this.mMenuItems.elementAt(i)).mActive = false;
        }
        final int intValue = (int)(Object)new Float(n / this.mMenuWidth);
        final X_MenuItem x_MenuItem = this.mMenuItems.elementAt(intValue);
        if (!x_MenuItem.mActive) {
            this.mCurrentTransition = this.mSTEPCOUNT / 2;
            x_MenuItem.mActive = true;
            x_MenuItem.mTransitionLevel = this.mSTEPCOUNT;
            this.mActiveIndex = intValue;
        }
        this.showStatus(x_MenuItem.X_Message);
        if (!this.mValidRegCode) {
            this.showStatus(" <<->> (C)2000 AppletWorld -> Web : http://go.to/appletworld, E-Mail : appletworld@usa.net <<->>");
        }
        return true;
    }
}
