// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

final class ArgumentList
{
    private final Expression _arg;
    private final ArgumentList _rest;
    
    public ArgumentList(final Expression arg, final ArgumentList rest) {
        this._arg = arg;
        this._rest = rest;
    }
    
    public String toString() {
        return (this._rest == null) ? this._arg.toString() : (this._arg.toString() + ", " + this._rest.toString());
    }
}
