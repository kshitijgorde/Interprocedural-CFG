// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.ReferenceType;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xml.utils.XMLChar;
import org.apache.xalan.xsltc.compiler.util.Util;

final class WithParam extends Instruction
{
    private QName _name;
    protected String _escapedName;
    private Expression _select;
    private boolean _doParameterOptimization;
    
    WithParam() {
        this._doParameterOptimization = false;
    }
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("with-param " + this._name);
        if (this._select != null) {
            this.indent(indent + 4);
            Util.println("select " + this._select.toString());
        }
        this.displayContents(indent + 4);
    }
    
    public String getEscapedName() {
        return this._escapedName;
    }
    
    public QName getName() {
        return this._name;
    }
    
    public void setName(final QName name) {
        this._name = name;
        this._escapedName = Util.escape(name.getStringRep());
    }
    
    public void setDoParameterOptimization(final boolean flag) {
        this._doParameterOptimization = flag;
    }
    
    public void parseContents(final Parser parser) {
        final String name = this.getAttribute("name");
        if (name.length() > 0) {
            if (!XMLChar.isValidQName(name)) {
                final ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", name, this);
                parser.reportError(3, err);
            }
            this.setName(parser.getQNameIgnoreDefaultNs(name));
        }
        else {
            this.reportError(this, parser, "REQUIRED_ATTR_ERR", "name");
        }
        final String select = this.getAttribute("select");
        if (select.length() > 0) {
            this._select = parser.parseExpression(this, "select", null);
        }
        this.parseChildren(parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this._select != null) {
            final Type tselect = this._select.typeCheck(stable);
            if (!(tselect instanceof ReferenceType)) {
                this._select = new CastExpr(this._select, Type.Reference);
            }
        }
        else {
            this.typeCheckContents(stable);
        }
        return Type.Void;
    }
    
    public void translateValue(final ClassGenerator classGen, final MethodGenerator methodGen) {
        if (this._select != null) {
            this._select.translate(classGen, methodGen);
            this._select.startIterator(classGen, methodGen);
        }
        else if (this.hasContents()) {
            this.compileResultTree(classGen, methodGen);
        }
        else {
            final ConstantPoolGen cpg = classGen.getConstantPool();
            final InstructionList il = methodGen.getInstructionList();
            il.append(new PUSH(cpg, ""));
        }
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (this._doParameterOptimization) {
            this.translateValue(classGen, methodGen);
            return;
        }
        final String name = Util.escape(this.getEscapedName());
        il.append(classGen.loadTranslet());
        il.append(new PUSH(cpg, name));
        this.translateValue(classGen, methodGen);
        il.append(new PUSH(cpg, false));
        il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "addParameter", "(Ljava/lang/String;Ljava/lang/Object;Z)Ljava/lang/Object;")));
        il.append(InstructionConstants.POP);
    }
}
