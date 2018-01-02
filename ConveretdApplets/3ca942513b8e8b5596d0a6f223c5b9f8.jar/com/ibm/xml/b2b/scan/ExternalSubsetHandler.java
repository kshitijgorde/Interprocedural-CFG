// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan;

import com.ibm.xml.b2b.util.XMLName;

public interface ExternalSubsetHandler extends MarkupDeclHandler
{
    boolean externalSubsetPEReference(final XMLName p0);
    
    boolean startPEReferenceWithinMarkup(final XMLName p0, final int p1);
    
    void endPEReferenceWithinMarkup(final int p0);
}
