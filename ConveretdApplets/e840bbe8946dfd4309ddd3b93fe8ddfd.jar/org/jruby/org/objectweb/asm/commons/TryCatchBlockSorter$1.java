// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.commons;

import org.jruby.org.objectweb.asm.tree.AbstractInsnNode;
import org.jruby.org.objectweb.asm.tree.TryCatchBlockNode;
import java.util.Comparator;

class TryCatchBlockSorter$1 implements Comparator
{
    private final /* synthetic */ TryCatchBlockSorter this$0;
    
    TryCatchBlockSorter$1(final TryCatchBlockSorter this$0) {
        this.this$0 = this$0;
    }
    
    public int compare(final Object o, final Object o2) {
        return this.blockLength((TryCatchBlockNode)o) - this.blockLength((TryCatchBlockNode)o2);
    }
    
    private int blockLength(final TryCatchBlockNode tryCatchBlockNode) {
        return this.this$0.instructions.indexOf(tryCatchBlockNode.end) - this.this$0.instructions.indexOf(tryCatchBlockNode.start);
    }
}
