// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.math.algebraicscripting.operators;

import com.otnip.math.algebraicscripting.ScriptingNode;
import com.otnip.math.algebraicscripting.OperatorNode;

public class OperatorNode_Add extends OperatorNode
{
    public OperatorNode_Add(final ScriptingNode left, final ScriptingNode right) {
        super(left, right);
    }
    
    protected double[] operate(final double[] a, final double[] b) {
        final int N = a.length;
        final double[] c = new double[N];
        for (int i = 0; i < N; ++i) {
            c[i] = a[i] + b[i];
        }
        return c;
    }
    
    protected double[] operate(final double[] a, final double b) {
        final int N = a.length;
        final double[] c = new double[N];
        for (int i = 0; i < N; ++i) {
            c[i] = a[i] + b;
        }
        return c;
    }
    
    protected double[] operate(final double a, final double[] b) {
        return this.operate(b, a);
    }
    
    protected double operate(final double a, final double b) {
        return a + b;
    }
}