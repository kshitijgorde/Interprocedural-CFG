// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import org.apache.xml.utils.XMLStringFactory;
import java.util.Locale;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.WrappedRuntimeException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.XMLCharacterRecognizer;
import org.apache.xml.utils.XMLString;

public class XString extends XObject implements XMLString
{
    public static XString EMPTYSTRING;
    
    protected XString(final Object val) {
        super(val);
    }
    
    public XString(final String val) {
        super(val);
    }
    
    public int getType() {
        return 3;
    }
    
    public String getTypeString() {
        return "#STRING";
    }
    
    public boolean hasString() {
        return true;
    }
    
    public double num() {
        return this.toDouble();
    }
    
    public double toDouble() {
        final int end = this.length();
        if (0 == end) {
            return Double.NaN;
        }
        double result = 0.0;
        int start = 0;
        int punctPos = end - 1;
        for (int i = start; i < end; ++i) {
            final char c = this.charAt(i);
            if (!XMLCharacterRecognizer.isWhiteSpace(c)) {
                break;
            }
            ++start;
        }
        double sign = 1.0;
        if (start < end && this.charAt(start) == '-') {
            sign = -1.0;
            ++start;
        }
        int digitsFound = 0;
        for (int j = start; j < end; ++j) {
            final char c2 = this.charAt(j);
            if (c2 == '.') {
                punctPos = j;
                break;
            }
            if (XMLCharacterRecognizer.isWhiteSpace(c2)) {
                break;
            }
            if (!Character.isDigit(c2)) {
                return Double.NaN;
            }
            result = result * 10.0 + (c2 - '0');
            ++digitsFound;
        }
        if (this.charAt(punctPos) == '.') {
            double fractPart = 0.0;
            for (int k = end - 1; k > punctPos; --k) {
                final char c3 = this.charAt(k);
                if (XMLCharacterRecognizer.isWhiteSpace(c3)) {
                    break;
                }
                if (!Character.isDigit(c3)) {
                    return Double.NaN;
                }
                fractPart = fractPart / 10.0 + (c3 - '0');
                ++digitsFound;
            }
            result += fractPart / 10.0;
        }
        if (0 == digitsFound) {
            return Double.NaN;
        }
        return result * sign;
    }
    
    public boolean bool() {
        return this.str().length() > 0;
    }
    
    public XMLString xstr() {
        return this;
    }
    
    public String str() {
        return (String)((null != super.m_obj) ? super.m_obj : "");
    }
    
    public int rtf(final XPathContext support) {
        final DTM frag = support.createDocumentFragment();
        frag.appendTextChild(this.str());
        return frag.getDocument();
    }
    
    public void dispatchCharactersEvents(final ContentHandler ch) throws SAXException {
        final String str = this.str();
        ch.characters(str.toCharArray(), 0, str.length());
    }
    
    public void dispatchAsComment(final LexicalHandler lh) throws SAXException {
        final String str = this.str();
        lh.comment(str.toCharArray(), 0, str.length());
    }
    
    public int length() {
        return this.str().length();
    }
    
    public char charAt(final int index) {
        return this.str().charAt(index);
    }
    
    public void getChars(final int srcBegin, final int srcEnd, final char[] dst, final int dstBegin) {
        this.str().getChars(srcBegin, srcEnd, dst, dstBegin);
    }
    
    public boolean equals(final XObject obj2) {
        final int t = obj2.getType();
        try {
            if (4 == t) {
                return obj2.equals(this);
            }
            if (1 == t) {
                return obj2.bool() == this.bool();
            }
            if (2 == t) {
                return obj2.num() == this.num();
            }
        }
        catch (TransformerException te) {
            throw new WrappedRuntimeException(te);
        }
        return this.xstr().equals(obj2.xstr());
    }
    
    public boolean equals(final XMLString obj2) {
        if (!obj2.hasString()) {
            return obj2.equals(this);
        }
        return this.str().equals(obj2.toString());
    }
    
    public boolean equals(final Object obj2) {
        if (null == obj2) {
            return false;
        }
        if (obj2 instanceof XNodeSet) {
            return obj2.equals(this);
        }
        if (obj2 instanceof XNumber) {
            return obj2.equals(this);
        }
        return this.str().equals(obj2.toString());
    }
    
    public boolean equalsIgnoreCase(final String anotherString) {
        return this.str().equalsIgnoreCase(anotherString);
    }
    
    public int compareTo(final XMLString xstr) {
        final int len1 = this.length();
        final int len2 = xstr.length();
        int n = Math.min(len1, len2);
        int i = 0;
        int j = 0;
        while (n-- != 0) {
            final char c1 = this.charAt(i);
            final char c2 = xstr.charAt(j);
            if (c1 != c2) {
                return c1 - c2;
            }
            ++i;
            ++j;
        }
        return len1 - len2;
    }
    
    public int compareToIgnoreCase(final XMLString str) {
        throw new WrappedRuntimeException(new NoSuchMethodException("Java 1.2 method, not yet implemented"));
    }
    
    public boolean startsWith(final String prefix, final int toffset) {
        return this.str().startsWith(prefix, toffset);
    }
    
    public boolean startsWith(final String prefix) {
        return this.startsWith(prefix, 0);
    }
    
    public boolean startsWith(final XMLString prefix, final int toffset) {
        int to = toffset;
        final int tlim = this.length();
        int po = 0;
        int pc = prefix.length();
        if (toffset < 0 || toffset > tlim - pc) {
            return false;
        }
        while (--pc >= 0) {
            if (this.charAt(to) != prefix.charAt(po)) {
                return false;
            }
            ++to;
            ++po;
        }
        return true;
    }
    
    public boolean startsWith(final XMLString prefix) {
        return this.startsWith(prefix, 0);
    }
    
    public boolean endsWith(final String suffix) {
        return this.str().endsWith(suffix);
    }
    
    public int hashCode() {
        return this.str().hashCode();
    }
    
    public int indexOf(final int ch) {
        return this.str().indexOf(ch);
    }
    
    public int indexOf(final int ch, final int fromIndex) {
        return this.str().indexOf(ch, fromIndex);
    }
    
    public int lastIndexOf(final int ch) {
        return this.str().lastIndexOf(ch);
    }
    
    public int lastIndexOf(final int ch, final int fromIndex) {
        return this.str().lastIndexOf(ch, fromIndex);
    }
    
    public int indexOf(final String str) {
        return this.str().indexOf(str);
    }
    
    public int indexOf(final XMLString str) {
        return this.str().indexOf(str.toString());
    }
    
    public int indexOf(final String str, final int fromIndex) {
        return this.str().indexOf(str, fromIndex);
    }
    
    public int lastIndexOf(final String str) {
        return this.str().lastIndexOf(str);
    }
    
    public int lastIndexOf(final String str, final int fromIndex) {
        return this.str().lastIndexOf(str, fromIndex);
    }
    
    public XMLString substring(final int beginIndex) {
        return new XString(this.str().substring(beginIndex));
    }
    
    public XMLString substring(final int beginIndex, final int endIndex) {
        return new XString(this.str().substring(beginIndex, endIndex));
    }
    
    public XMLString concat(final String str) {
        return new XString(this.str().concat(str));
    }
    
    public XMLString toLowerCase(final Locale locale) {
        return new XString(this.str().toLowerCase(locale));
    }
    
    public XMLString toLowerCase() {
        return new XString(this.str().toLowerCase());
    }
    
    public XMLString toUpperCase(final Locale locale) {
        return new XString(this.str().toUpperCase(locale));
    }
    
    public XMLString toUpperCase() {
        return new XString(this.str().toUpperCase());
    }
    
    public XMLString trim() {
        return new XString(this.str().trim());
    }
    
    private static boolean isSpace(final char ch) {
        return XMLCharacterRecognizer.isWhiteSpace(ch);
    }
    
    public XMLString fixWhiteSpace(final boolean trimHead, final boolean trimTail, final boolean doublePunctuationSpaces) {
        final int len = this.length();
        final char[] buf = new char[len];
        this.getChars(0, len, buf, 0);
        boolean edit = false;
        int s;
        for (s = 0; s < len && !isSpace(buf[s]); ++s) {}
        int d = s;
        boolean pres = false;
        while (s < len) {
            final char c = buf[s];
            if (isSpace(c)) {
                if (!pres) {
                    if (' ' != c) {
                        edit = true;
                    }
                    buf[d++] = ' ';
                    if (doublePunctuationSpaces && s != 0) {
                        final char prevChar = buf[s - 1];
                        if (prevChar != '.' && prevChar != '!' && prevChar != '?') {
                            pres = true;
                        }
                    }
                    else {
                        pres = true;
                    }
                }
                else {
                    edit = true;
                    pres = true;
                }
            }
            else {
                buf[d++] = c;
                pres = false;
            }
            ++s;
        }
        if (trimTail && 1 <= d && ' ' == buf[d - 1]) {
            edit = true;
            --d;
        }
        int start = 0;
        if (trimHead && 0 < d && ' ' == buf[0]) {
            edit = true;
            ++start;
        }
        final XMLStringFactory xsf = XMLStringFactoryImpl.getFactory();
        return edit ? xsf.newstr(new String(buf, start, d - start)) : this;
    }
    
    public void callVisitors(final ExpressionOwner owner, final XPathVisitor visitor) {
        visitor.visitStringLiteral(owner, this);
    }
    
    static {
        XString.EMPTYSTRING = new XString("");
    }
}
