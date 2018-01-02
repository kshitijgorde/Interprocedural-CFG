// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public final class Copying
{
    public static OutputIterator copy(final InputIterator inputIterator, final InputIterator inputIterator2, final OutputIterator outputIterator) {
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        final OutputIterator outputIterator2 = (OutputIterator)outputIterator.clone();
        while (!inputIterator3.equals(inputIterator2)) {
            outputIterator2.put(inputIterator3.nextElement());
            outputIterator2.advance();
        }
        return outputIterator2;
    }
    
    public static OutputIterator copy(final Container container, final OutputIterator outputIterator) {
        return copy(container.start(), container.finish(), outputIterator);
    }
    
    public static void copy(final Container container, final Container container2) {
        copy(container.start(), container.finish(), new InsertIterator(container2));
    }
    
    public static OutputIterator copyBackward(final BidirectionalIterator bidirectionalIterator, final BidirectionalIterator bidirectionalIterator2, final BidirectionalIterator bidirectionalIterator3) {
        final BidirectionalIterator bidirectionalIterator4 = (BidirectionalIterator)bidirectionalIterator2.clone();
        final BidirectionalIterator bidirectionalIterator5 = (BidirectionalIterator)bidirectionalIterator3.clone();
        while (!bidirectionalIterator.equals(bidirectionalIterator4)) {
            bidirectionalIterator5.retreat();
            bidirectionalIterator4.retreat();
            bidirectionalIterator5.put(bidirectionalIterator4.get());
        }
        return bidirectionalIterator5;
    }
}
