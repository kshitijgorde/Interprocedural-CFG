import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_ag implements Enumeration
{
    private int a;
    private int b;
    private Enumeration a;
    private int c;
    private /* synthetic */ rp_N a;
    
    rp_ag(final rp_N a, final int a2) {
        this.a = a;
        this.a = a2;
        this.b = 0;
        this.a = null;
        this.c = 0;
    }
    
    public final Object nextElement() {
        if (this.c == 0) {
            this.a();
        }
        ++this.b;
        return this.a.nextElement();
    }
    
    public final boolean hasMoreElements() {
        if (this.c == 0) {
            this.a();
        }
        boolean b = false;
        if (this.c == 1) {
            if (0x0 != (this.a & 0x1) && 0x0 == (this.a & 0x2)) {
                b = (this.b < this.a.a.size() - this.a.c);
            }
            else {
                b = this.a.hasMoreElements();
            }
            if (b) {
                return true;
            }
            if (0x0 == (this.a & 0x4)) {
                return false;
            }
            this.c = 2;
            this.b = 0;
            this.a = this.a.a(4);
        }
        if (this.c == 2) {
            b = this.a.hasMoreElements();
        }
        return b;
    }
    
    private void a() {
        if (this.c != 0) {
            return;
        }
        if (0x0 != (this.a & 0x3)) {
            if (0x0 == (this.a & 0x1)) {
                this.a = this.a.a(2);
            }
            else {
                this.a = this.a.a.elements();
            }
            this.c = 1;
            return;
        }
        if (0x0 != (this.a & 0x4)) {
            this.a = this.a.b.elements();
            this.c = 2;
        }
    }
}
