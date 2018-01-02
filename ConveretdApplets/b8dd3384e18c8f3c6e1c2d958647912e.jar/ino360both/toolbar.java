// 
// Decompiled by Procyon v0.5.30
// 

package ino360both;

import java.awt.image.ImageFilter;
import java.io.InputStream;
import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class toolbar implements Runnable
{
    protected int BAR_HEIGHT;
    protected int BAR_X_OFFSET;
    protected int BAR_Y_OFFSET;
    protected int BTN_HS;
    protected int BTN_HS_DISABLED;
    protected int BTN_HS_OVER;
    protected int BTN_HS_PRESSED;
    protected int BTN_HS_PRESSED_OVER;
    protected int BTN_MINUS;
    protected int BTN_MINUS_OVER;
    protected int BTN_MINUS_PRESSED;
    protected int BTN_PLUS;
    protected int BTN_PLUS_OVER;
    protected int BTN_PLUS_PRESSED;
    protected int BTN_STATUS_DISABLED;
    protected int BTN_STATUS_NORMAL;
    protected int BTN_STATUS_OVER;
    protected int BTN_STATUS_PRESSED;
    protected int BTN_STATUS_PRESSED_OVER;
    protected int H_BUTTON;
    protected int N_BUTTONS;
    protected int W_BUTTON;
    protected int X_OFFSET;
    protected int Y_OFFSET;
    protected Color barColor;
    protected int barPerc;
    protected int barTotWidth;
    protected int barX;
    protected int barY;
    protected Color bgColor;
    protected Color borderColor;
    protected Image[] buttons;
    protected int curStatusHS;
    protected int curStatusMinus;
    protected int curStatusPlus;
    protected int height;
    protected boolean isHSButtonPressed;
    protected boolean justPressedHSButton;
    protected String msg;
    boolean msgBold;
    Font msgFont;
    protected boolean paintFinished;
    protected ptviewer ptv;
    protected Color textColor;
    protected Color textDefaultColor;
    protected Graphics tlbGraph;
    protected Image tlbImage;
    protected int width;
    int xMessage;
    protected int xTlb;
    int yMessage;
    protected int yTlb;
    private Thread zoomThread;
    
    public toolbar(final ptviewer ptv, final String imgStripName) {
        this.height = 23;
        this.BTN_PLUS = 0;
        this.BTN_PLUS_OVER = 1;
        this.BTN_PLUS_PRESSED = 2;
        this.BTN_MINUS = 3;
        this.BTN_MINUS_OVER = 4;
        this.BTN_MINUS_PRESSED = 5;
        this.BTN_HS = 6;
        this.BTN_HS_OVER = 7;
        this.BTN_HS_PRESSED = 8;
        this.BTN_HS_PRESSED_OVER = 9;
        this.BTN_HS_DISABLED = 10;
        this.N_BUTTONS = 11;
        this.W_BUTTON = 27;
        this.H_BUTTON = 19;
        this.X_OFFSET = 1;
        this.Y_OFFSET = 2;
        this.BTN_STATUS_NORMAL = 0;
        this.BTN_STATUS_OVER = 1;
        this.BTN_STATUS_PRESSED = 2;
        this.BTN_STATUS_PRESSED_OVER = 3;
        this.BTN_STATUS_DISABLED = 4;
        this.curStatusPlus = this.BTN_STATUS_NORMAL;
        this.curStatusMinus = this.BTN_STATUS_NORMAL;
        this.curStatusHS = this.BTN_STATUS_DISABLED;
        this.isHSButtonPressed = false;
        this.justPressedHSButton = false;
        this.zoomThread = null;
        this.BAR_X_OFFSET = 89;
        this.BAR_Y_OFFSET = 7;
        this.BAR_HEIGHT = 9;
        this.barPerc = 0;
        this.paintFinished = true;
        this.ptv = ptv;
        this.msg = "";
        this.msgFont = null;
        this.msgBold = false;
        this.textDefaultColor = Color.black;
        this.textColor = this.textDefaultColor;
        this.buttons = new Image[this.N_BUTTONS];
        this.loadButtonImages(imgStripName);
        this.xMessage = this.BAR_X_OFFSET;
    }
    
    public void SetTextColor(final String txtColor) {
        try {
            this.textDefaultColor = new Color(Integer.parseInt(txtColor, 16));
        }
        catch (Exception e) {}
    }
    
    protected void createFont(final Graphics g) {
        boolean fontFits = false;
        final Font curFont = g.getFont();
        final int maxCharHeight = 19;
        final int minFontSize = 5;
        Font font = g.getFont();
        int size = font.getSize();
        final String name = font.getName();
        int style = font.getStyle();
        if (this.msgBold) {
            style = 1;
        }
        g.setFont(font = new Font(name, style, size));
        FontMetrics fontMetrics = g.getFontMetrics();
        while (!fontFits) {
            if (fontMetrics.getHeight() <= maxCharHeight) {
                fontFits = true;
            }
            else if (size <= minFontSize) {
                fontFits = true;
            }
            else {
                g.setFont(font = new Font(name, style, --size));
                fontMetrics = g.getFontMetrics();
            }
        }
        this.yMessage = this.yTlb + this.height - (this.height - fontMetrics.getAscent()) / 2 - 2;
        this.msgFont = g.getFont();
        g.setFont(curFont);
    }
    
    protected void drawButton(final int btnIndex, final int x, final int y) {
        this.tlbGraph.drawImage(this.buttons[btnIndex], x, y, this.ptv);
    }
    
    protected void drawHSButton(final int btnStatus) {
        final int i = btnStatus + 6;
        this.drawButton(i, this.X_OFFSET + this.W_BUTTON * 2 + 1, this.Y_OFFSET);
        this.curStatusHS = btnStatus;
    }
    
    protected void drawMinusButton(final int btnStatus) {
        final int i = btnStatus + 3;
        this.drawButton(i, this.X_OFFSET + this.W_BUTTON + 1, this.Y_OFFSET);
        this.curStatusMinus = btnStatus;
    }
    
    protected void drawPlusButton(final int btnStatus) {
        this.drawButton(btnStatus, this.X_OFFSET, this.Y_OFFSET);
        this.curStatusPlus = btnStatus;
    }
    
    protected void drawProgressBar() {
        if (this.barPerc <= 0 || this.barPerc > 100) {
            return;
        }
        final int barWidth = this.barTotWidth * this.barPerc / 100;
        this.tlbGraph.setColor(this.barColor);
        this.tlbGraph.fillRect(this.barX, this.barY, barWidth, this.BAR_HEIGHT);
    }
    
    protected void drawToolbar() {
        this.tlbGraph.setColor(this.borderColor);
        this.tlbGraph.drawRect(0, 0, this.width - 1, this.height - 1);
        this.tlbGraph.setColor(this.bgColor);
        this.tlbGraph.fillRect(1, 1, this.width - 2, this.height - 2);
        this.drawPlusButton(this.curStatusPlus);
        this.drawMinusButton(this.curStatusMinus);
        if (this.ptv.numhs > 0 && this.curStatusHS == this.BTN_STATUS_DISABLED) {
            this.curStatusHS = this.BTN_STATUS_NORMAL;
        }
        this.drawHSButton(this.curStatusHS);
    }
    
    protected void extractColors(final Image imgStrip) {
        final int[] pixel = { 0 };
        PixelGrabber pg = new PixelGrabber(imgStrip, 0, 0, 1, 1, pixel, 0, 1);
        try {
            pg.grabPixels();
            final int r = pixel[0] >> 16 & 0xFF;
            final int g = pixel[0] >> 8 & 0xFF;
            final int b = pixel[0] & 0xFF;
            this.borderColor = new Color(r, g, b);
        }
        catch (Exception e) {
            this.borderColor = new Color(0, 0, 0);
        }
        pg = new PixelGrabber(imgStrip, 306, 11, 1, 1, pixel, 0, 1);
        try {
            pg.grabPixels();
            final int r = pixel[0] >> 16 & 0xFF;
            final int g = pixel[0] >> 8 & 0xFF;
            final int b = pixel[0] & 0xFF;
            this.barColor = new Color(r, g, b);
        }
        catch (Exception e) {
            this.barColor = new Color(0, 60, 116);
        }
        pg = new PixelGrabber(imgStrip, 298, 11, 1, 1, pixel, 0, 1);
        try {
            pg.grabPixels();
            final int r = pixel[0] >> 16 & 0xFF;
            final int g = pixel[0] >> 8 & 0xFF;
            final int b = pixel[0] & 0xFF;
            this.bgColor = new Color(r, g, b);
        }
        catch (Exception e) {
            this.bgColor = new Color(236, 233, 216);
        }
    }
    
    public int getHeight() {
        return this.height;
    }
    
    protected void loadButtonImages(final String imgStripName) {
        Image imgStrip;
        if (imgStripName == null) {
            try {
                final MediaTracker m = new MediaTracker(this.ptv);
                final InputStream is = this.getClass().getResourceAsStream("Toolbar.gif");
                final BufferedInputStream bis = new BufferedInputStream(is);
                final byte[] byBuf = new byte[10000];
                final int byteRead = bis.read(byBuf, 0, 10000);
                imgStrip = Toolkit.getDefaultToolkit().createImage(byBuf);
                m.addImage(imgStrip, 0);
                m.waitForAll();
            }
            catch (Exception e) {
                imgStrip = null;
            }
        }
        else {
            imgStrip = this.ptv.loadImage(imgStripName);
        }
        for (int i = 0; i < this.N_BUTTONS; ++i) {
            final ImageFilter crop = new CropImageFilter(1 + i * this.W_BUTTON, 2, this.W_BUTTON, this.H_BUTTON);
            this.buttons[i] = this.ptv.createImage(new FilteredImageSource(imgStrip.getSource(), crop));
            this.ptv.prepareImage(this.buttons[i], this.ptv);
        }
        this.extractColors(imgStrip);
    }
    
    public void mouseDown(final int i, final int j) {
        this.justPressedHSButton = false;
        if (this.overPlusButton(i, j)) {
            this.justPressedHSButton = false;
            this.curStatusPlus = this.BTN_STATUS_PRESSED;
            (this.zoomThread = new Thread(this, "zoomin")).start();
        }
        if (this.overMinusButton(i, j)) {
            this.justPressedHSButton = false;
            this.curStatusMinus = this.BTN_STATUS_PRESSED;
            (this.zoomThread = new Thread(this, "zoomout")).start();
        }
        if (this.ptv.numhs > 0 && this.overHSButton(i, j)) {
            this.justPressedHSButton = !this.isHSButtonPressed;
            Label_0167: {
                if (!this.isHSButtonPressed) {
                    this.curStatusHS = this.BTN_STATUS_PRESSED_OVER;
                    this.isHSButtonPressed = !this.isHSButtonPressed;
                    this.ptv.showHS();
                    break Label_0167;
                }
                break Label_0167;
            }
            goto Label_0168;
        }
    }
    
    public void mouseDrag(final int i, final int j) {
        if (!this.overPlusButton(i, j) && this.curStatusPlus != this.BTN_STATUS_NORMAL) {
            this.curStatusPlus = this.BTN_STATUS_NORMAL;
            this.zoomThread = null;
            this.ptv.repaint();
            goto Label_0041;
        }
        if (!this.overMinusButton(i, j) && this.curStatusMinus != this.BTN_STATUS_NORMAL) {
            this.curStatusMinus = this.BTN_STATUS_NORMAL;
            this.zoomThread = null;
            this.ptv.repaint();
            goto Label_0083;
        }
    }
    
    public void mouseExit(final int i, final int j) {
        this.curStatusPlus = this.BTN_STATUS_NORMAL;
        this.curStatusMinus = this.BTN_STATUS_NORMAL;
        if (this.ptv.numhs > 0) {
            if (this.isHSButtonPressed) {
                this.curStatusHS = this.BTN_STATUS_PRESSED;
            }
            else {
                this.curStatusHS = this.BTN_STATUS_NORMAL;
            }
        }
        this.ptv.repaint();
    }
    
    public void mouseMove(final int i, final int j) {
        if (this.overPlusButton(i, j)) {
            if (this.curStatusPlus != this.BTN_STATUS_OVER) {
                this.curStatusPlus = this.BTN_STATUS_OVER;
                this.ptv.repaint();
            }
        }
        else if (this.curStatusPlus != this.BTN_STATUS_NORMAL) {
            this.curStatusPlus = this.BTN_STATUS_NORMAL;
            this.ptv.repaint();
        }
        if (this.overMinusButton(i, j)) {
            if (this.curStatusMinus != this.BTN_STATUS_OVER) {
                this.curStatusMinus = this.BTN_STATUS_OVER;
                this.ptv.repaint();
            }
        }
        else if (this.curStatusMinus != this.BTN_STATUS_NORMAL) {
            this.curStatusMinus = this.BTN_STATUS_NORMAL;
            this.ptv.repaint();
        }
        if (this.ptv.numhs > 0) {
            if (this.overHSButton(i, j)) {
                if (!this.isHSButtonPressed && this.curStatusHS != this.BTN_STATUS_OVER) {
                    this.curStatusHS = this.BTN_STATUS_OVER;
                    this.ptv.repaint();
                }
                else if (this.isHSButtonPressed && this.curStatusHS != this.BTN_STATUS_PRESSED_OVER) {
                    this.curStatusHS = this.BTN_STATUS_PRESSED_OVER;
                    this.ptv.repaint();
                }
            }
            else if (!this.isHSButtonPressed && this.curStatusHS != this.BTN_STATUS_NORMAL) {
                this.curStatusHS = this.BTN_STATUS_NORMAL;
                this.ptv.repaint();
            }
            else if (this.isHSButtonPressed && this.curStatusHS != this.BTN_STATUS_PRESSED) {
                this.curStatusHS = this.BTN_STATUS_PRESSED;
                this.ptv.repaint();
            }
        }
    }
    
    public void mouseUp(final int i, final int j) {
        if (this.overPlusButton(i, j)) {
            this.curStatusPlus = this.BTN_STATUS_OVER;
            this.zoomThread = null;
        }
        if (this.overMinusButton(i, j)) {
            this.curStatusMinus = this.BTN_STATUS_OVER;
            this.zoomThread = null;
        }
        if (this.ptv.numhs > 0 && (this.overHSButton(i, j) && (this.isHSButtonPressed && !this.justPressedHSButton))) {
            this.curStatusHS = this.BTN_STATUS_OVER;
            this.isHSButtonPressed = !this.isHSButtonPressed;
            this.ptv.hideHS();
            goto Label_0111;
        }
    }
    
    public void notifyEndPaint() {
        this.paintFinished = true;
    }
    
    protected boolean overButtonN(final int i, final int j, final int nButton) {
        return i >= this.xTlb + this.X_OFFSET + 1 + this.W_BUTTON * nButton && i <= this.xTlb + this.X_OFFSET + this.W_BUTTON - 2 + this.W_BUTTON * nButton && j >= this.yTlb + this.Y_OFFSET + 1 && j <= this.yTlb + this.Y_OFFSET + this.H_BUTTON - 2;
    }
    
    protected boolean overHSButton(final int i, final int j) {
        return this.overButtonN(i, j, 2);
    }
    
    protected boolean overMinusButton(final int i, final int j) {
        return this.overButtonN(i, j, 1);
    }
    
    protected boolean overPlusButton(final int i, final int j) {
        return this.overButtonN(i, j, 0);
    }
    
    public void paint(final Graphics g) {
        if (this.msgFont == null) {
            this.createFont(g);
        }
        this.drawToolbar();
        this.drawProgressBar();
        g.drawImage(this.tlbImage, this.xTlb, this.yTlb, this.ptv);
        if (this.msg != "") {
            final Color curColor = g.getColor();
            g.setColor(this.textColor);
            final Font curFont = g.getFont();
            g.setFont(this.msgFont);
            g.drawString(this.msg, this.xMessage, this.yMessage);
            g.setFont(curFont);
            g.setColor(curColor);
        }
    }
    
    public void run() {
        final int ptvQuality = this.ptv.getQuality();
        if (ptvQuality > 3) {
            this.ptv.setQuality(3);
        }
        final Thread myThread = Thread.currentThread();
        while (this.zoomThread == myThread) {
            long t = System.currentTimeMillis();
            this.paintFinished = false;
            if (myThread.getName().equals("zoomin")) {
                this.ptv.ZoomIn();
            }
            if (myThread.getName().equals("zoomout")) {
                this.ptv.ZoomOut();
            }
            Thread.yield();
            while (!this.paintFinished) {
                try {
                    Thread.sleep(20L);
                }
                catch (Exception e) {}
            }
            t = System.currentTimeMillis() - t;
            if (t < 50L) {
                try {
                    Thread.sleep(50L - t);
                }
                catch (Exception e) {}
            }
        }
        if (ptvQuality > 3) {
            this.ptv.setQuality(ptvQuality);
            this.ptv.repaint();
        }
    }
    
    public void setBarPerc(final int perc) {
        this.barPerc = perc;
    }
    
    public void setMessage(final String msg, final Color textColor) {
        this.msg = msg;
        this.textColor = textColor;
    }
    
    public void setMessage(final String msg) {
        this.msg = msg;
        this.textColor = this.textDefaultColor;
    }
    
    public void setMsgBold(final boolean isBold) {
        this.msgBold = isBold;
    }
    
    public void setToolbarDescrX(final int x) {
        if (x > 0) {
            this.xMessage = x;
        }
    }
    
    public void setViewerSize(final int wViewer, final int hViewer, final int wx, final int wy) {
        this.width = wViewer;
        this.xTlb = wx;
        this.yTlb = hViewer - this.height + wy;
        this.barX = this.BAR_X_OFFSET;
        this.barY = this.BAR_Y_OFFSET;
        this.barTotWidth = this.width - this.BAR_X_OFFSET - 9;
        this.tlbImage = this.ptv.createImage(this.width, this.height);
        this.tlbGraph = this.tlbImage.getGraphics();
    }
    
    public void syncHSButton() {
        if (this.ptv.numhs == 0) {
            return;
        }
        if (this.ptv.showhs) {
            this.isHSButtonPressed = true;
            if (this.curStatusHS == this.BTN_STATUS_OVER || this.curStatusHS == this.BTN_STATUS_PRESSED_OVER) {
                this.curStatusHS = this.BTN_STATUS_PRESSED_OVER;
            }
            else {
                this.curStatusHS = this.BTN_STATUS_PRESSED;
            }
        }
        else {
            this.isHSButtonPressed = false;
            if (this.curStatusHS == this.BTN_STATUS_OVER || this.curStatusHS == this.BTN_STATUS_PRESSED_OVER) {
                this.curStatusHS = this.BTN_STATUS_OVER;
            }
            else {
                this.curStatusHS = this.BTN_STATUS_NORMAL;
            }
        }
    }
    
    public void toggleHSButton() {
        if (this.ptv.numhs == 0) {
            return;
        }
        if (this.isHSButtonPressed) {
            if (this.curStatusHS == this.BTN_STATUS_PRESSED_OVER) {
                this.curStatusHS = this.BTN_STATUS_OVER;
            }
            else {
                this.curStatusHS = this.BTN_STATUS_NORMAL;
            }
        }
        else if (this.curStatusHS == this.BTN_STATUS_OVER) {
            this.curStatusHS = this.BTN_STATUS_PRESSED_OVER;
        }
        else {
            this.curStatusHS = this.BTN_STATUS_PRESSED;
        }
        this.isHSButtonPressed = !this.isHSButtonPressed;
    }
}
