// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public class Traversal
{
    int nMoves;
    int currentDepth;
    int[] depth;
    int[] x;
    int[] y;
    int[] z;
    
    public Traversal(final int capacity, final int maxElims) {
        this.x = new int[capacity];
        this.y = new int[capacity];
        this.z = new int[capacity];
        this.depth = new int[capacity];
        final boolean b = false;
        this.currentDepth = (b ? 1 : 0);
        this.nMoves = (b ? 1 : 0);
    }
    
    public boolean addMove(final int mx, final int my, final int mz) {
        if (this.nMoves == this.x.length) {
            System.out.println("Traversal overload " + this.nMoves);
            return false;
        }
        this.x[this.nMoves] = mx;
        this.y[this.nMoves] = my;
        this.z[this.nMoves] = mz;
        this.depth[this.nMoves++] = this.currentDepth++;
        return true;
    }
    
    public boolean unwind() {
        if (this.currentDepth == 0 || this.nMoves == this.x.length) {
            return false;
        }
        --this.currentDepth;
        return true;
    }
    
    public int find(final int start, final int end, final int[] findX, final int[] findY, final int[] findZ, final int nFindMoves) {
        int s;
        for (int e = this.nextEnd(start); e < end; e = this.nextEnd(s)) {
            final int d;
            if ((d = this.match(start, e, findX, findY, findZ, nFindMoves)) == 1 + this.depth[e - 1]) {
                return e;
            }
            s = this.nextStart(e, d);
        }
        return -1;
    }
    
    public int find(final int[] findX, final int[] findY, final int[] findZ, final int nFindMoves) {
        return this.find(0, this.nMoves, findX, findY, findZ, nFindMoves);
    }
    
    int nextStart(int position, final int startDepth) {
        while (++position < this.nMoves && this.depth[position] != startDepth) {}
        return position;
    }
    
    int nextEnd(int position) {
        while (++position < this.nMoves && this.depth[position] == this.depth[position - 1] + 1) {}
        return position;
    }
    
    int match(final int start, int end, final int[] matchX, final int[] matchY, final int[] matchZ, final int nMatchMoves) {
        int d = (end > 0) ? (1 + this.depth[end - 1]) : 0;
        if (d > nMatchMoves) {
            return nMatchMoves;
        }
        int lowestMismatchDepth = d;
        while (--end >= start) {
            if (this.depth[end] >= d) {
                continue;
            }
            --d;
            int i;
            for (i = 0; i < nMatchMoves && (this.x[end] != matchX[i] || this.y[end] != matchY[i] || this.z[end] != matchZ[i]); ++i) {}
            if (i != nMatchMoves) {
                continue;
            }
            lowestMismatchDepth = d;
        }
        return lowestMismatchDepth;
    }
    
    public void reset() {
        final boolean b = false;
        this.currentDepth = (b ? 1 : 0);
        this.nMoves = (b ? 1 : 0);
    }
    
    public int size() {
        return this.nMoves;
    }
    
    public int depth() {
        return this.currentDepth;
    }
    
    public int capacity() {
        return this.x.length;
    }
    
    public String toString() {
        return this.toString(0, this.nMoves);
    }
    
    String toString(final int start, int end) {
        final StringBuffer sb = new StringBuffer();
        int d = (end > 0) ? (1 + this.depth[end - 1]) : 0;
        while (--end >= start) {
            if (this.depth[end] >= d) {
                continue;
            }
            sb.append(String.valueOf(end) + ". [" + this.depth[end] + "](" + this.x[end] + "," + this.y[end] + "," + this.z[end] + ")\n");
            --d;
        }
        return sb.toString();
    }
}
