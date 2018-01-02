// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.structurals;

import com.ibm.xslt4j.bcel.generic.ObjectType;
import com.ibm.xslt4j.bcel.Constants;
import com.ibm.xslt4j.bcel.generic.ReferenceType;

public class UninitializedObjectType extends ReferenceType implements Constants
{
    private ObjectType initialized;
    
    public UninitializedObjectType(final ObjectType t) {
        super((byte)15, "<UNINITIALIZED OBJECT OF TYPE '" + t.getClassName() + "'>");
        this.initialized = t;
    }
    
    public ObjectType getInitialized() {
        return this.initialized;
    }
    
    public boolean equals(final Object o) {
        return o instanceof UninitializedObjectType && this.initialized.equals(((UninitializedObjectType)o).initialized);
    }
}
