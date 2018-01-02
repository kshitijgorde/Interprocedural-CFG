// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata;

import a.b.h.a.b;
import a.b.h.a.a;

public class MediaSetFactory implements a
{
    private static MediaSetFactory a;
    
    public static MediaSetFactory a() {
        return MediaSetFactory.a;
    }
    
    public b a() {
        return new MediaSet();
    }
    
    static {
        MediaSetFactory.a = new MediaSetFactory();
    }
}
