// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.structurals;

import com.ibm.xslt4j.bcel.generic.ReferenceType;
import com.ibm.xslt4j.bcel.verifier.exc.StructuralCodeConstraintException;
import com.ibm.xslt4j.bcel.verifier.exc.AssertionViolatedException;
import com.ibm.xslt4j.bcel.generic.Type;

public class LocalVariables
{
    private Type[] locals;
    
    public LocalVariables(final int maxLocals) {
        this.locals = new Type[maxLocals];
        for (int i = 0; i < maxLocals; ++i) {
            this.locals[i] = Type.UNKNOWN;
        }
    }
    
    protected Object clone() {
        final LocalVariables lvs = new LocalVariables(this.locals.length);
        for (int i = 0; i < this.locals.length; ++i) {
            lvs.locals[i] = this.locals[i];
        }
        return lvs;
    }
    
    public Type get(final int i) {
        return this.locals[i];
    }
    
    public LocalVariables getClone() {
        return (LocalVariables)this.clone();
    }
    
    public int maxLocals() {
        return this.locals.length;
    }
    
    public void set(final int i, final Type type) {
        if (type == Type.BYTE || type == Type.SHORT || type == Type.BOOLEAN || type == Type.CHAR) {
            throw new AssertionViolatedException("LocalVariables do not know about '" + type + "'. Use Type.INT instead.");
        }
        this.locals[i] = type;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof LocalVariables)) {
            return false;
        }
        final LocalVariables lv = (LocalVariables)o;
        if (this.locals.length != lv.locals.length) {
            return false;
        }
        for (int i = 0; i < this.locals.length; ++i) {
            if (!this.locals[i].equals(lv.locals[i])) {
                return false;
            }
        }
        return true;
    }
    
    public void merge(final LocalVariables lv) {
        if (this.locals.length != lv.locals.length) {
            throw new AssertionViolatedException("Merging LocalVariables of different size?!? From different methods or what?!?");
        }
        for (int i = 0; i < this.locals.length; ++i) {
            this.merge(lv, i);
        }
    }
    
    private void merge(final LocalVariables lv, final int i) {
        if (!(this.locals[i] instanceof UninitializedObjectType) && lv.locals[i] instanceof UninitializedObjectType) {
            throw new StructuralCodeConstraintException("Backwards branch with an uninitialized object in the local variables detected.");
        }
        if (!this.locals[i].equals(lv.locals[i]) && this.locals[i] instanceof UninitializedObjectType && lv.locals[i] instanceof UninitializedObjectType) {
            throw new StructuralCodeConstraintException("Backwards branch with an uninitialized object in the local variables detected.");
        }
        if (this.locals[i] instanceof UninitializedObjectType && !(lv.locals[i] instanceof UninitializedObjectType)) {
            this.locals[i] = ((UninitializedObjectType)this.locals[i]).getInitialized();
        }
        if (this.locals[i] instanceof ReferenceType && lv.locals[i] instanceof ReferenceType) {
            if (!this.locals[i].equals(lv.locals[i])) {
                final Type sup = ((ReferenceType)this.locals[i]).firstCommonSuperclass((ReferenceType)lv.locals[i]);
                if (sup == null) {
                    throw new AssertionViolatedException("Could not load all the super classes of '" + this.locals[i] + "' and '" + lv.locals[i] + "'.");
                }
                this.locals[i] = sup;
            }
        }
        else if (!this.locals[i].equals(lv.locals[i])) {
            this.locals[i] = Type.UNKNOWN;
        }
    }
    
    public String toString() {
        String s = new String();
        for (int i = 0; i < this.locals.length; ++i) {
            s = String.valueOf(s) + Integer.toString(i) + ": " + this.locals[i] + "\n";
        }
        return s;
    }
    
    public void initializeObject(final UninitializedObjectType u) {
        for (int i = 0; i < this.locals.length; ++i) {
            if (this.locals[i] == u) {
                this.locals[i] = u.getInitialized();
            }
        }
    }
}
