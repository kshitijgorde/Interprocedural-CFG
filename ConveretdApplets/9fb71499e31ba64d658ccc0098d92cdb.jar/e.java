import java.awt.Graphics;
import java.awt.Color;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.awt.image.RGBImageFilter;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.awt.image.PixelGrabber;
import java.util.Hashtable;
import java.awt.Image;
import java.util.Vector;
import sexy.gui.SexyApplet;
import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

public class e implements Runnable
{
    public j[] a;
    public AudioClip[] b;
    public SexyApplet c;
    public int d;
    public int[][] e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public String o;
    public Vector p;
    public Vector q;
    public j[] r;
    public int s;
    public int t;
    public int u;
    public int v;
    public Image w;
    public long x;
    public Hashtable y;
    private static /* synthetic */ Class z;
    
    public void a(final String s, final String[] array) {
        String s2 = null;
        String s3 = null;
        String substring = "";
        String substring2 = s;
        final int lastIndex = substring2.lastIndexOf(47);
        if (lastIndex != -1) {
            substring = substring2.substring(0, lastIndex + 1);
            substring2 = substring2.substring(lastIndex + 1);
        }
        if (substring2.charAt(0) == '?') {
            s2 = substring + substring2.substring(1);
            s3 = substring + "_" + substring2.substring(1);
        }
        else if (substring2.charAt(0) == '_') {
            s3 = substring + substring2;
        }
        else {
            s2 = substring + substring2;
        }
        array[0] = s2;
        array[1] = s3;
    }
    
    public synchronized void a() {
        this.n = true;
        this.notifyAll();
        for (int i = 0; i < this.d; ++i) {
            this.b[i].stop();
        }
    }
    
    public synchronized j a(final String s, final String s2) {
        final j j = new j();
        Image image = null;
        Image image2 = null;
        if (s != null) {
            try {
                image = this.c.getImage(this.c.getCodeBase(), this.o + s);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
            this.y.put(image, j);
        }
        if (s2 != null) {
            try {
                image2 = this.c.getImage(this.c.getCodeBase(), this.o + s2);
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                return null;
            }
            this.y.put(image2, j);
        }
        j.a(this, image, image2);
        return j;
    }
    
    public j a(final String s) {
        final String[] array = new String[2];
        this.a(s, array);
        return this.a(array[0], array[1]);
    }
    
    public String[] b() {
        return new String[0];
    }
    
    public synchronized void c() {
        Enumeration<j> enumeration = this.y.elements();
        while (enumeration.hasMoreElements()) {
            final j j = enumeration.nextElement();
            if (j.n != null && j.o == null) {
                (j.o = new PixelGrabber(j.n, 0, 0, -1, -1, true)).startGrabbing();
            }
            if (j.p != null && j.q == null) {
                (j.q = new PixelGrabber(j.p, 0, 0, -1, -1, true)).startGrabbing();
            }
            if (((j.n != null && (j.o.getStatus() & 0x40) != 0x0) || (j.p != null && (j.q.getStatus() & 0x40) != 0x0)) && !j.r) {
                System.out.println("Failed to load async image");
                j.r = true;
            }
            if ((j.n == null || (j.o.getStatus() & 0x30) != 0x0) && (j.p == null || (j.q.getStatus() & 0x30) != 0x0)) {
                int[] array = null;
                int[] array2 = null;
                int a = 0;
                int b = 0;
                if (j.n != null) {
                    a = j.o.getWidth();
                    b = j.o.getHeight();
                    array = (int[])j.o.getPixels();
                    this.y.remove(j.n);
                    enumeration = this.y.elements();
                    j.n.flush();
                    j.n = null;
                }
                if (j.p != null) {
                    a = j.q.getWidth();
                    b = j.q.getHeight();
                    array2 = (int[])j.q.getPixels();
                    this.y.remove(j.p);
                    enumeration = this.y.elements();
                    j.p.flush();
                    j.p = null;
                }
                final int[] c = new int[a * b];
                if (array != null) {
                    if (array2 != null) {
                        for (int i = 0; i < c.length; ++i) {
                            c[i] = (array2[i] << 24 | (array[i] & 0xFFFFFF));
                        }
                    }
                    else {
                        for (int k = 0; k < c.length; ++k) {
                            c[k] = array[k];
                        }
                    }
                }
                else if (array2 != null) {
                    for (int l = 0; l < c.length; ++l) {
                        c[l] = (array2[l] << 24 | 0xFFFFFF);
                    }
                }
                synchronized (this) {
                    j.c = c;
                    j.a = a;
                    j.b = b;
                    j.g();
                }
            }
        }
    }
    
    public j a(final j j, final double n) {
        return new j(this.a(j.b(), j.h(), j.c(), n), j.h(), j.c());
    }
    
    public void a(final int[] array, final int n, final int n2, final int n3, final int n4, final int[] array2, final int n5, final int n6) {
        for (int i = 0; i < n6; ++i) {
            for (int j = 0; j < n5; ++j) {
                final int n7 = array2[i * n5 + j];
                if ((n7 >> 24 & 0xFF) >= 128) {
                    array[(n2 + i) * n3 + n + j] = n7;
                }
            }
        }
    }
    
    public double[] a(final int[] array) {
        final double[] array2 = new double[array.length * 3];
        for (int i = 0; i < array.length; ++i) {
            final double n = (array[i] >> 16 & 0xFF) / 255.0;
            final double n2 = (array[i] >> 8 & 0xFF) / 255.0;
            final double n3 = (array[i] & 0xFF) / 255.0;
            final double max = Math.max(n, Math.max(n2, n3));
            final double min = Math.min(n, Math.min(n2, n3));
            double n4 = -1.0;
            double n5 = 0.0;
            final double n6 = 0.50000000001 * (min + max);
            final double n7 = max - min;
            if (n7 != 0.0) {
                n5 = n7 / ((n6 <= 0.5) ? (min + max) : (2.0 - max - min));
                double n8;
                if (n == max) {
                    n8 = ((n2 == min) ? (5.0 + (max - n3) / n7) : (1.0 - (max - n2) / n7));
                }
                else if (n2 == max) {
                    n8 = ((n3 == min) ? (1.0 + (max - n) / n7) : (3.0 - (max - n3) / n7));
                }
                else {
                    n8 = ((n == min) ? (3.0 + (max - n2) / n7) : (5.0 - (max - n) / n7));
                }
                n4 = n8 / 6.0;
            }
            array2[i * 3] = n4;
            array2[i * 3 + 1] = n5;
            array2[i * 3 + 2] = n6;
        }
        return array2;
    }
    
    public String a(final String s, final boolean b) {
        String s2 = null;
        InputStream inputStream = null;
        try {
            final URL url = new URL(this.c.getCodeBase(), this.o + s);
            synchronized (this) {
                inputStream = url.openConnection().getInputStream();
            }
            s2 = "";
            final byte[] array = new byte[256];
            int n = 0;
            while (true) {
                final byte b2 = (byte)inputStream.read();
                if (b2 == -1) {
                    break;
                }
                array[n++] = b2;
                if (n != 256) {
                    continue;
                }
                s2 = s2.concat(new String(array, 0, n));
                n = 0;
            }
            s2 = s2.concat(new String(array, 0, n));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            if (b) {
                this.c.a("loading", "text file: " + s);
            }
        }
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        catch (Exception ex2) {}
        return s2;
    }
    
    public String b(final String s) {
        return this.a(s, true);
    }
    
    public j[] a(final Image[] array, final boolean b, final int[][] array2) {
        final int length = array.length;
        final j[] array3 = new j[length];
        final PixelGrabber[] array4 = new PixelGrabber[length];
        for (int i = 0; i < length; ++i) {
            if (array[i] != null) {
                (array4[i] = new PixelGrabber(array[i], 0, 0, -1, -1, true)).startGrabbing();
            }
        }
        for (int j = 0; j < length; ++j) {
            if (array4[j] != null) {
                if (b && this.n) {
                    return null;
                }
                try {
                    array4[j].grabPixels();
                    if ((array4[j].getStatus() & 0x80) != 0x0) {
                        if (b) {
                            this.c.e("Failed to load image " + this.d()[j]);
                        }
                        else {
                            System.err.println("CopyImages Failed to copy image #" + j);
                        }
                        return null;
                    }
                    final int[] array5 = (int[])array4[j].getPixels();
                    if (array2 != null) {
                        array2[j] = array5;
                    }
                    array3[j] = new j(array5, array4[j].getWidth(), array4[j].getHeight());
                    if (b) {
                        ++this.t;
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return array3;
    }
    
    public j a(final j j, final RGBImageFilter rgbImageFilter) {
        final j i = new j();
        i.a(j.a, j.b);
        new n(i).a(j, 0, 0);
        if (rgbImageFilter != null) {
            for (int k = 0; k < i.b; ++k) {
                for (int l = 0; l < i.a; ++l) {
                    i.c[l + k * i.a] = rgbImageFilter.filterRGB(l, k, i.c[l + k * i.a]);
                }
            }
        }
        return i;
    }
    
    public String[] d() {
        return new String[0];
    }
    
    public int[] a(final j j) {
        return j.b();
    }
    
    public int[] a(final int[] array, final int n, final int n2, final double n3) {
        final int[] array2 = new int[n * n2];
        final float n4 = (float)n3;
        if (n3 >= 1.0) {
            final float n5 = n / 2 - n / n4 / 2.0f;
            float n6 = n2 / 2 - n2 / n4 / 2.0f;
            final float n7 = 1.0f / n4;
            for (int i = 0; i < n2; ++i) {
                float n8 = n5;
                for (int j = 0; j < n; ++j) {
                    final int[][][] array3 = new int[2][2][4];
                    int n9 = 0;
                    do {
                        int n10 = 0;
                        do {
                            final int n11 = (int)n8 + n10 - 1;
                            final int n12 = (int)n6 + n9 - 1;
                            if (n11 >= 0 && n11 < n && n12 >= 0 && n12 < n2) {
                                final int n13 = array[n12 * n + n11];
                                array3[n9][n10][0] = (n13 & 0xFF);
                                array3[n9][n10][1] = (n13 >> 8 & 0xFF);
                                array3[n9][n10][2] = (n13 >> 16 & 0xFF);
                                array3[n9][n10][3] = (n13 >> 24 & 0xFF);
                            }
                            else {
                                array3[n9][n10][0] = 0;
                                array3[n9][n10][1] = 0;
                                array3[n9][n10][2] = 0;
                                array3[n9][n10][3] = 0;
                            }
                        } while (++n10 < 2);
                    } while (++n9 < 2);
                    final int[][] array4 = new int[2][4];
                    final float n14 = n8 - (int)n8;
                    int n15 = 0;
                    do {
                        int n16 = 0;
                        do {
                            array4[n15][n16] = (int)(array3[n15][0][n16] * (1.0 - n14) + array3[n15][1][n16] * n14);
                        } while (++n16 < 4);
                    } while (++n15 < 2);
                    final int[] array5 = new int[4];
                    final float n17 = n6 - (int)n6;
                    int n18 = 0;
                    do {
                        array5[n18] = (int)(array4[0][n18] * (1.0 - n17) + array4[1][n18] * n17);
                    } while (++n18 < 4);
                    array2[i * n + j] = (array5[0] | array5[1] << 8 | array5[2] << 16 | array5[3] << 24);
                    n8 += n7;
                }
                n6 += n7;
            }
        }
        else {
            final float[][] array6 = new float[n * n2][4];
            for (int k = 0; k < n2; ++k) {
                for (int l = 0; l < n; ++l) {
                    final float n19 = n4;
                    final float n20 = 1.0f / n19;
                    final float n21 = (n - n * n4) / 2.0f + l * n4;
                    final float n22 = (n2 - n2 * n4) / 2.0f + k * n4;
                    int n23 = 0;
                    do {
                        int n24 = 0;
                        do {
                            float n25 = n4 * n4;
                            final int n26 = (int)(n21 + n24 * n19);
                            final int n27 = (int)(n22 + n23 * n19);
                            if (n24 == 0) {
                                final float n28 = ((int)n21 + 1 - n21) * n20;
                                if (n28 < 1.0) {
                                    n25 *= n28;
                                }
                            }
                            else {
                                final float n29 = (n21 + n19 - ((int)n21 + 1)) * n20;
                                if (n29 < 0.0) {
                                    n25 = 0.0f;
                                }
                                else if (n29 < 1.0) {
                                    n25 *= n29;
                                }
                            }
                            if (n23 == 0) {
                                final float n30 = ((int)n22 + 1 - n22) * n20;
                                if (n30 < 1.0) {
                                    n25 *= n30;
                                }
                            }
                            else {
                                final float n31 = (n22 + n19 - ((int)n22 + 1)) * n20;
                                if (n31 < 0.0) {
                                    n25 = 0.0f;
                                }
                                else if (n31 < 1.0) {
                                    n25 *= n31;
                                }
                            }
                            if (n26 >= 0 && n26 < n && n27 >= 0 && n27 < n2) {
                                int n32 = 0;
                                do {
                                    final float[] array7 = array6[n27 * n + n26];
                                    final int n33 = n32;
                                    array7[n33] += (array[k * n + l] >> n32 * 8 & 0xFF) * n25;
                                } while (++n32 < 4);
                            }
                        } while (++n24 < 2);
                    } while (++n23 < 2);
                }
            }
            for (int n34 = 0; n34 < n2; ++n34) {
                for (int n35 = 0; n35 < n; ++n35) {
                    int n36 = 0;
                    do {
                        final int[] array8 = array2;
                        final int n37 = n34 * n + n35;
                        array8[n37] |= Math.min(Math.round(array6[n34 * n + n35][n36]), 255) << n36 * 8;
                    } while (++n36 < 4);
                }
            }
        }
        return array2;
    }
    
    public int[] a(final double[] array) {
        final int[] array2 = new int[array.length];
        for (int i = 0; i < array.length / 3; ++i) {
            final double n = array[i * 3];
            final double n2 = array[i * 3 + 1];
            final double n3 = array[i * 3 + 2];
            final double n4 = (n3 <= 0.5) ? (n3 * (1.0 + n2)) : (n3 + n2 - n3 * n2);
            int n5;
            int n6;
            int n7;
            if (n2 == 0.0 || n == -1.0) {
                n5 = (int)(255.0 * n3 + 0.5);
                n6 = (int)(255.0 * n3 + 0.5);
                n7 = (int)(255.0 * n3 + 0.5);
            }
            else {
                final double n8 = 2.0 * n3 - n4;
                final double n9 = n8 + (n4 - n8) * (6.0 * n - Math.floor(6.0 * n));
                final double n10 = n4 - (n4 - n8) * (6.0 * n - Math.floor(6.0 * n));
                double n11 = 0.0;
                double n12 = 0.0;
                double n13 = 0.0;
                switch ((int)(6.0 * n)) {
                    case 0: {
                        n11 = n4;
                        n12 = n9;
                        n13 = n8;
                        break;
                    }
                    case 1: {
                        n11 = n10;
                        n12 = n4;
                        n13 = n8;
                        break;
                    }
                    case 2: {
                        n11 = n8;
                        n12 = n4;
                        n13 = n9;
                        break;
                    }
                    case 3: {
                        n11 = n8;
                        n12 = n10;
                        n13 = n4;
                        break;
                    }
                    case 4: {
                        n11 = n9;
                        n12 = n8;
                        n13 = n4;
                        break;
                    }
                    case 5: {
                        n11 = n4;
                        n12 = n8;
                        n13 = n10;
                        break;
                    }
                    default: {
                        n11 = n4;
                        n12 = n9;
                        n13 = n8;
                        break;
                    }
                }
                n5 = (int)(255.0 * n11 + 0.5);
                n6 = (int)(255.0 * n12 + 0.5);
                n7 = (int)(255.0 * n13 + 0.5);
                if (n5 > 255) {
                    n5 = 255;
                }
                if (n6 > 255) {
                    n6 = 255;
                }
                if (n7 > 255) {
                    n7 = 255;
                }
            }
            array2[i] = (0xFF000000 | n5 << 16 | n6 << 8 | n7);
        }
        return array2;
    }
    
    public void c(final String s) {
        InputStream inputStream = null;
        final String[] d = this.d();
        final j[] array = new j[d.length];
        try {
            final URL url = new URL(this.c.getCodeBase(), s);
            synchronized (this) {
                inputStream = url.openConnection().getInputStream();
            }
            final ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                final Class<?> forName = Class.forName("sexy.gif.gifReader");
                final Object instance = forName.getConstructor((e.z != null) ? e.z : (e.z = class$("java.io.InputStream"))).newInstance(zipInputStream);
                forName.getMethod("readAll", (Class<?>[])null).invoke(instance, (Object[])null);
                final j j = (j)Class.forName("sexy.gif.gifImage").getMethod("frame", (Class<?>[])null).invoke(forName.getMethod("getFrame", Integer.TYPE).invoke(instance, new Integer(0)), (Object[])null);
                boolean b = false;
                for (int i = 0; i < d.length; ++i) {
                    final String[] array2 = new String[2];
                    this.a(d[i], array2);
                    if (array2[0] != null && array2[0].equals(zipEntry.getName())) {
                        this.a[i] = j;
                        b = true;
                    }
                    else if (array2[1] != null && array2[1].equals(zipEntry.getName())) {
                        array[i] = j;
                        b = true;
                    }
                }
                if (b) {
                    ++this.t;
                }
                else {
                    System.out.println("WARNING: Unreferenced zip image '" + zipEntry.getName() + "'");
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.c.a("loading", "gif zip fail");
        }
        for (int k = 0; k < d.length; ++k) {
            if (array[k] != null) {
                final int[] b2 = array[k].b();
                if (this.a[k] != null) {
                    final int[] b3 = this.a[k].b();
                    for (int l = 0; l < b3.length; ++l) {
                        b3[l] = ((b3[l] & 0xFFFFFF) | (b2[l] & 0xFF) << 24);
                    }
                }
                else {
                    this.a[k] = array[k];
                    for (int n = 0; n < b2.length; ++n) {
                        b2[n] = (0xFFFFFF | (b2[n] & 0xFF) << 24);
                    }
                }
                this.a[k].g();
            }
        }
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void e() {
    }
    
    public e(final SexyApplet c) {
        this.o = "";
        this.p = new Vector();
        this.q = new Vector();
        this.r = new j[10];
        this.y = new Hashtable();
        this.c = c;
        this.w = this.c.createImage(1, 1);
        new Thread(this).start();
    }
    
    public void f() {
        final String[] b = this.b();
        for (int n = 0; n < this.g() && !this.n; ++n) {
            try {
                this.b[n] = this.c.getAudioClip(this.c.getCodeBase(), this.o + "sounds/" + b[n]);
                if (n == 0) {
                    if (System.getProperty("os.name").startsWith("Win")) {
                        this.b[n].loop();
                    }
                }
                else {
                    this.b[n].play();
                    this.b[n].stop();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            ++this.d;
            ++this.t;
        }
        this.m = true;
        if (this.h && this.m) {
            System.out.println("Resource loading time: " + (System.currentTimeMillis() - this.x) + "ms");
        }
        while (true) {
            Label_0285: {
                synchronized (this) {
                    this.notifyAll();
                    break Label_0285;
                }
                synchronized (this) {
                    Label_0264: {
                        try {
                            this.wait();
                            break Label_0264;
                        }
                        catch (Exception ex3) {
                        }
                        // monitorexit(this)
                    }
                }
            }
            if (this.h || this.n) {
                for (int g = this.g(); g < b.length && !this.n; ++g) {
                    try {
                        synchronized (this) {
                            this.b[g] = this.c.getAudioClip(this.c.getCodeBase(), this.o + "sounds/" + b[g]);
                        }
                        if (g == 0) {
                            if (System.getProperty("os.name").startsWith("Win")) {
                                this.b[g].loop();
                            }
                        }
                        else {
                            this.b[g].play();
                            this.b[g].stop();
                        }
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    ++this.d;
                    ++this.t;
                }
                return;
            }
            continue;
        }
    }
    
    public int g() {
        return this.b().length;
    }
    
    public boolean h() {
        final Graphics graphics = this.w.getGraphics();
        graphics.setColor(new Color(255, 0, 0));
        graphics.fillRect(0, 0, 1, 1);
        graphics.dispose();
        final int[] array = { 0 };
        final PixelGrabber pixelGrabber = new PixelGrabber(this.w, 0, 0, 1, 1, array, 0, 1);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return true;
        }
        return (array[0] >> 24 & 0xFF) != 0xFF || (array[0] >> 16 & 0xFF) < 248 || (array[0] >> 8 & 0xFF) != 0x0 || (array[0] & 0xFF) != 0x0;
    }
    
    public int i() {
        return 3500;
    }
    
    public void j() {
    }
    
    public j a(final j j, final int n, final int n2, final int n3, final int n4, final RGBImageFilter rgbImageFilter) {
        final j i = new j();
        i.a(n3, n4);
        new n(i).a(j, -n, -n2);
        if (rgbImageFilter != null) {
            for (int k = 0; k < i.b; ++k) {
                for (int l = 0; l < i.a; ++l) {
                    i.c[l + k * i.a] = rgbImageFilter.filterRGB(l, k, i.c[l + k * i.a]);
                }
            }
        }
        return i;
    }
    
    private static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public double k() {
        return 0.0;
    }
    
    public void finalize() throws Throwable {
        System.out.println("Resources finalized");
    }
    
    public void run() {
        while (!this.f) {
            if (this.n) {
                return;
            }
            this.c();
            synchronized (this) {
                Label_0031: {
                    try {
                        this.wait(50L);
                        break Label_0031;
                    }
                    catch (Exception ex3) {
                    }
                    // monitorexit(this)
                }
            }
        }
        if (this.i) {
            this.f();
            return;
        }
        this.i = true;
        final String[] d = this.d();
        this.b();
        try {
            Thread.currentThread().setPriority(1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.l = this.h();
            this.s = this.g() + d.length;
            if (this.c.s) {
                System.out.print("GIFS: ");
                for (int i = 0; i < d.length; ++i) {
                    if (d[i].endsWith(".gif")) {
                        System.out.print(d[i] + " ");
                    }
                }
                System.out.println();
                System.out.print("NON-GIFS: ");
                for (int j = 0; j < d.length; ++j) {
                    if (!d[j].endsWith(".gif")) {
                        System.out.print(d[j] + " ");
                    }
                }
                System.out.println();
            }
            for (int k = 0; k < d.length; ++k) {
                final String[] array = new String[2];
                this.a(d[k], array);
                if (array[0] != null && array[1] != null) {
                    ++this.s;
                }
            }
            this.t = 0;
            this.x = System.currentTimeMillis();
            new Thread(this).start();
            final Image[] array2 = new Image[d.length];
            final Image[] array3 = new Image[d.length];
            this.a = new j[d.length];
            final String parameter = this.c.getParameter("RequiredGifZip");
            if (parameter != null) {
                this.c(parameter);
            }
            for (int l = 0; l < d.length; ++l) {
                if (this.a[l] == null) {
                    final String s = d[l];
                    final String[] array4 = new String[2];
                    this.a(this.o + "images/" + s, array4);
                    if (array4[0] != null) {
                        array2[l] = this.c.getImage(this.c.getCodeBase(), array4[0]);
                    }
                    if (array4[1] != null) {
                        array3[l] = this.c.getImage(this.c.getCodeBase(), array4[1]);
                    }
                }
            }
            this.e = new int[array2.length][];
            final j[] a = this.a(array2, true, this.e);
            if (a == null) {
                this.c.b("loading");
                this.j = true;
                return;
            }
            for (int n = 0; n < a.length; ++n) {
                if (a[n] != null) {
                    this.a[n] = a[n];
                }
            }
            final j[] a2 = this.a(array3, true, new int[array3.length][]);
            if (a2 == null) {
                this.c.b("loading");
                this.j = true;
                return;
            }
            for (int n2 = 0; n2 < d.length; ++n2) {
                if (a2[n2] != null) {
                    if (this.a[n2] == null) {
                        final int[] b = a2[n2].b();
                        for (int n3 = 0; n3 < b.length; ++n3) {
                            b[n3] = ((b[n3] & 0xFF) << 24 | 0xFFFFFF);
                        }
                        this.a[n2] = a2[n2];
                    }
                    else {
                        final int[] b2 = this.a[n2].b();
                        final int[] b3 = a2[n2].b();
                        for (int n4 = 0; n4 < b2.length; ++n4) {
                            b2[n4] = ((b2[n4] & 0xFFFFFF) | (b3[n4] & 0xFF) << 24);
                        }
                    }
                    this.a[n2].g();
                }
            }
            if (this.a == null) {
                this.c.b("loading");
                this.j = true;
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            this.j();
            final int n5 = (int)(System.currentTimeMillis() - currentTimeMillis);
            System.out.println("Filter time: " + n5);
            if (n5 > this.i()) {
                this.k = true;
            }
            this.h = true;
            if (this.h && this.m) {
                System.out.println("Resource loading time: " + (System.currentTimeMillis() - this.x) + "ms");
            }
            while (true) {
                Label_1015: {
                    synchronized (this) {
                        this.notifyAll();
                        break Label_1015;
                    }
                    synchronized (this) {
                        Label_0994: {
                            try {
                                this.wait();
                                break Label_0994;
                            }
                            catch (Exception ex4) {
                            }
                            // monitorexit(this)
                        }
                    }
                }
                if (this.m || this.n) {
                    this.g = true;
                    this.c.q();
                    this.e();
                    while (!this.n) {
                        this.c();
                        synchronized (this) {
                            Label_1068: {
                                try {
                                    this.wait(50L);
                                    break Label_1068;
                                }
                                catch (Exception ex5) {
                                }
                                // monitorexit(this)
                            }
                        }
                    }
                    System.out.println("Resource thread exiting");
                    return;
                }
                continue;
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            System.out.println("Resource thread failed due to exception!");
            this.c.a(ex2);
        }
    }
    
    public double l() {
        if (this.g) {
            return 100.0;
        }
        if (this.t < this.s || this.u == 0) {
            return 100.0 * this.t * (1.0 - this.k()) / this.s;
        }
        return 100.0 * (1.0 - this.k() + this.v * this.k() / this.u);
    }
    
    public int[] a(final int[] array, final int[] array2, final int n, final int n2, final double n3) {
        final int[] array3 = new int[n * n2];
        for (int i = 0; i < array3.length; ++i) {
            final int n4 = array[i];
            final int n5 = n4 & 0xFF;
            final int n6 = n4 >> 8 & 0xFF;
            final int n7 = n4 >> 16 & 0xFF;
            final int n8 = n4 >> 24 & 0xFF;
            final int n9 = array2[i];
            array3[i] = ((int)(n5 * (1.0 - n3) + (n9 & 0xFF) * n3) | (int)(n6 * (1.0 - n3) + (n9 >> 8 & 0xFF) * n3) << 8 | (int)(n7 * (1.0 - n3) + (n9 >> 16 & 0xFF) * n3) << 16 | Math.max(n8, n9 >> 24 & 0xFF) << 24);
        }
        return array3;
    }
    
    public synchronized void m() {
        if (!this.f) {
            this.f = true;
            this.a = new j[this.d().length];
            this.b = new AudioClip[this.b().length];
            this.notifyAll();
        }
    }
}
