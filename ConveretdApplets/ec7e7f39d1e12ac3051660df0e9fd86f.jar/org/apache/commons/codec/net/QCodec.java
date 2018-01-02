// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.codec.net;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import java.util.BitSet;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;

public class QCodec extends RFC1522Codec implements StringEncoder, StringDecoder
{
    private String charset;
    private static final BitSet PRINTABLE_CHARS;
    private static byte BLANK;
    private static byte UNDERSCORE;
    private boolean encodeBlanks;
    
    public QCodec() {
        this.charset = "UTF-8";
        this.encodeBlanks = false;
    }
    
    public QCodec(final String charset) {
        this.charset = "UTF-8";
        this.encodeBlanks = false;
        this.charset = charset;
    }
    
    protected String getEncoding() {
        return "Q";
    }
    
    protected byte[] doEncoding(final byte[] bytes) throws EncoderException {
        if (bytes == null) {
            return null;
        }
        final byte[] data = QuotedPrintableCodec.encodeQuotedPrintable(QCodec.PRINTABLE_CHARS, bytes);
        if (this.encodeBlanks) {
            for (int i = 0; i < data.length; ++i) {
                if (data[i] == QCodec.BLANK) {
                    data[i] = QCodec.UNDERSCORE;
                }
            }
        }
        return data;
    }
    
    protected byte[] doDecoding(final byte[] bytes) throws DecoderException {
        if (bytes == null) {
            return null;
        }
        boolean hasUnderscores = false;
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] == QCodec.UNDERSCORE) {
                hasUnderscores = true;
                break;
            }
        }
        if (hasUnderscores) {
            final byte[] tmp = new byte[bytes.length];
            for (int j = 0; j < bytes.length; ++j) {
                final byte b = bytes[j];
                if (b != QCodec.UNDERSCORE) {
                    tmp[j] = b;
                }
                else {
                    tmp[j] = QCodec.BLANK;
                }
            }
            return QuotedPrintableCodec.decodeQuotedPrintable(tmp);
        }
        return QuotedPrintableCodec.decodeQuotedPrintable(bytes);
    }
    
    public String encode(final String pString, final String charset) throws EncoderException {
        if (pString == null) {
            return null;
        }
        try {
            return this.encodeText(pString, charset);
        }
        catch (UnsupportedEncodingException e) {
            throw new EncoderException(e.getMessage());
        }
    }
    
    public String encode(final String pString) throws EncoderException {
        if (pString == null) {
            return null;
        }
        return this.encode(pString, this.getDefaultCharset());
    }
    
    public String decode(final String pString) throws DecoderException {
        if (pString == null) {
            return null;
        }
        try {
            return this.decodeText(pString);
        }
        catch (UnsupportedEncodingException e) {
            throw new DecoderException(e.getMessage());
        }
    }
    
    public Object encode(final Object pObject) throws EncoderException {
        if (pObject == null) {
            return null;
        }
        if (pObject instanceof String) {
            return this.encode((String)pObject);
        }
        throw new EncoderException("Objects of type " + pObject.getClass().getName() + " cannot be encoded using Q codec");
    }
    
    public Object decode(final Object pObject) throws DecoderException {
        if (pObject == null) {
            return null;
        }
        if (pObject instanceof String) {
            return this.decode((String)pObject);
        }
        throw new DecoderException("Objects of type " + pObject.getClass().getName() + " cannot be decoded using Q codec");
    }
    
    public String getDefaultCharset() {
        return this.charset;
    }
    
    public boolean isEncodeBlanks() {
        return this.encodeBlanks;
    }
    
    public void setEncodeBlanks(final boolean b) {
        this.encodeBlanks = b;
    }
    
    static {
        (PRINTABLE_CHARS = new BitSet(256)).set(32);
        QCodec.PRINTABLE_CHARS.set(33);
        QCodec.PRINTABLE_CHARS.set(34);
        QCodec.PRINTABLE_CHARS.set(35);
        QCodec.PRINTABLE_CHARS.set(36);
        QCodec.PRINTABLE_CHARS.set(37);
        QCodec.PRINTABLE_CHARS.set(38);
        QCodec.PRINTABLE_CHARS.set(39);
        QCodec.PRINTABLE_CHARS.set(40);
        QCodec.PRINTABLE_CHARS.set(41);
        QCodec.PRINTABLE_CHARS.set(42);
        QCodec.PRINTABLE_CHARS.set(43);
        QCodec.PRINTABLE_CHARS.set(44);
        QCodec.PRINTABLE_CHARS.set(45);
        QCodec.PRINTABLE_CHARS.set(46);
        QCodec.PRINTABLE_CHARS.set(47);
        for (int i = 48; i <= 57; ++i) {
            QCodec.PRINTABLE_CHARS.set(i);
        }
        QCodec.PRINTABLE_CHARS.set(58);
        QCodec.PRINTABLE_CHARS.set(59);
        QCodec.PRINTABLE_CHARS.set(60);
        QCodec.PRINTABLE_CHARS.set(62);
        QCodec.PRINTABLE_CHARS.set(64);
        for (int i = 65; i <= 90; ++i) {
            QCodec.PRINTABLE_CHARS.set(i);
        }
        QCodec.PRINTABLE_CHARS.set(91);
        QCodec.PRINTABLE_CHARS.set(92);
        QCodec.PRINTABLE_CHARS.set(93);
        QCodec.PRINTABLE_CHARS.set(94);
        QCodec.PRINTABLE_CHARS.set(96);
        for (int i = 97; i <= 122; ++i) {
            QCodec.PRINTABLE_CHARS.set(i);
        }
        QCodec.PRINTABLE_CHARS.set(123);
        QCodec.PRINTABLE_CHARS.set(124);
        QCodec.PRINTABLE_CHARS.set(125);
        QCodec.PRINTABLE_CHARS.set(126);
        QCodec.BLANK = 32;
        QCodec.UNDERSCORE = 95;
    }
}
