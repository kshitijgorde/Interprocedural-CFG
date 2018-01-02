// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;

public class FilenameInstr extends NoOperandInstr
{
    public final String filename;
    
    public FilenameInstr(final String filename) {
        super(Operation.FILE_NAME);
        this.filename = filename;
    }
    
    public String toString() {
        return super.toString() + "(" + this.filename + ")";
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return this;
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        interp.getContext().setFile(this.filename);
        return null;
    }
}
