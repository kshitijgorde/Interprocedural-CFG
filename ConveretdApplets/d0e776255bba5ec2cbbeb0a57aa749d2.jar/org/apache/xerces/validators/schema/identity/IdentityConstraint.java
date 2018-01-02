// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema.identity;

public abstract class IdentityConstraint
{
    public static final short UNIQUE = 0;
    public static final short KEY = 1;
    public static final short KEYREF = 2;
    protected String fIdentityConstraintName;
    protected String fElementName;
    protected Selector fSelector;
    protected int fFieldCount;
    protected Field[] fFields;
    
    protected IdentityConstraint(final String fIdentityConstraintName, final String fElementName) {
        this.fIdentityConstraintName = fIdentityConstraintName;
        this.fElementName = fElementName;
    }
    
    public abstract short getType();
    
    public String getIdentityConstraintName() {
        return this.fIdentityConstraintName;
    }
    
    public String getElementName() {
        return this.fElementName;
    }
    
    public void setSelector(final Selector fSelector) {
        this.fSelector = fSelector;
    }
    
    public Selector getSelector() {
        return this.fSelector;
    }
    
    public void addField(final Field field) {
        try {
            this.fFields[this.fFieldCount] = null;
        }
        catch (NullPointerException ex) {
            this.fFields = new Field[4];
        }
        catch (ArrayIndexOutOfBoundsException ex2) {
            final Field[] fFields = new Field[this.fFields.length * 2];
            System.arraycopy(this.fFields, 0, fFields, 0, this.fFields.length);
            this.fFields = fFields;
        }
        this.fFields[this.fFieldCount++] = field;
    }
    
    public int getFieldCount() {
        return this.fFieldCount;
    }
    
    public Field getFieldAt(final int n) {
        return this.fFields[n];
    }
    
    public String toString() {
        final String string = super.toString();
        final int lastIndex = string.lastIndexOf(36);
        if (lastIndex != -1) {
            return string.substring(lastIndex + 1);
        }
        final int lastIndex2 = string.lastIndexOf(46);
        if (lastIndex2 != -1) {
            return string.substring(lastIndex2 + 1);
        }
        return string;
    }
}
