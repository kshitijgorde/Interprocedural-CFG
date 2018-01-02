// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.URLConnection;
import neat.lb;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import neat.kb;
import neat.ob;
import neat.system.f;

public class cb implements neat.system.cb, Runnable
{
    private static f a;
    private Object b;
    private ob c;
    private boolean d;
    private kb e;
    private int f;
    private kb g;
    private boolean h;
    private boolean i;
    private kb j;
    private static /* synthetic */ Class k;
    private static String[] z;
    
    public void a() {
        synchronized (this.b) {
            if (this.c == null) {
                // monitorexit(this.b)
                return;
            }
            this.c.d();
            this.c.f();
            this.c = null;
            this.b();
            this.c();
        }
        // monitorexit(this.b)
    }
    
    private void b() {
        synchronized (this.b) {
            if (!this.d) {
                // monitorexit(this.b)
                return;
            }
            this.d = false;
            if (this.e != null) {
                this.e.f();
                this.e = null;
            }
            this.f = -1;
            if (this.g != null) {
                this.g.f();
                this.g = null;
            }
            this.h = false;
        }
        // monitorexit(this.b)
    }
    
    private void c() {
        synchronized (this.b) {
            if (!this.i) {
                // monitorexit(this.b)
                return;
            }
            this.i = false;
            if (this.j != null) {
                this.j.f();
                this.j = null;
            }
        }
        // monitorexit(this.b)
    }
    
    private static final kb a(final kb kb, int n, final kb kb2, final boolean doOutput) {
        if (!x.v) {
            return null;
        }
        try {
            if (kb == null) {
                return null;
            }
            kb b = kb2.b();
            kb d = null;
            if (doOutput) {
                final int c = b.c(63);
                if (c >= 0) {
                    final kb c2 = b.c(0, c);
                    d = b.d(c + 1);
                    b.f();
                    b = c2;
                }
            }
            if (n < 0) {
                n = 80;
            }
            final String string = b.toString();
            b.f();
            String string2;
            if (d == null) {
                string2 = null;
            }
            else {
                string2 = d.toString();
                d.f();
            }
            final URLConnection openConnection = new URL(cb.z[1], kb.toString(), n, string).openConnection();
            openConnection.setDoOutput(doOutput);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty(cb.z[0], cb.z[2]);
            if (doOutput) {
                try {
                    final PrintWriter printWriter = new PrintWriter(openConnection.getOutputStream());
                    if (string2 != null) {
                        printWriter.print(string2);
                    }
                    else {
                        printWriter.println();
                    }
                    printWriter.flush();
                }
                catch (Throwable t) {}
            }
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            final lb a = lb.a();
            Label_0282: {
                break Label_0282;
                String line = null;
                do {
                    a.c(line);
                    a.c("\n");
                    line = bufferedReader.readLine();
                } while (line != null);
            }
            bufferedReader.close();
            return a.b();
        }
        catch (Throwable t2) {
            return null;
        }
    }
    
    public void run() {
        final ob c = this.c;
        while (true) {
            kb b = null;
            int f = -1;
            kb b2 = null;
            final boolean h;
            synchronized (this.b) {
                if (c != this.c) {
                    // monitorexit(this.b)
                    return;
                }
                if (!this.d) {
                    try {
                        this.b.wait(1000L);
                    }
                    catch (InterruptedException ex) {}
                    // monitorexit(this.b)
                    continue;
                }
                if (this.e != null) {
                    b = this.e.b();
                }
                f = this.f;
                if (this.g != null) {
                    b2 = this.g.b();
                }
                h = this.h;
            }
            // monitorexit(this.b)
            final kb a = a(b, f, b2, h);
            if (b != null) {
                b.f();
            }
            if (b2 != null) {
                b2.f();
            }
            synchronized (this.b) {
                if (c != this.c) {
                    if (a != null) {
                        a.f();
                    }
                    // monitorexit(this.b)
                    return;
                }
                this.d = false;
                if (this.e != null) {
                    this.e.f();
                    this.e = null;
                }
                if (this.g != null) {
                    this.g.f();
                    this.g = null;
                }
                if (this.j != null) {
                    this.j.f();
                    this.j = null;
                }
                if (a != null) {
                    this.j = a.b();
                }
                this.i = true;
                this.b.notify();
            }
            // monitorexit(this.b)
            if (a != null) {
                a.f();
            }
        }
    }
    
    public static cb d() {
        return (cb)cb.a.a();
    }
    
    public void f() {
        cb.a.a(this);
    }
    
    public void g() {
        this.b();
        this.c();
    }
    
    public void h() {
        this.a();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public cb() {
        this.b = new Object();
        this.c = null;
        this.e = null;
        this.f = -1;
        this.g = null;
        this.h = false;
        this.j = null;
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "Z\u0019\\`@w\u0002\u001f`\\i\u0013".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0019';
                            break;
                        }
                        case 1: {
                            c2 = 'v';
                            break;
                        }
                        case 2: {
                            c2 = '2';
                            break;
                        }
                        case 3: {
                            c2 = '\u0014';
                            break;
                        }
                        default: {
                            c2 = '%';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "q\u0002Fd".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0019';
                            break;
                        }
                        case 1: {
                            c4 = 'v';
                            break;
                        }
                        case 2: {
                            c4 = '2';
                            break;
                        }
                        case 3: {
                            c4 = '\u0014';
                            break;
                        }
                        default: {
                            c4 = '%';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "m\u0013J`\ni\u001aS}K".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0019';
                            break;
                        }
                        case 1: {
                            c6 = 'v';
                            break;
                        }
                        case 2: {
                            c6 = '2';
                            break;
                        }
                        case 3: {
                            c6 = '\u0014';
                            break;
                        }
                        default: {
                            c6 = '%';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "xXQv".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\u0019';
                            break;
                        }
                        case 1: {
                            c8 = 'v';
                            break;
                        }
                        case 2: {
                            c8 = '2';
                            break;
                        }
                        case 3: {
                            c8 = '\u0014';
                            break;
                        }
                        default: {
                            c8 = '%';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                z[n13] = new String(charArray4).intern();
                cb.z = z;
                cb.a = new f((cb.k != null) ? cb.k : (cb.k = a(cb.z[3])));
                return;
            }
            continue;
        }
    }
}
