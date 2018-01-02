// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.Util;

final class ValueOf extends Instruction
{
    private Expression _select;
    private boolean _escaping;
    private boolean _isString;
    
    ValueOf() {
        this._escaping = true;
        this._isString = false;
    }
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("ValueOf");
        this.indent(indent + 4);
        Util.println("select " + this._select.toString());
    }
    
    public void parseContents(final Parser parser) {
        this._select = parser.parseExpression(this, "select", null);
        if (this._select.isDummy()) {
            this.reportError(this, parser, "REQUIRED_ATTR_ERR", "select");
            return;
        }
        final String str = this.getAttribute("disable-output-escaping");
        if (str != null && str.equals("yes")) {
            this._escaping = false;
        }
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Type type = this._select.typeCheck(stable);
        if (type != null && !type.identicalTo(Type.Node)) {
            if (type.identicalTo(Type.NodeSet)) {
                this._select = new CastExpr(this._select, Type.Node);
            }
            else {
                this._isString = true;
                if (!type.identicalTo(Type.String)) {
                    this._select = new CastExpr(this._select, Type.String);
                }
                this._isString = true;
            }
        }
        return Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int setEscaping = cpg.addInterfaceMethodref("org/apache/xml/serializer/SerializationHandler", "setEscaping", "(Z)Z");
        if (!this._escaping) {
            il.append(methodGen.loadHandler());
            il.append(new PUSH(cpg, false));
            il.append(new INVOKEINTERFACE(setEscaping, 2));
        }
        if (this._isString) {
            final int characters = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "characters", "(Ljava/lang/String;Lorg/apache/xml/serializer/SerializationHandler;)V");
            il.append(classGen.loadTranslet());
            this._select.translate(classGen, methodGen);
            il.append(methodGen.loadHandler());
            il.append(new INVOKEVIRTUAL(characters));
        }
        else {
            final int characters = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "characters", "(ILorg/apache/xml/serializer/SerializationHandler;)V");
            il.append(methodGen.loadDOM());
            this._select.translate(classGen, methodGen);
            il.append(methodGen.loadHandler());
            il.append(new INVOKEINTERFACE(characters, 3));
        }
        if (!this._escaping) {
            il.append(methodGen.loadHandler());
            il.append(InstructionConstants.SWAP);
            il.append(new INVOKEINTERFACE(setEscaping, 2));
            il.append(InstructionConstants.POP);
        }
    }
}
