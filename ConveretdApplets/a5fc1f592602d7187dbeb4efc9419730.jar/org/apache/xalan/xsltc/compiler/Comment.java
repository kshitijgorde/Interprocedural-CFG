// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

final class Comment extends Instruction
{
    public void parseContents(final Parser parser) {
        this.parseChildren(parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this.typeCheckContents(stable);
        return Type.String;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        Text rawText = null;
        if (this.elementCount() == 1) {
            final Object content = this.elementAt(0);
            if (content instanceof Text) {
                rawText = (Text)content;
            }
        }
        if (rawText != null) {
            il.append(methodGen.loadHandler());
            if (rawText.canLoadAsArrayOffsetLength()) {
                rawText.loadAsArrayOffsetLength(classGen, methodGen);
                final int comment = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "comment", "([CII)V");
                il.append(new INVOKEINTERFACE(comment, 4));
            }
            else {
                il.append(new PUSH(cpg, rawText.getText()));
                final int comment = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "comment", "(Ljava/lang/String;)V");
                il.append(new INVOKEINTERFACE(comment, 2));
            }
        }
        else {
            il.append(methodGen.loadHandler());
            il.append(InstructionConstants.DUP);
            il.append(classGen.loadTranslet());
            il.append(new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "stringValueHandler", "Lorg/apache/xalan/xsltc/runtime/StringValueHandler;")));
            il.append(InstructionConstants.DUP);
            il.append(methodGen.storeHandler());
            this.translateContents(classGen, methodGen);
            il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.StringValueHandler", "getValue", "()Ljava/lang/String;")));
            final int comment = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "comment", "(Ljava/lang/String;)V");
            il.append(new INVOKEINTERFACE(comment, 2));
            il.append(methodGen.storeHandler());
        }
    }
}
