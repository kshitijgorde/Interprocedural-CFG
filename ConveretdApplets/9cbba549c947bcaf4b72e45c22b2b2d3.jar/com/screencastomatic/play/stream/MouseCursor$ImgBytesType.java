// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

public enum MouseCursor$ImgBytesType
{
    a("ARGB_BYTES", 0), 
    b("BW_WITH_XOR_BYTES", 1), 
    c("RGB_555", 2), 
    d("WIN_MASK_32COLOR", 3), 
    e("WIN_MASK_16COLOR", 4);
    
    private MouseCursor$ImgBytesType(final String s, final int n) {
    }
    
    static {
        f = new MouseCursor$ImgBytesType[] { MouseCursor$ImgBytesType.a, MouseCursor$ImgBytesType.b, MouseCursor$ImgBytesType.c, MouseCursor$ImgBytesType.d, MouseCursor$ImgBytesType.e };
    }
}
