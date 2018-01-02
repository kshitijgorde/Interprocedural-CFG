// 
// Decompiled by Procyon v0.5.30
// 

package org.litecoinpool.miner;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.regex.Matcher;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

public final class d
{
    private static final Pattern a;
    private static final Pattern b;
    private static final Pattern c;
    private URL d;
    private String e;
    private long f;
    private String g;
    private byte[] h;
    private byte[] i;
    private byte[] j;
    private static final char[] k;
    private static int[] l;
    
    static {
        a = Pattern.compile("\"data\"\\s*:\\s*\"([0-9a-f]+)\"");
        b = Pattern.compile("\"target\"\\s*:\\s*\"([0-9a-f]+)\"");
        c = Pattern.compile("\"result\"\\s*:\\s*([0-9A-Za-z]+)");
        k = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        d.l = new int[128];
        for (int i = 0; i < d.k.length; ++i) {
            d.l[d.k[i]] = i;
        }
    }
    
    public d(final URL url, final String s, final byte b) {
        this(url, s);
    }
    
    private d(final URL url, final String s) {
        this((HttpURLConnection)url.openConnection(), url, s);
    }
    
    public d(HttpURLConnection a, final URL d, final String e) {
        this.g = null;
        final int responseCode;
        if ((responseCode = (a = a(a, "{\"method\": \"getwork\", \"params\": [], \"id\":0}", e)).getResponseCode()) == 401 || responseCode == 403) {
            throw new IllegalArgumentException("Access denied");
        }
        final String a2 = a(a);
        this.f = System.currentTimeMillis();
        final Matcher matcher;
        if (!(matcher = org.litecoinpool.miner.d.a.matcher(a2)).find()) {
            throw new RuntimeException(a2);
        }
        this.h = a(matcher.group(1));
        final Matcher matcher2;
        if (!(matcher2 = org.litecoinpool.miner.d.b.matcher(a2)).find()) {
            throw new RuntimeException(a2);
        }
        this.i = a(matcher2.group(1));
        this.j = a(this.h);
        this.g = a.getHeaderField("X-Long-Polling");
        this.d = d;
        this.e = e;
    }
    
    public final boolean a(final int n) {
        final byte[] array;
        (array = this.h.clone())[79] = (byte)n;
        array[78] = (byte)(n >> 8);
        array[77] = (byte)(n >> 16);
        array[76] = (byte)(n >> 24);
        final byte[] array2 = array;
        final StringBuilder sb = new StringBuilder(80);
        for (int i = 0; i < array2.length; ++i) {
            sb.append(Integer.toString((array2[i] & 0xFF) + 256, 16).substring(1));
        }
        final Matcher matcher;
        return (matcher = org.litecoinpool.miner.d.c.matcher(a(a((HttpURLConnection)this.d.openConnection(), "{\"method\": \"getwork\", \"params\": [ \"" + sb.toString() + "\" ], \"id\":1}", this.e)))).find() && matcher.group(1).equals("true");
    }
    
    public final boolean a(final int n, final c c) {
        byte[] a;
        for (int i = (a = c.a(this.j, n)).length - 1; i >= 0; --i) {
            if ((a[i] & 0xFF) > (this.i[i] & 0xFF)) {
                return false;
            }
            if ((a[i] & 0xFF) < (this.i[i] & 0xFF)) {
                return true;
            }
        }
        return true;
    }
    
    public final long a() {
        return System.currentTimeMillis() - this.f;
    }
    
    public final URL b() {
        if (this.g == null) {
            return null;
        }
        return this.d.toURI().resolve(this.g).toURL();
    }
    
    private static byte[] a(final byte[] array) {
        final byte[] array2 = new byte[80];
        for (int i = 0; i < 80; i += 4) {
            array2[i] = array[i + 3];
            array2[i + 1] = array[i + 2];
            array2[i + 2] = array[i + 1];
            array2[i + 3] = array[i];
        }
        return array2;
    }
    
    private static byte[] a(final String s) {
        final int length;
        final byte[] array = new byte[(length = s.length()) / 2];
        for (int i = 0; i < length; i += 2) {
            array[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return array;
    }
    
    private static String b(final String s) {
        final byte[] bytes;
        final int length;
        final char[] array = new char[((length = (bytes = s.getBytes()).length) + 2) / 3 << 2];
        int n = 0;
        byte b;
        byte b2;
        byte b3;
        for (int i = 0; i < length; b = bytes[i++], b2 = (byte)((i < length) ? bytes[i++] : 0), b3 = (byte)((i < length) ? bytes[i++] : 0), array[n++] = d.k[b >> 2 & 0x3F], array[n++] = d.k[(b << 4 | (b2 & 0xFF) >> 4) & 0x3F], array[n++] = d.k[(b2 << 2 | (b3 & 0xFF) >> 6) & 0x3F], array[n++] = d.k[b3 & 0x3F]) {}
        switch (length % 3) {
            case 1: {
                array[--n] = '=';
            }
            case 2: {
                array[--n] = '=';
                break;
            }
        }
        return new String(array);
    }
    
    private static HttpURLConnection a(final HttpURLConnection httpURLConnection, final String s, final String s2) {
        if (httpURLConnection.getConnectTimeout() == 0) {
            httpURLConnection.setConnectTimeout(10000);
        }
        if (httpURLConnection.getReadTimeout() == 0) {
            httpURLConnection.setReadTimeout(10000);
        }
        httpURLConnection.setRequestMethod("POST");
        if (s2 != null) {
            httpURLConnection.setRequestProperty("Authorization", "Basic " + b(s2));
        }
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Content-Length", Integer.toString(s.getBytes().length));
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoOutput(true);
        final DataOutputStream dataOutputStream;
        (dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream())).writeBytes(s);
        dataOutputStream.close();
        return httpURLConnection;
    }
    
    private static String a(final HttpURLConnection httpURLConnection) {
        final InputStream inputStream = httpURLConnection.getInputStream();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[4096];
        int read;
        while ((read = inputStream.read(array)) != -1) {
            byteArrayOutputStream.write(array, 0, read);
        }
        final String string = byteArrayOutputStream.toString();
        inputStream.close();
        return string;
    }
}
