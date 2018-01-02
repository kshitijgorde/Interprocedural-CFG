// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.c;

import java.awt.Image;
import java.awt.Component;

public abstract class f
{
    public static final double a = 3.141592653589793;
    public static final double b = 6.283185307179586;
    public static final double c = 1.5707963267948966;
    public static final double d = 0.017453292519943295;
    public static final double e = 0.004363323129985824;
    int f;
    int g;
    public static boolean h;
    
    public f() {
        this.f = -999;
        this.g = -999;
    }
    
    public abstract void a(final int p0, final int p1, final int[] p2, final Component p3);
    
    public abstract boolean a(final int p0, final int p1, final boolean p2);
    
    public Image a(final Component component, final boolean b) {
        return null;
    }
    
    public abstract void a(final c[] p0, final int p1);
    
    public abstract void a(final int[] p0, final int p1, final int p2, final int p3, final int p4);
    
    public abstract void a(final int p0, final int p1, final double p2, final double p3, final double p4, final double p5, final double p6, final double p7, final double p8, final double p9, final double p10, final double p11, final double p12, final boolean p13, final int p14, final int p15, final Component p16);
    
    public abstract double a(final double p0);
    
    public abstract double b(final double p0);
    
    public abstract double c(final double p0);
    
    public abstract void a(final int p0);
    
    public abstract void a(final boolean p0);
    
    public abstract void a();
    
    public abstract double b();
    
    public abstract double c();
    
    public abstract double d();
    
    public abstract void a(final int p0, final int p1);
    
    public int e() {
        return this.f;
    }
    
    public int f() {
        return this.g;
    }
}
