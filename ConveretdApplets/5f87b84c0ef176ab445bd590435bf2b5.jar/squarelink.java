import java.awt.Event;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class squarelink extends Applet implements ImageObserver
{
    public static final String mszAppletInfo = "MightyLink/squarelink for Java 1.0\nVersion 1.0.1\nCopyright 1998-1999 by Maier Media\nhttp://www.maiermedia.com\n";
    public static final int FIRE_LINK = 0;
    public static final int CAPTION_CHANGE = 1;
    public squarehelper mLinkButton;
    public Rectangle mButtonRect;
    public int mnButtonX;
    public int mnButtonY;
    public int mnButtonWidth;
    public int mnButtonHeight;
    public URL murlImage;
    public Image miImage;
    public boolean mbImageReady;
    public Color mBackgroundColor;
    public Color mBorderColor;
    public Color mButtonShadow;
    public int mnBorderWidth;
    public String msLink;
    public String msCaption;
    public String msMouseCaption;
    public String msClickCaption;
    public boolean mbMouseOver;
    public boolean mbMouseDown;
    public boolean mbMouseJustPressed;
    public boolean mbMouseDragOut;
    
    public String getAppletInfo() {
        return "MightyLink/squarelink for Java 1.0\nVersion 1.0.1\nCopyright 1998-1999 by Maier Media\nhttp://www.maiermedia.com\n";
    }
    
    public void init() {
        this.setDefaults();
        this.getParameters();
        this.add(this.mLinkButton);
        this.loadImages();
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.bounds();
        this.mLinkButton.bounds();
        graphics.setColor(this.mBackgroundColor);
        graphics.fillRect(0, 0, bounds.width, bounds.height);
        if (this.miImage != null && this.mbImageReady) {
            graphics.drawImage(this.miImage, 0, 0, bounds.width, bounds.height, this.mBackgroundColor, this);
        }
        if (this.mButtonShadow != null) {
            graphics.setColor(this.mButtonShadow);
            graphics.fillRect(this.mnButtonX + this.mnButtonWidth, this.mnButtonY + 2, 2, this.mnButtonHeight);
            graphics.fillRect(this.mnButtonX + 2, this.mnButtonY + this.mnButtonHeight, this.mnButtonWidth, 2);
        }
        if (this.mbMouseDragOut) {
            this.mLinkButton.reshape(this.mnButtonX, this.mnButtonY, this.mnButtonWidth, this.mnButtonHeight);
            this.mbMouseDragOut = false;
        }
        if (this.mbMouseOver && this.mbMouseDown) {
            this.mLinkButton.reshape(this.mnButtonX + 1, this.mnButtonY + 1, this.mnButtonWidth, this.mnButtonHeight);
            return;
        }
        this.mLinkButton.reshape(this.mnButtonX, this.mnButtonY, this.mnButtonWidth, this.mnButtonHeight);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            if (image == this.mLinkButton.miImage) {
                this.mLinkButton.mbImageReady = true;
            }
            if (image == this.miImage) {
                this.mbImageReady = true;
            }
            if (image == this.mLinkButton.miMouseImage) {
                this.mLinkButton.mbMouseImageReady = true;
            }
            if (image == this.mLinkButton.miClickImage) {
                this.mLinkButton.mbClickImageReady = true;
            }
            this.repaint();
            this.mLinkButton.repaint();
            return true;
        }
        return super.imageUpdate(image, n, n2, n3, n4, n5);
    }
    
    public void fireLink() {
        try {
            this.getAppletContext().showDocument(new URL(this.msLink));
        }
        catch (Exception ex) {
            System.out.println("Unable to Link -- " + this.msLink + ex.toString());
        }
    }
    
    public void setDefaults() {
        final Rectangle bounds = this.bounds();
        this.mnButtonX = 0;
        this.mnButtonY = 0;
        this.mnButtonWidth = bounds.width - 1;
        this.mnButtonHeight = bounds.height - 1;
        this.mnBorderWidth = 0;
        try {
            this.murlImage = new URL(this.getCodeBase().toString());
        }
        catch (Exception ex) {}
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.msCaption);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.getAppletContext().showStatus("");
        return true;
    }
    
    public void showStatus(final String s) {
        this.getAppletContext().showStatus(s);
    }
    
    public void loadImages() {
        if (this.mLinkButton.miMouseImage != null) {
            this.mLinkButton.mbImageReady = this.prepareImage(this.mLinkButton.miImage, this);
            while (!this.mLinkButton.mbImageReady) {
                try {
                    Thread.sleep(100L);
                }
                catch (Exception ex) {}
            }
        }
        if (this.miImage != null) {
            this.mbImageReady = this.prepareImage(this.miImage, this);
            while (!this.mbImageReady) {
                try {
                    Thread.sleep(100L);
                }
                catch (Exception ex2) {}
            }
        }
        if (this.mLinkButton.miMouseImage != null && this.mLinkButton.miMouseImage != this.mLinkButton.miImage) {
            this.mLinkButton.mbMouseImageReady = this.prepareImage(this.mLinkButton.miMouseImage, this);
            while (!this.mLinkButton.mbMouseImageReady) {
                try {
                    Thread.sleep(100L);
                }
                catch (Exception ex3) {}
            }
        }
        if (this.mLinkButton.miClickImage != null && this.mLinkButton.miClickImage != this.mLinkButton.miMouseImage) {
            this.mLinkButton.mbClickImageReady = this.prepareImage(this.mLinkButton.miClickImage, this);
            while (!this.mLinkButton.mbClickImageReady) {
                try {
                    Thread.sleep(100L);
                }
                catch (Exception ex4) {}
            }
        }
    }
    
    public void getParameters() {
        final String safeParm = this.getSafeParm("PRESET");
        if (safeParm.length() > 0) {
            this.mLinkButton.doPreset(safeParm);
        }
        final String safeParm2 = this.getSafeParm("BGCOLOR");
        if (safeParm2.length() > 0) {
            this.mBackgroundColor = this.getHTMLColor(safeParm2);
        }
        final String safeParm3 = this.getSafeParm("APPLETBORDERCOLOR");
        if (safeParm3.length() > 0) {
            this.mBorderColor = this.getHTMLColor(safeParm3);
        }
        final String safeParm4 = this.getSafeParm("BGIMAGE");
        if (safeParm4.length() > 0) {
            try {
                this.miImage = this.getImage(this.murlImage, safeParm4);
            }
            catch (Exception ex) {
                this.miImage = null;
            }
        }
        final String safeParm5 = this.getSafeParm("BUTTONX");
        if (safeParm5.length() > 0) {
            try {
                this.mnButtonX = new Integer(safeParm5);
            }
            catch (Exception ex2) {}
        }
        final String safeParm6 = this.getSafeParm("BUTTONY");
        if (safeParm6.length() > 0) {
            try {
                this.mnButtonY = new Integer(safeParm6);
            }
            catch (Exception ex3) {}
        }
        final String safeParm7 = this.getSafeParm("BUTTONSHADOW");
        if (safeParm7.length() > 0) {
            this.mButtonShadow = this.getHTMLColor(safeParm7);
            if (this.mButtonShadow != null) {
                this.mnButtonWidth -= 2;
                this.mnButtonHeight -= 2;
            }
        }
        final String safeParm8 = this.getSafeParm("BUTTONWIDTH");
        if (safeParm8.length() > 0) {
            try {
                this.mnButtonWidth = new Integer(safeParm8);
            }
            catch (Exception ex4) {}
        }
        final String safeParm9 = this.getSafeParm("BUTTONHEIGHT");
        if (safeParm9.length() > 0) {
            try {
                this.mnButtonHeight = new Integer(safeParm9);
            }
            catch (Exception ex5) {}
        }
        final String safeParm10 = this.getSafeParm("COLOR");
        if (safeParm10.length() > 0) {
            this.mLinkButton.mBackgroundColor = this.getHTMLColor(safeParm10);
        }
        final String safeParm11 = this.getSafeParm("BORDERCOLOR");
        if (safeParm11.length() > 0) {
            this.mLinkButton.mBorderColor = this.getHTMLColor(safeParm11);
        }
        final String safeParm12 = this.getSafeParm("TEXTCOLOR");
        if (safeParm12.length() > 0) {
            this.mLinkButton.mTextColor = this.getHTMLColor(safeParm12);
        }
        final String safeParm13 = this.getSafeParm("MOUSECOLOR");
        if (safeParm13.length() > 0) {
            this.mLinkButton.mMouseBackgroundColor = this.getHTMLColor(safeParm13);
        }
        else {
            this.mLinkButton.mMouseBackgroundColor = this.mLinkButton.mBackgroundColor;
        }
        final String safeParm14 = this.getSafeParm("MOUSEBORDERCOLOR");
        if (safeParm14.length() > 0) {
            this.mLinkButton.mMouseBorderColor = this.getHTMLColor(safeParm14);
        }
        else {
            this.mLinkButton.mMouseBorderColor = this.mLinkButton.mBorderColor;
        }
        final String safeParm15 = this.getSafeParm("MOUSETEXTCOLOR");
        if (safeParm15.length() > 0) {
            this.mLinkButton.mMouseTextColor = this.getHTMLColor(safeParm15);
        }
        else {
            this.mLinkButton.mMouseTextColor = this.mLinkButton.mTextColor;
        }
        final String safeParm16 = this.getSafeParm("CLICKCOLOR");
        if (safeParm16.length() > 0) {
            this.mLinkButton.mClickBackgroundColor = this.getHTMLColor(safeParm16);
        }
        else {
            this.mLinkButton.mClickBackgroundColor = this.mLinkButton.mMouseBackgroundColor;
        }
        final String safeParm17 = this.getSafeParm("CLICKBORDERCOLOR");
        if (safeParm17.length() > 0) {
            this.mLinkButton.mClickBorderColor = this.getHTMLColor(safeParm17);
        }
        else {
            this.mLinkButton.mClickBorderColor = this.mLinkButton.mMouseBorderColor;
        }
        final String safeParm18 = this.getSafeParm("CLICKTEXTCOLOR");
        if (safeParm18.length() > 0) {
            this.mLinkButton.mClickTextColor = this.getHTMLColor(safeParm18);
        }
        else {
            this.mLinkButton.mClickTextColor = this.mLinkButton.mMouseTextColor;
        }
        final String safeParm19 = this.getSafeParm("IMAGE");
        if (safeParm19.length() > 0) {
            try {
                this.mLinkButton.miImage = this.getImage(this.murlImage, safeParm19);
            }
            catch (Exception ex6) {
                this.mLinkButton.miImage = null;
            }
        }
        final String safeParm20 = this.getSafeParm("MOUSEIMAGE");
        if (safeParm20.length() > 0) {
            try {
                this.mLinkButton.miMouseImage = this.getImage(this.murlImage, safeParm20);
            }
            catch (Exception ex7) {
                this.mLinkButton.miMouseImage = null;
            }
        }
        else {
            this.mLinkButton.miMouseImage = this.mLinkButton.miImage;
        }
        final String safeParm21 = this.getSafeParm("CLICKIMAGE");
        if (safeParm21.length() > 0) {
            try {
                this.mLinkButton.miClickImage = this.getImage(this.murlImage, safeParm21);
            }
            catch (Exception ex8) {
                this.mLinkButton.miClickImage = null;
            }
        }
        else {
            this.mLinkButton.miClickImage = this.mLinkButton.miMouseImage;
        }
        final String safeParm22 = this.getSafeParm("BORDERWIDTH");
        if (safeParm22.length() > 0) {
            try {
                this.mLinkButton.mnBorderWidth = new Integer(safeParm22);
            }
            catch (Exception ex9) {}
        }
        final String safeParm23 = this.getSafeParm("TEXT");
        if (safeParm23.length() > 0) {
            this.mLinkButton.msText = safeParm23;
        }
        final String safeParm24 = this.getSafeParm("MOUSETEXT");
        if (safeParm24.length() > 0) {
            this.mLinkButton.msMouseText = safeParm24;
        }
        else {
            this.mLinkButton.msMouseText = this.mLinkButton.msText;
        }
        final String safeParm25 = this.getSafeParm("CLICKTEXT");
        if (safeParm25.length() > 0) {
            this.mLinkButton.msClickText = safeParm25;
        }
        else {
            this.mLinkButton.msClickText = this.mLinkButton.msMouseText;
        }
        final String safeParm26 = this.getSafeParm("TEXTVALIGN");
        if (safeParm26.length() > 0) {
            if (safeParm26.toUpperCase().charAt(0) == 'C') {
                this.mLinkButton.mnTextVAlign = 0;
            }
            if (safeParm26.toUpperCase().charAt(0) == 'T') {
                this.mLinkButton.mnTextVAlign = 1;
            }
            if (safeParm26.toUpperCase().charAt(0) == 'B') {
                this.mLinkButton.mnTextVAlign = 2;
            }
        }
        final String safeParm27 = this.getSafeParm("MOUSETEXTVALIGN");
        if (safeParm27.length() > 0) {
            if (safeParm27.toUpperCase().charAt(0) == 'C') {
                this.mLinkButton.mnMouseTextVAlign = 0;
            }
            if (safeParm27.toUpperCase().charAt(0) == 'T') {
                this.mLinkButton.mnMouseTextVAlign = 1;
            }
            if (safeParm27.toUpperCase().charAt(0) == 'B') {
                this.mLinkButton.mnMouseTextVAlign = 2;
            }
        }
        else {
            this.mLinkButton.mnMouseTextVAlign = this.mLinkButton.mnTextVAlign;
        }
        final String safeParm28 = this.getSafeParm("CLICKTEXTVALIGN");
        if (safeParm28.length() > 0) {
            if (safeParm28.toUpperCase().charAt(0) == 'C') {
                this.mLinkButton.mnClickTextVAlign = 0;
            }
            if (safeParm28.toUpperCase().charAt(0) == 'T') {
                this.mLinkButton.mnClickTextVAlign = 1;
            }
            if (safeParm28.toUpperCase().charAt(0) == 'B') {
                this.mLinkButton.mnClickTextVAlign = 2;
            }
        }
        else {
            this.mLinkButton.mnClickTextVAlign = this.mLinkButton.mnMouseTextVAlign;
        }
        final String safeParm29 = this.getSafeParm("FONT");
        if (safeParm29.length() > 0) {
            this.mLinkButton.msFont = safeParm29;
        }
        final String safeParm30 = this.getSafeParm("MOUSEFONT");
        if (safeParm30.length() > 0) {
            this.mLinkButton.msMouseFont = safeParm30;
        }
        else {
            this.mLinkButton.msMouseFont = this.mLinkButton.msFont;
        }
        final String safeParm31 = this.getSafeParm("CLICKFONT");
        if (safeParm31.length() > 0) {
            this.mLinkButton.msClickFont = safeParm31;
        }
        else {
            this.mLinkButton.msClickFont = this.mLinkButton.msMouseFont;
        }
        final String safeParm32 = this.getSafeParm("FONTSIZE");
        if (safeParm32.length() > 0) {
            try {
                this.mLinkButton.mnFontSize = new Integer(safeParm32);
            }
            catch (Exception ex10) {}
        }
        final String safeParm33 = this.getSafeParm("MOUSEFONTSIZE");
        if (safeParm33.length() > 0) {
            try {
                this.mLinkButton.mnMouseFontSize = new Integer(safeParm33);
            }
            catch (Exception ex11) {}
        }
        else {
            this.mLinkButton.mnMouseFontSize = this.mLinkButton.mnFontSize;
        }
        final String safeParm34 = this.getSafeParm("CLICKFONTSIZE");
        if (safeParm34.length() > 0) {
            try {
                this.mLinkButton.mnClickFontSize = new Integer(safeParm34);
            }
            catch (Exception ex12) {}
        }
        else {
            this.mLinkButton.mnClickFontSize = this.mLinkButton.mnMouseFontSize;
        }
        final String safeParm35 = this.getSafeParm("FONTSTYLE");
        if (safeParm35.length() > 0) {
            if (safeParm35.toUpperCase().indexOf("P") > -1) {
                this.mLinkButton.mnFontStyle = this.mLinkButton.mnFontStyle;
            }
            if (safeParm35.toUpperCase().indexOf("B") > -1) {
                this.mLinkButton.mnFontStyle |= 0x1;
            }
            if (safeParm35.toUpperCase().indexOf("I") > -1) {
                this.mLinkButton.mnFontStyle |= 0x2;
            }
            if (safeParm35.toUpperCase().indexOf("U") > -1) {
                this.mLinkButton.mbUnderline = true;
            }
        }
        final String safeParm36 = this.getSafeParm("MOUSEFONTSTYLE");
        if (safeParm36.length() > 0) {
            if (safeParm36.toUpperCase().indexOf("P") > -1) {
                this.mLinkButton.mnMouseFontStyle = this.mLinkButton.mnMouseFontStyle;
            }
            if (safeParm36.toUpperCase().indexOf("B") > -1) {
                this.mLinkButton.mnMouseFontStyle |= 0x1;
            }
            if (safeParm36.toUpperCase().indexOf("I") > -1) {
                this.mLinkButton.mnMouseFontStyle |= 0x2;
            }
            if (safeParm36.toUpperCase().indexOf("U") > -1) {
                this.mLinkButton.mbMouseUnderline = true;
            }
        }
        else {
            this.mLinkButton.mnMouseFontStyle = this.mLinkButton.mnFontStyle;
            this.mLinkButton.mbMouseUnderline = this.mLinkButton.mbUnderline;
        }
        final String safeParm37 = this.getSafeParm("CLICKFONTSTYLE");
        if (safeParm37.length() > 0) {
            if (safeParm37.toUpperCase().indexOf("P") > -1) {
                this.mLinkButton.mnClickFontStyle = this.mLinkButton.mnClickFontStyle;
            }
            if (safeParm37.toUpperCase().indexOf("B") > -1) {
                this.mLinkButton.mnClickFontStyle |= 0x1;
            }
            if (safeParm37.toUpperCase().indexOf("I") > -1) {
                this.mLinkButton.mnClickFontStyle |= 0x2;
            }
            if (safeParm37.toUpperCase().indexOf("U") > -1) {
                this.mLinkButton.mbClickUnderline = true;
            }
        }
        else {
            this.mLinkButton.mnClickFontStyle = this.mLinkButton.mnMouseFontStyle;
            this.mLinkButton.mbClickUnderline = this.mLinkButton.mbMouseUnderline;
        }
        final String safeParm38 = this.getSafeParm("LINK");
        if (safeParm38.length() > 0) {
            this.msLink = this.getSafeHREF(safeParm38);
        }
        else {
            this.msLink = "";
        }
        final String safeParm39 = this.getSafeParm("HREF");
        if (safeParm39.length() > 0) {
            this.msLink = this.getSafeHREF(safeParm39);
        }
        else {
            this.msLink = "";
        }
        final String safeParm40 = this.getSafeParm("CAPTION");
        if (safeParm40.length() > 0) {
            this.msCaption = safeParm40;
        }
        else {
            this.msCaption = "";
        }
        final String safeParm41 = this.getSafeParm("MOUSECAPTION");
        if (safeParm41.length() > 0) {
            this.msMouseCaption = safeParm41;
        }
        else if (this.msCaption.length() > 0) {
            this.msMouseCaption = this.msCaption;
        }
        else {
            this.msMouseCaption = this.mLinkButton.msText;
        }
        final String safeParm42 = this.getSafeParm("CLICKCAPTION");
        if (safeParm42.length() > 0) {
            this.msClickCaption = safeParm42;
        }
        else {
            this.msClickCaption = this.msMouseCaption;
        }
        final String safeParm43 = this.getSafeParm("BUTTONSTYLE");
        if (safeParm43.length() > 0) {
            if (safeParm43.toUpperCase().charAt(0) == 'N') {
                this.mLinkButton.mnStyle = 0;
            }
            if (safeParm43.toUpperCase().charAt(0) == 'R') {
                this.mLinkButton.mnStyle = 1;
            }
            if (safeParm43.toUpperCase().charAt(0) == 'M') {
                this.mLinkButton.mnStyle = 2;
            }
        }
        final String safeParm44 = this.getSafeParm("TEXTSHADOW");
        if (safeParm44.length() > 0) {
            this.mLinkButton.mTextShadowColor = this.getHTMLColor(safeParm44);
        }
        final String safeParm45 = this.getSafeParm("MOUSETEXTSHADOW");
        if (safeParm45.length() > 0) {
            this.mLinkButton.mMouseTextShadowColor = this.getHTMLColor(safeParm45);
        }
        else {
            this.mLinkButton.mMouseTextShadowColor = this.mLinkButton.mTextShadowColor;
        }
        final String safeParm46 = this.getSafeParm("CLICKTEXTSHADOW");
        if (safeParm46.length() > 0) {
            this.mLinkButton.mClickTextShadowColor = this.getHTMLColor(safeParm46);
            return;
        }
        this.mLinkButton.mClickTextShadowColor = this.mLinkButton.mMouseTextShadowColor;
    }
    
    public String getSafeParm(final String s) {
        String s2 = "";
        String parameter = "";
        int n = 1;
        try {
            parameter = this.getParameter(s);
        }
        catch (Exception ex) {
            n = 0;
        }
        try {
            if (parameter.length() > 0) {
                n = 1;
            }
        }
        catch (Exception ex2) {
            n = 0;
        }
        if (n == 1) {
            s2 = parameter;
        }
        return s2;
    }
    
    public String getSafeHREF(String s) {
        final String trim = s.trim();
        s = s.trim();
        if (trim.toLowerCase().indexOf("http://") == 0) {
            return s;
        }
        if (s.indexOf("/") == 0) {
            s = s.substring(1);
        }
        s = this.getCodeBase().toString() + s;
        return s;
    }
    
    public Color getHTMLColor(String trim) {
        trim = trim.toUpperCase().trim();
        if (trim.compareTo("WHITE") == 0) {
            return Color.white;
        }
        if (trim.compareTo("BLACK") == 0) {
            return Color.black;
        }
        if (trim.compareTo("GRAY") == 0) {
            return Color.gray;
        }
        if (trim.compareTo("LIGHTGRAY") == 0) {
            return Color.lightGray;
        }
        if (trim.compareTo("DARKGRAY") == 0) {
            return Color.darkGray;
        }
        if (trim.compareTo("RED") == 0) {
            return Color.red;
        }
        if (trim.compareTo("GREEN") == 0) {
            return Color.green;
        }
        if (trim.compareTo("BLUE") == 0) {
            return Color.blue;
        }
        if (trim.compareTo("PINK") == 0) {
            return Color.pink;
        }
        if (trim.compareTo("ORANGE") == 0) {
            return Color.orange;
        }
        if (trim.compareTo("YELLOW") == 0) {
            return Color.yellow;
        }
        if (trim.compareTo("MAGENTA") == 0) {
            return Color.magenta;
        }
        if (trim.compareTo("CYAN") == 0) {
            return Color.cyan;
        }
        if (trim.charAt(0) == '#') {
            return new Color(this.hexToInt("" + trim.substring(trim.indexOf("#") + 1)));
        }
        return Color.white;
    }
    
    public int hexToInt(String upperCase) {
        int intValue = 0;
        int n = 1;
        int n2 = 0;
        upperCase = upperCase.toUpperCase();
        for (int i = upperCase.length() - 1; i > -1; --i) {
            final String substring = upperCase.substring(i, i + 1);
            try {
                intValue = new Integer(substring);
            }
            catch (Exception ex) {
                if (substring.charAt(0) == 'A') {
                    intValue = 10;
                }
                if (substring.charAt(0) == 'B') {
                    intValue = 11;
                }
                if (substring.charAt(0) == 'C') {
                    intValue = 12;
                }
                if (substring.charAt(0) == 'D') {
                    intValue = 13;
                }
                if (substring.charAt(0) == 'E') {
                    intValue = 14;
                }
                if (substring.charAt(0) == 'F') {
                    intValue = 15;
                }
            }
            n2 += intValue * n;
            n *= 16;
        }
        return n2;
    }
    
    public squarelink() {
        this.mLinkButton = new squarehelper(this);
        this.mbImageReady = true;
        this.mBackgroundColor = Color.white;
        this.mBorderColor = Color.white;
        this.mbMouseOver = false;
        this.mbMouseDown = false;
        this.mbMouseJustPressed = false;
        this.mbMouseDragOut = false;
    }
}
