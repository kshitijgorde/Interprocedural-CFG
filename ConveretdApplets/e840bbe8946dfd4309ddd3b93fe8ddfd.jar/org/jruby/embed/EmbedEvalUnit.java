// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed;

import org.jruby.runtime.scope.ManyVarsDynamicScope;
import org.jruby.ast.Node;
import org.jruby.javasupport.JavaEmbedUtils;

public interface EmbedEvalUnit extends JavaEmbedUtils.EvalUnit
{
    Node getNode();
    
    ManyVarsDynamicScope getScope();
}
