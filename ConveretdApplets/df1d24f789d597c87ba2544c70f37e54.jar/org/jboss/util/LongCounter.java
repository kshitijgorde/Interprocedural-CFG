// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.io.Serializable;

public class LongCounter implements Serializable, Cloneable
{
    static final long serialVersionUID = 1615297462859270139L;
    private long count;
    
    public LongCounter(final long count) {
        this.count = count;
    }
    
    public LongCounter() {
    }
    
    public long increment() {
        return ++this.count;
    }
    
    public long decrement() {
        return --this.count;
    }
    
    public long getCount() {
        return this.count;
    }
    
    public void reset() {
        this.count = 0L;
    }
    
    public boolean equals(final Object obj) {
        return obj == this || (obj != null && obj.getClass() == this.getClass() && ((LongCounter)obj).count == this.count);
    }
    
    public String toString() {
        return String.valueOf(this.count);
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
    
    public static LongCounter makeSynchronized(final LongCounter counter) {
        return new Wrapper(counter) {
            public synchronized long increment() {
                return this.counter.increment();
            }
            
            public synchronized long decrement() {
                return this.counter.decrement();
            }
            
            public synchronized long getCount() {
                return this.counter.getCount();
            }
            
            public synchronized void reset() {
                this.counter.reset();
            }
            
            public synchronized int hashCode() {
                return this.counter.hashCode();
            }
            
            public synchronized boolean equals(final Object obj) {
                return this.counter.equals(obj);
            }
            
            public synchronized String toString() {
                return this.counter.toString();
            }
            
            public synchronized Object clone() {
                return this.counter.clone();
            }
        };
    }
    
    public static LongCounter makeDirectional(final LongCounter counter, final boolean increasing) {
        LongCounter temp;
        if (increasing) {
            temp = new Wrapper(counter) {
                public long decrement() {
                    throw new UnsupportedOperationException();
                }
                
                public void reset() {
                    throw new UnsupportedOperationException();
                }
            };
        }
        else {
            temp = new Wrapper(counter) {
                public long increment() {
                    throw new UnsupportedOperationException();
                }
            };
        }
        return temp;
    }
    
    private static class Wrapper extends LongCounter
    {
        protected final LongCounter counter;
        
        public Wrapper(final LongCounter counter) {
            this.counter = counter;
        }
        
        public long increment() {
            return this.counter.increment();
        }
        
        public long decrement() {
            return this.counter.decrement();
        }
        
        public long getCount() {
            return this.counter.getCount();
        }
        
        public void reset() {
            this.counter.reset();
        }
        
        public boolean equals(final Object obj) {
            return this.counter.equals(obj);
        }
        
        public String toString() {
            return this.counter.toString();
        }
        
        public Object clone() {
            return this.counter.clone();
        }
    }
}
