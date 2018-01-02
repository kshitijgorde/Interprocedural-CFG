// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.LocalVariableGen;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.GETFIELD;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.ASTORE;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xml.utils.XML11Char;
import org.apache.xalan.xsltc.compiler.util.Util;

final class ProcessingInstruction extends Instruction
{
    private AttributeValue _name;
    private boolean _isLiteral;
    
    ProcessingInstruction() {
        this._isLiteral = false;
    }
    
    public void parseContents(final Parser parser) {
        final String name = this.getAttribute("name");
        if (name.length() > 0) {
            this._isLiteral = Util.isLiteral(name);
            if (this._isLiteral && !XML11Char.isXML11ValidNCName(name)) {
                final ErrorMsg err = new ErrorMsg("INVALID_NCNAME_ERR", name, this);
                parser.reportError(3, err);
            }
            this._name = AttributeValue.create(this, name, parser);
        }
        else {
            this.reportError(this, parser, "REQUIRED_ATTR_ERR", "name");
        }
        if (name.equals("xml")) {
            this.reportError(this, parser, "ILLEGAL_PI_ERR", "xml");
        }
        this.parseChildren(parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this._name.typeCheck(stable);
        this.typeCheckContents(stable);
        return Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (!this._isLiteral) {
            final LocalVariableGen nameValue = methodGen.addLocalVariable2("nameValue", Util.getJCRefType("Ljava/lang/String;"), il.getEnd());
            this._name.translate(classGen, methodGen);
            il.append(new ASTORE(nameValue.getIndex()));
            il.append(new ALOAD(nameValue.getIndex()));
            final int check = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "checkNCName", "(Ljava/lang/String;)V");
            il.append(new INVOKESTATIC(check));
            il.append(methodGen.loadHandler());
            il.append(InstructionConstants.DUP);
            il.append(new ALOAD(nameValue.getIndex()));
        }
        else {
            il.append(methodGen.loadHandler());
            il.append(InstructionConstants.DUP);
            this._name.translate(classGen, methodGen);
        }
        il.append(classGen.loadTranslet());
        il.append(new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "stringValueHandler", "Lorg/apache/xalan/xsltc/runtime/StringValueHandler;")));
        il.append(InstructionConstants.DUP);
        il.append(methodGen.storeHandler());
        this.translateContents(classGen, methodGen);
        il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.StringValueHandler", "getValueOfPI", "()Ljava/lang/String;")));
        final int processingInstruction = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "processingInstruction", "(Ljava/lang/String;Ljava/lang/String;)V");
        il.append(new INVOKEINTERFACE(processingInstruction, 3));
        il.append(methodGen.storeHandler());
    }
}
