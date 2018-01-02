// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.util.List;
import java.net.URI;
import java.net.ProxySelector;
import java.io.Serializable;
import java.util.Iterator;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.net.Proxy;

public final class a
{
    private static boolean a;
    private static Proxy b;
    
    public static String a(final String s, final Map map, final InputStream inputStream) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(s, byteArrayOutputStream, map, inputStream);
        return new String(byteArrayOutputStream.toByteArray());
    }
    
    private static void a(final String s, final OutputStream outputStream, final Map map, final InputStream inputStream) {
        try {
            InputStream b = null;
            try {
                b = b(s, map, inputStream);
                final byte[] array = new byte[4000];
                int read;
                while ((read = b.read(array, 0, array.length)) != -1) {
                    outputStream.write(array, 0, read);
                    outputStream.flush();
                }
            }
            finally {
                if (b != null) {
                    b.close();
                }
            }
        }
        catch (IOException ex2) {
            final IOException ex = ex2;
            f.a(ex2);
            throw new RuntimeException("Failed to download: " + s, ex);
        }
    }
    
    public static InputStream a(final String s) {
        return b(s, Collections.emptyMap(), null);
    }
    
    private static InputStream b(final String s, final Map map, final InputStream inputStream) {
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection(b(s));
            for (final Map.Entry<String, V> entry : map.entrySet()) {
                httpURLConnection.addRequestProperty(entry.getKey(), (String)entry.getValue());
            }
            if (inputStream != null) {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                final OutputStream outputStream = httpURLConnection.getOutputStream();
                final byte[] array = new byte[1024];
                int read;
                while ((read = inputStream.read(array)) > 0) {
                    outputStream.write(array, 0, read);
                }
                inputStream.close();
                outputStream.close();
            }
            final int responseCode;
            if ((responseCode = httpURLConnection.getResponseCode()) != 200) {
                throw new RuntimeException("Failed to get to url: " + s + " (response: " + responseCode + ")");
            }
            return httpURLConnection.getInputStream();
        }
        catch (IOException ex2) {
            final IOException ex = ex2;
            f.a(ex2);
            throw new RuntimeException("Failed to download: " + s, ex);
        }
    }
    
    public static void a(final Proxy proxy) {
        f.b("Saving proxy state: " + proxy + " class: " + proxy.getClass());
        final j j;
        (j = new j("proxy")).a("type", proxy.type());
        j.a("address", proxy.address());
        j.a(false);
    }
    
    public static void a() {
        j.a("proxy");
        a.a.a.a.b = null;
    }
    
    public static Proxy b(final String s) {
        if (!a.a.a.a.a) {
            return Proxy.NO_PROXY;
        }
        System.setProperty("java.net.useSystemProxies", "true");
        Proxy no_PROXY = Proxy.NO_PROXY;
        try {
            final List<Proxy> select;
            if ((select = ProxySelector.getDefault().select(new URI(s))) != null && !select.isEmpty()) {
                no_PROXY = select.get(0);
                f.a((Object)("Using system proxy: " + no_PROXY));
            }
        }
        catch (Exception ex) {
            f.a(ex);
        }
        return no_PROXY;
    }
    
    static {
        a.a.a.a.a = true;
    }
}
