// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.e;

import java.io.IOException;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.io.EOFException;
import java.io.DataInputStream;

public final class e
{
    private static final String f = "Requested Level is not the same (";
    private static final String d = "Corrupt File Error: Bad Comp-group Index number (";
    private static final String c = "Uncompressed images are not supported";
    private static final String b = "LP Core Error: Internal exception in Tile createImage";
    public static final byte[] e;
    public static final int for;
    private static final byte[] try;
    private static final String new = "tile";
    private static final String goto = "t";
    private static final String case = ",";
    private static final String if = ")";
    private static final byte void = 48;
    private static final byte do = 57;
    private static final int long = 4096;
    private static final int else = 255;
    private static final int char = -16777216;
    private int byte;
    private byte[] int;
    private int a;
    public boolean null;
    
    public e(final DataInputStream dataInputStream, final byte[][] array, final int n) throws Exception {
        this.int = null;
        this.null = false;
        try {
            while (!this.a(dataInputStream).equals("tile")) {
                dataInputStream.readLine();
            }
            final int if1 = this.if(dataInputStream);
            if (if1 != n) {
                throw new c("Requested Level is not the same (" + if1 + "," + n + ")");
            }
            this.byte = this.if(dataInputStream);
            this.if(dataInputStream);
            final int n2 = this.if(dataInputStream) - 10;
            final byte byte1 = dataInputStream.readByte();
            if (byte1 == 2) {
                int length = 0;
                this.a(dataInputStream, 6);
                final int n3 = (dataInputStream.readByte() & 0xFF) - 1;
                this.a(dataInputStream, 2);
                if (n3 != -1) {
                    if (n3 < 0 || n3 > 254 || array[n3] == null) {
                        throw new c("Corrupt File Error: Bad Comp-group Index number (" + n3 + ")");
                    }
                    length = array[n3].length;
                    this.int = new byte[n2 + length + zp.a.a.a.e.e.for];
                    System.arraycopy(array[n3], 0, this.int, zp.a.a.a.e.e.for, length);
                }
                else {
                    this.int = new byte[n2 + zp.a.a.a.e.e.for];
                }
                dataInputStream.readFully(this.int, length + zp.a.a.a.e.e.for, n2);
                System.arraycopy(zp.a.a.a.e.e.e, 0, this.int, 0, zp.a.a.a.e.e.for);
            }
            else {
                if (byte1 != 1) {
                    throw new c("Uncompressed images are not supported");
                }
                this.a(dataInputStream, 3);
                this.a = dataInputStream.readInt();
                this.a = (this.a >>> 8 | 0xFF000000);
            }
        }
        catch (EOFException ex2) {}
        catch (Exception ex) {
            this.int = null;
            throw ex;
        }
        this.null = true;
    }
    
    public int a() {
        return this.byte;
    }
    
    public static void a(final Image image, final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) throws InterruptedException {
        new PixelGrabber(image, n, n2, n3, n4, array, n5, n6).grabPixels();
    }
    
    public boolean a(final URL url, final int[] array) {
        boolean b = true;
        if (this.int != null) {
            try {
                this.a(array);
            }
            catch (Exception ex) {
                b = false;
                System.out.println("LP Core Error: Internal exception in Tile createImage");
            }
        }
        else {
            for (int i = 0; i < array.length; ++i) {
                array[i] = this.a;
            }
        }
        return b;
    }
    
    private void a(final int[] array) throws InterruptedException {
        a(Toolkit.getDefaultToolkit().createImage(this.int), 0, 0, 64, 64, array, 0, 64);
    }
    
    private void a(final DataInputStream dataInputStream, final int n) throws IOException {
        for (int i = 0; i < n; ++i) {
            dataInputStream.readByte();
        }
    }
    
    private String a(final DataInputStream dataInputStream) throws IOException, EOFException {
        final StringBuffer sb = new StringBuffer("t");
        byte byte1;
        do {
            byte1 = dataInputStream.readByte();
        } while (byte1 == 32 || byte1 == 10 || byte1 == 13 || byte1 == 9);
        if (byte1 != 84 && byte1 != 116) {
            return sb.toString();
        }
    Block_8:
        while (true) {
            final byte byte2 = dataInputStream.readByte();
            for (int i = 0; i < zp.a.a.a.e.e.try.length; ++i) {
                if (byte2 == zp.a.a.a.e.e.try[i]) {
                    break Block_8;
                }
            }
            sb.append((char)byte2);
        }
        return sb.toString();
    }
    
    private int if(final DataInputStream dataInputStream) throws IOException, EOFException {
        byte byte1;
        while (true) {
            if ((byte1 = dataInputStream.readByte()) >= 48) {
                if (byte1 > 57) {
                    continue;
                }
                break;
            }
        }
        int n = byte1 - 48;
        byte byte2;
        while ((byte2 = dataInputStream.readByte()) >= 48 && byte2 <= 57) {
            n = n * 10 + byte2 - 48;
        }
        return n;
    }
    
    static {
        e = new byte[] { -1, -40, -1, -32, 0, 16, 74, 70, 73, 70, 0, 1, 2, 0, 0, 1, 0, 1, 0, 0 };
        for = zp.a.a.a.e.e.e.length;
        try = new byte[] { 58, 44 };
    }
}
