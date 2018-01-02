// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

import org.jruby.compiler.ir.operands.Label;

public class IRLoop
{
    public final IRExecutionScope container;
    public final IRLoop parentLoop;
    public final Label loopStartLabel;
    public final Label loopEndLabel;
    public final Label iterStartLabel;
    public final Label iterEndLabel;
    
    public IRLoop(final IRExecutionScope s) {
        this.container = s;
        this.parentLoop = s.getCurrentLoop();
        this.loopStartLabel = s.getNewLabel("_LOOP_BEGIN");
        this.loopEndLabel = s.getNewLabel("_LOOP_END");
        this.iterStartLabel = s.getNewLabel("_ITER_BEGIN");
        this.iterEndLabel = s.getNewLabel("_ITER_END");
    }
}
