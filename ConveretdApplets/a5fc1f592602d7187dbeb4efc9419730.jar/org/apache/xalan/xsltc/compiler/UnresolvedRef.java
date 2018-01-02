// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;

final class UnresolvedRef extends VariableRefBase
{
    private QName _variableName;
    private VariableRefBase _ref;
    private VariableBase _var;
    private Stylesheet _sheet;
    
    public UnresolvedRef(final QName name) {
        this._variableName = null;
        this._ref = null;
        this._var = null;
        this._sheet = null;
        this._variableName = name;
        this._sheet = this.getStylesheet();
    }
    
    public QName getName() {
        return this._variableName;
    }
    
    private ErrorMsg reportError() {
        final ErrorMsg err = new ErrorMsg("VARIABLE_UNDEF_ERR", this._variableName, this);
        this.getParser().reportError(3, err);
        return err;
    }
    
    private VariableRefBase resolve(final Parser parser, final SymbolTable stable) {
        VariableBase ref = parser.lookupVariable(this._variableName);
        if (ref == null) {
            ref = (VariableBase)stable.lookupName(this._variableName);
        }
        if (ref == null) {
            this.reportError();
            return null;
        }
        if ((this._var = this.findParentVariable()) != null) {
            this._var.addDependency(ref);
        }
        if (ref instanceof Variable) {
            return new VariableRef((Variable)ref);
        }
        if (ref instanceof Param) {
            return new ParameterRef((Param)ref);
        }
        return null;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this._ref != null) {
            final String name = this._variableName.toString();
            final ErrorMsg err = new ErrorMsg("CIRCULAR_VARIABLE_ERR", name, this);
        }
        if ((this._ref = this.resolve(this.getParser(), stable)) != null) {
            return super._type = this._ref.typeCheck(stable);
        }
        throw new TypeCheckError(this.reportError());
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        if (this._ref != null) {
            this._ref.translate(classGen, methodGen);
        }
        else {
            this.reportError();
        }
    }
    
    public String toString() {
        return "unresolved-ref()";
    }
}
