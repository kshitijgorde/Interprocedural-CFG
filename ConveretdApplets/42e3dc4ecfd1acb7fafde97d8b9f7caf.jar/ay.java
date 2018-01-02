// 
// Decompiled by Procyon v0.5.30
// 

public class ay implements z
{
    public final int a(final av av, final av av2) {
        if (av instanceof bj && av2 instanceof bj) {
            final bj bj = (bj)av;
            final bj bj2 = (bj)av2;
            if (bj.f() < bj2.f()) {
                return -1;
            }
            if (bj.f() > bj2.f()) {
                return 1;
            }
        }
        return av.s().toLowerCase().compareTo(av2.s().toLowerCase());
    }
}
