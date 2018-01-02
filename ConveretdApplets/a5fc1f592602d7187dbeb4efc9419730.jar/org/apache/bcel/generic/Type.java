// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import java.util.ArrayList;
import org.apache.bcel.classfile.Utility;

public abstract class Type
{
    protected byte type;
    protected String signature;
    public static final BasicType VOID;
    public static final BasicType BOOLEAN;
    public static final BasicType INT;
    public static final BasicType SHORT;
    public static final BasicType BYTE;
    public static final BasicType LONG;
    public static final BasicType DOUBLE;
    public static final BasicType FLOAT;
    public static final BasicType CHAR;
    public static final ObjectType OBJECT;
    public static final ObjectType STRING;
    public static final ObjectType STRINGBUFFER;
    public static final ObjectType THROWABLE;
    public static final Type[] NO_ARGS;
    public static final ReferenceType NULL;
    public static final Type UNKNOWN;
    private static int consumed_chars;
    
    protected Type(final byte t, final String s) {
        this.type = t;
        this.signature = s;
    }
    
    public String getSignature() {
        return this.signature;
    }
    
    public byte getType() {
        return this.type;
    }
    
    public int getSize() {
        switch (this.type) {
            case 7:
            case 11: {
                return 2;
            }
            case 12: {
                return 0;
            }
            default: {
                return 1;
            }
        }
    }
    
    public String toString() {
        return (this.equals(Type.NULL) || this.type >= 15) ? this.signature : Utility.signatureToString(this.signature, false);
    }
    
    public static String getMethodSignature(final Type return_type, final Type[] arg_types) {
        final StringBuffer buf = new StringBuffer("(");
        for (int length = (arg_types == null) ? 0 : arg_types.length, i = 0; i < length; ++i) {
            buf.append(arg_types[i].getSignature());
        }
        buf.append(')');
        buf.append(return_type.getSignature());
        return buf.toString();
    }
    
    public static final Type getType(final String signature) throws StringIndexOutOfBoundsException {
        final byte type = Utility.typeOfSignature(signature);
        if (type <= 12) {
            Type.consumed_chars = 1;
            return BasicType.getType(type);
        }
        if (type == 13) {
            int dim = 0;
            do {
                ++dim;
            } while (signature.charAt(dim) == '[');
            final Type t = getType(signature.substring(dim));
            Type.consumed_chars += dim;
            return new ArrayType(t, dim);
        }
        final int index = signature.indexOf(59);
        if (index < 0) {
            throw new ClassFormatError("Invalid signature: " + signature);
        }
        Type.consumed_chars = index + 1;
        return new ObjectType(signature.substring(1, index).replace('/', '.'));
    }
    
    public static Type getReturnType(final String signature) {
        try {
            return getType(signature.substring(signature.lastIndexOf(41) + 1));
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatError("Invalid method signature: " + signature);
        }
    }
    
    public static Type[] getArgumentTypes(final String signature) {
        final ArrayList vec = new ArrayList();
        try {
            if (signature.charAt(0) != '(') {
                throw new ClassFormatError("Invalid method signature: " + signature);
            }
            for (int index = 1; signature.charAt(index) != ')'; index += Type.consumed_chars) {
                vec.add(getType(signature.substring(index)));
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatError("Invalid method signature: " + signature);
        }
        final Type[] types = new Type[vec.size()];
        vec.toArray(types);
        return types;
    }
    
    static {
        VOID = new BasicType((byte)12);
        BOOLEAN = new BasicType((byte)4);
        INT = new BasicType((byte)10);
        SHORT = new BasicType((byte)9);
        BYTE = new BasicType((byte)8);
        LONG = new BasicType((byte)11);
        DOUBLE = new BasicType((byte)7);
        FLOAT = new BasicType((byte)6);
        CHAR = new BasicType((byte)5);
        OBJECT = new ObjectType("java.lang.Object");
        STRING = new ObjectType("java.lang.String");
        STRINGBUFFER = new ObjectType("java.lang.StringBuffer");
        THROWABLE = new ObjectType("java.lang.Throwable");
        NO_ARGS = new Type[0];
        NULL = new ReferenceType();
        UNKNOWN = new Type((byte)15, "<unknown object>") {};
        Type.consumed_chars = 0;
    }
}
