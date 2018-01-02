// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import java.util.Vector;

final class LocalNameCall extends NameBase
{
    public LocalNameCall(final QName fname) {
        super(fname);
    }
    
    public LocalNameCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int getNodeName = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeName", "(I)Ljava/lang/String;");
        final int getLocalName = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "getLocalName", "(Ljava/lang/String;)Ljava/lang/String;");
        super.translate(classGen, methodGen);
        il.append(new INVOKEINTERFACE(getNodeName, 2));
        il.append(new INVOKESTATIC(getLocalName));
    }
}
