// 
// Decompiled by Procyon v0.5.30
// 

package b;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class p
{
    public static void a(final String[] array) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        OutputStream outputStream2 = null;
        final int[] array2 = new int[11];
        final short[] array3 = new short[82];
        final short[] array4 = new short[80];
        if (array.length != 2) {
            throw new RuntimeException("Must use 3 args.");
        }
        try {
            inputStream = new FileInputStream(array[0]);
            outputStream = new FileOutputStream(array[1] + ".itu");
            outputStream2 = new FileOutputStream(array[1]);
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        final k k = new k();
        final o o = new o();
        k.a();
        o.a();
        int n = 0;
        final byte[] array5 = new byte[160];
        try {
            while (inputStream.read(array5) == 160) {
                final short[] if1 = j.if(array5);
                ++n;
                final float[] array6 = new float[if1.length];
                for (int i = 0; i < 80; ++i) {
                    array6[i] = if1[i];
                }
                k.a(array6, 80);
                o.a(array6);
                o.a(array2, 0);
                final byte[] array7 = new byte[10];
                ai.a(array2, array7);
                ai.a(array2, array3);
                if (n < 3) {
                    System.out.println("A: " + a(if1));
                    System.out.println("PRM: " + a(array2));
                }
                outputStream2.write(array7);
                outputStream.write(j.a(array3));
            }
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
            System.exit(1);
            try {
                outputStream.close();
                inputStream.close();
                outputStream2.close();
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
        }
        finally {
            try {
                outputStream.close();
                inputStream.close();
                outputStream2.close();
            }
            catch (IOException ex4) {
                ex4.printStackTrace();
            }
        }
    }
    
    public static String a(final byte[] array) {
        String string = "";
        for (int i = 0; i < array.length; ++i) {
            string = string + array[i] + " ";
        }
        return string;
    }
    
    public static String a(final short[] array) {
        String string = "";
        for (int i = 0; i < array.length; ++i) {
            string = string + array[i] + " ";
        }
        return string;
    }
    
    public static String a(final int[] array) {
        String string = "";
        for (int i = 0; i < array.length; ++i) {
            string = string + array[i] + " ";
        }
        return string;
    }
}
