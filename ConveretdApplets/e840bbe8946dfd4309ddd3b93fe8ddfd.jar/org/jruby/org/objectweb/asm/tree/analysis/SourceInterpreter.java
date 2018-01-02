// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.tree.analysis;

import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import org.jruby.org.objectweb.asm.tree.MethodInsnNode;
import java.util.List;
import org.jruby.org.objectweb.asm.tree.FieldInsnNode;
import org.jruby.org.objectweb.asm.tree.LdcInsnNode;
import org.jruby.org.objectweb.asm.tree.AbstractInsnNode;
import org.jruby.org.objectweb.asm.Type;
import org.jruby.org.objectweb.asm.Opcodes;

public class SourceInterpreter implements Opcodes, Interpreter
{
    public Value newValue(final Type type) {
        if (type == Type.VOID_TYPE) {
            return null;
        }
        return new SourceValue((type == null) ? 1 : type.getSize());
    }
    
    public Value newOperation(final AbstractInsnNode abstractInsnNode) {
        int size = 0;
        switch (abstractInsnNode.getOpcode()) {
            case 9:
            case 10:
            case 14:
            case 15: {
                size = 2;
                break;
            }
            case 18: {
                final Object cst = ((LdcInsnNode)abstractInsnNode).cst;
                size = ((cst instanceof Long || cst instanceof Double) ? 2 : 1);
                break;
            }
            case 178: {
                size = Type.getType(((FieldInsnNode)abstractInsnNode).desc).getSize();
                break;
            }
            default: {
                size = 1;
                break;
            }
        }
        return new SourceValue(size, abstractInsnNode);
    }
    
    public Value copyOperation(final AbstractInsnNode abstractInsnNode, final Value value) {
        return new SourceValue(value.getSize(), abstractInsnNode);
    }
    
    public Value unaryOperation(final AbstractInsnNode abstractInsnNode, final Value value) {
        int size = 0;
        switch (abstractInsnNode.getOpcode()) {
            case 117:
            case 119:
            case 133:
            case 135:
            case 138:
            case 140:
            case 141:
            case 143: {
                size = 2;
                break;
            }
            case 180: {
                size = Type.getType(((FieldInsnNode)abstractInsnNode).desc).getSize();
                break;
            }
            default: {
                size = 1;
                break;
            }
        }
        return new SourceValue(size, abstractInsnNode);
    }
    
    public Value binaryOperation(final AbstractInsnNode abstractInsnNode, final Value value, final Value value2) {
        int n = 0;
        switch (abstractInsnNode.getOpcode()) {
            case 47:
            case 49:
            case 97:
            case 99:
            case 101:
            case 103:
            case 105:
            case 107:
            case 109:
            case 111:
            case 113:
            case 115:
            case 121:
            case 123:
            case 125:
            case 127:
            case 129:
            case 131: {
                n = 2;
                break;
            }
            default: {
                n = 1;
                break;
            }
        }
        return new SourceValue(n, abstractInsnNode);
    }
    
    public Value ternaryOperation(final AbstractInsnNode abstractInsnNode, final Value value, final Value value2, final Value value3) {
        return new SourceValue(1, abstractInsnNode);
    }
    
    public Value naryOperation(final AbstractInsnNode abstractInsnNode, final List list) {
        int size;
        if (abstractInsnNode.getOpcode() == 197) {
            size = 1;
        }
        else {
            size = Type.getReturnType(((MethodInsnNode)abstractInsnNode).desc).getSize();
        }
        return new SourceValue(size, abstractInsnNode);
    }
    
    public void returnOperation(final AbstractInsnNode abstractInsnNode, final Value value, final Value value2) {
    }
    
    public Value merge(final Value value, final Value value2) {
        final SourceValue sourceValue = (SourceValue)value;
        final SourceValue sourceValue2 = (SourceValue)value2;
        if (sourceValue.insns instanceof SmallSet && sourceValue2.insns instanceof SmallSet) {
            final Set union = ((SmallSet)sourceValue.insns).union((SmallSet)sourceValue2.insns);
            if (union == sourceValue.insns && sourceValue.size == sourceValue2.size) {
                return value;
            }
            return new SourceValue(Math.min(sourceValue.size, sourceValue2.size), union);
        }
        else {
            if (sourceValue.size != sourceValue2.size || !sourceValue.insns.containsAll(sourceValue2.insns)) {
                final HashSet set = new HashSet();
                set.addAll(sourceValue.insns);
                set.addAll(sourceValue2.insns);
                return new SourceValue(Math.min(sourceValue.size, sourceValue2.size), set);
            }
            return value;
        }
    }
}
