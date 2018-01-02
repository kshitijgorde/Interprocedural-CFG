// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni;

public interface NamespaceContext
{
    public static final String XML_URI = "http://www.w3.org/XML/1998/namespace".intern();
    public static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/".intern();
    
    String getURI(final String p0);
    
    int getDeclaredPrefixCount();
    
    String getDeclaredPrefixAt(final int p0);
    
    NamespaceContext getParentContext();
}
