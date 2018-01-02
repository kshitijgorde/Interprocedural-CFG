// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv;

public class ValidatedInfo
{
    public String normalizedValue;
    public Object actualValue;
    public XSSimpleType memberType;
    public XSSimpleType[] memberTypes;
    
    public void reset() {
        this.normalizedValue = null;
        this.actualValue = null;
        this.memberType = null;
        this.memberTypes = null;
    }
    
    public String stringValue() {
        if (this.actualValue == null) {
            return this.normalizedValue;
        }
        return this.actualValue.toString();
    }
}
