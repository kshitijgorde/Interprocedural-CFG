// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.tree;

import java.util.Map;
import org.jruby.org.objectweb.asm.MethodVisitor;

public class IincInsnNode extends AbstractInsnNode
{
    public int var;
    public int incr;
    
    public IincInsnNode(final int var, final int incr) {
        super(132);
        this.var = var;
        this.incr = incr;
    }
    
    public int getType() {
        return 9;
    }
    
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitIincInsn(this.var, this.incr);
    }
    
    public AbstractInsnNode clone(final Map map) {
        return new IincInsnNode(this.var, this.incr);
    }
}
