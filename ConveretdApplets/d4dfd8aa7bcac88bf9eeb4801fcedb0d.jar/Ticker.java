import java.awt.Dimension;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ticker extends Canvas implements Runnable
{
    private Thread m_Ticker;
    private Vector m_CaptionList;
    private Vector m_ColorList;
    private Image m_Image;
    private int m_Gap;
    private int m_Bullet;
    private int m_Step;
    private int m_CurrentX;
    private int m_OffsetX;
    
    private void stop() {
        if (this.m_Ticker != null) {
            this.m_Ticker.stop();
            this.m_Ticker = null;
        }
    }
    
    private void clearTicker(final Graphics graphics) {
        final Rectangle clipRect = graphics.getClipRect();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, clipRect.width, clipRect.height);
    }
    
    public void removeAllCaptions() {
        this.stop();
        this.m_CaptionList.removeAllElements();
        this.m_ColorList.removeAllElements();
    }
    
    public void reset() {
        try {
            if (this.m_CaptionList.size() == 0) {
                this.m_Image = null;
                this.repaint();
                return;
            }
            final int captionWidth = this.getCaptionWidth();
            final int n = (captionWidth < this.size().width) ? this.size().width : captionWidth;
            final int height = this.size().height;
            this.m_Image = this.createImage(n, height);
            final Graphics graphics = this.m_Image.getGraphics();
            graphics.setFont(this.getFont());
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, n, height);
            int n2 = 0;
            for (int i = 0; i < this.m_CaptionList.size(); ++i) {
                graphics.setColor((Color)this.m_ColorList.elementAt(i));
                final String s = this.m_CaptionList.elementAt(i);
                graphics.fillRect(n2 + this.m_Gap, (height - this.m_Bullet) / 2, this.m_Bullet, this.m_Bullet);
                graphics.drawString(s, n2 + 2 * this.m_Gap + this.m_Bullet, (height - fontMetrics.getHeight()) / 2 + fontMetrics.getHeight() - 2);
                n2 += fontMetrics.stringWidth(s) + 2 * this.m_Gap + this.m_Bullet;
            }
            this.update(this.getGraphics());
            this.start();
        }
        catch (Exception ex) {}
    }
    
    public Ticker() {
        this.m_Ticker = null;
        this.m_Image = null;
        this.m_Gap = 5;
        this.m_Bullet = 4;
        this.m_Step = 1;
        this.m_CaptionList = new Vector();
        this.m_ColorList = new Vector();
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    private void drawTicker(final Graphics graphics) {
        try {
            if (this.m_Image == null) {
                this.clearTicker(graphics);
                return;
            }
            final int width = this.size().width;
            final int width2 = this.m_Image.getWidth(this);
            if (width2 <= width) {
                graphics.drawImage(this.m_Image, 0, 0, this);
                this.stop();
                return;
            }
            int n;
            int n2;
            if (this.m_Step > 0) {
                n = width2 - this.m_OffsetX;
                n2 = -this.m_OffsetX;
            }
            else {
                n = -this.m_OffsetX;
                n2 = width2 - this.m_OffsetX;
            }
            graphics.drawImage(this.m_Image, n, 0, this);
            graphics.drawImage(this.m_Image, n2, 0, this);
        }
        catch (Exception ex) {}
    }
    
    public void update(final Graphics graphics) {
        this.drawTicker(graphics);
    }
    
    private int getCaptionWidth() {
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.getFont());
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        String string = "";
        for (int i = 0; i < this.m_CaptionList.size(); ++i) {
            string += (String)this.m_CaptionList.elementAt(i);
        }
        return fontMetrics.stringWidth(string) + this.m_CaptionList.size() * (2 * this.m_Gap + this.m_Bullet);
    }
    
    private void start() {
        if (this.m_Ticker == null) {
            (this.m_Ticker = new Thread(this)).start();
        }
    }
    
    public boolean mouseDown(final Event event, final int currentX, final int n) {
        this.m_CurrentX = currentX;
        return true;
    }
    
    public void addCaption(final String s, final Color color) {
        this.m_CaptionList.addElement(s);
        this.m_ColorList.addElement(color);
    }
    
    public Dimension preferredSize() {
        return this.size();
    }
    
    private synchronized void setOffsetX(final int offsetX) {
        this.m_OffsetX = offsetX;
    }
    
    public void run() {
        try {
            final Graphics graphics = this.getGraphics();
            this.setOffsetX(0);
            final int width = this.m_Image.getWidth(this);
            this.paint(graphics);
            while (this.m_Ticker != null) {
                try {
                    final int n = this.m_OffsetX + this.m_Step;
                    int offsetX;
                    if (this.m_Step > 0) {
                        offsetX = ((n > width) ? 0 : n);
                    }
                    else {
                        offsetX = ((n < 0) ? width : n);
                    }
                    this.setOffsetX(offsetX);
                    this.drawTicker(graphics);
                    Thread.sleep(25L);
                }
                catch (InterruptedException ex) {
                    this.stop();
                }
            }
        }
        catch (Exception ex2) {}
        this.stop();
    }
    
    public boolean mouseDrag(final Event event, final int currentX, final int n) {
        final int n2 = currentX - this.m_CurrentX;
        this.m_OffsetX -= n2;
        this.m_Step = ((n2 > 0) ? -1 : 1);
        this.m_CurrentX = currentX;
        return true;
    }
}
