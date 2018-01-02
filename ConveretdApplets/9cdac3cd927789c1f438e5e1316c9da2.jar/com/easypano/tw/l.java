// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import com.easypano.tw.c.p;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Color;
import com.easypano.tw.c.r;

public class l extends e
{
    private TWViewer l;
    protected r m;
    
    public l(final TWViewer l) {
        this.m = null;
        this.l = l;
        this.a(new r(this));
        this.setBackground(Color.white);
        this.addMouseListener(new ba(this));
        this.addMouseMotionListener(new bk(this));
    }
    
    public void a(final r m) {
        super.a(this.m = m);
    }
    
    public p a() {
        return this.m;
    }
    
    public TWViewer e() {
        return this.l;
    }
    
    public void destroyResource() {
        super.destroyResource();
        this.m = null;
        this.l = null;
    }
}
