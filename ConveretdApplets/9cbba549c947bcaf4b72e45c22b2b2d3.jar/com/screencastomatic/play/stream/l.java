// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import java.awt.CompositeContext;
import java.awt.RenderingHints;
import java.awt.image.ColorModel;
import java.awt.Composite;

class l implements Composite
{
    final /* synthetic */ j a;
    
    private l(final j a) {
        this.a = a;
    }
    
    public CompositeContext createContext(final ColorModel colorModel, final ColorModel colorModel2, final RenderingHints renderingHints) {
        return new t(this);
    }
}