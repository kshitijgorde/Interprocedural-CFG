// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;

public interface DocumentEventHandler
{
    void startElementEvent(final boolean p0);
    
    void endElementEvent();
    
    void characters(final XMLString p0);
    
    void character(final int p0, final boolean p1);
    
    void processingInstruction(final XMLName p0, final XMLString p1);
}
