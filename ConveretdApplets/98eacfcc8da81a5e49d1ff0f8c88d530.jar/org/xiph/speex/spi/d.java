// 
// Decompiled by Procyon v0.5.30
// 

package org.xiph.speex.spi;

import javax.sound.sampled.AudioFileFormat;

public final class d extends Type
{
    public static final Type a;
    
    private d(final String s, final String s2) {
        super(s, s2);
    }
    
    static {
        a = new d("SPEEX", "spx");
    }
}
