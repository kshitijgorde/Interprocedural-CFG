// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.util.concurrent;

import java.io.Serializable;

public class J2SE14ReadWriteLock implements ReadWriteLock, Serializable
{
    final J2SE14ReadWriteLock$ReadLock a;
    final J2SE14ReadWriteLock$WriteLock b;
    final J2SE14ReadWriteLock$Sync c;
    
    public J2SE14ReadWriteLock() {
        this.a = new J2SE14ReadWriteLock$ReadLock(this);
        this.b = new J2SE14ReadWriteLock$WriteLock(this);
        this.c = new J2SE14ReadWriteLock$NonfairSync();
    }
    
    public Lock a() {
        return this.b;
    }
    
    public Lock b() {
        return this.a;
    }
    
    protected Thread c() {
        return this.c.j();
    }
    
    public int d() {
        return this.c.k();
    }
    
    public int e() {
        return this.c.m();
    }
    
    public String toString() {
        return super.toString() + "[Write locks = " + this.e() + ", Read locks = " + this.d() + "]";
    }
}
