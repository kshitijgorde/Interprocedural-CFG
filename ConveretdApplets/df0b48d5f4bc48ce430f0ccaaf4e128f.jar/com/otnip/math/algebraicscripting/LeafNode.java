// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.math.algebraicscripting;

public class LeafNode implements ScriptingNode
{
    private double[] vector;
    private double scalar;
    private DataType dataType;
    
    public LeafNode() {
        this.dataType = DataType.NULL;
    }
    
    public LeafNode(final double data) {
        this.scalar = data;
        this.dataType = DataType.SCALAR;
    }
    
    public LeafNode(final double[] data) {
        if (data.length == 1) {
            this.scalar = data[0];
            this.dataType = DataType.SCALAR;
        }
        else {
            this.vector = data;
            this.dataType = DataType.VECTOR;
        }
    }
    
    public LeafNode getLeafNode() {
        return this;
    }
    
    public double[] getVector() {
        return this.vector;
    }
    
    public double getScalar() {
        return this.scalar;
    }
    
    public DataType getNodeType() {
        return this.dataType;
    }
    
    public enum DataType
    {
        VECTOR, 
        SCALAR, 
        NULL;
    }
}
