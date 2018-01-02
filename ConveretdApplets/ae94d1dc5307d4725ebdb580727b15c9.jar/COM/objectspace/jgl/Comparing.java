// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public final class Comparing
{
    public static Object median(final Object o, final Object o2, final Object o3, final BinaryPredicate binaryPredicate) {
        if (binaryPredicate.execute(o, o2)) {
            if (binaryPredicate.execute(o2, o3)) {
                return o2;
            }
            if (binaryPredicate.execute(o, o3)) {
                return o3;
            }
            return o;
        }
        else {
            if (binaryPredicate.execute(o, o3)) {
                return o;
            }
            if (binaryPredicate.execute(o2, o3)) {
                return o3;
            }
            return o2;
        }
    }
    
    public static Pair mismatch(final InputIterator inputIterator, final InputIterator inputIterator2, final InputIterator inputIterator3) {
        final InputIterator inputIterator4 = (InputIterator)inputIterator.clone();
        final InputIterator inputIterator5 = (InputIterator)inputIterator3.clone();
        while (!inputIterator4.equals(inputIterator2) && inputIterator4.get().equals(inputIterator5.get())) {
            inputIterator4.advance();
            inputIterator5.advance();
        }
        return new Pair(inputIterator4, inputIterator5);
    }
    
    public static Pair mismatch(final Container container, final Container container2) {
        return mismatch(container.start(), container.finish(), container2.start());
    }
    
    public static Pair mismatch(final InputIterator inputIterator, final InputIterator inputIterator2, final InputIterator inputIterator3, final BinaryPredicate binaryPredicate) {
        final InputIterator inputIterator4 = (InputIterator)inputIterator.clone();
        final InputIterator inputIterator5 = (InputIterator)inputIterator3.clone();
        while (!inputIterator4.equals(inputIterator2) && binaryPredicate.execute(inputIterator4.get(), inputIterator5.get())) {
            inputIterator4.advance();
            inputIterator5.advance();
        }
        return new Pair(inputIterator4, inputIterator5);
    }
    
    public static Pair mismatch(final Container container, final Container container2, final BinaryPredicate binaryPredicate) {
        return mismatch(container.start(), container.finish(), container2.start(), binaryPredicate);
    }
    
    public static boolean equal(final InputIterator inputIterator, final InputIterator inputIterator2, final InputIterator inputIterator3) {
        final InputIterator inputIterator4 = (InputIterator)inputIterator.clone();
        final InputIterator inputIterator5 = (InputIterator)inputIterator3.clone();
        while (!inputIterator4.equals(inputIterator2)) {
            if (!inputIterator4.get().equals(inputIterator5.get())) {
                return false;
            }
            inputIterator4.advance();
            inputIterator5.advance();
        }
        return true;
    }
    
    public static boolean equal(final Container container, final Container container2) {
        return container.size() == container2.size() && equal(container.start(), container.finish(), container2.start());
    }
    
    public static boolean lexicographicalCompare(final InputIterator inputIterator, final InputIterator inputIterator2, final InputIterator inputIterator3, final InputIterator inputIterator4) {
        return lexicographicalCompare(inputIterator, inputIterator2, inputIterator3, inputIterator4, new HashComparator());
    }
    
    public static boolean lexicographicalCompare(final Container container, final Container container2) {
        return lexicographicalCompare(container.start(), container.finish(), container2.start(), container2.finish());
    }
    
    public static boolean lexicographicalCompare(final InputIterator inputIterator, final InputIterator inputIterator2, final InputIterator inputIterator3, final InputIterator inputIterator4, final BinaryPredicate binaryPredicate) {
        final InputIterator inputIterator5 = (InputIterator)inputIterator.clone();
        final InputIterator inputIterator6 = (InputIterator)inputIterator3.clone();
        while (!inputIterator5.equals(inputIterator2) && !inputIterator6.equals(inputIterator4)) {
            if (binaryPredicate.execute(inputIterator5.get(), inputIterator6.get())) {
                return true;
            }
            if (binaryPredicate.execute(inputIterator6.get(), inputIterator5.get())) {
                return false;
            }
            inputIterator5.advance();
            inputIterator6.advance();
        }
        return inputIterator5.equals(inputIterator2) && !inputIterator6.equals(inputIterator4);
    }
    
    public static boolean lexicographicalCompare(final Container container, final Container container2, final BinaryPredicate binaryPredicate) {
        return lexicographicalCompare(container.start(), container.finish(), container2.start(), container2.finish(), binaryPredicate);
    }
}
