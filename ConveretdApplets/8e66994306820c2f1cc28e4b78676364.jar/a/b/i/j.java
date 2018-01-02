// 
// Decompiled by Procyon v0.5.30
// 

package a.b.i;

import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;

public abstract class j implements i
{
    private static String[] z;
    
    protected byte[] b(final k[] array) throws a {
        try {
            if (array == null) {
                throw new a(j.z[0]);
            }
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            for (int i = 0; i < array.length; ++i) {
                objectOutputStream.writeObject(array[i]);
            }
            objectOutputStream.flush();
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            objectOutputStream.close();
            return byteArray;
        }
        catch (IOException ex) {
            throw new a(j.z[1] + ex.getMessage(), ex);
        }
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "\tn\f\u0017\u001a>/\u0015\u000b\u001c>jB\u0016\u0000>/\u0003Y\u001b?c\u000eY\u0007/~\u0017\u001c\u0006>/\r\u001b\u001f/l\u0016W".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'J';
                    break;
                }
                case 1: {
                    c2 = '\u000f';
                    break;
                }
                case 2: {
                    c2 = 'b';
                    break;
                }
                case 3: {
                    c2 = 'y';
                    break;
                }
                default: {
                    c2 = 'u';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\tn\f\u0017\u001a>/\u0015\u000b\u001c>jB\u0016\u0000>/\u0016\u0011\u0010jK\u000b\n\u0005&n\u001b=\u0014>n1\u001c\u0001j{\rY\u0014$/\r\f\u0001:z\u0016Y\u001a(e\u0007\u001a\u0001j|\u0016\u000b\u0010+bXY".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'J';
                    break;
                }
                case 1: {
                    c4 = '\u000f';
                    break;
                }
                case 2: {
                    c4 = 'b';
                    break;
                }
                case 3: {
                    c4 = 'y';
                    break;
                }
                default: {
                    c4 = 'u';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        a.b.i.j.z = z;
    }
}
