// 
// Decompiled by Procyon v0.5.30
// 

package a.b.k;

import a.b.h.c.d;
import java.io.IOException;
import java.io.EOFException;
import java.util.Vector;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import a.b.h.a.b;

public abstract class i implements h
{
    private static String[] z;
    
    protected b[] a(final byte[] array) throws a {
        try {
            if (array == null) {
                throw new a(i.z[0]);
            }
            final ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(array));
            final Vector<b> vector = new Vector<b>();
            int i = 0;
            while (i == 0) {
                try {
                    vector.addElement((b)objectInputStream.readObject());
                }
                catch (EOFException ex4) {
                    i = 1;
                }
            }
            final b[] array2 = new b[vector.size()];
            for (int j = 0; j < vector.size(); ++j) {
                array2[j] = vector.elementAt(j);
            }
            return array2;
        }
        catch (ClassCastException ex) {
            throw new a(i.z[1] + ex.getMessage(), ex);
        }
        catch (IOException ex2) {
            throw new a(i.z[2] + ex2.getMessage(), ex2);
        }
        catch (ClassNotFoundException ex3) {
            throw new a(i.z[3] + ex3.getMessage(), ex3);
        }
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "D\f p\\sM>\u007fAt\bn\u007f\u0013i\u0018\"r\u0013u\b=n\\i\u001e+0".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0007';
                    break;
                }
                case 1: {
                    c2 = 'm';
                    break;
                }
                case 2: {
                    c2 = 'N';
                    break;
                }
                case 3: {
                    c2 = '\u001e';
                    break;
                }
                default: {
                    c2 = '3';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "S\u0005+>Ab\u001e>q]t\bnqQm\b-j\u0013n\u001enp\\sM/>wn\u001e>rR~)/jRT\b:$\u0013".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0007';
                    break;
                }
                case 1: {
                    c4 = 'm';
                    break;
                }
                case 2: {
                    c4 = 'N';
                    break;
                }
                case 3: {
                    c4 = '\u001e';
                    break;
                }
                default: {
                    c4 = '3';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "N\"nNAh\u000f\"{^'\u001a&{]'\u0018 mVu\u0004/rZ}\u0004 y\u0013u\b=n\\i\u001e+>\\e\u0007+}G=M".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0007';
                    break;
                }
                case 1: {
                    c6 = 'm';
                    break;
                }
                case 2: {
                    c6 = 'N';
                    break;
                }
                case 3: {
                    c6 = '\u001e';
                    break;
                }
                default: {
                    c6 = '3';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "D\f p\\sM(w]cM*{Un\u0003'jZh\u0003nx\\uM={An\f\"wIb\tn}_f\u001e=$\u0013".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0007';
                    break;
                }
                case 1: {
                    c8 = 'm';
                    break;
                }
                case 2: {
                    c8 = 'N';
                    break;
                }
                case 3: {
                    c8 = '\u001e';
                    break;
                }
                default: {
                    c8 = '3';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        a.b.k.i.z = z;
    }
}
