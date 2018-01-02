// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor;

import anon.tor.cells.Cell;

final class CellQueue
{
    private CellQueueEntry m_firstEntry;
    private CellQueueEntry m_lastEntry;
    private int m_iSize;
    
    public CellQueue() {
        this.m_firstEntry = null;
        this.m_lastEntry = null;
        this.m_iSize = 0;
    }
    
    public synchronized void addElement(final Cell cell) {
        final CellQueueEntry cellQueueEntry = new CellQueueEntry(cell);
        if (this.m_lastEntry == null) {
            final CellQueueEntry cellQueueEntry2 = cellQueueEntry;
            this.m_lastEntry = cellQueueEntry2;
            this.m_firstEntry = cellQueueEntry2;
        }
        else {
            this.m_lastEntry.m_next = cellQueueEntry;
            this.m_lastEntry = cellQueueEntry;
        }
        ++this.m_iSize;
    }
    
    public synchronized Cell removeElement() {
        if (this.m_firstEntry == null) {
            return null;
        }
        final Cell cell = this.m_firstEntry.m_Cell;
        this.m_firstEntry = this.m_firstEntry.m_next;
        if (this.m_firstEntry == null) {
            this.m_lastEntry = null;
        }
        --this.m_iSize;
        return cell;
    }
    
    public synchronized int size() {
        return this.m_iSize;
    }
    
    public synchronized boolean isEmpty() {
        return this.m_firstEntry == null;
    }
    
    final class CellQueueEntry
    {
        Cell m_Cell;
        CellQueueEntry m_next;
        
        CellQueueEntry(final Cell cell) {
            this.m_Cell = cell;
            this.m_next = null;
        }
    }
}
