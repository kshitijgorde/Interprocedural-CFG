// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import com.objectbox.runner.gui.JBee;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.awt.LayoutManager;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.Container;

public class LWWindowMover extends Container
{
    Window windowtomove;
    private int offsetx;
    private int offsety;
    private int startx_mouse;
    private int starty_mouse;
    private int startx_window;
    private int starty_window;
    private String title;
    private Font titlefont;
    static final Color BGCOL;
    static final Color TXCOL;
    static final Color BUTCOL;
    private int headerheight;
    private Dimension pref_size;
    private Image gradient;
    private Image left;
    private Image right;
    private Rectangle quitdrawrect;
    private Cursor crosshair;
    private Cursor defaultcursor;
    
    static {
        BGCOL = new Color(220, 220, 255);
        TXCOL = LWWindowMover.BGCOL.brighter().brighter();
        BUTCOL = new Color(250, 100, 200);
    }
    
    public LWWindowMover(final Window windowtomove, final String title) {
        this.windowtomove = null;
        this.offsetx = 0;
        this.offsety = 0;
        this.startx_mouse = 0;
        this.starty_mouse = 0;
        this.startx_window = 0;
        this.starty_window = 0;
        this.title = "";
        this.titlefont = null;
        this.headerheight = 5;
        this.pref_size = new Dimension(10, 2);
        this.gradient = null;
        this.left = null;
        this.right = null;
        this.quitdrawrect = null;
        this.crosshair = new Cursor(1);
        this.defaultcursor = new Cursor(0);
        this.enableEvents(48L);
        this.windowtomove = windowtomove;
        this.title = title;
        this.setLayout(null);
        this.titlefont = new Font("Sansserif", 0, 8);
    }
    
    public void addNotify() {
        super.addNotify();
        final Dimension size = this.getSize();
        if (size.width == 0 || size.height == 0) {
            this.setSize(this.getPreferredSize());
        }
    }
    
    public int getHeaderheight() {
        return this.headerheight;
    }
    
    public Image getImageResource(final String s) {
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
            final DataInputStream dataInputStream = new DataInputStream(resourceAsStream);
            if (resourceAsStream != null) {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        byteArrayOutputStream.write(dataInputStream.readByte());
                    }
                    catch (IOException ex) {
                        break;
                    }
                }
                final byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                dataInputStream.close();
                final Image image = Toolkit.getDefaultToolkit().createImage(byteArray);
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForID(0);
                return image;
            }
        }
        catch (Exception ex2) {
            return null;
        }
        return null;
    }
    
    public Dimension getMaximumSize() {
        return this.pref_size;
    }
    
    public Dimension getMinimumSize() {
        return this.pref_size;
    }
    
    public Dimension getPreferredSize() {
        return this.pref_size;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (this.quitdrawrect == null) {
            this.quitdrawrect = new Rectangle(width - 12, 2, 10, height - 3);
        }
        graphics.setColor(LWWindowMover.BGCOL);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, width - 1, height - 1);
        graphics.setColor(LWWindowMover.BGCOL);
        graphics.fill3DRect(this.quitdrawrect.x, this.quitdrawrect.y, this.quitdrawrect.width, this.quitdrawrect.height, false);
        super.paint(graphics);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.setCursor(this.crosshair);
                this.offsetx = mouseEvent.getX();
                this.offsety = mouseEvent.getY();
                this.startx_mouse = mouseEvent.getX();
                this.starty_mouse = mouseEvent.getY();
                this.startx_window = this.windowtomove.getLocation().x;
                this.starty_window = this.windowtomove.getLocation().y;
                if (this.quitdrawrect != null && this.quitdrawrect.contains(this.offsetx, this.offsety) && this.windowtomove instanceof JBSmallWindow) {
                    ((JBSmallWindow)this.windowtomove).kill();
                }
                this.windowtomove.toFront();
                this.windowtomove.requestFocus();
                break;
            }
            case 502: {
                if (JBee.OS_type == 1) {
                    this.windowtomove.setLocation(this.windowtomove.getLocationOnScreen().x + (mouseEvent.getX() - this.offsetx), this.windowtomove.getLocationOnScreen().y + (mouseEvent.getY() - this.offsety));
                }
                else {
                    this.windowtomove.setLocation(mouseEvent.getX() - this.startx_mouse + this.startx_window, mouseEvent.getY() - this.starty_mouse + this.starty_window);
                }
                this.setCursor(this.defaultcursor);
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 502: {}
            case 504: {}
            case 506: {
                if (JBee.OS_type == 1) {
                    this.windowtomove.setLocation(this.windowtomove.getLocationOnScreen().x + (mouseEvent.getX() - this.offsetx), this.windowtomove.getLocationOnScreen().y + (mouseEvent.getY() - this.offsety));
                    break;
                }
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void setHeaderheight(final int headerheight) {
        this.headerheight = headerheight;
        this.pref_size = new Dimension(this.getSize().width, this.headerheight);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
