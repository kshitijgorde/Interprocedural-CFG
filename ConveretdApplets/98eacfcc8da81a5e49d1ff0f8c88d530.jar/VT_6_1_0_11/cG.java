// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import com.hw.client.util.e;
import com.hw.client.util.f;

public final class cG extends f
{
    public static final aB a;
    public static final aB b;
    public static final aB c;
    private aB d;
    
    public cG(final e e, final aB d) {
        super(e);
        this.d = d;
    }
    
    public final aB a() {
        return this.d;
    }
    
    static {
        a = new aB("started");
        new aB("initializing");
        new aB("initialized");
        new aB("terminating");
        b = new aB("stopped");
        c = new aB("init_failed");
        new aB("disconnected");
        new aB("wsp_error");
    }
}
