// 
// Decompiled by Procyon v0.5.30
// 

public abstract class q implements v
{
    public static int a;
    
    public int hashCode() {
        return this.b().hashCode();
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof v && this.b().equals(((v)o).a()));
    }
    
    public r a() {
        return this.b();
    }
    
    public abstract r b();
}
