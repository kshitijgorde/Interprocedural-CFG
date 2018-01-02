import java.awt.Component;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class squarehelper extends Canvas
{
    public static final int RECTANGLE = 0;
    public static final int NORMAL = 0;
    public static final int FLAT = 0;
    public static final int RAISED = 1;
    public static final int MODERN = 2;
    public static final int VALIGN_CENTER = 0;
    public static final int VALIGN_TOP = 1;
    public static final int VALIGN_BOTTOM = 2;
    public static final String sIMAGEBAR = "IMAGEBAR";
    public static final String sIMAGEBUTTON = "IMAGEBUTTON";
    public static final String sTOOLBAR = "TOOLBAR";
    public static final String sPUSHBUTTON = "PUSHBUTTON";
    public squarelink mApplet;
    public int mnShape;
    public int mnStyle;
    public Color mBorderColor;
    public Color mBackgroundColor;
    public Color mTextColor;
    public Color mMouseBorderColor;
    public Color mMouseBackgroundColor;
    public Color mMouseTextColor;
    public Color mClickBorderColor;
    public Color mClickBackgroundColor;
    public Color mClickTextColor;
    public Color mTextShadowColor;
    public Color mMouseTextShadowColor;
    public Color mClickTextShadowColor;
    public int mnBorderWidth;
    public String msFont;
    public String msMouseFont;
    public String msClickFont;
    public int mnFontSize;
    public int mnMouseFontSize;
    public int mnClickFontSize;
    public String msText;
    public String msMouseText;
    public String msClickText;
    public int mnFontStyle;
    public int mnMouseFontStyle;
    public int mnClickFontStyle;
    public boolean mbUnderline;
    public boolean mbMouseUnderline;
    public boolean mbClickUnderline;
    public int mnTextVAlign;
    public int mnMouseTextVAlign;
    public int mnClickTextVAlign;
    public Image miImage;
    public boolean mbImageReady;
    public Image miMouseImage;
    public boolean mbMouseImageReady;
    public Image miClickImage;
    public boolean mbClickImageReady;
    public String msPreset;
    
    public squarehelper(final squarelink mApplet) {
        this.mBorderColor = Color.black;
        this.mBackgroundColor = Color.white;
        this.mTextColor = Color.blue;
        this.mMouseBorderColor = Color.black;
        this.mMouseBackgroundColor = Color.white;
        this.mMouseTextColor = Color.blue;
        this.mClickBorderColor = Color.black;
        this.mClickBackgroundColor = Color.white;
        this.mClickTextColor = Color.blue;
        this.mnBorderWidth = 1;
        this.msFont = "";
        this.msMouseFont = "";
        this.msClickFont = "";
        this.msText = "";
        this.msMouseText = "";
        this.msClickText = "";
        this.mbUnderline = false;
        this.mbMouseUnderline = false;
        this.mbClickUnderline = false;
        this.mbImageReady = true;
        this.mbMouseImageReady = true;
        this.mbClickImageReady = true;
        this.msPreset = "";
        this.mApplet = mApplet;
    }
    
    public void paint(final Graphics graphics) {
        this.bounds();
        switch (this.mnShape) {
            case 0: {
                this.drawRectangle(graphics);
            }
            default: {}
        }
    }
    
    public void drawRectangle(final Graphics graphics) {
        final Rectangle bounds = this.bounds();
        Color color = this.mBackgroundColor;
        Color color2 = this.mBorderColor;
        Color color3 = this.mTextColor;
        if (this.mApplet.mbMouseOver && this.mApplet.mbMouseDown) {
            color = this.mClickBackgroundColor;
            color2 = this.mClickBorderColor;
            color3 = this.mClickTextColor;
        }
        if (this.mApplet.mbMouseOver && !this.mApplet.mbMouseDown) {
            color = this.mMouseBackgroundColor;
            color2 = this.mMouseBorderColor;
            color3 = this.mMouseTextColor;
        }
        final int n = 0;
        final int n2 = 0;
        final int width = bounds.width;
        final int height = bounds.height;
        graphics.setColor(color);
        graphics.fillRect(n, n2, width, height);
        if (this.msPreset.toUpperCase().compareTo("IMAGEBUTTON") != 0 && this.msPreset.toUpperCase().compareTo("IMAGEBAR") != 0 && (this.miImage != null || this.miMouseImage != null || this.miClickImage != null)) {
            this.drawImage(graphics, n + this.mnBorderWidth, n2 + this.mnBorderWidth, width - this.mnBorderWidth * 2 + 1, height - this.mnBorderWidth * 2 + 1);
        }
        graphics.setColor(color3);
        this.drawText(graphics, n + this.mnBorderWidth, n2 + this.mnBorderWidth, width - this.mnBorderWidth * 2 + 1, height - this.mnBorderWidth * 2 + 1);
        graphics.setColor(color2);
        this.drawBorder(graphics, n, n2, width, height);
    }
    
    public void drawImage(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.mApplet.mbMouseOver && this.mApplet.mbMouseDown && this.miClickImage != null && this.mbClickImageReady) {
            graphics.drawImage(this.miClickImage, n, n2, n3, n4, this.mClickBackgroundColor, (ImageObserver)this.mApplet);
            return;
        }
        if (this.mApplet.mbMouseOver && !this.mApplet.mbMouseDown && this.miMouseImage != null && this.mbMouseImageReady) {
            graphics.drawImage(this.miMouseImage, n, n2, n3, n4, this.mMouseBackgroundColor, (ImageObserver)this.mApplet);
            return;
        }
        if (!this.mApplet.mbMouseOver && this.miImage != null && this.mbImageReady) {
            graphics.drawImage(this.miImage, n, n2, n3, n4, this.mBackgroundColor, (ImageObserver)this.mApplet);
        }
    }
    
    public void drawBorder(final Graphics graphics, int n, int n2, int n3, int n4) {
        final Color color = graphics.getColor();
        switch (this.mnStyle) {
            case 1: {
                if (!this.mApplet.mbMouseDown) {
                    graphics.setColor(Color.white);
                    graphics.drawLine(n, n2, n3, n2);
                    graphics.drawLine(n, n2, n, n4);
                    graphics.setColor(Color.black);
                    graphics.drawLine(n, n4 - 1, n3, n4 - 1);
                    graphics.drawLine(n3 - 1, n2, n3 - 1, n4);
                }
                else {
                    graphics.setColor(Color.black);
                    graphics.drawLine(n, n2, n3, n2);
                    graphics.drawLine(n, n2, n, n4);
                    graphics.setColor(Color.white);
                    graphics.drawLine(n, n4 - 1, n3, n4 - 1);
                    graphics.drawLine(n3 - 1, n2, n3 - 1, n4);
                }
                ++n;
                ++n2;
                n3 -= 2;
                n4 -= 2;
                graphics.setColor(color);
            }
            case 0:
            case 2: {
                for (int i = 0; i < this.mnBorderWidth; ++i) {
                    graphics.drawRect(n + i, n2 + i, n3 - i * 2 - 1, n4 - i * 2 - 1);
                }
                if (this.mnStyle != 2) {
                    break;
                }
                if (this.mApplet.mbMouseOver && !this.mApplet.mbMouseDown) {
                    graphics.setColor(Color.white);
                    graphics.drawLine(n, n2, n3, n2);
                    graphics.drawLine(n, n2, n, n4);
                    graphics.setColor(Color.black);
                    graphics.drawLine(n, n4 - 1, n3, n4 - 1);
                    graphics.drawLine(n3 - 1, n2, n3 - 1, n4);
                    ++n;
                    ++n2;
                    n3 -= 2;
                    n4 -= 2;
                    graphics.setColor(color);
                }
                if (this.mApplet.mbMouseDown) {
                    graphics.setColor(Color.black);
                    graphics.drawLine(n, n2, n3, n2);
                    graphics.drawLine(n, n2, n, n4);
                    graphics.setColor(Color.white);
                    graphics.drawLine(n, n4 - 1, n3, n4 - 1);
                    graphics.drawLine(n3 - 1, n2, n3 - 1, n4);
                    graphics.setColor(color);
                    return;
                }
                break;
            }
        }
    }
    
    public void drawText(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        String s = this.msText;
        String s2 = this.msFont;
        int n6 = this.mnTextVAlign;
        int n7 = this.mnFontSize;
        int n8 = this.mnFontStyle;
        int n9 = 0;
        int n10 = 0;
        boolean b = this.mbUnderline;
        int n11 = n3 / 30;
        final int n12 = n4 / 30;
        boolean b2 = false;
        Color color = this.mTextShadowColor;
        final Color color2 = graphics.getColor();
        if (this.mApplet.mbMouseOver && this.mApplet.mbMouseDown) {
            s = this.msClickText;
            s2 = this.msClickFont;
            n7 = this.mnClickFontSize;
            n8 = this.mnClickFontStyle;
            n6 = this.mnClickTextVAlign;
            color = this.mClickTextShadowColor;
            b = this.mbClickUnderline;
        }
        if (this.mApplet.mbMouseOver && !this.mApplet.mbMouseDown) {
            s = this.msMouseText;
            s2 = this.msMouseFont;
            n7 = this.mnMouseFontSize;
            n8 = this.mnMouseFontStyle;
            n6 = this.mnMouseTextVAlign;
            color = this.mMouseTextShadowColor;
            b = this.mbMouseUnderline;
        }
        int n13 = n7 / 10;
        if ((n8 & 0x1) == 0x1) {
            ++n13;
        }
        if (b && n13 <= 0) {
            n13 = 1;
        }
        String s3 = s;
        graphics.setFont(new Font(s2, n8, n7));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n14 = (n4 - n2) / fontMetrics.getHeight();
        if (n14 < 1) {
            n14 = 1;
        }
        final String[] array = new String[n14];
        while (!b2) {
            int stringWidth = fontMetrics.stringWidth(s3);
            if (color != null) {
                stringWidth += 2;
            }
            if (stringWidth < n3 - 1) {
                array[n9] = s3;
                if (++n9 > n14 - 1) {
                    b2 = true;
                }
                n10 += s3.length() + 1;
                if (n10 > s.length()) {
                    b2 = true;
                }
                else {
                    s3 = s.substring(n10);
                }
            }
            else {
                final int lastIndex = s3.lastIndexOf(" ");
                if (lastIndex > 0) {
                    s3 = s3.substring(0, lastIndex);
                }
                else {
                    array[n9] = s3;
                    ++n9;
                    b2 = true;
                }
            }
        }
        int height = fontMetrics.getHeight();
        if (color != null) {
            height += 2;
        }
        final int n15 = height * (n9 - 1);
        switch (n6) {
            case 0: {
                n5 = n4 / 2 + fontMetrics.getAscent() / 2 - fontMetrics.getDescent() / 2 + n2 - fontMetrics.getHeight() * (n9 - 1) / 2;
                break;
            }
            case 1: {
                n5 = n2 + fontMetrics.getAscent() + n12 - n15;
                break;
            }
            case 2: {
                n5 = n2 + n4 - fontMetrics.getDescent() - n12 - n15;
                break;
            }
        }
        for (int i = 0; i < n9; ++i) {
            int round = Math.round(n3 / 2.0f - fontMetrics.stringWidth(array[i]) / 2.0f + n);
            --round;
            if (color != null) {
                graphics.setColor(color);
                graphics.drawString(array[i], round + 1, n5 + 1);
                if (b) {
                    graphics.fillRect(round + 1, n5 + 2, fontMetrics.stringWidth(array[i]), n13);
                }
                graphics.setColor(color2);
                graphics.drawString(array[i], round - 1, n5 - 1);
                if (b) {
                    graphics.fillRect(round, n5, fontMetrics.stringWidth(array[i]), n13);
                }
            }
            else {
                graphics.drawString(array[i], round, n5);
                if (b) {
                    graphics.fillRect(round, n5 + 1, fontMetrics.stringWidth(array[i]), n13);
                }
            }
            n5 += height;
        }
        if ((this.msPreset.toUpperCase().compareTo("IMAGEBUTTON") == 0 || this.msPreset.toUpperCase().compareTo("IMAGEBAR") == 0) && (this.miImage != null || this.miMouseImage != null || this.miClickImage != null)) {
            int n16 = n4 - n12 * 2;
            if (s.length() > 0) {
                n16 -= height * n9 + n12;
                n11 = n3 / 2 - n16 / 2;
            }
            this.drawImage(graphics, n + n11, n2 + n12, n3 - n11 * 2, n16 + 1);
        }
    }
    
    public void doPreset(final String s) {
        this.msPreset = s.toUpperCase();
        if (this.msPreset.compareTo("TOOLBAR") == 0 || this.msPreset.compareTo("IMAGEBAR") == 0) {
            this.mApplet.mBackgroundColor = Color.lightGray;
            this.mApplet.mBorderColor = Color.lightGray;
            this.mApplet.mnButtonX = 1;
            this.mApplet.mnButtonY = 1;
            this.mApplet.mnButtonWidth -= 2;
            this.mApplet.mnButtonHeight -= 2;
            this.mBackgroundColor = Color.lightGray;
            this.mTextColor = Color.black;
            this.mBorderColor = Color.lightGray;
            this.mMouseBackgroundColor = Color.lightGray;
            this.mMouseTextColor = Color.black;
            this.mMouseBorderColor = Color.lightGray;
            this.mClickBackgroundColor = Color.lightGray;
            this.mClickTextColor = Color.lightGray;
            this.mClickBorderColor = Color.lightGray;
            this.mnFontSize = 10;
            this.mnMouseFontSize = 10;
            this.mnClickFontSize = 10;
            this.msFont = "Dialog";
            this.msMouseFont = "Dialog";
            this.msClickFont = "Dialog";
            this.mnStyle = 2;
            if (this.msPreset.compareTo("IMAGEBAR") == 0) {
                this.mnTextVAlign = 2;
                this.mnMouseTextVAlign = 2;
                this.mnClickTextVAlign = 2;
            }
            return;
        }
        if (this.msPreset.compareTo("PUSHBUTTON") == 0 || this.msPreset.compareTo("IMAGEBUTTON") == 0) {
            this.mApplet.mBackgroundColor = Color.lightGray;
            this.mApplet.mBorderColor = Color.lightGray;
            this.mBackgroundColor = Color.lightGray;
            this.mTextColor = Color.black;
            this.mBorderColor = Color.lightGray;
            this.mMouseBackgroundColor = Color.lightGray;
            this.mMouseTextColor = Color.black;
            this.mMouseBorderColor = Color.lightGray;
            this.mClickBackgroundColor = Color.lightGray;
            this.mClickTextColor = Color.lightGray;
            this.mClickBorderColor = Color.lightGray;
            this.mnFontSize = 10;
            this.mnMouseFontSize = 10;
            this.mnClickFontSize = 10;
            this.msFont = "Dialog";
            this.msMouseFont = "Dialog";
            this.msClickFont = "Dialog";
            this.mnStyle = 1;
            if (this.msPreset.compareTo("IMAGEBUTTON") == 0) {
                this.mnTextVAlign = 2;
                this.mnMouseTextVAlign = 2;
                this.mnClickTextVAlign = 2;
            }
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.mApplet.mbMouseOver) {
            return true;
        }
        this.mApplet.mbMouseOver = true;
        this.paint(this.getGraphics());
        this.mApplet.showStatus(this.mApplet.msMouseCaption);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.mApplet.mbMouseJustPressed) {
            this.mApplet.mbMouseJustPressed = false;
            return super.mouseExit(event, n, n2);
        }
        if (!this.mApplet.mbMouseDown) {
            this.mApplet.mbMouseOver = false;
            this.paint(this.getGraphics());
            return super.mouseExit(event, n, n2);
        }
        if (this.mApplet.mbMouseDown) {
            this.mApplet.mbMouseDown = false;
            this.mApplet.mbMouseOver = false;
            this.mApplet.mbMouseDragOut = true;
            this.mApplet.paint(((Component)this.mApplet).getGraphics());
        }
        return super.mouseExit(event, n, n2);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.mApplet.mbMouseDown = true;
        this.mApplet.mbMouseJustPressed = true;
        this.mApplet.paint(((Component)this.mApplet).getGraphics());
        this.mApplet.showStatus(this.mApplet.msClickCaption);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.mApplet.mbMouseDown && this.mApplet.mbMouseOver) {
            this.mApplet.mbMouseDown = false;
            this.mApplet.paint(((Component)this.mApplet).getGraphics());
            this.mApplet.fireLink();
        }
        return true;
    }
    
    public void checkProperties() {
        if (this.mMouseBackgroundColor == null) {
            this.mMouseBackgroundColor = this.mBackgroundColor;
        }
        if (this.mMouseBorderColor == null) {
            this.mMouseBorderColor = this.mBorderColor;
        }
        if (this.mMouseTextColor == null) {
            this.mMouseTextColor = this.mTextColor;
        }
        if (this.msMouseText == "") {
            this.msMouseText = this.msText;
        }
        if (this.msMouseFont == "") {
            this.msMouseFont = this.msFont;
        }
        if (this.mnMouseFontSize == 0) {
            this.mnMouseFontSize = this.mnFontSize;
        }
        if (this.miMouseImage == null) {
            this.miMouseImage = this.miImage;
        }
        if (this.mClickBackgroundColor == null) {
            this.mClickBackgroundColor = this.mBackgroundColor;
        }
        if (this.mClickBorderColor == null) {
            this.mClickBorderColor = this.mBorderColor;
        }
        if (this.mClickTextColor == null) {
            this.mClickTextColor = this.mTextColor;
        }
        if (this.msClickText == "") {
            this.msClickText = this.msMouseText;
        }
        if (this.msClickFont == "") {
            this.msClickFont = this.msMouseFont;
        }
        if (this.mnClickFontSize == 0) {
            this.mnClickFontSize = this.mnMouseFontSize;
        }
        if (this.miClickImage == null) {
            this.miClickImage = this.miMouseImage;
        }
    }
}
