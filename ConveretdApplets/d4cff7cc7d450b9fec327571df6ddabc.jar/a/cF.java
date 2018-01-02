// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.EOFException;
import java.io.DataInputStream;
import java.util.Hashtable;

public final class cF extends Hashtable
{
    private static int q;
    private String q;
    
    public final Object put(final Object o, final Object o2) {
        if (!(o instanceof String) || !(o2 instanceof String)) {
            throw new IllegalArgumentException();
        }
        return super.put(o, o2);
    }
    
    public final String q(String s) {
        if (s == null) {
            return null;
        }
        return s = this.get(s);
    }
    
    private static String q(final DataInputStream dataInputStream) {
        try {
            final char[] array = new char[256];
            int n = 0;
            for (char c = dataInputStream.readChar(); c != '\0'; c = dataInputStream.readChar()) {
                array[n++] = c;
            }
            final char[] array2 = new char[n];
            System.arraycopy(array, 0, array2, 0, n);
            final char[] array4;
            final char[] array3 = new char[(array4 = array2).length];
            for (int i = 0; i < array4.length; ++i) {
                array3[i] = (char)(array4[i] ^ cF.q);
            }
            return new String(array3);
        }
        catch (EOFException ex) {
            return null;
        }
    }
    
    public final boolean equals(final Object o) {
        return o instanceof cF && ((cF)o).q.equals(this.q);
    }
    
    public cF(InputStream inputStream) {
        inputStream = new DataInputStream(inputStream);
        this.q = ((DataInputStream)inputStream).readLine();
        System.out.println("read line:" + this.q);
        try {
            for (String s = q((DataInputStream)inputStream), s2 = q((DataInputStream)inputStream); s != null && s2 != null; s = q((DataInputStream)inputStream), s2 = q((DataInputStream)inputStream)) {
                this.put(s, s2);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        ((FilterInputStream)inputStream).close();
    }
    
    static {
        cF.q = 4883;
    }
}
