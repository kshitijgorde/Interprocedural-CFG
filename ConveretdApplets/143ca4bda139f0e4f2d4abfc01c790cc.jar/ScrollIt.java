import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.Cursor;
import java.net.URL;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScrollIt extends Applet implements Runnable
{
    private boolean loadingApplet;
    private Color lg;
    private int appletWidth;
    private int appletHeight;
    private int scrollAreaWidth;
    private int scrollAreaHeight;
    private final int BUTTON_WIDTH = 17;
    private final int BUTTON_HEIGHT = 20;
    private int rx;
    private int sx;
    private int fx;
    private int yall;
    private boolean rewind;
    private boolean stopped;
    private boolean forward;
    private MediaTracker tracker;
    private Image[] button;
    Thread scrollThread;
    private int xPos;
    private int yPos;
    private int textWidth;
    private int textHeight;
    private String text;
    private Font f;
    private FontMetrics fm;
    private Graphics offscreenGraphics;
    private Image offscreenImage;
    private Color bgcolor;
    private Color scrollbgcolor;
    private Color fontcolor;
    private Color hovercolor;
    private Color linkcolor;
    private int brakes;
    private String fontface;
    private String fontstyle;
    private String fontbold;
    private int fontsize;
    private URL dir;
    private URL docBase;
    private String target;
    private String textfile;
    private int lineNumber;
    private String line;
    private String[] stringLine;
    private int link;
    private int linkTotal;
    private int[] linkStartPos;
    private int[] linkEndPos;
    private String[] linkURL;
    private String[] linkText;
    private int start;
    private int end;
    private int lurl;
    private Color currentlinkcolor;
    private int currentlink;
    private int oldlink;
    private boolean inscrollarea;
    private int wasoutside;
    private int mouseX;
    private int mouseY;
    private int newStartPos;
    private int newEndPos;
    private Cursor cursorHand;
    private Cursor cursorDefault;
    
    public void init() {
        this.cursorHand = new Cursor(12);
        this.setCursor(this.cursorDefault = Cursor.getDefaultCursor());
        System.out.println("(C)copyright 2000 Pikeus");
        this.loading();
        for (int i = 0; i <= 8; ++i) {
            this.button[i] = this.getImage(this.getCodeBase(), "b" + i + ".gif");
        }
        try {
            this.bgcolor = new Color(Integer.parseInt(this.getParameter("bgcolor"), 16));
        }
        catch (Exception ex2) {
            this.bgcolor = new Color(197, 197, 197);
        }
        try {
            this.scrollbgcolor = new Color(Integer.parseInt(this.getParameter("scrollbgcolor"), 16));
        }
        catch (Exception ex3) {
            this.scrollbgcolor = Color.white;
        }
        try {
            this.fontcolor = new Color(Integer.parseInt(this.getParameter("fontcolor"), 16));
        }
        catch (Exception ex4) {
            this.fontcolor = Color.black;
        }
        try {
            this.hovercolor = new Color(Integer.parseInt(this.getParameter("hovercolor"), 16));
        }
        catch (Exception ex5) {
            this.hovercolor = Color.red;
        }
        try {
            this.linkcolor = new Color(Integer.parseInt(this.getParameter("linkcolor"), 16));
        }
        catch (Exception ex6) {
            this.linkcolor = Color.blue;
        }
        this.currentlinkcolor = this.linkcolor;
        try {
            this.brakes = Integer.parseInt(this.getParameter("brakes"));
        }
        catch (Exception ex7) {
            this.brakes = 5;
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
            this.textfile = this.getParameter("textfile");
            if (this.textfile.equals("off")) {
                this.textfile = this.getParameter("text");
                final StringTokenizer stringTokenizer = new StringTokenizer(this.textfile, "^");
                this.lineNumber = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    this.stringLine[this.lineNumber] = stringTokenizer.nextToken();
                    ++this.lineNumber;
                }
                --this.lineNumber;
                this.text = this.processLines();
            }
            else {
                this.text = this.getText(this.textfile);
            }
        }
        catch (NullPointerException ex) {
            this.text = "* You have not specified a text file - or the 'text' parameter has errors";
            System.out.println(">" + ex);
        }
        if (this.text.equals("")) {
            this.text = "* The text file you have specified contains no information: check that the URL is correct, and that the text file exists. *";
        }
        this.target = this.getParameter("target");
        if (this.target == null) {
            this.target = "_self";
        }
        this.stopped = false;
        this.appletWidth = this.size().width - 1;
        this.appletHeight = this.size().height - 1;
        this.scrollAreaWidth = this.appletWidth - 67;
        this.scrollAreaHeight = this.appletHeight - 10;
        this.rx = this.scrollAreaWidth + 11;
        this.sx = this.scrollAreaWidth + 28;
        this.fx = this.scrollAreaWidth + 45;
        this.yall = this.appletHeight / 2 - 9;
        this.xPos = 0;
        this.yPos = 0;
        this.currentlink = -1;
        this.oldlink = -2;
        this.inscrollarea = false;
        this.wasoutside = 1;
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
        this.fm = this.getFontMetrics(this.f);
        this.textHeight = this.fm.getHeight();
        this.textWidth = this.fm.stringWidth(this.text);
        this.xPos = 0;
        this.yPos = (this.scrollAreaHeight - 3) / 2 - (this.textHeight / 2 + 4);
        this.offscreenImage = this.createImage(this.scrollAreaWidth - 3, this.scrollAreaHeight - 3);
        this.offscreenGraphics = this.offscreenImage.getGraphics();
    }
    
    public void start() {
        if (this.scrollThread == null) {
            (this.scrollThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.scrollThread != null && this.scrollThread.isAlive()) {
            this.scrollThread.stop();
        }
        this.scrollThread = null;
    }
    
    public void run() {
        final Graphics graphics = this.getGraphics();
        this.tracker = new MediaTracker(this);
        for (int i = 0; i <= 8; ++i) {
            this.tracker.addImage(this.button[i], i);
        }
        for (int j = 0; j <= 8; ++j) {
            try {
                this.tracker.waitForID(j);
            }
            catch (InterruptedException ex) {}
        }
        this.loadingApplet = false;
        this.repaint();
        Thread.currentThread().setPriority(1);
        while (true) {
            if (this.xPos + (this.scrollAreaWidth - 3) < -this.textWidth + 15) {
                if (this.xPos + (this.scrollAreaWidth - 3) >= -this.textWidth + 15) {
                    continue;
                }
                this.xPos = 0;
            }
            else {
                this.updateScrollArea(graphics);
                try {
                    Thread.sleep(this.brakes);
                }
                catch (InterruptedException ex2) {}
                if (this.forward) {
                    this.xPos += 8;
                    if (this.xPos + (this.scrollAreaWidth - 3) >= this.scrollAreaWidth - 3) {
                        this.xPos = 0;
                    }
                }
                if (this.rewind) {
                    this.xPos -= 8;
                }
                if (this.stopped || this.inscrollarea) {
                    continue;
                }
                --this.xPos;
            }
        }
    }
    
    public void updateScrollArea(final Graphics graphics) {
        this.offscreenGraphics.setColor(this.scrollbgcolor);
        this.offscreenGraphics.fillRect(0, 0, this.scrollAreaWidth - 3, this.scrollAreaHeight - 3);
        this.offscreenGraphics.setFont(this.f);
        this.offscreenGraphics.setColor(this.fontcolor);
        this.offscreenGraphics.drawString(this.text, this.xPos + (this.scrollAreaWidth - 3), this.yPos + this.textHeight);
        for (int i = 0; i < this.linkTotal; ++i) {
            if (this.mouseX > 8 & this.mouseX < this.scrollAreaWidth + 8) {
                this.inscrollarea = true;
                this.newStartPos = this.xPos + (this.scrollAreaWidth - 3);
                this.newStartPos += this.fm.stringWidth(this.text.substring(0, this.linkStartPos[i]));
                this.newEndPos = this.xPos + (this.scrollAreaWidth - 3);
                this.newEndPos += this.fm.stringWidth(this.text.substring(0, this.linkEndPos[i]));
                if (this.mouseX - 8 >= this.newStartPos & this.mouseX - 8 <= this.newEndPos) {
                    this.currentlink = i;
                    if (this.currentlink != this.oldlink | this.wasoutside == 1) {
                        this.currentlinkcolor = this.hovercolor;
                        this.oldlink = this.currentlink;
                        this.showStatus(this.linkURL[i]);
                        this.setCursor(this.cursorHand);
                        this.wasoutside = 0;
                    }
                }
                else if (this.oldlink == i & this.oldlink > -1) {
                    this.currentlinkcolor = this.linkcolor;
                    this.currentlink = -1;
                    this.oldlink = this.currentlink;
                    this.showStatus("");
                    this.setCursor(this.cursorDefault);
                }
            }
            else {
                this.inscrollarea = false;
                this.currentlink = -1;
                this.wasoutside = 1;
                this.setCursor(this.cursorDefault);
                this.currentlinkcolor = this.linkcolor;
            }
            if (this.oldlink == i) {
                this.offscreenGraphics.setColor(this.currentlinkcolor);
                this.offscreenGraphics.drawString(this.linkText[i], this.getLinkStart(i), this.yPos + this.textHeight);
            }
            else {
                this.offscreenGraphics.setColor(this.linkcolor);
                this.offscreenGraphics.drawString(this.linkText[i], this.getLinkStart(i), this.yPos + this.textHeight);
            }
        }
        graphics.drawImage(this.offscreenImage, 8, 7, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.loadingApplet) {
            graphics.setColor(Color.white);
            graphics.drawString("Loading...", 15, 15);
            return;
        }
        this.drawFrame(graphics);
        this.drawScrollArea(graphics);
        this.drawOffButtons(graphics);
    }
    
    public void drawFrame(final Graphics graphics) {
        graphics.setColor(this.bgcolor);
        graphics.fillRect(0, 0, this.appletWidth, this.appletHeight);
        graphics.setColor(this.lg);
        graphics.drawLine(0, 0, this.appletWidth, 0);
        graphics.drawLine(0, 0, 0, this.appletHeight);
        graphics.setColor(Color.white);
        graphics.drawLine(1, 1, this.appletWidth - 1, 1);
        graphics.drawLine(1, 1, 1, this.appletHeight - 1);
        graphics.setColor(Color.gray);
        graphics.drawLine(1, this.appletHeight, this.appletWidth, this.appletHeight);
        graphics.drawLine(this.appletWidth, this.appletHeight, this.appletWidth, 1);
        graphics.setColor(Color.black);
        graphics.drawLine(2, this.appletHeight - 1, this.appletWidth - 1, this.appletHeight - 1);
        graphics.drawLine(this.appletWidth - 1, 2, this.appletWidth - 1, this.appletHeight - 1);
    }
    
    public void drawScrollArea(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(6, 5, this.scrollAreaWidth, this.scrollAreaHeight);
        graphics.setColor(this.lg);
        graphics.draw3DRect(6, 5, this.scrollAreaWidth, this.scrollAreaHeight, false);
        graphics.setColor(Color.gray);
        graphics.draw3DRect(7, 6, this.scrollAreaWidth - 2, this.scrollAreaHeight - 2, false);
    }
    
    public void drawOffButtons(final Graphics graphics) {
        graphics.drawImage(this.button[4], this.rx, this.yall, this);
        if (!this.stopped) {
            graphics.drawImage(this.button[5], this.sx, this.yall, this);
        }
        else {
            graphics.drawImage(this.button[8], this.sx, this.yall, this);
        }
        graphics.drawImage(this.button[3], this.fx, this.yall, this);
    }
    
    public boolean mouseMove(final Event event, final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        final Graphics graphics = this.getGraphics();
        this.drawOffButtons(graphics);
        if (mouseY >= this.yall & mouseY < this.yall + 20) {
            if (mouseX >= this.rx & mouseX < this.rx + 17) {
                graphics.drawImage(this.button[1], this.rx, this.yall, this);
            }
            else if (mouseX > this.sx & mouseX < this.sx + 17) {
                if (!this.stopped) {
                    graphics.drawImage(this.button[2], this.sx, this.yall, this);
                }
            }
            else if (mouseX > this.fx & mouseX < this.fx + 17) {
                graphics.drawImage(this.button[0], this.fx, this.yall, this);
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        final Graphics graphics = this.getGraphics();
        this.drawOffButtons(graphics);
        if (this.currentlink != -1) {
            this.goThere(this.linkURL[this.currentlink]);
        }
        if (mouseY >= this.yall & mouseY < this.yall + 20) {
            if (mouseX >= this.rx & mouseX < this.rx + 17) {
                graphics.drawImage(this.button[7], this.rx, this.yall, this);
                this.rewind = true;
            }
            else if (mouseX > this.sx & mouseX < this.sx + 17) {
                if (this.stopped) {
                    this.stopped = false;
                    graphics.drawImage(this.button[2], this.sx, this.yall, this);
                }
                else {
                    this.stopped = true;
                    graphics.drawImage(this.button[8], this.sx, this.yall, this);
                }
            }
            else if (mouseX > this.fx & mouseX < this.fx + 17) {
                graphics.drawImage(this.button[6], this.fx, this.yall, this);
                this.forward = true;
            }
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        this.drawOffButtons(graphics);
        if (n2 >= this.yall & n2 < this.yall + 20) {
            if (n >= this.rx & n < this.rx + 17) {
                graphics.drawImage(this.button[1], this.rx, this.yall, this);
                this.rewind = false;
            }
            else if (n > this.sx & n < this.sx + 17) {
                if (!this.stopped) {
                    graphics.drawImage(this.button[2], this.sx, this.yall, this);
                }
            }
            else if (n > this.fx & n < this.fx + 17) {
                graphics.drawImage(this.button[0], this.fx, this.yall, this);
                this.forward = false;
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("ScrollIt applet");
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseX = 0;
        this.mouseY = 0;
        this.rewind = false;
        this.forward = false;
        this.drawOffButtons(this.getGraphics());
        return true;
    }
    
    public void goThere(final String s) {
        try {
            if (s.startsWith("http")) {
                this.dir = new URL(this.docBase, s);
                this.getAppletContext().showDocument(this.dir, this.target);
                if (this.target.equals("_self")) {
                    this.stop();
                }
            }
            else {
                this.dir = new URL(this.getDocumentBase(), s);
                this.getAppletContext().showDocument(this.dir, this.target);
                if (!this.target.equals("_self")) {
                    return;
                }
                this.stop();
            }
        }
        catch (MalformedURLException ex) {}
    }
    
    public String getText(final String s) {
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
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            Label_0107: {
                break Label_0107;
                String line;
                do {
                    this.stringLine[this.lineNumber] = this.line;
                    ++this.lineNumber;
                    line = dataInputStream.readLine();
                    this.line = line;
                } while (line != null);
            }
            --this.lineNumber;
        }
        catch (IOException ex) {
            System.out.println("IO Error:" + ex.getMessage());
        }
        return this.processLines();
    }
    
    public String processLines() {
        this.text = "";
        int i = 0;
        String string = "";
        this.link = 0;
        this.linkTotal = 0;
        while (i <= this.lineNumber) {
            if (this.stringLine[i].startsWith("|")) {
                for (int j = 1; j < this.stringLine[i].length(); ++j) {
                    string = String.valueOf(string) + this.stringLine[i].charAt(j);
                }
                this.text = String.valueOf(this.text) + string;
                this.linkText[this.link] = string;
                this.linkStartPos[this.link] = this.text.length() - string.length();
                this.linkEndPos[this.link] = this.text.length();
                string = "";
                final int n = i + 1;
                this.linkURL[this.link] = this.stringLine[n];
                ++this.link;
                i = n + 1;
            }
            else if (this.stringLine[i].startsWith("#")) {
                ++i;
            }
            else if (this.stringLine[i].equals("\n")) {
                ++i;
            }
            else {
                this.text = String.valueOf(this.text) + this.stringLine[i];
                ++i;
            }
        }
        this.linkTotal = this.link;
        return this.text;
    }
    
    public int getLinkStart(int n) {
        n = this.xPos + this.scrollAreaWidth - 3 + this.fm.stringWidth(this.text.substring(0, this.linkStartPos[n]));
        return n;
    }
    
    public int getLinkEnd(int n) {
        n = this.xPos + this.scrollAreaWidth - 3 + this.fm.stringWidth(this.text.substring(0, this.linkEndPos[n]));
        return n;
    }
    
    public void loading() {
        this.setBackground(Color.black);
        this.loadingApplet = true;
        this.repaint();
    }
    
    public String getAppletInfo() {
        return "Name: ScrollIt V2\nAuthor: Chris Pike\n";
    }
    
    public ScrollIt() {
        this.button = new Image[9];
        this.stringLine = new String[100];
        this.linkStartPos = new int[100];
        this.linkEndPos = new int[100];
        this.linkURL = new String[100];
        this.linkText = new String[100];
    }
}
