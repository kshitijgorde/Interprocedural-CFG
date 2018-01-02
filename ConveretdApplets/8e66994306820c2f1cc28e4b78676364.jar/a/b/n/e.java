// 
// Decompiled by Procyon v0.5.30
// 

package a.b.n;

import java.net.URL;
import org.a.c.f;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import org.a.d.c.b;

public class e extends b
{
    private static boolean b;
    private long[] c;
    private long d;
    private String e;
    private Class f;
    static /* synthetic */ Class g;
    private static String[] z;
    
    protected e(final ThreadGroup threadGroup, final String s, final long n, final long d, final String e, final Class f) {
        super(threadGroup, s);
        (this.c = new long[1])[0] = n;
        this.d = d;
        this.e = e;
        this.f = f;
        if (this.c[0] < 1000L) {
            this.c[0] = 1000L;
        }
    }
    
    public void run() {
        if (!(this instanceof b)) {
            return;
        }
        if (a.b.n.e.b) {
            return;
        }
        a.b.n.e.b = true;
        final f a = a.b.g.b.a().d().a(((a.b.n.e.g == null) ? (a.b.n.e.g = a(a.b.n.e.z[2])) : a.b.n.e.g).getName());
        File file = null;
        final URL resource = this.f.getResource(this.e);
        if (resource != null && resource.getProtocol().equalsIgnoreCase(a.b.n.e.z[0])) {
            try {
                file = new File(resource.getFile());
                if (!file.canRead()) {
                    a.b.n.e.b = false;
                    return;
                }
            }
            catch (Exception ex) {
                a.b.n.e.b = false;
                return;
            }
        }
        a.a(a.b.n.e.z[1]);
        while (!this.b()) {
            try {
                Thread.sleep(this.d);
            }
            catch (InterruptedException ex2) {}
            if (!this.b() && this.c[0] < file.lastModified()) {
                this.c[0] = file.lastModified();
                a.a(a.b.n.e.z[3]);
                try {
                    final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    final a.b.n.b b = (a.b.n.b)a.b.g.b.a().d();
                    a.b.n.b.a(bufferedInputStream);
                }
                catch (IOException ex3) {
                    this.a();
                }
                catch (ClassCastException ex4) {
                    this.a();
                }
            }
        }
        a.b.n.e.b = false;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "e&1*".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0003';
                    break;
                }
                case 1: {
                    c2 = 'O';
                    break;
                }
                case 2: {
                    c2 = ']';
                    break;
                }
                case 3: {
                    c2 = 'O';
                    break;
                }
                default: {
                    c2 = '\u0011';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "G63.|j,}#~do> \u007fe&::cb&) \u007f#;5=tb+}&b#=(!\u007fj!:a".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0003';
                    break;
                }
                case 1: {
                    c4 = 'O';
                    break;
                }
                case 2: {
                    c4 = ']';
                    break;
                }
                case 3: {
                    c4 = 'O';
                    break;
                }
                default: {
                    c4 = '\u0011';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "ba?a\u007f-*".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0003';
                    break;
                }
                case 1: {
                    c6 = 'O';
                    break;
                }
                case 2: {
                    c6 = ']';
                    break;
                }
                case 3: {
                    c6 = 'O';
                    break;
                }
                default: {
                    c6 = '\u0011';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "V?9.ej!:o}l(},~m)4(dq.)&~m<s".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0003';
                    break;
                }
                case 1: {
                    c8 = 'O';
                    break;
                }
                case 2: {
                    c8 = ']';
                    break;
                }
                case 3: {
                    c8 = 'O';
                    break;
                }
                default: {
                    c8 = '\u0011';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        e.z = z;
        e.b = false;
    }
}
