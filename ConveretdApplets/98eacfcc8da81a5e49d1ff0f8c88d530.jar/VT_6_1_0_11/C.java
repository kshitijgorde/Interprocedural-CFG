// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.BoundedRangeModel;
import javax.swing.event.ChangeListener;
import com.hw.client.util.a;
import javax.swing.DefaultBoundedRangeModel;

public final class C
{
    private static C a;
    private DefaultBoundedRangeModel b;
    private DefaultBoundedRangeModel c;
    private String d;
    private String e;
    private boolean f;
    private boolean g;
    private int h;
    
    private C() {
        this.f = true;
        this.h = 11;
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("AudioModel: Creating AudioModel");
        }
        this.b = new DefaultBoundedRangeModel(0, 1, 0, 100);
        this.c = new DefaultBoundedRangeModel(0, 1, 0, 100);
        final cj cj = new cj(this);
        this.b.addChangeListener(cj);
        this.c.addChangeListener(cj);
    }
    
    public static C a() {
        return C.a;
    }
    
    public final BoundedRangeModel b() {
        return this.b;
    }
    
    public final String c() {
        return this.d;
    }
    
    public final void a(final String d) {
        this.d = d;
    }
    
    public final String d() {
        return this.e;
    }
    
    public final void b(final String e) {
        this.e = e;
    }
    
    public final String e() {
        return null;
    }
    
    public final BoundedRangeModel f() {
        return this.c;
    }
    
    public final boolean g() {
        return this.f;
    }
    
    public final void a(final boolean f) {
        this.f = f;
    }
    
    public final boolean h() {
        return this.g;
    }
    
    public final void b(final boolean b) {
        this.g = false;
    }
    
    public final boolean i() {
        return (this.h & 0x2) != 0x0;
    }
    
    public final void c(final boolean b) {
        if (b) {
            this.h |= 0xA;
            return;
        }
        this.h &= 0xFFFFFFF5;
    }
    
    public final boolean j() {
        return (this.h & 0x1) != 0x0;
    }
    
    public final void d(final boolean b) {
        if (b) {
            this.h |= 0x1;
            return;
        }
        this.h &= 0xFFFFFFFE;
    }
    
    public final int k() {
        return this.h;
    }
    
    static boolean a(final C c, final boolean b) {
        return c.g = true;
    }
    
    static {
        C.a = new C();
    }
}
