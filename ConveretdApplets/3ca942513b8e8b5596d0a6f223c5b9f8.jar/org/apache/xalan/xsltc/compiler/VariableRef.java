// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import org.apache.xalan.xsltc.compiler.util.NodeSetType;
import com.ibm.xslt4j.bcel.generic.CHECKCAST;
import com.ibm.xslt4j.bcel.generic.GETFIELD;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;

final class VariableRef extends VariableRefBase
{
    public VariableRef(final Variable variable) {
        super(variable);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (super._type.implementedAsMethod()) {
            return;
        }
        final String name = super._variable.getEscapedName();
        final String signature = super._type.toSignature();
        if (super._variable.isLocal()) {
            if (classGen.isExternal()) {
                Closure variableClosure;
                for (variableClosure = super._closure; variableClosure != null && !variableClosure.inInnerClass(); variableClosure = variableClosure.getParentClosure()) {}
                if (variableClosure != null) {
                    il.append(InstructionConstants.ALOAD_0);
                    il.append(new GETFIELD(cpg.addFieldref(variableClosure.getInnerClassName(), name, signature)));
                }
                else {
                    il.append(super._variable.loadInstruction());
                    super._variable.removeReference(this);
                }
            }
            else {
                il.append(super._variable.loadInstruction());
                super._variable.removeReference(this);
            }
        }
        else {
            final String className = classGen.getClassName();
            il.append(classGen.loadTranslet());
            if (classGen.isExternal()) {
                il.append(new CHECKCAST(cpg.addClass(className)));
            }
            il.append(new GETFIELD(cpg.addFieldref(className, name, signature)));
        }
        if (super._variable.getType() instanceof NodeSetType) {
            final int clone = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "cloneIterator", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
            il.append(new INVOKEINTERFACE(clone, 1));
        }
    }
}
