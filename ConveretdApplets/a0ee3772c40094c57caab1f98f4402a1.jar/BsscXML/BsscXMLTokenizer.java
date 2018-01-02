// 
// Decompiled by Procyon v0.5.30
// 

package BsscXML;

import java.io.IOException;
import java.io.Reader;

public class BsscXMLTokenizer
{
    private static final int buffersize = 128;
    IBsscXMLParser m_out;
    
    private static final boolean isSpace(final int n) {
        return n == 32 || n == 9 || n == 10 || n == 13;
    }
    
    public int getAttribute(int n, final Reader reader) throws IOException, BsscXMLException {
        final StringBuffer sb = new StringBuffer(128);
        final StringBuffer sb2 = new StringBuffer(128);
        if (isSpace(n)) {
            n = this.skipSpace(reader);
        }
        while (n != -1 && isNameChar(n)) {
            sb.append((char)n);
            n = reader.read();
        }
        if (sb.length() != 0 && n != -1) {
            if (isSpace(n)) {
                n = this.skipSpace(reader);
            }
            if (n == 61) {
                n = this.skipSpace(reader);
                if (n == 39 || n == 34) {
                    int n2;
                    for (n2 = n, n = reader.read(); n != -1 && n != n2; n = reader.read()) {
                        sb2.append((char)n);
                    }
                    if (n == n2) {
                        this.m_out.findAttr(sb.toString(), sb2.toString());
                    }
                    n = reader.read();
                }
            }
        }
        return n;
    }
    
    BsscXMLTokenizer(final IBsscXMLParser out) {
        this.m_out = out;
    }
    
    public int getTag(int n, final Reader reader) throws BsscXMLException, IOException {
        final StringBuffer sb = new StringBuffer(128);
        if (isSpace(n)) {
            n = this.skipSpace(reader);
        }
        while (n != -1 && isNameChar(n)) {
            sb.append((char)n);
            n = reader.read();
        }
        if (n != -1) {
            this.m_out.beginElement(sb.toString());
            if (isSpace(n)) {
                n = this.skipSpace(reader);
            }
            n = this.getAttributes(n, reader);
            if (n == 47) {
                if (reader.read() == 62) {
                    this.m_out.endElement("");
                    n = reader.read();
                }
            }
            else if (n == 62) {
                return reader.read();
            }
        }
        return n;
    }
    
    public int skipHeader(final Reader reader) throws IOException {
        int i;
        for (i = reader.read(); i != -1; i = reader.read()) {
            if (i == 63 && reader.read() == 62) {
                i = reader.read();
                break;
            }
        }
        return i;
    }
    
    public int getAttributes(int n, final Reader reader) throws IOException, BsscXMLException {
        while (n != -1 && n != 47 && n != 62) {
            if (isNameChar(n)) {
                n = this.getAttribute(n, reader);
            }
            else {
                n = -1;
            }
            if (isSpace(n)) {
                n = this.skipSpace(reader);
            }
        }
        return n;
    }
    
    public int getEndTag(final Reader reader) throws IOException, BsscXMLException {
        final StringBuffer sb = new StringBuffer(128);
        int n;
        for (n = this.skipSpace(reader); n != -1 && isNameChar(n); n = reader.read()) {
            sb.append((char)n);
        }
        if (isSpace(n)) {
            n = this.skipSpace(reader);
            if (n != 62) {
                return -1;
            }
        }
        if (n != -1) {
            this.m_out.endElement(sb.toString());
            return this.skipSpace(reader);
        }
        return -1;
    }
    
    private static final boolean isNameChar(final int n) {
        return (97 <= n && n <= 122) || (65 <= n && n <= 90) || (48 <= n && n <= 57) || n == 46 || n == 45 || n == 95;
    }
    
    void tokenize(final Reader reader) {
        final StringBuffer sb = new StringBuffer(128);
        try {
            int i = this.skipHeader(reader);
            if (isSpace(i)) {
                i = this.skipSpace(reader);
            }
            this.m_out.beginDocument();
            do {
                if (i == 60) {
                    if (sb.length() != 0 && sb.length() != 0) {
                        this.m_out.beginElement("#text");
                        this.m_out.findValue(sb.toString());
                        this.m_out.endElement("");
                        sb.setLength(0);
                    }
                    final int read = reader.read();
                    if (read == 47) {
                        i = this.getEndTag(reader);
                    }
                    else {
                        i = this.getTag(read, reader);
                    }
                    if (!isSpace(i)) {
                        continue;
                    }
                    i = this.skipSpace(reader);
                }
                else {
                    sb.append((char)i);
                    i = reader.read();
                }
            } while (i != -1);
            this.m_out.endDocument();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int skipSpace(final Reader reader) throws IOException {
        int n;
        for (n = reader.read(); n != -1 && isSpace(n); n = reader.read()) {}
        return n;
    }
}
