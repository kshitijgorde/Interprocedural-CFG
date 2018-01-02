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
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.IFEQ;

public final class BooleanType extends Type
{
    static /* synthetic */ Class class$java$lang$Boolean;
    
    public String toString() {
        return "boolean";
    }
    
    public boolean identicalTo(final Type other) {
        return this == other;
    }
    
    public String toSignature() {
        return "Z";
    }
    
    public boolean isSimple() {
        return true;
    }
    
    public org.apache.bcel.generic.Type toJCType() {
        return org.apache.bcel.generic.Type.BOOLEAN;
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Type type) {
        if (type == Type.String) {
            this.translateTo(classGen, methodGen, (StringType)type);
        }
        else if (type == Type.Real) {
            this.translateTo(classGen, methodGen, (RealType)type);
        }
        else if (type == Type.Reference) {
            this.translateTo(classGen, methodGen, (ReferenceType)type);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), type.toString());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final StringType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final BranchHandle falsec = il.append(new IFEQ(null));
        il.append(new PUSH(cpg, "true"));
        final BranchHandle truec = il.append(new GOTO(null));
        falsec.setTarget(il.append(new PUSH(cpg, "false")));
        truec.setTarget(il.append(InstructionConstants.NOP));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final RealType type) {
        methodGen.getInstructionList().append(InstructionConstants.I2D);
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final ReferenceType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new NEW(cpg.addClass("java.lang.Boolean")));
        il.append(InstructionConstants.DUP_X1);
        il.append(InstructionConstants.SWAP);
        il.append(new INVOKESPECIAL(cpg.addMethodref("java.lang.Boolean", "<init>", "(Z)V")));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        if (clazz == java.lang.Boolean.TYPE) {
            methodGen.getInstructionList().append(InstructionConstants.NOP);
        }
        else if (clazz.isAssignableFrom((BooleanType.class$java$lang$Boolean == null) ? (BooleanType.class$java$lang$Boolean = class$("java.lang.Boolean")) : BooleanType.class$java$lang$Boolean)) {
            this.translateTo(classGen, methodGen, Type.Reference);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), clazz.getName());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateFrom(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        this.translateTo(classGen, methodGen, clazz);
    }
    
    public void translateBox(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this.translateTo(classGen, methodGen, Type.Reference);
    }
    
    public void translateUnBox(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new CHECKCAST(cpg.addClass("java.lang.Boolean")));
        il.append(new INVOKEVIRTUAL(cpg.addMethodref("java.lang.Boolean", "booleanValue", "()Z")));
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
