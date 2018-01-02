// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gif;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class gifReader
{
    public int a;
    public int b;
    public int c;
    public int d;
    public f e;
    private byte[][] f;
    private byte[][] g;
    public Vector h;
    public Vector i;
    public Vector j;
    private InputStream k;
    private gifImage l;
    private String m;
    private boolean n;
    
    public byte[] a(final byte[] array, final int n, final int n2) {
        final byte[] array2 = new byte[array.length];
        int n3 = 0;
        final int[] array3 = { 0, 4, 2, 1 };
        final int[] array4 = { 8, 8, 4, 2 };
        int n4 = 0;
        do {
            for (int i = array3[n4]; i < n2; i += array4[n4]) {
                System.arraycopy(array, n * n3++, array2, i * n, n);
            }
        } while (++n4 < 4);
        return array2;
    }
    
    public void a() throws IOException {
        int read;
        do {
            read = this.k.read();
        } while (read != 0 && read != -1);
    }
    
    private int a(final int n) {
        return n & 0xFF;
    }
    
    public void a(final byte[] array) throws IOException {
        int read;
        for (int i = 0; i < array.length; i += read) {
            read = this.k.read(array, i, array.length - i);
            if (read == -1) {
                throw new IOException("readFully failed");
            }
        }
    }
    
    public void b() throws IOException {
        final byte[] array = new byte[6];
        try {
            this.a(array);
            this.l.b = this.a(array[2], array[3]);
            if ((array[1] & 0x1) != 0x0) {
                this.l.i = true;
                this.l.h = this.a(array[4]);
            }
            this.l.g = (array[1] >> 2 & 0x7);
        }
        catch (IOException ex) {
            System.out.println("Failed to read graphic control extension");
        }
    }
    
    public void readAll() {
        try {
            this.g();
            this.e();
            while (true) {
                final int j = this.j();
                if (j == -1) {
                    break;
                }
                switch (j) {
                    case 33: {
                        switch (this.j()) {
                            case 249: {
                                this.b();
                                continue;
                            }
                            case 254: {
                                this.f();
                                continue;
                            }
                            case 1: {
                                this.i();
                                continue;
                            }
                            case 255: {
                                this.c();
                                continue;
                            }
                        }
                        break;
                    }
                    case 44: {
                        this.d();
                        continue;
                    }
                    default: {
                        this.a();
                        continue;
                    }
                }
            }
            this.h();
        }
        catch (IOException ex) {
            System.out.println("Decoding failed!");
            ex.printStackTrace();
        }
    }
    
    public void c() throws IOException {
        final byte[] array = new byte[11];
        try {
            this.a(this.k.read());
            final byte[] array2 = new byte[11];
            this.a(array2);
            final int a = this.a(this.k.read());
            final byte[] array3 = new byte[a];
            this.a(array3);
            if (new String(array2, 0).toLowerCase().indexOf("netscape2.0") != -1 && a == 3) {
                this.d = this.a(array3[1], array3[2]);
            }
            int a2;
            while ((a2 = this.a(this.k.read())) != 0) {
                this.a(new byte[a2]);
            }
        }
        catch (IOException ex) {
            System.out.println("Failed to read application extension");
        }
    }
    
    public void d() throws IOException {
        final byte[] array = new byte[9];
        this.a(array);
        final int a = this.a(array[0], array[1]);
        final int a2 = this.a(array[2], array[3]);
        final int a3 = this.a(array[4], array[5]);
        final int a4 = this.a(array[6], array[7]);
        final byte b = array[8];
        final boolean b2 = (b & 0x40) != 0x0;
        byte[] a5 = new byte[a3 * a4];
        if ((b & 0x80) != 0x0) {
            this.i.addElement(this.g = this.b((int)Math.pow(2.0, 1 + (b & 0x7))));
        }
        else {
            this.g = null;
        }
        final int read = this.k.read();
        final byte[] array2 = { 0 };
        byte[] a6 = new byte[0];
        int read2;
        while ((read2 = this.k.read()) != 0) {
            array2[0] = (byte)read2;
            final byte[] a7 = this.a(a6, array2);
            final byte[] array3 = new byte[read2];
            this.a(array3);
            a6 = this.a(a7, array3);
        }
        new g(new ByteArrayInputStream(a6), read, false).a(a5);
        final byte[][] array4 = (this.g == null) ? this.f : this.g;
        final j j = new j();
        j.a(a3, a4);
        final int[] b3 = j.b();
        final int[] array5 = new int[array4.length];
        for (int i = 0; i < array4.length; ++i) {
            array5[i] = (0xFF000000 | (array4[i][2] & 0xFF) | (array4[i][1] & 0xFF) << 8 | (array4[i][0] & 0xFF) << 16);
        }
        if (this.l.i) {
            final int[] array6 = array5;
            final int h = this.l.h;
            array6[h] &= 0xFFFFFF;
        }
        if (b2) {
            a5 = this.a(a5, a3, a4);
        }
        for (int k = 0; k < b3.length; ++k) {
            b3[k] = array5[a5[k] & 0xFF];
        }
        this.h.addElement(new gifImage(j, a, a2, this.l.b, this.l.g, this.l.h, this.l.i));
        ++this.c;
    }
    
    private byte[][] b(final int n) {
        final byte[] array = new byte[3];
        final byte[][] g = new byte[n][3];
        try {
            for (int i = 0; i < n; ++i) {
                this.a(g[i]);
            }
        }
        catch (IOException ex) {
            System.out.println("colorTable build failed");
        }
        return this.g = g;
    }
    
    public void e() throws IOException {
        final byte[] array = new byte[7];
        int n = 0;
        try {
            this.a(array);
            this.a = this.a(array[0], array[1]);
            this.b = this.a(array[2], array[3]);
            if ((array[4] & 0x80) != 0x0) {
                n = (int)Math.pow(2.0, 1 + (array[4] & 0x7));
                this.i.addElement(this.f = this.b(n));
            }
            final int n2 = array[5] & 0xFF;
            if (n2 >= 0 && n2 < n) {
                this.e = new f(this.f[n2][0], this.f[n2][1], this.f[n2][2]);
            }
        }
        catch (IOException ex) {
            System.out.println("Failed to read header");
        }
    }
    
    public void f() throws IOException {
        final StringBuffer sb = new StringBuffer();
        try {
            int a;
            while ((a = this.a(this.k.read())) != 0) {
                final byte[] array = new byte[a];
                this.a(array);
                sb.append(new String(array, 0));
            }
        }
        catch (IOException ex) {
            System.out.println("Failed to read comment text");
        }
        if (sb.length() > 0) {
            if (this.j == null) {
                this.j = new Vector();
            }
            this.j.addElement(sb.toString());
        }
    }
    
    public void g() throws IOException {
        try {
            this.a(new byte[6]);
        }
        catch (IOException ex) {
            System.out.println("Failed to read header");
        }
    }
    
    public void h() throws IOException {
        if (this.n) {
            this.k.close();
        }
    }
    
    public void i() throws IOException {
        final StringBuffer sb = new StringBuffer();
        boolean b = false;
        final byte[] array = new byte[12];
        try {
            this.a(this.k.read());
            this.a(array);
            while (!b) {
                final int a = this.a(this.k.read());
                if (a != 0) {
                    final byte[] array2 = new byte[a];
                    this.a(array2);
                    sb.append(array2);
                }
                else {
                    b = true;
                }
            }
        }
        catch (IOException ex) {
            System.out.println("Failed to read plain text block");
        }
    }
    
    public gifReader(final InputStream k) {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = -1;
        this.e = null;
        this.f = null;
        this.h = new Vector();
        this.i = new Vector();
        this.j = null;
        this.l = new gifImage();
        this.m = "";
        this.n = false;
        this.k = k;
    }
    
    public gifImage getFrame(final int n) {
        if (n < this.h.size()) {
            return this.h.elementAt(n);
        }
        return null;
    }
    
    private int a(final int n, final int n2) {
        return (n2 << 8 & 0xFF00) | (n & 0xFF);
    }
    
    private byte[] a(final byte[] array, final byte[] array2) {
        final byte[] array3 = new byte[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length, array2.length);
        return array3;
    }
    
    public int j() throws IOException {
        return this.k.read();
    }
}
