// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.classfile;

import java.io.DataInputStream;
import org.apache.bcel.Constants;
import java.io.IOException;
import java.io.DataOutputStream;

public abstract class Constant implements Cloneable, Node
{
    protected byte tag;
    
    Constant(final byte tag) {
        this.tag = tag;
    }
    
    public abstract void accept(final Visitor p0);
    
    public abstract void dump(final DataOutputStream p0) throws IOException;
    
    public final byte getTag() {
        return this.tag;
    }
    
    public String toString() {
        return Constants.CONSTANT_NAMES[this.tag] + "[" + this.tag + "]";
    }
    
    public Constant copy() {
        try {
            return (Constant)super.clone();
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    static final Constant readConstant(final DataInputStream file) throws IOException, ClassFormatError {
        final byte b = file.readByte();
        switch (b) {
            case 7: {
                return new ConstantClass(file);
            }
            case 9: {
                return new ConstantFieldref(file);
            }
            case 10: {
                return new ConstantMethodref(file);
            }
            case 11: {
                return new ConstantInterfaceMethodref(file);
            }
            case 8: {
                return new ConstantString(file);
            }
            case 3: {
                return new ConstantInteger(file);
            }
            case 4: {
                return new ConstantFloat(file);
            }
            case 5: {
                return new ConstantLong(file);
            }
            case 6: {
                return new ConstantDouble(file);
            }
            case 12: {
                return new ConstantNameAndType(file);
            }
            case 1: {
                return new ConstantUtf8(file);
            }
            default: {
                throw new ClassFormatError("Invalid byte tag in constant pool: " + b);
            }
        }
    }
}
