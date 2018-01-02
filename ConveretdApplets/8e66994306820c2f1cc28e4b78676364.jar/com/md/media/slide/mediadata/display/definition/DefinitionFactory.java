// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.display.definition;

import a.b.o.a.a.e;
import a.b.o.a.a.c;
import a.b.o.a.a.f;
import a.b.o.a.a.h;
import a.b.o.a.a.d;

public class DefinitionFactory implements d
{
    private static DefinitionFactory a;
    
    public h a() {
        return new TextDef();
    }
    
    public h a(final String s) {
        return new TextDef(s);
    }
    
    public f a(final byte[] array) {
        return new PictureDataDef(array);
    }
    
    public c b() {
        return new ColorDef();
    }
    
    public e c() {
        return new FontDef();
    }
    
    static {
        DefinitionFactory.a = new DefinitionFactory();
    }
}
