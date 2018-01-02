// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.serialize;

import java.io.UnsupportedEncodingException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.OutputStream;

public class Encodings
{
    static final int m_defaultLastPrintable = 127;
    public static final String DEFAULT_MIME_ENCODING = "UTF-8";
    private static final EncodingInfo[] _encodings;
    
    static {
        _encodings = new EncodingInfo[] { new EncodingInfo("WINDOWS-1250", "Cp1250", 255), new EncodingInfo("UTF-8", "UTF8", 65535), new EncodingInfo("US-ASCII", "ISO8859_1", 127), new EncodingInfo("ISO-8859-1", "ISO8859_1", 255), new EncodingInfo("ISO-8859-2", "ISO8859_2", 255), new EncodingInfo("ISO-8859-3", "ISO8859_3", 255), new EncodingInfo("ISO-8859-4", "ISO8859_4", 255), new EncodingInfo("ISO-8859-5", "ISO8859_5", 255), new EncodingInfo("ISO-8859-6", "ISO8859_6", 255), new EncodingInfo("ISO-8859-7", "ISO8859_7", 255), new EncodingInfo("ISO-8859-8", "ISO8859_8", 255), new EncodingInfo("ISO-8859-9", "ISO8859_9", 255), new EncodingInfo("US-ASCII", "8859_1", 255), new EncodingInfo("ISO-8859-1", "8859_1", 255), new EncodingInfo("ISO-8859-2", "8859_2", 255), new EncodingInfo("ISO-8859-3", "8859_3", 255), new EncodingInfo("ISO-8859-4", "8859_4", 255), new EncodingInfo("ISO-8859-5", "8859_5", 255), new EncodingInfo("ISO-8859-6", "8859_6", 255), new EncodingInfo("ISO-8859-7", "8859_7", 255), new EncodingInfo("ISO-8859-8", "8859_8", 255), new EncodingInfo("ISO-8859-9", "8859_9", 255), new EncodingInfo("ISO-2022-JP", "JIS", 65535), new EncodingInfo("SHIFT_JIS", "SJIS", 65535), new EncodingInfo("EUC-JP", "EUC_JP", 65535), new EncodingInfo("EUC-KR", "EUC_KR", 65535), new EncodingInfo("EUC-CN", "EUC_CN", 65535), new EncodingInfo("EUC-TW", "EUC_TW", 65535), new EncodingInfo("GB2312", "EUC_CN", 65535), new EncodingInfo("GB2312", "GB2312", 65535), new EncodingInfo("BIG5", "Big5", 65535), new EncodingInfo("EUC-JP", "EUCJIS", 65535), new EncodingInfo("EUC-KR", "KSC5601", 65535), new EncodingInfo("ISO-2022-KR", "ISO2022KR", 65535), new EncodingInfo("KOI8-R", "KOI8_R", 65535), new EncodingInfo("EBCDIC-CP-US", "Cp037", 255), new EncodingInfo("EBCDIC-CP-CA", "Cp037", 255), new EncodingInfo("EBCDIC-CP-NL", "Cp037", 255), new EncodingInfo("EBCDIC-CP-DK", "Cp277", 255), new EncodingInfo("EBCDIC-CP-NO", "Cp277", 255), new EncodingInfo("EBCDIC-CP-FI", "Cp278", 255), new EncodingInfo("EBCDIC-CP-SE", "Cp278", 255), new EncodingInfo("EBCDIC-CP-IT", "Cp280", 255), new EncodingInfo("EBCDIC-CP-ES", "Cp284", 255), new EncodingInfo("EBCDIC-CP-GB", "Cp285", 255), new EncodingInfo("EBCDIC-CP-FR", "Cp297", 255), new EncodingInfo("EBCDIC-CP-AR1", "Cp420", 255), new EncodingInfo("EBCDIC-CP-HE", "Cp424", 255), new EncodingInfo("EBCDIC-CP-CH", "Cp500", 255), new EncodingInfo("EBCDIC-CP-ROECE", "Cp870", 255), new EncodingInfo("EBCDIC-CP-YU", "Cp870", 255), new EncodingInfo("EBCDIC-CP-IS", "Cp871", 255), new EncodingInfo("EBCDIC-CP-AR2", "Cp918", 255), new EncodingInfo("ASCII", "ASCII", 127), new EncodingInfo("ISO-Latin-1", "ASCII", 255), new EncodingInfo("UTF-8", "UTF8", 65535), new EncodingInfo("UNICODE", "Unicode", 65535), new EncodingInfo("UTF-16", "Unicode", 65535) };
    }
    
    public static String convertJava2MimeEncoding(final String encoding) {
        for (int i = 0; i < Encodings._encodings.length; ++i) {
            if (Encodings._encodings[i].javaName.equalsIgnoreCase(encoding)) {
                return Encodings._encodings[i].name;
            }
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
    
    public static int getLastPrintable() {
        return 127;
    }
    
    public static int getLastPrintable(final String encoding) {
        for (int i = 0; i < Encodings._encodings.length; ++i) {
            if (Encodings._encodings[i].name.equalsIgnoreCase(encoding) || Encodings._encodings[i].javaName.equalsIgnoreCase(encoding)) {
                return Encodings._encodings[i].lastPrintable;
            }
        }
        return 127;
    }
    
    public static String getMimeEncoding(String encoding) {
        if (encoding == null) {
            try {
                encoding = System.getProperty("file.encoding", "UTF8");
                if (encoding != null) {
                    final String jencoding = (encoding.equalsIgnoreCase("Cp1252") || encoding.equalsIgnoreCase("ISO8859_1") || encoding.equalsIgnoreCase("8859_1") || encoding.equalsIgnoreCase("UTF8")) ? "UTF-8" : convertJava2MimeEncoding(encoding);
                    encoding = ((jencoding != null) ? jencoding : "UTF-8");
                }
                else {
                    encoding = "UTF-8";
                }
            }
            catch (SecurityException ex) {
                encoding = "UTF-8";
            }
        }
        else {
            encoding = convertJava2MimeEncoding(encoding);
        }
        return encoding;
    }
    
    public static Writer getWriter(final OutputStream output, final String encoding) throws UnsupportedEncodingException {
        for (int i = 0; i < Encodings._encodings.length; ++i) {
            if (Encodings._encodings[i].name.equalsIgnoreCase(encoding)) {
                try {
                    return new OutputStreamWriter(output, Encodings._encodings[i].javaName);
                }
                catch (IllegalArgumentException ex) {}
                catch (UnsupportedEncodingException ex2) {}
            }
        }
        try {
            return new OutputStreamWriter(output, encoding);
        }
        catch (IllegalArgumentException ex3) {
            throw new UnsupportedEncodingException(encoding);
        }
    }
}
