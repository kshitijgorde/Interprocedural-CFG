// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.classfile;

import com.ibm.xslt4j.bcel.Constants;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public class ConstantPool implements Cloneable, Node
{
    private int constant_pool_count;
    private Constant[] constant_pool;
    
    public ConstantPool(final Constant[] constant_pool) {
        this.setConstantPool(constant_pool);
    }
    
    ConstantPool(final DataInputStream file) throws IOException, ClassFormatError {
        this.constant_pool_count = file.readUnsignedShort();
        this.constant_pool = new Constant[this.constant_pool_count];
        for (int i = 1; i < this.constant_pool_count; ++i) {
            this.constant_pool[i] = Constant.readConstant(file);
            final byte tag = this.constant_pool[i].getTag();
            if (tag == 6 || tag == 5) {
                ++i;
            }
        }
    }
    
    public void accept(final Visitor v) {
        v.visitConstantPool(this);
    }
    
    public String constantToString(Constant c) throws ClassFormatError {
        final byte tag = c.getTag();
        String str = null;
        switch (tag) {
            case 7: {
                final int i = ((ConstantClass)c).getNameIndex();
                c = this.getConstant(i, (byte)1);
                str = Utility.compactClassName(((ConstantUtf8)c).getBytes(), false);
                break;
            }
            case 8: {
                final int i = ((ConstantString)c).getStringIndex();
                c = this.getConstant(i, (byte)1);
                str = "\"" + escape(((ConstantUtf8)c).getBytes()) + "\"";
                break;
            }
            case 1: {
                str = ((ConstantUtf8)c).getBytes();
                break;
            }
            case 6: {
                str = "" + ((ConstantDouble)c).getBytes();
                break;
            }
            case 4: {
                str = "" + ((ConstantFloat)c).getBytes();
                break;
            }
            case 5: {
                str = "" + ((ConstantLong)c).getBytes();
                break;
            }
            case 3: {
                str = "" + ((ConstantInteger)c).getBytes();
                break;
            }
            case 12: {
                str = String.valueOf(this.constantToString(((ConstantNameAndType)c).getNameIndex(), (byte)1)) + " " + this.constantToString(((ConstantNameAndType)c).getSignatureIndex(), (byte)1);
                break;
            }
            case 9:
            case 10:
            case 11: {
                str = String.valueOf(this.constantToString(((ConstantCP)c).getClassIndex(), (byte)7)) + "." + this.constantToString(((ConstantCP)c).getNameAndTypeIndex(), (byte)12);
                break;
            }
            default: {
                throw new RuntimeException("Unknown constant type " + tag);
            }
        }
        return str;
    }
    
    private static final String escape(final String str) {
        final int len = str.length();
        final StringBuffer buf = new StringBuffer(len + 5);
        final char[] ch = str.toCharArray();
        for (int i = 0; i < len; ++i) {
            switch (ch[i]) {
                case '\n': {
                    buf.append("\\n");
                    break;
                }
                case '\r': {
                    buf.append("\\r");
                    break;
                }
                case '\t': {
                    buf.append("\\t");
                    break;
                }
                case '\b': {
                    buf.append("\\b");
                    break;
                }
                case '\"': {
                    buf.append("\\\"");
                    break;
                }
                default: {
                    buf.append(ch[i]);
                    break;
                }
            }
        }
        return buf.toString();
    }
    
    public String constantToString(final int index, final byte tag) throws ClassFormatError {
        final Constant c = this.getConstant(index, tag);
        return this.constantToString(c);
    }
    
    public void dump(final DataOutputStream file) throws IOException {
        file.writeShort(this.constant_pool_count);
        for (int i = 1; i < this.constant_pool_count; ++i) {
            if (this.constant_pool[i] != null) {
                this.constant_pool[i].dump(file);
            }
        }
    }
    
    public Constant getConstant(final int index) {
        if (index >= this.constant_pool.length || index < 0) {
            throw new ClassFormatError("Invalid constant pool reference: " + index + ". Constant pool size is: " + this.constant_pool.length);
        }
        return this.constant_pool[index];
    }
    
    public Constant getConstant(final int index, final byte tag) throws ClassFormatError {
        final Constant c = this.getConstant(index);
        if (c == null) {
            throw new ClassFormatError("Constant pool at index " + index + " is null.");
        }
        if (c.getTag() == tag) {
            return c;
        }
        throw new ClassFormatError("Expected class `" + Constants.CONSTANT_NAMES[tag] + "' at index " + index + " and got " + c);
    }
    
    public Constant[] getConstantPool() {
        return this.constant_pool;
    }
    
    public String getConstantString(final int index, final byte tag) throws ClassFormatError {
        Constant c = this.getConstant(index, tag);
        int i = 0;
        switch (tag) {
            case 7: {
                i = ((ConstantClass)c).getNameIndex();
                break;
            }
            case 8: {
                i = ((ConstantString)c).getStringIndex();
                break;
            }
            default: {
                throw new RuntimeException("getConstantString called with illegal tag " + tag);
            }
        }
        c = this.getConstant(i, (byte)1);
        return ((ConstantUtf8)c).getBytes();
    }
    
    public int getLength() {
        return this.constant_pool_count;
    }
    
    public void setConstant(final int index, final Constant constant) {
        this.constant_pool[index] = constant;
    }
    
    public void setConstantPool(final Constant[] constant_pool) {
        this.constant_pool = constant_pool;
        this.constant_pool_count = ((constant_pool == null) ? 0 : constant_pool.length);
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        for (int i = 1; i < this.constant_pool_count; ++i) {
            buf.append(String.valueOf(i) + ")" + this.constant_pool[i] + "\n");
        }
        return buf.toString();
    }
    
    public ConstantPool copy() {
        ConstantPool c = null;
        try {
            c = (ConstantPool)this.clone();
        }
        catch (CloneNotSupportedException ex) {}
        c.constant_pool = new Constant[this.constant_pool_count];
        for (int i = 1; i < this.constant_pool_count; ++i) {
            if (this.constant_pool[i] != null) {
                c.constant_pool[i] = this.constant_pool[i].copy();
            }
        }
        return c;
    }
}
