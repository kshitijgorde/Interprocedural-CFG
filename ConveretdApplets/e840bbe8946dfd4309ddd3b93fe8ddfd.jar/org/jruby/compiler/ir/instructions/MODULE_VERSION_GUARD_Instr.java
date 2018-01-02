// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.compiler.ir.CodeVersion;
import org.jruby.compiler.ir.IRModule;

public class MODULE_VERSION_GUARD_Instr extends GuardInstr
{
    IRModule guardedModule;
    CodeVersion reqdVersion;
    Label failurePathLabel;
    
    public MODULE_VERSION_GUARD_Instr(final IRModule m, final CodeVersion v, final Label failurePathLabel) {
        super(Operation.MODULE_VERSION_GUARD);
        this.guardedModule = m;
        this.reqdVersion = v;
        this.failurePathLabel = failurePathLabel;
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new MODULE_VERSION_GUARD_Instr(this.guardedModule, this.reqdVersion, ii.getRenamedLabel(this.failurePathLabel));
    }
}
