// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.xalan.xsltc.compiler.SyntaxTreeNode;

public class TypeCheckError extends Exception
{
    static final long serialVersionUID = 3246224233917854640L;
    ErrorMsg _error;
    SyntaxTreeNode _node;
    
    public TypeCheckError(final SyntaxTreeNode node) {
        this._error = null;
        this._node = null;
        this._node = node;
    }
    
    public TypeCheckError(final ErrorMsg error) {
        this._error = null;
        this._node = null;
        this._error = error;
    }
    
    public TypeCheckError(final String code, final Object param) {
        this._error = null;
        this._node = null;
        this._error = new ErrorMsg(code, param);
    }
    
    public TypeCheckError(final String code, final Object param1, final Object param2) {
        this._error = null;
        this._node = null;
        this._error = new ErrorMsg(code, param1, param2);
    }
    
    public ErrorMsg getErrorMsg() {
        return this._error;
    }
    
    public String getMessage() {
        return this.toString();
    }
    
    public String toString() {
        if (this._error == null) {
            if (this._node != null) {
                this._error = new ErrorMsg("TYPE_CHECK_ERR", this._node.toString());
            }
            else {
                this._error = new ErrorMsg("TYPE_CHECK_UNK_LOC_ERR");
            }
        }
        return this._error.toString();
    }
}
