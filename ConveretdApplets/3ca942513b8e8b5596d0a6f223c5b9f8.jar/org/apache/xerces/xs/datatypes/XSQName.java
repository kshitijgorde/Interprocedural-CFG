// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs.datatypes;

import org.apache.xerces.xni.QName;

public interface XSQName
{
    QName getXNIQName();
    
    javax.xml.namespace.QName getJAXPQName();
}
