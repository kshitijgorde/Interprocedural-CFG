// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import org.apache.bcel.classfile.Attribute;
import java.util.ArrayList;
import org.apache.bcel.classfile.AccessFlags;

public abstract class FieldGenOrMethodGen extends AccessFlags implements NamedAndTyped, Cloneable
{
    protected String name;
    protected Type type;
    protected ConstantPoolGen cp;
    private ArrayList attribute_vec;
    
    protected FieldGenOrMethodGen() {
        this.attribute_vec = new ArrayList();
    }
    
    public void setType(final Type type) {
        this.type = type;
    }
    
    public Type getType() {
        return this.type;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public ConstantPoolGen getConstantPool() {
        return this.cp;
    }
    
    public void setConstantPool(final ConstantPoolGen cp) {
        this.cp = cp;
    }
    
    public void addAttribute(final Attribute a) {
        this.attribute_vec.add(a);
    }
    
    public void removeAttribute(final Attribute a) {
        this.attribute_vec.remove(a);
    }
    
    public void removeAttributes() {
        this.attribute_vec.clear();
    }
    
    public Attribute[] getAttributes() {
        final Attribute[] attributes = new Attribute[this.attribute_vec.size()];
        this.attribute_vec.toArray(attributes);
        return attributes;
    }
    
    public abstract String getSignature();
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            System.err.println(e);
            return null;
        }
    }
}
