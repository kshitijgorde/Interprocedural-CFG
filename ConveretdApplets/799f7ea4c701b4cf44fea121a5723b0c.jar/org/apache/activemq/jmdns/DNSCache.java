// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.util.Collections;
import java.util.Iterator;
import java.util.HashMap;
import java.util.logging.Logger;

class DNSCache
{
    private static Logger logger;
    private int size;
    private HashMap hashtable;
    
    public DNSCache(final int size) {
        this.hashtable = new HashMap(size);
    }
    
    public synchronized void clear() {
        this.hashtable.clear();
        this.size = 0;
    }
    
    public synchronized void add(final DNSEntry entry) {
        final CacheNode newValue = new CacheNode(entry);
        final CacheNode node = this.hashtable.get(entry.getName());
        if (node == null) {
            this.hashtable.put(entry.getName(), newValue);
        }
        else {
            newValue.next = node.next;
            node.next = newValue;
        }
        ++this.size;
    }
    
    public synchronized boolean remove(final DNSEntry entry) {
        CacheNode node = this.hashtable.get(entry.getName());
        if (node != null) {
            if (node.value == entry) {
                if (node.next == null) {
                    this.hashtable.remove(entry.getName());
                }
                else {
                    this.hashtable.put(entry.getName(), node.next);
                }
                --this.size;
                return true;
            }
            CacheNode previous = node;
            for (node = node.next; node != null; node = node.next) {
                if (node.value == entry) {
                    previous.next = node.next;
                    --this.size;
                    return true;
                }
                previous = node;
            }
        }
        return false;
    }
    
    public synchronized DNSEntry get(final DNSEntry entry) {
        for (CacheNode node = this.find(entry.getName()); node != null; node = node.next) {
            if (node.value.equals(entry)) {
                return node.value;
            }
        }
        return null;
    }
    
    public synchronized DNSEntry get(final String name, final int type, final int clazz) {
        for (CacheNode node = this.find(name); node != null; node = node.next) {
            if (node.value.type == type && node.value.clazz == clazz) {
                return node.value;
            }
        }
        return null;
    }
    
    public Iterator iterator() {
        return Collections.unmodifiableCollection(this.hashtable.values()).iterator();
    }
    
    public synchronized CacheNode find(final String name) {
        return this.hashtable.get(name);
    }
    
    public synchronized void print() {
        for (CacheNode n : this) {
            while (n != null) {
                System.out.println(n.value);
                n = n.next;
            }
        }
    }
    
    @Override
    public synchronized String toString() {
        final StringBuffer aLog = new StringBuffer();
        aLog.append("\t---- cache ----");
        for (CacheNode n : this) {
            while (n != null) {
                aLog.append("\n\t\t" + n.value);
                n = n.next;
            }
        }
        return aLog.toString();
    }
    
    static {
        DNSCache.logger = Logger.getLogger(DNSCache.class.toString());
    }
    
    public static class CacheNode
    {
        private static Logger logger;
        private DNSEntry value;
        private CacheNode next;
        
        public CacheNode(final DNSEntry value) {
            this.value = value;
        }
        
        public CacheNode next() {
            return this.next;
        }
        
        public DNSEntry getValue() {
            return this.value;
        }
        
        static {
            CacheNode.logger = Logger.getLogger(CacheNode.class.toString());
        }
    }
}
