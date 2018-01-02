// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.xalan.xsltc.compiler.util.NodeSetType;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.runtime.BasisLibrary;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;

final class ParameterRef extends VariableRefBase
{
    QName _name;
    
    public ParameterRef(final Param param) {
        super(param);
        this._name = null;
        this._name = param._name;
    }
    
    public String toString() {
        return "parameter-ref(" + super._variable.getName() + '/' + super._variable.getType() + ')';
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final String name = BasisLibrary.mapQNameToJavaName(this._name.toString());
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
