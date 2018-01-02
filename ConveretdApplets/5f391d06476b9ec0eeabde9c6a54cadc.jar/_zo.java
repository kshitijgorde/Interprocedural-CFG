import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.awt.Image;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class _zo
{
    protected static PixScreen p;
    private Hashtable p;
    
    public final Image p(final String s, final int n, final int n2, final int n3) {
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
        Image image;
        try {
            final int available = resourceAsStream.available();
            final byte[] array = new byte[available + 3];
            for (int i = 0; i < available; i += resourceAsStream.read(array, i, available - i)) {}
            array[available] = (byte)n;
            array[available + 1] = (byte)n2;
            array[available + 2] = (byte)n3;
            image = defaultToolkit.createImage(this.p(array, 1278932113));
        }
        catch (Exception ex) {
            return null;
        }
        _zo.p.prepareImage(image, _zo.p);
        final MediaTracker mediaTracker = new MediaTracker(_zo.p);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex2) {}
        return image;
    }
    
    public final String p(final int n) {
        return this.p.get(new Integer(n));
    }
    
    public _zo(final PixScreen p) {
        _zo.p = p;
    }
    
    public final String p(final byte[] array, final int n) {
        final int p2 = this.p(array, n);
        final StringBuffer sb = new StringBuffer(p2);
        for (int i = 0; i < p2; ++i) {
            sb.append((char)array[i + 4 + n]);
        }
        return new String(sb.toString());
    }
    
    public final void p(final String s) {
        final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
        byte[] p;
        try {
            final int available = resourceAsStream.available();
            final byte[] array = new byte[available];
            for (int i = 0; i < available; i += resourceAsStream.read(array, i, available - i)) {}
            p = this.p(array, 1357549999);
        }
        catch (Exception ex) {
            return;
        }
        this.p = new Hashtable();
        int j = 0;
        while (j < p.length) {
            final int p2 = this.p(p, j);
            j += 4;
            final int p3 = this.p(p, j);
            j += 4;
            final String s2 = new String(p, j, p3);
            j += p3;
            this.p.put(new Integer(p2), s2);
        }
    }
    
    public final int p(final byte[] array, final int n) {
        final int[] array2 = new int[4];
        int n2 = 0;
        do {
            array2[n2] = array[n + n2];
            if (array2[n2] < 0) {
                final int[] array3 = array2;
                final int n3 = n2;
                array3[n3] += 256;
            }
        } while (++n2 < 4);
        return array2[0] | array2[1] << 8 | array2[2] << 16 | array2[3] << 24;
    }
    
    public final byte[] p(final byte[] array, final int n) {
        final int n2 = array.length - 3;
        final byte[] array2 = new byte[n2];
        int n3 = ((array[n2] & 0xFF) << 8 | (array[n2 + 1] & 0xFF)) << 8 | (array[n2 + 2] & 0xFF);
        for (int i = n2 - 1; i >= 0; --i) {
            final int n4 = (n3 | array[i] << 24) * n;
            n3 = (n4 & 0xFFFFFF);
            array2[i] = (byte)(n4 >>> 24);
        }
        return array2;
    }
}
