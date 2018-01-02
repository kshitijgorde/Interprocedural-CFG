// 
// Decompiled by Procyon v0.5.30
// 

package doppler;

import java.awt.Graphics;

class DopplerWaveCrest
{
    private int x0;
    private int y0;
    private double vx;
    private double vy;
    private double t0;
    
    public DopplerWaveCrest(final double t0, final int x0, final int y0) {
        this.x0 = x0;
        this.y0 = y0;
        this.t0 = t0;
        this.vx = 0.0;
        this.vx = 0.0;
    }
    
    public DopplerWaveCrest(final double t0, final int x0, final int y0, final double vx, final double vy) {
        this.x0 = x0;
        this.y0 = y0;
        this.t0 = t0;
        this.vx = vx;
        this.vy = vy;
    }
    
    public void translate(final int n) {
        this.x0 += n;
    }
    
    public void draw(final double n, final Graphics graphics) {
        final int n2 = (int)Math.round(n - this.t0);
        graphics.drawOval(this.x0 - n2, this.y0 - n2, n2 * 2, n2 * 2);
    }
}
