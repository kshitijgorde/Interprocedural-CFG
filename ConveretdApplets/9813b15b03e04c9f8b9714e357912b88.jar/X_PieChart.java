import java.awt.Cursor;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Point;
import java.net.URL;
import java.awt.image.ImageObserver;
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

public class X_PieChart extends Applet
{
    private String mTitle;
    private Vector mPieParts;
    private Image mBackgroundImage;
    private Image mOffImage;
    private Graphics mOffGraphics;
    private Font mFont;
    private Font mFontA;
    private Font mFontTitle;
    private FontMetrics mFontMetrics;
    private Color mBACKCOLOR;
    private Color mFORECOLOR;
    private int lcMaxTitleWidth;
    private String[] mMessages;
    private int mLineWidth;
    private int mCommentLeft;
    private Color mActiveColor;
    private Color mActiveColorShadow;
    StringTokenizer mTok;
    int mAutomaticOut;
    int mTitleAlign;
    int mLeftX;
    int mLeftY;
    int mWidth;
    int mHeight;
    int mCenterX;
    int mCenterY;
    int mDistance;
    int mDeep;
    int mShowRatios;
    private boolean mValidRegCode;
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.mPieParts.size(); ++i) {
            ((X_PiePart)this.mPieParts.elementAt(i)).active = false;
        }
        this.PrepareChart();
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.mOffImage, 0, 0, this);
    }
    
    public X_PieChart() {
        this.mTitle = new String("");
        this.mPieParts = null;
        this.mBackgroundImage = null;
        this.mOffImage = null;
        this.mOffGraphics = null;
        this.mFont = new Font("Tahoma", 0, 9);
        this.mFontA = new Font("Tahoma", 1, 9);
        this.mFontTitle = new Font("Tahoma", 0, 11);
        this.mBACKCOLOR = this.stringToColor("FFFFFF");
        this.mFORECOLOR = this.stringToColor("FFFFFF");
        this.mLineWidth = 0;
        this.mCommentLeft = 0;
        this.mActiveColor = null;
        this.mActiveColorShadow = null;
        this.mAutomaticOut = 0;
        this.mTitleAlign = 0;
        this.mLeftX = 0;
        this.mLeftY = 0;
        this.mWidth = 0;
        this.mDistance = 10;
        this.mDeep = 7;
        this.mShowRatios = 1;
        this.mValidRegCode = false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.mPieParts.size(); ++i) {
            ((X_PiePart)this.mPieParts.elementAt(i)).active = false;
        }
        final int returnIndex = this.ReturnIndex(n, n2);
        if (returnIndex >= 0) {
            final X_PiePart x_PiePart = this.mPieParts.elementAt(returnIndex);
            this.showStatus(x_PiePart.X_Message);
            x_PiePart.active = true;
            if (!x_PiePart.X_URL.equalsIgnoreCase("")) {
                this.showURL(x_PiePart.X_URL, x_PiePart.X_TargetFrame);
            }
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
    
    private void PrepareChart() {
        this.mOffGraphics.setColor(this.mBACKCOLOR);
        this.mOffGraphics.fillRect(0, 0, this.size().width, this.size().height);
        this.mOffGraphics.drawImage(this.mBackgroundImage, (this.size().width - this.mBackgroundImage.getWidth(this)) / 2, (this.size().height - this.mBackgroundImage.getHeight(this)) / 2, this);
        this.DrawTitles();
        this.DrawPie();
        this.mOffGraphics.setFont(this.mFontTitle);
        this.mOffGraphics.setColor(this.mFORECOLOR);
        int n = 0;
        if (this.mTitleAlign == 0) {
            n = 5;
        }
        else if (this.mTitleAlign == 1) {
            n = (this.getSize().width - this.getFontMetrics(this.mFontTitle).stringWidth(this.mTitle)) / 2;
        }
        else if (this.mTitleAlign == 2) {
            n = this.getSize().width - this.getFontMetrics(this.mFontTitle).stringWidth(this.mTitle) - 5;
        }
        this.mOffGraphics.drawString(this.mTitle, n, this.getFontMetrics(this.mFontTitle).getAscent());
        if (!this.mValidRegCode) {
            this.mOffGraphics.setColor(this.mBACKCOLOR.darker());
            this.mOffGraphics.setFont(this.mFont);
            this.mOffGraphics.drawString("http://appletworld.xoasis.com", 10, this.getSize().height - 2);
        }
    }
    
    private void DrawTitles() {
        final int n = 10;
        final int n2 = this.getSize().width - this.lcMaxTitleWidth - n - 5;
        final int height = this.mFontMetrics.getHeight();
        final int n3 = this.getSize().height - height * this.mPieParts.size() - 2;
        for (int i = 0; i < this.mPieParts.size(); ++i) {
            final X_PiePart x_PiePart = this.mPieParts.elementAt(i);
            if (x_PiePart.active) {
                this.mOffGraphics.setFont(this.mFont);
                this.mOffGraphics.setColor(this.mActiveColorShadow);
                this.sbTokenize(x_PiePart.X_Message, this.mMessages);
                int n4 = 0;
                while (!this.mMessages[n4].equals("")) {
                    this.mOffGraphics.drawString(this.mMessages[n4], this.mCommentLeft, (this.mTitle.equals("") ? 5 : (this.mFontMetrics.getAscent() + 3)) + this.mFontMetrics.getHeight() + n4 * height);
                    if (++n4 > 9) {
                        break;
                    }
                }
                this.mOffGraphics.setColor(this.mActiveColor);
            }
            else {
                this.mOffGraphics.setFont(this.mFontA);
                this.mOffGraphics.setColor(x_PiePart.X_Color);
            }
            this.mOffGraphics.fill3DRect(n2, n3 + i * height, n, n, true);
            this.mOffGraphics.drawString(x_PiePart.X_Name, n2 + n + 5, n3 + i * height + n);
        }
    }
    
    private void CalculateAngles() {
        int n = 0;
        for (int i = 0; i < this.mPieParts.size(); ++i) {
            final X_PiePart x_PiePart = this.mPieParts.elementAt(i);
            n += x_PiePart.X_Value;
            if (this.lcMaxTitleWidth < this.mFontMetrics.stringWidth(x_PiePart.X_Name)) {
                this.lcMaxTitleWidth = this.mFontMetrics.stringWidth(x_PiePart.X_Name);
            }
        }
        for (int j = 0; j < this.mPieParts.size(); ++j) {
            final X_PiePart x_PiePart2 = this.mPieParts.elementAt(j);
            x_PiePart2.Angle = x_PiePart2.X_Value * 360 / n;
            final X_PiePart x_PiePart3 = x_PiePart2;
            x_PiePart3.X_Name = x_PiePart3.X_Name + " (" + String.valueOf(x_PiePart2.Angle * 100 / 360) + "%)";
        }
        int n2 = 0;
        for (int k = 0; k < this.mPieParts.size(); ++k) {
            n2 += ((X_PiePart)this.mPieParts.elementAt(k)).Angle;
        }
        if (n2 < 360) {
            final X_PiePart x_PiePart4 = this.mPieParts.elementAt(0);
            x_PiePart4.Angle += 360 - n2;
        }
        for (int l = 0; l < this.mPieParts.size(); ++l) {
            final X_PiePart x_PiePart5 = this.mPieParts.elementAt(l);
            if (this.lcMaxTitleWidth < this.mFontMetrics.stringWidth(x_PiePart5.X_Name)) {
                this.lcMaxTitleWidth = this.mFontMetrics.stringWidth(x_PiePart5.X_Name);
            }
        }
        this.lcMaxTitleWidth += 5;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void DrawPie() {
        final Color color = new Color(1, 1, 1);
        final Color color2 = new Color(1, 1, 1);
        int n = 15;
        for (int i = 0; i < this.mPieParts.size(); ++i) {
            final X_PiePart x_PiePart = this.mPieParts.elementAt(i);
            Color color3;
            Color color4;
            if (x_PiePart.active) {
                color3 = this.mActiveColor;
                color4 = this.mActiveColorShadow;
            }
            else {
                color3 = x_PiePart.X_Color;
                color4 = x_PiePart.X_Color_Shadow;
            }
            this.DrawPiePart(n, x_PiePart.Angle, color4, color3);
            n += x_PiePart.Angle;
        }
    }
    
    private int ReturnIndex(final int n, final int n2) {
        final int n3 = 10;
        final int n4 = this.getSize().width - this.lcMaxTitleWidth - n3 - 5;
        final int height = this.mFontMetrics.getHeight();
        final int n5 = this.getSize().height - height * this.mPieParts.size() - 2;
        for (int i = 0; i < this.mPieParts.size(); ++i) {
            if (n > n4 && n2 > n5 + i * height && n2 < n5 + i * height + n3) {
                return i;
            }
        }
        return -1;
    }
    
    public String getAppletInfo() {
        return "X_PieChart version 1.0\r\nCopyright 2001 by Applet World (appletworld@usa.net)";
    }
    
    public void ReadPieParts() {
        int n = 0;
        this.mPieParts = new Vector(1, 1);
        for (String s = this.getParameter("p" + String.valueOf(n)); s != null && s != ""; s = this.getParameter("p" + String.valueOf(++n))) {
            String nextToken = "";
            String nextToken2 = "";
            String nextToken3 = "";
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "#");
            final String nextToken4 = stringTokenizer.nextToken();
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final Color stringToColor = this.stringToColor(stringTokenizer.nextToken());
            final Color stringToColor2 = this.stringToColor(stringTokenizer.nextToken());
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                nextToken2 = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                nextToken3 = stringTokenizer.nextToken();
            }
            this.mPieParts.addElement(new X_PiePart(nextToken4, int1, stringToColor, stringToColor2, nextToken, nextToken2, nextToken3));
        }
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
        catch (Exception ex) {}
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
    
    private Point fnReturnCenter(final int n, final int n2, int n3) {
        final Point point = new Point();
        if (this.mAutomaticOut == 1) {
            n3 = n3 * 45 / n2;
        }
        final int n4 = n + n2 / 2;
        if (n4 <= 90) {
            final int x = n3 * (90 - n4) / 90;
            final int n5 = n3 * n4 / 90;
            point.x = x;
            point.y = n5 * -1;
        }
        else if (n4 >= 90 && n4 <= 180) {
            final int x2 = n3 * (90 - n4) / 90;
            final int n6 = n3 * (180 - n4) / 90;
            point.x = x2;
            point.y = n6 * -1;
        }
        else if (n4 >= 180 && n4 <= 270) {
            final int x3 = n3 * (n4 - 270) / 90;
            final int n7 = n3 * (180 - n4) / 90;
            point.x = x3;
            point.y = n7 * -1;
        }
        else if (n4 >= 270 && n4 <= 360) {
            final int x4 = n3 * (n4 - 270) / 90;
            final int n8 = n3 * (n4 - 360) / 90;
            point.x = x4;
            point.y = n8 * -1;
        }
        return point;
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
        if (("AW" + String.valueOf(((n / c + 123654) * c % 1000000 * (c + '\n') + c - 1) % 10000000)).equalsIgnoreCase(s2)) {
            this.mValidRegCode = true;
        }
    }
    
    public void init() {
        this.RegCodeCheck(this.getCodeBase().toString(), this.getParameter("regcode"));
        this.ReadParams();
        this.mOffImage = this.createImage(this.size().width, this.size().height);
        (this.mOffGraphics = this.mOffImage.getGraphics()).setFont(this.mFont);
        this.mFontMetrics = this.getFontMetrics(this.mFont);
        this.mCommentLeft = this.size().width * 3 / 5;
        this.mLineWidth = this.size().width * 2 / 5 - 2;
        final int height = this.getSize().height;
        this.mLeftX = this.mDistance;
        if (this.mTitle.equals("")) {
            this.mLeftY = 5 + this.mDistance * 2;
        }
        else {
            this.mLeftY = 15 + this.mDistance * 2;
        }
        this.mWidth = height * 3 / 2 - this.mLeftX * 2 - this.mDistance * 3;
        this.mHeight = height - this.mLeftY - 10;
        this.mMessages = new String[10];
        this.CalculateAngles();
        this.PrepareChart();
        this.repaint();
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
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, "#");
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
            this.mFontA = new Font(nextToken, 1, int1);
            this.mFontTitle = new Font(nextToken, 1, 11);
        }
        final String parameter3 = this.getParameter("title");
        if (parameter3 != null && parameter3 != "") {
            this.mTitle = parameter3;
        }
        final String parameter4 = this.getParameter("backcolor");
        if (parameter4 != null && parameter4 != "") {
            this.mBACKCOLOR = this.stringToColor(parameter4);
        }
        final String parameter5 = this.getParameter("forecolor");
        if (parameter5 != null && parameter5 != "") {
            this.mFORECOLOR = this.stringToColor(parameter5);
        }
        final String parameter6 = this.getParameter("activecolor");
        if (parameter6 != null && parameter6 != "") {
            this.mActiveColor = this.stringToColor(parameter6);
        }
        final String parameter7 = this.getParameter("activecolor_shadow");
        if (parameter7 != null && parameter7 != "") {
            this.mActiveColorShadow = this.stringToColor(parameter7);
        }
        final String parameter8 = this.getParameter("deep");
        if (parameter8 != null && parameter8 != "") {
            this.mDeep = Integer.parseInt(parameter8);
        }
        final String parameter9 = this.getParameter("distance");
        if (parameter9 != null && parameter9 != "") {
            this.mDistance = Integer.parseInt(parameter9);
        }
        final String parameter10 = this.getParameter("showratios");
        if (parameter10 != null && parameter10 != "") {
            this.mShowRatios = Integer.parseInt(parameter10);
        }
        final String parameter11 = this.getParameter("titlealign");
        if (parameter11 != null && parameter11 != "") {
            this.mTitleAlign = Integer.parseInt(parameter11);
        }
        this.ReadPieParts();
        System.gc();
    }
    
    public void DrawPiePart(final int n, final int n2, final Color color, final Color color2) {
        final Point point = new Point(1, 1);
        final Point fnReturnCenter = this.fnReturnCenter(n, n2, this.mDistance);
        final int n3 = this.mLeftX + fnReturnCenter.x;
        final int n4 = this.mLeftY + fnReturnCenter.y;
        this.mOffGraphics.setColor(Color.white);
        this.mOffGraphics.setColor(color);
        for (int i = 1; i <= this.mDeep; ++i) {
            this.mOffGraphics.fillArc(n3, n4 - i, this.mWidth, this.mHeight, n, n2);
        }
        this.mOffGraphics.setColor(color2);
        this.mOffGraphics.fillArc(n3, n4 - this.mDeep, this.mWidth, this.mHeight, n, n2);
        if (this.mShowRatios == 1) {
            final Point fnReturnCenter2 = this.fnReturnCenter(n, n2, this.mHeight / 2);
            fnReturnCenter2.x += this.mLeftX + this.mWidth / 2;
            fnReturnCenter2.y += this.mLeftY + this.mHeight / 2;
            this.mOffGraphics.setColor(color);
            final String s = new String(String.valueOf(n2 * 100 / 360) + "%");
            this.mOffGraphics.setFont(new Font("Tahoma", 1, 12));
            System.out.println(this.mFontMetrics.stringWidth(s));
            this.mOffGraphics.drawString(s, fnReturnCenter2.x - 5, fnReturnCenter2.y - 5);
            this.mOffGraphics.drawString(s, fnReturnCenter2.x - 5 + 1, fnReturnCenter2.y - 5);
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.mPieParts.size(); ++i) {
            ((X_PiePart)this.mPieParts.elementAt(i)).active = false;
        }
        final int returnIndex = this.ReturnIndex(n, n2);
        if (returnIndex >= 0) {
            final X_PiePart x_PiePart = this.mPieParts.elementAt(returnIndex);
            this.showStatus(x_PiePart.X_URL);
            x_PiePart.active = true;
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
        this.PrepareChart();
        this.repaint();
        if (!this.mValidRegCode) {
            this.showStatus("<<>> (c)1999-2001 AppletWorld, Free, Original Java Applets -> Web : http://go.to/appletworld, E-Mail : appletworld@usa.net <<>>");
        }
        return true;
    }
}
