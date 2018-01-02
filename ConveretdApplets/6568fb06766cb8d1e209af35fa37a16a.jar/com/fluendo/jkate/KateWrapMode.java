// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

public class KateWrapMode
{
    public static final KateWrapMode kate_wrap_word;
    public static final KateWrapMode kate_wrap_none;
    private static final KateWrapMode[] list;
    
    public static KateWrapMode CreateWrapMode(final int n) throws KateException {
        if (n < 0 || n >= KateWrapMode.list.length) {
            throw new KateException("Wrap mode " + n + " out of bounds");
        }
        return KateWrapMode.list[n];
    }
    
    static {
        kate_wrap_word = new KateWrapMode();
        kate_wrap_none = new KateWrapMode();
        list = new KateWrapMode[] { KateWrapMode.kate_wrap_word, KateWrapMode.kate_wrap_none };
    }
}
