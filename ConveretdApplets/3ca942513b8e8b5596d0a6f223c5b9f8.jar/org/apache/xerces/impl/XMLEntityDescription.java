// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.xni.XMLResourceIdentifier;

public interface XMLEntityDescription extends XMLResourceIdentifier
{
    void setEntityName(final String p0);
    
    String getEntityName();
}
