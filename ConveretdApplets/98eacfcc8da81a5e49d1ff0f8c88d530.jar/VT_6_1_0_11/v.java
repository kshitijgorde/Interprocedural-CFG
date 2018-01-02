// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import com.hw.client.util.e;
import com.hw.client.util.f;

public final class v extends f
{
    public static final cF a;
    public static final cF b;
    public static final cF c;
    public static final cF d;
    public static final cF e;
    public static final cF f;
    public static final cF g;
    private cF h;
    private String i;
    private e j;
    
    public v(final e j, final cF h, final String i, final int n) {
        super(j);
        this.h = null;
        this.i = null;
        this.j = j;
        this.h = h;
        this.i = i;
    }
    
    public final v a(final int n) {
        return new v(this.j, this.h, this.i, n);
    }
    
    static {
        a = new cF("download start");
        b = new cF("download progress");
        c = new cF("download enf");
        d = new cF("extraction start");
        e = new cF("extraction progress");
        f = new cF("extraction end");
        g = new cF("installed");
        new cF("error");
    }
}
