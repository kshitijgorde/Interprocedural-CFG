// 
// Decompiled by Procyon v0.5.30
// 

package cue.lang.unicode;

import java.io.ObjectInputStream;

class Normalizer5 extends a
{
    private static final char[] a;
    
    static {
        try {
            final ObjectInputStream objectInputStream = new ObjectInputStream(Normalizer5.class.getResourceAsStream("normtable.bin"));
            try {
                a = (char[])objectInputStream.readObject();
            }
            finally {
                objectInputStream.close();
            }
            objectInputStream.close();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public final String a(final String s) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            sb.append((char1 >= Normalizer5.a.length) ? char1 : Normalizer5.a[char1]);
        }
        return sb.toString();
    }
}
