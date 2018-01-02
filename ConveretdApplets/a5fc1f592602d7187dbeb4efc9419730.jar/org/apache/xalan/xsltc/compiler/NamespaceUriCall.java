// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import java.util.Vector;

final class NamespaceUriCall extends NameBase
{
    public NamespaceUriCall(final QName fname) {
        super(fname);
    }
    
    public NamespaceUriCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int getNamespace = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNamespaceName", "(I)Ljava/lang/String;");
        super.translate(classGen, methodGen);
        il.append(new INVOKEINTERFACE(getNamespace, 2));
    }
}
