// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.java_signature;

import java.util.Iterator;
import java.util.List;

public class MethodSignatureNode extends SignatureNode
{
    protected TypeNode returnType;
    
    public MethodSignatureNode(final String name, final List<ParameterNode> parameterList) {
        super(name, parameterList);
    }
    
    public TypeNode getReturnType() {
        return this.returnType;
    }
    
    public void setReturnType(final TypeNode returnType) {
        this.returnType = returnType;
    }
    
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (final Modifier modifier : this.modifiers) {
            builder.append(modifier).append(' ');
        }
        if (this.extraTypeInfo != null) {
            builder.append(this.extraTypeInfo).append(' ');
        }
        builder.append(this.returnType).append(' ');
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
