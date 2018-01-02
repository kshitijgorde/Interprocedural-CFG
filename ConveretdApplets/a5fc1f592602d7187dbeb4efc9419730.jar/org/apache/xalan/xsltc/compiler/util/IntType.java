// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.bcel.generic.IF_ICMPLE;
import org.apache.bcel.generic.IFLE;
import org.apache.bcel.generic.IF_ICMPLT;
import org.apache.bcel.generic.IFLT;
import org.apache.bcel.generic.IF_ICMPGE;
import org.apache.bcel.generic.IFGE;
import org.apache.bcel.generic.IF_ICMPGT;
import org.apache.bcel.generic.IFGT;
import org.apache.bcel.generic.ISTORE;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.NEW;
import org.apache.xalan.xsltc.compiler.FlowList;
import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.IFEQ;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionConstants;

public final class IntType extends NumberType
{
    static /* synthetic */ Class class$java$lang$Double;
    
    public String toString() {
        return "int";
    }
    
    public boolean identicalTo(final Type other) {
        return this == other;
    }
    
    public String toSignature() {
        return "I";
    }
    
    public org.apache.bcel.generic.Type toJCType() {
        return org.apache.bcel.generic.Type.INT;
    }
    
    public int distanceTo(final Type type) {
        if (type == this) {
            return 0;
        }
        if (type == Type.Real) {
            return 1;
        }
        return Integer.MAX_VALUE;
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Type type) {
        if (type == Type.Real) {
            this.translateTo(classGen, methodGen, (RealType)type);
        }
        else if (type == Type.String) {
            this.translateTo(classGen, methodGen, (StringType)type);
        }
        else if (type == Type.Boolean) {
            this.translateTo(classGen, methodGen, (BooleanType)type);
        }
        else if (type == Type.Reference) {
            this.translateTo(classGen, methodGen, (ReferenceType)type);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), type.toString());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final RealType type) {
        methodGen.getInstructionList().append(InstructionConstants.I2D);
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final StringType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new INVOKESTATIC(cpg.addMethodref("java.lang.Integer", "toString", "(I)Ljava/lang/String;")));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final BooleanType type) {
        final InstructionList il = methodGen.getInstructionList();
        final BranchHandle falsec = il.append(new IFEQ(null));
        il.append(InstructionConstants.ICONST_1);
        final BranchHandle truec = il.append(new GOTO(null));
        falsec.setTarget(il.append(InstructionConstants.ICONST_0));
        truec.setTarget(il.append(InstructionConstants.NOP));
    }
    
    public FlowList translateToDesynthesized(final ClassGenerator classGen, final MethodGenerator methodGen, final BooleanType type) {
        final InstructionList il = methodGen.getInstructionList();
        return new FlowList(il.append(new IFEQ(null)));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final ReferenceType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new NEW(cpg.addClass("java.lang.Integer")));
        il.append(InstructionConstants.DUP_X1);
        il.append(InstructionConstants.SWAP);
        il.append(new INVOKESPECIAL(cpg.addMethodref("java.lang.Integer", "<init>", "(I)V")));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        final InstructionList il = methodGen.getInstructionList();
        if (clazz == Character.TYPE) {
            il.append(InstructionConstants.I2C);
        }
        else if (clazz == Byte.TYPE) {
            il.append(InstructionConstants.I2B);
        }
        else if (clazz == Short.TYPE) {
            il.append(InstructionConstants.I2S);
        }
        else if (clazz == Integer.TYPE) {
            il.append(InstructionConstants.NOP);
        }
        else if (clazz == Long.TYPE) {
            il.append(InstructionConstants.I2L);
        }
        else if (clazz == Float.TYPE) {
            il.append(InstructionConstants.I2F);
        }
        else if (clazz == Double.TYPE) {
            il.append(InstructionConstants.I2D);
        }
        else if (clazz.isAssignableFrom((IntType.class$java$lang$Double == null) ? (IntType.class$java$lang$Double = class$("java.lang.Double")) : IntType.class$java$lang$Double)) {
            il.append(InstructionConstants.I2D);
            Type.Real.translateTo(classGen, methodGen, Type.Reference);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), clazz.getName());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateBox(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this.translateTo(classGen, methodGen, Type.Reference);
    }
    
    public void translateUnBox(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new CHECKCAST(cpg.addClass("java.lang.Integer")));
        final int index = cpg.addMethodref("java.lang.Integer", "intValue", "()I");
        il.append(new INVOKEVIRTUAL(index));
    }
    
    public Instruction ADD() {
        return InstructionConstants.IADD;
    }
    
    public Instruction SUB() {
        return InstructionConstants.ISUB;
    }
    
    public Instruction MUL() {
        return InstructionConstants.IMUL;
    }
    
    public Instruction DIV() {
        return InstructionConstants.IDIV;
    }
    
    public Instruction REM() {
        return InstructionConstants.IREM;
    }
    
    public Instruction NEG() {
        return InstructionConstants.INEG;
    }
    
    public Instruction LOAD(final int slot) {
        return new ILOAD(slot);
    }
    
    public Instruction STORE(final int slot) {
        return new ISTORE(slot);
    }
    
    public BranchInstruction GT(final boolean tozero) {
        return tozero ? new IFGT(null) : new IF_ICMPGT(null);
    }
    
    public BranchInstruction GE(final boolean tozero) {
        return tozero ? new IFGE(null) : new IF_ICMPGE(null);
    }
    
    public BranchInstruction LT(final boolean tozero) {
        return tozero ? new IFLT(null) : new IF_ICMPLT(null);
    }
    
    public BranchInstruction LE(final boolean tozero) {
        return tozero ? new IFLE(null) : new IF_ICMPLE(null);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
