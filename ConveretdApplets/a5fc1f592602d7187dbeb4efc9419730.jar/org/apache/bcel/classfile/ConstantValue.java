// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public final class ConstantValue extends Attribute
{
    private int constantvalue_index;
    
    public ConstantValue(final ConstantValue c) {
        this(c.getNameIndex(), c.getLength(), c.getConstantValueIndex(), c.getConstantPool());
    }
    
    ConstantValue(final int name_index, final int length, final DataInputStream file, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, file.readUnsignedShort(), constant_pool);
    }
    
    public ConstantValue(final int name_index, final int length, final int constantvalue_index, final ConstantPool constant_pool) {
        super((byte)1, name_index, length, constant_pool);
        this.constantvalue_index = constantvalue_index;
    }
    
    public void accept(final Visitor v) {
        v.visitConstantValue(this);
    }
    
    public final void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(this.constantvalue_index);
    }
    
    public final int getConstantValueIndex() {
        return this.constantvalue_index;
    }
    
    public final void setConstantValueIndex(final int constantvalue_index) {
        this.constantvalue_index = constantvalue_index;
    }
    
    public final String toString() throws InternalError {
        Constant c = super.constant_pool.getConstant(this.constantvalue_index);
        String buf = null;
        switch (c.getTag()) {
            case 5: {
                buf = "" + ((ConstantLong)c).getBytes();
                break;
            }
            case 4: {
                buf = "" + ((ConstantFloat)c).getBytes();
                break;
            }
            case 6: {
                buf = "" + ((ConstantDouble)c).getBytes();
                break;
            }
            case 3: {
                buf = "" + ((ConstantInteger)c).getBytes();
                break;
            }
            case 8: {
                final int i = ((ConstantString)c).getStringIndex();
                c = super.constant_pool.getConstant(i, (byte)1);
                buf = "\"" + convertString(((ConstantUtf8)c).getBytes()) + "\"";
                break;
            }
            default: {
                throw new InternalError("Type of ConstValue invalid: " + c);
            }
        }
        return buf;
    }
    
    private static final String convertString(final String label) {
        final char[] ch = label.toCharArray();
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < ch.length; ++i) {
            switch (ch[i]) {
                case '\n': {
                    buf.append("\\n");
                    break;
                }
                case '\r': {
                    buf.append("\\r");
                    break;
                }
                case '\"': {
                    buf.append("\\\"");
                    break;
                }
                case '\'': {
                    buf.append("\\'");
                    break;
                }
                case '\\': {
                    buf.append("\\\\");
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
    
    public Attribute copy(final ConstantPool constant_pool) {
        final ConstantValue c = (ConstantValue)this.clone();
        c.constant_pool = constant_pool;
        return c;
    }
}
