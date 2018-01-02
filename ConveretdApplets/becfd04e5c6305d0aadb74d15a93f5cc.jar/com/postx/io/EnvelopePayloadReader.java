// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.io;

import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import com.postx.util.logging.Logger;
import java.io.Reader;

public class EnvelopePayloadReader extends Reader
{
    public static final String Ident = "$Id: EnvelopePayloadReader.java,v 1.3 2011/01/10 05:13:52 blm Exp $";
    private static final Logger log;
    private static final String LT = "<";
    private static final String GT = ">";
    private static final String DIV_TAG;
    private static final String ID_ATTR = "id";
    private static final String PAYLOAD_DIV_ID = "payloadImage";
    private static final String IMG_TAG;
    private static final String IMG_ALT = "alt";
    private static final String IMG_ALT_VALUE = "\"\"";
    private static final String IMG_SRC = "src";
    private static final String IMG_SRC_VALUE_START = "\"?p";
    private static final String IMG_AMP = "&amp;";
    private static final String IMG_D_PARAM = "d";
    private static final String GPT_BREAK = "<br>";
    private static final String EQUALS = "=";
    private ExtendedPushbackReader src;
    private int imgIndex;
    
    public void close() throws IOException {
        this.src.close();
    }
    
    public EnvelopePayloadReader(final String s, final String s2) throws IOException, MalformedURLException {
        this.imgIndex = 0;
        final URLConnection openConnection = new URL(s).openConnection();
        openConnection.connect();
        if (openConnection instanceof HttpURLConnection) {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)openConnection;
            if (httpURLConnection.getResponseCode() != 200) {
                EnvelopePayloadReader.log.info("Got an unexpected status of " + httpURLConnection.getResponseCode() + " (" + httpURLConnection.getResponseMessage() + ") opening " + s);
            }
        }
        this.src = new ExtendedPushbackReader(new BufferedReader(new InputStreamReader(openConnection.getInputStream(), s2)), 32);
        String s3 = null;
        while (this.src.skipTo(EnvelopePayloadReader.DIV_TAG)) {
            if (this.src.atWhitespace()) {
                if (!this.src.skipWhitespace()) {
                    s3 = "All whitespace after " + EnvelopePayloadReader.DIV_TAG;
                    break;
                }
                if (!this.src.at(new String[] { "id", "=", "payloadImage" }) || !this.src.atWhitespace()) {
                    continue;
                }
                if (!this.src.skipTo(">")) {
                    s3 = "No " + ">" + " after " + EnvelopePayloadReader.DIV_TAG + " " + "id" + "=" + "payloadImage";
                    break;
                }
                if (!this.src.skipTo(new String[] { EnvelopePayloadReader.IMG_TAG, null, "alt", "=", "\"\"", null, "src", "=", "\"?p", "=", String.valueOf(this.imgIndex), "&amp;", "d", "=" })) {
                    s3 = "No " + EnvelopePayloadReader.IMG_TAG + " " + "alt" + "=" + "\"\"" + " " + "src" + "=" + "\"?p" + "=" + this.imgIndex + "&amp;" + "d" + "=";
                    break;
                }
                ++this.imgIndex;
                break;
            }
        }
        final int read = this.src.read();
        if (read == -1) {
            if (s3 == null) {
                s3 = "No appropriate " + EnvelopePayloadReader.DIV_TAG;
            }
            throw new EnvelopeDamagedException(s, "Couldn't find an envelope payload (" + s3 + ")");
        }
        this.src.unread(read);
    }
    
    public int read() throws IOException {
        int raw = this.readRaw();
        Label_0127: {
            switch (raw) {
                case 37: {
                    raw = 0;
                    int n = 0;
                    int raw2;
                    while ((raw2 = this.readRaw()) != -1) {
                        final int n2 = raw << 4;
                        if (48 <= raw2 && raw2 <= 57) {
                            raw = n2 + (raw2 & 0xF);
                        }
                        else {
                            if ((65 > raw2 || raw2 > 70) && (97 > raw2 || raw2 > 102)) {
                                return -1;
                            }
                            raw = n2 + (9 + (raw2 & 0xF));
                        }
                        if (++n >= 2) {
                            break Label_0127;
                        }
                    }
                    return -1;
                }
                case 43: {
                    raw = 32;
                    break;
                }
            }
        }
        return raw;
    }
    
    public int read(final char[] array, final int n, final int n2) throws IOException {
        int n3;
        int read;
        for (n3 = 0; n3 < n2 && (read = this.read()) != -1; array[n + n3++] = (char)read) {}
        if (n3 == 0) {
            return -1;
        }
        return n3;
    }
    
    private int readRaw() throws IOException {
        int n;
        do {
            n = this.src.read();
        } while (n != -1 && Character.isWhitespace((char)n));
        if (n == 38) {
            if (!this.src.skipTo(">") || !this.src.skipWhitespace()) {
                return -1;
            }
            if (this.src.at("<br>") && !this.src.skipWhitespace()) {
                return -1;
            }
            if (!this.src.at(new String[] { EnvelopePayloadReader.IMG_TAG, null, "alt", "=", "\"\"", null, "src", "=", "\"?p", "=", String.valueOf(this.imgIndex), "&amp;", "d", "=" })) {
                return -1;
            }
            ++this.imgIndex;
            n = this.src.read();
        }
        return n;
    }
    
    static {
        log = Logger.global;
        DIV_TAG = "<" + "div";
        IMG_TAG = "<" + "img";
    }
}
