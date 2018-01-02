// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

public class IntStack extends IntVector
{
    public IntStack() {
    }
    
    public IntStack(final int blocksize) {
        super(blocksize);
    }
    
    public boolean empty() {
        return super.m_firstFree == 0;
    }
    
    public int peek() {
        return super.m_map[super.m_firstFree - 1];
    }
    
    public int pop() {
        final int[] map = super.m_map;
        final int firstFree = super.m_firstFree - 1;
        super.m_firstFree = firstFree;
        return map[firstFree];
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
    
    public void quickPop(final int n) {
        super.m_firstFree -= n;
    }
    
    public int search(final int o) {
        final int i = this.lastIndexOf(o);
        if (i >= 0) {
            return this.size() - i;
        }
        return -1;
    }
    
    public void setTop(final int val) {
        super.m_map[super.m_firstFree - 1] = val;
    }
}
