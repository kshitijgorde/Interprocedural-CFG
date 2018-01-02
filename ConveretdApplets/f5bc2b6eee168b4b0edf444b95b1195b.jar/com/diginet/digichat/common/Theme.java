// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import com.esial.util.LanguageSupport;
import com.diginet.digichat.awt.ColorChoice;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import com.diginet.digichat.network.t;

public class Theme extends DigiItem
{
    public static final String BackgroundName = "background.gif";
    public static final String ChatBackgroundName = "chatbackground.gif";
    public static Theme defaultTheme;
    public static t defaultMessage;
    public Color outerBackground;
    public Color innerBackground;
    public Color helpText;
    public Color helpBackground;
    public Color inputText;
    public Color inputBackground;
    public Color tabsText;
    public Color tabsBackground;
    public Color normalMessages;
    public Color flaggedMessages;
    public Color normalBackground;
    public Color privateMessages;
    public Color privateBackground;
    public String fontName;
    public int fontSize;
    public int fontStyle;
    private Font a;
    private Font b;
    private Font c;
    public int roundedCorners;
    private String d;
    public Image helpLogo;
    public Image helpLogoIM;
    public Image closeHelp;
    public Image chatLogo;
    public Image IMLogo;
    public Image avEnterpriseIM;
    public Image fileTransferLogo;
    public Image available;
    public Image unavailable;
    public Image backgroundImage;
    public Image chatBackgroundImage;
    public Image pmLogo;
    public Image[] borderImgs;
    
    public final Font getBoldFont() {
        if (this.b == null) {
            this.b = new Font(this.fontName, this.fontStyle | 0x1, this.fontSize);
        }
        return this.b;
    }
    
    public final Font getFont() {
        if (this.a == null) {
            this.a = new Font(this.fontName, this.fontStyle, this.fontSize);
        }
        return this.a;
    }
    
    public final Font getDateFont() {
        if (this.c == null || this.c.getSize() + 4 != this.a.getSize()) {
            this.c = new Font(this.fontName, this.fontStyle, this.fontSize - 4);
        }
        return this.c;
    }
    
    public final void resetFont() {
        this.a = null;
        this.b = null;
    }
    
    public final void setDirectory(final String d) {
        if (d == null) {
            return;
        }
        this.d = d;
    }
    
    public final String getDirectory() {
        return this.d.toLowerCase();
    }
    
    public final String getFullDirectory() {
        return "Themes/" + this.getDirectory() + "/";
    }
    
    public final Image getBackgroundImage() {
        return this.getBackground() ? this.backgroundImage : null;
    }
    
    public final Image getChatBackgroundImage() {
        return this.getChatBackground() ? this.chatBackgroundImage : null;
    }
    
    public final Color getOuterBackground() {
        return this.outerBackground;
    }
    
    public final Image[] getBorderImgs() {
        return this.borderImgs;
    }
    
    public final boolean getBackground() {
        return this.u(60);
    }
    
    public final void setBackground(final boolean b) {
        this.a(60, b);
    }
    
    public final boolean getChatBackground() {
        return this.u(59);
    }
    
    public final void setChatBackground(final boolean b) {
        this.a(59, b);
    }
    
    public final boolean getScaleChatBackground() {
        return this.u(58);
    }
    
    public final void setScaleChatBackground(final boolean b) {
        this.a(58, b);
    }
    
    public final boolean getImageButtons() {
        return this.u(57);
    }
    
    public final void setImageButtons(final boolean b) {
        this.a(57, b);
    }
    
    public final boolean getImageTabs() {
        return this.u(56);
    }
    
    public final void setImageTabs(final boolean b) {
        this.a(56, b);
    }
    
    public final boolean getPMLogoEnabled() {
        return this.u(55);
    }
    
    public final void setPMLogoEnabled(final boolean b) {
        this.a(55, b);
    }
    
    public final String toString() {
        return "outerBackground = " + this.outerBackground + "\n" + "innerBackground = " + this.innerBackground + "\n" + "helpText = " + this.helpText + "\n" + "helpBackground = " + this.helpBackground + "\n" + "tabsText = " + this.tabsText + "\n" + "tabsBackground = " + this.tabsBackground + "\n" + "normalMessages = " + this.normalMessages + "\n" + "flaggedMessages = " + this.flaggedMessages + "\n" + "normalBackground = " + this.normalBackground + "\n" + "privateMessages = " + this.privateMessages + "\n" + "privateBackground = " + this.privateBackground + "\n" + "fontName = " + this.fontName + "\n" + "fontSize = " + this.fontSize + "\n" + "fontStyle = " + this.fontStyle + "\n" + "directory = " + this.d + "\n" + "roundedCorners = " + this.roundedCorners + "\n" + "background = " + this.getBackground() + "\n" + "chatBackground = " + this.getChatBackground() + "\n" + "scaleChatBackground = " + this.getScaleChatBackground() + "\n" + "imageButtons = " + this.getImageButtons() + "\n" + "imageTabs = " + this.getImageTabs() + "\n" + "pmLogoEnabled = " + this.getPMLogoEnabled() + "\n";
    }
    
    public Theme(final int n, final String s) {
        super(n, s);
        this.outerBackground = ColorChoice.platinum;
        this.innerBackground = ColorChoice.platinum;
        this.helpText = Color.black;
        this.helpBackground = ColorChoice.platinum;
        this.inputText = Color.black;
        this.inputBackground = Color.white;
        this.tabsText = Color.black;
        this.tabsBackground = ColorChoice.platinum;
        this.normalMessages = Color.black;
        this.flaggedMessages = Color.red;
        this.normalBackground = ColorChoice.platinum;
        this.privateMessages = Color.blue;
        this.privateBackground = ColorChoice.lemonYellow;
        this.fontName = "SansSerif";
        this.fontSize = 12;
        this.fontStyle = 0;
        this.a = null;
        this.b = null;
        this.c = null;
        this.roundedCorners = 0;
        this.d = "Default";
        this.borderImgs = null;
    }
    
    public Theme(final t t, final int n) {
        super(t.a(n, 0), t.c(n, 0));
        this.outerBackground = ColorChoice.platinum;
        this.innerBackground = ColorChoice.platinum;
        this.helpText = Color.black;
        this.helpBackground = ColorChoice.platinum;
        this.inputText = Color.black;
        this.inputBackground = Color.white;
        this.tabsText = Color.black;
        this.tabsBackground = ColorChoice.platinum;
        this.normalMessages = Color.black;
        this.flaggedMessages = Color.red;
        this.normalBackground = ColorChoice.platinum;
        this.privateMessages = Color.blue;
        this.privateBackground = ColorChoice.lemonYellow;
        this.fontName = "SansSerif";
        this.fontSize = 12;
        this.fontStyle = 0;
        this.a = null;
        this.b = null;
        this.c = null;
        this.roundedCorners = 0;
        this.d = "Default";
        this.borderImgs = null;
    }
    
    static {
        Theme.defaultTheme = new Theme(0, "");
        (Theme.defaultMessage = new t(67341, 1)).f(0, 62);
        Theme.defaultMessage.a(0, 0, 1);
        Theme.defaultMessage.a(0, 1, ColorChoice.platinum.getRGB());
        Theme.defaultMessage.a(0, 2, ColorChoice.platinum.getRGB());
        Theme.defaultMessage.a(0, 3, Color.black.getRGB());
        Theme.defaultMessage.a(0, 4, ColorChoice.platinum.getRGB());
        Theme.defaultMessage.a(0, 5, Color.black.getRGB());
        Theme.defaultMessage.a(0, 6, ColorChoice.platinum.getRGB());
        Theme.defaultMessage.a(0, 7, Color.black.getRGB());
        Theme.defaultMessage.a(0, 8, Color.red.getRGB());
        Theme.defaultMessage.a(0, 9, ColorChoice.platinum.getRGB());
        Theme.defaultMessage.a(0, 10, Color.blue.getRGB());
        Theme.defaultMessage.a(0, 11, ColorChoice.lemonYellow.getRGB());
        Theme.defaultMessage.a(0, 12, 0);
        Theme.defaultMessage.a(0, 13, 12);
        Theme.defaultMessage.a(0, 15, Color.black.getRGB());
        Theme.defaultMessage.a(0, 16, Color.white.getRGB());
        Theme.defaultMessage.a(0, 0, LanguageSupport.translate("Default"));
        Theme.defaultMessage.a(0, 1, "SansSerif");
        Theme.defaultMessage.a(0, 2, "Default");
        Theme.defaultMessage.a(0, 14, 0);
    }
}
