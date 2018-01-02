// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.component;

import org.jruby.util.IdUtil;
import org.jruby.runtime.builtin.Variable;

public class VariableEntry<BaseObjectType> implements Variable<BaseObjectType>
{
    public final String name;
    public final BaseObjectType value;
    
    public VariableEntry(final String name, final BaseObjectType value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public BaseObjectType getValue() {
        return this.value;
    }
    
    public boolean isClassVariable() {
        return IdUtil.isClassVariable(this.name);
    }
    
    public boolean isConstant() {
        return IdUtil.isConstant(this.name);
    }
    
    public boolean isInstanceVariable() {
        return IdUtil.isInstanceVariable(this.name);
    }
    
    public boolean isRubyVariable() {
        final char c;
        return this.name.length() > 0 && ((c = this.name.charAt(0)) == '@' || (c <= 'Z' && c >= 'A'));
    }
    
    public String toString() {
        return "Name: " + this.getName();
    }
}
