// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.classfile.JavaClass;
import com.ibm.xslt4j.bcel.Constants;
import com.ibm.xslt4j.bcel.Repository;

public class ReferenceType extends Type
{
    protected ReferenceType(final byte t, final String s) {
        super(t, s);
    }
    
    ReferenceType() {
        super((byte)14, "<null object>");
    }
    
    public boolean isCastableTo(final Type t) {
        return this.equals(Type.NULL) || this.isAssignmentCompatibleWith(t);
    }
    
    public boolean isAssignmentCompatibleWith(final Type t) {
        if (!(t instanceof ReferenceType)) {
            return false;
        }
        final ReferenceType T = (ReferenceType)t;
        if (this.equals(Type.NULL)) {
            return true;
        }
        if (this instanceof ObjectType && ((ObjectType)this).referencesClass()) {
            if (T instanceof ObjectType && ((ObjectType)T).referencesClass()) {
                if (this.equals(T)) {
                    return true;
                }
                if (Repository.instanceOf(((ObjectType)this).getClassName(), ((ObjectType)T).getClassName())) {
                    return true;
                }
            }
            if (T instanceof ObjectType && ((ObjectType)T).referencesInterface() && Repository.implementationOf(((ObjectType)this).getClassName(), ((ObjectType)T).getClassName())) {
                return true;
            }
        }
        if (this instanceof ObjectType && ((ObjectType)this).referencesInterface()) {
            if (T instanceof ObjectType && ((ObjectType)T).referencesClass() && T.equals(Type.OBJECT)) {
                return true;
            }
            if (T instanceof ObjectType && ((ObjectType)T).referencesInterface()) {
                if (this.equals(T)) {
                    return true;
                }
                if (Repository.implementationOf(((ObjectType)this).getClassName(), ((ObjectType)T).getClassName())) {
                    return true;
                }
            }
        }
        if (this instanceof ArrayType) {
            if (T instanceof ObjectType && ((ObjectType)T).referencesClass() && T.equals(Type.OBJECT)) {
                return true;
            }
            if (T instanceof ArrayType) {
                final Type sc = ((ArrayType)this).getElementType();
                final Type tc = ((ArrayType)this).getElementType();
                if (sc instanceof BasicType && tc instanceof BasicType && sc.equals(tc)) {
                    return true;
                }
                if (tc instanceof ReferenceType && sc instanceof ReferenceType && ((ReferenceType)sc).isAssignmentCompatibleWith(tc)) {
                    return true;
                }
            }
            if (T instanceof ObjectType && ((ObjectType)T).referencesInterface()) {
                for (int ii = 0; ii < Constants.INTERFACES_IMPLEMENTED_BY_ARRAYS.length; ++ii) {
                    if (T.equals(new ObjectType(Constants.INTERFACES_IMPLEMENTED_BY_ARRAYS[ii]))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public ReferenceType firstCommonSuperclass(final ReferenceType t) {
        if (this.equals(Type.NULL)) {
            return t;
        }
        if (t.equals(Type.NULL)) {
            return this;
        }
        if (this.equals(t)) {
            return this;
        }
        if (this instanceof ArrayType || t instanceof ArrayType) {
            return Type.OBJECT;
        }
        if ((this instanceof ObjectType && ((ObjectType)this).referencesInterface()) || (t instanceof ObjectType && ((ObjectType)t).referencesInterface())) {
            return Type.OBJECT;
        }
        final ObjectType thiz = (ObjectType)this;
        final ObjectType other = (ObjectType)t;
        final JavaClass[] thiz_sups = Repository.getSuperClasses(thiz.getClassName());
        final JavaClass[] other_sups = Repository.getSuperClasses(other.getClassName());
        if (thiz_sups == null || other_sups == null) {
            return null;
        }
        final JavaClass[] this_sups = new JavaClass[thiz_sups.length + 1];
        final JavaClass[] t_sups = new JavaClass[other_sups.length + 1];
        System.arraycopy(thiz_sups, 0, this_sups, 1, thiz_sups.length);
        System.arraycopy(other_sups, 0, t_sups, 1, other_sups.length);
        this_sups[0] = Repository.lookupClass(thiz.getClassName());
        t_sups[0] = Repository.lookupClass(other.getClassName());
        for (int i = 0; i < t_sups.length; ++i) {
            for (int j = 0; j < this_sups.length; ++j) {
                if (this_sups[j].equals(t_sups[i])) {
                    return new ObjectType(this_sups[j].getClassName());
                }
            }
        }
        return null;
    }
}
