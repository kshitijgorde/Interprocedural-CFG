// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.compiler_pass;

import org.jruby.compiler.ir.operands.MethAddr;
import java.util.Iterator;
import org.jruby.compiler.ir.IRModule;
import org.jruby.compiler.ir.representations.CFG;
import org.jruby.compiler.ir.instructions.CallInstr;
import org.jruby.compiler.ir.instructions.Instr;
import org.jruby.compiler.ir.representations.BasicBlock;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.compiler.ir.IRScope;

public class InlineTest implements CompilerPass
{
    public final String methodToInline;
    
    public InlineTest(final String m) {
        this.methodToInline = m;
    }
    
    public boolean isPreOrder() {
        return true;
    }
    
    public void run(final IRScope s) {
        if (s instanceof IRMethod) {
            final CFG c = ((IRMethod)s).getCFG();
            final IRModule m = s.getNearestModule();
            final IRMethod mi = m.getInstanceMethod(this.methodToInline);
            for (final BasicBlock b : c.getNodes()) {
                for (final Instr i : b.getInstrs()) {
                    if (i instanceof CallInstr) {
                        final CallInstr call = (CallInstr)i;
                        final MethAddr addr = call.getMethodAddr();
                        if (this.methodToInline.equals(addr.getName())) {
                            System.out.println("Will be inlining method " + this.methodToInline + " at callsite: " + call);
                            c.inlineMethod(mi, b, call);
                            return;
                        }
                        continue;
                    }
                }
            }
        }
    }
}
