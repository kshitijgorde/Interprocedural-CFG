// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.Container;
import java.net.MalformedURLException;
import java.applet.Applet;
import java.awt.Image;
import java.awt.Color;
import java.net.URL;
import java.applet.AppletContext;
import java.awt.Frame;

class Globals
{
    public static final String[][] paramInfo;
    public static final String[] paramHeadings;
    public static final int imgCenter = 0;
    public static final int imgTile = 1;
    public Frame appletFrameParent;
    public AppletContext context;
    public URL documentBase;
    public URL codeBase;
    public String sourceFile;
    public Color foregroundColor;
    public Color backgroundColor;
    public Color peopleBoxBkg;
    public int peopleBoxBorderWidth;
    public Image backgroundImage;
    public int bkgImageLayout;
    public boolean clearBackground;
    public int primary;
    public String detailLoc;
    public String htmlTarget;
    public int initialZoom;
    public URL helpUrl;
    public boolean dumpStats;
    public boolean doAbsoluteScrolling;
    public static final boolean groupSpouses = false;
    public static final int statusOK = 0;
    public static final int statusError = 1;
    public static final int statusBadParam = 2;
    public int statusCode;
    public String statusDesc;
    
    public Globals(final Applet applet, final String sourceFile, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, String detailLoc, String htmlTarget, final String s9, final String s10, final String s11) {
        this.doAbsoluteScrolling = true;
        this.statusCode = 0;
        this.statusDesc = "";
        this.appletFrameParent = this.getFrameParent(applet);
        this.context = applet.getAppletContext();
        this.documentBase = applet.getDocumentBase();
        this.codeBase = applet.getCodeBase();
        this.sourceFile = sourceFile;
        if (this.sourceFile == null) {
            this.statusCode = 2;
            this.statusDesc = "Missing source file";
        }
        try {
            if (s != null) {
                this.foregroundColor = new Color(Integer.parseInt(s, 16));
            }
            else {
                this.foregroundColor = Color.black;
            }
            if (s2 != null) {
                this.backgroundColor = new Color(Integer.parseInt(s2, 16));
            }
            else {
                this.backgroundColor = Color.lightGray;
            }
            if (s3 != null) {
                this.peopleBoxBkg = new Color(Integer.parseInt(s3, 16));
            }
            else {
                this.peopleBoxBkg = null;
            }
            if (s4 != null) {
                this.peopleBoxBorderWidth = Integer.parseInt(s4);
            }
            else if (s3 == null && s5 != null) {
                this.peopleBoxBorderWidth = 1;
            }
            else {
                this.peopleBoxBorderWidth = 2;
            }
            if (s5 != null) {
                this.backgroundImage = applet.getImage(this.documentBase, s5);
                if (this.backgroundImage == null) {
                    System.out.println("Could not find background image: " + s5);
                }
            }
            if (s6 != null) {
                switch (Integer.parseInt(s6)) {
                    case 0: {
                        this.bkgImageLayout = 1;
                        break;
                    }
                    default: {
                        this.bkgImageLayout = 0;
                        break;
                    }
                }
            }
            else {
                this.bkgImageLayout = 0;
            }
            if (s7 != null && Integer.parseInt(s7) == 1) {
                this.clearBackground = true;
            }
            else {
                this.clearBackground = false;
            }
            if (s8 != null) {
                this.primary = Integer.parseInt(s8);
            }
            else {
                this.primary = -1;
            }
            if (detailLoc != null && detailLoc.length() == 0) {
                detailLoc = null;
            }
            this.detailLoc = detailLoc;
            if (htmlTarget != null && htmlTarget.length() == 0) {
                htmlTarget = null;
            }
            this.htmlTarget = htmlTarget;
            Label_0425: {
                if (s9 != null) {
                    this.initialZoom = Integer.parseInt(s9);
                    break Label_0425;
                }
                this.initialZoom = 9;
                try {
                    this.helpUrl = null;
                    if (s10 != null) {
                        this.helpUrl = new URL(s10);
                    }
                }
                catch (MalformedURLException ex2) {}
                finally {
                    if (this.helpUrl == null) {
                        try {
                            this.helpUrl = new URL(this.codeBase, "zaluc/geneo/help.html");
                        }
                        catch (MalformedURLException ex3) {}
                    }
                }
            }
            if (s11 != null && Integer.parseInt(s11) == 1) {
                this.dumpStats = true;
                return;
            }
            this.dumpStats = false;
        }
        catch (NumberFormatException ex) {
            this.statusCode = 2;
            this.statusDesc = "NumberFormatException: " + ex.getMessage();
        }
    }
    
    public void updateContext(final AppletContext context) {
        this.context = context;
    }
    
    private Frame getFrameParent(final Applet applet) {
        Container container;
        for (container = applet.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        return (Frame)container;
    }
    
    static {
        paramInfo = new String[][] { { "Source", "filename", "(required) The name of the gedcom file to display" }, { "Width", "number", "(required) The width of the window to create" }, { "Height", "number", "(required) The height of the window to create" }, { "Foreground", "hexadecimal color value", "(optional) The color to display as the foreground" }, { "Background", "hexadecimal color value", "(optional) The color to display as the background" }, { "PeopleBoxBkg", "hexadecimal color value", "(optional) The color to display in the background of a people box" }, { "PBoxBorderWidth", "number", "(optional) The width of the people box border" }, { "BkgImage", "filename", "(optional) The image to display as the background" }, { "BkgImgLayout", "number", "(optional) How to layout background image (0 => center, 1 => tile), default is center" }, { "ClearBkg", "boolean (0,1)", "(optional) Force clearing the background (for transparent GIFs as BkgImage)" }, { "BorderWidth", "number", "(optional) The width of the border" }, { "EmbedInPage", "boolean (true,false)", "(optional) Embed Geneo in web page, default = false" }, { "Primary", "number", "(optional) Specify individual to start with as primary, default taken from data file" }, { "DetailLoc", "Partial URL", "(optional) Specify the base URL for an individuals detailed information" }, { "HtmlTarget", "String", "(optional) Specify the frame target for the html pages such as 'help' or 'details'" }, { "InitialZoom", "number", "(optional) Specify the initial zoom level at which the tree will be displayed (default = 9)" }, { "HelpUrl", "URL", "(optional) Specify the URL of the help page" }, { "DumpStats", "boolean (0,1)", "(optional) Dump memory and tree statistics to the java console" } };
        paramHeadings = new String[] { "Parameter:", "Type:", "Description:" };
    }
}
