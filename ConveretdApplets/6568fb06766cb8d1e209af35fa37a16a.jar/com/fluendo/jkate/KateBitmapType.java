// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

public class KateBitmapType
{
    public static final KateBitmapType kate_bitmap_type_paletted;
    public static final KateBitmapType kate_bitmap_type_png;
    private static final KateBitmapType[] list;
    
    public static KateBitmapType CreateBitmapType(final int n) throws KateException {
        if (n < 0 || n >= KateBitmapType.list.length) {
            throw new KateException("Bitmap type " + n + " out of bounds");
        }
        return KateBitmapType.list[n];
    }
    
    static {
        kate_bitmap_type_paletted = new KateBitmapType();
        kate_bitmap_type_png = new KateBitmapType();
        list = new KateBitmapType[] { KateBitmapType.kate_bitmap_type_paletted, KateBitmapType.kate_bitmap_type_png };
    }
}
