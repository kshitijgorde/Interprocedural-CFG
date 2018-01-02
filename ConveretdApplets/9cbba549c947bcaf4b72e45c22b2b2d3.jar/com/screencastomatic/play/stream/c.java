// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import java.awt.CompositeContext;
import java.awt.RenderingHints;
import java.awt.image.ColorModel;
import java.awt.Composite;

class c implements Composite
{
    final /* synthetic */ j a;
    
    private c(final j a) {
        this.a = a;
    }
    
    public CompositeContext createContext(final ColorModel colorModel, final ColorModel colorModel2, final RenderingHints renderingHints) {
        return new w(this);
    }
}
