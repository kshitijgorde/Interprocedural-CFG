// 
// Decompiled by Procyon v0.5.30
// 

public abstract class s extends r
{
    public final boolean equals(final Object o) {
        return this == o || (o instanceof v && this.a(((v)o).a()));
    }
    
    public abstract int hashCode();
    
    abstract boolean a(final r p0);
}
