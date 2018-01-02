// 
// Decompiled by Procyon v0.5.30
// 

public class ah
{
    ah a;
    ah b;
    int c;
    ai d;
    int e;
    
    public ah() {
        this.a = null;
        this.b = null;
        this.d = null;
    }
    
    void a() {
        this.c = 0;
        ai ai = this.d;
        while (true) {
            Label_0034: {
                if (!c.l) {
                    break Label_0034;
                }
                this.c |= ai.k;
                ai = ai.h;
            }
            if (ai == null) {
                return;
            }
            continue;
        }
    }
    
    void b() {
        final boolean l = c.l;
        ai ai = this.d;
        while (true) {
            while (true) {
                Label_0028: {
                    if (!l) {
                        break Label_0028;
                    }
                    ai.k = 0;
                    ai.n = 0;
                    ai = ai.h;
                }
                if (ai != null) {
                    continue;
                }
                break;
            }
            this.c = 0;
            if (!l) {
                return;
            }
            continue;
        }
    }
}
