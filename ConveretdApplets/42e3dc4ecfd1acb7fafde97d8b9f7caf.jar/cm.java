// 
// Decompiled by Procyon v0.5.30
// 

public class cm implements z
{
    public final int a(final av av, final av av2) {
        final cz cz = (cz)av;
        final cz cz2 = (cz)av2;
        final int b = this.b(cz, cz2);
        if (b == -2) {
            return cz.s().compareTo(cz2.s());
        }
        return b;
    }
    
    private int a(final cz cz, final cz cz2) {
        if (cz.v() && !cz.y() && cz2.v() && !cz2.y()) {
            return cz.s().toString().compareTo(cz2.s());
        }
        if (cz.v() && !cz.y()) {
            return -1;
        }
        if (cz2.v() && !cz2.y()) {
            return 1;
        }
        return -2;
    }
    
    private int b(final cz cz, final cz cz2) {
        if (cz.f() == cz2.f()) {
            return this.a(cz, cz2);
        }
        if (cz.f() > cz2.f()) {
            return -1;
        }
        return 1;
    }
}
