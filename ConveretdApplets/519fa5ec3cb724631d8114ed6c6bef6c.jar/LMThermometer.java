import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class LMThermometer extends Canvas implements Runnable
{
    private static final int SLEEP_TIME = 40;
    private static final int MAX_PIXELS = 165;
    private LoveMeter loveMeter;
    private Thread thread;
    private Image bufferImage;
    private Graphics bufferGraphics;
    private Dimension bufferSize;
    private volatile boolean livingThread;
    private Image image;
    private Color backgroundColor;
    private Color foregroundColor;
    private volatile int resultPixel;
    private volatile int currentPixel;
    
    public LMThermometer(final LoveMeter loveMeter, final Image image, final Color backgroundColor, final Color foregroundColor) {
        this.bufferSize = null;
        this.livingThread = true;
        this.resultPixel = 0;
        this.currentPixel = 0;
        this.loveMeter = loveMeter;
        this.image = image;
        this.setBackground(this.backgroundColor = backgroundColor);
        this.foregroundColor = foregroundColor;
        (this.thread = new Thread(this)).setPriority(Thread.currentThread().getPriority() - 1);
        this.thread.start();
    }
    
    public synchronized void resetResult() {
        this.resultPixel = 0;
        this.currentPixel = 0;
        this.repaint();
    }
    
    public synchronized void goToResult(final int endResult) {
        if (endResult < 0) {
            this.resultPixel = 0;
        }
        else {
            this.resultPixel = 5 + endResult * 20;
        }
        this.notify();
    }
    
    public synchronized void destroy() {
        this.livingThread = false;
        this.thread.interrupt();
    }
    
    public void run() {
        while (this.livingThread) {
            synchronized (this) {
                while (this.livingThread && this.currentPixel == this.resultPixel) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException interruptedException) {
                        return;
                    }
                }
            }
            this.repaint();
            while (this.livingThread && this.currentPixel != this.resultPixel) {
                try {
                    Thread.sleep(40L);
                }
                catch (InterruptedException interruptedException) {
                    return;
                }
                synchronized (this) {
                    if (this.currentPixel < this.resultPixel) {
                        ++this.currentPixel;
                    }
                    else if (this.currentPixel > this.resultPixel) {
                        --this.currentPixel;
                    }
                }
                this.repaint();
            }
            synchronized (this) {
                if (!this.livingThread || this.currentPixel != this.resultPixel) {
                    continue;
                }
                if (this.resultPixel == 0) {
                    this.loveMeter.setResult(-1);
                }
                else {
                    this.loveMeter.setResult((this.resultPixel - 5) / 20);
                }
            }
        }
    }
    
    private void confirmBuffer() {
        final Dimension size = this.getSize();
        if (this.bufferSize == null || !this.bufferSize.equals(size)) {
            this.bufferImage = this.createImage(size.width, size.height);
            this.bufferGraphics = this.bufferImage.getGraphics();
            this.bufferSize = new Dimension(size);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.confirmBuffer();
        final int currentSizeHeight = this.bufferSize.height - 40 - this.currentPixel * (this.bufferSize.height - 60) / 165;
        if (this.image == null) {
            this.bufferGraphics.setColor(this.backgroundColor);
            this.bufferGraphics.fillRect(0, 0, this.bufferSize.width, this.bufferSize.height);
            this.bufferGraphics.setColor(this.foregroundColor);
            this.bufferGraphics.fillRect(5, currentSizeHeight - 2, 30, 4);
        }
        else {
            final int imageHeight = this.image.getHeight(this);
            final int currentImageHeight = imageHeight - 40 - this.currentPixel * (imageHeight - 60) / 165;
            this.bufferGraphics.drawImage(this.image, 0, 0, 40, currentSizeHeight, 0, 0, 40, currentImageHeight, this);
            this.bufferGraphics.drawImage(this.image, 0, currentSizeHeight, 40, this.bufferSize.height, 40, currentImageHeight, 80, imageHeight, this);
        }
        graphics.drawImage(this.bufferImage, 0, 0, this);
    }
}
