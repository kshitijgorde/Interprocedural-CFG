// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

class VariableRefBase extends Expression
{
    protected final VariableBase _variable;
    protected Closure _closure;
    
    public VariableRefBase(final VariableBase variable) {
        this._closure = null;
        (this._variable = variable).addReference(this);
    }
    
    public VariableRefBase() {
        this._closure = null;
        this._variable = null;
    }
    
    public VariableBase getVariable() {
        return this._variable;
    }
    
    public VariableBase findParentVariable() {
        SyntaxTreeNode node;
        for (node = this; node != null && !(node instanceof VariableBase); node = node.getParent()) {}
        return (VariableBase)node;
    }
    
    public boolean equals(final Object obj) {
        try {
            return this._variable == ((VariableRefBase)obj)._variable;
        }
        catch (ClassCastException e) {
            return false;
        }
    }
    
    public String toString() {
        return "variable-ref(" + this._variable.getName() + '/' + this._variable.getType() + ')';
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (super._type != null) {
            return super._type;
        }
        Label_0081: {
            if (this._variable.isLocal()) {
                SyntaxTreeNode node = this.getParent();
                while (true) {
                    while (!(node instanceof Closure)) {
                        if (!(node instanceof TopLevelElement)) {
                            node = node.getParent();
                            if (node != null) {
                                continue;
                            }
                        }
                        if (this._closure != null) {
                            this._closure.addVariable(this);
                        }
                        break Label_0081;
                    }
                    this._closure = (Closure)node;
                    continue;
                }
            }
        }
        final VariableBase parent = this.findParentVariable();
        if (parent != null) {
            VariableBase var = this._variable;
            if (this._variable._ignore) {
                if (this._variable instanceof Variable) {
                    var = parent.getSymbolTable().lookupVariable(this._variable._name);
                }
                else if (this._variable instanceof Param) {
                    var = parent.getSymbolTable().lookupParam(this._variable._name);
                }
            }
            parent.addDependency(var);
        }
        super._type = this._variable.getType();
        if (super._type == null) {
            this._variable.typeCheck(stable);
            super._type = this._variable.getType();
        }
        return super._type;
    }
}
