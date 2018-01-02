// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public final class Counting
{
    public static int count(final InputIterator inputIterator, final InputIterator inputIterator2, final Object o) {
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        int n = 0;
        while (!inputIterator3.equals(inputIterator2)) {
            if (inputIterator3.nextElement().equals(o)) {
                ++n;
            }
        }
        return n;
    }
    
    public static int count(final Container container, final Object o) {
        return count(container.start(), container.finish(), o);
    }
    
    public static int countIf(final InputIterator inputIterator, final InputIterator inputIterator2, final UnaryPredicate unaryPredicate) {
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        int n = 0;
        while (!inputIterator3.equals(inputIterator2)) {
            if (unaryPredicate.execute(inputIterator3.nextElement())) {
                ++n;
            }
        }
        return n;
    }
    
    public static int countIf(final Container container, final UnaryPredicate unaryPredicate) {
        return countIf(container.start(), container.finish(), unaryPredicate);
    }
    
    public static Number accumulate(final InputIterator inputIterator, final InputIterator inputIterator2, final Number n) {
        return accumulate(inputIterator, inputIterator2, n, new PlusNumber(n.getClass()));
    }
    
    public static Number accumulate(final Container container, final Number n) {
        return accumulate(container.start(), container.finish(), n, new PlusNumber(n.getClass()));
    }
    
    public static Number accumulate(final InputIterator inputIterator, final InputIterator inputIterator2, Number n, final BinaryFunction binaryFunction) {
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        while (!inputIterator3.equals(inputIterator2)) {
            n = (Number)binaryFunction.execute(n, inputIterator3.nextElement());
        }
        return n;
    }
    
    public static Number accumulate(final Container container, final Number n, final BinaryFunction binaryFunction) {
        return accumulate(container.start(), container.finish(), n, binaryFunction);
    }
    
    public static OutputIterator adjacentDifference(final InputIterator inputIterator, final InputIterator inputIterator2, final OutputIterator outputIterator) {
        return adjacentDifference(inputIterator, inputIterator2, outputIterator, new MinusNumber());
    }
    
    public static OutputIterator adjacentDifference(final Container container, final OutputIterator outputIterator) {
        return adjacentDifference(container.start(), container.finish(), outputIterator, new MinusNumber());
    }
    
    public static OutputIterator adjacentDifference(final Container container, final Container container2) {
        return adjacentDifference(container.start(), container.finish(), new InsertIterator(container2), new MinusNumber());
    }
    
    public static OutputIterator adjacentDifference(final InputIterator inputIterator, final InputIterator inputIterator2, final OutputIterator outputIterator, final BinaryFunction binaryFunction) {
        final OutputIterator outputIterator2 = (OutputIterator)outputIterator.clone();
        if (inputIterator.equals(inputIterator2)) {
            return outputIterator2;
        }
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        outputIterator2.put(inputIterator3.get());
        outputIterator2.advance();
        Object nextElement = inputIterator3.nextElement();
        while (!inputIterator3.equals(inputIterator2)) {
            final Object nextElement2 = inputIterator3.nextElement();
            outputIterator2.put(binaryFunction.execute(nextElement2, nextElement));
            outputIterator2.advance();
            nextElement = nextElement2;
        }
        return outputIterator2;
    }
    
    public static OutputIterator adjacentDifference(final Container container, final OutputIterator outputIterator, final BinaryFunction binaryFunction) {
        return adjacentDifference(container.start(), container.finish(), outputIterator, binaryFunction);
    }
    
    public static OutputIterator adjacentDifference(final Container container, final Container container2, final BinaryFunction binaryFunction) {
        return adjacentDifference(container.start(), container.finish(), new InsertIterator(container2), binaryFunction);
    }
}
