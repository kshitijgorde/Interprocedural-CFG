// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.math.algebraicscripting;

public abstract class OperatorNode implements ScriptingNode
{
    protected ScriptingNode leftNode;
    protected ScriptingNode rightNode;
    
    protected OperatorNode(final ScriptingNode leftNode, final ScriptingNode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
    
    protected double[] operate(final double[] a, final double[] b) throws Exception {
        throw new Exception("Operation Not Defined:  " + this.getClass().getName());
    }
    
    protected double[] operate(final double[] a, final double b) throws Exception {
        throw new Exception("Operation Not Defined");
    }
    
    protected double[] operate(final double a, final double[] b) throws Exception {
        throw new Exception("Operation Not Defined");
    }
    
    protected double operate(final double a, final double b) throws Exception {
        throw new Exception("Operation Not Defined");
    }
    
    protected double operate(final double x) throws Exception {
        throw new Exception("Operation Not Defined");
    }
    
    protected double[] operate(final double[] x) throws Exception {
        throw new Exception("Operation Not Defined:  " + this.getClass());
    }
    
    public LeafNode getLeafNode() throws Exception {
        final LeafNode a = this.leftNode.getLeafNode();
        final LeafNode b = this.rightNode.getLeafNode();
        LeafNode c = null;
        if (a.getNodeType() == LeafNode.DataType.NULL || b.getNodeType() == LeafNode.DataType.NULL) {
            LeafNode node = a;
            if (node.getNodeType() == LeafNode.DataType.NULL) {
                node = b;
            }
            if (node.getNodeType() == LeafNode.DataType.SCALAR) {
                c = new LeafNode(this.operate(node.getScalar()));
            }
            else {
                c = new LeafNode(this.operate(node.getVector()));
            }
        }
        else if (a.getNodeType() == LeafNode.DataType.VECTOR && b.getNodeType() == LeafNode.DataType.VECTOR) {
            final double[] dataC = this.operate(a.getVector(), b.getVector());
            c = new LeafNode(dataC);
        }
        else if (a.getNodeType() == LeafNode.DataType.SCALAR && b.getNodeType() == LeafNode.DataType.SCALAR) {
            final double dataC2 = this.operate(a.getScalar(), b.getScalar());
            c = new LeafNode(dataC2);
        }
        else {
            double[] dataC = null;
            if (a.getNodeType() == LeafNode.DataType.VECTOR) {
                dataC = this.operate(a.getVector(), b.getScalar());
            }
            else {
                dataC = this.operate(a.getScalar(), b.getVector());
            }
            c = new LeafNode(dataC);
        }
        return c;
    }
}
