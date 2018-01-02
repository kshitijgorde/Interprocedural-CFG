// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.StringType;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.Util;

final class TransletOutput extends Instruction
{
    private Expression _filename;
    private boolean _append;
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("TransletOutput: " + this._filename);
    }
    
    public void parseContents(final Parser parser) {
        final String filename = this.getAttribute("file");
        final String append = this.getAttribute("append");
        if (filename == null || filename.equals("")) {
            this.reportError(this, parser, "REQUIRED_ATTR_ERR", "file");
        }
        this._filename = AttributeValue.create(this, filename, parser);
        if (append != null && (append.toLowerCase().equals("yes") || append.toLowerCase().equals("true"))) {
            this._append = true;
        }
        else {
            this._append = false;
        }
        this.parseChildren(parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Type type = this._filename.typeCheck(stable);
        if (!(type instanceof StringType)) {
            this._filename = new CastExpr(this._filename, Type.String);
        }
        this.typeCheckContents(stable);
        return Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final boolean isSecureProcessing = classGen.getParser().getXSLTC().isSecureProcessing();
        if (isSecureProcessing) {
            final int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "unallowed_extension_elementF", "(Ljava/lang/String;)V");
            il.append(new PUSH(cpg, "redirect"));
            il.append(new INVOKESTATIC(index));
            return;
        }
        il.append(methodGen.loadHandler());
        final int open = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "openOutputHandler", "(Ljava/lang/String;Z)Lorg/apache/xml/serializer/SerializationHandler;");
        final int close = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "closeOutputHandler", "(Lorg/apache/xml/serializer/SerializationHandler;)V");
        il.append(classGen.loadTranslet());
        this._filename.translate(classGen, methodGen);
        il.append(new PUSH(cpg, this._append));
        il.append(new INVOKEVIRTUAL(open));
        il.append(methodGen.storeHandler());
        this.translateContents(classGen, methodGen);
        il.append(classGen.loadTranslet());
        il.append(methodGen.loadHandler());
        il.append(new INVOKEVIRTUAL(close));
        il.append(methodGen.storeHandler());
    }
}
