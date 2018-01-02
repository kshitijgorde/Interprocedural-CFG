// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

public class KateMarkupType
{
    public static final KateMarkupType kate_markup_none;
    public static final KateMarkupType kate_markup_simple;
    private static final KateMarkupType[] list;
    
    public static KateMarkupType CreateMarkupType(final int n) throws KateException {
        if (n < 0 || n >= KateMarkupType.list.length) {
            throw new KateException("Markup type " + n + " out of bounds");
        }
        return KateMarkupType.list[n];
    }
    
    static {
        kate_markup_none = new KateMarkupType();
        kate_markup_simple = new KateMarkupType();
        list = new KateMarkupType[] { KateMarkupType.kate_markup_none, KateMarkupType.kate_markup_simple };
    }
}
