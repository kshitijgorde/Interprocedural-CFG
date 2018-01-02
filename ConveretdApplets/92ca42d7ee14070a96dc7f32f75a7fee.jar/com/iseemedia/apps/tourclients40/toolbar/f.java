// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.toolbar;

import java.awt.Insets;
import java.awt.Component;
import java.awt.Image;
import com.iseemedia.apps.tourclients40.players.h;
import java.awt.Panel;

public final class f extends Panel
{
    private g a;
    private e[] b;
    private c c;
    private boolean d;
    private a e;
    private h f;
    
    public f(final g a, final boolean d, final a e, final h f) {
        this.b = new e[3];
        this.f = f;
        this.a = a;
        this.d = d;
        this.e = e;
    }
    
    public final void a(final int n) {
        this.c.a(n);
    }
    
    public final void a() {
        this.d();
        this.e();
    }
    
    public final void b() {
        this.f = null;
        this.a = null;
        this.c.a();
        this.removeAll();
        this.c = null;
        for (int i = 0; i < 3; ++i) {
            this.b[i].a();
            this.b[i] = null;
        }
    }
    
    private void d() {
        final String[] array = { "Zoom In", "Zoom Out", "Pan", "Rotate" };
        Image[] array2;
        Image[] array3;
        if (this.d) {
            array2 = new Image[] { com.iseemedia.apps.tourclients40.toolbar.a.a("zoomIn_down"), com.iseemedia.apps.tourclients40.toolbar.a.a("zoomOut_down"), com.iseemedia.apps.tourclients40.toolbar.a.a("pan_down") };
            array3 = new Image[] { com.iseemedia.apps.tourclients40.toolbar.a.a("zoomIn_up"), com.iseemedia.apps.tourclients40.toolbar.a.a("zoomOut_up"), com.iseemedia.apps.tourclients40.toolbar.a.a("pan_up") };
        }
        else {
            array2 = new Image[] { com.iseemedia.apps.tourclients40.toolbar.a.a("zoomIn_down"), com.iseemedia.apps.tourclients40.toolbar.a.a("zoomOut_down"), com.iseemedia.apps.tourclients40.toolbar.a.a("rotate_down") };
            array3 = new Image[] { com.iseemedia.apps.tourclients40.toolbar.a.a("zoomIn_up"), com.iseemedia.apps.tourclients40.toolbar.a.a("zoomOut_up"), com.iseemedia.apps.tourclients40.toolbar.a.a("rotate_up") };
        }
        this.add(this.c = new c(3, array, array2, array3, this, this.e, this.f));
        this.c.reshape(0, 0, this.e.b * 3, this.e.c);
    }
    
    private void e() {
        final String[] array = { "Resets image to initial view", "", "Help" };
        final Image[] array2 = { com.iseemedia.apps.tourclients40.toolbar.a.a("reset_down"), com.iseemedia.apps.tourclients40.toolbar.a.a("fill"), com.iseemedia.apps.tourclients40.toolbar.a.a("info_down") };
        final Image[] array3 = { com.iseemedia.apps.tourclients40.toolbar.a.a("reset_up"), com.iseemedia.apps.tourclients40.toolbar.a.a("fill"), com.iseemedia.apps.tourclients40.toolbar.a.a("info_up") };
        final Image[] array4 = { com.iseemedia.apps.tourclients40.toolbar.a.a("reset_up"), com.iseemedia.apps.tourclients40.toolbar.a.a("fill"), com.iseemedia.apps.tourclients40.toolbar.a.a("info_up") };
        final boolean[] array5 = { false, false, false };
        for (int i = 0; i < 3; ++i) {
            this.add(this.b[i] = new e(array[i], i, array2[i], array3[i], array4[i], array5[i], this, this.f));
            this.b[i].reshape(this.e.b * (3 + i), 0, this.e.b, this.e.c);
        }
    }
    
    public final void b(final int n) {
        this.a.b(n);
    }
    
    public final void a(final int n, final int n2) {
        this.a.a(n, n2);
    }
    
    public final Insets insets() {
        return new Insets(0, 0, 0, 0);
    }
    
    public final void c() {
        this.c.b();
    }
    
    public final void a(final String s) {
        this.a.a(s);
    }
}
