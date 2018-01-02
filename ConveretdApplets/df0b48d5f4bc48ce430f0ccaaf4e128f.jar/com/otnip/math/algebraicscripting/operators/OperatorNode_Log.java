// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.math.algebraicscripting.operators;

import com.otnip.math.algebraicscripting.LeafNode;
import com.otnip.math.algebraicscripting.ScriptingNode;
import com.otnip.math.algebraicscripting.OperatorNode;

public class OperatorNode_Log extends OperatorNode
{
    public OperatorNode_Log(final ScriptingNode node) {
        super(node, new LeafNode());
    }
    
    protected double[] operate(final double[] a) throws Exception {
        for (int i = 0; i < a.length; ++i) {
            a[i] = Math.log(a[i]);
        }
        return a;
    }
    
    protected double operate(final double x) throws Exception {
        return Math.log(x);
    }
}
