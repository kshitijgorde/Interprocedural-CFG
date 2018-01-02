// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

import java.util.EmptyStackException;
import java.io.Serializable;

public class StackOfDouble implements Serializable
{
    private double[] data;
    private int top;
    
    public StackOfDouble() {
        this.data = new double[1];
    }
    
    public StackOfDouble(final int n) {
        this.data = new double[(n > 0) ? n : 1];
    }
    
    public void push(final double n) {
        if (this.top >= this.data.length) {
            final double[] data = new double[2 * this.data.length];
            System.arraycopy(this.data, 0, data, 0, this.data.length);
            this.data = data;
        }
        this.data[this.top++] = n;
    }
    
    public double pop() {
        if (this.top == 0) {
            throw new EmptyStackException();
        }
        final double[] data = this.data;
        final int top = this.top - 1;
        this.top = top;
        return data[top];
    }
    
    public boolean isEmpty() {
        return this.top == 0;
    }
    
    public void makeEmpty() {
        this.top = 0;
    }
    
    public int size() {
        return this.top;
    }
}
