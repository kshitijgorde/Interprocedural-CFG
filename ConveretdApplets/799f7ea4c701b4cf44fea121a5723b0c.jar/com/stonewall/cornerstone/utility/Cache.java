// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.Hashtable;
import java.util.concurrent.locks.ReentrantLock;
import org.jdom.xpath.XPath;
import java.util.Map;

class Cache extends ThreadLocal<Map<String, XPath>>
{
    private final ReentrantLock lock;
    
    Cache() {
        this.lock = new ReentrantLock();
    }
    
    @Override
    protected Map<String, XPath> initialValue() {
        this.lock.lock();
        try {
            return new Hashtable<String, XPath>();
        }
        finally {
            this.lock.unlock();
        }
    }
}
