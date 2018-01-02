import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class WaitAnimator extends Canvas implements Runnable
{
    private Thread m_Ticker;
    private Image m_Wait_Image;
    private Image m_OffScreen_Image;
    private int m_Width;
    private int m_Height;
    private int m_Start;
    
    public void stop() {
        if (this.m_Ticker != null) {
            this.m_Ticker.stop();
            this.m_Ticker = null;
            this.m_OffScreen_Image = null;
            this.repaint();
        }
    }
    
    public WaitAnimator(final Applet applet, final String s, final int width, final int height) {
        this.m_Ticker = null;
        if (width == 0 || height == 0) {
            this.m_Wait_Image = null;
        }
        else {
            try {
                this.m_Wait_Image = applet.getImage(applet.getCodeBase(), s);
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.m_Wait_Image, 0);
                mediaTracker.waitForAll();
                if (mediaTracker.isErrorAny()) {
                    this.m_Wait_Image = null;
                }
            }
            catch (Exception ex) {
                this.m_Wait_Image = null;
            }
            this.m_Width = width;
            this.m_Height = height;
        }
        this.m_Start = 0;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.doDraw(graphics);
    }
    
    private void doDraw(final Graphics graphics) {
        try {
            if (this.m_Wait_Image == null || graphics == null) {
                return;
            }
            final Dimension size = this.size();
            if (this.m_Ticker != null) {
                graphics.clipRect(1, 1, size.width - 2, size.height - 2);
                graphics.drawImage(this.m_OffScreen_Image, this.m_Start - this.m_Width, 0, this);
                return;
            }
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, size.width - 1, size.height - 1);
            graphics.setColor(this.getForeground());
            graphics.fillRect(1, 1, size.width - 2, size.height - 2);
        }
        catch (Exception ex) {}
    }
    
    public void start() {
        if (this.m_Wait_Image == null) {
            return;
        }
        if (this.m_Ticker == null) {
            final int n = Math.round(this.size().width / this.m_Width) + 2;
            this.m_OffScreen_Image = this.createImage(n * this.m_Width, this.m_Height);
            if (this.m_OffScreen_Image == null) {
                return;
            }
            final Graphics graphics = this.m_OffScreen_Image.getGraphics();
            int n2 = 0;
            for (int i = 0; i < n; ++i) {
                graphics.drawImage(this.m_Wait_Image, n2, 0, this);
                n2 += this.m_Width;
            }
            (this.m_Ticker = new Thread(this)).start();
        }
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.size().width, this.m_Height + 1);
    }
    
    public void run() {
        this.m_Start = 0;
        while (this.m_Ticker != null) {
            this.repaint();
            try {
                Thread.sleep(160L);
            }
            catch (InterruptedException ex) {}
            if (this.m_Start >= this.m_Width - 1) {
                this.m_Start = 0;
            }
            else {
                ++this.m_Start;
            }
        }
    }
}
