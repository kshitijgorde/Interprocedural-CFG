// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStream;

public class HttpParser
{
    public static Header[] parseHeaders(final InputStream is) throws IOException, HttpException {
        LOG.trace("enter HeaderParser.parseHeaders(HttpConnection, HeaderGroup)");
        final ArrayList headers = new ArrayList();
        String name = null;
        StringBuffer value = null;
        while (true) {
            final String line = readLine(is);
            if (line == null || line.length() < 1) {
                if (name != null) {
                    headers.add(new Header(name, value.toString()));
                }
                return headers.toArray(new Header[headers.size()]);
            }
            if (line.charAt(0) == ' ' || line.charAt(0) == '\t') {
                if (value == null) {
                    continue;
                }
                value.append(' ');
                value.append(line.trim());
            }
            else {
                if (name != null) {
                    headers.add(new Header(name, value.toString()));
                }
                final int colon = line.indexOf(":");
                if (colon < 0) {
                    throw new ProtocolException("Unable to parse header: " + line);
                }
                name = line.substring(0, colon).trim();
                value = new StringBuffer(line.substring(colon + 1).trim());
            }
        }
    }
    
    public static String readLine(final InputStream inputStream) throws IOException {
        LOG.trace("enter HttpParser.readLine()");
        final byte[] rawdata = readRawLine(inputStream);
        if (rawdata == null) {
            return null;
        }
        final int len = rawdata.length;
        int offset = 0;
        if (len > 0 && rawdata[len - 1] == 10) {
            ++offset;
            if (len > 1 && rawdata[len - 2] == 13) {
                ++offset;
            }
        }
        return HttpConstants.getString(rawdata, 0, len - offset);
    }
    
    public static byte[] readRawLine(final InputStream inputStream) throws IOException {
        LOG.trace("enter HttpParser.readRawLine()");
        final ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int ch;
        while ((ch = inputStream.read()) >= 0) {
            buf.write(ch);
            if (ch == 10) {
                break;
            }
        }
        if (buf.size() == 0) {
            return null;
        }
        return buf.toByteArray();
    }
}
