// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import java.util.Iterator;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class k
{
    private List a;
    private long b;
    private boolean c;
    
    public k(final long b) {
        this.b = b;
        this.a = new ArrayList();
    }
    
    public k(final long b, final s... array) {
        this.b = b;
        Collections.addAll(this.a = new ArrayList(), array);
    }
    
    public k(final long n, final BufferedImage bufferedImage) {
        this(n, new s[] { new s(bufferedImage) });
        this.c = true;
    }
    
    public List a() {
        return this.a;
    }
    
    public long b() {
        return this.b;
    }
    
    public boolean c() {
        return this.c;
    }
    
    public BufferedImage d() {
        if (!this.c()) {
            return null;
        }
        return this.a.get(0).c;
    }
    
    public void a(final BufferedImage bufferedImage) {
        final Graphics graphics = bufferedImage.getGraphics();
        for (final s s : this.a) {
            graphics.drawImage(s.c, s.a, s.b, null);
        }
        graphics.dispose();
    }
}
