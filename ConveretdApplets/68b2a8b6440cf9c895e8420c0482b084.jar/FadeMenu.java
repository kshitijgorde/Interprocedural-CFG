import java.awt.image.ImageObserver;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Point;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FadeMenu extends Applet implements Runnable
{
    int currentFontSize;
    int minFontSize;
    int maxFontSize;
    int framePause;
    int wordPause;
    int currentStep;
    int currentParam;
    boolean fontGrowing;
    Boolean randCoords;
    Boolean randKeywords;
    String fontFace;
    Font mainFont;
    FontMetrics mainFontMetrics;
    Color fontColor1;
    Color fontColor2;
    Color mouseOverColor1;
    Color mouseOverColor2;
    Color mouseDownColor1;
    Color mouseDownColor2;
    Color bgColor;
    Color currentColor;
    Gradient gradient;
    Point textCenter;
    Vector keyword;
    Vector target;
    Vector url;
    Rectangle textRect;
    Random randNum;
    Image offScreenImg;
    Graphics offScreenG;
    Thread runner;
    
    public void init() {
        this.currentStep = 0;
        this.fontFace = this.getParameter("fontFace");
        this.randCoords = new Boolean(this.getParameter("randCoords"));
        this.randKeywords = new Boolean(this.getParameter("randKeywords"));
        this.framePause = Integer.parseInt(this.getParameter("framePause"));
        this.wordPause = Integer.parseInt(this.getParameter("wordPause"));
        this.fontColor1 = new Color(Integer.parseInt(this.getParameter("fontColor1Red")), Integer.parseInt(this.getParameter("fontColor1Green")), Integer.parseInt(this.getParameter("fontColor1Blue")));
        this.fontColor2 = new Color(Integer.parseInt(this.getParameter("fontColor2Red")), Integer.parseInt(this.getParameter("fontColor2Green")), Integer.parseInt(this.getParameter("fontColor2Blue")));
        this.mouseOverColor1 = new Color(Integer.parseInt(this.getParameter("mouseOverColor1Red")), Integer.parseInt(this.getParameter("mouseOverColor1Green")), Integer.parseInt(this.getParameter("mouseOverColor1Blue")));
        this.mouseOverColor2 = new Color(Integer.parseInt(this.getParameter("mouseOverColor2Red")), Integer.parseInt(this.getParameter("mouseOverColor2Green")), Integer.parseInt(this.getParameter("mouseOverColor2Blue")));
        this.mouseDownColor1 = new Color(Integer.parseInt(this.getParameter("mouseDownColor1Red")), Integer.parseInt(this.getParameter("mouseDownColor1Green")), Integer.parseInt(this.getParameter("mouseDownColor1Blue")));
        this.mouseDownColor2 = new Color(Integer.parseInt(this.getParameter("mouseDownColor2Red")), Integer.parseInt(this.getParameter("mouseDownColor2Green")), Integer.parseInt(this.getParameter("mouseDownColor2Blue")));
        this.bgColor = new Color(Integer.parseInt(this.getParameter("bgRed")), Integer.parseInt(this.getParameter("bgGreen")), Integer.parseInt(this.getParameter("bgBlue")));
        this.minFontSize = Integer.parseInt(this.getParameter("minFontSize"));
        this.maxFontSize = Integer.parseInt(this.getParameter("maxFontSize"));
        this.currentFontSize = this.minFontSize;
        this.mainFont = new Font(this.fontFace, 0, this.currentFontSize);
        this.mainFontMetrics = this.getFontMetrics(this.mainFont);
        this.keyword = this.autoInitParam("keyword", "String");
        this.target = this.autoInitParam("target", "String");
        this.url = this.autoInitParam("url", "URL");
        this.gradient = new Gradient(this.fontColor1, this.fontColor2, this.maxFontSize - this.minFontSize + 1);
        this.setCoords();
        this.offScreenImg = this.createImage(this.size().width, this.size().height);
        this.offScreenG = this.offScreenImg.getGraphics();
    }
    
    public Vector autoInitParam(final String s, final String s2) {
        int n = 1;
        final Vector<URL> vector = new Vector<URL>();
        if (s2.equals("URL")) {
            while (this.getParameter(String.valueOf(s) + Integer.toString(n)) != null) {
                try {
                    vector.addElement(new URL(this.getParameter(String.valueOf(s) + Integer.toString(n))));
                    System.out.println(String.valueOf(s) + n + ": " + this.getParameter(String.valueOf(s) + Integer.toString(n)));
                }
                catch (MalformedURLException ex) {
                    if (this.getParameter(String.valueOf(s) + Integer.toString(n)).equals("")) {
                        vector.addElement((URL)this.getParameter(String.valueOf(s) + Integer.toString(n)));
                        System.out.println(String.valueOf(s) + n + ": " + this.getParameter(String.valueOf(s) + Integer.toString(n)));
                    }
                    else {
                        System.out.println("Bad URL: " + this.getParameter(String.valueOf(s) + Integer.toString(n)));
                    }
                }
                ++n;
            }
        }
        else if (s2.equals("String")) {
            while (this.getParameter(String.valueOf(s) + Integer.toString(n)) != null) {
                vector.addElement((URL)this.getParameter(String.valueOf(s) + Integer.toString(n)));
                System.out.println(String.valueOf(s) + n + ": " + this.getParameter(String.valueOf(s) + Integer.toString(n)));
                ++n;
            }
        }
        return vector;
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
            this.showStatus(this.getAppletInfo());
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
        }
        this.runner = null;
    }
    
    public void setTextRect() {
        this.mainFontMetrics = this.getFontMetrics(this.mainFont);
        this.textRect.reshape(this.textCenter.x - this.mainFontMetrics.stringWidth(this.keyword.elementAt(this.currentParam)) / 2, this.textCenter.y - (this.mainFontMetrics.getAscent() + this.mainFontMetrics.getDescent()) / 2, this.mainFontMetrics.stringWidth(this.keyword.elementAt(this.currentParam)), this.mainFontMetrics.getHeight());
    }
    
    public void getNextKeyword() {
        if (this.randKeywords) {
            this.currentParam = (int)Math.floor(this.randNum.nextFloat() * this.keyword.size());
            return;
        }
        if (!this.randKeywords) {
            if (this.currentParam == this.keyword.size() - 1) {
                this.currentParam = 0;
                return;
            }
            ++this.currentParam;
        }
    }
    
    public void setCoords() {
        final FontMetrics fontMetrics = this.getFontMetrics(new Font(this.fontFace, 0, this.maxFontSize));
        int n;
        int n2;
        if (this.randCoords) {
            for (n = (int)Math.floor(this.randNum.nextFloat() * this.size().width), n2 = (int)Math.floor(this.randNum.nextFloat() * this.size().height); n < fontMetrics.stringWidth(this.keyword.elementAt(this.currentParam)) / 2 || n > this.size().width - fontMetrics.stringWidth(this.keyword.elementAt(this.currentParam)) / 2 || n2 < (fontMetrics.getAscent() + fontMetrics.getDescent()) / 2 || n2 > this.size().height - (fontMetrics.getAscent() + fontMetrics.getDescent()) / 2; n = (int)Math.floor(this.randNum.nextFloat() * this.size().width), n2 = (int)Math.floor(this.randNum.nextFloat() * this.size().height)) {}
        }
        else {
            n = this.size().width / 2;
            n2 = this.size().height / 2;
        }
        this.textCenter.move(n, n2);
    }
    
    public void grow() {
        ++this.currentStep;
        this.currentColor = this.gradient.getColor(this.currentStep);
        ++this.currentFontSize;
        this.mainFont = new Font(this.fontFace, 0, this.currentFontSize);
        this.fontGrowing = true;
    }
    
    public void shrink() {
        --this.currentStep;
        this.currentColor = this.gradient.getColor(this.currentStep);
        --this.currentFontSize;
        this.mainFont = new Font(this.fontFace, 0, this.currentFontSize);
        this.fontGrowing = false;
    }
    
    public void reset() {
        try {
            this.currentStep = 0;
            this.currentColor = this.bgColor;
            this.gradient.changeColors(this.fontColor1, this.fontColor2);
            this.showStatus("");
            this.update(this.getGraphics());
            this.getNextKeyword();
            this.setCoords();
            Thread.sleep(this.wordPause);
        }
        catch (InterruptedException ex) {
            System.out.println("Oops. Error: " + ex.getMessage());
        }
    }
    
    public void run() {
        try {
            while (true) {
                if (this.currentStep < this.gradient.getSteps() - 1 && this.currentStep >= 0 && this.fontGrowing) {
                    this.grow();
                }
                else if (this.currentStep < this.gradient.getSteps() - 1 && this.currentStep > 0 && !this.fontGrowing) {
                    this.shrink();
                }
                else if (this.currentStep == 0 && !this.fontGrowing) {
                    this.reset();
                    this.grow();
                }
                else if (this.currentStep == this.gradient.getSteps() - 1) {
                    this.shrink();
                }
                else {
                    this.reset();
                    this.grow();
                }
                this.setTextRect();
                this.repaint();
                Thread.sleep(this.framePause);
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Oops. Error: " + ex.getMessage());
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.textRect.inside(n, n2) && !this.url.elementAt(this.currentParam).equals("")) {
            this.gradient.changeColors(this.mouseOverColor1, this.mouseOverColor2);
            this.update(this.getGraphics());
            this.showStatus(this.url.elementAt(this.currentParam).toString());
            return true;
        }
        if (!this.textRect.inside(n, n2)) {
            this.gradient.changeColors(this.fontColor1, this.fontColor2);
            this.showStatus("");
            return true;
        }
        return false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.textRect.inside(n, n2) && !this.url.elementAt(this.currentParam).equals("")) {
            this.gradient.changeColors(this.mouseDownColor1, this.mouseDownColor2);
            this.update(this.getGraphics());
            return true;
        }
        if (!this.textRect.inside(n, n2)) {
            this.gradient.changeColors(this.fontColor1, this.fontColor2);
            return true;
        }
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.textRect.inside(n, n2)) {
            if (!this.url.elementAt(this.currentParam).equals("") && !this.target.elementAt(this.currentParam).equals("")) {
                this.getAppletContext().showDocument(this.url.elementAt(this.currentParam), this.target.elementAt(this.currentParam));
                System.out.println("Targetting new frame or window.");
            }
            else if (!this.url.elementAt(this.currentParam).equals("")) {
                this.getAppletContext().showDocument(this.url.elementAt(this.currentParam));
                System.out.println("Getting new page in same window.");
            }
            return true;
        }
        return false;
    }
    
    public void update(final Graphics graphics) {
        graphics.clipRect(this.textRect.x - 20, this.textRect.y - 20, this.textRect.width + 40, this.textRect.height + 40);
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.offScreenG.setColor(this.bgColor);
        this.offScreenG.fillRect(0, 0, this.size().width, this.size().height);
        this.offScreenG.setColor(this.currentColor);
        this.offScreenG.setFont(this.mainFont);
        this.offScreenG.drawString(this.keyword.elementAt(this.currentParam), this.textRect.x, this.textCenter.y + (this.mainFontMetrics.getAscent() - (this.mainFontMetrics.getAscent() + this.mainFontMetrics.getDescent()) / 2));
        graphics.drawImage(this.offScreenImg, 0, 0, this);
    }
    
    public FadeMenu() {
        this.fontGrowing = true;
        this.textCenter = new Point(0, 0);
        this.keyword = new Vector();
        this.target = new Vector();
        this.url = new Vector();
        this.textRect = new Rectangle();
        this.randNum = new Random();
    }
}
