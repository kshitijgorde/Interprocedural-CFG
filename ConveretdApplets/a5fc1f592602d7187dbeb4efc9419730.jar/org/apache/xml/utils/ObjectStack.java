// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import java.util.EmptyStackException;

public class ObjectStack extends ObjectVector
{
    public ObjectStack() {
    }
    
    public ObjectStack(final int blocksize) {
        super(blocksize);
    }
    
    public ObjectStack(final ObjectStack v) {
        super(v);
    }
    
    public Object push(final Object i) {
        if (super.m_firstFree + 1 >= super.m_mapSize) {
            super.m_mapSize += super.m_blocksize;
            final Object[] newMap = new Object[super.m_mapSize];
            System.arraycopy(super.m_map, 0, newMap, 0, super.m_firstFree + 1);
            super.m_map = newMap;
        }
        super.m_map[super.m_firstFree] = i;
        ++super.m_firstFree;
        return i;
    }
    
    public Object pop() {
        final Object[] map = super.m_map;
        final int firstFree = super.m_firstFree - 1;
        super.m_firstFree = firstFree;
        final Object val = map[firstFree];
        super.m_map[super.m_firstFree] = null;
        return val;
    }
    
    public void quickPop(final int n) {
        super.m_firstFree -= n;
    }
    
    public Object peek() {
        try {
            return super.m_map[super.m_firstFree - 1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }
    
    public Object peek(final int n) {
        try {
            return super.m_map[super.m_firstFree - (1 + n)];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }
    
    public void setTop(final Object val) {
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
    
    public int search(final Object o) {
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
