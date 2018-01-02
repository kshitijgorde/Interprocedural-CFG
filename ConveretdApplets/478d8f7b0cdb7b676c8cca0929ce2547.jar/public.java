import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

public class public
{
    public static final int Ba = 1;
    public static final int Ca = 2;
    private int Da;
    private double Ea;
    private double Fa;
    private double Ga;
    private interface Ha;
    private ImageObserver xa;
    
    public public(final interface ha, final int da, final ImageObserver xa) {
        this.Ha = ha;
        this.Da = da;
        this.xa = xa;
        this.Ga = 0.0;
    }
    
    public void _(final Graphics2D graphics2D) {
        int n;
        int n2;
        if (this.Da == 1) {
            n = this.l();
            n2 = this.m();
        }
        else {
            n = this.n();
            n2 = this.c();
        }
        final AffineTransform transform = graphics2D.getTransform();
        final AffineTransform transform2 = (AffineTransform)transform.clone();
        transform2.rotate(Math.toRadians(this.Ga), this.Ha.d(), this.Ha.e());
        graphics2D.setTransform(transform2);
        graphics2D.drawImage(this.Ha._(), n, n2, this.xa);
        graphics2D.setTransform(transform);
    }
    
    public void b(final double ga) {
        this.Ga = ga;
    }
    
    public int l() {
        return this.Ha.d() - this.Ha._().getWidth(this.xa) / 2;
    }
    
    public int m() {
        return this.Ha.e() - this.Ha._().getHeight(this.xa);
    }
    
    public int n() {
        return this.Ha.d() - this.Ha._().getWidth(this.xa) / 2;
    }
    
    public int c() {
        return this.Ha.e() - this.Ha._().getHeight(this.xa) / 2;
    }
}
