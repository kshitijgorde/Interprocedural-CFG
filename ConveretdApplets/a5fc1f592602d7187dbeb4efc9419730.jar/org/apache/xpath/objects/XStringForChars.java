// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.FastStringBuffer;
import org.apache.xpath.res.XPATHMessages;

public class XStringForChars extends XString
{
    int m_start;
    int m_length;
    protected String m_strCache;
    
    public XStringForChars(final char[] val, final int start, final int length) {
        super(val);
        this.m_strCache = null;
        this.m_start = start;
        this.m_length = length;
        if (null == val) {
            throw new IllegalArgumentException(XPATHMessages.createXPATHMessage("ER_FASTSTRINGBUFFER_CANNOT_BE_NULL", null));
        }
    }
    
    private XStringForChars(final String val) {
        super(val);
        this.m_strCache = null;
        throw new IllegalArgumentException(XPATHMessages.createXPATHMessage("ER_XSTRINGFORCHARS_CANNOT_TAKE_STRING", null));
    }
    
    public FastStringBuffer fsb() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_FSB_NOT_SUPPORTED_XSTRINGFORCHARS", null));
    }
    
    public void appendToFsb(final FastStringBuffer fsb) {
        fsb.append((char[])super.m_obj, this.m_start, this.m_length);
    }
    
    public boolean hasString() {
        return null != this.m_strCache;
    }
    
    public String str() {
        if (null == this.m_strCache) {
            this.m_strCache = new String((char[])super.m_obj, this.m_start, this.m_length);
        }
        return this.m_strCache;
    }
    
    public Object object() {
        return this.str();
    }
    
    public void dispatchCharactersEvents(final ContentHandler ch) throws SAXException {
        ch.characters((char[])super.m_obj, this.m_start, this.m_length);
    }
    
    public void dispatchAsComment(final LexicalHandler lh) throws SAXException {
        lh.comment((char[])super.m_obj, this.m_start, this.m_length);
    }
    
    public int length() {
        return this.m_length;
    }
    
    public char charAt(final int index) {
        return ((char[])super.m_obj)[index + this.m_start];
    }
    
    public void getChars(final int srcBegin, final int srcEnd, final char[] dst, final int dstBegin) {
        System.arraycopy(super.m_obj, this.m_start + srcBegin, dst, dstBegin, srcEnd);
    }
}
