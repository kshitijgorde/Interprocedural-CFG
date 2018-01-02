import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class toolbar implements Runnable
{
    protected ptviewer ptv;
    protected int width;
    protected int height;
    protected int xTlb;
    protected int yTlb;
    protected Image tlbImage;
    protected Graphics tlbGraph;
    protected Image[] buttons;
    protected int BTN_PLUS;
    protected int BTN_PLUS_OVER;
    protected int BTN_PLUS_PRESSED;
    protected int BTN_MINUS;
    protected int BTN_MINUS_OVER;
    protected int BTN_MINUS_PRESSED;
    protected int BTN_HS;
    protected int BTN_HS_OVER;
    protected int BTN_HS_PRESSED;
    protected int BTN_HS_PRESSED_OVER;
    protected int BTN_HS_DISABLED;
    protected int N_BUTTONS;
    protected int W_BUTTON;
    protected int H_BUTTON;
    protected int X_OFFSET;
    protected int Y_OFFSET;
    protected int BTN_STATUS_NORMAL;
    protected int BTN_STATUS_OVER;
    protected int BTN_STATUS_PRESSED;
    protected int BTN_STATUS_PRESSED_OVER;
    protected int BTN_STATUS_DISABLED;
    protected int curStatusPlus;
    protected int curStatusMinus;
    protected int curStatusHS;
    protected boolean isHSButtonPressed;
    protected boolean justPressedHSButton;
    private Thread zoomThread;
    protected Color barColor;
    protected Color borderColor;
    protected Color bgColor;
    protected Color textColor;
    protected Color textDefaultColor;
    protected int BAR_X_OFFSET;
    protected int BAR_Y_OFFSET;
    protected int BAR_HEIGHT;
    protected int barX;
    protected int barY;
    protected int barTotWidth;
    protected int barPerc;
    protected boolean paintFinished;
    protected String msg;
    Font msgFont;
    int xMessage;
    int yMessage;
    boolean msgBold;
    
    public toolbar(final ptviewer ptv, final String s) {
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
        this.loadButtonImages(s);
        this.xMessage = this.BAR_X_OFFSET;
    }
    
    protected void createFont(final Graphics graphics) {
        boolean b = false;
        final Font font = graphics.getFont();
        final int n = 19;
        final int n2 = 5;
        final Font font2 = graphics.getFont();
        int size = font2.getSize();
        final String name = font2.getName();
        int style = font2.getStyle();
        if (this.msgBold) {
            style = 1;
        }
        graphics.setFont(new Font(name, style, size));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        while (!b) {
            if (fontMetrics.getHeight() <= n) {
                b = true;
            }
            else if (size <= n2) {
                b = true;
            }
            else {
                graphics.setFont(new Font(name, style, --size));
                fontMetrics = graphics.getFontMetrics();
            }
        }
        this.yMessage = this.yTlb + this.height - (this.height - fontMetrics.getAscent()) / 2 - 2;
        this.msgFont = graphics.getFont();
        graphics.setFont(font);
    }
    
    public void setMessage(final String msg) {
        this.msg = msg;
        this.textColor = this.textDefaultColor;
    }
    
    public void setMessage(final String msg, final Color textColor) {
        this.msg = msg;
        this.textColor = textColor;
    }
    
    public void run() {
        final int quality = this.ptv.getQuality();
        if (quality > 3) {
            this.ptv.setQuality(3);
        }
        final Thread currentThread = Thread.currentThread();
        while (this.zoomThread == currentThread) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.paintFinished = false;
            if (currentThread.getName().equals("zoomin")) {
                this.ptv.ZoomIn();
            }
            if (currentThread.getName().equals("zoomout")) {
                this.ptv.ZoomOut();
            }
            Thread.yield();
            while (!this.paintFinished) {
                try {
                    Thread.sleep(20L);
                }
                catch (Exception ex) {}
            }
            final long n = System.currentTimeMillis() - currentTimeMillis;
            if (n < 50L) {
                try {
                    Thread.sleep(50L - n);
                }
                catch (Exception ex2) {}
            }
        }
        if (quality > 3) {
            this.ptv.setQuality(quality);
            this.ptv.repaint();
        }
    }
    
    public void notifyEndPaint() {
        this.paintFinished = true;
    }
    
    public void setViewerSize(final int width, final int n, final int xTlb, final int n2) {
        this.width = width;
        this.xTlb = xTlb;
        this.yTlb = n - this.height + n2;
        this.barX = this.BAR_X_OFFSET;
        this.barY = this.BAR_Y_OFFSET;
        this.barTotWidth = this.width - this.BAR_X_OFFSET - 9;
        this.tlbImage = this.ptv.createImage(this.width, this.height);
        this.tlbGraph = this.tlbImage.getGraphics();
    }
    
    public void setBarPerc(final int barPerc) {
        this.barPerc = barPerc;
    }
    
    public void setToolbarDescrX(final int xMessage) {
        if (xMessage > 0) {
            this.xMessage = xMessage;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.msgFont == null) {
            this.createFont(graphics);
        }
        this.drawToolbar();
        this.drawProgressBar();
        graphics.drawImage(this.tlbImage, this.xTlb, this.yTlb, this.ptv);
        if (this.msg != "") {
            final Color color = graphics.getColor();
            graphics.setColor(this.textColor);
            final Font font = graphics.getFont();
            graphics.setFont(this.msgFont);
            graphics.drawString(this.msg, this.xMessage, this.yMessage);
            graphics.setFont(font);
            graphics.setColor(color);
        }
    }
    
    public int getHeight() {
        return this.height;
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
    
    public void mouseMove(final int n, final int n2) {
        if (this.overPlusButton(n, n2)) {
            if (this.curStatusPlus != this.BTN_STATUS_OVER) {
                this.curStatusPlus = this.BTN_STATUS_OVER;
                this.ptv.repaint();
            }
        }
        else if (this.curStatusPlus != this.BTN_STATUS_NORMAL) {
            this.curStatusPlus = this.BTN_STATUS_NORMAL;
            this.ptv.repaint();
        }
        if (this.overMinusButton(n, n2)) {
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
            if (this.overHSButton(n, n2)) {
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
    
    public void mouseDown(final int n, final int n2) {
        this.justPressedHSButton = false;
        if (this.overPlusButton(n, n2)) {
            this.justPressedHSButton = false;
            this.curStatusPlus = this.BTN_STATUS_PRESSED;
            (this.zoomThread = new Thread(this, "zoomin")).start();
        }
        if (this.overMinusButton(n, n2)) {
            this.justPressedHSButton = false;
            this.curStatusMinus = this.BTN_STATUS_PRESSED;
            (this.zoomThread = new Thread(this, "zoomout")).start();
        }
        if (this.ptv.numhs > 0 && this.overHSButton(n, n2)) {
            this.justPressedHSButton = !this.isHSButtonPressed;
            if (!this.isHSButtonPressed) {
                this.curStatusHS = this.BTN_STATUS_PRESSED_OVER;
                this.isHSButtonPressed = !this.isHSButtonPressed;
                this.ptv.showHS();
            }
        }
    }
    
    public void mouseUp(final int n, final int n2) {
        if (this.overPlusButton(n, n2)) {
            this.curStatusPlus = this.BTN_STATUS_OVER;
            this.zoomThread = null;
        }
        if (this.overMinusButton(n, n2)) {
            this.curStatusMinus = this.BTN_STATUS_OVER;
            this.zoomThread = null;
        }
        if (this.ptv.numhs > 0 && this.overHSButton(n, n2) && this.isHSButtonPressed && !this.justPressedHSButton) {
            this.curStatusHS = this.BTN_STATUS_OVER;
            this.isHSButtonPressed = !this.isHSButtonPressed;
            this.ptv.hideHS();
        }
    }
    
    public void mouseDrag(final int n, final int n2) {
        if (!this.overPlusButton(n, n2) && this.curStatusPlus != this.BTN_STATUS_NORMAL) {
            this.curStatusPlus = this.BTN_STATUS_NORMAL;
            this.zoomThread = null;
            this.ptv.repaint();
        }
        if (!this.overMinusButton(n, n2) && this.curStatusMinus != this.BTN_STATUS_NORMAL) {
            this.curStatusMinus = this.BTN_STATUS_NORMAL;
            this.zoomThread = null;
            this.ptv.repaint();
        }
    }
    
    public void mouseExit(final int n, final int n2) {
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
    
    public void SetTextColor(final String s) {
        try {
            this.textDefaultColor = new Color(Integer.parseInt(s, 16));
        }
        catch (Exception ex) {}
    }
    
    protected boolean overPlusButton(final int n, final int n2) {
        return this.overButtonN(n, n2, 0);
    }
    
    protected boolean overMinusButton(final int n, final int n2) {
        return this.overButtonN(n, n2, 1);
    }
    
    protected boolean overHSButton(final int n, final int n2) {
        return this.overButtonN(n, n2, 2);
    }
    
    protected boolean overButtonN(final int n, final int n2, final int n3) {
        return n >= this.xTlb + this.X_OFFSET + 1 + this.W_BUTTON * n3 && n <= this.xTlb + this.X_OFFSET + this.W_BUTTON - 2 + this.W_BUTTON * n3 && n2 >= this.yTlb + this.Y_OFFSET + 1 && n2 <= this.yTlb + this.Y_OFFSET + this.H_BUTTON - 2;
    }
    
    protected void loadButtonImages(final String s) {
        Image image;
        if (s == null) {
            try {
                final MediaTracker mediaTracker = new MediaTracker(this.ptv);
                final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.getClass().getResourceAsStream("Toolbar.gif"));
                final byte[] array = new byte[10000];
                bufferedInputStream.read(array, 0, 10000);
                image = Toolkit.getDefaultToolkit().createImage(array);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForAll();
            }
            catch (Exception ex) {
                image = null;
            }
        }
        else {
            image = this.ptv.loadImage(s);
        }
        for (int i = 0; i < this.N_BUTTONS; ++i) {
            this.buttons[i] = this.ptv.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(1 + i * this.W_BUTTON, 2, this.W_BUTTON, this.H_BUTTON)));
            this.ptv.prepareImage(this.buttons[i], this.ptv);
        }
        this.extractColors(image);
    }
    
    protected void drawButton(final int n, final int n2, final int n3) {
        this.tlbGraph.drawImage(this.buttons[n], n2, n3, this.ptv);
    }
    
    protected void drawPlusButton(final int curStatusPlus) {
        this.drawButton(curStatusPlus, this.X_OFFSET, this.Y_OFFSET);
        this.curStatusPlus = curStatusPlus;
    }
    
    protected void drawMinusButton(final int curStatusMinus) {
        this.drawButton(curStatusMinus + 3, this.X_OFFSET + this.W_BUTTON + 1, this.Y_OFFSET);
        this.curStatusMinus = curStatusMinus;
    }
    
    protected void drawHSButton(final int curStatusHS) {
        this.drawButton(curStatusHS + 6, this.X_OFFSET + this.W_BUTTON * 2 + 1, this.Y_OFFSET);
        this.curStatusHS = curStatusHS;
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
    
    protected void extractColors(final Image image) {
        final int[] array = { 0 };
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, 1, 1, array, 0, 1);
        try {
            pixelGrabber.grabPixels();
            this.borderColor = new Color(array[0] >> 16 & 0xFF, array[0] >> 8 & 0xFF, array[0] & 0xFF);
        }
        catch (Exception ex) {
            this.borderColor = new Color(0, 0, 0);
        }
        final PixelGrabber pixelGrabber2 = new PixelGrabber(image, 306, 11, 1, 1, array, 0, 1);
        try {
            pixelGrabber2.grabPixels();
            this.barColor = new Color(array[0] >> 16 & 0xFF, array[0] >> 8 & 0xFF, array[0] & 0xFF);
        }
        catch (Exception ex2) {
            this.barColor = new Color(0, 60, 116);
        }
        final PixelGrabber pixelGrabber3 = new PixelGrabber(image, 298, 11, 1, 1, array, 0, 1);
        try {
            pixelGrabber3.grabPixels();
            this.bgColor = new Color(array[0] >> 16 & 0xFF, array[0] >> 8 & 0xFF, array[0] & 0xFF);
        }
        catch (Exception ex3) {
            this.bgColor = new Color(236, 233, 216);
        }
    }
    
    protected void drawProgressBar() {
        if (this.barPerc <= 0 || this.barPerc > 100) {
            return;
        }
        final int n = this.barTotWidth * this.barPerc / 100;
        this.tlbGraph.setColor(this.barColor);
        this.tlbGraph.fillRect(this.barX, this.barY, n, this.BAR_HEIGHT);
    }
    
    public void setMsgBold(final boolean msgBold) {
        this.msgBold = msgBold;
    }
}
