// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.validation;

import org.w3c.dom.TypeInfo;

public abstract class TypeInfoProvider
{
    public abstract TypeInfo getElementTypeInfo();
    
    public abstract TypeInfo getAttributeTypeInfo(final int p0);
    
    public abstract boolean isIdAttribute(final int p0);
    
    public abstract boolean isSpecified(final int p0);
}
