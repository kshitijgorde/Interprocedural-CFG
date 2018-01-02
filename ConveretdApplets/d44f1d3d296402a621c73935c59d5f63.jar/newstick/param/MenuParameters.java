// 
// Decompiled by Procyon v0.5.30
// 

package newstick.param;

import java.applet.Applet;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;

public class MenuParameters extends AppletParameters
{
    private static final String menuShowPARAM = "menushow";
    private static final boolean menuShowDEFAULT = true;
    private static final String menuDisplayPARAM = "menudisplay";
    private static final String menuDisplayDEFAULT = "Menu";
    private static final String menuItemsUrlPARAM = "menuitemsurl";
    private static final String menuItemsUrlDEFAULT = "http://www.wldcup.com/newsexp/menu";
    private static final String menuFontPARAM = "menufont";
    private static final Font menuFontDEFAULT;
    private static final String menuAlignmentPARAM = "menualignment";
    private static final String menuAlignmentDEFAULT = "right";
    private static final String menuBackColorPARAM = "menubackcolor";
    private static final Color menuBackColorDEFAULT;
    private static final String menuForeColorPARAM = "menuforecolor";
    private static final Color menuForeColorDEFAULT;
    private static final String menuOutlineColorPARAM = "menuoutlinecolor";
    private static final Color menuOutlineColorDEFAULT;
    private static final String menuHighliteBackColorPARAM = "menuhighlitebackcolor";
    private static final Color menuHighliteBackColorDEFAULT;
    private static final String menuHighliteForeColorPARAM = "menuhighliteforecolor";
    private static final Color menuHighliteForeColorDEFAULT;
    private static final String menuHorizMarginPARAM = "menuhorizmargin";
    private static final int menuHorizMarginDEFAULT = 4;
    private static final String menuVertMarginPARAM = "menuvertmargin";
    private static final int menuVertMarginDEFAULT = 4;
    public boolean menuShow;
    public String menuDisplay;
    public URL menuItemsUrl;
    public float menuAlignment;
    public Font menuFont;
    public Color menuBackColor;
    public Color menuForeColor;
    public Color menuOutlineColor;
    public Color menuHighliteBackColor;
    public Color menuHighliteForeColor;
    public int menuHorizMargin;
    public int menuVertMargin;
    
    public MenuParameters(final Applet applet) {
        super(applet);
        this.menuShow = this.paramToBoolean("menushow", true);
        this.menuDisplay = this.paramToString("menudisplay", "Menu");
        this.menuItemsUrl = this.paramToURL("menuitemsurl", "http://www.wldcup.com/newsexp/menu");
        if (this.paramToString("menualignment", "right").toLowerCase().compareTo("right") == 0) {
            this.menuAlignment = 1.0f;
        }
        else {
            this.menuAlignment = 0.0f;
        }
        this.menuFont = this.paramToFont("menufont", (MenuParameters.menuFontDEFAULT != null) ? MenuParameters.menuFontDEFAULT : applet.getFont());
        this.menuBackColor = this.paramToColor("menubackcolor", MenuParameters.menuBackColorDEFAULT);
        this.menuForeColor = this.paramToColor("menuforecolor", MenuParameters.menuForeColorDEFAULT);
        this.menuOutlineColor = this.paramToColor("menuoutlinecolor", MenuParameters.menuOutlineColorDEFAULT);
        this.menuHighliteBackColor = this.paramToColor("menuhighlitebackcolor", MenuParameters.menuHighliteBackColorDEFAULT);
        this.menuHighliteForeColor = this.paramToColor("menuhighliteforecolor", MenuParameters.menuHighliteForeColorDEFAULT);
        this.menuHorizMargin = this.paramToInt("menuhorizmargin", 4);
        this.menuVertMargin = this.paramToInt("menuvertmargin", 4);
    }
    
    static {
        menuFontDEFAULT = null;
        menuBackColorDEFAULT = Color.lightGray;
        menuForeColorDEFAULT = Color.black;
        menuOutlineColorDEFAULT = Color.black;
        menuHighliteBackColorDEFAULT = Color.darkGray;
        menuHighliteForeColorDEFAULT = Color.white;
    }
}
