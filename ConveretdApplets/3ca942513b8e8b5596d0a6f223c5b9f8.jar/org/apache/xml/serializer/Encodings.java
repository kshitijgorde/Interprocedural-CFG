// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import java.util.Enumeration;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.xml.serializer.utils.WrappedRuntimeException;
import java.util.StringTokenizer;
import java.util.Properties;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.OutputStream;
import java.util.Hashtable;

public final class Encodings
{
    private static final String ENCODINGS_FILE = "org/apache/xml/serializer/Encodings.properties";
    private static final String ENCODINGS_PROP = "org.apache.xalan.serialize.encodings";
    static final String DEFAULT_MIME_ENCODING = "UTF-8";
    private static final Hashtable _encodingTableKeyJava;
    private static final Hashtable _encodingTableKeyMime;
    private static final EncodingInfo[] _encodings;
    
    static Writer getWriter(final OutputStream output, final String encoding) throws UnsupportedEncodingException {
        for (int i = 0; i < Encodings._encodings.length; ++i) {
            if (Encodings._encodings[i].name.equalsIgnoreCase(encoding)) {
                try {
                    return new OutputStreamWriter(output, Encodings._encodings[i].javaName);
                }
                catch (IllegalArgumentException iae) {}
                catch (UnsupportedEncodingException ex) {}
            }
        }
        try {
            return new OutputStreamWriter(output, encoding);
        }
        catch (IllegalArgumentException iae) {
            throw new UnsupportedEncodingException(encoding);
        }
    }
    
    static EncodingInfo getEncodingInfo(final String encoding) {
        final String normalizedEncoding = toUpperCaseFast(encoding);
        EncodingInfo ei = Encodings._encodingTableKeyJava.get(normalizedEncoding);
        if (ei == null) {
            ei = Encodings._encodingTableKeyMime.get(normalizedEncoding);
        }
        if (ei == null) {
            ei = new EncodingInfo(null, null);
        }
        return ei;
    }
    
    public static boolean isRecognizedEncoding(final String encoding) {
        final String normalizedEncoding = encoding.toUpperCase();
        EncodingInfo ei = Encodings._encodingTableKeyJava.get(normalizedEncoding);
        if (ei == null) {
            ei = Encodings._encodingTableKeyMime.get(normalizedEncoding);
        }
        return ei != null;
    }
    
    private static String toUpperCaseFast(final String s) {
        boolean different = false;
        final int mx = s.length();
        final char[] chars = new char[mx];
        for (int i = 0; i < mx; ++i) {
            char ch = s.charAt(i);
            if ('a' <= ch && ch <= 'z') {
                ch -= 32;
                different = true;
            }
            chars[i] = ch;
        }
        String upper;
        if (different) {
            upper = String.valueOf(chars);
        }
        else {
            upper = s;
        }
        return upper;
    }
    
    static String getMimeEncoding(String encoding) {
        if (null == encoding) {
            try {
                encoding = System.getProperty("file.encoding", "UTF8");
                if (null != encoding) {
                    final String jencoding = (encoding.equalsIgnoreCase("Cp1252") || encoding.equalsIgnoreCase("ISO8859_1") || encoding.equalsIgnoreCase("8859_1") || encoding.equalsIgnoreCase("UTF8")) ? "UTF-8" : convertJava2MimeEncoding(encoding);
                    encoding = ((null != jencoding) ? jencoding : "UTF-8");
                }
                else {
                    encoding = "UTF-8";
                }
            }
            catch (SecurityException se) {
                encoding = "UTF-8";
            }
        }
        else {
            encoding = convertJava2MimeEncoding(encoding);
        }
        return encoding;
    }
    
    private static String convertJava2MimeEncoding(final String encoding) {
        final EncodingInfo enc = Encodings._encodingTableKeyJava.get(toUpperCaseFast(encoding));
        if (null != enc) {
            return enc.name;
        }
        return encoding;
    }
    
    public static String convertMime2JavaEncoding(final String encoding) {
        for (int i = 0; i < Encodings._encodings.length; ++i) {
            if (Encodings._encodings[i].name.equalsIgnoreCase(encoding)) {
                return Encodings._encodings[i].javaName;
            }
        }
        return encoding;
    }
    
    private static EncodingInfo[] loadEncodingInfo() {
        URL url = null;
        try {
            String urlString = null;
            InputStream is = null;
            try {
                urlString = System.getProperty("org.apache.xalan.serialize.encodings", "");
            }
            catch (SecurityException ex) {}
            if (urlString != null && urlString.length() > 0) {
                url = new URL(urlString);
                is = url.openStream();
            }
            if (is == null) {
                final SecuritySupport ss = SecuritySupport.getInstance();
                is = ss.getResourceAsStream(ObjectFactory.findClassLoader(), "org/apache/xml/serializer/Encodings.properties");
            }
            final Properties props = new Properties();
            if (is != null) {
                props.load(is);
                is.close();
            }
            final int totalEntries = props.size();
            int totalMimeNames = 0;
            Enumeration keys = props.keys();
            for (int i = 0; i < totalEntries; ++i) {
                final String javaName = keys.nextElement();
                final String val = props.getProperty(javaName);
                ++totalMimeNames;
                for (int pos = val.indexOf(32), j = 0; j < pos; ++j) {
                    if (val.charAt(j) == ',') {
                        ++totalMimeNames;
                    }
                }
            }
            final EncodingInfo[] ret = new EncodingInfo[totalMimeNames];
            int k = 0;
            keys = props.keys();
            for (int l = 0; l < totalEntries; ++l) {
                final String javaName2 = keys.nextElement();
                final String val2 = props.getProperty(javaName2);
                final int pos2 = val2.indexOf(32);
                if (pos2 < 0) {
                    final String mimeName = val2;
                }
                else {
                    final StringTokenizer st = new StringTokenizer(val2.substring(0, pos2), ",");
                    boolean first = true;
                    while (st.hasMoreTokens()) {
                        final String mimeName = st.nextToken();
                        ret[k] = new EncodingInfo(mimeName, javaName2);
                        Encodings._encodingTableKeyMime.put(toUpperCaseFast(mimeName), ret[k]);
                        if (first) {
                            Encodings._encodingTableKeyJava.put(javaName2.toUpperCase(), ret[k]);
                        }
                        ++k;
                        first = false;
                    }
                }
            }
            return ret;
        }
        catch (MalformedURLException mue) {
            throw new WrappedRuntimeException(mue);
        }
        catch (IOException ioe) {
            throw new WrappedRuntimeException(ioe);
        }
    }
    
    static boolean isHighUTF16Surrogate(final char ch) {
        return '\ud800' <= ch && ch <= '\udbff';
    }
    
    static boolean isLowUTF16Surrogate(final char ch) {
        return '\udc00' <= ch && ch <= '\udfff';
    }
    
    static int toCodePoint(final char highSurrogate, final char lowSurrogate) {
        final int codePoint = (highSurrogate - '\ud800' << 10) + (lowSurrogate - '\udc00') + 65536;
        return codePoint;
    }
    
    static int toCodePoint(final char ch) {
        return ch;
    }
    
    static {
        _encodingTableKeyJava = new Hashtable();
        _encodingTableKeyMime = new Hashtable();
        _encodings = loadEncodingInfo();
    }
}
