// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public final class Hashing
{
    static final int HASH_SIZE = 16;
    
    public static int orderedHash(final Container container) {
        return orderedHash(container.start(), container.finish());
    }
    
    public static int orderedHash(final ForwardIterator forwardIterator, final ForwardIterator forwardIterator2) {
        int n = 0;
        final int distance = forwardIterator.distance(forwardIterator2);
        int n2 = 0;
        int n3 = 1;
        if (distance >= 16) {
            n3 = distance / 16;
            forwardIterator.advance(distance % 16);
        }
        while (!forwardIterator.equals(forwardIterator2)) {
            if (forwardIterator.get() != null) {
                n ^= forwardIterator.get().hashCode() / (n2 % 16 + 1);
            }
            ++n2;
            forwardIterator.advance(n3);
        }
        return n;
    }
    
    public static int unorderedHash(final Container container) {
        return unorderedHash(container.start(), container.finish());
    }
    
    public static int unorderedHash(final ForwardIterator forwardIterator, final ForwardIterator forwardIterator2) {
        int n = 0;
        while (!forwardIterator.equals(forwardIterator2)) {
            if (forwardIterator.get() != null) {
                n ^= forwardIterator.get().hashCode();
            }
            forwardIterator.advance();
        }
        return n;
    }
}
