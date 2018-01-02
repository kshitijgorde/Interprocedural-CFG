// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.commons;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import org.jruby.org.objectweb.asm.MethodVisitor;
import org.jruby.org.objectweb.asm.tree.MethodNode;

public class TryCatchBlockSorter extends MethodNode
{
    private final MethodVisitor mv;
    
    public TryCatchBlockSorter(final MethodVisitor mv, final int n, final String s, final String s2, final String s3, final String[] array) {
        super(n, s, s2, s3, array);
        this.mv = mv;
    }
    
    public void visitEnd() {
        Collections.sort((List<Object>)this.tryCatchBlocks, new TryCatchBlockSorter$1(this));
        if (this.mv != null) {
            this.accept(this.mv);
        }
    }
}
