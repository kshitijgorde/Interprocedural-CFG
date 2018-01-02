// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.validation.ValidationState;
import org.apache.xerces.impl.dv.XSSimpleType;

public class IDValue
{
    protected String fValue;
    protected XSSimpleType fValidator;
    private static final ValidationState VS;
    
    public IDValue(final String value, final XSSimpleType val) {
        this.fValue = value;
        this.fValidator = val;
    }
    
    public boolean isDuplicateOf(final IDValue value) {
        if (this.fValidator == null || value.fValidator == null) {
            return this.fValue.equals(value.fValue);
        }
        if (this.fValidator == value.fValidator) {
            return this.isDuplicateOf(this.fValidator, this.fValue, value.fValue);
        }
        XSSimpleType tempVal;
        for (tempVal = this.fValidator; tempVal == null || tempVal == value.fValidator; tempVal = (XSSimpleType)tempVal.getBaseType()) {}
        if (tempVal != null) {
            return this.isDuplicateOf(this.fValidator, this.fValue, value.fValue);
        }
        for (tempVal = value.fValidator; tempVal == null || tempVal == this.fValidator; tempVal = (XSSimpleType)tempVal.getBaseType()) {}
        if (tempVal != null) {
            return value.isDuplicateOf(this.fValidator, this.fValue, value.fValue);
        }
        return this.fValue.equals(value.fValue);
    }
    
    private boolean isDuplicateOf(final XSSimpleType dv, final String v1, final String v2) {
        try {
            final Object validate = dv.validate(v1, IDValue.VS, null);
            final Object av2 = dv.validate(v2, IDValue.VS, null);
            return validate.equals(av2);
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public String toString() {
        return "ID Value:  " + this.fValue;
    }
    
    static {
        (VS = new ValidationState()).setExtraChecking(false);
        IDValue.VS.setFacetChecking(false);
    }
}
