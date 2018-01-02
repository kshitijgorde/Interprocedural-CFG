// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.concurrent.locks.ReentrantLock;

public class IdentityFactory
{
    private final ReentrantLock lock;
    private final MyRandom random;
    
    public IdentityFactory() {
        this.lock = new ReentrantLock();
        this.random = new MyRandom();
    }
    
    public String next() {
        this.lock.lock();
        try {
            return this.random.next().toUpperCase();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    class MyRandom extends SecureRandom
    {
        static final long serialVersionUID = 0L;
        
        String next() {
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; ++i) {
                sb.append(Integer.toString(this.next(31), 36));
            }
            return sb.substring(0, 9);
        }
    }
}
