import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class Joystick
{
    public static Image m_imageBackground;
    public static Image m_imageKnob;
    Image m_imageBuffer;
    static int m_nXsize;
    static int m_nYsize;
    static int m_nXsizeKnob;
    static int m_nYsizeKnob;
    int m_nXdraw;
    int m_nYdraw;
    public double m_dXvalue;
    public double m_dYvalue;
    boolean m_bActive;
    boolean m_bRedraw;
    final double SHAFT_THICKNESS = 5.0;
    final Color SHAFT_COLOR;
    double[] m_dXshaft;
    double[] m_dYshaft;
    int[] m_nXshaft;
    int[] m_nYshaft;
    
    public void draw(final Graphics graphics) {
        if (this.m_bRedraw) {
            this.forceDraw(graphics);
        }
    }
    
    public synchronized void forceDraw(final Graphics graphics) {
        final double n = (Math.min(Joystick.m_nXsize, Joystick.m_nYsize) - Joystick.m_nXsizeKnob) / 2.0;
        double n2 = this.m_dXvalue * n;
        double n3 = this.m_dYvalue * n;
        final double sqrt = Math.sqrt(n2 * n2 + n3 * n3);
        double n4 = 0.0;
        double n5 = 0.0;
        if (sqrt > 0.0) {
            n5 = n2 / sqrt;
            n4 = n3 / sqrt;
        }
        if (sqrt > n) {
            n2 *= n / sqrt;
            n3 *= n / sqrt;
        }
        final int n6 = (Joystick.m_nXsize - Joystick.m_nXsizeKnob) / 2 + (int)n2;
        final int n7 = (Joystick.m_nYsize - Joystick.m_nYsizeKnob) / 2 - (int)n3;
        if (sqrt > 0.0) {
            this.m_dXshaft[0] = -n4 * 2.5;
            this.m_dYshaft[0] = n5 * 2.5;
            this.m_dXshaft[1] = n2 - n4 * 3.5;
            this.m_dYshaft[1] = n3 + n5 * 3.5;
            this.m_dXshaft[2] = n2 + n4 * 3.5;
            this.m_dYshaft[2] = n3 - n5 * 3.5;
            this.m_dXshaft[3] = n4 * 2.5;
            this.m_dYshaft[3] = -n5 * 2.5;
            int n8 = 0;
            do {
                this.m_nXshaft[n8] = Joystick.m_nXsize / 2 + (int)this.m_dXshaft[n8];
                this.m_nYshaft[n8] = Joystick.m_nYsize / 2 - (int)this.m_dYshaft[n8];
            } while (++n8 < 4);
        }
        final Graphics graphics2 = this.m_imageBuffer.getGraphics();
        graphics2.drawImage(Joystick.m_imageBackground, 0, 0, null);
        if (sqrt > 0.0) {
            graphics2.setColor(this.SHAFT_COLOR);
            graphics2.fillPolygon(this.m_nXshaft, this.m_nYshaft, 4);
        }
        graphics2.drawImage(Joystick.m_imageKnob, n6, n7, null);
        graphics2.dispose();
        graphics.drawImage(this.m_imageBuffer, this.m_nXdraw, this.m_nYdraw, null);
        this.m_bRedraw = false;
    }
    
    public Joystick(final int n, final int n2, final Component component) {
        this.SHAFT_COLOR = new Color(12632256);
        Joystick.m_nXsize = Joystick.m_imageBackground.getWidth(null);
        Joystick.m_nYsize = Joystick.m_imageBackground.getHeight(null);
        Joystick.m_nXsizeKnob = Joystick.m_imageKnob.getWidth(null);
        Joystick.m_nYsizeKnob = Joystick.m_imageKnob.getHeight(null);
        this.m_imageBuffer = component.createImage(Joystick.m_nXsize, Joystick.m_nYsize);
        this.m_nXdraw = n - Joystick.m_nXsize / 2;
        this.m_nYdraw = n2 - Joystick.m_nYsize / 2;
        this.m_dXvalue = 0.0;
        this.m_dYvalue = 0.0;
        this.m_bActive = false;
        this.m_bRedraw = true;
        this.m_dXshaft = new double[4];
        this.m_dYshaft = new double[4];
        this.m_nXshaft = new int[4];
        this.m_nYshaft = new int[4];
    }
    
    public void update(final int n, final int n2, final boolean b, final boolean b2) {
        if (n < this.m_nXdraw || n > this.m_nXdraw + Joystick.m_nXsize || n2 < this.m_nYdraw || n2 > this.m_nYdraw + Joystick.m_nYsize || !b || !b2) {
            if (this.m_bActive) {
                this.m_bActive = false;
                this.m_bRedraw = true;
                this.m_dXvalue = 0.0;
                this.m_dYvalue = 0.0;
            }
            return;
        }
        this.m_bActive = true;
        this.m_bRedraw = true;
        this.m_dXvalue = (n - this.m_nXdraw) / Joystick.m_nXsize * 2.0 - 1.0;
        this.m_dYvalue = -((n2 - this.m_nYdraw) / Joystick.m_nYsize * 2.0 - 1.0);
    }
}
