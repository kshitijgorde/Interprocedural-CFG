// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.Constants;

public final class BasicType extends Type
{
    BasicType(final byte type) {
        super(type, Constants.SHORT_TYPE_NAMES[type]);
        if (type < 4 || type > 12) {
            throw new ClassGenException("Invalid type: " + type);
        }
    }
    
    public static final BasicType getType(final byte type) {
        switch (type) {
            case 12: {
                return Type.VOID;
            }
            case 4: {
                return Type.BOOLEAN;
            }
            case 8: {
                return Type.BYTE;
            }
            case 9: {
                return Type.SHORT;
            }
            case 5: {
                return Type.CHAR;
            }
            case 10: {
                return Type.INT;
            }
            case 11: {
                return Type.LONG;
            }
            case 7: {
                return Type.DOUBLE;
            }
            case 6: {
                return Type.FLOAT;
            }
            default: {
                throw new ClassGenException("Invalid type: " + type);
            }
        }
    }
    
    public boolean equals(final Object type) {
        return type instanceof BasicType && ((BasicType)type).type == super.type;
    }
}
