import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class p extends Class98 implements Interface9
{
    int anInt3319;
    Canvas aCanvas3320;
    private static boolean aBoolean3321;
    int anInt3322;
    long nativeid;
    private static long aLong3323;
    
    @Override
    protected final void finalize() {
        if (this.nativeid != 0L) {
            Class192.method2654(false, this);
        }
    }
    
    final void method1445(final Rectangle[] array, final int n, final int n2, final int n3) {
        try {
            synchronized (this.aCanvas3320.getTreeLock()) {
                for (final Rectangle rectangle : array) {
                    if (rectangle.width > 0 && rectangle.height > 0) {
                        this.K(rectangle.x, rectangle.y, rectangle.width, rectangle.height, n2, n3);
                    }
                }
                p.aBoolean3321 = false;
            }
        }
        catch (Exception ex) {
            this.method1446(ex);
        }
    }
    
    private final native void H(final int p0, final int p1, final int p2, final int p3);
    
    private final void method1446(final Exception ex) {
        if (!p.aBoolean3321) {
            p.aLong3323 = Class343.method3819(-47);
            p.aBoolean3321 = true;
        }
        else {
            if (Class343.method3819(-47) - p.aLong3323 >= 30000L) {
                throw new RuntimeException(ex.getMessage());
            }
            this.aCanvas3320.repaint();
        }
    }
    
    private final native void oa(final Canvas p0, final int p1, final int p2);
    
    private final native void K(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    @Override
    public final native void w(final boolean p0);
    
    final void method1447(final int n, final int n2) {
        try {
            synchronized (this.aCanvas3320.getTreeLock()) {
                final Dimension size = this.aCanvas3320.getSize();
                this.H(n, n2, size.width, size.height);
                p.aBoolean3321 = false;
            }
        }
        catch (Exception ex) {
            this.method1446(ex);
        }
    }
    
    p(final oa oa, final Canvas aCanvas3320, final int anInt3319, final int anInt3320) {
        this.aCanvas3320 = aCanvas3320;
        this.anInt3319 = anInt3319;
        this.anInt3322 = anInt3320;
        this.sa(oa, this.aCanvas3320, anInt3319, anInt3320);
    }
    
    final void method1448(final Canvas canvas, final int anInt3319, final int anInt3320) {
        this.oa(canvas, this.anInt3319 = anInt3319, this.anInt3322 = anInt3320);
    }
    
    final void method1449() {
        this.w(true);
        this.nativeid = 0L;
        this.aCanvas3320 = null;
    }
    
    private final native void sa(final oa p0, final Canvas p1, final int p2, final int p3);
    
    static {
        p.aBoolean3321 = false;
    }
}
