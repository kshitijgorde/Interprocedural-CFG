// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.IFNONNULL;
import org.apache.bcel.generic.IFEQ;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.BranchHandle;
import org.apache.xalan.xsltc.compiler.FlowList;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.InstructionConstants;

public class StringType extends Type
{
    static /* synthetic */ Class class$java$lang$String;
    
    public String toString() {
        return "string";
    }
    
    public boolean identicalTo(final Type other) {
        return this == other;
    }
    
    public String toSignature() {
        return "Ljava/lang/String;";
    }
    
    public boolean isSimple() {
        return true;
    }
    
    public org.apache.bcel.generic.Type toJCType() {
        return org.apache.bcel.generic.Type.STRING;
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Type type) {
        if (type == Type.Boolean) {
            this.translateTo(classGen, methodGen, (BooleanType)type);
        }
        else if (type == Type.Real) {
            this.translateTo(classGen, methodGen, (RealType)type);
        }
        else if (type == Type.Reference) {
            this.translateTo(classGen, methodGen, (ReferenceType)type);
        }
        else if (type != Type.ObjectString) {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), type.toString());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final BooleanType type) {
        final InstructionList il = methodGen.getInstructionList();
        final FlowList falsel = this.translateToDesynthesized(classGen, methodGen, type);
        il.append(InstructionConstants.ICONST_1);
        final BranchHandle truec = il.append(new GOTO(null));
        falsel.backPatch(il.append(InstructionConstants.ICONST_0));
        truec.setTarget(il.append(InstructionConstants.NOP));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final RealType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new INVOKESTATIC(cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "stringToReal", "(Ljava/lang/String;)D")));
    }
    
    public FlowList translateToDesynthesized(final ClassGenerator classGen, final MethodGenerator methodGen, final BooleanType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new INVOKEVIRTUAL(cpg.addMethodref("java.lang.String", "length", "()I")));
        return new FlowList(il.append(new IFEQ(null)));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final ReferenceType type) {
        methodGen.getInstructionList().append(InstructionConstants.NOP);
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        if (clazz.isAssignableFrom((StringType.class$java$lang$String == null) ? (StringType.class$java$lang$String = class$("java.lang.String")) : StringType.class$java$lang$String)) {
            methodGen.getInstructionList().append(InstructionConstants.NOP);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), clazz.getName());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateFrom(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (clazz.getName().equals("java.lang.String")) {
            il.append(InstructionConstants.DUP);
            final BranchHandle ifNonNull = il.append(new IFNONNULL(null));
            il.append(InstructionConstants.POP);
            il.append(new PUSH(cpg, ""));
            ifNonNull.setTarget(il.append(InstructionConstants.NOP));
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
        methodGen.getInstructionList().append(InstructionConstants.NOP);
    }
    
    public String getClassName() {
        return "java.lang.String";
    }
    
    public Instruction LOAD(final int slot) {
        return new ALOAD(slot);
    }
    
    public Instruction STORE(final int slot) {
        return new ASTORE(slot);
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
