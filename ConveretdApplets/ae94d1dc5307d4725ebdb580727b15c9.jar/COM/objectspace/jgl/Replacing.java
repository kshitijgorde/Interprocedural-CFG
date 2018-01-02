// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public final class Replacing
{
    public static int replace(final ForwardIterator forwardIterator, final ForwardIterator forwardIterator2, final Object o, final Object o2) {
        final ForwardIterator forwardIterator3 = (ForwardIterator)forwardIterator.clone();
        int n = 0;
        while (!forwardIterator3.equals(forwardIterator2)) {
            if (forwardIterator3.get().equals(o)) {
                forwardIterator3.put(o2);
                ++n;
            }
            forwardIterator3.advance();
        }
        return n;
    }
    
    public static int replace(final Container container, final Object o, final Object o2) {
        return replace(container.start(), container.finish(), o, o2);
    }
    
    public static int replaceIf(final ForwardIterator forwardIterator, final ForwardIterator forwardIterator2, final UnaryPredicate unaryPredicate, final Object o) {
        final ForwardIterator forwardIterator3 = (ForwardIterator)forwardIterator.clone();
        int n = 0;
        while (!forwardIterator3.equals(forwardIterator2)) {
            if (unaryPredicate.execute(forwardIterator3.get())) {
                forwardIterator3.put(o);
                ++n;
            }
            forwardIterator3.advance();
        }
        return n;
    }
    
    public static int replaceIf(final Container container, final UnaryPredicate unaryPredicate, final Object o) {
        return replaceIf(container.start(), container.finish(), unaryPredicate, o);
    }
    
    public static OutputIterator replaceCopy(final InputIterator inputIterator, final InputIterator inputIterator2, final OutputIterator outputIterator, final Object o, final Object o2) {
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        final OutputIterator outputIterator2 = (OutputIterator)outputIterator.clone();
        while (!inputIterator3.equals(inputIterator2)) {
            outputIterator2.put(inputIterator3.get().equals(o) ? o2 : inputIterator3.get());
            outputIterator2.advance();
            inputIterator3.advance();
        }
        return outputIterator2;
    }
    
    public static OutputIterator replaceCopy(final Container container, final OutputIterator outputIterator, final Object o, final Object o2) {
        return replaceCopy(container.start(), container.finish(), outputIterator, o, o2);
    }
    
    public static void replaceCopy(final Container container, final Container container2, final Object o, final Object o2) {
        replaceCopy(container.start(), container.finish(), new InsertIterator(container2), o, o2);
    }
    
    public static OutputIterator replaceCopyIf(final InputIterator inputIterator, final InputIterator inputIterator2, final OutputIterator outputIterator, final UnaryPredicate unaryPredicate, final Object o) {
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        final OutputIterator outputIterator2 = (OutputIterator)outputIterator.clone();
        while (!inputIterator3.equals(inputIterator2)) {
            outputIterator2.put(unaryPredicate.execute(inputIterator3.get()) ? o : inputIterator3.get());
            outputIterator2.advance();
            inputIterator3.advance();
        }
        return outputIterator2;
    }
    
    public static OutputIterator replaceCopyIf(final Container container, final OutputIterator outputIterator, final UnaryPredicate unaryPredicate, final Object o) {
        return replaceCopyIf(container.start(), container.finish(), outputIterator, unaryPredicate, o);
    }
    
    public static void replaceCopyIf(final Container container, final Container container2, final UnaryPredicate unaryPredicate, final Object o) {
        replaceCopyIf(container.start(), container.finish(), new InsertIterator(container2), unaryPredicate, o);
    }
}
