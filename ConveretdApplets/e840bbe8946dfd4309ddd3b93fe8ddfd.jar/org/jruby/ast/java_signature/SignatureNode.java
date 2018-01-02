// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.java_signature;

import java.util.Iterator;
import java.util.List;

public class SignatureNode
{
    protected List<Modifier> modifiers;
    protected String name;
    protected List<ParameterNode> parameterList;
    protected String extraTypeInfo;
    protected List<TypeNode> throwTypes;
    
    public SignatureNode(final String name, final List<ParameterNode> parameterList) {
        this.name = name;
        this.parameterList = parameterList;
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<ParameterNode> getParameters() {
        return this.parameterList;
    }
    
    public void setModifiers(final List<Modifier> modifiers) {
        this.modifiers = modifiers;
    }
    
    public void setExtraTypeInfo(final String extraTypeInfo) {
        this.extraTypeInfo = extraTypeInfo;
    }
    
    public List<Modifier> getModifiers() {
        return this.modifiers;
    }
    
    public void setThrows(final List<TypeNode> throwTypes) {
        this.throwTypes = throwTypes;
    }
    
    public List<TypeNode> getThrows() {
        return this.throwTypes;
    }
    
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (final Modifier modifier : this.modifiers) {
            builder.append(modifier).append(' ');
        }
        if (this.extraTypeInfo != null) {
            builder.append(this.extraTypeInfo).append(' ');
        }
        builder.append(this.name).append('(');
        int length = this.parameterList.size();
        for (int i = 0; i < length - 1; ++i) {
            builder.append(this.parameterList.get(i)).append(", ");
        }
        if (length > 0) {
            builder.append(this.parameterList.get(length - 1));
        }
        builder.append(')');
        length = this.throwTypes.size();
        if (length > 0) {
            builder.append(" throws ");
            for (int i = 0; i < length - 1; ++i) {
                builder.append(this.throwTypes.get(i)).append(", ");
            }
            builder.append(this.throwTypes.get(length - 1));
        }
        return builder.toString();
    }
}
