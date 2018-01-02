// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

import java.applet.Applet;
import com.easypano.tourweaver.b.hb;
import java.awt.Rectangle;
import com.easypano.tourweaver.b.l;
import com.easypano.tourweaver.b.ib;

public class r
{
    private q a;
    private p b;
    private h c;
    private m d;
    private g e;
    
    public r() {
        this.a = new q();
        this.b = new com.easypano.tourweaver.d.g();
        this.c = new h();
        this.d = new m(this.a);
        this.e = new g(this.a, this.d, this.c);
        this.c.setActionReceiver(this.e);
        this.c.setPlayController(this.d);
        this.b.a(this.c);
        this.b.a(this.a);
        this.a.addPlayerListener(this.e);
        this.a.setResManager(this.b);
    }
    
    public void a() {
        this.b.a();
        this.d.a();
        this.c.destroy();
        this.a.destroy();
    }
    
    public void a(final boolean hasBgSound) {
        this.e.setHasBgSound(hasBgSound);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        final boolean v = g.v;
        final ib mainWindow;
        final ib ib = mainWindow = this.c.getMainWindow();
        if (!v) {
            if (mainWindow == null) {
                return;
            }
            ib.setBounds(n, n2, n3, n4);
        }
        final l f = mainWindow.f();
        if (v || f != null) {
            f.setBounds(0, 0, n3, n4);
        }
    }
    
    public boolean b() {
        return this.c.hasMapViewer();
    }
    
    public boolean c() {
        return this.c.hasSceneViewer();
    }
    
    public Rectangle d() {
        final ib mainWindow = this.c.getMainWindow();
        if (!g.v && mainWindow == null) {
            return null;
        }
        return mainWindow.getBounds();
    }
    
    public void a(final String s) {
        this.e.inputScript(s);
    }
    
    public ib e() {
        return this.c.getMainWindow();
    }
    
    public hb f() {
        return this.c.getLoadingWindow();
    }
    
    public h g() {
        return this.c;
    }
    
    public p h() {
        return this.b;
    }
    
    public n i() {
        return this.a;
    }
    
    public void a(final Applet applet) {
        this.e.setApplet(applet);
    }
    
    public g j() {
        return this.e;
    }
    
    public q k() {
        return this.a;
    }
}
