// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public abstract class ComputeNode implements IComputeNode
{
    private static int sid;
    private List<IComputeNode> dependencies;
    private boolean hasValue;
    private float value;
    private int id;
    private boolean cycle;
    
    static {
        ComputeNode.sid = 0;
    }
    
    public ComputeNode() {
        this.id = ++ComputeNode.sid;
    }
    
    @Override
    public int getID() {
        return this.id;
    }
    
    @Override
    public void addDependency(final IComputeNode computeNode) {
        if (this.dependencies == null) {
            this.dependencies = new ArrayList<IComputeNode>(1);
        }
        if (!this.dependencies.contains(computeNode)) {
            this.dependencies.add(0, computeNode);
        }
    }
    
    @Override
    public void removeDependency(final IComputeNode computeNode) {
        if (this.dependencies != null) {
            this.dependencies.remove(computeNode);
        }
    }
    
    @Override
    public void clearDependencies() {
        if (this.dependencies != null) {
            this.dependencies.clear();
        }
    }
    
    @Override
    public List<IComputeNode> getDependencies() {
        return (this.dependencies == null) ? Collections.emptyList() : this.dependencies;
    }
    
    @Override
    public boolean hasXHandle() {
        return false;
    }
    
    @Override
    public boolean hasYHandle() {
        return false;
    }
    
    @Override
    public boolean hasValue() {
        return this.hasValue;
    }
    
    @Override
    public void reset() {
        this.hasValue = false;
    }
    
    @Override
    public void update() {
        for (final IComputeNode computeNode : this.getDependencies()) {
            if (computeNode.hasValue()) {
                this.setValue(computeNode.getValue());
                break;
            }
        }
    }
    
    @Override
    public float getValue() {
        return this.value;
    }
    
    @Override
    public void setValue(final float value) {
        this.value = value;
        this.hasValue = true;
    }
    
    protected String printValue() {
        return this.hasValue() ? String.format("%2.1f", this.getValue()) : "?";
    }
    
    protected String printDependencies() {
        if (this.dependencies == null || this.dependencies.size() == 0) {
            return "";
        }
        if (this.cycle) {
            return " ...";
        }
        this.cycle = true;
        final StringBuilder sb = new StringBuilder();
        for (final IComputeNode computeNode : this.dependencies) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(computeNode.getID());
        }
        this.cycle = false;
        return sb.toString();
    }
}
