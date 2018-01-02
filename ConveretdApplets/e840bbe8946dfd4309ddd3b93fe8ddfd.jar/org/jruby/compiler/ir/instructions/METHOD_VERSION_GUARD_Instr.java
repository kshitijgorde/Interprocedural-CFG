// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.compiler.ir.CodeVersion;
import org.jruby.compiler.ir.IRMethod;

public class METHOD_VERSION_GUARD_Instr extends GuardInstr
{
    IRMethod guardedMethod;
    CodeVersion reqdVersion;
    Label failurePathLabel;
    
    public METHOD_VERSION_GUARD_Instr(final IRMethod m, final CodeVersion v, final Label failurePathLabel) {
        super(Operation.METHOD_VERSION_GUARD);
        this.guardedMethod = m;
        this.reqdVersion = v;
        this.failurePathLabel = failurePathLabel;
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new METHOD_VERSION_GUARD_Instr(this.guardedMethod, this.reqdVersion, ii.getRenamedLabel(this.failurePathLabel));
    }
}
