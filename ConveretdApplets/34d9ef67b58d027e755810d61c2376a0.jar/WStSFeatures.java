import java.util.Random;
import java.awt.FontMetrics;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WStSFeatures extends Applet
{
    private int theScrollWidth;
    private appletScrollbar theScrollbar;
    private boolean scrollbarWasAdded;
    private int maxVisableItems;
    private int theTop;
    private int lastTitleLine;
    private int lastAuthorLine;
    private Vector theTextList;
    private URL destURL;
    private Image offImage;
    private Graphics offGraphics;
    private boolean allowUpdate;
    private Rectangle mySize;
    private String featureTitle;
    private String featureAuthor;
    private String featureText;
    private String featureLocation;
    
    public WStSFeatures() {
        this.theScrollWidth = 16;
        this.theScrollbar = null;
        this.scrollbarWasAdded = false;
        this.theTop = 0;
        this.lastTitleLine = 0;
        this.lastAuthorLine = 0;
        this.theTextList = new Vector();
        this.allowUpdate = false;
        this.mySize = null;
        this.featureTitle = new String();
        this.featureAuthor = new String();
        this.featureText = new String();
        this.featureLocation = new String();
    }
    
    @Override
    public void init() {
        this.setFont(new Font(this.getGraphics().getFont().getName(), this.getGraphics().getFont().getStyle(), 9));
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.maxVisableItems = this.size().height / this.getGraphics().getFontMetrics().getHeight();
    }
    
    @Override
    public void start() {
        this.mySize = new Rectangle(0, 0, this.size().width, this.size().height);
        this.chooseFeature(this.mySize.width, this.getGraphics().getFontMetrics());
        this.destURL = this.MyStringToURL(this.getCodeBase().toString() + this.featureLocation);
        this.wrapAndAddText(this.mySize.width, this.featureTitle, this.getGraphics().getFontMetrics());
        this.lastTitleLine = this.theTextList.size() - 1;
        this.wrapAndAddText(this.mySize.width, this.featureAuthor, this.getGraphics().getFontMetrics());
        this.lastAuthorLine = this.theTextList.size() - 1;
        this.theTextList.addElement("");
        this.wrapAndAddText(this.mySize.width, this.featureText, this.getGraphics().getFontMetrics());
        if (this.theTextList.size() > this.maxVisableItems) {
            this.theTextList.removeAllElements();
            final Rectangle mySize = this.mySize;
            mySize.width -= this.theScrollWidth;
            this.wrapAndAddText(this.mySize.width, this.featureTitle, this.getGraphics().getFontMetrics());
            this.lastTitleLine = this.theTextList.size() - 1;
            this.wrapAndAddText(this.mySize.width, this.featureAuthor, this.getGraphics().getFontMetrics());
            this.lastAuthorLine = this.theTextList.size() - 1;
            this.theTextList.addElement("");
            this.wrapAndAddText(this.mySize.width, this.featureText, this.getGraphics().getFontMetrics());
            this.theScrollbar = new appletScrollbar(this, this.size().width - this.theScrollWidth, this.mySize.y, this.getGraphics().getFontMetrics().getHeight() * this.maxVisableItems + 2, this.maxVisableItems, this.theTextList.size());
            this.scrollbarWasAdded = true;
        }
        this.featureTitle = null;
        this.featureAuthor = null;
        this.featureText = null;
        this.featureLocation = null;
        this.offImage = this.createImage(this.mySize.width, this.mySize.height);
        this.offGraphics = this.offImage.getGraphics();
        this.allowUpdate = true;
        this.repaint();
    }
    
    @Override
    public void stop() {
        this.theTextList.removeAllElements();
        if (this.scrollbarWasAdded) {
            this.scrollbarWasAdded = false;
            this.theScrollbar.stop();
            this.theScrollbar = null;
        }
        this.mySize = null;
        this.offImage = null;
        this.offGraphics = null;
        this.allowUpdate = false;
    }
    
    @Override
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    @Override
    public void update(final Graphics graphics) {
        int n = 0;
        final int n2 = 0;
        if (!this.allowUpdate || graphics == null || this.mySize == null || this.offImage == null || this.offGraphics == null) {
            return;
        }
        this.offGraphics.setColor(this.getBackground());
        this.offGraphics.fillRect(0, 0, this.mySize.width, this.mySize.height);
        this.offGraphics.drawRect(0, 0, this.mySize.width, this.mySize.height);
        this.offGraphics.setColor(this.getForeground());
        this.offGraphics.setFont(graphics.getFont());
        if (this.theScrollbar != null) {
            this.theTop = this.theScrollbar.getValue();
        }
        int i = this.theTop;
        int size = i + this.maxVisableItems;
        if (size > this.theTextList.size()) {
            size = this.theTextList.size();
        }
        while (i < size) {
            final String s = this.theTextList.elementAt(i);
            if (i <= this.lastTitleLine) {
                final int n3 = n + this.offGraphics.getFontMetrics().getAscent() + 1;
                final int n4 = n2 + this.mySize.width / 2 - this.offGraphics.getFontMetrics().stringWidth(s) / 2;
                this.offGraphics.setColor(Color.blue);
                this.offGraphics.drawLine(n4, n3, n4 + this.offGraphics.getFontMetrics().stringWidth(s), n3);
                this.offGraphics.drawString(s, n4, n + this.offGraphics.getFontMetrics().getAscent());
            }
            else if (i <= this.lastAuthorLine) {
                final int n5 = n2 + this.mySize.width / 2 - this.offGraphics.getFontMetrics().stringWidth(s) / 2;
                this.offGraphics.setColor(Color.black);
                this.offGraphics.drawString(s, n5, n + this.offGraphics.getFontMetrics().getAscent());
            }
            else {
                this.offGraphics.setColor(Color.black);
                this.offGraphics.drawString(s, n2, n + this.offGraphics.getFontMetrics().getAscent());
            }
            n += this.offGraphics.getFontMetrics().getHeight();
            ++i;
        }
        graphics.drawImage(this.offImage, 0, 0, this);
        if (this.theScrollbar != null) {
            this.theScrollbar.paint(graphics);
        }
    }
    
    @Override
    public boolean handleEvent(final Event event) {
        boolean b = false;
        if (this.theScrollbar != null) {
            b = this.theScrollbar.handleEvent(event);
        }
        if (!b && event.id == 502) {
            int n = 0;
            final int n2 = 0;
            if (this.theScrollbar != null) {
                this.theTop = this.theScrollbar.getValue();
            }
            int i = this.theTop;
            if (i <= this.lastTitleLine) {
                while (i <= this.lastTitleLine) {
                    final String s = this.theTextList.elementAt(i);
                    final int height = this.getGraphics().getFontMetrics().getHeight();
                    final int stringWidth = this.getGraphics().getFontMetrics().stringWidth(s);
                    if (new Rectangle(n2 + this.mySize.width / 2 - stringWidth / 2, n, stringWidth, height).inside(event.x, event.y)) {
                        this.showDocument(this.destURL);
                    }
                    n += height;
                    ++i;
                }
            }
            b = true;
        }
        if (!b) {
            b = super.handleEvent(event);
        }
        return b;
    }
    
    void showDocument(final URL url) {
        this.getAppletContext().showDocument(url);
    }
    
    URL MyStringToURL(final String s) {
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            url = null;
        }
        return url;
    }
    
    void chooseFeature(final int n, final FontMetrics fontMetrics) {
        final Random random = new Random();
        int i;
        do {
            i = random.nextInt();
        } while (i < 0);
        switch (i % 1 + 1) {
            case 1: {
                this.featureTitle = new String("A Spiritual Biography of Katie Stewart (1950-2004)");
                this.featureAuthor = new String("by Tom Stewart");
                this.featureText = new String("The greatest tribute that can be made to mortal man is not man's greatness, but \"What hath God wrought!\" (Numbers 23:23) through them. \"He that glorieth, let him glory in the LORD\" (1Corinthians 1:31). My beloved wife of thirty years, Katie Stewart, recently passed into the presence of the LORD without any warning, illness, or accident, when a blood vessel burst in her head. \"We are confident, I say, and willing rather to be absent from the body, and to be present with the LORD\" (2Corinthians 5:8). Everything that you read at WhatSaithTheScripture.com (WStS) prior to early June 2004 was placed online by Katie Stewart-- edited, promoted, and quite often written by her.");
                this.featureLocation = new String("../Home_Page/About_Us.html");
                break;
            }
        }
    }
    
    void wrapAndAddText(final int n, final String s, final FontMetrics fontMetrics) {
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        final StringBuffer sb3 = new StringBuffer();
        int n2 = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '\r') {
                if (n2 != 0) {
                    if (fontMetrics.stringWidth(sb.toString()) + fontMetrics.stringWidth(sb2.toString()) + fontMetrics.stringWidth(sb3.toString()) <= n) {
                        sb.append(sb2.toString());
                        sb.append(sb3.toString());
                        this.theTextList.addElement(sb.toString());
                        sb.setLength(0);
                    }
                    else {
                        this.theTextList.addElement(sb.toString());
                        sb.setLength(0);
                        sb.append(sb3.toString());
                    }
                }
                else {
                    this.theTextList.addElement(sb.toString());
                    sb.setLength(0);
                }
                sb2.setLength(0);
                sb3.setLength(0);
                n2 = 0;
            }
            else if (s.charAt(i) <= ' ') {
                if (n2 != 0) {
                    if (fontMetrics.stringWidth(sb.toString()) + fontMetrics.stringWidth(sb2.toString()) + fontMetrics.stringWidth(sb3.toString()) <= n) {
                        sb.append(sb2.toString());
                        sb.append(sb3.toString());
                    }
                    else {
                        this.theTextList.addElement(sb.toString());
                        sb.setLength(0);
                        sb.append(sb3.toString());
                    }
                    sb2.setLength(0);
                    sb3.setLength(0);
                    n2 = 0;
                }
                sb2.append(s.charAt(i));
            }
            else {
                sb3.append(s.charAt(i));
                n2 = 1;
            }
        }
        if (n2 != 0) {
            if (fontMetrics.stringWidth(sb.toString()) + fontMetrics.stringWidth(sb2.toString()) + fontMetrics.stringWidth(sb3.toString()) <= n) {
                sb.append(sb2.toString());
                sb.append(sb3.toString());
                this.theTextList.addElement(sb.toString());
            }
            else {
                this.theTextList.addElement(sb.toString());
                this.theTextList.addElement(sb3.toString());
            }
        }
        else {
            this.theTextList.addElement(sb.toString());
        }
    }
}
