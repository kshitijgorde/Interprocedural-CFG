// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import com.ibm.xml.b2b.util.XMLStringBuffer;
import com.ibm.xml.b2b.util.XMLString;

public interface ParsedEntityFactory
{
    void reset(final boolean p0);
    
    ParsedEntity createParsedEntity(final EntityInputSource p0, final boolean p1);
    
    ParsedEntity createParsedEntity(final XMLString p0);
    
    void releaseParsedEntity(final ParsedEntity p0);
    
    XMLStringBuffer createStringBuffer();
}
