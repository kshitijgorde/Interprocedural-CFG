// 
// Decompiled by Procyon v0.5.30
// 

package org.xiph.speex.spi;

import javax.sound.sampled.AudioFormat;

public final class e extends Encoding
{
    public static final e a;
    public static final e b;
    public static final e c;
    public static final e d;
    public static final e e;
    public static final e f;
    public static final e g;
    public static final e h;
    public static final e i;
    public static final e j;
    public static final e k;
    public static final e l;
    public static final e m;
    public static final e n;
    public static final e o;
    public static final e p;
    public static final e q;
    public static final e r;
    public static final e s;
    public static final e t;
    public static final e u;
    public static final e v;
    public static final e w;
    private int x;
    private boolean y;
    
    private e(final String s, final int x, final boolean y) {
        super(s);
        this.x = x;
        this.y = y;
    }
    
    private e(final String s) {
        this(s, 3, false);
    }
    
    public final int a() {
        return this.x;
    }
    
    public final boolean b() {
        return this.y;
    }
    
    static {
        a = new e("SPEEX");
        b = new e("SPEEX_quality_0", 0, false);
        c = new e("SPEEX_quality_1", 1, false);
        d = new e("SPEEX_quality_2", 2, false);
        e = new e("SPEEX_quality_3", 3, false);
        f = new e("SPEEX_quality_4", 4, false);
        g = new e("SPEEX_quality_5", 5, false);
        h = new e("SPEEX_quality_6", 6, false);
        i = new e("SPEEX_quality_7", 7, false);
        j = new e("SPEEX_quality_8", 8, false);
        k = new e("SPEEX_quality_9", 9, false);
        l = new e("SPEEX_quality_10", 10, false);
        m = new e("SPEEX_VBR_quality_0", 0, true);
        n = new e("SPEEX_VBR_quality_1", 1, true);
        o = new e("SPEEX_VBR_quality_2", 2, true);
        p = new e("SPEEX_VBR_quality_3", 3, true);
        q = new e("SPEEX_VBR_quality_4", 4, true);
        r = new e("SPEEX_VBR_quality_5", 5, true);
        s = new e("SPEEX_VBR_quality_6", 6, true);
        t = new e("SPEEX_VBR_quality_7", 7, true);
        u = new e("SPEEX_VBR_quality_8", 8, true);
        v = new e("SPEEX_VBR_quality_9", 9, true);
        w = new e("SPEEX_VBR_quality_10", 10, true);
    }
}
