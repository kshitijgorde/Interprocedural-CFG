// 
// Decompiled by Procyon v0.5.30
// 

package newstick.param;

import java.net.MalformedURLException;
import java.applet.Applet;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;

public class TyperParameters extends AppletParameters
{
    private static final String idPARAM = "tickerID";
    private static final String idDEFAULT = "1";
    private static final String contentUrlPARAM = "contenturl";
    private static final String contentUrlDEFAULT = "http://www.wldcup.com/newsexp/content";
    private static final String startThemePARAM = "startoption";
    private static final String startThemeDEFAULT = "newsheadlines";
    private static final String waitingMessagePARAM = "waitingmessage";
    private static final String waitingMessageDEFAULT = "Waiting to connect...";
    private static final String waitingMessageRepeatPARAM = "waitingmessagerepeat";
    private static final int waitingMessageRepeatDEFAULT = 1;
    private static final String newPageTopPARAM = "newpagetop";
    private static final int newPageTopDEFAULT = 2;
    private static final String typerStartTopVALUE = "top";
    private static final String typerStartBottomVALUE = "bottom";
    private static final String typerStartPARAM = "typerstart";
    private static final String typerStartDEFAULT = "top";
    public static final int typer3DAreaRaisedENUM = 1;
    public static final int typer3DAreaSunkENUM = 2;
    private static final String typer3DAreaRaisedVALUE = "raised";
    private static final String typer3DAreaSunkVALUE = "sunk";
    private static final String typer3DAreaPARAM = "typer3darea";
    private static final int typer3DAreaDEFAULT = 0;
    private static final String typerLineSpacePARAM = "typerlinespace";
    private static final int typerLineSpaceDEFAULT = 0;
    private static final String typerCharSpacePARAM = "typercharspace";
    private static final int typerCharSpaceDEFAULT = 0;
    private static final String typerLineSpeedPARAM = "typerlinespeed";
    private static final int typerLineSpeedDEFAULT = 300;
    private static final String typerCharSpeedPARAM = "typercharspeed";
    private static final int typerCharSpeedDEFAULT = 70;
    private static final String typerScrollSpeedPARAM = "typerscrollspeed";
    private static final int typerScrollSpeedDEFAULT = 15;
    private static final String afterTitleSpacePARAM = "aftertitlespace";
    private static final int afterTitleSpaceDEFAULT = 5;
    private static final String afterTextSpacePARAM = "aftertextspace";
    private static final int afterTextSpaceDEFAULT = 10;
    private static final String fontPARAM = "font";
    private static final Font fontDEFAULT;
    private static final String titleFontPARAM = "titlefont";
    private static final Font titleFontDEFAULT;
    private static final String backColorPARAM = "backcolor";
    private static final Color backColorDEFAULT;
    private static final String backTyperColorPARAM = "backtypercolor";
    private static final Color backTyperColorDEFAULT;
    private static final String foreColorPARAM = "forecolor";
    private static final Color foreColorDEFAULT;
    private static final String highliteForeColorPARAM = "highliteforecolor";
    private static final Color highliteForeColorDEFAULT;
    private static final String titleColorPARAM = "titlecolor";
    private static final Color titleColorDEFAULT;
    private static final String highliteTitleColorPARAM = "highlitetitlecolor";
    private static final Color highliteTitleColorDEFAULT;
    private static final String outlineColorPARAM = "outlinecolor";
    private static final Color outlineColorDEFAULT;
    private static final String typerTopPARAM = "typertop";
    private static final int typerTopDEFAULT = 2;
    private static final String typerLeftPARAM = "typerleft";
    private static final int typerLeftDEFAULT = 2;
    private static final String typerRightPARAM = "typerright";
    private static final int typerRightDEFAULT = 2;
    private static final String typerBottomPARAM = "typerbottom";
    private static final int typerBottomDEFAULT = 2;
    private static final String textVertMarginPARAM = "textvertmargin";
    private static final int textVertMarginDEFAULT = 0;
    private static final String textHorizMarginPARAM = "texthorizmargin";
    private static final int textHorizMarginDEFAULT = 0;
    public String id;
    public URL contentUrl;
    public String startTheme;
    public String waitingMessage;
    public int waitingMessageRepeat;
    public int newPageTop;
    public float typerStart;
    public int typer3DArea;
    public boolean typer3DAreaBool;
    public int typerLineSpace;
    public int typerCharSpace;
    public int typerLineSpeed;
    public int typerCharSpeed;
    public int typerScrollSpeed;
    public int afterTitleSpace;
    public int afterTextSpace;
    public Font font;
    public Font titleFont;
    public Color backColor;
    public Color backTyperColor;
    public Color foreColor;
    public Color highliteForeColor;
    public Color outlineColor;
    public Color titleColor;
    public Color highliteTitleColor;
    public int typerTop;
    public int typerLeft;
    public int typerRight;
    public int typerBottom;
    public int textHorizMargin;
    public int textVertMargin;
    
    public TyperParameters(final Applet applet) {
        super(applet);
        this.id = this.paramToString("tickerID", "1");
        this.contentUrl = this.paramToURL("contenturl", (URL)null);
        if (this.contentUrl == null) {
            this.contentUrl = this.paramToURL("NewsURL", (URL)null);
        }
        if (this.contentUrl == null) {
            try {
                this.contentUrl = new URL("http://www.wldcup.com/newsexp/content");
            }
            catch (MalformedURLException ex) {}
        }
        this.startTheme = this.paramToString("startoption", "newsheadlines");
        this.waitingMessage = this.paramToString("waitingmessage", "Waiting to connect...");
        this.waitingMessageRepeat = this.paramToInt("waitingmessagerepeat", 1);
        if (this.waitingMessageRepeat < 1 || this.waitingMessageRepeat > 100) {
            this.waitingMessageRepeat = 1;
        }
        this.newPageTop = this.paramToInt("newpagetop", -1);
        if (this.newPageTop == -1) {
            this.newPageTop = this.paramToInt("Lines", -1);
        }
        if (this.newPageTop == -1) {
            this.newPageTop = 2;
        }
        if (this.paramToString("typerstart", "top").toLowerCase().compareTo("top") == 0) {
            this.typerStart = 0.0f;
        }
        else {
            this.typerStart = 1.0f;
        }
        this.typer3DAreaBool = false;
        final String paramToString = this.paramToString("typer3darea", null);
        if (paramToString == null) {
            this.typer3DArea = 0;
        }
        else if (paramToString.toLowerCase().compareTo("raised") == 0) {
            this.typer3DArea = 1;
            this.typer3DAreaBool = true;
        }
        else if (paramToString.toLowerCase().compareTo("sunk") == 0) {
            this.typer3DArea = 2;
            this.typer3DAreaBool = true;
        }
        else {
            this.typer3DArea = 0;
        }
        this.typerLineSpace = this.paramToInt("typerlinespace", 0);
        this.typerCharSpace = this.paramToInt("typercharspace", 0);
        this.typerLineSpeed = this.paramToInt("typerlinespeed", 300);
        this.typerCharSpeed = this.paramToInt("typercharspeed", -1);
        if (this.typerCharSpeed == -1) {
            this.typerCharSpeed = this.paramToInt("TickSpeed", -1);
        }
        if (this.typerCharSpeed == -1) {
            this.typerCharSpeed = 70;
        }
        this.typerScrollSpeed = this.paramToInt("typerscrollspeed", -1);
        if (this.typerScrollSpeed == -1) {
            this.typerScrollSpeed = this.paramToInt("ScrollSpeed", -1);
        }
        if (this.typerScrollSpeed == -1) {
            this.typerScrollSpeed = 15;
        }
        this.afterTitleSpace = this.paramToInt("aftertitlespace", 5);
        this.afterTextSpace = this.paramToInt("aftertextspace", 10);
        if (applet.getParameter("Font") != null && applet.getParameter("Font").indexOf(",") != -1) {
            this.font = this.paramToFont("font", (Font)null);
        }
        if (this.font == null) {
            this.font = this.toFont(this.paramToString("Font", "Tahoma") + "," + this.paramToString("FontStyle", "Plain") + "," + this.paramToString("FontSize", "10"), ",");
        }
        if (this.font == null) {
            this.font = ((TyperParameters.fontDEFAULT != null) ? TyperParameters.fontDEFAULT : applet.getFont());
        }
        this.titleFont = this.paramToFont("titlefont", (TyperParameters.titleFontDEFAULT != null) ? TyperParameters.titleFontDEFAULT : this.font);
        this.backColor = this.paramToColor("backcolor", TyperParameters.backColorDEFAULT);
        this.backTyperColor = this.paramToColor("backtypercolor", (Color)null);
        if (this.backTyperColor == null) {
            this.backTyperColor = this.paramToColor("BgColor", (Color)null);
        }
        if (this.backTyperColor == null) {
            this.backTyperColor = TyperParameters.backTyperColorDEFAULT;
        }
        this.foreColor = this.paramToColor("forecolor", (Color)null);
        if (this.foreColor == null) {
            this.foreColor = this.paramToColor("FgColor", (Color)null);
        }
        if (this.foreColor == null) {
            this.foreColor = TyperParameters.foreColorDEFAULT;
        }
        this.highliteForeColor = this.paramToColor("highliteforecolor", TyperParameters.highliteForeColorDEFAULT);
        this.titleColor = this.paramToColor("titlecolor", (Color)null);
        if (this.titleColor == null) {
            this.titleColor = this.paramToColor("FgColor", (Color)null);
        }
        if (this.titleColor == null) {
            this.titleColor = TyperParameters.titleColorDEFAULT;
        }
        this.highliteTitleColor = this.paramToColor("highlitetitlecolor", TyperParameters.highliteTitleColorDEFAULT);
        this.outlineColor = this.paramToColor("outlinecolor", TyperParameters.outlineColorDEFAULT);
        this.typerTop = this.paramToInt("typertop", 2);
        this.typerLeft = this.paramToInt("typerleft", 2);
        this.typerRight = this.paramToInt("typerright", 2);
        this.typerBottom = this.paramToInt("typerbottom", 2);
        this.textHorizMargin = this.paramToInt("texthorizmargin", 0);
        this.textVertMargin = this.paramToInt("textvertmargin", -1);
        if (this.textVertMargin == -1) {
            this.textVertMargin = this.paramToInt("Leading", -1);
        }
        if (this.textVertMargin == -1) {
            this.textVertMargin = 0;
        }
    }
    
    static {
        fontDEFAULT = null;
        titleFontDEFAULT = null;
        backColorDEFAULT = Color.white;
        backTyperColorDEFAULT = Color.white;
        foreColorDEFAULT = Color.black;
        highliteForeColorDEFAULT = Color.red;
        titleColorDEFAULT = Color.black;
        highliteTitleColorDEFAULT = Color.red;
        outlineColorDEFAULT = TyperParameters.backTyperColorDEFAULT;
    }
}
