// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.java_signature;

import java.util.List;

public class ConstructorSignatureNode extends SignatureNode
{
    public ConstructorSignatureNode(final String name, final List<ParameterNode> parameterList) {
        super(name, parameterList);
    }
}
