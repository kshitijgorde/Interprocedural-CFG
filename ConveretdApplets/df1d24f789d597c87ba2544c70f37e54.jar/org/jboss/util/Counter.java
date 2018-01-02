// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.io.Serializable;

public class Counter implements Serializable, Cloneable
{
    static final long serialVersionUID = 7736259185393081556L;
    private int count;
    
    public Counter(final int count) {
        this.count = count;
    }
    
    public Counter() {
    }
    
    public int increment() {
        return ++this.count;
    }
    
    public int decrement() {
        return --this.count;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public void reset() {
        this.count = 0;
    }
    
    public boolean equals(final Object obj) {
        return obj == this || (obj != null && obj.getClass() == this.getClass() && ((Counter)obj).count == this.count);
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
    
    public static Counter makeSynchronized(final Counter counter) {
        return new Wrapper(counter) {
            public synchronized int increment() {
                return this.counter.increment();
            }
            
            public synchronized int decrement() {
                return this.counter.decrement();
            }
            
            public synchronized int getCount() {
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
    
    public static Counter makeDirectional(final Counter counter, final boolean increasing) {
        Counter temp;
        if (increasing) {
            temp = new Wrapper(counter) {
                public int decrement() {
                    throw new UnsupportedOperationException();
                }
                
                public void reset() {
                    throw new UnsupportedOperationException();
                }
            };
        }
        else {
            temp = new Wrapper(counter) {
                public int increment() {
                    throw new UnsupportedOperationException();
                }
            };
        }
        return temp;
    }
    
    private static class Wrapper extends Counter
    {
        protected final Counter counter;
        
        public Wrapper(final Counter counter) {
            this.counter = counter;
        }
        
        public int increment() {
            return this.counter.increment();
        }
        
        public int decrement() {
            return this.counter.decrement();
        }
        
        public int getCount() {
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
