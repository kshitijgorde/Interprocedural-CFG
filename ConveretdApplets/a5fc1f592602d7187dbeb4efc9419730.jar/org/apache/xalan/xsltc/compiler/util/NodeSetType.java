// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.IFLT;
import org.apache.bcel.generic.BranchHandle;
import org.apache.xalan.xsltc.compiler.FlowList;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.ObjectType;

public final class NodeSetType extends Type
{
    public String toString() {
        return "node-set";
    }
    
    public boolean identicalTo(final Type other) {
        return this == other;
    }
    
    public String toSignature() {
        return "Lorg/apache/xml/dtm/DTMAxisIterator;";
    }
    
    public org.apache.bcel.generic.Type toJCType() {
        return new ObjectType("org.apache.xml.dtm.DTMAxisIterator");
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
        else if (type == Type.Node) {
            this.translateTo(classGen, methodGen, (NodeType)type);
        }
        else if (type == Type.Reference) {
            this.translateTo(classGen, methodGen, (ReferenceType)type);
        }
        else if (type == Type.Object) {
            this.translateTo(classGen, methodGen, (org.apache.xalan.xsltc.compiler.util.ObjectType)type);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), type.toString());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateFrom(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        final InstructionList il = methodGen.getInstructionList();
        final ConstantPoolGen cpg = classGen.getConstantPool();
        if (clazz.getName().equals("org.w3c.dom.NodeList")) {
            il.append(classGen.loadTranslet());
            il.append(methodGen.loadDOM());
            final int convert = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "nodeList2Iterator", "(Lorg/w3c/dom/NodeList;Lorg/apache/xalan/xsltc/Translet;Lorg/apache/xalan/xsltc/DOM;)Lorg/apache/xml/dtm/DTMAxisIterator;");
            il.append(new INVOKESTATIC(convert));
        }
        else if (clazz.getName().equals("org.w3c.dom.Node")) {
            il.append(classGen.loadTranslet());
            il.append(methodGen.loadDOM());
            final int convert = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "node2Iterator", "(Lorg/w3c/dom/Node;Lorg/apache/xalan/xsltc/Translet;Lorg/apache/xalan/xsltc/DOM;)Lorg/apache/xml/dtm/DTMAxisIterator;");
            il.append(new INVOKESTATIC(convert));
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), clazz.getName());
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
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final StringType type) {
        final InstructionList il = methodGen.getInstructionList();
        this.getFirstNode(classGen, methodGen);
        il.append(InstructionConstants.DUP);
        final BranchHandle falsec = il.append(new IFLT(null));
        Type.Node.translateTo(classGen, methodGen, type);
        final BranchHandle truec = il.append(new GOTO(null));
        falsec.setTarget(il.append(InstructionConstants.POP));
        il.append(new PUSH(classGen.getConstantPool(), ""));
        truec.setTarget(il.append(InstructionConstants.NOP));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final RealType type) {
        this.translateTo(classGen, methodGen, Type.String);
        Type.String.translateTo(classGen, methodGen, Type.Real);
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final NodeType type) {
        this.getFirstNode(classGen, methodGen);
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final org.apache.xalan.xsltc.compiler.util.ObjectType type) {
        methodGen.getInstructionList().append(InstructionConstants.NOP);
    }
    
    public FlowList translateToDesynthesized(final ClassGenerator classGen, final MethodGenerator methodGen, final BooleanType type) {
        final InstructionList il = methodGen.getInstructionList();
        this.getFirstNode(classGen, methodGen);
        return new FlowList(il.append(new IFLT(null)));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final ReferenceType type) {
        methodGen.getInstructionList().append(InstructionConstants.NOP);
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final String className = clazz.getName();
        il.append(methodGen.loadDOM());
        il.append(InstructionConstants.SWAP);
        if (className.equals("org.w3c.dom.Node")) {
            final int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "makeNode", "(Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/w3c/dom/Node;");
            il.append(new INVOKEINTERFACE(index, 2));
        }
        else if (className.equals("org.w3c.dom.NodeList") || className.equals("java.lang.Object")) {
            final int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "makeNodeList", "(Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/w3c/dom/NodeList;");
            il.append(new INVOKEINTERFACE(index, 2));
        }
        else if (className.equals("java.lang.String")) {
            final int next = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "next", "()I");
            final int index2 = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getStringValueX", "(I)Ljava/lang/String;");
            il.append(new INVOKEINTERFACE(next, 1));
            il.append(new INVOKEINTERFACE(index2, 2));
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), className);
            classGen.getParser().reportError(2, err);
        }
    }
    
    private void getFirstNode(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new INVOKEINTERFACE(cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "next", "()I"), 1));
    }
    
    public void translateBox(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this.translateTo(classGen, methodGen, Type.Reference);
    }
    
    public void translateUnBox(final ClassGenerator classGen, final MethodGenerator methodGen) {
        methodGen.getInstructionList().append(InstructionConstants.NOP);
    }
    
    public String getClassName() {
        return "org.apache.xml.dtm.DTMAxisIterator";
    }
    
    public Instruction LOAD(final int slot) {
        return new ALOAD(slot);
    }
    
    public Instruction STORE(final int slot) {
        return new ASTORE(slot);
    }
}
