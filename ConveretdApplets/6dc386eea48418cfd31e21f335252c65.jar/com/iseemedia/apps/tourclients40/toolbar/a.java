// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.toolbar;

import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import com.iseemedia.apps.tourclients40.resource.b;
import java.util.Hashtable;
import java.awt.Toolkit;
import java.awt.Image;

public final class a
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public static Image l;
    private static Toolkit m;
    private static Hashtable n;
    
    public a() {
        this.d = 27;
        new b();
        this.a = com.iseemedia.apps.tourclients40.resource.b.e();
        this.b = com.iseemedia.apps.tourclients40.resource.b.f();
        this.c = com.iseemedia.apps.tourclients40.resource.b.g();
        this.e = this.d * this.b;
        this.f = com.iseemedia.apps.tourclients40.resource.b.d();
        this.g = com.iseemedia.apps.tourclients40.resource.b.k();
        this.h = com.iseemedia.apps.tourclients40.resource.b.h();
        this.i = com.iseemedia.apps.tourclients40.resource.b.i();
        this.j = com.iseemedia.apps.tourclients40.resource.b.l();
        this.k = com.iseemedia.apps.tourclients40.resource.b.m();
    }
    
    public static final Image a(final String s) {
        return a.n.get(s);
    }
    
    public static final void a(final Image l) {
        a.l = l;
    }
    
    public final void a() {
        final int[] array = new int[(this.a + this.e) * this.c];
        try {
            new PixelGrabber(com.iseemedia.apps.tourclients40.toolbar.a.l.getSource(), 0, 0, this.a + this.e, this.c, array, 0, this.a + this.e).grabPixels();
        }
        catch (Exception ex) {}
        final String[] array2 = { "logo", "pan_up", "pan_over", "pan_down", "pan_gray", "reset_up", "reset_over", "reset_down", "reset_gray", "info_up", "info_over", "info_down", "info_gray", "zoomIn_up", "zoomIn_over", "zoomIn_down", "zoomIn_gray", "zoomOut_up", "zoomOut_over", "zoomOut_down", "zoomOut_gray", "rotate_up", "rotate_over", "rotate_down", "rotate_gray", "max_zoom", "sand_watch", "fill" };
        (com.iseemedia.apps.tourclients40.toolbar.a.n = new Hashtable(array2.length)).put(array2[0], this.a(array));
        for (int i = 1; i < array2.length; ++i) {
            com.iseemedia.apps.tourclients40.toolbar.a.n.put(array2[i], this.a(array, i));
        }
    }
    
    private Image a(final int[] array) {
        final int[] array2 = new int[this.a * this.c];
        for (int i = 0; i < this.c; ++i) {
            for (int j = 0; j < this.a; ++j) {
                array2[i * this.a + j] = array[i * (this.a + this.e) + j];
            }
        }
        return com.iseemedia.apps.tourclients40.toolbar.a.m.createImage(new MemoryImageSource(this.a, this.c, array2, 0, this.a));
    }
    
    private Image a(final int[] array, final int n) {
        final int[] array2 = new int[this.b * this.c];
        for (int i = 0; i < this.c; ++i) {
            for (int j = 0; j < this.b; ++j) {
                array2[i * this.b + j] = array[i * (this.a + this.e) + (n - 1) * this.b + this.a + j];
            }
        }
        return com.iseemedia.apps.tourclients40.toolbar.a.m.createImage(new MemoryImageSource(this.b, this.c, array2, 0, this.b));
    }
    
    static {
        a.m = Toolkit.getDefaultToolkit();
    }
}
