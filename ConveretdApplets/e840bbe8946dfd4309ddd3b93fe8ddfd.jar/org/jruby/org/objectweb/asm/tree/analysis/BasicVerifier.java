// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.tree.analysis;

import org.jruby.org.objectweb.asm.tree.MethodInsnNode;
import java.util.List;
import org.jruby.org.objectweb.asm.Type;
import org.jruby.org.objectweb.asm.tree.FieldInsnNode;
import org.jruby.org.objectweb.asm.tree.AbstractInsnNode;

public class BasicVerifier extends BasicInterpreter
{
    public Value copyOperation(final AbstractInsnNode abstractInsnNode, final Value value) throws AnalyzerException {
        Value value2 = null;
        switch (abstractInsnNode.getOpcode()) {
            case 21:
            case 54: {
                value2 = BasicValue.INT_VALUE;
                break;
            }
            case 23:
            case 56: {
                value2 = BasicValue.FLOAT_VALUE;
                break;
            }
            case 22:
            case 55: {
                value2 = BasicValue.LONG_VALUE;
                break;
            }
            case 24:
            case 57: {
                value2 = BasicValue.DOUBLE_VALUE;
                break;
            }
            case 25: {
                if (!((BasicValue)value).isReference()) {
                    throw new AnalyzerException(abstractInsnNode, null, "an object reference", value);
                }
                return value;
            }
            case 58: {
                if (!((BasicValue)value).isReference() && !BasicValue.RETURNADDRESS_VALUE.equals(value)) {
                    throw new AnalyzerException(abstractInsnNode, null, "an object reference or a return address", value);
                }
                return value;
            }
            default: {
                return value;
            }
        }
        if (!value2.equals(value)) {
            throw new AnalyzerException(abstractInsnNode, null, value2, value);
        }
        return value;
    }
    
    public Value unaryOperation(final AbstractInsnNode abstractInsnNode, final Value value) throws AnalyzerException {
        Value value2 = null;
        switch (abstractInsnNode.getOpcode()) {
            case 116:
            case 132:
            case 133:
            case 134:
            case 135:
            case 145:
            case 146:
            case 147:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 170:
            case 171:
            case 172:
            case 188:
            case 189: {
                value2 = BasicValue.INT_VALUE;
                break;
            }
            case 118:
            case 139:
            case 140:
            case 141:
            case 174: {
                value2 = BasicValue.FLOAT_VALUE;
                break;
            }
            case 117:
            case 136:
            case 137:
            case 138:
            case 173: {
                value2 = BasicValue.LONG_VALUE;
                break;
            }
            case 119:
            case 142:
            case 143:
            case 144:
            case 175: {
                value2 = BasicValue.DOUBLE_VALUE;
                break;
            }
            case 180: {
                value2 = this.newValue(Type.getObjectType(((FieldInsnNode)abstractInsnNode).owner));
                break;
            }
            case 192: {
                if (!((BasicValue)value).isReference()) {
                    throw new AnalyzerException(abstractInsnNode, null, "an object reference", value);
                }
                return super.unaryOperation(abstractInsnNode, value);
            }
            case 190: {
                if (!this.isArrayValue(value)) {
                    throw new AnalyzerException(abstractInsnNode, null, "an array reference", value);
                }
                return super.unaryOperation(abstractInsnNode, value);
            }
            case 176:
            case 191:
            case 193:
            case 194:
            case 195:
            case 198:
            case 199: {
                if (!((BasicValue)value).isReference()) {
                    throw new AnalyzerException(abstractInsnNode, null, "an object reference", value);
                }
                return super.unaryOperation(abstractInsnNode, value);
            }
            case 179: {
                value2 = this.newValue(Type.getType(((FieldInsnNode)abstractInsnNode).desc));
                break;
            }
            default: {
                throw new Error("Internal error.");
            }
        }
        if (!this.isSubTypeOf(value, value2)) {
            throw new AnalyzerException(abstractInsnNode, null, value2, value);
        }
        return super.unaryOperation(abstractInsnNode, value);
    }
    
    public Value binaryOperation(final AbstractInsnNode abstractInsnNode, final Value value, final Value value2) throws AnalyzerException {
        Value value3 = null;
        Value value4 = null;
        switch (abstractInsnNode.getOpcode()) {
            case 46: {
                value3 = this.newValue(Type.getType("[I"));
                value4 = BasicValue.INT_VALUE;
                break;
            }
            case 51: {
                if (this.isSubTypeOf(value, this.newValue(Type.getType("[Z")))) {
                    value3 = this.newValue(Type.getType("[Z"));
                }
                else {
                    value3 = this.newValue(Type.getType("[B"));
                }
                value4 = BasicValue.INT_VALUE;
                break;
            }
            case 52: {
                value3 = this.newValue(Type.getType("[C"));
                value4 = BasicValue.INT_VALUE;
                break;
            }
            case 53: {
                value3 = this.newValue(Type.getType("[S"));
                value4 = BasicValue.INT_VALUE;
                break;
            }
            case 47: {
                value3 = this.newValue(Type.getType("[J"));
                value4 = BasicValue.INT_VALUE;
                break;
            }
            case 48: {
                value3 = this.newValue(Type.getType("[F"));
                value4 = BasicValue.INT_VALUE;
                break;
            }
            case 49: {
                value3 = this.newValue(Type.getType("[D"));
                value4 = BasicValue.INT_VALUE;
                break;
            }
            case 50: {
                value3 = this.newValue(Type.getType("[Ljava/lang/Object;"));
                value4 = BasicValue.INT_VALUE;
                break;
            }
            case 96:
            case 100:
            case 104:
            case 108:
            case 112:
            case 120:
            case 122:
            case 124:
            case 126:
            case 128:
            case 130:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164: {
                value3 = BasicValue.INT_VALUE;
                value4 = BasicValue.INT_VALUE;
                break;
            }
            case 98:
            case 102:
            case 106:
            case 110:
            case 114:
            case 149:
            case 150: {
                value3 = BasicValue.FLOAT_VALUE;
                value4 = BasicValue.FLOAT_VALUE;
                break;
            }
            case 97:
            case 101:
            case 105:
            case 109:
            case 113:
            case 127:
            case 129:
            case 131:
            case 148: {
                value3 = BasicValue.LONG_VALUE;
                value4 = BasicValue.LONG_VALUE;
                break;
            }
            case 121:
            case 123:
            case 125: {
                value3 = BasicValue.LONG_VALUE;
                value4 = BasicValue.INT_VALUE;
                break;
            }
            case 99:
            case 103:
            case 107:
            case 111:
            case 115:
            case 151:
            case 152: {
                value3 = BasicValue.DOUBLE_VALUE;
                value4 = BasicValue.DOUBLE_VALUE;
                break;
            }
            case 165:
            case 166: {
                value3 = BasicValue.REFERENCE_VALUE;
                value4 = BasicValue.REFERENCE_VALUE;
                break;
            }
            case 181: {
                final FieldInsnNode fieldInsnNode = (FieldInsnNode)abstractInsnNode;
                value3 = this.newValue(Type.getObjectType(fieldInsnNode.owner));
                value4 = this.newValue(Type.getType(fieldInsnNode.desc));
                break;
            }
            default: {
                throw new Error("Internal error.");
            }
        }
        if (!this.isSubTypeOf(value, value3)) {
            throw new AnalyzerException(abstractInsnNode, "First argument", value3, value);
        }
        if (!this.isSubTypeOf(value2, value4)) {
            throw new AnalyzerException(abstractInsnNode, "Second argument", value4, value2);
        }
        if (abstractInsnNode.getOpcode() == 50) {
            return this.getElementValue(value);
        }
        return super.binaryOperation(abstractInsnNode, value, value2);
    }
    
    public Value ternaryOperation(final AbstractInsnNode abstractInsnNode, final Value value, final Value value2, final Value value3) throws AnalyzerException {
        Value value4 = null;
        Value value5 = null;
        switch (abstractInsnNode.getOpcode()) {
            case 79: {
                value4 = this.newValue(Type.getType("[I"));
                value5 = BasicValue.INT_VALUE;
                break;
            }
            case 84: {
                if (this.isSubTypeOf(value, this.newValue(Type.getType("[Z")))) {
                    value4 = this.newValue(Type.getType("[Z"));
                }
                else {
                    value4 = this.newValue(Type.getType("[B"));
                }
                value5 = BasicValue.INT_VALUE;
                break;
            }
            case 85: {
                value4 = this.newValue(Type.getType("[C"));
                value5 = BasicValue.INT_VALUE;
                break;
            }
            case 86: {
                value4 = this.newValue(Type.getType("[S"));
                value5 = BasicValue.INT_VALUE;
                break;
            }
            case 80: {
                value4 = this.newValue(Type.getType("[J"));
                value5 = BasicValue.LONG_VALUE;
                break;
            }
            case 81: {
                value4 = this.newValue(Type.getType("[F"));
                value5 = BasicValue.FLOAT_VALUE;
                break;
            }
            case 82: {
                value4 = this.newValue(Type.getType("[D"));
                value5 = BasicValue.DOUBLE_VALUE;
                break;
            }
            case 83: {
                value4 = value;
                value5 = BasicValue.REFERENCE_VALUE;
                break;
            }
            default: {
                throw new Error("Internal error.");
            }
        }
        if (!this.isSubTypeOf(value, value4)) {
            throw new AnalyzerException(abstractInsnNode, "First argument", "a " + value4 + " array reference", value);
        }
        if (!BasicValue.INT_VALUE.equals(value2)) {
            throw new AnalyzerException(abstractInsnNode, "Second argument", BasicValue.INT_VALUE, value2);
        }
        if (!this.isSubTypeOf(value3, value5)) {
            throw new AnalyzerException(abstractInsnNode, "Third argument", value5, value3);
        }
        return null;
    }
    
    public Value naryOperation(final AbstractInsnNode abstractInsnNode, final List list) throws AnalyzerException {
        final int opcode = abstractInsnNode.getOpcode();
        if (opcode == 197) {
            for (int i = 0; i < list.size(); ++i) {
                if (!BasicValue.INT_VALUE.equals(list.get(i))) {
                    throw new AnalyzerException(abstractInsnNode, null, BasicValue.INT_VALUE, list.get(i));
                }
            }
        }
        else {
            int j = 0;
            int n = 0;
            if (opcode != 184 && opcode != 186) {
                final Type objectType = Type.getObjectType(((MethodInsnNode)abstractInsnNode).owner);
                if (!this.isSubTypeOf(list.get(j++), this.newValue(objectType))) {
                    throw new AnalyzerException(abstractInsnNode, "Method owner", this.newValue(objectType), list.get(0));
                }
            }
            final Type[] argumentTypes = Type.getArgumentTypes(((MethodInsnNode)abstractInsnNode).desc);
            while (j < list.size()) {
                final Value value = this.newValue(argumentTypes[n++]);
                final Value value2 = list.get(j++);
                if (!this.isSubTypeOf(value2, value)) {
                    throw new AnalyzerException(abstractInsnNode, "Argument " + n, value, value2);
                }
            }
        }
        return super.naryOperation(abstractInsnNode, list);
    }
    
    public void returnOperation(final AbstractInsnNode abstractInsnNode, final Value value, final Value value2) throws AnalyzerException {
        if (!this.isSubTypeOf(value, value2)) {
            throw new AnalyzerException(abstractInsnNode, "Incompatible return type", value2, value);
        }
    }
    
    protected boolean isArrayValue(final Value value) {
        return ((BasicValue)value).isReference();
    }
    
    protected Value getElementValue(final Value value) throws AnalyzerException {
        return BasicValue.REFERENCE_VALUE;
    }
    
    protected boolean isSubTypeOf(final Value value, final Value value2) {
        return value.equals(value2);
    }
}
