// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.codec.net;

import org.apache.commons.codec.DecoderException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.EncoderException;

abstract class RFC1522Codec
{
    protected String encodeText(final String text, final String charset) throws EncoderException, UnsupportedEncodingException {
        if (text == null) {
            return null;
        }
        final StringBuffer buffer = new StringBuffer();
        buffer.append("=?");
        buffer.append(charset);
        buffer.append('?');
        buffer.append(this.getEncoding());
        buffer.append('?');
        final byte[] rawdata = this.doEncoding(text.getBytes(charset));
        buffer.append(new String(rawdata, "US-ASCII"));
        buffer.append("?=");
        return buffer.toString();
    }
    
    protected String decodeText(final String text) throws DecoderException, UnsupportedEncodingException {
        if (text == null) {
            return null;
        }
        if (!text.startsWith("=?") || !text.endsWith("?=")) {
            throw new DecoderException("RFC 1522 violation: malformed encoded content");
        }
        final int termnator = text.length() - 2;
        int from = 2;
        int to = text.indexOf("?", from);
        if (to == -1 || to == termnator) {
            throw new DecoderException("RFC 1522 violation: charset token not found");
        }
        final String charset = text.substring(from, to);
        if (charset.equals("")) {
            throw new DecoderException("RFC 1522 violation: charset not specified");
        }
        from = to + 1;
        to = text.indexOf("?", from);
        if (to == -1 || to == termnator) {
            throw new DecoderException("RFC 1522 violation: encoding token not found");
        }
        final String encoding = text.substring(from, to);
        if (!this.getEncoding().equalsIgnoreCase(encoding)) {
            throw new DecoderException("This codec cannot decode " + encoding + " encoded content");
        }
        from = to + 1;
        to = text.indexOf("?", from);
        byte[] data = text.substring(from, to).getBytes("US-ASCII");
        data = this.doDecoding(data);
        return new String(data, charset);
    }
    
    protected abstract String getEncoding();
    
    protected abstract byte[] doEncoding(final byte[] p0) throws EncoderException;
    
    protected abstract byte[] doDecoding(final byte[] p0) throws DecoderException;
}
