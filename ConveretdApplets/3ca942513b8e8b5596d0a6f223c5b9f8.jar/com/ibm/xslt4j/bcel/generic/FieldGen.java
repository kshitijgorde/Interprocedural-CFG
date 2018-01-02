// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.classfile.Utility;
import java.util.Iterator;
import com.ibm.xslt4j.bcel.classfile.Constant;
import com.ibm.xslt4j.bcel.classfile.ConstantPool;
import com.ibm.xslt4j.bcel.classfile.ConstantObject;
import com.ibm.xslt4j.bcel.classfile.Attribute;
import com.ibm.xslt4j.bcel.classfile.ConstantValue;
import com.ibm.xslt4j.bcel.classfile.Field;
import java.util.ArrayList;

public class FieldGen extends FieldGenOrMethodGen
{
    private Object value;
    private ArrayList observers;
    
    public FieldGen(final int access_flags, final Type type, final String name, final ConstantPoolGen cp) {
        this.value = null;
        this.setAccessFlags(access_flags);
        this.setType(type);
        this.setName(name);
        this.setConstantPool(cp);
    }
    
    public FieldGen(final Field field, final ConstantPoolGen cp) {
        this(field.getAccessFlags(), Type.getType(field.getSignature()), field.getName(), cp);
        final Attribute[] attrs = field.getAttributes();
        for (int i = 0; i < attrs.length; ++i) {
            if (attrs[i] instanceof ConstantValue) {
                this.setValue(((ConstantValue)attrs[i]).getConstantValueIndex());
            }
            else {
                this.addAttribute(attrs[i]);
            }
        }
    }
    
    private void setValue(final int index) {
        final ConstantPool cp = super.cp.getConstantPool();
        final Constant c = cp.getConstant(index);
        this.value = ((ConstantObject)c).getConstantValue(cp);
    }
    
    public void setInitValue(final String str) {
        this.checkType(new ObjectType("java.lang.String"));
        if (str != null) {
            this.value = str;
        }
    }
    
    public void setInitValue(final long l) {
        this.checkType(Type.LONG);
        if (l != 0L) {
            this.value = new Long(l);
        }
    }
    
    public void setInitValue(final int i) {
        this.checkType(Type.INT);
        if (i != 0) {
            this.value = new Integer(i);
        }
    }
    
    public void setInitValue(final short s) {
        this.checkType(Type.SHORT);
        if (s != 0) {
            this.value = new Integer(s);
        }
    }
    
    public void setInitValue(final char c) {
        this.checkType(Type.CHAR);
        if (c != '\0') {
            this.value = new Integer(c);
        }
    }
    
    public void setInitValue(final byte b) {
        this.checkType(Type.BYTE);
        if (b != 0) {
            this.value = new Integer(b);
        }
    }
    
    public void setInitValue(final boolean b) {
        this.checkType(Type.BOOLEAN);
        if (b) {
            this.value = new Integer(1);
        }
    }
    
    public void setInitValue(final float f) {
        this.checkType(Type.FLOAT);
        if (f != 0.0) {
            this.value = new Float(f);
        }
    }
    
    public void setInitValue(final double d) {
        this.checkType(Type.DOUBLE);
        if (d != 0.0) {
            this.value = new Double(d);
        }
    }
    
    public void cancelInitValue() {
        this.value = null;
    }
    
    private void checkType(final Type atype) {
        if (super.type == null) {
            throw new ClassGenException("You haven't defined the type of the field yet");
        }
        if (!this.isFinal()) {
            throw new ClassGenException("Only final fields may have an initial value!");
        }
        if (!super.type.equals(atype)) {
            throw new ClassGenException("Types are not compatible: " + super.type + " vs. " + atype);
        }
    }
    
    public Field getField() {
        final String signature = this.getSignature();
        final int name_index = super.cp.addUtf8(super.name);
        final int signature_index = super.cp.addUtf8(signature);
        if (this.value != null) {
            this.checkType(super.type);
            final int index = this.addConstant();
            this.addAttribute(new ConstantValue(super.cp.addUtf8("ConstantValue"), 2, index, super.cp.getConstantPool()));
        }
        return new Field(super.access_flags, name_index, signature_index, this.getAttributes(), super.cp.getConstantPool());
    }
    
    private int addConstant() {
        switch (super.type.getType()) {
            case 4:
            case 5:
            case 8:
            case 9:
            case 10: {
                return super.cp.addInteger((int)this.value);
            }
            case 6: {
                return super.cp.addFloat((float)this.value);
            }
            case 7: {
                return super.cp.addDouble((double)this.value);
            }
            case 11: {
                return super.cp.addLong((long)this.value);
            }
            case 14: {
                return super.cp.addString((String)this.value);
            }
            default: {
                throw new RuntimeException("Oops: Unhandled : " + super.type.getType());
            }
        }
    }
    
    public String getSignature() {
        return super.type.getSignature();
    }
    
    public void addObserver(final FieldObserver o) {
        if (this.observers == null) {
            this.observers = new ArrayList();
        }
        this.observers.add(o);
    }
    
    public void removeObserver(final FieldObserver o) {
        if (this.observers != null) {
            this.observers.remove(o);
        }
    }
    
    public void update() {
        if (this.observers != null) {
            final Iterator e = this.observers.iterator();
            while (e.hasNext()) {
                e.next().notify(this);
            }
        }
    }
    
    public String getInitValue() {
        if (this.value != null) {
            return this.value.toString();
        }
        return null;
    }
    
    public final String toString() {
        String access = Utility.accessToString(super.access_flags);
        access = (access.equals("") ? "" : (String.valueOf(access) + " "));
        final String signature = super.type.toString();
        final String name = this.getName();
        final StringBuffer buf = new StringBuffer(String.valueOf(access) + signature + " " + name);
        final String value = this.getInitValue();
        if (value != null) {
            buf.append(" = " + value);
        }
        return buf.toString();
    }
    
    public FieldGen copy(final ConstantPoolGen cp) {
        final FieldGen fg = (FieldGen)this.clone();
        fg.setConstantPool(cp);
        return fg;
    }
}
