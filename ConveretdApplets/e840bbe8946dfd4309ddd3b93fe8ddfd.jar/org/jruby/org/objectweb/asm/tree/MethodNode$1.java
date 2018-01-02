// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.tree;

import java.util.ArrayList;

class MethodNode$1 extends ArrayList
{
    private final /* synthetic */ MethodNode this$0;
    
    MethodNode$1(final MethodNode this$0, final int n) {
        super(n);
        this.this$0 = this$0;
    }
    
    public boolean add(final Object annotationDefault) {
        this.this$0.annotationDefault = annotationDefault;
        return super.add(annotationDefault);
    }
}
