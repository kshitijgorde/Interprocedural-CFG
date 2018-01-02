import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class LMResult extends Canvas implements Runnable
{
    private static final int SLEEP_TIME = 40;
    private LoveMeter loveMeter;
    private String loadingString;
    private Thread loadingThread;
    private boolean livingThread;
    private Image[][] images;
    private Color backgroundColor;
    private Color foregroundColor;
    private int genderState;
    private int resultIndex;
    
    public LMResult(final LoveMeter loveMeter, final String loadingString, final Color backgroundColor, final Color foregroundColor, final Image[] defaultImages) {
        this.livingThread = true;
        this.images = new Image[3][10];
        this.genderState = 0;
        this.resultIndex = -1;
        this.loveMeter = loveMeter;
        this.loadingString = loadingString;
        this.setBackground(this.backgroundColor = backgroundColor);
        this.foregroundColor = foregroundColor;
        for (int i = 0; i < this.images.length; ++i) {
            this.images[i][0] = defaultImages[i];
        }
        (this.loadingThread = new Thread(this)).setPriority(Thread.currentThread().getPriority() + 1);
        this.loadingThread.start();
    }
    
    public synchronized void setResult(final int genderState, final int resultIndex) {
        this.genderState = genderState;
        this.resultIndex = resultIndex;
        this.repaint();
    }
    
    public void destroy() {
        this.livingThread = false;
        if (this.loadingThread != null) {
            this.loadingThread.interrupt();
        }
    }
    
    public void run() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final String[] genderStrings = { "Male", "Female", "Couple" };
        for (int i = 0; this.livingThread && i < 3; ++i) {
            for (int j = 0; this.livingThread && j < 9; ++j) {
                final String pictureString = this.loveMeter.getParameter(genderStrings[i] + (j + 1) + "Picture");
                if (pictureString.equals("") || pictureString.equalsIgnoreCase("None")) {
                    this.images[i][j + 1] = this.images[i][0];
                }
                else {
                    mediaTracker.addImage(this.images[i][j + 1] = this.loveMeter.getImage(this.loveMeter.getCodeBase(), pictureString), i * 9 + j);
                }
            }
        }
        for (int k = 0; this.livingThread && k < 3; ++k) {
            for (int l = 0; this.livingThread && l < 9; ++l) {
                if (this.images[k][l + 1] != this.images[k][0]) {
                    try {
                        mediaTracker.waitForID(k * 9 + l);
                        if (this.images[k][l + 1].getWidth(this.loveMeter) <= 0) {
                            this.loveMeter.setErrorString("Picture " + this.loveMeter.getParameter(genderStrings[k] + (l + 1) + "Picture") + " is not present.");
                            return;
                        }
                    }
                    catch (InterruptedException interruptedException) {
                        return;
                    }
                    synchronized (this) {
                        if (this.genderState == k && this.resultIndex == l) {
                            this.repaint();
                        }
                    }
                }
            }
        }
        this.loadingThread = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.images[this.genderState][this.resultIndex + 1] == null) {
            graphics.setColor(this.backgroundColor);
            graphics.fillRect(0, 0, size.width, size.height);
            if (this.resultIndex >= 0) {
                graphics.setColor(this.foregroundColor);
                graphics.setFont(new Font("Arial", 0, 16));
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                graphics.drawString(this.loadingString, (size.width - fontMetrics.stringWidth(this.loadingString)) / 2, (size.height + fontMetrics.getAscent()) / 2);
            }
        }
        else {
            graphics.drawImage(this.images[this.genderState][this.resultIndex + 1], 0, 0, size.width, size.height, this);
        }
    }
}
