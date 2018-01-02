import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class drawHistogram extends Canvas
{
    int[] count;
    int bins;
    Applet applet;
    double[] theoreticalCount;
    boolean drawTheoretical;
    
    public drawHistogram(final int[] count, final int bins, final Applet applet) {
        this.applet = applet;
        this.bins = bins;
        this.count = count;
    }
    
    public drawHistogram(final int[] count, final int bins, final double[] theoreticalCount, final Applet applet) {
        this.applet = applet;
        this.bins = bins;
        this.count = count;
        this.theoreticalCount = theoreticalCount;
        this.drawTheoretical = true;
    }
    
    @Override
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final int height = size.height;
        final int n = size.width / (this.bins + 2);
        for (int i = 0; i < this.bins; ++i) {
            graphics.setColor(Color.white);
            graphics.fillRect((i + 1) * n, height - 10 - this.count[i] * 4, n, this.count[i] * 4);
            if (this.drawTheoretical) {
                final int n2 = (int)(height - 10 - this.theoreticalCount[i] * 4.0);
                graphics.setColor(Color.red);
                graphics.drawLine((i + 1) * n, n2, (i + 2) * n - 1, n2);
                graphics.drawLine((i + 1) * n, n2 + 1, (i + 2) * n - 1, n2 + 1);
            }
        }
    }
    
    public void update(final int[] count) {
        this.count = count;
        this.repaint();
    }
    
    public void update(final int[] count, final double[] theoreticalCount) {
        this.count = count;
        this.theoreticalCount = theoreticalCount;
        this.repaint();
    }
    
    public void clearHistogram(final int[] count, final int bins) {
        this.bins = bins;
        this.count = count;
        this.repaint();
    }
    
    public void clearHistogram(final int[] count, final int bins, final double[] theoreticalCount) {
        this.bins = bins;
        this.count = count;
        this.theoreticalCount = theoreticalCount;
        this.repaint();
    }
    
    @Override
    public Dimension preferredSize() {
        return new Dimension(200, 250);
    }
}
