// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.b.a;

import java.awt.image.ImageObserver;
import java.awt.Image;
import com.daysofwonder.b.b;

public class a implements b
{
    private Image a;
    
    public a(final Image a) {
        this.a = a;
    }
    
    public com.daysofwonder.b.a a() {
        return new com.daysofwonder.b.a.b(this.a.getGraphics());
    }
    
    public int a(final Object o) {
        return this.a.getWidth((ImageObserver)o);
    }
    
    public int b(final Object o) {
        return this.a.getHeight((ImageObserver)o);
    }
    
    public Image b() {
        return this.a;
    }
    
    public void c() {
        this.a.flush();
    }
}
