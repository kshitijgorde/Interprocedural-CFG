// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

public class XMLStringFactoryDefault extends XMLStringFactory
{
    private static final XMLStringDefault EMPTY_STR;
    
    public XMLString newstr(final String string) {
        return new XMLStringDefault(string);
    }
    
    public XMLString newstr(final FastStringBuffer fsb, final int start, final int length) {
        return new XMLStringDefault(fsb.getString(start, length));
    }
    
    public XMLString newstr(final char[] string, final int start, final int length) {
        return new XMLStringDefault(new String(string, start, length));
    }
    
    public XMLString emptystr() {
        return XMLStringFactoryDefault.EMPTY_STR;
    }
    
    static {
        EMPTY_STR = new XMLStringDefault("");
    }
}
