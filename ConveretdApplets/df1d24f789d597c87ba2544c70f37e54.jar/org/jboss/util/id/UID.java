// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.id;

import EDU.oswego.cs.dl.util.concurrent.SynchronizedLong;

public class UID implements ID
{
    private static final long serialVersionUID = -8093336932569424512L;
    protected static final SynchronizedLong COUNTER;
    protected final long time;
    protected final long id;
    
    public UID() {
        this.time = System.currentTimeMillis();
        this.id = UID.COUNTER.increment();
    }
    
    protected UID(final UID uid) {
        this.time = uid.time;
        this.id = uid.id;
    }
    
    public final long getTime() {
        return this.time;
    }
    
    public final long getID() {
        return this.id;
    }
    
    public String toString() {
        return Long.toString(this.time, 36) + "-" + Long.toString(this.id, 36);
    }
    
    public int hashCode() {
        return (int)this.id;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == this.getClass()) {
            final UID uid = (UID)obj;
            return uid.time == this.time && uid.id == this.id;
        }
        return false;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
    
    public static String asString() {
        return new UID().toString();
    }
    
    static {
        COUNTER = new SynchronizedLong(0L);
    }
}
