// 
// Decompiled by Procyon v0.5.30
// 

public final class C extends ad
{
    public final void a(final U u) {
        super.a(u);
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            System.out.println("Invalid ROM! Unable to load.");
            return;
        }
        this.g(0, 32768);
        this.g(1, 40960);
        this.g(2, 49152);
        this.g(3, 57344);
        this.h();
        this.a.b.a(2);
    }
}
