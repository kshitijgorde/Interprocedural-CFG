import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.Polygon;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.net.URL;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScrollItUp extends Applet implements Runnable
{
    Thread task;
    Thread delayThread;
    int x;
    int y;
    int textWidth;
    int textHeight;
    int i;
    Font f;
    FontMetrics fm;
    Graphics offscreenGraphics;
    Image offscreenImage;
    Graphics offscreenScrollGraphics;
    Image offscreenScrollImage;
    Graphics fullScrollGraphics;
    Image fullScrollImage;
    int appletWidth;
    int appletHeight;
    int screenWidth;
    int screenHeight;
    private Cursor cursorHand;
    private Cursor cursorDefault;
    private Color lightgray;
    private Color bgcolor;
    private Color scrollbgcolor;
    private Color outlinecolor;
    private Color fontcolor;
    private Color hovercolor;
    private Color linkcolor;
    private String fontface;
    private String fontstyle;
    private String fontbold;
    private int fontsize;
    private int brakes;
    private int margin;
    private URL dir;
    private URL docBase;
    private String target;
    private String textfile;
    private int lineNumber;
    private String stringLine;
    Vector wrapLine;
    int lineCount;
    int fullScreenHeight;
    private final int BUTTON_PLAIN = 0;
    private final int BUTTON_HOVER = 1;
    private final int BUTTON_CLICK = 2;
    private final int UP = 0;
    private final int STARTSTOP = 1;
    private final int DOWN = 2;
    private boolean upButtonOn;
    private boolean startStopButtonOn;
    private boolean downButtonOn;
    private int button;
    private int[] buttonStatus;
    private int buttonYstartPos;
    private int buttonXstartPos;
    private int linkCount;
    Vector linkText;
    Vector linkURL;
    Vector linkTarget;
    Vector linkXstart;
    Vector linkXend;
    Vector linkYstart;
    Vector linkYend;
    private boolean linkOn;
    private int previousLink;
    private int linkYcount;
    private int totalLinksOff;
    private boolean mouseisDown;
    
    public void init() {
        System.out.println("(C)copyright 2000 Pikeus");
        this.cursorHand = new Cursor(12);
        this.setCursor(this.cursorDefault = Cursor.getDefaultCursor());
        this.lineCount = 0;
        this.linkText = new Vector();
        this.linkURL = new Vector();
        this.linkTarget = new Vector();
        this.linkXstart = new Vector();
        this.linkXend = new Vector();
        this.linkYstart = new Vector();
        this.linkYend = new Vector();
        this.lightgray = new Color(191, 191, 191);
        try {
            this.bgcolor = new Color(Integer.parseInt(this.getParameter("bgcolor"), 16));
        }
        catch (Exception ex2) {
            this.bgcolor = new Color(197, 197, 197);
        }
        try {
            this.outlinecolor = new Color(Integer.parseInt(this.getParameter("outlinecolor"), 16));
        }
        catch (Exception ex3) {
            this.outlinecolor = Color.black;
        }
        try {
            this.scrollbgcolor = new Color(Integer.parseInt(this.getParameter("scrollbgcolor"), 16));
        }
        catch (Exception ex4) {
            this.scrollbgcolor = Color.white;
        }
        try {
            this.fontcolor = new Color(Integer.parseInt(this.getParameter("fontcolor"), 16));
        }
        catch (Exception ex5) {
            this.fontcolor = Color.black;
        }
        try {
            this.hovercolor = new Color(Integer.parseInt(this.getParameter("hovercolor"), 16));
        }
        catch (Exception ex6) {
            this.hovercolor = Color.red;
        }
        try {
            this.linkcolor = new Color(Integer.parseInt(this.getParameter("linkcolor"), 16));
        }
        catch (Exception ex7) {
            this.linkcolor = Color.blue;
        }
        this.fontface = this.getParameter("fontface");
        if (this.fontface == null) {
            this.fontface = "Arial";
        }
        this.fontstyle = this.getParameter("fontstyle");
        if (this.fontstyle == null) {
            this.fontstyle = "plain";
        }
        this.fontbold = this.getParameter("fontbold");
        if (this.fontbold == null) {
            this.fontbold = "off";
        }
        try {
            this.fontsize = Integer.parseInt(this.getParameter("fontsize"));
        }
        catch (Exception ex8) {
            this.fontsize = 16;
        }
        try {
            this.brakes = Integer.parseInt(this.getParameter("brakes"));
        }
        catch (Exception ex9) {
            this.brakes = 25;
        }
        try {
            this.margin = Integer.parseInt(this.getParameter("margin"));
        }
        catch (Exception ex10) {
            this.margin = 5;
        }
        this.target = this.getParameter("target");
        if (this.target == null) {
            this.target = "_self";
        }
        if (this.fontstyle.equals("plain")) {
            if (this.fontbold.equals("on")) {
                this.f = new Font(this.fontface, 1, this.fontsize);
            }
            else {
                this.f = new Font(this.fontface, 0, this.fontsize);
            }
        }
        else if (this.fontbold.equals("on")) {
            this.f = new Font(this.fontface, 3, this.fontsize);
        }
        else {
            this.f = new Font(this.fontface, 2, this.fontsize);
        }
        this.setBackground(this.bgcolor);
        this.x = 0;
        this.y = this.size().height;
        this.fm = this.getFontMetrics(this.f);
        this.textHeight = this.fm.getHeight();
        this.linkOn = false;
        this.previousLink = 0;
        this.linkYcount = this.fm.getMaxAscent() + 1;
        this.appletWidth = this.size().width - 1;
        this.appletHeight = this.size().height - 1;
        this.screenWidth = this.size().width - 19;
        this.screenHeight = this.size().height - 5;
        this.offscreenImage = this.createImage(this.size().width, this.size().height);
        this.offscreenGraphics = this.offscreenImage.getGraphics();
        this.offscreenScrollImage = this.createImage(this.screenWidth - 1, this.screenHeight - 1);
        this.offscreenScrollGraphics = this.offscreenScrollImage.getGraphics();
        this.upButtonOn = false;
        this.startStopButtonOn = false;
        this.downButtonOn = false;
        this.buttonYstartPos = this.appletHeight / 2 - 24;
        this.buttonXstartPos = this.appletWidth - 16;
        for (int i = 0; i < 3; ++i) {
            this.buttonStatus[i] = 0;
        }
        try {
            this.getText(this.textfile = this.getParameter("textfile"));
        }
        catch (Exception ex) {
            System.out.println(">" + ex);
        }
    }
    
    public void start() {
        (this.task = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.task != null) {
            this.task.stop();
            this.task = null;
        }
    }
    
    public void run() {
        while (true) {
            this.y = this.size().height + this.textHeight;
            while (this.y >= -this.fm.getMaxDescent() - this.fullScreenHeight) {
                this.repaint();
                try {
                    Thread.sleep(this.brakes);
                }
                catch (InterruptedException ex) {}
                if (this.upButtonOn) {
                    this.y += 8;
                    if (this.y < this.size().height + this.textHeight) {
                        continue;
                    }
                    this.y = -this.fm.getMaxDescent() - this.fullScreenHeight;
                }
                else if (this.downButtonOn) {
                    this.y -= 12;
                }
                else {
                    if (this.startStopButtonOn) {
                        continue;
                    }
                    --this.y;
                }
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.offscreenGraphics.setColor(this.bgcolor);
        this.offscreenGraphics.fillRect(0, 0, this.appletWidth, this.appletHeight);
        this.offscreenGraphics.setColor(this.outlinecolor);
        this.offscreenGraphics.drawRect(0, 0, this.appletWidth, this.appletHeight);
        this.offscreenGraphics.setColor(this.bgcolor);
        this.offscreenGraphics.draw3DRect(2, 2, this.screenWidth, this.screenHeight, false);
        this.offscreenScrollGraphics.setColor(this.scrollbgcolor);
        this.offscreenScrollGraphics.fillRect(0, 0, this.screenWidth, this.screenHeight);
        this.offscreenScrollGraphics.drawImage(this.fullScrollImage, 0, this.y, this);
        this.offscreenGraphics.drawImage(this.offscreenScrollImage, 3, 3, this);
        this.drawButtons(graphics);
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    public void drawButtons(final Graphics graphics) {
        for (int i = 0; i < 3; ++i) {
            if (this.buttonStatus[i] == 0) {
                this.fillButtons(graphics, true, true, i);
            }
            else if (this.buttonStatus[i] == 1) {
                this.fillButtons(graphics, true, false, i);
            }
            else if (this.buttonStatus[i] == 2) {
                this.fillButtons(graphics, false, false, i);
            }
        }
    }
    
    public void fillButtons(final Graphics graphics, final boolean b, final boolean b2, final int n) {
        final int n2 = this.buttonXstartPos + 2;
        final int buttonYstartPos = this.buttonYstartPos;
        this.offscreenGraphics.setColor(this.lightgray);
        this.offscreenGraphics.fillRect(n2 + 1, buttonYstartPos + n * 17 + 1, 11, 13);
        this.offscreenGraphics.setColor(new Color(191, 191, 191));
        this.offscreenGraphics.draw3DRect(n2 + 1, buttonYstartPos + n * 17 + 1, 10, 12, b);
        this.offscreenGraphics.setColor(Color.black);
        this.offscreenGraphics.drawRect(n2, buttonYstartPos + n * 17, 12, 14);
        if (b2) {
            this.offscreenGraphics.setColor(Color.gray);
        }
        else {
            this.offscreenGraphics.setColor(Color.black);
        }
        if (n == 0) {
            final Polygon polygon = new Polygon();
            polygon.addPoint(n2 + 6, buttonYstartPos + 3);
            polygon.addPoint(n2 + 9, buttonYstartPos + 6);
            polygon.addPoint(n2 + 3, buttonYstartPos + 6);
            polygon.addPoint(n2 + 6, buttonYstartPos + 3);
            polygon.addPoint(n2 + 6, buttonYstartPos + 7);
            polygon.addPoint(n2 + 9, buttonYstartPos + 10);
            polygon.addPoint(n2 + 3, buttonYstartPos + 10);
            polygon.addPoint(n2 + 6, buttonYstartPos + 7);
            this.offscreenGraphics.drawPolygon(polygon);
            this.offscreenGraphics.fillPolygon(polygon);
            return;
        }
        if (n == 1) {
            this.offscreenGraphics.fillRect(n2 + 4, buttonYstartPos + 17 + 5, 5, 5);
            return;
        }
        final int n3 = buttonYstartPos + 34;
        final Polygon polygon2 = new Polygon();
        polygon2.addPoint(n2 + 6, n3 + 11);
        polygon2.addPoint(n2 + 9, n3 + 8);
        polygon2.addPoint(n2 + 3, n3 + 8);
        polygon2.addPoint(n2 + 6, n3 + 11);
        polygon2.addPoint(n2 + 6, n3 + 7);
        polygon2.addPoint(n2 + 9, n3 + 4);
        polygon2.addPoint(n2 + 3, n3 + 4);
        polygon2.addPoint(n2 + 6, n3 + 7);
        this.offscreenGraphics.drawPolygon(polygon2);
        this.offscreenGraphics.fillPolygon(polygon2);
    }
    
    public void drawLinkOn(final int n) {
        final Integer n2 = this.linkXstart.elementAt(n);
        final Integer n3 = this.linkYend.elementAt(n);
        this.fullScrollGraphics.setColor(this.hovercolor);
        this.fullScrollGraphics.drawString(this.linkText.elementAt(n), n2 - 3, n3);
    }
    
    public void drawLinkOff(final int n) {
        this.linkOn = false;
        final Integer n2 = this.linkXstart.elementAt(n);
        final Integer n3 = this.linkYend.elementAt(n);
        this.fullScrollGraphics.setColor(this.linkcolor);
        this.fullScrollGraphics.drawString(this.linkText.elementAt(n), n2 - 3, n3);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.getGraphics();
        for (int i = 0; i < 3; ++i) {
            this.buttonStatus[i] = 0;
            this.setCursor(this.cursorDefault);
        }
        if (this.whereIsMouse(n, n2) < 3) {
            this.buttonStatus[this.whereIsMouse(n, n2)] = 1;
        }
        else if (this.whereIsMouse(n, n2) == 3) {
            for (int j = 0; j < 3; ++j) {
                this.buttonStatus[j] = 0;
                this.setCursor(this.cursorDefault);
                this.linkOn = false;
            }
            this.totalLinksOff = 0;
            for (int k = 0; k <= this.linkText.size() - 1; ++k) {
                final Integer n3 = this.linkXstart.elementAt(k);
                final Integer n4 = this.linkXend.elementAt(k);
                if ((n2 - (this.y + 3) >= this.linkYstart.elementAt(k) & n2 - (this.y + 3) <= this.linkYend.elementAt(k)) && (n >= n3 & n <= n4)) {
                    this.linkOn = true;
                }
                if (!this.linkOn) {
                    ++this.totalLinksOff;
                    this.drawLinkOff(k);
                }
                else {
                    this.setCursor(this.cursorHand);
                    this.showStatus(String.valueOf(this.linkURL.elementAt(k)));
                    this.drawLinkOff(this.previousLink);
                    this.drawLinkOn(k);
                    this.previousLink = k;
                }
            }
            if (this.totalLinksOff == this.linkText.size()) {
                this.showStatus("");
            }
        }
        else {
            for (int l = 0; l < 3; ++l) {
                this.buttonStatus[l] = 0;
                this.setCursor(this.cursorDefault);
            }
        }
        this.checkStartStop();
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.getGraphics();
        if (this.whereIsMouse(n, n2) < 3) {
            this.buttonStatus[this.whereIsMouse(n, n2)] = 2;
            if (this.whereIsMouse(n, n2) == 1) {
                if (this.startStopButtonOn) {
                    this.startStopButtonOn = false;
                }
                else {
                    this.startStopButtonOn = true;
                }
            }
            else if (this.whereIsMouse(n, n2) == 2) {
                this.upButtonOn = true;
            }
            else if (this.whereIsMouse(n, n2) == 0) {
                this.downButtonOn = true;
            }
        }
        for (int i = 0; i <= this.linkText.size() - 1; ++i) {
            final Integer n3 = this.linkXstart.elementAt(i);
            final Integer n4 = this.linkXend.elementAt(i);
            if ((n2 - (this.y + 3) >= this.linkYstart.elementAt(i) & n2 - (this.y + 3) <= this.linkYend.elementAt(i)) && (n >= n3 & n <= n4)) {
                this.goThere((String)this.linkURL.elementAt(i), (String)this.linkTarget.elementAt(i));
            }
        }
        this.mouseisDown = true;
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.getGraphics();
        if (this.whereIsMouse(n, n2) < 3) {
            this.buttonStatus[this.whereIsMouse(n, n2)] = 1;
        }
        this.checkStartStop();
        this.upButtonOn = false;
        this.downButtonOn = false;
        this.mouseisDown = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("ScrollItUp applet");
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.getGraphics();
        for (int i = 0; i < 3; ++i) {
            this.buttonStatus[i] = 0;
            this.setCursor(this.cursorDefault);
        }
        this.checkStartStop();
        this.showStatus("");
        return true;
    }
    
    public int whereIsMouse(final int n, final int n2) {
        int n3 = 4;
        if (n >= this.buttonXstartPos & n <= this.buttonXstartPos + 11) {
            if (n2 >= this.buttonYstartPos & n2 <= this.buttonYstartPos + 14) {
                n3 = 0;
                this.setCursor(this.cursorHand);
            }
            else if (n2 >= this.buttonYstartPos + 17 & n2 <= this.buttonYstartPos + 31) {
                n3 = 1;
                this.setCursor(this.cursorHand);
            }
            else if (n2 >= this.buttonYstartPos + 34 & n2 <= this.buttonYstartPos + 48) {
                n3 = 2;
                this.setCursor(this.cursorHand);
            }
        }
        else {
            n3 = 3;
        }
        return n3;
    }
    
    public void checkStartStop() {
        if (this.buttonStatus[1] != 1) {
            if (this.startStopButtonOn) {
                this.buttonStatus[1] = 2;
                return;
            }
            this.buttonStatus[1] = 0;
        }
        else {
            if (this.startStopButtonOn) {
                this.buttonStatus[1] = 2;
                return;
            }
            this.buttonStatus[1] = 1;
        }
    }
    
    public void goThere(final String s, final String s2) {
        try {
            if (s.startsWith("http")) {
                this.dir = new URL(this.docBase, s);
                this.getAppletContext().showDocument(this.dir, s2);
                if (s2.equals("_self")) {
                    this.stop();
                }
            }
            else {
                this.dir = new URL(this.getDocumentBase(), s);
                this.getAppletContext().showDocument(this.dir, s2);
                if (!s2.equals("_self") || s.startsWith("#") || s.startsWith("mailto")) {
                    return;
                }
                this.stop();
            }
        }
        catch (MalformedURLException ex) {}
    }
    
    public void takeStringAndWrapItToScreenLength(final String s) {
        String string = "";
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " |,", true);
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String s2 = stringTokenizer.nextToken();
            if (s2.startsWith("|")) {
                boolean b = true;
                String nextToken = "";
                while (b) {
                    try {
                        if (nextToken.endsWith("|")) {
                            b = false;
                        }
                        else {
                            nextToken = stringTokenizer.nextToken();
                            s2 = String.valueOf(s2) + nextToken;
                        }
                    }
                    catch (Exception ex) {
                        s2 = String.valueOf(s2) + " ";
                    }
                }
                final String s3 = s2;
                s2 = "";
                final String s4 = ",";
                int i = 1;
                boolean b2 = true;
                while (b2) {
                    try {
                        if (s3.charAt(i) != s4.charAt(0)) {
                            s2 = String.valueOf(s2) + s3.charAt(i);
                            ++i;
                        }
                        else {
                            b2 = false;
                        }
                    }
                    catch (Exception ex2) {
                        s2 = String.valueOf(s2) + " ";
                        ++i;
                    }
                }
                this.linkText.addElement(s2);
                boolean b3 = true;
                ++i;
                while (b3) {
                    if (s3.charAt(i) != s4.charAt(0)) {
                        string = String.valueOf(string) + s3.charAt(i);
                        ++i;
                    }
                    else {
                        b3 = false;
                    }
                }
                this.linkURL.addElement(string);
                String string2 = "";
                ++i;
                while (i <= s3.length() - 2) {
                    string2 = String.valueOf(string2) + s3.charAt(i);
                    ++i;
                }
                this.linkTarget.addElement(string2);
                string = "";
                n = 1;
            }
            sb.append(s2);
            if (this.fm.stringWidth(sb.toString().trim()) >= this.screenWidth - 4 - this.margin * 2) {
                this.wrapLine.addElement(sb2.toString().trim());
                this.linkYcount += this.textHeight;
                sb.setLength(0);
                sb2.setLength(0);
                if (n != 0) {
                    this.linkYstart.addElement(Integer.valueOf(String.valueOf(this.linkYcount - this.textHeight)));
                    this.linkYend.addElement(Integer.valueOf(String.valueOf(this.linkYcount)));
                    this.linkXstart.addElement(Integer.valueOf(String.valueOf(this.fm.stringWidth(sb2.toString()) + (3 + this.margin))));
                    this.linkXend.addElement(Integer.valueOf(String.valueOf(this.fm.stringWidth(sb2.toString()) + this.fm.stringWidth(s2) + (3 + this.margin))));
                }
                sb2.append(s2);
                sb.append(s2);
            }
            else {
                if (n != 0) {
                    this.linkYstart.addElement(Integer.valueOf(String.valueOf(this.linkYcount - this.textHeight)));
                    this.linkYend.addElement(Integer.valueOf(String.valueOf(this.linkYcount)));
                    this.linkXstart.addElement(Integer.valueOf(String.valueOf(this.fm.stringWidth(sb2.toString()) + (3 + this.margin))));
                    this.linkXend.addElement(Integer.valueOf(String.valueOf(this.fm.stringWidth(sb2.toString()) + this.fm.stringWidth(s2) + (3 + this.margin))));
                }
                sb2.append(s2);
            }
            n = 0;
        }
        this.wrapLine.addElement(sb2.toString().trim());
        this.linkYcount += this.textHeight;
    }
    
    public void drawTextScreen() {
        this.getGraphics();
        this.fullScreenHeight = (this.wrapLine.size() + 1) * this.textHeight + this.fm.getMaxDescent();
        this.fullScrollImage = this.createImage(this.screenWidth, this.fullScreenHeight);
        (this.fullScrollGraphics = this.fullScrollImage.getGraphics()).setColor(this.scrollbgcolor);
        this.fullScrollGraphics.fillRect(0, 0, this.screenWidth, this.fullScreenHeight);
        for (int i = 0; i < this.wrapLine.size(); ++i) {
            this.fullScrollGraphics.setColor(this.fontcolor);
            this.fullScrollGraphics.setFont(this.f);
            this.fullScrollGraphics.drawString(this.wrapLine.elementAt(i).toString(), this.margin, this.fm.getMaxAscent() + (this.textHeight * this.lineCount + 1));
            ++this.lineCount;
        }
        for (int j = 0; j <= this.linkText.size() - 1; ++j) {
            this.drawLinkOn(j);
        }
    }
    
    public void getText(final String s) {
        try {
            if (s.startsWith("http")) {
                this.dir = new URL(this.docBase, s);
            }
            else {
                this.dir = new URL(this.getDocumentBase(), s);
            }
        }
        catch (MalformedURLException ex2) {}
        this.lineNumber = 0;
        try {
            final URLConnection openConnection = this.dir.openConnection();
            openConnection.connect();
            String line;
            while ((line = new DataInputStream(openConnection.getInputStream()).readLine()) != null) {
                this.stringLine = line;
                if (!this.stringLine.startsWith("#")) {
                    this.takeStringAndWrapItToScreenLength(this.stringLine);
                }
            }
        }
        catch (IOException ex) {
            System.out.println("IO Error:" + ex.getMessage());
        }
        this.drawTextScreen();
    }
    
    public String getAppletInfo() {
        return "Name: ScrollItUp\nAuthor: Chris Pike\n";
    }
    
    public ScrollItUp() {
        this.wrapLine = new Vector();
        this.buttonStatus = new int[3];
    }
}
