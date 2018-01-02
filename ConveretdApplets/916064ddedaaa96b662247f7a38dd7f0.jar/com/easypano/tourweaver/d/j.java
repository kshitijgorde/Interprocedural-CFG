// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.d;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import com.easypano.tourweaver.a.a;
import java.util.Vector;
import java.io.InputStream;

public class j implements d
{
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    InputStream d;
    Vector e;
    int f;
    
    public j(final InputStream inputStream, final int f) {
        this.e = new Vector();
        this.f = 0;
        this.a(inputStream);
        this.f = f;
    }
    
    public synchronized void a(final h h) {
        Vector vector2;
        final Vector vector = vector2 = this.e;
        h h2 = h;
        if (!i.u) {
            if (vector.contains(h)) {
                return;
            }
            vector2 = this.e;
            h2 = h;
        }
        vector2.addElement(h2);
    }
    
    public synchronized void b(final h h) {
        this.e.removeElement(h);
    }
    
    synchronized void a(final byte[] array, final String s) {
        final boolean u = i.u;
        int i = 0;
        while (i < this.e.size()) {
            ((h)this.e.elementAt(i)).a(array, s);
            ++i;
            if (u) {
                break;
            }
        }
    }
    
    synchronized void c() {
        final boolean u = i.u;
        int i = 0;
        while (i < this.e.size()) {
            ((h)this.e.elementAt(i)).a();
            ++i;
            if (u) {
                break;
            }
        }
    }
    
    synchronized void a(final a a) {
        final boolean u = i.u;
        int i = 0;
        while (i < this.e.size()) {
            ((h)this.e.elementAt(i)).a(a);
            ++i;
            if (u) {
                break;
            }
        }
    }
    
    synchronized void a(final byte[] array) {
        final boolean u = i.u;
        int i = 0;
        while (i < this.e.size()) {
            ((h)this.e.elementAt(i)).a(array);
            ++i;
            if (u) {
                break;
            }
        }
    }
    
    public void b() throws IOException {
        final boolean u = i.u;
        int n2;
        final int n = n2 = this.f;
        j j = null;
        Label_0060: {
            Label_0059: {
                if (!u) {
                    if (n == 0) {
                        this.d();
                        if (!u) {
                            break Label_0059;
                        }
                    }
                    final int f;
                    n2 = (f = this.f);
                }
                int n4;
                final int n3 = n4 = 1;
                if (!u) {
                    if (n == n3) {
                        this.e();
                        if (!u) {
                            break Label_0059;
                        }
                    }
                    j = this;
                    if (u) {
                        break Label_0060;
                    }
                    n2 = this.f;
                    n4 = 2;
                }
                if (n2 == n4) {
                    this.f();
                }
            }
            j = this;
        }
        j.d.close();
    }
    
    public void a(final InputStream inputStream) {
        this.d = new BufferedInputStream(inputStream);
    }
    
    public void a(final int f) {
        this.f = f;
    }
    
    public void a() throws IOException {
        final InputStream d = this.d;
        if (!i.u) {
            if (d == null) {
                return;
            }
            final InputStream d2 = this.d;
        }
        d.close();
    }
    
    private void d() throws IOException {
        final boolean u = i.u;
        int n = 0;
        final ZipInputStream zipInputStream = new ZipInputStream(this.d);
        ZipEntry nextEntry = null;
    Label_0170_Outer:
        while (true) {
            int i = 0;
            int n2 = 0;
            try {
                nextEntry = zipInputStream.getNextEntry();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            final ZipEntry zipEntry = nextEntry;
            if (!u && (zipEntry == null && !u)) {
                break;
            }
            int directory;
            final int n3 = directory = (zipEntry.isDirectory() ? 1 : 0);
            if (!u) {
                if (n3 != 0 && !u) {
                    continue Label_0170_Outer;
                }
                n = (int)nextEntry.getSize();
                final int n4;
                directory = (n4 = n);
            }
            if (!u) {
                if (n3 > 0) {
                    directory = n;
                }
                else {
                    directory = 10240;
                }
            }
            byte[] array = new byte[directory];
            final int n5 = n;
            Label_0286: {
                Label_0276: {
                Label_0170:
                    while (true) {
                        int n9 = 0;
                        Label_0185: {
                            Label_0184: {
                                if (u) {
                                    break Label_0184;
                                }
                                if (n5 > 0) {
                                    while (i < n) {
                                        n2 = zipInputStream.read(array, i, n - i);
                                        final int n7;
                                        final int n6 = n7 = n2;
                                        final int n8 = n9 = -1;
                                        if (u) {
                                            break Label_0185;
                                        }
                                        if (!u && (n6 == n8 && !u)) {
                                            break;
                                        }
                                        i = n6 + n8;
                                        if (u) {
                                            break Label_0170;
                                        }
                                    }
                                    break Label_0276;
                                }
                                n2 = zipInputStream.read(array, i, 10240);
                            }
                            n9 = -1;
                        }
                        if (n5 != n9) {
                            i += n2;
                            if (i + 10240 <= array.length) {
                                continue Label_0170;
                            }
                            final byte[] array2 = new byte[i + 10240];
                            System.arraycopy(array, 0, array2, 0, i);
                            array = array2;
                            if (u) {
                                break Label_0286;
                            }
                            if (!u) {
                                continue Label_0170;
                            }
                        }
                        break;
                    }
                    final int n10 = i;
                    if (u || n10 < array.length) {
                        final byte[] array3 = new byte[n10];
                        System.arraycopy(array, 0, array3, 0, i);
                        array = array3;
                    }
                }
                this.a(array, nextEntry.getName());
            }
            if (u) {
                break;
            }
        }
        this.c();
    }
    
    private void e() throws IOException {
        final a a = new a();
        a.c(this.d);
        this.a(a);
    }
    
    private void f() throws IOException {
        final boolean u = i.u;
        int n = 0;
        byte[] array = new byte[102400];
        int read;
        while ((read = this.d.read(array, n, 10240)) != -1) {
            n += read;
            if (n + 10240 > array.length) {
                final byte[] array2 = new byte[n + 10240];
                System.arraycopy(array, 0, array2, 0, n);
                array = array2;
                if (u) {
                    return;
                }
                if (u) {
                    break;
                }
                continue;
            }
        }
        this.a(array);
    }
}
