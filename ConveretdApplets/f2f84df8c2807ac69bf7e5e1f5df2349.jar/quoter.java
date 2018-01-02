import java.util.Random;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.awt.Component;
import java.util.Vector;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class quoter extends Applet
{
    Graphics offscreen;
    Image buf;
    MediaTracker tracker;
    Dimension AppletSize;
    Color fontColor;
    Color bgColor;
    Color borderColor;
    Color tempColor;
    String rgbDelimiter;
    StringTokenizer st;
    Image bgImage;
    Integer intTemp;
    int intImageWidth;
    int intImageHeight;
    Boolean boolResizeImage;
    String strImageName;
    int intAppletWidth;
    int intAppletHeight;
    int intRightIndent;
    int intLeftIndent;
    int intIndent;
    int intTopIndent;
    int intSpace;
    FontMetrics fm;
    Font fontOurFont;
    Font FontTemp;
    int intFontSize;
    String strFileName;
    Vector vecQuotes;
    
    public void init() {
        this.AppletSize = this.size();
        this.intAppletWidth = this.AppletSize.width;
        this.intAppletHeight = this.AppletSize.height;
        this.intSpace = this.PickInt("space", 3);
        this.intRightIndent = this.PickInt("rightindent", 5);
        this.intLeftIndent = this.PickInt("leftindent", 20);
        this.intTopIndent = this.PickInt("topindent", 0);
        this.intIndent = this.intLeftIndent + this.intRightIndent;
        this.intFontSize = this.PickInt("fontsize", 18);
        this.fontOurFont = this.PickFont("fontname", "TimesRoman", "fontstyle", 0, this.intFontSize);
        this.fontColor = this.PickColor("fontcolor", Color.black);
        this.borderColor = this.PickColor("bordercolor", Color.black);
        this.bgColor = this.PickColor("bgcolor", Color.white);
        this.strImageName = this.PickString("imagename", "");
        this.buf = this.createImage(this.intAppletWidth, this.intAppletHeight);
        this.offscreen = this.buf.getGraphics();
        this.tracker = new MediaTracker(this);
        this.boolResizeImage = this.PickBoolean("resizeimage", Boolean.TRUE);
        if (this.boolResizeImage == Boolean.TRUE) {
            this.intImageWidth = this.intAppletWidth;
            this.intImageHeight = this.intAppletHeight;
            this.bgImage = this.getImage(this.getDocumentBase(), this.strImageName);
        }
        else {
            this.intImageWidth = this.PickInt("imagewidth", 10);
            this.intImageHeight = this.PickInt("imageheight", 10);
            this.bgImage = this.getImage(this.getDocumentBase(), this.strImageName);
            if (this.intImageWidth > this.intAppletWidth - 2) {
                this.intImageWidth = this.intAppletWidth;
            }
            if (this.intImageHeight > this.intAppletHeight - 2) {
                this.intImageHeight = this.intAppletHeight;
            }
        }
        this.tracker.addImage(this.bgImage, 0);
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println("Error waiting for background image to load.");
        }
        this.strFileName = this.PickString("quotefile", "");
        this.GetQuotes();
    }
    
    public String PickString(final String strStringParameter, final String strDefaultString) {
        final String strStringTemp = this.getParameter(strStringParameter);
        if (strStringTemp == null) {
            return strDefaultString;
        }
        return strStringTemp;
    }
    
    public Boolean PickBoolean(final String strBooleanParameter, final Boolean defaultBoolean) {
        final String strBooleanTemp = this.getParameter(strBooleanParameter);
        if (strBooleanTemp == null) {
            return defaultBoolean;
        }
        if (strBooleanTemp.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        }
        if (strBooleanTemp.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        }
        return defaultBoolean;
    }
    
    public Font PickFont(final String strFontNameParameter, final String strDefaultFontName, final String strFontStyleParameter, final int intDefaultFontStyle, final int intFontSize) {
        String strFontNameTemp = null;
        String strFontStyleTemp = null;
        strFontNameTemp = this.getParameter(strFontNameParameter);
        if (strFontNameTemp == null) {
            strFontNameTemp = strDefaultFontName;
        }
        else if (strFontNameTemp.equalsIgnoreCase("TimesRoman")) {
            strFontNameTemp = "TimesRoman";
        }
        else if (strFontNameTemp.equalsIgnoreCase("Helvetica")) {
            strFontNameTemp = "Helvetica";
        }
        else if (strFontNameTemp.equalsIgnoreCase("Courier")) {
            strFontNameTemp = "Courier";
        }
        else if (strFontNameTemp.equalsIgnoreCase("Dialog")) {
            strFontNameTemp = "Dialog";
        }
        else if (strFontNameTemp.equalsIgnoreCase("DialogInput")) {
            strFontNameTemp = "DialogInput";
        }
        else {
            strFontNameTemp = "TimesRoman";
        }
        strFontStyleTemp = this.getParameter(strFontStyleParameter);
        int intFontStyle;
        if (strFontStyleTemp == null) {
            intFontStyle = intDefaultFontStyle;
        }
        else if (strFontStyleTemp.equalsIgnoreCase("PLAIN")) {
            intFontStyle = 0;
        }
        else if (strFontStyleTemp.equalsIgnoreCase("BOLD")) {
            intFontStyle = 1;
        }
        else if (strFontStyleTemp.equalsIgnoreCase("ITALIC")) {
            intFontStyle = 2;
        }
        else {
            intFontStyle = intDefaultFontStyle;
        }
        return this.FontTemp = new Font(strFontNameTemp, intFontStyle, intFontSize);
    }
    
    public int PickInt(final String strParameter, final int intDefault) {
        try {
            this.intTemp = new Integer(this.getParameter(strParameter));
            return this.intTemp;
        }
        catch (Exception ex) {
            return intDefault;
        }
    }
    
    public Color PickColor(final String strParameter, final Color defaultColor) {
        String strColor = null;
        strColor = this.getParameter(strParameter);
        if (strColor != null) {
            this.st = new StringTokenizer(strColor, this.rgbDelimiter);
        }
        if (strColor == null) {
            return defaultColor;
        }
        if (strColor.equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (strColor.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (strColor.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (strColor.equalsIgnoreCase("darkGray")) {
            return Color.darkGray;
        }
        if (strColor.equalsIgnoreCase("gray")) {
            return Color.gray;
        }
        if (strColor.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (strColor.equalsIgnoreCase("lightGray")) {
            return Color.lightGray;
        }
        if (strColor.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (strColor.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        if (strColor.equalsIgnoreCase("pink")) {
            return Color.pink;
        }
        if (strColor.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (strColor.equalsIgnoreCase("white")) {
            return Color.white;
        }
        if (strColor.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        if (this.st.countTokens() == 3) {
            final Integer r = new Integer(this.st.nextToken());
            final Integer g = new Integer(this.st.nextToken());
            final Integer b = new Integer(this.st.nextToken());
            return this.tempColor = new Color(r, g, b);
        }
        return defaultColor;
    }
    
    synchronized void GetQuotes() {
        DataInputStream inStream = null;
        URL theURL = null;
        if (this.strFileName == null) {
            return;
        }
        try {
            if (this.strFileName.indexOf("http://") >= 0) {
                theURL = new URL(this.strFileName);
            }
            else {
                theURL = new URL(this.getDocumentBase(), this.strFileName);
            }
            try {
                final InputStream conn = theURL.openStream();
                inStream = new DataInputStream(new BufferedInputStream(conn));
                String strLine;
                while ((strLine = inStream.readLine()) != null) {
                    if (!strLine.startsWith("#")) {
                        if (strLine.length() == 0) {
                            continue;
                        }
                        this.vecQuotes.addElement(strLine);
                    }
                }
            }
            catch (IOException ex) {}
        }
        catch (MalformedURLException ex2) {}
    }
    
    public void paint(final Graphics g) {
        if (this.offscreen != null) {
            this.paintApplet(this.offscreen);
            g.drawImage(this.buf, 0, 0, this);
            return;
        }
        this.paintApplet(g);
    }
    
    public void paintApplet(final Graphics g) {
        final Random randNumber = new Random();
        final float fltRandomNumber = randNumber.nextFloat() * (this.vecQuotes.size() - 1);
        final int intRandomNumber = Math.abs(Math.round(fltRandomNumber));
        this.fm = g.getFontMetrics(this.fontOurFont);
        final int intFontAscent = this.fm.getAscent();
        g.setColor(this.bgColor);
        g.fillRect(0, 0, this.intAppletWidth, this.intAppletHeight);
        if (this.strImageName != "") {
            if (this.boolResizeImage == Boolean.TRUE) {
                g.drawImage(this.bgImage, 1, 1, null);
            }
            else {
                final int intImageX = Math.round((this.intAppletWidth - 2 - this.intImageWidth) / 2);
                final int intImageY = Math.round((this.intAppletHeight - 2 - this.intImageHeight) / 2);
                g.drawImage(this.bgImage, intImageX, intImageY, null);
            }
        }
        g.setColor(this.borderColor);
        g.drawRect(0, 0, this.intAppletWidth - 1, this.intAppletHeight - 1);
        g.setColor(this.fontColor);
        g.setFont(this.fontOurFont);
        String strQuote = this.vecQuotes.elementAt(intRandomNumber).toString();
        int intStringWidth = 0;
        int intEnd = 0;
        int intY = this.intTopIndent + intFontAscent;
        if (strQuote.length() <= 0) {
            g.drawString("Error: Empty Quote", this.intRightIndent, intY);
        }
        while (strQuote.length() > 0) {
            if (this.fm.stringWidth(strQuote) + this.intIndent < this.intAppletWidth) {
                g.drawString(strQuote.trim(), this.intLeftIndent, intY);
                strQuote = "";
            }
            else if (intStringWidth + this.intIndent < this.intAppletWidth) {
                intStringWidth += this.fm.charWidth(strQuote.charAt(intEnd));
                ++intEnd;
            }
            else {
                intEnd = strQuote.lastIndexOf(32, intEnd - 1);
                g.drawString(strQuote.substring(0, intEnd).trim(), this.intLeftIndent, intY);
                intY += intFontAscent + this.intSpace;
                strQuote = strQuote.substring(intEnd);
                intEnd = 0;
                intStringWidth = 0;
            }
        }
    }
    
    public quoter() {
        this.rgbDelimiter = ":,.";
        this.vecQuotes = new Vector();
    }
}
