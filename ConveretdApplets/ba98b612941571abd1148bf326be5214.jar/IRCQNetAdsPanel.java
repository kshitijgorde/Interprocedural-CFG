import java.awt.Event;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetAdsPanel extends Panel implements ImageObserver
{
    private IRCQNet theApp;
    private int Mode;
    private Image Image1;
    private Image Image2;
    private int width;
    private int height;
    private IRCQNetCookieImage CookieImage;
    private IRCQNetCookieLocation CookieLocation;
    private String Cookie;
    private String adLocation;
    private String adLinkLocation;
    private boolean mouseWasDown;
    private String magicNum;
    private String defaultMagic;
    private String mLable;
    
    IRCQNetAdsPanel(final IRCQNet theApp, final int mode, final String mLable) {
        this.Mode = 1;
        this.mouseWasDown = false;
        this.defaultMagic = "44000113";
        this.theApp = theApp;
        this.Mode = mode;
        this.mLable = mLable;
        final String magic = theApp.getParams().getMagic(this.mLable);
        this.magicNum = magic;
        if (magic == null) {
            this.magicNum = this.defaultMagic;
        }
        this.setBackground(IRCQNetColors.controlColor);
        (this.CookieImage = new IRCQNetCookieImage(theApp.MPanel.getParams().adsServer, theApp.MPanel.getParams().getAdsPort(), this.magicNum)).start();
        this.setLayout(null);
        this.add(this.CookieImage);
        try {
            theApp.getToolkit().prepareImage(this.Image1 = theApp.getImage(theApp.getCodeBase(), "icons/logo.gif"), 232, 60, this);
        }
        catch (NullPointerException ex) {}
    }
    
    private void newImage() {
        try {
            this.theApp.getToolkit().prepareImage(this.Image1 = this.theApp.getImage(new URL("http", this.theApp.MPanel.getParams().adsServer, this.theApp.MPanel.getParams().getAdsPort(), this.adLocation)), 232, 60, this);
        }
        catch (MalformedURLException ex) {}
        catch (NullPointerException ex2) {}
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        int height = 0;
        graphics.setColor(IRCQNetColors.controlColorDarker);
        graphics.drawRect(0, 0, this.width - 1, this.height - 1);
        switch (this.Mode) {
            case 1: {
                if (this.Image1 != null) {
                    height = this.Image1.getHeight(null);
                }
                if (this.Image1.getWidth(null) <= 0 || height <= 0) {
                    return;
                }
                graphics.drawImage(this.Image1, 0, 0, IRCQNetColors.controlColorDarker, this);
            }
            case 2: {
                if (this.Image1 != null && this.Image2 != null) {
                    graphics.drawImage(this.Image1, this.width / 2 - this.Image1.getWidth(null), this.height / 2 - this.Image1.getHeight(null), this);
                    graphics.drawImage(this.Image2, this.width / 2 + this.Image2.getWidth(null), this.height / 2 - this.Image2.getHeight(null), this);
                    return;
                }
                break;
            }
        }
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        super.reshape(n, n2, width, height);
        this.width = width;
        this.height = height;
    }
    
    public Dimension preferredSize() {
        return new Dimension(234, 60);
    }
    
    public Dimension minimumSize() {
        return new Dimension(234, 60);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.mouseWasDown = true;
        return super.mouseDown(event, n, n2);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseWasDown = false;
        return super.mouseExit(event, n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.mouseWasDown && this.Cookie != null) {
            (this.CookieLocation = new IRCQNetCookieLocation(this.theApp, this.magicNum, this.Cookie)).start();
        }
        return super.mouseUp(event, n, n2);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 10010: {
                final String s = (String)event.arg;
                final int index;
                final int index2;
                if (s.length() > 0 && s != null && (index = s.indexOf("Cookie")) != -1 && (index2 = s.indexOf("Location")) != -1) {
                    this.Cookie = s.substring(index + 6, index2);
                    this.adLocation = s.substring(index2 + 8);
                    this.newImage();
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
}
