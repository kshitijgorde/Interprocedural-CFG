// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc;

public interface DOMEnhancedForDTM extends DOM
{
    short[] getMapping(final String[] p0, final String[] p1, final int[] p2);
    
    int[] getReverseMapping(final String[] p0, final String[] p1, final int[] p2);
    
    short[] getNamespaceMapping(final String[] p0);
    
    short[] getReverseNamespaceMapping(final String[] p0);
    
    String getDocumentURI();
    
    void setDocumentURI(final String p0);
    
    int getExpandedTypeID2(final int p0);
    
    boolean hasDOMSource();
    
    int getElementById(final String p0);
}
