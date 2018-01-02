// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public final class HashComparator implements BinaryPredicate
{
    public boolean execute(final Object o, final Object o2) {
        return o.hashCode() < o2.hashCode();
    }
}
