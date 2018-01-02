// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.image.ImageObserver;
import java.awt.Color;

final class dz extends u
{
    private an a;
    
    dz(final an a) {
        super(15, 15);
        this.a = a;
    }
    
    public final void a(final ei ei) {
        if (this.a.a != this.a) {
            Color color = Color.white;
            if (this.a.b || !this.a.j) {
                color = Color.lightGray;
            }
            ei.a(color);
            ei.b(3, 3, 8, 8);
            ei.a(this.a.a, 1, 1, null);
        }
        else {
            ei.a(this.a.b ? this.a() : this.d());
            ei.b(1, 1, this.c() - 2, this.d() - 2);
            ei.a(0, this.c(), this.d(), this.d(), this.f());
        }
        if (this.a.a) {
            ei.a(Color.black);
            if (this.a.a == this.a) {
                for (int i = 0; i < 3; ++i) {
                    ei.a(4, i + 6, 6, i + 8);
                    ei.a(6, i + 8, 10, i + 4);
                }
                return;
            }
            ei.b(5, 5, 4, 4);
        }
    }
}
