// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public final class Finding
{
    public static InputIterator find(final InputIterator inputIterator, final InputIterator inputIterator2, final Object o) {
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        while (!inputIterator3.equals(inputIterator2) && !inputIterator3.get().equals(o)) {
            inputIterator3.advance();
        }
        return inputIterator3;
    }
    
    public static InputIterator find(final Container container, final Object o) {
        return find(container.start(), container.finish(), o);
    }
    
    public static InputIterator findIf(final InputIterator inputIterator, final InputIterator inputIterator2, final UnaryPredicate unaryPredicate) {
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        while (!inputIterator3.equals(inputIterator2) && !unaryPredicate.execute(inputIterator3.get())) {
            inputIterator3.advance();
        }
        return inputIterator3;
    }
    
    public static InputIterator findIf(final Container container, final UnaryPredicate unaryPredicate) {
        return findIf(container.start(), container.finish(), unaryPredicate);
    }
    
    public static InputIterator adjacentFind(final InputIterator inputIterator, final InputIterator inputIterator2) {
        return adjacentFind(inputIterator, inputIterator2, new EqualTo());
    }
    
    public static InputIterator adjacentFind(final Container container) {
        return adjacentFind(container.start(), container.finish());
    }
    
    public static InputIterator adjacentFind(final InputIterator inputIterator, final InputIterator inputIterator2, final BinaryPredicate binaryPredicate) {
        if (inputIterator.equals(inputIterator2)) {
            return inputIterator2;
        }
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        final InputIterator inputIterator4 = (InputIterator)inputIterator.clone();
        inputIterator4.advance();
        while (!inputIterator4.equals(inputIterator2)) {
            if (binaryPredicate.execute(inputIterator3.get(), inputIterator4.get())) {
                return inputIterator3;
            }
            inputIterator3.advance();
            inputIterator4.advance();
        }
        return inputIterator4;
    }
    
    public static InputIterator adjacentFind(final Container container, final BinaryPredicate binaryPredicate) {
        return adjacentFind(container.start(), container.finish(), binaryPredicate);
    }
    
    public static Object detect(final ForwardIterator forwardIterator, final ForwardIterator forwardIterator2, final UnaryPredicate unaryPredicate) {
        if (forwardIterator.getContainer().getClass() != forwardIterator2.getContainer().getClass()) {
            throw new IllegalArgumentException("iterator containers must be the same");
        }
        final ForwardIterator forwardIterator3 = (ForwardIterator)forwardIterator.clone();
        while (!forwardIterator3.equals(forwardIterator2)) {
            final Object nextElement = forwardIterator3.nextElement();
            if (unaryPredicate.execute(nextElement)) {
                return nextElement;
            }
        }
        return null;
    }
    
    public static Object detect(final Container container, final UnaryPredicate unaryPredicate) {
        return detect(container.start(), container.finish(), unaryPredicate);
    }
    
    public static boolean some(final ForwardIterator forwardIterator, final ForwardIterator forwardIterator2, final UnaryPredicate unaryPredicate) {
        return detect(forwardIterator, forwardIterator2, unaryPredicate) != null;
    }
    
    public static boolean some(final Container container, final UnaryPredicate unaryPredicate) {
        return some(container.start(), container.finish(), unaryPredicate);
    }
    
    public static boolean every(final ForwardIterator forwardIterator, final ForwardIterator forwardIterator2, final UnaryPredicate unaryPredicate) {
        if (forwardIterator.getContainer().getClass() != forwardIterator2.getContainer().getClass()) {
            throw new IllegalArgumentException("iterator containers must be the same");
        }
        final ForwardIterator forwardIterator3 = (ForwardIterator)forwardIterator.clone();
        while (!forwardIterator3.equals(forwardIterator2)) {
            if (!unaryPredicate.execute(forwardIterator3.nextElement())) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean every(final Container container, final UnaryPredicate unaryPredicate) {
        return every(container.start(), container.finish(), unaryPredicate);
    }
}
