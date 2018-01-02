// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.a;

import java.awt.Stroke;
import java.awt.Shape;
import java.awt.Color;

public abstract class a implements k
{
    protected Color a;
    protected Color b;
    protected Color c;
    private Color f;
    protected Shape d;
    protected Stroke e;
    private int g;
    
    protected a(final Shape d, final Stroke e, final int g, final Color color, final Color color2) {
        this.c = Color.white;
        this.f = Color.black;
        this.a = Color.white;
        this.b = Color.black;
        this.d = d;
        this.e = e;
        this.g = g;
        this.c = color;
        this.f = color2;
        this.a = color;
        this.b = color2;
    }
    
    public final Color a() {
        return this.a;
    }
    
    public final Color b() {
        return this.b;
    }
    
    public final Color c() {
        return this.c;
    }
    
    public final Color d() {
        return this.f;
    }
    
    public final Shape g_() {
        return this.d;
    }
    
    public final Stroke f() {
        return this.e;
    }
    
    public final int g() {
        return this.g;
    }
    
    public void a(final Color a) {
        this.a = a;
    }
    
    public void b(final Color b) {
        this.b = b;
    }
    
    public void a(final Stroke e) {
        this.e = e;
    }
}
