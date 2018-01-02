// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import com.ibm.xslt4j.bcel.generic.ASTORE;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.IFEQ;
import org.apache.xalan.xsltc.compiler.FlowList;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.ILOAD;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;

public final class ReferenceType extends Type
{
    public String toString() {
        return "reference";
    }
    
    public boolean identicalTo(final Type other) {
        return this == other;
    }
    
    public String toSignature() {
        return "Ljava/lang/Object;";
    }
    
    public com.ibm.xslt4j.bcel.generic.Type toJCType() {
        return com.ibm.xslt4j.bcel.generic.Type.OBJECT;
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Type type) {
        if (type == Type.String) {
            this.translateTo(classGen, methodGen, (StringType)type);
        }
        else if (type == Type.Real) {
            this.translateTo(classGen, methodGen, (RealType)type);
        }
        else if (type == Type.Boolean) {
            this.translateTo(classGen, methodGen, (BooleanType)type);
        }
        else if (type == Type.NodeSet) {
            this.translateTo(classGen, methodGen, (NodeSetType)type);
        }
        else if (type == Type.Node) {
            this.translateTo(classGen, methodGen, (NodeType)type);
        }
        else if (type == Type.ResultTree) {
            this.translateTo(classGen, methodGen, (ResultTreeType)type);
        }
        else if (type == Type.Object) {
            this.translateTo(classGen, methodGen, (ObjectType)type);
        }
        else if (type != Type.Reference) {
            final ErrorMsg err = new ErrorMsg("INTERNAL_ERR", type.toString());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final StringType type) {
        final int current = methodGen.getLocalIndex("current");
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (current < 0) {
            il.append(new PUSH(cpg, 0));
        }
        else {
            il.append(new ILOAD(current));
        }
        il.append(methodGen.loadDOM());
        final int stringF = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "stringF", "(Ljava/lang/Object;ILorg/apache/xalan/xsltc/DOM;)Ljava/lang/String;");
        il.append(new INVOKESTATIC(stringF));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final RealType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(methodGen.loadDOM());
        final int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "numberF", "(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)D");
        il.append(new INVOKESTATIC(index));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final BooleanType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "booleanF", "(Ljava/lang/Object;)Z");
        il.append(new INVOKESTATIC(index));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final NodeSetType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToNodeSet", "(Ljava/lang/Object;)Lorg/apache/xml/dtm/DTMAxisIterator;");
        il.append(new INVOKESTATIC(index));
        index = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "reset", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
        il.append(new INVOKEINTERFACE(index, 1));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final NodeType type) {
        this.translateTo(classGen, methodGen, Type.NodeSet);
        Type.NodeSet.translateTo(classGen, methodGen, type);
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final ResultTreeType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToResultTree", "(Ljava/lang/Object;)Lorg/apache/xalan/xsltc/DOM;");
        il.append(new INVOKESTATIC(index));
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final ObjectType type) {
        methodGen.getInstructionList().append(InstructionConstants.NOP);
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int referenceToLong = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToLong", "(Ljava/lang/Object;)J");
        final int referenceToDouble = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToDouble", "(Ljava/lang/Object;)D");
        final int referenceToBoolean = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToBoolean", "(Ljava/lang/Object;)Z");
        if (clazz.getName().equals("java.lang.Object")) {
            il.append(InstructionConstants.NOP);
        }
        else if (clazz == Double.TYPE) {
            il.append(new INVOKESTATIC(referenceToDouble));
        }
        else if (clazz.getName().equals("java.lang.Double")) {
            il.append(new INVOKESTATIC(referenceToDouble));
            Type.Real.translateTo(classGen, methodGen, Type.Reference);
        }
        else if (clazz == Float.TYPE) {
            il.append(new INVOKESTATIC(referenceToDouble));
            il.append(InstructionConstants.D2F);
        }
        else if (clazz.getName().equals("java.lang.String")) {
            final int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToString", "(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)Ljava/lang/String;");
            il.append(methodGen.loadDOM());
            il.append(new INVOKESTATIC(index));
        }
        else if (clazz.getName().equals("org.w3c.dom.Node")) {
            final int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToNode", "(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)Lorg/w3c/dom/Node;");
            il.append(methodGen.loadDOM());
            il.append(new INVOKESTATIC(index));
        }
        else if (clazz.getName().equals("org.w3c.dom.NodeList")) {
            final int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToNodeList", "(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)Lorg/w3c/dom/NodeList;");
            il.append(methodGen.loadDOM());
            il.append(new INVOKESTATIC(index));
        }
        else if (clazz.getName().equals("org.apache.xalan.xsltc.DOM")) {
            this.translateTo(classGen, methodGen, Type.ResultTree);
        }
        else if (clazz == Long.TYPE) {
            il.append(new INVOKESTATIC(referenceToLong));
        }
        else if (clazz == Integer.TYPE) {
            il.append(new INVOKESTATIC(referenceToLong));
            il.append(InstructionConstants.L2I);
        }
        else if (clazz == Short.TYPE) {
            il.append(new INVOKESTATIC(referenceToLong));
            il.append(InstructionConstants.L2I);
            il.append(InstructionConstants.I2S);
        }
        else if (clazz == Byte.TYPE) {
            il.append(new INVOKESTATIC(referenceToLong));
            il.append(InstructionConstants.L2I);
            il.append(InstructionConstants.I2B);
        }
        else if (clazz == Character.TYPE) {
            il.append(new INVOKESTATIC(referenceToLong));
            il.append(InstructionConstants.L2I);
            il.append(InstructionConstants.I2C);
        }
        else if (clazz == java.lang.Boolean.TYPE) {
            il.append(new INVOKESTATIC(referenceToBoolean));
        }
        else if (clazz.getName().equals("java.lang.Boolean")) {
            il.append(new INVOKESTATIC(referenceToBoolean));
            Type.Boolean.translateTo(classGen, methodGen, Type.Reference);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), clazz.getName());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateFrom(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        if (clazz.getName().equals("java.lang.Object")) {
            methodGen.getInstructionList().append(InstructionConstants.NOP);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), clazz.getName());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public FlowList translateToDesynthesized(final ClassGenerator classGen, final MethodGenerator methodGen, final BooleanType type) {
        final InstructionList il = methodGen.getInstructionList();
        this.translateTo(classGen, methodGen, type);
        return new FlowList(il.append(new IFEQ(null)));
    }
    
    public void translateBox(final ClassGenerator classGen, final MethodGenerator methodGen) {
    }
    
    public void translateUnBox(final ClassGenerator classGen, final MethodGenerator methodGen) {
    }
    
    public Instruction LOAD(final int slot) {
        return new ALOAD(slot);
    }
    
    public Instruction STORE(final int slot) {
        return new ASTORE(slot);
    }
}
