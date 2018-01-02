// 
// Decompiled by Procyon v0.5.30
// 

package com.photochannel.easyUploader;

enum a
{
    a("SHUTDOWN", 0), 
    b("PICK_FILES", 1), 
    c("VIEW_AS_LIST", 2), 
    d("VIEW_AS_THUMBNAILS", 3), 
    e("START_UPLOAD", 4), 
    f("CANCEL_UPLOAD", 5), 
    g("UPLOAD_FAILED", 6), 
    h("REMOVE_ALL_PHOTOS", 7), 
    i("UPLOAD_COMPLETE", 8), 
    j("UPLOAD_ABORTED_BY_USER", 9);
    
    private static final /* synthetic */ a[] k;
    
    public static a[] a() {
        return a.k.clone();
    }
    
    private a(final String s, final int n) {
    }
    
    static {
        k = new a[] { a.a, a.b, a.c, a.d, a.e, a.f, a.g, a.h, a.i, a.j };
    }
}
