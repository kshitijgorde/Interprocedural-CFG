import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.Frame;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageZoom2 extends Applet
{
    Thread clock;
    Image pic;
    Image theCanvas;
    int aHeight;
    int aWidth;
    int iWidth;
    int iHeight;
    float cWidth;
    float cHeight;
    float oWidth;
    float oHeight;
    int mouseX;
    int mouseY;
    int xpos;
    int ypos;
    int oXPos;
    int oYPos;
    int maxWidth;
    int maxHeight;
    int minWidth;
    int minHeight;
    int messCount;
    float max;
    float min;
    int speed;
    int xMove;
    int yMove;
    int bgColor;
    int mColor;
    Color bgc;
    Color mC;
    boolean loading;
    boolean called;
    boolean mDown;
    boolean shiftDown;
    int counter;
    float radio;
    String startUp;
    String messZoomOut;
    String messLoading1;
    Thread bgLoading;
    backgroundLoading bgL;
    MediaTracker MTracker;
    
    public ImageZoom2() {
        this.clock = null;
        this.messCount = 0;
        this.max = 7.0f;
        this.min = 0.2f;
        this.speed = 6;
        this.xMove = 0;
        this.yMove = 0;
        this.bgColor = 16777215;
        this.mColor = 255;
        this.loading = true;
        this.called = false;
        this.counter = 0;
        this.startUp = "a";
        this.messZoomOut = "Reduce: Shift-Click";
        this.messLoading1 = "Please wait while image is loading...";
    }
    
    public void wakeUp() {
        if (this.mDown) {
            ++this.counter;
            if (this.counter > 5) {
                if (this.shiftDown) {
                    this.radio = 0.97f;
                }
                else {
                    this.radio = 1.03f;
                }
                this.changeSize(this.radio);
            }
            this.repaint();
        }
        if (this.yMove != 0 || this.xMove != 0) {
            this.repaint();
        }
    }
    
    public void init() {
        String tempS;
        if ((tempS = this.getParameter("bgColor")) != null) {
            this.bgColor = Integer.valueOf(tempS, 16);
        }
        if ((tempS = this.getParameter("messageColor")) != null) {
            this.mColor = Integer.valueOf(tempS, 16);
        }
        this.mC = new Color(this.mColor);
        this.setBackground(this.bgc = new Color(this.bgColor));
        this.aWidth = this.size().width;
        this.aHeight = this.size().height;
        this.theCanvas = this.createImage(this.aWidth, this.aHeight);
        final String picURL;
        if ((picURL = this.getParameter("IMAGE")) == null) {
            this.showStatus("Image File not definded");
            System.exit(1);
        }
        if ((tempS = this.getParameter("MaxPercent")) != null) {
            this.max = Integer.parseInt(tempS) / 100;
            if (this.max < 1) {
                this.max = 7.0f;
            }
        }
        if ((tempS = this.getParameter("MinPercent")) != null) {
            this.min = Integer.parseInt(tempS) / 100;
            if (this.min > 1) {
                this.min = 0.2f;
            }
        }
        if ((tempS = this.getParameter("PanSpeed")) != null) {
            this.speed = Integer.parseInt(tempS);
            if (this.speed < 1 || this.speed > 10) {
                this.speed = 6;
            }
        }
        if ((tempS = this.getParameter("StartUp")) != null) {
            this.startUp = tempS.toLowerCase();
        }
        if ((tempS = this.getParameter("messZoomOut")) != null) {
            this.messZoomOut = tempS;
        }
        Object theFrame;
        for (theFrame = null, theFrame = this.getParent(); !(theFrame instanceof Frame) && theFrame != null; theFrame = ((Component)theFrame).getParent()) {}
        if (theFrame != null) {
            ((Frame)theFrame).setCursor(12);
        }
        try {
            this.pic = (picURL.toLowerCase().startsWith("http") ? this.getImage(new URL(picURL)) : this.getImage(this.getDocumentBase(), picURL));
        }
        catch (MalformedURLException e) {
            System.out.println("Check the URL of the Image");
            this.showStatus("Check the URL of the Image");
        }
        (this.MTracker = new MediaTracker(this)).addImage(this.pic, 1);
        this.repaint();
    }
    
    public void init2() {
        this.iWidth = this.pic.getWidth(this);
        this.iHeight = this.pic.getHeight(this);
        this.maxWidth = (int)(this.aWidth * this.max);
        this.maxHeight = this.iHeight / this.iWidth * this.maxWidth;
        this.minWidth = (int)(this.aWidth * this.min);
        this.minHeight = this.iHeight / this.iWidth * this.minWidth;
        this.keyAction(this.startUp);
        if (this.clock == null) {
            (this.clock = new Thread(new notice(this), "clock")).start();
            this.requestFocus();
        }
        this.repaint();
    }
    
    public boolean keyDown(final Event e, final int key) {
        this.keyAction(String.valueOf((char)key));
        return true;
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        this.mDown = true;
        this.mouseX = x;
        this.mouseY = y;
        if (e.shiftDown()) {
            this.shiftDown = true;
        }
        else {
            this.shiftDown = false;
        }
        return true;
    }
    
    public boolean mouseUp(final Event e, final int x, final int y) {
        this.mDown = false;
        if (this.counter < 5) {
            if (e.shiftDown()) {
                this.radio = 0.5f;
            }
            else {
                this.radio = 2.0f;
            }
            this.changeSize(this.radio);
            if (this.cWidth < this.aWidth) {
                this.xpos = (int)(this.aWidth - this.cWidth) / 2;
            }
            else if (this.xpos > 0 && this.cWidth > this.aWidth) {
                this.xpos = 0;
            }
            if (this.cHeight < this.aHeight) {
                this.ypos = (int)(this.aHeight - this.cHeight) / 2;
            }
            else if (this.ypos > 0 && this.cHeight > this.aHeight) {
                this.ypos = 0;
            }
            this.repaint();
        }
        this.counter = 0;
        return true;
    }
    
    public boolean mouseExit(final Event e, final int x, final int y) {
        this.xMove = 0;
        this.yMove = 0;
        return true;
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        if (!this.mDown) {
            if (evt.x > 4 * this.aWidth / 5) {
                this.xMove = -this.aWidth / ((11 - this.speed) * 10);
            }
            else if (evt.x < this.aWidth / 5) {
                this.xMove = this.aWidth / ((11 - this.speed) * 10);
            }
            else {
                this.xMove = 0;
            }
            if (evt.y > 4 * this.aHeight / 5) {
                this.yMove = -this.aHeight / ((11 - this.speed) * 10);
            }
            else if (evt.y < this.aHeight / 5) {
                this.yMove = this.aHeight / ((11 - this.speed) * 10);
            }
            else {
                this.yMove = 0;
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event e, final int x, final int y) {
        this.requestFocus();
        return true;
    }
    
    public boolean lostFocus(final Event e, final Object o) {
        try {
            if (this.clock != null) {
                this.clock.suspend();
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public boolean gotFocus(final Event e, final Object o) {
        if (this.clock != null) {
            try {
                this.clock.resume();
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public void update(final Graphics gg) {
        final Graphics g = this.theCanvas.getGraphics();
        g.setColor(this.bgc);
        g.fillRect(0, 0, this.aWidth, this.aHeight);
        if (this.loading || this.pic == null || this.iHeight == 0) {
            if (!this.called) {
                this.called = true;
                (this.bgLoading = new Thread(new backgroundLoading(this, this.MTracker), "bgLoading")).start();
            }
            if (!this.loading && this.iHeight == 0) {
                this.init2();
            }
            g.setColor(this.mC);
            final Font f = new Font("Helvetica", 0, 11);
            final FontMetrics fm = g.getFontMetrics(f);
            g.setFont(f);
            final int xLoc = (this.aWidth - fm.stringWidth(this.messLoading1)) / 2;
            final int yLoc = this.aHeight / 5;
            g.drawString(this.messLoading1, xLoc, yLoc);
        }
        else {
            if ((this.xMove != 0 || this.yMove != 0) && !this.mDown) {
                this.setXPos();
                this.setYPos();
            }
            g.drawImage(this.pic, this.xpos, this.ypos, (int)this.cWidth, (int)this.cHeight, this);
            if (this.messCount > 0) {
                g.setColor(new Color(this.mColor));
                g.setFont(new Font("TimesRoman", 1, 16));
                --this.messCount;
                if (this.cWidth >= this.maxWidth) {
                    g.drawString(this.messZoomOut, 10, 20);
                }
            }
            g.setColor(Color.blue);
            g.setFont(new Font("TimesRoman", 2, 14));
            g.drawString("www.vivaorange.com", this.size().width - 130, this.size().height - 9);
        }
        g.dispose();
        this.paint(gg);
    }
    
    public String getAppletInfo() {
        return "Name: ImageZoom v2.0\r\nCreated By: Orange Idea Corp.\r\nWeb Site: http://www.vivaorange.com";
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.theCanvas, 0, 0, this);
    }
    
    protected void keyAction(String k) {
        k = k.toLowerCase();
        if (k.equals("a")) {
            this.cWidth = this.aWidth;
            this.cHeight = this.aWidth / this.iWidth * this.iHeight;
            this.xpos = 0;
            this.ypos = (int)(this.aHeight - this.cHeight) / 2;
            if (this.cHeight > this.aHeight) {
                this.cHeight = this.aHeight;
                this.cWidth = this.aHeight / this.iHeight * this.iWidth;
                this.xpos = (int)(this.aWidth - this.cWidth) / 2;
                this.ypos = 0;
            }
        }
        else if (k.equals("w")) {
            this.cHeight = this.iHeight / this.iWidth * this.aWidth;
            this.cWidth = this.aWidth;
            this.xpos = 0;
            this.ypos = (int)(this.aHeight - this.cHeight) / 2;
        }
        else if (k.equals("h")) {
            this.cHeight = this.aHeight;
            this.cWidth = this.iWidth / this.iHeight * this.aHeight;
            this.xpos = (int)(this.aWidth - this.cWidth) / 2;
            this.ypos = 0;
        }
        else if (k.equals("f")) {
            this.cHeight = this.iHeight;
            this.cWidth = this.iWidth;
            this.xpos = (int)(this.aWidth - this.cWidth) / 2;
            this.ypos = (int)(this.aHeight - this.cHeight) / 2;
        }
        else if (k.equals("c")) {
            this.xpos = (int)(this.aWidth - this.cWidth) / 2;
            this.ypos = (int)(this.aHeight - this.cHeight) / 2;
        }
        else {
            try {
                float q = Integer.parseInt(k);
                if (q > 9) {
                    if (q < 0) {
                        q = 100.0f;
                    }
                    q /= 100;
                    this.cWidth = this.aWidth * q;
                    this.cHeight = this.iHeight / this.iWidth * this.cWidth;
                    this.xpos = (int)(this.aWidth - this.cWidth) / 2;
                    this.ypos = (int)(this.aHeight - this.cHeight) / 2;
                }
            }
            catch (NumberFormatException ex) {}
        }
        this.repaint();
    }
    
    protected int coordinate(final float oL, final float nL, final int p, final int ppos) {
        final int nP = p - (int)(nL / oL * (p - ppos));
        return nP;
    }
    
    protected void changeSize(final float factor) {
        if (this.counter < 7) {
            this.oWidth = this.cWidth;
            this.oHeight = this.cHeight;
            this.oXPos = this.xpos;
            this.oYPos = this.ypos;
        }
        this.cWidth *= factor;
        this.cHeight *= factor;
        if (this.cWidth > this.maxWidth) {
            this.cWidth = this.maxWidth;
            this.cHeight = this.maxHeight;
            this.messCount = 30;
        }
        else if (this.cWidth < this.minWidth) {
            this.cWidth = this.minWidth;
            this.cHeight = this.minHeight;
        }
        this.xpos = this.coordinate(this.oWidth, this.cWidth, this.mouseX, this.oXPos);
        this.ypos = this.coordinate(this.oHeight, this.cHeight, this.mouseY, this.oYPos);
    }
    
    public void setXPos() {
        if ((this.xpos < 0 && this.xMove > 0) || (this.xpos + this.cWidth > this.aWidth && this.xMove < 0)) {
            this.xpos += this.xMove;
            if (this.xpos > 0) {
                if (this.cWidth < this.aWidth) {
                    this.xpos = (int)(this.aWidth - this.cWidth) / 2;
                    this.ypos = (int)(this.aHeight - this.cHeight) / 2;
                }
                else {
                    this.xpos = 0;
                }
            }
            else if (this.xpos < this.aWidth - this.cWidth) {
                this.xpos = (int)(this.aWidth - this.cWidth);
            }
        }
    }
    
    public void setYPos() {
        if ((this.ypos < 0 && this.yMove > 0) || (this.ypos + this.cHeight > this.aHeight && this.yMove < 0)) {
            this.ypos += this.yMove;
            if (this.ypos > 0) {
                if (this.cHeight < this.aHeight) {
                    this.xpos = (int)(this.aWidth - this.cWidth) / 2;
                    this.ypos = (int)(this.aHeight - this.cHeight) / 2;
                }
                else {
                    this.ypos = 0;
                }
            }
            else if (this.ypos < this.aHeight - this.cHeight) {
                this.ypos = (int)(this.aHeight - this.cHeight);
            }
        }
    }
    
    public void setImage(final String im) {
        this.pic = null;
        this.loading = true;
        this.iHeight = 0;
        this.repaint();
        int error = 0;
        try {
            this.pic = (im.toLowerCase().startsWith("http") ? this.getImage(new URL(im)) : this.getImage(this.getDocumentBase(), im));
        }
        catch (MalformedURLException e) {
            this.showStatus("Fail to set Image: Image not found or wrong format");
            error = 1;
        }
        if (error != 1) {
            final MediaTracker MTracker = new MediaTracker(this);
            MTracker.addImage(this.pic, 1);
            (this.bgLoading = new Thread(new backgroundLoading(this, MTracker), "bgLoading")).start();
        }
    }
    
    public void setSize(final String g) {
        this.keyAction(g);
    }
}
