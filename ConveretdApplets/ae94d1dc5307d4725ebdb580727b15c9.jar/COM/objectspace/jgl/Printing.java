// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public final class Printing
{
    public static String toString(final Container container, final String s) {
        return String.valueOf(s).concat(String.valueOf(toString(container.start(), container.finish())));
    }
    
    public static String toString(final InputIterator inputIterator, final InputIterator inputIterator2) {
        if (inputIterator.atEnd()) {
            return "()";
        }
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        final StringBuffer sb = new StringBuffer();
        sb.append("( ");
        while (!inputIterator3.equals(inputIterator2)) {
            sb.append(inputIterator3.nextElement());
            if (!inputIterator3.equals(inputIterator2)) {
                sb.append(", ");
            }
        }
        sb.append(" )");
        return sb.toString();
    }
    
    public static void print(final InputIterator inputIterator, final InputIterator inputIterator2) {
        System.out.print(toString(inputIterator, inputIterator2));
    }
    
    public static void print(final Container container) {
        System.out.print(toString(container.start(), container.finish()));
    }
    
    public static void println(final InputIterator inputIterator, final InputIterator inputIterator2) {
        System.out.println(toString(inputIterator, inputIterator2));
    }
    
    public static void println(final Container container) {
        System.out.println(toString(container.start(), container.finish()));
    }
}
