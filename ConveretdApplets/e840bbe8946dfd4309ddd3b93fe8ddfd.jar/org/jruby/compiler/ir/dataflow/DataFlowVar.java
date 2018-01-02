// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.dataflow;

public class DataFlowVar
{
    public final int _id;
    
    public DataFlowVar(final DataFlowProblem prob) {
        this._id = prob.addDataFlowVar(this);
    }
}
