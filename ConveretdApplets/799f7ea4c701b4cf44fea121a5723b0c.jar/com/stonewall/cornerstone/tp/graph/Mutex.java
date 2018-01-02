// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Mutex extends ReentrantReadWriteLock
{
    private static Mutex inst;
    static final long serialVersionUID = 0L;
    
    static {
        Mutex.inst = new Mutex();
    }
    
    public static Mutex instance() {
        return Mutex.inst;
    }
    
    protected void lock(final LM mode) {
        switch (mode) {
            case Read: {
                this.readLock().lock();
                break;
            }
            case Write: {
                this.writeLock().lock();
                break;
            }
        }
    }
    
    protected void unlock(final LM mode) {
        switch (mode) {
            case Read: {
                this.readLock().unlock();
                break;
            }
            case Write: {
                this.writeLock().unlock();
                break;
            }
        }
    }
    
    public enum LM
    {
        Read("Read", 0), 
        Write("Write", 1);
        
        private LM(final String s, final int n) {
        }
    }
}
