import java.beans.Expression;

// 
// Decompiled by Procyon v0.5.30
// 

public class jyqqqqq extends Expression
{
    public static String ALLATORI_DEMO(final String a) {
        final int n = (0x2 ^ 0x5) << 4;
        final int n2 = 1;
        final int n3 = n ^ n2 << n2;
        final int n4 = (0x2 ^ 0x5) << 3 ^ 0x3;
        final int length = a.length();
        final char[] array = new char[length];
        int n5;
        int i = n5 = length - 1;
        final char[] array2 = array;
        final char c = (char)n4;
        final int n6 = n3;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n7 = n5;
            final char char1 = a.charAt(n7);
            --n5;
            array3[n7] = (char)(char1 ^ n6);
            if (n5 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n8 = n5;
            final char c2 = (char)(a.charAt(n8) ^ c);
            --n5;
            array4[n8] = c2;
            i = n5;
        }
        return new String(array2);
    }
    
    public jyqqqqq(final Object a, final String a, final Object[] a) {
        super(a, a, a);
    }
}
