import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Point;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class appletScrollbar implements Runnable
{
    static int noPart;
    static int upArrowPart;
    static int downArrowPart;
    static int thumbPart;
    static int pagePart;
    private Thread stillDownThread;
    private long lastMills;
    private Applet ourApplet;
    private boolean inited;
    private Point trackPt;
    private Point dragStartPt;
    private float valuePixSize;
    private int dragStartValue;
    private int pageDir;
    private int selectedItem;
    private int trackedItem;
    private int scrollbarValue;
    private int scrollbarVisable;
    private int scrollbarMax;
    private Rectangle scrollbarRect;
    private Image scrollbarImage;
    private Graphics scrollbarBuf;
    private Rectangle upArrowRect;
    private Image upArrowImg;
    private Image upArrowSelImg;
    private Image upArrowDisImg;
    private Rectangle downArrowRect;
    private Image downArrowImg;
    private Image downArrowSelImg;
    private Image downArrowDisImg;
    private Image backgroundPat;
    private Image backgroundTop;
    private Image backgroundDis;
    private int thumbMinHeight;
    private Rectangle thumbRect;
    private Image thumbTopImg;
    private Image thumbBottomImg;
    private Image thumbCenterImg;
    private Image thumbBackImg;
    private Image thumbTopSelImg;
    private Image thumbBottomSelImg;
    private Image thumbCenterSelImg;
    private Image thumbBackSelImg;
    
    appletScrollbar(final Applet ourApplet, final int n, final int n2, final int n3, final int scrollbarVisable, final int scrollbarMax) {
        this.stillDownThread = null;
        this.lastMills = 0L;
        this.ourApplet = null;
        this.inited = false;
        this.trackPt = new Point(0, 0);
        this.dragStartPt = new Point(0, 0);
        this.valuePixSize = 0.0f;
        this.dragStartValue = 0;
        this.pageDir = 0;
        this.selectedItem = appletScrollbar.noPart;
        this.trackedItem = appletScrollbar.noPart;
        this.scrollbarValue = 0;
        this.scrollbarVisable = 1;
        this.scrollbarMax = 1;
        this.scrollbarImage = null;
        this.scrollbarBuf = null;
        this.inited = false;
        this.ourApplet = ourApplet;
        if (scrollbarVisable > 0) {
            this.scrollbarVisable = scrollbarVisable;
        }
        else {
            this.scrollbarVisable = 0;
        }
        if (scrollbarMax > 1) {
            this.scrollbarMax = scrollbarMax;
        }
        else {
            this.scrollbarMax = 1;
        }
        this.scrollbarRect = new Rectangle(n, n2, 16, n3);
        this.backgroundPat = this.ourApplet.createImage(16, 12);
        this.backgroundDis = this.ourApplet.createImage(16, 14);
        this.backgroundTop = this.ourApplet.createImage(16, 2);
        this.downArrowImg = this.ourApplet.createImage(16, 16);
        this.downArrowSelImg = this.ourApplet.createImage(16, 16);
        this.downArrowDisImg = this.ourApplet.createImage(16, 16);
        this.downArrowRect = new Rectangle(n, n2 + n3 - 16, 16, 16);
        this.upArrowImg = this.ourApplet.createImage(16, 16);
        this.upArrowSelImg = this.ourApplet.createImage(16, 16);
        this.upArrowDisImg = this.ourApplet.createImage(16, 16);
        this.upArrowRect = new Rectangle(n, n2, 16, 16);
        this.thumbTopImg = this.ourApplet.createImage(16, 2);
        this.thumbBottomImg = this.ourApplet.createImage(16, 2);
        this.thumbCenterImg = this.ourApplet.createImage(16, 8);
        this.thumbBackImg = this.ourApplet.createImage(16, 3);
        this.thumbTopSelImg = this.ourApplet.createImage(16, 2);
        this.thumbBottomSelImg = this.ourApplet.createImage(16, 2);
        this.thumbCenterSelImg = this.ourApplet.createImage(16, 8);
        this.thumbBackSelImg = this.ourApplet.createImage(16, 3);
        this.thumbMinHeight = 17;
        this.thumbRect = new Rectangle(n, n2 + 16, 16, this.thumbMinHeight);
        final Image image = this.ourApplet.createImage(16, 158);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(new Color(238, 238, 238));
        graphics.fillRect(0, 0, 16, 158);
        graphics.setColor(Color.black);
        graphics.drawLine(0, 0, 0, 158);
        graphics.drawLine(15, 0, 15, 158);
        final int n4 = 0;
        graphics.setColor(Color.black);
        graphics.drawRect(0, n4, 15, 15);
        graphics.setColor(new Color(222, 222, 222));
        graphics.fillRect(1, n4 + 1, 14, 14);
        graphics.setColor(new Color(172, 172, 172));
        graphics.drawLine(14, n4 + 2, 14, n4 + 14);
        graphics.drawLine(2, n4 + 14, 14, n4 + 14);
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawLine(1, n4 + 1, 13, n4 + 1);
        graphics.drawLine(1, n4 + 1, 1, n4 + 13);
        graphics.setColor(Color.black);
        graphics.drawLine(7, n4 + 6, 8, n4 + 6);
        graphics.drawLine(6, n4 + 7, 9, n4 + 7);
        graphics.drawLine(5, n4 + 8, 10, n4 + 8);
        graphics.drawLine(4, n4 + 9, 11, n4 + 9);
        final int n5 = 16;
        graphics.setColor(Color.black);
        graphics.drawRect(0, n5, 15, 15);
        graphics.setColor(new Color(115, 115, 115));
        graphics.fillRect(1, n5 + 1, 14, 14);
        graphics.setColor(new Color(156, 156, 156));
        graphics.drawLine(14, n5 + 2, 14, n5 + 14);
        graphics.drawLine(2, n5 + 14, 14, n5 + 14);
        graphics.setColor(new Color(82, 82, 82));
        graphics.drawLine(1, n5 + 1, 13, n5 + 1);
        graphics.drawLine(1, n5 + 1, 1, n5 + 13);
        graphics.setColor(Color.black);
        graphics.drawLine(7, n5 + 6, 8, n5 + 6);
        graphics.drawLine(6, n5 + 7, 9, n5 + 7);
        graphics.drawLine(5, n5 + 8, 10, n5 + 8);
        graphics.drawLine(4, n5 + 9, 11, n5 + 9);
        final int n6 = 32;
        graphics.setColor(Color.black);
        graphics.drawRect(0, n6, 15, 15);
        graphics.setColor(new Color(238, 238, 238));
        graphics.fillRect(1, n6 + 1, 14, 14);
        graphics.setColor(new Color(82, 82, 82));
        graphics.drawLine(1, n6 + 15, 14, n6 + 15);
        graphics.setColor(new Color(139, 139, 139));
        graphics.drawLine(7, n6 + 6, 8, n6 + 6);
        graphics.drawLine(6, n6 + 7, 9, n6 + 7);
        graphics.drawLine(5, n6 + 8, 10, n6 + 8);
        graphics.drawLine(4, n6 + 9, 11, n6 + 9);
        final int n7 = 48;
        graphics.setColor(Color.black);
        graphics.drawRect(0, n7, 15, 15);
        graphics.setColor(new Color(222, 222, 222));
        graphics.fillRect(1, n7 + 1, 14, 14);
        graphics.setColor(new Color(172, 172, 172));
        graphics.drawLine(14, n7 + 2, 14, n7 + 14);
        graphics.drawLine(2, n7 + 14, 14, n7 + 14);
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawLine(1, n7 + 1, 13, n7 + 1);
        graphics.drawLine(1, n7 + 1, 1, n7 + 13);
        graphics.setColor(Color.black);
        graphics.drawLine(4, n7 + 6, 11, n7 + 6);
        graphics.drawLine(5, n7 + 7, 10, n7 + 7);
        graphics.drawLine(6, n7 + 8, 9, n7 + 8);
        graphics.drawLine(7, n7 + 9, 8, n7 + 9);
        final int n8 = 64;
        graphics.setColor(Color.black);
        graphics.drawRect(0, n8, 15, 15);
        graphics.setColor(new Color(115, 115, 115));
        graphics.fillRect(1, n8 + 1, 14, 14);
        graphics.setColor(new Color(156, 156, 156));
        graphics.drawLine(14, n8 + 2, 14, n8 + 14);
        graphics.drawLine(2, n8 + 14, 14, n8 + 14);
        graphics.setColor(new Color(82, 82, 82));
        graphics.drawLine(1, n8 + 1, 13, n8 + 1);
        graphics.drawLine(1, n8 + 1, 1, n8 + 13);
        graphics.setColor(Color.black);
        graphics.drawLine(4, n8 + 6, 11, n8 + 6);
        graphics.drawLine(5, n8 + 7, 10, n8 + 7);
        graphics.drawLine(6, n8 + 8, 9, n8 + 8);
        graphics.drawLine(7, n8 + 9, 8, n8 + 9);
        final int n9 = 80;
        graphics.setColor(Color.black);
        graphics.drawRect(0, n9, 15, 15);
        graphics.setColor(new Color(238, 238, 238));
        graphics.fillRect(1, n9 + 1, 14, 14);
        graphics.setColor(new Color(82, 82, 82));
        graphics.drawLine(1, n9, 14, n9);
        graphics.setColor(new Color(139, 139, 139));
        graphics.drawLine(4, n9 + 6, 11, n9 + 6);
        graphics.drawLine(5, n9 + 7, 10, n9 + 7);
        graphics.drawLine(6, n9 + 8, 9, n9 + 8);
        graphics.drawLine(7, n9 + 9, 8, n9 + 9);
        final int n10 = 96;
        graphics.setColor(Color.black);
        graphics.drawRect(0, n10, 15, 16);
        graphics.setColor(new Color(156, 156, 255));
        graphics.fillRect(1, n10 + 1, 14, 15);
        graphics.setColor(new Color(98, 98, 205));
        graphics.drawLine(14, n10 + 2, 14, n10 + 15);
        graphics.drawLine(2, n10 + 15, 14, n10 + 15);
        graphics.setColor(new Color(205, 205, 255));
        graphics.drawLine(1, n10 + 1, 13, n10 + 1);
        graphics.drawLine(1, n10 + 1, 1, n10 + 14);
        graphics.setColor(new Color(238, 238, 238));
        graphics.drawLine(1, n10 + 1, 1, n10 + 1);
        graphics.setColor(new Color(205, 205, 255));
        graphics.drawLine(5, n10 + 4, 10, n10 + 4);
        graphics.drawLine(5, n10 + 6, 10, n10 + 6);
        graphics.drawLine(5, n10 + 8, 10, n10 + 8);
        graphics.drawLine(5, n10 + 10, 10, n10 + 10);
        graphics.setColor(new Color(238, 238, 238));
        graphics.drawLine(4, n10 + 4, 4, n10 + 4);
        graphics.drawLine(4, n10 + 6, 4, n10 + 6);
        graphics.drawLine(4, n10 + 8, 4, n10 + 8);
        graphics.drawLine(4, n10 + 10, 4, n10 + 10);
        graphics.setColor(new Color(49, 49, 156));
        graphics.drawLine(5, n10 + 5, 11, n10 + 5);
        graphics.drawLine(5, n10 + 7, 11, n10 + 7);
        graphics.drawLine(5, n10 + 9, 11, n10 + 9);
        graphics.drawLine(5, n10 + 11, 11, n10 + 11);
        final int n11 = 113;
        graphics.setColor(Color.black);
        graphics.drawRect(0, n11, 15, 16);
        graphics.setColor(new Color(98, 98, 205));
        graphics.fillRect(1, n11 + 1, 14, 15);
        graphics.setColor(new Color(49, 49, 156));
        graphics.drawLine(14, n11 + 2, 14, n11 + 15);
        graphics.drawLine(2, n11 + 15, 14, n11 + 15);
        graphics.setColor(new Color(156, 156, 255));
        graphics.drawLine(1, n11 + 1, 13, n11 + 1);
        graphics.drawLine(1, n11 + 1, 1, n11 + 14);
        graphics.setColor(new Color(205, 205, 255));
        graphics.drawLine(1, n11 + 1, 1, n11 + 1);
        graphics.setColor(new Color(156, 156, 255));
        graphics.drawLine(5, n11 + 4, 10, n11 + 4);
        graphics.drawLine(5, n11 + 6, 10, n11 + 6);
        graphics.drawLine(5, n11 + 8, 10, n11 + 8);
        graphics.drawLine(5, n11 + 10, 10, n11 + 10);
        graphics.setColor(new Color(205, 205, 255));
        graphics.drawLine(4, n11 + 4, 4, n11 + 4);
        graphics.drawLine(4, n11 + 6, 4, n11 + 6);
        graphics.drawLine(4, n11 + 8, 4, n11 + 8);
        graphics.drawLine(4, n11 + 10, 4, n11 + 10);
        graphics.setColor(new Color(0, 0, 82));
        graphics.drawLine(5, n11 + 5, 11, n11 + 5);
        graphics.drawLine(5, n11 + 7, 11, n11 + 7);
        graphics.drawLine(5, n11 + 9, 11, n11 + 9);
        graphics.drawLine(5, n11 + 11, 11, n11 + 11);
        final int n12 = 130;
        graphics.setColor(new Color(172, 172, 172));
        graphics.fillRect(1, n12, 14, 14);
        graphics.setColor(new Color(115, 115, 115));
        graphics.drawLine(1, n12, 13, n12);
        graphics.drawLine(1, n12, 1, n12 + 13);
        graphics.setColor(new Color(139, 139, 139));
        graphics.drawLine(2, n12 + 1, 12, n12 + 1);
        graphics.drawLine(2, n12 + 1, 2, n12 + 13);
        graphics.setColor(new Color(205, 205, 205));
        graphics.drawLine(14, n12 + 1, 14, n12 + 13);
        graphics.setColor(new Color(189, 189, 189));
        graphics.drawLine(13, n12 + 2, 13, n12 + 13);
        this.backgroundPat.getGraphics().drawImage(image, 0, -132, this.ourApplet);
        this.backgroundDis.getGraphics().drawImage(image, 0, -144, this.ourApplet);
        this.backgroundTop.getGraphics().drawImage(image, 0, -130, this.ourApplet);
        this.upArrowImg.getGraphics().drawImage(image, 0, 0, this.ourApplet);
        this.upArrowSelImg.getGraphics().drawImage(image, 0, -16, this.ourApplet);
        this.upArrowDisImg.getGraphics().drawImage(image, 0, -32, this.ourApplet);
        this.downArrowImg.getGraphics().drawImage(image, 0, -48, this.ourApplet);
        this.downArrowSelImg.getGraphics().drawImage(image, 0, -64, this.ourApplet);
        this.downArrowDisImg.getGraphics().drawImage(image, 0, -80, this.ourApplet);
        this.thumbTopImg.getGraphics().drawImage(image, 0, -96, this.ourApplet);
        this.thumbBottomImg.getGraphics().drawImage(image, 0, -111, this.ourApplet);
        this.thumbCenterImg.getGraphics().drawImage(image, 0, -100, this.ourApplet);
        this.thumbBackImg.getGraphics().drawImage(image, 0, -108, this.ourApplet);
        this.thumbTopSelImg.getGraphics().drawImage(image, 0, -113, this.ourApplet);
        this.thumbBottomSelImg.getGraphics().drawImage(image, 0, -128, this.ourApplet);
        this.thumbCenterSelImg.getGraphics().drawImage(image, 0, -117, this.ourApplet);
        this.thumbBackSelImg.getGraphics().drawImage(image, 0, -125, this.ourApplet);
        if (this.stillDownThread == null) {
            (this.stillDownThread = new Thread(this)).start();
        }
        this.inited = true;
        this.recalcThumbSize();
    }
    
    public void stop() {
        this.stillDownThread = null;
    }
    
    public void setValues(final int scrollbarValue, final int scrollbarMax) {
        if (scrollbarMax > 1) {
            this.scrollbarMax = scrollbarMax;
        }
        else {
            this.scrollbarMax = 1;
        }
        int scrollbarValue2 = this.scrollbarMax - this.scrollbarVisable;
        if (scrollbarValue2 < 0) {
            scrollbarValue2 = 0;
        }
        if (scrollbarValue < 0) {
            this.scrollbarValue = 0;
        }
        else if (scrollbarValue > scrollbarValue2) {
            this.scrollbarValue = scrollbarValue2;
        }
        else {
            this.scrollbarValue = scrollbarValue;
        }
        this.recalcThumbSize();
    }
    
    public int getValue() {
        return this.scrollbarValue;
    }
    
    private void drawlPattern(final Graphics graphics, final Image image, final Applet applet, final int n, final int n2, final int n3, final int n4) {
        final int width = image.getWidth(applet);
        for (int height = image.getHeight(applet), i = n2; i < n2 + n4; i += height) {
            if (i + height > n2 + n4) {
                i -= i + height - (n2 + n4);
            }
            for (int j = n; j < n + n3; j += width) {
                if (j + width > n + n3) {
                    j -= j + width - (n + n3);
                }
                graphics.drawImage(image, j, i, applet);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (!this.inited) {
            return;
        }
        if (this.scrollbarBuf == null) {
            this.scrollbarImage = this.ourApplet.createImage(16, this.scrollbarRect.height);
            this.scrollbarBuf = this.scrollbarImage.getGraphics();
        }
        this.scrollbarBuf.setColor(Color.white);
        this.scrollbarBuf.fillRect(0, 0, this.scrollbarRect.width, this.scrollbarRect.height);
        if (this.scrollbarMax > this.scrollbarVisable) {
            this.drawlPattern(this.scrollbarBuf, this.backgroundPat, this.ourApplet, 0, 16, this.scrollbarRect.width, this.scrollbarRect.height - 32);
            this.scrollbarBuf.drawImage(this.backgroundTop, 0, 16, this.ourApplet);
            this.scrollbarBuf.drawImage(this.backgroundTop, 0, this.thumbRect.y + this.thumbRect.height, this.ourApplet);
            if (this.selectedItem == appletScrollbar.upArrowPart) {
                this.scrollbarBuf.drawImage(this.upArrowSelImg, 0, 0, this.ourApplet);
            }
            else {
                this.scrollbarBuf.drawImage(this.upArrowImg, 0, 0, this.ourApplet);
            }
            if (this.selectedItem == appletScrollbar.downArrowPart) {
                this.scrollbarBuf.drawImage(this.downArrowSelImg, 0, this.scrollbarRect.height - 16, this.ourApplet);
            }
            else {
                this.scrollbarBuf.drawImage(this.downArrowImg, 0, this.scrollbarRect.height - 16, this.ourApplet);
            }
            if (this.selectedItem == appletScrollbar.thumbPart) {
                this.drawlPattern(this.scrollbarBuf, this.thumbBackSelImg, this.ourApplet, 0, this.thumbRect.y + 2, this.thumbRect.width, this.thumbRect.height - 4);
                this.scrollbarBuf.drawImage(this.thumbTopSelImg, 0, this.thumbRect.y, this.ourApplet);
                this.scrollbarBuf.drawImage(this.thumbBottomSelImg, 0, this.thumbRect.y + this.thumbRect.height - this.thumbBottomSelImg.getHeight(this.ourApplet), this.ourApplet);
                this.scrollbarBuf.drawImage(this.thumbCenterSelImg, 0, this.thumbRect.y + this.thumbRect.height / 2 - this.thumbCenterSelImg.getHeight(this.ourApplet) / 2, this.ourApplet);
            }
            else {
                this.drawlPattern(this.scrollbarBuf, this.thumbBackImg, this.ourApplet, 0, this.thumbRect.y + 2, this.thumbRect.width, this.thumbRect.height - 4);
                this.scrollbarBuf.drawImage(this.thumbTopImg, 0, this.thumbRect.y, this.ourApplet);
                this.scrollbarBuf.drawImage(this.thumbBottomImg, 0, this.thumbRect.y + this.thumbRect.height - this.thumbBottomImg.getHeight(this.ourApplet), this.ourApplet);
                this.scrollbarBuf.drawImage(this.thumbCenterImg, 0, this.thumbRect.y + this.thumbRect.height / 2 - this.thumbCenterImg.getHeight(this.ourApplet) / 2, this.ourApplet);
            }
        }
        else {
            this.drawlPattern(this.scrollbarBuf, this.backgroundDis, this.ourApplet, 0, 16, this.scrollbarRect.width, this.scrollbarRect.height - 32);
            this.scrollbarBuf.drawImage(this.upArrowDisImg, 0, 0, this.ourApplet);
            this.scrollbarBuf.drawImage(this.downArrowDisImg, 0, this.scrollbarRect.height - 16, this.ourApplet);
        }
        graphics.drawImage(this.scrollbarImage, this.scrollbarRect.x, this.scrollbarRect.y, this.ourApplet);
    }
    
    void recalcThumbSize() {
        int thumbMinHeight = (this.scrollbarRect.height - 30) * (this.scrollbarVisable / this.scrollbarMax);
        if (thumbMinHeight < this.thumbMinHeight) {
            thumbMinHeight = this.thumbMinHeight;
        }
        this.thumbRect.resize(16, thumbMinHeight);
        this.valuePixSize = (this.scrollbarRect.height - this.thumbRect.height - 30) / this.subtractNLZ(this.scrollbarMax, this.scrollbarVisable);
        this.thumbRect.move(this.scrollbarRect.x, this.scrollbarRect.y + 15 + (int)(this.valuePixSize * this.scrollbarValue));
    }
    
    public boolean handleEvent(final Event event) {
        boolean b = false;
        final int scrollbarValue = this.scrollbarValue;
        final int selectedItem = this.selectedItem;
        if (this.scrollbarMax <= this.scrollbarVisable) {
            return false;
        }
        if (event.id == 501 || event.id == 506) {
            final int scrollbarPart = this.getScrollbarPart(event.x, event.y);
            if (this.trackedItem == appletScrollbar.noPart) {
                this.trackedItem = scrollbarPart;
                if (this.trackedItem == appletScrollbar.thumbPart) {
                    this.dragStartPt.x = event.x;
                    this.dragStartPt.y = event.y;
                    this.dragStartValue = this.scrollbarValue;
                }
                else if (this.trackedItem == appletScrollbar.pagePart) {
                    if (event.y <= this.thumbRect.y) {
                        this.pageDir = 0;
                    }
                    else {
                        this.pageDir = 1;
                    }
                }
            }
            if (this.trackedItem != appletScrollbar.noPart) {
                this.trackPt.x = event.x;
                this.trackPt.y = event.y;
                if (this.trackedItem == scrollbarPart) {
                    this.selectedItem = scrollbarPart;
                }
                else if (this.trackedItem == appletScrollbar.thumbPart) {
                    this.selectedItem = appletScrollbar.thumbPart;
                }
                else {
                    this.selectedItem = appletScrollbar.noPart;
                }
                if (this.selectedItem == appletScrollbar.upArrowPart) {
                    if (this.scrollbarValue > 0) {
                        --this.scrollbarValue;
                    }
                }
                else if (this.selectedItem == appletScrollbar.downArrowPart) {
                    if (this.scrollbarValue < this.subtractNLZ(this.scrollbarMax, this.scrollbarVisable)) {
                        ++this.scrollbarValue;
                    }
                }
                else if (this.trackedItem == appletScrollbar.thumbPart) {
                    int n;
                    boolean b2;
                    if (this.dragStartPt.y > event.y) {
                        n = this.dragStartPt.y - event.y;
                        b2 = false;
                    }
                    else {
                        n = event.y - this.dragStartPt.y;
                        b2 = true;
                    }
                    int n2;
                    if (n == 0) {
                        n2 = 0;
                    }
                    else {
                        n2 = (int)(n / this.valuePixSize);
                    }
                    int scrollbarValue2;
                    if (!b2) {
                        scrollbarValue2 = this.dragStartValue - n2;
                    }
                    else {
                        scrollbarValue2 = this.dragStartValue + n2;
                    }
                    if (scrollbarValue2 < 0) {
                        scrollbarValue2 = 0;
                    }
                    if (scrollbarValue2 < 0) {
                        this.scrollbarValue = 0;
                    }
                    else if (scrollbarValue2 > this.subtractNLZ(this.scrollbarMax, this.scrollbarVisable)) {
                        this.scrollbarValue = this.subtractNLZ(this.scrollbarMax, this.scrollbarVisable);
                    }
                    else {
                        this.scrollbarValue = scrollbarValue2;
                    }
                }
                else if (this.selectedItem == appletScrollbar.pagePart) {
                    int scrollbarValue3 = this.scrollbarValue;
                    if (this.pageDir == 0) {
                        if (event.y < this.thumbRect.y) {
                            scrollbarValue3 -= this.scrollbarVisable;
                        }
                    }
                    else if (event.y >= this.thumbRect.y + this.thumbRect.height) {
                        scrollbarValue3 += this.scrollbarVisable;
                    }
                    if (scrollbarValue3 < 0) {
                        this.scrollbarValue = 0;
                    }
                    else if (scrollbarValue3 > this.subtractNLZ(this.scrollbarMax, this.scrollbarVisable)) {
                        this.scrollbarValue = this.subtractNLZ(this.scrollbarMax, this.scrollbarVisable);
                    }
                    else {
                        this.scrollbarValue = scrollbarValue3;
                    }
                }
                this.lastMills = System.currentTimeMillis();
                b = true;
            }
        }
        else if (event.id == 502) {
            this.selectedItem = appletScrollbar.noPart;
            if (this.trackedItem != appletScrollbar.noPart) {
                this.trackedItem = appletScrollbar.noPart;
                b = true;
            }
        }
        if (scrollbarValue != this.scrollbarValue) {
            this.recalcThumbSize();
            this.ourApplet.repaint();
        }
        else if (selectedItem != this.selectedItem) {
            this.ourApplet.repaint(this.scrollbarRect.x, this.scrollbarRect.y, this.scrollbarRect.width, this.scrollbarRect.height);
        }
        return b;
    }
    
    @Override
    public void run() {
        final Event event = new Event(this.ourApplet, 0L, 501, this.trackPt.x, this.trackPt.y, 0, 0);
        while (Thread.currentThread() == this.stillDownThread) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            if (this.trackedItem == appletScrollbar.upArrowPart || this.trackedItem == appletScrollbar.downArrowPart) {
                if (this.lastMills + 100L >= System.currentTimeMillis()) {
                    continue;
                }
                this.lastMills = System.currentTimeMillis();
                event.x = this.trackPt.x;
                event.y = this.trackPt.y;
                this.handleEvent(event);
            }
            else {
                if (this.trackedItem != appletScrollbar.pagePart || this.lastMills + 250L >= System.currentTimeMillis()) {
                    continue;
                }
                this.lastMills = System.currentTimeMillis();
                event.x = this.trackPt.x;
                event.y = this.trackPt.y;
                this.handleEvent(event);
            }
        }
    }
    
    private int getScrollbarPart(final int n, final int n2) {
        int n3 = appletScrollbar.noPart;
        if (this.upArrowRect.inside(n, n2)) {
            n3 = appletScrollbar.upArrowPart;
        }
        else if (this.downArrowRect.inside(n, n2)) {
            n3 = appletScrollbar.downArrowPart;
        }
        else if (this.thumbRect.inside(n, n2)) {
            n3 = appletScrollbar.thumbPart;
        }
        else if (this.scrollbarRect.inside(n, n2)) {
            n3 = appletScrollbar.pagePart;
        }
        return n3;
    }
    
    private int subtractNLZ(final int n, final int n2) {
        int n3 = n - n2;
        if (n3 < 0) {
            n3 = 0;
        }
        return n3;
    }
    
    static {
        appletScrollbar.noPart = 0;
        appletScrollbar.upArrowPart = 1;
        appletScrollbar.downArrowPart = 2;
        appletScrollbar.thumbPart = 3;
        appletScrollbar.pagePart = 4;
    }
}
