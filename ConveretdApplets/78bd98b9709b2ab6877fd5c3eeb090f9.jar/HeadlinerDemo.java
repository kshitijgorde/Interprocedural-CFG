import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.net.URLConnection;
import java.awt.Toolkit;
import java.util.StringTokenizer;
import java.io.DataInputStream;
import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class HeadlinerDemo extends Applet implements Runnable
{
    private String[] headLine;
    private String[] frame;
    private Font[] font;
    private Color[] fontColor;
    private Color bgColor;
    private Color mouseOverColor;
    private Color defaultFgColor;
    private int[] fontSize;
    private int[] fontStyle;
    private int refreshRate;
    private int header;
    private URL[] url;
    private boolean runable;
    private boolean updated;
    private boolean painted;
    private String fileName;
    private int numOfHeadline;
    private int startMove;
    private String stdFont;
    private Rectangle[] rect;
    private Rectangle drawingRect;
    public Thread runner;
    private Thread loadMe;
    private Graphics osg;
    private static Image osi;
    private int lastRect;
    private int changes;
    private int scrollDelay;
    private int margin;
    private int maxHeadline;
    private Point mouseLoc;
    
    public void init() {
        try {
            final String parameter = this.getParameter("maxHeadline");
            if (!parameter.equals("")) {
                this.maxHeadline = Integer.parseInt(parameter);
            }
            else {
                this.maxHeadline = 100;
            }
        }
        catch (Exception ex3) {
            this.maxHeadline = 100;
        }
        this.headLine = new String[this.maxHeadline];
        this.fontColor = new Color[this.maxHeadline];
        this.fontSize = new int[this.maxHeadline];
        this.fontStyle = new int[this.maxHeadline];
        this.url = new URL[this.maxHeadline];
        this.frame = new String[this.maxHeadline];
        this.rect = new Rectangle[this.maxHeadline];
        final String[] array = new String[this.maxHeadline];
        this.drawingRect = new Rectangle(0, 0, this.size().width, this.size().height);
        this.header = 0;
        this.lastRect = 0;
        this.mouseLoc = new Point(-1, -1);
        HeadlinerDemo.osi = this.createImage(this.size().width, this.size().height);
        this.osg = HeadlinerDemo.osi.getGraphics();
        try {
            final String parameter2 = this.getParameter("fileName");
            if (!parameter2.equals("")) {
                this.fileName = parameter2;
            }
            else {
                this.runable = false;
            }
        }
        catch (Exception ex) {
            this.runable = false;
            ex.printStackTrace();
        }
        try {
            final String parameter3 = this.getParameter("backgroundColor");
            if (!parameter3.equals("")) {
                final int index = parameter3.indexOf(",");
                final int index2 = parameter3.indexOf(",", index + 1);
                this.bgColor = new Color(Integer.parseInt(parameter3.substring(0, index)), Integer.parseInt(parameter3.substring(index + 1, index2)), Integer.parseInt(parameter3.substring(index2 + 1, parameter3.length())));
            }
        }
        catch (Exception ex4) {
            this.bgColor = new Color(0, 0, 0);
        }
        try {
            final String parameter4 = this.getParameter("defaultFgColor");
            if (!parameter4.equals("")) {
                final int index3 = parameter4.indexOf(",");
                final int index4 = parameter4.indexOf(",", index3 + 1);
                this.defaultFgColor = new Color(Integer.parseInt(parameter4.substring(0, index3)), Integer.parseInt(parameter4.substring(index3 + 1, index4)), Integer.parseInt(parameter4.substring(index4 + 1, parameter4.length())));
            }
        }
        catch (Exception ex5) {
            this.defaultFgColor = null;
        }
        try {
            final String parameter5 = this.getParameter("fontName");
            if (!parameter5.equals("")) {
                this.stdFont = parameter5;
            }
            else {
                this.stdFont = "TimesNewRoman";
            }
        }
        catch (Exception ex6) {
            this.stdFont = "TimesNewRoman";
        }
        try {
            final String parameter6 = this.getParameter("mouseOverColor");
            if (!parameter6.equals("")) {
                final int index5 = parameter6.indexOf(",");
                final int index6 = parameter6.indexOf(",", index5 + 1);
                this.mouseOverColor = new Color(Integer.parseInt(parameter6.substring(0, index5)), Integer.parseInt(parameter6.substring(index5 + 1, index6)), Integer.parseInt(parameter6.substring(index6 + 1, parameter6.length())));
            }
            else {
                this.mouseOverColor = new Color(255, 0, 0);
            }
        }
        catch (Exception ex7) {
            this.mouseOverColor = new Color(255, 0, 0);
        }
        try {
            final String parameter7 = this.getParameter("refreshRate");
            if (!parameter7.equals("")) {
                this.refreshRate = Integer.parseInt(parameter7);
            }
            else {
                this.refreshRate = 30;
            }
        }
        catch (Exception ex8) {
            this.refreshRate = 30;
        }
        try {
            final String parameter8 = this.getParameter("numberOfHeadline");
            if (!parameter8.equals("")) {
                this.numOfHeadline = Integer.parseInt(parameter8);
            }
            else {
                this.numOfHeadline = 5;
            }
        }
        catch (Exception ex9) {
            this.numOfHeadline = 5;
        }
        try {
            final String parameter9 = this.getParameter("leftMargin");
            if (!parameter9.equals("")) {
                this.margin = Integer.parseInt(parameter9);
            }
            else {
                this.margin = 5;
            }
        }
        catch (Exception ex10) {
            this.margin = 5;
        }
        try {
            final String parameter10 = this.getParameter("scrollDelay");
            if (!parameter10.equals("")) {
                this.scrollDelay = Integer.parseInt(parameter10);
            }
            else {
                this.scrollDelay = 50;
            }
        }
        catch (Exception ex11) {
            this.scrollDelay = 50;
        }
        if (this.runable) {
            try {
                final URLConnection openConnection = new URL(this.getCodeBase(), this.fileName).openConnection();
                openConnection.setUseCaches(false);
                final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                String s = dataInputStream.readLine();
                for (int n = 0; s != null && n < this.numOfHeadline; array[n++] = s.trim(), s = dataInputStream.readLine()) {}
                dataInputStream.close();
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                this.runable = false;
            }
        }
        if (this.runable) {
            for (int i = 0; i < this.numOfHeadline; ++i) {
                final StringTokenizer stringTokenizer = new StringTokenizer(array[i], "|");
                try {
                    this.headLine[i] = stringTokenizer.nextToken().trim();
                }
                catch (Exception ex12) {
                    this.headLine[i] = "Error..";
                }
                try {
                    final String trim = stringTokenizer.nextToken().trim();
                    final int index7 = trim.indexOf(",");
                    final int index8 = trim.indexOf(",", index7 + 1);
                    final int int1 = Integer.parseInt(trim.substring(0, index7));
                    final int int2 = Integer.parseInt(trim.substring(index7 + 1, index8));
                    final int int3 = Integer.parseInt(trim.substring(index8 + 1, trim.length()));
                    if (this.defaultFgColor == null) {
                        this.fontColor[i] = new Color(int1, int2, int3);
                    }
                    else {
                        this.fontColor[i] = this.defaultFgColor;
                    }
                }
                catch (Exception ex13) {
                    this.fontColor[i] = new Color(255, 255, 255);
                }
                try {
                    this.fontSize[i] = Integer.parseInt(stringTokenizer.nextToken().trim().trim());
                }
                catch (Exception ex14) {
                    this.fontSize[i] = 12;
                }
                try {
                    final String trim2 = stringTokenizer.nextToken().trim();
                    int n2;
                    if (trim2.equalsIgnoreCase("Font.BOLD")) {
                        n2 = 1;
                    }
                    else if (trim2.equalsIgnoreCase("Font.ITALIC")) {
                        n2 = 2;
                    }
                    else if (trim2.equalsIgnoreCase("Font.BOLD+Font.ITALIC")) {
                        n2 = 3;
                    }
                    else if (trim2.equalsIgnoreCase("Font.ITALIC+Font.BOLD")) {
                        n2 = 3;
                    }
                    else {
                        n2 = 0;
                    }
                    this.fontStyle[i] = n2;
                }
                catch (Exception ex15) {
                    this.fontStyle[i] = 0;
                }
                try {
                    this.url[i] = new URL(stringTokenizer.nextToken().trim());
                }
                catch (Exception ex16) {
                    this.url[i] = null;
                }
                try {
                    this.frame[i] = stringTokenizer.nextToken().trim();
                }
                catch (Exception ex17) {
                    this.frame[i] = "_top";
                }
            }
        }
        try {
            this.font = new Font[this.maxHeadline];
            int height = this.size().height;
            for (int j = 0; j < this.numOfHeadline; ++j) {
                this.font[j] = new Font(this.stdFont, this.fontStyle[j], this.fontSize[j]);
                final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.font[j]);
                final int height2 = fontMetrics.getHeight();
                this.rect[j] = new Rectangle(this.margin, height, fontMetrics.stringWidth(this.headLine[j]), height2);
                height += height2;
                ++this.lastRect;
                this.calculateClipArea();
            }
        }
        catch (Exception ex18) {
            this.runable = false;
        }
    }
    
    public void calculateClipArea() {
        try {
            int height = 0;
            for (int i = this.header; i < this.lastRect; ++i) {
                height += this.rect[i].height;
            }
            this.drawingRect.y = this.size().height - height;
            this.drawingRect.height = height;
        }
        catch (Exception ex) {
            this.runable = false;
        }
    }
    
    public void drawHeadline(final Graphics graphics, final int n) {
        graphics.setFont(this.font[n]);
        if (this.rect[n].inside(this.mouseLoc.x, this.mouseLoc.y)) {
            if (this.url[n] != null) {
                this.osg.setColor(this.mouseOverColor);
                this.showStatus(this.url[n].toString());
            }
            else {
                this.showStatus("Free applets at www.consultcom.com");
                this.osg.setColor(this.fontColor[n]);
            }
        }
        else {
            this.osg.setColor(this.fontColor[n]);
            this.showStatus("Free applets at www.consultcom.com");
        }
        this.osg.drawString(this.headLine[n], this.rect[n].x, this.rect[n].y + this.fontSize[n]);
    }
    
    public void drawBackground(final Graphics graphics) {
        graphics.setColor(this.bgColor);
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        graphics.clipRect(this.drawingRect.x, this.drawingRect.y, this.drawingRect.width, this.drawingRect.height);
        for (int i = this.startMove; i < this.lastRect; ++i) {
            this.drawHeadline(graphics, i);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.drawBackground(this.osg);
        graphics.drawImage(HeadlinerDemo.osi, 0, 0, this);
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).setPriority(10);
            this.runner.start();
            (this.loadMe = new Thread(new HLoaderDemo(this.fileName, this.headLine, this.fontColor, this.fontSize, this.fontStyle, this.url, this.frame, this.numOfHeadline, this.header, this, this.lastRect, this.refreshRate, this.rect, this.maxHeadline, this.stdFont, this.defaultFgColor, this.font))).setPriority(1);
            this.loadMe.start();
        }
    }
    
    public void stop() {
        if (this.runner.isAlive()) {
            this.loadMe.stop();
            this.loadMe = null;
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void receive(final int changes) {
        this.changes = changes;
        this.updated = true;
    }
    
    public void checkLatestHeadline() {
        if (this.updated) {
            this.header += this.changes;
            this.lastRect += this.changes;
            this.updated = false;
            this.calculateClipArea();
        }
    }
    
    public void run() {
        while (this.runner.isAlive() && this.runable) {
            final int n = this.lastRect - 2 * (this.numOfHeadline + 1);
            this.startMove = ((n >= 0) ? n : false);
            if (this.rect[this.lastRect - 1].y + this.rect[this.lastRect - 1].height > this.size().height) {
                for (int i = this.startMove; i < this.lastRect; ++i) {
                    final Rectangle rectangle = this.rect[i];
                    --rectangle.y;
                }
            }
            this.repaint();
            this.cleanUp();
            this.checkLatestHeadline();
            try {
                Thread.sleep(this.scrollDelay);
            }
            catch (Exception ex) {}
        }
        if (!this.runable) {
            this.showStatus("Error opening file..");
            this.repaint();
        }
    }
    
    public void cleanUp() {
        for (int i = this.startMove - 1; i > this.startMove - 6; --i) {
            if (i < 0) {
                return;
            }
            this.headLine[i] = null;
            this.fontColor[i] = null;
            this.url[i] = null;
            this.frame[i] = null;
            this.rect[i] = null;
            this.font[i] = null;
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.mouseLoc = new Point(n, n2);
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseLoc = new Point(-1, -1);
        this.showStatus("Free applets at www.consultcom.com");
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            for (int i = this.header; i < this.lastRect; ++i) {
                if (this.rect[i].inside(n, n2) && this.url[i] != null) {
                    this.getAppletContext().showDocument(this.url[i], this.frame[i]);
                }
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public HeadlinerDemo() {
        this.runable = true;
        this.updated = false;
        this.painted = true;
    }
}
