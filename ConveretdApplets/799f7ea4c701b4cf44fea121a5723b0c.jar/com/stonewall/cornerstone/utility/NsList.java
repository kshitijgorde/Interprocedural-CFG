// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import org.jdom.Namespace;
import java.util.List;

class NsList extends ThreadLocal<List<Namespace>>
{
    private final ReentrantLock lock;
    
    NsList() {
        this.lock = new ReentrantLock();
    }
    
    @Override
    protected List<Namespace> initialValue() {
        this.lock.lock();
        try {
            return new ArrayList<Namespace>();
        }
        finally {
            this.lock.unlock();
        }
    }
}
