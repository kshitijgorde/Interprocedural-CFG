// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import com.ibm.xslt4j.bcel.generic.ISTORE;
import com.ibm.xslt4j.bcel.generic.ILOAD;
import com.ibm.xslt4j.bcel.generic.GETFIELD;
import com.ibm.xslt4j.bcel.generic.CHECKCAST;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import com.ibm.xslt4j.bcel.generic.IFEQ;
import com.ibm.xslt4j.bcel.generic.INVOKESPECIAL;
import com.ibm.xslt4j.bcel.generic.NEW;
import com.ibm.xslt4j.bcel.generic.BranchHandle;
import org.apache.xalan.xsltc.compiler.FlowList;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.GOTO;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;

public final class NodeType extends Type
{
    private final int _type;
    
    protected NodeType() {
        this(-1);
    }
    
    protected NodeType(final int type) {
        this._type = type;
    }
    
    public int getType() {
        return this._type;
    }
    
    public String toString() {
        return "node-type";
    }
    
    public boolean identicalTo(final Type other) {
        return other instanceof NodeType;
    }
    
    public int hashCode() {
        return this._type;
    }
    
    public String toSignature() {
        return "I";
    }
    
    public com.ibm.xslt4j.bcel.generic.Type toJCType() {
        return com.ibm.xslt4j.bcel.generic.Type.INT;
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Type type) {
        if (type == Type.String) {
            this.translateTo(classGen, methodGen, (StringType)type);
        }
        else if (type == Type.Boolean) {
            this.translateTo(classGen, methodGen, (BooleanType)type);
        }
        else if (type == Type.Real) {
            this.translateTo(classGen, methodGen, (RealType)type);
        }
        else if (type == Type.NodeSet) {
            this.translateTo(classGen, methodGen, (NodeSetType)type);
        }
        else if (type == Type.Reference) {
            this.translateTo(classGen, methodGen, (ReferenceType)type);
        }
        else if (type == Type.Object) {
            this.translateTo(classGen, methodGen, (ObjectType)type);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), type.toString());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final StringType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        switch (this._type) {
            case 1:
            case 9: {
                il.append(methodGen.loadDOM());
                il.append(InstructionConstants.SWAP);
                final int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getElementValue", "(I)Ljava/lang/String;");
                il.append(new INVOKEINTERFACE(index, 2));
                break;
            }
            case -1:
            case 2:
            case 7:
            case 8: {
                il.append(methodGen.loadDOM());
                il.append(InstructionConstants.SWAP);
                final int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getStringValueX", "(I)Ljava/lang/String;");
                il.append(new INVOKEINTERFACE(index, 2));
                break;
            }
            default: {
                final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), type.toString());
                classGen.getParser().reportError(2, err);
                break;
            }
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
        this.translateTo(classGen, methodGen, Type.String);
        Type.String.translateTo(classGen, methodGen, Type.Real);
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final NodeSetType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.SingletonIterator")));
        il.append(InstructionConstants.DUP_X1);
        il.append(InstructionConstants.SWAP);
        final int init = cpg.addMethodref("org.apache.xalan.xsltc.dom.SingletonIterator", "<init>", "(I)V");
        il.append(new INVOKESPECIAL(init));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final ObjectType type) {
        methodGen.getInstructionList().append(InstructionConstants.NOP);
    }
    
    public FlowList translateToDesynthesized(final ClassGenerator classGen, final MethodGenerator methodGen, final BooleanType type) {
        final InstructionList il = methodGen.getInstructionList();
        return new FlowList(il.append(new IFEQ(null)));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final ReferenceType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.runtime.Node")));
        il.append(InstructionConstants.DUP_X1);
        il.append(InstructionConstants.SWAP);
        il.append(new PUSH(cpg, this._type));
        il.append(new INVOKESPECIAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.Node", "<init>", "(II)V")));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final String className = clazz.getName();
        if (className.equals("java.lang.String")) {
            this.translateTo(classGen, methodGen, Type.String);
            return;
        }
        il.append(methodGen.loadDOM());
        il.append(InstructionConstants.SWAP);
        if (className.equals("org.w3c.dom.Node") || className.equals("java.lang.Object")) {
            final int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "makeNode", "(I)Lorg/w3c/dom/Node;");
            il.append(new INVOKEINTERFACE(index, 2));
        }
        else if (className.equals("org.w3c.dom.NodeList")) {
            final int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "makeNodeList", "(I)Lorg/w3c/dom/NodeList;");
            il.append(new INVOKEINTERFACE(index, 2));
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), className);
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateBox(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this.translateTo(classGen, methodGen, Type.Reference);
    }
    
    public void translateUnBox(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new CHECKCAST(cpg.addClass("org.apache.xalan.xsltc.runtime.Node")));
        il.append(new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.Node", "node", "I")));
    }
    
    public String getClassName() {
        return "org.apache.xalan.xsltc.runtime.Node";
    }
    
    public Instruction LOAD(final int slot) {
        return new ILOAD(slot);
    }
    
    public Instruction STORE(final int slot) {
        return new ISTORE(slot);
    }
}
