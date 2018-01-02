// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.tree;

import org.jruby.org.objectweb.asm.MethodVisitor;

public class TryCatchBlockNode
{
    public LabelNode start;
    public LabelNode end;
    public LabelNode handler;
    public String type;
    
    public TryCatchBlockNode(final LabelNode start, final LabelNode end, final LabelNode handler, final String type) {
        this.start = start;
        this.end = end;
        this.handler = handler;
        this.type = type;
    }
    
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitTryCatchBlock(this.start.getLabel(), this.end.getLabel(), (this.handler == null) ? null : this.handler.getLabel(), this.type);
    }
}
