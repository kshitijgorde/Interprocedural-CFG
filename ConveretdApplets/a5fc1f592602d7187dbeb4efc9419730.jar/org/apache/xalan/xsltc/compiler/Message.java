// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

final class Message extends Instruction
{
    private boolean _terminate;
    
    Message() {
        this._terminate = false;
    }
    
    public void parseContents(final Parser parser) {
        final String termstr = this.getAttribute("terminate");
        if (termstr != null) {
            this._terminate = termstr.equals("yes");
        }
        this.parseChildren(parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this.typeCheckContents(stable);
        return Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(classGen.loadTranslet());
        Label_0493: {
            switch (this.elementCount()) {
                case 0: {
                    il.append(new PUSH(cpg, ""));
                    break Label_0493;
                }
                case 1: {
                    final SyntaxTreeNode child = (SyntaxTreeNode)this.elementAt(0);
                    if (child instanceof Text) {
                        il.append(new PUSH(cpg, ((Text)child).getText()));
                        break Label_0493;
                    }
                    break;
                }
            }
            il.append(methodGen.loadHandler());
            il.append(new NEW(cpg.addClass("org.apache.xml.serializer.ToXMLStream")));
            il.append(methodGen.storeHandler());
            il.append(new NEW(cpg.addClass("java.io.StringWriter")));
            il.append(InstructionConstants.DUP);
            il.append(InstructionConstants.DUP);
            il.append(new INVOKESPECIAL(cpg.addMethodref("java.io.StringWriter", "<init>", "()V")));
            il.append(methodGen.loadHandler());
            il.append(new INVOKESPECIAL(cpg.addMethodref("org.apache.xml.serializer.ToXMLStream", "<init>", "()V")));
            il.append(methodGen.loadHandler());
            il.append(InstructionConstants.SWAP);
            il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xml.serializer.SerializerBase", "setWriter", "(Ljava/io/Writer;)V")));
            il.append(methodGen.loadHandler());
            il.append(new PUSH(cpg, "UTF-8"));
            il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xml.serializer.SerializerBase", "setEncoding", "(Ljava/lang/String;)V")));
            il.append(methodGen.loadHandler());
            il.append(InstructionConstants.ICONST_1);
            il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xml.serializer.SerializerBase", "setOmitXMLDeclaration", "(Z)V")));
            il.append(methodGen.loadHandler());
            il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xml.serializer.SerializerBase", "startDocument", "()V")));
            this.translateContents(classGen, methodGen);
            il.append(methodGen.loadHandler());
            il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xml.serializer.SerializerBase", "endDocument", "()V")));
            il.append(new INVOKEVIRTUAL(cpg.addMethodref("java.io.StringWriter", "toString", "()Ljava/lang/String;")));
            il.append(InstructionConstants.SWAP);
            il.append(methodGen.storeHandler());
        }
        il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "displayMessage", "(Ljava/lang/String;)V")));
        if (this._terminate) {
            final int einit = cpg.addMethodref("java.lang.RuntimeException", "<init>", "(Ljava/lang/String;)V");
            il.append(new NEW(cpg.addClass("java.lang.RuntimeException")));
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, "Termination forced by an xsl:message instruction"));
            il.append(new INVOKESPECIAL(einit));
            il.append(InstructionConstants.ATHROW);
        }
    }
}
