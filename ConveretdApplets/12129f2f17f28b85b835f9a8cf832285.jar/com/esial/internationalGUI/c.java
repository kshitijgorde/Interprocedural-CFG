// 
// Decompiled by Procyon v0.5.30
// 

package com.esial.internationalGUI;

import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.util.Hashtable;

public class c extends Hashtable
{
    private static int a;
    private static char b;
    private String c;
    
    public Object put(final Object o, final Object o2) {
        if (!(o instanceof String) || !(o2 instanceof String)) {
            throw new IllegalArgumentException();
        }
        return super.put(o, o2);
    }
    
    public String a(final String s) {
        if (s == null) {
            return null;
        }
        return this.get(s);
    }
    
    public String a() {
        return this.c;
    }
    
    private final String a(final DataInputStream dataInputStream) throws IOException {
        try {
            final char[] array = new char[256];
            int n = 0;
            for (char c = dataInputStream.readChar(); c != '\0'; c = dataInputStream.readChar()) {
                array[n++] = c;
            }
            final char[] array2 = new char[n];
            System.arraycopy(array, 0, array2, 0, n);
            return a(array2);
        }
        catch (EOFException ex) {
            return null;
        }
    }
    
    public boolean equals(final Object o) {
        return o instanceof c && ((c)o).a().equals(this.a());
    }
    
    private static final String a(final char[] array) {
        final char[] array2 = new char[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (char)(array[i] ^ c.a);
        }
        return new String(array2);
    }
    
    public c(final InputStream inputStream) throws IOException {
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.c = dataInputStream.readLine();
        try {
            for (String s = this.a(dataInputStream), s2 = this.a(dataInputStream); s != null && s2 != null; s = this.a(dataInputStream), s2 = this.a(dataInputStream)) {
                this.put(s, s2);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        dataInputStream.close();
    }
    
    static {
        c.a = 4883;
        c.b = '\0';
    }
}
