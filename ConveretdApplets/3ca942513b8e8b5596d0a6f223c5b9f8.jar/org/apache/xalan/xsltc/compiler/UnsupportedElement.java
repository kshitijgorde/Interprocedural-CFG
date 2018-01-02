// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import java.util.Vector;

final class UnsupportedElement extends SyntaxTreeNode
{
    private Vector _fallbacks;
    private ErrorMsg _message;
    private boolean _isExtension;
    
    public UnsupportedElement(final String uri, final String prefix, final String local, final boolean isExtension) {
        super(uri, prefix, local);
        this._fallbacks = null;
        this._message = null;
        this._isExtension = false;
        this._isExtension = isExtension;
    }
    
    public void setErrorMessage(final ErrorMsg message) {
        this._message = message;
    }
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("Unsupported element = " + super._qname.getNamespace() + ":" + super._qname.getLocalPart());
        this.displayContents(indent + 4);
    }
    
    private void processFallbacks(final Parser parser) {
        final Vector children = this.getContents();
        if (children != null) {
            for (int count = children.size(), i = 0; i < count; ++i) {
                final SyntaxTreeNode child = children.elementAt(i);
                if (child instanceof Fallback) {
                    final Fallback fallback = (Fallback)child;
                    fallback.activate();
                    fallback.parseContents(parser);
                    if (this._fallbacks == null) {
                        this._fallbacks = new Vector();
                    }
                    this._fallbacks.addElement(child);
                }
            }
        }
    }
    
    public void parseContents(final Parser parser) {
        this.processFallbacks(parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this._fallbacks != null) {
            for (int count = this._fallbacks.size(), i = 0; i < count; ++i) {
                final Fallback fallback = this._fallbacks.elementAt(i);
                fallback.typeCheck(stable);
            }
        }
        return Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        if (this._fallbacks != null) {
            for (int count = this._fallbacks.size(), i = 0; i < count; ++i) {
                final Fallback fallback = this._fallbacks.elementAt(i);
                fallback.translate(classGen, methodGen);
            }
        }
        else {
            final ConstantPoolGen cpg = classGen.getConstantPool();
            final InstructionList il = methodGen.getInstructionList();
            final int unsupportedElem = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "unsupported_ElementF", "(Ljava/lang/String;Z)V");
            il.append(new PUSH(cpg, this.getQName().toString()));
            il.append(new PUSH(cpg, this._isExtension));
            il.append(new INVOKESTATIC(unsupportedElem));
        }
    }
}
