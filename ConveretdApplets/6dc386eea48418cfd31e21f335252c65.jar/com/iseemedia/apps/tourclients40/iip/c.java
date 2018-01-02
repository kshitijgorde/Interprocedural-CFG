// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.iip;

import java.io.IOException;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.io.EOFException;
import java.io.DataInputStream;

public final class c
{
    public static final byte[] a;
    public static final int b;
    private static final byte[] d;
    private int e;
    private byte[] f;
    private int g;
    public boolean c;
    
    public c(final DataInputStream dataInputStream, final byte[][] array, final int n) throws Exception {
        this.f = null;
        this.c = false;
        try {
            while (!this.a(dataInputStream).equals("tile")) {
                dataInputStream.readLine();
            }
            final int b;
            if ((b = this.b(dataInputStream)) != n) {
                throw new d("Requested Level is not the same (" + b + "," + n + ")");
            }
            this.e = this.b(dataInputStream);
            this.b(dataInputStream);
            final int n2 = this.b(dataInputStream) - 10;
            final byte byte1;
            if ((byte1 = dataInputStream.readByte()) == 2) {
                int length = 0;
                this.a(dataInputStream, 6);
                final int n3 = (dataInputStream.readByte() & 0xFF) - 1;
                this.a(dataInputStream, 2);
                if (n3 != -1) {
                    if (n3 < 0 || n3 > 254 || array[n3] == null) {
                        throw new d("Corrupt File Error: Bad Comp-group Index number (" + n3 + ")");
                    }
                    length = array[n3].length;
                    this.f = new byte[n2 + length + com.iseemedia.apps.tourclients40.iip.c.b];
                    System.arraycopy(array[n3], 0, this.f, com.iseemedia.apps.tourclients40.iip.c.b, length);
                }
                else {
                    this.f = new byte[n2 + com.iseemedia.apps.tourclients40.iip.c.b];
                }
                dataInputStream.readFully(this.f, length + com.iseemedia.apps.tourclients40.iip.c.b, n2);
                System.arraycopy(com.iseemedia.apps.tourclients40.iip.c.a, 0, this.f, 0, com.iseemedia.apps.tourclients40.iip.c.b);
            }
            else {
                if (byte1 != 1) {
                    throw new d("Uncompressed images are not supported");
                }
                this.a(dataInputStream, 3);
                this.g = dataInputStream.readInt();
                this.g = (this.g >>> 8 | 0xFF000000);
            }
        }
        catch (EOFException ex2) {}
        catch (Exception ex) {
            this.f = null;
            throw ex;
        }
        this.c = true;
    }
    
    public final int a() {
        return this.e;
    }
    
    public static final void a(final Image image, final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) throws InterruptedException {
        new PixelGrabber(image, n, n2, n3, n4, array, n5, n6).grabPixels();
    }
    
    public final boolean a(final URL url, final int[] array) {
        boolean b = true;
        if (this.f != null) {
            try {
                this.a(array);
            }
            catch (Exception ex) {
                b = false;
                System.out.println("Internal exception in Tile createImage");
            }
        }
        else {
            for (int i = 0; i < array.length; ++i) {
                array[i] = this.g;
            }
        }
        return b;
    }
    
    private void a(final int[] array) throws InterruptedException {
        a(Toolkit.getDefaultToolkit().createImage(this.f), 0, 0, 64, 64, array, 0, 64);
    }
    
    private void a(final DataInputStream dataInputStream, final int n) throws IOException {
        for (int i = 0; i < n; ++i) {
            dataInputStream.readByte();
        }
    }
    
    private String a(final DataInputStream dataInputStream) throws IOException, EOFException {
        try {
            final StringBuffer sb = new StringBuffer("t");
            byte b;
            while ((b = (byte)dataInputStream.read()) == 32 || b == 10 || b == 13 || b == 9) {}
            if (b != 84 && b != 116) {
                return sb.toString();
            }
        Block_8:
            while (true) {
                final byte byte1 = dataInputStream.readByte();
                for (int i = 0; i < com.iseemedia.apps.tourclients40.iip.c.d.length; ++i) {
                    if (byte1 == com.iseemedia.apps.tourclients40.iip.c.d[i]) {
                        break Block_8;
                    }
                }
                sb.append((char)byte1);
            }
            return sb.toString();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private int b(final DataInputStream dataInputStream) throws IOException, EOFException {
        byte byte1;
        while ((byte1 = dataInputStream.readByte()) < 48 || byte1 > 57) {}
        int n = byte1 - 48;
        byte byte2;
        while ((byte2 = dataInputStream.readByte()) >= 48 && byte2 <= 57) {
            n = n * 10 + byte2 - 48;
        }
        return n;
    }
    
    static {
        a = new byte[] { -1, -40, -1, -32, 0, 16, 74, 70, 73, 70, 0, 1, 2, 0, 0, 1, 0, 1, 0, 0 };
        b = c.a.length;
        d = new byte[] { 58, 44 };
    }
}
