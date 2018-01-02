// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.impl.xs.psvi.XSAnnotation;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.impl.xs.psvi.StringList;
import org.apache.xerces.impl.xs.psvi.XSIDConstraintDefinition;

public abstract class IdentityConstraint implements XSIDConstraintDefinition
{
    protected short type;
    protected String fNamespace;
    protected String fIdentityConstraintName;
    protected String fElementName;
    protected Selector fSelector;
    protected int fFieldCount;
    protected Field[] fFields;
    
    protected IdentityConstraint(final String namespace, final String identityConstraintName, final String elemName) {
        this.fNamespace = namespace;
        this.fIdentityConstraintName = identityConstraintName;
        this.fElementName = elemName;
    }
    
    public String getIdentityConstraintName() {
        return this.fIdentityConstraintName;
    }
    
    public void setSelector(final Selector selector) {
        this.fSelector = selector;
    }
    
    public Selector getSelector() {
        return this.fSelector;
    }
    
    public void addField(final Field field) {
        if (this.fFields == null) {
            this.fFields = new Field[4];
        }
        else if (this.fFieldCount == this.fFields.length) {
            this.fFields = resize(this.fFields, this.fFieldCount * 2);
        }
        this.fFields[this.fFieldCount++] = field;
    }
    
    public int getFieldCount() {
        return this.fFieldCount;
    }
    
    public Field getFieldAt(final int index) {
        return this.fFields[index];
    }
    
    public String getElementName() {
        return this.fElementName;
    }
    
    public String toString() {
        final String s = super.toString();
        final int index1 = s.lastIndexOf(36);
        if (index1 != -1) {
            return s.substring(index1 + 1);
        }
        final int index2 = s.lastIndexOf(46);
        if (index2 != -1) {
            return s.substring(index2 + 1);
        }
        return s;
    }
    
    public boolean equals(final IdentityConstraint id) {
        boolean areEqual = this.fIdentityConstraintName.equals(id.fIdentityConstraintName);
        if (!areEqual) {
            return false;
        }
        areEqual = this.fSelector.toString().equals(id.fSelector.toString());
        if (!areEqual) {
            return false;
        }
        areEqual = (this.fFieldCount == id.fFieldCount);
        if (!areEqual) {
            return false;
        }
        for (int i = 0; i < this.fFieldCount; ++i) {
            if (!this.fFields[i].toString().equals(id.fFields[i].toString())) {
                return false;
            }
        }
        return true;
    }
    
    static final Field[] resize(final Field[] oldArray, final int newSize) {
        final Field[] newArray = new Field[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }
    
    public short getType() {
        return 10;
    }
    
    public String getName() {
        return this.fIdentityConstraintName;
    }
    
    public String getNamespace() {
        return this.fNamespace;
    }
    
    public short getCategory() {
        return this.type;
    }
    
    public String getSelectorStr() {
        return this.fSelector.toString();
    }
    
    public StringList getFieldStrs() {
        final String[] strs = new String[this.fFieldCount];
        for (int i = 0; i < this.fFieldCount; ++i) {
            strs[i] = this.fFields[i].toString();
        }
        return new StringListImpl(strs, this.fFieldCount);
    }
    
    public XSIDConstraintDefinition getRefKey() {
        return null;
    }
    
    public XSAnnotation getAnnotation() {
        return null;
    }
}
