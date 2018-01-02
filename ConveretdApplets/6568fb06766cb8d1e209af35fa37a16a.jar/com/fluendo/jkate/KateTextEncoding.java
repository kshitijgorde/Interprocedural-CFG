// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

public class KateTextEncoding
{
    public static final KateTextEncoding kate_utf8;
    private static final KateTextEncoding[] list;
    
    public static KateTextEncoding CreateTextEncoding(final int n) throws KateException {
        if (n < 0 || n >= KateTextEncoding.list.length) {
            throw new KateException("Text encoding " + n + " out of bounds");
        }
        return KateTextEncoding.list[n];
    }
    
    static {
        kate_utf8 = new KateTextEncoding();
        list = new KateTextEncoding[] { KateTextEncoding.kate_utf8 };
    }
}
