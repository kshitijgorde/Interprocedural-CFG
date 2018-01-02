// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.entity.ParsedEntity;

public interface DocumentImplementationHandler
{
    boolean scanDoctypeDecl(final ParsedEntity p0);
    
    boolean scanExternalSubset();
    
    void startCDATA();
    
    void endCDATA();
    
    void comment(final XMLString p0);
    
    boolean entityReferenceInContent(final XMLName p0);
    
    boolean entityReferenceInAttValue(final XMLName p0);
}
