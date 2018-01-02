// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

public interface NonLocalControlFlowNode
{
    Node getValueNode();
    
    boolean hasValue();
}
