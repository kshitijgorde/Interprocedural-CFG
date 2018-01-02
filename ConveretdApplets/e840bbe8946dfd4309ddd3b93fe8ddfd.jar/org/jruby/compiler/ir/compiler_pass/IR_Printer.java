// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.compiler_pass;

import org.jruby.compiler.ir.representations.CFG;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.compiler.ir.IRExecutionScope;
import org.jruby.compiler.ir.IRScope;

public class IR_Printer implements CompilerPass
{
    public boolean isPreOrder() {
        return true;
    }
    
    public void run(final IRScope s) {
        System.out.println("----------------------------------------");
        System.out.println(s.toString());
        CFG c = null;
        if (s instanceof IRExecutionScope) {
            c = ((IRExecutionScope)s).getCFG();
        }
        if (c != null) {
            System.out.println("\nGraph:\n" + c.getGraph().toString());
            System.out.println("\nInstructions:\n" + c.toStringInstrs());
        }
        else if (s instanceof IRMethod) {
            final IRMethod m = (IRMethod)s;
            System.out.println("\n  instrs:\n" + m.toStringInstrs());
            System.out.println("\n  live variables:\n" + m.toStringVariables());
        }
    }
}
