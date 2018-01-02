// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.java_signature;

public class ParameterNode
{
    private final TypeNode type;
    private final String variableName;
    private boolean isFinal;
    private boolean isVarArgs;
    
    public ParameterNode(final TypeNode type, final String variableName) {
        this.isFinal = false;
        this.isVarArgs = false;
        this.type = type;
        this.variableName = variableName;
    }
    
    public ParameterNode(final TypeNode type, final String variableName, final boolean isFinal) {
        this(type, variableName);
        this.isFinal = isFinal;
    }
    
    public ParameterNode(final TypeNode type, final String variableName, final boolean isFinal, final boolean isVarArgs) {
        this(type, variableName, isFinal);
        this.isVarArgs = isVarArgs;
    }
    
    public TypeNode getType() {
        return this.type;
    }
    
    public String getVariableName() {
        return this.variableName;
    }
    
    public boolean isFinal() {
        return this.isFinal;
    }
    
    public boolean isVarArgs() {
        return this.isVarArgs;
    }
    
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (this.isFinal) {
            builder.append("final ");
        }
        builder.append(this.type);
        if (this.isVarArgs()) {
            builder.append("...");
        }
        if (this.variableName != null) {
            builder.append(" ").append(this.variableName);
        }
        return builder.toString();
    }
}
