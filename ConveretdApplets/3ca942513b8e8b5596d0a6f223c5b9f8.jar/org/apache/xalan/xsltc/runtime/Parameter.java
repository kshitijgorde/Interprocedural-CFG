// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

public class Parameter
{
    public String _name;
    public Object _value;
    public boolean _isDefault;
    
    public Parameter(final String name, final Object value) {
        this._name = name;
        this._value = value;
        this._isDefault = true;
    }
    
    public Parameter(final String name, final Object value, final boolean isDefault) {
        this._name = name;
        this._value = value;
        this._isDefault = isDefault;
    }
}
