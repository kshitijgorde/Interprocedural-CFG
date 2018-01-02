// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

public final class IntStack
{
    private int fDepth;
    private int[] fData;
    
    public int size() {
        return this.fDepth;
    }
    
    public void push(final int n) {
        this.ensureCapacity(this.fDepth + 1);
        this.fData[this.fDepth++] = n;
    }
    
    public int peek() {
        return this.fData[this.fDepth - 1];
    }
    
    public int pop() {
        final int[] fData = this.fData;
        final int fDepth = this.fDepth - 1;
        this.fDepth = fDepth;
        return fData[fDepth];
    }
    
    public void clear() {
        this.fDepth = 0;
    }
    
    public void print() {
        System.out.print('(');
        System.out.print(this.fDepth);
        System.out.print(") {");
        for (int i = 0; i < this.fDepth; ++i) {
            if (i == 3) {
                System.out.print(" ...");
                break;
            }
            System.out.print(' ');
            System.out.print(this.fData[i]);
            if (i < this.fDepth - 1) {
                System.out.print(',');
            }
        }
        System.out.print(" }");
        System.out.println();
    }
    
    private boolean ensureCapacity(final int n) {
        try {
            return this.fData[n] != 0;
        }
        catch (NullPointerException ex) {
            this.fData = new int[32];
        }
        catch (ArrayIndexOutOfBoundsException ex2) {
            final int[] fData = new int[this.fData.length * 2];
            System.arraycopy(this.fData, 0, fData, 0, this.fData.length);
            this.fData = fData;
        }
        return true;
    }
}
