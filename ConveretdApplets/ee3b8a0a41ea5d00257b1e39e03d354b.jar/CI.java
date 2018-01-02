import java.awt.image.DataBufferInt;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import I.I;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class CI extends Canvas
{
    private static String I;
    private int clearRect;
    private FontMetrics createImage;
    private Image drawImage;
    private Image drawLine;
    private Graphics drawRect;
    private Color drawString;
    private ztmPlayer enableEvents;
    private Dimension equals;
    private boolean getData;
    private int getDataBuffer;
    private int getFont;
    private String getFontMetrics;
    private Color getGraphics;
    private int getMaxDescent;
    private int getRaster;
    
    CI(final ztmPlayer enableEvents) {
        this.setBackground(this.drawString = enableEvents.F);
        this.equals = new Dimension();
        this.getGraphics = enableEvents.J;
        this.enableEvents = enableEvents;
        this.enableEvents(-1L);
    }
    
    final synchronized void I(final Image drawImage) {
        this.drawImage = drawImage;
        this.repaint();
    }
    
    final synchronized void I(final String getFontMetrics) {
        this.getFontMetrics = getFontMetrics;
        this.I();
        this.repaint();
    }
    
    private synchronized void I() {
        if (null != this.getFontMetrics && null != this.createImage) {
            this.getMaxDescent = this.equals.width - this.createImage.stringWidth(this.getFontMetrics) >> 1;
            this.getRaster = this.equals.height - this.createImage.getMaxDescent();
        }
    }
    
    public final Dimension Z() {
        return new Dimension(this.enableEvents.N, this.enableEvents.O);
    }
    
    public final void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public final synchronized void update(final Graphics graphics) {
        if (null == graphics) {
            return;
        }
        try {
            if (!this.equals.equals(this.getSize())) {
                this.equals = this.getSize();
                this.drawLine = this.createImage(this.equals.width, this.equals.height);
                this.drawRect = this.drawLine.getGraphics();
                int n = this.equals.width * 504 / (10 * graphics.getFontMetrics(new Font(CI.I, this.clearRect, 72)).stringWidth(I.I.I(704)));
                if (n < 16) {
                    this.clearRect = 0;
                }
                else {
                    n = 16;
                    this.clearRect = 1;
                }
                this.drawRect.setFont(new Font(CI.I, this.clearRect, n));
                this.createImage = this.getFontMetrics(this.drawRect.getFont());
                this.I();
                final int width = this.equals.width;
                final ztmPlayer enableEvents = this.enableEvents;
                this.getDataBuffer = width - 30 - 1;
                final int height = this.equals.height;
                final ztmPlayer enableEvents2 = this.enableEvents;
                this.getFont = height - 30 - 1;
            }
            if (null != this.drawImage) {
                this.getData = (this.equals.width != this.drawImage.getWidth(null));
                if (this.getData) {
                    this.drawRect.drawImage(this.drawImage, 0, 0, this.equals.width, this.equals.height, this.drawString, null);
                }
                else {
                    this.drawRect.drawImage(this.drawImage, 0, 0, this.drawString, null);
                }
            }
            else {
                this.drawRect.clearRect(0, 0, this.equals.width, this.equals.height);
            }
            if (null != this.getFontMetrics) {
                this.drawRect.setColor(this.getGraphics);
                this.drawRect.drawString(this.getFontMetrics, this.getMaxDescent, this.getRaster);
            }
            if (this.enableEvents.c) {
                this.drawRect.setColor(Color.lightGray);
                final Graphics drawRect = this.drawRect;
                final int getDataBuffer = this.getDataBuffer;
                final int getFont = this.getFont;
                final ztmPlayer enableEvents3 = this.enableEvents;
                final int n2 = 30;
                final ztmPlayer enableEvents4 = this.enableEvents;
                drawRect.drawRect(getDataBuffer, getFont, n2, 30);
                this.drawRect.drawRect(this.getDataBuffer + 17, this.getFont + 4, 10, 10);
                this.drawRect.drawLine(this.getDataBuffer + 20, this.getFont + 5, this.getDataBuffer + 26, this.getFont + 5);
                this.drawRect.drawLine(this.getDataBuffer + 15, this.getFont + 16, this.getDataBuffer + 17, this.getFont + 16);
                this.drawRect.drawLine(this.getDataBuffer + 17, this.getFont + 16, this.getDataBuffer + 17, this.getFont + 18);
                this.drawRect.drawLine(this.getDataBuffer + 14, this.getFont + 19, this.getDataBuffer + 17, this.getFont + 16);
                this.drawRect.drawRect(this.getDataBuffer + 4, this.getFont + 19, 7, 7);
                this.drawRect.drawLine(this.getDataBuffer + 6, this.getFont + 20, this.getDataBuffer + 10, this.getFont + 20);
            }
            graphics.drawImage(this.drawLine, 0, 0, null);
        }
        catch (Exception ex) {}
    }
    
    static final Image I(final int n, final int n2, final int n3) {
        if (255 > n3) {
            return new BufferedImage(n, n2, 2);
        }
        return new BufferedImage(n, n2, 1);
    }
    
    static final int[] Z(final Image image) {
        return ((DataBufferInt)((BufferedImage)image).getRaster().getDataBuffer()).getData();
    }
    
    static {
        CI.I = I.I.I(898);
    }
}
