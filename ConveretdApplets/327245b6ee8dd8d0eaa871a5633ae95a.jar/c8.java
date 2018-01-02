import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class c8
{
    float a;
    float b;
    float c;
    int d;
    int[] e;
    int f;
    
    c8() {
        this.e = new int[5];
        this.f = 0;
    }
    
    void a(final int n) {
        if (this.f >= 5) {
            final int[] e = this.e;
            this.e = new int[this.f + 1];
            int n2 = 0;
            while (true) {
                Label_0045: {
                    if (!c.l) {
                        break Label_0045;
                    }
                    this.e[n2] = e[n2];
                    ++n2;
                }
                if (n2 < this.f) {
                    continue;
                }
                break;
            }
        }
        this.e[this.f] = n;
        ++this.f;
    }
    
    da a(final c8 c8, final Vector vector) {
        int n = 0;
        while (true) {
            Label_0047: {
                if (!c.l) {
                    break Label_0047;
                }
                final da da = vector.elementAt(this.e[n]);
                if (da.a == c8 || da.b == c8) {
                    return da;
                }
                ++n;
            }
            if (n >= this.f) {
                return null;
            }
            continue;
        }
    }
    
    void a() {
        this.e = null;
    }
}
