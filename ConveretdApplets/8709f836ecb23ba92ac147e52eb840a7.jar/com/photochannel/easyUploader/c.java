// 
// Decompiled by Procyon v0.5.30
// 

package com.photochannel.easyUploader;

enum c
{
    a("INITIALIZING", 0), 
    b("UNTRUSTED_APPLET", 1), 
    c("PHOTO_SELECTION", 2), 
    d("POPULATING_CELLS", 3), 
    e("UPLOADING", 4), 
    f("UPLOAD_SUCCESS", 5), 
    g("UPLOAD_FAILED", 6), 
    h("UPLOAD_CANCELLED", 7);
    
    private c(final String s, final int n) {
    }
    
    static {
        final c[] array = { c.a, c.b, c.c, c.d, c.e, c.f, c.g, c.h };
    }
}
