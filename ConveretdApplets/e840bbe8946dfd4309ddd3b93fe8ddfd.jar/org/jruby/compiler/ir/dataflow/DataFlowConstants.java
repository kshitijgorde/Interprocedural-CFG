// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.dataflow;

import org.jruby.compiler.ir.dataflow.analyses.BindingStorePlacementProblem;
import org.jruby.compiler.ir.dataflow.analyses.BindingLoadPlacementProblem;
import org.jruby.compiler.ir.dataflow.analyses.LiveVariablesProblem;
import org.jruby.compiler.ir.operands.Operand;

public class DataFlowConstants
{
    public static final String LVP_NAME;
    public static final String BLP_NAME;
    public static final String BSP_NAME;
    public static final Operand TOP;
    public static final Operand BOTTOM;
    public static final Operand ANY;
    
    static {
        LVP_NAME = new LiveVariablesProblem().getName();
        BLP_NAME = new BindingLoadPlacementProblem().getName();
        BSP_NAME = new BindingStorePlacementProblem().getName();
        TOP = new LatticeTop();
        BOTTOM = new LatticeBottom();
        ANY = new Anything();
    }
    
    private static class LatticeBottom extends Operand
    {
        public String toString() {
            return "bottom";
        }
    }
    
    private static class LatticeTop extends Operand
    {
        public String toString() {
            return "top";
        }
    }
    
    private static class Anything extends Operand
    {
        public String toString() {
            return "anything";
        }
    }
}
