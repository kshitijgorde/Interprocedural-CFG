// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import java.util.EmptyStackException;

public class IntStack extends IntVector
{
    public IntStack() {
    }
    
    public IntStack(final int blocksize) {
        super(blocksize);
    }
    
    public IntStack(final IntStack v) {
        super(v);
    }
    
    public int push(final int i) {
        if (super.m_firstFree + 1 >= super.m_mapSize) {
            super.m_mapSize += super.m_blocksize;
            final int[] newMap = new int[super.m_mapSize];
            System.arraycopy(super.m_map, 0, newMap, 0, super.m_firstFree + 1);
            super.m_map = newMap;
        }
        super.m_map[super.m_firstFree] = i;
        ++super.m_firstFree;
        return i;
    }
    
    public final int pop() {
        final int[] map = super.m_map;
        final int firstFree = super.m_firstFree - 1;
        super.m_firstFree = firstFree;
        return map[firstFree];
    }
    
    public final void quickPop(final int n) {
        super.m_firstFree -= n;
    }
    
    public final int peek() {
        try {
            return super.m_map[super.m_firstFree - 1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }
    
    public int peek(final int n) {
        try {
            return super.m_map[super.m_firstFree - (1 + n)];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }
    
    public void setTop(final int val) {
        try {
            super.m_map[super.m_firstFree - 1] = val;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }
    
    public boolean empty() {
        return super.m_firstFree == 0;
    }
    
    public int search(final int o) {
        final int i = this.lastIndexOf(o);
        if (i >= 0) {
            return this.size() - i;
        }
        return -1;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
