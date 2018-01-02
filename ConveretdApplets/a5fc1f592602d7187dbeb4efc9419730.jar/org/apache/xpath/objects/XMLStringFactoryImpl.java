// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.apache.xml.utils.FastStringBuffer;
import org.apache.xml.utils.XMLString;
import org.apache.xml.utils.XMLStringFactory;

public class XMLStringFactoryImpl extends XMLStringFactory
{
    private static XMLStringFactory m_xstringfactory;
    
    public static XMLStringFactory getFactory() {
        return XMLStringFactoryImpl.m_xstringfactory;
    }
    
    public XMLString newstr(final String string) {
        return new XString(string);
    }
    
    public XMLString newstr(final FastStringBuffer fsb, final int start, final int length) {
        return new XStringForFSB(fsb, start, length);
    }
    
    public XMLString newstr(final char[] string, final int start, final int length) {
        return new XStringForChars(string, start, length);
    }
    
    public XMLString emptystr() {
        return XString.EMPTYSTRING;
    }
    
    static {
        XMLStringFactoryImpl.m_xstringfactory = new XMLStringFactoryImpl();
    }
}
