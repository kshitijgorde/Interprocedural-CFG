// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Color;

final class dt extends u
{
    private c a;
    
    dt(final c a) {
        this.a = a;
    }
    
    public final void a(final ei ei) {
        super.c = this.c();
        this.b(ei);
        for (int i = 0; i < 2; ++i) {
            ei.a((this.a.b ^ i % 2 == 0) ? Color.white : this.a.e());
            ei.a(0, 0, 0, 0, this.c() - 1, 0, this.c(), this.d(), i << 1);
            ei.a(0, 0, 0, 0, 0, this.d() - 1, this.c(), this.d(), i << 1);
        }
        ei.a(this.a.a ? Color.gray : Color.black);
        for (int j = 0; j < 4; ++j) {
            final int n = this.b() / 2 + 2 - j;
            ei.a(7 - j, n, j + 7, n);
        }
    }
    
    public final int b() {
        return this.a.a.b();
    }
    
    public final int a() {
        return 15;
    }
}
