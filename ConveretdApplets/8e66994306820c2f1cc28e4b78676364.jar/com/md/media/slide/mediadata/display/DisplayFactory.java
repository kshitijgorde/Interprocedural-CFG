// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.display;

import a.b.o.b.c;
import a.b.o.b.i;
import a.b.o.b.b;
import a.b.o.b.d;
import a.b.o.b.j;
import a.b.o.b.h;
import a.b.o.b.a;

public class DisplayFactory implements a
{
    private static DisplayFactory a;
    private boolean b;
    
    public DisplayFactory() {
        this.b = true;
    }
    
    public void a(final boolean b) {
        this.b = b;
    }
    
    public h a() {
        return new Text();
    }
    
    public j b() {
        return new TextChanger();
    }
    
    public d c() {
        final TextLink textLink = new TextLink();
        if (this.b) {
            textLink.c(true);
        }
        return textLink;
    }
    
    public b d() {
        return new Picture();
    }
    
    public i e() {
        return new PictureChanger();
    }
    
    public c f() {
        return new PictureLink();
    }
    
    static {
        DisplayFactory.a = new DisplayFactory();
    }
}
