// 
// Decompiled by Procyon v0.5.30
// 

package a.b.e;

import org.a.d.g;
import a.b.o.c.h;
import a.b.h.c.d;
import a.b.i.k;
import a.b.g.b;
import org.a.d.i;
import java.util.Vector;
import org.a.c.f;
import a.b.b.a;

public abstract class e implements Runnable, a
{
    private static f a;
    private long b;
    private Vector c;
    private i d;
    private o e;
    private boolean f;
    private String g;
    private int h;
    private static String[] z;
    
    public e() {
        this(Integer.MAX_VALUE);
    }
    
    public e(final int n) {
        this.f = false;
        this.g = a.b.e.e.z[6];
        this.a(n);
        this.a(500L);
        final ThreadGroup a = a.b.g.b.a().c().a();
        if (a.b.e.e.a == null) {
            a.b.e.e.a = a.b.g.b.a().d().a(this.getClass().getName());
        }
        this.c = new Vector();
        this.d = new i();
        if (a != null) {
            this.e = new o(a, this, this.g);
        }
        else {
            this.e = new o(this, this.g);
        }
        this.e.start();
    }
    
    public void a(final int h) {
        if (h < 1) {
            this.h = Integer.MAX_VALUE;
        }
        else {
            this.h = h;
        }
    }
    
    public void a(final long b) {
        if (b < 0L) {
            this.b = 500L;
        }
        else {
            this.b = b;
        }
    }
    
    public d[] a(final k[] array) throws c {
        if (array == null) {
            return null;
        }
        final d[] b = this.b(array);
        if (b == null) {
            a.b.e.e.a.b(a.b.e.e.z[0]);
            return null;
        }
        if (b.length != array.length) {
            a.b.e.e.a.b(a.b.e.e.z[2] + array.length + a.b.e.e.z[3] + b.length);
        }
        for (int i = 0; i < b.length; ++i) {
            if (b[i] != null) {
                this.a(b[i]);
            }
            else {
                a.b.e.e.a.b(a.b.e.e.z[1] + i);
            }
        }
        return b;
    }
    
    protected abstract d[] b(final k[] p0) throws c;
    
    public void a(final b b) {
        if (b != null) {
            this.c.addElement(b);
        }
    }
    
    protected void a(final d d) {
        if (d != null) {
            final h a = d.a();
            if (a != null) {
                for (int i = 0; i < this.c.size(); ++i) {
                    a.a((b)this.c.elementAt(i));
                }
            }
        }
    }
    
    public void a(final a.b.b.c c) {
        if (c != null) {
            try {
                this.d.a(c);
            }
            catch (ClassCastException ex) {}
        }
    }
    
    protected void a() {
        int a = this.d.a();
        if (a <= 0) {
            return;
        }
        a.b.h.c.c[] array = new a.b.h.c.c[a];
        k[] array2 = new k[a];
        int n = 0;
        for (int i = 0; i < a; ++i) {
            a.b.b.d d = null;
            try {
                d = (a.b.b.d)this.d.b();
            }
            catch (g g) {}
            catch (ClassCastException ex) {}
            final int n2 = i - n;
            if (d != null) {
                array[n2] = (a.b.h.c.c)d.getSource();
                array2[n2] = d.a();
            }
            if (array[n2] == null || array2[n2] == null) {
                if (array[n2] != null) {
                    array[n2].a(null);
                }
                ++n;
                a.b.e.e.a.b(a.b.e.e.z[4]);
            }
        }
        if (n != 0) {
            final int n3 = a - n;
            if (n3 > 0) {
                final a.b.h.c.c[] array3 = new a.b.h.c.c[n3];
                final k[] array4 = new k[n3];
                for (int j = 0; j < n3; ++j) {
                    array3[j] = array[j];
                    array4[j] = array2[j];
                }
                array = array3;
                array2 = array4;
            }
            a = n3;
        }
        int min;
        for (int k = 0; k < a; k += min) {
            min = Math.min(this.h, a - k);
            final k[] array5 = new k[min];
            for (int l = 0; l < min; ++l) {
                array5[l] = array2[l + k];
            }
            final a.b.h.c.c[] array6 = new a.b.h.c.c[min];
            for (int n4 = 0; n4 < min; ++n4) {
                array6[n4] = array[n4 + k];
            }
            this.a(array5, array6);
        }
    }
    
    protected void a(final k[] array, final a.b.h.c.c[] array2) {
        d[] a = null;
        try {
            a = this.a(array);
        }
        catch (c c) {
            a.b.e.e.a.c(a.b.e.e.z[5], c);
        }
        if (a != null) {
            for (int i = 0; i < array2.length; ++i) {
                if (i < a.length) {
                    array2[i].a(a[i]);
                }
                else {
                    array2[i].a(null);
                }
            }
        }
        else {
            for (int j = 0; j < array2.length; ++j) {
                array2[j].a(null);
            }
        }
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        if (this.f || !(currentThread instanceof o)) {
            return;
        }
        this.f = true;
        final o o = (o)currentThread;
        while (!o.b()) {
            this.a();
            if (!o.b()) {
                try {
                    Thread.sleep(this.b);
                }
                catch (InterruptedException ex) {}
            }
        }
        this.f = false;
    }
    
    static {
        final String[] z = new String[7];
        final int n = 0;
        final char[] charArray = ",sof+\ne*f\u007f\u0001tfk\u007f+hyw3\u000exYb+O`xu>\u0016/".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'o';
                    break;
                }
                case 1: {
                    c2 = '\u0001';
                    break;
                }
                case 2: {
                    c2 = '\n';
                    break;
                }
                case 3: {
                    c2 = '\u0007';
                    break;
                }
                default: {
                    c2 = '_';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = ",sof+\ne*f\u007f\u0001tfk\u007f+hyw3\u000exYb+O`~'6\u0001eo\u007feO".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'o';
                    break;
                }
                case 1: {
                    c4 = '\u0001';
                    break;
                }
                case 2: {
                    c4 = '\n';
                    break;
                }
                case 3: {
                    c4 = '\u0007';
                    break;
                }
                default: {
                    c4 = '_';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = ",neu;\u0006oks0\u001d!nn;Ooes\u007f\u001ddib6\u0019d*s7\n!o\u007f/\nb~b;Oo\u007fj=\ns*h9OEct/\u0003`sT:\u001br$'\u007f*yzb<\u001bdn=\u007f".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'o';
                    break;
                }
                case 1: {
                    c6 = '\u0001';
                    break;
                }
                case 2: {
                    c6 = '\n';
                    break;
                }
                case 3: {
                    c6 = '\u0007';
                    break;
                }
                default: {
                    c6 = '_';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "OfeseO".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'o';
                    break;
                }
                case 1: {
                    c8 = '\u0001';
                    break;
                }
                case 2: {
                    c8 = '\n';
                    break;
                }
                case 3: {
                    c8 = '\u0007';
                    break;
                }
                default: {
                    c8 = '_';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "<jcw/\u0006om'.\u001ad\u007fb;OEct/\u0003`sT:\u001b!xb.\u001adys\u007f\u000bto'1\u001amf',\u0000txd:Onx'1\u001amf'/\u000eskj+\nsy)".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'o';
                    break;
                }
                case 1: {
                    c10 = '\u0001';
                    break;
                }
                case 2: {
                    c10 = '\n';
                    break;
                }
                case 3: {
                    c10 = '\u0007';
                    break;
                }
                default: {
                    c10 = '_';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = ":oke3\n!~h\u007f\bd~'>OEct/\u0003`sT:\u001b!nr:Oue':\u001dseue".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'o';
                    break;
                }
                case 1: {
                    c12 = '\u0001';
                    break;
                }
                case 2: {
                    c12 = '\n';
                    break;
                }
                case 3: {
                    c12 = '\u0007';
                    break;
                }
                default: {
                    c12 = '_';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "*woi+=dyw0\u0001eou".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'o';
                    break;
                }
                case 1: {
                    c14 = '\u0001';
                    break;
                }
                case 2: {
                    c14 = '\n';
                    break;
                }
                case 3: {
                    c14 = '\u0007';
                    break;
                }
                default: {
                    c14 = '_';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        e.z = z;
    }
}
