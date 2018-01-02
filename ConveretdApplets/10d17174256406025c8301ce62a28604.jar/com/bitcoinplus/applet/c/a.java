// 
// Decompiled by Procyon v0.5.30
// 

package com.bitcoinplus.applet.c;

import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.net.URL;

public final class a extends b
{
    public final String a(String headerField, final String s) {
        try {
            final String s2 = headerField;
            final String s3 = null;
            final URLConnection openConnection;
            (openConnection = new URL(s2).openConnection()).addRequestProperty("Accept-Encoding", "gzip,deflate");
            if (s3 != null) {
                openConnection.setRequestProperty("Cookie", s3);
            }
            headerField = openConnection.getHeaderField("Content-Encoding");
            InputStream inputStream;
            if ("gzip".equalsIgnoreCase(headerField)) {
                inputStream = new GZIPInputStream(openConnection.getInputStream());
            }
            else {
                inputStream = openConnection.getInputStream();
            }
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            a.a.a.b.a.a(inputStream, byteArrayOutputStream);
            return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
