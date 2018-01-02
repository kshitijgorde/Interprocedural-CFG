// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.util.zip.InflaterInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import I.I;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.MemoryImageSource;

public final class E
{
    private final int[] a;
    private final MemoryImageSource b;
    private final int c;
    private final int d;
    private int e;
    private int f;
    
    private E(final RevolverEngine revolverEngine, final int c, final int d, final byte b) {
        this.c = c;
        this.d = d;
        this.e = this.c;
        this.f = this.d;
        final DirectColorModel directColorModel = new DirectColorModel(24, 16711680, 65280, 255);
        this.a = new int[c * d];
        (this.b = new MemoryImageSource(c, d, directColorModel, this.a, 0, c)).setAnimated(true);
        this.b.setFullBufferUpdates(true);
        RevolverEngine.a(revolverEngine, revolverEngine.createImage(this.b));
    }
    
    public final void a(int n, int i, final D d) {
        final int n3;
        final int n2 = ((n3 = this.e - n) < d.a) ? n3 : d.a;
        final int n5;
        final int n4 = ((n5 = this.f - i) < d.b) ? n5 : d.b;
        final int n6 = (n < 0) ? (0 - n) : 0;
        final int n7 = (i < 0) ? (0 - i) : 0;
        n += this.c * i;
        int n8;
        int n9;
        int j;
        for (i = n7; i < n4; ++i) {
            n8 = d.a * i;
            n9 = n + this.c * i;
            for (j = n6; j < n2; ++j) {
                this.c(j + n9, 255, D.a(d)[j + n8] & 0xFF);
            }
        }
    }
    
    public final void a(int n, int i, final FI fi) {
        final int n3;
        final int n2 = ((n3 = this.e - n) < fi.a) ? n3 : fi.a;
        final int n5;
        final int n4 = ((n5 = this.f - i) < fi.b) ? n5 : fi.b;
        final int n6 = (n < 0) ? (0 - n) : 0;
        final int n7 = (i < 0) ? (0 - i) : 0;
        n += this.c * i;
        int n8;
        int n9;
        int j;
        for (i = n7; i < n4; ++i) {
            n8 = fi.a * i;
            n9 = n + this.c * i;
            for (j = n6; j < n2; ++j) {
                this.a[j + n9] = FI.a(fi)[j + n8];
            }
        }
    }
    
    private void b(int n, int i, int n2) {
        if (i < this.f && i >= 0) {
            final int n3;
            n2 = (((n3 = this.e - n) < n2) ? n3 : n2);
            final int n4 = (n < 0) ? (0 - n) : 0;
            n += this.c * i;
            for (i = n4; i < n2; ++i) {
                this.a[i + n] = 4473924;
            }
        }
    }
    
    public final void a(int n, int i) {
        if (n >= 0 && n < this.e) {
            final int n3;
            final int n2 = ((n3 = this.f - i) < 11) ? n3 : 11;
            final int n4 = (i < 0) ? (0 - i) : 0;
            n += this.c * i;
            for (i = n4; i < n2; ++i) {
                this.a[n + this.c * i] = 4473924;
            }
        }
    }
    
    public final void a(int n, int n2, int n3) {
        n3 -= 2;
        ++n;
        this.b(n, n2, n3);
        this.b(n, n2 + 13 - 1, n3);
        ++n2;
        this.a(n - 1, n2);
        this.a(n + n3, n2);
    }
    
    public final void a(int n, int i, int n2, int n3, final int n4) {
        final int n5;
        n2 = (((n5 = this.e - n) < n2) ? n5 : n2);
        final int n6;
        n3 = (((n6 = this.f - i) < n3) ? n6 : n3);
        final int n7 = (n < 0) ? (0 - n) : 0;
        final int n8 = (i < 0) ? (0 - i) : 0;
        n += this.c * i;
        int n9;
        int j;
        for (i = n8; i < n3; ++i) {
            n9 = n + this.c * i;
            for (j = n7; j < n2; ++j) {
                this.c(j + n9, 0, n4);
            }
        }
    }
    
    private void c(final int n, int n2, int n3) {
        if (n3 != 0) {
            final int n4 = this.a[n];
            n2 = n3 * n2 >> 8;
            n3 = 256 - n3;
            final int n5 = n2 + ((n4 >> 16) * n3 >> 8);
            final int n6 = n2 + ((n4 >> 8 & 0xFF) * n3 >> 8);
            n2 += (n4 & 0xFF) * n3 >> 8;
            n3 = (n5 << 16 | n6 << 8 | n2);
            this.a[n] = n3;
        }
    }
    
    public final void b(final int n, final int n2, int i, final int n3, final int n4) {
        final int n5 = i * i;
        int j = 0;
        while (i > 0) {
            final int n6;
            final boolean b = (n6 = n + i) >= 0 && n6 < this.e;
            final int n7;
            final boolean b2 = (n7 = n - i) >= 0 && n7 < this.e;
            int n8;
            for (n8 = (int)Math.sqrt(n5 - i * i); j < n8; ++j) {
                final int n9;
                if ((n9 = n2 + j) >= 0) {
                    if (n9 < this.f) {
                        final int n10 = this.c * n9;
                        if (b) {
                            this.c(n6 + n10, n3, n4);
                        }
                        if (b2) {
                            this.c(n7 + n10, n3, n4);
                        }
                    }
                    final int n11;
                    if ((n11 = n2 - j) >= 0 && n11 < this.f) {
                        final int n12 = this.c * n11;
                        if (b2) {
                            this.c(n7 + n12, n3, n4);
                        }
                        if (b) {
                            this.c(n6 + n12, n3, n4);
                        }
                    }
                }
            }
            j = n8;
            --i;
        }
    }
    
    E(final RevolverEngine revolverEngine, final int n, final int n2) {
        this(revolverEngine, n, n2, (byte)0);
    }
    
    static final int[] a(final E e) {
        return e.a;
    }
    
    static final MemoryImageSource b(final E e) {
        return e.b;
    }
    
    public E() {
    }
    
    public static final String a(final String s) {
        final char[] array = { '!', '*', '\'', '(', ')', ';', ':', '@', '&', '=', '+', '$', ',', '/', '?', '%', '#', '[', ']' };
        String s2 = "";
        int i = 0;
    Label_0211:
        while (i < s.length()) {
            final char char1 = s.charAt(i);
            while (true) {
                for (int j = 0; j < array.length; ++j) {
                    if (char1 == array[j]) {
                        s2 = String.valueOf(s2) + I.I(671) + Integer.toHexString(char1);
                        ++i;
                        continue Label_0211;
                    }
                }
                s2 = String.valueOf(s2) + char1;
                continue;
            }
        }
        return s2;
    }
    
    public static final URL b(final String s) {
        try {
            return new URL(s);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public static final URL a(final URL url, final String s) {
        try {
            return new URL(String.valueOf(d(url.toString())) + s);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public static final URLConnection c(final String s) {
        try {
            return new URL(s).openConnection();
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public static final String d(final String s) {
        if (!s.endsWith(I.I(70))) {
            return String.valueOf(s) + I.I(70);
        }
        return s;
    }
    
    public static final byte[] a(final InputStream inputStream) {
        try {
            final byte[] array = new byte[512];
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while ((read = inputStream.read(array, 0, 512)) != -1) {
                byteArrayOutputStream.write(array, 0, read);
            }
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static final BufferedReader a(URLConnection urlConnection, final boolean b) {
        try {
            if (!urlConnection.getDoInput()) {
                urlConnection.setDoInput(true);
            }
            InputStream inputStream = urlConnection.getInputStream();
            if (b) {
                inputStream = new InflaterInputStream(inputStream);
            }
            urlConnection = (URLConnection)new BufferedInputStream(inputStream);
            InputStreamReader inputStreamReader;
            try {
                inputStreamReader = new InputStreamReader((InputStream)urlConnection, I.I(72));
            }
            catch (UnsupportedEncodingException ex) {
                inputStreamReader = new InputStreamReader((InputStream)urlConnection);
            }
            return new BufferedReader(inputStreamReader);
        }
        catch (IOException ex2) {
            return null;
        }
    }
    
    public static final int a(final byte b, final byte b2) {
        return (b & 0xFF) << 8 | (b2 & 0xFF);
    }
}
