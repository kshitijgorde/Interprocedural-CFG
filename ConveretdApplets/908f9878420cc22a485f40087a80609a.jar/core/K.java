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
import Z.Z;

public final class K
{
    public final int a;
    public final int b;
    private final byte[] c;
    private final byte[] d;
    private final byte[] e;
    
    public K(final Z z) {
        this.a = z.a;
        this.b = z.b;
        final int length = z.c.length;
        this.c = new byte[length];
        this.d = new byte[length];
        this.e = new byte[length];
        for (int i = 0; i < length; ++i) {
            final H h = new H(z.c[i]);
            this.c[i] = h.a;
            this.d[i] = h.b;
            this.e[i] = h.c;
        }
    }
    
    public final void a(int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            final int n6 = 256 - n4;
            n = (n4 * n >> 8) + ((this.c[n5] & 0xFF) * n6 >> 8);
            this.c[n5] = (byte)n;
            n = (n4 * n2 >> 8) + ((this.d[n5] & 0xFF) * n6 >> 8);
            this.d[n5] = (byte)n;
            n = (n4 * n3 >> 8) + ((this.e[n5] & 0xFF) * n6 >> 8);
            this.e[n5] = (byte)n;
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
    }
    
    static final byte[] a(final K k) {
        return k.c;
    }
    
    static final byte[] b(final K k) {
        return k.d;
    }
    
    static final byte[] c(final K k) {
        return k.e;
    }
    
    public K() {
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
                        s2 = String.valueOf(s2) + I.I(652) + Integer.toHexString(char1);
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
    
    public static final String a() {
        return I.I(75) + Integer.toString((int)(1679615.0 * Math.random()), 36);
    }
    
    public static final String d(final String s) {
        if (!s.endsWith(I.I(67))) {
            return String.valueOf(s) + I.I(67);
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
                inputStreamReader = new InputStreamReader((InputStream)urlConnection, I.I(69));
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
