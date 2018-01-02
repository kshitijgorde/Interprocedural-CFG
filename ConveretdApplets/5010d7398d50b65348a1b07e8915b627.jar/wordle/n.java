// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import wordle.a.b;
import java.net.HttpURLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import wordle.core.r;
import java.awt.Color;
import wordle.core.K;
import java.awt.Font;

public class n
{
    private Font a;
    private K b;
    private String c;
    private String d;
    private byte[] e;
    private z f;
    private String g;
    private Color h;
    
    public n(final r r, final Font a, final K b, final String c, final String d, final z f, final byte[] e, final String g, final Color h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    static K a(final WordleApplet wordleApplet) {
        final Font a = wordleApplet.a(wordleApplet.getParameter("font"));
        try {
            wordleApplet.a().setBackground(Color.decode(wordleApplet.getParameter("background")));
        }
        catch (Exception ex) {
            System.err.println(ex);
            wordleApplet.a().setBackground(Color.WHITE);
        }
        return K.a(a(wordleApplet.b("/drawables/" + wordleApplet.getParameter("saved"))), a);
    }
    
    private static String a(URL url) {
        final StringBuilder sb = new StringBuilder();
        url = (URL)new BufferedReader(new InputStreamReader(new GZIPInputStream(url.openStream()), "UTF-8"));
        try {
            String line;
            while ((line = ((BufferedReader)url).readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
        finally {
            ((BufferedReader)url).close();
        }
    }
    
    final void b(final WordleApplet wordleApplet) {
        final HttpURLConnection httpURLConnection;
        (httpURLConnection = (HttpURLConnection)wordleApplet.b("/save").openConnection()).setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
        final HttpURLConnection httpURLConnection2 = httpURLConnection;
        try {
            final b b = new b(httpURLConnection2);
            try {
                b.a("font", this.a.getName());
                b.a("title", (this.c == null) ? "Untitled" : this.c);
                b.a("thumbtype", this.f.toString().toLowerCase().substring(0, 1));
                b.a("username", (this.d == null) ? "Anonymous" : this.d);
                b.a("comment", (this.g == null) ? "" : this.g);
                b.a("background", Integer.toHexString(this.h.getRGB()).substring(2));
                final byte[] a = a(this.b.a());
                b.a("drawables", "application/octet-stream", a);
                b.a("thumb", "application/octet-stream", this.e);
                System.err.println(String.format("Image %d bytes; drawables %d bytes", this.e.length, a.length));
            }
            finally {
                b.a();
            }
            b.a();
            wordleApplet.getAppletContext().showDocument(wordleApplet.b("/show/" + a(httpURLConnection2)));
        }
        catch (IOException ex) {
            b(httpURLConnection2);
        }
    }
    
    private static byte[] a(final String s) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(s.length() / 2);
        final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        try {
            gzipOutputStream.write(s.getBytes("UTF-8"));
        }
        finally {
            gzipOutputStream.close();
        }
        gzipOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    public n() {
    }
    
    private static String a(HttpURLConnection httpURLConnection) {
        httpURLConnection = (HttpURLConnection)new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
        try {
            String line2;
            for (String line = line2 = ((BufferedReader)httpURLConnection).readLine(); line != null; line = ((BufferedReader)httpURLConnection).readLine()) {
                System.err.println(line);
                line2 = line;
            }
            return line2;
        }
        finally {
            ((BufferedReader)httpURLConnection).close();
        }
    }
    
    private static void b(HttpURLConnection httpURLConnection) {
        final InputStream errorStream;
        if ((errorStream = httpURLConnection.getErrorStream()) != null) {
            httpURLConnection = (HttpURLConnection)new BufferedReader(new InputStreamReader(errorStream));
            try {
                String line;
                while ((line = ((BufferedReader)httpURLConnection).readLine()) != null) {
                    System.err.println(line);
                }
            }
            finally {
                ((BufferedReader)httpURLConnection).close();
            }
            ((BufferedReader)httpURLConnection).close();
        }
    }
}
