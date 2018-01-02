// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import java.applet.AudioClip;
import neat.kb;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import neat.system.graphics.renderer.a;
import java.awt.Graphics;
import neat.system.graphics.renderer.h;
import neat.system.gb;
import neat.system.graphics.renderer.g;
import neat.system.graphics.renderer.e;
import neat.system.graphics.renderer.b;
import java.awt.image.ImageObserver;
import neat.nb;
import java.awt.Image;
import java.awt.Component;
import java.awt.Toolkit;
import neat.system.pb;
import neat.system.lb;
import neat.system.graphics.renderer.p;
import neat.system.graphics.renderer.f;

public class j extends f
{
    private p a;
    private lb b;
    private pb c;
    private m d;
    private static String[] z;
    
    private Toolkit a() {
        final Component a = this.c.a();
        if (a != null) {
            return a.getToolkit();
        }
        return Toolkit.getDefaultToolkit();
    }
    
    public Image a(final int n, final int n2) {
        Image image = null;
        int n3 = 0;
        while (true) {
            try {
                final Component a = this.c.a();
                if (a != null) {
                    image = a.createImage(n, n2);
                }
                break;
            }
            catch (Throwable t2) {
                final Throwable t = t2;
                if (++n3 >= 3) {
                    throw new RuntimeException(j.z[7] + n + j.z[6] + n2 + j.z[5] + nb.a(t));
                }
                try {
                    System.gc();
                    Thread.yield();
                    Thread.sleep(500L);
                    System.gc();
                    Thread.yield();
                    Thread.sleep(500L);
                }
                catch (Throwable t3) {}
            }
        }
        return image;
    }
    
    private boolean a(final Image image) {
        final Toolkit a = this.a();
        boolean prepareImage = a.prepareImage(image, -1, -1, null);
        int n = 0;
        while (!prepareImage) {
            if (n >= 1) {
                Thread.yield();
            }
            if (n >= 4) {
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex) {}
            }
            ++n;
            final int checkImage = a.checkImage(image, -1, -1, null);
            prepareImage = ((checkImage & 0x30) != 0x0);
            if ((checkImage & 0xC0) != 0x0) {
                break;
            }
        }
        return prepareImage;
    }
    
    public b a() {
        return i.a();
    }
    
    public void a(final b b) {
        b.f();
    }
    
    public g a(final e e) {
        final neat.system.graphics.k a = neat.system.graphics.k.a(this.b, e);
        a.a(this.d);
        return a;
    }
    
    public void a(final g g) {
        g.f();
    }
    
    public neat.system.graphics.renderer.m b() {
        return n.a();
    }
    
    public void a(final neat.system.graphics.renderer.m m) {
        m.f();
    }
    
    public g a(final int n, final int n2) {
        return l.a(this.b, n, n2);
    }
    
    public g a(final int n, final int n2, final int n3) {
        return null;
    }
    
    public g b(final e e) {
        return l.a(this.c, e);
    }
    
    public void b(final g g) {
        g.f();
    }
    
    public boolean c() {
        return false;
    }
    
    public void d() {
    }
    
    public void e() {
    }
    
    public int f() {
        return this.c.b();
    }
    
    public int g() {
        return this.c.c();
    }
    
    public void a(final boolean b) {
    }
    
    public boolean h() {
        return false;
    }
    
    public boolean a(final boolean b) {
        if (this.d != null) {
            this.d.f();
            this.d = null;
        }
        if (!b) {
            return true;
        }
        this.d = m.a(this.b, this.f(), this.g());
        this.a.a(this.d);
        return true;
    }
    
    boolean b() {
        return this.d.b();
    }
    
    void a(final Graphics graphics, final boolean b) {
        if (graphics == null) {
            return;
        }
        if (b || this.b()) {
            final a h = neat.system.graphics.renderer.a.h();
            this.d.b(h);
            int b2 = h.b;
            int c = h.c;
            int f = h.b + h.d;
            int g = h.c + h.e;
            h.f();
            if (b) {
                b2 = 0;
                c = 0;
                f = this.d.f();
                g = this.d.g();
            }
            graphics.drawImage(this.d.a(), b2, c, f, g, b2, c, f, g, null);
        }
        this.a.p();
    }
    
    public neat.system.graphics.renderer.l i() {
        return neat.system.graphics.d.d();
    }
    
    public void a(final neat.system.graphics.renderer.l l) {
        l.f();
    }
    
    public neat.system.graphics.renderer.j a(final Image image, final int n, final int n2, final int n3) {
        if (image == null) {
            return null;
        }
        final c c = neat.system.graphics.c.c();
        c.a(image);
        return c;
    }
    
    public neat.system.graphics.renderer.j a(final byte[] array, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n2 * n3;
        if (n < n5 * 3) {
            throw new RuntimeException(j.z[11] + n + j.z[9] + n2 + j.z[8] + n3 + ")");
        }
        int n6 = 0;
        final int[] array2 = new int[n5];
        for (int i = 0; i < n5; ++i) {
            array2[i] = ((array[n6++] & 0xFF) << 16 | (array[n6++] & 0xFF) << 8 | (array[n6++] & 0xFF) | 0xFF000000);
        }
        int n7 = 0;
        Image image;
        while (true) {
            try {
                final MemoryImageSource memoryImageSource = new MemoryImageSource(n2, n3, new DirectColorModel(32, 16711680, 65280, 255), array2, 0, n2);
                memoryImageSource.setAnimated(true);
                image = this.a().createImage(memoryImageSource);
                if (image == null) {
                    return null;
                }
                final Image a = this.a(image.getWidth(null), image.getHeight(null));
                if (a != null) {
                    a.getGraphics().drawImage(image, 0, 0, null);
                    try {
                        image.flush();
                    }
                    catch (Throwable t3) {}
                    image = a;
                }
                break;
            }
            catch (Throwable t2) {
                final Throwable t = t2;
                if (++n7 >= 3) {
                    throw new RuntimeException(j.z[10] + n + j.z[2] + nb.a(t));
                }
                try {
                    System.gc();
                    Thread.yield();
                    Thread.sleep(500L);
                    System.gc();
                    Thread.yield();
                    Thread.sleep(500L);
                }
                catch (Throwable t4) {}
            }
        }
        return this.a(image, n2, n3, n4);
    }
    
    public neat.system.graphics.renderer.j a(final kb kb, final byte[] array, final int n, final int n2) {
        if (kb.c(p.f)) {
            int n3 = 0;
            Image image;
            while (true) {
                try {
                    image = this.a().createImage(array, 0, n);
                    if (image == null) {
                        return null;
                    }
                    this.a(image);
                    if (n2 < 0) {
                        final Image a = this.a(image.getWidth(null), image.getHeight(null));
                        if (a != null) {
                            a.getGraphics().drawImage(image, 0, 0, null);
                            try {
                                image.flush();
                            }
                            catch (Throwable t5) {}
                            image = a;
                        }
                    }
                    break;
                }
                catch (Throwable t2) {
                    final Throwable t = t2;
                    if (++n3 >= 3) {
                        throw new RuntimeException(j.z[4] + kb + j.z[3] + n + j.z[2] + nb.a(t));
                    }
                    try {
                        System.gc();
                        Thread.yield();
                        Thread.sleep(500L);
                        System.gc();
                        Thread.yield();
                        Thread.sleep(500L);
                    }
                    catch (Throwable t6) {}
                }
            }
            return this.a(image, image.getWidth(null), image.getHeight(null), n2);
        }
        if (!kb.c(p.h)) {
            return null;
        }
        if (n < 5) {
            return null;
        }
        int n4 = 0;
        final int n5 = (array[n4++] & 0xFF) << 8 | (array[n4++] & 0xFF);
        final int n6 = (array[n4++] & 0xFF) << 8 | (array[n4++] & 0xFF);
        if (n5 <= 0 || n6 <= 0) {
            return null;
        }
        int n7 = array[n4++] & 0xFF;
        if (n7 == 0) {
            n7 = 256;
        }
        if (n7 <= 0) {
            return null;
        }
        final int n8 = n4;
        int n9 = n4 + n7 * 3;
        final int n10 = n5 * n6;
        if (n9 + n10 > n) {
            return null;
        }
        final int[] array2 = new int[n10];
        for (int i = 0; i < n10; ++i) {
            int n11 = array[n9++] & 0xFF;
            if (n11 >= n7) {
                n11 = 0;
            }
            final int n12 = n11 * 3 + n8;
            int n13 = (array[n12] & 0xFF) << 16 | (array[n12 + 1] & 0xFF) << 8 | (array[n12 + 2] & 0xFF);
            if (n2 >= 0 && n13 != n2) {
                n13 |= 0xFF000000;
            }
            array2[i] = n13;
        }
        int n14 = 0;
        Image image2;
        while (true) {
            try {
                MemoryImageSource memoryImageSource;
                if (n2 < 0) {
                    memoryImageSource = new MemoryImageSource(n5, n6, new DirectColorModel(32, 16711680, 65280, 255), array2, 0, n5);
                }
                else {
                    memoryImageSource = new MemoryImageSource(n5, n6, array2, 0, n5);
                }
                memoryImageSource.setAnimated(true);
                image2 = this.a().createImage(memoryImageSource);
                if (image2 == null) {
                    return null;
                }
                if (n2 < 0) {
                    final Image a2 = this.a(image2.getWidth(null), image2.getHeight(null));
                    if (a2 != null) {
                        a2.getGraphics().drawImage(image2, 0, 0, null);
                        try {
                            image2.flush();
                        }
                        catch (Throwable t7) {}
                        image2 = a2;
                    }
                }
                break;
            }
            catch (Throwable t4) {
                final Throwable t3 = t4;
                if (++n14 >= 3) {
                    throw new RuntimeException(j.z[1] + kb + j.z[0] + n + j.z[2] + nb.a(t3));
                }
                try {
                    System.gc();
                    Thread.yield();
                    Thread.sleep(500L);
                    System.gc();
                    Thread.yield();
                    Thread.sleep(500L);
                }
                catch (Throwable t8) {}
            }
        }
        return this.a(image2, n5, n6, n2);
    }
    
    public neat.system.graphics.renderer.j a(final Object o) {
        if (!(o instanceof AudioClip)) {
            return null;
        }
        final c c = neat.system.graphics.c.c();
        c.a((AudioClip)o);
        return c;
    }
    
    public void a(final kb kb) {
        System.exit(0);
    }
    
    public void b(final kb kb) {
    }
    
    public boolean j() {
        return this.c.a();
    }
    
    public void k() {
    }
    
    j(final pb c, final neat.system.kb kb) {
        this.c = c;
        this.b = (lb)kb.b(lb.i);
        this.a = (p)kb.b(p.j);
    }
    
    static {
        final String[] z = new String[12];
        final int n = 0;
        final char[] charArray = "\u0018q9\u0019\u0019Qav".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0102: {
                if (n2 > 1) {
                    break Label_0102;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '4';
                            break;
                        }
                        case 1: {
                            c2 = '\u0013';
                            break;
                        }
                        case 2: {
                            c2 = 'L';
                            break;
                        }
                        case 3: {
                            c2 = '\u007f';
                            break;
                        }
                        default: {
                            c2 = '\u007f';
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
        final char[] charArray2 = "qk/\u001a\u000f@z#\u0011_]}l\u0016\u0012Ut)_\u0019]\u007f)_\u0013[r(\u0016\u0011S3d\u0019\u0016Xv\"\u001e\u0012Q)".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0222: {
                if (n6 > 1) {
                    break Label_0222;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '4';
                            break;
                        }
                        case 1: {
                            c4 = '\u0013';
                            break;
                        }
                        case 2: {
                            c4 = 'L';
                            break;
                        }
                        case 3: {
                            c4 = '\u007f';
                            break;
                        }
                        default: {
                            c4 = '\u007f';
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
        final char[] charArray3 = "\u001d3l^u".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0342: {
                if (n10 > 1) {
                    break Label_0342;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '4';
                            break;
                        }
                        case 1: {
                            c6 = '\u0013';
                            break;
                        }
                        case 2: {
                            c6 = 'L';
                            break;
                        }
                        case 3: {
                            c6 = '\u007f';
                            break;
                        }
                        default: {
                            c6 = '\u007f';
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
        final char[] charArray4 = "\u00183.\n\u0019Rv>E".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0462: {
                if (n14 > 1) {
                    break Label_0462;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '4';
                            break;
                        }
                        case 1: {
                            c8 = '\u0013';
                            break;
                        }
                        case 2: {
                            c8 = 'L';
                            break;
                        }
                        case 3: {
                            c8 = '\u007f';
                            break;
                        }
                        default: {
                            c8 = '\u007f';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "qk/\u001a\u000f@z#\u0011_]}l\u0016\u0012Ut)_\u0019]\u007f)_\u0013[r(\u0016\u0011S3d\u0011\u001eYvv".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0582: {
                if (n18 > 1) {
                    break Label_0582;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '4';
                            break;
                        }
                        case 1: {
                            c10 = '\u0013';
                            break;
                        }
                        case 2: {
                            c10 = 'L';
                            break;
                        }
                        case 3: {
                            c10 = '\u007f';
                            break;
                        }
                        default: {
                            c10 = '\u007f';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "\u001d3mu".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0698: {
                if (n22 > 1) {
                    break Label_0698;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '4';
                            break;
                        }
                        case 1: {
                            c12 = '\u0013';
                            break;
                        }
                        case 2: {
                            c12 = 'L';
                            break;
                        }
                        case 3: {
                            c12 = '\u007f';
                            break;
                        }
                        default: {
                            c12 = '\u007f';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "\u00149l".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0818: {
                if (n26 > 1) {
                    break Label_0818;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '4';
                            break;
                        }
                        case 1: {
                            c14 = '\u0013';
                            break;
                        }
                        case 2: {
                            c14 = 'L';
                            break;
                        }
                        case 3: {
                            c14 = '\u007f';
                            break;
                        }
                        default: {
                            c14 = '\u007f';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "qk/\u001a\u000f@z#\u0011_]}l\u0016\u0012Ut)_\u001cFv-\u000b\u0016ZtlW".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0938: {
                if (n30 > 1) {
                    break Label_0938;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '4';
                            break;
                        }
                        case 1: {
                            c16 = '\u0013';
                            break;
                        }
                        case 2: {
                            c16 = 'L';
                            break;
                        }
                        case 3: {
                            c16 = '\u007f';
                            break;
                        }
                        default: {
                            c16 = '\u007f';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "\u0014kl".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1058: {
                if (n34 > 1) {
                    break Label_1058;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = '4';
                            break;
                        }
                        case 1: {
                            c18 = '\u0013';
                            break;
                        }
                        case 2: {
                            c18 = 'L';
                            break;
                        }
                        case 3: {
                            c18 = '\u007f';
                            break;
                        }
                        default: {
                            c18 = '\u007f';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "\u00183?\u0016\u0005Q)".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1178: {
                if (n38 > 1) {
                    break Label_1178;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = '4';
                            break;
                        }
                        case 1: {
                            c20 = '\u0013';
                            break;
                        }
                        case 2: {
                            c20 = 'L';
                            break;
                        }
                        case 3: {
                            c20 = '\u007f';
                            break;
                        }
                        default: {
                            c20 = '\u007f';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        z[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "qk/\u001a\u000f@z#\u0011_]}l\u0016\u0012Ut)_\u0019]\u007f)_\u0013[r(\u0016\u0011S3d\u001d\nRu)\rE".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1298: {
                if (n42 > 1) {
                    break Label_1298;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = '4';
                            break;
                        }
                        case 1: {
                            c22 = '\u0013';
                            break;
                        }
                        case 2: {
                            c22 = 'L';
                            break;
                        }
                        case 3: {
                            c22 = '\u007f';
                            break;
                        }
                        default: {
                            c22 = '\u007f';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        z[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "ca#\u0011\u0018\u0014`%\u0005\u001a\u0014|*_\rQ`#\n\rWvm_WFv?E".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1418: {
                if (n46 > 1) {
                    break Label_1418;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = '4';
                            break;
                        }
                        case 1: {
                            c24 = '\u0013';
                            break;
                        }
                        case 2: {
                            c24 = 'L';
                            break;
                        }
                        case 3: {
                            c24 = '\u007f';
                            break;
                        }
                        default: {
                            c24 = '\u007f';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 <= n48) {
                z[n45] = new String(charArray12).intern();
                j.z = z;
                return;
            }
            continue;
        }
    }
}
