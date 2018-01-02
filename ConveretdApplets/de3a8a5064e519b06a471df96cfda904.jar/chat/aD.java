// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.EOFException;
import java.io.DataInputStream;
import java.util.Hashtable;

public final class aD extends Hashtable
{
    private static int a;
    private String a;
    
    public final Object put(final Object o, final Object o2) {
        if (!(o instanceof Integer) || !(o2 instanceof String)) {
            throw new IllegalArgumentException();
        }
        return super.put(o, o2);
    }
    
    public final String a(final int n) {
        return this.get(new Integer(n));
    }
    
    private static String a(final DataInputStream dataInputStream) {
        try {
            final String utf;
            final char[] array = new char[(utf = dataInputStream.readUTF()).length()];
            int n = 0;
            for (int i = 0; i < utf.length(); ++i) {
                array[n++] = utf.charAt(i);
            }
            final char[] array2 = new char[n];
            System.arraycopy(array, 0, array2, 0, n);
            final char[] array4;
            final char[] array3 = new char[(array4 = array2).length];
            for (int j = 0; j < array4.length; ++j) {
                array3[j] = (char)(array4[j] ^ aD.a);
            }
            return new String(array3);
        }
        catch (EOFException ex) {
            return null;
        }
    }
    
    public final boolean equals(final Object o) {
        return o instanceof aD && ((aD)o).a.equals(this.a);
    }
    
    public aD(InputStream inputStream) {
        inputStream = new DataInputStream(inputStream);
        this.a = ((DataInputStream)inputStream).readLine();
        try {
            int n;
            try {
                n = ((DataInputStream)inputStream).readShort();
            }
            catch (NumberFormatException ex2) {
                n = -1;
            }
            catch (EOFException ex3) {
                n = -1;
            }
            for (String s = a((DataInputStream)inputStream); n != -1 && s != null; s = a((DataInputStream)inputStream)) {
                this.put(new Integer(n), s);
                try {
                    n = ((DataInputStream)inputStream).readShort();
                }
                catch (NumberFormatException ex4) {
                    n = -1;
                }
                catch (EOFException ex5) {
                    n = -1;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        ((FilterInputStream)inputStream).close();
    }
    
    static {
        aD.a = 10;
    }
}
