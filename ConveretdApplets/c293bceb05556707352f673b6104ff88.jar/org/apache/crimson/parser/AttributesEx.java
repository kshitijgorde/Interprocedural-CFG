// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.parser;

import org.xml.sax.Attributes;

public interface AttributesEx extends Attributes
{
    boolean isSpecified(final int p0);
    
    String getDefault(final int p0);
    
    String getIdAttributeName();
}
