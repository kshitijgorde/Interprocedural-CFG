// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.cbf;

import daeja4.filter.msg.nf;
import java.io.UnsupportedEncodingException;
import ji.io.h;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Vector;
import ji.io.ac;

public class ne
{
    protected ng a;
    protected nj b;
    protected nj c;
    protected ac d;
    protected Vector e;
    protected byte[] f;
    protected boolean g;
    protected ni h;
    
    public ne(final ac d) throws Exception {
        this.d = d;
        this.b();
        this.g = false;
    }
    
    public String j() throws Exception {
        final long n = (this.a.d() << this.a.h()) + 512;
        final byte[] array = new byte[128];
        this.d.a(n);
        this.d.a(array);
        return new ni(array, this.a.e(), 0).f();
    }
    
    public void a() throws Exception {
        if (this.g) {
            return;
        }
        final byte[] array = new byte[512 * this.a.c()];
        this.a(array);
        this.b = new nj(array, this.a.h());
        this.c();
        this.e();
        this.d();
    }
    
    public void a(final PrintStream printStream) {
        printStream.print("jiCompoundBinaryFile:  ");
        boolean b = this.e != null;
        if (b) {
            b = !this.e.isEmpty();
        }
        if (b) {
            printStream.print("\n ************** summary ***************\n");
            printStream.print(this.e.elementAt(0).a(0, false, true));
            printStream.print("\n ************** details ***************\n");
            printStream.print(this.a);
            printStream.print("\n");
            printStream.print(this.b);
            printStream.print("\n");
            printStream.print(this.c);
            printStream.print("\n");
            try {
                printStream.print(this.e.elementAt(0));
                for (int i = 1; i < this.e.size(); ++i) {
                    printStream.println();
                    printStream.print(this.e.elementAt(i));
                    printStream.print("\n\t\tdirectoryData:  ");
                    printStream.print(this.c((ni)this.e.elementAt(i)));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                printStream.print("******** ERROR fetching data **************");
            }
            printStream.print("\n; mini-stream: ");
            if (this.f != null) {
                for (int j = 0; j < this.f.length; ++j) {
                    printStream.print((char)this.f[j]);
                }
            }
            else {
                printStream.print("null");
            }
        }
        else {
            printStream.print(this.a);
            printStream.println("\n ************** FAT **************");
            printStream.print(this.b);
        }
        printStream.println();
    }
    
    public String toString() {
        String string = null;
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final PrintStream printStream = new PrintStream(byteArrayOutputStream);
            this.a(printStream);
            printStream.flush();
            string = byteArrayOutputStream.toString();
            byteArrayOutputStream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return string;
    }
    
    protected String a(final ni ni, final String s, final boolean b) throws Exception {
        return this.a(ni.a(s), b);
    }
    
    protected String a(final ni ni, final String s) throws Exception {
        String s2 = "";
        final byte[] b = this.b(ni);
        if (b != null && b.length > 0) {
            if (s != null) {
                try {
                    s2 = new String(b, s);
                }
                catch (UnsupportedEncodingException ex) {
                    ji.io.h.c(null, ex.getMessage());
                    s2 = new String(b);
                }
            }
            else {
                s2 = new String(b);
            }
        }
        return s2;
    }
    
    protected String a(final ni ni, final boolean b) throws Exception {
        String s = "";
        final byte[] b2 = this.b(ni);
        if (b2 != null && b2.length > 0) {
            if (b) {
                s = nh.h(b2, 0, b2.length);
            }
            else {
                s = nh.g(b2, 0, b2.length);
            }
        }
        return s;
    }
    
    protected byte[] b(final ni ni) throws Exception {
        byte[] byteArray = null;
        if (ni != null) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                this.a(ni, byteArrayOutputStream);
                byteArrayOutputStream.flush();
                byteArray = byteArrayOutputStream.toByteArray();
            }
            finally {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            }
        }
        return byteArray;
    }
    
    protected void a(final ni ni, final OutputStream outputStream) throws Exception {
        this.a(ni, outputStream, -1L);
    }
    
    protected void a(final ni ni, final OutputStream outputStream, final long n) throws Exception {
        try {
            final long h = ni.h();
            if (ni.g() >= 0 && ni.h() > 0) {
                int n2 = 0;
                if (ni.k()) {
                    int k = this.a.k();
                    final long[] a = this.c.a(ni.g());
                    for (int i = 0; i < a.length; ++i) {
                        if (n > 0 && n2 > n) {
                            break;
                        }
                        if (n2 + k > h) {
                            k = (int)(h - n2);
                        }
                        outputStream.write(this.f, (int)a[i], k);
                        n2 += k;
                    }
                }
                else {
                    int j = this.a.i();
                    final byte[] array = new byte[j];
                    final long[] a2 = this.b.a(ni.g());
                    for (int l = 0; l < a2.length; ++l) {
                        if (n > 0 && n2 > n) {
                            break;
                        }
                        if (n2 + j > h) {
                            j = (int)(h - n2);
                        }
                        this.d.a(a2[l]);
                        this.d.a(array, 0, j);
                        outputStream.write(array, 0, j);
                        n2 += j;
                    }
                }
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    protected Object c(final ni ni) throws Exception {
        Object string = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            this.a(ni, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            string = byteArrayOutputStream.toString("US-ASCII");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            string = "invalid";
        }
        finally {
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
        return string;
    }
    
    private void b() throws Exception {
        final byte[] array = new byte[512];
        this.d.a(0L);
        this.d.a(array);
        this.a = new nf(array);
    }
    
    private void a(final byte[] array) throws Exception {
        final int c = this.a.c();
        final int i = this.a.i();
        final long[] a = this.a.a();
        int n = 0;
        int j;
        for (j = 0; j < c && j < 109; ++j) {
            this.d.a((a[j] << 9) + 512);
            this.d.a(array, n, i);
            n += i;
        }
        if (c > 109) {
            final byte[] array2 = new byte[512];
            long n2 = (this.a.g() << 9) + 512;
            while (j < c) {
                this.d.a(n2);
                this.d.a(array2, 0, array2.length);
                for (int n3 = 0; n3 < array2.length - 1 && j < c; ++j, n3 += 4) {
                    n2 = (nh.b(array2, n3, 4) << 9) + 512;
                    this.d.a(n2);
                    this.d.a(array, n, i);
                    n += i;
                }
                if (j < c) {
                    n2 = (array2[array2.length - 1] << 9) + 512;
                }
            }
        }
        if (j < c) {
            ji.io.h.d(null, "FAT chain length=".concat(String.valueOf(String.valueOf(c))));
            throw new IllegalStateException("File size unsupported");
        }
    }
    
    private void c() throws Exception {
        final int i = this.a.i();
        final long[] a = this.b.a(this.a.f());
        final byte[] array = new byte[i * a.length];
        int n = 0;
        for (int j = 0; j < a.length; ++j) {
            this.d.a(a[j]);
            this.d.a(array, n, i);
            n += i;
        }
        this.c = new nk(array, this.a.j());
    }
    
    private void d() throws Exception {
        if (this.e.isEmpty()) {
            return;
        }
        final ni ni = this.e.elementAt(0);
        int i = this.a.i();
        final long[] a = this.b.a(ni.g());
        this.f = new byte[(int)ni.h()];
        int n = 0;
        for (int j = 0; j < a.length; ++j) {
            if (n + i > this.f.length) {
                i = this.f.length - n;
            }
            this.d.a(a[j]);
            this.d.a(this.f, n, i);
            n += i;
        }
    }
    
    private void e() throws Exception {
        this.e = new Vector();
        final long[] a = this.b.a(this.a.d());
        final byte[] array = new byte[128];
        for (int i = 0; i < a.length; ++i) {
            this.d.a(a[i]);
            this.b(array);
        }
        if (this.e.size() > 0) {
            this.a(this.h = this.e.elementAt(0));
        }
    }
    
    private void b(final byte[] array) throws Exception {
        for (int i = 0; i < 512; i += 128) {
            this.d.a(array);
            this.e.addElement(new ni(array, this.a.e(), this.e.size()));
        }
    }
    
    private void a(final ni ni) {
        if (ni.f != 4294967295L) {
            this.a(ni.o = this.e.elementAt(ni.c()));
        }
        if (ni.g != 4294967295L) {
            this.a(ni.p = this.e.elementAt(ni.d()));
        }
        if (ni.h != -1) {
            this.a(ni.q = this.e.elementAt(ni.h));
        }
    }
}
