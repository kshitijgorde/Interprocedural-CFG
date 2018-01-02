// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.util;

import java.io.IOException;
import java.util.Enumeration;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.net.URLConnection;

public class b
{
    public static InputStream a(final URLConnection urlConnection, final Hashtable hashtable) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);
        if (hashtable != null) {
            final Enumeration keys = hashtable.keys();
            int n = 0;
            while (keys.hasMoreElements()) {
                final Object nextElement = keys.nextElement();
                final Object value = hashtable.get(nextElement);
                if (n > 0) {
                    printWriter.write("&");
                }
                printWriter.write(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(URLEncoder.encode(nextElement.toString())))).append("=").append(URLEncoder.encode(value.toString())))));
                ++n;
            }
        }
        printWriter.flush();
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (urlConnection instanceof HttpURLConnection) {
            ((HttpURLConnection)urlConnection).setRequestMethod("POST");
        }
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("Content-Length", Integer.toString(byteArray.length));
        final OutputStream outputStream = urlConnection.getOutputStream();
        outputStream.write(byteArray);
        outputStream.flush();
        outputStream.close();
        return urlConnection.getInputStream();
    }
    
    public static InputStream b(final URLConnection urlConnection, final Hashtable hashtable) throws IOException {
        if (urlConnection instanceof HttpURLConnection) {
            ((HttpURLConnection)urlConnection).setRequestMethod("POST");
        }
        urlConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=my_boundary_0591451s35896fn6j87fd2");
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (hashtable != null) {
            final Enumeration keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final Object nextElement = keys.nextElement();
                final a value = hashtable.get(nextElement);
                final String s = "--my_boundary_0591451s35896fn6j87fd2\r\n";
                if (value instanceof a) {
                    final byte[] a = value.a;
                    byteArrayOutputStream.write(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("Content-Disposition: form-data; name=\"").append(nextElement).append("\"; filename=\"").append(value.b).append("\"\r\n").append("Content-Type: ").append(value.c).append("\r\n\r\n"))).getBytes());
                    byteArrayOutputStream.write(a);
                    byteArrayOutputStream.write(new byte[] { 13, 10 });
                }
                else {
                    byteArrayOutputStream.write(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("Content-Disposition: form-data; name=\"").append(nextElement).append("\"\r\n\r\n").append(value).append("\r\n"))).getBytes());
                }
            }
        }
        byteArrayOutputStream.write(new String("--my_boundary_0591451s35896fn6j87fd2--\r\n").getBytes());
        byteArrayOutputStream.close();
        urlConnection.setDoOutput(true);
        final OutputStream outputStream = urlConnection.getOutputStream();
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();
        outputStream.close();
        return urlConnection.getInputStream();
    }
    
    public static byte[] a(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int read;
        while ((read = inputStream.read()) != -1) {
            byteArrayOutputStream.write(read);
        }
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    public static String a(String s) {
        final int index = s.indexOf(58);
        if (index >= 0 && index < s.length() - 4 && s.charAt(index + 1) == '/' && s.charAt(index + 2) == '/') {
            s = s.substring(index + 3);
            final int index2 = s.indexOf(64);
            if (index2 > 0) {
                s = s.substring(index2 + 1);
            }
            final int index3 = s.indexOf(47);
            if (index3 > 0) {
                s = s.substring(0, index3);
            }
            final int index4 = s.indexOf(58);
            if (index4 > 0) {
                s = s.substring(0, index4);
            }
            return s;
        }
        return "";
    }
}
