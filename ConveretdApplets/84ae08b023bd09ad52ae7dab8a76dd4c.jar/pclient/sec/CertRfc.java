// 
// Decompiled by Procyon v0.5.30
// 

package pclient.sec;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.List;

final class CertRfc
{
    private final String name;
    private final char[] chars;
    private final int len;
    private int cur;
    
    CertRfc(final String name) {
        this.cur = 0;
        this.name = name;
        this.len = name.length();
        this.chars = name.toCharArray();
    }
    
    List parseDn() throws InvalidNameException {
        this.cur = 0;
        final ArrayList<CertRdn> list = new ArrayList<CertRdn>(this.len / 3 + 10);
        if (this.len == 0) {
            return list;
        }
        list.add(this.doParse(new CertRdn()));
        while (this.cur < this.len) {
            if (this.chars[this.cur] != ',' && this.chars[this.cur] != ';') {
                throw new InvalidNameException("Invalid name: " + this.name);
            }
            ++this.cur;
            list.add(0, this.doParse(new CertRdn()));
        }
        return list;
    }
    
    CertRdn parseRdn() throws InvalidNameException {
        return this.parseRdn(new CertRdn());
    }
    
    CertRdn parseRdn(CertRdn doParse) throws InvalidNameException {
        doParse = this.doParse(doParse);
        if (this.cur < this.len) {
            throw new InvalidNameException("Invalid RDN: " + this.name);
        }
        return doParse;
    }
    
    private CertRdn doParse(final CertRdn certRdn) throws InvalidNameException {
        while (this.cur < this.len) {
            this.consumeWhitespace();
            final String attrType = this.parseAttrType();
            this.consumeWhitespace();
            if (this.cur >= this.len || this.chars[this.cur] != '=') {
                throw new InvalidNameException("Invalid name: " + this.name);
            }
            ++this.cur;
            this.consumeWhitespace();
            final String attrValue = this.parseAttrValue();
            this.consumeWhitespace();
            certRdn.put(attrType, CertRdn.unescapeValue(attrValue));
            if (this.cur >= this.len) {
                break;
            }
            if (this.chars[this.cur] != '+') {
                break;
            }
            ++this.cur;
        }
        certRdn.sort();
        return certRdn;
    }
    
    private String parseAttrType() throws InvalidNameException {
        final int cur = this.cur;
        while (this.cur < this.len) {
            final char c = this.chars[this.cur];
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '-' && c != ' ') {
                break;
            }
            ++this.cur;
        }
        while (this.cur > cur && this.chars[this.cur - 1] == ' ') {
            --this.cur;
        }
        if (cur == this.cur) {
            throw new InvalidNameException("Invalid name: " + this.name);
        }
        return new String(this.chars, cur, this.cur - cur);
    }
    
    private String parseAttrValue() throws InvalidNameException {
        if (this.cur < this.len && this.chars[this.cur] == '#') {
            return this.parseBinaryAttrValue();
        }
        if (this.cur < this.len && this.chars[this.cur] == '\"') {
            return this.parseQuotedAttrValue();
        }
        return this.parseStringAttrValue();
    }
    
    private String parseBinaryAttrValue() throws InvalidNameException {
        final int cur = this.cur;
        ++this.cur;
        while (this.cur < this.len && Character.isLetterOrDigit(this.chars[this.cur])) {
            ++this.cur;
        }
        return new String(this.chars, cur, this.cur - cur);
    }
    
    private String parseQuotedAttrValue() throws InvalidNameException {
        final int cur = this.cur;
        ++this.cur;
        while (this.cur < this.len && this.chars[this.cur] != '\"') {
            if (this.chars[this.cur] == '\\') {
                ++this.cur;
            }
            ++this.cur;
        }
        if (this.cur >= this.len) {
            throw new InvalidNameException("Invalid name: " + this.name);
        }
        ++this.cur;
        return new String(this.chars, cur, this.cur - cur);
    }
    
    private String parseStringAttrValue() throws InvalidNameException {
        final int cur = this.cur;
        int cur2 = -1;
        while (this.cur < this.len && !this.atTerminator()) {
            if (this.chars[this.cur] == '\\') {
                ++this.cur;
                cur2 = this.cur;
            }
            ++this.cur;
        }
        if (this.cur > this.len) {
            throw new InvalidNameException("Invalid name: " + this.name);
        }
        int cur3;
        for (cur3 = this.cur; cur3 > cur && isWhitespace(this.chars[cur3 - 1]) && cur2 != cur3 - 1; --cur3) {}
        return new String(this.chars, cur, cur3 - cur);
    }
    
    private void consumeWhitespace() {
        while (this.cur < this.len && isWhitespace(this.chars[this.cur])) {
            ++this.cur;
        }
    }
    
    private boolean atTerminator() {
        return this.cur < this.len && (this.chars[this.cur] == ',' || this.chars[this.cur] == ';' || this.chars[this.cur] == '+');
    }
    
    private static boolean isWhitespace(final char c) {
        return c == ' ' || c == '\r';
    }
}
