import java.awt.FontMetrics;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Starfield extends Applet implements Runnable
{
    private String inputDisplayText;
    private URL inputClickLink;
    private int inputTextSpeed;
    private int inputStarSpeed;
    Thread threadAnimate;
    private Image imageOffScreen;
    private Graphics graphicsOffScreen;
    private Point[] backStarField;
    private Point[] midStarField;
    private Point[] frontStarField;
    private int nbrBackStars;
    private int nbrMidStars;
    private int nbrFrontStars;
    private DisplayString[] displayText;
    private int nbrLines;
    private Point textBlockLocation;
    private int textBlockWidth;
    private int textBlockHeight;
    
    public Starfield() {
        this.threadAnimate = new Thread(this);
    }
    
    public void stop() {
        if (this.threadAnimate != null) {
            this.threadAnimate.stop();
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("Starfield, © 1998 ShadoWare - http://www.shadoware.com");
        return false;
    }
    
    void sleep(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return false;
    }
    
    public final void paint(final Graphics graphics) {
        for (int i = 0; i < this.nbrBackStars; ++i) {
            plot(this.graphicsOffScreen, this.backStarField[i].getX(), this.backStarField[i].getY(), Color.gray);
        }
        for (int j = 0; j < this.nbrMidStars; ++j) {
            plot(this.graphicsOffScreen, this.midStarField[j].getX(), this.midStarField[j].getY(), Color.lightGray);
        }
        for (int k = 0; k < this.nbrFrontStars; ++k) {
            plot(this.graphicsOffScreen, this.frontStarField[k].getX(), this.frontStarField[k].getY(), Color.white);
        }
        for (int l = 0; l < this.nbrLines; ++l) {
            final float n = this.size().height / 4;
            float n2 = 1.0f;
            final int n3 = this.textBlockLocation.getY() + this.displayText[l].getFont().getSize() * l;
            if (n3 >= 0) {
                if (n3 <= this.size().height) {
                    if (n3 <= n) {
                        n2 = Math.abs(n3 / n);
                    }
                    else if (n3 >= this.size().height - n) {
                        n2 = Math.abs((this.size().height - n3) / n);
                    }
                    if (n2 > 1.0f) {
                        n2 = 1.0f;
                    }
                }
                else {
                    n2 = 0.0f;
                }
            }
            else {
                n2 = 0.0f;
            }
            this.graphicsOffScreen.setColor(new Color((int)(this.displayText[l].getColor().getRed() * n2), (int)(this.displayText[l].getColor().getGreen() * n2), (int)(this.displayText[l].getColor().getBlue() * n2)));
            this.graphicsOffScreen.setFont(this.displayText[l].getFont());
            this.graphicsOffScreen.drawString(this.displayText[l].getString(), this.textBlockLocation.getX(), n3);
        }
        graphics.drawImage(this.imageOffScreen, 0, 0, this);
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "Display Text", "String", "The text to scroll over the stars" }, { "TextSpeed", "int", "The speed to scroll the text. (10 is very fast)" }, { "StarSpeed", "int", "The speed to move the stars (20 is very fast)" }, { "NbrStars", "int", "The number of stars to display (You probably don't want more than 70)" }, { "ClickLink", "URL", "The URL to link to when the applet is clicked." } };
    }
    
    public final void update(final Graphics graphics) {
        this.graphicsOffScreen.setColor(Color.black);
        this.graphicsOffScreen.fillRect(0, 0, this.size().width, this.size().height);
        for (int i = 0; i < this.nbrBackStars; ++i) {
            this.backStarField[i].moveRight(this.inputStarSpeed / 2);
        }
        for (int j = 0; j < this.nbrMidStars; ++j) {
            this.midStarField[j].moveRight(this.inputStarSpeed * 3 / 4);
        }
        for (int k = 0; k < this.nbrFrontStars; ++k) {
            this.frontStarField[k].moveRight(this.inputStarSpeed);
        }
        if (this.textBlockLocation.getY() < -(this.textBlockHeight + 10)) {
            this.textBlockLocation.setY(this.size().height + this.displayText[0].getFont().getSize() + 10);
        }
        else {
            this.textBlockLocation.setY(this.textBlockLocation.getY() - this.inputTextSpeed);
        }
        this.paint(graphics);
    }
    
    public void start() {
        if (this.threadAnimate != null) {
            this.threadAnimate.start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: Starfield\n" + "Copyright: © 1998 ShadoWare\n" + "Version: 1.0\n" + "Author: Christopher Karper (ShadoWare)\n" + "Original URL: http://www.shadoware.com\n" + "Usage: See getParameterInfo() for the \n" + "       parameters.  You'll need Starfield.class\n" + "       as well as Point.class, and DisplayString.class\n" + "       to get this applet to work." + "Contact: Send any questions, comments, or *gasp*\n" + "         bugs to support@shadoware.com\n" + "Notes: This was inspired by an applet I saw on\n" + "       Virgin Interactive's web site for SubSpace.\n" + "       I wrote a nice e-mail asking them if I could\n" + "       use it, and they said \"No, write your own!\",\n" + "       and so I have.  You may use this any time you\n" + "       wish, but there will be no decompiling, reverse\n" + "       engineering, modifying, or anything similar.\n" + "       Don't like the rules? Don't use it... =P\n" + "       And send me an e-mail at karperc@shadoware.com\n" + "       if you use this on a page.\n";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.inputClickLink != null) {
            this.getAppletContext().showDocument(this.inputClickLink);
        }
        return true;
    }
    
    private static final void plot(final Graphics graphics, final int n, final int n2, final Color color) {
        graphics.setColor(color);
        graphics.drawLine(n, n2, n, n2);
    }
    
    public void run() {
        while (true) {
            this.repaint();
            this.sleep(50);
        }
    }
    
    public void init() {
        try {
            this.inputDisplayText = this.getParameter("DisplayText");
        }
        catch (Exception ex) {
            this.inputDisplayText = "";
        }
        try {
            this.inputTextSpeed = Integer.valueOf(this.getParameter("TextSpeed"));
        }
        catch (Exception ex2) {
            this.inputTextSpeed = 3;
        }
        try {
            this.inputStarSpeed = Integer.valueOf(this.getParameter("StarSpeed"));
        }
        catch (Exception ex3) {
            this.inputStarSpeed = 7;
        }
        try {
            final int intValue = Integer.valueOf(this.getParameter("NbrStars"));
            this.nbrFrontStars = intValue / 7;
            this.nbrMidStars = intValue / 7 * 2;
            this.nbrBackStars = intValue / 7 * 4;
        }
        catch (Exception ex4) {
            this.nbrFrontStars = 10;
            this.nbrMidStars = 20;
            this.nbrBackStars = 40;
        }
        try {
            this.inputClickLink = new URL(this.getParameter("ClickLink"));
        }
        catch (Exception ex5) {
            this.inputClickLink = null;
        }
        this.imageOffScreen = this.createImage(this.size().width, this.size().height);
        (this.graphicsOffScreen = this.imageOffScreen.getGraphics()).setColor(Color.black);
        this.graphicsOffScreen.fillRect(0, 0, this.size().width, this.size().height);
        this.backStarField = new Point[this.nbrBackStars];
        this.midStarField = new Point[this.nbrMidStars];
        this.frontStarField = new Point[this.nbrFrontStars];
        for (int i = 0; i < this.nbrBackStars; ++i) {
            (this.backStarField[i] = new Point()).randomize(this.size().width, this.size().height);
        }
        for (int j = 0; j < this.nbrMidStars; ++j) {
            (this.midStarField[j] = new Point()).randomize(this.size().width, this.size().height);
        }
        for (int k = 0; k < this.nbrFrontStars; ++k) {
            (this.frontStarField[k] = new Point()).randomize(this.size().width, this.size().height);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.inputDisplayText, "|");
        this.nbrLines = stringTokenizer.countTokens();
        this.displayText = new DisplayString[this.nbrLines];
        for (int l = 0; l < this.nbrLines; ++l) {
            Font font = new Font("Arial", 0, 12);
            Color color = new Color(255, 255, 255);
            String s = stringTokenizer.nextToken().trim();
            if (s.indexOf("{font:") != -1) {
                font = new Font(s.substring(s.indexOf("{font:") + 6, s.indexOf("}", s.indexOf("{font:"))).trim(), font.getStyle(), font.getSize());
                s = s.substring(0, s.indexOf("{font:")) + s.substring(s.indexOf("}", s.indexOf("{font:")) + 1);
            }
            if (s.indexOf("{font-style:") != -1) {
                final String substring = s.substring(s.indexOf("{font-style:") + 12, s.indexOf("}", s.indexOf("{font-style:")));
                if (substring.indexOf("plain") != -1) {
                    font = new Font(font.getName(), 0, font.getSize());
                }
                if (substring.indexOf("bold") != -1) {
                    font = new Font(font.getName(), font.getStyle() + 1, font.getSize());
                }
                if (substring.indexOf("italic") != -1) {
                    font = new Font(font.getName(), font.getStyle() + 2, font.getSize());
                }
                s = s.substring(0, s.indexOf("{font-style:")) + s.substring(s.indexOf("}", s.indexOf("{font-style:")) + 1);
            }
            if (s.indexOf("{font-color:") != -1) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(s.substring(s.indexOf("{font-color:") + 12, s.indexOf("}", s.indexOf("{font-color:"))).trim(), ",");
                color = new Color(Integer.valueOf(stringTokenizer2.nextToken().trim()), Integer.valueOf(stringTokenizer2.nextToken().trim()), Integer.valueOf(stringTokenizer2.nextToken().trim()));
                s = s.substring(0, s.indexOf("{font-color:")) + s.substring(s.indexOf("}", s.indexOf("{font-color:")) + 1);
            }
            final String s2 = s;
            this.displayText[l] = new DisplayString(s2, font, color);
            final FontMetrics fontMetrics = this.graphicsOffScreen.getFontMetrics(font);
            this.textBlockHeight += fontMetrics.getHeight();
            if (fontMetrics.stringWidth(s2) > this.textBlockWidth) {
                this.textBlockWidth = fontMetrics.stringWidth(s2);
            }
        }
        this.textBlockLocation = new Point(this.size().width / 2 - this.textBlockWidth / 2, this.size().height + 10);
    }
}
