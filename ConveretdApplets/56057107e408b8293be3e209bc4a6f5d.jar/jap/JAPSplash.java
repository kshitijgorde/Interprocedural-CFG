// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ColorModel;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;

public final class JAPSplash extends Window implements ISplashResponse
{
    private static final long serialVersionUID = 1L;
    private static final String IMGPATHHICOLOR = "images/";
    private static final String IMGPATHLOWCOLOR = "images/lowcolor/";
    private static final String SPLASH_FILE = "splash.jpg";
    private static final String BUSY_FILE = "busy.gif";
    private static final int SPLASH_WIDTH = 501;
    private static final int SPLASH_HEIGHT = 330;
    private static final int SPLASH_FILESIZE = 150000;
    private static final int BUSY_FILESIZE = 7000;
    private static final int VERSION_OFFSET_X = 10;
    private static final int VERSION_OFFSET_Y = 15;
    private static final int BUSY_POSITION_X = 15;
    private static final int BUSY_POSITION_Y = 312;
    private static final int MESSAGE_POSITION_X = 17;
    private static final int MESSAGE_POSITION_Y = 302;
    private Image m_imgSplash;
    private Image m_imgBusy;
    private Image m_imgOffScreen;
    private Font m_fntFont;
    private String m_strLoading;
    private String m_currentText;
    private String m_strVersion;
    private int m_iXVersion;
    private int m_iYVersion;
    
    public JAPSplash(final Frame frame) {
        this(frame, null);
    }
    
    public JAPSplash(final Frame frame, final String text) {
        super(frame);
        this.m_imgOffScreen = null;
        this.setLayout(null);
        final int n = 100;
        this.m_iYVersion = n;
        this.m_iXVersion = n;
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.loadImages(mediaTracker);
        if (text == null || text.trim().length() == 0) {
            this.setText("Busy");
        }
        else {
            this.setText(text);
        }
        this.m_strVersion = "Version: 00.12.005";
        this.m_fntFont = new Font("Sans", 0, 9);
        this.m_iXVersion = 491 - defaultToolkit.getFontMetrics(this.m_fntFont).stringWidth(this.m_strVersion);
        this.m_iYVersion = 315;
        this.setSize(501, 330);
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {}
        this.toFront();
    }
    
    private Image loadImage(final String s, final int n, final MediaTracker mediaTracker) {
        Class<?> forName = null;
        try {
            forName = Class.forName("JAP");
        }
        catch (Exception ex) {}
        InputStream resourceAsStream = forName.getResourceAsStream(s);
        if (resourceAsStream == null) {
            try {
                resourceAsStream = new FileInputStream(s);
            }
            catch (FileNotFoundException ex2) {}
        }
        Image image = null;
        if (resourceAsStream != null) {
            final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
            final byte[] array = new byte[n];
            int n2 = 0;
            try {
                int read;
                while ((read = resourceAsStream.read(array, n2, array.length - n2)) > 0) {
                    n2 += read;
                }
                image = defaultToolkit.createImage(array, 0, n2);
                mediaTracker.addImage(this.m_imgSplash, 1);
                mediaTracker.checkID(1, true);
            }
            catch (Exception ex3) {}
        }
        return image;
    }
    
    private boolean isHighColor() {
        ColorModel colorModel = null;
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        try {
            colorModel = defaultToolkit.getColorModel();
        }
        catch (Throwable t) {}
        return colorModel != null && colorModel.getPixelSize() > 16;
    }
    
    private void loadImages(final MediaTracker mediaTracker) {
        if (this.isHighColor()) {
            this.m_imgSplash = this.loadImage("images/splash.jpg", 150000, mediaTracker);
            this.m_imgBusy = this.loadImage("images/busy.gif", 7000, mediaTracker);
        }
        else {
            this.m_imgSplash = this.loadImage("images/lowcolor/splash.jpg", 150000, mediaTracker);
            this.m_imgBusy = this.loadImage("images/lowcolor/busy.gif", 7000, mediaTracker);
        }
    }
    
    public void setText(final String currentText) {
        if (currentText != null && currentText.trim().length() > 0) {
            this.m_currentText = currentText;
            this.m_strLoading = currentText + "...";
        }
    }
    
    public String getText() {
        return this.m_currentText;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_imgOffScreen == null) {
            this.m_imgOffScreen = this.createImage(501, 330);
        }
        final Graphics graphics2 = this.m_imgOffScreen.getGraphics();
        if (this.m_imgSplash != null) {
            graphics2.drawImage(this.m_imgSplash, 0, 0, this);
        }
        if (this.m_imgBusy != null) {
            graphics2.drawImage(this.m_imgBusy, 15, 312, this);
        }
        graphics2.setColor(Color.gray);
        graphics2.drawRect(0, 0, 500, 329);
        graphics2.setFont(this.m_fntFont);
        graphics2.setColor(Color.black);
        graphics2.drawString(this.m_strLoading, 17, 302);
        graphics2.drawString(this.m_strVersion, this.m_iXVersion, this.m_iYVersion);
        graphics.drawImage(this.m_imgOffScreen, 0, 0, this);
    }
    
    public void centerOnScreen() {
        centerOnScreen(this);
    }
    
    private static void centerOnScreen(final Window window) {
        final Dimension size = window.getSize();
        Rectangle rectangle;
        try {
            final Object invoke = Class.forName("java.awt.GraphicsEnvironment").getMethod("getLocalGraphicsEnvironment", (Class<?>[])null).invoke(null, (Object[])null);
            final Object invoke2 = invoke.getClass().getMethod("getDefaultScreenDevice", (Class<?>[])null).invoke(invoke, (Object[])null);
            final Object invoke3 = invoke2.getClass().getMethod("getDefaultConfiguration", (Class<?>[])null).invoke(invoke2, (Object[])null);
            rectangle = (Rectangle)invoke3.getClass().getMethod("getBounds", (Class<?>[])null).invoke(invoke3, (Object[])null);
        }
        catch (Exception ex) {
            rectangle = new Rectangle(new Point(0, 0), window.getToolkit().getScreenSize());
        }
        window.setLocation(rectangle.x + (rectangle.width - size.width) / 2, rectangle.y + (rectangle.height - size.height) / 2);
    }
}
