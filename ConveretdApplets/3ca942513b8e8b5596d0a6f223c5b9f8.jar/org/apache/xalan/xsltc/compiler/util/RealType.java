// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.CHECKCAST;
import com.ibm.xslt4j.bcel.generic.INVOKESPECIAL;
import com.ibm.xslt4j.bcel.generic.NEW;
import com.ibm.xslt4j.bcel.generic.LocalVariableGen;
import com.ibm.xslt4j.bcel.generic.IFNE;
import com.ibm.xslt4j.bcel.generic.DLOAD;
import com.ibm.xslt4j.bcel.generic.IFEQ;
import com.ibm.xslt4j.bcel.generic.DSTORE;
import com.ibm.xslt4j.bcel.generic.BranchHandle;
import org.apache.xalan.xsltc.compiler.FlowList;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.GOTO;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;

public final class RealType extends NumberType
{
    static /* synthetic */ Class class$java$lang$Double;
    
    public String toString() {
        return "real";
    }
    
    public boolean identicalTo(final Type other) {
        return this == other;
    }
    
    public String toSignature() {
        return "D";
    }
    
    public com.ibm.xslt4j.bcel.generic.Type toJCType() {
        return com.ibm.xslt4j.bcel.generic.Type.DOUBLE;
    }
    
    public int distanceTo(final Type type) {
        if (type == this) {
            return 0;
        }
        if (type == Type.Int) {
            return 1;
        }
        return Integer.MAX_VALUE;
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Type type) {
        if (type == Type.String) {
            this.translateTo(classGen, methodGen, (StringType)type);
        }
        else if (type == Type.Boolean) {
            this.translateTo(classGen, methodGen, (BooleanType)type);
        }
        else if (type == Type.Reference) {
            this.translateTo(classGen, methodGen, (ReferenceType)type);
        }
        else if (type == Type.Int) {
            this.translateTo(classGen, methodGen, (IntType)type);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), type.toString());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final StringType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new INVOKESTATIC(cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "realToString", "(D)Ljava/lang/String;")));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final BooleanType type) {
        final InstructionList il = methodGen.getInstructionList();
        final FlowList falsel = this.translateToDesynthesized(classGen, methodGen, type);
        il.append(InstructionConstants.ICONST_1);
        final BranchHandle truec = il.append(new GOTO(null));
        falsel.backPatch(il.append(InstructionConstants.ICONST_0));
        truec.setTarget(il.append(InstructionConstants.NOP));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final IntType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new INVOKESTATIC(cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "realToInt", "(D)I")));
    }
    
    public FlowList translateToDesynthesized(final ClassGenerator classGen, final MethodGenerator methodGen, final BooleanType type) {
        final FlowList flowlist = new FlowList();
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(InstructionConstants.DUP2);
        final LocalVariableGen local = methodGen.addLocalVariable("real_to_boolean_tmp", com.ibm.xslt4j.bcel.generic.Type.DOUBLE, il.getEnd(), null);
        il.append(new DSTORE(local.getIndex()));
        il.append(InstructionConstants.DCONST_0);
        il.append(InstructionConstants.DCMPG);
        flowlist.add(il.append(new IFEQ(null)));
        il.append(new DLOAD(local.getIndex()));
        il.append(new DLOAD(local.getIndex()));
        il.append(InstructionConstants.DCMPG);
        flowlist.add(il.append(new IFNE(null)));
        return flowlist;
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final ReferenceType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new NEW(cpg.addClass("java.lang.Double")));
        il.append(InstructionConstants.DUP_X2);
        il.append(InstructionConstants.DUP_X2);
        il.append(InstructionConstants.POP);
        il.append(new INVOKESPECIAL(cpg.addMethodref("java.lang.Double", "<init>", "(D)V")));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        final InstructionList il = methodGen.getInstructionList();
        if (clazz == Character.TYPE) {
            il.append(InstructionConstants.D2I);
            il.append(InstructionConstants.I2C);
        }
        else if (clazz == Byte.TYPE) {
            il.append(InstructionConstants.D2I);
            il.append(InstructionConstants.I2B);
        }
        else if (clazz == Short.TYPE) {
            il.append(InstructionConstants.D2I);
            il.append(InstructionConstants.I2S);
        }
        else if (clazz == Integer.TYPE) {
            il.append(InstructionConstants.D2I);
        }
        else if (clazz == Long.TYPE) {
            il.append(InstructionConstants.D2L);
        }
        else if (clazz == Float.TYPE) {
            il.append(InstructionConstants.D2F);
        }
        else if (clazz == Double.TYPE) {
            il.append(InstructionConstants.NOP);
        }
        else if (clazz.isAssignableFrom((RealType.class$java$lang$Double == null) ? (RealType.class$java$lang$Double = class$("java.lang.Double")) : RealType.class$java$lang$Double)) {
            this.translateTo(classGen, methodGen, Type.Reference);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), clazz.getName());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateFrom(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        final InstructionList il = methodGen.getInstructionList();
        if (clazz == Character.TYPE || clazz == Byte.TYPE || clazz == Short.TYPE || clazz == Integer.TYPE) {
            il.append(InstructionConstants.I2D);
        }
        else if (clazz == Long.TYPE) {
            il.append(InstructionConstants.L2D);
        }
        else if (clazz == Float.TYPE) {
            il.append(InstructionConstants.F2D);
        }
        else if (clazz == Double.TYPE) {
            il.append(InstructionConstants.NOP);
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
        il.append(new CHECKCAST(cpg.addClass("java.lang.Double")));
        il.append(new INVOKEVIRTUAL(cpg.addMethodref("java.lang.Double", "doubleValue", "()D")));
    }
    
    public Instruction ADD() {
        return InstructionConstants.DADD;
    }
    
    public Instruction SUB() {
        return InstructionConstants.DSUB;
    }
    
    public Instruction MUL() {
        return InstructionConstants.DMUL;
    }
    
    public Instruction DIV() {
        return InstructionConstants.DDIV;
    }
    
    public Instruction REM() {
        return InstructionConstants.DREM;
    }
    
    public Instruction NEG() {
        return InstructionConstants.DNEG;
    }
    
    public Instruction LOAD(final int slot) {
        return new DLOAD(slot);
    }
    
    public Instruction STORE(final int slot) {
        return new DSTORE(slot);
    }
    
    public Instruction POP() {
        return InstructionConstants.POP2;
    }
    
    public Instruction CMP(final boolean less) {
        return less ? InstructionConstants.DCMPG : InstructionConstants.DCMPL;
    }
    
    public Instruction DUP() {
        return InstructionConstants.DUP2;
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
