// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.math.algebraicscripting.operators;

import com.otnip.math.algebraicscripting.ScriptingNode;
import com.otnip.math.algebraicscripting.LeafNode;
import com.otnip.math.algebraicscripting.OperatorNode;

public class OperatorNode_Constant extends OperatorNode
{
    private LeafNode constant;
    
    public OperatorNode_Constant(final double x) {
        super(new LeafNode(), new LeafNode());
        this.constant = new LeafNode(x);
    }
    
    public OperatorNode_Constant(final double[] x) {
        super(null, null);
        this.constant = new LeafNode(x);
    }
    
    public LeafNode getLeafNode() {
        return this.constant;
    }
}
