import java.io.InputStream;
import java.net.URLConnection;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.ByteArrayInputStream;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class v
{
    private int[] p;
    public String p;
    public Image p;
    public int[] d;
    public Image d;
    protected static PixScreen p;
    public _zo p;
    
    private final int p(final byte[] array, final int[] array2) {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        final char c = (char)byteArrayInputStream.read();
        final char c2 = (char)byteArrayInputStream.read();
        final int p2 = p(byteArrayInputStream);
        final int p3 = p(byteArrayInputStream);
        p(byteArrayInputStream);
        for (int i = 0; i < p2 * p3; ++i) {
            array2[i] = (byteArrayInputStream.read() << 16 | byteArrayInputStream.read() << 8 | byteArrayInputStream.read() | 0xFF000000);
        }
        return 1;
    }
    
    public v(final _zo p) {
        this.p = p;
    }
    
    private static final char d(final ByteArrayInputStream byteArrayInputStream) {
        char p;
        do {
            p = p(byteArrayInputStream);
        } while (p == ' ' || p == '\t' || p == '\n' || p == '\r');
        return p;
    }
    
    private static final char p(final ByteArrayInputStream byteArrayInputStream) {
        char c = (char)byteArrayInputStream.read();
        if (c == '#') {
            do {
                c = (char)byteArrayInputStream.read();
            } while (c != '\n' && c != '\r');
        }
        return c;
    }
    
    private static final int p(final ByteArrayInputStream byteArrayInputStream) {
        char c = d(byteArrayInputStream);
        if (c < '0' || c > '9') {
            return 0;
        }
        int n = 0;
        do {
            n = n * 10 + c - 48;
            c = p(byteArrayInputStream);
        } while (c >= '0' && c <= '9');
        return n;
    }
    
    public final synchronized int p(final String s, final PixScreen p2) {
        v.p = p2;
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            return 0;
        }
        byte[] array;
        try {
            final URLConnection openConnection = url.openConnection();
            final InputStream inputStream = openConnection.getInputStream();
            int contentLength = openConnection.getContentLength();
            if (contentLength == -1) {
                contentLength = 1027;
            }
            array = new byte[contentLength];
            for (int i = 0; i < contentLength; i += inputStream.read(array, i, contentLength - i)) {}
            inputStream.close();
        }
        catch (Exception ex2) {
            return 0;
        }
        int n = 0;
        final int p3 = this.p.p(array, n);
        n += 4;
        if (p3 != 1953723747) {
            return 0;
        }
        final int p4 = this.p.p(array, n);
        n += 4;
        if (p4 != 101) {
            return 0;
        }
        final int p5 = this.p.p(array, n);
        n += 4;
        final int p6 = this.p.p(array, n);
        n += 4;
        final byte[] array2 = new byte[p6];
        for (int j = 0; j < p6; ++j) {
            array2[j] = array[n];
            ++n;
        }
        final byte[] p7 = this.p.p(array2, 1351088001);
        int n2 = 0;
        for (int k = 0; k < p5; ++k) {
            final int p8 = this.p.p(p7, n2);
            n2 += 4;
            switch (p8) {
                case 1000: {
                    final int p9 = this.p.p(p7, n2);
                    n2 += 4;
                    if (p9 != 3) {
                        return 0;
                    }
                    final String p10 = this.p.p(p7, n2);
                    n2 += 4 + p10.length();
                    this.p = p10.trim();
                    break;
                }
                case 1001: {
                    final int p11 = this.p.p(p7, n2);
                    n2 += 4;
                    if (p11 != 6) {
                        return 0;
                    }
                    n2 += 4 + this.p.p(p7, n2).length();
                    final int p12 = this.p.p(p7, n2);
                    n2 += 4;
                    final byte[] array3 = new byte[p12];
                    for (int l = 0; l < p12; ++l) {
                        array3[l] = p7[n2];
                        ++n2;
                    }
                    final int n3 = 16;
                    final int n4 = 16;
                    this.p(array3, this.p = new int[n3 * n4]);
                    v.p.ny = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(n3, n4, this.p, 0, n3));
                    v.p.prepareImage(v.p.ny, v.p);
                    this.p = null;
                    break;
                }
                case 1002: {
                    final int p13 = this.p.p(p7, n2);
                    n2 += 4;
                    if (p13 != 6) {
                        return 0;
                    }
                    n2 += 4 + this.p.p(p7, n2).length();
                    final int p14 = this.p.p(p7, n2);
                    n2 += 4;
                    final byte[] array4 = new byte[p14];
                    for (int n5 = 0; n5 < p14; ++n5) {
                        array4[n5] = p7[n2];
                        ++n2;
                    }
                    this.p(array4, this.d = new int[2304]);
                    int n6 = 0;
                    while (n6 < (n6 = 1152) + 1152) {
                        this.d[n6] = (this.d[n6] & 0xFF00) >> 8;
                        ++n6;
                    }
                    break;
                }
                case 1003: {
                    final int p15 = this.p.p(p7, n2);
                    n2 += 4;
                    if (p15 != 6) {
                        return 0;
                    }
                    n2 += 4 + this.p.p(p7, n2).length();
                    final int p16 = this.p.p(p7, n2);
                    n2 += 4;
                    final byte[] array5 = new byte[p16];
                    for (int n7 = 0; n7 < p16; ++n7) {
                        array5[n7] = p7[n2];
                        ++n2;
                    }
                    this.d = Toolkit.getDefaultToolkit().createImage(array5);
                    v.p.prepareImage(this.d, v.p);
                    break;
                }
            }
        }
        return 1;
    }
}
