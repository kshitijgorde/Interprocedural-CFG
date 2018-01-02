// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

public class OutputFormatter
{
    public static int formtime(final char[] array, int n) {
        if (n < 0) {
            n = -n;
        }
        final int n2 = n / 60;
        n -= n2 * 60;
        final int n3 = n2 / 60;
        final int n4 = n2 - n3 * 60;
        int formint = 0;
        if (n3 > 0) {
            formint = formint(array, formint, n3);
            array[formint++] = ':';
        }
        array[formint++] = (char)(48 + n4 / 10);
        array[formint++] = (char)(48 + n4 % 10);
        array[formint++] = ':';
        array[formint++] = (char)(48 + n / 10);
        array[formint++] = (char)(48 + n % 10);
        return formint;
    }
    
    public static int formint(final char[] array, final int n, int i) {
        int n2 = n;
        if (i > 0) {
            int j;
            for (j = 10; j < i; j *= 10) {}
            int n4;
            for (int n3 = j / 10; i > 0; i -= n4 * n3, n3 /= 10, array[n2++] = (char)(48 + n4)) {
                n4 = i / n3;
            }
        }
        else {
            array[n2++] = '0';
        }
        return n2;
    }
}
