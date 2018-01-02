// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public final class Removing
{
    public static ForwardIterator remove(ForwardIterator forwardIterator, final ForwardIterator forwardIterator2, final Object o) {
        if (!(forwardIterator.getContainer() instanceof Sequence)) {
            throw new IllegalArgumentException("iterator containers must be a Sequence");
        }
        forwardIterator = (ForwardIterator)Finding.find(forwardIterator, forwardIterator2, o);
        if (forwardIterator.equals(forwardIterator2)) {
            return forwardIterator;
        }
        final ForwardIterator forwardIterator3 = (ForwardIterator)forwardIterator.clone();
        forwardIterator3.advance();
        return (ForwardIterator)removeCopy(forwardIterator3, forwardIterator2, forwardIterator, o);
    }
    
    public static ForwardIterator remove(final Sequence sequence, final Object o) {
        return remove(sequence.start(), sequence.finish(), o);
    }
    
    public static ForwardIterator removeIf(ForwardIterator forwardIterator, final ForwardIterator forwardIterator2, final UnaryPredicate unaryPredicate) {
        if (!(forwardIterator.getContainer() instanceof Sequence)) {
            throw new IllegalArgumentException("iterator containers must be a Sequence");
        }
        forwardIterator = (ForwardIterator)Finding.findIf(forwardIterator, forwardIterator2, unaryPredicate);
        if (forwardIterator.equals(forwardIterator2)) {
            return forwardIterator;
        }
        final ForwardIterator forwardIterator3 = (ForwardIterator)forwardIterator.clone();
        forwardIterator3.advance();
        return (ForwardIterator)removeCopyIf(forwardIterator3, forwardIterator2, forwardIterator, unaryPredicate);
    }
    
    public static ForwardIterator removeIf(final Sequence sequence, final UnaryPredicate unaryPredicate) {
        return removeIf(sequence.start(), sequence.finish(), unaryPredicate);
    }
    
    public static OutputIterator removeCopy(final InputIterator inputIterator, final InputIterator inputIterator2, final OutputIterator outputIterator, final Object o) {
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        final OutputIterator outputIterator2 = (OutputIterator)outputIterator.clone();
        while (!inputIterator3.equals(inputIterator2)) {
            if (!inputIterator3.get().equals(o)) {
                outputIterator2.put(inputIterator3.get());
                outputIterator2.advance();
            }
            inputIterator3.advance();
        }
        return outputIterator2;
    }
    
    public static OutputIterator removeCopy(final Container container, final OutputIterator outputIterator, final Object o) {
        return removeCopy(container.start(), container.finish(), outputIterator, o);
    }
    
    public static void removeCopy(final Container container, final Container container2, final Object o) {
        removeCopy(container.start(), container.finish(), new InsertIterator(container2), o);
    }
    
    public static OutputIterator removeCopyIf(final InputIterator inputIterator, final InputIterator inputIterator2, final OutputIterator outputIterator, final UnaryPredicate unaryPredicate) {
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        final OutputIterator outputIterator2 = (OutputIterator)outputIterator.clone();
        while (!inputIterator3.equals(inputIterator2)) {
            if (!unaryPredicate.execute(inputIterator3.get())) {
                outputIterator2.put(inputIterator3.get());
                outputIterator2.advance();
            }
            inputIterator3.advance();
        }
        return outputIterator2;
    }
    
    public static OutputIterator removeCopyIf(final Container container, final OutputIterator outputIterator, final UnaryPredicate unaryPredicate) {
        return removeCopyIf(container.start(), container.finish(), outputIterator, unaryPredicate);
    }
    
    public static void removeCopyIf(final Container container, final Container container2, final UnaryPredicate unaryPredicate) {
        removeCopyIf(container.start(), container.finish(), new InsertIterator(container2), unaryPredicate);
    }
}
